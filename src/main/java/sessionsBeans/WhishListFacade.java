/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionsBeans;

import entities.Ballance;
import entities.Custvend;
import entities.Product;
import entities.Wishlist;
import helpers.DateTime;
import helpers.MailSender;
import java.util.List;
import javax.ejb.EJB;
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
public class WhishListFacade {
    
    @PersistenceContext(unitName="PrimeFacesPU")
    private EntityManager em;

    protected EntityManager getEm() {
        return em;
    }
    
    
    public void chechIfTheProductIsInWhishList(Product product, float qty){
        
        TypedQuery<Wishlist> query = em.createQuery("SELECT o FROM Wishlist o WHERE o.productid = :productid AND o.qty <= :qty AND o.inform = :inform", Wishlist.class)
                .setParameter("productid", product)
                .setParameter("qty", qty)
                .setParameter("inform", 0);
        
        List<Wishlist> wishlist = query.getResultList();
              
        for (Wishlist whi:wishlist){
            //MailSender.send(whi.getCustvendid().getEmail(), "ezikos - Ενημέρωση Διαθεσιμότητας", "<p>Αγαπητέ "+whi.getCustvendid().getFname()+" "+whi.getCustvendid().getLname()+",</p><h3>Το προϊόν: "+whi.getProductid().getName()+" είναι διαθέσιμο.</h3><p> Πατηστε <a href='http://localhost:8081/PrimeFaces/faces/proion.xhtml?id="+whi.getProductid().getProductid()+"'>εδώ</a> για να το αγοράσετε</p>");
            
            MailSender.send(whi.getCustvendid().getEmail(), "ezikos - Ενημέρωση Διαθεσιμότητας", "Το προϊόν:"+whi.getProductid().getName()+" είναι διαθέσιμο. <br/> Πατηστε <a href='http://localhost:8081/PrimeFaces/faces/proion.xhtml?id="+whi.getProductid().getProductid()+"'>εδώ</a> για να το αγοράσεται");
            updateWhishListInform(whi.getWishlistid());
        }
        
    }
    
    public List<Wishlist> getAllWishlistBySyuserFromDB(Custvend custvend){
               
        TypedQuery<Wishlist> query = em.createQuery("SELECT p FROM Wishlist p WHERE p.custvendid = :custvendid", Wishlist.class).setParameter("custvendid", custvend);

         List<Wishlist> results = query.getResultList();
          
        return results;
    }
    
    public void updateWhishListInform(int id){
       // , INFORMDATE ="+DateTime.getDateTimeForDatabase()+"
        Query query = em.createNativeQuery("UPDATE wishlist SET INFORM ="+1+" WHERE WISHLISTID="+id);
        query.executeUpdate();
    }
    
    public boolean insertWhiShListToDB(Wishlist wishlist){  
        
        try{  
            em.persist(wishlist);
            em.flush();
            return true;
        }catch(Exception ex){
            System.out.println(ex);
            return false;
        }  
    }
}
