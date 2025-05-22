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
public class ArticleMaster {
	@NonNull
	private int id;
	private int tempId;
	@NonNull
	private String userId;
	@NonNull
	private String type;
	@NonNull
	private int imageId;
	private String imageUrl;
	private String content;
	private String ingredient;
	@NonNull
	private String useYN;
	@NonNull
	private String rgstDttm;
	@NonNull
	private String mdfcDttm;
}
