package com.dalian.sea.service;

import com.dalian.sea.parameter.PZsCompanyCulture;

import java.util.List;

/**
 * Created by Administrator on 2018/3/29.
 */
public interface ZsCompanyCultureService {

    /**
     * 通过id获取
     * @param id
     * @return
     */
    PZsCompanyCulture getPZsCompanyCultuerById(Long id);

    /**
     * 企业文化管理
     * @param page
     * @param rows
     * @return
     */
    List<PZsCompanyCulture> getPCompanyCultureList(Integer page, Integer rows);
}
