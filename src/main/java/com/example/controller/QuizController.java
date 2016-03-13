package com.example.controller;

import com.example.model.Course;
import com.example.model.Quiz;
import com.example.model.Student;
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
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by sahil on 3/11/16.
 */
@Controller
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;

    @RequestMapping(value="/api/quiz/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Quiz> getStudent(@PathVariable("name") String name){
        return new ResponseEntity<Quiz>(quizRepository.findByName(name), HttpStatus.OK);
    }

    @RequestMapping(value="/api/quiz", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody void createQuiz(HttpServletRequest request,
                                         HttpServletResponse response){
        String name =  request.getParameter("name");
        String question = request.getParameter("question");
        String time = request.getParameter("time");

        quizRepository.save(new Quiz(name, question, time));

        response.setStatus(200);
        response.setHeader("message", "success");
        response.setContentType("application/json");
        try {
            PrintWriter out = response.getWriter();
            out.println("{ \"message\": \"created quiz\" }");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
