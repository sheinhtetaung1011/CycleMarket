package cycleTest.cycleDemo.repository;

import cycleTest.cycleDemo.entity.PackagePurchase;
import cycleTest.cycleDemo.entity.User;
import cycleTest.cycleDemo.enums.PurchaseStatus;
import cycleTest.cycleDemo.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PackagePurchaseRepository extends JpaRepository<PackagePurchase,Long> , JpaSpecificationExecutor<PackagePurchase> {

    Integer countByPurchaseStatus(PurchaseStatus requested);

    @Query(value = "select pp.* from package_purchase pp " +
            "where pp.purchase_status='ACTIVE' and pp.expire_date<=now()",nativeQuery = true)
    List<PackagePurchase> getAllExpirePackages();


    Optional<PackagePurchase> findByUserIdAndPurchaseStatus(Long id, PurchaseStatus purchaseStatus);

    @Query(value = "select pp from PackagePurchase pp where pp.purchaseStatus=?1 or ?1 is null ")
    List<PackagePurchase> findByPurchaseStatus(PurchaseStatus purchaseStatus);
}
