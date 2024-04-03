package com.cg.module3.finalexamination_module3.service;

import com.cg.module3.finalexamination_module3.DBContext.DBContext;
import com.cg.module3.finalexamination_module3.Model.Classroom;
import com.cg.module3.finalexamination_module3.Model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    Connection connection = null;
    PreparedStatement cmd = null;
    ResultSet rs = null;

public List<Student> getAllStudents(){

        List<Student> students = new ArrayList<>();

        String query = " SELECT s.*, c.classID as classID, c.nameClass as nameClass" +
                " FROM Student as s " +
                " INNER JOIN Classroom as c ON s.classID = c.classID ";

        try {
            connection = new DBContext().getConnection();
            cmd = connection.prepareStatement(query);
            rs = cmd.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setNameStudent(rs.getString("nameStudent"));
                student.setEmail(rs.getString("email"));
                student.setDob(rs.getDate("dob").toLocalDate());
                student.setAddress(rs.getString("address"));
                student.setPhoneNumber(rs.getString("phoneNumber"));
                student.setClassID(rs.getInt("classID"));

                int classID = rs.getInt("classID");
                String nameClass = rs.getString("nameClass");
                Classroom classroom = new Classroom(classID, nameClass);
                student.setClassroom(classroom); // Thêm thông tin về tên lớp học

                students.add(student);
                System.out.println(" ===> Display all student");
            }
            rs.close();
            DBContext.closed(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    /**
     * CALL STUDENT BY ID UPDATE
     * @param studentID ID
     * @return
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
            e.printStackTrace();
        }
        return student;
    }

    public boolean addStudent(Student student) throws Exception {

        connection = new DBContext().getConnection();
        try {
            String query = "INSERT INTO Student (nameStudent, email, dob, address, phoneNumber, classID) VALUES (?, ?, ?, ?, ?, ?)";
            cmd = connection.prepareStatement(query);

            cmd.setString(1, student.getNameStudent());
            cmd.setString(2, student.getEmail());
            cmd.setDate(3, Date.valueOf(student.getDob()));
            cmd.setString(4, student.getAddress());
            cmd.setString(5, student.getPhoneNumber());
            cmd.setInt(6, student.getClassID());

            cmd.executeUpdate();
            System.out.println("===> Add new Student Success!");

            DBContext.closed(connection);
            cmd.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Ham edit Student
     * @param student EDIT
     */
    public boolean updateStudent(Student student) {

        String query = "UPDATE Student SET nameStudent = ?, email = ?, dob = ?, address = ?, phoneNumber = ?, classID = ? WHERE id = ?";

        try {
            connection = new DBContext().getConnection();
            cmd = connection.prepareStatement(query);

            cmd.setString(1, student.getNameStudent());
            cmd.setString(2, student.getEmail());
            cmd.setDate(3, Date.valueOf(student.getDob()));
            cmd.setString(4, student.getAddress());
            cmd.setString(5, student.getPhoneNumber());
            cmd.setInt(6, student.getClassID());
            cmd.setInt(7, student.getId());
            cmd.executeUpdate();

            DBContext.closed(connection);
            cmd.close();
            System.out.println("===> CALL TO BACK UPDATE!");

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteStudent(int id) throws SQLException {
        connection = new DBContext().getConnection();
        try {

            String sql = " DELETE FROM Student WHERE id = ? ";
            PreparedStatement cmd = connection.prepareStatement(sql);
            cmd = connection.prepareStatement(sql);
            cmd.setInt(1, id);
            cmd.executeUpdate();

            DBContext.closed(connection);
            System.out.println("====> Deleted Successfully ");

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Student> searchStudentsByName(String nameStudent) throws SQLException {
        List<Student> students = new ArrayList<>();
        connection = new DBContext().getConnection();
        try {

            String sql = "SELECT * FROM Student WHERE nameStudent LIKE ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + nameStudent + "%");
             rs = statement.executeQuery();

            while (rs.next()) {

                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setNameStudent(rs.getString("nameStudent"));
                student.setEmail(rs.getString("email"));
                student.setDob(rs.getDate("dob").toLocalDate());
                student.setAddress(rs.getString("address"));
                student.setPhoneNumber(rs.getString("phoneNumber"));
                student.setClassID(rs.getInt("classID"));

                students.add(student);
            }

            rs.close();
            DBContext.closed(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
        public List<Student> searchStudentsByName1(String name) throws SQLException {
            List<Student> result = new ArrayList<>();
            for (Student student : result) {
                if (student.getNameStudent().equalsIgnoreCase(name)) {
                    result.add(student);
                }
            }
            try (Connection connection = new DBContext().getConnection();
                 PreparedStatement statement = connection.prepareStatement("SELECT * FROM Student WHERE name LIKE ?")) {
                // Set the parameter in the SQL query
                statement.setString(1, "%" + name + "%");
                // Execute the query and process the result set
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Student student = new Student();
                        student.setId(resultSet.getInt("id"));
                        student.setNameStudent(resultSet.getString("name"));
                        student.setEmail(resultSet.getString("email"));
                        // Ensure dob is correctly handled based on your Student class and database structure
                        student.setDob(resultSet.getDate("dob").toLocalDate());
                        student.setAddress(resultSet.getString("address"));
                        student.setPhoneNumber(resultSet.getString("phone_number"));
                        student.setClassID(resultSet.getInt("classroom_id"));
                        // Add the student to the result list
                        result.add(student);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return result;
        }
}

