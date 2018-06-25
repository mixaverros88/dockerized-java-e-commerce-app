/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "orderlines")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orderlines.findAll", query = "SELECT o FROM Orderlines o")
    , @NamedQuery(name = "Orderlines.findByOrderlinesid", query = "SELECT o FROM Orderlines o WHERE o.orderlinesid = :orderlinesid")
    , @NamedQuery(name = "Orderlines.findByQty", query = "SELECT o FROM Orderlines o WHERE o.qty = :qty")
    , @NamedQuery(name = "Orderlines.findByPrice", query = "SELECT o FROM Orderlines o WHERE o.price = :price")
    , @NamedQuery(name = "Orderlines.findByLinenetval", query = "SELECT o FROM Orderlines o WHERE o.linenetval = :linenetval")
    , @NamedQuery(name = "Orderlines.findByLinevatval", query = "SELECT o FROM Orderlines o WHERE o.linevatval = :linevatval")
    , @NamedQuery(name = "Orderlines.findByLinesumval", query = "SELECT o FROM Orderlines o WHERE o.linesumval = :linesumval")
    , @NamedQuery(name = "Orderlines.findByRemarks", query = "SELECT o FROM Orderlines o WHERE o.remarks = :remarks")})
public class Orderlines implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ORDERLINESID")
    private Integer orderlinesid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "QTY")
    private Float qty;
    @Column(name = "PRICE")
    private Float price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LINENETVAL")
    private float linenetval;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LINEVATVAL")
    private float linevatval;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LINESUMVAL")
    private float linesumval;
    @Size(max = 2000)
    @Column(name = "REMARKS")
    private String remarks;
    @JoinColumn(name = "ORDERID", referencedColumnName = "ORDERID")
    @ManyToOne(optional = false)
    private Orders orderid;
    @JoinColumn(name = "ROLEID", referencedColumnName = "ROLEID")
    @ManyToOne(optional = false)
    private Roles roleid;
    @JoinColumn(name = "PRODUCTID", referencedColumnName = "PRODUCTID")
    @ManyToOne(optional = false)
    private Product productid;
    @JoinColumn(name = "PRODUNITID", referencedColumnName = "PRODUNITID")
    @ManyToOne
    private Produnit produnitid;
    @JoinColumn(name = "VENDOR", referencedColumnName = "CUSTVENDID")
    @ManyToOne
    private Custvend vendor;

    public Orderlines() {
    }

    public Orderlines(Integer orderlinesid) {
        this.orderlinesid = orderlinesid;
    }

    public Orderlines(Integer orderlinesid, float linenetval, float linevatval, float linesumval) {
        this.orderlinesid = orderlinesid;
        this.linenetval = linenetval;
        this.linevatval = linevatval;
        this.linesumval = linesumval;
    }

    public Integer getOrderlinesid() {
        return orderlinesid;
    }

    public void setOrderlinesid(Integer orderlinesid) {
        this.orderlinesid = orderlinesid;
    }

    public Float getQty() {
        return qty;
    }

    public void setQty(Float qty) {
        this.qty = qty;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public float getLinenetval() {
        return linenetval;
    }

    public void setLinenetval(float linenetval) {
        this.linenetval = linenetval;
    }

    public float getLinevatval() {
        return linevatval;
    }

    public void setLinevatval(float linevatval) {
        this.linevatval = linevatval;
    }

    public float getLinesumval() {
        return linesumval;
    }

    public void setLinesumval(float linesumval) {
        this.linesumval = linesumval;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Orders getOrderid() {
        return orderid;
    }

    public void setOrderid(Orders orderid) {
        this.orderid = orderid;
    }

    public Roles getRoleid() {
        return roleid;
    }

    public void setRoleid(Roles roleid) {
        this.roleid = roleid;
    }

    public Product getProductid() {
        return productid;
    }

    public void setProductid(Product productid) {
        this.productid = productid;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderlinesid != null ? orderlinesid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orderlines)) {
            return false;
        }
        Orderlines other = (Orderlines) object;
        if ((this.orderlinesid == null && other.orderlinesid != null) || (this.orderlinesid != null && !this.orderlinesid.equals(other.orderlinesid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Orderlines[ orderlinesid=" + orderlinesid + " ]";
    }
    
}
