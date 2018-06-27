package com.dalian.sea.controller;

import com.dalian.sea.json.JqGridParam;
import com.dalian.sea.parameter.PTagGenerate;
import com.dalian.sea.parameter.PTagSweep;
import com.dalian.sea.service.TagSweepService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 * @author 杨建
 * @date 2018/3/18
 */
@Slf4j
@Controller
@RequestMapping(value = "/tagSweep")
public class TagSweepController extends LayoutRazor  {
    @Autowired
    private TagSweepService tagSweepService;

    /**
     * 加载表格
     */
    @RequestMapping("/GridJson.json")
    @ResponseBody
    public Object GridJson(PTagSweep tagSweep,Integer page, Integer rows) {
        List<PTagSweep> pTagSweeps = tagSweepService.selectTagSweepList(tagSweep, page, rows);
        PageInfo<PTagSweep> pageInfo = new PageInfo<>(pTagSweeps);
        return new JqGridParam(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), pTagSweeps);
    }
}
