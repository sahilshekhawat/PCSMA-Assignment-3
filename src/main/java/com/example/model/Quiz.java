package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

/**
 * Created by sahil on 3/11/16.
 */
@Document(collection = "quizes")
public class Quiz {
    @Id
    private String id;
    String question;
    ArrayList<String> options;
}
