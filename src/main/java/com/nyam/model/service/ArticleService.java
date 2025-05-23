package com.nyam.model.service;

import java.sql.SQLException;
import java.util.List;

import com.nyam.model.dto.ArticleMaster;
import com.nyam.model.dto.ArticleWrap;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ArticleService {
	
	int writeArticle(HttpServletRequest request, HttpServletResponse response, ArticleWrap article) throws SQLException;
	
	ArticleWrap selectArticle(HttpServletRequest request, HttpServletResponse response, int id) throws SQLException;
	
	List<ArticleMaster> getAllArticle(HttpServletRequest request, HttpServletResponse response) throws SQLException;
}