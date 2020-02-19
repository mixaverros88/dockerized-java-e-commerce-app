/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;


import entities.Prodcategory;
import entities.Vat;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;

import sessionsBeans.VatFacade;

/**
 * @author user
 */
@ManagedBean
@RequestScoped
public class VatManage implements Serializable {

    @NotNull(message = "Παρακαλώ Συμπληρώστε το ποσοστό του ΦΠΑ")
    private float percnt;
    private List<Vat> vat;
    private String isactive;

    @EJB
    VatFacade vatFacade;


    private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

    public String editVat() {

        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        int categoryId = Integer.parseInt(params.get("vatID"));
        Vat vat = vatFacade.searchWithID(categoryId);

        sessionMap.put("editVat", vat);

        return "/web/vatEdit.xhtml?faces-redirect=true";
    }

    public List<Vat> getAllVat() {
        return vat = vatFacade.getAllVatFromDB();
    }

    public String changeStatusVat(int status, int id) {
        vatFacade.changeStatusVatFromDB(status, id);
        return "";
    }

    public String deleteVat(int id) {
        vatFacade.deleteVatFromDB(id);
        return "";
    }

    public String insertVat() {

        Vat vat = new Vat();
        vat.setPercnt(percnt);

        //Convert checkbox value to int. True = 1 , False = 0
        int isactiveInt;
        if (isactive.equals("true")) {
            isactiveInt = 1;
        } else {
            isactiveInt = 0;
        }
        vat.setIsactive((short) isactiveInt);

        //mhnhmata από το magaebean στην σελίδα
        if (vatFacade.insertVatToDB(vat)) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("DATA OK"));
            return "vatAll";
        }

        return "";
    }


    public List<Vat> getVat() {
        return vat;
    }

    public void setVat(List<Vat> vat) {
        this.vat = vat;
    }

    public float getPercnt() {
        return percnt;
    }

    public void setPercnt(float percnt) {
        this.percnt = percnt;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

}
