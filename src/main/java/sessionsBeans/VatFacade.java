package sessionsBeans;

import entities.Vat;
import org.apache.log4j.Logger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class VatFacade {

    final static Logger logger = Logger.getLogger(VatFacade.class);

    @PersistenceContext(unitName = "PrimeFacesPU")
    private EntityManager em;

    public Vat returnOneVat(String id) {
        if(logger.isDebugEnabled()){ logger.debug("Return vat with id: " + id); }
        return em.find(Vat.class, Integer.parseInt(id));
    }

    public Vat searchWithID(int id) {
        if(logger.isDebugEnabled()){ logger.debug("Search vat with id : " + id); }
        return  em.find(Vat.class, id);
    }

    public boolean updateVatToDatabase(Vat vat) {
        if(logger.isDebugEnabled()){ logger.debug("Update Vat: " + vat); }
        if(vat != null){
            em.merge(vat);
            return true;
        }else {
            return false;
        }
    }

    public List<Vat> getAllVatFromDB() {
        if(logger.isDebugEnabled()){ logger.debug("Get All Vats"); }
        TypedQuery<Vat> query = em.createNamedQuery("Vat.findAll", Vat.class);
        return query.getResultList();
    }

    public int deleteVatFromDB(int id) {
        if(logger.isDebugEnabled()){ logger.debug("Delete Vat FromDB "); }
        Vat vat = em.find(Vat.class, id);
        if(vat != null){
            em.remove(vat);
            return 1;
        }else {
            return 0;
        }

    }

    public int changeStatusVatFromDB(int status, int id) {
        if(logger.isDebugEnabled()){ logger.debug("Change Status Vat From DB : "); }
        Vat vat = em.find(Vat.class, id);
        if(vat != null){
            vat.setIsactive((short) status);
            em.merge(vat);
            return 1;
        }else {
            return 0;
        }
    }

    public boolean insertVatToDB(Vat vat) {
        if(logger.isDebugEnabled()){ logger.debug("Insert Vat in database : " + vat.toString()); }
        try {
            em.persist(vat);
            em.flush();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
