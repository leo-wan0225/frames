package leo.wan.converter;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;
/**
 * 
 *Title: 
 *Description:自定义日期格式转换器，需要在spring-mvc.xml中配置
 *Company: 
 * @author Administrator
 * @date 2017年8月28日  下午11:42:11
 */
public class DateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		return null;
	}

}
