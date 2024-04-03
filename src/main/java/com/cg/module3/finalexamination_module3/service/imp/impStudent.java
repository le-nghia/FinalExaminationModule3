package com.cg.module3.finalexamination_module3.service.imp;

import com.cg.module3.finalexamination_module3.Model.Student;
import com.cg.module3.finalexamination_module3.service.StudentService;

import java.sql.SQLException;
import java.util.ArrayList;

public class impStudent {

    StudentService s = new StudentService();

    public Student getByIdStudent(int studentID){
        return s.getStudentById(studentID);
    }
    /**
     * Check add new Student
     * @param student STUDENT
     * @return
     * @throws SQLException
     */
    public boolean addNewStudent(Student student) throws Exception {
        return s.addStudent(student);
    }

    /**
     * Method Edit Student
     * @param student STUDENT
     * @return RETURN
     */
    public boolean editStudent(Student student){
        return s.updateStudent(student);
    }

    public boolean delStudent(int id) throws SQLException {
        return s.deleteStudent(id);
    }

    public ArrayList<Student> searchStudentByName(String name) throws SQLException {
        return (ArrayList<Student>) s.searchStudentsByName(name);
    }
}
