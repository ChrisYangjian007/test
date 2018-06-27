package Utils;

import java.util.ResourceBundle;

/**
 * 项目参数工具类
 * @author John
 */
public class ConfigUtil {
	private static final ResourceBundle bundle = ResourceBundle.getBundle("config");
	/**
	 * 获得sessionInfo名字
	 */
	public static final String getSessionInfoName() {
		return bundle.getString("sessionInfoName");
	}

	/**
	 * 通过键获取值
	 */
	public static final String get(String key) {
		return bundle.getString(key);
	}
}
