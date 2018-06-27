package com.dalian.sea.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "zs_production_process_detail")
@Data
public class ZsProductionProcessDetail implements Serializable {
    @Id
    @Column(name = "production_process_detailed_id")
    private Long productionProcessDetailedId;

    /**
     * 数字编号
     */
    @Column(name = "production_process_detail_number")
    private Integer productionProcessDetailNumber;

    /**
     * 过程详情名称
     */
    @Column(name = "production_process_detail_name")
    private String productionProcessDetailName;

    /**
     * 生产过程Id
     */
    @Column(name = "production_process_id")
    private Long productionProcessId;


    /**
     * 过程描述
     */
    @Column(name = "process_description")
    private String processDescription;

    /**
     * 生产过程详情图片(1张）
     */
    @Column(name = "detail_image")
    private String detailImage;

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
        sb.append(", productionProcessDetailedId=").append(productionProcessDetailedId);
        sb.append(", productionProcessId=").append(productionProcessId);
        sb.append(", processDescription=").append(processDescription);
        sb.append(", detailImage=").append(detailImage);
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