/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionsBeans;

import entities.Prodcategory;
import entities.Produnit;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author user
 */
@Stateless
public class ProductUnitFacade {
    
    @PersistenceContext(unitName="PrimeFacesPU")
    private EntityManager em;

    protected EntityManager getEm() {
        return em;
    }
    
    public int changeStatusProductUnitFromDB(int status, int id){
        Query query = em.createNativeQuery("UPDATE produnit SET ISACTIVE ="+status+" WHERE PRODUNITID="+id);
        return query.executeUpdate();
    }
    
    public List<Produnit> getAllProdunitFromDB(){

        TypedQuery<Produnit> query = em.createNamedQuery("Produnit.findAll", Produnit.class);
        List<Produnit> results = query.getResultList();
        
        return results;
    } 
    
    public List<Produnit> getAllProdunit(){
        TypedQuery<Produnit> query = em.createNamedQuery("Produnit.findByIsactive", Produnit.class).setParameter("isactive", 1);
        List<Produnit> results = query.getResultList();
        
        return results;
    } 
       
    public boolean updatProductUnitToDB(Produnit produnitid){

        Query query = em.createNativeQuery("UPDATE produnit SET NAME ='"+produnitid.getName()+"', ISACTIVE ="+produnitid.getIsactive()+"  WHERE PRODUNITID="+produnitid.getProdunitid());
        query.executeUpdate();
         
         return true;
    }  
    
    public Produnit searchWithID(int id){
               
        Produnit produnit = null;
        TypedQuery<Produnit> query = em.createNamedQuery("Produnit.findByProdunitid", Produnit.class).setParameter("produnitid", id);
        Produnit results = query.getSingleResult();
        System.out.println("cccccccccccccc"+results.getName());
        
        
        return results;    
        
    }
      
    public int deleteProductUnitFromDB(int id){
        Query query = em.createNativeQuery("DELETE FROM produnit WHERE PRODUNITID ="+id);
       return query.executeUpdate();
    }
    
    public boolean insertProductUnitToDB(Produnit produnit){  
        try{  
            em.persist(produnit);
            em.flush();
            return true;
        }catch(Exception ex){
            System.out.println(ex);
            return false;
        }  
    }
}
