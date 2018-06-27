package Configs;

import Models.EmailInfo;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by Administrator on 2017-08-13.
 */
public class EmailConfigs extends BaseConfigs<EmailConfig>  {
    private static EmailConfigs item;
    public static EmailConfigs Instance() {
        if (item == null) {
            item = new EmailConfigs();
        }
        return item;
    }

    @Override
    public void LoadConfig() {
        try {
            File file = new File(getFilePath() + getFileName());
            //不存在则自动接创建
            m_ConfigInfo = getTClass().newInstance();
            if (!file.exists()) {
                //默认数据库配置
                EmailInfo m_EmailInfo = new EmailInfo();
                m_EmailInfo.setHtml(true);
                m_EmailInfo.setLoginName("shaoxj@greenmarks.org");
                m_EmailInfo.setLoginPwd("exqqmailcom465");
                m_EmailInfo.setRecipientMaxNum(5);
                m_EmailInfo.setSendMail("server@greenmarks.org");
                m_EmailInfo.setSendUserName("greenmarks");
                m_EmailInfo.setSmtpPort(25);
                m_EmailInfo.setSmtpServer("smtp.exmail.qq.com");
                m_ConfigInfo.EmailList.add(m_EmailInfo);
                SaveConfig(m_ConfigInfo);
            }
            LoadAt = file.lastModified();
            synchronized (m_LockHelper) {
                //打开文件
                FileInputStream fileInputStream = new FileInputStream(getFilePath() + getFileName());

                EmailConfig m_EmailConfig = new EmailConfig();
                XStream xStream = new XStream(new DomDriver("UTF-8"));
                xStream.alias("EmailConfig", EmailConfig.class);
                xStream.alias("EmailList", m_EmailConfig.EmailList.getClass());
                xStream.alias(EmailInfo.EmailInfo, EmailInfo.class);
                m_ConfigInfo = (EmailConfig) xStream.fromXML(fileInputStream);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void SaveConfig(EmailConfig emailConfig) {
        try {
            //打开文件
            FileOutputStream fileOutputStream = new FileOutputStream(getFilePath() + getFileName());
            XStream xStream = new XStream(new DomDriver("UTF-8"));
            // 设置根元素名
            xStream.alias("EmailConfig", EmailConfig.class);
            //设置子元素名
            xStream.alias("EmailList", emailConfig.EmailList.getClass());
            //设置子元素名
            xStream.alias(EmailInfo.EmailInfo, EmailInfo.class);
            synchronized (m_LockHelper) {
                //开始序列化
                xStream.toXML(emailConfig, fileOutputStream);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
