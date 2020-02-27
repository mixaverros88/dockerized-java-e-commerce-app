package sessionsBeans;

import entities.District;
import org.apache.log4j.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DistrictFacade {

    final static Logger logger = Logger.getLogger(DistrictFacade.class);

    @PersistenceContext(unitName = "PrimeFacesPU")
    private EntityManager em;

    protected EntityManager getEm() {
        return em;
    }

    public District returnOneDistrict(String id) {
        return em.find(District.class, Integer.parseInt(id));
    }

}
