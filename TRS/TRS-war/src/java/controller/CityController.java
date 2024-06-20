/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bussiness.CityBeanLocal;
import commonapp.utility.JsfUtil;
import entity.Citytbl;
import javax.inject.Named;
import java.io.Serializable;
import java.text.ParseException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import mapper.CitytblFacade;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author db
 */
@Named(value = "cityController")
@ViewScoped
public class CityController implements Serializable {

    /**
     * Creates a new instance of CityController
     */
    public CityController() {
        this.saveorUpdateBundle = "Save";
    }
    @EJB
    CitytblFacade tblFacade;
    @EJB
    CityBeanLocal beanLocal;
    @Inject
    Citytbl tblName;
    private DataModel<Citytbl> tblModel;
    private Citytbl selectedItem;
    private String saveorUpdateBundle = "Save";
    int updteStatus = 0;

    @PostConstruct
    public void initialize() {
        clearPage();
        recerateDataModel();

    }

    public Citytbl getTblName() {
        if (tblName == null) {
            tblName = new Citytbl();
        }
        return tblName;
    }

    public void setTblName(Citytbl tblName) {
        this.tblName = tblName;
    }

    public DataModel<Citytbl> getTblModel() {
        if (tblModel == null) {
            tblModel = new ListDataModel<>();
        }
        return tblModel;
    }

    public void setTblModel(DataModel<Citytbl> tblModel) {
        this.tblModel = tblModel;
    }

    public Citytbl getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Citytbl selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getSaveorUpdateBundle() {
        return saveorUpdateBundle;
    }

    public void setSaveorUpdateBundle(String saveorUpdateBundle) {
        this.saveorUpdateBundle = saveorUpdateBundle;
    }

    private String clearPage() {
        tblName = null;
        saveorUpdateBundle = "Save";
        updteStatus = 0;
        return null;
    }

    public String saveUpdate() {
        if (updteStatus == 0) {
            if (!(tblFacade.checkCityNameDuplicated(tblName))) {

                try {
                    beanLocal.create(tblName);
                    JsfUtil.addSuccessMessage("Successfully Saved");
                    recerateDataModel();
                    clearPage();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } else {
                JsfUtil.addFatalMessage("City name is Duplicated");
            }

        } else {
            try {
                beanLocal.edit(tblName);
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
        tblModel = null;
        List<Citytbl> tblList;
        tblList = tblFacade.searchAllInfo();
        tblModel = new ListDataModel(tblList);
    }

    public void getrowData(SelectEvent event) throws ParseException {
        tblName = (Citytbl) event.getObject();
        updteStatus = 1;
        saveorUpdateBundle = "Update";

    }

    public String deleteAction(Citytbl tbl) {
        beanLocal.delet(tbl);
        recerateDataModel();
        return null;
    }

}
