package Core.ORM;

import Extend.StringExtend;
import Models.Annotations.ColumnAttribute;
import Models.Annotations.DBAttribute;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017-07-23.
 */
public class Mapping {
    private DBAttribute classInfo;
    public DBAttribute getClassInfo() {
        return classInfo;
    }
    public void setClassInfo(DBAttribute classInfo) {
        this.classInfo = classInfo;
    }
    private String indexName;
    public String getIndexName() {
        return indexName;
    }
    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }
    private Type m_type;
    public Type getType() {
        return m_type;
    }
    public void setType(Type type) {
        this.m_type = type;
    }

    // 获取所有属性
    public Map<Field, ColumnAttribute> ModelList;
    // 关系映射
    protected Mapping(Type type) {
        Class<?> m_objClass =(Class)type;
        //判断是不是使用了我们刚才定义的注解接口
        boolean isEmpty = m_objClass.isAnnotationPresent(DBAttribute.class);
        if (isEmpty){
            classInfo = m_objClass.getAnnotation(DBAttribute.class);
        }
        ModelList = new HashMap<Field, ColumnAttribute>();
        for (Field field : m_objClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(ColumnAttribute.class)) {
                ColumnAttribute m_ColumnAttribute = field.getAnnotation(ColumnAttribute.class);
                ModelList.put(field, m_ColumnAttribute);
            }
        }
        ColumnAttribute m_objColumn = GetModelInfo("");
        indexName = m_objColumn != null ? m_objColumn.Name() : GetModelInfo("ID").Name();
        this.m_type = type;
    }
    // 获取当前属性（通过使用的userName）
    public ColumnAttribute GetModelInfo(String Name) {
        ColumnAttribute m_return = null;
        if (StringExtend.Empty(Name)) {
            for (ColumnAttribute tmp : ModelList.values()) {
                if (tmp.IsPrimaryKey()) {
                    m_return = tmp;
                    break;
                }
            }
        } else {
            m_return = ModelList.get(Name);
        }
        return m_return;
    }
}
