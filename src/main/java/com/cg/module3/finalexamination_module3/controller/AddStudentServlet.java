package com.cg.module3.finalexamination_module3.controller;

import com.cg.module3.finalexamination_module3.Model.Classroom;
import com.cg.module3.finalexamination_module3.Model.Student;
import com.cg.module3.finalexamination_module3.service.ClassroomService;
import com.cg.module3.finalexamination_module3.service.StudentService;
import com.cg.module3.finalexamination_module3.service.imp.impStudent;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "AddStudentServlet",  urlPatterns = "/add-student")
public class AddStudentServlet extends HttpServlet {
    private final StudentService studentService = new StudentService();
    private final ClassroomService classroomService = new ClassroomService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Classroom> classrooms = classroomService.getAllClassroom();
        req.setAttribute("classrooms", classrooms);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/create.jsp");
        dispatcher.forward(req, resp);
        System.out.println("===> Call method ADD STUDENT.");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");

        String name = request.getParameter("nameStudent");
        String email = request.getParameter("email");
        LocalDate dob = LocalDate.parse(request.getParameter("dob")); // Chú ý kiểm tra định dạng ngày
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        int classID = Integer.parseInt(request.getParameter("classID"));

        Student student = new Student();
        student.setNameStudent(name);
        student.setEmail(email);
        student.setDob(dob);
        student.setAddress(address);
        student.setPhoneNumber(phoneNumber);
        student.setClassID(classID);

        impStudent impStudent = new impStudent();
        try {
            boolean check = impStudent.addNewStudent(student);
            if (check){
                response.sendRedirect(request.getContextPath() + "/students");
                System.out.println("===> Add new STUDENT SUCCESSFULLY.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
