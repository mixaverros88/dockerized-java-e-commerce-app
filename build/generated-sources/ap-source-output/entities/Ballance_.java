package entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Ballance.class)
public abstract class Ballance_ {

	public static volatile SingularAttribute<Ballance, Date> upddate;
	public static volatile SingularAttribute<Ballance, Float> amount;
	public static volatile SingularAttribute<Ballance, Date> transactiondate;
	public static volatile SingularAttribute<Ballance, Custvend> custvendid;
	public static volatile SingularAttribute<Ballance, Integer> ballanceid;
	public static volatile SingularAttribute<Ballance, Date> insdate;
	public static volatile SingularAttribute<Ballance, Short> sysuser;

}

