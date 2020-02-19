/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import entities.Photos;
import entities.Product;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import sessionsBeans.CategoryFacade;
import sessionsBeans.ProductFacade;
import sessionsBeans.ProdunitFacade;
import sessionsBeans.UploadImageFacade;

/**
 * @author user
 */
@ManagedBean
@RequestScoped
public class indexFrontManage implements Serializable {

    private List<Product> products;
    private Photos photos;

    @EJB
    ProductFacade productFacade;

    @EJB
    CategoryFacade categoryFacade;

    @EJB
    ProdunitFacade produnitFacade;

    @EJB
    UploadImageFacade uploadImageFacade;

    public List<Product> getAllUserData() {
        return products = productFacade.getAllProductsFromDatabase();
    }

    public List<Product> getAllProductsByIsactive(int isactive) {
        //return products = productFacade.getAllProductsFromDatabase();
        return products = productFacade.getAllProductsByIsactiveFromDB(isactive);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }


}
