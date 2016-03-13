package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;


@Document(collection = "quizes")
public class Quiz {
    @Id
    private String id;
    String question;
    ArrayList<String> options;

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
                ", question='" + question + '\'' +
                ", options=" + options +
                '}';
    }

    public Quiz(String question) {
        this.question = question;
    }
}
