package leo.wan.test.unit;

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
    @Test
    public void testUserDao() {
        Map map = new HashMap(16);
        List<User> users = UserService.findUserByPage(map);
        System.out.println("----------------"+users);
    }
}
