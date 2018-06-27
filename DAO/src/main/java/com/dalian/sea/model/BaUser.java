package com.dalian.sea.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "ba_user")
@Data
public class BaUser implements Serializable {
    /**
     * 用户主键
     */
    @Id
    @Column(name = "user_id")
    private Long userId;

    /**
     * 公司主键
     */
    @Column(name = "company_id")
    private Long companyId;

    /**
     * 部门主键（0-待分配）
     */
    @Column(name = "department_id")
    private Long departmentId;

    /**
     * 部门名称（无表示待分配）
     */
    @Column(name = "department_name")
    private String departmentName;
    /**
     * 用户认证状态（0-未认证，1-认证）
     */
    @Column(name = "auth_status")
    private Byte authStatus;

    /**
     * 用户认证类型（1-个人，2-企业，3-机构，4-监管机构）
     */
    @Column(name = "user_authority_type")
    private Byte userAuthorityType;

    /**
     * 编码
     */
    private String code;

    /**
     * 登录账号
     */
    private String account;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 密码秘钥
     */
    private String secretkey;

    /**
     * 名称
     */
    @Column(name = "c_name")
    private String cName;

    /**
     * 姓名拼音
     */
    private String spell;

    /**
     * 性别（1-男，2-女）
     */
    private Byte gender;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 电话
     */
    private String telephone;

    /**
     * QQ号码
     */
    private String qicq;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 最后修改密码日期
     */
    @Column(name = "change_password_date")
    private Date changePasswordDate;

    /**
     * 单点登录标识
     */
    @Column(name = "open_id")
    private Long openId;

    /**
     * 登录次数
     */
    @Column(name = "log_on_count")
    private Integer logOnCount;

    /**
     * 上次访问一时间
     */
    @Column(name = "previous_visit")
    private Date previousVisit;

    /**
     * 最后访问时间
     */
    @Column(name = "last_visit")
    private Date lastVisit;

    /**
     * 是否在线（0-不在线，1-在线）
     */
    private Byte online;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序
     */
    @Column(name = "list_index")
    private Integer listIndex;

    /**
     * 地区ID
     */
    @Column(name = "area_id")
    private Long areaId;

    /**
     * 地区所属父类ID
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 微信号
     */
    @Column(name = "wei_xin")
    private String weiXin;

    /**
     * 是否可以控制设备（0-不可以控制，1-可以控制）
     */
    @Column(name = "is_control")
    private Integer isControl;

    /**
     * 头像
     */
    @Column(name = "head_image")
    private String headImage;

    /**
     * 审核状态（0-待审核，1-审核通过，2-审核失败，3-修改待审核）
     */
    @Column(name = "ckeck_status")
    private Integer ckeckStatus;

    /**
     * 学历
     */
    private String education;

    /**
     * 家庭住址ID
     */
    @Column(name = "address_id")
    private Long addressId;

    /**
     * 详细地址
     */
    @Column(name = "address_details")
    private String addressDetails;

    /**
     * 户口
     */
    private String residence;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 创建者ID
     */
    @Column(name = "create_user_id")
    private Long createUserId;

    /**
     * 更新时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 更新者ID
     */
    @Column(name = "update_user_id")
    private Long updateUserId;

    /**
     * 状态（1-正常，2-禁用，6-删除）
     */
    private Byte status;

    /**
     * 1:pc,2:pda,3:pc&pda
     * */
    @Column(name = "user_type")
    private Byte userType;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "BaUser{" +
                "userId=" + userId +
                ", companyId=" + companyId +
                ", departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", authStatus=" + authStatus +
                ", userAuthorityType=" + userAuthorityType +
                ", code='" + code + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", secretkey='" + secretkey + '\'' +
                ", cName='" + cName + '\'' +
                ", spell='" + spell + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", mobile='" + mobile + '\'' +
                ", telephone='" + telephone + '\'' +
                ", qicq='" + qicq + '\'' +
                ", email='" + email + '\'' +
                ", changePasswordDate=" + changePasswordDate +
                ", openId=" + openId +
                ", logOnCount=" + logOnCount +
                ", previousVisit=" + previousVisit +
                ", lastVisit=" + lastVisit +
                ", online=" + online +
                ", remark='" + remark + '\'' +
                ", listIndex=" + listIndex +
                ", areaId=" + areaId +
                ", parentId=" + parentId +
                ", weiXin='" + weiXin + '\'' +
                ", isControl=" + isControl +
                ", headImage='" + headImage + '\'' +
                ", ckeckStatus=" + ckeckStatus +
                ", education='" + education + '\'' +
                ", addressId=" + addressId +
                ", addressDetails='" + addressDetails + '\'' +
                ", residence='" + residence + '\'' +
                ", createDate=" + createDate +
                ", createUserId=" + createUserId +
                ", updateDate=" + updateDate +
                ", updateUserId=" + updateUserId +
                ", status=" + status +
                ", userType=" + userType +
                '}';
    }
}