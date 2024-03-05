package cycleTest.cycleDemo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "city")
public class City  extends BaseEntity {

    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;







}
