package cycleTest.cycleDemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@Data
@Table(name = "notification")
public class Notification extends BaseEntity{

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "topic")
    private String topic;


}
