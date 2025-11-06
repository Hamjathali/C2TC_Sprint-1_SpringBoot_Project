package com.tnsif.studentservice.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tnsif.studentservice.Student;
import com.tnsif.studentservice.service.StudentService;

import jakarta.persistence.NoResultException;


@RestController
public class StudentController {

	@Autowired
	private StudentService s;    // creating obj for StudentService Class for mapping the methods  
	
	@GetMapping("/studentservice")
	public List<Student> list(){
		return s.listAll();
	}
	
	@PostMapping("/studentservice")
	public void add(@RequestBody Student s1) {
		s.save(s1);
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
