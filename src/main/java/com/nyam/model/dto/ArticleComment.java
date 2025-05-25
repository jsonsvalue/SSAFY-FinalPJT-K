package com.nyam.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ArticleComment {
	private int id;
	private String userId;
	private String articleId;
	private String content;
	private String useYN;
	private String rgstDttm;
	private String mdfcDttm;
}
