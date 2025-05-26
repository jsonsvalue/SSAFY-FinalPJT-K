package com.nyam.model.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class User {
	private String userId;
	private String name;
	private String email;
	private String password;
	private String role;
	private String intro;
	private String feedType;
	private String imageId;
	private String imageUrl;
	
	private Character useYN;
	
	// @DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date rgstDttm;
	// @DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date mdfcDttm;
	
	public User() {
	}
	
	public User(String userId, String name, String email, String password, String role, Character userYN, Date rgstDttm, Date mdfcDttm, String info, String feedType, String imageId, String imageUrl) {
		this.userId = userId; 
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.useYN = userYN;
		this.rgstDttm = rgstDttm;
		this.mdfcDttm = mdfcDttm;
		this.intro = intro;
		this.feedType = feedType;
		this.imageId = imageId;
		this.imageUrl = imageUrl;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getFeedType() {
		return feedType;
	}

	public void setFeedType(String feedType) {
		this.feedType = feedType;
	}

	public Character getUseYN() {
		return useYN;
	}

	public void setUseYN(Character useYN) {
		this.useYN = useYN;
	}

	public Date getRgstDttm() {
		return rgstDttm;
	}

	public void setRgstDttm(Date rgstDttm) {
		this.rgstDttm = rgstDttm;
	}

	public Date getMdfcDttm() {
		return mdfcDttm;
	}

	public void setMdfcDttm(Date mdfcDttm) {
		this.mdfcDttm = mdfcDttm;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password + ", role="
				+ role + ", intro=" + intro + ", feedType=" + feedType + ", imageId=" + imageId + ", imageUrl="
				+ imageUrl + ", useYN=" + useYN + ", rgstDttm=" + rgstDttm + ", mdfcDttm=" + mdfcDttm + "]";
	}
	
	

}
