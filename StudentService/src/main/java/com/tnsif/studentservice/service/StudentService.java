package com.tnsif.studentservice.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tnsif.studentservice.Student;
import com.tnsif.studentservice.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    // To get all the records from the table
    public List<Student> listAll() {
        return repo.findAll();
    }

    // To save a record
    public void save(Student s) {
        repo.save(s);
    }

    // To get a specific record by ID
    public Student get(Integer sid) {
        return repo.findById(sid).get();
    }

    // To delete a specific record by ID
    public void delete(Integer sid) {
        repo.deleteById(sid);
    }

    // To update a record
    public void update(Student s) {
        repo.save(s);
    }
}

