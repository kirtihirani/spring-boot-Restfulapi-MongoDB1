package com.example.demoProject.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="CourseDB" )
public class Course {

    @Id
    private long cid;
    private String name;

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course(long cid, String name) {
        this.cid = cid;
        this.name = name;
    }

    public Course() {
    }
}
