package com.dalian.sea.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "zs_company_product")
@Data
public class ZsCompanyProduct implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "product_id")
    private Long productId;

    /**
     * 产品编号
     */
    @Column(name = "product_no")
    private String productNo;

    /**
     * 产品名称
     */
    @Column(name = "c_name")
    private String cName;

    /**
     * 类型(数据字典)
     */
    private Long type;

    /**
     * 产品类型
     */
    @Column(name = "product_type_id")
    private Long productTypeId;

    /**
     * 类型名称
     */
    @Column(name = "product_type_name")
    private String productTypeName;

    /**
     * 产品等级 (普通、绿色、有机、无公害)
     */
    private Byte grade;

    /**
     * 产品规格（手动输入）
     */
    @Column(name = "product_specification")
    private String productSpecification;

    /**
     * 单位
     */
    @Column(name = "sys_unit_id")
    private Long sysUnitId;

    /**
     * 单位
     */
    @Column(name = "sys_unit_name")
    private String sysUnitName;

    /**
     * 净重
     */
    @Column(name = "net_weight")
    private String netWeight;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 排序码
     */
    @Column(name = "list_index")
    private Integer listIndex;

    /**
     * 公司主键
     */
    @Column(name = "company_id")
    private Long companyId;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 产品图片(限３个用,分开)
     */
    @Column(name = "image_path")
    private String imagePath;

    /**
     * 备注
     */
    private String remark;

    /**
     * 原料供应商ID
     */
    @Column(name = "material_company_id")
    private Long materialCompanyId;

    /**
     * 原料供应商名
     */
    @Column(name = "material_c_name")
    private String materialCName;

    /**
     * 生许有效期
     */
    @Column(name = "produce_term_date")
    private Date produceTermDate;

    /**
     * 产标有效期
     */
    @Column(name = "term_date")
    private Date termDate;

    /**
     * 产品标准号
     */
    @Column(name = "produce_code")
    private String produceCode;

    /**
     * 生产许可证
     */
    @Column(name = "produce_license")
    private String produceLicense;

    /**
     * 农登有效期
     */
    @Column(name = "register_term_date")
    private Date registerTermDate;

    /**
     * 农药登记证
     */
    @Column(name = "pesticide_license")
    private String pesticideLicense;

    /**
     * 规格ID
     */
    @Column(name = "product_spec_id")
    private Long productSpecId;

    /**
     * 规格
     */
    @Column(name = "product_spec_name")
    private String productSpecName;

    /**
     * 件规格ID
     */
    @Column(name = "piece_spec_id")
    private Long pieceSpecId;

    /**
     * 件规格
     */
    @Column(name = "piece_spec_name")
    private String pieceSpecName;

    /**
     * 剂型（字典）
     */
    @Column(name = "dosage_type")
    private Long dosageType;

    /**
     * 剂型
     */
    @Column(name = "dosage_type_name")
    private String dosageTypeName;

    /**
     * 含量
     */
    private String measure;

    /**
     * 是否长期有效
     */
    @Column(name = "is_valid")
    private Byte isValid;

    /**
     * 经销商供货价 
     */
    @Column(name = "supplier_price")
    private BigDecimal supplierPrice;

    /**
     * 保质期(冷藏)
     */
    @Column(name = "prod_life_cold")
    private String prodLifeCold;

    /**
     * 保质期(冷冻)
     */
    @Column(name = "prod_life_freezing")
    private String prodLifeFreezing;

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
        sb.append(", productId=").append(productId);
        sb.append(", productNo=").append(productNo);
        sb.append(", cName=").append(cName);
        sb.append(", type=").append(type);
        sb.append(", productTypeId=").append(productTypeId);
        sb.append(", productTypeName=").append(productTypeName);
        sb.append(", grade=").append(grade);
        sb.append(", productSpecification=").append(productSpecification);
        sb.append(", sysUnitId=").append(sysUnitId);
        sb.append(", sysUnitId=").append(sysUnitName);
        sb.append(", netWeight=").append(netWeight);
        sb.append(", price=").append(price);
        sb.append(", listIndex=").append(listIndex);
        sb.append(", companyId=").append(companyId);
        sb.append(", brand=").append(brand);
        sb.append(", imagePath=").append(imagePath);
        sb.append(", remark=").append(remark);
        sb.append(", materialCompanyId=").append(materialCompanyId);
        sb.append(", materialCName=").append(materialCName);
        sb.append(", produceTermDate=").append(produceTermDate);
        sb.append(", termDate=").append(termDate);
        sb.append(", produceCode=").append(produceCode);
        sb.append(", produceLicense=").append(produceLicense);
        sb.append(", registerTermDate=").append(registerTermDate);
        sb.append(", pesticideLicense=").append(pesticideLicense);
        sb.append(", productSpecId=").append(productSpecId);
        sb.append(", productSpecName=").append(productSpecName);
        sb.append(", pieceSpecId=").append(pieceSpecId);
        sb.append(", pieceSpecName=").append(pieceSpecName);
        sb.append(", dosageType=").append(dosageType);
        sb.append(", dosageTypeName=").append(dosageTypeName);
        sb.append(", measure=").append(measure);
        sb.append(", isValid=").append(isValid);
        sb.append(", supplierPrice=").append(supplierPrice);
        sb.append(", prodLifeCold=").append(prodLifeCold);
        sb.append(", prodLifeFreezing=").append(prodLifeFreezing);
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