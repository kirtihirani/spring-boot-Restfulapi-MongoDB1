package com.example.demoProject.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Teacher {
    @Id
    private long tid;
    private String name;
    private String department;
    private List<Course> coursesTaught = new ArrayList<>();

    public Teacher(long tid, String name, String department) {
        this.tid = tid;
        this.name = name;
        this.department = department;
        this.coursesTaught = new ArrayList<>();
    }

    public Teacher() {
    }

    public long getTid() {
        return tid;
    }

    public void setId(long tid) {
        this.tid = tid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }



    public List<Course> getCoursesTaught() {
        return coursesTaught;
    }

    public void setCoursesTaught(List<Course> coursesTaught) {
        this.coursesTaught = coursesTaught;
    }

    public void addCourse(Course course){
        this.coursesTaught.add(course);
    }

//    public void removeCourse(String courseId){
//        Course course = this.coursesTaught.stream().filter(c-> c.getId().equals(courseId)).findFirst().orElseThrow();
//        this.coursesTaught.remove(course);
//    }
}
