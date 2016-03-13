package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by sahil on 3/11/16.
 */
@Document(collection = "courses")
public class Course {

    @Id
    private String id;

    String name;
    String code;

}
