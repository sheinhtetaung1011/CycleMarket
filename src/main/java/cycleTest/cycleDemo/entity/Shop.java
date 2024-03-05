package cycleTest.cycleDemo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Entity
@Table(name = "shop")
@Data
public class Shop extends BaseEntity implements Serializable {

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @ManyToOne
    @JoinColumn(name = "township_id", nullable = false)
    private Township township;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "description", length = 400)
    private String description;

    @Column(name = "show_in_home", columnDefinition = "bit(1) default 0")
    private Boolean showInHome;

    @Column(name = "image_url")
    private String imageUrl;
}


