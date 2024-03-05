package cycleTest.cycleDemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cycleTest.cycleDemo.enums.Status;
import cycleTest.cycleDemo.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "user")
public class User extends BaseEntity implements Serializable {

    @Column(name = "name",unique = true,length = 50,nullable = false)
    private String name;

    @JsonIgnore
    @Column(name = "password",nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role",length = 10,nullable = false)
    private UserRole userRole;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",length = 10,nullable = false)
    private Status status;

    @Column(name = "phone_No")
    @Length(max = 13)
    private String phoneNo;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "township_id")
    private  Township township;






}
