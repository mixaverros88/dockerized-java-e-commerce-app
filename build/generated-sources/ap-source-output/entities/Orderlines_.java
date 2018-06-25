package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Orderlines.class)
public abstract class Orderlines_ {

	public static volatile SingularAttribute<Orderlines, Float> linevatval;
	public static volatile SingularAttribute<Orderlines, Integer> orderlinesid;
	public static volatile SingularAttribute<Orderlines, Product> productid;
	public static volatile SingularAttribute<Orderlines, Orders> orderid;
	public static volatile SingularAttribute<Orderlines, Produnit> produnitid;
	public static volatile SingularAttribute<Orderlines, Float> price;
	public static volatile SingularAttribute<Orderlines, Float> linenetval;
	public static volatile SingularAttribute<Orderlines, Roles> roleid;
	public static volatile SingularAttribute<Orderlines, Custvend> vendor;
	public static volatile SingularAttribute<Orderlines, Float> qty;
	public static volatile SingularAttribute<Orderlines, String> remarks;
	public static volatile SingularAttribute<Orderlines, Float> linesumval;

}

