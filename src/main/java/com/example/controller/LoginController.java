package com.example.controller;

import com.example.model.Student;
import com.example.repository.StudentRepository;
import com.example.repository.TeacherRepository;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@Controller
public class LoginController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String teacherLogin(){
        return "login";
    }

    @RequestMapping(value="/api/studentlogin", method = RequestMethod.POST)
    public @ResponseBody void studentLogin(HttpServletRequest request,
                                           HttpServletResponse response){


        String name =  request.getParameter("name");
        String password =  request.getParameter("password");
        Student student = studentRepository.findByName(name);

        System.out.println(name);
        System.out.println(password);
        if(request.getSession().getAttribute("name") != null){
            System.out.println("Already Logged In!");
            response.setHeader("message", "loggedin");
            response.setContentType("application/json");
            try {
                PrintWriter out = response.getWriter();
                out.println("{ \"message\": \"loggedin\" }");
            } catch (IOException e){
                e.printStackTrace();
            }
        } else {


            if (student.getPassword().equals(password)) {
                //logged In
                HttpSession session = request.getSession();
                session.setAttribute("name", name);
                System.out.println("Success!!");

                response.setStatus(200);
                response.setHeader("message", "success");
                response.setContentType("application/json");
                try {
                    PrintWriter out = response.getWriter();
                    out.println("{ \"message\": \"success\" }");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                // Failed
                System.out.println("Failed!!");
                response.setHeader("message", "failed");
                response.setContentType("application/json");
                try {
                    PrintWriter out = response.getWriter();
                    out.println("{ \"message\": \"failed\" }");
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }
    }

//
//    @RequestMapping(value="/api/teacherlogin", method = RequestMethod.POST)
//    public @ResponseBody void teacherLogin(HttpServletRequest request,
//                                           HttpServletResponse response){
//
//
//        String name =  request.getParameter("name");
//        String password =  request.getParameter("password");
//        Student student = studentRepository.findByName(name);
//
//        System.out.println(name);
//        System.out.println(password);
//        if(request.getSession().getAttribute("name") != null){
//            System.out.println("Already Logged In!");
//            response.setHeader("message", "loggedin");
//            response.setContentType("application/json");
//            try {
//                PrintWriter out = response.getWriter();
//                out.println("{ \"message\": \"loggedin\" }");
//            } catch (IOException e){
//                e.printStackTrace();
//            }
//        }
//
//
//        if(student.getPassword().equals(password)){
//            //logged In
//            HttpSession session = request.getSession();
//            session.setAttribute("name", name);
//            System.out.println("Success!!");
//
//            response.setStatus(200);
//            response.setHeader("message", "success");
//            response.setContentType("application/json");
//            try {
//                PrintWriter out = response.getWriter();
//                out.println("{ \"message\": \"success\" }");
//            } catch (IOException e){
//                e.printStackTrace();
//            }
//
//        } else{
//            // Failed
//            System.out.println("Failed!!");
//            response.setHeader("message", "failed");
//            response.setContentType("application/json");
//            try {
//                PrintWriter out = response.getWriter();
//                out.println("{ \"message\": \"failed\" }");
//            } catch (IOException e){
//                e.printStackTrace();
//
//            }
//        }
//    }
}
