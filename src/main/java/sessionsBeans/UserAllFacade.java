package sessionsBeans;

import entities.Custvend;
import entities.User;
import org.apache.log4j.Logger;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class UserAllFacade {

    final static Logger logger = Logger.getLogger(UserAllFacade.class);

    @PersistenceContext(unitName = "PrimeFacesPU")
    private EntityManager em;

    protected EntityManager getEm() {
        return em;
    }

    public List<Custvend> getAllUsers() {
        TypedQuery<Custvend> query = em.createNamedQuery("Custvend.findAll", Custvend.class);
         return query.getResultList();
    }

    public User searchWithat(String at) {
        User user = null;
        TypedQuery<User> query = em.createNamedQuery("User.findByAt", User.class).setParameter("at", at);
        return query.getSingleResult();
    }

    public int deleteUser(int at) {
        Query query = em.createNativeQuery("DELETE FROM user WHERE at =" + at);
        return query.executeUpdate();
    }
}
