/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bussiness.GenerateReportBean;
import bussiness.Singleton;
import static com.sun.javafx.logging.PulseLogger.addMessage;
import commonapp.utility.JsfUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author AdG
 */
@Named(value = "reportController")
@ViewScoped
public class ReportController implements Serializable {

    
     @EJB
    private GenerateReportBean generateReport;
    /**
     * Creates a new instance of ReportController
     */
    public ReportController() {
    }
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    Date fromDate;
    Date ToDate;
    private String reportFormat;
      private String reportName = "ticketReservationReport";
    private boolean ajax;
    private String toDateStr, fromDateStr;
       byte[] imageLogo = null;
    private ImageIcon LOGO;

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public boolean isAjax() {
        return ajax;
    }

    public void setAjax(boolean ajax) {
        this.ajax = ajax;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return ToDate;
    }

    public void setToDate(Date ToDate) {
        this.ToDate = ToDate;
    }

    public String getReportFormat() {
        return reportFormat;
    }

    public void setReportFormat(String reportFormat) {
        this.reportFormat = reportFormat;
    }

    public void selectReportType(ValueChangeEvent e) {
        if (null != e.getNewValue() && !e.getNewValue().equals("")) {
            if (e.getNewValue().toString().equalsIgnoreCase("html")) {
                ajax = true;
            } else {
                ajax = false;
            }
        }

    }

    public void fromDateEvent(SelectEvent event) {
        if (event.getObject() != null) {
            fromDate = (Date) event.getObject();
            fromDateStr = dateFormat.format(fromDate);
//            tblName.setDespatureTime(despatureTimeStr);
//                tblName.setDespatureTime(destinationTimeStr);

        }
    }

    public void toDateEvent(SelectEvent event) {
        if (event.getObject() != null) {
            ToDate = (Date) event.getObject();
            toDateStr = dateFormat.format(ToDate);
//            tblName.setDespatureTime(despatureTimeStr);
//                tblName.setDespatureTime(destinationTimeStr);

        }
    }

    public void btnGenerateReport() throws ParseException {
        if (fromDate == null && ToDate == null) {
            JsfUtil.addFatalMessage("Pleas select the date");
        } else {

            Singleton singleton = Singleton.getInstance();
            HashMap hashMap = new HashMap();

            String fileName = "";
            FacesContext context = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
            System.out.println(" FromDate " + fromDateStr);
            hashMap.put("fromDate", fromDateStr);
            hashMap.put("toDate", toDateStr);
//            hashMap.put("banner", "resources/images/all_EPSE_banner.jpg");
            fileName = servletContext.getRealPath("report/ticketReservationReport.jasper");
            singleton.setFormat(getReportFormat());
            singleton.setFileName(fileName);
//            singleton.setDownloadedReportName("Studentmarkreport");
            singleton.setParam(hashMap);
            singleton.setReportIndex("ticketReservationReport");
            if (getReportFormat().equalsIgnoreCase("html")) {
                RequestContext.getCurrentInstance().execute("window.open('ReportViewer.xhtml', 'ReportViewer', ('width='+.75*screen.width+', "
                        + "height='+.75*screen.height+',  top='+.1*screen.height+', left='+.1*screen.width+', "
                        + "dependent=yes, menubar=yes,location=yes,resizable=yes,scrollbars=yes,status=no'));");
                getClearInputParameter();
            } else {
                generateReport();
                getClearInputParameter();
            }
        }

    }

    private void getClearInputParameter() {
        reportFormat = null;
        reportName = null;
      
    }
     public void generateReport() {

        Singleton singleton = Singleton.getInstance();
        FacesContext context = FacesContext.getCurrentInstance();

        HttpServletRequest request
                = (HttpServletRequest) context.getExternalContext().getRequest();

        HttpServletResponse response
                = (HttpServletResponse) context.getExternalContext().getResponse();

        int status = generateReports(response, request,
                singleton.getFormat(),
                singleton.getFileName(),
                singleton.getReportName(),
                singleton.getReportIndex(),
                singleton.getParam());
        singleton.getClearSingletonParameter();
        if (status == 0) {
            addMessage("Sorry; No Record Has Been Found!!");
        } else {
            FacesContext.getCurrentInstance().responseComplete();
        }

    }
     public int generateReports(HttpServletResponse response, HttpServletRequest request,
            String format, String fileName, String reportName, String reportIndex, HashMap param) {
        Collection rs = null;
//        if (reportIndex.equalsIgnoreCase("daily")) {
        rs = generateReport.ticketReservationReport(param);
        System.out.println("result list " + rs);
//        } else if (reportIndex.equalsIgnoreCase("newEmpPayrollReport")) {
//            rs = generateReport.taxCollReport(param);
//        }
        if (rs != null || (format.equalsIgnoreCase("html") && rs == null)) {
            ReportControl.generateReportColl(response, request, format, fileName, reportName, param, rs);
            return 1;
        } else {
            return 0;
        }

    }
    
//     public void btnGenerateReport() throws ParseException {
//
//        Singleton singleton = Singleton.getInstance();
//        HashMap hashMap = new HashMap();
//
//        String fileName = "";
//        FacesContext context = FacesContext.getCurrentInstance();
//        ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
//
////        System.out.println(" getTicketNo " + ticketReservationTbl.getTicketNo());
////         System.out.println(" scheduleDate " + ticketReservationTbl.getScheduleId().getScheduleDate());
//        hashMap.put("ticketNo", "TCK-00001");
//         hashMap.put("scheduleDate","2024/05/10");
//        fileName = servletContext.getRealPath("report/printTicketReport.jasper");
//        singleton.setFormat(getReportFormat());
//        singleton.setFileName(fileName);
////        singleton.setDownloadedReportName("Studentmarkreport");
//        singleton.setParam(hashMap);
//        singleton.setReportIndex("printReservTicket");
//        if (getReportFormat().equalsIgnoreCase("html")) {
//            RequestContext.getCurrentInstance().execute("window.open('ReportViewer.xhtml', 'ReportViewer', ('width='+.75*screen.width+', "
//                    + "height='+.75*screen.height+',  top='+.1*screen.height+', left='+.1*screen.width+', "
//                    + "dependent=yes, menubar=yes,location=yes,resizable=yes,scrollbars=yes,status=no'));");
//            getClearInputParameter();
//        } else {
//            generateReport();
//            getClearInputParameter();
//        }
//
//    }
//
//    private void getClearInputParameter() {
//        reportFormat = null;
//        reportName = null;
//
//    }
//
//    public void generateReport() {
//
//        Singleton singleton = Singleton.getInstance();
//        FacesContext context = FacesContext.getCurrentInstance();
//
//        HttpServletRequest request
//                = (HttpServletRequest) context.getExternalContext().getRequest();
//
//        HttpServletResponse response
//                = (HttpServletResponse) context.getExternalContext().getResponse();
//
//        int status = generateReports(response, request,
//                singleton.getFormat(),
//                singleton.getFileName(),
//                singleton.getReportName(),
//                singleton.getReportIndex(),
//                singleton.getParam());
//        singleton.getClearSingletonParameter();
//        if (status == 0) {
//            addMessage("Sorry; No Record Has Been Found!!");
//        } else {
//            FacesContext.getCurrentInstance().responseComplete();
//        }
//
//    }
//
//    public int generateReports(HttpServletResponse response, HttpServletRequest request,
//            String format, String fileName, String reportName, String reportIndex, HashMap param) {
//        Collection rs = null;
////        if (reportIndex.equalsIgnoreCase("daily")) {
////        rs = generateReport.ticketReservationReport(param);
//        rs = generateReport.printReservTicket(param);
//        System.out.println("result list " + rs);
////        } else if (reportIndex.equalsIgnoreCase("newEmpPayrollReport")) {
////            rs = generateReport.taxCollReport(param);
////        }
//        if (rs != null || (format.equalsIgnoreCase("html") && rs == null)) {
//            ReportControl.generateReportColl(response, request, format, fileName, reportName, param, rs);
//            return 1;
//        } else {
//            return 0;
//        }
//
//    }

}
