package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * <h1>Orders Entity</h1>
 *
 * @author Mike-George Verros
 * @version 1.0
 */
@Entity
@SqlResultSetMapping(
        name = "ordersChart",
        entities = @EntityResult(
                entityClass = Orders.class,
                fields = {
                        @FieldResult(name = "orderdate", column = "dateChart"),
                        @FieldResult(name = "sumamnt", column = "sumChart")}))


@Table(name = "orders")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o ORDER BY o.orderid DESC")
        //, @NamedQuery(name = "Orders.chart", query = "SELECT CAST(o.orderdate AS date), SUM(o.sumamnt) FROM o.Orders group by CAST(o.orderdate AS date)")
        , @NamedQuery(name = "Orders.findByCustvendid", query = "SELECT o FROM Orders o WHERE o.custvendid = :custvendid")
        , @NamedQuery(name = "Orders.findByOrderid", query = "SELECT o FROM Orders o WHERE o.orderid = :orderid")
        , @NamedQuery(name = "Orders.findByOrderdate", query = "SELECT o FROM Orders o WHERE o.orderdate = :orderdate")
        , @NamedQuery(name = "Orders.findByVatid", query = "SELECT o FROM Orders o WHERE o.vatid = :vatid")
        , @NamedQuery(name = "Orders.findByIscancel", query = "SELECT o FROM Orders o WHERE o.iscancel = :iscancel")
        , @NamedQuery(name = "Orders.findByIsapprv", query = "SELECT o FROM Orders o WHERE o.isapprv = :isapprv")
        , @NamedQuery(name = "Orders.findByIspayed", query = "SELECT o FROM Orders o WHERE o.ispayed = :ispayed")
        , @NamedQuery(name = "Orders.findBySumamnt", query = "SELECT o FROM Orders o WHERE o.sumamnt = :sumamnt")
        , @NamedQuery(name = "Orders.findByRemarks", query = "SELECT o FROM Orders o WHERE o.remarks = :remarks")
        , @NamedQuery(name = "Orders.findByInsdate", query = "SELECT o FROM Orders o WHERE o.insdate = :insdate")
        , @NamedQuery(name = "Orders.findBySysuser", query = "SELECT o FROM Orders o WHERE o.sysuser = :sysuser ORDER BY o.orderid DESC")
        , @NamedQuery(name = "Orders.findByUpddate", query = "SELECT o FROM Orders o WHERE o.upddate = :upddate")})
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ORDERID")
    private Integer orderid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDERDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VATID")
    private int vatid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ISCANCEL")
    private short iscancel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ISAPPRV")
    private short isapprv;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ISPAYED")
    private short ispayed;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SUMAMNT")
    private float sumamnt;
    @Size(max = 2000)
    @Column(name = "REMARKS")
    private String remarks;
    @Column(name = "INVOICE")
    private String invoice;
    @Column(name = "INSDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date insdate;
    @Column(name = "SYSUSER")
    private int sysuser;
    @Column(name = "UPDDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date upddate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderid")
    private Collection<Orderlines> orderlinesCollection;
    @JoinColumn(name = "ROLEID", referencedColumnName = "ROLEID")
    @ManyToOne(optional = false)
    private Roles roleid;
    @JoinColumn(name = "CUSTVENDID", referencedColumnName = "CUSTVENDID")
    @ManyToOne
    private Custvend custvendid;

    public Orders() {
    }

    public Orders(Integer orderid) {
        this.orderid = orderid;
    }

    public Orders(Integer orderid, Date orderdate, int vatid, short iscancel, short isapprv, short ispayed, float sumamnt) {
        this.orderid = orderid;
        this.orderdate = orderdate;
        this.vatid = vatid;
        this.iscancel = iscancel;
        this.isapprv = isapprv;
        this.ispayed = ispayed;
        this.sumamnt = sumamnt;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public int getVatid() {
        return vatid;
    }

    public void setVatid(int vatid) {
        this.vatid = vatid;
    }

    public short getIscancel() {
        return iscancel;
    }

    public void setIscancel(short iscancel) {
        this.iscancel = iscancel;
    }

    public short getIsapprv() {
        return isapprv;
    }

    public void setIsapprv(short isapprv) {
        this.isapprv = isapprv;
    }

    public short getIspayed() {
        return ispayed;
    }

    public void setIspayed(short ispayed) {
        this.ispayed = ispayed;
    }

    public float getSumamnt() {
        return sumamnt;
    }

    public void setSumamnt(float sumamnt) {
        this.sumamnt = sumamnt;
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

    public int getSysuser() {
        return sysuser;
    }

    public void setSysuser(int sysuser) {
        this.sysuser = sysuser;
    }

    public Date getUpddate() {
        return upddate;
    }

    public void setUpddate(Date upddate) {
        this.upddate = upddate;
    }

    @XmlTransient
    public Collection<Orderlines> getOrderlinesCollection() {
        return orderlinesCollection;
    }

    public void setOrderlinesCollection(Collection<Orderlines> orderlinesCollection) {
        this.orderlinesCollection = orderlinesCollection;
    }

    public Roles getRoleid() {
        return roleid;
    }

    public void setRoleid(Roles roleid) {
        this.roleid = roleid;
    }

    public Custvend getCustvendid() {
        return custvendid;
    }

    public void setCustvendid(Custvend custvendid) {
        this.custvendid = custvendid;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderid != null ? orderid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        return (this.orderid != null || other.orderid == null) && (this.orderid == null || this.orderid.equals(other.orderid));
    }

    @Override
    public String toString() {
        return "entities.Orders[ orderid=" + orderid + " ]";
    }

}
