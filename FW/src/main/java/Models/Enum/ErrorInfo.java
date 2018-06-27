package Models.Enum;

/**
 * Created by Administrator on 2017-08-14.
 */
public enum ErrorInfo {
    // 遇到系统报错
    SystemErrorFailure(-10000, "遇到系统报错"),
    // 没有找到符合要求的对象
    ObjectIsNull(-10001, "没有找到符合要求的对象"),
    //创建时出错
    InsertObjectFailure(-10003,"创建时出错"),
    ///当前所选有子节点数据，不能删除。
    ExistingChildNodes (-18998,"当前所选有子节点数据，不能删除。"),
    //删除出错
    DelObjectFailure(-10004,"删除出错"),
    // 键值不能为空
    KeyValueIsNull(-10002, "键值不能为空");
    /**
     * 定义枚举类型自己的属性*
     */
    private int code;
    private String label;

    private ErrorInfo(int code) {
        this.code = code;
    }

    private ErrorInfo(int code, String desc) {
        this.code = code;
        this.label = desc;
    }

    //根据ID返回字符串
    public static String getValueByID(int code) {
        for (ErrorInfo enum_id_type : ErrorInfo.values()) {
            if (enum_id_type.code == code) {
                return enum_id_type.label;
            }
        }
        return "";
    }

    public int getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }
}
