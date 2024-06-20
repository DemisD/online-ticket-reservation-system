/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

import entity.Scheduletbl;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import mapper.ScheduletblFacade;

/**
 *
 * @author AdG
 */
@Stateless
public class ScheduleBean implements ScheduleBeanLocal {
 @EJB
    ScheduletblFacade scheduletblFacade;

    @Override
    public void create(Scheduletbl scheduletbl) {
        scheduletblFacade.create(scheduletbl);
    }

    @Override
    public void edit(Scheduletbl scheduletbl) {
        scheduletblFacade.edit(scheduletbl);
    }

    @Override
    public void delet(Scheduletbl scheduletbl) {
        scheduletblFacade.remove(scheduletbl);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
