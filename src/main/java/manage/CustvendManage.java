package manage;

import entities.Custvend;
import entities.Roles;
import helpers.HashinUtils;
import helpers.MailSender;
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
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.log4j.Logger;
import sessionsBeans.CustvendFacade;
import sessionsBeans.ProductFacade;
import sessionsBeans.UserAddFacade;

@ManagedBean
@RequestScoped
public class CustvendManage implements Serializable {

    final static Logger logger = Logger.getLogger(CustvendManage.class);

    @NotNull(message = "Συμπληρώστε το όνομα")
    private String fname;
    @NotNull(message = "Συμπληρώστε το επίθετο")
    private String lname;
    @NotNull(message = "Συμπληρώστε το ΑΦΜ")
    @Size(min = 10, max = 10, message = "Το ΑΦΜ θα πρέπει να έχει 10 αριθμούς")
    private String afm;
    @Size(min = 2, message = "Το username θα πρέπει να έχει πάνω από2 στοιχεία")
    @NotNull(message = "Συμπληρώστε το username")
    private String username;
    @Size(min = 5, message = "Το password θα πρέπει να έχει πάνω από 5 στοιχεία")
    @NotNull(message = "Συμπληρώστε το password")
    private String password;
    @Size(min = 5, message = "Το password θα πρέπει να έχει πάνω από 5 στοιχεία")
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
    @NotNull(message = "Συμπληρώστε την Διεύθυνση")
    private String address;
    @NotNull(message = "Συμπληρώστε τον Αριθμό Τηλεφώνου")
    @Size(min = 10, max = 10, message = "O αριθμός τηλεφώνου θα πρέπει να είναι 10 ψηφία")
    private String phone;
    private Roles rr;
    private List<Roles> roles;
    private List<Custvend> custvend;

    private HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    private StringBuffer url = origRequest.getRequestURL();


    @EJB
    UserAddFacade userAddFacade;

    @EJB
    CustvendFacade custvendFacade;

    @EJB
    ProductFacade productFacade;

    @PostConstruct
    void init() {
        if(logger.isDebugEnabled()){ logger.debug("Init Manage Users"); }
        roles = userAddFacade.findRoles();
        //
        String urlCompare = "/java-e-commerce/web/register.xhtml";
        String urtString = url.toString();
        if (urlCompare.equals(urtString)) {
            roles.remove(0);
        }
    }

    private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

    public String editCustvend() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        int categoryId = Integer.parseInt(params.get("custvendid"));
        Custvend u = custvendFacade.searchWithID(categoryId);
        sessionMap.put("editCustvend", u);
        return "/web/custvendEdit.xhtml?faces-redirect=true";
    }

    public List<Custvend> getAllCustvendData() {
        return custvend = custvendFacade.getAllCustvendFromDB();
    }

    public long chechIfTheVendorAsycToProduct(Custvend custvend) {
        return productFacade.chechIfTheVendorAsycToProductFromDB(custvend);
    }

    public String deleteCustvend(int id) {

        if (custvendFacade.deleteCustvendFacadeFromDB(id) == 1) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ο χρήστης Διαγράφτηκε επιτυχώς"));

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ο χρήστης<strong>ΔΕΝ</strong> Διαγράφτηκε επιτυχώς"));
        }

        return "";
    }

    public String changeStatusCustvend(int status, int id) {
        if (custvendFacade.changeStatusCustvendFromDB(status, id) == 1) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ο χρήστης άλλαξε κατάσταση επιτυχώς"));

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ο χρήστης άλλαξε <strong>ΔΕΝ</strong> κατάσταση επιτυχώς"));
        }
        return "";
    }

    public String changeRgisterCustvend(int register, int id, Custvend custvend) {
        if (custvendFacade.changeRegisterCustvendFromDB(register, id) == 1) {

            MailSender mailSend = new MailSender();
            if (register == 1) {
                MailSender.send(custvend.getEmail(), "ezikos.gr Έγκριση Λογαριασμού", "<h3>Ο λογαριασμός σας στο ezikos.gr Εγκρίθηκε</h3> <br/> Για να συνδεθείτε πατήστε <a href=\"/java-e-commerce/web/login.xhtml\">εδώ</a>");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ο χρήστης άλλαξε κατάσταση επιτυχώς. Και θα ενημερωθή με email"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ο χρήστης άλλαξε <strong>ΔΕΝ</strong> κατάσταση επιτυχώς"));
        }
        return "";
    }

    public String insertCustVend() {

        Date date = new Date();
        HashinUtils hu = new HashinUtils();
        Custvend custvend = new Custvend();
        custvend.setFname(fname.trim());
        custvend.setLname(lname.trim());


        custvend.setAfm(afm.trim());
        custvend.setUsername(username.trim());
        custvend.setZip(zip.trim());
        custvend.setDistrict(district.trim());
        custvend.setEmail(email.trim());
        custvend.setRoleid(rr);
        custvend.setCity(city);
        custvend.setAddress(address.trim());
        custvend.setPhone(phone.trim());
        custvend.setJobname(jobname);
        custvend.setBallance(0.0f);
        custvend.setBallance((float) 0.0);
        custvend.setRemarks("");
        if (rr.getRoleid() == 1 || rr.getRoleid() == 2) {
            custvend.setIsactive((short) 1);
            custvend.setRegister(1);
        } else {
            custvend.setIsactive((short) 0);
            custvend.setRegister(0);
        }

        custvend.setInsdate(date);


        custvend.setPasswd(hu.hashPass(password.trim()));

        if(logger.isDebugEnabled()){ logger.debug("AM Validation"); }
        if (custvend.getRoleid().getRoleid() == 3 && custvend.getAfm() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Εφόσον είσται προμηθευτής θα πρέπει να εισάγεται ΑΦΜ"));
            return "";
        }

        if(logger.isDebugEnabled()){ logger.debug("Password Validation"); }
        if (!password.equals(passwordCheck)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Τα password δεν ταιριάζουν"));
            return "";
        }

        if(logger.isDebugEnabled()){ logger.debug("Phone Validation"); }
        if (custvendFacade.checkIfPhoneNumberExists(phone) > 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ο αριθμός τηλεφώνου που δώσατε υπάρχει."));
            return "";
        }

        if(logger.isDebugEnabled()){ logger.debug("Email Validation"); }
        if (custvendFacade.checkIfΕmailExists(email) > 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Το email που δώσατε υπάρχει."));
            return "";
        }

        if(logger.isDebugEnabled()){ logger.debug("Username Validation"); }
        if (custvendFacade.checkIfUserNameExists(username) > 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Το username που δώσατε υπάρχει."));
            return "";
        }

        //mhnhmata από το magaebean στην σελίδα
        if (custvendFacade.insertCustVendToDB(custvend)) {
            if(logger.isDebugEnabled()){ logger.debug("After adding the user"); }

            //TODO change url
            String urlCompare = "http://localhost:8080/java-e-commerce/web/register.xhtml";
            String urtString = url.toString();
            //MailSender mailSend = new MailSender();

            if(logger.isDebugEnabled()){ logger.debug("url.toString()" + url.toString()); }

            if (urlCompare.equals(urtString)) {
                if (rr.getRoleid() == 2) {
                    if(logger.isDebugEnabled()){ logger.debug("User role = 2 "); }
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ο λογαριασμό σας δημιουργήθηκε επιτυχώς"));
                    MailSender.send(custvend.getEmail(), "ezikos.gr Δημιουργία Λογαριασμού", "<h3>Ο λογαριασμός σας στο ezikos.gr δημιουργήθηκε επιτυχώς.</h3> <br/> Για να συνδεθείτε πατήστε <a href=\"/java-e-commerce/web/login.xhtml\">εδώ</a>");
                    return "login";
                } else if (rr.getRoleid() == 3) {
                    if(logger.isDebugEnabled()){ logger.debug("User role = 3 "); }
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ο λογαριασμό σας δημιουργήθηκε επιτυχώς. Θα σας αποσταλεί email όταν θα ενεργοποιηθεί"));
                    return "index";
                }
            } else {
                if(logger.isDebugEnabled()){ logger.debug("User role = 1 "); }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ο λογαριασμό σας δημιουργήθηκε επιτυχώς"));
                return "custvenAll";
            }
        }

        return "";
    }

    public List<Custvend> getCustvend() {
        return custvend;
    }

    public void setCustvend(List<Custvend> custvend) {
        this.custvend = custvend;
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

    public Roles getRr() {
        return rr;
    }

    public void setRr(Roles rr) {
        this.rr = rr;
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

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


}
