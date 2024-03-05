package cycleTest.cycleDemo.entity;

import cycleTest.cycleDemo.enums.*;
import jakarta.persistence.*;
import lombok.Data;

import java.util.EnumSet;

@Entity
@Data
@Table(name = "product")
public class Product extends  BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "cycle_type")
    @Enumerated(EnumType.STRING)
    private CycleType cycleType;

    @Column(name = "part_type")
    @Enumerated(EnumType.STRING)
    private PartType partType;

    @Column(name = "product_sell_status")
    @Enumerated(EnumType.STRING)
    private ProductSellStatus productSellStatus;

    @Column(name = "product_status")
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    @Column(name = "product_type")
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @Column(name = "purchase_status")
    @Enumerated(EnumType.STRING)
    private PurchaseStatus purchaseStatus;

    @Column(name = "upload_type")
    @Enumerated(EnumType.STRING)
    private UploadType uploadType;

    @Column(name = "image_urls")
    private String imageUrls;

    @Column(name = "video_url")
    private String videoUrl;

    @ManyToOne
    @JoinColumn(name = "user_brand_id")
    private UserBrand userBrand;

    @Column(name = "year")
    private String year;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    @Column(name = "engine_power")
    private String enginePower;

    @Column(name = "license")
    private String license;

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "township_id")
    private Township township;

    @Column(name = "description",length = 500)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "show_in_home",columnDefinition = "bit(1) default 0")
    private Boolean showInHome;








}
