package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "courses")
public class Course {

    @Id
    private String id;

    String name;
    String code;
    ArrayList<Quiz> quizs = new ArrayList<>();

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", quizs=" + quizs +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ArrayList<Quiz> getQuizs() {
        return quizs;
    }

    public void addQuiz(Quiz quiz) {
        this.quizs.add(quiz);
    }

    public Course(String name, String code) {
        this.name = name;
        this.code = code;
    }
}
