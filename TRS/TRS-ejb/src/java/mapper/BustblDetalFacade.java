/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import entity.Bustbl;
import entity.BustblDetal;
import entity.Scheduletbl;
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
public class BustblDetalFacade extends AbstractFacade<BustblDetal> {

    @PersistenceContext(unitName = "TRS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BustblDetalFacade() {
        super(BustblDetal.class);
    }

    public List<BustblDetal> searchAllBusSeatInfo() {

        List<BustblDetal> einfoList = null;
        Query query = em.createNamedQuery("BustblDetal.findAll", BustblDetal.class);
        einfoList = query.getResultList();
        return einfoList;
    }

    public List<BustblDetal> searchAllBusSeatInfoByBusId(Bustbl bustbl) {
        List<BustblDetal> einfoList = null;
        Query query = em.createNamedQuery("BustblDetal.findByBusId");
        query.setParameter("id", bustbl.getId());

        try {
            einfoList = query.getResultList();
            return einfoList;
        } catch (Exception ex) {
            return null;
        }
    }

    public Boolean checkeBusSeatNo(BustblDetal bustblDetal, Bustbl bustbl) {
//   public Citytbl searchCityObjectByName(Citytbl citytbl) {
        Query query = em.createNamedQuery("BustblDetal.findByBusIdAndSeatNo");
        query.setParameter("id", bustbl.getId());
        query.setParameter("seatNo", bustblDetal.getSeatNo());
        try {
            Boolean cityDataInfo = query.getResultList().isEmpty();
            return !cityDataInfo;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

//        TypedQuery<Usertbl> query = em.createNamedQuery("Usertbl.findByUsernameAndPassword", Usertbl.class);
//        Query query = em.createQuery("SELECT u FROM Usertbl u where u.userName = :userName AND u.password= :password");
//        query.setParameter("userName", userName);
//        query.setParameter("password", password);
//        return !query.getResultList().isEmpty();
    }
//}

    public BustblDetal searchBusSeatNo(BustblDetal bustblDetal, Bustbl bustbl) {
        Query query = em.createNamedQuery("BustblDetal.findBySeatNo");
        query.setParameter("seatNo", bustblDetal.getSeatNo());
        System.out.println("s no" + bustblDetal.getSeatNo());
          query.setParameter("busNumber", bustbl.getBusNumber());
              System.out.println("busNumber" + bustbl.getBusNumber());
        try {
            BustblDetal list = (BustblDetal) (query.getSingleResult());
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<BustblDetal> searchAllBusSeatNo(Scheduletbl scheduletbl) {

        List<BustblDetal> einfoList = null;
//          Query query = em.createNamedQuery("BustblDetal.findAllSeatNoByBus", BustblDetal.class);
        Query query = em.createNamedQuery("BustblDetal.findAllSeatNoByBus1", BustblDetal.class);
        query.setParameter("busNumber", scheduletbl.getBusId().getBusNumber());
        einfoList = query.getResultList();
        return einfoList;
    }

}
