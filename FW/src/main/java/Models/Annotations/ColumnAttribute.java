package Models.Annotations;

import Models.Enum.ColumnType;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.FIELD})//表示用于标识方法
@Retention(RetentionPolicy.RUNTIME)//表示运行时保留
@Documented
public @interface ColumnAttribute {
    /**
     * 列名
     */
    String Name() default "";
    /**
     * 是否主键
     */
    boolean IsPrimaryKey() default false;
    /**
     * 表单名称
     */
    ColumnType ColumnType() default ColumnType.NVarChar;
    /**
     * 是否忽略
     */
    boolean Ignore() default false;
    /**
     * 长度
     */
    int Length()  default 0;
    /**
     * 备注
     */
    String Description() default "";
}
