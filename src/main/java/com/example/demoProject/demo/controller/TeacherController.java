package com.example.demoProject.demo.controller;

import com.example.demoProject.demo.model.Course;
import com.example.demoProject.demo.model.Teacher;
import com.example.demoProject.demo.repository.CourseRepository;
import com.example.demoProject.demo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeacherController  {
    @Autowired
    private TeacherRepository repo;
    @Autowired
    private CourseRepository courseRepo;

    @PostMapping("/teacher")
    public Teacher addTeacher(@RequestBody Teacher teacher){
        return repo.save(teacher);

    }

    @GetMapping("/teacher")
    public List<Teacher> getAllTeachers(){
        return repo.findAll();
    }

    @GetMapping("/teacher/{id}")
        public Teacher getByID(@PathVariable Long id){
            return repo.findById(id).orElseThrow();
        }

//

    @PostMapping("/teacher/{tid}/course")
    public void addCourseToTeacher(@PathVariable long tid, @RequestBody Course course){
        Teacher teacher = repo.findById(tid).orElseThrow();
        long crsid = course.getCid();
        System.out.println(crsid);
        Course _course =  courseRepo.findById(crsid).orElse(null);

        if(_course!=null){
            teacher.getCoursesTaught().add(_course);
            repo.save(teacher);
        }
        else{
            teacher.getCoursesTaught().add(course);
            courseRepo.save(course);
            repo.save(teacher);
          }

    }

    @DeleteMapping("teacher/{tid}/course/{cid}")
    public String deleteCourseFromTeacher(@PathVariable long tid,@PathVariable long cid ){
        Teacher teacher = repo.findById(tid).orElseThrow();

        Course crs = teacher.getCoursesTaught().stream().filter(c->c.getCid()==cid).findFirst().orElseThrow();
        if(crs!=null){
            teacher.getCoursesTaught().remove(crs);
            repo.save(teacher);
            return "course deleted";
        }
        return "course is null";

    }
}
