package cycleTest.cycleDemo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import cycleTest.cycleDemo.entity.PackagePurchase;
import cycleTest.cycleDemo.enums.PurchaseStatus;
import cycleTest.cycleDemo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class PackagePurchaseDTO extends CommonDTO {

    private Long packageId;
    private String packageName;
    private Long userId;
    private String userName;
    private Integer packageDuration;
    private Date expireDate;
    private Date confimDate;
    private String transactionNo;
    private PurchaseStatus purchaseStatus;

    public PackagePurchaseDTO(PackagePurchase packagePurchase) {

        super(packagePurchase);
        this.packageId = packagePurchase.getPackageId();
        this.packageName = packagePurchase.getPackageName();
        this.userId = packagePurchase.getUser() == null ? null : packagePurchase.getUser().getId();
        this.userName = packagePurchase.getUser() == null ? null : packagePurchase.getUser().getName();
        this.packageDuration = packagePurchase.getPackageDuration();
        this.expireDate = packagePurchase.getExpireDate() == null ? null : packagePurchase.getExpireDate();
        this.confimDate = packagePurchase.getConfirmDate() == null ? null : packagePurchase.getConfirmDate();
        this.transactionNo = packagePurchase.getTransactionNo();
        this.purchaseStatus = packagePurchase.getPurchaseStatus();
    }
}
