package com.nyam.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nyam.model.dao.UserDao;
import com.nyam.model.dto.User;
import com.nyam.model.service.UserService;

import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/api")
public class UserController {
		
	UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	UserDao userDao;
	
	@GetMapping("/test")
	public String test() {
		return "user/hello";
	}
	
	/*
	 * @Autowired UserDao userDao;
	 * 
	 * @GetMapping("/login/{userId}") public ResponseEntity<?>
	 * loginPage(@PathVariable("userId") String userId){
	 * 
	 * User user = userDao.selectByuserId(userId);
	 * 
	 * return new ResponseEntity<User>(user, HttpStatus.OK); }
	 */
	
	/**
	 * 로그인한 사용자의 Session 정보를 갖고 온다.
	 * **/
	// 로그인한 user의 정보를 이용해서 DB를 조회하고, 
	// update된 정보를 session에 반영해준다.
	@GetMapping("/getUserInfo")
	public ResponseEntity<?> getUserInfo(HttpSession session){
		User sessUser = (User)session.getAttribute("loginUser");
		
		String loggedInUser = sessUser.getUserId();
		
		User updatedUser = userDao.selectByuserId(loggedInUser);
		
		if(sessUser != null) {
			System.out.println("Get Session");
			System.out.println(session.getId());
			
			session.setAttribute("loginUser", updatedUser);
			
			return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
		}else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("There is no user attributed to the session");
		}
		
	}
	
	// 로그인하면서 DB에 있는 사용자인지 확인하고, 
	// DB에 존재한다면 session에 loginUser를 설정해준다.
	@PostMapping("/login")
	public ResponseEntity<?> postLogin(HttpSession session, @RequestBody User user){
		
		User loginUser = userService.doLogin(user);
		
		// System.out.println(loginUser);
		
		if(loginUser != null) {	
			session.setAttribute("loginUser", loginUser);
			return new ResponseEntity<User>(loginUser, HttpStatus.OK);
		}else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid User");
		}
	}
	
	// 회원 가입을 하고, session에 해당 User에 대한 정보를 설정한다.
	// Axios로 json형태로 보낼 경우, RequestBody를 써야한다.
	@PostMapping("/regist")
	public ResponseEntity<?> postRegist(HttpSession session, @RequestBody User newUser){
		User sessUser = (User)session.getAttribute("loginUser");
		
		// 이미 로그인 했다면, 해당되는 sessUser를 반환한다.
		if(sessUser != null) {
			return new ResponseEntity<User>(sessUser, HttpStatus.OK);
		
		// 로그인하지 않았다면 해당되는 newUser를 회원 가입을 시키고,
		// session에 해당 user의 정보를 설정한다.
		}else {
			boolean result = userService.registUser(newUser);
			if( result ) {
				session.setAttribute("loginUser", newUser);
				System.out.println(newUser.getUserId());
			}
			return new ResponseEntity<User>(newUser, HttpStatus.OK);
		}
		
	}
	
	// 로그인된 사용자가 있다면, session의 값을 없애준다.
	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session){
		User sessUser = (User)session.getAttribute("loginUser");
		
		if(sessUser != null) {
			session.removeAttribute("loginUser");
			// return new ResponseEntity<>(null, HttpStatus.OK);
			return ResponseEntity.status(HttpStatus.OK).body("Logged out Successfully");
		}else
			return null;
		
	}
	
	// 로그인된 사용자의 session을 가져와서, 
	// 해당되는 user의 회원 정보를 삭제한다.
	@PatchMapping("/delete")
	public ResponseEntity<?> deleteUser(HttpSession session, @RequestBody User delUser){
		User sessUser = (User)session.getAttribute("loginUser");
		
		try{
			if(sessUser != null) {
				userService.deleteUser(sessUser, delUser);
				session.removeAttribute("loginUser");
				return ResponseEntity.status(HttpStatus.OK).body("User Deleted Successfully");
				
			}else
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
			}
		catch(AccessDeniedException err) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err.getMessage());
		}
	}
	
	// 사용자 정보를 업데이트하고 session에 반영할 때는 반드시 modUser를 반영하는 것이 아니라,
	// DB에서 바뀐 값을 session에 반영해주는 것이 중요하다.
	@PatchMapping("/update")
	public ResponseEntity<?> updateUser(HttpSession session, @RequestBody User modUser){
		User sessUser = (User)session.getAttribute("loginUser");
		
		if(sessUser != null) {
			
			try {
				// 업데이트된 정보를 modUser에 반영하고,
				// DB에 업데이트한다.
				// modUser.setId(sessUser.getId());
				boolean updated = userService.updateUser(sessUser, modUser);
				
				// 업데이트된 정보를 DB에서 조회한 후, Session에 반영해준다.
				// modUser는 JSON 데이터로 받은 정보이기 때문에, DB에서 데이터를 조회하고 업데이트 해야 한다.
				if(updated) {
					
					User updatedUser = userService.searchUser(modUser.getUserId()).get(0);
					
					session.setAttribute("loginUser", updatedUser);
					
					//return ResponseEntity.status(HttpStatus.OK).body("User Information Updated Successfully");
					return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
				}else {
					
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update User information");
				}
			}
			catch(AccessDeniedException err) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err.getMessage());
			}
			
		}
		
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User Not logged in");
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> searchUser(@RequestParam("keyword")String keyword){
		
		//session이 없어도, 사용자 목록은 검색해서 확인할 수 있게는 한다.
		List<User> searchedUsers = userService.searchUser(keyword);
		
		return new ResponseEntity<List<User>>(searchedUsers, HttpStatus.OK);
	} 
	
	@GetMapping("/profile/{userId}")
	public ResponseEntity<?> profileCheck(@PathVariable("userId") String userId){
		User profileUser = userService.searchExactUser(userId);
		
		// 사용자가 DB에 존재한다면 반환하고, 그렇지 않으면 404를 보내준다.
		if(profileUser != null) {
			return new ResponseEntity<User>(profileUser, HttpStatus.OK);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
		}
		
	}
	
}
