package com.nyam.model.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyam.model.dao.ArticleDao;
import com.nyam.model.dto.ArticleComment;
import com.nyam.model.dto.ArticleDetail;
import com.nyam.model.dto.ArticleMaster;
import com.nyam.model.dto.ArticleWrap;
import com.nyam.model.dto.User;
import com.nyam.model.service.ArticleService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
	public ArticleWrap selectArticle(HttpSession session,HttpServletRequest request, HttpServletResponse response, int id)
			throws SQLException {
		User u = (User)session.getAttribute("loginUser");
		ArticleMaster article = dao.selectArticleMaster(id,u!=null?u.getUserId():null);
		if (article==null) throw new SQLException();
		List<ArticleDetail> subArticle = dao.selectArticleDetail(id);
		List<ArticleComment> comment = dao.selectComment(id);
		ArticleWrap vo = new ArticleWrap(article,subArticle,comment);
		return vo;
	}
	
	@Override
	public List<ArticleMaster> getAllArticle(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws SQLException {
		User u = (User)session.getAttribute("loginUser");
		
		return dao.selectArticleMasterAll(u!=null?u.getUserId():null);
	}

	@Override
	public List<ArticleMaster> selectArticleByUser(HttpServletRequest request, HttpServletResponse response, String userId)
			throws SQLException {
		// Article Master를 userId로 찾되,
		List<ArticleMaster> masterArticle = dao.selectArticleMasterByUser(userId);
		if(masterArticle == null) 
			throw new SQLException();
		// 어차피 subArticle은 masterArticle의 id를 갖고 있기 때문에 getId로 해서 묶어야 한다.
		// List<ArticleDetail> subArticle = dao.selectArticleDetail(masterArticle.getId());
		// ArticleWrap VO = new ArticleWrap(masterArticle, subArticle);
		
		return masterArticle;

	}
	
	@Override
	public int writeComment(HttpServletRequest request, HttpServletResponse response, ArticleComment comment)
			throws SQLException {
		int res = dao.insertComment(comment);
		int id = comment.getId();
		if (res!=0) {
			return id;
		} else {
			throw new SQLException();
		}
	}

	@Override
	public int likeArticle(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			int articleId) throws SQLException {
		User u = (User)session.getAttribute("loginUser");
		int ret = dao.insertArticleLike(articleId, u.getUserId());
		if (ret!=0) {
			dao.incArticleLike(articleId);
			return ret;
		} else {
			throw new SQLException();
		}
	}

	@Override
	public int dislikeArticle(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			int articleId) throws SQLException {
		User u = (User)session.getAttribute("loginUser");
		int ret = dao.deleteArticleLike(articleId, u.getUserId());
		if (ret!=0) {
			dao.decArticleLike(articleId);
			return ret;
		} else {
			throw new SQLException();
		}
	}
	
	
}
