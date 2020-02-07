/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import entities.Prodcategory;
import entities.Product;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import sessionsBeans.CategoryFacade;
import sessionsBeans.ProductFacade;

/**
 *
 * @author user
 */
@ManagedBean
@RequestScoped
public class KathgoriaFrontManage  implements Serializable{

    private List<Product> products;
    private Prodcategory prodcategory;
    
    @EJB
    ProductFacade productFacade;
    
    @EJB
    CategoryFacade categoryFacade;
    
    public List<Product> getAllProductsByCategory(int categoryID){
        prodcategory = categoryFacade.searchWithID(categoryID);
        return products = productFacade.getAllProductsFromDBByCategory(prodcategory);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public boolean checkIfCategoryExists(String id) throws IOException{
       if( categoryFacade.returnOneCategory(id) == null ){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Η κατηγορία που ζητήσατε δεν βρέθηκε."));
            return false;
       }
       return true;
    }
    
}
