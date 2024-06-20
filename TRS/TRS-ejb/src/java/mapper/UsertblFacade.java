/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import entity.Usertbl;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author demis
 */
@Stateless
public class UsertblFacade extends AbstractFacade<Usertbl> {

    @PersistenceContext(unitName = "TRS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsertblFacade() {
        super(Usertbl.class);
    }

    public List<Usertbl> searchAllInfo() {

        List<Usertbl> einfoList = null;
        Query query = em.createNamedQuery("Usertbl.findAll", Usertbl.class);
        einfoList = query.getResultList();
        return einfoList;
    }
    
       public List<Usertbl> searchUserByUserName(Usertbl usertbl) {
   
        List<Usertbl> user = null;
        Query query = em.createNamedQuery("Usertbl.findByUserName", Usertbl.class);
          query.setParameter("userName", usertbl.getUserName());
       
        user = query.getResultList();
        return user;
    }

    public void validateLogin(Usertbl usertbl) {
        String message;
        TypedQuery<Usertbl> query = em.createNamedQuery("Usertbl.findByUsernameAndPassword", Usertbl.class);
        query.setParameter("username", usertbl.getUserName());
        query.setParameter("password", usertbl.getPassword());
        try {
            Usertbl login = query.getSingleResult();
            message = "Valid User";
        } catch (Exception e) {
            message = "Invalid Login Credentials";
        }
    }

//       public boolean registerUser(Usertbl usertbl) {
//         
//        try {
//            System.out.println("User name "+ usertbl.getFirstName());
//             String encryptedPassword = PasswordUtils.hashPassword(usertbl.getPassword()); // Encrypt the password
//            usertbl.setPassword(encryptedPassword);
//            em.persist(usertbl);
//            return true;
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return false;
//        }
//    }
//  
    public boolean authenticate(String userName, String password) {
        boolean checkUserStatus;
//            TypedQuery<Usertbl> query = em.createNamedQuery("Usertbl.findByUsernameAndPassword", Usertbl.class);
//        Query query = em.createQuery("SELECT u FROM Usertbl u where u.userName = :userName AND u.password= :password");
//        query.setParameter("userName", userName);
//        query.setParameter("password", password);
//        return !query.getResultList().isEmpty();
        TypedQuery<Usertbl> query = em.createNamedQuery("Usertbl.findByUserName", Usertbl.class);
        query.setParameter("userName", userName);
        try {
            if (query.getResultList().size() >= 1) {
                Usertbl logintbl = query.getSingleResult();
                if (logintbl != null && PasswordUtils.checkPassword(password, logintbl.getPassword())) {
                    checkUserStatus = true;
                    return checkUserStatus;
                } else {
                    checkUserStatus = false;
                    return checkUserStatus;
                }
            } else {
                checkUserStatus = false;
                return checkUserStatus;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Retrieve the user from the database using the username
//        Usertbl user = em.createQuery("SELECT u FROM Usertbl u WHERE u.userName = :userName", Usertbl.class)
//                .setParameter("userName", userName)
//                .getSingleResult();
        // Check if the retrieved user exists and if the provided password matches the stored hashed password
//        return logintbl != null && PasswordUtils.checkPassword(password, user.getPassword());
        return false;
    }
    
       public  void insertAdiminData(){
        try {
            // Create new Employee object
            Usertbl usertbl = new Usertbl();
       
             usertbl.setFirstName("Admin");
            usertbl.setLastName("Admin");
            usertbl.setMobileNo((int).91392);
            usertbl.setPassword("$2a$10$5wuVeqcfKvGqwrrzRyZPsObntzR0CDunNeWjqtWAqVSSMVr9q27Da");
            usertbl.setUserName("admin");
            usertbl.setAddress("Addis ababa");
            usertbl.setRole("Admin");
            usertbl.setPlainTaxtPassword("123");

            // Insert the user into the database
            em.persist(usertbl);

            } catch (Exception e) {
            // Handle exception
        }
    }
    

    public Usertbl getUserDataByUserName(String userName) {
        Query query = em.createNamedQuery("Usertbl.findByUserName");
        query.setParameter("userName", userName);

        try {
            if (query.getResultList().size() >= 1) {
                Usertbl usertbl = (Usertbl) query.getResultList().get(0);
                return usertbl;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return null;
    }

    public boolean checkUserNameDuplicated(Usertbl usertbl) {
        boolean checkStatus;
        Query query = em.createNamedQuery("Usertbl.findByUserName");
        query.setParameter("userName", usertbl.getUserName());
        System.out.println("user name is " + usertbl.getUserName());
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
