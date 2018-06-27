package Models.Enum;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-07-23.
 */
public enum ColumnType  implements Serializable {
//    // 字符串类型
//    NVarChar(0, "字符串类型"),
//    // 整数类型
//    Int(1,"整数类型"),
//    // 时间类型
//    DateTime(2, "时间类型"),
//    // 字节类型
//    Char(3, "字节类型"),
//    // 长整型类型
//    Decimal(4, "长整型类型"),
//    // 二进制类型
//    Binary(5, "二进制类型"),
//    // 大文本类型
//    Text(6, "大文本类型"),
//    // 二进制类型
//    Image(21, "二进制类型"),
//    // 字符串类型
//    VarChar(8, "字符串类型");
//    /**
//     * 定义枚举类型自己的属性*
//     */
//    private int code;
//    private String desc;
//    private ColumnType(int code, String desc) {
//        this.code = code;
//        this.desc=desc;
//    }
//    public String toDescription() {
//        return this.desc == null ? this.name() : this.desc;
//    }
//    public int toCode() {
//        return this.code==0 ? this.ordinal() : this.code;
//    }
    // 字符串类型
    NVarChar(0),
    // 整数类型
    Int(1),
    // 时间类型
    DateTime(2),
    // 字节类型
    Char(3),
    // 长整型类型
    Decimal(4),
    // 二进制类型
    Binary(5),
    // 大文本类型
    Text(6),
    // 二进制类型
    Image(21),
    // 字符串类型
    VarChar(8);
    /**
     * 定义枚举类型自己的属性*
     */
    private int code;
    private String desc;
    private ColumnType(int code) {
        this.code = code;
//        this.desc=desc;
    }
    public String toDescription() {
        return this.desc == null ? this.name() : this.desc;
    }
    public int toCode() {
        return this.code==0 ? this.ordinal() : this.code;
    }
}
