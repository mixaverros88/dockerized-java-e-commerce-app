/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import entities.Product;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
@ManagedBean
@SessionScoped
public class WhishlistManage implements Serializable{
    
     private List<Product> products = new ArrayList<>();
     
    public String add(Product p){
        HttpSession session = SessionUtils.getSession();
        
        if(session.getAttribute("name") != null){
            if(!products.contains(p)){
                products.add(p);       
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Το προίόν "+p.getName()+" προστέθηκε στο καλάθι"));
            }else{
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Το προίόν "+p.getName()+" υπάρχει είδη στο καλάθι"));
            }
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Πρέπει να είσται συνδεδεμένος για να επιλέξεται προϊόντα."));
            return "login.xhtml";
        }

        return "";
    } 
    
    public boolean chechIfProducIsToWhisList(int id){
        Product p = new Product();
        p.setProductid(id);
        if(!products.contains(p)){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Το προίόν "+p.getName()+" υπάρχει είδη στο καλάθι"));
            return false;
            
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Το προίόν "+p.getName()+" υπάρχει είδη στο καλάθι"));
            return true;
        }
   
    } 
    
    public void remove(Product p){
        products.remove(p);
       
    }
    
    public void clearWhishlist(){
        products.clear();
    }
    
    public float getTotalPrice(){
        float total = 0;
        for (final Product product1 : products) {
                total+=product1.getBuyprice()*product1.getQty();
        }
        if (total == 0.0){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Το καλάθη σας δεν έχει προϊόντα."));
        }
        return total;
    }
    
    public int getWhishListCount(){
        return products.size();
    }
}
