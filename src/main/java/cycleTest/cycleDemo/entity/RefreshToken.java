package cycleTest.cycleDemo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "refresh_token")
public class RefreshToken implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;

    @Column(name = "token",nullable = false,unique = true)
    private String token;

    @Column(name = "expire_date",nullable = false)
    private Date expireDate;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
