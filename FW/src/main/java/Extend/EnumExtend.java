package Extend;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Method;
import java.security.InvalidParameterException;

/**
 * Created by Administrator on 2017-07-28.
 */
public class EnumExtend {
    public static <T> String GetName(Class<T> P_Enum)
    {
        if (!P_Enum.isEnum()) {
            throw new InvalidParameterException();
        }
        try {
            T[] enumConstants = P_Enum.getEnumConstants();
            JSONObject jsonObject = new JSONObject();
            for (T ec : enumConstants) {
                Method toDescription = ec.getClass().getMethod("toDescription");
                Method toCode =  ec.getClass().getMethod("toCode");
                if (toCode != null) {
                    jsonObject.put(String.valueOf((Integer)toCode.invoke(ec)),(String)toDescription.invoke(ec));
                }
            }
            return jsonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
