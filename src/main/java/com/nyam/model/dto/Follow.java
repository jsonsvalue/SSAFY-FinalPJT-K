package com.nyam.model.dto;

import java.util.Date;

public class Follow {
	private int id;
	private String userId;
	private String followId;
	
	private Character useYN;
	private Date rgstDttm;
	private Date mdfcDttm;
	
	public Follow() {
		
	}

	public Follow(int id, String userId, String followId, Character useYN, Date rgstDttm, Date mdfcDttm) {
		super();
		this.id = id;
		this.userId = userId;
		this.followId = followId;
		this.useYN = useYN;
		this.rgstDttm = rgstDttm;
		this.mdfcDttm = mdfcDttm;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFollowId() {
		return followId;
	}

	public void setFollowId(String followId) {
		this.followId = followId;
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

	@Override
	public String toString() {
		return "Follow [id=" + id + ", userId=" + userId + ", followId=" + followId + ", useYN=" + useYN + ", rgstDttm="
				+ rgstDttm + ", mdfcDttm=" + mdfcDttm + "]";
	}
	
	
	
	
	
}
