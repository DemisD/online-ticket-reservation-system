/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

import entity.Citytbl;
import javax.ejb.Local;

/**
 *
 * @author db
 */
@Local
public interface CityBeanLocal {
    public void create(Citytbl tbl);

  
    public void edit(Citytbl tbl);

    public void delet(Citytbl tbl);  
}
