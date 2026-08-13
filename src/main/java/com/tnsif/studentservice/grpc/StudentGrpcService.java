package com.tnsif.studentservice.grpc;

import com.tnsif.grpc.*;
import com.tnsif.studentservice.Student;
import com.tnsif.studentservice.service.StudentService;

import net.devh.boot.grpc.server.service.GrpcService;

import io.grpc.stub.StreamObserver;

import java.util.List;

@GrpcService
public class StudentGrpcService extends StudentServiceGrpc.StudentServiceImplBase {

    private final StudentService studentService;

    public StudentGrpcService(StudentService studentService) {
        this.studentService = studentService;
    }

    // 🔹 GET ALL STUDENTS
    @Override
    public void getAllStudents(Empty request, StreamObserver<StudentList> responseObserver) {

        List<Student> students = studentService.listAll();

        StudentList.Builder listBuilder = StudentList.newBuilder();

        for (Student s : students) {
            StudentResponse res = StudentResponse.newBuilder()
                    .setSId(s.getSid())
                    .setSName(s.getS_name())
                    .setDepartment(s.getDepartment())
                    .setMobileNo(s.getMobileNo())
                    .setEmail(s.getEmail())
                    .setAge(s.getAge())
                    .setGender(s.getGender())
                    .build();

            listBuilder.addStudents(res);
        }

        responseObserver.onNext(listBuilder.build());
        responseObserver.onCompleted();
    }

    // 🔹 GET BY ID
    @Override
    public void getStudentById(StudentIdRequest request,
                               StreamObserver<StudentResponse> responseObserver) {

        Student s = studentService.get(request.getId());

        StudentResponse response = StudentResponse.newBuilder()
                .setSId(s.getSid())
                .setSName(s.getS_name())
                .setDepartment(s.getDepartment())
                .setMobileNo(s.getMobileNo())
                .setEmail(s.getEmail())
                .setAge(s.getAge())
                .setGender(s.getGender())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void createStudent(StudentRequest request,
            StreamObserver<StudentResponse> responseObserver) {

        Student s = new Student(
                request.getSId(),
                request.getSName(),
                request.getDepartment(),
                request.getMobileNo(),
                request.getEmail(),
                request.getAge(),
                request.getGender()
        );

        studentService.save(s);

        StudentResponse response = StudentResponse.newBuilder()
                .setSId(s.getSid())
                .setSName(s.getS_name())
                .setDepartment(s.getDepartment())
                .setMobileNo(s.getMobileNo())
                .setEmail(s.getEmail())
                .setAge(s.getAge())
                .setGender(s.getGender())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void updateStudent(StudentRequest request,
            StreamObserver<StudentResponse> responseObserver) {

        Student existing = studentService.get(request.getSId());

        existing.setS_name(request.getSName());
        existing.setDepartment(request.getDepartment());
        existing.setMobileNo(request.getMobileNo());
        existing.setEmail(request.getEmail());
        existing.setAge(request.getAge());
        existing.setGender(request.getGender());

        studentService.update(existing);

        StudentResponse response = StudentResponse.newBuilder()
                .setSId(existing.getSid())
                .setSName(existing.getS_name())
                .setDepartment(existing.getDepartment())
                .setMobileNo(existing.getMobileNo())
                .setEmail(existing.getEmail())
                .setAge(existing.getAge())
                .setGender(existing.getGender())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteStudent(StudentIdRequest request,
            StreamObserver<Empty> responseObserver) {

        studentService.delete(request.getId());

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }
}