package cycleTest.cycleDemo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "model")
public class Model extends BaseEntity{

    @Column(name = "name",nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_brand_id")
    private UserBrand userBrand;


}
