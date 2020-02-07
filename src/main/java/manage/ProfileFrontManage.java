/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import entities.Custvend;
import entities.Orders;
import entities.Wishlist;
import helpers.HashinUtils;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
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
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import sessionsBeans.CustvendFacade;
import sessionsBeans.OrdersFacade;
import sessionsBeans.WhishListFacade;

/**
 *
 * @author user
 */
@ManagedBean
@RequestScoped
public class ProfileFrontManage implements Serializable{
    
    @NotNull(message = "Συμπληρώστε το όνομα")  
    private String fname;
    @NotNull(message = "Συμπληρώστε το επίθετο") 
    private String lname;
    @NotNull(message = "Συμπληρώστε το ΑΦΜ") 
    @Size(min=10, max=10, message="Το ΑΦΜ θα πρέπει να έχει 10 αριθμούς")
    private String afm;
    @Size(min=25, message="Το username θα πρέπει να έχει πάνω από2 στοιχεία")
    @NotNull(message = "Συμπληρώστε το username") 
    private String username;
    @Size(min=5, message="Το password θα πρέπει να έχει πάνω από 5 στοιχεία")
    @NotNull(message = "Συμπληρώστε το password") 
    private String password;
    @NotNull(message = "Συμπληρώστε το password") 
    private String passwordCheck;
    @NotNull(message = "Συμπληρώστε το ΤΚ") 
    @Size(min=5, max=5, message="Το ΤΚ θα πρέπει να έχει 5 αριθμούς")
    private String zip;
    @NotNull(message = "Συμπληρώστε την Πόλη") 
    private String city;
    @NotNull(message = "Συμπληρώστε την περιοχή") 
    private String district;
    @NotNull(message = "Συμπληρώστε το email") 
    private String email;
    private String jobname;
    private String remarks="1";
    private String isactive;
    @NotNull(message = "Συμπληρώστε την Διεύθυνση")
    private String address;
    @NotNull(message = "Συμπληρώστε τον Αριθμό Τηλεφώνου")
    @Size(min=10, max=10, message="O αριθμός τηλεφώνου θα πρέπει να είναι 10 ψηφία")
    private String phone;
    
    @EJB
    CustvendFacade custvendFacade;
    
    @EJB
    OrdersFacade ordersFacade;
    
    @EJB
    WhishListFacade whishListFacade;
    
    Custvend custvend;
    
    @PostConstruct
    public void init() {
        
        HttpSession session = SessionUtils.getSession();
        Custvend custvend = (Custvend) session.getAttribute("Custvend");
       
    }
    
      public String updateCustvend() throws NoSuchAlgorithmException{

        boolean mr=false;
        Custvend custvendpdate = new Custvend(); 
         HashinUtils hu = new HashinUtils();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();

        custvendpdate.setCustvendid(custvend.getCustvendid());
        custvendpdate.setFname(custvend.getFname().trim());
        custvendpdate.setLname(custvend.getLname().trim());
        custvendpdate.setAfm(custvend.getAfm().trim());
        custvendpdate.setUsername(custvend.getUsername().trim());

        custvendpdate.setAddress(custvend.getAddress().trim());
        custvendpdate.setZip(custvend.getZip().trim());
        custvendpdate.setCity(custvend.getCity().trim());
        custvendpdate.setDistrict(custvend.getDistrict().trim());
        custvendpdate.setPhone(custvend.getPhone().trim());
        custvendpdate.setEmail(custvend.getEmail().trim());
        custvendpdate.setJobname(custvend.getJobname().trim());

        custvendpdate.setSysuser(custvend.getSysuser());
        custvendpdate.setUpddate(date);

        
        mr = custvendFacade.updateCustvendToDatabase(custvendpdate);
        
        //mhnhmata από το magaebean στην σελίδα
        if(mr==true){
           
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Η επεξεργασία του χρήστη"));
            return "profile?id=74";
        }   
        
        return "profile?id=74";
    }
    public Custvend getCustvendByID(int id){
        Custvend cv = custvendFacade.searchWithID(id);
        return cv;
    }
    
    
    public List<Orders> getAllOrdersByID(int id){
        List<Orders> orders = ordersFacade.getAllSallesBySyuserFromDB(id);
        return orders;
    }

    
    public List<Wishlist> getAllWishlistByID(Custvend custvend){
        List<Wishlist> orders = whishListFacade.getAllWishlistBySyuserFromDB(custvend);
        return orders;
    }
        
    public Custvend getCustvend() {
        return custvend;
    }

    public void setCustvend(Custvend custvend) {
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public CustvendFacade getCustvendFacade() {
        return custvendFacade;
    }

    public void setCustvendFacade(CustvendFacade custvendFacade) {
        this.custvendFacade = custvendFacade;
    }

    public OrdersFacade getOrdersFacade() {
        return ordersFacade;
    }

    public void setOrdersFacade(OrdersFacade ordersFacade) {
        this.ordersFacade = ordersFacade;
    }
    
}
