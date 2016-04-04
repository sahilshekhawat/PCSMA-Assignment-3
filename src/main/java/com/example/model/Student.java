package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "students")
public class Student {

    @Id
    private String id;

    String name;
    String password;
    String rollno;
    String email;



    ArrayList<Course> courses = new ArrayList<Course>();

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void addCourse(Course course){
        this.courses.add(course);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getName() {
        return name;
    }

    public String getRollno() {
        return rollno;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", rollno='" + rollno + '\'' +
                ", courses=" + courses +
                '}';
    }



    public Student(String name, String email){
        this.name = name;
        this.email = email;
        System.out.println("Here 0");
    }

}
