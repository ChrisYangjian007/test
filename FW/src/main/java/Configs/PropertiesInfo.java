package Configs;

/**
 * Created by Administrator on 2016/12/6.
 */
public class PropertiesInfo {
    static java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("config");

    public static String getKeyValue(String p_strKey) {
        return bundle.getString(p_strKey);
    }
}
