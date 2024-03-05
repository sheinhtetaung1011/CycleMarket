package cycleTest.cycleDemo.specs;

import cycleTest.cycleDemo.entity.PackagePurchase;
import cycleTest.cycleDemo.entity.PackagePurchase_;
import cycleTest.cycleDemo.entity.User;
import cycleTest.cycleDemo.entity.User_;
import cycleTest.cycleDemo.enums.PurchaseStatus;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class PackagePurchaseSpecs {

    public static Specification<PackagePurchase> getAllPackages(Long userId
            , PurchaseStatus purchaseStatus
            , String packageName) {

        return new Specification<PackagePurchase>() {
            @Override
            public Predicate toPredicate(Root<PackagePurchase> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                Collection<Predicate> predicates=new ArrayList<>();

                if (!Objects.isNull(userId)) {
                    Join<PackagePurchase, User> userJoin=root.join(PackagePurchase_.USER);
                    Predicate userPredicate=criteriaBuilder.equal(userJoin.get(User_.ID),userId);
                    predicates.add(userPredicate);
                }
                if (!Objects.isNull(packageName)) {
                    Predicate packageNamePredicate= criteriaBuilder.like(root.get(PackagePurchase_.PACKAGE_NAME), "%"+packageName+"%");
                    predicates.add(packageNamePredicate);
                }
                if (!Objects.isNull(purchaseStatus)) {
                    Predicate statusPredicate= criteriaBuilder.equal(root.get(PackagePurchase_.PURCHASE_STATUS), purchaseStatus);
                    predicates.add(statusPredicate);
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };

    }
}
