package com.cg.module3.finalexamination_module3.controller;

import com.cg.module3.finalexamination_module3.Model.Classroom;
import com.cg.module3.finalexamination_module3.Model.Student;
import com.cg.module3.finalexamination_module3.service.StudentService;
import com.cg.module3.finalexamination_module3.service.imp.impStudent;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "DeleteServlet",  urlPatterns = "/delete-student")
public class DeleteServlet extends HttpServlet {
    private StudentService studentService;

    @Override
    public void init() throws ServletException {
        studentService = new StudentService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        impStudent impStudent = new impStudent();
//        Student student = impStudent.getByIdStudent(id);
        boolean check = false;
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
