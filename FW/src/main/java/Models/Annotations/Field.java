package Models.Annotations;

import Models.Enum.FieldIndex;
import Models.Enum.FieldType;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited()
public @interface Field
{
    // 字段的类型，如int,string等，通常情况下建议使用string
    FieldType type() default FieldType.text;
    // 是否对该字段进行索引操作（添加记录时对该字段建立索引）
    FieldIndex index() default FieldIndex.analyzed;
    // 是否对该字段进行索引查询操作
    boolean stored() default true;
    /*
     * 指定该字段使用的分析器(analyzer)（默认情况下使用IK分词器） （包括数据插入时建立索引和查询时所用的analyzer均为同一analyzer）
     */
    String analyzer() default "keyword";
    // String searchAnalyzer() default "";
    //
    // String indexAnalyzer() default "";
}
