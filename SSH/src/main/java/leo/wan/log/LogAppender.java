package leo.wan.log;


import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Priority;
/**
 * 
 *Title: 
 *Description:实现log日志的按级别分类存放
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
