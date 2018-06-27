package Configs;

import Extend.StringExtend;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Administrator on 2016/12/5.
 */
public abstract class BaseConfigs<T> {
    /// <summary>
    /// 锁对象
    /// </summary>
    protected Object m_LockHelper = new Object();
    /// <summary>
    /// 配置文件路径
    /// </summary>
    private String filePath;
    /// <summary>
    /// 配置文件名称
    /// </summary>
    private String fileName;
    /// <summary>
    /// 配置变量
    /// </summary>
    protected  T m_ConfigInfo;
    /// <summary>
    /// Config修改时间
    /// </summary>
    protected long LoadAt;
    /// <summary>
    /// 获取配置文件所在路径，支持自定义路径
    /// </summary>
    public String getFilePath() throws Exception {
        if(StringExtend.Empty(filePath)) {
            //取得根目录路径
            filePath = getBasePath()+ "App_Data/";
        }
        return filePath;
    }
    public void setFilePath(String value) {
        filePath = value;
        if (filePath.endsWith("/")) filePath += "/";
    }
    /// <summary>
    /// 获取配置文件所在路径
    /// </summary>
    protected String getFileName() {
        if (StringExtend.Empty(fileName)) {
            String m_strFilePix=getTClass().getName();
            fileName = String.format("%s.xml", m_strFilePix.endsWith("Config") ? m_strFilePix.substring(m_strFilePix.lastIndexOf(".")+1).substring(0, m_strFilePix.substring(m_strFilePix.lastIndexOf(".") + 1).length() - 6)
                    : m_strFilePix.substring(m_strFilePix.lastIndexOf(".")+1));
        }
        return fileName;
    }
    /**
     配置变量
     */
    public T getConfigInfo() throws Exception {
        File file = new File(getFilePath() + getFileName());
        if (m_ConfigInfo == null || LoadAt!=file.lastModified())
        {
            LoadConfig();
        }
        return m_ConfigInfo;
    }
    /**
     加载(反序列化)指定对象类型的配置对象
     */
    public abstract  void LoadConfig() ;
    /**
     保存(序列化)指定路径下的配置文件
     */
    public abstract void SaveConfig(T t) ;
    public Class<T> getTClass() {
        Type genType = getClass().getGenericSuperclass();//获取当前父类完整类
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();//获取父类泛型参数
        return  (Class<T>) params[0];//将第一个泛型参数返回
    }
    public  String getBasePath()
    {
        return PropertiesInfo.getKeyValue("BasePath");
    }
}
