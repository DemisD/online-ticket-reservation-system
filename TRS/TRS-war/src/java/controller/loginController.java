/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import commonapp.utility.JsfUtil;
import entity.Usertbl;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import mapper.UsertblFacade;
import servlets.SessionUtils;

/**
 *
 * @author db
 */
@Named(value = "loginController")
@SessionScoped
public class loginController implements Serializable {

    /**
     * Creates a new instance of loginController
     */
    public loginController() {
    }
    @EJB
    UsertblFacade usertblFacade;
    @Inject
    Usertbl usertbl;
    private String passwordInter;
    private String userName;
    private int failedAttempts = 0;
    private String invalidUserNameAndPassword = "";

//     private String tab = "disabled";
//    private String tabToggle = "tab";
    private String treeview = "";
    private String treeviewUser = "";
        private String homeHideForUserOrAdmin = "";

    @PostConstruct
    public void initialize() {
        invalidUserNameAndPassword = " ";
         if ( usertblFacade.getUserDataByUserName("admin") == null) {
            usertblFacade.insertAdiminData();
        }
    }

    public String getInvalidUserNameAndPassword() {
        return invalidUserNameAndPassword;
    }

    public void setInvalidUserNameAndPassword(String invalidUserNameAndPassword) {
        this.invalidUserNameAndPassword = invalidUserNameAndPassword;
    }

    public String getTreeview() {
        return treeview;
    }

    public void setTreeview(String treeview) {
        this.treeview = treeview;
    }

    public String getTreeviewUser() {
        return treeviewUser;
    }

    public void setTreeviewUser(String treeviewUser) {
        this.treeviewUser = treeviewUser;
    }

    public String getHomeHideForUserOrAdmin() {
        return homeHideForUserOrAdmin;
    }

    public void setHomeHideForUserOrAdmin(String homeHideForUserOrAdmin) {
        this.homeHideForUserOrAdmin = homeHideForUserOrAdmin;
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

    public String getPasswordInter() {
        return passwordInter;
    }

    public void setPasswordInter(String passwordInter) {
        this.passwordInter = passwordInter;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String login() {
        boolean valid = usertblFacade.authenticate(userName, passwordInter);
        if (valid) {
            usertbl = usertblFacade.getUserDataByUserName(userName);
//            loggedIn = true;
            return "HomePage.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Invalid credentials"));
            return null;
        }

    }

    public String logout() {
        System.out.println("user name ccccccccccccc  ");

        return "login.xhtml?faces-redirect=true";
    } // Getters and setters } 

    public void logout2() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("loginPage.xhtml");

    }

    public String userProfile() throws IOException {
        
        

 usertbl = usertblFacade.getUserDataByUserName(userName);
        FacesContext.getCurrentInstance().getExternalContext().redirect("AdminUser.xhtml");
//        boolean valid = true;
//        if (valid) {
//            HttpSession session = SessionUtils.getSession();
//            session.setAttribute("username", userName);
//            usertbl = usertblFacade.getUserDataByUserName(userName);
//            return "AdminUser.xhtml?faces-redirect=true";
//        }
        return null;

    }

//    public void signUp() throws IOException {
//        System.out.println("Sign up");
//        FacesContext.getCurrentInstance().getExternalContext().redirect("adminUser.xhtml");
////            loggedIn = true;
////            return "trsPage/adminUser.xhtml?faces-redirect=true";
//
//    }
    public String signUp() {
        boolean valid = true;
        if (valid) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", "user");
            return "AdminUser.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Passowrd",
                            "Please enter correct username and Password"));
            return "AdminUser.xhtml?faces-redirect=true";
        }

    }

    public String login3() {
        boolean valid = usertblFacade.authenticate(userName, passwordInter);
        if (valid) {
            usertbl = usertblFacade.getUserDataByUserName(userName);
//            loggedIn = true;
            return "HomePage.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Invalid credentials"));
            return null;
        }

    }

    public String validateUsernamePassword() {
        boolean valid = usertblFacade.authenticate(userName, passwordInter);
        System.out.println("boolean " + valid);
        if (valid) {
            invalidUserNameAndPassword = " ";
            usertbl = usertblFacade.getUserDataByUserName(userName);
            if (usertbl.getRole().equalsIgnoreCase("Admin")) {
                treeview = "treeview";
                treeviewUser = "hidden";
                homeHideForUserOrAdmin = "row";

            } else {
                treeview = "hidden";
                treeviewUser = "treeview";
                 homeHideForUserOrAdmin = "hidden";
            }
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", userName);
            return "HomePage.xhtml?faces-redirect=true";
        } else {
            invalidUserNameAndPassword = "Invalid UserName or password ";
            if (failedAttempts >= 3) {
                HttpSession session = SessionUtils.getSession();
                session.setAttribute("username", userName);
                return "errorPage.xhtml?faces-redirect=true";
            } else {
                failedAttempts++;
                return "loginPage.xhtml?faces-redirect=true";
            }
        }
    }

    //logout event, invalidate session
    public String logout3() throws IOException {
        
         
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.invalidate();
        ExternalContext externalContext = context.getExternalContext();
        externalContext.redirect("http://localhost:8080/TRS-war/");
        
        
//        HttpSession session = SessionUtils.getSession();
//        session.invalidate();
        usertbl = new Usertbl();
//        return "loginPage.xhtml?faces-redirect=true";
        return null;
    }

//
//    public String login2() {
//        FacesContext context = FacesContext.getCurrentInstance();
//        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
//        boolean valid = usertblFacade.authenticate(userName, passwordInter);
//        if (valid) {
//            request.getSession().setAttribute("username", userName);
//            return "HomePage.xhtml?faces-redirect=true";
//        } else {
//            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid credentials", null));
//            return null;
//        }
//    }
//
//    public String logout3() throws IOException {
//        FacesContext context = FacesContext.getCurrentInstance();
//        ExternalContext externalContext = context.getExternalContext();
//        externalContext.invalidateSession();
//        externalContext.redirect(externalContext.getRequestContextPath() + "/loginPage.xhtml");
//        return "loginPage.xhtml?faces-redirect=true";
//    }
}
