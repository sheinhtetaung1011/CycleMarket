package cycleTest.cycleDemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "cloudinary_key")
public class CloudinaryKey extends BaseEntity{

    @Column(name = "cloudinaryUrl")
    private String cloudinaryUrl;

    public CloudinaryKey(String cloudinaryUrl){
        this.cloudinaryUrl=cloudinaryUrl;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (this==obj){
            return true;
        }
        if (!(obj instanceof CloudinaryKey)){
            return false;
        }
        CloudinaryKey that= (CloudinaryKey) obj;
        return Objects.equals(this.getId(),that.getId());
    }






}
