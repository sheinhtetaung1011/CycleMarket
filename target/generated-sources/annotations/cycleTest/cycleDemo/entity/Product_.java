package cycleTest.cycleDemo.entity;

import cycleTest.cycleDemo.enums.CycleType;
import cycleTest.cycleDemo.enums.PartType;
import cycleTest.cycleDemo.enums.ProductSellStatus;
import cycleTest.cycleDemo.enums.ProductStatus;
import cycleTest.cycleDemo.enums.ProductType;
import cycleTest.cycleDemo.enums.PurchaseStatus;
import cycleTest.cycleDemo.enums.Status;
import cycleTest.cycleDemo.enums.UploadType;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Product.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Product_ extends cycleTest.cycleDemo.entity.BaseEntity_ {

	
	/**
	 * @see cycleTest.cycleDemo.entity.Product#year
	 **/
	public static volatile SingularAttribute<Product, String> year;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Product#city
	 **/
	public static volatile SingularAttribute<Product, City> city;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Product#productStatus
	 **/
	public static volatile SingularAttribute<Product, ProductStatus> productStatus;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Product#description
	 **/
	public static volatile SingularAttribute<Product, String> description;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Product#enginePower
	 **/
	public static volatile SingularAttribute<Product, String> enginePower;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Product#cycleType
	 **/
	public static volatile SingularAttribute<Product, CycleType> cycleType;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Product#productSellStatus
	 **/
	public static volatile SingularAttribute<Product, ProductSellStatus> productSellStatus;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Product#license
	 **/
	public static volatile SingularAttribute<Product, String> license;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Product#videoUrl
	 **/
	public static volatile SingularAttribute<Product, String> videoUrl;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Product#showInHome
	 **/
	public static volatile SingularAttribute<Product, Boolean> showInHome;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Product#price
	 **/
	public static volatile SingularAttribute<Product, Double> price;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Product#imageUrls
	 **/
	public static volatile SingularAttribute<Product, String> imageUrls;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Product#name
	 **/
	public static volatile SingularAttribute<Product, String> name;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Product#uploadType
	 **/
	public static volatile SingularAttribute<Product, UploadType> uploadType;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Product#purchaseStatus
	 **/
	public static volatile SingularAttribute<Product, PurchaseStatus> purchaseStatus;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Product#model
	 **/
	public static volatile SingularAttribute<Product, Model> model;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Product
	 **/
	public static volatile EntityType<Product> class_;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Product#user
	 **/
	public static volatile SingularAttribute<Product, User> user;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Product#partType
	 **/
	public static volatile SingularAttribute<Product, PartType> partType;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Product#productType
	 **/
	public static volatile SingularAttribute<Product, ProductType> productType;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Product#township
	 **/
	public static volatile SingularAttribute<Product, Township> township;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Product#userBrand
	 **/
	public static volatile SingularAttribute<Product, UserBrand> userBrand;
	
	/**
	 * @see cycleTest.cycleDemo.entity.Product#status
	 **/
	public static volatile SingularAttribute<Product, Status> status;

	public static final String YEAR = "year";
	public static final String CITY = "city";
	public static final String PRODUCT_STATUS = "productStatus";
	public static final String DESCRIPTION = "description";
	public static final String ENGINE_POWER = "enginePower";
	public static final String CYCLE_TYPE = "cycleType";
	public static final String PRODUCT_SELL_STATUS = "productSellStatus";
	public static final String LICENSE = "license";
	public static final String VIDEO_URL = "videoUrl";
	public static final String SHOW_IN_HOME = "showInHome";
	public static final String PRICE = "price";
	public static final String IMAGE_URLS = "imageUrls";
	public static final String NAME = "name";
	public static final String UPLOAD_TYPE = "uploadType";
	public static final String PURCHASE_STATUS = "purchaseStatus";
	public static final String MODEL = "model";
	public static final String USER = "user";
	public static final String PART_TYPE = "partType";
	public static final String PRODUCT_TYPE = "productType";
	public static final String TOWNSHIP = "township";
	public static final String USER_BRAND = "userBrand";
	public static final String STATUS = "status";

}

