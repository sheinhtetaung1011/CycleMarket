package cycleTest.cycleDemo.controller;

import cycleTest.cycleDemo.entity.Notification;
import cycleTest.cycleDemo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @GetMapping
    public List<Notification> getAllNotifications(){
        return notificationService.getAllNotifications();
    }

    @PostMapping
    public  void  sendNotification(@RequestBody Notification notification){
         notificationService.sendNotification(notification);
    }
}
