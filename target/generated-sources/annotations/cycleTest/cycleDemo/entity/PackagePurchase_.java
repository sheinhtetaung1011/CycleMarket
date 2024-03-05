package cycleTest.cycleDemo.entity;

import cycleTest.cycleDemo.enums.PurchaseStatus;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@StaticMetamodel(PackagePurchase.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class PackagePurchase_ extends cycleTest.cycleDemo.entity.BaseEntity_ {

	
	/**
	 * @see cycleTest.cycleDemo.entity.PackagePurchase#confirmDate
	 **/
	public static volatile SingularAttribute<PackagePurchase, Date> confirmDate;
	
	/**
	 * @see cycleTest.cycleDemo.entity.PackagePurchase#packageId
	 **/
	public static volatile SingularAttribute<PackagePurchase, Long> packageId;
	
	/**
	 * @see cycleTest.cycleDemo.entity.PackagePurchase#transactionNo
	 **/
	public static volatile SingularAttribute<PackagePurchase, String> transactionNo;
	
	/**
	 * @see cycleTest.cycleDemo.entity.PackagePurchase#purchaseStatus
	 **/
	public static volatile SingularAttribute<PackagePurchase, PurchaseStatus> purchaseStatus;
	
	/**
	 * @see cycleTest.cycleDemo.entity.PackagePurchase#expireDate
	 **/
	public static volatile SingularAttribute<PackagePurchase, Date> expireDate;
	
	/**
	 * @see cycleTest.cycleDemo.entity.PackagePurchase#packageName
	 **/
	public static volatile SingularAttribute<PackagePurchase, String> packageName;
	
	/**
	 * @see cycleTest.cycleDemo.entity.PackagePurchase#packageDuration
	 **/
	public static volatile SingularAttribute<PackagePurchase, Integer> packageDuration;
	
	/**
	 * @see cycleTest.cycleDemo.entity.PackagePurchase
	 **/
	public static volatile EntityType<PackagePurchase> class_;
	
	/**
	 * @see cycleTest.cycleDemo.entity.PackagePurchase#user
	 **/
	public static volatile SingularAttribute<PackagePurchase, User> user;
	
	/**
	 * @see cycleTest.cycleDemo.entity.PackagePurchase#packageAmount
	 **/
	public static volatile SingularAttribute<PackagePurchase, Double> packageAmount;

	public static final String CONFIRM_DATE = "confirmDate";
	public static final String PACKAGE_ID = "packageId";
	public static final String TRANSACTION_NO = "transactionNo";
	public static final String PURCHASE_STATUS = "purchaseStatus";
	public static final String EXPIRE_DATE = "expireDate";
	public static final String PACKAGE_NAME = "packageName";
	public static final String PACKAGE_DURATION = "packageDuration";
	public static final String USER = "user";
	public static final String PACKAGE_AMOUNT = "packageAmount";

}

