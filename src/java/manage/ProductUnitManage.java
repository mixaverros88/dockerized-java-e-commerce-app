/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import entities.Prodcategory;
import entities.Product;
import entities.Produnit;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import sessionsBeans.ProductUnitFacade;

/**
 *
 * @author user
 */
@ManagedBean
@RequestScoped
public class ProductUnitManage implements Serializable{
    
    @NotNull(message = "Παρακαλώ συμπληρώστε το όνομα της Μονάδας Μέτρησης")  
    private String name;
    private String isactive;
    private List<Produnit> produnit;
    
    @EJB
    ProductUnitFacade productUnitFacade;

    public String changeStatusProductUnit(int status, int id){
        productUnitFacade.changeStatusProductUnitFromDB(status, id);
        return "";
    }
    
   private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    
    public String editProductUnit(){

        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        int produnitId = Integer.parseInt(params.get("productUnitID"));
        Produnit u =  productUnitFacade.searchWithID(produnitId);
        
        sessionMap.put("editProductUnit", u);
  
        return "/produnitEdit.xhtml?faces-redirect=true";
    }
    
    public String deleteProductUnit(int id){
        productUnitFacade.deleteProductUnitFromDB(id);
        return "";
    }
    
    public String insertProductUnit(){
        
        boolean mr=false;
        Produnit productUnitInsert = new Produnit();
        productUnitInsert.setName(name);
        
        
        int isactiveInt;
        if(isactive.equals("true")){
            isactiveInt=1;
        }else{
            isactiveInt=0;
        }
        productUnitInsert.setIsactive(isactiveInt);
        mr = productUnitFacade.insertProductUnitToDB(productUnitInsert);
        
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
    
    
    public List<Produnit> getAllProdunit(){
        return produnit = productUnitFacade.getAllProdunitFromDB();
    }

    public List<Produnit> getProdunit() {
        return produnit;
    }

    public void setProdunit(List<Produnit> produnit) {
        this.produnit = produnit;
    }
    
    
    
}
