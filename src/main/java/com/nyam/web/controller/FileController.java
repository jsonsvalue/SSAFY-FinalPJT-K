package com.nyam.web.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nyam.model.dto.FileDto;
import com.nyam.model.dto.User;
import com.nyam.model.service.FileService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class FileController {
	
	@Autowired
	FileService service;
	
	@PostMapping("/upload")
	public ResponseEntity<?> uploadFile(HttpSession session, HttpServletRequest request, HttpServletResponse response, @RequestBody byte[] file) {
		User user = (User)session.getAttribute("loginUser");
		if (user==null||file.length==0) return ResponseEntity.badRequest().build();
		try {
			FileDto res = service.uploadFile(file);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	 
}
