package sessionsBeans;

import entities.Custvend;
import entities.Product;
import entities.Wishlist;
import helpers.MailSender;
import org.apache.log4j.Logger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class WhishListFacade {

    final static Logger logger = Logger.getLogger(WhishListFacade.class);

    @PersistenceContext(unitName = "PrimeFacesPU")
    private EntityManager em;

    public void chechIfTheProductIsInWhishList(Product product, float qty) {

        TypedQuery<Wishlist> query = em.createQuery("SELECT o FROM Wishlist o WHERE o.productid = :productid AND o.qty <= :qty AND o.inform = :inform", Wishlist.class)
                .setParameter("productid", product)
                .setParameter("qty", qty)
                .setParameter("inform", 0);

        List<Wishlist> wishlist = query.getResultList();

        for (Wishlist whi : wishlist) {
            MailSender.send(whi.getCustvendid().getEmail(), "ezikos - Ενημέρωση Διαθεσιμότητας", "Το προϊόν:" + whi.getProductid().getName() + " είναι διαθέσιμο. <br/> Πατηστε <a href='/java-e-commerce/web/proion.xhtml?id=" + whi.getProductid().getProductid() + "'>εδώ</a> για να το αγοράσεται");
            updateWhishListInform(whi);
        }
    }

    public List<Wishlist> getAllWishlistBySyuserFromDB(Custvend custvend) {
        TypedQuery<Wishlist> query = em.createQuery("SELECT p FROM Wishlist p WHERE p.custvendid = :custvendid", Wishlist.class).setParameter("custvendid", custvend);
        return query.getResultList();
    }

    public void updateWhishListInform(Wishlist wishlist) {
        if ( wishlist != null){
            wishlist.setInform(1);
            em.merge(wishlist);
        }
    }

    public boolean insertWhiShListToDB(Wishlist wishlist) {
        try {
            em.persist(wishlist);
            em.flush();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
