package edu.training.studentmanagementsystem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity

public class Student {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int studentId;
	private String studentName;
	private String studentEmil;
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentEmil() {
		return studentEmil;
	}
	public void setStudentEmil(String studentEmil) {
		this.studentEmil = studentEmil;
	}
	
}
