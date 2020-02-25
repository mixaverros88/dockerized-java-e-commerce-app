package manage;

import entities.Custvend;
import entities.Roles;
import helpers.HashinUtils;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.log4j.Logger;
import sessionsBeans.CustvendFacade;

@ManagedBean
@RequestScoped
public class CustvendEditManage implements Serializable {

    final static Logger logger = Logger.getLogger(CustvendEditManage.class);

    @NotNull(message = "Συμπληρώστε το όνομα")
    private String fname;
    @NotNull(message = "Συμπληρώστε το επίθετο")
    private String lname;
    @NotNull(message = "Συμπληρώστε το ΑΦΜ")
    @Size(min = 10, max = 10, message = "Το ΑΦΜ θα πρέπει να έχει 10 αριθμούς")
    private String afm;
    @Size(min = 25, message = "Το username θα πρέπει να έχει πάνω από2 στοιχεία")
    @NotNull(message = "Συμπληρώστε το username")
    private String username;
    @Size(min = 5, message = "Το password θα πρέπει να έχει πάνω από 5 στοιχεία")
    @NotNull(message = "Συμπληρώστε το password")
    private String password;
    @NotNull(message = "Συμπληρώστε το password")
    private String passwordCheck;
    @NotNull(message = "Συμπληρώστε το ΤΚ")
    @Size(min = 5, max = 5, message = "Το ΤΚ θα πρέπει να έχει 5 αριθμούς")
    private String zip;
    @NotNull(message = "Συμπληρώστε την Πόλη")
    private String city;
    @NotNull(message = "Συμπληρώστε την περιοχή")
    private String district;
    @NotNull(message = "Συμπληρώστε το email")
    private String email;
    private String jobname;
    private String remarks = "1";
    private String isactive;
    @NotNull(message = "Συμπληρώστε την Διεύθυνση")
    private String address;
    @NotNull(message = "Συμπληρώστε τον Αριθμό Τηλεφώνου")
    @Size(min = 10, max = 10, message = "O αριθμός τηλεφώνου θα πρέπει να είναι 10 ψηφία")
    private String phone;
    private Roles rr;
    private List<Roles> roles;
    private List<Custvend> custvend;
    private Custvend custvendA;

    @EJB
    CustvendFacade custvendFacade;

    private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

    @PostConstruct
    public void init() {
        if(logger.isDebugEnabled()){ logger.debug("Init Edit User"); }
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        custvendA = (Custvend) sessionMap.get("editCustvend");
        System.out.println("22222" + custvendA.getFname());
    }


    public String updateCustvend() {

        Custvend custvendpdate = new Custvend();
        HashinUtils hu = new HashinUtils();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        custvendpdate.setCustvendid(custvendA.getCustvendid());
        custvendpdate.setFname(custvendA.getFname().trim());
        custvendpdate.setLname(custvendA.getLname().trim());
        custvendpdate.setAfm(custvendA.getAfm().trim());
        custvendpdate.setUsername(custvendA.getUsername().trim());
        custvendpdate.setRoleid(custvendA.getRoleid());
        custvendpdate.setAddress(custvendA.getAddress().trim());
        custvendpdate.setZip(custvendA.getZip().trim());
        custvendpdate.setCity(custvendA.getCity().trim());
        custvendpdate.setDistrict(custvendA.getDistrict().trim());
        custvendpdate.setPhone(custvendA.getPhone().trim());
        custvendpdate.setEmail(custvendA.getEmail().trim());
        custvendpdate.setJobname(custvendA.getJobname().trim());
        custvendpdate.setPasswd(custvendA.getPasswd());
        custvendpdate.setBallance(custvendA.getBallance());

        if (custvendA.getRoleid().getRoleid() == 3 && custvendA.getAfm() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Εφόσον είσται προμηθευτής θα πρέπει να εισάγεται ΑΦΜ"));
            return "";
        }

        if (custvendFacade.checkIfPhoneNumberExists(phone) > 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ο αριθμός τηλεφώνου που δώσατε υπάρχει."));
            return "";
        }

        if (custvendFacade.checkIfΕmailExists(email) > 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Το email που δώσατε υπάρχει."));
            return "";
        }

        if (custvendFacade.checkIfUserNameExists(username) > 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Το username που δώσατε υπάρχει."));
            return "";
        }

        custvendpdate.setRegister(custvendA.getRegister());
        custvendpdate.setIsactive((short) custvendA.getIsactive());
        custvendpdate.setInsdate(date);
        custvendpdate.setSysuser(custvendA.getSysuser());
        custvendpdate.setUpddate(date);

        //mhnhmata από το magaebean στην σελίδα
        if (custvendFacade.updateCustvendToDatabase(custvendpdate)) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Η επεξεργασία του χρήστη"));
            return "custvenAll";
        }

        return "";
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAfm() {
        return afm;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordCheck() {
        return passwordCheck;
    }

    public void setPasswordCheck(String passwordCheck) {
        this.passwordCheck = passwordCheck;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Roles getRr() {
        return rr;
    }

    public void setRr(Roles rr) {
        this.rr = rr;
    }

    public Custvend getCustvendA() {
        return custvendA;
    }

    public void setCustvendA(Custvend custvendA) {
        this.custvendA = custvendA;
    }


}
