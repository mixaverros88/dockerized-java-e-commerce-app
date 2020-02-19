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
 * @author user
 */
@Entity
@Table(name = "roles")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Roles.findAll", query = "SELECT r FROM Roles r")
        , @NamedQuery(name = "Roles.findByRoleid", query = "SELECT r FROM Roles r WHERE r.roleid = :roleid")
        , @NamedQuery(name = "Roles.findByDescr", query = "SELECT r FROM Roles r WHERE r.descr = :descr")
        , @NamedQuery(name = "Roles.findByIsactive", query = "SELECT r FROM Roles r WHERE r.isactive = :isactive")})
public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ROLEID")
    private Integer roleid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "DESCR")
    private String descr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ISACTIVE")
    private int isactive;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleid")
    private Collection<Orderlines> orderlinesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleid")
    private Collection<Orders> ordersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleid")
    private Collection<Custvend> custvendCollection;

    public Roles() {
    }

    public Roles(Integer roleid) {
        this.roleid = roleid;
    }

    public Roles(Integer roleid, String descr, int isactive) {
        this.roleid = roleid;
        this.descr = descr;
        this.isactive = isactive;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public int getIsactive() {
        return isactive;
    }

    public void setIsactive(int isactive) {
        this.isactive = isactive;
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

    @XmlTransient
    public Collection<Custvend> getCustvendCollection() {
        return custvendCollection;
    }

    public void setCustvendCollection(Collection<Custvend> custvendCollection) {
        this.custvendCollection = custvendCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleid != null ? roleid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roles)) {
            return false;
        }
        Roles other = (Roles) object;
        if ((this.roleid == null && other.roleid != null) || (this.roleid != null && !this.roleid.equals(other.roleid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Roles[ roleid=" + roleid + " ]";
    }

}
