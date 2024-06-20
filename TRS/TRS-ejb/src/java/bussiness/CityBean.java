/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

import entity.Citytbl;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import mapper.CitytblFacade;

/**
 *
 * @author db
 */
@Stateless
public class CityBean implements CityBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    CitytblFacade citytblFacade;

    @Override
    public void create(Citytbl tbl) {
        citytblFacade.create(tbl);
    }

    @Override
    public void edit(Citytbl tbl) {
        citytblFacade.edit(tbl);
    }

    @Override
    public void delet(Citytbl tbl) {
        citytblFacade.remove(tbl);
    }
}
