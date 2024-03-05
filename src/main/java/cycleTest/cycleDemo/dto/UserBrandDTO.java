package cycleTest.cycleDemo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import cycleTest.cycleDemo.entity.UserBrand;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBrandDTO extends CommonDTO implements Serializable {

    @NotBlank
    private String name;

    private Long userId;
    private String userName;

    public UserBrandDTO(UserBrand userBrand){
        super(userBrand);
        this.name=userBrand.getName();
        this.userId=userBrand.getUser().getId();
        this.userName=userBrand.getUser().getName();
    }
}
