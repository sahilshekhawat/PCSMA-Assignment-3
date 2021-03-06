package com.example.controller;

import com.example.model.Course;
import com.example.model.Student;
import com.example.model.Teacher;
import com.example.repository.CourseRepository;
import com.example.repository.StudentRepository;
import com.example.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@Controller
public class TeacherController {
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;

    @RequestMapping(value="/api/teacher/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Teacher> getTeacher(@PathVariable("name") String name){
        return new ResponseEntity<Teacher>(teacherRepository.findByName(name), HttpStatus.OK);
    }

    @RequestMapping(value="/api/teacher", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody void createTeacher(HttpServletRequest request,
                                         HttpServletResponse response){
        String name =  request.getParameter("name");
        String password =  request.getParameter("password");

        teacherRepository.save(new Teacher(name, password));
        HttpSession session = request.getSession();
        session.setAttribute("name", name);
        session.setAttribute("type", "teacher");

        response.setStatus(200);
        response.setHeader("message", "success");
        response.setContentType("application/json");
        try {
            PrintWriter out = response.getWriter();
            out.println("{ \"message\": \"created teacher\" }");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value="/api/teacher/addcourse", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody void addcourse(HttpServletRequest request,
                                        HttpServletResponse response){
        String user_name = (String) request.getSession().getAttribute("name");
        Teacher teacher = teacherRepository.findByName(user_name);

        String course_name =  request.getParameter("name");
        Course course = courseRepository.findByName(course_name);
        System.out.println(user_name);
        teacher.addCourse(course);
        teacherRepository.save(teacher);

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
