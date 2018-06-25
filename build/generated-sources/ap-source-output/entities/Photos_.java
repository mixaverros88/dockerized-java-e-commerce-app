package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Photos.class)
public abstract class Photos_ {

	public static volatile SingularAttribute<Photos, Integer> photosid;
	public static volatile SingularAttribute<Photos, String> mIMEtype;
	public static volatile CollectionAttribute<Photos, Product> productCollection;
	public static volatile SingularAttribute<Photos, String> url;

}

