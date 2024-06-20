/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

import entity.Usertbl;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import mapper.UsertblFacade;

/**
 *
 * @author db
 */
@Stateless
public class UserBean implements UserBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    UsertblFacade usertblFacade;

    @Override
    public void create(Usertbl usertbl) {
        usertblFacade.create(usertbl);
    }

    @Override
    public void edit(Usertbl usertbl) {
        usertblFacade.edit(usertbl);
    }

    @Override
    public void delet(Usertbl usertbl) {
        usertblFacade.remove(usertbl);
    }
    
    
   

}
