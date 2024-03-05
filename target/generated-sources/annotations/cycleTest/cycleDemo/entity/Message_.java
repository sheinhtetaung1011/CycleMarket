package cycleTest.cycleDemo.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Message.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Message_ extends cycleTest.cycleDemo.entity.BaseEntity_ {

	
	/**
	 * @see cycleTest.cycleDemo.entity.Message#sendUserId
	 **/
	public static volatile SingularAttribute<Message, Long> sendUserId;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Message#receivedUserId
	 **/
	public static volatile SingularAttribute<Message, Long> receivedUserId;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Message#isRead
	 **/
	public static volatile SingularAttribute<Message, Boolean> isRead;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Message#message
	 **/
	public static volatile SingularAttribute<Message, String> message;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Message
	 **/
	public static volatile EntityType<Message> class_;

	public static final String SEND_USER_ID = "sendUserId";
	public static final String RECEIVED_USER_ID = "receivedUserId";
	public static final String IS_READ = "isRead";
	public static final String MESSAGE = "message";

}

