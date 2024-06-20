/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

import entity.Usertbl;
import javax.ejb.Local;

/**
 *
 * @author db
 */
@Local
public interface UserBeanLocal {
     public void create(Usertbl usertbl);

  
    public void edit(Usertbl usertbl);

    public void delet(Usertbl usertbl);
}
 