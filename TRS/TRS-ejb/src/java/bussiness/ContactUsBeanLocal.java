/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

import entity.Contactus;
import javax.ejb.Local;

/**
 *
 * @author demis
 */
@Local
public interface ContactUsBeanLocal {
     public void create(Contactus contactus);

  
    public void edit(Contactus contactus);

    public void delet(Contactus contactus);  
}
