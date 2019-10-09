package leo.wan.test.unit;

import leo.wan.dao.RoleMapper;
import leo.wan.model.Role;
import leo.wan.model.RoleExample;
import leo.wan.model.User;
import leo.wan.service.UserService;
import leo.wan.test.base.BaseJunit4Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestDao extends BaseJunit4Test {
    @Autowired
    UserService UserService;
    @Autowired
    RoleMapper roleMapper;

    @Test
    public void testUserDao() {
        Map map = new HashMap(16);
        List<User> users = UserService.findUserByPage(map);
        System.out.println("----------------" + users);
    }

    @Test
    public void testRoleDao() {
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        criteria.andIdEqualTo(1);
        List<Role> roles = roleMapper.selectByExample(roleExample);
        System.out.println(roles);
    }
}
