package com.nyam.model.service;

import java.util.List;

import com.nyam.model.dto.User;

import jakarta.servlet.http.HttpSession;

public interface UserService {
	
	User doLogin(User user);
	
	boolean registUser(User user);
	
	boolean updateUser(User user);
	
	void deleteUser(HttpSession session);
	
	List<User> searchUser(String userId);
	
}
