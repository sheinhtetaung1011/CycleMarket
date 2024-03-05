package cycleTest.cycleDemo.service;

import cycleTest.cycleDemo.dto.MessageDTO;
import cycleTest.cycleDemo.dto.PageableDTO;
import cycleTest.cycleDemo.dto.UserDTO;
import cycleTest.cycleDemo.entity.Message;
import cycleTest.cycleDemo.entity.User;
import cycleTest.cycleDemo.enums.UserRole;
import cycleTest.cycleDemo.repository.MessageRepository;
import cycleTest.cycleDemo.security.CustomUserDetails;
import cycleTest.cycleDemo.utils.CommonUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserService userService;

    public PageableDTO getAllMessagesByUserId(Long userId, Pageable pageable){
        Page<Message>  messagePage=messageRepository.getAllMessages(userId,pageable);
        List<Message> messageList =messagePage.getContent();
        List<MessageDTO> messageDTOList= CommonUtils.getDTOList(messageList,MessageDTO::new);
        PageableDTO pageableDTO=new PageableDTO(messageDTOList,messagePage);
        return  pageableDTO;
    }

    @Transactional(rollbackFor = Exception.class)
    public MessageDTO sendMessage(CustomUserDetails customUserDetails,@Valid MessageDTO messageDTO){

        Message message=new Message();
        message.setMessage(messageDTO.getMessage());
        message.setSendUserId(customUserDetails.getId());
        message.setReceivedUserId(messageDTO.getReceivedUserId());
        message.setIsRead(messageDTO.getIsRead());
        messageRepository.save(message);

        return new MessageDTO(message);
    }

    @Transactional(rollbackFor = Exception.class)
    public List<UserDTO> getUserMessages(){

        List<Object[]> objList=messageRepository.getUserUnreadMessages();

        List<UserDTO> userDTOList=new ArrayList<>();
        for(Object[] obj : objList){
            UserDTO userDTO=new UserDTO();
            userDTO.setUserRole(null);
            userDTO.setStatus(null);
            userDTO.setId(((BigInteger)obj[0]).longValue());
            userDTO.setName((String) obj[1]);
            userDTO.setMessageCount(((BigInteger)obj[2]).longValue());
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    @Transactional(rollbackFor = Exception.class)
    public void  readMessage(CustomUserDetails customUserDetails,Long userId){

        List<Long> userIdList=new ArrayList<>();
        Long receivedUserId=null;

        User currentUser=userService.getUserById(customUserDetails.getId());
        if (currentUser.getUserRole().equals(UserRole.USER)){
            receivedUserId=currentUser.getId();

            userIdList=userService.findByRole(UserRole.ADMIN);
        } else if (currentUser.getUserRole().equals(UserRole.ADMIN)) {
            userIdList.add(userId);
        }
        messageRepository.readMessage(userIdList,receivedUserId);
    }

    public Integer getTotalNewMessages(){

        return messageRepository.getTotalMessagesCount();
    }


}
