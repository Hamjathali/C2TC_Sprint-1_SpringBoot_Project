// package com.tnsif.studentservice.kafka;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.tnsif.studentservice.Student;
// import com.tnsif.studentservice.service.StudentService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.kafka.annotation.KafkaListener;
// import org.springframework.stereotype.Service;

// @Service
// public class StudentConsumer {

//     @Autowired
//     private StudentService studentService;
//     private final ObjectMapper objectMapper = new ObjectMapper();

//     @KafkaListener(topics = "student-topic", groupId = "student-group")
//     public void consume(String message) {
//         try {
//             Student student = objectMapper.readValue(message, Student.class); 
//             studentService.save(student);
//             System.out.println("Saved student from Kafka: " + student.getS_name());

//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }
// }

package com.tnsif.studentservice.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tnsif.studentservice.Student;
import com.tnsif.studentservice.dto.Notification;
import com.tnsif.studentservice.service.StudentService;
import com.tnsif.studentservice.websocket.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class StudentConsumer {

    @Autowired
    private StudentService studentService;

    @Autowired
    private NotificationService notificationService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "student-topic", groupId = "student-group")
    public void consume(String message) {

        try {

            // Convert JSON message to Student object
            Student student = objectMapper.readValue(message, Student.class);

            // Save student to the database
            Student savedStudent = studentService.save(student);

            System.out.println("Saved student from Kafka: " + savedStudent.getS_name());

            // Create notification
            Notification notification = new Notification(
                    "CREATE",
                    savedStudent.getS_name(),
                    "Student Created Successfully"
            );

            // Send WebSocket notification
            notificationService.sendNotification(notification);

            System.out.println("WebSocket notification sent.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}