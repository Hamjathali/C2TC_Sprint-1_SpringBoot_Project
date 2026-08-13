package com.tnsif.studentservice.dto;

public class Notification {

    private String action;
    private String studentName;
    private String message;

    public Notification() {}

    public Notification(String action,
                        String studentName,
                        String message) {
        this.action = action;
        this.studentName = studentName;
        this.message = message;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "action='" + action + '\'' +
                ", studentName='" + studentName + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}