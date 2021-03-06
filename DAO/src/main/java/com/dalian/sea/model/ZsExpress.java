package com.dalian.sea.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "zs_express")
@Data
public class ZsExpress implements Serializable {
    /**
     * 快递ID
     */
    @Id
    @Column(name = "express_id")
    private Long expressId;

    /**
     * 订单编号
     */
    @Column(name = "order_code")
    private String orderCode;

    /**
     * 箱码编号
     */
    @Column(name = "box_code")
    private String boxCode;

    /**
     * 快递编号
     */
    @Column(name = "express_code")
    private String expressCode;

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
     * 状态(1-正常,6-删除,2-禁用)
     */
    private Byte status;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", expressId=").append(expressId);
        sb.append(", orderCode=").append(orderCode);
        sb.append(", boxCode=").append(boxCode);
        sb.append(", expressCode=").append(expressCode);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}