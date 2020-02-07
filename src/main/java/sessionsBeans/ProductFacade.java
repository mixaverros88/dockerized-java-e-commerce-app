/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionsBeans;

import entities.Custvend;
import entities.Prodcategory;
import entities.Product;
import entities.Produnit;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Criteria;

import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author user
 */
@Stateless
public class ProductFacade {
    
    
    
    @PersistenceContext(unitName="PrimeFacesPU")
    private EntityManager em;

    protected EntityManager getEm() {
        return em;
    }
    
    
       public boolean updateProductToDatabase(Product product){
  
       
            em.merge(product);
            em.flush();
//            em.getTransaction().begin();
//            em.merge(custvend);
//            em.flush();
//            em.getTransaction().commit();
            return true;
//                
//        }catch(Exception ex){
//            System.out.println(ex);
//            return false;
//        }  
    }
    public boolean insertProductToDB(Product product){  
        
        try{  
            em.persist(product);
            em.flush();
            return true;
        }catch(Exception ex){
            System.out.println(ex);
            return false;
        }  
    }
    
    
    public long chechIfTheVendorAsycToProductFromDB(Custvend custvend){
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(p) FROM Product p WHERE p.vendor = :vendor", Long.class).setParameter("vendor", custvend);
        long countryCount;
        return countryCount = query.getSingleResult();
        
    }

     
    public long countProductsFromDB(int roleid, int sysuser){
        //TypedQuery<Double> query = em.createQuery("SELECT SUM(o.sumamnt) FROM Orders o WHERE o.roleid = :roleid", Double.class)
       //     .setParameter(roleid, roleid);
       long countryCount;
       if(roleid ==1){
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(p) FROM Product p", Long.class);
        countryCount = query.getSingleResult();
       }else{
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(p) FROM Product p WHERE p.sysuser = :sysuser", Long.class).setParameter("sysuser", sysuser);
        countryCount = query.getSingleResult() ;
       }

        
        return countryCount;
    }
        
    public int changeStatusProductFromDatabase(int status, int id){
        Query query = em.createNativeQuery("UPDATE product SET ISACTIVE ="+status+" WHERE PRODUCTID="+id);
        return query.executeUpdate();
    }
        
    public int deleteProductFromDatabase(int id){
        Query query = em.createNativeQuery("DELETE FROM product WHERE PRODUCTID ="+id);
        return query.executeUpdate();
    }
    
    public void updateQntProduct(float qnt, int id){
        Query query = em.createNativeQuery("UPDATE product SET QTY =QTY-"+qnt+" WHERE PRODUCTID="+id);
        query.executeUpdate();
    }
    
    public void updateQntProductVendorSalle(float qnt, int id){
        Query query = em.createNativeQuery("UPDATE product SET QTY =QTY+"+qnt+" WHERE PRODUCTID="+id);
        query.executeUpdate();
    }

    public List<Product> getAllProductsFromDatabase(){
        
        TypedQuery<Product> query = em.createNamedQuery("Product.findAll", Product.class);
        List<Product> results = query.getResultList();

        return results;
    }
    
    
    public List<Product> getAllProductsByIsactiveFromDB(int isactive){
       
             TypedQuery<Product> query = em.createNamedQuery("Product.findByIsactive", Product.class).setParameter("isactive", 1);
        List<Product> results = query.getResultList();
        
        return results;
    }
    
    public List<Product> getAllProductsFromDBByCategory(Prodcategory prodcategory){
        
        TypedQuery<Product> query = em.createNamedQuery("Product.findByProduCategorytid", Product.class).setParameter("prodcategoryid", prodcategory);
        List<Product> results = query.getResultList();

        return results;
    }
    
    public List<Product> getAllProductsFromDatabaseByID(int id){
        
        TypedQuery<Product> query = em.createNamedQuery("Product.findByProductid", Product.class).setParameter("productid", id);
        List<Product> results = query.getResultList();

        return results;
    }
    
        
    public int checkIfProductExistsInDB(String id){
        int foo = Integer.parseInt(id);
        TypedQuery<Product> query = em.createNamedQuery("Product.findByProductid", Product.class).setParameter("productid", foo);
        List<Product> results = query.getResultList();
        return results.size();
    }
    
    public Product getProductFromDatabaseByID(int id){
        
        Product product;
        product = em.find(Product.class, id);

        return product;
    }
    
    
     public Product returnOneProduct(String id){
        
        Product product;
        product = em.find(Product.class, Integer.parseInt(id));
        System.out.println("productFacade: "+product);
        return product;
    }
     
     
    public Product returnOneProduct2(String id){
        
        Product product;
        product = em.find(Product.class, Integer.parseInt(id));
       
        return product;
    }
    
    public Produnit returnOneUnit(String id){
        
        Produnit rol = new Produnit();
        rol = em.find(Produnit.class, Integer.parseInt(id));
       
        return rol;
    }
    
    public List<Product> searchProduct(){
        
      SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
      Session session  = sessionFactory.openSession();
      session.beginTransaction();

      Criteria criteria = session.createCriteria(Product.class);
      criteria.add(Restrictions.eq("NAME", "test2"));
      
      List<Product> products = (List<Product>) criteria.list();
      session.getTransaction().commit();
      session.close();
      
      return products;
    }
}
