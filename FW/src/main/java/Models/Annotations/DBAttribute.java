package Models.Annotations;

import Models.Enum.DataBaseType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2017-07-23.
 */
@Target({ElementType.TYPE,ElementType.METHOD})//表示用于标识方法
@Retention(RetentionPolicy.RUNTIME)//表示运行时保留
public @interface DBAttribute {
    /**
     * 主键字段
     */
    String SequenceKey() default "";
    /**
     * 表名
     */
    String Name() default "";
    /**
     * 设置数据库类型
     */
    DataBaseType DataType() default DataBaseType.SqlServer;
    /**
     * 是否忽略
     */
    boolean Ignore() default false;
    /**
     * 设置数据库连接配置(Dbconfig)的索引项
     */
    int DBIndex() default 0;
    /**
     * 设置数据库连接字符串
     */
    String ConnString() default "";
    /**
     * 设置数据库执行T-SQL时间，单位秒默认是30秒
     */
    int CommandTimeout() default 30;
    /**
     * 版本类型
     */
    String DataVer() default "com.microsoft.sqlserver.jdbc.SQLServerDriver";
}
