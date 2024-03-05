package cycleTest.cycleDemo.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Shop.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Shop_ extends cycleTest.cycleDemo.entity.BaseEntity_ {

	
	/**
	 * @see cycleTest.cycleDemo.entity.Shop#address
	 **/
	public static volatile SingularAttribute<Shop, String> address;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Shop#showInHome
	 **/
	public static volatile SingularAttribute<Shop, Boolean> showInHome;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Shop#city
	 **/
	public static volatile SingularAttribute<Shop, City> city;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Shop#imageUrl
	 **/
	public static volatile SingularAttribute<Shop, String> imageUrl;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Shop#name
	 **/
	public static volatile SingularAttribute<Shop, String> name;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Shop#description
	 **/
	public static volatile SingularAttribute<Shop, String> description;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Shop
	 **/
	public static volatile EntityType<Shop> class_;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Shop#user
	 **/
	public static volatile SingularAttribute<Shop, User> user;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Shop#township
	 **/
	public static volatile SingularAttribute<Shop, Township> township;

	public static final String ADDRESS = "address";
	public static final String SHOW_IN_HOME = "showInHome";
	public static final String CITY = "city";
	public static final String IMAGE_URL = "imageUrl";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String USER = "user";
	public static final String TOWNSHIP = "township";

}

