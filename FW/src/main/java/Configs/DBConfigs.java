package Configs;

import Models.DBInfo;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by Administrator on 2016/12/6.
 */
public class DBConfigs extends BaseConfigs<DBConfig> {
    private static DBConfigs item;
    public static DBConfigs Instance() {
        if (item == null) {
            item = new DBConfigs();
        }
        return item;
    }
    //加载配置文件
    @Override
    public void LoadConfig() {
        try {
            File file = new File(getFilePath() + getFileName());
            //不存在则自动接创建
            m_ConfigInfo = getTClass().newInstance();
            if (!file.exists()) {
                //默认数据库配置
                DBInfo m_DBInfo = new DBInfo();
                m_DBInfo.setServer("192.168.0.88");
                m_DBInfo.setCatalog("MainDB");
                m_DBInfo.setDataType("SqlServer");
                m_DBInfo.setDataVer("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                m_DBInfo.setUserID("sa");
                m_DBInfo.setPassWord("Flzx#)))3000chi");
                m_DBInfo.setPoolMaxSize("100");
                m_DBInfo.setPoolMinSize("16");
                m_DBInfo.setConnectTimeout("30");
                m_DBInfo.setPort("1433");
                m_ConfigInfo.DBList.add(m_DBInfo);
                SaveConfig(m_ConfigInfo);
            }
            LoadAt = file.lastModified();
            synchronized (m_LockHelper) {
                String strProperty = System.getProperty("user.dir");
                //打开文件
                FileInputStream fileInputStream = new FileInputStream(getFilePath() + getFileName());

                DBConfig m_DbConfig = new DBConfig();
                XStream xStream = new XStream(new DomDriver("UTF-8"));
                xStream.alias("DBConfig", DBConfig.class);
                xStream.alias("DBList", m_DbConfig.DBList.getClass());
                xStream.alias(DBInfo.DBINFO, DBInfo.class);
                m_ConfigInfo = (DBConfig) xStream.fromXML(fileInputStream);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    //写入配置文件
    @Override
    public void SaveConfig(DBConfig dbConfig) {
        try {
            //打开文件
            FileOutputStream fileOutputStream = new FileOutputStream(getFilePath() + getFileName());
            XStream xStream = new XStream(new DomDriver("UTF-8"));
            // 设置根元素名
            xStream.alias("DBConfig", DBConfig.class);
            //设置子元素名
            xStream.alias("DBList", dbConfig.DBList.getClass());
            //设置子元素名
            xStream.alias(DBInfo.DBINFO, DBInfo.class);
            synchronized (m_LockHelper) {
                //开始序列化
                xStream.toXML(dbConfig, fileOutputStream);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
