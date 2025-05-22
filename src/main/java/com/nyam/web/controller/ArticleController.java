package com.nyam.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nyam.model.dto.ArticleDetail;
import com.nyam.model.dto.ArticleMaster;
import com.nyam.model.dto.User;
import com.nyam.model.service.ArticleService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ArticleController {
	
	@Autowired
	ArticleService articleService;
	
	public ArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	@PostMapping
	public ResponseEntity<?> writeArticle(HttpSession session, HttpServletRequest request, HttpServletResponse response, ArticleMaster master, List<ArticleDetail> detailList) {
		User user = (User)session.getAttribute("loginUser");
		if (user==null) return ResponseEntity.badRequest().build();
		return null;
	}
	 
}
