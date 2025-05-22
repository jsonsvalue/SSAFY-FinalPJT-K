package com.nyam.model.service;

import java.util.List;

import com.nyam.model.dto.ArticleDetail;
import com.nyam.model.dto.ArticleMaster;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ArticleService {
	
	int writeArticle(HttpServletRequest request, HttpServletResponse response, ArticleMaster master, List<ArticleDetail> detailList);
}
