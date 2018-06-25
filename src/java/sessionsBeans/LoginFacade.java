/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionsBeans;


import entities.Custvend;
import entities.User;
import helpers.HashinUtils;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author user
 */
@Stateless
public class LoginFacade {
    
    @PersistenceContext(unitName="PrimeFacesPU")
    private EntityManager em;
    
    protected EntityManager getEm(){
        return em;
    }
    
    public Custvend getUser(String username, String password) throws NoSuchAlgorithmException{
        
       HashinUtils hu = new HashinUtils();
       String hashPassword =hu.hashPass(password);
       
        
        Custvend user = null;
        try{
            user = (Custvend) em.createQuery(

            "SELECT u FROM Custvend u WHERE u.username = :username AND u.isactive = :isactive")
            .setParameter("username", username)
            .setParameter("isactive", 1)
            .getSingleResult();
            System.out.println("password: "+password);
            System.out.println("password: "+user.getPasswd());
            
            if ( !hu.checkHash(password, user.getPasswd())){
                return user=null;
            }
            if(user!=null){ 
                return user; 
            }
            
        }catch(Exception ex){
            System.out.println("Error in login() -->" + ex.getMessage());
            return user; 
        }
        
        return user;    
        
    }
    
}
