package com.tnsif.studentservice.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tnsif.studentservice.Student;
import com.tnsif.studentservice.repository.StudentRepository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    
    public List<Student> listAll() {
        return repo.findAll();
    }

    @CachePut(value = "students", key = "#s.sid")
    public Student save(Student s) {
        System.out.println("Saving Student to Database...");
        return repo.save(s);
    }

    @Cacheable(value = "students", key = "#sid")
    public Student get(Integer sid) {

        System.out.println("Fetching Student From Database...");
        // return repo.findById(sid).get();
        return repo.findById(sid)
                .orElseThrow(() -> new RuntimeException("Student Not Found"));
    }

    @CacheEvict(value = "students", key = "#sid")
    public void delete(Integer sid) {

        System.out.println("Deleting Student...");
        repo.deleteById(sid);
    }

    @CachePut(value = "students", key = "#s.sid")
    public Student update(Student s) {
        System.out.println("Updating Student...");
        return repo.save(s);
    }
}

