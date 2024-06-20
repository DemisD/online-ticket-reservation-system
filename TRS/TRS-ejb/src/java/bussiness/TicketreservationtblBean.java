/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

import entity.Ticketreservationtbl;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import mapper.TicketreservationtblFacade;

/**
 *
 * @author demis
 */
@Stateless
public class TicketreservationtblBean implements TicketreservationtblBeanLocal {
   @EJB
    TicketreservationtblFacade ticketReservationTblFacade;
  
    
   
      @Override
    public void create(Ticketreservationtbl ticketReservationTbl) {
        ticketReservationTblFacade.create(ticketReservationTbl);
    }

    @Override
    public void edit(Ticketreservationtbl ticketReservationTbl) {
        ticketReservationTblFacade.edit(ticketReservationTbl);
    }

    @Override
    public void delet(Ticketreservationtbl ticketReservationTbl) {
        ticketReservationTblFacade.remove(ticketReservationTbl);
    }

}
