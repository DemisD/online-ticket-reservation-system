/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import entity.Bustbl;
import java.util.ArrayList;
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
public class BustblFacade extends AbstractFacade<Bustbl> {

    @PersistenceContext(unitName = "TRS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BustblFacade() {
        super(Bustbl.class);
    }

    public List<Bustbl> searchAllBusInfo() {

        List<Bustbl> einfoList = null;
        Query query = em.createNamedQuery("Bustbl.findAll", Bustbl.class);
        einfoList = query.getResultList();
        return einfoList;
    }

    public void deleteEntity(Integer entityId) {
        Bustbl bustbl = em.find(Bustbl.class, entityId);
        if (bustbl != null) {
            em.remove(bustbl);
        }
    }

    public ArrayList<Bustbl> searchBusByNumber(Bustbl bustbl) {
        Query query = em.createNamedQuery("Bustbl.findLikeBusNumber");
        query.setParameter("busNumber", "%" + bustbl.getBusNumber().toUpperCase() + "%");

        try {
            ArrayList<Bustbl> busList = new ArrayList(query.getResultList());
            return busList;
        } catch (Exception ex) {
            return null;
        }
    }

    public Bustbl searchBusObjectByNo(Bustbl bustbl) {
        Query query = em.createNamedQuery("Bustbl.findByBusNumber");
        query.setParameter("busNumber", bustbl.getBusNumber());
        try {
            Bustbl busDataInfo = (Bustbl) query.getResultList().get(0);
            return busDataInfo;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Integer tottalBusNo() {
        Query query = em.createNamedQuery("Bustbl.TotalBusNo");
//        query.setParameter("structureId", nebeid);
        try {
            System.out.println("Count "+ query.getSingleResult());
            return (Integer) query.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
     public boolean checkBusDuplicated(Bustbl bustbl) {
        boolean checkStatus;
        Query query = em.createNamedQuery("Bustbl.findByBusNumber");
        query.setParameter("busNumber", bustbl.getBusNumber());
  
        try {
            if (query.getResultList().size() > 0) {
                checkStatus = true;
                return checkStatus;
            } else {
                checkStatus = false;
                return checkStatus;
            }
        } catch (Exception e) {
//            Logger.getGlobal().log((LogRecord) (Supplier<String>) e);
            return false;
        }
    }
}
