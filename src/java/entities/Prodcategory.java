/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "prodcategory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prodcategory.findAll", query = "SELECT p FROM Prodcategory p ORDER BY p.prodcategoryid DESC")
    , @NamedQuery(name = "Prodcategory.findByProdcategoryid", query = "SELECT p FROM Prodcategory p WHERE p.prodcategoryid = :prodcategoryid")
    , @NamedQuery(name = "Prodcategory.findByName", query = "SELECT p FROM Prodcategory p WHERE p.name = :name")
    , @NamedQuery(name = "Prodcategory.findBySlugname", query = "SELECT p FROM Prodcategory p WHERE p.slugname = :slugname")
    , @NamedQuery(name = "Prodcategory.findByIsactive", query = "SELECT p FROM Prodcategory p WHERE p.isactive = :isactive")})
public class Prodcategory implements Serializable {

    @OneToMany(mappedBy = "prodcategoryid")
    private Collection<Product> productCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PRODCATEGORYID")
    private Integer prodcategoryid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "SLUGNAME")
    private String slugname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ISACTIVE")
    private int isactive;

    public Prodcategory() {
    }

    public Prodcategory(Integer prodcategoryid) {
        this.prodcategoryid = prodcategoryid;
    }

    public Prodcategory(Integer prodcategoryid, String name, String slugname, int isactive) {
        this.prodcategoryid = prodcategoryid;
        this.name = name;
        this.slugname = slugname;
        this.isactive = isactive;
    }

    public Integer getProdcategoryid() {
        return prodcategoryid;
    }

    public void setProdcategoryid(Integer prodcategoryid) {
        this.prodcategoryid = prodcategoryid;
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

    public int getIsactive() {
        return isactive;
    }

    public void setIsactive(int isactive) {
        this.isactive = isactive;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prodcategoryid != null ? prodcategoryid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prodcategory)) {
            return false;
        }
        Prodcategory other = (Prodcategory) object;
        if ((this.prodcategoryid == null && other.prodcategoryid != null) || (this.prodcategoryid != null && !this.prodcategoryid.equals(other.prodcategoryid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Prodcategory[ prodcategoryid=" + prodcategoryid + " ]";
    }

    @XmlTransient
    public Collection<Product> getProductCollection() {
        return productCollection;
    }

    public void setProductCollection(Collection<Product> productCollection) {
        this.productCollection = productCollection;
    }
    
}
