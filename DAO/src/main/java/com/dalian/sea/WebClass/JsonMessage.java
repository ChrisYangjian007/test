package com.dalian.sea.WebClass;

/**
 * Created by Administrator on 2017-10-11.
 */
public class JsonMessage implements java.io.Serializable {
    private boolean success = false;
    private String code = "";
    private String message = "";
    private Object data = null;
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
}