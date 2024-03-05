package cycleTest.cycleDemo.specs;

import cycleTest.cycleDemo.entity.User;
import cycleTest.cycleDemo.entity.User_;
import cycleTest.cycleDemo.enums.Status;
import cycleTest.cycleDemo.enums.UserRole;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class UserSpecs {

    public static Specification<User> getAllUsers(String name, String phoneNo, UserRole userRole, Status status){
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                final Collection<Predicate> predicates=new ArrayList<>();

                if(!Objects.isNull(name)){
                    Predicate namePredicate= criteriaBuilder.like(root.get(User_.NAME),"%"+name+"%");
                    predicates.add(namePredicate);
                }

                if (!Objects.isNull(phoneNo)){
                    Predicate phonePredicate=criteriaBuilder.equal(root.get(User_.PHONE_NO),phoneNo);
                    predicates.add(phonePredicate);
                }

                if (!Objects.isNull(userRole)) {
                    Predicate userRolePredicate=criteriaBuilder.equal(root.get(User_.USER_ROLE),userRole);
                    predicates.add(userRolePredicate);
                }

                if (!Objects.isNull(status)) {
                    Predicate statusPredicate=criteriaBuilder.equal(root.get(User_.STATUS),status);
                    predicates.add(statusPredicate);
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
