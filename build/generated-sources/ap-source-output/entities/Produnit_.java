package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Produnit.class)
public abstract class Produnit_ {

	public static volatile CollectionAttribute<Produnit, Product> productCollection;
	public static volatile SingularAttribute<Produnit, Integer> produnitid;
	public static volatile SingularAttribute<Produnit, Integer> isactive;
	public static volatile SingularAttribute<Produnit, String> name;
	public static volatile CollectionAttribute<Produnit, Orderlines> orderlinesCollection;

}

