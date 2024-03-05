package cycleTest.cycleDemo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import cycleTest.cycleDemo.entity.PromoPackage;
import cycleTest.cycleDemo.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class PromoPackageDTO extends CommonDTO{

    private String name;
    private Double amount;
    private Integer duration;
    private Status status;

    public PromoPackageDTO(PromoPackage promoPackage){

        super(promoPackage);
        this.name=promoPackage.getName();
        this.amount=promoPackage.getAmount();
        this.duration=promoPackage.getDuration();
        this.status=promoPackage.getStatus();
    }
}
