package cycleTest.cycleDemo.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@StaticMetamodel(RefreshToken.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class RefreshToken_ {

	
	/**
	 * @see cycleTest.cycleDemo.entity.RefreshToken#expireDate
	 **/
	public static volatile SingularAttribute<RefreshToken, Date> expireDate;
	
	/**
	 * @see cycleTest.cycleDemo.entity.RefreshToken#id
	 **/
	public static volatile SingularAttribute<RefreshToken, Long> id;
	
	/**
	 * @see cycleTest.cycleDemo.entity.RefreshToken
	 **/
	public static volatile EntityType<RefreshToken> class_;
	
	/**
	 * @see cycleTest.cycleDemo.entity.RefreshToken#user
	 **/
	public static volatile SingularAttribute<RefreshToken, User> user;
	
	/**
	 * @see cycleTest.cycleDemo.entity.RefreshToken#token
	 **/
	public static volatile SingularAttribute<RefreshToken, String> token;

	public static final String EXPIRE_DATE = "expireDate";
	public static final String ID = "id";
	public static final String USER = "user";
	public static final String TOKEN = "token";

}

