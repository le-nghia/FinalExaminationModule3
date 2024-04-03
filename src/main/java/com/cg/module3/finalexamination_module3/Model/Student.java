package com.cg.module3.finalexamination_module3.Model;

import java.time.LocalDate;

public class Student {
    private int id;
    private String nameStudent;
    private String email;
    private LocalDate dob;
    private String address;
    private String phoneNumber;
    private int classID;
    private Classroom classroom;

    public Student() {
    }

    public Student(int id, String nameStudent, String email, LocalDate dob, String address, String phoneNumber, int classID) {
        this.id = id;
        this.nameStudent = nameStudent;
        this.email = email;
        this.dob = dob;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.classID = classID;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public void setClassroom(String classroomName) {
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
}
