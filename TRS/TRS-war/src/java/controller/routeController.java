/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bussiness.RouteBeanLocal;
import commonapp.utility.JsfUtil;
import entity.Bustbl;
import entity.Citytbl;
import entity.Routetbl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import mapper.BustblFacade;
import mapper.CitytblFacade;
import mapper.RoutetblFacade;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author demis
 */
@Named(value = "routeController")
@ViewScoped
public class routeController implements Serializable {

    /**
     * Creates a new instance of routeController
     */
    public routeController() {
        this.saveorUpdateBundle = "Save";
    }
    @EJB
    RoutetblFacade routetblFacade;
    @EJB
    CitytblFacade citytblFacade;
    @EJB
    RouteBeanLocal routeBeanLocal;
    @Inject
    Routetbl routetbl;
    @Inject
    Citytbl citytbl;
    @Inject
    Citytbl citytbl2;

    @EJB
    BustblFacade bustblFacade;

    @Inject
    loginController loginControllerr;

    private DataModel<Routetbl> routetblModel;
    private Routetbl selectedItem;
    private String saveorUpdateBundle = "Save";
    int updteStatus = 0;

    @PostConstruct
    public void initialize() {
        recerateDataModel();

    }

    public Citytbl getCitytbl2() {
        if (citytbl2 == null) {
            citytbl2 = new Citytbl();
        }
        return citytbl2;
    }

    public void setCitytbl2(Citytbl citytbl2) {
        this.citytbl2 = citytbl2;
    }

    public Citytbl getCitytbl() {
        if (citytbl == null) {
            citytbl = new Citytbl();
        }
        return citytbl;
    }

    public void setCitytbl(Citytbl citytbl) {
        this.citytbl = citytbl;
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

    public Routetbl getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Routetbl selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getSaveorUpdateBundle() {
        return saveorUpdateBundle;
    }

    public void setSaveorUpdateBundle(String saveorUpdateBundle) {
        this.saveorUpdateBundle = saveorUpdateBundle;
    }

    public DataModel<Routetbl> getRoutetblModel() {
        if (routetblModel == null) {
            routetblModel = new ListDataModel<>();
        }
        return routetblModel;
    }

    public void setRoutetblModel(DataModel<Routetbl> routetblModel) {
        this.routetblModel = routetblModel;
    }

    private String clearPage() {

        routetbl = new Routetbl();
        citytbl2 = new Citytbl();
        citytbl = new Citytbl();
        saveorUpdateBundle = "Save";
        updteStatus = 0;
        return null;
    }

    public String saveUpdate() {
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX ");

        if (updteStatus == 0) {
            if (!(routetbl.getDepatureCityId().getCityName().equalsIgnoreCase(routetbl.getDestinationCityId().getCityName()))) {
                if (!(routetblFacade.checkRouteDuplicated(routetbl))) {

                    try {
                        routeBeanLocal.create(routetbl);
                        JsfUtil.addSuccessMessage("Successfully Saved");
                        recerateDataModel();
                        clearPage();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JsfUtil.addFatalMessage("Route is Duplicated");
                }
            } else {
                JsfUtil.addFatalMessage("Depature City and Destination city is not the same");
            }

        } else {
            try {
                routeBeanLocal.edit(routetbl);
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
        routetblModel = null;
        List<Routetbl> routeTblList;
        routeTblList = routetblFacade.searchAllRoutInfo();
        routetblModel = new ListDataModel(routeTblList);

    }

    public void getrowData(SelectEvent event) throws ParseException {

        routetbl = (Routetbl) event.getObject();
        citytbl.setCityName(routetbl.getDepatureCityId().getCityName());
        citytbl2.setCityName(routetbl.getDestinationCityId().getCityName());

        updteStatus = 1;
        saveorUpdateBundle = "Update";

    }

//     public void deleteRow(Integer id) {
//         System.out.println("Id dddd "+ id);
//           System.out.println("bustbl Obj "+ bustbl);
//        routetblFacade.deleteEntity(id);
//        // Reload data into dataList after deletion
//                recerateSubjectDataModel();
//    }
    public String deleteAction(Routetbl routetbl) {
        routeBeanLocal.delet(routetbl);
        recerateDataModel();
        return null;
    }

    public SelectItem[] getDepatureTypeList() {
        return JsfUtil.getSelectItems(citytblFacade.searchAllInfo(), true);
    }

    public void depatureTypeEvent(ValueChangeEvent event) {
        if (event.getNewValue() != null && !event.getNewValue().toString().equals("")) {
            Citytbl citytbl1 = new Citytbl();
            String valueName = event.getNewValue().toString();
            citytbl1.setCityName(valueName);
            citytbl = citytblFacade.searchCityObjectByName(citytbl1);
            routetbl.setDepatureCityId(citytbl);
        }
    }

    public void destinationEvent(ValueChangeEvent event) {
        if (event.getNewValue() != null && !event.getNewValue().toString().equals("")) {
            Citytbl citytbl1 = new Citytbl();
            String valueName = event.getNewValue().toString();
            citytbl1.setCityName(valueName);
            citytbl2 = citytblFacade.searchCityObjectByName(citytbl1);
            routetbl.setDestinationCityId(citytbl2);
            routetbl.setRouteName(citytbl.getCityName() + " To " + citytbl2.getCityName());
            String admintxt = loginControllerr.getUserName();
            System.out.println("   ------admin txt ----- " + admintxt);
            if (routetbl.getDepatureCityId().getCityName().equalsIgnoreCase(routetbl.getDestinationCityId().getCityName())) {
                JsfUtil.addFatalMessage("Depature City and Destination city is not the same");
            }
        }
    }

    public List<Citytbl> searchCityistNew(String nameList) {

        ArrayList<Citytbl> cityNameLst = null;
        citytbl.setCityName(nameList);
        cityNameLst = citytblFacade.searchCityByName(citytbl);
        return cityNameLst;
    }

    public void getSelectDespatureCityInfo(SelectEvent event) {
        if (event.getObject() != null && event.getObject() != "") {
            citytbl.setCityName(event.getObject().toString());
            citytbl = citytblFacade.searchCityObjectByName(citytbl);
            routetbl.setDepatureCityId(citytbl);
            String admintxt = loginControllerr.getUserName();
            System.out.println("------admin txt ----- " + admintxt);
        }
    }

    public List<Citytbl> searchDestinationCityList(String nameList) {

        ArrayList<Citytbl> cityNameLst = null;
        citytbl2.setCityName(nameList);
        cityNameLst = citytblFacade.searchCityByName(citytbl2);
        return cityNameLst;
    }

    public void getSelectDestinationCityInfo(SelectEvent event) {
        if (event.getObject() != null && event.getObject() != "") {
            citytbl2.setCityName(event.getObject().toString());
            citytbl2 = citytblFacade.searchCityObjectByName(citytbl2);
            routetbl.setDestinationCityId(citytbl2);
            routetbl.setRouteName(citytbl.getCityName() + " To " + citytbl2.getCityName());
            String admintxt = loginControllerr.getUserName();
            System.out.println("   ------admin txt ----- " + admintxt);
        }
    }

}
