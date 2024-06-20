/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

import entity.BustblDetal;
import javax.ejb.Local;

/**
 *
 * @author demis
 */
@Local
public interface BustblDetalBeanLocal {
    public void delet(BustblDetal bustblDetal); 
}
