package Extend;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2017-07-25.
 */
public class ObjectExtend {
    /// 将值转换成类型对像的值
    /// <param name="objValue">要转换的值</param>
    /// <param name="type">要转换的对像的类型</param>
    public static Object ConvertType(Object objValue, String strType) {
        if (objValue == null) {
            return null;
        }
        String s = strType.toLowerCase();
        switch (s) {
            case "date":
            case "datetime":
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    return formatter.parse(objValue.toString());
                } catch (ParseException ex) {
                    return null;
                }
            case "integer":
                return Integer.parseInt(objValue.toString());
            default:
                return objValue;
        }
    }
}
