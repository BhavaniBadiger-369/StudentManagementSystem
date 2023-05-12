package edu.training.studentmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.training.studentmanagementsystem.entity.Student;

public interface StudentRepo  extends JpaRepository<Student, Integer>{

}
