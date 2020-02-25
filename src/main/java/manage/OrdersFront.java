package manage;

import com.itextpdf.text.DocumentException;
import entities.Ballance;
import entities.Custvend;
import entities.Orderlines;
import entities.Orders;
import entities.Product;
import helpers.CreatePdf;
import helpers.DateTime;
import helpers.MailSender;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import sessionsBeans.BallanceFacade;
import sessionsBeans.CustvendFacade;
import sessionsBeans.OrderlinesFacade;
import sessionsBeans.OrdersFacade;
import sessionsBeans.ProductFacade;

@ManagedBean
@RequestScoped
public class OrdersFront implements Serializable {

    final static Logger logger = Logger.getLogger(OrdersFront.class);

    Orderlines orderlines = new Orderlines();
    private float credits;
    float newCredits;

    @EJB
    OrdersFacade ordersFacade;

    @EJB
    OrderlinesFacade orderlinesFacade;

    @EJB
    CustvendFacade custvendFacade;

    @EJB
    BallanceFacade ballanceFacade;

    @EJB
    ProductFacade productFacade;


    @ManagedProperty(value = "#{cartManage}")
    CartManage myCart;

    @PostConstruct
    void init() {
        if(logger.isDebugEnabled()){ logger.debug("Init Oders Front Manage"); }
    }

    public String addOrder(float totalPrice, int shipping) throws DocumentException {

        HttpSession session = SessionUtils.getSession();
        Custvend custvend = (Custvend) session.getAttribute("Custvend");
        List<Product> products = myCart.getProducts();

        if (totalPrice > 100) {
            newCredits = 5.00f;
        } else {
            newCredits = 0.0f;
        }

        credits = Float.parseFloat(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("sendCredits"));
        totalPrice -= credits;

        Orders orders = new Orders();
        orders.setRoleid(custvend.getRoleid());
        orders.setOrderdate(DateTime.getNowDateTime());
        orders.setCustvendid(custvend);
        orders.setVatid(1);
        orders.setSumamnt(totalPrice);
        orders.setInsdate(DateTime.getNowDateTime());
        orders.setSysuser(custvend.getCustvendid());
        List<Orderlines> orderlinesL = new ArrayList<>();

        StringBuilder emailProductTable = new StringBuilder();

        for (Product product1 : products) {

            Orderlines orderlines = new Orderlines();

            FacesContext ctx = FacesContext.getCurrentInstance();
            Float vat = Float.parseFloat(ctx.getExternalContext().getInitParameter("vat"));


            float qty = product1.getQty();
            float productSellPrice = product1.getSellprice();
            int productID = product1.getProductid();

            float linenetval = Math.round(qty * product1.getSellprice() * 100.00) / 100.00f;
            float linevatval = Math.round(vat * linenetval * 100.00) / 100.00f;
            float linesumval = Math.round((linenetval + linevatval) * 100.00) / 100.00f;
            //Ο vendor παίρνει Buyprice + 20% επί τουν κέρδους

            float vendorProfit = (float) Math.round(((product1.getBuyprice() * (vat + 1) * qty) + (((linesumval - (product1.getBuyprice() * (vat + 1) * qty)) * 20) / 100)) * 100.00) / 100.00f;
            float eZikosProfit = (float) Math.round((linesumval - vendorProfit) * 100.00) / 100.00f;
            Ballance ballance = new Ballance();

            ballance.setCustvendid(custvend);
            ballance.setTransactiondate(DateTime.getNowDateTime());
            ballance.setAmount(linesumval);
            ballance.setInsdate(DateTime.getNowDateTime());
            ballance.setSysuser(custvend.getSysuser());

            ballanceFacade.insertBalanceToDB(ballance);

            emailProductTable.append("<tr style=\"background-color: #e6e6e6;\"><td style=\"padding: 10px;\">")
                    .append(product1.getName())
                    .append("</td><td style=\"padding: 10px;\">")
                    .append(qty + " / " + product1.getProdunitid().getName())
                    .append("</td><td style=\"padding: 10px;\">")
                    .append(linesumval)
                    .append(" €</td></tr>");

            System.out.println(emailProductTable);


            orderlines.setOrderid(orders);
            orderlines.setRoleid(custvend.getRoleid());
            orderlines.setProductid(product1);
            orderlines.setVendor(product1.getVendor());
            orderlines.setProdunitid(product1.getProdunitid());
            orderlines.setQty(qty);
            orderlines.setPrice(product1.getSellprice());
            orderlines.setLinenetval(linenetval);
            orderlines.setLinevatval(linevatval);
            orderlines.setLinesumval(linesumval);

            orderlinesL.add(orderlines);
            System.out.println("eND");
            productFacade.updateQntProduct(qty, product1.getProductid());

            custvendFacade.changeBallanceCustvendFromDB(+vendorProfit, product1.getVendor().getCustvendid());
            custvendFacade.changeBallanceCustvendFromDB(+eZikosProfit, 39);

        }

        if (credits > 0) {
            newCredits -= credits;
        }
        custvendFacade.changeCreditsCustvendFromDB(newCredits, custvend.getCustvendid());

        orders.setOrderlinesCollection(orderlinesL);

        //Create A pdf
        CreatePdf cpdf = new CreatePdf();
        String invoiceURL = cpdf.createPDF(products, custvend, orders.getOrderid(), shipping);
        ordersFacade.insertPdfToOrderToDb(orders.getOrderid());


        if (ordersFacade.insertProductToOrdersToDB(orders)) {
            myCart.clearCart();

            MailSender.send(custvend.getEmail(), "ezikos.gr - Λεπτομέριες Παραγγελίας", "<p>Αγαπητέ " + custvend.getFname() + " " + custvend.getLname() + ",</p><p>Θα θέλαμε να σας ενημερώσουμε ότι η παραγγελία σας καταχωρήθηκε.</p><p>Θα σας ενημερώσουμε άμεσα με email για την εγκριση της παράγγελέια σας.</p><p>Στοιχεία παραγγελιας.</p><table style=\"border: 1px solid #dcdcdc;\"><tr style=\"background-color: #e6e6e6;\"><td style=\"padding: 10px;\">Όνομα Προϊόντος</td><td style=\"padding: 10px;\">Ποσότητα Προϊόντος</td><td style=\"padding: 10px;\">Τιμή Προϊόντος</td></tr>" + emailProductTable + ""
                    + "<tr style=\"background-color: #e6e6e6;\"><td></td><td style=\"padding: 10px;\" >Μεταφορικά</td><td style=\"padding: 10px;\">" + shipping + " €</td></tr>"
                    + "<tr style=\"background-color: #e6e6e6;\"><td></td><td></td><td style=\"padding: 10px;\">Σύνολο: " + Math.round(totalPrice * 100.00) / 100.00 + " €</td></tr></table><br/><p>Μπορείτε να κατεβάσετε την απόδειξή της παραγγελίας πατώντας <a href=\"/java-e-commerce/web/resources/invoices/" + invoiceURL + "\">εδώ</a></p><p>Ευχαριστούμε για την επιλογή σας.</p>");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Η αγορά σας πραγματοποιήθηκε επιτυχώς. Θα σας σταλεί email με την αγορά σας"));
        }


        return "";
    }

    public CartManage getMyCart() {
        return myCart;
    }

    public void setMyCart(CartManage myCart) {
        this.myCart = myCart;
    }

    public float getCredits() {
        return credits;
    }

    public void setCredits(float credits) {
        this.credits = credits;
    }

}
