/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionsBeans;

import entities.Ballance;
import entities.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author user
 */
@Stateless
public class BallanceFacade {

    @PersistenceContext(unitName = "PrimeFacesPU")
    private EntityManager em;

    protected EntityManager getEm() {
        return em;
    }


    public boolean insertBalanceToDB(Ballance ballance) {

        try {
            em.persist(ballance);
            em.flush();
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }
}
