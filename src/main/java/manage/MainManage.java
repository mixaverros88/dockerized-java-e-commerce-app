package manage;

import entities.Custvend;
import entities.Orderlines;
import helpers.Chart;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.apache.log4j.Logger;
import sessionsBeans.CategoryFacade;
import sessionsBeans.CustvendFacade;
import sessionsBeans.OrderlinesFacade;
import sessionsBeans.OrdersFacade;
import sessionsBeans.ProductFacade;
import sessionsBeans.SallesFacade;
import sessionsBeans.UserAllFacade;

@ManagedBean
@RequestScoped
public class MainManage implements Serializable {

    final static Logger logger = Logger.getLogger(MainManage.class);

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
    void init() {
        if(logger.isDebugEnabled()){ logger.debug("Init Main Manage"); }
    }

    public int countAllCategories() {
        return categoryFacade.coutnCategories();
    }

    public float countBallance(int id) {
        return custvendFacade.countBallanceOFAdminFromDB(id);
    }

    public long countAllProducts(int role, int custvendId) {
        return productFacade.countProductsFromDB(role, custvendId);
    }

    public double countAllPSUMAMNT() {
        return ordersFacade.countSumOrdersFromDB();
    }

    public double countAllPSUMAMNTByVendor(Custvend custvend) {
        return orderlinesFacade.orderDetailsSallesByVendorFROMDB(custvend);
    }

    public List<Orderlines> getAllProductsOrderByMaxSells() {
        return orderlinesFacade.getAllProductsOrderByMaxSellsFromDB();
    }

    public double countAllSallesSUMAMNT() {
        return sallesFacade.countSumSallesFromDB();
    }

    public double countAllSallesSUMAMNTbyVendor(Custvend custvend) {
        return sallesFacade.countSumSallesbyVendorFromDB(custvend);
    }

    public List<Chart> chartOrders() {
        return ordersFacade.listForChartFromDB();
    }

    public List<Chart> getChart() {
        return chart;
    }

    public void setChart(List<Chart> chart) {
        this.chart = chart;
    }


    public int countAllUsers() {

        Custvend u;
        List<Custvend> users = userAllFacade.getAllUsers();
        int count = 0;

        for (Custvend user : users) {
            u = user;
            u.getFname();
            count++;
        }

        return count;
    }
}
