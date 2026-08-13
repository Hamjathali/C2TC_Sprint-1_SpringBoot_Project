package com.tnsif.studentservice.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.tnsif.studentservice.dto.Notification;

@Service
public class NotificationService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

     
    public void sendNotification(Notification notification) {

        System.out.println("================================");
        System.out.println("Sending WebSocket Notification");
        System.out.println(notification);
        System.out.println("================================");

        messagingTemplate.convertAndSend(
                "/topic/students",
                notification
        );
    }
}


//  Send notification to all clients subscribed to /topic/students