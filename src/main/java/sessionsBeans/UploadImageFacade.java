package sessionsBeans;

import entities.Photos;
import org.apache.log4j.Logger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class UploadImageFacade {

    final static Logger logger = Logger.getLogger(UploadImageFacade.class);

    @PersistenceContext(unitName = "PrimeFacesPU")
    private EntityManager em;

    protected EntityManager getEm() {
        return em;
    }

    public List<Photos> getAllfiles() {
        TypedQuery<Photos> query = em.createNamedQuery("Photos.findAll", Photos.class);
        return query.getResultList();
    }

    public Photos returnOnePhoto(String id) {
        Photos photos;
        photos = em.find(Photos.class, Integer.parseInt(id));
        return photos;
    }

    public int deletePhotoFromDatabase(int photosid) {
        Query query = em.createNativeQuery("DELETE FROM photos WHERE photosid =" + photosid);
        return query.executeUpdate();
    }

    public boolean insertFilePathToDB(Photos photo) {
        try {
            em.persist(photo);
            em.flush();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
