package cycleTest.cycleDemo.entity;

import cycleTest.cycleDemo.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "blog")
public class Blog extends BaseEntity{

    @Column(name = "name",unique = true,nullable = false)
    private String name;

    @Column(name = "cover_image_url",nullable = false)
    private String coverImageUrl;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private  User user;

    @Column(name = "preview_text",length = 300)
    private String previewText;

    @Column(name = "text",columnDefinition = "TEXT")
    private String text;

    @Enumerated(EnumType.STRING)
    private Status status;



}
