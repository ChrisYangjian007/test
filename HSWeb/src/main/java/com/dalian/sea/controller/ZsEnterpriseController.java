package com.dalian.sea.controller;

import com.alibaba.fastjson.JSON;
import com.dalian.sea.ClientIP;
import com.dalian.sea.FileUpload.OSSClientUtil;
import com.dalian.sea.config.shiro.ShiroUser;
import com.dalian.sea.json.ImageJson;
import com.dalian.sea.json.JqGridParam;
import com.dalian.sea.json.Json;
import com.dalian.sea.model.SysSysLog;
import com.dalian.sea.model.ZsEnterprise;
import com.dalian.sea.parameter.PZsEnterprise;
import com.dalian.sea.service.BaUserService;
import com.dalian.sea.service.SysSysLogService;
import com.dalian.sea.service.ZsEnterpriseService;
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
import java.util.*;

/**
 * Created by Administrator on 2018/2/5.
 */
@Slf4j
@Controller
@RequestMapping(value = "/enterprise")
public class ZsEnterpriseController extends LayoutRazor{
    @Autowired
    private ZsEnterpriseService zsEnterpriseService;
    @Autowired
    private BaUserService baUserService;
    @Autowired
    private SysSysLogService sysSysLogService;
    @Autowired
    private OSSClientUtil ossClientUtil;
    /**
     * 供应商管理
     * @return
     */
    @RequestMapping(value = "/enterpriseManage.htm")
    public String enterpriseManage(HttpServletRequest request){
        return freeMarkerIndexResult("enterprise/enterpriseManage.ftl",request);
    }
    /**
     * 加载表格
     * @return
     */
    @RequestMapping(value = "/GridJson.json")
    @ResponseBody
    public Object GridJson(ZsEnterprise zsEnterprise, Integer page , Integer rows){
        List<PZsEnterprise> zsEnterpriseList = zsEnterpriseService.getEnterpriseForGrid(zsEnterprise,page,rows);
        PageInfo<PZsEnterprise> pageInfo = new PageInfo<>(zsEnterpriseList);
        return new JqGridParam(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getPages(),zsEnterpriseList);
    }

    @RequestMapping(value = "select.json")
    @ResponseBody
    public Object Select(ZsEnterprise zsEnterprise,String productNo, Integer page , Integer rows){
        zsEnterprise.setCName(productNo);
        List<ZsEnterprise> zsEnterpriseByName = zsEnterpriseService.getZsEnterpriseByName(zsEnterprise,page,rows);
        PageInfo<ZsEnterprise> pageInfo = new PageInfo<>(zsEnterpriseByName);
        return new JqGridParam(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getPages(),zsEnterpriseByName);
    }
    /**
     * 添加
     * @return
     */
    @RequestMapping(value = "/addenterpriseModal.htm")
    public String addWarehouseModal(HttpServletRequest request,Long resourceId){
        request.setAttribute("resourceId", resourceId);
        return "enterprise/addenterpriseModal";
    }
    /**
     * 添加
     * @return
     */
    @RequestMapping(value="/addZsEnterprise.json")
    @ResponseBody
    public Json addZsEnterprise(HttpServletRequest request, ZsEnterprise zsEnterprise, MultipartFile licenseImage1, MultipartFile licenseImage2, MultipartFile licenseImage3, MultipartFile licenseImage4, MultipartFile licenseImage5, Long resourceId ) {
        Json json = new Json();
        try {
            if(null!=licenseImage1){
                String name = ossClientUtil.uploadImg2Oss(licenseImage1);
                if (null == name) {
                    json.setMsg("图片上传失败");
                    return json;
                }else if("上传图片大小不能超过2M！".equals(name)){
                    json.setMsg("上传图片大小不能超过2M！");
                    return json;
                }else {
                    zsEnterprise.setBusinessLicenseImage(ossClientUtil.getImgUrl(name));
                }
            }
            if(null!=licenseImage2){
                String name = ossClientUtil.uploadImg2Oss(licenseImage2);
                if (null == name) {
                    json.setMsg("图片上传失败");
                    return json;
                }else if("上传图片大小不能超过2M！".equals(name)){
                    json.setMsg("上传图片大小不能超过2M！");
                    return json;
                }else {
                    zsEnterprise.setProductionLicenseImage(ossClientUtil.getImgUrl(name));
                }
            }
            List<ImageJson> imageJsonList = new ArrayList<>();
            if(null!=licenseImage3){
                String name = ossClientUtil.uploadImg2Oss(licenseImage3);
                if (null == name) {
                    json.setMsg("图片上传失败");
                    return json;
                }else if("上传图片大小不能超过2M！".equals(name)){
                    json.setMsg("上传图片大小不能超过2M！");
                    return json;
                }else {
                    ImageJson imageJson = new ImageJson();
                    imageJson.setImageName(name);
                    imageJson.setImageUrl(ossClientUtil.getImgUrl(name));
                    imageJsonList.add(imageJson);
                }
            }else {
                ImageJson imageJson = new ImageJson();
                imageJson.setImageName("");
                imageJson.setImageUrl("");
                imageJsonList.add(imageJson);
            }
            if(null!=licenseImage4){
                String name = ossClientUtil.uploadImg2Oss(licenseImage4);
                if (null == name) {
                    json.setMsg("图片上传失败");
                    return json;
                }else if("上传图片大小不能超过2M！".equals(name)){
                    json.setMsg("上传图片大小不能超过2M！");
                    return json;
                }else {
                    ImageJson imageJson = new ImageJson();
                    imageJson.setImageName(name);
                    imageJson.setImageUrl(ossClientUtil.getImgUrl(name));
                    imageJsonList.add(imageJson);
                }
            }else {
                ImageJson imageJson = new ImageJson();
                imageJson.setImageName("");
                imageJson.setImageUrl("");
                imageJsonList.add(imageJson);
            }
            if(null!=licenseImage5){
                String name = ossClientUtil.uploadImg2Oss(licenseImage5);
                if (null == name) {
                    json.setMsg("图片上传失败");
                    return json;
                }else if("上传图片大小不能超过2M！".equals(name)){
                    json.setMsg("上传图片大小不能超过2M！");
                    return json;
                }else {
                    ImageJson imageJson = new ImageJson();
                    imageJson.setImageName(name);
                    imageJson.setImageUrl(ossClientUtil.getImgUrl(name));
                    imageJsonList.add(imageJson);
                }
            }else {
                ImageJson imageJson = new ImageJson();
                imageJson.setImageName("");
                imageJson.setImageUrl("");
                imageJsonList.add(imageJson);
            }
            String otherImage = JSON.toJSONString(imageJsonList);
            zsEnterprise.setOtherLicenseImage(otherImage);
            Subject user = SecurityUtils.getSubject();
            ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
            zsEnterprise.setCreateUserId(shiroUser.getId());
            if (zsEnterpriseService.addZsEnterprise(zsEnterprise)) {
                SysSysLog sysLog = new SysSysLog();
                String ip = ClientIP.getClientIP(request);
                sysLog.setIpAddress(ip);
                sysLog.setCName("添加供应商");
                sysLog.setRemark("添加("+zsEnterprise.getCName()+")供应商");
                sysLog.setResourceId(resourceId);
                sysLog.setCreatedUserId(shiroUser.getId());
                sysSysLogService.addSysSysLog(sysLog);
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
    /**
     * 获取id回显修改
     * @return
     */
    @RequestMapping(value="updateEnterpriseModal.htm")
    public String updateEnterpriseById(HttpServletRequest request,Long id,Long resourceId){
        request.setAttribute("resourceId", resourceId);
        PZsEnterprise zsEnterprise = zsEnterpriseService.getPEnterPriseById(id);
        request.setAttribute("zsEnterprise",zsEnterprise);
        return "enterprise/updateenterpriseModal";
    }
    /**
     * 修改
     * @return
     */
    @RequestMapping(value="/updateZsEnterprise.json")
    @ResponseBody
    public Json updateZsEnterprise(HttpServletRequest request,ZsEnterprise zsEnterprise,
                                   Integer updLicenseImageId1, MultipartFile updLicenseImage1,  Integer updLicenseImageId2, MultipartFile updLicenseImage2, Integer updLicenseImageId3, MultipartFile updLicenseImage3,
                                   Integer updLicenseImageId4, MultipartFile updLicenseImage4, Integer updLicenseImageId5, MultipartFile updLicenseImage5,Long resourceId) {
        Json json = new Json();
        try {
            PZsEnterprise pZsEnterprise = zsEnterpriseService.getPEnterPriseById(zsEnterprise.getEnterpriseId());

            if(2==updLicenseImageId1){
                if(null!=updLicenseImage1){
                    String name = ossClientUtil.uploadImg2Oss(updLicenseImage1);
                    if (null == name) {
                        json.setMsg("图片上传失败");
                        return json;
                    }else if("上传图片大小不能超过2M！".equals(name)){
                        json.setMsg("上传图片大小不能超过2M！");
                        return json;
                    }else {
                        zsEnterprise.setBusinessLicenseImage(ossClientUtil.getImgUrl(name));
                    }
                }else {
                    zsEnterprise.setBusinessLicenseImage("");
                }
            }else {
                zsEnterprise.setBusinessLicenseImage(pZsEnterprise.getBusinessLicenseImage());
            }

            if(2==updLicenseImageId2){
                if(null!=updLicenseImage2){
                    String name = ossClientUtil.uploadImg2Oss(updLicenseImage2);
                    if (null == name) {
                        json.setMsg("图片上传失败");
                        return json;
                    }else if("上传图片大小不能超过2M！".equals(name)){
                        json.setMsg("上传图片大小不能超过2M！");
                        return json;
                    }else {
                        zsEnterprise.setProductionLicenseImage(ossClientUtil.getImgUrl(name));
                    }
                }else {
                    zsEnterprise.setProductionLicenseImage("");
                }
            }else {
                zsEnterprise.setProductionLicenseImage(pZsEnterprise.getProductionLicenseImage());
            }

            List<ImageJson> imageJsonList = new ArrayList<>();
            if(2==updLicenseImageId3){
                if(null!=updLicenseImage3){
                    String name = ossClientUtil.uploadImg2Oss(updLicenseImage3);
                    if (null == name) {
                        json.setMsg("图片上传失败");
                        return json;
                    }else if("上传图片大小不能超过2M！".equals(name)){
                        json.setMsg("上传图片大小不能超过2M！");
                        return json;
                    }else {
                        ImageJson imageJson = new ImageJson();
                        imageJson.setImageName(name);
                        imageJson.setImageUrl(ossClientUtil.getImgUrl(name));
                        imageJsonList.add(imageJson);
                    }
                }else {
                    ImageJson imageJson = new ImageJson();
                    imageJson.setImageName("");
                    imageJson.setImageUrl("");
                    imageJsonList.add(imageJson);
                }
            }else {
                imageJsonList.add(pZsEnterprise.getOtherLicenseImageList().get(0));
            }

            if(2==updLicenseImageId4){
                if(null!=updLicenseImage4){
                    String name = ossClientUtil.uploadImg2Oss(updLicenseImage4);
                    if (null == name) {
                        json.setMsg("图片上传失败");
                        return json;
                    }else if("上传图片大小不能超过2M！".equals(name)){
                        json.setMsg("上传图片大小不能超过2M！");
                        return json;
                    }else {
                        ImageJson imageJson = new ImageJson();
                        imageJson.setImageName(name);
                        imageJson.setImageUrl(ossClientUtil.getImgUrl(name));
                        imageJsonList.add(imageJson);
                    }
                }else {
                    ImageJson imageJson = new ImageJson();
                    imageJson.setImageName("");
                    imageJson.setImageUrl("");
                    imageJsonList.add(imageJson);
                }
            }else {
                imageJsonList.add(pZsEnterprise.getOtherLicenseImageList().get(1));
            }

            if(2==updLicenseImageId5){
                if(null!=updLicenseImage5){
                    String name = ossClientUtil.uploadImg2Oss(updLicenseImage5);
                    if (null == name) {
                        json.setMsg("图片上传失败");
                        return json;
                    }else if("上传图片大小不能超过2M！".equals(name)){
                        json.setMsg("上传图片大小不能超过2M！");
                        return json;
                    }else {
                        ImageJson imageJson = new ImageJson();
                        imageJson.setImageName(name);
                        imageJson.setImageUrl(ossClientUtil.getImgUrl(name));
                        imageJsonList.add(imageJson);
                    }
                }else {
                    ImageJson imageJson = new ImageJson();
                    imageJson.setImageName("");
                    imageJson.setImageUrl("");
                    imageJsonList.add(imageJson);
                }
            }else {
                imageJsonList.add(pZsEnterprise.getOtherLicenseImageList().get(2));
            }
            if(3>imageJsonList.size()){
                for(int i= imageJsonList.size(); i < 3; i++){
                    ImageJson imageJson = new ImageJson();
                    imageJson.setImageName("");
                    imageJson.setImageUrl("");
                    imageJsonList.add(imageJson);
                }
            }
            zsEnterprise.setOtherLicenseImage(JSON.toJSONString(imageJsonList));
            Subject user = SecurityUtils.getSubject();
            ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
            zsEnterprise.setUpdateUserId(shiroUser.getId());
            if (zsEnterpriseService.updateZsEnterprise(zsEnterprise)) {
                SysSysLog sysLog = new SysSysLog();
                String ip = ClientIP.getClientIP(request);
                sysLog.setIpAddress(ip);
                sysLog.setCName("修改供应商");
                sysLog.setRemark("修改("+zsEnterprise.getCName()+")供应商");
                sysLog.setResourceId(resourceId);
                sysLog.setCreatedUserId(shiroUser.getId());
                sysSysLogService.addSysSysLog(sysLog);
                json.setMsg("修改成功");
                json.setSuccess(true);
            } else {
                json.setMsg("修改失败");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }
    /**
     * 详细
     * @return
     */
    @RequestMapping(value="enterpriseDetailModal.htm")
    public String EnterpriseById(HttpServletRequest request,Long id){
        ZsEnterprise zsEnterprise = zsEnterpriseService.getzsEnterpriseById(id);
        request.setAttribute("zsEnterprise",zsEnterprise);

        return "enterprise/enterpriseDetailModal";
    }
    /**
     * 删除
     * @return
     */
    @RequestMapping(value="/deleteEnterprise.json")
    @ResponseBody
    public Json DelectZsEnterprise(HttpServletRequest request,ZsEnterprise zsEnterprise,Long resourceId) {
        Json json = new Json();
        try {
            Subject user = SecurityUtils.getSubject();
            ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
            zsEnterprise.setCreateUserId(shiroUser.getId());
            ZsEnterprise zsEnterprise1 = zsEnterpriseService.getzsEnterpriseById(zsEnterprise.getEnterpriseId());
            if (zsEnterpriseService.deleteZsEnterpriseById(zsEnterprise)) {
                SysSysLog sysLog = new SysSysLog();
                String ip = ClientIP.getClientIP(request);
                sysLog.setIpAddress(ip);
                sysLog.setCName("删除供应商");
                sysLog.setRemark("删除("+zsEnterprise1.getCName()+")供应商");
                sysLog.setResourceId(resourceId);
                sysLog.setCreatedUserId(shiroUser.getId());
                sysSysLogService.addSysSysLog(sysLog);
                json.setMsg("删除成功");
                json.setSuccess(true);
            } else {
                json.setMsg("删除失败");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 获取供应商列表
     * */
    @RequestMapping(value="/getEnterpriseList.json")
    @ResponseBody
    public Json getEnterpriseList(HttpServletRequest request) {
        Json json = new Json();
        try {
            List<ZsEnterprise> zsEnterpriseList = zsEnterpriseService.getEnterprise();
            if (null!=zsEnterpriseList&&!zsEnterpriseList.isEmpty()){
                json.setMsg("查询成功");
                json.setSuccess(true);
                json.setObj(zsEnterpriseList);
            }else {
                json.setMsg("查询暂无");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 查看图片
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/enterpriseImageModal.htm")
    public String enterpriseImage(HttpServletRequest request,Long id){
        ZsEnterprise zsEnterprise = zsEnterpriseService.PEnterPriseForImageById(id);
        request.setAttribute("zsEnterprise",zsEnterprise);
        return "enterprise/enterpriseImageModal";
    }

}
