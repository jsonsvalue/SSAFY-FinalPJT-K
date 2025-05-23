package com.nyam.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetail {
	private int id;
	private int articleId;
	private int order;
	private int imageId;
	private String imageUrl;
	private String content;
	private String useYN;
	private String rgstDttm;
	private String mdfcDttm;
}
