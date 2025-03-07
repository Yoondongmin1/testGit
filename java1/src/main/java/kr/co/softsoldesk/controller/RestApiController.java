package kr.co.softsoldesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import kr.co.softsoldesk.service.UserService;

@RestController//응답결과가 데이터
public class RestApiController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/user/checkUserIdExist/{user_id}")
	public String checkUserIdExist(@PathVariable String user_id) {
		
		System.out.println(user_id);
		
		boolean chk = userService.checkUserIdExist(user_id);
		System.out.println(chk);
		
		return chk + "";//ID가 DB에 있으면 false, 없으면 true
	}
	
}
