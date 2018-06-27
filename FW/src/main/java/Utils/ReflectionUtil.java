package Utils;

import Core.ORM.Mapping;
import Core.ORM.ModelCache;
import Models.Annotations.ColumnAttribute;
import Models.Filter;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * Created by Administrator on 2017-09-19.
 */
public class ReflectionUtil {

    /// <summary>
    /// 获取实体类主键字段
    /// </summary>
    /// <param name="entity">实体类</param>
    public static <T> Object GetKeyFieldValue(T entity, String p_strKey) {
        Type m_Type = entity.getClass();
        Mapping Map = ModelCache.GetInfo(m_Type);//缓存对象

        for (Field propertie : Map.ModelList.keySet()) {
            if (propertie != null && propertie.getName() == p_strKey) {
                try {
                    propertie.setAccessible(true);
                    return propertie.get(entity);
                } catch (Exception ex) {
                    return null;
                }
            }
        }
        return null;
    }

    /// <summary>
    /// 获取实体类主键字段
    /// </summary>
    /// <param name="entity">实体类</param>
    public static <T> void SetKeyFieldValue(T entity, Hashtable phtPara) {
        if (phtPara.size() > 0) {
            Type m_Type = entity.getClass();
            Mapping Map = ModelCache.GetInfo(m_Type);//缓存对象

            for (Field propertie : Map.ModelList.keySet()) {
                if (phtPara.contains(propertie.getName())) {
                    try {
                        propertie.setAccessible(true);
                        propertie.set(entity, phtPara.get(propertie.getName()));
                    } catch (Exception ex) {

                    }
                }
            }
        }
    }

    /// <summary>
    /// 获取实体类主键字段
    /// </summary>
    /// <param name="entity">实体类</param>
    public static <T> void SetKeyFieldValue(T entity, int pKeyID) {
        Hashtable mhtPara = new Hashtable();
        Type m_Type = entity.getClass();
        Mapping Map = ModelCache.GetInfo(m_Type);//缓存对象
        mhtPara.put(Map.getIndexName(), pKeyID);//主键
        SetKeyFieldValue(entity, mhtPara);
    }

    /// <summary>
    /// 获取实体类主键字段
    /// </summary>
    /// <param name="entity">实体类</param>
    public static <T> Boolean IsKeyField(T entity, String pstrKey) {
        Type m_Type = entity.getClass();
        Mapping Map = ModelCache.GetInfo(m_Type);//缓存对象
        for (Field propertie : Map.ModelList.keySet()) {
            if (propertie.getName().toLowerCase() == pstrKey.toLowerCase()) {
                return true;
            }
        }
        return false;
    }

    /// <summary>
    /// 获取实体类主键字段
    /// </summary>
    public static <T> Filter GetKeyFilter(T entity) {
        Type m_Type = entity.getClass();
        Mapping Map = ModelCache.GetInfo(m_Type);//缓存对象
        Iterator iter = Map.ModelList.entrySet().iterator();
        Field key = null;
        ColumnAttribute val = null;
        //赋值字段
        while (iter.hasNext()) {
            java.util.Map.Entry entry = (java.util.Map.Entry) iter.next();
            val = (ColumnAttribute) entry.getValue();
            if (val.Name() == Map.getIndexName()) {
                key = (Field) entry.getKey();
                break;
            }
        }
        key.setAccessible(true);
        try {
            return Filter.Equal(Map.getIndexName(), (key != null) ? key.get(entity).toString() : "");
        }
        catch (Exception ex)
        {
            return  null;
        }
    }
}