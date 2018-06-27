package Models;

import com.alibaba.druid.pool.DruidDataSource;

/**
* 数据库对象类
*/
public class DBInfo {
    public static final String DBINFO = "DBInfo";

    private DruidDataSource PoolData = null;
    public DruidDataSource getPoolData() {
        return PoolData;
    }
    public void setPoolData(DruidDataSource poolData) {
        PoolData = poolData;
    }

    private String server;
    public String getServer() {
        return server;
    }
    public void setServer(String p_server) {
        this.server = p_server;
    }

    private String userid;
    public String getUserID() {
        return userid;
    }
    public void setUserID(String p_userid) {
        this.userid = p_userid;
    }

    private String password;
    public String getPassWord() {
        return password;
    }
    public void setPassWord(String p_password) {
        this.password = p_password;
    }

    private String port;
    public String getPort() {
        return port;
    }
    public void setPort(String p_port) {
        this.port = p_port;
    }

    private String sid;
    public String getSID() {
        return sid;
    }
    public void setSID(String p_sid) {
        this.sid = p_sid;
    }

    private String datatype;
    public String getDataType() {
        return datatype;
    }
    public void setDataType(String p_datatype) {
        this.datatype = p_datatype;
    }

    private String dataver;
    public String getDataVer() {
        return dataver;
    }
    public void setDataVer(String p_dataver) {
        this.dataver = p_dataver;
    }

    private String catalog;
    public String getCatalog() {
        return catalog;
    }
    public void setCatalog(String p_catalog) {
        this.catalog = p_catalog;
    }

    private String tableprefix;
    public String getTablePrefix() {
        return tableprefix;
    }
    public void setTablePrefix(String p_tableprefix) {
        this.tableprefix = p_tableprefix;
    }

    private String poolminsize;
    public String getPoolMinSize() {
        return poolminsize;
    }
    public void setPoolMinSize(String p_poolminsize) {
        this.poolminsize = p_poolminsize;
    }

    private String poolmaxsize;
    public String getPoolMaxSize() {
        return poolmaxsize;
    }
    public void setPoolMaxSize(String p_poolmaxsize) {
        this.poolmaxsize = p_poolmaxsize;
    }

    private String connecttimeout;
    public String getConnectTimeout() {
        return connecttimeout;
    }
    public void setConnectTimeout(String p_connecttimeout) {
        this.connecttimeout = p_connecttimeout;
    }

}
