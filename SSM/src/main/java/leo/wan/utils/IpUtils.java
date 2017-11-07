package leo.wan.utils;
/**
 * 
 *@Title ip地址的操作  
 *@Description 随着经验和技术的增长需要不断更新
 *@author leo
 *@date 2017年10月3日  下午4:44:39
 */
public class IpUtils {
	private IpUtils() {

	}

	/**
	 * 把ip地址格式化为：000.000.000.000
	 * @param ip
	 * @return 规则的ip
	 */
	public static String strfullip(String ip) {
		StringBuffer buff = new StringBuffer();
		String strZero = "000";
		if (ip != null) {
			String[] arrIp = ip.split("\\.");
			if (arrIp.length == 4) {
				for (int i = 0; i < 4; i++) {
					int length = arrIp[i].length();
					if (length < 3) {
						buff.append(".")
								.append(strZero.substring(0, 3 - length))
								.append(arrIp[i]);
					} else {
						buff.append(".").append(arrIp[i]);
					}
				}
			}
		}
		return buff.toString().substring(1);
	}
}
