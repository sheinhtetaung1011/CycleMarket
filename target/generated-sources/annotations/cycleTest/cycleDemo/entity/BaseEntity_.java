package cycleTest.cycleDemo.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.MappedSuperclassType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.Instant;

@StaticMetamodel(BaseEntity.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class BaseEntity_ {

	
	/**
	 * @see cycleTest.cycleDemo.entity.BaseEntity#createdDate
	 **/
	public static volatile SingularAttribute<BaseEntity, Instant> createdDate;
	
	/**
	 * @see cycleTest.cycleDemo.entity.BaseEntity#id
	 **/
	public static volatile SingularAttribute<BaseEntity, Long> id;
	
	/**
	 * @see cycleTest.cycleDemo.entity.BaseEntity#updatedDate
	 **/
	public static volatile SingularAttribute<BaseEntity, Instant> updatedDate;
	
	/**
	 * @see cycleTest.cycleDemo.entity.BaseEntity
	 **/
	public static volatile MappedSuperclassType<BaseEntity> class_;

	public static final String CREATED_DATE = "createdDate";
	public static final String ID = "id";
	public static final String UPDATED_DATE = "updatedDate";

}

