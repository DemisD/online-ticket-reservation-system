/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bussiness.UserBeanLocal;
import commonapp.utility.JsfUtil;
import entity.Usertbl;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.ParseException;
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
import javax.servlet.http.HttpSession;
import mapper.UsertblFacade;
import org.primefaces.event.SelectEvent;
import servlets.SessionUtils;

/**
 *
 * @author demis
 */
@Named(value = "userController")
@ViewScoped
public class userController implements Serializable {

    /**
     * Creates a new instance of userController
     */
    public userController() {
        this.saveorUpdateBundle = "Save";
    }
    @EJB
    UsertblFacade usertblFacade;
    @EJB
    UserBeanLocal beanLocal;
    @Inject
    Usertbl usertbl;
    @Inject
    Usertbl usertblProfile;
    @Inject
    loginController loginControllerr;

    private DataModel<Usertbl> tblModel;
    private DataModel<Usertbl> userDataModel;
    private Usertbl selectedItem;
    private String saveorUpdateBundle = "Save";
    int updteStatus = 0;
    String password;
    String confirmPassword;
    String confirmMessage;
    boolean disableSaveButton = true;
    boolean renderedRoleAdmin = true;
    boolean renderedRoleUser = true;

    @PostConstruct
    public void initialize() {

        usertbl = usertblFacade.getUserDataByUserName(loginControllerr.getUserName());
        if (usertbl != null) {
            usertblProfile = usertbl;
            if (usertbl.getRole().equalsIgnoreCase("Admin")) {
                renderedRoleAdmin = true;
                renderedRoleUser = false;
                recerateAdminDataModel();
            } else if (usertbl.getRole().equalsIgnoreCase("User")) {
                recerateUserDataModel();
                renderedRoleAdmin = false;
                renderedRoleUser = true;
//                usertbl.setRole("User");
            }
        } else {
            renderedRoleAdmin = false;
            renderedRoleUser = true;
//            usertbl.setRole("User");
        }

        disableSaveButton = true;
        usertbl = new Usertbl();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRenderedRoleUser() {
        return renderedRoleUser;
    }

    public void setRenderedRoleUser(boolean renderedRoleUser) {
        this.renderedRoleUser = renderedRoleUser;
    }

    public boolean isRenderedRoleAdmin() {
        return renderedRoleAdmin;
    }

    public void setRenderedRoleAdmin(boolean renderedRoleAdmin) {
        this.renderedRoleAdmin = renderedRoleAdmin;
    }

    public boolean isDisableSaveButton() {
        return disableSaveButton;
    }

    public void setDisableSaveButton(boolean disableSaveButton) {
        this.disableSaveButton = disableSaveButton;
    }

    public String getConfirmMessage() {
        return confirmMessage;
    }

    public void setConfirmMessage(String confirmMessage) {
        this.confirmMessage = confirmMessage;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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

    public DataModel<Usertbl> getUserDataModel() {
        if (userDataModel == null) {
            userDataModel = new ListDataModel<>();
        }
        return userDataModel;
    }

    public void setUserDataModel(DataModel<Usertbl> userDataModel) {
        this.userDataModel = userDataModel;
    }

    public DataModel<Usertbl> getTblModel() {
        if (tblModel == null) {
            tblModel = new ListDataModel<>();
        }
        return tblModel;
    }

    public void setTblModel(DataModel<Usertbl> tblModel) {
        this.tblModel = tblModel;
    }

    public Usertbl getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Usertbl selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getSaveorUpdateBundle() {
        return saveorUpdateBundle;
    }

    public void setSaveorUpdateBundle(String saveorUpdateBundle) {
        this.saveorUpdateBundle = saveorUpdateBundle;
    }

    private String clearPage() {
        usertbl = new Usertbl();
        saveorUpdateBundle = "Save";
        updteStatus = 0;
        return null;
    }

    public String saveUpdate() {
        PasswordUtils PasswordUtilsObj = new PasswordUtils();
        if (updteStatus == 0) {
            if (usertblFacade.checkUserNameDuplicated(usertbl)) {
                JsfUtil.addFatalMessage("User Name is duplicated");
              
            } else {
                   try {

                    String encryptedPassword = PasswordUtilsObj.hashPassword(password); // Encrypt the password
                    usertbl.setPassword(encryptedPassword);
                    usertbl.setPlainTaxtPassword(password);
                    beanLocal.create(usertbl);
                    JsfUtil.addSuccessMessage("Successfully Saved");
                    recerateAdminDataModel();
                    clearPage();
//                    FacesContext.getCurrentInstance().getExternalContext().redirect("../loginPage.xhtml");
                    redirectToLogin();
                } catch (Exception ex) {
                    ex.printStackTrace();
                } 
            }
        } else {
            try {
                String encryptedPassword = PasswordUtilsObj.hashPassword(password); // Encrypt the password
                usertbl.setPassword(encryptedPassword);
                usertbl.setPlainTaxtPassword(password);
                beanLocal.edit(usertbl);
                JsfUtil.addSuccessMessage("Successfully Updated");
                clearPage();

//                       FacesContext.getCurrentInstance().getExternalContext().redirect("../loginPage.xhtml");
//                FacesContext.getCurrentInstance().getExternalContext().redirect("../loginPage.xhtml?faces-redirect=true");

                 
                redirectToLogin();
                
            } catch (Exception ex) {
                JsfUtil.addFatalMessage("Something Occured on Data Updated");
                ex.printStackTrace();
            }

        }
        return null;

    }

    public void redirectToLogin() throws IOException{
     FacesContext context = FacesContext.getCurrentInstance();
                HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
                session.invalidate();
                ExternalContext externalContext = context.getExternalContext();
                externalContext.redirect("http://localhost:8080/TRS-war/");
    
    }
    
    
    public void getConiformPasswored() {
        System.out.println("passwored " + usertbl.getPassword());
        System.out.println("passwored " + confirmPassword);
        if (!password.equals(confirmPassword)) {
            confirmMessage = "Passwored is not matched";
            disableSaveButton = true;
        }
        if (password.equals(confirmPassword)) {
            confirmMessage = "";
            disableSaveButton = false;
        }

    }

//    public String registerUser() {
//        if (!tblName.getPassword().equals(confirmPassword)) {
//            // Passwords don't match, handle this case appropriately
//
//            return null;
//        }
//
//        boolean success = tblFacade.registerUser(tblName);
//        if (success) {
//            System.out.println(" loginnnnnnnnnnnnnnnn    ");
//            return "loginPage.xhtml?faces-redirect=true";
//        } else {
//            return "registration-failure?faces-redirect=true";
//        }
//    }
    public void recerateAdminDataModel() {
        tblModel = null;
        List<Usertbl> tblList;
        tblList = usertblFacade.searchAllInfo();
        tblModel = new ListDataModel(tblList);

    }

    public void recerateUserDataModel() {
        userDataModel = null;
        List<Usertbl> userList;
        userList = usertblFacade.searchUserByUserName(usertblProfile);
        userDataModel = new ListDataModel(userList);

    }

    public void getrowData(SelectEvent event) throws ParseException {

        usertbl = (Usertbl) event.getObject();
        password = usertbl.getPlainTaxtPassword();
        updteStatus = 1;
        saveorUpdateBundle = "Update";

    }

    public String deleteAction(Usertbl tbl) {
        System.out.println("Id dddd " + tbl.getUserName());

        beanLocal.delet(tbl);
        System.out.println("Id dddd " + tbl.getUserName());
        recerateAdminDataModel();
        return null;
    }

}
