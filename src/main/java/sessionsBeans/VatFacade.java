/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionsBeans;

import entities.Vat;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * @author user
 */
@Stateless
public class VatFacade {


    @PersistenceContext(unitName = "PrimeFacesPU")
    private EntityManager em;

    protected EntityManager getEm() {
        return em;
    }

    public Vat returnOneVat(String id) {
        Vat vat;
        vat = em.find(Vat.class, Integer.parseInt(id));
        return vat;
    }


    public Vat searchWithID(int id) {
        Vat vat = null;
        TypedQuery<Vat> query = em.createNamedQuery("Vat.findByVatid", Vat.class).setParameter("vatid", id);
        return query.getSingleResult();
    }


    public boolean updateVatToDatabase(Vat vat) {
        System.out.println("VAT=" + vat.getPercnt() + " " + vat.getIsactive() + " " + vat.getVatid());
        Query query = em.createNativeQuery("UPDATE vat SET PERCNT =" + vat.getPercnt() + ", ISACTIVE =" + vat.getIsactive() + "  WHERE VATID=" + vat.getVatid());
        query.executeUpdate();
        return true;
    }

    public List<Vat> getAllVatFromDB() {
        TypedQuery<Vat> query = em.createNamedQuery("Vat.findAll", Vat.class);
        return query.getResultList();
    }

    public int deleteVatFromDB(int id) {
        Query query = em.createNativeQuery("DELETE FROM vat WHERE VATID =" + id);
        return query.executeUpdate();
    }


    public int changeStatusVatFromDB(int status, int id) {
        Query query = em.createNativeQuery("UPDATE vat SET ISACTIVE =" + status + " WHERE VATID=" + id);
        return query.executeUpdate();
    }

    public boolean insertVatToDB(Vat vat) {

        try {
            em.persist(vat);
            em.flush();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
