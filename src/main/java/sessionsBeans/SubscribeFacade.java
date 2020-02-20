/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionsBeans;

import entities.Subscribe;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author user
 */
@Stateless
public class SubscribeFacade {

    @PersistenceContext(unitName = "PrimeFacesPU")
    private EntityManager em;

    protected EntityManager getEm() {
        return em;
    }

    public boolean insertSubscriberToDB(Subscribe subscribe) {

        try {
            em.persist(subscribe);
            em.flush();
            return true;
        } catch (Exception ex) {
            return false;
        }

    }

}
