package converters;

import entities.Roles;
import org.apache.log4j.Logger;
import sessionsBeans.UserAddFacade;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean(name = "rolesConverter")
public class RolesConverter implements Converter {

    final static Logger logger = Logger.getLogger(RolesConverter.class);

    @EJB
    private UserAddFacade roleFacade;

    public RolesConverter() { }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return roleFacade.returnOneRoles(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String result = (value instanceof Roles) ? ((Roles) value).getRoleid().toString() : null;
        return result;
    }
}
