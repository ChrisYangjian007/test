package com.dalian.sea.mapper;

import com.dalian.sea.model.ZsCompanyCulture;
import com.dalian.sea.parameter.PZsCompanyCulture;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ZsCompanyCultureMapper extends Mapper<ZsCompanyCulture> {
    /***
     * 预览页面企业文化
     * @return
     */
    List<ZsCompanyCulture> getCompanyCultureList();

    /***
     * 新增企业文化
     * @return
     */
    Integer addCompanyCulture(ZsCompanyCulture companyCulture);

    /***
     * 根据id查询企业文化
     * @param companyCultureId
     * @return
     */
    ZsCompanyCulture getCompanyCultureById(Long companyCultureId);

    /***
     * 修改企业文化
     * @param companyCulture
     * @return
     */
    Integer updateCompanyCulture(ZsCompanyCulture companyCulture);

    /***
     * 删除企业文化
     * @param companyCulture
     * @return
     */
    Integer delectCompanyCulture(ZsCompanyCulture companyCulture);

    /**
     * 通过id获取
     * @param id
     * @return
     */
    PZsCompanyCulture getPZsCompanyCultuerById(Long id);

    /**
     * 企业文化管理
     * @return
     */
    List<PZsCompanyCulture> getPCompanyCultureList();
}