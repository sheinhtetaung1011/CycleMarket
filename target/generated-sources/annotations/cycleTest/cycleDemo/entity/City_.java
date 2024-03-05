package cycleTest.cycleDemo.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(City.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class City_ extends cycleTest.cycleDemo.entity.BaseEntity_ {

	
	/**
	 * @see cycleTest.cycleDemo.entity.City#name
	 **/
	public static volatile SingularAttribute<City, String> name;
	
	/**
	 * @see cycleTest.cycleDemo.entity.City
	 **/
	public static volatile EntityType<City> class_;

	public static final String NAME = "name";

}

