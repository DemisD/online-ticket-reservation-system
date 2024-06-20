/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bussiness.ScheduleBeanLocal;

import bussiness.TicketreservationtblBeanLocal;
import bussiness.UserBeanLocal;
import commonapp.utility.JsfUtil;
import entity.Bustbl;
import entity.BustblDetal;
import entity.Routetbl;
import entity.Scheduletbl;
import entity.Ticketreservationtbl;
import entity.Usertbl;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import mapper.BustblDetalFacade;

import mapper.BustblFacade;
import mapper.RoutetblFacade;
import mapper.ScheduletblFacade;
import mapper.TicketreservationtblFacade;
import mapper.UsertblFacade;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author demis
 */
@Named(value = "scheduleController")
@SessionScoped
public class ScheduleController implements Serializable {

    /**
     * Creates a new instance of ScheduleController
     */
    public ScheduleController() {
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
    Scheduletbl tblName;
    @Inject
    Bustbl bustbl;
    @Inject
    loginController loginControllerr;

    @Inject
    Ticketreservationtbl ticketReservationTbl;

    private DataModel<Scheduletbl> scheduletblModel;
    private Scheduletbl selectedItem;
    private String saveorUpdateBundle = "Save";
    String title = "Schedule";
    int updteStatus = 0;
    Date scheduleDate;
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//    DateFormat localDateFormat = new SimpleDateFormat("hh:mm");
    private DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    Date destinationTime;
    Date despatureTime;
    List<BustblDetal> detailLis;
    Date currentDate = new Date();
    Boolean booleanStatus = false;
    Boolean renderdAdmin = true;

    @PostConstruct
    public void initialize() {
        String userName = loginControllerr.getUserName();
        System.out.println("------admin txt ----- " + userName);
        usertbl = usertblFacade.getUserDataByUserName(userName);
        if (usertbl.getRole().equalsIgnoreCase("Admin")) {
            title = "Schedule";
            renderdAdmin = true;
            recerateDataModel();
        } else {
            title = " ";
            renderdAdmin = false;
            recerateDataModelPassager();
        }
        tblFacade.updateStatusToInactive();

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Usertbl getUsertbl() {
        if (usertbl == null) {
            usertbl = new Usertbl();
        }
        return usertbl;
    }

    public void setUsertbl(Usertbl usertbl) {
        this.usertbl = usertbl;
    }

    public Boolean getRenderdAdmin() {
        return renderdAdmin;
    }

    public void setRenderdAdmin(Boolean renderdAdmin) {
        this.renderdAdmin = renderdAdmin;
    }

    public Boolean getBooleanStatus() {
        return booleanStatus;
    }

    public void setBooleanStatus(Boolean booleanStatus) {
        this.booleanStatus = booleanStatus;
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

    public Scheduletbl getTblName() {
        if (tblName == null) {
            tblName = new Scheduletbl();
        }
        return tblName;
    }

    public void setTblName(Scheduletbl tblName) {
        this.tblName = tblName;
    }

    public DataModel<Scheduletbl> getScheduletblModel() {
        if (scheduletblModel == null) {
            scheduletblModel = new ListDataModel<>();
        }
        return scheduletblModel;
    }

    public void setScheduletblModel(DataModel<Scheduletbl> scheduletblModel) {
        this.scheduletblModel = scheduletblModel;
    }

    public Scheduletbl getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Scheduletbl selectedItem) {
        this.selectedItem = selectedItem;
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
        tblName = null;
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
//                String despatureTimeStr = localDateFormat.format(despatureTime);
//                String destinationTimeStr = localDateFormat.format(destinationTime);
//                System.out.println("ot   dest 1 " + destinationTimeStr);
                tblName.setScheduleDate(scheduleDateStr);
//                tblName.setDestinationTime(destinationTimeStr);
//                tblName.setDespatureTime(despatureTimeStr);
                tblName.setStatus("Active");//Active and Expired
                scheduleBeanLocal.create(tblName);
                JsfUtil.addSuccessMessage("Successfully Saved");
                recerateDataModel();
                clearPage();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                String scheduleDateStr = dateFormat.format(scheduleDate);
//                String despatureTimeStr = localDateFormat.format(despatureTime);
//                String destinationTimeStr = localDateFormat.format(destinationTime);
//                System.out.println("ot   dest 1 " + destinationTimeStr);
                tblName.setScheduleDate(scheduleDateStr);
//                tblName.setDestinationTime(destinationTimeStr);
//                tblName.setDespatureTime(despatureTimeStr);
                tblName.setStatus("Active");//Active and Expired
//                tblName.setScheduleDate(scheduleDateStr);
//                tblName.setDespatureTime(destinationTimeStr);
                scheduleBeanLocal.edit(tblName);
                JsfUtil.addSuccessMessage("Successfully Updated");
                clearPage();
            } catch (Exception ex) {
                JsfUtil.addFatalMessage("Something Occured on Data Updated");
                ex.printStackTrace();
            }

        }
        return null;
    }

    public void recerateDataModel() {
        scheduletblModel = null;
        List<Scheduletbl> tblList;
        tblList = tblFacade.searchAllInfo();
        System.out.println("size " + tblList.size());

//        for (int i = 0; i < tblList.size(); i++) {
//            if (tblList.get(i).getStatus().equalsIgnoreCase("Expired")) {
//                System.out.println("size ");
//                booleanStatus = true;
//            } else if (tblList.get(i).getStatus().equalsIgnoreCase("Active")) {
//                booleanStatus = false;
//            }  
//        }
        scheduletblModel = new ListDataModel(tblList);
        for (Scheduletbl item : scheduletblModel) {
            if (item.getStatus().equalsIgnoreCase("Expired")) {
                System.out.println(" check");
                booleanStatus = true;

            }
            if (item.getStatus().equalsIgnoreCase("Active")) {
                booleanStatus = false;

            }
        }

    }

    public void recerateDataModelPassager() {
        scheduletblModel = null;
        List<Scheduletbl> tblList;
//        tblList = tblFacade.searchAllInfo();
        tblList = tblFacade.searchAllInfoStatusIsActive();
        System.out.println("size " + tblList.size());
        scheduletblModel = new ListDataModel(tblList);
        for (Scheduletbl item : scheduletblModel) {
            if (item.getStatus().equalsIgnoreCase("Expired")) {
                System.out.println(" check");
                booleanStatus = true;

            }
            if (item.getStatus().equalsIgnoreCase("Active")) {
                booleanStatus = false;

            }
        }

    }

    public void getBooleanChange() {
        for (Scheduletbl item : scheduletblModel) {
            if (item.getStatus().equalsIgnoreCase("Expired")) {
                System.out.println(" check");
                booleanStatus = true;

            }
            if (item.getStatus().equalsIgnoreCase("Active")) {
                booleanStatus = false;

            }
        }
    }

    public void getrowData(SelectEvent event) throws ParseException {
        tblName = (Scheduletbl) event.getObject();

        bustbl = tblName.getBusId();
        routetbl = tblName.getRouteId();
        try {
            scheduleDate = dateFormat.parse(tblName.getScheduleDate());
            destinationTime = timeFormat.parse(tblName.getDestinationTime());
            despatureTime = timeFormat.parse(tblName.getDespatureTime());

        } catch (Exception e) {
            e.printStackTrace();// Logger.getGlobal().log((LogRecord) (Supplier<String>) e);// 
        }
        updteStatus = 1;
        saveorUpdateBundle = "Update";

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
            tblName.setBusId(bustbl);

        }
    }

    String routelist;

    public void changeRouteTypeList(ValueChangeEvent event) {
        if (event.getNewValue() != null) {
            String etype = event.getNewValue().toString();
            routelist = event.getNewValue().toString();
            routetbl.setRouteName(etype);
            routetbl = routetblFacade.searchRouteByRouteName(routetbl);
            tblName.setRouteId(routetbl);

        }

    }

    public void changeRouteSeatNoList(ValueChangeEvent event) {
        if (event.getNewValue() != null) {
            String etype = event.getNewValue().toString();
//            routelist = event.getNewValue().toString();
            bustblDetal.setSeatNo(etype);
            bustbl = tblName.getBusId();
            System.out.println(" seat no 1    " + bustblDetal.getSeatNo());
            bustblDetal = bustblDetalFacade.searchBusSeatNo(bustblDetal, bustbl);
            ticketReservationTbl.setBusSeatNoId(bustblDetal);

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

    public String deleteAction(Scheduletbl scheduletbl) {
        scheduleBeanLocal.delet(scheduletbl);
        recerateDataModel();
        return null;
    }

    public String populatScheduleData(Scheduletbl scheduletbl) {
        tblName = scheduletbl;

        detailLis = bustblDetalFacade.searchAllBusSeatNo(tblName);
        System.out.println("bus No size2 " + detailLis.size());

        String nextTicketNo = ticketReservationTblFacade.generateNextTicketNo();
        System.out.println("nextTicketNo  " + nextTicketNo);
        ticketReservationTbl.setTicketNo(nextTicketNo);
//        scheduleBeanLocal.delet(scheduletbl);
//        recerateDataModel();
        return null;
    }

    public String savePassangerReservation() {
        if (!(ticketReservationTblFacade.checkDipositSlipNoDuplicated(ticketReservationTbl))) {
            try {

                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                String currentDateString = dateFormat.format(currentDate);

                ticketReservationTbl.setReservDate(currentDateString);
                ticketReservationTbl.setStatus("Pend");//Pend, or Active or  Expired
                ticketReservationTbl.setScheduleId(tblName);

                ticketReservationTbl.setUserId(usertbl);

                ticketReservationBeanLocal.create(ticketReservationTbl);
                JsfUtil.addSuccessMessage("Successfully Saved");
                clearPageReserv();
                FacesContext.getCurrentInstance().getExternalContext().redirect("ticketReservationPage.xhtml");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            JsfUtil.addFatalMessage("Diposit Slip No is Duplicated");
        }

        return null;
    }

    private String clearPageReserv() {
        bustblDetal = null;
        ticketReservationTbl = null;
//        tblName = null;
//        routetbl = null;
//        scheduleDate = null;
//        despatureTime = null;
//        destinationTime = null;
//        bustbl = null;
        saveorUpdateBundle = "Save";
        updteStatus = 0;
        return null;
    }

    public void dispatureTimeEvent(SelectEvent event) {
        if (event.getObject() != null) {
            despatureTime = (Date) event.getObject();
            String despatureTimeStr = timeFormat.format(despatureTime);
            tblName.setDespatureTime(despatureTimeStr);
//                tblName.setDespatureTime(destinationTimeStr);

        }
    }
    
    public void scheduleDateDateEvent(SelectEvent event) {
        if (event.getObject() != null) {
            scheduleDate = (Date) event.getObject();
            Date currentDate = new Date();

            System.out.println("Selected scheduleDate Date: " + scheduleDate);
            System.out.println("Current Date: " + currentDate);

            try {
                // Formatting dates
                String currentDateString = dateFormat.format(currentDate);
                String scheduleDateDateString = dateFormat.format(scheduleDate);

                System.out.println("Formatted Current Date: " + currentDateString);
                System.out.println("Formatted scheduleDateDateString Date: " + scheduleDateDateString);

                // Parsing dates
                Date currentDateParsed = dateFormat.parse(currentDateString);
                Date scheduleDateStringParsed = dateFormat.parse(scheduleDateDateString);

                System.out.println("Parsed Current Date: " + currentDateParsed);
                System.out.println("Parsed Expiration Date: " + scheduleDateStringParsed);

                // Comparing dates
                if (scheduleDateStringParsed.before(currentDateParsed)) {

                    JsfUtil.addFatalMessage("Schedule date should be greater than the current date");
                    scheduleDate = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    

    public void destinationTimeEvent(SelectEvent event) {
        if (event.getObject() != null) {
            destinationTime = (Date) event.getObject();
            String destinationTimeStr = timeFormat.format(destinationTime);
            tblName.setDestinationTime(destinationTimeStr);
//                tblName.setDespatureTime(destinationTimeStr);

        }
    }
}
