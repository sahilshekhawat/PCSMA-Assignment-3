package com.example.controller;

import com.example.model.Course;
import com.example.model.Option;
import com.example.model.Quiz;
import com.example.model.Student;
import com.example.repository.QuizRepository;
import com.example.repository.StudentRepository;
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
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;


    @Autowired
    private StudentRepository studentRepository;

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
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
        String startingTime = sdf.format(date);

        quizRepository.save(new Quiz(name, question, time, ""));

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

    @RequestMapping(value="/api/quiz/{name}/startquiz",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody void startQuiz(HttpServletRequest request,
                                        HttpServletResponse response,
                                        @PathVariable("name") String quiz_name){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
        String startingTime = sdf.format(date);

        Quiz quiz = quizRepository.findByName(quiz_name);

        quiz.setStartingTime(startingTime);
        quizRepository.save(quiz);


        response.setStatus(200);
        response.setHeader("message", "success");
        response.setContentType("application/json");
        try {
            PrintWriter out = response.getWriter();
            out.println("{ \"message\": \"started quiz\" }");
        } catch (IOException e){
            e.printStackTrace();
        }

    }


    @RequestMapping(value="/api/quiz/{name}/addoption",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody void addOption(HttpServletRequest request,
                                        HttpServletResponse response,
                                        @PathVariable("name") String quiz_name){

        String value = request.getParameter("value");
        System.out.println(quiz_name);
        Quiz quiz = quizRepository.findByName(quiz_name);

        Option option = new Option(value);
        if(request.getParameter("answer") != null){
            if(request.getParameter("answer").equals("true")){
                option.setAnswer(true);
            }
        }

        quiz.addOptions(option);
        quizRepository.save(quiz);

        response.setStatus(200);
        response.setHeader("message", "success");
        response.setContentType("application/json");
        try {
            PrintWriter out = response.getWriter();
            out.println("{ \"message\": \"added option\" }");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value="/api/quiz/{name}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody void vote(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @PathVariable("name") String quiz_name){

//        HttpSession session = request.getSession();
        String user_name = request.getParameter("name");

//        Student student = studentRepository.findByName(user_name);
        String anonymous = request.getParameter("anonymous");
        String value = request.getParameter("value");

        System.out.println("vote request start");
        System.out.println(anonymous);
        System.out.println(value);
        System.out.println(user_name);
        System.out.println("vote request end");

        Boolean bool_anonymous;
        if(anonymous.equals("true")){
            bool_anonymous = true;
        } else{
            bool_anonymous = false;
        }
        Quiz quiz = quizRepository.findByName(quiz_name);
        for(Option option: quiz.getOptions()){
            if(option.getValue().equals(value)){
                option.addVote(user_name, bool_anonymous);
                break;
            }
        }

        quizRepository.save(quiz);

        response.setStatus(200);
        response.setHeader("message", "success");
        response.setContentType("application/json");
        try {
            PrintWriter out = response.getWriter();
            out.println("{ \"message\": \"voted\" }");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
