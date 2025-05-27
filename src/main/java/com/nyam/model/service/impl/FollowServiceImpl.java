package com.nyam.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyam.model.dao.FollowDao;
import com.nyam.model.dto.Follow;
import com.nyam.model.service.FollowService;

@Service
public class FollowServiceImpl implements FollowService{
	@Autowired
	FollowDao followDao;
	
	@Override
	public boolean followUser(String userId, String followId) {
		if(followDao.isAlreadyFollowing(userId, followId)) {
			return false;
		}
		
		int result = followDao.follow(userId, followId);
		
		return result > 0;
	}

	@Override
	public List<Follow> getFollowList(String userId) {
		List<Follow> followList = followDao.getFollow(userId);
		
		return followList;
	}

	@Override
	public List<Follow> getFollowersList(String followId) {
		List<Follow> followerList = followDao.getFollower(followId);
		
		return followerList;
	}

	@Override
	public boolean unfollowUser(String userId, String followId) {
		int result = followDao.unfollow(userId, followId);
				
		return result > 0;
	}
	
	
}
