package leo.wan.test.unit;
//新增静态导入，这样Assert的静态方法可以直接使用了
import static org.junit.Assert.*;
import leo.wan.service.UserService;
import leo.wan.test.base.BaseJunit4Test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class TestJunit extends BaseJunit4Test{
	@Autowired
	UserService UserService;
	@Test
	public void testStaticImport(){
		assertNotNull("");
		//静态导入后不需要写类名了
		//Assert.assertNotNull(null);
	}
}
