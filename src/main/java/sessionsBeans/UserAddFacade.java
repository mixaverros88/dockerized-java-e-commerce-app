package sessionsBeans;

import entities.Role;
import entities.Roles;
import entities.User;
import org.apache.log4j.Logger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class UserAddFacade {

    final static Logger logger = Logger.getLogger(UserAddFacade.class);

    @PersistenceContext(unitName = "PrimeFacesPU")
    private EntityManager em;

    protected EntityManager getEm() {
        return em;
    }


    public User getUserToEdit(int at) {
        User user = null;
        try {
            user = (User) em.createQuery(

                    "SELECT u FROM User u WHERE u.at = :at")
                    .setParameter("at", 1)
                    .getSingleResult();

            if (user != null) {
                return user;
            }

        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            return user;
        }

        return user;

    }

    public List<Role> findRole() {
        //JBQL
        TypedQuery<Role> query = em.createNamedQuery("Role.findAll", Role.class);
        return query.getResultList();
    }

    public List<Roles> findRoles() {
        //JBQL
        TypedQuery<Roles> query = em.createNamedQuery("Roles.findAll", Roles.class);
        return query.getResultList();
    }

    public Role returnOneRole(String id) {
        Role rol;
        rol = em.find(Role.class, Integer.parseInt(id));
        return rol;
    }

    public Roles returnOneRoles(String id) {
        Roles roles;
        roles = em.find(Roles.class, Integer.parseInt(id));
        return roles;
    }

    public boolean insertUserToDB(User u) {
        try {
            em.persist(u);
            em.flush();
            return true;
        } catch (Exception ex) {
            return false;
        }

    }

}
