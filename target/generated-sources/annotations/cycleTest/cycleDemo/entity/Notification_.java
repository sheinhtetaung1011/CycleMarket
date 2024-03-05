package cycleTest.cycleDemo.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Notification.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Notification_ extends cycleTest.cycleDemo.entity.BaseEntity_ {

	
	/**
	 * @see cycleTest.cycleDemo.entity.Notification#imageUrl
	 **/
	public static volatile SingularAttribute<Notification, String> imageUrl;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Notification#topic
	 **/
	public static volatile SingularAttribute<Notification, String> topic;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Notification#title
	 **/
	public static volatile SingularAttribute<Notification, String> title;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Notification#body
	 **/
	public static volatile SingularAttribute<Notification, String> body;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Notification
	 **/
	public static volatile EntityType<Notification> class_;

	public static final String IMAGE_URL = "imageUrl";
	public static final String TOPIC = "topic";
	public static final String TITLE = "title";
	public static final String BODY = "body";

}

