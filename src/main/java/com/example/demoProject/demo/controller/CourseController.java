package com.example.demoProject.demo.controller;

import com.example.demoProject.demo.model.Course;
import com.example.demoProject.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class CourseController {
    @Autowired
    private CourseRepository courseRepo;



    @PostMapping("/course")
    public Course addCourse(@RequestBody Course course){
        return courseRepo.save(course);
    }

    @GetMapping("/course")
    public List<Course> getAllCourses(){
        return courseRepo.findAll();
    }

    @GetMapping("/course/{id}")
    public Course getByIDd(@PathVariable long id){
        return courseRepo.findById(id).orElseThrow();
    }


    @PutMapping("/course/{id}")
    public Course updateCourseById(@PathVariable long id, @RequestBody Course course){
        Course st = courseRepo.findById(id).orElseThrow();
        st.setName(course.getName());
        return courseRepo.save(st);
    }

    @DeleteMapping("/course/{id}")
    public String deleteCourse(@PathVariable long id){
        courseRepo.deleteById(id);
        return "Deleted successfully";
    }
}
