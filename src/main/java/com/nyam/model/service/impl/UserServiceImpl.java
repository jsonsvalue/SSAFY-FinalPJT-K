package com.nyam.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyam.model.dao.UserDao;
import com.nyam.model.dto.User;
import com.nyam.model.service.UserService;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;
	
	// 해당되는 User가 DB에 있는지 확인하고,
	// DB에 있는 User를 갖고 온다.
	@Override
	public User doLogin(User user) {
		// 입력한 userId를 이용해서 dbUser를 가져온다.
		User dbUser = userDao.selectByuserId(user.getUserId());
		
		// 입력한 userId와 dbUser의 비밀번호가 동일한지 확인한다. 
		if( dbUser != null && dbUser.getPassword().equals(user.getPassword()) ) {
			return dbUser;
		}else {
			return null;
		}
	}
	
	// 새로운 User를 DB에 넣고, 
	// boolean 값을 반환 받는다.
	@Override
	public boolean registUser( User user ) {
		int result = userDao.insert(user);
		
		if( result ==1)
			return true;
		else
			return false;
		
	}


	@Override
	public void deleteUser(HttpSession session) {
		// session을 통해서 user 정보를 갖고와서,
		// 해당되는 user를 지운다.
		User sessUser = (User)session.getAttribute("loginUser");
		
		if(sessUser != null) {
			userDao.delete(sessUser.getUserId());
		}
		
		
	}
	
	@Override
	public boolean updateUser(User modUser) {
		// 정보가 수정된 modUser의 정보를, 
		// DB에 반영하여 업데이트 한다.
		int	result = userDao.update(modUser);
		
		return result == 1;
	}
	
}
