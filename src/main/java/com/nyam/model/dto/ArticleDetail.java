package com.nyam.model.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ArticleDetail {
	@NonNull
	private int id;
	private int tempId;
	private int articleId;
	
	@NonNull
	private int order;
	
	@NonNull
	private int imageId;
	
	private String imageUrl;
	
	private String content;
	@NonNull
	private String useYN;
	@NonNull
	private String rgstDttm;
	@NonNull
	private String mdfcDttm;
}
