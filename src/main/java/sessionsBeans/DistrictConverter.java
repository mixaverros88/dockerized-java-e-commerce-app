/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionsBeans;

import entities.District;
import entities.Prodcategory;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;




/**
 *
 * @author user
 */
@ManagedBean(name="districtConverter")
public class DistrictConverter implements Converter{
    
    @EJB
    private DistrictFacade districtFacade;
    
    public DistrictConverter(){
        
    }
  
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println(value);
        return districtFacade.returnOneDistrict(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String result =(value instanceof District) ? ((District) value).getDistrict().toString():null;
        System.out.println(result);
        return result;
    }
    
    
}
