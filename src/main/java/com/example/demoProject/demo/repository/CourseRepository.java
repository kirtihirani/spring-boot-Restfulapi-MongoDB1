package com.example.demoProject.demo.repository;

import com.example.demoProject.demo.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CourseRepository extends MongoRepository<Course,Long> {
    List<Course> findByName(String name);
}
