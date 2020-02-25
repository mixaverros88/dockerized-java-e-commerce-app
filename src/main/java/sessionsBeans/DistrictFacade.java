package sessionsBeans;

import entities.District;
import org.apache.log4j.Logger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class DistrictFacade {

    final static Logger logger = Logger.getLogger(DistrictFacade.class);

    @PersistenceContext(unitName = "PrimeFacesPU")
    private EntityManager em;

    protected EntityManager getEm() {
        return em;
    }

    public List<District> findAllDistricts() {
        TypedQuery<District> query = em.createNamedQuery("District.findAll", District.class);
        return query.getResultList();
    }

    public District returnOneDistrict(String id) {
        District district;
        district = em.find(District.class, Integer.parseInt(id));
        return district;
    }

}
