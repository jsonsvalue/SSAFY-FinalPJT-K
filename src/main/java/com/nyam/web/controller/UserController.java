package com.nyam.web.controller;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nyam.model.dto.User;
import com.nyam.model.service.UserService;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Refill;
import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/api")
public class UserController {
	
	
	
	
	
	UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
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
	@GetMapping("/getUserInfo")
	public ResponseEntity<?> getUserInfo(HttpSession session){
		User sessUser = (User)session.getAttribute("loginUser");
		
		if(sessUser != null) {
			System.out.println("Get Session");
			System.out.println(session.getId());
			
			return new ResponseEntity<User>(sessUser, HttpStatus.OK);
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
	public ResponseEntity<?> logout(HttpSession session, User user){
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
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteUser(HttpSession session, User user){
		User sessUser = (User)session.getAttribute("loginUser");
		
		if(sessUser != null) {
			userService.deleteUser(session);
			session.removeAttribute("loginUser");
			return ResponseEntity.status(HttpStatus.OK).body("User Deleted Successfully");
			
		}else
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Failed to delete user");
		
	}
	
	
	@PatchMapping("/update")
	public ResponseEntity<?> updateUser(HttpSession session, User modUser){
		User sessUser = (User)session.getAttribute("loginUser");
		
		if(sessUser != null) {
			
			// 업데이트된 정보를 modUser에 반영하고,
			// DB에 업데이트한다.
			// modUser.setId(sessUser.getId());
			boolean updated = userService.updateUser(modUser);
			
			// 업데이트된 정보를 Session에 반영해준다.
			if(updated) {
				session.setAttribute("loginUser", modUser);
				
				return ResponseEntity.status(HttpStatus.OK).body("User Information Updated Successfully");
			}else {
				
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update User information");
			}
			
		}
		
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User Not logged in");
	}
	
	@GetMapping("/find")
	public ResponseEntity<?> findUser(@RequestParam("keyword")String keyword){
		
		//session이 없어도, 사용자 목록은 검색해서 확인할 수 있게는 한다.
		List<User> searchedUsers = userService.searchUser(keyword);
		
		return new ResponseEntity<List<User>>(searchedUsers, HttpStatus.OK);
	} 
	
	
}
