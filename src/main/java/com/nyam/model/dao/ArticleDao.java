package com.nyam.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nyam.model.dto.ArticleComment;
import com.nyam.model.dto.ArticleDetail;
import com.nyam.model.dto.ArticleMaster;

public interface ArticleDao {
	int insertArticleMaster(ArticleMaster master);
	
	int updateArticleMaster(ArticleMaster master);
	
	int deleteArticleMaster(ArticleMaster master);
	
	ArticleMaster selectArticleMaster(@Param("articleId") int articleId,@Param("sessionId") String sessionId);
	
	List<ArticleMaster> selectArticleMasterAll(String sessionId);
	
	int insertArticleDetail(ArticleDetail detail);
	
	int updateArticleDetail(ArticleDetail detail);
	
	int deleteArticleDetail(int articleId);
	
	List<ArticleDetail> selectArticleDetail(int articleId);
	
	List<ArticleMaster> selectArticleMasterByUser(String userId);
	
	int insertComment(ArticleComment comment);
	
	List<ArticleComment> selectComment(int articleId);
	
	int insertArticleLike(@Param("articleId")int articleId, @Param("sessionId")String sessionId);
	
	int deleteArticleLike(@Param("articleId")int articleId, @Param("sessionId")String sessionId);
	
	int incArticleLike(int articleId);
	
	int decArticleLike(int articleId);
}
