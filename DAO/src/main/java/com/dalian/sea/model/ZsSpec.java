package com.dalian.sea.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "zs_spec")
public class ZsSpec implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "spec_id")
    private Long specId;

    /**
     * 名称
     */
    @Column(name = "c_name")
    private String cName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 公司主键
     */
    @Column(name = "company_id")
    private Long companyId;

    /**
     * 排序码
     */
    @Column(name = "list_index")
    private Integer listIndex;

    /**
     * 产品类别(字典)
     */
    @Column(name = "product_type_id")
    private Long productTypeId;

    /**
     * 产品类别(字典)
     */
    @Column(name = "product_type_name")
    private String productTypeName;

    /**
     * 包装单位ID
     */
    @Column(name = "pack_unit_id")
    private Long packUnitId;

    /**
     * 包装单位名称
     */
    @Column(name = "pack_unit_name")
    private String packUnitName;

    /**
     * 包装重量
     */
    private BigDecimal quantity;

    /**
     * 重量单位ID
     */
    @Column(name = "quantity_unit_id")
    private Long quantityUnitId;

    /**
     * 重量单位名称
     */
    @Column(name = "quantity_unit_name")
    private String quantityUnitName;

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
        sb.append(", specId=").append(specId);
        sb.append(", cName=").append(cName);
        sb.append(", remark=").append(remark);
        sb.append(", companyId=").append(companyId);
        sb.append(", listIndex=").append(listIndex);
        sb.append(", productTypeId=").append(productTypeId);
        sb.append(", productTypeName=").append(productTypeName);
        sb.append(", packUnitId=").append(packUnitId);
        sb.append(", packUnitName=").append(packUnitName);
        sb.append(", quantity=").append(quantity);
        sb.append(", quantityUnitId=").append(quantityUnitId);
        sb.append(", quantityUnitName=").append(quantityUnitName);
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