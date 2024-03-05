package cycleTest.cycleDemo.controller;


import cycleTest.cycleDemo.dto.MessageDTO;
import cycleTest.cycleDemo.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.message.SimpleMessage;
import org.jboss.logging.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class WebSocketController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    MessageService messageService;

    @MessageMapping("/chat")
    public void processMessage(@Payload MessageDTO messageDTO){

        log.info("message : {} "+ messageDTO);
        String recipient=null;
        Long recipientId=messageDTO.getReceivedUserId();

        if (recipientId==null)
            recipient="admin";

        else recipient=recipientId.toString();
        simpMessagingTemplate.convertAndSendToUser(recipient,"/queue/messages",messageDTO);
    }

}
