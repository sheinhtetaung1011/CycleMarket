package cycleTest.cycleDemo.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Township.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Township_ extends cycleTest.cycleDemo.entity.BaseEntity_ {

	
	/**
	 * @see cycleTest.cycleDemo.entity.Township#shopList
	 **/
	public static volatile ListAttribute<Township, Shop> shopList;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Township#city
	 **/
	public static volatile SingularAttribute<Township, City> city;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Township#name
	 **/
	public static volatile SingularAttribute<Township, String> name;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Township
	 **/
	public static volatile EntityType<Township> class_;

	public static final String SHOP_LIST = "shopList";
	public static final String CITY = "city";
	public static final String NAME = "name";

}

