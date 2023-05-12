package edu.training.studentmanagementsystem.dao;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import edu.training.studentmanagementsystem.entity.Student;
import edu.training.studentmanagementsystem.repository.AdminRepo;
import edu.training.studentmanagementsystem.repository.StudentRepo;

@Repository
public class StudentDao {
	
	@Autowired
	private  StudentRepo studentRepo;
	@Autowired
	private AdminRepo adminRepo;
	
	public Student saveStudent(Student student) {
	return	studentRepo.save(student);
	}
	
	public Student deleteStudent(Student student) {
		studentRepo.delete(student);
		return student;
	}

	public Student findStudentById(int studentId) {
	Optional<Student> optional = studentRepo.findById(studentId);
		if(optional.isEmpty()) {
			return null;
			
		}else {
			Student student=optional.get();
			return student;
		}
		}
	
	public Student updateStudentById(int studentId, Student student) {
		Optional<Student> optional =	studentRepo.findById(studentId);
		if(optional.isEmpty()) {
			return null;
		}else {
			student.setStudentId(studentId);
			return studentRepo.save(student);
		}
	}
	public Student deleteStudentById(int studentId){
	Optional<Student> optional = studentRepo.findById(studentId);
		if(optional.isEmpty()) {
			return null;
		}else {
			studentRepo.deleteById(studentId);	
			return optional.get();
		}
			
}
	public List<Student> getStudents(int adminId){
	Optional<List<Student>> optional = adminRepo.getStudents(adminId);
	if(optional.isEmpty()) {
		return null;
	}else {
		return optional.get();
	}
	}
	}

