package com.dalian.sea.Base;

import Compress.DeflaterCompress;
import Configs.PropertiesInfo;
import DevCache.CookieUtil;
import Interface.ICompress;
import com.dalian.sea.WebClass.ManageUser;
import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2017-09-20.
 */
public class ManageLogic {
    private static ManageLogic _item;
    private ManageUser _currentUser;
    /// <summary>
    /// 秘钥
    /// </summary>
    private String LoginUserKey = "MU";

    /// <summary>
    /// 当前提供者
    /// </summary>
    public static ManageLogic Instance() {
        if (_item == null) {
            _item = new ManageLogic();
        }
        return _item;
    }

    /// <summary>
    /// 写入登录信息
    /// </summary>
    /// <param name="user">成员信息</param>
   /* public void AddCurrent(HttpServletResponse response, ManageUser user) {
        try {
            ICompress mICompress = new DeflaterCompress();
            byte[] bytTemp = mICompress.Compress(JSON.toJSONString(user));
            CookieUtil.setCookie(response, LoginUserKey, URLEncoder.encode(Base64.encode(bytTemp), "utf-8"), Integer.parseInt(PropertiesInfo.getKeyValue("Cookies_TimeOut")));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /// <summary>
    /// 当前用户
    /// </summary>
    public ManageUser Current(HttpServletRequest request) {
        ICompress mICompress = new DeflaterCompress();
        try {
            _currentUser = JSON.parseObject(mICompress.Decompress(Base64.decode(URLDecoder.decode(CookieUtil.getCookie(request, LoginUserKey),"utf-8"))), ManageUser.class);
            return _currentUser;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /// <summary>
    /// 删除登录信息
    /// </summary>
    public void EmptyCurrent(HttpServletRequest request, HttpServletResponse response)
    {
        CookieUtil.Clear(request,response,LoginUserKey.trim());
        CookieUtil.Clear(request,response,"MID");
        CookieUtil.Clear(request,response,"UI");
    }*/
}
