package cycleTest.cycleDemo.repository;

import cycleTest.cycleDemo.entity.User;
import cycleTest.cycleDemo.entity.UserBrand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface UserBrandRepository extends JpaRepository<UserBrand,Long>, JpaSpecificationExecutor<UserBrand> {

    Page<UserBrand> findByName(String name, Pageable pageable);

    Optional<UserBrand> findByName(String name);

}
