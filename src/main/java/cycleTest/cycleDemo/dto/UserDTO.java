package cycleTest.cycleDemo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import cycleTest.cycleDemo.entity.City;
import cycleTest.cycleDemo.entity.User;
import cycleTest.cycleDemo.enums.Status;
import cycleTest.cycleDemo.enums.UserRole;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class UserDTO extends CommonDTO{

    private String name;
    private String password;
    private UserRole userRole;
    private Status status;
    private String phoneNo;
    private Long cityId;
    private String  cityName;
    private Long townshipId;
    private String townshipName;

    //for chat message room
    private Long messageCount;
    
    public UserDTO(User user){
        super(user);
        this.name= user.getName();
        this.userRole=user.getUserRole();
        this.status=user.getStatus();
        this.phoneNo=user.getPhoneNo();
        this.cityId=user.getCity().getId();
        this.cityName=user.getCity().getName();

        this.townshipId=user.getTownship().getId();
        this.townshipName=user.getTownship().getName();

    }
}
