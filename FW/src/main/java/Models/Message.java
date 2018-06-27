package Models;

import Models.Enum.MessagePriority;

import java.io.File;
import java.util.Map;
import java.util.Vector;

/**
 * Created by Administrator on 2017-08-13.
 */
public class Message {
    private MessagePriority _priority = MessagePriority.Normal;
    private String To;
    private String Cc;
    private String Bcc;
    private String Body;
    private String Subject;
    private Vector<File> files;
    private Map<String,String> _headers;

    public Map<String,String> getHeaders() {
        return _headers;
    }
    public void setHeaders(Map<String,String> _headers) {
        this._headers = _headers;
    }

    public Vector<File> getFiles() {
        return files;
    }
    public void setFiles(Vector<File> files) {
        this.files = files;
    }

    public String getSubject() {
        return Subject;
    }
    public void setSubject(String subject) {
        Subject = subject;
    }

    public MessagePriority getPriority() {
        return _priority;
    }
    public void setPriority(MessagePriority _priority) {
        this._priority = _priority;
    }

    public String getTo() {
        return To;
    }
    public void setTo(String to) {
        To = to;
    }

    public String getCc() {
        return Cc;
    }
    public void setCc(String cc) {
        Cc = cc;
    }

    public String getBcc() {
        return Bcc;
    }
    public void setBcc(String bcc) {
        Bcc = bcc;
    }

    public String getBody() {
        return Body;
    }
    public void setBody(String body) {
        Body = body;
    }
}
