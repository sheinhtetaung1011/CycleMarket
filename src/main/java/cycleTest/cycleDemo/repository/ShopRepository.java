package cycleTest.cycleDemo.repository;

import cycleTest.cycleDemo.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop,Long>,JpaSpecificationExecutor<Shop> {

    Optional<Shop> findByUserId (Long userId);


}
