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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author user
 */
@Entity
@Table(name = "vat")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Vat.findAll", query = "SELECT v FROM Vat v")
        , @NamedQuery(name = "Vat.findByVatid", query = "SELECT v FROM Vat v WHERE v.vatid = :vatid")
        , @NamedQuery(name = "Vat.findByPercnt", query = "SELECT v FROM Vat v WHERE v.percnt = :percnt")
        , @NamedQuery(name = "Vat.findByIsactive", query = "SELECT v FROM Vat v WHERE v.isactive = :isactive")})
public class Vat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "VATID")
    private Integer vatid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PERCNT")
    private float percnt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ISACTIVE")
    private short isactive;

    public Vat() {
    }

    public Vat(Integer vatid) {
        this.vatid = vatid;
    }

    public Vat(Integer vatid, float percnt, short isactive) {
        this.vatid = vatid;
        this.percnt = percnt;
        this.isactive = isactive;
    }

    public Integer getVatid() {
        return vatid;
    }

    public void setVatid(Integer vatid) {
        this.vatid = vatid;
    }

    public float getPercnt() {
        return percnt;
    }

    public void setPercnt(float percnt) {
        this.percnt = percnt;
    }

    public short getIsactive() {
        return isactive;
    }

    public void setIsactive(short isactive) {
        this.isactive = isactive;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vatid != null ? vatid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vat)) {
            return false;
        }
        Vat other = (Vat) object;
        if ((this.vatid == null && other.vatid != null) || (this.vatid != null && !this.vatid.equals(other.vatid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Vat[ vatid=" + vatid + " ]";
    }

}
