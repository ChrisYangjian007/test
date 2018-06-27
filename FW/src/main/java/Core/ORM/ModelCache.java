package Core.ORM;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public  class ModelCache {

    /// 缓存所有实体类
    protected static Map<Type, Mapping> ModelList =new HashMap<Type, Mapping>();
    protected static Object m_LockHelper = new Object();

    /// 返回实体类映射的信息
    /// <param name="type">实体类Type</param>
    public static Mapping GetInfo(Type type) {
        if (!ModelList.containsKey(type)) {
            synchronized (m_LockHelper) {
                if (!ModelList.containsKey(type)) {
                    ModelList.put(type, new Mapping(type));
                }
            }
        }
        return ModelList.get(type);
    }

    /// 清除缓存
    public static void ClearCache() {
        ModelList.clear();
    }
}
