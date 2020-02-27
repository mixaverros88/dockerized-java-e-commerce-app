package sessionsBeans;

import entities.Custvend;
import entities.Prodcategory;
import entities.Product;
import entities.Produnit;
import org.apache.log4j.Logger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class ProductFacade {

    final static Logger logger = Logger.getLogger(ProductFacade.class);

    @PersistenceContext(unitName = "PrimeFacesPU")
    private EntityManager em;

    public boolean updateProductToDatabase(Product product) {
        try {
            em.merge(product);
            em.flush();
            return true;
        }catch(Exception ex){
            return false;
        }
    }

    public boolean insertProductToDB(Product product) {
        try {
            em.persist(product);
            em.flush();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public long chechIfTheVendorAsycToProductFromDB(Custvend custvend) {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(p) FROM Product p WHERE p.vendor = :vendor", Long.class).setParameter("vendor", custvend);
        return  query.getSingleResult();
    }

    public long countProductsFromDB(int roleid, int sysuser) {
        long countryCount;
        if (roleid == 1) {
            TypedQuery<Long> query = em.createQuery("SELECT COUNT(p) FROM Product p", Long.class);
            countryCount = query.getSingleResult();
        } else {
            TypedQuery<Long> query = em.createQuery("SELECT COUNT(p) FROM Product p WHERE p.sysuser = :sysuser", Long.class).setParameter("sysuser", sysuser);
            countryCount = query.getSingleResult();
        }
        return countryCount;
    }

    public int changeStatusProductFromDatabase(int status, int id) {
        Product product = em.find(Product.class, id);
        if (product != null){
            product.setIsactive(status);
            em.merge(product);
            return 1;
        }else {
            return 0;
        }
    }

    public int deleteProductFromDatabase(int id) {
        Product product = em.find(Product.class, id);
        if (product != null){
            em.remove(product);
            return 1;
        }else{
            return 0;
        }
    }

    public void updateQntProduct(float qnt, int id) {
        Product product = em.find(Product.class, id);
        if( product != null){
            product.setQty((int) qnt);
            em.merge(product);
        }
    }

    public void updateQntProductVendorSalle(float qnt, int id) {
        Product product = em.find(Product.class, id);
        if( product != null){
            product.setQty((int) qnt);
            em.merge(product);
        }
    }

    public List<Product> getAllProductsFromDatabase() {
        TypedQuery<Product> query = em.createNamedQuery("Product.findAll", Product.class);
        return query.getResultList();
    }

    public List<Product> getAllProductsByIsactiveFromDB(int isactive) {
        TypedQuery<Product> query = em.createNamedQuery("Product.findByIsactive", Product.class).setParameter("isactive", 1);
        return query.getResultList();
    }

    public List<Product> getAllProductsFromDBByCategory(Prodcategory prodcategory) {
        TypedQuery<Product> query = em.createNamedQuery("Product.findByProduCategorytid", Product.class).setParameter("prodcategoryid", prodcategory);
        return query.getResultList();
    }

    public int checkIfProductExistsInDB(String id) {
        TypedQuery<Product> query = em.createNamedQuery("Product.findByProductid", Product.class).setParameter("productid", Integer.parseInt(id));
        return query.getResultList().size();
    }

    public Product getProductFromDatabaseByID(int id) {
        return em.find(Product.class, id);
    }

    public Product returnOneProduct(String id) {
        return em.find(Product.class, Integer.parseInt(id));
    }

    public Produnit returnOneUnit(String id) {
        return em.find(Produnit.class, Integer.parseInt(id));
    }

}
