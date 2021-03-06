package leo.wan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import leo.wan.model.PageBean;
import leo.wan.model.User;
import leo.wan.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 用户管理controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/user/")
public class UserController  {
	@Autowired
	private UserService UserService;
	@RequestMapping("toList")
	public String toUserList(){
		System.out.println("success");
		System.out.println("这个插件太棒了");
		return "admin/userList";
	}
	@RequestMapping("list")
	@ResponseBody
	public  Map<String,Object> list(PageBean pageBean,User user){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageSize", pageBean.getPageSize());
		map.put("pageStar", pageBean.getStart());
		/*String iString = null;
		iString.endsWith("e");*/
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
