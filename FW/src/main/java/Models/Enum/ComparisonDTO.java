package Models.Enum;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-07-22.
 */
public enum ComparisonDTO  implements Serializable {
//    // 等于
//    Equal(10,"等于"),
//    // 不等于
//    NotEqual(11,"不等于"),
//    // 大于
//    GreaterThan(12,"大于"),
//    // 大于等于
//    GreaterEqualThan(13,"大于等于"),
//    // 小于
//    LessThan(14,"小于"),
//    // 小于等于
//    LessEqualThan(15,"小于等于"),
//    // 包含
//    In(20,"包含"),
//    // 模糊查找
//    Like(21,"模糊查找"),
//    // 等于
//    EqualProperty(30,"等于"),
//    // 不等于
//    NotEqualProperty(31,"不等于"),
//    // 大于
//    GreaterThanProperty(32,"大于"),
//    // 大于等于
//    GreaterEqualThanProperty(33,"大于等于"),
//    // 小于
//    LessThanProperty(34,"小于"),
//    // 小于等于
//    LessEqualThanProperty(35,"小于等于"),
//    // 并且
//    And(90,"并且"),
//    // 或者
//    OR(91,"或者"),
//    // 不包含
//    Not(92,"不包含"),
//    // 空内容
//    IsNull(100,"空内容"),
//    Sql(200,"SQL");
//    /**
//     * 定义枚举类型自己的属性*
//     */
//    private int code;
//    private String desc;
//    private ComparisonDTO(int code, String desc) {
//        this.code = code;
//        this.desc=desc;
//    }
//    public String toDescription() {
//        return this.desc == null ? this.name() : this.desc;
//    }
//    public int toCode() {
//        return this.code==0 ? this.ordinal() : this.code;
//    }
    // 等于
    Equal(10),
    // 不等于
    NotEqual(11),
    // 大于
    GreaterThan(12),
    // 大于等于
    GreaterEqualThan(13),
    // 小于
    LessThan(14),
    // 小于等于
    LessEqualThan(15),
    // 包含
    In(20),
    // 模糊查找
    Like(21),
    // 等于
    EqualProperty(30),
    // 不等于
    NotEqualProperty(31),
    // 大于
    GreaterThanProperty(32),
    // 大于等于
    GreaterEqualThanProperty(33),
    // 小于
    LessThanProperty(34),
    // 小于等于
    LessEqualThanProperty(35),
    // 并且
    And(90),
    // 或者
    OR(91),
    // 不包含
    Not(92),
    // 空内容
    IsNull(100),
    Sql(200);
    /**
     * 定义枚举类型自己的属性*
     */
    private int code;
    private String desc;
    private ComparisonDTO(int code) {
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
