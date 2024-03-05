package cycleTest.cycleDemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "message")
public class Message extends BaseEntity implements Serializable {

    @Column(name = "message",length = 500)
    private String message;

    @Column(name = "send_user_id")
    private Long sendUserId;

    @Column(name = "received_user_id")
    private Long receivedUserId;

    @Column(name = "is_read")
    private Boolean isRead;
}
