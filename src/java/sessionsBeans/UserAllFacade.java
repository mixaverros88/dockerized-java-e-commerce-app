/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionsBeans;

import entities.User;
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
public class UserAllFacade {
    
    @PersistenceContext(unitName="PrimeFacesPU")
    private EntityManager em;
    
    protected EntityManager getEm(){
        return em;
    }
    
    public List<User> getAllUsers(){
        TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
        List<User> results = query.getResultList();
        
        return results;
    } 
    
    
     public User searchWithat(String at){
               
        User user = null;
        TypedQuery<User> query = em.createNamedQuery("User.findByAt", User.class).setParameter("at", at);
        User results = query.getSingleResult();
           
//        try{
//            user = (User) em.createQuery(
//
//            "SELECT u FROM User u WHERE u.at = :at")
//            .setParameter("at", 1)
//            .getSingleResult();
//        
//            if(user!=null){ 
//                return user; 
//            }
//            
//        }catch(Exception ex){
//            System.out.println("Error in login() -->" + ex.getMessage());
//            return user; 
//        }
        
        return results;    
        
    }
     
     
    public int deleteUser(int at){
        
    Query query = em.createNativeQuery("DELETE FROM user WHERE at ="+at);
    //System.out.println(query.executeUpdate());
        
       return query.executeUpdate();
    }
}
