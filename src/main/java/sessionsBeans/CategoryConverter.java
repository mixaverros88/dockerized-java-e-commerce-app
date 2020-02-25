package sessionsBeans;

import entities.Prodcategory;
import org.apache.log4j.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean(name = "categoryConverter")
public class CategoryConverter implements Converter {

    final static Logger logger = Logger.getLogger(CategoryConverter.class);

    @EJB
    private CategoryFacade categoryFacade;

    public CategoryConverter() { }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println(value);
        return categoryFacade.returnOneCategory(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String result = (value instanceof Prodcategory) ? ((Prodcategory) value).getProdcategoryid().toString() : null;
        System.out.println(result);
        return result;
    }

}
