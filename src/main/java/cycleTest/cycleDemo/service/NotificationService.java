package cycleTest.cycleDemo.service;

import cycleTest.cycleDemo.entity.Notification;
import cycleTest.cycleDemo.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class NotificationService {

    private static final String FCM_API = "https://fcm.googleapis.com/fcm/send";
    private static final String SERVER_KEY = "AIzaSyD3EQmuk62VG5-S3qP50d2jcY78Zsdk-6Q";

    @Autowired
    NotificationRepository notificationRepository;

    public List<Notification> getAllNotifications(){
        return notificationRepository.findAll();
    }

    public void  sendNotification(Notification notification){

        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization","key="+SERVER_KEY);

        HttpEntity<Notification> request=new HttpEntity<>(notification,headers);
        RestTemplate template=new RestTemplate();
        template.postForEntity(FCM_API,request, String.class);
        notificationRepository.save(notification);
    }
}
