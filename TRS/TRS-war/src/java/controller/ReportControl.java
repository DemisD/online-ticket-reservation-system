// 
// Decompiled by Procyon v0.5.36
// 

package controller;
  
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import java.util.Locale;
import java.util.Iterator;
import net.sf.jasperreports.engine.JasperPrint;
import javax.servlet.ServletOutputStream;
import net.sf.jasperreports.engine.JRException;
import java.sql.SQLException;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.JRDataSource;
import java.util.Map;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import oracle.jdbc.rowset.OracleCachedRowSet;
import java.io.ByteArrayInputStream;
import net.sf.jasperreports.engine.fill.JRSwapFileVirtualizer;
import net.sf.jasperreports.engine.util.JRSwapFile;
import java.util.Collection; 
import java.sql.ResultSet;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.File;

public class ReportControl
{
    public static byte[] getBytesFromFile(final File file) throws IOException {
        final InputStream is = new FileInputStream(file);
        final long length = file.length();
        if (length > 2147483647L) {
            throw new IOException(" File is too large " + file.getName());
        }
        final byte[] bytes = new byte[(int)length];
        int offset = 0;
        for (int numRead = 0; offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0; offset += numRead) {}
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }
        is.close();
        return bytes;
    }
    
    public static void generateReportRS(final HttpServletResponse response, final HttpServletRequest request, final String format, final String fileName, final String downloadedReportName, final HashMap param, final ResultSet resultset) {
        generateReport(response, request, format, fileName, downloadedReportName, param, resultset);
    }
    
    public static void generateReportColl(final HttpServletResponse response, final HttpServletRequest request, final String format, final String fileName, final String downloadedReportName, final HashMap param, final Collection collection) {
        generateReport(response, request, format, fileName, downloadedReportName, param, collection);
    }
    
    private static void generateReport(final HttpServletResponse response, final HttpServletRequest request, final String format, final String fileName, final String downloadedReportName, final HashMap param, final Object data) {
        try {
            String _path = System.getProperty("user.home");
            _path = _path + File.separatorChar + "ReportData";
            final File swapFileVirtualizer = new File(_path);
            if (!swapFileVirtualizer.isFile()) {
                swapFileVirtualizer.mkdir();
            }
            final JRSwapFileVirtualizer fileVirtualizer = new JRSwapFileVirtualizer(10, new JRSwapFile(_path, 2048, 2048));
            param.put("REPORT_VIRTUALIZER", fileVirtualizer);
            final File jasperFile = new File(fileName);
            ServletOutputStream outputStream = null;
            final byte[] reportDesignByte = getBytesFromFile(jasperFile);
            final ByteArrayInputStream reportDesign = new ByteArrayInputStream(reportDesignByte);
            JasperPrint jsPrint = null;
            if (data != null) {
                if (data instanceof ResultSet) {
                    final ResultSet resultset = (ResultSet)data;
                    final OracleCachedRowSet cachedResultSet = new OracleCachedRowSet();
                    cachedResultSet.populate(resultset);
                    if (cachedResultSet.next()) {
                        cachedResultSet.beforeFirst();
                        final JRResultSetDataSource dataSource = new JRResultSetDataSource((ResultSet)cachedResultSet);
                        jsPrint = JasperFillManager.fillReport((InputStream)reportDesign, (Map)param, (JRDataSource)dataSource);
                    }
                }
                else if (data instanceof Collection) {
                    final Collection collection = (Collection)data;
                    for (Object next : collection) {}
                    final JRBeanCollectionDataSource dataSource2 = new JRBeanCollectionDataSource(collection);
                    jsPrint = JasperFillManager.fillReport((InputStream)reportDesign, (Map)param, (JRDataSource)dataSource2);
                }
                if (format.equalsIgnoreCase("html")) {
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("text/html;charset=UTF-8");
                    outputStream = response.getOutputStream();
                    formatReportAsHtmlStream(jsPrint, outputStream, request);
                }
                else if (format.equalsIgnoreCase("pdf")) {
                    response.setContentType("application/pdf");
                    response.setHeader("Cache-Control", "no-cache");
                    response.setHeader("Cache-Control", "max-age=0");
                    response.setHeader("Content-disposition", "attachment; filename=" + downloadedReportName + ".pdf");
                    outputStream = response.getOutputStream();
                    formatReportAsPdfStream(jsPrint, outputStream);
                    System.out.println(" format  " + jsPrint);
                }
                else if (format.equalsIgnoreCase("xlsx")) {
                    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                    response.setHeader("Cache-Control", "no-cache");
                    response.setHeader("Cache-Control", "max-age=0");
                    outputStream = response.getOutputStream();
                    response.setHeader("Content-disposition", "attachment; filename=" + downloadedReportName + ".xlsx");
                    response.flushBuffer();
                    formatReportAsXlsStream(jsPrint, outputStream);
                }
                else if (format.equalsIgnoreCase("docx")) {
                    response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                    response.setHeader("Cache-Control", "no-cache");
                    response.setHeader("Cache-Control", "max-age=0");
                    response.setHeader("Content-disposition", "attachment; filename=" + downloadedReportName + ".docx");
                    outputStream = response.getOutputStream();
                    response.flushBuffer();
                    formatReportAsDocxStream(jsPrint, outputStream);
                }
                else if (format.equalsIgnoreCase("pptx")) {
                    response.setContentType("application/vnd.openxmlformats-officedocument.presentationml.presentation");
                    response.setHeader("Cache-Control", "no-cache");
                    response.setHeader("Cache-Control", "max-age=0");
                    response.setHeader("Content-disposition", "attachment; filename=" + downloadedReportName + ".pptx");
                    outputStream = response.getOutputStream();
                    response.flushBuffer();
                    formatReportAsPptxStream(jsPrint, outputStream);
                }
                else if (format.equalsIgnoreCase("odt")) {
                    response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                    response.setHeader("Cache-Control", "no-cache");
                    response.setHeader("Cache-Control", "max-age=0");
                    response.setHeader("Content-disposition", "attachment; filename=" + downloadedReportName + ".docx");
                    outputStream = response.getOutputStream();
                    response.flushBuffer();
                    formatReportAsOdtStream(jsPrint, outputStream);
                }
                else if (format.equalsIgnoreCase("cvs")) {
                    response.setContentType("application/ms-excel");
                    response.setHeader("Cache-Control", "no-cache");
                    response.setHeader("Cache-Control", "max-age=0");
                    response.setHeader("Content-disposition", "attachment; filename=" + downloadedReportName + ".cvs");
                    outputStream = response.getOutputStream();
                    response.flushBuffer();
                    formatReportAsOdtStream(jsPrint, outputStream);
                }
            }
            else {
                final FacesContext context = FacesContext.getCurrentInstance();
                final Locale locale = context.getViewRoot().getLocale();
                String message;
                if (locale.toString().compareToIgnoreCase("am_ET") == 0) {
                    if (data == null) {
                        message = "\u12e8 \u12f3\u1273 \u124b\u1275 \u121b\u130d\u1298\u1275 \u12a0\u120d\u127b\u1208\u121d .";
                    }
                    else {
                        message = "\u121d\u1295\u121d \u1218\u1228\u1303 \u12e8\u1208\u121d ";
                    }
                }
                else if (data == null) {
                    message = "Can not establish connection to Database.";
                }
                else {
                    message = "No Data Found";
                }
                outputStream.println("<html> <body> <center><h3> " + message + " " + "</h3> </center> </body> </html>");
                outputStream.close();
            }
            fileVirtualizer.cleanup();
        }
        catch (IOException ex) {}
        catch (SQLException ex2) {}
        catch (JRException ex3) {}
    }
    
    public static void formatReportAsHtmlStream(final JasperPrint jsPrint, final ServletOutputStream outputStream, final HttpServletRequest request) {
        try {
            final JRHtmlExporter htmlExporter = new JRHtmlExporter();
            htmlExporter.setParameter(JRExporterParameter.JASPER_PRINT, (Object)jsPrint);
            htmlExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, (Object)outputStream);
            htmlExporter.setParameter((JRExporterParameter)JRHtmlExporterParameter.IMAGES_URI, (Object)(request.getContextPath() + "/image?image="));
            htmlExporter.exportReport();
        }
        catch (Exception ex) {}
    }
    
    public static void formatReportAsPdfStream(final JasperPrint jsPrint, final ServletOutputStream outputStream) {
        try {
            final JRPdfExporter pdfExporter = new JRPdfExporter();
            pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, (Object)jsPrint);
            pdfExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, (Object)outputStream);
            pdfExporter.exportReport();
        }
        catch (Exception ex) {}
    }
    
    public static void formatReportAsXlsStream(final JasperPrint jsPrint, final ServletOutputStream outputStream) {
        try {
            final JRXlsxExporter xlsExporter = new JRXlsxExporter();
            xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, (Object)jsPrint);
            xlsExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, (Object)outputStream);
            xlsExporter.exportReport();
        }
        catch (Exception ex) {}
    }
    
    public static void formatReportAsDocxStream(final JasperPrint jsPrint, final ServletOutputStream outputStream) {
        try {
            final JRDocxExporter docxExporter = new JRDocxExporter();
            docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, (Object)jsPrint);
            docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, (Object)outputStream);
            docxExporter.exportReport();
        }
        catch (Exception ex) {}
    }
    
    public static void formatReportAsOdtStream(final JasperPrint jsPrint, final ServletOutputStream outputStream) {
        try {
            final JROdtExporter odtExporter = new JROdtExporter();
            odtExporter.setParameter(JRExporterParameter.JASPER_PRINT, (Object)jsPrint);
            odtExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, (Object)outputStream);
            odtExporter.exportReport();
        }
        catch (Exception ex) {}
    }
    
    public static void formatReportAsPptxStream(final JasperPrint jsPrint, final ServletOutputStream outputStream) {
        try {
            final JRPptxExporter pptxExporter = new JRPptxExporter();
            pptxExporter.setParameter(JRExporterParameter.JASPER_PRINT, (Object)jsPrint);
            pptxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, (Object)outputStream);
            pptxExporter.exportReport();
        }
        catch (Exception ex) {}
    }
    
    public static void formatReportAsCsvStream(final JasperPrint jsPrint, final ServletOutputStream outputStream) {
        try {
            final JRCsvExporter csvExporter = new JRCsvExporter();
            csvExporter.setParameter(JRExporterParameter.JASPER_PRINT, (Object)jsPrint);
            csvExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, (Object)outputStream);
            csvExporter.exportReport();
        }
        catch (Exception ex) {}
    }
}
