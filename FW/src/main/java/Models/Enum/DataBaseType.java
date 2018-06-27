package Models.Enum;
import java.io.Serializable;

/**
 * 数据库类型
 */
public enum DataBaseType implements Serializable{
    // Oracle
    Oracle(0),
    // SqlServer
    SqlServer(1),
    // OleDb
    OleDb(2),
    // MySql
    MySql(3),
    // ODBC
    ODBC(4),
    // SQLite
    SQLite(5),
    /// Xml
    Xml(6),
    //DB2
    DB2(7);
    /**
     * 定义枚举类型自己的属性*
     */
    private int m_intType;
    private String label;
    private DataBaseType(int Type) {
        this.m_intType = Type;
    }
    //根据ID返回字符串
    public static String getValueByIdType(int code){
        for(DataBaseType enum_id_type :DataBaseType.values()){
            if(enum_id_type.m_intType==code){
                return enum_id_type.label;
            }
        }
        return "";
    }
}