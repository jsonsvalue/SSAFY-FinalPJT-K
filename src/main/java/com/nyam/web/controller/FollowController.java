package com.nyam.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nyam.model.dto.Follow;
import com.nyam.model.service.FollowService;

@RestController
@RequestMapping("/api/user")
public class FollowController {
	
	FollowService followService;
	
	@Autowired
	public FollowController(FollowService followService) {
		this.followService = followService;
	}
	
	@GetMapping("/test")
	public String test() {
		return "follow/hello";
	}
	
	// Path에 있는 userId와 requestParam에 있는 followId를 이용해서,
	// follow DB에 데이터를 저장한다.
	@PostMapping("/follow/{userId}")
	public ResponseEntity<?> followUser(@PathVariable("userId") String userId, @RequestParam("followId") String followId){
		
		boolean result = followService.followUser(userId, followId);
		
		if(result) {
			return ResponseEntity.status(HttpStatus.OK).body("Follow Successfully completed");
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Following of user failed");
		}
			
	}
	
	
	@GetMapping("/follow/getFollow/{userId}")
	public ResponseEntity<?> getFollow(@PathVariable("userId") String userId){
		
		List<Follow> followList = followService.getFollowList(userId);
		
		return new ResponseEntity<List<Follow>>(followList, HttpStatus.OK);
			
	}
	
	@GetMapping("/follow/getFollowers/{userId}")
	public ResponseEntity<?> getFollowers(@PathVariable("userId") String userId){
		List<Follow> followerList = followService.getFollowersList(userId);
		
		return new ResponseEntity<List<Follow>>(followerList, HttpStatus.OK);
		
	}
	
	
	
	
	
}
