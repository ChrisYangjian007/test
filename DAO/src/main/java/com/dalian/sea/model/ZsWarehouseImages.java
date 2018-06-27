package com.dalian.sea.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "zs_warehouse_images")
@Data
public class ZsWarehouseImages implements Serializable {
    @Id
    @Column(name = "warehouse_image_id")
    private Long warehouseImageId;

    /**
     * 仓库ID
     */
    @Column(name = "warehouse_id")
    private Long warehouseId;

    /**
     * 上传月份
     */
    @Column(name = "upload_month")
    private Date uploadMonth;

    /**
     * 上传图片
     */
    @Column(name = "image_size")
    private Integer imagesSize;

    /**
     * 上传图片
     */
    @Column(name = "images_json")
    private String imagesJson;

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
        sb.append(", warehouseImageId=").append(warehouseImageId);
        sb.append(", warehouseId=").append(warehouseId);
        sb.append(", uploadMonth=").append(uploadMonth);
        sb.append(", imagesJson=").append(imagesJson);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}