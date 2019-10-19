package leo.wan.test.unit;

import leo.wan.dao.CostProjectDetailMapper;
import leo.wan.dao.CostProjectDetailMapperExt;
import leo.wan.dao.RoleMapper;
import leo.wan.model.*;
import leo.wan.service.UserService;
import leo.wan.test.base.BaseJunit4Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestDao extends BaseJunit4Test {
    @Autowired
    UserService UserService;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    CostProjectDetailMapperExt costProjectDetailMapperExt;

    @Test
    public void testUserDao() {
        Map map = new HashMap(16);
        List<User> users = UserService.findUserByPage(map);
        System.out.println("----------------" + users);
    }

    @Test
    public void testGroupBy() {
        Map param = new HashMap();
        param.put("costProjectId", "31");
        param.put("level3Name", "level_3_name");
        List<CostProjectDetail> list = costProjectDetailMapperExt.getList(param);
        System.out.println(list.toString());
    }
    @Test
    public void testExample() {
        CostProjectDetailExample example = new CostProjectDetailExample();
        CostProjectDetailExample.Criteria criteria = example.createCriteria();
        criteria.andCostProjectIdEqualTo(31);
        List<CostProjectDetail> costProjectDetails = costProjectDetailMapperExt.selectByExample(example);
    }


    @Test
    public void testRoleDao() {
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        criteria.andIdEqualTo(1);
        List<Role> roles = roleMapper.selectByExample(roleExample);
        System.out.println("debug---------------------" + roles.toString());
    }
}
