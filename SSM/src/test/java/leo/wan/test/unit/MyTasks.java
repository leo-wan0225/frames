package leo.wan.test.unit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import leo.wan.utils.DateUtils;

public class MyTasks {
	private static final Logger logger = LoggerFactory.getLogger(MyTasks.class);
	public void task1(){
		//正常的记录异常的方法，必须将异常对象放在 info记录方法的最后，否则不会打印错误的详细详细
		logger.info("executor task1:{}",DateUtils.getStringFullDate(),new RuntimeException("报错"));
	}
	public void task2(){
		logger.info("executor task2:{},{}","这是个非最后的报错",new RuntimeException("kankan"),23444);
	}
}
