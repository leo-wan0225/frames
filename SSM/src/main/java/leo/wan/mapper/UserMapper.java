package leo.wan.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import leo.wan.entity.User;

public interface UserMapper {
	User findUserByNameAndPwd(@Param("id2")int id,@Param("pwd")String pwd);
}
