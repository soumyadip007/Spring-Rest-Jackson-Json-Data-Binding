package com.spring.rest.jackson.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.jackson.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {

	
	
	@GetMapping("/students")
	public List<Student> getStudents()
	{
		return null;
	}
}
