package com.tnsif.studentservice.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tnsif.studentservice.Student;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class StudentProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public StudentProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = new ObjectMapper();
    }

    public void sendStudent(Student student) {
        try {
            String json = objectMapper.writeValueAsString(student);
            kafkaTemplate.send("student-topic", json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}