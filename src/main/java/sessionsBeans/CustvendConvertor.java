package sessionsBeans;

import entities.Custvend;
import org.apache.log4j.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean(name = "custvendConvertor")
public class CustvendConvertor implements Converter {

    final static Logger logger = Logger.getLogger(CustvendConvertor.class);

    @EJB
    private CustvendFacade custvendFacade;

    public CustvendConvertor() { }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println(value);
        return custvendFacade.returnOneCustvend(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String result = (value instanceof Custvend) ? ((Custvend) value).getCustvendid().toString() : null;
        System.out.println(result);
        return result;
    }

}
