package com.dalian.sea.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sa_order")
public class SaOrder implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 排序
     */
    @Column(name = "list_index")
    private Integer listIndex;

    /**
     * 操作人员ID
     */
    @Column(name = "create_user_id")
    private Long createUserId;

    /**
     * 修改用户主键
     */
    @Column(name = "update_user_id")
    private Long updateUserId;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 更新时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 订单状态（1-待处理 2-进行中 3-已完成）
     */
    @Column(name = "order_status")
    private Byte orderStatus;

    /**
     * 状态(1-正常,6-删除,2-禁用)
     */
    private Byte status;

    /**
     * 订货申请编号
     */
    @Column(name = "order_name")
    private String orderName;

    /**
     * 申请人
     */
    @Column(name = "order_ownerid")
    private String orderOwnerid;

    /**
     * 订货日期
     */
    @Column(name = "order_dhrq")
    private Date orderDhrq;

    /**
     * 收货人
     */
    @Column(name = "order_shr")
    private String orderShr;

    /**
     * 收货地址
     */
    @Column(name = "order_shdz")
    private String orderShdz;

    /**
     * 联系电话
     */
    @Column(name = "order_lxdh")
    private String orderLxdh;

    /**
     * 审批状态
     */
    @Column(name = "order_spzt")
    private String orderSpzt;

    /**
     * 所属门店
     */
    @Column(name = "order_ssmd")
    private String orderSsmd;

    /**
     * 是否是经销商
     */
    @Column(name = "order_isjxs")
    private Byte orderIsjxs;

    /**
     * 经销商
     */
    @Column(name = "order_jxs")
    private String orderJxs;
    /**
     * 是否打包
     */
    @Column(name = "order_ispack")
    private Byte orderIspack;
    /**
     * 打包数量
     */
    @Column(name = "order_pack_count")
    private Integer orderPackCount;
    private static final long serialVersionUID = 1L;

    /**
     * 获取主键
     *
     * @return order_id - 主键
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 设置主键
     *
     * @param orderId 主键
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取排序
     *
     * @return list_index - 排序
     */
    public Integer getListIndex() {
        return listIndex;
    }

    /**
     * 设置排序
     *
     * @param listIndex 排序
     */
    public void setListIndex(Integer listIndex) {
        this.listIndex = listIndex;
    }

    /**
     * 获取操作人员ID
     *
     * @return create_user_id - 操作人员ID
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置操作人员ID
     *
     * @param createUserId 操作人员ID
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取修改用户主键
     *
     * @return update_user_id - 修改用户主键
     */
    public Long getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 设置修改用户主键
     *
     * @param updateUserId 修改用户主键
     */
    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取更新时间
     *
     * @return update_date - 更新时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置更新时间
     *
     * @param updateDate 更新时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取订单状态（1-待处理 2-进行中 3-已完成）
     *
     * @return order_status - 订单状态（1-待处理 2-进行中 3-已完成）
     */
    public Byte getOrderStatus() {
        return orderStatus;
    }

    /**
     * 设置订单状态（1-待处理 2-进行中 3-已完成）
     *
     * @param orderStatus 订单状态（1-待处理 2-进行中 3-已完成）
     */
    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 获取状态(1-正常,6-删除,2-禁用)
     *
     * @return status - 状态(1-正常,6-删除,2-禁用)
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置状态(1-正常,6-删除,2-禁用)
     *
     * @param status 状态(1-正常,6-删除,2-禁用)
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取订货申请编号
     *
     * @return order_name - 订货申请编号
     */
    public String getOrderName() {
        return orderName;
    }

    /**
     * 设置订货申请编号
     *
     * @param orderName 订货申请编号
     */
    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    /**
     * 获取申请人
     *
     * @return order_ownerid - 申请人
     */
    public String getOrderOwnerid() {
        return orderOwnerid;
    }

    /**
     * 设置申请人
     *
     * @param orderOwnerid 申请人
     */
    public void setOrderOwnerid(String orderOwnerid) {
        this.orderOwnerid = orderOwnerid;
    }

    /**
     * 获取订货日期
     *
     * @return order_dhrq - 订货日期
     */
    public Date getOrderDhrq() {
        return orderDhrq;
    }

    /**
     * 设置订货日期
     *
     * @param orderDhrq 订货日期
     */
    public void setOrderDhrq(Date orderDhrq) {
        this.orderDhrq = orderDhrq;
    }

    /**
     * 获取收货人
     *
     * @return order_shr - 收货人
     */
    public String getOrderShr() {
        return orderShr;
    }

    /**
     * 设置收货人
     *
     * @param orderShr 收货人
     */
    public void setOrderShr(String orderShr) {
        this.orderShr = orderShr;
    }

    /**
     * 获取收货地址
     *
     * @return order_shdz - 收货地址
     */
    public String getOrderShdz() {
        return orderShdz;
    }

    /**
     * 设置收货地址
     *
     * @param orderShdz 收货地址
     */
    public void setOrderShdz(String orderShdz) {
        this.orderShdz = orderShdz;
    }

    /**
     * 获取联系电话
     *
     * @return order_lxdh - 联系电话
     */
    public String getOrderLxdh() {
        return orderLxdh;
    }

    /**
     * 设置联系电话
     *
     * @param orderLxdh 联系电话
     */
    public void setOrderLxdh(String orderLxdh) {
        this.orderLxdh = orderLxdh;
    }

    /**
     * 获取审批状态
     *
     * @return order_spzt - 审批状态
     */
    public String getOrderSpzt() {
        return orderSpzt;
    }

    /**
     * 设置审批状态
     *
     * @param orderSpzt 审批状态
     */
    public void setOrderSpzt(String orderSpzt) {
        this.orderSpzt = orderSpzt;
    }

    /**
     * 获取所属门店
     *
     * @return order_ssmd - 所属门店
     */
    public String getOrderSsmd() {
        return orderSsmd;
    }

    /**
     * 设置所属门店
     *
     * @param orderSsmd 所属门店
     */
    public void setOrderSsmd(String orderSsmd) {
        this.orderSsmd = orderSsmd;
    }

    /**
     * 获取是否是经销商
     *
     * @return order_isjxs - 是否是经销商
     */
    public Byte getOrderIsjxs() {
        return orderIsjxs;
    }

    /**
     * 设置是否是经销商
     *
     * @param orderIsjxs 是否是经销商
     */
    public void setOrderIsjxs(Byte orderIsjxs) {
        this.orderIsjxs = orderIsjxs;
    }

    public String getOrderJxs() {
        return orderJxs;
    }

    public void setOrderJxs(String orderJxs) {
        this.orderJxs = orderJxs;
    }

    public Byte getOrderIspack() {
        return orderIspack;
    }

    public void setOrderIspack(Byte orderIspack) {
        this.orderIspack = orderIspack;
    }

    public Integer getOrderPackCount() {
        return orderPackCount;
    }

    public void setOrderPackCount(Integer orderPackCount) {
        this.orderPackCount = orderPackCount;
    }
}