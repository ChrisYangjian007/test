package com.dalian.sea.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "zs_batch")
public class ZsBatch implements Serializable {
    @Id
    @Column(name = "batch_id")
    private Long batchId;

    /**
     * 任务编号
     */
    @Column(name = "task_code")
    private String taskCode;

    /**
     * 任务名称
     */
    @Column(name = "task_name")
    private String taskName;

    /**
     * 产品编号
     */
    @Column(name = "product_code")
    private String productCode;

    /**
     * 产品类别
     */
    @Column(name = "product_category")
    private String productCategory;

    /**
     * 产品名称
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 产品规格
     */
    @Column(name = "product_format")
    private String productFormat;

    /**
     * 产品单位
     */
    @Column(name = "product_unit")
    private String productUnit;

    /**
     * 产品重量
     */
    @Column(name = "product_weight")
    private String productWeight;

    /**
     * 产品线
     */
    @Column(name = "product_line")
    private String productLine;

    /**
     * 产品大类
     */
    @Column(name = "product_big_type")
    private String productBigType;

    /**
     * 产品小类
     */
    @Column(name = "product_small_type")
    private String productSmallType;

    /**
     * 批次编号
     */
    @Column(name = "batch_code")
    private String batchCode;

    /**
     * 批次名称
     */
    @Column(name = "batch_name")
    private String batchName;

    /**
     * 批次日期
     */
    @Column(name = "batch_date")
    private String batchDate;

    /**
     * 批次包装
     */
    @Column(name = "batch_pack")
    private String batchPack;

    @Column(name = "create_date")
    private Date createDate;

    private Byte status;

    @Column(name = "create_user_id")
    private Long createUserId;

    /**
     * 创建人姓名
     */
    @Column(name = "create_user_name")
    private String createUserName;

    /**
     * 标签数量
     */
    @Column(name = "label_number")
    private Integer labelNumber;

    /**
     * 箱码数量
     */
    @Column(name = "box_number")
    private Integer boxNumber;

    /**
     * 扫码次数
     */
    @Column(name = "scan_number")
    private Integer scanNumber;
    /**
     * 唯一编号
     */
    @Column(name = "batch_unique")
    private String batchUnique;
    private static final long serialVersionUID = 1L;

    /**
     * @return batch_id
     */
    public Long getBatchId() {
        return batchId;
    }

    /**
     * @param batchId
     */
    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    /**
     * 获取任务编号
     *
     * @return task_code - 任务编号
     */
    public String getTaskCode() {
        return taskCode;
    }

    /**
     * 设置任务编号
     *
     * @param taskCode 任务编号
     */
    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    /**
     * 获取任务名称
     *
     * @return task_name - 任务名称
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * 设置任务名称
     *
     * @param taskName 任务名称
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * 获取产品编号
     *
     * @return product_code - 产品编号
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * 设置产品编号
     *
     * @param productCode 产品编号
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * 获取产品类别
     *
     * @return product_category - 产品类别
     */
    public String getProductCategory() {
        return productCategory;
    }

    /**
     * 设置产品类别
     *
     * @param productCategory 产品类别
     */
    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
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
     * 获取产品规格
     *
     * @return product_format - 产品规格
     */
    public String getProductFormat() {
        return productFormat;
    }

    /**
     * 设置产品规格
     *
     * @param productFormat 产品规格
     */
    public void setProductFormat(String productFormat) {
        this.productFormat = productFormat;
    }

    /**
     * 获取产品单位
     *
     * @return product_unit - 产品单位
     */
    public String getProductUnit() {
        return productUnit;
    }

    /**
     * 设置产品单位
     *
     * @param productUnit 产品单位
     */
    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    /**
     * 获取产品重量
     *
     * @return product_weight - 产品重量
     */
    public String getProductWeight() {
        return productWeight;
    }

    /**
     * 设置产品重量
     *
     * @param productWeight 产品重量
     */
    public void setProductWeight(String productWeight) {
        this.productWeight = productWeight;
    }

    /**
     * 获取产品线
     *
     * @return product_line - 产品线
     */
    public String getProductLine() {
        return productLine;
    }

    /**
     * 设置产品线
     *
     * @param productLine 产品线
     */
    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    /**
     * 获取产品大类
     *
     * @return product_big_type - 产品大类
     */
    public String getProductBigType() {
        return productBigType;
    }

    /**
     * 设置产品大类
     *
     * @param productBigType 产品大类
     */
    public void setProductBigType(String productBigType) {
        this.productBigType = productBigType;
    }

    /**
     * 获取产品小类
     *
     * @return product_small_type - 产品小类
     */
    public String getProductSmallType() {
        return productSmallType;
    }

    /**
     * 设置产品小类
     *
     * @param productSmallType 产品小类
     */
    public void setProductSmallType(String productSmallType) {
        this.productSmallType = productSmallType;
    }

    /**
     * 获取批次编号
     *
     * @return batch_code - 批次编号
     */
    public String getBatchCode() {
        return batchCode;
    }

    /**
     * 设置批次编号
     *
     * @param batchCode 批次编号
     */
    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    /**
     * 获取批次名称
     *
     * @return batch_name - 批次名称
     */
    public String getBatchName() {
        return batchName;
    }

    /**
     * 设置批次名称
     *
     * @param batchName 批次名称
     */
    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    /**
     * 获取批次日期
     *
     * @return batch_date - 批次日期
     */
    public String getBatchDate() {
        return batchDate;
    }

    /**
     * 设置批次日期
     *
     * @param batchDate 批次日期
     */
    public void setBatchDate(String batchDate) {
        this.batchDate = batchDate;
    }

    /**
     * 获取批次包装
     *
     * @return batch_pack - 批次包装
     */
    public String getBatchPack() {
        return batchPack;
    }

    /**
     * 设置批次包装
     *
     * @param batchPack 批次包装
     */
    public void setBatchPack(String batchPack) {
        this.batchPack = batchPack;
    }

    /**
     * @return create_date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return status
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * @return create_user_id
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * @param createUserId
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取创建人姓名
     *
     * @return create_user_name - 创建人姓名
     */
    public String getCreateUserName() {
        return createUserName;
    }

    /**
     * 设置创建人姓名
     *
     * @param createUserName 创建人姓名
     */
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    /**
     * 获取标签数量
     *
     * @return label_number - 标签数量
     */
    public Integer getLabelNumber() {
        return labelNumber;
    }

    /**
     * 设置标签数量
     *
     * @param labelNumber 标签数量
     */
    public void setLabelNumber(Integer labelNumber) {
        this.labelNumber = labelNumber;
    }

    /**
     * 获取箱码数量
     *
     * @return box_number - 箱码数量
     */
    public Integer getBoxNumber() {
        return boxNumber;
    }

    /**
     * 设置箱码数量
     *
     * @param boxNumber 箱码数量
     */
    public void setBoxNumber(Integer boxNumber) {
        this.boxNumber = boxNumber;
    }

    /**
     * 获取扫码次数
     *
     * @return scan_number - 扫码次数
     */
    public Integer getScanNumber() {
        return scanNumber;
    }

    /**
     * 设置扫码次数
     *
     * @param scanNumber 扫码次数
     */
    public void setScanNumber(Integer scanNumber) {
        this.scanNumber = scanNumber;
    }

    public String getBatchUnique() {
        return batchUnique;
    }

    public void setBatchUnique(String batchUnique) {
        this.batchUnique = batchUnique;
    }
}