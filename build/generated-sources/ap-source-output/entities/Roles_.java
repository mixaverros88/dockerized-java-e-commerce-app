package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Roles.class)
public abstract class Roles_ {

	public static volatile SingularAttribute<Roles, String> descr;
	public static volatile SingularAttribute<Roles, Integer> roleid;
	public static volatile SingularAttribute<Roles, Integer> isactive;
	public static volatile CollectionAttribute<Roles, Custvend> custvendCollection;
	public static volatile CollectionAttribute<Roles, Orders> ordersCollection;
	public static volatile CollectionAttribute<Roles, Orderlines> orderlinesCollection;

}

