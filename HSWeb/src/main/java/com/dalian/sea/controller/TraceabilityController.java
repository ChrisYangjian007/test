package com.dalian.sea.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalian.sea.ClientIP;
import com.dalian.sea.FileUpload.OSSClientUtil;
import com.dalian.sea.config.shiro.ShiroUser;
import com.dalian.sea.json.ImageJson;
import com.dalian.sea.json.JqGridParam;
import com.dalian.sea.json.Json;
import com.dalian.sea.model.*;
import com.dalian.sea.parameter.*;
import com.dalian.sea.service.*;
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
import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by Administrator on 2018/2/24.
 */
@Slf4j
@Controller
@RequestMapping(value = "/traceability")
public class TraceabilityController extends LayoutRazor{
    @Autowired
    private TraceabilityService traceabilityservice;
    @Autowired
    private OSSClientUtil ossClientUtil;
    @Autowired
    private ZsCertificateManageService zsCertificateManageService;
    @Autowired
    private SysProductTypeService sysProductTypeService;
    @Autowired
    private ZsProductionProcessService zsProductionProcessService;
    @Autowired
    private ZsProductionProcessDetailService zsProductionProcessDetailService;
    @Autowired
    private ZsCompanyCultureService zsCompanyCultureService;
    @Autowired
    private ZsCorporateNewsService zsCorporateNewsService;
    @Autowired
    private ZsTestingEquipmentService zsTestingEquipmentService;
    @Autowired
    private ZsTestingEquipmentDetailService zsTestingEquipmentDetailService;
    @Autowired
    private ZsProductionInformationService zsProductionInformationService;
    @Autowired
    private SysSysLogService sysSysLogService;
    @Autowired
    private BaDataDictionaryDetailsService baDataDictionaryDetailsService;
    @Autowired
    private ZsXqKitchenService zsXqKitchenService;
    /**
     * 追溯管理
     * @param request
     * @return
     */
    @RequestMapping(value = "/traceabilityManage.htm")
    public String newsManage(HttpServletRequest request){
        return freeMarkerIndexResult("traceability/traceabilityManage.ftl",request);
    }

    /**
     * 展示新闻列表
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "getCorporateNewsBy.json")
    @ResponseBody
    public Object getCorporateNewsBy(Integer page, Integer rows) {
        List<ZsCorporateNews> corporateNewsBy = traceabilityservice.getCorporateNewsBy(page, rows);
        PageInfo<ZsCorporateNews> pageInfo = new PageInfo<>(corporateNewsBy);
        return new JqGridParam(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), corporateNewsBy);
    }

    /***
     * 新增企业新闻
     * @return
     */
    @RequestMapping(value = "/addCorporateNewsModal.htm")
    public  String addCorporateNewsModal(HttpServletRequest request,Long resourceId){
        if(null!=resourceId){
            request.setAttribute("resourceId",resourceId);
        }
        return "traceability/addCorporateNewsModal";
    }
    /**
     * 添加新闻内容
     * @return
     */
    @RequestMapping(value="/addCorporateNews.json")
    @ResponseBody
    public Object addCorporateNews(HttpServletRequest request,ZsCorporateNews corporateNews, MultipartFile images, Long resourceId) {
        Json json = new Json();
        try {
            if(images!=null) {
                String name = ossClientUtil.uploadImg2Oss(images);
                //由上传文件是否为空来判断删除和添加修改操作
                if (null == name) {
                    json.setMsg("图片上传失败");
                    return json;
                }else if("上传图片大小不能超过2M！".equals(name)){
                    json.setMsg("上传图片大小不能超过2M！");
                    return json;
                }
                corporateNews.setImage(ossClientUtil.getImgUrl(name));
            }
                Subject user = SecurityUtils.getSubject();
                ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                corporateNews.setCreateUserId(shiroUser.getId());
                corporateNews.setCreateDate(new Date());
                corporateNews.setUpdateDate(new Date());
                if (traceabilityservice.addCorporateNews(corporateNews)) {
                    SysSysLog sysLog = new SysSysLog();
                    String ip = ClientIP.getClientIP(request);
                    sysLog.setIpAddress(ip);
                    sysLog.setCName("添加企业新闻");
                    sysLog.setRemark("添加("+corporateNews.getTitle()+")企业新闻");
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
     * 修改新闻内容
     * @return
     */
    @RequestMapping(value = "/updateCorporateNewsModal.htm")
    public  String updateCorporateNewsModal(HttpServletRequest request, Long id,Long resourceId){
        ZsCorporateNews corporateNewsById = traceabilityservice.getCorporateNewsById(id);
        request.setAttribute("resourceId",resourceId);
        request.setAttribute("corporateNewsById",corporateNewsById);
        return "traceability/updateCorporateNewsModal";
    }
    @RequestMapping(value="/updateCorporateNews.json")
    @ResponseBody
    public Object updateCorporateNews(HttpServletRequest request, ZsCorporateNews corporateNews, String updCorporateNewsImageId, MultipartFile updCorporateNewsImage, Long resourceId) {
        Json json = new Json();
        try {
            ZsCorporateNews zsCorporateNews = zsCorporateNewsService.getCorporateNewsById(corporateNews.getCorporateNewsId());
            if(null!=zsCorporateNews){
                if(null!=updCorporateNewsImageId&&!"".equals(updCorporateNewsImageId)){
                    if(updCorporateNewsImage!=null) {
                        String name = ossClientUtil.uploadImg2Oss(updCorporateNewsImage);
                        //由上传文件是否为空来判断删除和添加修改操作
                        if (null == name) {
                            json.setMsg("图片上传失败");
                            return json;
                        }else if("上传图片大小不能超过2M！".equals(name)){
                            json.setMsg("上传图片大小不能超过2M！");
                            return json;
                        }
                        corporateNews.setImage(ossClientUtil.getImgUrl(name));
                    }else {
                        corporateNews.setImage(ossClientUtil.getImgUrl(""));
                    }
                }else {
                    corporateNews.setImage(zsCorporateNews.getImage());
                }
            }else {
                json.setMsg("传入的数据异常，无法修改");
                return json;
            }
                Subject user = SecurityUtils.getSubject();
                ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                corporateNews.setUpdateUserId(shiroUser.getId());
                if (traceabilityservice.updateCorporateNews(corporateNews)) {
                    SysSysLog sysLog = new SysSysLog();
                    String ip = ClientIP.getClientIP(request);
                    sysLog.setIpAddress(ip);
                    sysLog.setCName("修改企业新闻");
                    sysLog.setRemark("修改("+corporateNews.getTitle()+")企业新闻");
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
     * 新增新闻内容
     * @return
     */
    @RequestMapping(value = "/updateAddCorporateNewsModal.htm")
    public  String updateAddCorporateNews(HttpServletRequest request, Long id, Long resourceId){
        ZsCorporateNews corporateNewsById = traceabilityservice.getCorporateNewsById(id);
        request.setAttribute("resourceId",resourceId);
        request.setAttribute("corporateNewsById",corporateNewsById);
        return "traceability/updateAddCorporateNewsModal";
    }
    @RequestMapping(value="/updateAddCorporateNews.json")
    @ResponseBody
    public Object updateAddCorporateNews(HttpServletRequest request, ZsCorporateNews corporateNews, Long resourceId) {
        Json json = new Json();
        try {
            if(null!=corporateNews){
                Subject user = SecurityUtils.getSubject();
                ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                corporateNews.setUpdateUserId(shiroUser.getId());
                if (traceabilityservice.updateAddCorporateNews(corporateNews)) {
                    SysSysLog sysLog = new SysSysLog();
                    String ip = ClientIP.getClientIP(request);
                    sysLog.setIpAddress(ip);
                    sysLog.setCName("新增企业新闻内容");
                    sysLog.setRemark("新增("+corporateNews.getCorporateNewsName()+")企业新闻内容");
                    sysLog.setResourceId(resourceId);
                    sysLog.setCreatedUserId(shiroUser.getId());
                    sysSysLogService.addSysSysLog(sysLog);
                    json.setMsg("新增成功");
                    json.setSuccess(true);
                } else {
                    json.setMsg("新增失败");
                }
            }else {
                json.setMsg("传入参数异常，无法修改");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /***
     * 删除新闻
     * @param
     * @return
     */
    @RequestMapping(value="/delectCorporateNews.json")
    @ResponseBody
    public Object delectCorporateNews(HttpServletRequest request,Long corporateNewsId, Long resourceId) {
        Json json = new Json();
        try {
        ZsCorporateNews corporateNews = zsCorporateNewsService.getCorporateNewsById(corporateNewsId);
        if(null!=corporateNews){
        Subject user = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
        corporateNews.setUpdateUserId(shiroUser.getId());
            if (traceabilityservice.delectCorporateNews(corporateNews)) {
                SysSysLog sysLog = new SysSysLog();
                String ip = ClientIP.getClientIP(request);
                sysLog.setIpAddress(ip);
                sysLog.setCName("删除企业新闻");
                sysLog.setRemark("删除("+corporateNews.getTitle()+")企业新闻");
                sysLog.setResourceId(resourceId);
                sysLog.setCreatedUserId(shiroUser.getId());
                sysSysLogService.addSysSysLog(sysLog);
                json.setMsg("删除成功");
                json.setSuccess(true);
            } else {
                json.setMsg("删除失败");
            }
        }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }
    /**
     * 查看新闻图片
     * @return
     */
    @RequestMapping(value = "/viewCorporateNewsModal.htm")
    public  String viewCorporateNews(Long id,HttpServletRequest request){
        ZsCorporateNews corporateNewsById = traceabilityservice.getCorporateNewsById(id);
        request.setAttribute("corporateNewsById",corporateNewsById);
        return "traceability/viewCorporateNewsModal";
    }
    /**
     * 展示企业文化列表
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "getCompanyCulture.json")
    @ResponseBody
    public Object getCompanyCulture(Integer page, Integer rows) {
        List<PZsCompanyCulture> companyCultureList = zsCompanyCultureService.getPCompanyCultureList(page, rows);
        PageInfo<PZsCompanyCulture> pageInfo = new PageInfo<>(companyCultureList);
        return new JqGridParam(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), companyCultureList);
    }

    /***
     *新增企业文化
     * @param
     * @return
     */
    @RequestMapping(value="/addCompanyCultureModal.htm")
    public  String addCompanyCultureModal(HttpServletRequest request, Long resourceId){
        request.setAttribute("resourceId",resourceId);
        return "traceability/addCompanyCultureModal";
    }
    @RequestMapping(value="/addCompanyCulture.json")
    @ResponseBody
    public Object addCompanyCulture(HttpServletRequest request,ZsCompanyCulture companyCulture,String cultureImagesId,List<MultipartFile> cultureImageList, Long resourceId) {
        Json json = new Json();
        try {
            List<ImageJson> imageJsonList = new ArrayList<>();
            if(null!=cultureImagesId&&!"".equals(cultureImagesId)){
                if(null!=cultureImageList&&0!=cultureImageList.size()){
                    for(int i = 0; i < cultureImageList.size(); i++){
                        MultipartFile file = cultureImageList.get(i);
                        if(null!=file&&0!=file.getSize()){
                            String imageName = ossClientUtil.uploadImg2Oss(file);
                            if(null==imageName){
                                json.setMsg("图片上传失败");
                                return json;
                            }else if("上传图片大小不能超过2M！".equals(imageName)){
                                        json.setMsg("上传图片大小不能超过2M！");
                                        return json;
                            }
                            ImageJson imageJson = new ImageJson();
                            imageJson.setImageName(imageName);
                            String imageUrl = ossClientUtil.getImgUrl(imageName);
                            imageJson.setImageUrl(imageUrl);
                            imageJsonList.add(imageJson);
                        }
                    }
                }
            }
            if(10>imageJsonList.size()){
                for(int i=imageJsonList.size();i<10;i++){
                    ImageJson imageJson = new ImageJson();
                    imageJson.setImageName("");
                    imageJson.setImageUrl("");
                    imageJsonList.add(imageJson);
                }
            }
            if(null!=imageJsonList&&0!=imageJsonList.size()){
                String cultureImage = JSON.toJSONString(imageJsonList);
                companyCulture.setImage(cultureImage);
            }
            Subject user = SecurityUtils.getSubject();
            ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
            companyCulture.setCreateUserId(shiroUser.getId());
            companyCulture.setCreateDate(new Date());
            companyCulture.setUpdateDate(new Date());
            if (traceabilityservice.addCompanyCulture(companyCulture)) {
                SysSysLog sysLog = new SysSysLog();
                String ip = ClientIP.getClientIP(request);
                sysLog.setIpAddress(ip);
                sysLog.setCName("新增企业文化");
                sysLog.setRemark("新增("+companyCulture.getCompanyCultureName()+")企业文化");
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
     * 修改企业文化
     * @return
     */
    @RequestMapping(value = "/updateCompanyCultureModal.htm")
    public  String updateCompanyCultureModal(HttpServletRequest request, Long id, Long resourceId){
        request.setAttribute("resourceId",resourceId);
        PZsCompanyCulture companyCulture = zsCompanyCultureService.getPZsCompanyCultuerById(id);
        if(null!=companyCulture.getImage()&&""!=companyCulture.getImage()){
            Type type = new TypeReference<List<ImageJson>>() {}.getType();
            List<ImageJson> imageJsonList = JSON.parseObject(companyCulture.getImage(),type);
            if(null!=imageJsonList&&0!=imageJsonList.size()){
                Iterator<ImageJson> iterator = imageJsonList.iterator();
                while(iterator.hasNext()){
                    ImageJson imageJson = iterator.next();
                    if(null==imageJson){
                        iterator.remove();
                    }
                }
                companyCulture.setImageJsonList(imageJsonList);
            }
        }
        request.setAttribute("companyCultureById",companyCulture);
        return "traceability/updateCompanyCultureModal";
    }
    @RequestMapping(value="/updateCompanyCulture.json")
    @ResponseBody
    public Object updateCompanyCulture(HttpServletRequest request, ZsCompanyCulture companyCulture,String updCompanyCultureImageId,List<MultipartFile> updCompanyCultureImage, Long resourceId) {
        Json json = new Json();
        try {
            List<ImageJson> imageJsonList = new ArrayList<>();
            PZsCompanyCulture pZsCompanyCulture = zsCompanyCultureService.getPZsCompanyCultuerById(companyCulture.getCompanyCultureId());
            if(null!=updCompanyCultureImageId){
                if(null!=pZsCompanyCulture){
                    if(null!=pZsCompanyCulture.getImage()){
                        Type type = new TypeReference<List<ImageJson>>() {}.getType();
                        imageJsonList = JSON.parseObject(pZsCompanyCulture.getImage(),type);
                        for(int i = 0; i < 10; i++){
                            if(updCompanyCultureImageId.contains("" + i)){
                                imageJsonList.get(i).setImageUrl("");
                                imageJsonList.get(i).setImageName("");
                            }
                        }
                        Iterator<ImageJson> iterator = imageJsonList.iterator();
                        while(iterator.hasNext()){
                            ImageJson imageJson = iterator.next();
                            if("".equals(imageJson.getImageUrl())){
                                iterator.remove();
                            }
                        }
                        if(null!=updCompanyCultureImage&&0!=updCompanyCultureImage.size()){
                            for(int i = 0; i < updCompanyCultureImage.size(); i++){
                                MultipartFile file = updCompanyCultureImage.get(i);
                                if(null != file && 0 != file.getSize()){
                                    String imageName = ossClientUtil.uploadImg2Oss(file);
                                    if(null==imageName){
                                        json.setMsg("图片上传失败");
                                        return json;
                                    }else {
                                        if("上传图片大小不能超过2M！".equals(imageName)){
                                            json.setMsg("上传图片大小不能超过2M！");
                                            return json;
                                        }
                                    }
                                    String url = ossClientUtil.getImgUrl(imageName);
                                    ImageJson imageJson = new ImageJson();
                                    imageJson.setImageName(imageName);
                                    imageJson.setImageUrl(url);
                                    imageJsonList.add(imageJson);
                                }
                            }
                        }
                    }
                }else {
                    imageJsonList = new ArrayList<>();
                    if(null!=updCompanyCultureImage&&0!=updCompanyCultureImage.size()){
                        for(int i = 0; i < updCompanyCultureImage.size(); i++){
                            MultipartFile file = updCompanyCultureImage.get(i);
                            if(null != file && 0 != file.getSize()){
                                String imageName = ossClientUtil.uploadImg2Oss(file);
                                if(null==imageName){
                                    json.setMsg("图片上传失败");
                                    return json;
                                }else {
                                    if("上传图片大小不能超过2M！".equals(imageName)){
                                        json.setMsg("上传图片大小不能超过2M！");
                                        return json;
                                    }
                                }
                                String url = ossClientUtil.getUrl(imageName);
                                ImageJson imageJson = new ImageJson();
                                imageJson.setImageName(imageName);
                                imageJson.setImageUrl(url);
                                imageJsonList.add(imageJson);
                            }
                        }
                    }
                }
                if(10>imageJsonList.size()){
                    for(int i = imageJsonList.size(); i < 10; i++){
                        ImageJson imageJson = new ImageJson();
                        imageJson.setImageName("");
                        imageJson.setImageUrl("");
                        imageJsonList.add(imageJson);
                    }
                }
                String imageJson = JSON.toJSONString(imageJsonList);
                companyCulture.setImage(imageJson);
            }else {
                companyCulture.setImage(pZsCompanyCulture.getImage());
            }
            Subject user = SecurityUtils.getSubject();
            ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
            companyCulture.setUpdateUserId(shiroUser.getId());
            if (traceabilityservice.updateCompanyCultureById(companyCulture)) {
                SysSysLog sysLog = new SysSysLog();
                String ip = ClientIP.getClientIP(request);
                sysLog.setIpAddress(ip);
                sysLog.setCName("修改企业文化");
                sysLog.setRemark("修改("+companyCulture.getCompanyCultureName()+")企业文化");
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
     * 查看企业图片
     * @return
     */
    @RequestMapping(value = "/viewCompanyCultureModal.htm")
    public  String viewCompanyCulture(Long id,HttpServletRequest request){
        ZsCompanyCulture companyCultureById = traceabilityservice.getCompanyCultureById(id);
        if(null!=companyCultureById){
            if(null!=companyCultureById.getImage()){
                Type type = new TypeReference<List<ImageJson>>() {}.getType();
                List<ImageJson> imageJsonList = JSON.parseObject(companyCultureById.getImage(),type);
                if(null!=imageJsonList&&0!=imageJsonList.size()){
                    Iterator<ImageJson> iterator = imageJsonList.iterator();
                    while(iterator.hasNext()){
                        ImageJson imageJson = iterator.next();
                        if("".equals(imageJson.getImageUrl())){
                            iterator.remove();
                        }
                    }
                    request.setAttribute("imageJsonList",imageJsonList);
                }
            }
        }
        return "traceability/viewCompanyCultureModal";
    }
    /***
     * 删除企业文化
     * @param
     * @return
     */
    @RequestMapping(value="/delectCompanyCulture.json")
    @ResponseBody
    public Object delectCompanyCulture(HttpServletRequest request, ZsCompanyCulture companyCulture, Long resourceId) {
        Json json = new Json();
        try {
            ZsCompanyCulture zsCompanyCulture = zsCompanyCultureService.getPZsCompanyCultuerById(companyCulture.getCompanyCultureId());
            if(null!=zsCompanyCulture){
                Subject user =SecurityUtils.getSubject();
                ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                companyCulture.setUpdateUserId(shiroUser.getId());
                if (traceabilityservice.delectCompanyCulture(companyCulture)) {
                    SysSysLog sysLog = new SysSysLog();
                    String ip = ClientIP.getClientIP(request);
                    sysLog.setIpAddress(ip);
                    sysLog.setCName("删除企业文化");
                    sysLog.setRemark("删除("+zsCompanyCulture.getCompanyCultureName()+")企业文化");
                    sysLog.setResourceId(resourceId);
                    sysLog.setCreatedUserId(shiroUser.getId());
                    sysSysLogService.addSysSysLog(sysLog);
                    json.setMsg("删除成功");
                    json.setSuccess(true);
                } else {
                    json.setMsg("删除失败");
                }
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }
    /**
     * 展示生产控制点列表
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "getProductionInformation.json")
    @ResponseBody
    public Object getProductionInformation(Integer page, Integer rows) {
        List<ZsProductionInformation> productionInformation = traceabilityservice.getProductionInformation(page, rows);
        PageInfo<ZsProductionInformation> pageInfo = new PageInfo<>(productionInformation);
        return new JqGridParam(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), productionInformation);
    }
    /***
     *新增生产控制点
     * @param
     * @return
     */
    @RequestMapping(value="/addProductionInformationModal.htm")
    public  String addProductionInformationModal(HttpServletRequest request, Long resourceId){
        request.setAttribute("resourceId",resourceId);
        return "traceability/addProductionInformationModal";
    }
    @RequestMapping(value="/addProductionInformation.json")
    @ResponseBody
    public Object addProductionInformation(HttpServletRequest request, ZsProductionInformation productionInformation, MultipartFile image, Long resourceId) {
        Json json = new Json();
        try {
            if(image!=null) {
                String name = ossClientUtil.uploadImg2Oss(image);
                //由上传文件是否为空来判断删除和添加修改操作
                if (null == name) {
                    json.setMsg("图片上传失败");
                    return json;
                }else if("上传图片大小不能超过2M！".equals(name)){
                    json.setMsg("上传图片大小不能超过2M！");
                    return json;
                }
                productionInformation.setImages(ossClientUtil.getImgUrl(name));
            }
            Subject user = SecurityUtils.getSubject();
            ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
            productionInformation.setCreateUserId(shiroUser.getId());
            productionInformation.setCreateDate(new Date());
            productionInformation.setUpdateDate(new Date());
            if (traceabilityservice.addProductionInformation(productionInformation)) {
                SysSysLog sysLog = new SysSysLog();
                String ip = ClientIP.getClientIP(request);
                sysLog.setIpAddress(ip);
                sysLog.setCName("新增生产控制点");
                sysLog.setRemark("新增("+productionInformation.getProductionInformationName()+")生产控制点");
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
     * 修改生产控制点
     * @return
     */
    @RequestMapping(value = "/updateProductionInformationModal.htm")
    public  String updateProductionInformationModal(HttpServletRequest request, Long id, Long resourceId){
        request.setAttribute("resourceId",resourceId);
        ZsProductionInformation productionInformationById = traceabilityservice.getProductionInformationById(id);
        request.setAttribute("productionInformationById",productionInformationById);
        return "traceability/updateProductionInformationModal";
    }
    @RequestMapping(value="/updateProductionInformation.json")
    @ResponseBody
    public Object updateProductionInformation(HttpServletRequest request, ZsProductionInformation productionInformation, String updProductionInformationImageId, MultipartFile updProductionInformationImage, Long resourceId) {
        Json json = new Json();
        try {
            ZsProductionInformation zsProductionInformation = zsProductionInformationService.getProductionInformationByProductionInformationId(productionInformation.getProductionInformationId());
            if(null!=zsProductionInformation) {
                if(null!=updProductionInformationImageId&&!"".equals(updProductionInformationImageId)){
                    if (updProductionInformationImage != null) {
                        String name = ossClientUtil.uploadImg2Oss(updProductionInformationImage);
                        //由上传文件是否为空来判断删除和添加修改操作
                        if (null == name) {
                            json.setMsg("图片上传失败");
                            return json;
                        } else if ("上传图片大小不能超过2M！".equals(name)) {
                            json.setMsg("上传图片大小不能超过2M！");
                            return json;
                        }
                        productionInformation.setImages(ossClientUtil.getImgUrl(name));
                    } else {
                        productionInformation.setImages(ossClientUtil.getImgUrl(""));
                    }
                }else{
                    productionInformation.setImages(zsProductionInformation.getImages());
                }
                Subject user = SecurityUtils.getSubject();
                ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                productionInformation.setUpdateUserId(shiroUser.getId());
                if (traceabilityservice.updateProductionInformation(productionInformation)) {
                    SysSysLog sysLog = new SysSysLog();
                    String ip = ClientIP.getClientIP(request);
                    sysLog.setIpAddress(ip);
                    sysLog.setCName("编辑生产控制点");
                    sysLog.setRemark("编辑("+productionInformation.getProductionInformationName()+")生产控制点");
                    sysLog.setResourceId(resourceId);
                    sysLog.setCreatedUserId(shiroUser.getId());
                    sysSysLogService.addSysSysLog(sysLog);
                    json.setMsg("修改成功");
                    json.setSuccess(true);
                } else {
                    json.setMsg("修改失败");
                }
            }else {
                json.setMsg("传入数据异常，无法修改");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }
    /**
     * 查看生产控制点图片
     * @return
     */
    @RequestMapping(value = "/viewProductionInformationModal.htm")
    public  String viewProductionInformation(Long id,HttpServletRequest request){
        ZsProductionInformation productionInformationById = traceabilityservice.getProductionInformationById(id);
        request.setAttribute("productionInformationById",productionInformationById);
        return "traceability/viewProductionInformationModal";
    }
    /***
     * 删除生产控制点
     * @param
     * @return
     */
    @RequestMapping(value="/delectProductionInformation.json")
    @ResponseBody
    public Object delectProductionInformation(HttpServletRequest request, ZsProductionInformation productionInformation, Long resourceId) {
        Json json = new Json();
        try {
            ZsProductionInformation zsProductionInformation = zsProductionInformationService.getProductionInformationByProductionInformationId(productionInformation.getProductionInformationId());
            if(null!=zsProductionInformation){
                Subject user = SecurityUtils.getSubject();
                ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                productionInformation.setUpdateUserId(shiroUser.getId());
                if (traceabilityservice.delectProductionInformation(productionInformation)) {
                    SysSysLog sysLog = new SysSysLog();
                    String ip = ClientIP.getClientIP(request);
                    sysLog.setIpAddress(ip);
                    sysLog.setCName("删除生产控制点");
                    sysLog.setRemark("删除("+zsProductionInformation.getProductionInformationName()+")生产控制点");
                    sysLog.setResourceId(resourceId);
                    sysLog.setCreatedUserId(shiroUser.getId());
                    sysSysLogService.addSysSysLog(sysLog);
                    json.setMsg("删除成功");
                    json.setSuccess(true);
                } else {
                    json.setMsg("删除失败");
                }
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }
    /**
     * 展示晓芹厨房列表
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "getXqKitchen.json")
    @ResponseBody
    public Object getXqKitchen(Integer page, Integer rows) {
        List<PXqKitchen> xqKitchen = traceabilityservice.getXqKitchen(page, rows);
        PageInfo<PXqKitchen> pageInfo = new PageInfo<>(xqKitchen);
        return new JqGridParam(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), xqKitchen);
    }
    /***
     *新增晓芹厨房
     * @param
     * @return
     */
    @RequestMapping(value="/addXqKitchenModal.htm")
    public  String addXqKitchenModal(HttpServletRequest request, Long resourceId){
        request.setAttribute("resourceId",resourceId);
        return "traceability/addXqKitchenModal";
    }
    @RequestMapping(value="/addXqKitchen.json")
    @ResponseBody
    public Object addXqKitchen(HttpServletRequest request, PXqKitchen pXqKitchen,String kitchenImageId, List<MultipartFile> kitchenImagesList, Long resourceId) {
        Json json = new Json();
        try {
            if (null!=kitchenImageId && kitchenImageId!="" ){
                List<ImageJson> imageList = new ArrayList<>();
                    imageList = new ArrayList<>();
                    for(int i = 0; i < kitchenImagesList.size(); i++){
                        MultipartFile imgFile = kitchenImagesList.get(i);
                        if(null != imgFile && 0 != imgFile.getSize()){
                            String name = ossClientUtil.uploadImg2Oss(imgFile);
                            if (null==name){
                                json.setMsg("图片上传失败");
                                return json;
                            }else {
                                if ("上传图片大小不能超过2M！".equals(name)){
                                    json.setMsg("上传图片大小不能超过2M！");
                                    return json;
                                }
                            }
                            ImageJson imageJson = new ImageJson();
                            imageJson.setImageName(name);
                            imageJson.setImageUrl(ossClientUtil.getImgUrl(name));
                            imageList.add(imageJson);
                        }else {
                            json.setMsg("第"+(i+1)+"张，图片有误，请重新上传！");
                            return json;
                        }
                    }
                if (10>=imageList.size()) {
                    for (int i = imageList.size(); i < 10; i++) {
                        ImageJson imageJson = new ImageJson();
                        imageJson.setImageName("");
                        imageJson.setImageUrl("");
                        imageList.add(imageJson);
                    }
                }
                String jsonText = JSON.toJSONString(imageList,true);
                pXqKitchen.setImages(jsonText);
            }else {
                pXqKitchen.setImages(pXqKitchen.getImages());
            }
            Subject user = SecurityUtils.getSubject();
            ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
            pXqKitchen.setCreateUserId(shiroUser.getId());
            pXqKitchen.setCreateDate(new Date());
            pXqKitchen.setUpdateDate(new Date());
            if (traceabilityservice.addXqKitchen(pXqKitchen)) {
                SysSysLog sysLog = new SysSysLog();
                String ip = ClientIP.getClientIP(request);
                sysLog.setIpAddress(ip);
                sysLog.setCName("新增晓芹厨房");
                sysLog.setRemark("新增("+pXqKitchen.getKitchenName()+")晓芹厨房");
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
     * 修改晓芹厨房
     * @return
     */
    @RequestMapping(value = "/updateXqKitchenModal.htm")
    public  String updateXqKitchenModal(HttpServletRequest request, Long id, Long resourceId){
        request.setAttribute("resourceId",resourceId);
        PXqKitchen xqKitchenById = traceabilityservice.getXqKitchenById(id);
        Type type = new TypeReference<List<ImageJson>>() {}.getType();
        List<ImageJson> imageList = JSON.parseObject(xqKitchenById.getImages(), type);
        xqKitchenById.setImagesJson(imageList);
        request.setAttribute("xqKitchenById",xqKitchenById);
        return "traceability/updateXqKitchenModal";
    }
    @RequestMapping(value="/updateXqKitchen.json")
    @ResponseBody
    public Object updateXqKitchen(HttpServletRequest request, PXqKitchen pXqKitchen,String updKitchenImageId, List<MultipartFile> updKitchenimagesList, Long resourceId) {
        Json json = new Json();
        try {
            PXqKitchen xqKitchenById = traceabilityservice.getXqKitchenById(pXqKitchen.getKitchenId());
            if (null!=updKitchenImageId){
                List<ImageJson> imageList = new ArrayList<>();
                if (null!=xqKitchenById.getImages()){
                    String jsonStr = xqKitchenById.getImages();
                    Type type = new TypeReference<List<ImageJson>>() {}.getType();
                    imageList = JSON.parseObject(jsonStr, type);
                    xqKitchenById.setSeaAreaImagesJson(imageList);
                    for(int i = 0; i < 10; i++) {
                        if (updKitchenImageId.contains("" + i)) {
                            imageList.get(i).setImageName("");
                            imageList.get(i).setImageUrl("");
                        }
                    }
                    //删除空图片url
                    Iterator<ImageJson> iterator = imageList.iterator();
                    while (iterator.hasNext()){
                        ImageJson str = iterator.next();
                        if("".equals(str.getImageUrl())){
                            iterator.remove();
                        }
                    }
                    if(null!=updKitchenimagesList && 0!=updKitchenimagesList.size()){
                        for (int i =0;i<updKitchenimagesList.size();i++){
                            MultipartFile imgFile = updKitchenimagesList.get(i);
                            if(null != imgFile && 0 != imgFile.getSize()){
                                String name = ossClientUtil.uploadImg2Oss(imgFile);
                                if (null==name){
                                    json.setMsg("图片上传失败");
                                    return json;
                                }else {
                                    if ("上传图片大小不能超过2M！".equals(name)){
                                        json.setMsg("上传图片大小不能超过2M！");
                                        return json;
                                    }
                                }
                                ImageJson imageJson = new ImageJson();
                                imageJson.setImageName(name);
                                imageJson.setImageUrl(ossClientUtil.getImgUrl(name));
                                imageList.add(imageJson);
                            }else {
                                json.setMsg("第"+(i+1)+"张，图片有误，请重新上传！");
                                return json;
                            }
                        }
                    }
                }else {
                    imageList = new ArrayList<>();
                    if(null!=updKitchenimagesList&&0!=updKitchenimagesList.size()){
                        for(int i = 0; i < updKitchenimagesList.size(); i++){
                            MultipartFile imgFile = updKitchenimagesList.get(i);
                            if(null != imgFile && 0 != imgFile.getSize()){
                                String name = ossClientUtil.uploadImg2Oss(imgFile);
                                if (null==name){
                                    json.setMsg("图片上传失败");
                                    return json;
                                }else {
                                    if ("上传图片大小不能超过2M！".equals(name)){
                                        json.setMsg("上传图片大小不能超过2M！");
                                        return json;
                                    }
                                }
                                ImageJson imageJson = new ImageJson();
                                imageJson.setImageName(name);
                                imageJson.setImageUrl(ossClientUtil.getImgUrl(name));
                                imageList.add(imageJson);
                            }else {
                                json.setMsg("第"+(i+1)+"张，图片有误，请重新上传！");
                                return json;
                            }
                        }
                    }
                }
                if (10>=imageList.size()) {
                    for (int i = imageList.size(); i < 10; i++) {
                        ImageJson imageJson = new ImageJson();
                        imageJson.setImageName("");
                        imageJson.setImageUrl("");
                        imageList.add(imageJson);
                    }
                }
                String jsonText = JSON.toJSONString(imageList,true);
                pXqKitchen.setImages(jsonText);
            }else {
                pXqKitchen.setImages(xqKitchenById.getImages());
            }
            Subject user = SecurityUtils.getSubject();
            ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
            pXqKitchen.setUpdateUserId(shiroUser.getId());
            if (traceabilityservice.updateXqKitchen(pXqKitchen)) {
                SysSysLog sysLog = new SysSysLog();
                String ip = ClientIP.getClientIP(request);
                sysLog.setIpAddress(ip);
                sysLog.setCName("编辑晓芹厨房");
                sysLog.setRemark("编辑("+pXqKitchen.getKitchenName()+")晓芹厨房");
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
     * 查看晓芹厨房图片
     * @return
     */
    @RequestMapping(value = "/viewXqKitchenModal.htm")
    public  String viewXqKitchen(Long id,HttpServletRequest request){
        PXqKitchen xqKitchenById = traceabilityservice.getXqKitchenById(id);
        Type type = new TypeReference<List<ImageJson>>() {}.getType();
        List<ImageJson> imageList = JSON.parseObject(xqKitchenById.getImages(), type);
        Iterator<ImageJson> iterator = imageList.iterator();
        while(iterator.hasNext()){
            ImageJson imageJson = iterator.next();
            if("".equals(imageJson.getImageUrl())){
                iterator.remove();
            }
        }
        if(null!=imageList&&0!=imageList.size()){
            request.setAttribute("imageList",imageList);
        }
        return "traceability/viewXqKitchenModal";
    }
    /***
     * 删除晓芹厨房
     * @param
     * @return
     */
    @RequestMapping(value="/delectXqKitchen.json")
    @ResponseBody
    public Object delectXqKitchen(HttpServletRequest request, PXqKitchen pXqKitchen, Long resourceId) {
        Json json = new Json();
        try {
            PXqKitchen xqKitchen = zsXqKitchenService.getPXqKitchenById(pXqKitchen.getKitchenId());
            if(null!=xqKitchen){
                Subject user = SecurityUtils.getSubject();
                ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                pXqKitchen.setUpdateUserId(shiroUser.getId());
                if (traceabilityservice.delectXqKitchen(pXqKitchen)) {
                    SysSysLog sysLog = new SysSysLog();
                    String ip = ClientIP.getClientIP(request);
                    sysLog.setIpAddress(ip);
                    sysLog.setCName("删除晓芹厨房");
                    sysLog.setRemark("删除("+xqKitchen.getKitchenName()+")晓芹厨房");
                    sysLog.setResourceId(resourceId);
                    sysLog.setCreatedUserId(shiroUser.getId());
                    sysSysLogService.addSysSysLog(sysLog);
                    json.setMsg("删除成功");
                    json.setSuccess(true);
                } else {
                    json.setMsg("删除失败");
                }
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 展示检验设备列表
     * @param
     * @param rows
     * @return
     */
    @RequestMapping(value = "getTestingEquipment.json")
    @ResponseBody
    public Object getTestingEquipment(Integer page, Integer rows) {
        List<PTestingEquipment> testingEquipment = traceabilityservice.getTestingEquipment(page, rows);
        PageInfo<PTestingEquipment> pageInfo = new PageInfo<>(testingEquipment);
        return new JqGridParam(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), testingEquipment);
    }
    /**
     * 展示检验设备内容列表
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "getTestingEquipmentDetail.json")
    @ResponseBody
    public Object getTestingEquipmentDetail(Integer page, Integer rows) {
        List<PTestingEquipmentDetail> testingEquipmentDetail = traceabilityservice.getTestingEquipmentDetail(page, rows);
        PageInfo<PTestingEquipmentDetail> pageInfo = new PageInfo<>(testingEquipmentDetail);
        return new JqGridParam(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), testingEquipmentDetail);
    }
    /***
     *新增检验设备
     * @param
     * @return
     */
    @RequestMapping(value="/addTestingEquipmentModal.htm")
    public  String addTestingEquipmentModal(HttpServletRequest request, Long resourceId){
        request.setAttribute("resourceId",resourceId);
        List<PTestingEquipment> dictionary = traceabilityservice.getDictionary();
        request.setAttribute("dictionary",dictionary);
        return "traceability/addTestingEquipmentModal";
    }
    @RequestMapping(value="/addTestingEquipment.json")
    @ResponseBody
    public Object addTestingEquipment(HttpServletRequest request, PTestingEquipment pTestingEquipment, MultipartFile image, Long resourceId) {
        Json json = new Json();
        try {
            PTestingEquipment testingEquipment = zsTestingEquipmentService.getPTestingEquipmentByDataDictionaryDetailsId(pTestingEquipment.getDataDictionaryDetailsId());
            if(null==testingEquipment) {
                if (image != null) {
                    String name = ossClientUtil.uploadImg2Oss(image);
                    //由上传文件是否为空来判断删除和添加修改操作
                    if (null == name) {
                        json.setMsg("图片上传失败");
                        return json;
                    } else if ("上传图片大小不能超过2M！".equals(name)) {
                        json.setMsg("上传图片大小不能超过2M！");
                        return json;
                    }
                    pTestingEquipment.setImages(ossClientUtil.getImgUrl(name));
                }
                Subject user = SecurityUtils.getSubject();
                ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                pTestingEquipment.setCreateUserId(shiroUser.getId());
                pTestingEquipment.setCreateDate(new Date());
                pTestingEquipment.setUpdateDate(new Date());
                if (traceabilityservice.addTestingEquipment(pTestingEquipment)) {
                    BaDataDictionaryDetails baDataDictionaryDetails = baDataDictionaryDetailsService.getBaDataDictionaryDetailsById(pTestingEquipment.getDataDictionaryDetailsId());
                    SysSysLog sysLog = new SysSysLog();
                    String ip = ClientIP.getClientIP(request);
                    sysLog.setIpAddress(ip);
                    sysLog.setCName("新增检验室");
                    sysLog.setRemark("新增("+baDataDictionaryDetails.getCName()+")检验室");
                    sysLog.setResourceId(resourceId);
                    sysLog.setCreatedUserId(shiroUser.getId());
                    sysSysLogService.addSysSysLog(sysLog);
                    json.setMsg("添加成功");
                    json.setSuccess(true);
                } else {
                    json.setMsg("添加失败");
                }
            }else{
                json.setMsg("该检验室已经存在");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }
    /**
     * 修改生检验设备
     * @return
     */
    @RequestMapping(value = "/updateTestingEquipmentModal.htm")
    public  String updateTestingEquipmentModal(HttpServletRequest request,Long id,Long resourceId){
        request.setAttribute("resourceId",resourceId);
        List<PTestingEquipment> dictionary = traceabilityservice.getDictionary();
        request.setAttribute("dictionary",dictionary);
        PTestingEquipment testingEquipmentById = traceabilityservice.getTestingEquipmentById(id);
        request.setAttribute("testingEquipmentById",testingEquipmentById);
        return "traceability/updateTestingEquipmentModal";
    }
    @RequestMapping(value="/updateTestingEquipment.json")
    @ResponseBody
    public Object updateTestingEquipment(HttpServletRequest request, PTestingEquipment pTestingEquipment, String updTestEqImageId, MultipartFile updTestEqImage, Long resourceId) {
        Json json = new Json();
        try {
            PTestingEquipment testingEquipment = zsTestingEquipmentService.getTestingEquipmentByTestingEquipmentId(pTestingEquipment.getTestingEquipmentId());
            if(null!=testingEquipment){
                if(null!=updTestEqImageId&&!"".equals(updTestEqImageId)){
                    if(updTestEqImage!=null) {
                        String name = ossClientUtil.uploadImg2Oss(updTestEqImage);
                        //由上传文件是否为空来判断删除和添加修改操作
                        if (null == name) {
                            json.setMsg("图片上传失败");
                            return json;
                        }else if("上传图片大小不能超过2M！".equals(name)){
                            json.setMsg("上传图片大小不能超过2M！");
                            return json;
                        }
                        pTestingEquipment.setImages(ossClientUtil.getImgUrl(name));
                    }else {
                        pTestingEquipment.setImages(ossClientUtil.getImgUrl(""));
                    }
                }else {
                    pTestingEquipment.setImages(testingEquipment.getImages());
                }
                Subject user = SecurityUtils.getSubject();
                ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                pTestingEquipment.setUpdateUserId(shiroUser.getId());
                if (traceabilityservice.updateTestingEquipment(pTestingEquipment)) {
                    BaDataDictionaryDetails baDataDictionaryDetails = baDataDictionaryDetailsService.getBaDataDictionaryDetailsById(pTestingEquipment.getDataDictionaryDetailsId());
                    SysSysLog sysLog = new SysSysLog();
                    String ip = ClientIP.getClientIP(request);
                    sysLog.setIpAddress(ip);
                    sysLog.setCName("修改检验室");
                    sysLog.setRemark("修改("+baDataDictionaryDetails.getCName()+")检验室");
                    sysLog.setResourceId(resourceId);
                    sysLog.setCreatedUserId(shiroUser.getId());
                    sysSysLogService.addSysSysLog(sysLog);
                    json.setMsg("修改成功");
                    json.setSuccess(true);
                } else {
                    json.setMsg("修改失败");
                }
            }else {
                json.setMsg("传入数据异常，无法修改");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }
    /***
     * 删除检验设备
     * @param
     * @return
     */
    @RequestMapping(value="/delectTestingEquipment.json")
    @ResponseBody
    public Object delectTestingEquipment(HttpServletRequest request,PTestingEquipment pTestingEquipment,Long resourceId) {
        Json json = new Json();
        try {
            PTestingEquipment testingEquipment = zsTestingEquipmentService.getTestingEquipmentByTestingEquipmentId(pTestingEquipment.getTestingEquipmentId());
            if(null!=testingEquipment){
                Subject user = SecurityUtils.getSubject();
                ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                pTestingEquipment.setUpdateUserId(shiroUser.getId());
                if (traceabilityservice.delectTestingEquipment(pTestingEquipment)) {
                    BaDataDictionaryDetails baDataDictionaryDetails = baDataDictionaryDetailsService.getBaDataDictionaryDetailsById(testingEquipment.getDataDictionaryDetailsId());
                    SysSysLog sysLog = new SysSysLog();
                    String ip = ClientIP.getClientIP(request);
                    sysLog.setIpAddress(ip);
                    sysLog.setCName("删除检验室");
                    sysLog.setRemark("删除("+baDataDictionaryDetails.getCName()+")检验室");
                    sysLog.setResourceId(resourceId);
                    sysLog.setCreatedUserId(shiroUser.getId());
                    sysSysLogService.addSysSysLog(sysLog);
                    json.setMsg("删除成功");
                    json.setSuccess(true);
                } else {
                    json.setMsg("删除失败");
                }
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }
    /**
     * 查看检验设备图片
     * @return
     */
    @RequestMapping(value = "/viewTestingEquipmentModal.htm")
    public  String viewTestingEquipment(Long id,HttpServletRequest request){
        PTestingEquipment testingEquipmentById = traceabilityservice.getTestingEquipmentById(id);
        request.setAttribute("testingEquipmentById",testingEquipmentById);
        return "traceability/viewTestingEquipmentModal";
    }
    /***
     *新增检验设备内容
     * @param
     * @return
     */
    @RequestMapping(value="/updateAddTestingEquipmentDetailModal.htm")
    public  String updateAddTestingEquipmentDetailModal(HttpServletRequest request,Long testingEquipmentId, Long resourceId){
        request.setAttribute("resourceId", resourceId);
        PTestingEquipment pTestingEquipment = zsTestingEquipmentService.getTestingEquipmentByTestingEquipmentId(testingEquipmentId);
        if(null!=pTestingEquipment){
            request.setAttribute("pTestingEquipment",pTestingEquipment);
        }
        return "traceability/updateAddTestingEquipmentDetailModal";
    }
    @RequestMapping(value="/addTestingEquipmentDetailModal.htm")
    public  String addTestingEquipmentDetailModal(HttpServletRequest request, Long id, Long resourceId){
        request.setAttribute("resourceId",resourceId);
        if(null!=id){
            PTestingEquipmentDetail pTestingEquipmentDetail = zsTestingEquipmentDetailService.getPTestingEqDetailsByTestingEquipmentDetailId(id);
            if(null!=pTestingEquipmentDetail){
                request.setAttribute("pTestingEquipmentDetail",pTestingEquipmentDetail);
            }
        }
        return "traceability/addTestingEquipmentDetailModal";
    }
    @RequestMapping(value="/updateAddTestingEquipmentDetail.json")
    @ResponseBody
    public Object updateAddTestingEquipmentDetail(HttpServletRequest request,PTestingEquipmentDetail testingEquipmentDetail, MultipartFile image,Long resourceId) {
        Json json = new Json();
        try {
            PTestingEquipmentDetail pTestingEquipmentDetail = zsTestingEquipmentDetailService.getPTestingEqDetailsByTestingEqIdAndName(testingEquipmentDetail);
            if(null==pTestingEquipmentDetail) {
                if (image != null) {
                    String name = ossClientUtil.uploadImg2Oss(image);
                    //由上传文件是否为空来判断删除和添加修改操作
                    if (null == name) {
                        json.setMsg("图片上传失败");
                        return json;
                    } else if ("上传图片大小不能超过2M！".equals(name)) {
                        json.setMsg("上传图片大小不能超过2M！");
                        return json;
                    }
                    testingEquipmentDetail.setImages(ossClientUtil.getImgUrl(name));
                }else {
                    testingEquipmentDetail.setImages(ossClientUtil.getImgUrl(""));
                }
                Subject user = SecurityUtils.getSubject();
                ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                testingEquipmentDetail.setCreateUserId(shiroUser.getId());
                testingEquipmentDetail.setCreateDate(new Date());
                testingEquipmentDetail.setUpdateDate(new Date());
                if (traceabilityservice.addTestingEquipmentDetail(testingEquipmentDetail)) {
                    SysSysLog sysLog = new SysSysLog();
                    String ip = ClientIP.getClientIP(request);
                    sysLog.setIpAddress(ip);
                    sysLog.setCName("新增检验室内容");
                    sysLog.setRemark("新增("+testingEquipmentDetail.getTestingEquipmentDetailCname()+")检验室内容");
                    sysLog.setResourceId(resourceId);
                    sysLog.setCreatedUserId(shiroUser.getId());
                    sysSysLogService.addSysSysLog(sysLog);
                    json.setMsg("添加成功");
                    json.setSuccess(true);
                } else {
                    json.setMsg("添加失败");
                }
            }else {
                json.setMsg("该检验室下已经存在此内容");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }


    /***
     *修改检验设备内容
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateTestingEquipmentDetailModal.htm")
    public  String updateTestingEquipmentDetailModal(HttpServletRequest request, Long id, Long resourceId){
        request.setAttribute("resourceId",resourceId);
        List<PTestingEquipment> testingEquipmentList = zsTestingEquipmentService.getAllTestingEquipment();
        if(null!=testingEquipmentList&&0!=testingEquipmentList.size()){
            request.setAttribute("testingEquipmentList",testingEquipmentList);
        }
        PTestingEquipmentDetail testingEquipmentDetailById = traceabilityservice.getTestingEquipmentDetailById(id);
        request.setAttribute("testingEquipmentDetailById",testingEquipmentDetailById);
        return "traceability/updateTestingEquipmentDetailModal";
    }
    @RequestMapping(value="/updateTestingEquipmentDetail.json")
    @ResponseBody
    public Object updateTestingEquipmentDetail(HttpServletRequest request, PTestingEquipmentDetail pTestingEquipmentDetail,String updTestEqDetailImageId, MultipartFile updTestEqDetailImage, Long resourceId) {
        Json json = new Json();
        try {
            PTestingEquipmentDetail testingEquipmentDetail = zsTestingEquipmentDetailService.getPTestingEqDetailsByTestingEquipmentDetailId(pTestingEquipmentDetail.getTestingEquipmentDetailId());
            if(null!=testingEquipmentDetail){
                PTestingEquipmentDetail equipmentDetail = zsTestingEquipmentDetailService.getPTestingEqDetailsByTestingEqIdAndName(pTestingEquipmentDetail);
                if(null==equipmentDetail||equipmentDetail.getTestingEquipmentDetailId().equals(pTestingEquipmentDetail.getTestingEquipmentDetailId())){
                if(null!=updTestEqDetailImageId&&!"".equals(updTestEqDetailImageId)){
                    if(updTestEqDetailImage!=null) {
                        String name = ossClientUtil.uploadImg2Oss(updTestEqDetailImage);
                        //由上传文件是否为空来判断删除和添加修改操作
                        if (null == name) {
                            json.setMsg("图片上传失败");
                            return json;
                        }else if("上传图片大小不能超过2M！".equals(name)){
                            json.setMsg("上传图片大小不能超过2M！");
                            return json;
                        }
                        pTestingEquipmentDetail.setImages(ossClientUtil.getImgUrl(name));
                    }else {
                        pTestingEquipmentDetail.setImages(ossClientUtil.getImgUrl(""));
                    }
                }else{
                    pTestingEquipmentDetail.setImages(testingEquipmentDetail.getImages());
                }
                    Subject user = SecurityUtils.getSubject();
                    ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                    pTestingEquipmentDetail.setUpdateUserId(shiroUser.getId());
                    if (traceabilityservice.updateTestingEquipmentDetail(pTestingEquipmentDetail)) {
                        SysSysLog sysLog = new SysSysLog();
                        String ip = ClientIP.getClientIP(request);
                        sysLog.setIpAddress(ip);
                        sysLog.setCName("编辑检验室内容");
                        sysLog.setRemark("编辑("+pTestingEquipmentDetail.getTestingEquipmentDetailCname()+")检验室内容");
                        sysLog.setResourceId(resourceId);
                        sysLog.setCreatedUserId(shiroUser.getId());
                        sysSysLogService.addSysSysLog(sysLog);
                        json.setMsg("修改成功");
                        json.setSuccess(true);
                    } else {
                        json.setMsg("修改失败");
                    }
                }else {
                    json.setMsg("该检验室下已经存在此设备");
                }
            }else{
                json.setMsg("传入参数异常");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }
    /**
     * 查看检验设备内容图片
     * @return
     */
    @RequestMapping(value = "/viewTestingEquipmentDetailModal.htm")
    public  String viewTestingEquipmentDetailModal(Long id,HttpServletRequest request){
        PTestingEquipmentDetail testingEquipmentDetailById = traceabilityservice.getTestingEquipmentDetailById(id);
        request.setAttribute("testingEquipmentDetailById",testingEquipmentDetailById);
        return "traceability/viewTestingEquipmentDetailModal";
    }
    /***
     * 删除检验设备内容
     * @param
     * @return
     */
    @RequestMapping(value="/delectTestingEquipmentDetail.json")
    @ResponseBody
    public Object delectTestingEquipmentDetail(HttpServletRequest request, PTestingEquipmentDetail pTestingEquipmentDetail,Long resourceId) {
        Json json = new Json();
        try {
            PTestingEquipmentDetail testingEquipmentDetail = zsTestingEquipmentDetailService.getPTestingEqDetailsByTestingEquipmentDetailId(pTestingEquipmentDetail.getTestingEquipmentDetailId());
            if(null!=testingEquipmentDetail){
                Subject user = SecurityUtils.getSubject();
                ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                pTestingEquipmentDetail.setUpdateUserId(shiroUser.getId());
                if (traceabilityservice.delectTestingEquipmentDetail(pTestingEquipmentDetail)) {
                    SysSysLog sysLog = new SysSysLog();
                    String ip = ClientIP.getClientIP(request);
                    sysLog.setIpAddress(ip);
                    sysLog.setCName("删除检验室内容");
                    sysLog.setRemark("删除("+testingEquipmentDetail.getTestingEquipmentDetailCname()+")检验室内容");
                    sysLog.setResourceId(resourceId);
                    sysLog.setCreatedUserId(shiroUser.getId());
                    sysSysLogService.addSysSysLog(sysLog);
                    json.setMsg("删除成功");
                    json.setSuccess(true);
                } else {
                    json.setMsg("删除失败");
                }
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    @RequestMapping(value = "/getCertificateManage.json")
    @ResponseBody
    public Object getCertificateManage(HttpServletRequest request,Integer page,Integer rows){
        List<PZsCertificateManage> certificateManageList = zsCertificateManageService.getCertificateManageForGrid(page,rows);
        PageInfo<PZsCertificateManage> pageInfo = new PageInfo<>(certificateManageList);
        return new JqGridParam(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getPages(),certificateManageList);
    }

    /**
     *新增证书modal
     * @param request
     * @return
     */
    @RequestMapping(value = "/addCertificateManageModal.htm")
    public String addCertificateManageModal(HttpServletRequest request,Long resourceId){
        request.setAttribute("resourceId",resourceId);
        List<SysProductType> productTypeList = sysProductTypeService.getSysProductTypeByALevel(2);
        if(null!=productTypeList&&0!=productTypeList.size()){
            request.setAttribute("productTypeList",productTypeList);
        }
        List<ZsProductionProcess> productionProcessList = zsProductionProcessService.getAllProductionProcess();
        if(null!=productionProcessList&&0!=productionProcessList.size()){
            request.setAttribute("productionProcessList",productionProcessList);
        }
        return "traceability/addCertificateManageModal";
    }

    /**
     *添加证书
     * @param request
     * @param zsCertificateManage
     * @return
     */
    @RequestMapping(value = "/addCertificateManage.json")
    @ResponseBody
    public Object addCertificateManage(HttpServletRequest request,ZsCertificateManage zsCertificateManage,MultipartFile images1,MultipartFile images2,MultipartFile images3,MultipartFile images4,MultipartFile images5,MultipartFile images6,MultipartFile images7,MultipartFile images8,MultipartFile images9,MultipartFile images10,Long resourceId){
        Json json = new Json();
        try {
            ZsCertificateManage certificateManage = zsCertificateManageService.getCertificateManageBySProductTypeAndName(zsCertificateManage);
            if(null==certificateManage){
                Subject user = SecurityUtils.getSubject();
                ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                zsCertificateManage.setCreateUserId(shiroUser.getId());
                List<ImageJson> imageJsonList = new ArrayList<>();
                if(null != images1 && 0 != images1.getSize()){
                    String fileName =ossClientUtil.uploadImg2Oss(images1);
                    if ("上传图片大小不能超过2M！".equals(fileName)) {
                        json.setMsg("上传图片大小不能超过2M！");
                        return  json;
                    }
                    String url =ossClientUtil.getImgUrl(fileName);
                    ImageJson imageJson = new ImageJson();
                    imageJson.setImageName(fileName);
                    imageJson.setImageUrl(url);
                    imageJsonList.add(imageJson);
                }
                if(null != images2 && 0 != images2.getSize()){
                    String fileName =ossClientUtil.uploadImg2Oss(images2);
                    if ("上传图片大小不能超过2M！".equals(fileName)) {
                        json.setMsg("上传图片大小不能超过2M！");
                        return  json;
                    }
                    String url =ossClientUtil.getImgUrl(fileName);
                    ImageJson imageJson = new ImageJson();
                    imageJson.setImageName(fileName);
                    imageJson.setImageUrl(url);
                    imageJsonList.add(imageJson);
                }
                if(null != images3 && 0 != images3.getSize()){
                    String fileName =ossClientUtil.uploadImg2Oss(images3);
                    if ("上传图片大小不能超过2M！".equals(fileName)) {
                        json.setMsg("上传图片大小不能超过2M！");
                        return  json;
                    }
                    String url =ossClientUtil.getImgUrl(fileName);
                    ImageJson imageJson = new ImageJson();
                    imageJson.setImageName(fileName);
                    imageJson.setImageUrl(url);
                    imageJsonList.add(imageJson);
                }
                if(null != images4 && 0 != images4.getSize()){
                    String fileName =ossClientUtil.uploadImg2Oss(images4);
                    if ("上传图片大小不能超过2M！".equals(fileName)) {
                        json.setMsg("上传图片大小不能超过2M！");
                        return  json;
                    }
                    String url =ossClientUtil.getImgUrl(fileName);
                    ImageJson imageJson = new ImageJson();
                    imageJson.setImageName(fileName);
                    imageJson.setImageUrl(url);
                    imageJsonList.add(imageJson);
                }
                if(null != images5 && 0 != images5.getSize()){
                    String fileName =ossClientUtil.uploadImg2Oss(images5);
                    if ("上传图片大小不能超过2M！".equals(fileName)) {
                        json.setMsg("上传图片大小不能超过2M！");
                        return  json;
                    }
                    String url =ossClientUtil.getImgUrl(fileName);
                    ImageJson imageJson = new ImageJson();
                    imageJson.setImageName(fileName);
                    imageJson.setImageUrl(url);
                    imageJsonList.add(imageJson);
                }
                if(null != images6 && 0 != images6.getSize()){
                    String fileName =ossClientUtil.uploadImg2Oss(images6);
                    if ("上传图片大小不能超过2M！".equals(fileName)) {
                        json.setMsg("上传图片大小不能超过2M！");
                        return  json;
                    }
                    String url =ossClientUtil.getImgUrl(fileName);
                    ImageJson imageJson = new ImageJson();
                    imageJson.setImageName(fileName);
                    imageJson.setImageUrl(url);
                    imageJsonList.add(imageJson);
                }
                if(null != images7 && 0 != images7.getSize()){
                    String fileName =ossClientUtil.uploadImg2Oss(images7);
                    if ("上传图片大小不能超过2M！".equals(fileName)) {
                        json.setMsg("上传图片大小不能超过2M！");
                        return  json;
                    }
                    String url =ossClientUtil.getImgUrl(fileName);
                    ImageJson imageJson = new ImageJson();
                    imageJson.setImageName(fileName);
                    imageJson.setImageUrl(url);
                    imageJsonList.add(imageJson);
                }
                if(null != images8 && 0 != images8.getSize()){
                    String fileName =ossClientUtil.uploadImg2Oss(images8);
                    if ("上传图片大小不能超过2M！".equals(fileName)) {
                        json.setMsg("上传图片大小不能超过2M！");
                        return  json;
                    }
                    String url =ossClientUtil.getImgUrl(fileName);
                    ImageJson imageJson = new ImageJson();
                    imageJson.setImageName(fileName);
                    imageJson.setImageUrl(url);
                    imageJsonList.add(imageJson);
                }
                if(null != images9 && 0 != images9.getSize()){
                    String fileName =ossClientUtil.uploadImg2Oss(images9);
                    if ("上传图片大小不能超过2M！".equals(fileName)) {
                        json.setMsg("上传图片大小不能超过2M！");
                        return  json;
                    }
                    String url =ossClientUtil.getImgUrl(fileName);
                    ImageJson imageJson = new ImageJson();
                    imageJson.setImageName(fileName);
                    imageJson.setImageUrl(url);
                    imageJsonList.add(imageJson);
                }
                if(null != images10 && 0 != images10.getSize()){
                    String fileName =ossClientUtil.uploadImg2Oss(images10);
                    if ("上传图片大小不能超过2M！".equals(fileName)) {
                        json.setMsg("上传图片大小不能超过2M！");
                        return  json;
                    }
                    String url =ossClientUtil.getImgUrl(fileName);
                    ImageJson imageJson = new ImageJson();
                    imageJson.setImageName(fileName);
                    imageJson.setImageUrl(url);
                    imageJsonList.add(imageJson);
                }
                if(imageJsonList.size()<10){
                    for(int i = imageJsonList.size(); i < 10; i++){
                        ImageJson imageJson = new ImageJson();
                        imageJson.setImageName("");
                        imageJson.setImageUrl("");
                        imageJsonList.add(imageJson);
                    }
                }
                if(null!=imageJsonList&&0!=imageJsonList.size()){
                    String jsonText = JSON.toJSONString(imageJsonList,true);
                    zsCertificateManage.setCertificateImage(jsonText);
                }
                if(zsCertificateManageService.addCertificateManage(zsCertificateManage)){
                    SysSysLog sysLog = new SysSysLog();
                    String ip = ClientIP.getClientIP(request);
                    sysLog.setIpAddress(ip);
                    sysLog.setCName("添加证书管理");
                    sysLog.setRemark("添加("+zsCertificateManage.getProductionLicense()+"证书管理");
                    sysLog.setResourceId(resourceId);
                    sysLog.setCreatedUserId(shiroUser.getId());
                    sysSysLogService.addSysSysLog(sysLog);
                    json.setMsg("添加成功");
                    json.setSuccess(true);
                }else {
                    json.setMsg("添加失败");
                }
            }else {
                json.setMsg("同一产品小类下，生产许可证不能相同");
            }
        }catch (Exception e){
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 删除证书
     * @param request
     * @param zsCertificateManage
     * @return
     */
    @RequestMapping(value = "/deleteCertificateManage.json")
    @ResponseBody
    public Object deleteCertificateManage(HttpServletRequest request,ZsCertificateManage zsCertificateManage, Long resourceId){
            Json json =  new Json();
            try {
                ZsCertificateManage certificateManage = zsCertificateManageService.getCertificateManageByCertificateManageId(zsCertificateManage.getCertificateManageId());
                if(null!=certificateManage){
                    Subject user = SecurityUtils.getSubject();
                    ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                    zsCertificateManage.setUpdateUserId(shiroUser.getId());
                    if(zsCertificateManageService.deleteCertificateManageById(zsCertificateManage)){
                        SysSysLog sysLog = new SysSysLog();
                        String ip = ClientIP.getClientIP(request);
                        sysLog.setIpAddress(ip);
                        sysLog.setCName("删除证书");
                        sysLog.setRemark("删除("+certificateManage.getProductionLicense()+"证书");
                        sysLog.setResourceId(resourceId);
                        sysLog.setCreatedUserId(shiroUser.getId());
                        sysSysLogService.addSysSysLog(sysLog);
                        json.setMsg("删除成功");
                        json.setSuccess(true);
                    }else {
                        json.setMsg("删除失败");
                    }
                }
            }catch (Exception e){
                json.setMsg("服务器异常");
            }
            return json;
    }

    /**
     * 获取生产过程详情
     * @param request
     * @param productionProcess
     * @return
     */
    @RequestMapping(value = "/getDetailByProductionProcess.json")
    @ResponseBody
    public Object getDetailByProductionProcess(HttpServletRequest request,String productionProcess ){
        Json json = new Json();
        try {
            ZsProductionProcess zsProductionProcess = zsProductionProcessService.getProductionProcessByName(productionProcess);
            if(null!=zsProductionProcess){
                List<ZsProductionProcessDetail> detailList = zsProductionProcessDetailService.getProdutionProcessDetailByProductionProcessId(zsProductionProcess.getProductionProcessId());
                if(null!=detailList&&0!=detailList.size()){
                    json.setObj(detailList);
                }
            }
            request.setAttribute("productionProcess",productionProcess);
        }catch (Exception e){
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 修改证书modal
     * @param request
     * @param certificateManageId
     * @return
     */
    @RequestMapping(value = "/updateCertificateManageModal.htm")
    public String updateCertificateManageModal(HttpServletRequest request,Long certificateManageId, Long resourceId){
        request.setAttribute("resourceId",resourceId);
        if(null!=certificateManageId){
            PZsCertificateManage pZsCertificateManage = zsCertificateManageService.getCertificateManageByCertificateManageId(certificateManageId);
            if(null!=pZsCertificateManage){
                if(null!=pZsCertificateManage.getCertificateImage()&&""!=pZsCertificateManage.getCertificateImage()){
                    Type type = new TypeReference<List<ImageJson>>() {}.getType();
                    List<ImageJson> imageJsonList = JSON.parseObject(pZsCertificateManage.getCertificateImage(),type);
                    pZsCertificateManage.setImageJsonList(imageJsonList);
                }
                request.setAttribute("pZsCertificateManage",pZsCertificateManage);
                List<SysProductType> productTypeList = sysProductTypeService.getSysProductTypeByALevel(2);
                if(null!=productTypeList&&0!=productTypeList.size()){
                    request.setAttribute("productTypeList",productTypeList);
                }
                List<SysProductType> smallProductTypes = sysProductTypeService.getSysProductTypeByParentId(pZsCertificateManage.getBigProductTypeId());
                if(null!=smallProductTypes&&0!=smallProductTypes.size()){
                    request.setAttribute("smallProductTypes",smallProductTypes);
                }
                List<ZsProductionProcess> productionProcessList = zsProductionProcessService.getAllProductionProcess();
                if(null!=productionProcessList&&0!=productionProcessList.size()){
                    request.setAttribute("productionProcessList",productionProcessList);
                }
            }
        }
        return "traceability/updateCertificateManageModal";
    }


    /**
     * 修改证书
     * @param request
     * @param zsCertificateManage
     * @return
     */
    @RequestMapping(value = "/updateCertificateManage.json")
    @ResponseBody
    public Object updateCertificateManage(HttpServletRequest request,ZsCertificateManage zsCertificateManage,String imagesId,List<MultipartFile> imageList, Long resourceId){
            Json json = new Json();
            try {
                ZsCertificateManage certificateManage = zsCertificateManageService.getCertificateManageBySProductTypeAndName(zsCertificateManage);
                if(null==certificateManage||zsCertificateManage.getCertificateManageId().equals(certificateManage.getCertificateManageId())){
                    Subject user = SecurityUtils.getSubject();
                    ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                    zsCertificateManage.setUpdateUserId(shiroUser.getId());
                    List<ImageJson> imageJsonList = new ArrayList<>();
                    Type type = new TypeReference<List<ImageJson>>() {}.getType();
                    PZsCertificateManage pZsCertificateManage = zsCertificateManageService.getCertificateManageByCertificateManageId(zsCertificateManage.getCertificateManageId());
                    if(null!=pZsCertificateManage.getCertificateImage()){
                        imageJsonList = JSON.parseObject(pZsCertificateManage.getCertificateImage(),type);
                        for(int i = 0; i < 10; i++) {
                            if (imagesId.contains("" + i)) {
                                imageJsonList.get(i).setImageName("");
                                imageJsonList.get(i).setImageUrl("");
                            }
                        }
                        //删除空图片url
                        Iterator<ImageJson> iterator = imageJsonList.iterator();
                        while (iterator.hasNext()){
                            ImageJson str = iterator.next();
                            if("".equals(str.getImageUrl())){
                                iterator.remove();
                            }
                        }
                        if (null !=imagesId&&!"".equals(imagesId)) {
                            if(null!=imageList&&0!=imageList.size()){
                                for(int i = 0; i < imageList.size(); i++){
                                    MultipartFile file = imageList.get(i);
                                    if(null!=file&&0!=file.getSize()){
                                        String imageName = ossClientUtil.uploadImg2Oss(file);
                                        if("上传图片大小不能超过2M".equals(imageName)){
                                            json.setMsg("上传图片大小不能超过2M");
                                            return json;
                                        }
                                        String imageUrl = ossClientUtil.getImgUrl(imageName);
                                        ImageJson imageJson = new ImageJson();
                                        imageJson.setImageName(imageName);
                                        imageJson.setImageUrl(imageUrl);
                                        imageJsonList.add(imageJson);
                                    }
                                }
                            }
                        }
                    }else {
                        if(null==imageJsonList){
                            imageJsonList = new ArrayList<>();
                            for(int i = 0; i < imageList.size(); i++){
                                MultipartFile file = imageList.get(i);
                                if(null!=file&&0!=file.getSize()){
                                    String imageName = ossClientUtil.uploadImg2Oss(file);
                                    if("上传图片大小不能超过2M".equals(imageName)){
                                        json.setMsg("上传图片大小不能超过2M");
                                        return json;
                                    }
                                    String imageUrl = ossClientUtil.getImgUrl(imageName);
                                    ImageJson imageJson = new ImageJson();
                                    imageJson.setImageName(imageName);
                                    imageJson.setImageUrl(imageUrl);
                                    imageJsonList.add(imageJson);
                                }
                            }
                        }
                    }
                    if(imageJsonList.size()<10){
                        for(int i = imageJsonList.size(); i < 10; i++){
                            ImageJson imageJson = new ImageJson();
                            imageJson.setImageName("");
                            imageJson.setImageUrl("");
                            imageJsonList.add(imageJson);
                        }
                    }
                    String certificateImage = JSON.toJSONString(imageJsonList,true);
                    zsCertificateManage.setCertificateImage(certificateImage);
                    if(zsCertificateManageService.updateCertificateManage(zsCertificateManage)){
                        SysSysLog sysLog = new SysSysLog();
                        String ip = ClientIP.getClientIP(request);
                        sysLog.setIpAddress(ip);
                        sysLog.setCName("修改证书");
                        sysLog.setRemark("修改("+zsCertificateManage.getProductionLicense()+"证书");
                        sysLog.setResourceId(resourceId);
                        sysLog.setCreatedUserId(shiroUser.getId());
                        sysSysLogService.addSysSysLog(sysLog);
                        json.setMsg("修改成功");
                        json.setSuccess(true);
                    }else {
                        json.setMsg("修改失败");
                    }
                }else {
                    json.setMsg("同一产品小类下，生产许可证不能相同");
                }
            }catch (Exception e){
                json.setMsg("服务器异常");
            }
            return json;
    }

    /**
     * 查看证书
     * @param request
     * @param certificateManageId
     * @return
     */
    @RequestMapping(value = "/certificateManageDetailModal.htm")
    public String certificateManageDetailModal(HttpServletRequest request,Long certificateManageId){
        if(null!=certificateManageId){
            PZsCertificateManage pZsCertificateManage = zsCertificateManageService.getCertificateManageByCertificateManageId(certificateManageId);
            if(null!=pZsCertificateManage){
                if(null!=pZsCertificateManage.getCertificateImage()){
                    Type type = new TypeReference<List<ImageJson>>() {}.getType();
                    List<ImageJson> imageJsonList = JSON.parseObject(pZsCertificateManage.getCertificateImage(),type);
                    Iterator<ImageJson> iterator = imageJsonList.iterator();
                    while(iterator.hasNext()){
                        ImageJson imageJson = iterator.next();
                        if("".equals(imageJson.getImageUrl())){
                            iterator.remove();
                        }
                    }
                    if(null!=imageJsonList&&0!=imageJsonList.size()){
                        pZsCertificateManage.setImageJsonList(imageJsonList);
                    }
                }
                request.setAttribute("pZsCertificateManage",pZsCertificateManage);
            }
        }
        return "traceability/certificateManageDetailModal";
    }

    /**
     * 证书管理增加内容modal
     * @param request
     * @param certificateManageId
     * @return
     */
    @RequestMapping(value = "/addCertificateContentModal.htm")
    public String addCertificateContentModal(HttpServletRequest request, Long certificateManageId, Long resourceId){
        if(null!=certificateManageId){
            request.setAttribute("resourceId",resourceId);
            ZsCertificateManage zsCertificateManage = zsCertificateManageService.getCertificateManageByCertificateManageId(certificateManageId);
            if(null!=zsCertificateManage){
                request.setAttribute("zsCertificateManage",zsCertificateManage);
            }
        }
        return "traceability/addCertificateContentModal";
    }

    /**
     *证书管理增加内容
     * @param request
     * @param zsCertificateManage
     * @return
     */
    @RequestMapping(value = "/addCertificateContent.json")
    @ResponseBody
    public Object addCertificateContent(HttpServletRequest request, ZsCertificateManage zsCertificateManage, Long resourceId){
        Json json = new Json();
        try {
            ZsCertificateManage certificateManage = zsCertificateManageService.getCertificateManageByCertificateManageId(zsCertificateManage.getCertificateManageId());
            if(null!=certificateManage){
                Subject user = SecurityUtils.getSubject();
                ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                zsCertificateManage.setUpdateUserId(shiroUser.getId());
                if(zsCertificateManageService.addCertificateContent(zsCertificateManage)){
                    SysSysLog sysLog = new SysSysLog();
                    String ip = ClientIP.getClientIP(request);
                    sysLog.setIpAddress(ip);
                    sysLog.setCName("添加证书内容");
                    sysLog.setRemark("添加("+certificateManage.getProductionLicense()+"证书内容");
                    sysLog.setResourceId(resourceId);
                    sysLog.setCreatedUserId(shiroUser.getId());
                    sysSysLogService.addSysSysLog(sysLog);
                    json.setMsg("添加成功");
                    json.setSuccess(true);
                }else {
                    json.setMsg("添加失败");
                }
            }else {
                json.setMsg("传入数据异常，无法添加");
            }
        }catch (Exception e){
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 获取生产过程
     * @return
     */
    @RequestMapping(value = "getProductionProcess.json")
    @ResponseBody
    public Object getProductionProcess(HttpServletRequest request,Integer page, Integer rows){
        List<PZsProductionProcess> productionProcessList = zsProductionProcessService.getAllProductionProcessForGrid(page,rows);
        PageInfo<PZsProductionProcess> pageInfo = new PageInfo<>(productionProcessList);
        return new JqGridParam(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), productionProcessList);
    }

    /**
     * 生产过程详情
     * @param request
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/getProductionProcessDetail.json")
    @ResponseBody
    public Object getProductionProcessDetail(HttpServletRequest request, Integer page, Integer rows){
        List<PZsProductionProcessDetail> productionProcessDetailList = zsProductionProcessDetailService.getAllProductionProcessDetailForGrid(page,rows);
        PageInfo<PZsProductionProcessDetail> pageInfo = new PageInfo<>(productionProcessDetailList);
        return new JqGridParam(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), productionProcessDetailList);
    }

    /**
     * 新增生产过程modal
     * @param request
     * @return
     */
    @RequestMapping(value = "/addProductionProcessModal.htm")
    public String addProductionProcessModal(HttpServletRequest request,  Long resourceId){
        request.setAttribute("resourceId", resourceId);
        return "traceability/addProductionProcessModal";
    }

    /**
     * 添加生产过程
     * @param request
     * @param zsProductionProcess
     * @return
     */
    @RequestMapping(value = "/addProductionProcess.json")
    @ResponseBody
    public Object addProductionProcess(HttpServletRequest request, ZsProductionProcess zsProductionProcess, Long resourceId){
        Json json = new Json();
        try {
            if(null!=zsProductionProcess){
                ZsProductionProcess productionProcess = zsProductionProcessService.getProductionProcessByName(zsProductionProcess.getProductionProcessName());
                if(null==productionProcess){
                    Subject user = SecurityUtils.getSubject();
                    ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                    zsProductionProcess.setCreateUserId(shiroUser.getId());
                    if(zsProductionProcessService.addZsProductionProcess(zsProductionProcess)){
                        SysSysLog sysLog = new SysSysLog();
                        String ip = ClientIP.getClientIP(request);
                        sysLog.setIpAddress(ip);
                        sysLog.setCName("添加生产过程");
                        sysLog.setRemark("添加("+zsProductionProcess.getProductionProcessName()+")生产过程");
                        sysLog.setResourceId(resourceId);
                        sysLog.setCreatedUserId(shiroUser.getId());
                        sysSysLogService.addSysSysLog(sysLog);
                        json.setSuccess(true);
                        json.setMsg("添加成功");
                    }else {
                        json.setMsg("添加失败");
                    }
                }else{
                    json.setMsg("该生产过程已经存在");
                }
            }else {
                json.setMsg("数据异常，无法添加");
            }
        }catch (Exception e){
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 修改生产过程
     * @param request
     * @param productionProcessId
     * @return
     */
    @RequestMapping(value = "/updProductionProcessModal.htm")
    public String updProductionProcessModal(HttpServletRequest request,Long productionProcessId, Long resourceId){
        request.setAttribute("resourceId",resourceId);
        ZsProductionProcess zsProductionProcess = zsProductionProcessService.getProductionProcessById(productionProcessId);
        if(null!=zsProductionProcess){
            request.setAttribute("zsProductionProcess",zsProductionProcess);
        }
        return "traceability/updProductionProcessModal";
    }

    /**
     *修改生产过程
     * @param request
     * @param zsProductionProcess
     * @return
     */
    @RequestMapping(value = "/updProductionProcess.json")
    @ResponseBody
    public Object updProductionProcess(HttpServletRequest request,ZsProductionProcess zsProductionProcess, Long resourceId){
        Json json = new Json();
        try {
            if(null!=zsProductionProcess){
                ZsProductionProcess productionProcess = zsProductionProcessService.getProductionProcessByName(zsProductionProcess.getProductionProcessName());
                if(null==productionProcess||productionProcess.getProductionProcessId().equals(zsProductionProcess.getProductionProcessId())){
                    Subject user = SecurityUtils.getSubject();
                    ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                    zsProductionProcess.setUpdateUserId(shiroUser.getId());
                    if(zsProductionProcessService.updateProductionProcess(zsProductionProcess)){
                        SysSysLog sysLog = new SysSysLog();
                        String ip = ClientIP.getClientIP(request);
                        sysLog.setIpAddress(ip);
                        sysLog.setCName("编辑生产过程");
                        sysLog.setRemark("编辑("+zsProductionProcess.getProductionProcessName()+")生产过程");
                        sysLog.setResourceId(resourceId);
                        sysLog.setCreatedUserId(shiroUser.getId());
                        sysSysLogService.addSysSysLog(sysLog);
                        json.setSuccess(true);
                        json.setMsg("修改成功");
                    }else {
                        json.setMsg("修改失败");
                    }
                }else {
                    json.setMsg("该生产过程已经存在");
               }
            }else {
                json.setMsg("数据异常，无法修改");
            }
        }catch (Exception e){
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 删除生产过程
     * @param request
     * @param zsProductionProcess
     * @return
     */
    @RequestMapping(value = "/deleteProductionProcess.json")
    @ResponseBody
    public Object deleteProductionProcess(HttpServletRequest request, ZsProductionProcess zsProductionProcess, Long resourceId){
        Json json = new Json();
        try {
            ZsProductionProcess productionProcess = zsProductionProcessService.getProductionProcessById(zsProductionProcess.getProductionProcessId());
            if(null!=productionProcess){
                Subject user = SecurityUtils.getSubject();
                ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                zsProductionProcess.setUpdateUserId(shiroUser.getId());
                if(zsProductionProcessService.deleteProductionProcess(zsProductionProcess)){
                    SysSysLog sysLog = new SysSysLog();
                    String ip = ClientIP.getClientIP(request);
                    sysLog.setIpAddress(ip);
                    sysLog.setCName("删除生产过程");
                    sysLog.setRemark("删除("+productionProcess.getProductionProcessName()+")生产过程");
                    sysLog.setResourceId(resourceId);
                    sysLog.setCreatedUserId(shiroUser.getId());
                    sysSysLogService.addSysSysLog(sysLog);
                    json.setSuccess(true);
                    json.setMsg("删除成功");
                }else {
                    json.setMsg("删除失败");
                }
            }else {
                json.setMsg("传入参数异常，无法删除");
            }
        }catch (Exception e){
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 增加详情
     * @param request
     * @param productionProcessId
     * @return
     */
    @RequestMapping(value = "/addProductionProcessDetailModal.htm")
    public String addProductionProcessDetailModal(HttpServletRequest request, Long productionProcessId, Long resourceId){
        request.setAttribute("resourceId",resourceId);
        if(null!=productionProcessId){
            ZsProductionProcess zsProductionProcess = zsProductionProcessService.getProductionProcessById(productionProcessId);
            if(null!=zsProductionProcess){
                request.setAttribute("zsProductionProcess",zsProductionProcess);
            }
        }
        return "traceability/addProductionProcessDetailModal";
    }

    @RequestMapping(value = "/addProductionProcessDetail.json")
    @ResponseBody
    public Object addProductionProcessDetail(HttpServletRequest request, ZsProductionProcessDetail zsProductionProcessDetail, MultipartFile image, Long resourceId){
        Json json = new Json();
        try {
            if(null!=zsProductionProcessDetail){
                ZsProductionProcessDetail productionProcessDetail = zsProductionProcessDetailService.getProductionProcessDetailByProductionProcessIdAndName(zsProductionProcessDetail);
                if(null==productionProcessDetail){
                    if(null!=image&&0!=image.getSize()){
                        String imageName = ossClientUtil.uploadImg2Oss(image);
                        if(null==imageName){
                            json.setMsg("图片上传失败");
                        }else {
                            if("上传图片大小不能超过2M！".equals(imageName)){
                                json.setMsg("上传图片大小不能超过2M");
                                return json;
                            }else {
                                zsProductionProcessDetail.setDetailImage(ossClientUtil.getImgUrl(imageName));
                            }
                        }
                    }
                    Subject user = SecurityUtils.getSubject();
                    ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                    zsProductionProcessDetail.setCreateUserId(shiroUser.getId());
                    if(zsProductionProcessDetailService.addProductionProcessDetail(zsProductionProcessDetail)){
                        SysSysLog sysLog = new SysSysLog();
                        String ip = ClientIP.getClientIP(request);
                        sysLog.setIpAddress(ip);
                        sysLog.setCName("增加生产过程详情");
                        sysLog.setRemark("增加("+zsProductionProcessDetail.getProductionProcessDetailName()+")生产过程详情");
                        sysLog.setResourceId(resourceId);
                        sysLog.setCreatedUserId(shiroUser.getId());
                        sysSysLogService.addSysSysLog(sysLog);
                        json.setMsg("添加成功");
                        json.setSuccess(true);
                    }else {
                        json.setMsg("添加失败");
                    }
                }else {
                    json.setMsg("该生产过程下已经存在该详情");
                }
            }else {
                json.setMsg("传入参数异常，无法添加");
            }
        }catch (Exception e){
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     *修改生产过程详情modal
     * @param request
     * @param productionProcessDetailedId
     * @return
     */
    @RequestMapping(value = "/updateProductionProcessDetailModal.htm")
    public String updateProductionProcessDetailModal(HttpServletRequest request,Long productionProcessDetailedId, Long resourceId){
        request.setAttribute("resourceId",resourceId);
        if(null!=productionProcessDetailedId){
            ZsProductionProcessDetail zsProductionProcessDetail = zsProductionProcessDetailService.getProductionProcessDetailById(productionProcessDetailedId);
            if(null!=zsProductionProcessDetail){
                request.setAttribute("zsProductionProcessDetail",zsProductionProcessDetail);
            }
        }
        List<ZsProductionProcess> productionProcessList = zsProductionProcessService.getAllProductionProcess();
        if(null!=productionProcessList&&0!=productionProcessList.size()){
            request.setAttribute("productionProcessList",productionProcessList);
        }
        return "traceability/updateProductionProcessDetailModal";
    }

    /**
     * 修改生产过程详情
     * @param request
     * @param zsProductionProcessDetail
     * @param updProductionProcessDetailImageId
     * @param updProductionProcessDetailImage
     * @return
     */
    @RequestMapping(value = "/updateProductionProcessDetail.json")
    @ResponseBody
    public Object updateProductionProcessDetail(HttpServletRequest request, ZsProductionProcessDetail zsProductionProcessDetail, String updProductionProcessDetailImageId, MultipartFile updProductionProcessDetailImage, Long resourceId){
            Json json = new Json();
            try {
                ZsProductionProcessDetail productionProcessDetail = zsProductionProcessDetailService.getProductionProcessDetailById(zsProductionProcessDetail.getProductionProcessDetailedId());
                if(null!=productionProcessDetail){
                    ZsProductionProcessDetail processDetail = zsProductionProcessDetailService.getProductionProcessDetailByProductionProcessIdAndName(zsProductionProcessDetail);
                    if(null==processDetail||(processDetail.getProductionProcessDetailedId()).equals(zsProductionProcessDetail.getProductionProcessDetailedId())){
                        if(null!=updProductionProcessDetailImageId&&!"".equals(updProductionProcessDetailImageId)){
                            if(null!=updProductionProcessDetailImage&&0!=updProductionProcessDetailImage.getSize()){
                                String imageName = ossClientUtil.uploadImg2Oss(updProductionProcessDetailImage);
                                if(null==imageName){
                                    json.setMsg("传入图片失败");
                                    return json;
                                }else {
                                    if("上传图片大小不能超过2M！".equals(imageName)){
                                        json.setMsg("上传图片大小不能超过2M！");
                                        return json;
                                    }else {
                                        zsProductionProcessDetail.setDetailImage(ossClientUtil.getImgUrl(imageName));
                                    }
                                }
                            }else {
                                zsProductionProcessDetail.setDetailImage(ossClientUtil.getImgUrl(""));
                            }
                        }else {
                            zsProductionProcessDetail.setDetailImage(productionProcessDetail.getDetailImage());
                        }
                        Subject user = SecurityUtils.getSubject();
                        ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                        zsProductionProcessDetail.setUpdateUserId(shiroUser.getId());
                        if(zsProductionProcessDetailService.updateProductionProcessDetail(zsProductionProcessDetail)){
                            SysSysLog sysLog = new SysSysLog();
                            String ip = ClientIP.getClientIP(request);
                            sysLog.setIpAddress(ip);
                            sysLog.setCName("编辑生产过程详情");
                            sysLog.setRemark("编辑("+zsProductionProcessDetail.getProductionProcessDetailName()+")生产过程详情");
                            sysLog.setResourceId(resourceId);
                            sysLog.setCreatedUserId(shiroUser.getId());
                            sysSysLogService.addSysSysLog(sysLog);
                            json.setMsg("修改成功");
                            json.setSuccess(true);
                        }else {
                            json.setMsg("修改失败");
                        }
                    }else {
                        json.setMsg("该生产过程下已经存在此详情");
                    }
                }else {
                    json.setMsg("传入参数异常，无法修改");
                }
            }catch (Exception e){
                json.setMsg("服务器异常");
            }
            return json;
    }

    /**
     *删除生产过程详情
     * @param request
     * @param zsProductionProcessDetail
     * @return
     */
    @RequestMapping(value = "/deleteProductionProcessDetail.json")
    @ResponseBody
    public Object deleteProductionProcessDetail(HttpServletRequest request, ZsProductionProcessDetail zsProductionProcessDetail, Long resourceId){
        Json json = new Json();
        try {
            ZsProductionProcessDetail productionProcessDetail = zsProductionProcessDetailService.getProductionProcessDetailById(zsProductionProcessDetail.getProductionProcessDetailedId());
            if(null!=productionProcessDetail){
                Subject user = SecurityUtils.getSubject();
                ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                zsProductionProcessDetail.setUpdateUserId(shiroUser.getId());
                if(zsProductionProcessDetailService.deleteProductionProcessDetail(zsProductionProcessDetail)){
                    SysSysLog sysLog = new SysSysLog();
                    String ip = ClientIP.getClientIP(request);
                    sysLog.setIpAddress(ip);
                    sysLog.setCName("删除生产过程详情");
                    sysLog.setRemark("删除("+productionProcessDetail.getProductionProcessDetailName()+")生产过程详情");
                    sysLog.setResourceId(resourceId);
                    sysLog.setCreatedUserId(shiroUser.getId());
                    sysSysLogService.addSysSysLog(sysLog);
                    json.setMsg("删除成功");
                    json.setSuccess(true);
                }else {
                    json.setMsg("删除失败");
                }
            }else {
                json.setMsg("传入参数异常");
            }
        }catch (Exception e){
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 生产过程详情查看图片
     * @param request
     * @param productionProcessDetailedId
     * @return
     */
    @RequestMapping(value = "/viewProductionProcessDetailModal.htm")
    private String viewProductionProcessDetailModal(HttpServletRequest request, Long productionProcessDetailedId){
        if(null!=productionProcessDetailedId){
            ZsProductionProcessDetail zsProductionProcessDetail = zsProductionProcessDetailService.getProductionProcessDetailById(productionProcessDetailedId);
            if(null!=zsProductionProcessDetail){
                request.setAttribute("zsProductionProcessDetail",zsProductionProcessDetail);
            }
        }
        return "traceability/viewProductionProcessDetailModal";
    }

    @RequestMapping(value = "/addProductionProcessDetailTwoModal.htm")
    public String addProductionProcessDetailModalTwo(HttpServletRequest request, Long resourceId){
        request.setAttribute("resourceId",resourceId);
        List<ZsProductionProcess> productionProcessList = zsProductionProcessService.getAllProductionProcess();
        if(null!=productionProcessList&&0!=productionProcessList.size()){
            request.setAttribute("productionProcessList",productionProcessList);
        }
        return "traceability/addProductionProcessDetailModalTwo";
    }
}
