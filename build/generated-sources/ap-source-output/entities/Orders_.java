package entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Orders.class)
public abstract class Orders_ {

	public static volatile SingularAttribute<Orders, Integer> orderid;
	public static volatile SingularAttribute<Orders, Short> iscancel;
	public static volatile SingularAttribute<Orders, Roles> roleid;
	public static volatile SingularAttribute<Orders, Integer> vatid;
	public static volatile SingularAttribute<Orders, Integer> sysuser;
	public static volatile SingularAttribute<Orders, Date> orderdate;
	public static volatile SingularAttribute<Orders, Short> isapprv;
	public static volatile SingularAttribute<Orders, Date> upddate;
	public static volatile SingularAttribute<Orders, Custvend> custvendid;
	public static volatile SingularAttribute<Orders, Date> insdate;
	public static volatile SingularAttribute<Orders, String> invoice;
	public static volatile SingularAttribute<Orders, Float> sumamnt;
	public static volatile SingularAttribute<Orders, Short> ispayed;
	public static volatile SingularAttribute<Orders, String> remarks;
	public static volatile CollectionAttribute<Orders, Orderlines> orderlinesCollection;

}

