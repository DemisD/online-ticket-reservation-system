/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

import entity.Routetbl;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import mapper.RoutetblFacade;

/**
 *
 * @author demis
 */
@Stateless
public class RouteBean implements RouteBeanLocal {

    @EJB
    RoutetblFacade routetblFacade;

    @Override
    public void create(Routetbl routetbl) {
        routetblFacade.create(routetbl);
    }

    @Override
    public void edit(Routetbl routetbl) {
        routetblFacade.edit(routetbl);
    }

    @Override
    public void delet(Routetbl routetbl) {
        routetblFacade.remove(routetbl);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
