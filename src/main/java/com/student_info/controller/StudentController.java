package com.student_info.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.student_info.entity.Student;
import com.student_info.exception.StudentNotFoundException;
import com.student_info.repository.StudentRepository;

@RestController
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@PostMapping("/student")
	public Student addStudent(@RequestBody Student newStudent) {
		
		return studentRepository.save(newStudent);
	}
	
	@GetMapping("/students")
	public List<Student> getAllStudents(){
		
		return studentRepository.findAll();
	}
	
	@GetMapping("/student/{rollNo}")
	public Student getStudentByRollno(@PathVariable Long rollNo) {
		
		return studentRepository.findById(rollNo).
				orElseThrow(()->new StudentNotFoundException(rollNo));
	}
	
	@PutMapping("/student/{rollNo}")
	public Student updateStudent(@RequestBody Student newStudent, 
			@PathVariable Long rollNo) {
		
		return studentRepository.findById(rollNo)
				.map(Student ->{
					//Student.setRollNo(newStudent.getRollNo());
					Student.setFirstName(newStudent.getFirstName());
					Student.setLastName(newStudent.getLastName());
					Student.setCourse(newStudent.getCourse());
					Student.setMobNo(newStudent.getMobNo());
					Student.setEmail(newStudent.getEmail());
					return studentRepository.save(Student);
				}).orElseThrow(() -> new StudentNotFoundException(rollNo));
		
	}
	
	
	@DeleteMapping("/student/{rollNo}")
	public String deleteStudent(@PathVariable Long rollNo) {
		
		if(!studentRepository.existsById(rollNo)){
			throw new StudentNotFoundException(rollNo);
		}
		
		studentRepository.deleteById(rollNo);
		
		return "Student with rollno: "+rollNo+" deleted successesfully!!!";
	}
}
