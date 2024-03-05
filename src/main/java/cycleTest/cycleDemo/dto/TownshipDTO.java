package cycleTest.cycleDemo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import cycleTest.cycleDemo.entity.Shop;
import cycleTest.cycleDemo.entity.Township;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class TownshipDTO extends CommonDTO {

    @NotBlank
    @Length(max = 50)
    private String name;

    private String cityName;

    private Long cityId;

    public TownshipDTO(Township township){
        super(township);
        this.name=township.getName();
        this.cityId=township.getCity().getId();
        this.cityName=township.getCity().getName();
    }
}
