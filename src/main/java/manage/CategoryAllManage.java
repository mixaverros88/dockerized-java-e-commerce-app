package manage;

import entities.Prodcategory;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
import sessionsBeans.CategoryFacade;

@ManagedBean
@RequestScoped
public class CategoryAllManage implements Serializable {

    final static Logger logger = Logger.getLogger(CategoryAllManage.class);

    private List<Prodcategory> prodcategory;

    @EJB
    CategoryFacade categoryFacade;

    @PostConstruct
    void init() {
        if(logger.isDebugEnabled()){ logger.debug("Init Category All Manage"); }
    }

    public List<Prodcategory> getAllUserData() {
        return prodcategory = categoryFacade.getAllCategories();
    }

    private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

    public String editCategory() {

        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        int categoryId = Integer.parseInt(params.get("categoryID"));
        Prodcategory u = categoryFacade.searchWithID(categoryId);

        sessionMap.put("editUser", u);

        return "/web/categoryEdit.xhtml?faces-redirect=true";
    }

    public String deleteCategory(int id) {
        if (categoryFacade.deleteCategory(id) == 1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Η κατηγορία διαγράφτηκε επιτυχώς"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Η κατηγορία δεν διαγράφτηκε επιτυχώς"));
        }
        return "";
    }

    public String changeStatusCategory(int status, int id) {
        if (categoryFacade.changeStatus(status, id) == 1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Η κατηγορία άλλαξε κατάσταση επιτυχώς"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Η κατηγορία δεν άλλαξε κατάσταση επιτυχώς"));
        }
        return "";

    }

    public List<Prodcategory> getProdcategory() {
        return prodcategory;
    }

    public void setProdcategory(List<Prodcategory> prodcategory) {
        this.prodcategory = prodcategory;
    }
}
