package cycleTest.cycleDemo.repository;

import cycleTest.cycleDemo.entity.Township;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TownshipRepository extends JpaRepository<Township,Long> {

    Optional<Township> findByName(String name);

    Page<Township> findByCityId(Long cityId, Pageable pageable);

    List<Township>  findByCityId(Long cityId);

    Optional<Township> findByCityIdAndName(Long cityId,String name);
}
