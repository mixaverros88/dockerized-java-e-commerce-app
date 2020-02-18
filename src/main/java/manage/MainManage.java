/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import entities.Custvend;
import entities.Orderlines;
import helpers.Chart;
import entities.Orders;
import entities.Product;
import entities.User;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import sessionsBeans.CategoryFacade;
import sessionsBeans.CustvendFacade;
import sessionsBeans.OrderlinesFacade;
import sessionsBeans.OrdersFacade;
import sessionsBeans.ProductFacade;
import sessionsBeans.SallesFacade;
import sessionsBeans.UserAllFacade;

/**
 *
 * @author user
 */
@ManagedBean
@RequestScoped
public class MainManage implements Serializable{
    
    private int countCategories;
    private List<Chart> chart;

    @EJB
    CategoryFacade categoryFacade;
    
    @EJB
    UserAllFacade userAllFacade;
    
    @EJB
    ProductFacade productFacade;
    
    @EJB
    OrdersFacade ordersFacade;
    
    @EJB
    SallesFacade sallesFacade;
    
    @EJB
    CustvendFacade custvendFacade;
    
    @EJB
    OrderlinesFacade orderlinesFacade;
    
    @PostConstruct
        void init(){   
            //chart = ordersFacade.listForChartFromDB();
        }
        
 
    public int countAllCategories(){
        return categoryFacade.coutnCategories();
    }
    
    public float countBallance(int id){
        return custvendFacade.countBallanceOFAdminFromDB(id);
    }
    
    public long countAllProducts(int role, int custvendId){
        return productFacade.countProductsFromDB(role, custvendId);
    }
    
    public double countAllPSUMAMNT(){
         return ordersFacade.countSumOrdersFromDB();
    }
    
    public double countAllPSUMAMNTByVendor(Custvend custvend){
         return orderlinesFacade.orderDetailsSallesByVendorFROMDB(custvend);
    }
    
    public List<Orderlines> getAllProductsOrderByMaxSells(){
         return orderlinesFacade.getAllProductsOrderByMaxSellsFromDB();
    }
    public double countAllSallesSUMAMNT(){
        return sallesFacade.countSumSallesFromDB();
    }
    
    public double countAllSallesSUMAMNTbyVendor(Custvend custvend){
        return sallesFacade.countSumSallesbyVendorFromDB(custvend);
    }
    
    public List<Chart> chartOrders(){
        List<Chart> ch = ordersFacade.listForChartFromDB();
        return ch;
    }
    
    public void makeApDF(){
        
    }

    public List<Chart> getChart() {
        return chart;
    }

    public void setChart(List<Chart> chart) {
        this.chart = chart;
    }


    
    public int countAllUsers(){

        User u;
        List<User> users = userAllFacade.getAllUsers();
        int count = 0;
        
        for(int x = 0; x < users.size(); x++) {
           u = users.get(x);
           u.getName();
           count ++;
        }
        
        return count;
    }
    
    public int getCountCategories() {
        return countCategories;
    }

    public void setCountCategories(int countCategories) {
        this.countCategories = countCategories;
    }
}
