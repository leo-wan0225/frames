package leo.wan.exeception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
/**
 * 实现异常统一处理
 * @author Administrator
 *
 */
public class ExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		 	Map<String, Object> model = new HashMap<String, Object>();  
	        model.put("ex", ex);  
	          
	        // 根据不同错误转向不同页面  
	        if(ex instanceof NullPointerException) {  
	        	System.out.println("空指针异常");
	            return new ModelAndView("error-business", model);  
	        }else if(ex instanceof ArithmeticException) {  
	        	System.out.println("除0异常");
	            return new ModelAndView("error-parameter", model);  
	        } else {  
	            return new ModelAndView("error", model);  
	        }  
	}

}
