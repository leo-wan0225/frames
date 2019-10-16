package leo.wan.service;

import java.util.List;
import java.util.Map;


import leo.wan.annotation.LogDescribe;
import leo.wan.model.User;
import leo.wan.dao.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {
	@Autowired
	private UserMapper userMapper;
	@LogDescribe(description="查询用户信息")
	public List<User> findUserByPage(Map<String, Object> map){
		return userMapper.findUserByPage(map);
	}
	public int getTotalUserCount(Map<String, Object> map){
		return userMapper.getTotalUserCount(map);
	}
}
