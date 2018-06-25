/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import entities.Prodcategory;
import entities.Produnit;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import sessionsBeans.CategoryFacade;
import sessionsBeans.ProductUnitFacade;

/**
 *
 * @author user
 */
@ManagedBean
@RequestScoped
public class ProductUnitEditManage implements Serializable{
    
   @NotNull(message = "Name can't be null")  
    private String name;
    private String isactive;
    private int produnitid;
    
    private Produnit produnit;
    
    @EJB
    ProductUnitFacade productUnitFacade;

   private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    
    @PostConstruct
    public void init() {
        
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        
        produnit =(Produnit) sessionMap.get("editProductUnit");
        System.out.println("22222"+produnit.getName());
//        category = categoryFacade.searchWithID(2);
//        System.out.println("ccccccccccccssscc"+category.getName());
    }
    
    public String updateProductUnit(){

        boolean mr;
        Produnit produnitUpdate = new Produnit(); 
         
        produnitUpdate.setName(produnit.getName());
        produnitUpdate.setIsactive(produnit.getIsactive());
        produnitUpdate.setProdunitid(produnit.getProdunitid());

        mr = productUnitFacade.updatProductUnitToDB(produnitUpdate);
        
        //mhnhmata από το magaebean στην σελίδα
        if(mr==true){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("DATA OK"));
            return "produnitAll";
        }   
        
        return "";
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    public int getProdunitid() {
        return produnitid;
    }

    public void setProdunitid(int produnitid) {
        this.produnitid = produnitid;
    }

    public Produnit getProdunit() {
        return produnit;
    }

    public void setProdunit(Produnit produnit) {
        this.produnit = produnit;
    }

    
    
}
