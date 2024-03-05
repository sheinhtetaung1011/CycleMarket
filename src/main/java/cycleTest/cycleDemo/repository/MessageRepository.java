package cycleTest.cycleDemo.repository;

import cycleTest.cycleDemo.entity.Message;
import cycleTest.cycleDemo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(value = "select m from Message m " +
            "where m.sendUserId=:userId " +
            "or m.receivedUserId=:userId " +
            "order by m.createdDate DESC ")
    Page<Message> getAllMessages(Long userId, Pageable pageable);

    
    @Modifying
    @Query(value = "update message m  " +
            "set m.is_read=true " +
            "where m.send_user_id IN ?1  " +
            "and (case  when ?2 is null " +
            "then m.received_user_id is  null " +
            "else m.received_user_id=?2 end )"
            ,nativeQuery = true)
    void readMessage(List<Long> userIds, Long receivedUserId);


    @Query(value = "select count(IF(m.is_read=false,1,null))as count " +
            "from user u " +
            "left join message m on m.send_user_id=u.id " +
            "where u.user_role='USER'"
            ,nativeQuery = true)
    Integer getTotalMessagesCount();


    @Query(value = "SELECT u.id, u.name, "
            + "COUNT(IF(m.is_read = FALSE, 1, NULL)) AS count "
            + "FROM user u "
            + "LEFT JOIN message m ON m.send_user_id = u.id "
            + "WHERE u.user_role = 'USER' "
            + "GROUP BY u.id "
            + "ORDER BY count DESC ",
            nativeQuery = true)
    List<Object[]> getUserUnreadMessages();


}
