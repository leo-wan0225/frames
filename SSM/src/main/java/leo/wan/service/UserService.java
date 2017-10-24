package leo.wan.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import leo.wan.entity.User;
import leo.wan.mapper.UserMapper;

import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {
	@Resource
	private UserMapper userMapper;
	public List<User> findUserByPage(Map<String, Object> map){
		return userMapper.findUserByPage(map);
	}
	public int getTotalUserCount(Map<String, Object> map){
		return userMapper.getTotalUserCount(map);
	}
}
