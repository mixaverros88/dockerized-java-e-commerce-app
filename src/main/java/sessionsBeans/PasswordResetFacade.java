package sessionsBeans;

import entities.Custvend;
import helpers.HashinUtils;
import org.apache.log4j.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PasswordResetFacade {

    final static Logger logger = Logger.getLogger(PasswordResetFacade.class);

    @PersistenceContext(unitName = "PrimeFacesPU")
    private EntityManager em;

    public String changeUserPass(String email, String newPass) {

        HashinUtils hash = new HashinUtils();
        String hashpass = hash.hashPass(newPass);
        Query query = em.createNativeQuery("UPDATE custvend SET passwd ='" + hashpass + "' WHERE email='" + email + "' ");
        query.executeUpdate();
        return newPass;
    }

    public Custvend validateEmail(String email) {
        Custvend user = null;
        try {
            user = (Custvend) em.createQuery(

                    "SELECT u FROM Custvend u WHERE u.email = :email")
                    .setParameter("email", email)
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
}
