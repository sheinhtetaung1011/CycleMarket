package cycleTest.cycleDemo.entity;

import cycleTest.cycleDemo.enums.PurchaseStatus;
import cycleTest.cycleDemo.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "package_purchase")
public class PackagePurchase extends BaseEntity{

    @Column(name = "package_id",nullable = false)
    private Long packageId;

    @Column(name = "package_name",nullable = false)
    private String packageName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "package_duration",nullable = false)
    private Integer packageDuration;

    @Column(name = "package_amount",nullable = false)
    private Double packageAmount;

    @Column(name = "expire_date")
    private Date expireDate;

    @Column(name = "confirm_date")
    private Date confirmDate;

    @Column(name = "transaction_no",nullable = false)
    private String transactionNo;

    @Column(name = "purchase_status")
    @Enumerated(EnumType.STRING)
    private PurchaseStatus purchaseStatus;
}
