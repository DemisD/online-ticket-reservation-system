/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import entity.Routetbl;
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
public class RoutetblFacade extends AbstractFacade<Routetbl> {

    @PersistenceContext(unitName = "TRS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RoutetblFacade() {
        super(Routetbl.class);
    }

    public List<Routetbl> searchAllRoutInfo() {

        List<Routetbl> einfoList = null;
        Query query = em.createNamedQuery("Routetbl.findAll", Routetbl.class);
        einfoList = query.getResultList();
        return einfoList;
    }

    public Routetbl searchRouteByRouteName(Routetbl routetbl) {
        Query query = em.createNamedQuery("Routetbl.findByRouterRouteName");
        query.setParameter("routeName", routetbl.getRouteName());
        try {
            Routetbl list = (Routetbl) (query.getSingleResult());
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<Routetbl> getTypListByRoute(String routeName) {
        Query query = em.createNamedQuery("Routetbl.findAllLikeRouteName", Routetbl.class);
        query.setParameter("routeName", routeName + "%");
        try {
            ArrayList<Routetbl> typeList = new ArrayList(query.getResultList());
            return typeList;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }
    
     public boolean checkRouteDuplicated(Routetbl routetbl) {
        boolean checkStatus;
        Query query = em.createNamedQuery("Routetbl.findByRouterRouteName");
        query.setParameter("routeName", routetbl.getRouteName());
         System.out.println("routeName "+ routetbl.getRouteName());
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
