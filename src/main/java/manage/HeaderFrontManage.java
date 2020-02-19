/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import entities.Prodcategory;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import sessionsBeans.CategoryFacade;

/**
 * @author user
 */
@ManagedBean
@RequestScoped
public class HeaderFrontManage implements Serializable {

    private List<Prodcategory> prodcategory;

    @EJB
    CategoryFacade categoryFacade;

    public List<Prodcategory> getAllUserData() {
        return prodcategory = categoryFacade.getAllCategoriesWhereIsActive();
    }

    public List<Prodcategory> getProdcategory() {
        return prodcategory;
    }

    public void setProdcategory(List<Prodcategory> prodcategory) {
        this.prodcategory = prodcategory;
    }


}
