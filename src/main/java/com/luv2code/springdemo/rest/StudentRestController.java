package com.luv2code.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	//write code to populate data only once
	private List<Student> theStudents;
	
	//@PostConstruct is called only once when the bean is created
	@PostConstruct
	public void loadData()
	{
		theStudents = new ArrayList<>();
		
		theStudents.add(new Student("Daniil","Medvedev"));
		theStudents.add(new Student("Alexander","Zverev"));
		theStudents.add(new Student("Matteo","Berrettini"));
		theStudents.add(new Student("Carlos","Alcaraz"));
	}
	
	@GetMapping("/students")
	public List<Student> getStudents()
	{
		//empty list
//		List<Student> theStudents = new ArrayList<>();
		
		//hard code values...later will add into db
//		theStudents.add(new Student("Daniil","Medvedev"));
//		theStudents.add(new Student("Alexander","Zverev"));
//		theStudents.add(new Student("Matteo","Berrettini"));
//		theStudents.add(new Student("Carlos","Alcaraz"));
		
		//return the list and jackson will convert it into JSON data
		return theStudents;
		
	}
	
	//write endpoint for "/students/{studentId}".......here we'll return by list index
	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable int studentId)
	{
		//check whether studentid is in range or else throw the exception
		if(studentId >= theStudents.size() || studentId < 0)
		{
			throw new StudentNotFoundException("StudentID not found - " +studentId);
		}
		//studentid is in range
		return theStudents.get(studentId);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
