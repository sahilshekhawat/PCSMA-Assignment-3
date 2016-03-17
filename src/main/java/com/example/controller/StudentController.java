package com.example.controller;

import com.example.model.Course;
import com.example.model.Student;
import com.example.model.Teacher;
import com.example.repository.CourseRepository;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @RequestMapping(value="/api/student/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> getStudent(@PathVariable("name") String name){
        return new ResponseEntity<Student>(studentRepository.findByName(name), HttpStatus.OK);
    }

    @RequestMapping(value="/api/student", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody void createStudent(HttpServletRequest request,
                    HttpServletResponse response){
        String name =  request.getParameter("name");
        String password =  request.getParameter("password");
        String rollno = request.getParameter("rollno");

        studentRepository.save(new Student(name, password, rollno));
        HttpSession session = request.getSession();
        session.setAttribute("name", name);
        session.setAttribute("type", "student");

        response.setStatus(200);
        response.setHeader("message", "success");
        response.setContentType("application/json");
        try {
            PrintWriter out = response.getWriter();
            out.println("{ \"message\": \"created student\" }");
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    @RequestMapping(value="/api/student/addcourse", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody void addcourse(HttpServletRequest request,
                                        HttpServletResponse response){
//        String user_name = (String) request.getSession().getAttribute("name");

        String user_name =  request.getParameter("stu_name");
        String course_name =  request.getParameter("name");

        Student student = studentRepository.findByName(user_name);
        Course course = courseRepository.findByName(course_name);
        System.out.println(user_name);
        student.addCourse(course);
        studentRepository.save(student);

        response.setStatus(200);
        response.setHeader("message", "success");
        response.setContentType("application/json");
        try {
            PrintWriter out = response.getWriter();
            out.println("{ \"message\": \"added course\" }");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
