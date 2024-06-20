/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

import entity.BustblDetal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import mapper.BustblDetalFacade;

/**
 *
 * @author demis
 */
@Stateless
public class BustblDetalBean implements BustblDetalBeanLocal {

    @EJB
    BustblDetalFacade bustblDetalFacade;

    @Override
    public void delet(BustblDetal bustblDetal) {
        bustblDetalFacade.remove(bustblDetal); 
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
