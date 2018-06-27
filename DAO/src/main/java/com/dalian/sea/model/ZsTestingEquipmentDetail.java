package com.dalian.sea.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "zs_testing_equipment_detail")
@Data
public class ZsTestingEquipmentDetail implements Serializable {
    @Id
    @Column(name = "testing_equipment_detail_id")
    private Long testingEquipmentDetailId;

    /**
     * 实验室id
     */
    @Column(name = "testing_equipment_id")
    private Long testingEquipmentId;

    /**
     * 名称
     */
    @Column(name = "testing_equipment_detail_cname")
    private String testingEquipmentDetailCname;

    /**
     * 文字描述
     */
    private String remark;

    /**
     * 图片
     */
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
        sb.append(", testingEquipmentDetailId=").append(testingEquipmentDetailId);
        sb.append(", testingEquipmentId=").append(testingEquipmentId);
        sb.append(", testingEquipmentDetailCname=").append(testingEquipmentDetailCname);
        sb.append(", remark=").append(remark);
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