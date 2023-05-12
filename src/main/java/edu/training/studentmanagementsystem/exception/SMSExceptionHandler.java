package edu.training.studentmanagementsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import edu.training.studentmanagementsystem.util.ResponseStructure;

@RestControllerAdvice
public class SMSExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>>AdminNotFoundById(AdminNotFoundByIdException ex){
		ResponseStructure<String> responseStructure =new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(ex.getMessage());
		responseStructure.setData("Admin Not Found with the requested Id!");
		return new ResponseEntity<ResponseStructure<String>>(responseStructure , HttpStatus.NOT_FOUND);
		
	}
	
	

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>>StudentNotFoundById(StudentNotFoundByIdException ex){
		ResponseStructure<String> responseStructure =new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(ex.getMessage());
		responseStructure.setData("Student Not Found with the requested AdminId!");
		return new ResponseEntity<ResponseStructure<String>>(responseStructure , HttpStatus.NOT_FOUND);
		
	}

}
