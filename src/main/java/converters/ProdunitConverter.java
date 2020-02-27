package converters;

import entities.Produnit;
import org.apache.log4j.Logger;
import sessionsBeans.ProductFacade;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean(name = "produnitConverter")
public class ProdunitConverter implements Converter {

    final static Logger logger = Logger.getLogger(ProdunitConverter.class);

    @EJB
    private ProductFacade productFacade;

    public ProdunitConverter() {}

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return productFacade.returnOneUnit(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String result = (value instanceof Produnit) ? ((Produnit) value).getProdunitid().toString() : null;
        return result;
    }
}
