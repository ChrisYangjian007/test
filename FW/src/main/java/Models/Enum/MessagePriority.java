package Models.Enum;

/**
 * Created by Administrator on 2017-08-13.
 */
public enum MessagePriority {
    // 时间类型
    Low(0),
    // 字符串类型
    Normal(1),
    // 整数类型
    High(2);
    /**
     * 定义枚举类型自己的属性*
     */
    private int code;

    private MessagePriority(int code) {
        this.code = code;
    }
}
