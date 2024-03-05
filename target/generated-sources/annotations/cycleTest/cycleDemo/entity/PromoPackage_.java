package cycleTest.cycleDemo.entity;

import cycleTest.cycleDemo.enums.Status;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(PromoPackage.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class PromoPackage_ extends cycleTest.cycleDemo.entity.BaseEntity_ {

	
	/**
	 * @see cycleTest.cycleDemo.entity.PromoPackage#duration
	 **/
	public static volatile SingularAttribute<PromoPackage, Integer> duration;
	
	/**
	 * @see cycleTest.cycleDemo.entity.PromoPackage#amount
	 **/
	public static volatile SingularAttribute<PromoPackage, Double> amount;
	
	/**
	 * @see cycleTest.cycleDemo.entity.PromoPackage#name
	 **/
	public static volatile SingularAttribute<PromoPackage, String> name;
	
	/**
	 * @see cycleTest.cycleDemo.entity.PromoPackage
	 **/
	public static volatile EntityType<PromoPackage> class_;
	
	/**
	 * @see cycleTest.cycleDemo.entity.PromoPackage#status
	 **/
	public static volatile SingularAttribute<PromoPackage, Status> status;

	public static final String DURATION = "duration";
	public static final String AMOUNT = "amount";
	public static final String NAME = "name";
	public static final String STATUS = "status";

}

