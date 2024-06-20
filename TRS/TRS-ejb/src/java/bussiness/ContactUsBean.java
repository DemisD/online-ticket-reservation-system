/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

import entity.Contactus;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import mapper.ContactusFacade;

/**
 *
 * @author demis
 */
@Stateless
public class ContactUsBean implements ContactUsBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    ContactusFacade contactusFacade;

    @Override
    public void create(Contactus contactus) {
        contactusFacade.create(contactus);
    }

    @Override
    public void edit(Contactus contactus) {
        contactusFacade.edit(contactus);
    }

    @Override
    public void delet(Contactus contactus) {
        contactusFacade.remove(contactus);
    }
}
