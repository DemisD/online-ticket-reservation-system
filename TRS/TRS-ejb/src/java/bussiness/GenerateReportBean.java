/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

import java.util.Collection;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import mapper.TicketreservationtblFacade;

/**
 *
 * @author AdG
 */
@Stateless
@LocalBean
public class GenerateReportBean {

    @EJB
    TicketreservationtblFacade ticketreservationtblFacade;

    public Collection ticketReservationReport(HashMap hashMap) {

        return ticketreservationtblFacade.ticketReservationReport(hashMap);
    }

    public Collection printReservTicket(HashMap hashMap) {

        return ticketreservationtblFacade.printReservTicket(hashMap);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
