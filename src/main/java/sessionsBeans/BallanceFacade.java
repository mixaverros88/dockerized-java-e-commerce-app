package sessionsBeans;

import entities.Ballance;
import org.apache.log4j.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class BallanceFacade {

    final static Logger logger = Logger.getLogger(BallanceFacade.class);

    @PersistenceContext(unitName = "PrimeFacesPU")
    private EntityManager em;

    public boolean insertBalanceToDB(Ballance ballance) {
        if(logger.isDebugEnabled()){ logger.debug("Insert Balance in database : " + ballance.toString()); }
        try {
            em.persist(ballance);
            em.flush();
            return true;
        } catch (Exception ex) {
            logger.error("This is error : " + ex);
            return false;
        }
    }
}
