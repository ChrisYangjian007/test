package Models.Enum;

/**
 * Created by Administrator on 2017-08-14.
 */
public enum WatermarkType {
    // 无
    None(0),
    // 文本
    Text(1),
    // 图片
    Image(2);
    /**
     * 定义枚举类型自己的属性*
     */
    private int code;

    private WatermarkType(int code) {
        this.code = code;
    }
}
