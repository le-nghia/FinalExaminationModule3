package com.finalexaminationModule3.Model;

public class Classroom {
    private int classID;
    private String nameClass;

    public Classroom(int classID, String nameClass) {
        this.classID = classID;
        this.nameClass = nameClass;
    }

    public Classroom() {
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public String getNameClass() {
        return nameClass;
    }

    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }
}
