package Models.Enum;
import java.io.Serializable;

/**
 * 数据库类型
 */
public enum OperateResult implements Serializable{
    // 异常
    error(0),
    // 成功
    success(1),
    // 失败
    fail(2),
    // 未请求到数据
    empty(3);
    /**
     * 定义枚举类型自己的属性*
     */
    private int code;

    private OperateResult(int code) {
        this.code = code;
    }
    public int toCode() {
        return this.code==0 ? this.ordinal() : this.code;
    }
}