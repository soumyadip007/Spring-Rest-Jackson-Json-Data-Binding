package com.spring.rest.jackson.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.rest.jackson.entity.Student;

@Controller
@RequestMapping("/api")
public class StudentRestController {

	
	
	@GetMapping("/students")
	public List<Student> getStudents()
	{
		
		List<Student> obj=new ArrayList<>();
		
		obj.add(new Student("Soumya", "Dip"));
		obj.add(new Student("Soumya", "Dip"));
		obj.add(new Student("Soumya", "Dip"));
		
		return obj;
	}
}
