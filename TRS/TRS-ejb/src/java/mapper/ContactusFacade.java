/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import entity.Contactus;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author demis
 */
@Stateless
public class ContactusFacade extends AbstractFacade<Contactus> {
    @PersistenceContext(unitName = "TRS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContactusFacade() {
        super(Contactus.class);
    }
      public List<Contactus> searchAllInfo() {

        List<Contactus> einfoList = null;
        Query query = em.createNamedQuery("Contactus.findAll", Contactus.class);
        einfoList = query.getResultList();
        return einfoList;
    }
}
