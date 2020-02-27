package sessionsBeans;

import entities.Produnit;
import org.apache.log4j.Logger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class ProductUnitFacade {

    final static Logger logger = Logger.getLogger(ProductUnitFacade.class);

    @PersistenceContext(unitName = "PrimeFacesPU")
    private EntityManager em;

    public int changeStatusProductUnitFromDB(int status, int id) {
        Produnit produnit = em.find(Produnit.class, id);
        if(produnit != null){
            em.merge(produnit);
            return 1;
        }else {
            return 0;
        }
    }

    public List<Produnit> getAllProdunitFromDB() {
        TypedQuery<Produnit> query = em.createNamedQuery("Produnit.findAll", Produnit.class);
        return query.getResultList();
    }

    public List<Produnit> getAllProdunit() {
        TypedQuery<Produnit> query = em.createNamedQuery("Produnit.findByIsactive", Produnit.class).setParameter("isactive", 1);
        return query.getResultList();
    }

    public boolean updateProductUnitToDB(Produnit produnit) {
        if( produnit != null){
            em.merge(produnit);
            return true;
        }else {
            return false;
        }
    }

    public Produnit searchWithID(int id) {
        return em.find(Produnit.class, id);
    }

    public int deleteProductUnitFromDB(int id) {
        Produnit produnit = em.find(Produnit.class, id);
        if( produnit != null){
            em.remove(produnit);
            return 1;
        }else {
            return 0;
        }
    }

    public boolean insertProductUnitToDB(Produnit produnit) {
        try {
            em.persist(produnit);
            em.flush();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
