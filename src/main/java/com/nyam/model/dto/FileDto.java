package com.nyam.model.dto;

import org.springframework.beans.factory.annotation.Value;

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
	private String orgFile;
	private String useYN;
	private String rgstDttm;
	private String mdfcDttm;
}
