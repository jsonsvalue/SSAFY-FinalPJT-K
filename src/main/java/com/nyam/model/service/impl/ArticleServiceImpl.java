package com.nyam.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyam.model.dao.ArticleDao;
import com.nyam.model.dto.ArticleDetail;
import com.nyam.model.dto.ArticleMaster;
import com.nyam.model.service.ArticleService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ArticleServiceImpl implements ArticleService{
	
	@Autowired
	ArticleDao articleDao;

	@Override
	public int writeArticle(HttpServletRequest request, HttpServletResponse response, ArticleMaster master,
			List<ArticleDetail> detailList) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
