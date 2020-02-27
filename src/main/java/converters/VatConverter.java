/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import entities.Vat;
import sessionsBeans.VatFacade;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * @author user
 */
@ManagedBean(name = "vatConverter")
public class VatConverter implements Converter {


    @EJB
    private VatFacade vatFacade;

    public VatConverter() {}

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return vatFacade.returnOneVat(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String result = (value instanceof Vat) ? ((Vat) value).getVatid().toString() : null;
        return result;
    }

}
