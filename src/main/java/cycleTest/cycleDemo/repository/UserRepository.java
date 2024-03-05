package cycleTest.cycleDemo.repository;

import cycleTest.cycleDemo.entity.User;
import cycleTest.cycleDemo.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {

    Optional<User> findByName(String name);

    Optional<User> findById (Long id);

    @Query(value = "select u from User u where u.userRole=?1")
    List<Long> findByUserRole(UserRole userRole);



}
