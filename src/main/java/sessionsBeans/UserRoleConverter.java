/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionsBeans;

import entities.Role;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author user
 */
@ManagedBean(name="userRoleConverter")
public class UserRoleConverter implements Converter{
    
    @EJB
    private UserAddFacade roleFacade;
    
    public UserRoleConverter(){
        
    }

    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println(value);
        return roleFacade.returnOneRole(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String result =(value instanceof Role) ? ((Role) value).getId().toString():null;
        System.out.println(result);
        return result;
    }
    
    
    
}
