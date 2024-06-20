/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

import entity.Bustbl;
import javax.ejb.Local;

/**
 *
 * @author demis
 */
@Local
public interface BusBeanLocal {
     public void create(Bustbl bustbl);

  
    public void edit(Bustbl subjectTbl);

    public void delet(Bustbl bustbl);

   
}
