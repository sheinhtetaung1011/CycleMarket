package cycleTest.cycleDemo.repository;

import cycleTest.cycleDemo.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> , JpaSpecificationExecutor<City> {

    Optional<City> findByName (String name);
}
