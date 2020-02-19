/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionsBeans;

import entities.Custvend;
import entities.Prodcategory;
import entities.Roles;
import entities.User;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * @author user
 */
@Stateless
public class CustvendFacade {

    @PersistenceContext(unitName = "PrimeFacesPU")
    private EntityManager em;

    protected EntityManager getEm() {
        return em;
    }


    public List<Custvend> getAllCustvendVendorFromDB() {

        Roles role = new Roles();
        role.setRoleid(3);
        TypedQuery<Custvend> query = em.createQuery("SELECT o FROM Custvend o WHERE o.roleid = :roleid", Custvend.class).setParameter("roleid", role);
        return query.getResultList();
    }


    public List<Custvend> getAllCustvendFromDB() {
        TypedQuery<Custvend> query = em.createNamedQuery("Custvend.findAll", Custvend.class);
        return query.getResultList();
    }

    public Float countBallanceOFAdminFromDB(int id) {
        TypedQuery<Float> query = em.createQuery("SELECT ROUND(c.ballance, 2) FROM Custvend c WHERE c.custvendid = :custvendid", Float.class).setParameter("custvendid", id);
        return query.getSingleResult();
    }

    public int changeStatusCustvendFromDB(int status, int id) {
        Query query = em.createNativeQuery("UPDATE Custvend SET ISACTIVE =" + status + " WHERE CUSTVENDID=" + id);
        return query.executeUpdate();
    }

    public int changeRegisterCustvendFromDB(int register, int id) {
        Query query = em.createNativeQuery("UPDATE Custvend SET REGISTER =" + register + " WHERE CUSTVENDID=" + id);
        return query.executeUpdate();
    }

    public Custvend getBallanceAmount(int id) {
        TypedQuery<Custvend> query = em.createNamedQuery("Custvend.findByCustvendid", Custvend.class).setParameter("custvendid", id);
        return query.getSingleResult();
    }

    public Custvend getCreditsAmount(int id) {
        TypedQuery<Custvend> query = em.createNamedQuery("Custvend.findByCredits", Custvend.class).setParameter("custvendid", id);
        Custvend results = query.getSingleResult();

        return results;
    }

    public void changeBallanceCustvendFromDB(float linesumval, int id) {
        Custvend custvend = getBallanceAmount(id);
        float balance = custvend.getBallance() + (linesumval);
        Query query = em.createNativeQuery("UPDATE Custvend SET BALLANCE =" + balance + " WHERE CUSTVENDID=" + id);
        query.executeUpdate();
    }

    public void changeCreditsCustvendFromDB(float credits, int id) {
        Custvend custvend = getBallanceAmount(id);
        credits = custvend.getCredits() + (credits);
        Query query = em.createNativeQuery("UPDATE Custvend SET credits =" + credits + " WHERE CUSTVENDID=" + id);
        query.executeUpdate();
    }


    public boolean updateCustvendToDatabase(Custvend custvend) {


        em.merge(custvend);
        em.flush();
//            em.getTransaction().begin();
//            em.merge(custvend);
//            em.flush();
//            em.getTransaction().commit();
        return true;
//                
//        }catch(Exception ex){
//            System.out.println(ex);
//            return false;
//        }  
    }

    public Custvend searchWithID(int id) {
        Custvend custvend = null;
        TypedQuery<Custvend> query = em.createNamedQuery("Custvend.findByCustvendid", Custvend.class).setParameter("custvendid", id);
        return query.getSingleResult();

    }


    public Long checkIfPhoneNumberExists(String phone) {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(o) FROM Custvend o WHERE o.phone = :phone", Long.class).setParameter("phone", phone);
        Long countryCount = query.getSingleResult();
        return countryCount;
    }

    public Long checkIfΕmailExists(String email) {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(o) FROM Custvend o WHERE o.email = :email", Long.class).setParameter("email", email);
        return query.getSingleResult();
    }

    public Long checkIfUserNameExists(String username) {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(o) FROM Custvend o WHERE o.username = :username", Long.class).setParameter("username", username);
        Long countryCount = query.getSingleResult();
        return countryCount;
    }


    public Custvend returnOneCustvend(String id) {
        Custvend custvend;
        custvend = em.find(Custvend.class, Integer.parseInt(id));
        return custvend;
    }

    public int deleteCustvendFacadeFromDB(int id) {
        Query query = em.createNativeQuery("DELETE FROM Custvend WHERE CUSTVENDID =" + id);
        return query.executeUpdate();
    }

    public boolean insertCustVendToDB(Custvend custvend) {

        try {
            em.persist(custvend);
            em.flush();
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }
}
