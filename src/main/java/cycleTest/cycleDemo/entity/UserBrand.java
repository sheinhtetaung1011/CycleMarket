package cycleTest.cycleDemo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "user_brand")
public class UserBrand extends BaseEntity {

    @Column(name = "name",nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
