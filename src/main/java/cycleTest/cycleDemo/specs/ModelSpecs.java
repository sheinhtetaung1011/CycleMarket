package cycleTest.cycleDemo.specs;

import cycleTest.cycleDemo.entity.Model;
import cycleTest.cycleDemo.entity.Model_;
import cycleTest.cycleDemo.entity.UserBrand;
import cycleTest.cycleDemo.entity.UserBrand_;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class ModelSpecs {

    public static Specification<Model> getAllModels(String name,Long userBrandId){
        return new Specification<Model>() {
            @Override
            public Predicate toPredicate(Root<Model> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                Collection<Predicate> predicates=new ArrayList<>();

                if(!Objects.isNull(name)){
                    Predicate namePredicate= criteriaBuilder.like(root.get(Model_.NAME),"%"+name+"%");
                    predicates.add(namePredicate);
                }

                if (!Objects.isNull(userBrandId)){
                    Join<Model, UserBrand> modelJoin=root.join(Model_.USER_BRAND);
                    Predicate userBrandPredicate= criteriaBuilder.equal(modelJoin.get(UserBrand_.ID),userBrandId);
                    predicates.add(userBrandPredicate);
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])) ;
            }
        };
    }
}
