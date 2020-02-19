/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import entities.Custvend;
import entities.Orderlines;
import entities.Orders;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.TypedQuery;

import sessionsBeans.OrderlinesFacade;
import sessionsBeans.OrdersFacade;

/**
 * @author user
 */
@ManagedBean
@RequestScoped
public class SallesDesc implements Serializable {

    @EJB
    OrderlinesFacade orderlinesFacade;

    @EJB
    OrdersFacade ordersFacade;

    public List<Orderlines> orderDetails(int orderID) {
        Orders o = ordersFacade.getOrderByIDFromDB(orderID);
        return orderlinesFacade.orderDetailsFROMDB(o);
    }


    public float getRound2demicalsNumber(float num) {
        return (float) (Math.round(num * 100.0) / 100.0);
    }

    public List<Orderlines> getAllOrderSFromVendro(Custvend custvend) {

        return orderlinesFacade.getAllOrderSFromVendroFROMDB(custvend);
    }
}
