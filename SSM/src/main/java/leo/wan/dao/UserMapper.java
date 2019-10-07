package leo.wan.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import leo.wan.model.User;
@Repository
public interface UserMapper {
	List<User> findUserByPage(Map<String, Object> map);
	Integer getTotalUserCount(Map<String, Object> map);
}
