/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bussiness.GenerateReportBean;
import bussiness.ScheduleBeanLocal;
import bussiness.Singleton;
import bussiness.TicketreservationtblBeanLocal;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import static com.sun.javafx.logging.PulseLogger.addMessage;
import commonapp.utility.JsfUtil;
import entity.Bustbl;
import entity.BustblDetal;
import entity.Routetbl;
import entity.Scheduletbl;
import entity.Ticketreservationtbl;
import entity.Usertbl;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.inject.Named;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mapper.BustblDetalFacade;
import mapper.BustblFacade;
import mapper.RoutetblFacade;
import mapper.ScheduletblFacade;
import mapper.TicketreservationtblFacade;
import mapper.UsertblFacade;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author demis
 */
@Named(value = "ticketReservationBean")
@ViewScoped
public class ticketReservationController implements Serializable {

    /**
     * Creates a new instance of ticketReservationBean
     */
    public ticketReservationController() {
        this.saveorUpdateBundle = "Save";

    }
    @EJB
    ScheduleBeanLocal scheduleBeanLocal;
    @EJB
    TicketreservationtblBeanLocal ticketReservationBeanLocal;
    @EJB
    RoutetblFacade routetblFacade;
    @EJB
    BustblDetalFacade bustblDetalFacade;
    @EJB
    ScheduletblFacade tblFacade;
    @EJB
    TicketreservationtblFacade ticketReservationTblFacade;
    @EJB
    private GenerateReportBean generateReport;
    @EJB
    BustblFacade bustblFacade;
    @EJB
    UsertblFacade usertblFacade;
//    @EJB
//    UserBeanLocal beanLocal;
    @Inject
    Usertbl usertbl;
    @Inject
    BustblDetal bustblDetal;
    @Inject
    Routetbl routetbl;
    @Inject
    Scheduletbl scheduletbl;
    @Inject
    Bustbl bustbl;

    @Inject
    Ticketreservationtbl ticketReservationTbl;
    @Inject
    loginController loginControllerr;
    private DataModel<Ticketreservationtbl> TicketreservationtblDataModel;
    private Ticketreservationtbl selectedItem;
    private String saveorUpdateBundle = "Save";
    int updteStatus = 0;
    Date scheduleDate;
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    DateFormat localDateFormat = new SimpleDateFormat("hh:mm");
    Date destinationTime;
    Date despatureTime;
    List<BustblDetal> detailLis;
    List<Ticketreservationtbl> TicketreservationtblList;
    Date currentDate = new Date();
    boolean renderedStatus = false;
    private String reportFormat = "pdf";
     private String reportName = "printReservTicket";

    @PostConstruct
    public void initialize() {
        System.out.println("cureent date is ");
        ticketReservationTblFacade.updateStatusToInactive();

        if (loginControllerr.getUserName() != null) {
            usertbl = usertblFacade.getUserDataByUserName(loginControllerr.getUserName());

            if (usertbl.getRole().equalsIgnoreCase("admin")) {
                renderedStatus = true;
                recerateDataModel();
            } else {
                System.out.println("user role " + usertbl.getUserName());
                recerateDataModelUser();
                renderedStatus = false;
            }
        }

//        if (loginControllerr.getUserName() == null) {
//            try {
//                System.out.println("check the loging");   
//                FacesContext.getCurrentInstance().getExternalContext().redirect("/TRS-war/loginPage.xhtml");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }

    public void getloginPage() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/TRS-war/loginPage.xhtml");
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportFormat() {
        return reportFormat;
    }

    public void setReportFormat(String reportFormat) {
        this.reportFormat = reportFormat;
    }

    public boolean isRenderedStatus() {
        return renderedStatus;
    }

    public void setRenderedStatus(boolean renderedStatus) {
        this.renderedStatus = renderedStatus;
    }

    public Ticketreservationtbl getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Ticketreservationtbl selectedItem) {
        this.selectedItem = selectedItem;
    }

    public Date getDestinationTime() {
        return destinationTime;
    }

    public void setDestinationTime(Date destinationTime) {
        this.destinationTime = destinationTime;
    }

    public Date getDespatureTime() {
        return despatureTime;
    }

    public void setDespatureTime(Date despatureTime) {
        this.despatureTime = despatureTime;
    }

    public BustblDetal getBustblDetal() {
        if (bustblDetal == null) {
            bustblDetal = new BustblDetal();
        }
        return bustblDetal;
    }

    public void setBustblDetal(BustblDetal bustblDetal) {
        this.bustblDetal = bustblDetal;
    }

    public Routetbl getRoutetbl() {
        if (routetbl == null) {
            routetbl = new Routetbl();
        }
        return routetbl;
    }

    public void setRoutetbl(Routetbl routetbl) {
        this.routetbl = routetbl;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public Ticketreservationtbl getTicketReservationTbl() {
        if (ticketReservationTbl == null) {
            ticketReservationTbl = new Ticketreservationtbl();
        }
        return ticketReservationTbl;
    }

    public void setTicketReservationTbl(Ticketreservationtbl ticketReservationTbl) {
        this.ticketReservationTbl = ticketReservationTbl;
    }

    public Bustbl getBustbl() {
        if (bustbl == null) {
            bustbl = new Bustbl();
        }
        return bustbl;
    }

    public void setBustbl(Bustbl bustbl) {
        this.bustbl = bustbl;
    }

    public Scheduletbl getScheduletbl() {
        if (scheduletbl == null) {
            scheduletbl = new Scheduletbl();
        }
        return scheduletbl;
    }

    public void setScheduletbl(Scheduletbl scheduletbl) {
        this.scheduletbl = scheduletbl;
    }

    public DataModel<Ticketreservationtbl> getTicketreservationtblDataModel() {
        if (TicketreservationtblDataModel == null) {
            TicketreservationtblDataModel = new ListDataModel<>();
        }
        return TicketreservationtblDataModel;
    }

    public void setTicketreservationtblDataModel(DataModel<Ticketreservationtbl> TicketreservationtblDataModel) {
        this.TicketreservationtblDataModel = TicketreservationtblDataModel;
    }

    public String getSaveorUpdateBundle() {
        return saveorUpdateBundle;
    }

    public void setSaveorUpdateBundle(String saveorUpdateBundle) {
        this.saveorUpdateBundle = saveorUpdateBundle;
    }

    public List<BustblDetal> getDetailLis() {
        return detailLis;
    }

    public void setDetailLis(List<BustblDetal> detailLis) {
        this.detailLis = detailLis;
    }

    private String clearPage() {
        scheduletbl = null;
        routetbl = null;
        scheduleDate = null;
        despatureTime = null;
        destinationTime = null;
        bustbl = null;
        saveorUpdateBundle = "Save";
        updteStatus = 0;
        return null;
    }

    public String saveUpdate() {
        if (updteStatus == 0) {
            try {
                String scheduleDateStr = dateFormat.format(scheduleDate);
                String despatureTimeStr = localDateFormat.format(despatureTime);
                String destinationTimeStr = localDateFormat.format(destinationTime);
                System.out.println("ot   dest 1 " + destinationTimeStr);
                scheduletbl.setScheduleDate(scheduleDateStr);
                scheduletbl.setDestinationTime(destinationTimeStr);
                scheduletbl.setDespatureTime(despatureTimeStr);
                scheduletbl.setStatus("Active");//Active and Expired
                scheduleBeanLocal.create(scheduletbl);
                JsfUtil.addSuccessMessage("Successfully Saved");
                recerateDataModel();
                clearPage();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                String scheduleDateStr = dateFormat.format(scheduleDate);
                String despatureTimeStr = localDateFormat.format(despatureTime);
                String destinationTimeStr = localDateFormat.format(destinationTime);
                System.out.println("ot   dest 1 " + destinationTimeStr);
                scheduletbl.setScheduleDate(scheduleDateStr);
                scheduletbl.setDestinationTime(destinationTimeStr);
                scheduletbl.setDespatureTime(despatureTimeStr);
                scheduletbl.setStatus("Active");//Active and Expired
                scheduletbl.setScheduleDate(scheduleDateStr);
                scheduletbl.setDespatureTime(destinationTimeStr);
                scheduleBeanLocal.edit(scheduletbl);
                JsfUtil.addSuccessMessage("Successfully Updated");
                clearPage();
            } catch (Exception ex) {
                JsfUtil.addFatalMessage("Something Occured on Data Updated");
                ex.printStackTrace();
            }

        }
        return null;
    }

    public String updatePassangerReservation() {

        try {

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String currentDateString = dateFormat.format(currentDate);

            ticketReservationTbl.setReservDate(currentDateString);
//            ticketReservationTbl.setStatus("Pend");//Pend, or Active or  Expired
            ticketReservationTbl.setScheduleId(scheduletbl);
            ticketReservationBeanLocal.edit(ticketReservationTbl);
            JsfUtil.addSuccessMessage("Successfully Updated");
            clearPageReserv();
//             FacesContext.getCurrentInstance().getExternalContext().redirect("ticketReservationPage.xhtml");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    private String clearPageReserv() {
        bustblDetal = null;
        ticketReservationTbl = null;
        scheduletbl = null;
        bustblDetal = null;
//        tblName = null;
//        routetbl = null;
//        scheduleDate = null;
//        despatureTime = null;
//        destinationTime = null;
//        bustbl = null;

        return null;
    }

    public void recerateDataModel() {
        TicketreservationtblDataModel = null;
        List<Ticketreservationtbl> tblList;
        tblList = ticketReservationTblFacade.searchAllInfo();
        System.out.println("size " + tblList.size());
        TicketreservationtblDataModel = new ListDataModel(tblList);

    }

    public void recerateDataModelUser() {
        TicketreservationtblDataModel = null;
        List<Ticketreservationtbl> tblList;
        tblList = ticketReservationTblFacade.searchAllInfoByUserName(usertbl);
        System.out.println("size " + tblList.size());
        TicketreservationtblDataModel = new ListDataModel(tblList);

    }

    public void getrowData(SelectEvent event) throws ParseException {
        ticketReservationTbl = (Ticketreservationtbl) event.getObject();
        scheduletbl = ticketReservationTbl.getScheduleId();

        bustbl = scheduletbl.getBusId();
        routetbl = scheduletbl.getRouteId();
        detailLis = bustblDetalFacade.searchAllBusSeatNo(scheduletbl);
        bustblDetal = ticketReservationTbl.getBusSeatNoId();
        System.out.println(" seat No is " + bustblDetal.getSeatNo());
    }

    public List<Bustbl> searchBusList(String nameList) {

        ArrayList<Bustbl> busNameLst = null;
        bustbl.setBusNumber(nameList);
        busNameLst = bustblFacade.searchBusByNumber(bustbl);
        return busNameLst;
    }

    public void getSelectBusInfo(SelectEvent event) {
        if (event.getObject() != null && event.getObject() != "") {
            bustbl.setBusNumber(event.getObject().toString());
            bustbl = bustblFacade.searchBusObjectByNo(bustbl);
            scheduletbl.setBusId(bustbl);

        }
    }

    String routelist;

    public void changeRouteTypeList(ValueChangeEvent event) {
        if (event.getNewValue() != null) {
            String etype = event.getNewValue().toString();
            routelist = event.getNewValue().toString();
            routetbl.setRouteName(etype);
            routetbl = routetblFacade.searchRouteByRouteName(routetbl);
            scheduletbl.setRouteId(routetbl);

        }

    }

    public void changeRouteSeatNoList(ValueChangeEvent event) {
        if (event.getNewValue() != null) {
            String etype = event.getNewValue().toString();
//            routelist = event.getNewValue().toString();
            bustblDetal.setSeatNo(etype);
            bustblDetal = bustblDetalFacade.searchBusSeatNo(bustblDetal, bustbl);
            ticketReservationTbl.setBusSeatNoId(bustblDetal);
            System.out.println(" seat no    " + ticketReservationTbl.getBusSeatNoId().getSeatNo());

        }

    }

    public List<BustblDetal> getSeatNoList() {

        detailLis = bustblDetalFacade.searchAllBusSeatInfo();

//         return bustblDetalFacade.searchAllBusSeatNo(tblName);
        return detailLis;

    }

    public List<Routetbl> getTypeList() {
        return routetblFacade.searchAllRoutInfo();
    }

    public ArrayList<Routetbl> getTypeList1() {
        return routetblFacade.getTypListByRoute(routelist);

    }

    public String deleteAction(Ticketreservationtbl ticketreservationtbl) {
        ticketReservationBeanLocal.delet(ticketreservationtbl);
        recerateDataModel();
        return null;
    }

    public String populatScheduleData(Ticketreservationtbl ticketreservationtbl) throws ParseException {
//        scheduletbl = scheduletbl;
        ticketReservationTbl = ticketReservationTblFacade.searchTicketReservDataByTckNo(ticketreservationtbl);
//        TicketreservationtblList = ticketReservationTblFacade.searchAllTByTckNo(ticketReservationTbl);
        // Generate PDF using iText
        btnGenerateReport();

        return null;
    }

    //SELECT
//           ts.firstName AS first_name,
//           ts.fatherName AS middle_name,
//           rt.routeName   AS routeName,
//           bus.busNumber AS busNo,
//           busd.seatNo   AS seatNo,
//           sc.scheduleDate   AS scheduleDate,
//           sc.despatureTime   AS despatureTime,
//           ts.dipositSlipNo   AS dipositSlipNo,
//           sc.price   AS Amount,
//           ts.ReservDate   AS paymentDate
//
//
//
//FROM ticketreservationtbl AS ts
//INNER JOIN bustbl_detal AS busd ON ts.busSeatNoId = busd.detailId
//INNER JOIN bustbl AS bus ON busd.busId = bus.id
//
//INNER JOIN scheduletbl AS sc ON bus.id = sc.busId
//INNER JOIN routetbl AS rt ON sc.routeId = rt.routerId
//
// WHERE
//ts.ticketNo = 'TCK-00002';
    private void addWatermark(Document document, String imagePath) throws IOException, DocumentException {
        Image image = Image.getInstance(imagePath);
        float width = document.getPageSize().getWidth();
        float height = document.getPageSize().getHeight();
        float absoluteX = (width - image.getScaledWidth()) / 2;
        float absoluteY = (height - image.getScaledHeight()) / 2;
        image.setAbsolutePosition(absoluteX, absoluteY);
        PdfContentByte contentByte = PdfWriter.getInstance(document, new FileOutputStream("temp.pdf")).getDirectContentUnder();
        contentByte.addImage(image);
    }

    public void btnGenerateReport() throws ParseException {

        Singleton singleton = Singleton.getInstance();
        HashMap hashMap = new HashMap();

        String fileName = "";
        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();

        System.out.println(" getTicketNo " + ticketReservationTbl.getTicketNo());
         System.out.println(" scheduleDate " + ticketReservationTbl.getScheduleId().getScheduleDate());
        hashMap.put("ticketNo", ticketReservationTbl.getTicketNo());
         hashMap.put("scheduleDate", ticketReservationTbl.getScheduleId().getScheduleDate());
        fileName = servletContext.getRealPath("report/printTicketReport.jasper");
        singleton.setFormat(getReportFormat());
        singleton.setFileName(fileName);
//        singleton.setDownloadedReportName("Studentmarkreport");
        singleton.setParam(hashMap);
        singleton.setReportIndex("printReservTicket");
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

    private void getClearInputParameter() {
//        reportFormat = null;
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
//        rs = generateReport.ticketReservationReport(param);
        rs = generateReport.printReservTicket(param);
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

}
