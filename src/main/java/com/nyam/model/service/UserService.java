package com.nyam.model.service;

import java.util.List;

import com.nyam.model.dto.User;

import jakarta.servlet.http.HttpSession;

public interface UserService {
	
	User doLogin(User user);
	
	boolean registUser(User user);
	
	boolean updateUser(User sessUser, User moduser);
	
	void deleteUser(User sessUser, User delUser);
	
	List<User> searchUser(String userId);
	
	User searchExactUser(String userId);
	
}
