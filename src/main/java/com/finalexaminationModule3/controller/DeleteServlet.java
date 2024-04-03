package com.finalexaminationModule3.controller;

import com.finalexaminationModule3.service.StudentService;
import com.finalexaminationModule3.service.imp.impStudent;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteServlet",  urlPatterns = "/delete-student")
public class DeleteServlet extends HttpServlet {
    StudentService studentService;

    @Override
    public void init() {
        studentService = new StudentService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        impStudent impStudent = new impStudent();
        boolean check;
        try {
            check = impStudent.delStudent(id);
            if (check){
                HttpSession session = request.getSession();
                session.setAttribute("Del","Successfully");
                session.setMaxInactiveInterval(15);
                response.sendRedirect(request.getContextPath() + "/students");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
