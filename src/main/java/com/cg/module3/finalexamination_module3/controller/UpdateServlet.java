package com.cg.module3.finalexamination_module3.controller;

import com.cg.module3.finalexamination_module3.Model.Classroom;
import com.cg.module3.finalexamination_module3.service.ClassroomService;
import com.cg.module3.finalexamination_module3.service.StudentService;
import com.cg.module3.finalexamination_module3.Model.Student;
import com.cg.module3.finalexamination_module3.service.imp.impStudent;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "UpdateServlet", urlPatterns = "/update-student")
public class UpdateServlet extends HttpServlet {
    private StudentService studentService;
    private ClassroomService classroomService;
    @Override
    public void init() {
        studentService = new StudentService();
        classroomService = new ClassroomService();
    }

    /**
     * GET ID STUDENT AND UPDATE
     * @param req REQ
     * @param resp RES
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        Student student = studentService.getStudentById(id);
        List<Classroom> classrooms = classroomService.getAllClassroom();
        req.setAttribute("student", student);
        req.setAttribute("classrooms", classrooms);
        req.getRequestDispatcher("/update.jsp").forward(req, resp);
        System.out.println("==> Ham nay UPDATE Student.");

    }

    /**
     * Updated success
     * @param req REQ
     * @param resp RES
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        Student student = studentService.getStudentById(id);

        String nameStudent = req.getParameter("nameStudent");
        String email = req.getParameter("email");
        LocalDate dob = LocalDate.parse(req.getParameter("dob")); // Chú ý kiểm tra định dạng ngày
        String address = req.getParameter("address");
        String phoneNumber = req.getParameter("phoneNumber");
        int classID = Integer.parseInt(req.getParameter("classID"));

        student.setNameStudent(nameStudent);
        student.setEmail(email);
        student.setAddress(address);
        student.setDob(dob);
        student.setPhoneNumber(phoneNumber);
        student.setClassID(classID);

        impStudent impStudent = new impStudent();
        boolean check = impStudent.editStudent(student);
        if (check){
            HttpSession session = req.getSession();
            session.setAttribute("Edit","Successfully");
            session.setMaxInactiveInterval(15);
//          Redirect back to students page
            resp.sendRedirect(req.getContextPath() + "/students");
            System.out.println("======> updated SUCCESS!");
        }
    }
}
