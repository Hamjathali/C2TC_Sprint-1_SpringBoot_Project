package com.tnsif.studentservice.graphql;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.tnsif.studentservice.Student;
import com.tnsif.studentservice.service.StudentService;

@Controller
public class StudentQueryController {

    private final StudentService service;

    public StudentQueryController(StudentService service) {
        this.service = service;
    }

    @QueryMapping
    public List<Student> students() {
        return service.listAll();
    }

    @QueryMapping
    public Student student(@Argument Integer sid) {
        return service.get(sid);
    }
}