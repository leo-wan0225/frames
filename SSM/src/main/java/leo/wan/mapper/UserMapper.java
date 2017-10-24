package leo.wan.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import leo.wan.entity.User;
@Repository
public interface UserMapper {
	List<User> findUserByPage(Map<String, Object> map);
	Integer getTotalUserCount(Map<String, Object> map);
}
