package com.dalian.sea.WebClass;

import java.util.Date;

/**
 * Created by Administrator on 2017-09-20.
 */
public class ManageUser {
    /// <summary>
    /// 用户主键
    /// </summary>
    public int UID ;
    /// <summary>
    /// 用户编号
    /// </summary>
    public String C ;
    /// <summary>
    /// 用户姓名
    /// </summary>
    public String UN ;
    /// <summary>
    /// 公司名称
    /// </summary>
    public String CN ;
    /// <summary>
    /// 登陆账户
    /// </summary>
    public String A ;
    /// <summary>
    /// 登陆密码
    /// </summary>
    public String P ;
    /// <summary>
    /// 登录时间
    /// </summary>
    public Date LT ;
    /// <summary>
    /// 密钥
    /// </summary>
    public String S ;
    /// <summary>
    /// 性别(0-保密,1-男,2-女)
    /// </summary>
    public int G ;
    /// <summary>
    /// 公司ID
    /// </summary>
    public int CID ;
    /// <summary>
    /// 部门ID
    /// </summary>
    public int DID ;
    /// <summary>
    /// 对象用户关系ID,对象分类:1-部门2-角色3-岗位4-群组
    /// </summary>
    public String OID ;
    /// <summary>
    /// 登录IP地址
    /// </summary>
    public String IP ;
    /// <summary>
    /// 录IP地址所在地址
    /// </summary>
    public String IPName ;
    /// <summary>
    /// 是否系统账户；拥有所以权限(1-是,0-不是)
    /// </summary>
    public int ISys ;
    ///// <summary>
    ///// 允许产品ID
    ///// </summary>
    public int IsControl ;

    public String RoleName ;
}
