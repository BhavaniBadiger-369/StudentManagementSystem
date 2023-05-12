package edu.training.studentmanagementsystem.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import edu.training.studentmanagementsystem.entity.Student;

@Component

public class AdminDto {
	
	private int adminId;
	private String adminName;
	private String adminEmail;

	private List<Student> students;

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
}
