package com.nyam.model.service;

import java.sql.SQLException;

import com.nyam.model.VO.ArticleVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ArticleService {
	
	int writeArticle(HttpServletRequest request, HttpServletResponse response, ArticleVO article) throws SQLException;
	
	ArticleVO selectArticle(HttpServletRequest request, HttpServletResponse response, int id) throws SQLException;
}