package com.finalexaminationModule3.controller;

import com.finalexaminationModule3.Model.Student;
import com.finalexaminationModule3.service.StudentService;
import com.finalexaminationModule3.service.imp.impStudent;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@WebServlet(name = "SearchServlet", urlPatterns = "/search-student")
public class SearchServlet extends HttpServlet {
    private StudentService studentService;
    @Override
    public void init() throws ServletException {
        super.init();
        studentService = new StudentService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("search");
        impStudent impStudent = new impStudent();
        try {
            if (name != null && !name.isEmpty()) {
                List<Student> students = impStudent.searchStudentByName(name);
                System.out.println("==> Search name Student: " + students.toString());
                request.setAttribute("students", students);
                request.getRequestDispatcher("/search.jsp").forward(request, response);
            }else {
                List<Student> students = studentService.getAllStudents();
                request.setAttribute("students", students);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new RuntimeException("RUN TIME ERROR");
        }

    }

}