package com.tnsif.studentservice;

//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
//@Table(name = "Student")
public class Student {

    @Id
    @Column(name = "S_id")
    private int s_id;

    @Column(name = "S_name")
    private String s_name;

    @Column(name = "Department")
    private String department;

    @Column(name = "Mobile_No")
    private String mobileNo;

    @Column(name = "Email")
    private String email;

    @Column(name = "Age")
    private int age;

    @Column(name = "Gender")
    private String gender;

    // Default Constructor
    public Student() {
        super();
    }

    // Parameterized Constructor
    public Student(int s_id, String s_name, String department, String mobileNo, String email, int age, String gender) {
        super();
        this.s_id = s_id;
        this.s_name = s_name;
        this.department = department;
        this.mobileNo = mobileNo;
        this.email = email;
        this.age = age;
        this.gender = gender;
    }

    // Getters and Setters
    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // toString Method
    @Override
    public String toString() {
        return "Student [s_id=" + s_id + ", s_name=" + s_name + ", department=" + department + 
               ", mobileNo=" + mobileNo + ", email=" + email + ", age=" + age + ", gender=" + gender + "]";
    }
}
