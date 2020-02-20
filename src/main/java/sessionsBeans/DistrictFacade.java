/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionsBeans;

import entities.District;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * @author user
 */
@Stateless
public class DistrictFacade {

    @PersistenceContext(unitName = "PrimeFacesPU")
    private EntityManager em;

    protected EntityManager getEm() {
        return em;
    }

    public List<District> findAllDistricts() {
        TypedQuery<District> query = em.createNamedQuery("District.findAll", District.class);
        return query.getResultList();
    }

    public District returnOneDistrict(String id) {
        District district;
        district = em.find(District.class, Integer.parseInt(id));
        return district;
    }

}
