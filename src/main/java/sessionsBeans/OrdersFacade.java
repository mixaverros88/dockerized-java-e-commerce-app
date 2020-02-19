/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionsBeans;

import helpers.Chart;
import entities.Custvend;
import entities.Orderlines;
import entities.Orders;
import entities.Prodcategory;
import entities.Product;
import entities.Roles;
import helpers.DBConnections;
import helpers.MailSender;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * @author user
 */
@Stateless
public class OrdersFacade {

    @EJB
    ProductFacade productFacade;

    @EJB
    OrderlinesFacade orderlinesFacade;

    @PersistenceContext(unitName = "PrimeFacesPU")
    private EntityManager em;

    protected EntityManager getEm() {
        return em;
    }


    //vendorTransaction if is 1 the transaction is sale from vendor to as
    //vendorTransaction if is 2 the transaction is sale from as to customer
    public List<Orders> getAllSallesFromDB(Roles roleid, Custvend custvend, int vendorTransaction, String view) {

        //if is admin
        if (roleid.getRoleid() == 1) {

            if (view.equals("sallesAll")) {
                Roles r = new Roles();
                r.setRoleid(3);
                TypedQuery<Orders> query = em.createQuery("SELECT o FROM Orders o WHERE o.roleid = :roleid ORDER BY o.orderid DESC", Orders.class).setParameter("roleid", r);
                return query.getResultList();
            } else { //purchases
                Roles r = new Roles();
                r.setRoleid(2);
                TypedQuery<Orders> query = em.createQuery("SELECT o FROM Orders o WHERE o.roleid = :roleid ORDER BY o.orderid DESC", Orders.class).setParameter("roleid", r);
                return query.getResultList();
            }

        } else {
            TypedQuery<Orders> query = em.createQuery("SELECT o FROM Orders o WHERE o.sysuser = :sysuser ORDER BY o.orderid DESC", Orders.class).setParameter("sysuser", custvend.getCustvendid());
            return query.getResultList();
        }

    }

    public List<Orders> getAllSallesBySyuserFromDB(int id) {
        TypedQuery<Orders> query = em.createNamedQuery("Orders.findBySysuser", Orders.class).setParameter("sysuser", id);
        return query.getResultList();
    }


    public Orders getOrderByIDFromDB(int orderid) {

        Orders orders = null;
        TypedQuery<Orders> query = em.createNamedQuery("Orders.findByOrderid", Orders.class).setParameter("orderid", orderid);
        return query.getSingleResult();

    }

    public double countSumOrdersFromDB() {
        Roles role = new Roles();
        role.setRoleid(1);
        TypedQuery<Double> query = em.createQuery("SELECT ROUND(SUM(o.sumamnt), 2) FROM Orders o", Double.class);
        return query.getSingleResult();
    }

    public int changeStatusOrderFromDatabase(int status, int id, Orders order) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        StringBuilder emailProductTable = new StringBuilder();
        List<Orderlines> orderlines = orderlinesFacade.orderDetailsFROMDB(order);

        for (Orderlines ol : orderlines) {

            emailProductTable.append("<tr style=\"background-color: #e6e6e6;\"><td style=\"padding: 10px;\">").append(ol.getProductid().getName()).append("</td><td style=\"padding: 10px;\">").append(ol.getProductid().getBuyprice()).append(" €</td></tr>");
        }

        if (status == 0) {
            //productFacade.updateQntProduct(qty, product.getProductid());
            Query query = em.createNativeQuery("UPDATE orders SET ISAPPRV =0, ISCANCEL =1, INSDATE ='" + dateFormat.format(date) + "' WHERE ORDERID=" + id);


            MailSender.send(order.getCustvendid().getEmail(), "ezikos.gr - Λεπτομέριες Πώλησης", "<h3 style=\"text-align: center;\">Η πώληση σας με κωδικό: <font size=\"3\" color=\"red\">" + order.getOrderid() + "</font> ακυρώθηκε.<br/><table style=\"border: 1px solid #dcdcdc;\"><tr style=\"background-color: #e6e6e6;\"><td style=\"padding: 10px;\">Όνομα Προϊόντος</td><td style=\"padding: 10px;\">Τιμή Προϊόντος</td></tr>" + emailProductTable + "</table>");

            return query.executeUpdate();
        } else {


            List<Orderlines> orlines = orderlinesFacade.getQntsOfProduct(order);

            for (Orderlines ord : orlines) {
                productFacade.updateQntProductVendorSalle(ord.getQty(), ord.getProductid().getProductid());
            }

            Query query = em.createNativeQuery("UPDATE orders SET ISAPPRV =1, ISCANCEL =0, INSDATE ='" + dateFormat.format(date) + "' WHERE ORDERID=" + id);
            MailSender.send(order.getCustvendid().getEmail(), "ezikos.gr - Λεπτομέριες Πώλησης", "<h3 style=\"text-align: center;\">Η πώληση σας με κωδικό: <font size=\"3\" color=\"red\">" + order.getOrderid() + "</font> εγκρίθηκε.<br/><table style=\"border: 1px solid #dcdcdc;\"><tr style=\"background-color: #e6e6e6;\"><td style=\"padding: 10px;\">Όνομα Προϊόντος</td><td style=\"padding: 10px;\">Τιμή Προϊόντος</td></tr>" + emailProductTable + "</table>");

            return query.executeUpdate();
        }

    }


    public void insertPdfToOrderToDb(int id) {

        Query query = em.createNativeQuery("UPDATE orders SET invoice ='" + id + ".pdf' WHERE ORDERID=" + id);
        query.executeUpdate();

    }

    public List<Chart> listForChartFromDB() {

//https://www.thoughts-on-java.org/result-set-mapping-basics/
//SQL result set mapping
//http://www.java2s.com/Tutorial/Java/0355__JPA/SqlResultSetMappingWithEntityResultAndFieldResult.htm 

//        TypedQuery<Chart> query  = em.createQuery("SELECT CAST(o.orderdate AS date) as dateChart, ROUND(SUM(o.sumamnt),2) as sumChart FROM Orders o group by CAST(o.orderdate AS date)", Chart.class);
//        List<Chart> results = query.getResultList();


//        List<TestClass> result = new ArrayList<>();
//        Query query = em.createQuery("SELECT new model.TestClass(c.cName, v.vName) FROM Candidates c, Voters v where c.cName=v.vName");
//        result = query.getResultList();
//        return result;

        Roles r = new Roles();
        r.setRoleid(2);
        List<Chart> result = new ArrayList<>();
        String queryStr = "SELECT new helpers.Chart(CAST(o.orderdate AS date) as dateChart, ROUND(SUM(o.sumamnt),2) as sumChart) FROM Orders o WHERE o.isapprv= :isapprv AND o.roleid= :roleid group by CAST(o.orderdate AS date)";
        short isapprv = 1;
        Query query = em.createQuery(queryStr).setParameter("isapprv", isapprv).setParameter("roleid", r);
        result = query.setMaxResults(6).getResultList();


//         List<Chart> ch = (List<Chart>)results;
//
//        System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz"+results.size());

        return result;


    }

    public boolean insertProductToOrdersToDB(Orders Orders) {

        try {
            em.persist(Orders);
            em.flush();
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }

    }
}
