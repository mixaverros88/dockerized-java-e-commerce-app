package sessionsBeans;

import entities.Custvend;
import entities.Product;
import entities.Roles;
import org.apache.log4j.Logger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class SallesFacade {

    final static Logger logger = Logger.getLogger(SallesFacade.class);

    @PersistenceContext(unitName = "PrimeFacesPU")
    private EntityManager em;

    public List<Product> findProductsCombineByVendor(Custvend custvend) {
        TypedQuery<Product> query = em.createQuery("SELECT o FROM Product o WHERE o.vendor = :vendor", Product.class).setParameter("vendor", custvend);
        return query.getResultList();
    }

    public double countSumSallesFromDB() {
        Roles role = new Roles();
        role.setRoleid(2);
          try{
            TypedQuery<Double> query = em.createQuery("SELECT ROUND(SUM(o.sumamnt), 2) FROM Orders o WHERE o.roleid = :roleid", Double.class).setParameter("roleid", role);
            double countryCount = query.getSingleResult();
            return countryCount;
             } catch (Exception e) {
           return 0.0;
        }
    }

    public double countSumSallesbyVendorFromDB(Custvend custvend) {
        Query query = em.createQuery("SELECT ROUND(SUM(o.sumamnt), 2) FROM Orders o WHERE o.custvendid = :custvendid");
        query.setParameter("custvendid", custvend);

        try{

           double countryCount = (double) query.getSingleResult();
           return countryCount;
        } catch (Exception e) {
           return 0.0;
        }

    }
}
