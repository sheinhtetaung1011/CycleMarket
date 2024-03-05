package cycleTest.cycleDemo.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(UserBrand.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class UserBrand_ extends cycleTest.cycleDemo.entity.BaseEntity_ {

	
	/**
	 * @see cycleTest.cycleDemo.entity.UserBrand#name
	 **/
	public static volatile SingularAttribute<UserBrand, String> name;
	
	/**
	 * @see cycleTest.cycleDemo.entity.UserBrand
	 **/
	public static volatile EntityType<UserBrand> class_;
	
	/**
	 * @see cycleTest.cycleDemo.entity.UserBrand#user
	 **/
	public static volatile SingularAttribute<UserBrand, User> user;

	public static final String NAME = "name";
	public static final String USER = "user";

}

