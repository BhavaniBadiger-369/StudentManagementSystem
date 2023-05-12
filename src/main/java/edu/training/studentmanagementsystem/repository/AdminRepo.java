package edu.training.studentmanagementsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.training.studentmanagementsystem.dto.AdminDto;
import edu.training.studentmanagementsystem.entity.Admin;
import edu.training.studentmanagementsystem.entity.Student;

public interface AdminRepo  extends JpaRepository<Admin, Integer>{
	
@Query(value = "select a.students from Admin a where a.adminId=?1")
	public Optional<List<Student>> getStudents(int adminId);
}
