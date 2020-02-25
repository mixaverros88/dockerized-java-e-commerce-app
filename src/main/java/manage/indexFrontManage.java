package manage;

import entities.Photos;
import entities.Product;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.apache.log4j.Logger;
import sessionsBeans.ProductFacade;

@ManagedBean
@RequestScoped
public class indexFrontManage implements Serializable {

    final static Logger logger = Logger.getLogger(indexFrontManage.class);
    private List<Product> products;
    private Photos photos;

    @PostConstruct
    void init() {
        if(logger.isDebugEnabled()){ logger.debug("Init Intex Front Mangage"); }
    }

    @EJB
    ProductFacade productFacade;

    public List<Product> getAllUserData() {
        return products = productFacade.getAllProductsFromDatabase();
    }

    public List<Product> getAllProductsByIsactive(int isactive) {
        return products = productFacade.getAllProductsByIsactiveFromDB(isactive);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

}
