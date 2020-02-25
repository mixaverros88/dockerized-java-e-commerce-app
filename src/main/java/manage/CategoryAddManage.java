package manage;

import entities.Prodcategory;
import helpers.ConvertToGreeklish;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import org.apache.log4j.Logger;
import sessionsBeans.CategoryFacade;

@ManagedBean
@RequestScoped
public class CategoryAddManage implements Serializable {

    final static Logger logger = Logger.getLogger(CategoryAddManage.class);

    @NotNull(message = "Συμπληρώστε το όνομα της κατηγορίας")
    private String name;
    private String isactive;

    private Prodcategory prodcategory;

    @EJB
    CategoryFacade categoryFacade;

    @PostConstruct
    void init() {
        if(logger.isDebugEnabled()){ logger.debug("Init Add category"); }
    }

    public String insertCategory() {
        Prodcategory category = new Prodcategory();
        category.setName(name.trim());

        //Convert checkbox value to int. True = 1 , False = 0
        int isactiveInt;
        if (isactive.equals("true")) {
            isactiveInt = 1;
        } else {
            isactiveInt = 0;
        }
        category.setIsactive((short) isactiveInt);

        //String slug = name.replaceAll(" ", "-");


        category.setSlugname(ConvertToGreeklish.greek2Roman(name));
        System.out.println("Category: " + category);

        //mhnhmata από το magaebean στην σελίδα
        if (categoryFacade.insertCategoryToDB(category)) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Η κατηγορία δημιουργήθηκε επιτυχώς"));
            return "categoryAll";
        }

        return "";
    }


    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Prodcategory getProdcategory() {
        return prodcategory;
    }

    public void setProdcategory(Prodcategory prodcategory) {
        this.prodcategory = prodcategory;
    }
}
