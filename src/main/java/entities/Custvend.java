package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * <h1>User Entity</h1>
 *
 * @author Mike-George Verros
 * @version 1.0
 */
@Entity
@Table(name = "custvend")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Custvend.findAll", query = "SELECT c FROM Custvend c")
        , @NamedQuery(name = "Custvend.findByCustvendid", query = "SELECT c FROM Custvend c WHERE c.custvendid = :custvendid")
        , @NamedQuery(name = "Custvend.findByFname", query = "SELECT c FROM Custvend c WHERE c.fname = :fname")
        , @NamedQuery(name = "Custvend.findByLname", query = "SELECT c FROM Custvend c WHERE c.lname = :lname")
        , @NamedQuery(name = "Custvend.findByAfm", query = "SELECT c FROM Custvend c WHERE c.afm = :afm")
        , @NamedQuery(name = "Custvend.findByUsername", query = "SELECT c FROM Custvend c WHERE c.username = :username")
        , @NamedQuery(name = "Custvend.findByPasswd", query = "SELECT c FROM Custvend c WHERE c.passwd = :passwd")
        , @NamedQuery(name = "Custvend.findByAddress", query = "SELECT c FROM Custvend c WHERE c.address = :address")
        , @NamedQuery(name = "Custvend.findByZip", query = "SELECT c FROM Custvend c WHERE c.zip = :zip")
        , @NamedQuery(name = "Custvend.findByCity", query = "SELECT c FROM Custvend c WHERE c.city = :city")
        , @NamedQuery(name = "Custvend.findByDistrict", query = "SELECT c FROM Custvend c WHERE c.district = :district")
        , @NamedQuery(name = "Custvend.findByPhone", query = "SELECT c FROM Custvend c WHERE c.phone = :phone")
        , @NamedQuery(name = "Custvend.findByEmail", query = "SELECT c FROM Custvend c WHERE c.email = :email")
        , @NamedQuery(name = "Custvend.findByJobname", query = "SELECT c FROM Custvend c WHERE c.jobname = :jobname")
        , @NamedQuery(name = "Custvend.findByBallance", query = "SELECT c FROM Custvend c WHERE c.ballance = :ballance")
        , @NamedQuery(name = "Custvend.findByRemarks", query = "SELECT c FROM Custvend c WHERE c.remarks = :remarks")
        , @NamedQuery(name = "Custvend.findByRegister", query = "SELECT c FROM Custvend c WHERE c.register = :register")
        , @NamedQuery(name = "Custvend.findByIsactive", query = "SELECT c FROM Custvend c WHERE c.isactive = :isactive")
        , @NamedQuery(name = "Custvend.findByInsdate", query = "SELECT c FROM Custvend c WHERE c.insdate = :insdate")
        , @NamedQuery(name = "Custvend.findByCredits", query = "SELECT c FROM Custvend c WHERE c.credits = :credits")
        , @NamedQuery(name = "Custvend.findBySysuser", query = "SELECT c FROM Custvend c WHERE c.sysuser = :sysuser")
        , @NamedQuery(name = "Custvend.findByUpddate", query = "SELECT c FROM Custvend c WHERE c.upddate = :upddate")})
public class Custvend implements Serializable {

    @Column(name = "REGISTER")
    private int register;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ISACTIVE")
    private int isactive;
    @OneToMany(mappedBy = "custvendid")
    private Collection<Wishlist> wishlistCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CUSTVENDID")
    private Integer custvendid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "FNAME")
    private String fname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "LNAME")
    private String lname;
    @Size(max = 20)
    @Column(name = "AFM")
    private String afm;
    @Size(max = 45)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "PASSWD")
    private String passwd;
    @Size(max = 100)
    @Column(name = "ADDRESS")
    private String address;
    @Size(max = 10)
    @Column(name = "ZIP")
    private String zip;
    @Size(max = 30)
    @Column(name = "CITY")
    private String city;
    @Size(max = 30)
    @Column(name = "DISTRICT")
    private String district;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PHONE")
    private String phone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 128)
    @Column(name = "JOBNAME")
    private String jobname;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "BALLANCE")
    private Float ballance;
    @Size(max = 2000)
    @Column(name = "REMARKS")
    private String remarks;
    @Column(name = "INSDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date insdate;
    @Column(name = "CREDITS")
    private float credits;
    @Column(name = "SYSUSER")
    private Short sysuser;
    @Column(name = "UPDDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date upddate;
    @OneToMany(mappedBy = "vendor")
    private Collection<Product> productCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "custvendid")
    private Collection<Ballance> ballanceCollection;
    @OneToMany(mappedBy = "vendor")
    private Collection<Orderlines> orderlinesCollection;
    @OneToMany(mappedBy = "custvendid")
    private Collection<Orders> ordersCollection;
    @JoinColumn(name = "ROLEID", referencedColumnName = "ROLEID")
    @ManyToOne(optional = false)
    private Roles roleid;

    public Custvend() {
    }

    public Custvend(Integer custvendid) {
        this.custvendid = custvendid;
    }

    public Custvend(Integer custvendid, String fname, String lname, String passwd, String phone, String email, short isactive) {
        this.custvendid = custvendid;
        this.fname = fname;
        this.lname = lname;
        this.passwd = passwd;
        this.phone = phone;
        this.email = email;
        this.isactive = isactive;
    }

    public Integer getCustvendid() {
        return custvendid;
    }

    public void setCustvendid(Integer custvendid) {
        this.custvendid = custvendid;
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

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Float getBallance() {
        return ballance;
    }

    public void setBallance(Float ballance) {
        this.ballance = ballance;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


    public Date getInsdate() {
        return insdate;
    }

    public void setInsdate(Date insdate) {
        this.insdate = insdate;
    }

    public Short getSysuser() {
        return sysuser;
    }

    public void setSysuser(Short sysuser) {
        this.sysuser = sysuser;
    }

    public Date getUpddate() {
        return upddate;
    }

    public void setUpddate(Date upddate) {
        this.upddate = upddate;
    }

    @XmlTransient
    public Collection<Product> getProductCollection() {
        return productCollection;
    }

    public void setProductCollection(Collection<Product> productCollection) {
        this.productCollection = productCollection;
    }

    @XmlTransient
    public Collection<Ballance> getBallanceCollection() {
        return ballanceCollection;
    }

    public void setBallanceCollection(Collection<Ballance> ballanceCollection) {
        this.ballanceCollection = ballanceCollection;
    }

    @XmlTransient
    public Collection<Orderlines> getOrderlinesCollection() {
        return orderlinesCollection;
    }

    public void setOrderlinesCollection(Collection<Orderlines> orderlinesCollection) {
        this.orderlinesCollection = orderlinesCollection;
    }

    @XmlTransient
    public Collection<Orders> getOrdersCollection() {
        return ordersCollection;
    }

    public void setOrdersCollection(Collection<Orders> ordersCollection) {
        this.ordersCollection = ordersCollection;
    }

    public Roles getRoleid() {
        return roleid;
    }

    public void setRoleid(Roles roleid) {
        this.roleid = roleid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (custvendid != null ? custvendid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Custvend)) {
            return false;
        }
        Custvend other = (Custvend) object;
        return (this.custvendid != null || other.custvendid == null) && (this.custvendid == null || this.custvendid.equals(other.custvendid));
    }

    @Override
    public String toString() {
        return "entities.Custvend[ custvendid=" + custvendid + " ]";
    }


    @XmlTransient
    public Collection<Wishlist> getWishlistCollection() {
        return wishlistCollection;
    }

    public void setWishlistCollection(Collection<Wishlist> wishlistCollection) {
        this.wishlistCollection = wishlistCollection;
    }

    public void setSysuser(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getRegister() {
        return register;
    }

    public void setRegister(int register) {
        this.register = register;
    }

    public int getIsactive() {
        return isactive;
    }

    public void setIsactive(int isactive) {
        this.isactive = isactive;
    }

    public float getCredits() {
        return credits;
    }

    public void setCredits(float credits) {
        this.credits = credits;
    }


}
