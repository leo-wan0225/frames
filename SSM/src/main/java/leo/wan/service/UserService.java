package leo.wan.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import leo.wan.annotation.LogDescribe;
import leo.wan.entity.User;
import leo.wan.mapper.UserMapper;

import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {
	@Resource
	private UserMapper userMapper;
	@LogDescribe(description="查询用户信息重载方法")
	public List<User> findUserByPage(String map){
		return null;
	}
	@LogDescribe(description="查询用户信息")
	public List<User> findUserByPage(Map<String, Object> map){
		return userMapper.findUserByPage(map);
	}
	public int getTotalUserCount(Map<String, Object> map){
		return userMapper.getTotalUserCount(map);
	}
	
}
