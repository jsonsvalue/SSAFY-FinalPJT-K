package com.nyam.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nyam.model.VO.ArticleVO;
import com.nyam.model.dto.User;
import com.nyam.model.service.ArticleService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;


@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ArticleController {
	
	@Autowired
	ArticleService service;
	
	@PostMapping("/article")
	public ResponseEntity<?> writeArticle(HttpSession session, HttpServletRequest request, HttpServletResponse response,@RequestBody ArticleVO article) {
		User user = (User)session.getAttribute("loginUser");
//		if (user==null) return ResponseEntity.badRequest().build();
		article.getArticle().setUserId("hell");
		try {
			int id = service.writeArticle(request, response, article);
			return ResponseEntity.ok(id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/article/{id}")
	public ResponseEntity<?> selectArticle(HttpSession session, HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {
		try {
			ArticleVO vo = service.selectArticle(request, response, Integer.parseInt(id));
			return ResponseEntity.ok(vo);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}
}
