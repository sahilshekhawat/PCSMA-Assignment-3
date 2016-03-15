package com.example;

import com.example.config.SpringMongoConfig;
import com.example.model.Student;
import com.example.model.Teacher;
import com.example.repository.StudentRepository;
import com.example.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

@SpringBootApplication
public class PcsmaAssignment3Application implements CommandLineRunner{


	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	public static void main(String[] args) {
		SpringApplication.run(PcsmaAssignment3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		studentRepository.deleteAll();
		studentRepository.save(new Student("sahil", "sahil", "2013083"));
		teacherRepository.save(new Teacher("varsha", "varsha"));

		for(Student student: studentRepository.findAll()){
			System.out.println(student);
		}

		System.out.println(studentRepository.findByName("sahil"));
	}
}
