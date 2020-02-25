package sessionsBeans;

import entities.Product;
import org.apache.log4j.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean(name = "productConverter")
public class ProductConverter implements Converter {

    final static Logger logger = Logger.getLogger(ProductConverter.class);

    @EJB
    ProductFacade productFacade;

    public ProductConverter() { }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println(value);
        return productFacade.returnOneProduct(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String result = (value instanceof Product) ? ((Product) value).getProductid().toString() : null;
        System.out.println(result);
        return result;
    }

}
