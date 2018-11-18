package leo.wan.aspect;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import leo.wan.annotation.LogDescribePaser;
import leo.wan.utils.DateUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 
 * Title: Description:利用aop的方式实现日志的统一处理 Company:
 * 
 * @author Administrator
 * @date 2017年8月29日 上午11:17:13
 */
public class LogAspect {
	//使用slf4j的方式
	private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);// slf4j日志记录器

	public Object loggingAroundForController(ProceedingJoinPoint jp) throws Throwable {
		// 获取请求,可以得到用户信息
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		String ipAddr = request.getRemoteAddr();
		String methodName = jp.getSignature().toLongString();
		List list = Arrays.asList(jp.getArgs());
		System.out.println(ipAddr);
		// 反射获取一些信息
		Class clazz = jp.getTarget().getClass();
		String className = clazz.getName();
		logger.info("*******************************Controller开始**********************************");
		logger.info("要调用的类名:{}",clazz.getName());
		logger.info("方法名:{}",methodName);
		logger.info("方法参数:{}" , list);
		logger.info("方法的描述信息{}",LogDescribePaser.getDescription(clazz, methodName));
		Date dateStart = new Date();
		logger.info("开始执行时间:{}",DateUtils.dateToString(dateStart, "yyyy-MM-dd HH:mm:ss"));
		Object object = null;
		long startMil = dateStart.getTime();
		// 调用目标方法
		try {
			object = jp.proceed(jp.getArgs());
		} catch (Throwable e) {
			throw e;
		}
		Date dateEnd = new Date();
		long endMil = dateEnd.getTime();
		logger.info("结束执行时间:{}" , DateUtils.dateToString(dateEnd, "yyyy-MM-dd HH:mm:ss"));
		logger.info("耗时:{}" , (endMil - startMil));
		logger.info("*******************************Controller结束**********************************");

		return object;
	}
	public Object loggingAroundForService(ProceedingJoinPoint jp) throws Throwable {
		// 获取请求,可以得到用户信息
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		String ipAddr = request.getRemoteAddr();
		String methodName = jp.getSignature().toLongString();
		List list = Arrays.asList(jp.getArgs());
		System.out.println(ipAddr);
		// 反射获取一些信息
		Class clazz = jp.getTarget().getClass();
		String className = clazz.getName();
		logger.info("===============================Service开始=================================");
		logger.info("要调用的类名:{}" , clazz.getName());
		logger.info("方法名:{}",methodName);
		logger.info("方法参数:{}" , list);
		logger.info("方法的描述信息:{}",LogDescribePaser.getDescription(clazz, methodName));
		Date dateStart = new Date();
		logger.info("开始执行时间:{}",DateUtils.dateToString(dateStart, "yyyy-MM-dd HH:mm:ss"));
		Object object = null;
		long startMil = dateStart.getTime();
		// 调用目标方法
		try {
			object = jp.proceed(jp.getArgs());
		} catch (Throwable e) {
			throw e;
		}
		Date dateEnd = new Date();
		long endMil = dateEnd.getTime();
		logger.info("结束执行时间:{}" , DateUtils.dateToString(dateEnd, "yyyy-MM-dd HH:mm:ss"));
		logger.info("耗时:{}",(endMil - startMil));
		logger.info("=============================Service结束=============================");

		return object;
	}
	public Object loggingAroundForDao(ProceedingJoinPoint jp) throws Throwable {
		// 获取请求,可以得到用户信息
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		String ipAddr = request.getRemoteAddr();
		String methodName = jp.getSignature().toLongString();
		List list = Arrays.asList(jp.getArgs());
		System.out.println(ipAddr);
		// 反射获取一些信息
		Class clazz = jp.getTarget().getClass();
		String className = clazz.getName();
		logger.info("------------------------------Dao开始-----------------------------");
		logger.info("要调用的类名:{}",clazz.getName());
		logger.info("方法名:{}",methodName);
		logger.info("方法参数:{}" , list);
		logger.info("方法的描述信息:{}",LogDescribePaser.getDescription(clazz, methodName));
		Date dateStart = new Date();
		logger.info("开始执行时间:{}",DateUtils.dateToString(dateStart, "yyyy-MM-dd HH:mm:ss"));
		Object object = null;
		long startMil = dateStart.getTime();
		// 调用目标方法
		try {
			object = jp.proceed(jp.getArgs());
		} catch (Throwable e) {
			throw e;
		}
		Date dateEnd = new Date();
		long endMil = dateEnd.getTime();
		logger.info("结束执行时间{}" , DateUtils.dateToString(dateEnd, "yyyy-MM-dd HH:mm:ss"));
		logger.info("耗时:{}" , (endMil - startMil));
		logger.info("------------------------------Dao结束--------------------------");

		return object;
	}
}
