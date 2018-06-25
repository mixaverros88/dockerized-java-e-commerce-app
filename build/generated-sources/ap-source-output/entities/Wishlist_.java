package entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Wishlist.class)
public abstract class Wishlist_ {

	public static volatile SingularAttribute<Wishlist, Integer> inform;
	public static volatile SingularAttribute<Wishlist, Product> productid;
	public static volatile SingularAttribute<Wishlist, Custvend> custvendid;
	public static volatile SingularAttribute<Wishlist, Float> qty;
	public static volatile SingularAttribute<Wishlist, Date> insdate;
	public static volatile SingularAttribute<Wishlist, Integer> wishlistid;
	public static volatile SingularAttribute<Wishlist, Date> informdate;

}

