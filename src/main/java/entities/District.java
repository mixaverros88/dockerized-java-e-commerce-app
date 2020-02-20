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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <h1>District Entity</h1>
 *
 * @author Mike-George Verros
 * @version 1.0
 */
@Entity
@Table(name = "district")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "District.findAll", query = "SELECT d FROM District d")
        , @NamedQuery(name = "District.findByDistrict", query = "SELECT d FROM District d WHERE d.district = :district")
        , @NamedQuery(name = "District.findByName", query = "SELECT d FROM District d WHERE d.name = :name")
        , @NamedQuery(name = "District.findByTransfee", query = "SELECT d FROM District d WHERE d.transfee = :transfee")})
public class District implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DISTRICT")
    private Integer district;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NAME")
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TRANSFEE")
    private Float transfee;

    public District() {
    }

    public District(Integer district) {
        this.district = district;
    }

    public District(Integer district, String name) {
        this.district = district;
        this.name = name;
    }

    public Integer getDistrict() {
        return district;
    }

    public void setDistrict(Integer district) {
        this.district = district;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getTransfee() {
        return transfee;
    }

    public void setTransfee(Float transfee) {
        this.transfee = transfee;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (district != null ? district.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof District)) {
            return false;
        }
        District other = (District) object;
        return (this.district != null || other.district == null) && (this.district == null || this.district.equals(other.district));
    }

    @Override
    public String toString() {
        return "entities.District[ district=" + district + " ]";
    }

}
