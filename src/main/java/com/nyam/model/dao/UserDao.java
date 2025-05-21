package com.nyam.model.dao;

import java.util.List;

import com.nyam.model.dto.User;

public interface UserDao {
	// 외부 API 요청에 따른 처리
	User selectByuserId(String userId);
	
	// 내부 DB 데이터 바꾸기 위한 처리
	User selectById(int id);
	
	// User 정보 선택
	User select(int id);
	
	// 회원가입
	int insert(User user);
	
	// User 정보 업데이트
	int update(User user);
	
	// User 삭제
	int delete(String userId);
	
	// User를 검색한다.
	// 특정 keyword로 시작하는 User목록을 반환한다.
	List<User> search(String keyword);
	
}
