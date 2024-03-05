package cycleTest.cycleDemo.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import cycleTest.cycleDemo.entity.Shop;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ShopDTO extends CommonDTO{

    @NotBlank
    @Length(max=50,min=5)
    private String name;

    private Long userId;
    private String userName;

    private Long cityId;
    private String cityName;

    private Long townshipId;
    private String townshipName;

    private String description;

    private Boolean showInHome;

    @NotBlank
    private String address;

    private String imageUrl;
    private String image;

    /* need to add image url */

    public ShopDTO(Shop shop){
        super(shop);
        this.name=shop.getName();

        this.userId=shop.getUser().getId();
        this.userName=shop.getUser().getName();

        this.cityId=shop.getCity().getId();
        this.cityName=shop.getCity().getName();

        this.townshipId=shop.getTownship().getId();
        this.townshipName=shop.getTownship().getName();

        this.showInHome=shop.getShowInHome();
        this.address=shop.getAddress();
        this.description=shop.getDescription();

    }


}
