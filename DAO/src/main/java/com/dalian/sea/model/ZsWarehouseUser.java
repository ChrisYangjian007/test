package com.dalian.sea.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "zs_warehouse_user")
@Data
public class ZsWarehouseUser implements Serializable {
    /**
     * 仓库负责人表id
     */
    @Id
    @Column(name = "warehouse_user_id")
    private Long warehouseUserId;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 仓库id
     */
    @Column(name = "warehouse_id")
    private Long warehouseId;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 创建人id
     */
    @Column(name = "create_user_id")
    private Long createUserId;

    /**
     * 修改时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 修改人id
     */
    @Column(name = "update_user_id")
    private Long updateUserId;

    /**
     * 状态（1-正常 2-禁用 6-删除）
     */
    private Byte status;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", warehouseUserId=").append(warehouseUserId);
        sb.append(", userId=").append(userId);
        sb.append(", warehouseId=").append(warehouseId);
        sb.append(", createDate=").append(createDate);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}