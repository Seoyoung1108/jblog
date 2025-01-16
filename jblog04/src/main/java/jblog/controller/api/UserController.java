package jblog.controller.api;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jblog.dto.JsonResult;
import jblog.service.UserService;
import jblog.vo.UserVo;

@RestController("userApiController") // bean 등록 시 id, name(안 겹치게)
@RequestMapping("/api")
public class UserController {
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService=userService;
	}
	
	// "{ exist:false or true }
	// @ResponseBody => RestController 사용으로 생략 가능
	@GetMapping("/checkid")
	public JsonResult checkEmail(@RequestParam(value="id", defaultValue="", required=true) String id) {
		UserVo userVo = userService.getUser(id);
		
		return JsonResult.success(Map.of("exist", userVo!=null));	
	}	
}
