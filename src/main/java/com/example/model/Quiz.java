package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;


@Document(collection = "quizs")
public class Quiz {
    @Id
    private String id;

    String name;
    String question;
    String time;
    ArrayList<String> options;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void addOptions(String options) {
        this.options.add(options);
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", question='" + question + '\'' +
                ", options=" + options +
                '}';
    }

    public Quiz(String name, String question, String time) {
        this.name = name;
        this.question = question;
        this.time = time;
    }
}
