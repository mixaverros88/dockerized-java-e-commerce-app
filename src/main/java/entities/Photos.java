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
 * <h1>Photos Entity</h1>
 *
 * @author Mike-George Verros
 * @version 1.0
 */
@Entity
@Table(name = "photos")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Photos.findAll", query = "SELECT p FROM Photos p ORDER BY p.photosid ASC")
        , @NamedQuery(name = "Photos.findByPhotosid", query = "SELECT p FROM Photos p WHERE p.photosid = :photosid")
        , @NamedQuery(name = "Photos.findByUrl", query = "SELECT p FROM Photos p WHERE p.url = :url")
        , @NamedQuery(name = "Photos.findByMIMEtype", query = "SELECT p FROM Photos p WHERE p.mIMEtype = :mIMEtype")})
public class Photos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PHOTOSID")
    private Integer photosid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "URL")
    private String url;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "MIMEtype")
    private String mIMEtype;
    @OneToMany(mappedBy = "photosid")
    private Collection<Product> productCollection;

    public Photos() {
    }

    public Photos(Integer photosid) {
        this.photosid = photosid;
    }

    public Photos(Integer photosid, String url, String mIMEtype) {
        this.photosid = photosid;
        this.url = url;
        this.mIMEtype = mIMEtype;
    }

    public Integer getPhotosid() {
        return photosid;
    }

    public void setPhotosid(Integer photosid) {
        this.photosid = photosid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMIMEtype() {
        return mIMEtype;
    }

    public void setMIMEtype(String mIMEtype) {
        this.mIMEtype = mIMEtype;
    }

    @XmlTransient
    public Collection<Product> getProductCollection() {
        return productCollection;
    }

    public void setProductCollection(Collection<Product> productCollection) {
        this.productCollection = productCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (photosid != null ? photosid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Photos)) {
            return false;
        }
        Photos other = (Photos) object;
        return (this.photosid != null || other.photosid == null) && (this.photosid == null || this.photosid.equals(other.photosid));
    }

    @Override
    public String toString() {
        return "entities.Photos[ photosid=" + photosid + " ]";
    }

}
