package leo.wan.annotation;

import java.lang.reflect.Method;

/**
 * LogDescribe注解解析器,用来获取对应方法上的值,可以学习这种处理的思想
 * @author Administrator
 *
 */
public class LogDescribePaser {
	public static String getDescription(Class clazz,String methodName){
		//得到目标对象的所有方法
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			//防止方法重载需要用method.toString()方法获得方法的签名来比较
			if (methodName.equals(method.toString())) {
				//返回该方法上的描述信息
				LogDescribe logDescribe =  method.getAnnotation(LogDescribe.class);
				if (logDescribe!=null) {
					return logDescribe.description();
				}
			}
		}
		return "";
	}
}
