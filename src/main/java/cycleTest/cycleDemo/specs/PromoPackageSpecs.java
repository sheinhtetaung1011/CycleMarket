package cycleTest.cycleDemo.specs;

import cycleTest.cycleDemo.entity.PromoPackage;
import cycleTest.cycleDemo.entity.PromoPackage_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class PromoPackageSpecs {

    public static Specification<PromoPackage> getAllPromoPackages(String name){
        return new Specification<PromoPackage>() {
            @Override
            public Predicate toPredicate(Root<PromoPackage> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                Collection<Predicate> predicates=new ArrayList<>();
                if (!Objects.isNull(name)) {
                    Predicate namePredicate=criteriaBuilder.like(root.get(PromoPackage_.NAME),"%"+name+"%");
                    predicates.add(namePredicate);
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
