package com.tnsif.studentservice.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tnsif.studentservice.Student;
import com.tnsif.studentservice.service.StudentService;
import com.tnsif.studentservice.kafka.StudentProducer;

import jakarta.persistence.NoResultException;


@RestController
public class StudentController {

	@Autowired
	private StudentService s;  

	@Autowired
	private StudentProducer producer;  
	
	@GetMapping("/studentservice")
	public List<Student> list(){
		return s.listAll();
	}
	

	// 🔥 UPDATED: Using Kafka instead of direct DB save
    @PostMapping("/studentservice")
    public ResponseEntity<String> add(@RequestBody Student s1) {

        // Send data to Kafka
        producer.sendStudent(s1);  

        return new ResponseEntity<>("Student sent to Kafka", HttpStatus.OK);
    }
	
	@GetMapping("/studentservice/{id}")
	public ResponseEntity<Student> get(@PathVariable Integer id){
		try {
			Student s2 = s.get(id);
			return new ResponseEntity<Student>(s2,HttpStatus.OK);
		}catch(NoResultException e) {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/studentservice/{id}")
	public void delete(@PathVariable Integer id) {
		s.delete(id);
	}
	
	@PutMapping("/studentservice/{id}")
	public ResponseEntity<Student> update(@PathVariable Integer id,@RequestBody Student update_s){
		try {
			Student exist_s = s.get(id);
			exist_s.setS_name(update_s.getS_name());
			exist_s.setDepartment(update_s.getDepartment());
			exist_s.setMobileNo(update_s.getMobileNo());
			exist_s.setEmail(update_s.getEmail());
			exist_s.setAge(update_s.getAge());
			exist_s.setGender(update_s.getGender());
			s.update(exist_s);
			return new ResponseEntity<Student>(exist_s,HttpStatus.OK);
		}catch(NoResultException e) {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
	}
}
