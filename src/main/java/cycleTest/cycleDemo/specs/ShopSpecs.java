package cycleTest.cycleDemo.specs;

import cycleTest.cycleDemo.entity.Shop;
import cycleTest.cycleDemo.entity.Shop_;
import cycleTest.cycleDemo.entity.User;
import cycleTest.cycleDemo.entity.User_;
import jakarta.persistence.criteria.*;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.util.Predicates;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class ShopSpecs {

    public static Specification<Shop> getAllShops(String name, Long userId, Boolean showInHome) {
        return new Specification<Shop>() {
            @Override
            public Predicate toPredicate(Root<Shop> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                final Collection<Predicate> predicates = new ArrayList<>();

                if (!Objects.isNull(name)) {
                    final Predicate namePredicate = criteriaBuilder.like(root.get(Shop_.NAME), "%" + name + "%");
                    predicates.add(namePredicate);
                }

                if (!Objects.isNull(userId)) {
                    Join<Shop, User> userJoin = root.join(Shop_.USER);
                    final Predicate userPredicate = criteriaBuilder.equal(userJoin.get(User_.ID), userId);
                    predicates.add(userPredicate);
                }

                if (!Objects.isNull(showInHome)) {
                    if (showInHome.equals(Boolean.TRUE)) {
                        final Predicate showInHomePredicate = criteriaBuilder.isTrue(root.get(Shop_.showInHome));
                        predicates.add(showInHomePredicate);
                    } else {
                        final Predicate showInHomePredicate = criteriaBuilder.isFalse(root.get(Shop_.showInHome));
                        predicates.add(showInHomePredicate);
                    }
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
