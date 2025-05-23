package com.nyam.web.controller;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nyam.model.dto.FileDto;
import com.nyam.model.dto.User;
import com.nyam.model.service.FileService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/api")
public class FileController {
	
	@Autowired
	FileService service;
	
	@PostMapping("/upload")
	public ResponseEntity<?> uploadFile(HttpSession session, HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile file) {
		User user = (User)session.getAttribute("loginUser");
		FileDto res = null;
		if (file==null) return ResponseEntity.badRequest().build();
		try {
			res = service.uploadFile(file);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok(res);
	}
}
