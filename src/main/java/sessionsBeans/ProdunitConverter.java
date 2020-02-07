/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionsBeans;

import entities.Produnit;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author user
 */
@ManagedBean(name="produnitConverter")
public class ProdunitConverter  implements Converter{
    
    @EJB
    private ProductFacade productFacade;
    
    public ProdunitConverter(){
        
    }

    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println(value);
        return productFacade.returnOneUnit(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String result =(value instanceof Produnit) ? ((Produnit) value).getProdunitid().toString():null;
        System.out.println(result);
        return result;
    }
    
}
