/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bussiness.BusBeanLocal;
import bussiness.BustblDetalBeanLocal;
import commonapp.utility.JsfUtil;
import entity.Bustbl;
import entity.BustblDetal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import mapper.BustblDetalFacade;
import mapper.BustblFacade;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author demis
 */
@Named(value = "busController")
@ViewScoped
public class busController implements Serializable {

    /**
     * Creates a new instance of busController
     */
    public busController() {
        this.saveorUpdateBundle = "Save";
    }
    @EJB
    BustblFacade bustblFacade;
    @EJB
    BustblDetalFacade bustblDetalFacade;
    @EJB
    BusBeanLocal busBeanLocal;
    @EJB
    BustblDetalBeanLocal bustblDetalBeanLocal;
    @Inject
    Bustbl bustbl;
    @Inject
    private BustblDetal bustblDetal;

    private DataModel<BustblDetal> busDetailsModel;
    private DataModel<Bustbl> bustblModel;
    private Bustbl selectedItem;
    private String saveorUpdateBundle;
    private String busName = "Zemen Bus";
    int updteStatus = 0;
    boolean seatNoRenderd = false;
    private List<String> selectedOptions;
//     private String tab = "disabled";
//    private String tabToggle = "tab";
    private String tab = "";
    private String tabToggle = "";
    private List<BustblDetal> bustblDetalList;
    private String tabSummery = "";
    private String tabDetail = "";

    private String tabActive = "tab-pane active";
    private String tabActiveSummery = "tab-pane";
    private String tabToggleDetail = "tab";
    private String tabToggleSummery = "tab";

    @PostConstruct
    public void initialize() {
        seatNoRenderd = false;
        recerateBusDataModel();
        tab = "disabled";
        tabToggle = "tab";

//        listOfStudentTbl = studentTblFacade.searchWaitingListInfo();
//        System.out.println("Size_________________" + listOfStudentTbl.size());
//        studentTblDataModelForWai = new ListDataModel(new ArrayList(listOfStudentTbl));
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public List<String> getSelectedOptions() {
        return selectedOptions;
    }

    public void setSelectedOptions(List<String> selectedOptions) {
        this.selectedOptions = selectedOptions;
    }

    public boolean isSeatNoRenderd() {
        return seatNoRenderd;
    }

    public void setSeatNoRenderd(boolean seatNoRenderd) {
        this.seatNoRenderd = seatNoRenderd;
    }

    public String getTabSummery() {
        return tabSummery;
    }

    public void setTabSummery(String tabSummery) {
        this.tabSummery = tabSummery;
    }

    public String getTabDetail() {
        return tabDetail;
    }

    public void setTabDetail(String tabDetail) {
        this.tabDetail = tabDetail;
    }

    public String getTabActive() {
        return tabActive;
    }

    public void setTabActive(String tabActive) {
        this.tabActive = tabActive;
    }

    public String getTabActiveSummery() {
        return tabActiveSummery;
    }

    public void setTabActiveSummery(String tabActiveSummery) {
        this.tabActiveSummery = tabActiveSummery;
    }

    public String getTabToggleDetail() {
        return tabToggleDetail;
    }

    public void setTabToggleDetail(String tabToggleDetail) {
        this.tabToggleDetail = tabToggleDetail;
    }

    public String getTabToggleSummery() {
        return tabToggleSummery;
    }

    public void setTabToggleSummery(String tabToggleSummery) {
        this.tabToggleSummery = tabToggleSummery;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public String getTabToggle() {
        return tabToggle;
    }

    public void setTabToggle(String tabToggle) {
        this.tabToggle = tabToggle;
    }

    public List<BustblDetal> getBustblDetalList() {
        return bustblDetalList;
    }

    public void setBustblDetalList(List<BustblDetal> bustblDetalList) {
        this.bustblDetalList = bustblDetalList;
    }

    public Bustbl getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Bustbl selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getSaveorUpdateBundle() {
        return saveorUpdateBundle;
    }

    public void setSaveorUpdateBundle(String saveorUpdateBundle) {
        this.saveorUpdateBundle = saveorUpdateBundle;
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

    public DataModel<BustblDetal> getBusDetailsModel() {
        if (busDetailsModel == null) {
            busDetailsModel = new ListDataModel<>();
        }
        return busDetailsModel;
    }

    public void setBusDetailsModel(DataModel<BustblDetal> busDetailsModel) {
        this.busDetailsModel = busDetailsModel;
    }

    public DataModel<Bustbl> getBustblModel() {
        if (bustblModel == null) {
            bustblModel = new ListDataModel<>();
        }
        return bustblModel;
    }

    public void setBustblModel(DataModel<Bustbl> bustblModel) {
        this.bustblModel = bustblModel;
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

    private String clearPage() {
        bustbl = null;
//        subjectTblModel = null;
        saveorUpdateBundle = "Save";
//        birthDate = null;
//        luKebeleTbl = null;
//        luTraidTypeTbl = null;
        updteStatus = 0;
        seatNoRenderd = false;
        return null;

    }

    public String addDetailData() {

        System.out.println("seat No " + bustbl.getSeatNumber());
        for (int i = 1; i < bustbl.getSeatNumber(); i++) {
            bustblDetal.setSeatNo("Seat No  " + i);
            System.out.println("seat No " + bustbl.getSeatNumber());
//            System.out.println("seat No i " + i);
            bustbl.addDetaill(bustblDetal);
            recreateDetailDataModel();
//            clearPopup();
        }
        System.out.println("seat No out " + bustbl.getBustblDetalList());
        return null;

    }

    private void recreateDetailDataModel() {
        busDetailsModel = null;
        busDetailsModel = new ListDataModel(new ArrayList<>(bustbl.getBustblDetalList()));
        System.out.println("sise is " + bustbl.getBustblDetalList().size());

    }

    private String clearPopup() {
        bustblDetal = null;
        return null;
    }

    public void getSeatNoList() {
        Integer size = bustbl.getSeatNumber();
        if (updteStatus == 0) {
            System.out.println("0000000000000000000 X ");
            for (int i = 1; i <= size; i++) {
                bustblDetal = new BustblDetal();
                bustblDetal.setSeatNo("Seat No " + i);
                bustbl.addDetaill(bustblDetal);
//            busDetailsModel = new ListDataModel(new ArrayList<>(bustbl.getBustblDetalList()));
                recreateDetailDataModel();
//            clearPopup();
            }
        } else {
            System.out.println("other 0 X ");
            for (int i = 1; i <= size; i++) {
                bustblDetal = new BustblDetal();
                bustblDetal.setSeatNo("Seat No " + i);
                boolean checkExistance = bustblDetalFacade.checkeBusSeatNo(bustblDetal, bustbl);
                if (checkExistance) {
                    recreateDetailDataModel();
                } else {
                    bustbl.addDetaill(bustblDetal);
                    recreateDetailDataModel();
//            clearPopup();
                }

            }

        }

    }

    public String saveUpdate() {
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX ");

        if (updteStatus == 0) {
            if (!(bustblFacade.checkBusDuplicated(bustbl))) {
                try {
//                addDetailData();

                    bustbl.setBusName(busName);
                    busBeanLocal.create(bustbl);
                    JsfUtil.addSuccessMessage("Successfully Saved");
                    recerateBusDataModel();
                    clearPage();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                JsfUtil.addFatalMessage("Bus Number is Duplicated");
            }
        } else {
            try {
                bustbl.setBusName(busName);
                busBeanLocal.edit(bustbl);
                JsfUtil.addSuccessMessage("Successfully Updated");
                clearPage();
            } catch (Exception ex) {
                JsfUtil.addFatalMessage("Something Occured on Data Updated");
                ex.printStackTrace();
            }

        }

        return null;

    }

    public void recerateBusDataModel() {
        bustblModel = null;
        List<Bustbl> busTblList;

        busTblList = bustblFacade.searchAllBusInfo();

        bustblModel = new ListDataModel(busTblList);

    }

    public void getrowData(SelectEvent event) throws ParseException {
        bustbl = (Bustbl) event.getObject();
        busName = bustbl.getBusName();
        bustblDetalList = bustblDetalFacade.searchAllBusSeatInfoByBusId(bustbl);
        updteStatus = 1;
        seatNoRenderd = true;
        saveorUpdateBundle = "Update";

    }

    public void deleteRow(Integer id) {
        System.out.println("Id dddd " + id);
        System.out.println("bustbl Obj " + bustbl);
        bustblFacade.deleteEntity(id);
        // Reload data into dataList after deletion
        recerateBusDataModel();
    }

    public String deleteAction(Bustbl order) {
        System.out.println("Id dddd " + order.getBusName());

        busBeanLocal.delet(order);
        System.out.println("Id dddd " + order.getBusName());
        recerateBusDataModel();
        return null;
    }

    public String deleteSeatNo(BustblDetal order) {
        System.out.println("Id dddd ");
        System.out.println("Id dddd " + order.getSeatNo());
        bustblDetalFacade.remove(order);
        recreateDetailDataModel();
        return null;
    }

}
