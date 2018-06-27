package Core.Data;

/**
* Created by Administrator on 2017-08-10.
*/
public class SqlServerBuilder<T> extends DBBuilder<T> {
    public SqlServerBuilder(String tableName) throws IllegalAccessException, InstantiationException {
        super(tableName);
    }
}
