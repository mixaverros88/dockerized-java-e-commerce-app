package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <h1>Subscriber Entity</h1>
 *
 * @author Mike-George Verros
 * @version 1.0
 */
@Entity
@Table(name = "subscribe")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Subscribe.findAll", query = "SELECT s FROM Subscribe s")
        , @NamedQuery(name = "Subscribe.findBySubscribe", query = "SELECT s FROM Subscribe s WHERE s.subscribe = :subscribe")
        , @NamedQuery(name = "Subscribe.findByEmail", query = "SELECT s FROM Subscribe s WHERE s.email = :email")
        , @NamedQuery(name = "Subscribe.findByInsdate", query = "SELECT s FROM Subscribe s WHERE s.insdate = :insdate")})
public class Subscribe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SUBSCRIBE")
    private Integer subscribe;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "INSDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date insdate;

    public Subscribe() {
    }

    public Subscribe(Integer subscribe) {
        this.subscribe = subscribe;
    }

    public Subscribe(Integer subscribe, String email) {
        this.subscribe = subscribe;
        this.email = email;
    }

    public Integer getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Integer subscribe) {
        this.subscribe = subscribe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getInsdate() {
        return insdate;
    }

    public void setInsdate(Date insdate) {
        this.insdate = insdate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subscribe != null ? subscribe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subscribe)) {
            return false;
        }
        Subscribe other = (Subscribe) object;
        return (this.subscribe != null || other.subscribe == null) && (this.subscribe == null || this.subscribe.equals(other.subscribe));
    }

    @Override
    public String toString() {
        return "entities.Subscribe[ subscribe=" + subscribe + " ]";
    }

}
