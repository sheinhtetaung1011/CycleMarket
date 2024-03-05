package cycleTest.cycleDemo.entity;

import cycleTest.cycleDemo.enums.Status;
import cycleTest.cycleDemo.enums.UserRole;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(User.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class User_ extends cycleTest.cycleDemo.entity.BaseEntity_ {

	
	/**
	 * @see cycleTest.cycleDemo.entity.User#password
	 **/
	public static volatile SingularAttribute<User, String> password;
	
	/**
	 * @see cycleTest.cycleDemo.entity.User#city
	 **/
	public static volatile SingularAttribute<User, City> city;
	
	/**
	 * @see cycleTest.cycleDemo.entity.User#name
	 **/
	public static volatile SingularAttribute<User, String> name;
	
	/**
	 * @see cycleTest.cycleDemo.entity.User#userRole
	 **/
	public static volatile SingularAttribute<User, UserRole> userRole;
	
	/**
	 * @see cycleTest.cycleDemo.entity.User
	 **/
	public static volatile EntityType<User> class_;
	
	/**
	 * @see cycleTest.cycleDemo.entity.User#phoneNo
	 **/
	public static volatile SingularAttribute<User, String> phoneNo;
	
	/**
	 * @see cycleTest.cycleDemo.entity.User#township
	 **/
	public static volatile SingularAttribute<User, Township> township;
	
	/**
	 * @see cycleTest.cycleDemo.entity.User#status
	 **/
	public static volatile SingularAttribute<User, Status> status;

	public static final String PASSWORD = "password";
	public static final String CITY = "city";
	public static final String NAME = "name";
	public static final String USER_ROLE = "userRole";
	public static final String PHONE_NO = "phoneNo";
	public static final String TOWNSHIP = "township";
	public static final String STATUS = "status";

}

