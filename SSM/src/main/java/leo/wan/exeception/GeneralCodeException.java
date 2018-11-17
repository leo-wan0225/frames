package leo.wan.exeception;
/**
 * 新增通用异常处理类，将对应的异常和code对应起来
 * @author wj
 *
 */
public class GeneralCodeException extends RuntimeException {
	private String code;

	public GeneralCodeException(String code,String message) {
		super(message);
		this.code = code;
	}
	
	public GeneralCodeException(String message,Throwable throwable) {
		super(message,throwable);
	}
	public GeneralCodeException(String code,String message,Throwable throwable) {
		super(message,throwable);
		this.code=code;
	}
}
