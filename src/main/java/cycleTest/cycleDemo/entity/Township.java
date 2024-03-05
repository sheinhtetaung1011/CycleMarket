package cycleTest.cycleDemo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "township")
public class Township extends BaseEntity implements Serializable {

    @Column(name = "name",nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(mappedBy = "township", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Shop> shopList=new ArrayList<>();

}
