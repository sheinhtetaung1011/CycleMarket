package cycleTest.cycleDemo.controller;

import cycleTest.cycleDemo.annotation.CurrentUser;
import cycleTest.cycleDemo.dto.MessageDTO;
import cycleTest.cycleDemo.dto.PageableDTO;
import cycleTest.cycleDemo.dto.UserDTO;
import cycleTest.cycleDemo.entity.User;
import cycleTest.cycleDemo.security.CustomUserDetails;
import cycleTest.cycleDemo.service.MessageService;
import cycleTest.cycleDemo.service.UserService;
import cycleTest.cycleDemo.utils.CommonUtils;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/message")
@Slf4j
public class MessageController {

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @GetMapping()
    public ResponseEntity<?> getAllMessages(@CurrentUser CustomUserDetails customUserDetails,@PageableDefault(size = 20) Pageable pageable){

        log.debug("start getting all messages::");

        PageableDTO pageableDTO=messageService.getAllMessagesByUserId(customUserDetails.getId(),pageable);
        log.debug("end getting all messages::");
        return  new ResponseEntity<>(CommonUtils.returnSuccessfulMessage("get all messages."),HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getAllMessageByUser(@PathVariable("userId")Long userId, @PageableDefault(size = 20) Pageable pageable){

        log.debug("start getting user messages::");
        log.debug("user id : "+userId);
        PageableDTO pageableDTO=messageService.getAllMessagesByUserId(userId,pageable);
        log.debug("end getting user messages::");
        return new ResponseEntity<>(pageableDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> sendMessage(@CurrentUser CustomUserDetails customUserDetails,
                                         @Valid @RequestBody MessageDTO messageDTO, Errors errors){

        log.debug("start sending message::");
        User currentUser=userService.findByName(customUserDetails.getUsername()).get();
        userService.checkUserStatus(currentUser);

        if (errors.hasErrors()){
            return new ResponseEntity<>(CommonUtils.getFieldErrors(errors),HttpStatus.BAD_REQUEST);
        }
        MessageDTO result=messageService.sendMessage(customUserDetails,messageDTO);
        log.debug("end sending message::");
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getAllUserMessage(){

        log.debug("start getting all user messages::");
        List<UserDTO> userDTOList=messageService.getUserMessages();
        log.debug("end getting all user messages::");
        return  new ResponseEntity<>(userDTOList,HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> readMessageByAdmin(@CurrentUser CustomUserDetails customUserDetails,@PathVariable("userId") Long userId,Errors errors){

        log.debug("start reading message by admin::");
        if (errors.hasErrors()){
            return new ResponseEntity<>(CommonUtils.getFieldErrors(errors),HttpStatus.BAD_REQUEST);
        }
        messageService.readMessage(customUserDetails,userId);
        log.debug("end reading message by admin::");
        return new ResponseEntity<>(CommonUtils.returnSuccessfulMessage("read all messages successfully"),HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<?> getTotalNewMessageCount(){

        log.debug("start getting all new messages::");
        Integer totalCount=messageService.getTotalNewMessages();

        log.debug("end getting total new messages::");
        return new ResponseEntity<>(totalCount,HttpStatus.OK);
    }




}
