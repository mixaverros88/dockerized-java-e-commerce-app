/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionsBeans;

import entities.Custvend;
import entities.Orderlines;
import entities.Orders;
import entities.Product;
import entities.Roles;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.criterion.Order;

/**
 * @author user
 */
@Stateless
public class OrderlinesFacade {

    @PersistenceContext(unitName = "PrimeFacesPU")
    private EntityManager em;

    protected EntityManager getEm() {
        return em;
    }


    public double orderDetailsSallesByVendorFROMDB(Custvend custvend) {

        Roles r = new Roles();
        r.setRoleid(2);
        try {
            TypedQuery<Double> query = em.createQuery("SELECT ROUND(SUM(o.linesumval), 2) FROM Orderlines o WHERE o.vendor = :vendor AND o.roleid = :roleid", Double.class)
                    .setParameter("vendor", custvend)
                    .setParameter("roleid", r);
            double countryCount = query.getSingleResult();
            return countryCount;
        } catch (Exception e) {
            return 0.0;
        }
    }

    public List<Orderlines> orderDetailsFROMDB(Orders order) {
        TypedQuery<Orderlines> query = em.createQuery("SELECT o FROM Orderlines o WHERE o.orderid = :orderid ", Orderlines.class).setParameter("orderid", order);
        return query.getResultList();
    }


    public List<Orderlines> getAllOrderSFromVendroFROMDB(Custvend custvend) {
        TypedQuery<Orderlines> query = em.createQuery("SELECT o FROM Orderlines o WHERE o.vendor = :vendor ORDER BY o.orderlinesid DESC", Orderlines.class).setParameter("vendor", custvend);
        return query.getResultList();
    }

    public List<Orderlines> getAllProductsOrderByMaxSellsFromDB() {
        TypedQuery<Orderlines> query = em.createQuery("SELECT o FROM Orderlines o GROUP BY o.productid ORDER BY o.linesumval DESC", Orderlines.class);
        return query.setMaxResults(6).getResultList();
    }


    public Orderlines getQntOfProduct(Orders order) {

        TypedQuery<Orderlines> query = em.createQuery("SELECT o FROM Orderlines o WHERE o.orderid = :orderid", Orderlines.class)
                .setParameter("orderid", order);
        return query.getSingleResult();
    }


    public List<Orderlines> getQntsOfProduct(Orders order) {

        TypedQuery<Orderlines> query = em.createQuery("SELECT o FROM Orderlines o WHERE o.orderid = :orderid", Orderlines.class)
                .setParameter("orderid", order);
        return query.getResultList();
    }

    public double orderDetailsFROMDB(Custvend custvend) {
        try {
            TypedQuery<Double> query = em.createQuery("SELECT ROUND(SUM(o.linesumval), 2) FROM Orderlines o WHERE o.vendor = :vendor", Double.class).setParameter("vendor", custvend);
            double countryCount = query.getSingleResult();
            return countryCount;
        } catch (Exception e) {
            return 0.0;
        }
    }

    public boolean insertProductToOrdersLinesToDB(Orderlines orderlines) {


        try {
            em.persist(orderlines);
            em.flush();
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }

    }

}
