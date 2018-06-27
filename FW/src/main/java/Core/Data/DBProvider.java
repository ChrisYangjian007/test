package Core.Data;

/**
 * Created by Administrator on 2017-08-10.
 */
public abstract class DBProvider {
    public String ParamsPrefix() {
        return "?";
    }
    /// 创建字段保护符
    /// <param name="fieldName">字符名称</param>
    public String CreateTableAegis(String fieldName) {
        return String.format("[%s]", fieldName);
    }
}
