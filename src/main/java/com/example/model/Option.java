package com.example.model;

import java.util.HashMap;

/**
 * Created by sahil on 3/13/16.
 */
public class Option {

    String value;
    HashMap<Student, Boolean> votes = new HashMap<>();

    public HashMap<Student, Boolean> getVotes() {
        return votes;
    }

    public void addVote(Student student, Boolean anonymous) {
        this.votes.put(student, anonymous);
    }

    public String getValue() {

        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Option(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Option{" +
                "value='" + value + '\'' +
                ", votes=" + votes +
                '}';
    }
}
