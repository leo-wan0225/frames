package leo.wan.test.unit;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import leo.wan.dao.CostProjectDetailMapper;
import leo.wan.dao.CostProjectDetailMapperExt;
import leo.wan.dao.QuestionItemMapperExt;
import leo.wan.dao.RoleMapper;
import leo.wan.model.*;
import leo.wan.service.QuestionItemService;
import leo.wan.service.SupplierAnswerService;
import leo.wan.service.UserService;
import leo.wan.test.base.BaseJunit4Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sound.midi.Soundbank;
import java.util.*;

public class TestDao extends BaseJunit4Test {
    @Autowired
    UserService UserService;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    CostProjectDetailMapperExt costProjectDetailMapperExt;
    @Autowired
    QuestionItemMapperExt questionItemMapperExt;
    @Autowired
    private SupplierAnswerService supplierAnswerService;
    @Autowired
    private QuestionItemService questionItemService;

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

    /**
     * 测试分页插件的分页功能
     */
    @Test
    public void testPage() {
        Page page1 = PageHelper.startPage(1, 5);
        CostProjectDetailExample example = new CostProjectDetailExample();
        CostProjectDetailExample.Criteria c = example.createCriteria();
        c.andLevel3NameEqualTo("司法程序");
        List<CostProjectDetail> costProjectDetails = costProjectDetailMapperExt.selectByExample(example);
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        Page page2 = PageHelper.startPage(1, 6);
        List<Role> roles = roleMapper.selectByExample(roleExample);
        int i = 10 + 9;
        long count = page1.getTotal();
        long count2 = page2.getTotal();
        //根据情况封装对象
        PageInfo pageInfo = new PageInfo(costProjectDetails);
    }

    /**
     * 测试链表分页查询。单边主表分页是否存在问题：筛选条件在主表上？筛选条件在副表上？采用子查询是否可以解决这个问题？用pageHelp的插件是否能够解决这个问题？
     */
    /**
     * 测试切面也用了这个方法
     */
    @Test
    public void testJoinPage() {
        //不使用分页插件出现的情况
        Map params = new HashMap();
        leo.wan.common.Page page = new leo.wan.common.Page(1, 3);
        params.put("page", page);
        List<QuestionItemExt> questionItemExts = questionItemMapperExt.getQuestionItemsByPage(params);
        Page page1 = PageHelper.startPage(1, 3);
        List<QuestionItemExt> questionItemExts2 = questionItemMapperExt.getQuestionItems();
        long count = page1.getTotal();
        //由以上代码可以知道，mybatis的分页插件确实不支持多表连接的分页操作。且以上的分页也是有问题的（由于分页参数查出来的结果多方会少数据）。需要对主表进行分页，然后在连接副表。
        //以下是正确的分页方式
        //分页插件加子查询的方式，分页插件分页的是主表
        Page page2 = PageHelper.startPage(1, 3);
        List<QuestionItemExt> questionItemExts3 = questionItemMapperExt.getQuestionItemsWithSelect();
        long count2 = page2.getTotal();
        //以上正确的子查询思路和自己实现先对主表进行分页在连接副表查询的思路是一致的
    }


    @Test
    public void testTransaction() {

        questionItemService.getQuestionItemsByPage();
    }

    @Test
    public void testTransaction2() {
        supplierAnswerService.add();
        //questionItemService.getQuestionItemsByPage();
    }

    @Test
    public void temp() {
        System.out.println(Integer.MAX_VALUE - 2);
    }

    /**
     * 测试自动维护创建事件和修改时间的功能
     */
    @Test
    public void testTwoTime() {
        QuestionItem questionItem = new QuestionItem();
        questionItem.setId(16);
        //questionItem.setAttribute("newset11");
        questionItem.setSubArrtibute("subattr1212");
        //questionItem.setQuestionType("1");
        questionItemMapperExt.updateByPrimaryKeySelective(questionItem);
        //  questionItemMapperExt.insert(questionItem);
       /* QuestionItem questionItem1 = new QuestionItem();
        questionItem1.setAttribute("wanjing");
        QuestionItemExample example = new QuestionItemExample();
        QuestionItemExample.Criteria criteria = example.createCriteria();
        criteria.andSubArrtibuteEqualTo("wanjing");
      //  questionItemMapperExt.updateByExampleSelective(questionItem1,example);
        questionItemMapperExt.insertSelective(questionItem1);*/
    }

    @Test
    public void testAutoSetTimeAspect() {
        QuestionItem questionItem = new QuestionItem();
        questionItem.setAttribute("testAutoAAAAect11111");
        questionItem.setSubArrtibute("testAutBBBpect11111");
        QuestionItem questionItem2 = new QuestionItem();
        questionItem2.setAttribute("testAutoSCCCpect22222");
        questionItem2.setSubArrtibute("testAuDDDTimeAspect22222");
        List<QuestionItem> questionItems = new ArrayList<>(16);
        questionItems.add(questionItem);
        questionItems.add(questionItem2);
        questionItemMapperExt.insertBatch(questionItems);
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
