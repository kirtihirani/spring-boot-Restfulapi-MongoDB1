package com.example.demoProject.demo.repository;

import com.example.demoProject.demo.model.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeacherRepository extends MongoRepository<Teacher,Long> {
}
