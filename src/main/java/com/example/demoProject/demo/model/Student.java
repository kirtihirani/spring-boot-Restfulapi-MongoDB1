package com.example.demoProject.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Document(collection = "StudentDB")
public class Student {
    @Id
    private Long sid;



    private String name;
    private int age;
    private List<Course> courses = new ArrayList<>();

    public Student() {
    }

    public Student(long sid, String name, int age) {
        this.sid = sid;
        this.name = name;
        this.age = age;
        this.courses = new ArrayList<>();
    }

    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

}
