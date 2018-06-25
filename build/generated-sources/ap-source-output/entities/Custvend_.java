package entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Custvend.class)
public abstract class Custvend_ {

	public static volatile SingularAttribute<Custvend, String> city;
	public static volatile SingularAttribute<Custvend, Roles> roleid;
	public static volatile SingularAttribute<Custvend, Integer> isactive;
	public static volatile CollectionAttribute<Custvend, Orders> ordersCollection;
	public static volatile SingularAttribute<Custvend, Date> upddate;
	public static volatile SingularAttribute<Custvend, String> lname;
	public static volatile SingularAttribute<Custvend, Float> ballance;
	public static volatile SingularAttribute<Custvend, Integer> custvendid;
	public static volatile SingularAttribute<Custvend, Float> credits;
	public static volatile CollectionAttribute<Custvend, Ballance> ballanceCollection;
	public static volatile SingularAttribute<Custvend, String> email;
	public static volatile SingularAttribute<Custvend, String> jobname;
	public static volatile CollectionAttribute<Custvend, Orderlines> orderlinesCollection;
	public static volatile SingularAttribute<Custvend, String> zip;
	public static volatile SingularAttribute<Custvend, String> fname;
	public static volatile SingularAttribute<Custvend, String> address;
	public static volatile CollectionAttribute<Custvend, Product> productCollection;
	public static volatile CollectionAttribute<Custvend, Wishlist> wishlistCollection;
	public static volatile SingularAttribute<Custvend, String> afm;
	public static volatile SingularAttribute<Custvend, Short> sysuser;
	public static volatile SingularAttribute<Custvend, String> passwd;
	public static volatile SingularAttribute<Custvend, String> phone;
	public static volatile SingularAttribute<Custvend, String> district;
	public static volatile SingularAttribute<Custvend, Date> insdate;
	public static volatile SingularAttribute<Custvend, String> remarks;
	public static volatile SingularAttribute<Custvend, Integer> register;
	public static volatile SingularAttribute<Custvend, String> username;

}

