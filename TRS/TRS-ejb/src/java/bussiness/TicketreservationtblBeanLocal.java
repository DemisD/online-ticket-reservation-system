/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

import entity.Ticketreservationtbl;
import javax.ejb.Local;

/**
 *
 * @author demis
 */
@Local
public interface TicketreservationtblBeanLocal {
     public void create(Ticketreservationtbl usertbl);

  
    public void edit(Ticketreservationtbl usertbl);

    public void delet(Ticketreservationtbl usertbl); 
}
