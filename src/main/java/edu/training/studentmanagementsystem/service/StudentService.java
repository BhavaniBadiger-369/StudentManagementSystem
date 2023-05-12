package edu.training.studentmanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.training.studentmanagementsystem.dao.AdminDao;
import edu.training.studentmanagementsystem.dao.StudentDao;
import edu.training.studentmanagementsystem.entity.Admin;
import edu.training.studentmanagementsystem.entity.Student;
import edu.training.studentmanagementsystem.exception.AdminNotFoundByIdException;
import edu.training.studentmanagementsystem.exception.StudentNotFoundByIdException;
import edu.training.studentmanagementsystem.util.ResponseStructure;

@Service
public class StudentService {
	
	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private AdminDao adminDao;
	
	public ResponseEntity<ResponseStructure<Student>> saveStudent(Student student , int adminId) {
		
		//finding the admin present in database
	Admin admin=	adminDao.findAdminById(adminId);
	
	//fetching the existing student list with admin
	List<Student>students =  admin.getStudents();
	
	//assigning the new student to the existing student list
	students.add(student);
	
	//setting the student list with new student to the admin
	admin.setStudents(students);
	
	//saving student to the database
	Student student2 = studentDao.saveStudent(student);
	
	//updating the student to the admin in the database
	adminDao.saveAdmin(admin);
	
	ResponseStructure<Student> responseStructure =new ResponseStructure<>();
	responseStructure.setStatusCode(HttpStatus.CREATED.value());
	responseStructure.setMessage("Student Saved Successfully!");
	responseStructure.setData(student2);
	return new ResponseEntity<ResponseStructure<Student>>(responseStructure,HttpStatus.CREATED);
	
		
	}
	
	public ResponseEntity<ResponseStructure<Student>> findStudentById(int studentId) {
	Student student=	studentDao.findStudentById(studentId);
	if(student!=null){
		ResponseStructure<Student> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.FOUND.value());
		responseStructure.setMessage("Student Fetched Successfully!");
		responseStructure.setData(student);
		return new ResponseEntity<ResponseStructure<Student>>(responseStructure, HttpStatus.FOUND);
	}else {
		
		throw new StudentNotFoundByIdException("Failed to Find Student!");
	}
	}
	
        public ResponseEntity<ResponseStructure<Student>> updateStudentById(int studentId, Student student) {
      Student student2 = studentDao.updateStudentById(studentId, student);
      if(student2!=null){
  		ResponseStructure<Student> responseStructure = new ResponseStructure<>();
  		responseStructure.setStatusCode(HttpStatus.OK.value());
  		responseStructure.setMessage("Student Updated Successfully!");
  		responseStructure.setData(student2);
  		return new ResponseEntity<ResponseStructure<Student>>(responseStructure, HttpStatus.OK);
  	}else {
  		throw new StudentNotFoundByIdException("Failed to Update Student!");
  	}
  	}
       
        public  ResponseEntity<ResponseStructure<Student>> deleteStudentById(int studentId, int adminId) {
	Student student= studentDao.findStudentById(studentId);
	if(student!=null) {
		
		Admin admin= adminDao.findAdminById(adminId);
		
		if(admin!=null) {
		List<Student> students = admin.getStudents();
		students.remove(student);
		admin.setStudents(students);
		adminDao.updateAdminById(adminId, admin);
		
     	Student student2 =	studentDao.deleteStudentById(studentId);
	
	    ResponseStructure<Student> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Student Deleted Successfully!");
		responseStructure.setData(student2);
		return new ResponseEntity<ResponseStructure<Student>>(responseStructure, HttpStatus.OK);
		
		}else {
			 throw new AdminNotFoundByIdException("Failed to delete student");
		}
		
	}else {
		
		Admin admin= adminDao.findAdminById(adminId);
		if(admin==null) {
			
	throw new AdminNotFoundByIdException("Failed to delete student because both Admin and student are not Present with this Id!");
		
		} else 
		throw new StudentNotFoundByIdException("Failed to Delete Student!");
	}
}

public ResponseEntity<ResponseStructure<List<Student>>> getStudents(int adminId){
Admin admin=adminDao.findAdminById(adminId);
if(admin!=null) {
	List<Student> students= studentDao.getStudents(adminId);
	if(students!=null) {
		
     if(students.isEmpty()) {
	throw new StudentNotFoundByIdException("Failed to find students!");
	
}else {
	
	    ResponseStructure<List<Student>> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.FOUND.value());
		responseStructure.setMessage("Student List Found Successfully!");
		responseStructure.setData(students);
		return new ResponseEntity<ResponseStructure<List<Student>>>(responseStructure, HttpStatus.FOUND);
}
	}else {
	throw new StudentNotFoundByIdException("Failed to Find  Students List !");
	}

}else {
	throw new AdminNotFoundByIdException("Failed to find Students!");
}
	
	}
}
