package com.nyam.model.service;

import java.util.List;

import com.nyam.model.dto.Follow;

public interface FollowService {
	// 함수명은 DAO와 구분하기 위해 반환하는 값을 뒤에 붙여주면,
	// DB에서 갖고 온 값을 Service에서 어떻게 처리하는지 더 명확하게 보여줄 수 있다.
	
	// 특정 user가 다른 followUser를 Follow 한다.
	boolean followUser(String userId, String followId);
	
	// 특정 user라 Follow하는 목록을 가져온다.
	List<Follow> getFollowList(String userId);
	
	// 특정 user의 Follower를 가져온다.
	List<Follow> getFollowersList(String followId);
	
}
