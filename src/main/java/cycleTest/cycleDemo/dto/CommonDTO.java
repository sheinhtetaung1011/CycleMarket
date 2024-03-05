package cycleTest.cycleDemo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import cycleTest.cycleDemo.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonDTO implements Serializable {
    private Long  id;
    private Long createdTimeInMillis;
    private Long updatedTimeInMillis;

    public<T extends BaseEntity> CommonDTO(T entity){
        this.id=entity.getId();
        this.createdTimeInMillis=entity.getCreatedDate()==null?
                null:entity.getCreatedDate().toEpochMilli();
        this.updatedTimeInMillis=entity.getUpdatedDate()==null?
                null:entity.getUpdatedDate().toEpochMilli();
    }
}
