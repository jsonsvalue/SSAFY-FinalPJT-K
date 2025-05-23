package com.nyam.model.VO;

import java.util.List;

import com.nyam.model.dto.ArticleDetail;
import com.nyam.model.dto.ArticleMaster;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleVO {
	private ArticleMaster article;
	private List<ArticleDetail> subArticle;
}
