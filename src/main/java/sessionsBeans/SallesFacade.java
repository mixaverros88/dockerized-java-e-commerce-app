/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionsBeans;

import entities.Custvend;
import entities.Product;
import entities.Role;
import entities.Roles;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author user
 */
@Stateless
public class SallesFacade {
    
    @PersistenceContext(unitName="PrimeFacesPU")
    private EntityManager em;

    protected EntityManager getEm() {
        return em;
    }
    
    public List<Product> findProducts(){
        
        //JBQL
        TypedQuery<Product> query = em.createNamedQuery("Product.findAll", Product.class);
        List<Product> results = query.getResultList();
        
        return results;
    }
    
    
        public List<Product> findProductsCombineByVendor(Custvend custvend){
        
        //JBQL
//        TypedQuery<Product> query = em.createNamedQuery("Product.findAll", Product.class);
//        List<Product> results = query.getResultList();
        
        TypedQuery<Product> query = em.createQuery("SELECT o FROM Product o WHERE o.vendor = :vendor", Product.class).setParameter("vendor", custvend);
        List<Product> products = query.getResultList();
        return products;
       
    }
        
    
    public double countSumSallesFromDB(){

        return 0.0;
//        Roles role = new Roles();
//        role.setRoleid(2);
//          try{
//            TypedQuery<Double> query = em.createQuery("SELECT ROUND(SUM(o.sumamnt), 2) FROM Orders o WHERE o.roleid = :roleid", Double.class).setParameter("roleid", role);
//            double countryCount = query.getSingleResult();
//            return countryCount;
//             } catch (Exception e) {
//           return 0.0;
//        }
    }
    
       public double countSumSallesbyVendorFromDB(Custvend custvend){

        return 0.0;
//        Query query = em.createQuery("SELECT ROUND(SUM(o.sumamnt), 2) FROM Orders o WHERE o.custvendid = :custvendid");
//        query.setParameter("custvendid", custvend);
//
//        try{
//
//           double countryCount = (double) query.getSingleResult();
//           return countryCount;
//        } catch (Exception e) {
//           return 0.0;
//        }
        
        
    }
}
