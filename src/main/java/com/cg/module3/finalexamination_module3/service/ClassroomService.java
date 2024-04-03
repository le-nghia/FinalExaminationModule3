package com.cg.module3.finalexamination_module3.service;

import com.cg.module3.finalexamination_module3.DBContext.DBContext;
import com.cg.module3.finalexamination_module3.Model.Classroom;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClassroomService {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    public List<Classroom> getAllClassroom(){
        List<Classroom> classrooms = new ArrayList<>();
        String query = "SELECT * " +
                " FROM classroom ";
        try {
            connection = new DBContext().getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Classroom c = new Classroom();

                c.setClassID(resultSet.getInt("classID"));
                c.setNameClass(resultSet.getString("nameClass"));

                classrooms.add(c);

                System.out.println(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classrooms;
    }
}
