package cycleTest.cycleDemo.entity;

import cycleTest.cycleDemo.enums.Status;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Blog.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Blog_ extends cycleTest.cycleDemo.entity.BaseEntity_ {

	
	/**
	 * @see cycleTest.cycleDemo.entity.Blog#coverImageUrl
	 **/
	public static volatile SingularAttribute<Blog, String> coverImageUrl;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Blog#name
	 **/
	public static volatile SingularAttribute<Blog, String> name;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Blog#text
	 **/
	public static volatile SingularAttribute<Blog, String> text;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Blog#previewText
	 **/
	public static volatile SingularAttribute<Blog, String> previewText;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Blog
	 **/
	public static volatile EntityType<Blog> class_;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Blog#user
	 **/
	public static volatile SingularAttribute<Blog, User> user;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Blog#status
	 **/
	public static volatile SingularAttribute<Blog, Status> status;

	public static final String COVER_IMAGE_URL = "coverImageUrl";
	public static final String NAME = "name";
	public static final String TEXT = "text";
	public static final String PREVIEW_TEXT = "previewText";
	public static final String USER = "user";
	public static final String STATUS = "status";

}

