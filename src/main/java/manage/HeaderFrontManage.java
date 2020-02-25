package manage;

import entities.Prodcategory;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.log4j.Logger;
import sessionsBeans.CategoryFacade;

/**
 * @author user
 */
@ManagedBean
@RequestScoped
public class HeaderFrontManage implements Serializable {

    final static Logger logger = Logger.getLogger(HeaderFrontManage.class);
    private List<Prodcategory> prodcategory;

    @PostConstruct
    void init() {
        if(logger.isDebugEnabled()){ logger.debug("Init Header Front Mangage"); }
    }

    @EJB
    CategoryFacade categoryFacade;

    public List<Prodcategory> getAllUserData() {
        return prodcategory = categoryFacade.getAllCategoriesWhereIsActive();
    }

    public List<Prodcategory> getProdcategory() {
        return prodcategory;
    }

    public void setProdcategory(List<Prodcategory> prodcategory) {
        this.prodcategory = prodcategory;
    }


}
