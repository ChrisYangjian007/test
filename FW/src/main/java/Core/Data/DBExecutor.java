package Core.Data;

import Configs.DBConfigs;
import Core.ORM.Mapping;
import Core.ORM.ModelCache;
import Extend.ListExtend;
import Extend.StringExtend;
import Models.Annotations.ColumnAttribute;
import Models.DBInfo;
import Models.Enum.ErrorInfo;
import Models.Filter;
import Models.PagingOptions;
import Models.SortBy;
import com.alibaba.druid.pool.DruidDataSource;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
//import org.apache.log4j.Logger;

/**
 * 数据库操作类
 */
public class DBExecutor {
    /// log4对象
//    private static Logger logger = Logger.getLogger(DBExecutor.class);
    /**
     * 它为null表示没有事务
     * 它不为null表示有事务
     * 当开启事务时，需要给它赋值
     * 当结束事务时，需要给它赋值为null
     * 并且在开启事务时，让dao的多个方法共享这个Connection
     */
    private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
    private DruidDataSource cpds = null;
    /// 功能描述: 设置数据库连接字符串
    private String ConnString;
    /// 数据库连接对象
    private Connection _Conn = null;
    private boolean IsTran = false;
    private DBInfo dbInfo = null;
    private PreparedStatement _pStatement = null;
    private ResultSet _rSet = null;

    public DBExecutor(int p_intDBIndex) {
        try {
            dbInfo = DBConfigs.Instance().getConfigInfo().DBList.get(p_intDBIndex);
            ConnString = DBFactory.CreateConnString(dbInfo);
            cpds = dbInfo.getPoolData();
            if (cpds == null) {
                cpds = new DruidDataSource();
                cpds.setDriverClassName(dbInfo.getDataVer());
                cpds.setUrl(ConnString);
                cpds.setUsername(dbInfo.getUserID());//用户名
                cpds.setPassword(dbInfo.getPassWord());//密码
                cpds.setInitialSize(Integer.parseInt(dbInfo.getPoolMinSize()));
                cpds.setMaxActive(Integer.parseInt(dbInfo.getPoolMaxSize()));
                cpds.setMinIdle(1);
                cpds.setMaxWait(60000);//等待时间
//                cpds.setPoolPreparedStatements(true);// 打开PSCache，并且指定每个连接上PSCache的大小
//                cpds.setMaxPoolPreparedStatementPerConnectionSize(20);
//                cpds.setTestOnBorrow(false);
//                cpds.setTestWhileIdle(true);
//                cpds.setRemoveAbandonedTimeout(180);
//                cpds.setRemoveAbandoned(true);
//                cpds.setMaxPoolPreparedStatementPerConnectionSize(20);

//                cpds=new ComboPooledDataSource();
//                cpds.setDriverClass(dbInfo.getDataVer());
//                cpds.setJdbcUrl(ConnString);
//                cpds.setUser(dbInfo.getUserID());
//                cpds.setPassword(dbInfo.getPassWord());
//                cpds.setCheckoutTimeout(10000);
//                cpds.setInitialPoolSize(10);
//                cpds.setMaxPoolSize(100);
//                cpds.setMinPoolSize(10);
//                cpds.setMaxIdleTime(30);//connection能存活的最大时间
//                cpds.setMaxStatements(200);
//                //当数据库重启后或者由于某种原因进程被杀掉后，C3P0不会自动重新初始化数据库连接池，当新的请求需要访问数据库的时候，此时会报错误(因为连接失效)，同时刷新数据库连接池，丢弃掉已经失效的连接，当第二个请求到来时恢复正常。
//                cpds.setIdleConnectionTestPeriod(30);
                dbInfo.setPoolData(cpds);
            }
        } catch (Exception ex) {
//            logger.error("Framework--重构时出错--" + ex.getStackTrace() + "--DB Error--" + ex.getMessage());
        }
    }

    /// 作者:
    /// 功能描述: 关闭数据库连接
    public void DBClose() {
        DBClose("");
    }

    /// 作者:
    /// 功能描述: 关闭数据库连接
    public void DBClose(String p_strFun) {
        try {
            if (_rSet != null) {
                _rSet.close();
            }
            if (_pStatement != null) {
                _pStatement.close();
            }
            if (_Conn != null && !_Conn.isClosed()) {
                _Conn.close();
            }
            IsTran = false;
        } catch (Exception ex) {
//            logger.error("Framework--DBClose()关闭连接时出错--" + p_strFun + ex.getStackTrace() + "--DB Error--" + ex.getMessage());
        }
    }

    /// 功能描述: 判断数据库是否已经打开
    /// <returns>true=已打开，false=未打开</returns>
    public boolean IsOpen() {
        try {
            if (_Conn == null) {
                return false;
            }
            if (!this._Conn.isClosed()) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
//            logger.error("Framework-判断是否打开数据库时出错--" + ex.getStackTrace() + "--DB Error--" + ex.getMessage());
            return false;
        }
    }

    /// 作者:
    /// 功能描述: 打开数据连接
    public Connection DBOpen() {
        try {
            if (StringExtend.Empty(ConnString)) {
//                logger.error("Framework--DBOpen()连接时出错--没有连接字符串！");
                return null;
            } else {
                if (!IsOpen()) {
                /*
                 * 如果有事务，返回当前事务的con
                 * 如果没有事务，通过连接池返回新的con
                 */
                    Connection con = tl.get();//获取当前线程的事务连接
                    if (con != null) {
                        return con;
                    }
                    return cpds.getConnection();
//                    Class.forName(dbInfo.getDataVer());
//                    return DriverManager.getConnection(ConnString, dbInfo.getUserID(), dbInfo.getPassWord());
                } else {
                    return _Conn;
                }
            }
        } catch (Exception ex) {
//            logger.error("Framework--DBOpen()异常退出--连接时出错--DB Error--" + ex.getMessage());
            try {
                if (_Conn != null) {
                    _Conn.close();
                }
            } catch (SQLException ex1) {
//                logger.error("Framework--DBOpen()连接时出错--关闭报错！" + ex.getMessage());
            }
            return null;
        }
    }

    /// 功能描述: 创建ExecuteScalar对象(出错null)
    /// <param name="p_strSQL">SQL语句</param>
    public int ExecuteNonQuery(String p_strSQL) {
        return ExecuteNonQuery(p_strSQL, new Object[]{}, false);
    }

    /// 功能描述: 创建ExecuteReader对象(出错null)
    /// <param name="p_strSQL">SQL</param>
    /// <param name="p_objDataParms">绑定参数</param>
    public int ExecuteNonQuery(String p_strSQL, Object[] param, boolean isID) {
        int result = 0;
        try {
            _Conn = DBOpen();
            _pStatement = _Conn.prepareStatement(p_strSQL);
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    if (param[i].getClass().getSimpleName().toLowerCase().equals("date")) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        _pStatement.setDate(i + 1, new java.sql.Date(format.parse(format.format(param[i])).getTime()));
                    } else {
                        _pStatement.setObject(i + 1, param[i]);
                    }
                }
            }
            result = _pStatement.executeUpdate();
            if (isID && result > 0) {
                ResultSet rs = _pStatement.getGeneratedKeys();
                if (rs.next()) {
                    result = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /// 功能描述: 创建ExecuteScalar对象(出错null)
    /// <param name="p_strSQL">SQL语句</param>
    public ResultSet ExecutQuery(String p_strSQL) {
        return ExecutQuery(p_strSQL, false, new Object[]{});
    }

    /**
     * 查询
     */
    public ResultSet ExecutQuery(String sql, boolean p_getTotalCount, Object[] param) {
        try {
            if (_rSet != null && !_rSet.isClosed()) {
                _rSet.close();
            }
            _Conn = DBOpen();
            _pStatement = _Conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            if (param != null) {
                int i = 0;
                for (; i < param.length; i++) {
                    if (param[i].getClass().getSimpleName().toLowerCase().equals("date")) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        _pStatement.setDate(i + 1, new java.sql.Date(format.parse(format.format(param[i])).getTime()));
                    } else {
                        _pStatement.setObject(i + 1, param[i]);
                    }
                }
            }
            _rSet = _pStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return _rSet;
    }

    /// 功能描述: 获取单个值(出错null)
    /// <param name="p_strSQL">SQL语句</param>
    public String GetValue(String p_strSQL) {
        return GetValues(p_strSQL)[0];
    }

    /// 功能描述: 获取单个值(出错null)
    /// <param name="strSQL">SQL语句</param>
    /// <param name="objDataParms">绑定参数</param>
    public String GetValue(String strSQL, List<Filter> p_Filters) {
        return GetValues(strSQL, p_Filters)[0];
    }

    /// 功能描述: 获取单个值(出错null)
    /// <param name="strSQL">SQL语句</param>
    /// <param name="objDataParms">绑定参数</param>
    public String GetValue(String strSQL, Object[] objDataParms) {
        return GetValues(strSQL, objDataParms)[0];
    }

    /// 功能描述: 以字符串数组形式返回选择的内容只用于针对一行数据(无为null)
    /// <param name="p_strSQL">SQL语句</param>
    public String[] GetValues(String p_strSQL) {
        return GetValues(p_strSQL, new Object[]{});
    }

    /// 功能描述: 以字符串数组形式返回选择的内容只用于针对一行数据(无为null)
    /// <param name="strSQL">SQL语句</param>
    /// <param name="p_Filters">绑定参数</param>
    public String[] GetValues(String p_strSQL, List<Filter> p_Filters) {
        //DbProvider m_DbProvider = CreateDbProvider();
        List<Object> lists = new ArrayList<Object>();
        //传参
        for (Filter Para : p_Filters) {
            Para.ToSqlString(null);
            lists.addAll(Para.getParaList());
            Para.getParaList().clear();
        }
        return GetValues(p_strSQL, lists.toArray());
    }

    /// 功能描述: 以字符串数组形式返回选择的内容只用于针对一行数据(无为null)
    /// <param name="strSQL">SQL语句</param>
    /// <param name="objDataParms">绑定参数</param>
    public String[] GetValues(String p_strSQL, Object[] p_objDataParms) {
        List<String> m_Return = new ArrayList<String>();
        try {
            ResultSet m_objReturn = ExecutQuery(p_strSQL, false, p_objDataParms);
            //记录SQL语句
            while ((m_objReturn != null) && m_objReturn.next()) {
                if (m_objReturn.getString(1) != null) {
                    m_Return.add(m_objReturn.getString(1));
                }
            }
        } catch (Exception ex) {
//            logger.error("Framework--GetValues异常退出--" + ex.getStackTrace() + "--DB Error--" + ex.getMessage() + "SQL     : " + p_strSQL);
            return null;
        }
        return m_Return.toArray(new String[0]);
    }

    /// 返回查询条数
    public <T> int FindCount(Class<T> t) {
        int m_intReturn = 0;
        try {
            Type m_Type = t.newInstance().getClass();
            Mapping Map = ModelCache.GetInfo(m_Type);//缓存对象
            String tableName = Map.getClassInfo().Name();//获取表名
            ResultSet m_objReturn = ExecutQuery("SELECT Count(1) as Count FROM " + tableName + " WHERE 1=1 ");
            if (m_objReturn != null && m_objReturn.next()) {
                m_intReturn = m_objReturn.getInt("Count");
//                if (!m_objReturn.isClosed()) m_objReturn.close();
            }
//            if (!_pStatement.isClosed()) _pStatement.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return m_intReturn;
    }
    /// <summary>
    /// 查询对象、返回实体
    /// </summary>
    /// <param name="propertyValue">主键值</param>
    public <T> int FindCount(Class<T> t,List<Filter> p_Filters)
    {
        int m_intReturn = 0;
        Map<String, String> DictionaryColumn = new HashMap<String, String>();
        try {
            StringBuilder sbWhere = new StringBuilder();//存条件
            Type m_Type = t.newInstance().getClass();
            Mapping Map = ModelCache.GetInfo(m_Type);//缓存对象
            String tableName = Map.getClassInfo().Name();//获取表名
            List<Object> lists = new ArrayList<Object>();
            int i = 0;
            String p_strSQL = "SELECT Count(1) as Count FROM " + tableName + " WHERE 1=1 %s ";
            StringBuilder sbBody = new StringBuilder();
            //选择对象
            Iterator iter = Map.ModelList.entrySet().iterator();
            while (iter.hasNext()) {
                java.util.Map.Entry entry = (java.util.Map.Entry) iter.next();
                ColumnAttribute val = (ColumnAttribute) entry.getValue();
                DictionaryColumn.put(val.Name(), val.Name());
            }
            //传参
            for (Filter Para : p_Filters) {
                sbWhere.append(" AND ").append(Para.ToSqlString(i, DictionaryColumn));
                lists.addAll(Para.getParaList());
                Para.getParaList().clear();
                i++;
            }
            p_strSQL = String.format(p_strSQL,sbWhere.toString());
            ResultSet m_objReturn = ExecutQuery(p_strSQL);
            if (m_objReturn != null && m_objReturn.next()) {
                m_intReturn = m_objReturn.getInt("Count");
            }
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return m_intReturn;
    }

    public <T> T GetClassByPara(Class<T> t, String m_strSQL) {
        T m_lstReturn = null;
        try {
            ResultSet m_objReturn = ExecutQuery(m_strSQL, false, null);
            if (m_objReturn != null) {
                m_lstReturn = ListExtend.ToInfo(t, m_objReturn);
                if (!m_objReturn.isClosed()) {
                    m_objReturn.close();
                }
            }
            if (!_pStatement.isClosed()) {
                _pStatement.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return m_lstReturn;
    }

    /// <typeparam name="T"></typeparam>
    /// <param name="p_Filters"></param>
    public <T> T GetClassByPara(Class<T> t, List<Filter> p_Filters) {
        return GetClassByPara(t, PagingOptions.Default(), p_Filters);
    }

    /// <typeparam name="T"></typeparam>
    /// <param name="p_Filters"></param>
    public <T> T GetClassByPara(Class<T> t, PagingOptions p_Options, List<Filter> p_Filters) {
        List<T> m_List = GetClassListByPara(t, p_Options, p_Filters);
        if (m_List != null && m_List.size() > 0) {
            return m_List.get(0);
        } else {
            return null;
        }
    }

    public <T> List<T> GetClassListByPara(Class<T> t, String p_strSQL, PagingOptions p_Options, List<Filter> p_Filters) {
        //DbProvider m_DbProvider = CreateDbProvider();
        List<Object> lists = new ArrayList<Object>();
        List<T> m_lstReturn = null;
        //传参
        try {
            for (Filter Para : p_Filters) {
                Para.ToSqlString(null);
                lists.addAll(Para.getParaList());
                Para.getParaList().clear();
            }
            if (p_Options == null) {
                p_Options = PagingOptions.Default();
            }
            ResultSet m_objReturn = ExecutQuery(p_strSQL, p_Options.FetchTotalRecordCount, lists.toArray());
            if (m_objReturn != null) {
                m_lstReturn = ListExtend.ToList(t, m_objReturn, p_Options.StartNumber, p_Options.PageSize, p_Options.FetchTotalRecordCount);
                if (p_Options.FetchTotalRecordCount) {
                    m_objReturn.last();
                    p_Options.TotalCount = m_objReturn.getRow();
                }
//                if (!m_objReturn.isClosed()) m_objReturn.close();
            }
//            if (!_pStatement.isClosed()) _pStatement.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return m_lstReturn;
    }

    public <T> List<T> GetClassListByPara(Class<T> t, PagingOptions p_Options, List<Filter> p_Filters) {
        List<T> m_lstReturn = new ArrayList<T>();
        Map<String, String> DictionaryColumn = new HashMap<String, String>();
        try {
            Type m_Type = t.newInstance().getClass();
            Mapping Map = ModelCache.GetInfo(m_Type);//缓存对象
            //DbProvider m_DbProvider = CreateDbProvider();
            StringBuilder sbWhere = new StringBuilder();//存条件
            StringBuilder sbOrderBy = new StringBuilder();//存排序
            String p_strSQL = "SELECT %s FROM %s WHERE 1=1 %s %s";
            StringBuilder sbBody = new StringBuilder();
            List<Object> lists = new ArrayList<Object>();
            //去重
            if (p_Options.SortBy.size() > 0) {
                HashSet m_hs = new HashSet(p_Options.SortBy);
                p_Options.SortBy.clear();
                p_Options.SortBy.addAll(m_hs);
                for (SortBy m_tmp : p_Options.SortBy) {
                    sbOrderBy.append(m_tmp.ToSqlString()).append(",");
                }
            }
            int i = 0;
            String m_strSelect = "";
            //选择对象
            Iterator iter = Map.ModelList.entrySet().iterator();
            while (iter.hasNext()) {
                java.util.Map.Entry entry = (java.util.Map.Entry) iter.next();
                ColumnAttribute val = (ColumnAttribute) entry.getValue();
                DictionaryColumn.put(val.Name(), val.Name());
                if (val.Ignore() == false && StringExtend.Empty(p_Options.SelectColumn)) {
                    m_strSelect += "," + val.Name();
                }
            }
            //传参
            for (Filter Para : p_Filters) {
                sbWhere.append(" AND ").append(Para.ToSqlString(i, DictionaryColumn));
                lists.addAll(Para.getParaList());
                Para.getParaList().clear();
                i++;
            }
            sbBody.append(StringExtend.Empty(p_Options.SelectColumn) ? m_strSelect : p_Options.SelectColumn);
            p_strSQL = String.format(p_strSQL, StringExtend.Trim(sbBody.toString(), ","), Map.getClassInfo().Name(), sbWhere.toString(),
                    (StringExtend.Empty(StringExtend.Trim(sbOrderBy.toString(), ",")) ? "" : " order by " + StringExtend.Trim(sbOrderBy.toString(), ",")));
            ResultSet m_objRS = ExecutQuery(p_strSQL, p_Options.FetchTotalRecordCount, lists.toArray());
            if (m_objRS != null) {
                m_lstReturn = ListExtend.ToList(t, m_objRS, p_Options.StartNumber, p_Options.PageSize, p_Options.FetchTotalRecordCount);
                if (p_Options.FetchTotalRecordCount) {
                    m_objRS.last();
                    p_Options.TotalCount = m_objRS.getRow();
                }
//                if (!m_objRS.isClosed()) m_objRS.close();
            }
//            if (!_pStatement.isClosed()) _pStatement.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return m_lstReturn;
    }

    /// 插入数据
    /// <param name="entity">实体类对象</param>
    public <T> int Insert(T entity, boolean isID) throws InstantiationException, IllegalAccessException {
        DBBuilder m_DbBuilder = DBFactory.CreateDBBuilder(entity, "");
        String m_strInsertSQL = (isID) ? m_DbBuilder.Insert(entity) + m_DbBuilder.LastIdentity() : m_DbBuilder.Insert(entity);
        int m_intReturn = ExecuteNonQuery(m_strInsertSQL, m_DbBuilder.ParamsList.toArray(), isID);
        return m_intReturn;
    }

    /// <summary>
    /// 批量插入数据
    /// </summary>
    /// <param name="entity">实体类对象</param>
    public <T> int Insert(List<T> entity) {
        int m_intReturn = 0;
        BeginTransaction();
        try {
            for (T item : entity) {
                m_intReturn = Insert(item, false);
            }
            CommitTransection();
        } catch (Exception ex) {
            RollbackTransection();
            m_intReturn = -1;
        }
        return m_intReturn;
    }

    /// 修改数据
    /// <param name="entity">实体对象</param>
    public <T> int Update(T entity) throws InstantiationException, IllegalAccessException {
        int m_intReturn = 0;
        DBBuilder<T> m_DbBuilder = DBFactory.CreateDBBuilder(entity, "");
        String m_strUpdateSQL = m_DbBuilder.Update(entity);
        m_intReturn = ExecuteNonQuery(m_strUpdateSQL, m_DbBuilder.ParamsList.toArray(), false);
        return m_intReturn;
    }

    /// 批量修改数据
    /// <param name="entity">实体对象</param>
    public <T> int Update(List<T> entity, boolean errorReturn) {
        int m_intReturn = 0;
        BeginTransaction();
        try {
            for (T item : entity) {
                m_intReturn = Update(item);
                if (errorReturn && m_intReturn < 0) {
                    break;
                }
            }
            if (errorReturn && m_intReturn < 0) {
                RollbackTransection();
            } else {
                CommitTransection();
            }
        } catch (Exception ex) {
            RollbackTransection();
            m_intReturn = -10000;
        }
        return m_intReturn;
    }

    /// 修改数据
    /// <param name="entity">实体对象</param>
    public int Update(String p_strSQL, List<Filter> p_Filters) {
        List<Object> lists = new ArrayList<Object>();

        for (Filter Para : p_Filters) {
            Para.ToSqlString(null);
            lists.addAll(Para.getParaList());
            Para.getParaList().clear();
        }

        return ExecuteNonQuery(p_strSQL, lists.toArray(), false);
    }

    /// 删除数据
    /// <param name="entity">实体类</param>
    public <T> int Delete(T entity) throws InstantiationException, IllegalAccessException {
        int m_intReturn = 0;
        DBBuilder<T> m_DbBuilder = DBFactory.CreateDBBuilder(entity, "");
        String m_strUpdateSQL = m_DbBuilder.Delete(entity);
        m_intReturn = ExecuteNonQuery(m_strUpdateSQL, m_DbBuilder.ParamsList.toArray(), false);
        return m_intReturn;
    }

    /// 删除数据
    /// <param name="propertyValue">主键值</param>
    public <T> int Delete(Class<T> t, Object propertyValue) throws IllegalAccessException, InstantiationException {
        int m_intReturn = 0;
        Type m_Type = t.newInstance().getClass();
        Mapping Map = ModelCache.GetInfo(m_Type);//缓存对象
        String tableName = Map.getClassInfo().Name();//获取表名
        String pkName = Map.getIndexName();//获取主键
        m_intReturn = ExecuteNonQuery("DELETE FROM " + tableName + " WHERE " + pkName + " IN (?)", new Object[]{propertyValue}, false);
        return m_intReturn;
    }

    /// 删除数据
    /// <param name="propertyValue">主键值</param>
    public <T> int Delete(Class<T> t, List<Filter> p_Filters) throws IllegalAccessException, InstantiationException {
        int m_intReturn = 0;
        List<Object> lists = new ArrayList<Object>();
        Type m_Type = t.newInstance().getClass();
        Mapping Map = ModelCache.GetInfo(m_Type);//缓存对象
        String tableName = Map.getClassInfo().Name();//获取表名

        StringBuilder sbWhere = new StringBuilder();//存条件
        String p_strSQL = "DELETE FROM " + tableName + " WHERE 1=1 ";
        Map<String, String> DictionaryColumn = new HashMap<String, String>();

        int i = 0;
        //传参
        for (Filter Para : p_Filters) {
            sbWhere.append(" AND ").append(Para.ToSqlString(i, DictionaryColumn));
            lists.addAll(Para.getParaList());
            Para.getParaList().clear();
            i++;
        }

        p_strSQL = p_strSQL + sbWhere.toString();
        m_intReturn = ExecuteNonQuery(p_strSQL, lists.toArray(), false);
        return m_intReturn;
    }

    /// 批量删除数据
    /// <param name="propertyValue">主键值：数组1,2,3,4,5,6.....</param>
    public <T> int Delete(Class<T> t, String[] propertyValue) throws IllegalAccessException, InstantiationException {
        int m_intReturn = 0;
        Type m_Type = t.newInstance().getClass();
        Mapping Map = ModelCache.GetInfo(m_Type);//缓存对象
        String tableName = Map.getClassInfo().Name();//获取表名
        String pkName = Map.getIndexName();//获取主键
        List<Object> m_lstPara = new ArrayList<Object>();
        String m_strColumn = "";
        try {
            for (int i = 0; i <= (propertyValue.length - 1); i++) {
                m_strColumn += "?,";
                m_lstPara.add(propertyValue[i]);
            }
            m_intReturn = ExecuteNonQuery(String.format("DELETE FROM " + tableName + " WHERE " + pkName + " IN (%s)", StringExtend.Trim(m_strColumn, ",")), m_lstPara.toArray(), false);
            return m_intReturn;
        } catch (Exception ex) {
            ex.printStackTrace();
            return -10000;
        }
    }

    /// 批量删除数据
    /// <param name="propertyName">实体属性名称</param>
    /// <param name="propertyValue">字段值：数组1,2,3,4,5,6.....</param>
    public <T> int Delete(Class<T> t, String propertyName, Object[] propertyValue) throws IllegalAccessException, InstantiationException {
        int m_intReturn = 0;
        Type m_Type = t.newInstance().getClass();
        Mapping Map = ModelCache.GetInfo(m_Type);//缓存对象
        String tableName = Map.getClassInfo().Name();//获取表名
        String pkName = Map.getIndexName();//获取主键
        if (StringExtend.Empty(propertyName)) {
            propertyName = pkName;
        }
        List<Object> m_lstPara = new ArrayList<>();
        String m_strColumn = "";
        try {
            for (int i = 0; i <= (propertyValue.length - 1); i++) {
                m_strColumn += "?,";
                m_lstPara.add(propertyValue[i]);
            }
            m_intReturn = ExecuteNonQuery(String.format("DELETE FROM " + tableName + " WHERE " + propertyName + " IN ({0})", StringExtend.Trim(m_strColumn, ",")), m_lstPara.toArray(), false);
            return m_intReturn;
        } catch (Exception ex) {
            ex.getStackTrace();
            return -10000;
        }
    }

    /// <summary>
    /// 查询对象、返回实体
    /// </summary>
    /// <param name="propertyValue">主键值</param>
    public <T> Integer FindMax(Class<T> t ,String propertyName) {
        try {
            Type m_Type = t.newInstance().getClass();
            Mapping Map = ModelCache.GetInfo(m_Type);//缓存对象
            ResultSet objRS = ExecutQuery("SELECT MAX(" + propertyName + ") as Num FROM " + Map.getClassInfo().Name() + "  WHERE 1=1 ");
            if (objRS != null && objRS.next()) {
                return objRS.getInt("Num");
            } else {
                return 0;
            }
        } catch (Exception ex) {
            return ErrorInfo.SystemErrorFailure.getCode();
        }
    }

    /// 查询对象、返回实体
    /// <param name="propertyValue">主键值</param>
    public int Delete(String p_strSQL, List<Filter> p_Filters) {
        List<Object> lists = new ArrayList<Object>();
        //传参
        for (Filter Para : p_Filters) {
            Para.ToSqlString();
            lists.addAll(Para.getParaList());
            Para.getParaList().clear();
        }
        int m_objReturn = ExecuteNonQuery(p_strSQL, lists.toArray(), false);
        return m_objReturn;
    }

    /// 功能描述: 事务开始
    public void BeginTransaction() {
        //打开连接
        _Conn = DBOpen();
        //开始事务
        if (IsTran == false) {
            IsTran = true;
            try {
                _Conn.setAutoCommit(false);
            } catch (SQLException e) {
//                logger.error("Framework--BeginTransaction()时出错--" + e.getMessage());
            }
        }
    }

    /// 作者:
    /// 功能描述: 提交事务
    public void CommitTransection() {
        if (IsTran == true) {
            try {
                _Conn.commit();
            } catch (SQLException e) {
//                logger.error("Framework--CommitTransection()时出错--" + e.getMessage());
            }
            IsTran = false;
        }
        DBClose("CommitTransection");
    }

    /// 作者:
    /// 功能描述: 回滚事务
    public void RollbackTransection() {
        if (IsTran == true) {
            try {
                _Conn.rollback();
            } catch (SQLException e) {
//                logger.error("Framework--RollbackTransection()时出错--" + e.getMessage());
            }
            IsTran = false;
        }
        DBClose("RollbackTransection");
    }
}
