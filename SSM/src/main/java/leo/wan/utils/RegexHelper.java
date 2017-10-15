package leo.wan.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
/**
 * 
 *@Title  正则表达式工具类,模板如果不加量词,就是表示一个,符合模版要求的叫做一个子序列
 *@Description 随着经验和技术的增加，需要不断更新该类
 *@author leo
 *@date 2017年10月3日  下午8:58:04
 */
public class RegexHelper {
	/**
	 * 匹配数字
	 */
	public static final String NUMBER= "^[0-9]*$";
	/**
	 * 匹配汉字
	 */
	public static final String CHINESE_CHARACTERS = "^[\u4e00-\u9fa5]{0,}$";
	/**
	 * 匹配字符和数字
	 */
	public static final String LETTERS_AND_NUMBERS = "^[A-Za-z0-9]+$";
	/**
	 * 匹配非负数
	 */
	public static final String NONNEGATIVE_INTEGER="^\\d+$";
	/**
	 * 匹配非正数
	 */
	public static final String Non_positive = "^-[1-9]\\d*|0$";
	/**
	 * 匹配非零开头的最多带两位小数的数字
	 */
	public static final String TWO_DECIMAL="^([1-9][0-9]*)+(.[0-9]{1,2})?$";
	/**
	 * 匹配字母
	 */
	public static final String LETTER = "^[A-Za-z]+$";
	/**
	 * 匹配Email地址
	 */
	public static final String EMAIL_ADDRESS = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
	/**
	 * 匹配手机号码
	 */
	public static final String MOBILE_PHONE_NUMBER = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
	/**
	 * 匹配国内电话号码
	 */
	public static final String  CHINA_TELEPHONE_NUMBER  ="\\d{3}-\\d{8}|\\d{4}-\\d{7}";
	/**
	 * 匹配身份证号
	 */
	public static final String ID_CARD = "^\\d{15}|\\d{18}$";
	/**
	 * 匹配ip地址
	 */
	public static final String IP_ADDRESS="((?:(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d)\\.){3}(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d))";
	/**
	 * 匹配邮编
	 */
	public static final String POSTAL_CODE="[1-9]\\d{5}(?!\\d)";
	
	/**
	 * 要求从头到尾大小写作为一个子序列匹配正则表达式，这个时候正则表达式前后不需要显示加上^和$
	 * 
	 * @param pattern
	 *            正则表达式模式
	 * @param str
	 *            要匹配的字串
	 * @return 匹配返回true
	 */
	public static boolean matcher(String pattern, String str)
			throws PatternSyntaxException {
		try {
			Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(str);
			return m.matches();
		} catch (PatternSyntaxException e) {
			throw e;
		}
	}

	/**
	 * 判断字符串是否包含符合模板的子序列,可以通过在正则表达式前后分别加上^和$来实现matcher方法的效果
	 * 
	 * @param pattern
	 *            正则表达式模式
	 * @param str
	 *            要匹配的字串
	 * @return 包含返回true
	 */
	public static boolean contains(String pattern, String str)
			throws PatternSyntaxException {
		try {
			Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(str);
			return m.find();
		} catch (PatternSyntaxException e) {
			throw e;
		}
	}

	/**
	 * 匹配且替换字串
	 * 
	 * @param pattern
	 *            正则表达式模式
	 * @param newStr
	 *            需要替换为的新字符串
	 * @param oldStr
	 *            需要替换的就字符串
	 * @return 替换后的字符串,没有匹配时返回原串
	 * @throws PatternSyntaxException
	 */
	public static final String matcherAndReplace(String pattern, String newStr,
			String oldStr) throws PatternSyntaxException {
		try {
			Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(oldStr);
			return m.replaceAll(newStr);
		} catch (PatternSyntaxException e) {
			throw e;
		}
	}

	/**
	 * 将匹配到的子序列放入list中返回
	 * 
	 * @param pattern为正则表达式模式
	 * @param str
	 *            原始字串
	 * @return list
	 */
	public static List matcherToList(String pattern, String str)
			throws PatternSyntaxException {
		ArrayList list = new ArrayList();
		try {
			Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(str);
			while (m.find()) {
				list.add(m.group());
			}
			return list;
		} catch (PatternSyntaxException e) {
			throw e;
		}
	}

	/**
	 * 转义正则表达式字符(之所以需要将\和$字符用escapeDollarBackslash方法的方式是因为用repalceAll是不行的，简单的试试
	 * "$".repalceAll("\\$","\\\\$")你会发现这个调用会导致数组越界错误)
	 * 
	 * @param pattern为正则表达式模式
	 * @param str
	 *            原始字串
	 * @return array
	 */
	public static String escapeDollarBackslash(String original) {
		StringBuffer buffer = new StringBuffer(original.length());
		for (int i = 0; i < original.length(); i++) {
			char c = original.charAt(i);
			if (c == '\\' || c == '$') {
				buffer.append("\\").append(c);
			} else {
				buffer.append(c);
			}
		}
		return buffer.toString();
	}

	private RegexHelper() {

	}
}
