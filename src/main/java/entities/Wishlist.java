/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author user
 */
@Entity
@Table(name = "wishlist")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Wishlist.findAll", query = "SELECT w FROM Wishlist w")
        , @NamedQuery(name = "Wishlist.findByWishlistid", query = "SELECT w FROM Wishlist w WHERE w.wishlistid = :wishlistid")
        , @NamedQuery(name = "Wishlist.findByQty", query = "SELECT w FROM Wishlist w WHERE w.qty = :qty")
        , @NamedQuery(name = "Wishlist.findByInsdate", query = "SELECT w FROM Wishlist w WHERE w.insdate = :insdate")
        , @NamedQuery(name = "Wishlist.findByInform", query = "SELECT w FROM Wishlist w WHERE w.inform = :inform")
        , @NamedQuery(name = "Wishlist.findByInformdate", query = "SELECT w FROM Wishlist w WHERE w.informdate = :informdate")})
public class Wishlist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "WISHLISTID")
    private Integer wishlistid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "QTY")
    private Float qty;
    @Column(name = "INSDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date insdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INFORM")
    private int inform;
    @Column(name = "INFORMDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date informdate;
    @JoinColumn(name = "CUSTVENDID", referencedColumnName = "CUSTVENDID")
    @ManyToOne
    private Custvend custvendid;
    @JoinColumn(name = "PRODUCTID", referencedColumnName = "PRODUCTID")
    @ManyToOne(optional = false)
    private Product productid;

    public Wishlist() {
    }

    public Wishlist(Integer wishlistid) {
        this.wishlistid = wishlistid;
    }

    public Wishlist(Integer wishlistid, int inform) {
        this.wishlistid = wishlistid;
        this.inform = inform;
    }

    public Integer getWishlistid() {
        return wishlistid;
    }

    public void setWishlistid(Integer wishlistid) {
        this.wishlistid = wishlistid;
    }

    public Float getQty() {
        return qty;
    }

    public void setQty(Float qty) {
        this.qty = qty;
    }

    public Date getInsdate() {
        return insdate;
    }

    public void setInsdate(Date insdate) {
        this.insdate = insdate;
    }

    public int getInform() {
        return inform;
    }

    public void setInform(int inform) {
        this.inform = inform;
    }

    public Date getInformdate() {
        return informdate;
    }

    public void setInformdate(Date informdate) {
        this.informdate = informdate;
    }

    public Custvend getCustvendid() {
        return custvendid;
    }

    public void setCustvendid(Custvend custvendid) {
        this.custvendid = custvendid;
    }

    public Product getProductid() {
        return productid;
    }

    public void setProductid(Product productid) {
        this.productid = productid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wishlistid != null ? wishlistid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wishlist)) {
            return false;
        }
        Wishlist other = (Wishlist) object;
        if ((this.wishlistid == null && other.wishlistid != null) || (this.wishlistid != null && !this.wishlistid.equals(other.wishlistid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Wishlist[ wishlistid=" + wishlistid + " ]";
    }

}
