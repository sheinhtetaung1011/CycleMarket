package cycleTest.cycleDemo.repository;

import cycleTest.cycleDemo.entity.Product;
import cycleTest.cycleDemo.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Objects;

public interface ProductRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {

    Integer countByStatus(Status pending);

    @Query(value = "select count(*) As AllProduct" +
            ",count(if(p.product_status='REJECTED',1,null)) as RejectProduct" +
            ",count(if(p.product_status='ACCEPTED',1,null)) as AcceptProduct" +
            ",count(if(p.status='ACTIVE',1,null)) as ShowStatus " +
            "from product p " +
            "where p.user_id=?1 or ?1 is null "
            ,nativeQuery = true)
    List<Object[]> getAllProductCount(Long userId);



}
