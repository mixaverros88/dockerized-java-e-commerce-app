package sessionsBeans;

import entities.Custvend;
import org.apache.log4j.Logger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class UserAllFacade {

    final static Logger logger = Logger.getLogger(UserAllFacade.class);

    @PersistenceContext(unitName = "PrimeFacesPU")
    private EntityManager em;

    public List<Custvend> getAllUsers() {
        TypedQuery<Custvend> query = em.createNamedQuery("Custvend.findAll", Custvend.class);
         return query.getResultList();
    }

    public Custvend searchWithat(String at) {
        return em.find(Custvend.class, at);
    }

    public int deleteUser(int at) {
        Custvend custvend = em.find(Custvend.class, at);
        if(custvend != null){
            em.remove(custvend);
            return 1;
        }else {
            return 0;
        }
    }
}
