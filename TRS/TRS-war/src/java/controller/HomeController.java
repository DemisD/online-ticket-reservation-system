/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import mapper.BustblFacade;
import mapper.ScheduletblFacade;
import mapper.TicketreservationtblFacade;

/**
 *
 * @author demis
 */
@Named(value = "homeController")
@ViewScoped
public class HomeController implements Serializable {

    /**
     * Creates a new instance of HomeController
     */
    
    @PostConstruct()
    public void init() {
        totalBus = ticketreservationtblFacade.totalBusNo();
        totalRoute = ticketreservationtblFacade.totalRoutNo();
        totalCustomer = ticketreservationtblFacade.totalCustomerNo();
        totalBooking = ticketreservationtblFacade.totalBookingNo();
        totalAdmin = ticketreservationtblFacade.totalAdminNo();
        totalPrice = ticketreservationtblFacade.totalPriceNo();
           scheduletblFacade.updateStatusToInactive();
      
    }

    public HomeController() {

    }
    @EJB
    TicketreservationtblFacade ticketreservationtblFacade;
    @EJB
    BustblFacade bustblFacade;
      @EJB
    ScheduletblFacade scheduletblFacade;

    Integer totalBus, totalRoute, totalCustomer, totalBooking, totalAdmin;
    Double totalPrice = 2.00;

    public Integer getTotalBus() {
        return totalBus;
    }

    public void setTotalBus(Integer totalBus) {
        this.totalBus = totalBus;
    }

    public Integer getTotalRoute() {
        return totalRoute;
    }

    public void setTotalRoute(Integer totalRoute) {
        this.totalRoute = totalRoute;
    }

    public Integer getTotalCustomer() {
        return totalCustomer;
    }

    public void setTotalCustomer(Integer totalCustomer) {
        this.totalCustomer = totalCustomer;
    }

    public Integer getTotalAdmin() {
        return totalAdmin;
    }

    public void setTotalAdmin(Integer totalAdmin) {
        this.totalAdmin = totalAdmin;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTotalBooking() {
        return totalBooking;
    }

    public void setTotalBooking(Integer totalBooking) {
        this.totalBooking = totalBooking;
    }

}
