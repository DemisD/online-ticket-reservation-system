/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import entity.Scheduletbl;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author AdG
 */
@Stateless
public class ScheduletblFacade extends AbstractFacade<Scheduletbl> {

    @PersistenceContext(unitName = "TRS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ScheduletblFacade() {
        super(Scheduletbl.class);
    }

    public List<Scheduletbl> searchAllInfo() {
        List<Scheduletbl> einfoList = null; 
        Query query = em.createNamedQuery("Scheduletbl.findAll", Scheduletbl.class);
//            Query query = em.createNamedQuery("Scheduletbl.findAllByStatus", Scheduletbl.class);
        einfoList = query.getResultList();
        return einfoList;
    }
      public List<Scheduletbl> searchAllInfoStatusIsActive() {
        List<Scheduletbl> einfoList = null; 
//        Query query = em.createNamedQuery("Scheduletbl.findAll", Scheduletbl.class);
            Query query = em.createNamedQuery("Scheduletbl.findAllByStatus", Scheduletbl.class);
        einfoList = query.getResultList();
        return einfoList;
    }

    public void updateStatusToInactive() {
        // Get the current date
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String currentDateString = dateFormat.format(currentDate);
          System.out.println("current Date is  " + currentDateString);
        // Query to update status field where dateField is in the past
        Query query = em.createQuery("UPDATE Scheduletbl s SET s.status = 'Expired' WHERE s.scheduleDate < :currentDateString");
        query.setParameter("currentDateString", currentDateString);
        // Execute the update query
        int updatedCount = query.executeUpdate();
        System.out.println("Number of entities updated to 'Expired': " + updatedCount);
    }
}
