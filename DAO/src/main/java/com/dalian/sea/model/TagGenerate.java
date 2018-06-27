package com.dalian.sea.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "tag_generate")
public class TagGenerate implements Serializable {
    /**
     * 产品ID
     */
    @Id
    @Column(name = "generate_id")
    private Long generateId;

    /**
     * 总码段
     */
    @Column(name = "all_code")
    private String allCode;

    /**
     * 已分配码段
     */
    @Column(name = "alloted_code")
    private String allotedCode;

    /**
     * 未分配码段
     */
    @Column(name = "un_allot_code")
    private String unAllotCode;

    /**
     * 损码码段
     */
    @Column(name = "loss_code")
    private String lossCode;

    /**
     * 标签印刷批次号
     */
    @Column(name = "print_batch")
    private String printBatch;

    /**
     * 标签总数
     */
    @Column(name = "tag_count")
    private Integer tagCount;

    /**
     * 已使用量
     */
    @Column(name = "already_count")
    private Integer alreadyCount;

    /**
     * 损码数量
     */
    @Column(name = "loss_count")
    private Integer lossCount;

    /**
     * 印刷URL前缀
     */
    private String prefix;

    /**
     * 规则ID
     */
    @Column(name = "rule_id")
    private Long ruleId;

    /**
     * 规则名称
     */
    @Column(name = "rule_name")
    private String ruleName;

    /**
     * 规格ID
     */
    @Column(name = "tag_spec_id")
    private Long tagSpecId;

    /**
     * 规格名称
     */
    @Column(name = "tag_spec_name")
    private String tagSpecName;

    /**
     * 是否已完成所有分配
     */
    @Column(name = "is_finish")
    private Byte isFinish;

    /**
     * 是否印刷
     */
    @Column(name = "is_print")
    private Byte isPrint;

    /**
     * 是否可分配
     */
    @Column(name = "is_allot")
    private Byte isAllot;

    /**
     * 开始编号
     */
    @Column(name = "start_no")
    private Integer startNo;

    /**
     * 结束编号
     */
    @Column(name = "end_no")
    private Integer endNo;

    /**
     * 当前ID
     */
    @Column(name = "current_no")
    private Long currentNo;

    /**
     * 状态
0-已读1-未读
     */
    private Byte status;

    /**
     * 是否生成导出文件
     */
    @Column(name = "is_create")
    private Byte isCreate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 标签类型(1-公司码,2-喷码)
     */
    @Column(name = "tag_type")
    private Integer tagType;

    /**
     * 农药登记证
     */
    @Column(name = "pesticide_license")
    private String pesticideLicense;

    /**
     * 规格码
     */
    @Column(name = "tag_spec_no")
    private String tagSpecNo;

    /**
     * 实际库存量
     */
    @Column(name = "remain_count")
    private Integer remainCount;

    /**
     * 农药名
     */
    @Column(name = "c_name")
    private String cName;

    /**
     * 后22位总码段
     */
    @Column(name = "last_no")
    private String lastNo;

    /**
     * 含量
     */
    private String measure;

    /**
     * 剂型
     */
    @Column(name = "dosage_type_name")
    private String dosageTypeName;

    /**
     * 下载文件路径
     */
    @Column(name = "file_name")
    private String fileName;

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
     * 创建姓名
     */
    @Column(name = "create_user_name")
    private String createUserName;

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
     * 排序
     */
    @Column(name = "list_index")
    private Integer listIndex;

    /**
     * 公司ID
     */
    @Column(name = "company_id")
    private Long companyId;
    /**
     * 导入量
     */
    @Column(name = "import_count")
    private Integer importCount;
    /**
     * 导入时间
     */
    @Column(name = "import_date")
    private Date importDate;
    private static final long serialVersionUID = 1L;

    /**
     * 获取产品ID
     *
     * @return generate_id - 产品ID
     */
    public Long getGenerateId() {
        return generateId;
    }

    /**
     * 设置产品ID
     *
     * @param generateId 产品ID
     */
    public void setGenerateId(Long generateId) {
        this.generateId = generateId;
    }

    /**
     * 获取总码段
     *
     * @return all_code - 总码段
     */
    public String getAllCode() {
        return allCode;
    }

    /**
     * 设置总码段
     *
     * @param allCode 总码段
     */
    public void setAllCode(String allCode) {
        this.allCode = allCode;
    }

    /**
     * 获取已分配码段
     *
     * @return alloted_code - 已分配码段
     */
    public String getAllotedCode() {
        return allotedCode;
    }

    /**
     * 设置已分配码段
     *
     * @param allotedCode 已分配码段
     */
    public void setAllotedCode(String allotedCode) {
        this.allotedCode = allotedCode;
    }

    /**
     * 获取未分配码段
     *
     * @return un_allot_code - 未分配码段
     */
    public String getUnAllotCode() {
        return unAllotCode;
    }

    /**
     * 设置未分配码段
     *
     * @param unAllotCode 未分配码段
     */
    public void setUnAllotCode(String unAllotCode) {
        this.unAllotCode = unAllotCode;
    }

    /**
     * 获取损码码段
     *
     * @return loss_code - 损码码段
     */
    public String getLossCode() {
        return lossCode;
    }

    /**
     * 设置损码码段
     *
     * @param lossCode 损码码段
     */
    public void setLossCode(String lossCode) {
        this.lossCode = lossCode;
    }

    /**
     * 获取标签印刷批次号
     *
     * @return print_batch - 标签印刷批次号
     */
    public String getPrintBatch() {
        return printBatch;
    }

    /**
     * 设置标签印刷批次号
     *
     * @param printBatch 标签印刷批次号
     */
    public void setPrintBatch(String printBatch) {
        this.printBatch = printBatch;
    }

    /**
     * 获取标签总数
     *
     * @return tag_count - 标签总数
     */
    public Integer getTagCount() {
        return tagCount;
    }

    /**
     * 设置标签总数
     *
     * @param tagCount 标签总数
     */
    public void setTagCount(Integer tagCount) {
        this.tagCount = tagCount;
    }

    /**
     * 获取已使用量
     *
     * @return already_count - 已使用量
     */
    public Integer getAlreadyCount() {
        return alreadyCount;
    }

    /**
     * 设置已使用量
     *
     * @param alreadyCount 已使用量
     */
    public void setAlreadyCount(Integer alreadyCount) {
        this.alreadyCount = alreadyCount;
    }

    /**
     * 获取损码数量
     *
     * @return loss_count - 损码数量
     */
    public Integer getLossCount() {
        return lossCount;
    }

    /**
     * 设置损码数量
     *
     * @param lossCount 损码数量
     */
    public void setLossCount(Integer lossCount) {
        this.lossCount = lossCount;
    }

    /**
     * 获取印刷URL前缀
     *
     * @return prefix - 印刷URL前缀
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * 设置印刷URL前缀
     *
     * @param prefix 印刷URL前缀
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * 获取规则ID
     *
     * @return rule_id - 规则ID
     */
    public Long getRuleId() {
        return ruleId;
    }

    /**
     * 设置规则ID
     *
     * @param ruleId 规则ID
     */
    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    /**
     * 获取规则名称
     *
     * @return rule_name - 规则名称
     */
    public String getRuleName() {
        return ruleName;
    }

    /**
     * 设置规则名称
     *
     * @param ruleName 规则名称
     */
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    /**
     * 获取规格ID
     *
     * @return tag_spec_id - 规格ID
     */
    public Long getTagSpecId() {
        return tagSpecId;
    }

    /**
     * 设置规格ID
     *
     * @param tagSpecId 规格ID
     */
    public void setTagSpecId(Long tagSpecId) {
        this.tagSpecId = tagSpecId;
    }

    /**
     * 获取规格名称
     *
     * @return tag_spec_name - 规格名称
     */
    public String getTagSpecName() {
        return tagSpecName;
    }

    /**
     * 设置规格名称
     *
     * @param tagSpecName 规格名称
     */
    public void setTagSpecName(String tagSpecName) {
        this.tagSpecName = tagSpecName;
    }

    /**
     * 获取是否已完成所有分配
     *
     * @return is_finish - 是否已完成所有分配
     */
    public Byte getIsFinish() {
        return isFinish;
    }

    /**
     * 设置是否已完成所有分配
     *
     * @param isFinish 是否已完成所有分配
     */
    public void setIsFinish(Byte isFinish) {
        this.isFinish = isFinish;
    }

    /**
     * 获取是否印刷
     *
     * @return is_print - 是否印刷
     */
    public Byte getIsPrint() {
        return isPrint;
    }

    /**
     * 设置是否印刷
     *
     * @param isPrint 是否印刷
     */
    public void setIsPrint(Byte isPrint) {
        this.isPrint = isPrint;
    }

    /**
     * 获取是否可分配
     *
     * @return is_allot - 是否可分配
     */
    public Byte getIsAllot() {
        return isAllot;
    }

    /**
     * 设置是否可分配
     *
     * @param isAllot 是否可分配
     */
    public void setIsAllot(Byte isAllot) {
        this.isAllot = isAllot;
    }

    /**
     * 获取开始编号
     *
     * @return start_no - 开始编号
     */
    public Integer getStartNo() {
        return startNo;
    }

    /**
     * 设置开始编号
     *
     * @param startNo 开始编号
     */
    public void setStartNo(Integer startNo) {
        this.startNo = startNo;
    }

    /**
     * 获取结束编号
     *
     * @return end_no - 结束编号
     */
    public Integer getEndNo() {
        return endNo;
    }

    /**
     * 设置结束编号
     *
     * @param endNo 结束编号
     */
    public void setEndNo(Integer endNo) {
        this.endNo = endNo;
    }

    /**
     * 获取当前ID
     *
     * @return current_no - 当前ID
     */
    public Long getCurrentNo() {
        return currentNo;
    }

    /**
     * 设置当前ID
     *
     * @param currentNo 当前ID
     */
    public void setCurrentNo(Long currentNo) {
        this.currentNo = currentNo;
    }

    /**
     * 获取状态
0-已读1-未读
     *
     * @return status - 状态
0-已读1-未读
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置状态
0-已读1-未读
     *
     * @param status 状态
0-已读1-未读
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取是否生成导出文件
     *
     * @return is_create - 是否生成导出文件
     */
    public Byte getIsCreate() {
        return isCreate;
    }

    /**
     * 设置是否生成导出文件
     *
     * @param isCreate 是否生成导出文件
     */
    public void setIsCreate(Byte isCreate) {
        this.isCreate = isCreate;
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
     * 获取标签类型(1-公司码,2-喷码)
     *
     * @return tag_type - 标签类型(1-公司码,2-喷码)
     */
    public Integer getTagType() {
        return tagType;
    }

    /**
     * 设置标签类型(1-公司码,2-喷码)
     *
     * @param tagType 标签类型(1-公司码,2-喷码)
     */
    public void setTagType(Integer tagType) {
        this.tagType = tagType;
    }

    /**
     * 获取农药登记证
     *
     * @return pesticide_license - 农药登记证
     */
    public String getPesticideLicense() {
        return pesticideLicense;
    }

    /**
     * 设置农药登记证
     *
     * @param pesticideLicense 农药登记证
     */
    public void setPesticideLicense(String pesticideLicense) {
        this.pesticideLicense = pesticideLicense;
    }

    /**
     * 获取规格码
     *
     * @return tag_spec_no - 规格码
     */
    public String getTagSpecNo() {
        return tagSpecNo;
    }

    /**
     * 设置规格码
     *
     * @param tagSpecNo 规格码
     */
    public void setTagSpecNo(String tagSpecNo) {
        this.tagSpecNo = tagSpecNo;
    }

    /**
     * 获取实际库存量
     *
     * @return remain_count - 实际库存量
     */
    public Integer getRemainCount() {
        return remainCount;
    }

    /**
     * 设置实际库存量
     *
     * @param remainCount 实际库存量
     */
    public void setRemainCount(Integer remainCount) {
        this.remainCount = remainCount;
    }

    /**
     * 获取农药名
     *
     * @return c_name - 农药名
     */
    public String getcName() {
        return cName;
    }

    /**
     * 设置农药名
     *
     * @param cName 农药名
     */
    public void setcName(String cName) {
        this.cName = cName;
    }

    /**
     * 获取后22位总码段
     *
     * @return last_no - 后22位总码段
     */
    public String getLastNo() {
        return lastNo;
    }

    /**
     * 设置后22位总码段
     *
     * @param lastNo 后22位总码段
     */
    public void setLastNo(String lastNo) {
        this.lastNo = lastNo;
    }

    /**
     * 获取含量
     *
     * @return measure - 含量
     */
    public String getMeasure() {
        return measure;
    }

    /**
     * 设置含量
     *
     * @param measure 含量
     */
    public void setMeasure(String measure) {
        this.measure = measure;
    }

    /**
     * 获取剂型
     *
     * @return dosage_type_name - 剂型
     */
    public String getDosageTypeName() {
        return dosageTypeName;
    }

    /**
     * 设置剂型
     *
     * @param dosageTypeName 剂型
     */
    public void setDosageTypeName(String dosageTypeName) {
        this.dosageTypeName = dosageTypeName;
    }

    /**
     * 获取下载文件路径
     *
     * @return file_name - 下载文件路径
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置下载文件路径
     *
     * @param fileName 下载文件路径
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
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
     * 获取创建姓名
     *
     * @return create_user_name - 创建姓名
     */
    public String getCreateUserName() {
        return createUserName;
    }

    /**
     * 设置创建姓名
     *
     * @param createUserName 创建姓名
     */
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
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


    public Integer getImportCount() {
        return importCount;
    }

    public void setImportCount(Integer importCount) {
        this.importCount = importCount;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }
}