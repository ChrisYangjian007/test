package Models;

/**
 * Created by Administrator on 2017-08-13.
 */
public class EmailInfo {

    public static final String EmailInfo = "EmailInfo";
    /// 是否Html邮件
    private boolean IsHtml = false;
    /// 登陆用户名
    private String LoginName = "";
    /// 登陆密码
    private String LoginPwd = "";
    /// 最多收件人数量
    private int RecipientMaxNum = 5;
    /// 发件人E-Mail地址
    private String SendMail = "";
    /// 发件人姓名
    private String SendUserName = "";
    /// 端口号
    private int SmtpPort = 25;
    /// 是否需要验证
    private boolean IsAuth = true;
    /// 邮件服务器域名和验证信息
    /// 形如：Smtp.server.com"
    private String SmtpServer = "";

    public boolean isHtml() {
        return IsHtml;
    }
    public void setHtml(boolean isHtml) {
        IsHtml = isHtml;
    }

    public boolean isAuth() {
        return IsAuth;
    }
    public void setAuth(boolean isAuth) {
        IsAuth = isAuth;
    }

    public String getLoginName() {
        return LoginName;
    }
    public void setLoginName(String loginName) {
        LoginName = loginName;
    }

    public String getLoginPwd() {
        return LoginPwd;
    }
    public void setLoginPwd(String loginPwd) {
        LoginPwd = loginPwd;
    }

    public int getRecipientMaxNum() {
        return RecipientMaxNum;
    }
    public void setRecipientMaxNum(int recipientMaxNum) {
        RecipientMaxNum = recipientMaxNum;
    }

    public String getSendMail() {
        return SendMail;
    }
    public void setSendMail(String sendMail) {
        SendMail = sendMail;
    }

    public String getSendUserName() {
        return SendUserName;
    }
    public void setSendUserName(String sendUserName) {
        SendUserName = sendUserName;
    }

    public int getSmtpPort() {
        return SmtpPort;
    }
    public void setSmtpPort(int smtpPort) {
        SmtpPort = smtpPort;
    }

    public String getSmtpServer() {
        return SmtpServer;
    }
    public void setSmtpServer(String smtpServer) {
        SmtpServer = smtpServer;
    }
}
