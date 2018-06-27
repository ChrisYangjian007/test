package Models.Enum;

/**
 * Created by Administrator on 2017-08-21.
 */
public enum CurrentDatabase {
    // 主数据库
    MainDB(0),
    // 追溯数据库
    ZSDB(1),
    // 标签数据库
    TagDB(2),
    // 物联网数据库
    WLDB(3),
    // 监管数据库
    JGDB(4);
    /**
     * 定义枚举类型自己的属性*
     */
    private int code;
    private String desc;
    private CurrentDatabase(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }
}
