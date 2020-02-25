package manage;

import entities.Vat;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import org.apache.log4j.Logger;
import sessionsBeans.VatFacade;

@ManagedBean
@RequestScoped
public class VatEditManage {

    final static Logger logger = Logger.getLogger(VatEditManage.class);

    @NotNull(message = "Παρακαλώ Συμπληρώστε το ποσοστό του ΦΠΑ")
    private float percnt;
    private List<Vat> vat;
    private String isactive;
    private Vat vatEdit;

    @EJB
    VatFacade vatFacade;

    private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

    @PostConstruct
    public void init() {
        if (logger.isDebugEnabled()) {  logger.debug("Init Edit Vat Manager"); }
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        vatEdit = (Vat) sessionMap.get("editVat");
    }


    public String updateVat() {
        Vat vatUpdate = new Vat();
        vatUpdate.setPercnt(vatEdit.getPercnt());
        vatUpdate.setIsactive(vatEdit.getIsactive());
        vatUpdate.setVatid(vatEdit.getVatid());
        //mhnhmata από το magaebean στην σελίδα
        if (vatFacade.updateVatToDatabase(vatUpdate)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("DATA OK"));
            return "vatAll";
        }
        return "";
    }

    public float getPercnt() {
        return percnt;
    }

    public void setPercnt(float percnt) {
        this.percnt = percnt;
    }

    public List<Vat> getVat() {
        return vat;
    }

    public void setVat(List<Vat> vat) {
        this.vat = vat;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    public Vat getVatEdit() {
        return vatEdit;
    }

    public void setVatEdit(Vat vatEdit) {
        this.vatEdit = vatEdit;
    }

    public VatFacade getVatFacade() {
        return vatFacade;
    }

    public void setVatFacade(VatFacade vatFacade) {
        this.vatFacade = vatFacade;
    }

    public Map<String, Object> getSessionMap() {
        return sessionMap;
    }

    public void setSessionMap(Map<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }

}
