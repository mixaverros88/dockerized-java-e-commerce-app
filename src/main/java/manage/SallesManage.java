package manage;

import entities.Ballance;
import entities.Custvend;
import entities.Orderlines;
import entities.Orders;
import entities.Product;
import entities.Role;
import entities.Roles;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import org.apache.log4j.Logger;
import sessionsBeans.BallanceFacade;
import sessionsBeans.CustvendFacade;
import sessionsBeans.OrderlinesFacade;
import sessionsBeans.OrdersFacade;
import sessionsBeans.ProductFacade;
import sessionsBeans.SallesFacade;
import sessionsBeans.WhishListFacade;

@ManagedBean
@RequestScoped
public class SallesManage implements Serializable {

    final static Logger logger = Logger.getLogger(SallesManage.class);

    @NotNull(message = "Συμπληρώστε την ποσότητα")
    private float qty;
    private Role role;
    private Product product;
    private float vat;

    private List<Product> Products;
    private List<Orders> salles;

    HttpSession session = SessionUtils.getSession();
    Custvend custvend = (Custvend) session.getAttribute("Custvend");

    @EJB
    OrdersFacade ordersFacade;

    @EJB
    SallesFacade sallesFacade;

    @EJB
    BallanceFacade ballanceFacade;

    @EJB
    CustvendFacade custvendFacade;


    @EJB
    WhishListFacade whishListFacade;


    public String getContextParameter() {
        return FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getInitParameter("vat");
    }

    public String insertOrder() {

        FacesContext ctx = FacesContext.getCurrentInstance();
        Float vat1 = Float.parseFloat(ctx.getExternalContext().getInitParameter("vat"));

        List<Orderlines> orderlinesL = new ArrayList<>();

        Ballance ballance = new Ballance();
        ballance.setCustvendid(custvend);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43

        float linenetval = Math.round(qty * product.getBuyprice());
        float linevatval = Math.round(vat1 * linenetval * 100.00) / 100.00f;
        float linesumval = Math.round((linenetval + linevatval) * 100.00) / 100.00f;

        ballance.setTransactiondate(date);
        ballance.setAmount(linesumval);
        ballanceFacade.insertBalanceToDB(ballance);

        Orders orders = new Orders();

        orders.setRoleid(custvend.getRoleid());
        orders.setOrderdate(date);
        orders.setCustvendid(custvend);
        orders.setVatid(1);
        orders.setSumamnt(linesumval);
        orders.setSysuser(custvend.getCustvendid());
        orders.setInsdate(date);
        Orderlines orderlines = new Orderlines();
        orderlines.setQty(qty);
        orderlines.setVendor(custvend);
        orderlines.setProductid(product);
        orderlines.setProdunitid(product.getProdunitid());
        orderlines.setPrice(product.getBuyprice());
        orderlines.setRoleid(custvend.getRoleid());
        orderlines.setVendor(custvend);
        orderlines.setOrderid(orders);
        orderlines.setLinenetval(linenetval);
        orderlines.setLinevatval(linevatval);
        orderlines.setLinesumval(linesumval);


        orderlinesL.add(orderlines);

        orders.setOrderlinesCollection(orderlinesL);

        System.out.print("User Id" + custvend.getCustvendid() + "custvend");

        custvendFacade.changeBallanceCustvendFromDB(-linesumval, custvend.getCustvendid());
        whishListFacade.chechIfTheProductIsInWhishList(product, qty);

        if (ordersFacade.insertProductToOrdersToDB(orders)) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Η πώληση δημιουργήθηκε επιτυχώς"));
            return "sallesAll";
        }

        return "";
    }

    public String changeStatusOrder(int status, int id) {
        Orders order = ordersFacade.getOrderByIDFromDB(id);
        if (ordersFacade.changeStatusOrderFromDatabase(status, id, order) == 1) {
            if (status == 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("H παραγγελεία είναι ανενεργή"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("H παραγγελεία είναι ενεργή"));
            }
        }
        return "";
    }

    @PostConstruct
    void init() {
       if (logger.isDebugEnabled()) {  logger.debug("Init Sales Manage"); }
        Products = sallesFacade.findProductsCombineByVendor(custvend);
    }

    //vendorTransaction if is 1 the transaction is sale from vendor to as
    //vendorTransaction if is 2 the transaction is sale from as to customer
    public List<Orders> getAllSalles(Roles role, Custvend custvend, int vendorTransaction, String view) {

        return ordersFacade.getAllSallesFromDB(role, custvend, vendorTransaction, view);
    }

    public List<Orders> getSalles() {
        return salles;
    }

    public void setSalles(List<Orders> salles) {
        this.salles = salles;
    }

    public float getQty() {
        return qty;
    }

    public void setQty(float qty) {
        this.qty = qty;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OrdersFacade getOrdersFacade() {
        return ordersFacade;
    }

    public void setOrdersFacade(OrdersFacade ordersFacade) {
        this.ordersFacade = ordersFacade;
    }

    public List<Product> getProducts() {
        return Products;
    }

    public void setProducts(List<Product> Products) {
        this.Products = Products;
    }

    public float getVat() {
        return vat;
    }

    public void setVat(float vat) {
        this.vat = vat;
    }

}
