package sessionsBeans;

import entities.Custvend;
import entities.Roles;
import org.apache.log4j.Logger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@SuppressWarnings("ALL")
@Stateless
public class CustvendFacade {

    final static Logger logger = Logger.getLogger(CustvendConvertor.class);

    @PersistenceContext(unitName = "PrimeFacesPU")
    private EntityManager em;

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
        Custvend custvend = em.find(Custvend.class, id);
        if( custvend != null){
            custvend.setIsactive(status);
            em.merge(custvend);
            return 1;
        }else {
            return 0;
        }
    }

    public int changeRegisterCustvendFromDB(int register, int id) {
        Custvend custvend = em.find(Custvend.class, id);
        if( custvend != null){
            custvend.setRegister(register);
            em.merge(custvend);
            return 1;
        }else {
            return 0;
        }
    }

    public Custvend getBallanceAmount(int id) {
        TypedQuery<Custvend> query = em.createNamedQuery("Custvend.findByCustvendid", Custvend.class).setParameter("custvendid", id);
        return query.getSingleResult();
    }

    public Custvend getCreditsAmount(int id) {
        TypedQuery<Custvend> query = em.createNamedQuery("Custvend.findByCredits", Custvend.class).setParameter("custvendid", id);
        return query.getSingleResult();
    }

    public void changeBallanceCustvendFromDB(float linesumval, int id) {
        Custvend custvend = em.find(Custvend.class, id);
        if(custvend != null){
            custvend.setBallance(linesumval);
            em.merge(custvend);
        }
    }

    public void changeCreditsCustvendFromDB(float credits, int id) {
        Custvend custvend = em.find(Custvend.class, id);
        if(custvend != null){
            custvend.setCredits(credits);
            em.merge(custvend);
        }
    }

    public boolean updateCustvendToDatabase(Custvend custvend) {
        if(logger.isDebugEnabled()){ logger.debug("Insert Balance in database : " + custvend.toString()); }
        try {
            em.persist(custvend);
            em.flush();
            return true;
        } catch (Exception ex) {
            logger.error("This is error : " + ex);
            return false;
        }
    }

    public Custvend searchWithID(int id) {
        return em.find(Custvend.class, id);
    }

    public Long checkIfPhoneNumberExists(String phone) {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(o) FROM Custvend o WHERE o.phone = :phone", Long.class).setParameter("phone", phone);
        return query.getSingleResult();
    }

    public Long checkIfΕmailExists(String email) {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(o) FROM Custvend o WHERE o.email = :email", Long.class).setParameter("email", email);
        return query.getSingleResult();
    }

    public Long checkIfUserNameExists(String username) {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(o) FROM Custvend o WHERE o.username = :username", Long.class).setParameter("username", username);
        return query.getSingleResult();
    }

    public Custvend returnOneCustvend(String id) {
        return em.find(Custvend.class, Integer.parseInt(id));
    }

    public int deleteCustvendFacadeFromDB(int id) {
        Custvend custvend = em.find(Custvend.class, id);
        if(custvend != null){
            em.remove(custvend);
            return 1;
        }else {
            return 0;
        }
    }

    public boolean insertCustVendToDB(Custvend custvend) {
        try {
            em.persist(custvend);
            em.flush();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
