package leo.wan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import leo.wan.model.PageBean;
import leo.wan.model.User;
import leo.wan.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 用户管理controller
 * @author Administrator
 *
 */
@Controller
public class UserController  {
	@Resource
	private UserService UserService;
	@RequestMapping("/user/toList")
	public String toUserList(){
		System.out.println("success");
		return "admin/userList";
	}
	@RequestMapping("/user/list")
	@ResponseBody
	public  Map<String,Object> list(PageBean pageBean,User user){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageSize", pageBean.getPageSize());
		map.put("pageStar", pageBean.getStart());
		String iString = null;
		iString.endsWith("e");
		map.put("user", user);
		List<User> users = UserService.findUserByPage(map);
		System.out.println(users);
		int total = UserService.getTotalUserCount(map);
		Map<String, Object> result=new HashMap<String, Object>();
		result.put("total", total);
		result.put("rows", users);
		return result;
	}
}
