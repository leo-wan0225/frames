package leo.wan.converter;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class MyDateConverter extends StrutsTypeConverter {

	public MyDateConverter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		String paramString = values[0];
		System.out.println("要转换的参数："+paramString);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date  = null;
		 try {
			date = sdf.parse(paramString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	@Override
	public String convertToString(Map context, Object o) {
		return null;
	}

}
