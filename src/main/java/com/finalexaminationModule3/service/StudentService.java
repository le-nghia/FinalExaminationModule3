package com.finalexaminationModule3.service;

import com.finalexaminationModule3.DBContext.DBContext;
import com.finalexaminationModule3.Model.Classroom;
import com.finalexaminationModule3.Model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    Connection connection = null;
    PreparedStatement cmd = null;
    ResultSet rs = null;

public List<Student> getAllStudents(){

        List<Student> students = new ArrayList<>();

        String sql = " SELECT s.*, c.classID as classID, c.nameClass as nameClass" +
                        " FROM Student as s " +
                        " INNER JOIN Classroom as c ON s.classID = c.classID ";

        try {
            connection = new DBContext().getConnection();
            cmd = connection.prepareStatement(sql);
            rs = cmd.executeQuery();

            while (rs.next()) {
                Student student = new Student();

                Extra(student);

                int classID = rs.getInt("classID");
                String nameClass = rs.getString("nameClass");
                Classroom classroom = new Classroom(classID, nameClass);
                student.setClassroom(classroom); // Thêm thông tin về tên lớp học

                students.add(student);
            }

            System.out.println(" ===> Display all student");
            rs.close();
            DBContext.closed(connection);

        } catch (Exception e) {
            throw new RuntimeException("Run time error!");
        }
        return students;
    }

    /**
     * CALL STUDENT BY ID UPDATE
     * @param studentID ID
     */
    public Student getStudentById(int studentID) {

        Student student = new Student();
        String query = "SELECT * FROM Student WHERE id = ?";

        try {

            connection = new DBContext().getConnection();
            cmd = connection.prepareStatement(query);
            cmd.setInt(1, studentID);
            rs = cmd.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("id");
                String nameStudent = rs.getString("nameStudent");
                String email = rs.getString("email");
                Date dob = rs.getDate("dob");
                String address = rs.getString("address");
                String phone = rs.getString("phoneNumber");
                int classID = rs.getInt("classID");

                student = new Student(id, nameStudent, email, dob.toLocalDate(), address, phone, classID);
                System.out.println("===> Student updated: ID  is " + studentID);
            }

            rs.close();
            DBContext.closed(connection);

        } catch (Exception e) {
            throw new RuntimeException("Run time error!");
        }
        return student;
    }

    public boolean addStudent(Student student) throws Exception {

        connection = new DBContext().getConnection();

        try {

            String query = "INSERT INTO Student (nameStudent, email, dob, address, phoneNumber, classID) VALUES (?, ?, ?, ?, ?, ?)";
            ExtraCMD(student, query);
            cmd.executeUpdate();
            System.out.println("===> Add new Student Success!");

            DBContext.closed(connection);
            cmd.close();
            return true;

        } catch (Exception e) {
            throw new RuntimeException("Run time error!");
        }
    }

    /**
     * Ham edit Student
     * @param student EDIT
     */
    public boolean updateStudent(Student student) {

        String query = "UPDATE Student SET nameStudent = ?, email = ?, dob = ?, address = ?, phoneNumber = ?, classID = ? WHERE id = ?";

        try {
            connection = new DBContext().getConnection();
            ExtraCMD(student, query);
            cmd.setInt(7, student.getId());
            cmd.executeUpdate();

            DBContext.closed(connection);
            cmd.close();
            System.out.println("===> CALL TO BACK UPDATE!");
            System.out.println("====> Class: " + StudentService.class);
            System.out.println("-----------------------------------");

            return true;
        } catch (Exception e) {
            throw new RuntimeException("Run time error!");
        }
    }

    public boolean deleteStudent(int id) throws SQLException {
        connection = new DBContext().getConnection();
        PreparedStatement cmd;
        try {

            String sql = " DELETE FROM Student WHERE id = ? ";


            cmd = connection.prepareStatement(sql);
            cmd.setInt(1, id);
            cmd.executeUpdate();

            DBContext.closed(connection);
            System.out.println("====> Deleted Successfully ");

            return true;
        } catch (Exception e) {
            throw new RuntimeException("Run time error!");
        }
    }

    public List<Student> searchStudentsByName(String nameStudent) throws SQLException {

        List<Student> students = new ArrayList<>();
        connection = new DBContext().getConnection();

        try {

            String sql = "SELECT * FROM Student WHERE nameStudent LIKE ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + nameStudent + "%");
             rs = statement.executeQuery();
            Student student = new Student();

            while (rs.next()) {
                Extra(student);
                students.add(student);
            }

            rs.close();
            DBContext.closed(connection);

        } catch (SQLException e) {
            throw new RuntimeException("Run time error!");
        }
        return students;
    }

    private void ExtraCMD(Student student, String query) throws SQLException {

        cmd = connection.prepareStatement(query);
        cmd.setString(1, student.getNameStudent());
        cmd.setString(2, student.getEmail());
        cmd.setDate(3, Date.valueOf(student.getDob()));
        cmd.setString(4, student.getAddress());
        cmd.setString(5, student.getPhoneNumber());
        cmd.setInt(6, student.getClassID());
    }
    private void Extra(Student student) throws SQLException {
        student.setId(rs.getInt("id"));
        student.setNameStudent(rs.getString("nameStudent"));
        student.setEmail(rs.getString("email"));
        student.setDob(rs.getDate("dob").toLocalDate());
        student.setAddress(rs.getString("address"));
        student.setPhoneNumber(rs.getString("phoneNumber"));
        student.setClassID(rs.getInt("classID"));
    }

}

