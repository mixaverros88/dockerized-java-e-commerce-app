package manage;

import entities.Produnit;
import java.io.Serializable;
import java.util.List;
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
public class ProductUnitManage implements Serializable {

    final static Logger logger = Logger.getLogger(ProductUnitManage.class);

    @NotNull(message = "Παρακαλώ συμπληρώστε το όνομα της Μονάδας Μέτρησης")
    private String name;
    private String isactive;
    private List<Produnit> produnit;

    @EJB
    ProductUnitFacade productUnitFacade;

    @PostConstruct
    public void init() {
        if (logger.isDebugEnabled()) {  logger.debug("Init Product Unit Edit Manage"); }
    }

    public String changeStatusProductUnit(int status, int id) {
        productUnitFacade.changeStatusProductUnitFromDB(status, id);
        return "";
    }

    private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

    public String editProductUnit() {

        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        int produnitId = Integer.parseInt(params.get("productUnitID"));
        Produnit u = productUnitFacade.searchWithID(produnitId);

        sessionMap.put("editProductUnit", u);

        return "/web/produnitEdit.xhtml?faces-redirect=true";
    }

    public String deleteProductUnit(int id) {
        productUnitFacade.deleteProductUnitFromDB(id);
        return "";
    }

    public String insertProductUnit() {

        Produnit productUnitInsert = new Produnit();
        productUnitInsert.setName(name);


        int isactiveInt;
        if (isactive.equals("true")) {
            isactiveInt = 1;
        } else {
            isactiveInt = 0;
        }
        productUnitInsert.setIsactive(isactiveInt);

        //mhnhmata από το magaebean στην σελίδα
        if (productUnitFacade.insertProductUnitToDB(productUnitInsert)) {

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


    public List<Produnit> getAllProdunit() {
        return produnit = productUnitFacade.getAllProdunitFromDB();
    }

    public List<Produnit> getProdunit() {
        return produnit;
    }

    public void setProdunit(List<Produnit> produnit) {
        this.produnit = produnit;
    }


}
