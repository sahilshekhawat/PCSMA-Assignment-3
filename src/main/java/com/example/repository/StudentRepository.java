package com.example.repository;

import com.example.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by sahil on 3/11/16.
 */

public interface StudentRepository extends MongoRepository<Student, String> {
    public Student findByName(String name);
    public Student findById(String id);
}
