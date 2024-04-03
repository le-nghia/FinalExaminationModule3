package com.finalexaminationModule3.controller;

import com.finalexaminationModule3.Model.Student;
import com.finalexaminationModule3.service.StudentService;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "StudentServlet",  urlPatterns = "/students")
public class StudentHome extends HttpServlet {
    private StudentService studentService;
    @Override
    public void init() {
        studentService = new StudentService();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> students = studentService.getAllStudents();
        request.setAttribute("students", students);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
        System.out.println("===> Display all STUDENTS!");
    }
}