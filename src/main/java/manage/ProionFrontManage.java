/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import entities.Custvend;
import entities.Product;
import entities.Wishlist;
import helpers.DateTime;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import sessionsBeans.ProductFacade;
import sessionsBeans.WhishListFacade;

/**
 * @author user
 */
@ManagedBean
@RequestScoped
public class ProionFrontManage implements Serializable {
    private Product pro;
    private float selectqnt;

    @EJB
    ProductFacade productFacade;

    @EJB
    WhishListFacade wishListFacade;

    @ManagedProperty(value = "#{cartManage}")
    CartManage myCart;


    public void addToCart() {
        int qnt = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("sendQty"));

        int id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
        System.out.println("qnt: " + qnt + " id: " + id);
        Product pro = productFacade.getProductFromDatabaseByID(id);
        pro.setQty(qnt);
        myCart.add(pro);


    }

    public float getRound2demicalsNumber(float num) {
        return (Math.round(num * 100.00) / 100.00f);
    }

    public float getRound(float num) {
        return Math.round(num);

    }

    public void addToWishList() {
        HttpSession session = SessionUtils.getSession();
        Custvend custvend = (Custvend) session.getAttribute("Custvend");


        float qnt = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("sendQty"));
        int id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));

        System.out.println("qnt: " + qnt + " id: " + id);
        Product pro = productFacade.getProductFromDatabaseByID(id);
        Wishlist wishlist = new Wishlist();
        wishlist.setCustvendid(custvend);
        wishlist.setProductid(pro);
        wishlist.setInsdate(DateTime.getNowDateTime());
        wishlist.setQty(qnt);

        if (wishListFacade.insertWhiShListToDB(wishlist)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Το προϊόν προσθέθηκε στην Wishlist"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Το προϊόν Δεν προσθέθηκε στην Wishlist"));
        }


    }

    public String getIdFromUrl() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
    }

    public boolean checkIfProductExists() {
        if (productFacade.checkIfProductExistsInDB(getIdFromUrl()) == 0) {
//           FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Το προϊόν που ζητήσατε δεν βρέθηκε."));
            return false;
        }
        return true;
    }

    public void checkIfProductExistsInCart(int id) {
        myCart.chechIfProducIsToCart(id);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Το προϊόν δεν υπάρχει"));

    }

    public Product getProduct() {
        return productFacade.returnOneProduct(getIdFromUrl());
    }

    public CartManage getMyCart() {
        return myCart;
    }

    public void setMyCart(CartManage myCart) {
        this.myCart = myCart;
    }


    public Product getPro() {
        return pro;
    }

    public void setPro(Product pro) {
        this.pro = pro;
    }

    public float getSelectqnt() {
        return selectqnt;
    }

    public void setSelectqnt(float selectqnt) {
        this.selectqnt = selectqnt;
    }


}
