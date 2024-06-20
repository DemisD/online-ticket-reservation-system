/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

import entity.Scheduletbl;
import javax.ejb.Local;

/**
 *
 * @author AdG
 */
@Local
public interface ScheduleBeanLocal {
      public void create(Scheduletbl scheduletbl);

  
    public void edit(Scheduletbl scheduletbl);

    public void delet(Scheduletbl scheduletbl); 
}
