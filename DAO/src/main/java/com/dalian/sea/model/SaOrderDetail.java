package com.dalian.sea.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sa_order_detail")
public class SaOrderDetail implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "order_detail_id")
    private Long orderDetailId;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 操作人员ID
     */
    @Column(name = "create_user_id")
    private Long createUserId;

    /**
     * 更新时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 修改用户主键
     */
    @Column(name = "update_user_id")
    private Long updateUserId;

    /**
     * 状态(1-正常,6-删除,2-禁用)
     */
    private Byte status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 公司ID
     */
    @Column(name = "company_id")
    private Long companyId;

    /**
     * 排序
     */
    @Column(name = "list_index")
    private Integer listIndex;

    /**
     * 入库重量
     */
    @Column(name = "in_weight")
    private BigDecimal inWeight;

    /**
     * 单位ID
     */
    @Column(name = "unit_id")
    private Long unitId;

    /**
     * 单位名称
     */
    @Column(name = "unit_name")
    private String unitName;

    /**
     * 规格名称
     */
    @Column(name = "product_spec_name")
    private String productSpecName;

    /**
     * 规格ID
     */
    @Column(name = "product_spec_id")
    private Long productSpecId;

    /**
     * 产品ID
     */
    @Column(name = "product_id")
    private Long productId;

    /**
     * 产品名称
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 仓库ID
     */
    @Column(name = "warehouse_id")
    private Long warehouseId;

    /**
     * 订单主键
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 明细编号
     */
    @Column(name = "order_detail_name")
    private String orderDetailName;

    /**
     * 订货申请编号
     */
    @Column(name = "order_detail_dhsq")
    private String orderDetailDhsq;

    /**
     * 产品编号
     */
    @Column(name = "order_detail_cpbh")
    private String orderDetailCpbh;

    /**
     * 规格
     */
    @Column(name = "order_detail_gg")
    private String orderDetailGg;

    /**
     * 单位
     */
    @Column(name = "order_detail_dw")
    private String orderDetailDw;

    /**
     * 数量
     */
    @Column(name = "order_detail_sl")
    private String orderDetailSl;

    /**
     * 折扣
     */
    @Column(name = "order_detail_zk")
    private String orderDetailZk;

    /**
     * 成本价
     */
    @Column(name = "order_detail_cbj")
    private String orderDetailCbj;


    /**
     * 绑定二维码
     */
    @Column(name = "order_detail_qrcode")
    private String orderDetailQrcode;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键
     *
     * @return order_detail_id - 主键
     */
    public Long getOrderDetailId() {
        return orderDetailId;
    }

    /**
     * 设置主键
     *
     * @param orderDetailId 主键
     */
    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
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
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取公司ID
     *
     * @return company_id - 公司ID
     */
    public Long getCompanyId() {
        return companyId;
    }

    /**
     * 设置公司ID
     *
     * @param companyId 公司ID
     */
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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
     * 获取入库重量
     *
     * @return in_weight - 入库重量
     */
    public BigDecimal getInWeight() {
        return inWeight;
    }

    /**
     * 设置入库重量
     *
     * @param inWeight 入库重量
     */
    public void setInWeight(BigDecimal inWeight) {
        this.inWeight = inWeight;
    }

    /**
     * 获取单位ID
     *
     * @return unit_id - 单位ID
     */
    public Long getUnitId() {
        return unitId;
    }

    /**
     * 设置单位ID
     *
     * @param unitId 单位ID
     */
    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    /**
     * 获取单位名称
     *
     * @return unit_name - 单位名称
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * 设置单位名称
     *
     * @param unitName 单位名称
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    /**
     * 获取规格名称
     *
     * @return product_spec_name - 规格名称
     */
    public String getProductSpecName() {
        return productSpecName;
    }

    /**
     * 设置规格名称
     *
     * @param productSpecName 规格名称
     */
    public void setProductSpecName(String productSpecName) {
        this.productSpecName = productSpecName;
    }

    /**
     * 获取规格ID
     *
     * @return product_spec_id - 规格ID
     */
    public Long getProductSpecId() {
        return productSpecId;
    }

    /**
     * 设置规格ID
     *
     * @param productSpecId 规格ID
     */
    public void setProductSpecId(Long productSpecId) {
        this.productSpecId = productSpecId;
    }

    /**
     * 获取产品ID
     *
     * @return product_id - 产品ID
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * 设置产品ID
     *
     * @param productId 产品ID
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * 获取产品名称
     *
     * @return product_name - 产品名称
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 设置产品名称
     *
     * @param productName 产品名称
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 获取仓库ID
     *
     * @return warehouse_id - 仓库ID
     */
    public Long getWarehouseId() {
        return warehouseId;
    }

    /**
     * 设置仓库ID
     *
     * @param warehouseId 仓库ID
     */
    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    /**
     * 获取订单主键
     *
     * @return order_id - 订单主键
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 设置订单主键
     *
     * @param orderId 订单主键
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取明细编号
     *
     * @return order_detail_name - 明细编号
     */
    public String getOrderDetailName() {
        return orderDetailName;
    }

    /**
     * 设置明细编号
     *
     * @param orderDetailName 明细编号
     */
    public void setOrderDetailName(String orderDetailName) {
        this.orderDetailName = orderDetailName;
    }

    /**
     * 获取订货申请编号
     *
     * @return order_detail_dhsq - 订货申请编号
     */
    public String getOrderDetailDhsq() {
        return orderDetailDhsq;
    }

    /**
     * 设置订货申请编号
     *
     * @param orderDetailDhsq 订货申请编号
     */
    public void setOrderDetailDhsq(String orderDetailDhsq) {
        this.orderDetailDhsq = orderDetailDhsq;
    }

    /**
     * 获取产品编号
     *
     * @return order_detail_cpbh - 产品编号
     */
    public String getOrderDetailCpbh() {
        return orderDetailCpbh;
    }

    /**
     * 设置产品编号
     *
     * @param orderDetailCpbh 产品编号
     */
    public void setOrderDetailCpbh(String orderDetailCpbh) {
        this.orderDetailCpbh = orderDetailCpbh;
    }

    /**
     * 获取规格
     *
     * @return order_detail_gg - 规格
     */
    public String getOrderDetailGg() {
        return orderDetailGg;
    }

    /**
     * 设置规格
     *
     * @param orderDetailGg 规格
     */
    public void setOrderDetailGg(String orderDetailGg) {
        this.orderDetailGg = orderDetailGg;
    }

    /**
     * 获取单位
     *
     * @return order_detail_dw - 单位
     */
    public String getOrderDetailDw() {
        return orderDetailDw;
    }

    /**
     * 设置单位
     *
     * @param orderDetailDw 单位
     */
    public void setOrderDetailDw(String orderDetailDw) {
        this.orderDetailDw = orderDetailDw;
    }

    /**
     * 获取数量
     *
     * @return order_detail_sl - 数量
     */
    public String getOrderDetailSl() {
        return orderDetailSl;
    }

    /**
     * 设置数量
     *
     * @param orderDetailSl 数量
     */
    public void setOrderDetailSl(String orderDetailSl) {
        this.orderDetailSl = orderDetailSl;
    }

    /**
     * 获取折扣
     *
     * @return order_detail_zk - 折扣
     */
    public String getOrderDetailZk() {
        return orderDetailZk;
    }

    /**
     * 设置折扣
     *
     * @param orderDetailZk 折扣
     */
    public void setOrderDetailZk(String orderDetailZk) {
        this.orderDetailZk = orderDetailZk;
    }

    /**
     * 获取成本价
     *
     * @return order_detail_cbj - 成本价
     */
    public String getOrderDetailCbj() {
        return orderDetailCbj;
    }

    /**
     * 设置成本价
     *
     * @param orderDetailCbj 成本价
     */
    public void setOrderDetailCbj(String orderDetailCbj) {
        this.orderDetailCbj = orderDetailCbj;
    }

    public String getOrderDetailQrcode() {
        return orderDetailQrcode;
    }

    public void setOrderDetailQrcode(String orderDetailQrcode) {
        this.orderDetailQrcode = orderDetailQrcode;
    }
}