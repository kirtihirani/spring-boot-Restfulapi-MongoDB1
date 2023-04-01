package com.example.demoProject.demo;

import com.example.demoProject.demo.controller.StudentController;
import com.example.demoProject.demo.model.Course;
import com.example.demoProject.demo.model.Student;
import com.example.demoProject.demo.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Equals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private StudentController controller;

	@MockBean
	private StudentRepository repository;


	@Test
	void contextLoads() {
	}
	@Test
	public void getAllStudents(){
		when(repository.findAll()).thenReturn(Stream.of(new Student(12,"john",12),new Student(11,"sam",13)).collect(Collectors.toList()));
		assertEquals(2,controller.getAllStudents().size());
	}

	@Test
	public void saveStudent(){
		Student s = new Student(12,"sam",13);
		when(repository.save(s)).thenReturn(s);
		assertEquals(s,controller.addStudent(s));
	}

	@Test
	public void deleteStudent(){
		Student s = new Student(5,"sam",13);
		controller.deleteById(s.getSid());
		verify(repository,times(1)).deleteById(s.getSid());
	}


}
