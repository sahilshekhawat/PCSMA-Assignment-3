package com.example.controller;

import com.example.model.Student;
import com.example.model.Teacher;
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
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String teacherLogin(){
        return "login";
    }

    @RequestMapping(value = "/profile/{name}", method = RequestMethod.GET)
    public String getProfileHtml(){
        return "profile";
    }

    @RequestMapping(value = "/course/{name}", method = RequestMethod.GET)
    public String getCourseHtml(){
        return "course";
    }

    @RequestMapping(value = "/quiz/{name}", method = RequestMethod.GET)
    public String getQuizHtml(){
        return "quiz";
    }

    @RequestMapping(value = "/analyze/{name}", method = RequestMethod.GET)
    public String getAnalyzeHtml(){
        return "analyze";
    }

    @RequestMapping(value="/api/login", method = RequestMethod.POST)
    public @ResponseBody void userLogin(HttpServletRequest request,
                                        HttpServletResponse response){
        String name =  request.getParameter("name");
        String password =  request.getParameter("password");
        String type = request.getParameter("type");
        String userpassword;
        if(type.equals("student")){
            Student student = studentRepository.findByName(name);
            userpassword = student.getPassword();
        } else{
            Teacher teacher = teacherRepository.findByName(name);
            userpassword = teacher.getPassword();
        }

        System.out.println(name);
        System.out.println(password);
        if(request.getSession().getAttribute("name") != null){
            System.out.println("Already Logged In!");
            response.setHeader("message", "loggedin");
            response.setContentType("application/json");
            try {
                PrintWriter out = response.getWriter();
                out.println("{ \"message\": \"already loggedin\" }");
            } catch (IOException e){
                e.printStackTrace();
            }
        } else {
            if (userpassword.equals(password)) {
                //logged In
                HttpSession session = request.getSession();
                session.setAttribute("name", name);
                session.setAttribute("type", type);
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

    @RequestMapping(value="/api/logout", method=RequestMethod.GET)
    public void userLogout(HttpServletRequest request,
                           HttpServletResponse response){
        HttpSession session = request.getSession();
        session.removeAttribute("name");
        response.setHeader("message", "loggedin");
        response.setContentType("application/json");
        try {
            PrintWriter out = response.getWriter();
            out.println("{ \"message\": \"logged out\" }");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
