package leo.wan.aspect;

import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.aspectj.lang.ProceedingJoinPoint;


/**
 * 
 *Title: 
 *Description:利用aop的方式实现日志的统一处理
 *Company: 
 * @author Administrator
 * @date 2017年8月29日  上午11:17:13
 */
public class LogAspect {
	Logger logger = Logger.getLogger(LogAspect.class);
	public Object loggingAround(ProceedingJoinPoint jp){
		
		
		//调用目标方法
		try {
			Object object = jp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return "";
	}
}
