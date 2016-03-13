package com.example.repository;

import com.example.model.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by sahil on 3/11/16.
 */

public interface TeacherRepository extends MongoRepository<Teacher, String> {

    public Teacher findByName(String name);
    public Teacher findById(String id);
}
