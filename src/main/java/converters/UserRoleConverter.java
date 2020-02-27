package converters;

import entities.Role;
import org.apache.log4j.Logger;
import sessionsBeans.UserAddFacade;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean(name = "userRoleConverter")
public class UserRoleConverter implements Converter {

    final static Logger logger = Logger.getLogger(UserRoleConverter.class);

    @EJB
    private UserAddFacade roleFacade;

    public UserRoleConverter() { }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return roleFacade.returnOneRole(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String result = (value instanceof Role) ? ((Role) value).getId().toString() : null;
        return result;
    }
}
