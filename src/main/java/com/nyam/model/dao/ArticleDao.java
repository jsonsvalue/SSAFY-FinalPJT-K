package com.nyam.model.dao;

import java.util.List;

import com.nyam.model.dto.ArticleMaster;

public interface ArticleDao {
	int insertArticleMaster(ArticleMaster master);
	
	int updateArticleMaster(ArticleMaster master);
	
	int deleteArticleMaster(ArticleMaster master);
	
	ArticleMaster selectArticleMaster(int articleId);
	
	List<ArticleMaster> selectArticleMasterAll();
}
