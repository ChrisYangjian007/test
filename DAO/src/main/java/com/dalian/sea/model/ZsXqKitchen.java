package com.dalian.sea.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "zs_xq_kitchen")
@Data
public class ZsXqKitchen implements Serializable {
    @Id
    @Column(name = "kitchen_id")
    private Long kitchenId;

    /**
     * 名称
     */
    @Column(name = "kitchen_name")
    private String kitchenName;

    /**
     * 特点
     */
    private String features;

    /**
     * 配料
     */
    @Column(name = "Ingredients")
    private String ingredients;

    /**
     * 制作方法
     */
    @Column(name = "production_method")
    private String productionMethod;

    private String images;

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
        sb.append(", kitchenId=").append(kitchenId);
        sb.append(", kitchenName=").append(kitchenName);
        sb.append(", features=").append(features);
        sb.append(", ingredients=").append(ingredients);
        sb.append(", productionMethod=").append(productionMethod);
        sb.append(", images=").append(images);
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