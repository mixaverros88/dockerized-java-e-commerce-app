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
@Table(name = "ballance")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Ballance.findAll", query = "SELECT b FROM Ballance b")
        , @NamedQuery(name = "Ballance.findByBallanceid", query = "SELECT b FROM Ballance b WHERE b.ballanceid = :ballanceid")
        , @NamedQuery(name = "Ballance.findByTransactiondate", query = "SELECT b FROM Ballance b WHERE b.transactiondate = :transactiondate")
        , @NamedQuery(name = "Ballance.findByAmount", query = "SELECT b FROM Ballance b WHERE b.amount = :amount")
        , @NamedQuery(name = "Ballance.findByInsdate", query = "SELECT b FROM Ballance b WHERE b.insdate = :insdate")
        , @NamedQuery(name = "Ballance.findBySysuser", query = "SELECT b FROM Ballance b WHERE b.sysuser = :sysuser")
        , @NamedQuery(name = "Ballance.findByUpddate", query = "SELECT b FROM Ballance b WHERE b.upddate = :upddate")})
public class Ballance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BALLANCEID")
    private Integer ballanceid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANSACTIONDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactiondate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AMOUNT")
    private float amount;
    @Column(name = "INSDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date insdate;
    @Column(name = "SYSUSER")
    private Short sysuser;
    @Column(name = "UPDDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date upddate;
    @JoinColumn(name = "CUSTVENDID", referencedColumnName = "CUSTVENDID")
    @ManyToOne(optional = false)
    private Custvend custvendid;

    public Ballance() {
    }

    public Ballance(Integer ballanceid) {
        this.ballanceid = ballanceid;
    }

    public Ballance(Integer ballanceid, Date transactiondate, float amount) {
        this.ballanceid = ballanceid;
        this.transactiondate = transactiondate;
        this.amount = amount;
    }

    public Integer getBallanceid() {
        return ballanceid;
    }

    public void setBallanceid(Integer ballanceid) {
        this.ballanceid = ballanceid;
    }

    public Date getTransactiondate() {
        return transactiondate;
    }

    public void setTransactiondate(Date transactiondate) {
        this.transactiondate = transactiondate;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
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

    public Custvend getCustvendid() {
        return custvendid;
    }

    public void setCustvendid(Custvend custvendid) {
        this.custvendid = custvendid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ballanceid != null ? ballanceid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ballance)) {
            return false;
        }
        Ballance other = (Ballance) object;
        if ((this.ballanceid == null && other.ballanceid != null) || (this.ballanceid != null && !this.ballanceid.equals(other.ballanceid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Ballance[ ballanceid=" + ballanceid + " ]";
    }

}
