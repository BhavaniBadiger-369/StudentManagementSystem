package edu.training.studentmanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.training.studentmanagementsystem.dao.AdminDao;
import edu.training.studentmanagementsystem.dao.StudentDao;
import edu.training.studentmanagementsystem.dto.AdminDto;
import edu.training.studentmanagementsystem.entity.Admin;
import edu.training.studentmanagementsystem.entity.Student;
import edu.training.studentmanagementsystem.exception.AdminNotFoundByIdException;
import edu.training.studentmanagementsystem.util.ResponseStructure;

@Service
public class AdminService {

	@Autowired
private AdminDao adminDao;
	
	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private AdminDto adminDto;
	
	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(Admin admin) {
	Admin admin2 =	adminDao.saveAdmin(admin);
	
	adminDto.setAdminId(admin2.getAdminId());
	adminDto.setAdminName(admin2.getAdminName());
	adminDto.setAdminEmail(admin2.getAdminEmail());
	adminDto.setStudents(admin2.getStudents());
	
	
	
	ResponseStructure<Admin> responseStructure = new ResponseStructure<>();
	responseStructure.setStatusCode(HttpStatus.CREATED.value());
	responseStructure.setMessage("Admin Saved Successfully!");
	responseStructure.setData(adminDto);
	
	return new ResponseEntity<ResponseStructure<Admin>>(responseStructure, HttpStatus.CREATED);
	}
	
	public  ResponseEntity<ResponseStructure<Admin>> findAdminById(int adminId) {
	Admin admin =	adminDao.findAdminById(adminId);
	if(admin!=null) {
		
		adminDto.setAdminId(admin.getAdminId());
		adminDto.setAdminName(admin.getAdminName());
		adminDto.setAdminEmail(admin.getAdminEmail());
		adminDto.setStudents(admin.getStudents());
		
		
		ResponseStructure<Admin> responseStructure= new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.FOUND.value());
		responseStructure.setMessage("Admin Found");
		responseStructure.setData(adminDto);
		return new ResponseEntity<ResponseStructure<Admin>>(responseStructure, HttpStatus.FOUND);
	}else {
		 throw new AdminNotFoundByIdException("Failed to Find Admin!");
	}

	}
	
	public ResponseEntity<ResponseStructure<Admin>> updateAdminById(int adminId, Admin admin) {
		Admin admin2 =	adminDao.updateAdminById(adminId, admin);
		if(admin2!=null) {
			
			adminDto.setAdminId(admin2.getAdminId());
			adminDto.setAdminName(admin2.getAdminName());
			adminDto.setAdminEmail(admin2.getAdminEmail());
			adminDto.setStudents(admin2.getStudents());
			
			ResponseStructure<Admin> responseStructure= new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Admin Updated");
			responseStructure.setData(adminDto);
			return new ResponseEntity<ResponseStructure<Admin>>(responseStructure, HttpStatus.OK);
		}else 
		         throw new AdminNotFoundByIdException("Failed to Update Admin!");
		}

		
	
	public  ResponseEntity<ResponseStructure<Admin>> deleteAdminById(int adminId) {
	Admin admin = adminDao.findAdminById(adminId);
	if(admin!=null) {
	
	Admin admin2 =adminDao.deleteAdminById(adminId);
	
    adminDto.setAdminId(admin2.getAdminId());
	adminDto.setAdminName(admin2.getAdminName());
	adminDto.setAdminEmail(admin2.getAdminEmail());
	adminDto.setStudents(admin2.getStudents());
	
	ResponseStructure<Admin> responseStructure= new ResponseStructure<>();
	responseStructure.setStatusCode(HttpStatus.OK.value());
	responseStructure.setMessage("Admin Deleted Successfully!");
	responseStructure.setData(adminDto);
	return new ResponseEntity<ResponseStructure<Admin>>(responseStructure, HttpStatus.OK);
	
	}else {
		throw new AdminNotFoundByIdException("Failed to Delete Admin!");
	}
	}
}
