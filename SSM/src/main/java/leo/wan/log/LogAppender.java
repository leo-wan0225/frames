package leo.wan.log;


import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Priority;
/**
 * 
 *Title: 这个类应该没啥用
 *Description:实现log日志的按级别分类存放,如果要实现按级别存放日志，则需要在log的配置文件中引用该类的路径即可
 *Company: 
 * @author Administrator
 * @date 2017年8月28日  下午11:07:04
 */
public class LogAppender extends DailyRollingFileAppender {
	@Override
	public boolean isAsSevereAsThreshold(Priority priority) {
		return this.getThreshold().equals(priority);  
	}
}
