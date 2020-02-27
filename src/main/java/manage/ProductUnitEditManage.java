package manage;

import entities.Produnit;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;

import org.apache.log4j.Logger;
import sessionsBeans.ProductUnitFacade;

@ManagedBean
@RequestScoped
public class ProductUnitEditManage implements Serializable {

    final static Logger logger = Logger.getLogger(ProductUnitEditManage.class);

    @NotNull(message = "Name can't be null")
    private String name;
    private String isactive;
    private int produnitid;

    private Produnit produnit;

    @EJB
    ProductUnitFacade productUnitFacade;

    private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

    @PostConstruct
    public void init() {
        if(logger.isDebugEnabled()){ logger.debug("Init Product Unit Edit Manage"); }
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        produnit = (Produnit) sessionMap.get("editProductUnit");
    }

    public String updateProductUnit() {

        Produnit produnitUpdate = new Produnit();
        produnitUpdate.setName(produnit.getName());
        produnitUpdate.setIsactive(produnit.getIsactive());
        produnitUpdate.setProdunitid(produnit.getProdunitid());

        //mhnhmata από το magaebean στην σελίδα
        if (productUnitFacade.updateProductUnitToDB(produnitUpdate)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("DATA OK"));
            return "produnitAll";
        }
        return "";
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    public int getProdunitid() {
        return produnitid;
    }

    public void setProdunitid(int produnitid) {
        this.produnitid = produnitid;
    }

    public Produnit getProdunit() {
        return produnit;
    }

    public void setProdunit(Produnit produnit) {
        this.produnit = produnit;
    }


}
