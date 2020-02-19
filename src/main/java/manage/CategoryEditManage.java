/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import entities.Prodcategory;

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

/**
 * @author user
 */
@ManagedBean
@RequestScoped
public class CategoryEditManage implements Serializable {


    @NotNull(message = "Name can't be null")
    private String name;
    private String isactive;
    private int prodcategoryid;
    private Prodcategory category;


    private Prodcategory prodcategory;

    @EJB
    CategoryFacade categoryFacade;

    private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

    @PostConstruct
    public void init() {

        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();

        category = (Prodcategory) sessionMap.get("editUser");
        System.out.println("22222" + category.getName());
    }

    public String updateCategory() {

        Prodcategory categoryUpdate = new Prodcategory();

        categoryUpdate.setName(category.getName());

        System.out.println("Category is active: " + String.valueOf(category.getIsactive()));
        categoryUpdate.setIsactive(category.getIsactive());

        //mhnhmata από το magaebean στην σελίδα
        if (categoryFacade.updateCategoryToDatabase(categoryUpdate)) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Η κατηγορία ανανεώθηκε επιτυχώς"));
            return "categoryAll";
        }

        return "";
    }


    public Prodcategory getCategory() {
        return category;
    }

    public void setCategory(Prodcategory category) {
        this.category = category;
    }


    public int getProdcategoryid() {
        return prodcategoryid;
    }

    public void setProdcategoryid(int prodcategoryid) {
        this.prodcategoryid = prodcategoryid;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Prodcategory getProdcategory() {
        return prodcategory;
    }

    public void setProdcategory(Prodcategory prodcategory) {
        this.prodcategory = prodcategory;
    }
}
