/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionsBeans;

/**
 * @author user
 */

import entities.Role;
import entities.Roles;
import entities.User;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author user
 */
@Stateless
public class UserAddFacade {

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


    public List<Role> findRoles() {
        //JBQL
        TypedQuery<Role> query = em.createNamedQuery("Role.findAll", Role.class);
        return query.getResultList();
    }

    public List<Roles> findRoles1() {
        //JBQL
        TypedQuery<Roles> query = em.createNamedQuery("Roles.findAll", Roles.class);
        return query.getResultList();
    }


    public Role returnOneRole(String id) {
        Role rol = new Role();
        rol = em.find(Role.class, Integer.parseInt(id));
        return rol;
    }

    public Roles returnOneRoles(String id) {
        Roles roles = new Roles();
        roles = em.find(Roles.class, Integer.parseInt(id));
        return roles;
    }

    public boolean insertUserToDB(User u) {

        try {
            em.persist(u);
            em.flush();
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }

    }

}
