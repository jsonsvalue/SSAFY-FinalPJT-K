package com.nyam.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FileDto {
	private int id;
	private String thumbFile;
	private String compFile;
	private String orgFile;
	private String useYN;
	private String rgstDttm;
	private String mdfcDttm;
}
