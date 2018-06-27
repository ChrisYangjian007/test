package Extend;

import Core.ORM.Mapping;
import Core.ORM.ModelCache;
import Models.Annotations.ColumnAttribute;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2017-07-25.
 */
public class ListExtend <T> {
    public static <T> List<T> ToList(Class<T> t, ResultSet reader) {
        List<T> list = new ArrayList<T>();
        try {
            if (reader == null) {
                return null;
            }
            Type m_Type = t.newInstance().getClass();
            Mapping Map = ModelCache.GetInfo(m_Type);//缓存对象
            String m_strColumn = GetColumnName(reader);
            while (reader.next()) {
                T m_objT = t.newInstance();
                Iterator iter = Map.ModelList.entrySet().iterator();
                //赋值字段
                while (iter.hasNext()) {
                    java.util.Map.Entry entry = (java.util.Map.Entry) iter.next();
                    Field key = (Field) entry.getKey();
                    ColumnAttribute val = (ColumnAttribute) entry.getValue();
                    Object m_objValue = null;
                    if (val != null && !val.Ignore() && m_strColumn.indexOf("," + val.Name() + ",") >= 0) {
                        m_objValue = reader.getObject(val.Name());
                    }
                    key.setAccessible(true);
                    if (val != null && !val.Ignore() && m_strColumn.indexOf("," + val.Name() + ",") >= 0 && m_objValue != null) {
                        key.set(m_objT, ObjectExtend.ConvertType(m_objValue, key.getType().getSimpleName()));
//                        key.invoke(m_objT,ObjectExtend.ConvertType(m_objValue, key.getParameterTypes()[0].getSimpleName()));
                    }
                }
                list.add(m_objT);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    /// IDataReader转换为实体类
    /// <typeparam name="T">实体类</typeparam>
    /// <param name="reader">源IDataReader</param>
    /// <param name="p_StartNum">开始从0</param>
    /// <param name="p_PageSize">每页条数</param>
    /// <param name="p_RowCount">总记录条数(当小于0不返回)</param>
    public static <T> List<T> ToList(Class<T> t, ResultSet reader, int p_StartNum, int p_PageSize, boolean p_getTotalCount) {
        if (reader == null) {
            return null;
        }
        List<T> list = new ArrayList<T>();
        try {
            if (p_PageSize != 0) {
                Type m_Type = t.newInstance().getClass();
                Mapping Map = ModelCache.GetInfo(m_Type);
                String m_strColumn = GetColumnName(reader);
                int k = p_StartNum;
                reader.absolute(p_StartNum);
                while (reader != null && reader.next()) {
                    //读取分页间数据
                    if (p_StartNum <= k && k < p_StartNum + p_PageSize) {
                        T m_objT = t.newInstance();
                        //赋值字段
                        for (Object o : Map.ModelList.entrySet()) {
                            java.util.Map.Entry entry = (java.util.Map.Entry) o;
                            Field key = (Field) entry.getKey();
                            ColumnAttribute val = (ColumnAttribute) entry.getValue();
                            Object m_objValue = null;
                            if (val != null && !val.Ignore() && m_strColumn.indexOf("," + val.Name() + ",") >= 0) {
                                m_objValue = reader.getObject(val.Name());
                            }
                            key.setAccessible(true);
                            if (val != null && !val.Ignore() && m_strColumn.indexOf("," + val.Name() + ",") >= 0 && m_objValue != null) {
                                key.set(m_objT, ObjectExtend.ConvertType(m_objValue, key.getType().getSimpleName()));
//                                key.invoke(m_objT, ObjectExtend.ConvertType(m_objValue, key.getParameterTypes()[0].getSimpleName()));
                            }
                        }
                        list.add(m_objT);
                    }
                    if (k > p_StartNum + p_PageSize) {
                        break;
                    }
                    k++;
                }
                return list;
            } else {
                return ToList(t, reader);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /// <summary>
    /// 数据填充
    /// </summary>
    /// <param name="reader">源IDataReader</param>
    /// <typeparam name="T">实体类</typeparam>
    public static <T> T ToInfo(Class<T> t, ResultSet reader) {
        if (reader == null) {
            return null;
        }
        boolean isHaveValue = false;
        try {
            T m_objT = t.newInstance();
            Type m_Type = m_objT.getClass();
            Mapping Map = ModelCache.GetInfo(m_Type);//缓存对象
            String m_strColumn = GetColumnName(reader);
            if (reader.next()) {
                Iterator iter = Map.ModelList.entrySet().iterator();
                //赋值字段
                while (iter.hasNext()) {
                    java.util.Map.Entry entry = (java.util.Map.Entry) iter.next();
                    Field key = (Field) entry.getKey();
                    ColumnAttribute val = (ColumnAttribute) entry.getValue();
                    Object m_objValue = null;
                    if (val != null && !val.Ignore() && m_strColumn.indexOf("," + val.Name() + ",") >= 0) {
                        m_objValue = reader.getObject(val.Name());
                    }
                    key.setAccessible(true);
                    if (val != null && !val.Ignore() && m_strColumn.indexOf("," + val.Name() + ",") >= 0 && m_objValue != null) {
                        key.set(m_objT, ObjectExtend.ConvertType(m_objValue, key.getType().getSimpleName()));
//                        key.invoke(m_objT, ObjectExtend.ConvertType(m_objValue, key.getParameterTypes()[0].getSimpleName()));
                        isHaveValue = true;
                    }
                }
            }
            return isHaveValue ? m_objT : null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String GetColumnName(ResultSet reader) {
        String m_strReturn = "";
        if (reader == null) {
            return "";
        }
        try {
            ResultSetMetaData m_rsmd = reader.getMetaData();
            for (int i = 1; i <= m_rsmd.getColumnCount(); i++) {
                m_strReturn += "," + m_rsmd.getColumnName(i);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return m_strReturn + ",";
    }

    /// 判断IDataReader是否存在某列
    public static boolean HaveName(ResultSet reader, String name) throws SQLException {
        for (int i = 0; i < reader.getMetaData().getColumnCount(); i++) {
            if (reader.getMetaData().getColumnName(i).toLowerCase().equals(name.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}