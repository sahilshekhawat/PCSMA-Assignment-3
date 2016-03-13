package com.example.controller;

import com.example.model.Course;
import com.example.model.Quiz;
import com.example.model.Student;
import com.example.repository.CourseRepository;
import com.example.repository.QuizRepository;
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
import javax.ws.rs.Path;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private QuizRepository quizRepository;

    @RequestMapping(value="/api/course/{name}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Course> getCourse(@PathVariable("name") String name){
        return new ResponseEntity<Course>(courseRepository.findByName(name), HttpStatus.OK);
    }

    @RequestMapping(value="/api/course", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody void createCourse(HttpServletRequest request,
                                            HttpServletResponse response){
        String name =  request.getParameter("name");
        String code = request.getParameter("code");

        courseRepository.save(new Course(name, code));

        response.setStatus(200);
        response.setHeader("message", "success");
        response.setContentType("application/json");
        try {
            PrintWriter out = response.getWriter();
            out.println("{ \"message\": \"created course\" }");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value="/api/course/{name}/addquiz", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody void addquiz(HttpServletRequest request,
                                      HttpServletResponse response,
                                      @PathVariable("name") String course_name){
        String quiz_name =request.getParameter("name");
        Quiz quiz = quizRepository.findByName(quiz_name);

        Course course = courseRepository.findByName(course_name);

        course.addQuiz(quiz);
        response.setStatus(200);
        response.setHeader("message", "success");
        response.setContentType("application/json");
        try {
            PrintWriter out = response.getWriter();
            out.println("{ \"message\": \"Added quiz\" }");
        } catch (IOException e){
            e.printStackTrace();
        }


    }
}
