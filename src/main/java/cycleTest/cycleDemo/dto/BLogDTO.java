package cycleTest.cycleDemo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import cycleTest.cycleDemo.entity.Blog;
import cycleTest.cycleDemo.enums.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class BLogDTO extends CommonDTO{

    private String name;
    private String coverImageUrl;
    private Long userId;
    private String userName;
    private String previewText;
    private String text;
    private Status status;

    public BLogDTO(Blog blog){
        super(blog);
        this.name=blog.getName();
        this.coverImageUrl=blog.getCoverImageUrl();
        this.userId=blog.getUser().getId();
        this.userName=blog.getUser().getName();
        this.previewText=blog.getPreviewText();
        this.text=blog.getText();
        this.status=blog.getStatus();

    }
}
