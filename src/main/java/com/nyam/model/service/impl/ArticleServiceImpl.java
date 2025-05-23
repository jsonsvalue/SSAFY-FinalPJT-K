package com.nyam.model.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyam.model.dao.ArticleDao;
import com.nyam.model.dto.ArticleDetail;
import com.nyam.model.dto.ArticleMaster;
import com.nyam.model.dto.ArticleWrap;
import com.nyam.model.service.ArticleService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ArticleServiceImpl implements ArticleService{
	
	@Autowired
	ArticleDao dao;

	@Override
	public int writeArticle(HttpServletRequest request, HttpServletResponse response, ArticleWrap article) throws SQLException {
		
		int res = dao.insertArticleMaster(article.getArticle());
		int id = article.getArticle().getId();
		if (res!=0&&id!=0) {
			for (ArticleDetail sub : article.getSubArticle()) {
				sub.setArticleId(id);
				dao.insertArticleDetail(sub);
			}
		} else {
			throw new SQLException();
		}
		return id;
	}

	@Override
	public ArticleWrap selectArticle(HttpServletRequest request, HttpServletResponse response, int id)
			throws SQLException {
		ArticleMaster article = dao.selectArticleMaster(id);
		if (article==null) throw new SQLException();
		List<ArticleDetail> subArticle = dao.selectArticleDetail(article.getId());
		ArticleWrap vo = new ArticleWrap(article,subArticle);
		return vo;
	}
	
	@Override
	public List<ArticleMaster> getAllArticle(HttpServletRequest request, HttpServletResponse response)
			throws SQLException {
		return dao.selectArticleMasterAll();
	}
	
	
}
