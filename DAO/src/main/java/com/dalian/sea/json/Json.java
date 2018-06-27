package com.dalian.sea.json;

import java.io.Serializable;

/**
 *
 * @author 杨文波
 * @date 2017/12/13
 */
public class Json implements Serializable{
    private boolean success = false;
    private Object obj;
    private String msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
