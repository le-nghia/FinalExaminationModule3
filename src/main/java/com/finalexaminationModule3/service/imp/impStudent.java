package com.finalexaminationModule3.service.imp;

import com.finalexaminationModule3.Model.Student;
import com.finalexaminationModule3.service.StudentService;

import java.sql.SQLException;
import java.util.List;

public class impStudent {

    StudentService s = new StudentService();

    public Student getByIdStudent(int studentID){
        return s.getStudentById(studentID);
    }

    /**
     * Check add new Student
     * @param student STUDENT
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

    public List<Student> searchStudentByName(String name) throws SQLException {
        return s.searchStudentsByName(name);
    }
}
