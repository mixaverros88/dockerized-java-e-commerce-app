/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionsBeans;


import entities.Prodcategory;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author user
 */
@Stateless
public class CategoryFacade {
    
    @PersistenceContext(unitName="PrimeFacesPU")
    private EntityManager em;

    protected EntityManager getEm() {
        return em;
    }
    
    public List<Prodcategory> getAllCategories(){
        TypedQuery<Prodcategory> query = em.createNamedQuery("Prodcategory.findAll", Prodcategory.class);
        List<Prodcategory> results = query.getResultList();
        
        return results;
    } 
    
    public List<Prodcategory> getAllCategoriesWhereIsActive(){
        TypedQuery<Prodcategory> query = em.createNamedQuery("Prodcategory.findByIsactive", Prodcategory.class) .setParameter("isactive", 1);
        List<Prodcategory> results = query.getResultList();
        
        return results;
    } 
      
    
    public Prodcategory returnOneCategory(String id){
        
        Prodcategory category;
        category = em.find(Prodcategory.class, Integer.parseInt(id));
       
        return category;
    }
    
    public Prodcategory searchWithID(int id){
               
        Prodcategory prodcategory = null;
        TypedQuery<Prodcategory> query = em.createNamedQuery("Prodcategory.findByProdcategoryid", Prodcategory.class).setParameter("prodcategoryid", id);
        Prodcategory results = query.getSingleResult();
        System.out.println("cccccccccccccc"+results.getName());
        
        
        return results;    
        
    }
    
    public int deleteCategory(int id){
        Query query = em.createNativeQuery("DELETE FROM prodcategory WHERE PRODCATEGORYID ="+id);
       return query.executeUpdate();
    }
    
    public int coutnCategories(){
        TypedQuery<Prodcategory> query = em.createNamedQuery("Prodcategory.findAll", Prodcategory.class);
        List<Prodcategory> results = query.getResultList();
        
        return results.size();
    }
    
    public int changeStatus(int status, int id){
       

            
        Query query = em.createNativeQuery("UPDATE prodcategory SET ISACTIVE ="+status+" WHERE PRODCATEGORYID="+id);
        return query.executeUpdate();
        
    }
    
    public boolean updateCategoryToDatabase(Prodcategory prodcategory){
        
        
//       Prodcategory pc = new Prodcategory();
//        pc.setProdcategoryid(7);
//        pc.setName("ddd");
//        pc.setSlugname("ds");
//        pc.setIsactive(1);
////        em.merge(pc);
////        em.getTransaction().commit();
//
//          
//            em.merge(pc);
//            em.flush();
    
        Query query = em.createNativeQuery("UPDATE prodcategory SET NAME ='"+prodcategory.getName()+"', ISACTIVE ="+prodcategory.getIsactive()+"  WHERE PRODCATEGORYID="+prodcategory.getProdcategoryid());
        query.executeUpdate();
         
         return true;
    }
    
    public boolean insertCategoryToDB(Prodcategory prodcategory){  
        
        try{  
            em.persist(prodcategory);
            em.flush();
            return true;
        }catch(Exception ex){
            System.out.println(ex);
            return false;
        }  
    }
    
}
