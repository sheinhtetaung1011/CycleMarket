package cycleTest.cycleDemo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import cycleTest.cycleDemo.entity.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
public class ModelDTO extends CommonDTO{

    private String name;
    private Long userBrandId;
    private String userBrandName;

    public ModelDTO(Model model){
        super(model);
        this.name=model.getName();
        this.userBrandId=model.getUserBrand().getId();
        this.userBrandName=model.getUserBrand().getName();
    }
}
