package com.dalian.sea.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalian.sea.FileUpload.OSSClientUtil;
import com.dalian.sea.config.shiro.ShiroUser;
import com.dalian.sea.json.ImageJson;
import com.dalian.sea.json.JqGridParam;
import com.dalian.sea.json.Json;
import com.dalian.sea.model.PuReceiveTest;
import com.dalian.sea.parameter.PPuReceiveTestMapper;
import com.dalian.sea.parameter.PXqKitchen;
import com.dalian.sea.service.PuReceiveTestService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2018/2/7.
 */
@Slf4j
@Controller
@RequestMapping(value = "/receivetest")
public class PuReceiveTestController extends LayoutRazor {
    @Autowired
    private PuReceiveTestService puReceiveTestService;

    @Autowired
    private OSSClientUtil ossClientUtil;

    /**
     * 检验室/receivetest/receivetestManage.htm
     *
     * @return
     */
    @RequestMapping(value = "/receivetestManage.htm")
    public String enterpriseManage(HttpServletRequest request) {
        return freeMarkerIndexResult("receivetest/receivetestManage.ftl", request);
    }

    /**
     * 展示列表
     *
     * @param pPuReceiveTestMapper
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "GridJson.json")
    @ResponseBody
    public Object GetGrid(PPuReceiveTestMapper pPuReceiveTestMapper, Integer page, Integer rows) {
        List<PPuReceiveTestMapper> puReceiveTestList = puReceiveTestService.puReceiveTestList(pPuReceiveTestMapper, page, rows);
        PageInfo<PPuReceiveTestMapper> pageInfo = new PageInfo<>(puReceiveTestList);
        return new JqGridParam(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), puReceiveTestList);
    }

    /**
     * 上传报告
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/uploadingReportsModal.htm")
    public String uploadingReportsModal(HttpServletRequest request, Long id) {
        PuReceiveTest puReceiveTest = puReceiveTestService.uploadingReports(id);
        request.setAttribute("puReceiveTest", puReceiveTest);
        List<PPuReceiveTestMapper> puReceiveTests = puReceiveTestService.dataDictionary();
        request.setAttribute("puReceiveTests", puReceiveTests);
        return "receivetest/uploadingReportsModal";
    }

    /***
     * 查看检验报告
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/checkReportModal.htm")
    public String checkReport(HttpServletRequest request, Long id) {
        PuReceiveTest puReceiveTest = puReceiveTestService.uploadingReports(id);
        request.setAttribute("puReceiveTest", puReceiveTest);
        return "receivetest/checkReportModal";
    }

    /**
     * 数据导出
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/updateEnterpriseModal.json")
    public Object updateEnterpriseModal(HttpServletRequest request, HttpServletResponse response) {
        Json json = new Json();
        try {
            Boolean aBoolean = puReceiveTestService.updateEnterpriseModalservice(request, response);
            if (aBoolean) {
                json.setMsg("导出成功");
                json.setSuccess(true);
            } else {
                json.setMsg("导出失败");
            }
        } catch (Exception e) {
            json.setObj(e);
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 上传报告
     *
     * @param puReceiveTest
     * @return
     */
    @RequestMapping(value = "/uploadingReports.json")
    @ResponseBody
    public Json uploadingReports(
            PuReceiveTest puReceiveTest,String imageId,
            MultipartFile iamge) {
        Json json = new Json();
        try {
            String imageById = puReceiveTestService.getImageById(puReceiveTest.getReceiveDetailId());
            if(imageId!=null && imageId!=""){
            if (iamge != null) {
                String name = ossClientUtil.uploadImg2Oss(iamge);
                //由上传文件是否为空来判断删除和添加修改操作
                if (null == name) {
                    json.setMsg("图片上传失败");
                    return json;
                }else if("上传图片大小不能超过2M！".equals(name)){
                    json.setMsg("上传图片大小不能超过2M！");
                    return json;
                }else {
                    puReceiveTest.setIamges(ossClientUtil.getImgUrl(name));
                }
            } else {
                puReceiveTest.setIamges(ossClientUtil.getImgUrl(""));
            }
            }else {
                puReceiveTest.setIamges(imageById);
            }
            Subject user = SecurityUtils.getSubject();
            ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
            puReceiveTest.setUpdateUserId(shiroUser.getId());
            if (puReceiveTestService.addPuReceiveTest(puReceiveTest)) {
                json.setMsg("添加成功");
                json.setSuccess(true);
            } else {
                json.setMsg("添加失败");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }
}
