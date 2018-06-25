/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author user
 */
@Entity
@Table(name = "produnit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produnit.findAll", query = "SELECT p FROM Produnit p")
    , @NamedQuery(name = "Produnit.findByProdunitid", query = "SELECT p FROM Produnit p WHERE p.produnitid = :produnitid")
    , @NamedQuery(name = "Produnit.findByName", query = "SELECT p FROM Produnit p WHERE p.name = :name")
    , @NamedQuery(name = "Produnit.findByIsactive", query = "SELECT p FROM Produnit p WHERE p.isactive = :isactive")})
public class Produnit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PRODUNITID")
    private Integer produnitid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ISACTIVE")
    private int isactive;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produnitid")
    private Collection<Product> productCollection;
    @OneToMany(mappedBy = "produnitid")
    private Collection<Orderlines> orderlinesCollection;

    public Produnit() {
    }

    public Produnit(Integer produnitid) {
        this.produnitid = produnitid;
    }

    public Produnit(Integer produnitid, String name, int isactive) {
        this.produnitid = produnitid;
        this.name = name;
        this.isactive = isactive;
    }

    public Integer getProdunitid() {
        return produnitid;
    }

    public void setProdunitid(Integer produnitid) {
        this.produnitid = produnitid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIsactive() {
        return isactive;
    }

    public void setIsactive(int isactive) {
        this.isactive = isactive;
    }

    @XmlTransient
    public Collection<Product> getProductCollection() {
        return productCollection;
    }

    public void setProductCollection(Collection<Product> productCollection) {
        this.productCollection = productCollection;
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
        hash += (produnitid != null ? produnitid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produnit)) {
            return false;
        }
        Produnit other = (Produnit) object;
        if ((this.produnitid == null && other.produnitid != null) || (this.produnitid != null && !this.produnitid.equals(other.produnitid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Produnit[ produnitid=" + produnitid + " ]";
    }
    
}
