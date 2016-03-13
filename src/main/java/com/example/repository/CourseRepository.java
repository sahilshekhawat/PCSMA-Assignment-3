package com.example.repository;

import com.example.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by sahil on 3/11/16.
 */
public interface CourseRepository extends MongoRepository<Course, String> {

    public Course findByName(String name);
    public Course findByCode(String code);
}
