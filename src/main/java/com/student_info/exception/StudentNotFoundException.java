package com.student_info.exception;

public class StudentNotFoundException extends RuntimeException {

	public StudentNotFoundException(Long rollNo) {
		
		super("could not found the student with rollno: "+rollNo);
	}
	
}
