package com.spring.rest.jackson.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.jackson.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {

	
	List<Student> obj=new ArrayList<>();
	
	//Define @PostController to load the student 
	
	
	@PostConstruct				
	public void loadData()
	{
		obj.add(new Student("Soumya", "Dip"));
		obj.add(new Student("Soumya", "Dip"));
		obj.add(new Student("Soumya", "Dip"));
		
	}
	
	
	@GetMapping("/students")
	public List<Student> getStudents()
	{
		
	/*	List<Student> obj=new ArrayList<>();
		
		obj.add(new Student("Soumya", "Dip"));
		obj.add(new Student("Soumya", "Dip"));
		obj.add(new Student("Soumya", "Dip"));
	*/


		return obj;
	}
	
	
	//Retrive a single student  [/student/{studentID}]
	
	@GetMapping("/students/{studentID}")
	public Student getStudent(@PathVariable int studentID)
	{
		//check the StudentID aginst the list Size
		
		if(studentID  >= obj.size() && (studentID<0))
		{
			throw new StudentNotFoundException("Student is not found -"+ studentID);
		}
		
	
		return obj.get(studentID);
		
	}
	
}
