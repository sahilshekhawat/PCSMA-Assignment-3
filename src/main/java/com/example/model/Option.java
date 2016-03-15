package com.example.model;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.HashMap;

/**
 * Created by sahil on 3/13/16.
 */
public class Option {

    String value;

    public Boolean getAnswer() {
        return answer;
    }

    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }

    Boolean answer = false;
    HashMap<String, Boolean> votes = new HashMap<>();

    public HashMap<String, Boolean> getVotes() {
        return votes;
    }

    public void addVote(String student, Boolean anonymous) {
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
