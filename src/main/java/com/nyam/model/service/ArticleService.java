package com.nyam.model.service;

import java.sql.SQLException;
import java.util.List;

import com.nyam.model.dto.ArticleComment;
import com.nyam.model.dto.ArticleMaster;
import com.nyam.model.dto.ArticleWrap;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public interface ArticleService {
	
	int writeArticle(HttpServletRequest request, HttpServletResponse response, ArticleWrap article) throws SQLException;
	
	ArticleWrap selectArticle(HttpSession session, HttpServletRequest request, HttpServletResponse response, int id) throws SQLException;
	
	List<ArticleMaster> getAllArticle(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws SQLException;
	
	List<ArticleMaster> selectArticleByUser(HttpServletRequest request, HttpServletResponse response, String userId) throws SQLException;

	int writeComment(HttpServletRequest request, HttpServletResponse response, ArticleComment comment) throws SQLException;
	
	int likeArticle(HttpServletRequest request,HttpServletResponse response, HttpSession session, int articleId) throws SQLException;
	
	int dislikeArticle(HttpServletRequest request,HttpServletResponse response, HttpSession session, int articleId) throws SQLException;
}