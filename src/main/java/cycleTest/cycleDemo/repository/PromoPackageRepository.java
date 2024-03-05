package cycleTest.cycleDemo.repository;

import cycleTest.cycleDemo.entity.PromoPackage;
import cycleTest.cycleDemo.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PromoPackageRepository extends JpaRepository<PromoPackage,Long>, JpaSpecificationExecutor<PromoPackage> {

    List<PromoPackage> findByStatus(Status status);
}
