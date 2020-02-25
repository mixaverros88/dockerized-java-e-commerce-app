package sessionsBeans;

import entities.Custvend;
import helpers.HashinUtils;
import org.apache.log4j.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class LoginFacade {

    final static Logger logger = Logger.getLogger(LoginFacade.class);

    @PersistenceContext(unitName = "PrimeFacesPU")
    private EntityManager em;

    protected EntityManager getEm() {
        return em;
    }

    public Custvend getUser(String username, String password) {

        HashinUtils hu = new HashinUtils();
        String hashPassword = hu.hashPass(password);
        Custvend user = null;
        try {
            user = (Custvend) em.createQuery(

                    "SELECT u FROM Custvend u WHERE u.username = :username AND u.isactive = :isactive")
                    .setParameter("username", username)
                    .setParameter("isactive", 1)
                    .getSingleResult();
            System.out.println("password: " + password);
            System.out.println("password: " + user.getPasswd());

            if (!hu.checkHash(password, user.getPasswd())) {
                return user = null;
            }
            if (user != null) {
                return user;
            }

        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            return user;
        }

        return user;
    }

}
