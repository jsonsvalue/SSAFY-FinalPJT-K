package com.nyam.model.dao;

import java.util.List;

import com.nyam.model.dto.User;

public interface FollowDao {
	
	// User를 follow한다.
	int follow(User user);
	
	// 내가 Follow하는 사용자 목록을 가져온다.
	List<User> getFollow(String userId);
	
	// 나를 Follow하는 사용자 목록을 가져온다.
	List<User> getFollower(String followId);
	
}
