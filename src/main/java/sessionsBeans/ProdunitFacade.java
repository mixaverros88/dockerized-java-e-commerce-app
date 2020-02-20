/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionsBeans;

import entities.Produnit;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * @author user
 */
@Stateless
public class ProdunitFacade {

    @PersistenceContext(unitName = "PrimeFacesPU")
    private EntityManager em;

    protected EntityManager getEm() {
        return em;
    }


    public List<Produnit> getAllProdunit() {
        TypedQuery<Produnit> query = em.createNamedQuery("Produnit.findAll", Produnit.class);
        return query.getResultList();
    }
}
