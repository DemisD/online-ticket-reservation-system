/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bussiness.ContactUsBeanLocal;
import commonapp.utility.JsfUtil;
import entity.Contactus;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.ParseException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import mapper.ContactusFacade;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author demis
 */
@Named(value = "contuctUs")
@ViewScoped
public class ContuctUs implements Serializable {

    /**
     * Creates a new instance of ContuctUs
     */
    public ContuctUs() {
    }
    @EJB
    ContactusFacade contactusFacade;
    @EJB
    ContactUsBeanLocal beanLocal;
    @Inject
    Contactus contactus;

    private DataModel<Contactus> contactusModel;
    private Contactus selectedItem;
    private String saveorUpdateBundle = "Save";
    int updteStatus = 0;

    @PostConstruct
    public void initialize() {

        recerateDataModel();

    }

    public Contactus getContactus() {
        if (contactus == null) {
            contactus = new Contactus();
        }
        return contactus;
    }

    public void setContactus(Contactus contactus) {
        this.contactus = contactus;
    }

    public DataModel<Contactus> getContactusModel() {
        if (contactusModel == null) {
            contactusModel = new ListDataModel<>();
        }
        return contactusModel;
    }

    public void setContactusModel(DataModel<Contactus> contactusModel) {
        this.contactusModel = contactusModel;
    }

    public Contactus getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Contactus selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getSaveorUpdateBundle() {
        return saveorUpdateBundle;
    }

    public void setSaveorUpdateBundle(String saveorUpdateBundle) {
        this.saveorUpdateBundle = saveorUpdateBundle;
    }

    private String clearPage() {
        contactus = null;
        saveorUpdateBundle = "Save";
        updteStatus = 0;
        return null;
    }

    public String saveUpdate() {
        if (updteStatus == 0) {
            try {
                beanLocal.create(contactus);
                JsfUtil.addSuccessMessage("Successfully Saved");
                recerateDataModel();
                clearPage();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                beanLocal.edit(contactus);
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
        contactusModel = null;
        List<Contactus> tblList;
        tblList = contactusFacade.searchAllInfo();
        contactusModel = new ListDataModel(tblList);
    }

    public void getrowData(SelectEvent event) throws ParseException {
        contactus = (Contactus) event.getObject();
        updteStatus = 1;
        saveorUpdateBundle = "Update";

    }

    public String deleteAction(Contactus tbl) {
        beanLocal.delet(tbl);
        recerateDataModel();
        return null;
    }

}
