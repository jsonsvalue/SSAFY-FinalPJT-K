package com.nyam.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class ArticleMaster {
	private int id;
	@NonNull
	private String userId;
	@NonNull
	private String type;
	@NonNull
	private int imageId;
	private String imageUrl;
	private String content;
	private String ingredient;
	private String useYN;
	private String rgstDttm;
	private String mdfcDttm;
}
