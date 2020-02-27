package sessionsBeans;

import entities.Prodcategory;
import org.apache.log4j.Category;
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
        Prodcategory prodcategory = em.find(Prodcategory.class, id);
        if(prodcategory != null){
            em.remove(id);
            return 1;
        }else {
            return 0;
        }
    }

    public int coutnCategories() {
        if(logger.isDebugEnabled()){ logger.debug("Count categories"); }
        TypedQuery<Prodcategory> query = em.createNamedQuery("Prodcategory.findAll", Prodcategory.class);
        return query.getResultList().size();
    }

    public int changeStatus(int status, int id) {
        if(logger.isDebugEnabled()){ logger.debug("Change status to : " + status + " + of a category with id : " + id); }
        Prodcategory prodcategory = em.find(Prodcategory.class, id);
        if( prodcategory != null){
            prodcategory.setIsactive(status);
            em.merge(prodcategory);
            return 1;
        }else {
            return 0;
        }

    }

    public boolean updateCategoryToDatabase(Prodcategory prodcategory) {
        if(logger.isDebugEnabled()){ logger.debug("Update category"); }
        if( prodcategory != null){
            em.merge(prodcategory);
            return true;
        }else {
            return false;
        }
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
