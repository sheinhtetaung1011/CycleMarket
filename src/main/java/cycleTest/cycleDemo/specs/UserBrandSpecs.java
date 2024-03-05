package cycleTest.cycleDemo.specs;

import cycleTest.cycleDemo.entity.UserBrand;
import cycleTest.cycleDemo.entity.UserBrand_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class UserBrandSpecs {

    public static Specification<UserBrand> getAllBrands(String name){
        return new Specification<UserBrand>() {
            @Override
            public Predicate toPredicate(Root<UserBrand> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                Collection<Predicate> predicates=new ArrayList<>();

                if (!Objects.isNull(name)) {
                    final Predicate namePredicate=criteriaBuilder.like(root.get(UserBrand_.NAME),"%"+name+"%");
                    predicates.add(namePredicate);
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
