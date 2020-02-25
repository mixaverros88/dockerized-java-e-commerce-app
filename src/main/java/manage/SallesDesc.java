package manage;

import entities.Custvend;
import entities.Orderlines;
import entities.Orders;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.apache.log4j.Logger;
import sessionsBeans.OrderlinesFacade;
import sessionsBeans.OrdersFacade;

@ManagedBean
@RequestScoped
public class SallesDesc implements Serializable {

    final static Logger logger = Logger.getLogger(SallesDesc.class);

    @EJB
    OrderlinesFacade orderlinesFacade;

    @EJB
    OrdersFacade ordersFacade;

    @PostConstruct
    public void init() {
        if (logger.isDebugEnabled()) {  logger.debug("Init Sales Description"); }
    }

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
