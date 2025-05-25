package com.nyam.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nyam.model.dao.UserDao;
import com.nyam.model.dto.User;
import com.nyam.model.service.UserService;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	BCryptPasswordEncoder pwdEncoder;
	
	// 해당되는 User가 DB에 있는지 확인하고,
	// DB에 있는 User를 갖고 온다.
	@Override
	public User doLogin(User user) {
		// 입력한 userId를 이용해서 dbUser를 가져온다.
		User dbUser = userDao.selectByuserId(user.getUserId());
		
		// 평문 비밀번호랑 DB에서 암호화한 비밀번호가 일치하는지 확인한다.
		if( dbUser != null && pwdEncoder.matches(user.getPassword(), dbUser.getPassword())) {
			return dbUser;
		}else {
			return null;
		}
	}
	
	// 새로운 User를 DB에 넣고, 
	// boolean 값을 반환 받는다.
	@Override
	public boolean registUser( User user ) {
		// 회원 가입 시 비밀번호를 암호화하고, user 객체에 설정해준다.
		String encodedPw = pwdEncoder.encode(user.getPassword());
		user.setPassword(encodedPw);
		
		int result = userDao.insert(user);
		
		if( result ==1)
			return true;
		else
			return false;
		
	}

	// 로그인만 된 상태에서 다른 유저의 정보를 지우는 것을 막기 위해, 
	// modUser 그리고 session의 사용자가 같은지 반드시 확인해줘야 한다.
	@Override
	public void deleteUser(User sessUser, User delUser) {
		
		if(!delUser.getUserId().equals(sessUser.getUserId())) {
			throw new AccessDeniedException("Failed to delete user");
		}
		// session을 통해서 user 정보를 갖고와서,
		// 해당되는 user를 지운다.
		if(sessUser != null) {
			userDao.delete(sessUser.getUserId());
		}
		
		
	}
	
	// 로그인만 된 상태에서 다른 유저의 정보를 수정하는 것을 막기 위해, 
	// modUser 그리고 session의 사용자가 같은지 반드시 확인해줘야 한다.
	@Override
	public boolean updateUser(User sessUser, User modUser) {
		
		if(!modUser.getUserId().equals(sessUser.getUserId())) {
			throw new AccessDeniedException("Failed to update user's information");
		}
		// 정보가 수정된 modUser의 정보를, 
		// DB에 반영하여 업데이트 한다.
		int	result = userDao.update(modUser);
		
		return result == 1;
	}

	
	@Override
	public List<User> searchUser(String keyword) {
		// 해당되는 keyword를 가진 user목록을 반환한다.  
		List<User> searchedUsers = userDao.search(keyword);
		
		return searchedUsers;
	}

	@Override
	public User searchExactUser(String userId) {
		User profileUser = userDao.searchExact(userId);
		return profileUser;
	}	
	
	
}
