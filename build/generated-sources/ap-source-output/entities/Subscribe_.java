package entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Subscribe.class)
public abstract class Subscribe_ {

	public static volatile SingularAttribute<Subscribe, Integer> subscribe;
	public static volatile SingularAttribute<Subscribe, Date> insdate;
	public static volatile SingularAttribute<Subscribe, String> email;

}

