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
 * @author user
 */
@ManagedBean
@SessionScoped
public class CartManage implements Serializable {


    private List<Product> products = new ArrayList<Product>();
    private float credits;

    public CartManage() {
    }

    public String add(Product p) {
        HttpSession session = SessionUtils.getSession();

        if (session.getAttribute("name") != null) {
            if (!products.contains(p)) {
                products.add(p);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Το προίόν " + p.getName() + " προστέθηκε στο καλάθι"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Το προίόν " + p.getName() + " υπάρχει είδη στο καλάθι"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Πρέπει να είσται συνδεδεμένος για να επιλέξεται προϊόντα."));
            return "login.xhtml";
        }

        return "";
    }

    public boolean chechIfProducIsToCart(int id) {
        Product p = new Product();
        p.setProductid(id);
        if (!products.contains(p)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Το προίόν " + p.getName() + " υπάρχει είδη στο καλάθι"));
            return false;

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Το προίόν " + p.getName() + " υπάρχει είδη στο καλάθι"));
            return true;
        }

    }

    public void remove(Product p) {
        products.remove(p);

    }

    public void clearCart() {
        products.clear();
    }

    public float getTotalPrice() {

        FacesContext ctx = FacesContext.getCurrentInstance();
        Float vat = Float.parseFloat(ctx.getExternalContext().getInitParameter("vat"));

        float total = 0;
        for (final Product product1 : products) {
            total += product1.getSellprice() * product1.getQty() * (vat + 1);
        }
        if (total == 0.0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Το καλάθι σας δεν έχει προϊόντα."));
        }
        return getRound2demicalsNumber(total);
    }

    public float getRound2demicalsNumber(float num) {
        return (float) (Math.round(num * 100.0) / 100.0);
    }

    public float calculateShipping(float totalPrice) {
        if (totalPrice > 100.00) {
            return 0;
        } else {
            return 5;
        }
    }

    public int getCartCount() {
        return products.size();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public float getCredits() {
        return credits;
    }

    public void setCredits(float credits) {
        this.credits = credits;
    }


}
