package Utils;

import Configs.EmailConfigs;
import Extend.StringExtend;
import Models.EmailInfo;
import Models.Message;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Address;
import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

/**
* Created by Administrator on 2017-05-22.
*/
public class EmailSender {
    private String _SendMail;
    private EmailInfo _EmailInfo;
    private Session _SendMailSession;

    private static EmailSender item;
    public static EmailSender Instance(EmailInfo p_EmailInfo) {
        if (item == null) {
            item = new EmailSender(p_EmailInfo);
        }
        return item;
    }
    public static EmailSender Instance() throws Exception {
        if (item == null) {
            EmailInfo m_EmailInfo = EmailConfigs.Instance().getConfigInfo().EmailList.get(0);
            item = new EmailSender(m_EmailInfo);
        }
        return item;
    }

    public EmailSender(EmailInfo p_EmailInfo) {
        _EmailInfo = p_EmailInfo;
        _SendMail=p_EmailInfo.getSendMail();
        Properties pro = System.getProperties();
        pro.put("mail.transport.protocol", "smtp");
        pro.put("mail.smtp.host", _EmailInfo.getSmtpServer());
        pro.put("mail.smtp.port", _EmailInfo.getSmtpPort());
        if (_EmailInfo.isAuth()) {
            pro.put("mail.smtp.auth", "true");
            _SendMailSession = Session.getDefaultInstance(pro,
                    new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(_EmailInfo.getLoginName(), _EmailInfo.getLoginPwd());
                        }
                    });
        } else {
            pro.put("mail.smtp.auth", "false");
            _SendMailSession = Session.getDefaultInstance(pro, new Authenticator() {
            });
        }
    }

    public void Send(Message message, boolean IsBodyHtml) throws MessagingException {
        MimeMessage mailMessage = new MimeMessage(_SendMailSession);
        mailMessage.setFrom(new InternetAddress(_SendMail));

        for(String Tmp : message.getTo().split(";") ) {
            mailMessage.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(Tmp));
        }

        if (!StringExtend.Empty(message.getCc())) {
            for (String Tmp : message.getCc().split(";")) {
                mailMessage.addRecipient(javax.mail.Message.RecipientType.CC, new InternetAddress(Tmp));
            }
        }

        if (!StringExtend.Empty(message.getBcc())) {
            for (String Tmp : message.getBcc().split(";")) {
                mailMessage.addRecipient(javax.mail.Message.RecipientType.BCC, new InternetAddress(Tmp));
            }
        }

//        // 设置优先级(1:紧急   3:普通    5:低)
//        message.setHeader("X-Priority", "1");
//        // 要求阅读回执(收件人阅读邮件时会提示回复发件人,表明邮件已收到,并已阅读)
//        message.setHeader("Disposition-Notification-To", from);
        if (message.getHeaders()!=null&&message.getHeaders().size()>0) {
            Iterator iter = message.getHeaders().entrySet().iterator();
            //赋值字段
            while (iter.hasNext()) {
                java.util.Map.Entry entry = (java.util.Map.Entry) iter.next();
                mailMessage.setHeader((String)entry.getKey(),(String)entry.getValue());
            }
        }

        mailMessage.setReplyTo(new Address[]{new InternetAddress(_SendMail)});
        mailMessage.setSubject(message.getSubject(), "utf-8");
        // 发送时间
        mailMessage.setSentDate(new Date());

        if (null == message.getFiles() || message.getFiles().size() == 0) {
            mailMessage.setContent(message.getBody(), "text/html;charset=utf-8");
        } else {
            //创建 Mimemultipart加入内容(可包括多个附件)
            MimeMultipart multipart = new MimeMultipart();
            //MimeBodyPart(用于信件内容/附件)
            BodyPart bodyPart = new MimeBodyPart();
            bodyPart.setContent(message.getBody(), "text/html;charset=utf-8");
            //加入到MimeMultipart对象中
            multipart.addBodyPart(bodyPart);
            for (int i = 0; i < message.getFiles().size(); i++) {
                File file = (File) message.getFiles().elementAt(i);
                String fname = file.getName();
                //创建FileDAtaSource(用于加入附件)
                FileDataSource fds = new FileDataSource(file);
                BodyPart fileBodyPart = new MimeBodyPart();
                // 字符流形式装入文件
                fileBodyPart.setDataHandler(new DataHandler(fds));
                // 设置附件文件名称
                fileBodyPart.setFileName(fname);
                multipart.addBodyPart(fileBodyPart);
            }
            mailMessage.setContent(multipart);
        }

        // 保存并生成最终的邮件内容
        mailMessage.saveChanges();
        Transport.send(mailMessage);
    }
}