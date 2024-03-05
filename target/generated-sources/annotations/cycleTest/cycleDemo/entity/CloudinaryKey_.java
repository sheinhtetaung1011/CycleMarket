package cycleTest.cycleDemo.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(CloudinaryKey.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class CloudinaryKey_ extends cycleTest.cycleDemo.entity.BaseEntity_ {

	
	/**
	 * @see cycleTest.cycleDemo.entity.CloudinaryKey#cloudinaryUrl
	 **/
	public static volatile SingularAttribute<CloudinaryKey, String> cloudinaryUrl;
	
	/**
	 * @see cycleTest.cycleDemo.entity.CloudinaryKey
	 **/
	public static volatile EntityType<CloudinaryKey> class_;

	public static final String CLOUDINARY_URL = "cloudinaryUrl";

}

