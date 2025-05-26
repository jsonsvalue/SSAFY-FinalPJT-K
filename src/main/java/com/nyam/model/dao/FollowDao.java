package com.nyam.model.dao;

import java.util.List;

import com.nyam.model.dto.Follow;

public interface FollowDao {
	
	// User를 follow한다.
	int follow(String userId, String followId);
	
	// 내가 Follow하는 사용자 목록을 가져온다.
	List<Follow> getFollow(String userId);
	
	// 나를 Follow하는 사용자 목록을 가져온다.
	List<Follow> getFollower(String followId);
	
	// 상대 이용자를 Unfollow한다.
	int unfollow(String userId, String followId);
	
}
