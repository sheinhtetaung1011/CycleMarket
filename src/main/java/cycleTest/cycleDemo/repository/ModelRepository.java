package cycleTest.cycleDemo.repository;

import cycleTest.cycleDemo.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model,Long>, JpaSpecificationExecutor<Model> {

    List<Model> findByUserBrandId(Long userBrandId);
}
