package cycleTest.cycleDemo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import cycleTest.cycleDemo.entity.Product;
import cycleTest.cycleDemo.enums.*;
import cycleTest.cycleDemo.utils.CommonUtils;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ProductDTO extends CommonDTO {

    private String name;

    private CycleType cycleType;
    private PartType partType;
    private ProductSellStatus productSellStatus;
    private ProductStatus productStatus;
    private ProductType productType;
    private PurchaseStatus purchaseStatus;
    private Status status;
    private UploadType uploadType;

    private List<String> imageUrls;
    private String videoUrl;

    private Long userBrandId;
    private String userBrandName;

    private Long cityId;
    private String cityName;

    private Long townshipId;
    private String townshipName;

    private Long modelId;
    private String modelName;

    private Long userId;
    private String userName;
    private String phoneNo;

    private String enginePower;
    private Double price;
    private String license;

    @Length(max = 500)
    private String description;
    private String year;

    private Boolean showInHome;

    public ProductDTO(Product product, boolean isDetails) {

        super(product);
        this.name = product.getName();
        this.modelName = product.getModel().getName();
        this.userBrandName = product.getUserBrand().getName();
        this.userId = product.getUser().getId();
        this.userName = product.getUser().getName();
        this.phoneNo = product.getUser().getPhoneNo();
        this.year = product.getYear();
        this.productStatus = product.getProductStatus();
        this.productSellStatus = product.getProductSellStatus();
        this.productType = product.getProductType();
        this.status = product.getStatus();
        this.uploadType = product.getUploadType();
        this.showInHome = product.getShowInHome();

        if (isDetails) {
            this.imageUrls = new ArrayList<>();
            List<String> imageList = CommonUtils.separateString(product.getImageUrls(), ";");
            for (String url : imageList) {
                this.imageUrls = Arrays.asList(product.getImageUrls().split(";"));
            }

            this.cityId = product.getCity().getId();
            this.cityName = product.getCity().getName();
            this.townshipId = product.getTownship().getId();
            this.townshipName = product.getTownship().getName();
            this.description = product.getDescription();

            if (product.getProductType().equals(ProductType.CYCLE)) {
                this.price = product.getPrice();
                this.license = product.getLicense();
                this.enginePower = product.getEnginePower();
                this.productSellStatus = product.getProductSellStatus();
                this.userBrandId = product.getUserBrand() == null ? null : product.getUserBrand().getId();
                this.modelId = product.getModel() == null ? null : product.getModel().getId();
            } else if (product.getProductType().equals(ProductType.PART)) {
                this.partType = product.getPartType();
            }

        } else {
            this.imageUrls = new ArrayList<>();
            List<String> imageList = CommonUtils.separateString(product.getImageUrls(), ";");
            for (String obj : imageList) {
                this.imageUrls = Arrays.asList(product.getImageUrls().split(";"));
                break;
            }
        }


    }


}
