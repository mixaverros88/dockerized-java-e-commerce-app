package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Prodcategory.class)
public abstract class Prodcategory_ {

	public static volatile CollectionAttribute<Prodcategory, Product> productCollection;
	public static volatile SingularAttribute<Prodcategory, Integer> prodcategoryid;
	public static volatile SingularAttribute<Prodcategory, Integer> isactive;
	public static volatile SingularAttribute<Prodcategory, String> name;
	public static volatile SingularAttribute<Prodcategory, String> slugname;

}

