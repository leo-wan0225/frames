package leo.wan.test.base;


import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * JUnit整合Spring test 的基类,其他测试类继承该类就可以直接使用依赖注入得到测试对象
 * 其他使用方法依然是在方法上加上junit的@Test注解标识
 * @author Administrator
 *
 */
//使用junit测试
@RunWith(SpringJUnit4ClassRunner.class)
//加载spring配置文件
@ContextConfiguration({"/applicationContext.xml"})
public class BaseJunit4Test {
	
}
