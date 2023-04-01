package com.example.demoProject.demo.controller;

import com.example.demoProject.demo.model.Course;
import com.example.demoProject.demo.model.Student;
import com.example.demoProject.demo.repository.CourseRepository;
import com.example.demoProject.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentRepository sturepo;

    @Autowired
    private CourseRepository courseRepo;

    @PostMapping("/student")
    public Student addStudent(@RequestBody Student student){
        return sturepo.save(student);
    }

    @GetMapping("/student")
    public List<Student> getAllStudents(){
        return sturepo.findAll();
    }

    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable long id){
        return sturepo.findById(id).orElseThrow();
    }

    @PutMapping("/student/{id}")
    public Student updateStudentById(@PathVariable long id, @RequestBody Student student){
        Student st = sturepo.findById(id).orElseThrow();
        return sturepo.save(st);
    }

//    @PostMapping("/student/{sid}/course")
//    public Course addCourseToStudent(@PathVariable String sid, @RequestBody Course courseRequest) throws Exception{
//        Course course = sturepo.findById(sid).map(student -> {
//            String courseId = courseRequest.getId();
//
//            // tag is existed
//            Course _course;
//            if (courseId != null) {
//                try {
//                    _course = courseRepo.findById(courseId).orElseThrow(() -> new Exception());
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//                student.addCourse(_course);
//                sturepo.save(student);
//                return _course;
//            }
//
//            // add and create new Tag
//            student.addCourse(courseRequest);
//            return courseRepo.save(courseRequest);
//        }).orElseThrow(() -> new Exception("Not found Student with id = " + sid));
//
//        return course;
//    }

//    @DeleteMapping("/students/{sid}/courses/{cid}")
//    public void deleteCourseFromStudent(@PathVariable String sid, @PathVariable String cid)throws Exception{
//        Student stu = sturepo.findById(sid).orElseThrow(() -> new Exception());
//        stu.removeCourse(cid);
//        sturepo.save(stu);
//    }
    @DeleteMapping("/student/{sid}")
    public void deleteById(@PathVariable long sid){
        sturepo.deleteById(sid);
    }

    @PostMapping("/student/{sid}/course")
    public Student addCourseToStudent (@PathVariable long sid, @RequestBody Course course) {
        Student student = sturepo.findById(sid).orElseThrow();
        long cid = course.getCid();
        Course crs  = courseRepo.findById(cid).orElse(null);
        if(crs==null){
            student.getCourses().add(course);
            sturepo.save(student);
            courseRepo.save(course);
        }
        else {
            student.getCourses().add(crs);
            sturepo.save(student);
        }
        return student;

    }

    @DeleteMapping("/student/{sid}/course/{cid}")
    public String deleteCourseFromStudent(@PathVariable long sid, @PathVariable long cid){
        Student st = sturepo.findById(sid).orElseThrow();
        Course crs = st.getCourses().stream().filter(c->c.getCid()==cid).findFirst().orElseThrow();
        if(crs!=null){
            st.getCourses().remove(crs);
            sturepo.save(st);
            return "course deleted";
        }
        return "course is null";
    }
}
