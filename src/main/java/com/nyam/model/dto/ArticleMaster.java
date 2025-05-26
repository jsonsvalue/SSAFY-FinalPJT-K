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
public class ArticleMaster {
	private int id;
	private String userId;
	private String userName;
	private String userImage;
	private String type;
	private int imageId;
	private String imageUrl;
	private String content;
	private String ingredient;
	private int likeCount;
	private String useYN;
	private String rgstDttm;
	private String mdfcDttm;
}
