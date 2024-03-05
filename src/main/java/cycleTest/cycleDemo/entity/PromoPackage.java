package cycleTest.cycleDemo.entity;

import cycleTest.cycleDemo.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "promo_package")
public class PromoPackage extends BaseEntity{

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "amount",nullable = false)
    private Double amount;

    @Column(name = "duration",nullable = false)
    private Integer duration;

    @Column(name = "status",nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;


}
