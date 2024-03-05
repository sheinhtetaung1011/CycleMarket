package cycleTest.cycleDemo.repository;

import cycleTest.cycleDemo.entity.Blog;
import cycleTest.cycleDemo.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BlogRepository extends JpaRepository<Blog,Long> {


    @Query(value = "select b.name,b.cover_image_url,b.user_id,b.status " +
            "from blog b where b.user_id=?1 or ?1 is null " +
            "and b.status=?2 or ?2 is null " +
            "order by b.user_id"
            ,nativeQuery = true)
    Page<Object[]> findAllWithoutText(Long userId, Status status, Pageable pageable);
}
