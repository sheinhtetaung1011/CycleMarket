package cycleTest.cycleDemo.specs;

import cycleTest.cycleDemo.entity.City;
import cycleTest.cycleDemo.entity.City_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;
import java.util.ArrayList;
import java.util.Collection;

public class CitySpecs {
    public static Specification<City> getAllCities(String name) {
        return new Specification<City>() {
            @Override
            public Predicate toPredicate(Root<City> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                final Collection<Predicate> predicates = new ArrayList<>();
                if (!ObjectUtils.isEmpty(name)) {
                    final Predicate namePredicate = criteriaBuilder.like(root.get(City_.NAME),"%"+ name+ "%");
                    predicates.add(namePredicate);
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };

    }
}
