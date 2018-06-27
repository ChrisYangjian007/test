package com.dalian.sea.controller;

import com.dalian.sea.json.JqGridParam;
import com.dalian.sea.parameter.PZsQrCode;
import com.dalian.sea.parameter.PZsQrCodeUse;
import com.dalian.sea.service.ZsQrCodeUseService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * ZsQrCodeUseController
 *
 * @author TONE
 * @date 2018/3/19.
 */
@Slf4j
@Controller
@RequestMapping(value = "/qrCodeUse")
public class ZsQrCodeUseController extends LayoutRazor{
    @Autowired
    private ZsQrCodeUseService qrCodeUseService;



    /**
     * 二维码编码明细
     * @param request
     * @param qrCodeId
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/GridJson.json")
    @ResponseBody
    public Object GridJson(HttpServletRequest request, Long qrCodeId, Integer page, Integer rows) {
        List<PZsQrCodeUse> qrCodeUseList = qrCodeUseService.getQrCodeUseByQrCodeId(qrCodeId,page,rows);
        PageInfo<PZsQrCodeUse> pageInfo = new PageInfo<>(qrCodeUseList);
        return new JqGridParam(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getPages(),qrCodeUseList);
    }




}
