package cycleTest.cycleDemo.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Model.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Model_ extends cycleTest.cycleDemo.entity.BaseEntity_ {

	
	/**
	 * @see cycleTest.cycleDemo.entity.Model#name
	 **/
	public static volatile SingularAttribute<Model, String> name;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Model
	 **/
	public static volatile EntityType<Model> class_;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Model#userBrand
	 **/
	public static volatile SingularAttribute<Model, UserBrand> userBrand;

	public static final String NAME = "name";
	public static final String USER_BRAND = "userBrand";

}

