package sessionsBeans;

import entities.Prodcategory;
import org.apache.log4j.Logger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class CategoryFacade {

    final static Logger logger = Logger.getLogger(CategoryFacade.class);

    @PersistenceContext(unitName = "PrimeFacesPU")
    private EntityManager em;

    protected EntityManager getEm() {
        return em;
    }

    public List<Prodcategory> getAllCategories() {
        if(logger.isDebugEnabled()){ logger.debug("Get All Categories"); }
        TypedQuery<Prodcategory> query = em.createNamedQuery("Prodcategory.findAll", Prodcategory.class);
        return query.getResultList();
    }

    public List<Prodcategory> getAllCategoriesWhereIsActive() {
        if(logger.isDebugEnabled()){ logger.debug("Get All Categories Where Is Active"); }
        TypedQuery<Prodcategory> query = em.createNamedQuery("Prodcategory.findByIsactive", Prodcategory.class).setParameter("isactive", 1);
        return query.getResultList();
    }


    public Prodcategory returnOneCategory(String id) {
        if(logger.isDebugEnabled()){ logger.debug("Return category where id: " + id); }
        return em.find(Prodcategory.class, Integer.parseInt(id));
    }

    public Prodcategory searchWithID(int id) {

        Prodcategory prodcategory = null;
        TypedQuery<Prodcategory> query = em.createNamedQuery("Prodcategory.findByProdcategoryid", Prodcategory.class).setParameter("prodcategoryid", id);
        Prodcategory results = query.getSingleResult();
        System.out.println("Name: " + results.getName());
        return results;
    }

    public int deleteCategory(int id) {
        if(logger.isDebugEnabled()){ logger.debug("Delete Category with id: " + id); }
        Query query = em.createNativeQuery("DELETE FROM prodcategory WHERE PRODCATEGORYID =" + id);
        return query.executeUpdate();
    }

    public int coutnCategories() {
        if(logger.isDebugEnabled()){ logger.debug("Count categories"); }
        TypedQuery<Prodcategory> query = em.createNamedQuery("Prodcategory.findAll", Prodcategory.class);
        return query.getResultList().size();
    }

    public int changeStatus(int status, int id) {
        if(logger.isDebugEnabled()){ logger.debug("Change status to : " + status + " + of a category with id : " + id); }
        Query query = em.createNativeQuery("UPDATE prodcategory SET ISACTIVE =" + status + " WHERE PRODCATEGORYID=" + id);
        return query.executeUpdate();
    }

    public boolean updateCategoryToDatabase(Prodcategory prodcategory) {
        if(logger.isDebugEnabled()){ logger.debug("Update category"); }
        Query query = em.createNativeQuery("UPDATE prodcategory SET NAME ='" + prodcategory.getName() + "', ISACTIVE =" + prodcategory.getIsactive() + "  WHERE PRODCATEGORYID=" + prodcategory.getProdcategoryid());
        query.executeUpdate();
        return true;
    }

    public boolean insertCategoryToDB(Prodcategory prodcategory) {
        if(logger.isDebugEnabled()){ logger.debug("Insert category to database"); }
        try {
            em.persist(prodcategory);
            em.flush();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
