package manage;

import entities.Prodcategory;
import entities.Product;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
import sessionsBeans.CategoryFacade;
import sessionsBeans.ProductFacade;

@ManagedBean
@RequestScoped
public class KathgoriaFrontManage implements Serializable {

    final static Logger logger = Logger.getLogger(KathgoriaFrontManage.class);

    private List<Product> products;

    @EJB
    ProductFacade productFacade;

    @EJB
    CategoryFacade categoryFacade;

    @PostConstruct
    void init() {
        if(logger.isDebugEnabled()){ logger.debug("Init Category front manage"); }
    }

    public List<Product> getAllProductsByCategory(int categoryID) {
        Prodcategory prodcategory = categoryFacade.searchWithID(categoryID);
        return products = productFacade.getAllProductsFromDBByCategory(prodcategory);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public boolean checkIfCategoryExists(String id) {
        if (categoryFacade.returnOneCategory(id) == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Η κατηγορία που ζητήσατε δεν βρέθηκε."));
            return false;
        }
        return true;
    }

}
