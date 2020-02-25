package sessionsBeans;

import entities.Subscribe;
import org.apache.log4j.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SubscribeFacade {

    final static Logger logger = Logger.getLogger(SubscribeFacade.class);

    @PersistenceContext(unitName = "PrimeFacesPU")
    private EntityManager em;

    protected EntityManager getEm() {
        return em;
    }

    public boolean insertSubscriberToDB(Subscribe subscribe) {
        try {
            em.persist(subscribe);
            em.flush();
            return true;
        } catch (Exception ex) {
            return false;
        }

    }
}
