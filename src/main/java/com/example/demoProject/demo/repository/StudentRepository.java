package com.example.demoProject.demo.repository;

import com.example.demoProject.demo.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StudentRepository extends MongoRepository<Student,Long> {
    List<Student> findByName(String name);
}
