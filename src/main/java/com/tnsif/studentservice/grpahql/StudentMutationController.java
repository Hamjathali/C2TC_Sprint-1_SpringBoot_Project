package com.tnsif.studentservice.graphql;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import com.tnsif.studentservice.Student;
import com.tnsif.studentservice.service.StudentService;
import com.tnsif.studentservice.kafka.StudentProducer;

@Controller
public class StudentMutationController {

    private final StudentService service;
    private final StudentProducer producer;

    public StudentMutationController(StudentService service, StudentProducer producer) {
        this.service = service;
        this.producer = producer;
    }

    @MutationMapping
    public Student createStudent(
            @Argument Integer sid,
            @Argument String s_name,
            @Argument String department,
            @Argument String mobileNo,
            @Argument String email,
            @Argument Integer age,
            @Argument String gender) {

        Student s = new Student(
                sid,
                s_name,
                department,
                mobileNo,
                email,
                age,
                gender);

        producer.sendStudent(s);
        return s;
    }

    @MutationMapping
    public Student updateStudent(
            @Argument Integer sid,
            @Argument String s_name,
            @Argument String department,
            @Argument String mobileNo,
            @Argument String email,
            @Argument Integer age,
            @Argument String gender) {

        Student student = service.get(sid);

        student.setS_name(s_name);
        student.setDepartment(department);
        student.setMobileNo(mobileNo);
        student.setEmail(email);
        student.setAge(age);
        student.setGender(gender);

        return service.update(student);
    }

    @MutationMapping
    public String deleteStudent(@Argument Integer sid) {

        service.delete(sid);

        return "Student Deleted Successfully";
    }

}