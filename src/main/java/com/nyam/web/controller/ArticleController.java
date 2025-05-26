package com.nyam.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nyam.model.dto.ArticleComment;
import com.nyam.model.dto.ArticleMaster;
import com.nyam.model.dto.ArticleWrap;
import com.nyam.model.dto.User;
import com.nyam.model.service.ArticleService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/api")
public class ArticleController {
	
	@Autowired
	ArticleService service;
	
	@PostMapping("/article")
	public ResponseEntity<?> writeArticle(HttpSession session, HttpServletRequest request, HttpServletResponse response,@RequestBody ArticleWrap article) {
		User user = (User)session.getAttribute("loginUser");
		if (user==null) return ResponseEntity.badRequest().build();
		article.getArticle().setUserId(user.getUserId());
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
			ArticleWrap vo = service.selectArticle(session,request, response, Integer.parseInt(id));
			return ResponseEntity.ok(vo);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/feed")
	public ResponseEntity<?> getAllArticle(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<ArticleMaster> list = service.getAllArticle(session,request, response);
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/articleUser/{userId}")
	public ResponseEntity<?> selectArticleByUser(HttpSession session, HttpServletRequest request, HttpServletResponse response, @PathVariable("userId") String userId){
		try {
			List<ArticleMaster> articleMaster = service.selectArticleByUser(request, response, userId);
			return ResponseEntity.ok(articleMaster);
			
		}catch(Exception err) {
			err.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping("/article/{id}/comment")
	public ResponseEntity<?> postComment(HttpSession session, HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id,@RequestBody ArticleComment comment) {
		User user = (User)session.getAttribute("loginUser");
		if (user==null) return ResponseEntity.badRequest().build();
		comment.setUserId(user.getUserId());
		comment.setArticleId(id);
		try {
			int res = service.writeComment(request, response, comment);
			return ResponseEntity.ok(id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PutMapping("/article/{id}/like")
	public ResponseEntity<?> likeArticle(HttpSession session, HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {
		User user = (User)session.getAttribute("loginUser");
		if (user==null) return ResponseEntity.badRequest().build();
		try {
			int res = service.likeArticle(request, response, session,Integer.parseInt(id));
			return ResponseEntity.ok(id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping("/article/{id}/like")
	public ResponseEntity<?> dislikeArticle(HttpSession session, HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {
		User user = (User)session.getAttribute("loginUser");
		if (user==null) return ResponseEntity.badRequest().build();
		try {
			int res = service.dislikeArticle(request, response, session,Integer.parseInt(id));
			return ResponseEntity.ok(id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}
}
