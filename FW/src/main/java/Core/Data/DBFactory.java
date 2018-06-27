package Core.Data;

import Core.ORM.Mapping;
import Core.ORM.ModelCache;
import Extend.StringExtend;
import Models.DBInfo;
import Models.Enum.DataBaseType;

import java.lang.reflect.Type;

public class DBFactory {
    public static String CreateConnString(DBInfo Info)
    {
        return CreateConnString(DataBaseType.valueOf(Info.getDataType()),Info.getUserID(),Info.getPassWord(),Info.getServer(),Info.getCatalog(),Info.getDataVer(),
                Integer.valueOf(Info.getConnectTimeout()).intValue(), Integer.valueOf(Info.getPoolMinSize()).intValue(), Integer.valueOf(Info.getPoolMaxSize()).intValue(),Info.getPort());
    }
    /// <summary>
    /// 创建数据库Sql生成
    /// </summary>
    /// <typeparam name="TInfo">实体类</typeparam>
    /// <param name="dbType">数据库类型</param>
    /// <param name="tableName">表名称</param>
    public static <T> DBBuilder<T> CreateDBBuilder(T t, String tableName) throws IllegalAccessException, InstantiationException {
        Type m_Type = t.getClass();
        Mapping Map = ModelCache.GetInfo(m_Type);//缓存对象
        if (StringExtend.Empty(tableName)) {
            tableName = Map.getClassInfo().Name();
        }
        DBBuilder<T> m_DBBuilder;
        switch (Map.getClassInfo().DataType()) {
//            case OleDb: return new OleDbBuilder<T>(tableName);
//            case MySql: return new MySqlBuilder<TInfo>(tableName);
//            case SQLite: return new SQLiteBuilder<TInfo>(tableName);
//            case Oracle: return new OracleBuilder<TInfo>(tableName);
            default: {
                m_DBBuilder = new SqlServerBuilder<T>(tableName);
                m_DBBuilder.setDbProvider(new SqlServerProvider());
                return m_DBBuilder;
            }
        }
    }
     /// <summary>
     /// 创建数据库连接字符串
     /// </summary>
     /// <param name="dataType">数据库类型</param>
     /// <param name="userID">账号</param>
     /// <param name="passWord">密码</param>
     /// <param name="server">服务器地址</param>
     /// <param name="catalog">表名</param>
     /// <param name="dataVer">数据库版本</param>
     /// <param name="connectTimeout">链接超时时间</param>
     /// <param name="poolMinSize">连接池最小数量</param>
     /// <param name="poolMaxSize">连接池最大数量</param>
     /// <param name="port">端口</param>
    public static String CreateConnString(DataBaseType dataType, String userID, String passWord, String server,String catalog, String dataVer, int connectTimeout, int poolMinSize , int poolMaxSize, String port) {
         String connString = "";
         switch (dataType) {
             case MySql: {
                 connString += String.format("jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=utf-8&useSSL=false", server, port,catalog);
                 return connString;
             }
             case SqlServer: {
                 //connString += String.format("jdbc:sqlserver://%s:%s; DatabaseName=%s", server, port,catalog);
                 connString += String.format("jdbc:jtds:sqlserver://%s:%s/%s", server, port,catalog);
//                 connString += String.format("jdbc:jtds:sqlserver://%s:%s; DatabaseName=%s", server, port,catalog);
//                 if (poolMinSize > 0) connString += string.Format("Min Pool Size={0};", poolMinSize);
//                 if (poolMaxSize > 0) connString += string.Format("Max Pool Size={0};", poolMaxSize);
//                 if (connectTimeout > 0) connString += string.Format("Connect Timeout={0};", connectTimeout);
                 return connString;
             }
//             case DataBaseType.OleDb:
//             {
//                 var connString = string.Empty;
//                 switch (dataVer)
//                 {
//                     case "3.0":
//                     {
//                         connString = string.Format("Provider=Microsoft.Jet.OLEDB.4.0;Extended Properties=Excel 3.0;"); break;
//                     }
//                     case "4.0":
//                     {
//                         connString = string.Format("Provider=Microsoft.Jet.OLEDB.4.0;Extended Properties=Excel 4.0;"); break;
//                     }
//                     case "5.0":
//                     {
//                         connString = string.Format("Provider=Microsoft.Jet.OLEDB.4.0;Extended Properties=Excel 5.0;"); break;
//                     }
//                     case "95":
//                     {
//                         connString = string.Format("Provider=Microsoft.Jet.OLEDB.4.0;Extended Properties=Excel 5.0;"); break;
//                     }
//                     case "97":
//                     {
//                         connString = string.Format("Provider=Microsoft.Jet.OLEDB.3.51;"); break;
//                     }
//                     case "2003":
//                     {
//                         connString = string.Format("Provider=Microsoft.Jet.OLEDB.4.0;Extended Properties=Excel 8.0;"); break;
//                     }
//                     default:
//                     {
//                         connString = string.Format("Provider=Microsoft.ACE.OLEDB.12.0;Extended Properties=Excel 12.0;"); break;
//                     }
//                 }
//                 connString += string.Format("Data Source={0};", GetFilePath(server));
//                 if (!userID.IsNullOrEmpty()) { connString += string.Format("User ID={0};", userID); }
//                 if (!passWord.IsNullOrEmpty()) { connString += string.Format("Password={0};", passWord); }
//
//                 return connString;
//             }
//             case DataBaseType.Xml:
//             {
//                 return server.IsNullOrEmpty() ? string.Empty : GetFilePath(server);
//             }
//             case DataBaseType.SQLite:
//             {
//                 var plus = new StringBuilder();
//                 plus.AppendFormat("Data Source={0};Min Pool Size={1};Max Pool Size={2};", GetFilePath(server),poolMinSize, poolMaxSize);
//                 if (!passWord.IsNullOrEmpty())
//                 {
//                     plus.AppendFormat("Password={0};", passWord);
//                 }
//                 if (!dataVer.IsNullOrEmpty())
//                 {
//                     plus.AppendFormat("Version={0};", dataVer);
//                 }
//                 return plus.ToString();
//             }
//             case DataBaseType.Oracle:
//             {
//                 if (port.IsNullOrEmpty())
//                 {
//                     port = "1521";
//                 }
//                 return
//                         string.Format("Data Source=(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST={0})(PORT={3})))(CONNECT_DATA=(SERVER=DEDICATED)(SID={4})));User Id={1};Password={2};",server, userID, passWord, port, catalog);
//             }
             default:
                 return "";
         }
     }
}
