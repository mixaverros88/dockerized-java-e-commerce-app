package entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Product.class)
public abstract class Product_ {

	public static volatile SingularAttribute<Product, Short> isvisible;
	public static volatile SingularAttribute<Product, Integer> productid;
	public static volatile SingularAttribute<Product, Float> buyprice;
	public static volatile SingularAttribute<Product, Produnit> produnitid;
	public static volatile SingularAttribute<Product, Integer> isactive;
	public static volatile CollectionAttribute<Product, Wishlist> wishlistCollection;
	public static volatile SingularAttribute<Product, Integer> sysuser;
	public static volatile SingularAttribute<Product, Date> upddate;
	public static volatile SingularAttribute<Product, Photos> photosid;
	public static volatile SingularAttribute<Product, Prodcategory> prodcategoryid;
	public static volatile SingularAttribute<Product, Custvend> vendor;
	public static volatile SingularAttribute<Product, Float> sellprice;
	public static volatile SingularAttribute<Product, Integer> qty;
	public static volatile SingularAttribute<Product, String> name;
	public static volatile SingularAttribute<Product, String> slugname;
	public static volatile SingularAttribute<Product, Date> insdate;
	public static volatile SingularAttribute<Product, String> remarks;
	public static volatile CollectionAttribute<Product, Orderlines> orderlinesCollection;

}

