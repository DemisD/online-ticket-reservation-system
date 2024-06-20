/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

import entity.Bustbl;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import mapper.BustblFacade;

/**
 *
 * @author demis
 */
@Stateless
public class BusBean implements BusBeanLocal {

    @EJB
    BustblFacade bustblFacade;

    @Override
    public void create(Bustbl bustbl) {
        bustblFacade.create(bustbl);
    }

    @Override
    public void edit(Bustbl subjectTbl) {
        bustblFacade.edit(subjectTbl);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void delet(Bustbl bustbl) {
        bustblFacade.remove(bustbl);
    }
}
