package cycleTest.cycleDemo.payloads;

import lombok.*;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class MediaUploadResponse {

    private String url;
    private String publicId;
}

