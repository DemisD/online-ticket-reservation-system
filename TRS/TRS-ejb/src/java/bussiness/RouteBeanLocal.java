/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

import entity.Routetbl;
import javax.ejb.Local;

/**
 *
 * @author demis
 */
@Local
public interface RouteBeanLocal {
     public void create(Routetbl routetbl);

  
    public void edit(Routetbl routetbl);

    public void delet(Routetbl routetbl);
}
