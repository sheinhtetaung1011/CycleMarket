package cycleTest.cycleDemo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import cycleTest.cycleDemo.entity.Message;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageDTO extends CommonDTO implements Serializable {

    private String message;
    private Long sendUserId;
    private Long receivedUserId;
    private Boolean isRead;

    public MessageDTO(Message message){
        super(message);
        this.message=message.getMessage();
        this.sendUserId=message.getSendUserId();
        this.receivedUserId=message.getReceivedUserId();
        this.isRead=message.getIsRead();
    }
}
