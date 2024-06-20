/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import entity.Ticketreservationtbl;
import entity.Usertbl;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.eclipse.persistence.jpa.JpaEntityManager;
import org.eclipse.persistence.queries.DataReadQuery;
import org.eclipse.persistence.queries.StoredProcedureCall;

/**
 *
 * @author demis
 */
@Stateless
public class TicketreservationtblFacade extends AbstractFacade<Ticketreservationtbl> {

    @PersistenceContext(unitName = "TRS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TicketreservationtblFacade() {
        super(Ticketreservationtbl.class);
    }

    public List<Ticketreservationtbl> searchAllInfo() {
        List<Ticketreservationtbl> einfoList = null;
        Query query = em.createNamedQuery("Ticketreservationtbl.findAll", Ticketreservationtbl.class);
        einfoList = query.getResultList();
        return einfoList;
    }

    public List<Ticketreservationtbl> searchAllInfoByUserName(Usertbl usertbl) {
        List<Ticketreservationtbl> einfoList = null;
        Query query = em.createNamedQuery("Ticketreservationtbl.findByAllDataByUserName", Ticketreservationtbl.class);
        query.setParameter("userName", usertbl.getUserName());
        System.out.println("user is " + usertbl.getUserName());
        einfoList = query.getResultList();
        return einfoList;

    }

    public Integer totalBusNo() {
        Query query = em.createNamedQuery("Bustbl.totalBusNo");
//        query.setParameter("structureId", nebeid);
        try {

            String result = String.valueOf(query.getSingleResult());
            return Integer.parseInt(result);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public Integer totalRoutNo() {
        Query query = em.createNamedQuery("Routetbl.totalRoutNo");

        try {

            String result = String.valueOf(query.getSingleResult());
            return Integer.parseInt(result);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public Integer totalCustomerNo() {
        Query query = em.createNamedQuery("Ticketreservationtbl.totalCustomerNoNo");
        try {

            String result = String.valueOf(query.getSingleResult());
            return Integer.parseInt(result);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public Integer totalBookingNo() {
        Query query = em.createNamedQuery("Ticketreservationtbl.totalCustomerNoNo");
        try {

            String result = String.valueOf(query.getSingleResult());
            return Integer.parseInt(result);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public Integer totalAdminNo() {
        Query query = em.createNamedQuery("Usertbl.totalUserNo");
        try {

            String result = String.valueOf(query.getSingleResult());
            return Integer.parseInt(result);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public Double totalPriceNo() {
        Query query = em.createNamedQuery("Ticketreservationtbl.totalPrice");
        try {
            Object result = query.getSingleResult();
            if (result != null) {
                String resultStr = String.valueOf(result);
                try {
                    return Double.parseDouble(resultStr);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    return 0.0; // or any other default value you prefer
                }
            } else {
                return 0.0;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0.0;
        }

    }

    public String generateNextTicketNo() {
        TypedQuery<String> query = em.createNamedQuery("Ticketreservationtbl.findLastTickNo", String.class);
        query.setMaxResults(1);
//        String lastTicketNo = query.getSingleResult();
        String lastTicketNo = null;
        String nextTicketNo = null;
        try {
            lastTicketNo = query.getSingleResult();
        } catch (NoResultException e) {
            nextTicketNo = "TCK-00001"; // Initial patient ID number
        }
        if (lastTicketNo != null) {
            int lastNumber = Integer.parseInt(lastTicketNo.substring(5));
            nextTicketNo = "TCK-" + String.format("%05d", lastNumber + 1);

        }
        return nextTicketNo;
    }

    public void updateStatusToInactive() {
        // Get the current date
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String currentDateString = dateFormat.format(currentDate);
        System.out.println("current Date is  " + currentDateString);
        // Query to update status field where dateField is in the past
//       Query query = em.createNamedQuery("Ticketreservationtbl.updateStatus", Ticketreservationtbl.class);
        Query query = em.createQuery("UPDATE Ticketreservationtbl t SET t.status = 'Expired' WHERE t.scheduleId.scheduleDate < :currentDateString");
        query.setParameter("currentDateString", currentDateString);
        // Execute the update query
        int updatedCount = query.executeUpdate();
        System.out.println("Number of entities updated to 'Expired': " + updatedCount);
    }

    public Ticketreservationtbl searchTicketReservDataByTckNo(Ticketreservationtbl ticketreservationtbl) {
        Query query = em.createNamedQuery("Ticketreservationtbl.findByTckNo");
        query.setParameter("ticketNo", ticketreservationtbl.getTicketNo());
        try {
            Ticketreservationtbl list = (Ticketreservationtbl) (query.getSingleResult());
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Ticketreservationtbl> searchAllTByTckNo(Ticketreservationtbl ticketreservationtbl) {
        List<Ticketreservationtbl> einfoList = null;
        Query query = em.createNamedQuery("Ticketreservationtbl.findByTckNo", Ticketreservationtbl.class);
        query.setParameter("ticketNo", ticketreservationtbl.getTicketNo());
        einfoList = query.getResultList();
        return einfoList;

    }

    public Collection ticketReservationReport(HashMap hashMap) {
        DataReadQuery databaseQuery = new DataReadQuery(); // Add eclipslink liberary
        StoredProcedureCall call = new StoredProcedureCall();
        call.setProcedureName("ticketReservationProcedure");
//        call.useNamedCursorOutputAsResultSet("O_CURSSOR"); 
        call.addNamedArgumentValue("fromDate", hashMap.get("fromDate").toString());
        call.addNamedArgumentValue("toDate", hashMap.get("toDate").toString());   
        databaseQuery.setCall(call);
        Query query = ((JpaEntityManager) em.getDelegate()).createQuery(databaseQuery);
        if (query.getResultList().isEmpty() != true) {
            return (Collection) query.getResultList();
        } else {
            return null;
        }
    }

    public Collection printReservTicket(HashMap hashMap) {
        DataReadQuery databaseQuery = new DataReadQuery(); // Add eclipslink liberary
        StoredProcedureCall call = new StoredProcedureCall();
        call.setProcedureName("printReservTicketProcedure");
//        call.useNamedCursorOutputAsResultSet("O_CURSSOR");  
        call.addNamedArgumentValue("ticketNo", hashMap.get("ticketNo").toString());
        call.addNamedArgumentValue("scheduleDate", hashMap.get("scheduleDate").toString());
        databaseQuery.setCall(call);
        Query query = ((JpaEntityManager) em.getDelegate()).createQuery(databaseQuery);
        if (query.getResultList().isEmpty() != true) {
            return (Collection) query.getResultList();
        } else {
            return null;
        }
    }
    
       public boolean checkDipositSlipNoDuplicated(Ticketreservationtbl ticketreservationtbl) {
        boolean checkStatus;
        Query query = em.createNamedQuery("Ticketreservationtbl.findByBankSlipNo");
        query.setParameter("dipositSlipNo", ticketreservationtbl.getDipositSlipNo());
         System.out.println("dipositSlipNo "+ ticketreservationtbl.getDipositSlipNo());
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
