package Core.Data;

/**
 * Created by Administrator on 2017-08-10.
 */
public class SqlServerProvider extends DBProvider {
    @Override
    public String ParamsPrefix() {
        return "?";
    }
    /// 创建字段保护符
    /// <param name="fieldName">字符名称</param>
    @Override
    public String CreateTableAegis(String fieldName) {
        return String.format("[%s]", fieldName);
    }
}
