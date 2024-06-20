/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import entity.Citytbl;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author db
 */
@Stateless
public class CitytblFacade extends AbstractFacade<Citytbl> {

    @PersistenceContext(unitName = "TRS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CitytblFacade() {
        super(Citytbl.class);
    }

    public List<Citytbl> searchAllInfo() {

        List<Citytbl> einfoList = null;
        Query query = em.createNamedQuery("Citytbl.findAll", Citytbl.class);
        einfoList = query.getResultList();
        return einfoList;
    }

    public void deleteEntity(Integer entityId) {
        Citytbl bustbl = em.find(Citytbl.class, entityId);
        if (bustbl != null) {
            em.remove(bustbl);
        }
    }

    public ArrayList<Citytbl> searchCityByName(Citytbl citytbl) {
        Query query = em.createNamedQuery("Citytbl.findLikeCityName");
        query.setParameter("cityName", citytbl.getCityName().toUpperCase() + "%");

        try {
            ArrayList<Citytbl> nebeCityList = new ArrayList(query.getResultList());
            return nebeCityList;
        } catch (Exception ex) {
            return null;
        }
    }
    
    
     public Citytbl searchCityObjectByName(Citytbl citytbl) {
        Query query = em.createNamedQuery("Citytbl.findByCityName");
        query.setParameter("cityName", citytbl.getCityName());
        try {
            Citytbl cityDataInfo = (Citytbl) query.getResultList().get(0);
            return cityDataInfo;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

     public boolean checkCityNameDuplicated(Citytbl citytbl) {
        boolean checkStatus;
        Query query = em.createNamedQuery("Citytbl.findByCityName");
        query.setParameter("cityName", citytbl.getCityName());
  
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
