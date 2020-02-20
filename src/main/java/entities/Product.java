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
 * <h1>Product Entity</h1>
 *
 * @author Mike-George Verros
 * @version 1.0
 */
@Entity
@Table(name = "product")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p ORDER BY p.productid DESC")
        , @NamedQuery(name = "Product.findByProduCategorytid", query = "SELECT p FROM Product p WHERE p.prodcategoryid = :prodcategoryid")
        , @NamedQuery(name = "Product.findByProductid", query = "SELECT p FROM Product p WHERE p.productid = :productid")
        , @NamedQuery(name = "Product.findByName", query = "SELECT p FROM Product p WHERE p.name = :name")
        , @NamedQuery(name = "Product.findBySlugname", query = "SELECT p FROM Product p WHERE p.slugname = :slugname")
        , @NamedQuery(name = "Product.findByIsactive", query = "SELECT p FROM Product p WHERE p.isactive = :isactive")
        , @NamedQuery(name = "Product.findByIsvisible", query = "SELECT p FROM Product p WHERE p.isvisible = :isvisible")
        , @NamedQuery(name = "Product.findByBuyprice", query = "SELECT p FROM Product p WHERE p.buyprice = :buyprice")
        , @NamedQuery(name = "Product.findBySellprice", query = "SELECT p FROM Product p WHERE p.sellprice = :sellprice")
        , @NamedQuery(name = "Product.findByQty", query = "SELECT p FROM Product p WHERE p.qty = :qty")
        , @NamedQuery(name = "Product.findByRemarks", query = "SELECT p FROM Product p WHERE p.remarks = :remarks")
        , @NamedQuery(name = "Product.findByInsdate", query = "SELECT p FROM Product p WHERE p.insdate = :insdate")
        , @NamedQuery(name = "Product.findBySysuser", query = "SELECT p FROM Product p WHERE p.sysuser = :sysuser")
        , @NamedQuery(name = "Product.findByUpddate", query = "SELECT p FROM Product p WHERE p.upddate = :upddate")})
public class Product implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ISACTIVE")
    private Integer isactive;
    @Column(name = "SYSUSER")
    private Integer sysuser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productid")
    private Collection<Wishlist> wishlistCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PRODUCTID")
    private Integer productid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "SLUGNAME")
    private String slugname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ISVISIBLE")
    private short isvisible;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "BUYPRICE")
    private Float buyprice;
    @Column(name = "SELLPRICE")
    private Float sellprice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QTY")
    private int qty;
    @Size(max = 2000)
    @Column(name = "REMARKS")
    private String remarks;
    @Column(name = "INSDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date insdate;
    @Column(name = "UPDDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date upddate;
    @JoinColumn(name = "PRODCATEGORYID", referencedColumnName = "PRODCATEGORYID")
    @ManyToOne
    private Prodcategory prodcategoryid;
    @JoinColumn(name = "PRODUNITID", referencedColumnName = "PRODUNITID")
    @ManyToOne(optional = false)
    private Produnit produnitid;
    @JoinColumn(name = "VENDOR", referencedColumnName = "CUSTVENDID")
    @ManyToOne
    private Custvend vendor;
    @JoinColumn(name = "PHOTOSID", referencedColumnName = "PHOTOSID")
    @ManyToOne
    private Photos photosid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productid")
    private Collection<Orderlines> orderlinesCollection;

    public Product() {
    }

    public Product(Integer productid) {
        this.productid = productid;
    }

    public Product(Integer productid, String name, String slugname, int isactive, short isvisible, int qty) {
        this.productid = productid;
        this.name = name;
        this.slugname = slugname;
        this.isactive = isactive;
        this.isvisible = isvisible;
        this.qty = qty;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlugname() {
        return slugname;
    }

    public void setSlugname(String slugname) {
        this.slugname = slugname;
    }


    public short getIsvisible() {
        return isvisible;
    }

    public void setIsvisible(short isvisible) {
        this.isvisible = isvisible;
    }

    public Float getBuyprice() {
        return buyprice;
    }

    public void setBuyprice(Float buyprice) {
        this.buyprice = buyprice;
    }

    public Float getSellprice() {
        return sellprice;
    }

    public void setSellprice(Float sellprice) {
        this.sellprice = sellprice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
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


    public Date getUpddate() {
        return upddate;
    }

    public void setUpddate(Date upddate) {
        this.upddate = upddate;
    }

    public Prodcategory getProdcategoryid() {
        return prodcategoryid;
    }

    public void setProdcategoryid(Prodcategory prodcategoryid) {
        this.prodcategoryid = prodcategoryid;
    }

    public Produnit getProdunitid() {
        return produnitid;
    }

    public void setProdunitid(Produnit produnitid) {
        this.produnitid = produnitid;
    }

    public Custvend getVendor() {
        return vendor;
    }

    public void setVendor(Custvend vendor) {
        this.vendor = vendor;
    }

    public Photos getPhotosid() {
        return photosid;
    }

    public void setPhotosid(Photos photosid) {
        this.photosid = photosid;
    }

    @XmlTransient
    public Collection<Orderlines> getOrderlinesCollection() {
        return orderlinesCollection;
    }

    public void setOrderlinesCollection(Collection<Orderlines> orderlinesCollection) {
        this.orderlinesCollection = orderlinesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productid != null ? productid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        return (this.productid != null || other.productid == null) && (this.productid == null || this.productid.equals(other.productid));
    }

    @Override
    public String toString() {
        return "entities.Product[ productid=" + productid + " ]";
    }

    public int getIsactive() {
        return isactive;
    }

    public void setIsactive(int isactive) {
        this.isactive = isactive;
    }

    public Integer getSysuser() {
        return sysuser;
    }

    public void setSysuser(Integer sysuser) {
        this.sysuser = sysuser;
    }

    @XmlTransient
    public Collection<Wishlist> getWishlistCollection() {
        return wishlistCollection;
    }

    public void setWishlistCollection(Collection<Wishlist> wishlistCollection) {
        this.wishlistCollection = wishlistCollection;
    }

}
