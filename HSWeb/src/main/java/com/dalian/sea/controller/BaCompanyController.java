package com.dalian.sea.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalian.sea.ClientIP;
import com.dalian.sea.DTO.TreeJsonDTO;
import com.dalian.sea.FileUpload.OSSClientUtil;
import com.dalian.sea.common.HandleHelper;
import com.dalian.sea.config.shiro.ShiroUser;
import com.dalian.sea.json.ContentJson;
import com.dalian.sea.json.ImageJson;
import com.dalian.sea.json.JqGridParam;
import com.dalian.sea.json.Json;
import com.dalian.sea.model.BaCompany;
import com.dalian.sea.model.SysSysLog;
import com.dalian.sea.parameter.PBaCompany;
import com.dalian.sea.service.BaCompanyService;
import com.dalian.sea.service.SysSysLogService;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * BaCompanyController
 *
 * @author xintao
 * @date 2018/1/15
 */
@Slf4j
@Controller
@RequestMapping(value = "/company")
public class BaCompanyController extends LayoutRazor{
    @Autowired
    private BaCompanyService baCompanyService;

    @Autowired
    private OSSClientUtil ossClientUtil;

    @Autowired
    private SysSysLogService sysSysLogService;

    /**
     * 公司管理
     * @return
     */
    @RequestMapping(value = "/companyManage.htm")
    public String companyManage(HttpServletRequest request){
        return freeMarkerIndexResult("company/companyManage.ftl",request);
    }

    /**
     * 公司
     * @return
     */
    @RequestMapping(value = "/addContentModal.htm")
    public String addContent(HttpServletRequest request,Long id){
        PBaCompany company = baCompanyService.getBaCompanyById(id);
        if(null!=company){
            request.setAttribute("company",company);
        }
        return "/company/addContentModal";
    }

    /**
     * 公司详情
     * @return
     */
    @RequestMapping(value = "/companyDetailIFrame.htm")
    public String companyDetailIFrame(HttpServletRequest request,Long id){
        PBaCompany company = baCompanyService.getBaCompanyById(id);
        if(null!=company){
            if (null!=company.getSeaAreaImages()) {
                Type type = new TypeReference<List<ImageJson>>() {}.getType();
                List<ImageJson> imageList = JSON.parseObject(company.getSeaAreaImages(), type);
                company.setSeaAreaImagesJson(imageList);
            }
            if (null!=company.getEnterpriseImages()) {
                Type type = new TypeReference<List<ImageJson>>() {}.getType();
                List<ImageJson> imageList = JSON.parseObject(company.getEnterpriseImages(), type);
                company.setEnterpriseImagesJson(imageList);
            }
            if (null!=company.getPanorama()) {
                Type type = new TypeReference<List<ImageJson>>() {}.getType();
                List<ImageJson> imageList = JSON.parseObject(company.getPanorama(), type);
                company.setPanoramaJson(imageList);
            }
            if (null!=company.getDetectionCenterImages()) {
                Type type = new TypeReference<List<ImageJson>>() {}.getType();
                List<ImageJson> imageList = JSON.parseObject(company.getDetectionCenterImages(), type);
                company.setDetectionCenterImagesJson(imageList);
            }
            if (null!=company.getAddContent()) {
                Type type = new TypeReference<List<ContentJson>>() {}.getType();
                List<ContentJson> contentJsonList = JSON.parseObject(company.getAddContent(), type);
                company.setAddContentJson(contentJsonList);
            }
            request.setAttribute("company",company);
        }
        return freeMarkerIndexResult("company/companyDetailIFrame.ftl",request);
    }

    /**
     * 修改公司
     * @return
     */
    @RequestMapping(value = "/updateCompanyIFrame.htm")
    public String updateCompanyIFrame(HttpServletRequest request,Long id,Long resourceId){
        request.setAttribute("resourceId",resourceId);
        PBaCompany company = baCompanyService.getBaCompanyById(id);
        if(null!=company){
            if (null!=company.getSeaAreaImages()) {
                Type type = new TypeReference<List<ImageJson>>() {}.getType();
                List<ImageJson> imageList = JSON.parseObject(company.getSeaAreaImages(), type);
                company.setSeaAreaImagesJson(imageList);
            }
            if (null!=company.getEnterpriseImages()) {
                Type type = new TypeReference<List<ImageJson>>() {}.getType();
                List<ImageJson> imageList = JSON.parseObject(company.getEnterpriseImages(), type);
                company.setEnterpriseImagesJson(imageList);
            }
            if (null!=company.getPanorama()) {
                Type type = new TypeReference<List<ImageJson>>() {}.getType();
                List<ImageJson> imageList = JSON.parseObject(company.getPanorama(), type);
                company.setPanoramaJson(imageList);
            }
            if (null!=company.getDetectionCenterImages()) {
                Type type = new TypeReference<List<ImageJson>>() {}.getType();
                List<ImageJson> imageList = JSON.parseObject(company.getDetectionCenterImages(), type);
                company.setDetectionCenterImagesJson(imageList);
            }
            if (null!=company.getAddContent()) {
                Type type = new TypeReference<List<ContentJson>>() {}.getType();
                List<ContentJson> contentJsonList = JSON.parseObject(company.getAddContent(), type);
                company.setAddContentJson(contentJsonList);
            }
            request.setAttribute("company",company);
        }
        return freeMarkerIndexResult("company/updateCompanyIFrame.ftl",request);
    }

    /**
     * 获取所有父级ID为0的数据
     * @param request
     * @return
     */
    @RequestMapping(value = "/getBaCompanyByPidZero.json")
    @ResponseBody
    public String getBaCompanyByPidZero(HttpServletRequest request) {
        List<TreeJsonDTO> treeList = new ArrayList<>();
        try {
            List<PBaCompany> baResourceList = baCompanyService.getBaCompanyByPId((long) 0);
            for(BaCompany item : baResourceList){
                TreeJsonDTO tree = new TreeJsonDTO();
                tree.setId(String.valueOf(item.getCompanyId()));
                tree.setText(item.getCName());
                tree.setValue(String.valueOf(item.getCompanyId()));
                tree.setAttribute("Category");
                tree.setAttributeValue(String.valueOf(item.getCategory()));
                tree.setShowcheck(false);
                tree.setIsexpand(true);
                tree.setComplete(true);
                tree.setParentId(String.valueOf(item.getParentId()));
                tree.setImg(item.getParentId()==0? "/images/Icon16/molecule.png":"/images/Icon16/hostname.png");
                tree.setHasChildren(baResourceList.stream().filter(t -> Objects.equals(t.getParentId(), item.getCompanyId())).count() > 0);
                treeList.add(tree);
            }
        }catch (Exception e){
            log.info(String.valueOf(e));
        }
        return HandleHelper.TreeToJson2(treeList, "0");
    }

    /**
     * 公司管理
     * @param request
     * @return
     */
    @RequestMapping(value = "/GridJson.json")
    @ResponseBody
    public Object GridJson(HttpServletRequest request) {
        List<PBaCompany> companyList = baCompanyService.getBaCompanyByPId((long) 0);
        return new JqGridParam(1,1, (long) 1,0,companyList);
    }

    /**
     * 添加内容
     * @param request
     * @return
     */
    @RequestMapping(value = "/addContent.json")
    @ResponseBody
    public Object addContent(HttpServletRequest request,PBaCompany company,Long resourceId) {
        Json json = new Json();
        try {
            Subject user = SecurityUtils.getSubject();
            ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
            PBaCompany baCompany = baCompanyService.getBaCompanyById(company.getCompanyId());
            String jsonText=null;
            List<ContentJson> contentJsonList;
            List<ContentJson> contentList;
            Type type = new TypeReference<List<ContentJson>>() {}.getType();
            contentList = JSON.parseObject(company.getAddContent(), type);
            if (null!=baCompany.getAddContent()) {
                contentJsonList = JSON.parseObject(baCompany.getAddContent(), type);
                contentJsonList.addAll(contentList);
            }else {
                contentJsonList = contentList;
            }
            jsonText = JSON.toJSONString(contentJsonList,true);
            company.setAddContent(jsonText);
            company.setUpdateUserId(shiroUser.getId());
            Boolean boo = baCompanyService.addContent(company);
            String title = contentList.get(0).getTitle();
            if (boo) {
                SysSysLog sysLog = new SysSysLog();
                String ip = ClientIP.getClientIP(request);
                sysLog.setIpAddress(ip);
                sysLog.setCName("添加内容");
                sysLog.setRemark("添加("+title+")内容");
                sysLog.setResourceId(resourceId);
                sysLog.setCreatedUserId(shiroUser.getId());
                sysSysLogService.addSysSysLog(sysLog);
                json.setSuccess(true);
                json.setMsg("添加成功");
            }else {
                json.setMsg("添加失败");
            }
        }catch (Exception e){
            json.setMsg("服务器异常");
            json.setObj(e);
        }
        return json;
    }

    /**
     * 公司管理
     * @param company
     * @return
     */
    @RequestMapping(value = "/updateCompanySubmit.json")
    @ResponseBody
    public Object updateCompanySubmit(HttpServletRequest request,PBaCompany company,
                                      String seaAreaImagesId,List<MultipartFile> seaAreaImagesList,
                                      String enterpriseImagesId,List<MultipartFile> enterpriseImagesList,
                                      String panoramaId,List<MultipartFile> panoramaList,
                                      String detectionCenterImagesId,List<MultipartFile> detectionCenterImagesList,Long resourceId/*,MultipartFile video*/) {
        Json json = new Json();
        try {
            Subject user = SecurityUtils.getSubject();
            ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
            PBaCompany baCompany = baCompanyService.getBaCompanyById(company.getCompanyId());
            //海域图片
            if (null!=seaAreaImagesId){
                List<ImageJson> imageList = new ArrayList<>();
                if (null!=baCompany.getSeaAreaImages()){
                    String jsonStr = baCompany.getSeaAreaImages();
                    Type type = new TypeReference<List<ImageJson>>() {}.getType();
                    imageList = JSON.parseObject(jsonStr, type);
                    baCompany.setSeaAreaImagesJson(imageList);
                    for(int i = 0; i < 10; i++) {
                        if (seaAreaImagesId.contains("" + i)) {
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
                    if(null!=seaAreaImagesList && 0!=seaAreaImagesList.size()){
                        for (int i =0;i<seaAreaImagesList.size();i++){
                            MultipartFile imgFile = seaAreaImagesList.get(i);
                            if(null != imgFile && 0 != imgFile.getSize()){
                                String name = ossClientUtil.uploadImg2Oss(imgFile);
                                if (null==name){
                                    json.setMsg("图片上传失败");
                                    return json;
                                }else {
                                    if ("上传图片大小不能超过2M！".equals(name)){
                                        json.setMsg("上传海域图片大小不能超过2M！");
                                        return json;
                                    }
                                }
                                ImageJson imageJson = new ImageJson();
                                imageJson.setImageName(name);
                                imageJson.setImageUrl(ossClientUtil.getImgUrl(name));
                                imageList.add(imageJson);
                            }else {
                                json.setMsg("第"+(i+1)+"张，海域图片有误，请重新上传！");
                                return json;
                            }
                        }
                    }
                }else {
                    imageList = new ArrayList<>();
                    for(int i = 0; i < seaAreaImagesList.size(); i++){
                        MultipartFile imgFile = seaAreaImagesList.get(i);
                        if(null != imgFile && 0 != imgFile.getSize()){
                            String name = ossClientUtil.uploadImg2Oss(imgFile);
                            if (null==name){
                                json.setMsg("图片上传失败");
                                return json;
                            }else {
                                if ("上传图片大小不能超过2M！".equals(name)){
                                    json.setMsg("上传海域图片大小不能超过2M！");
                                    return json;
                                }
                            }
                            ImageJson imageJson = new ImageJson();
                            imageJson.setImageName(name);
                            imageJson.setImageUrl(ossClientUtil.getImgUrl(name));
                            imageList.add(imageJson);
                        }else {
                            json.setMsg("第"+(i+1)+"张，海域图片有误，请重新上传！");
                            return json;
                        }
                    }
                }
                if (10>imageList.size()) {
                    for (int i = imageList.size(); i < 10; i++) {
                        ImageJson imageJson = new ImageJson();
                        imageJson.setImageName("");
                        imageJson.setImageUrl("");
                        imageList.add(imageJson);
                    }
                }
                String jsonText = JSON.toJSONString(imageList,true);
                company.setSeaAreaImages(jsonText);
            }else {
                company.setSeaAreaImages(baCompany.getSeaAreaImages());
            }
            //企业图片
            if (null!=enterpriseImagesId){
                List<ImageJson> imageList = new ArrayList<>();
                if (null!=baCompany.getEnterpriseImages()){
                    String jsonStr = baCompany.getEnterpriseImages();
                    Type type = new TypeReference<List<ImageJson>>() {}.getType();
                    imageList = JSON.parseObject(jsonStr, type);
                    baCompany.setEnterpriseImagesJson(imageList);
                    for(int i = 0; i < 10; i++) {
                        if (enterpriseImagesId.contains("" + i)) {
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
                    if(null!=enterpriseImagesList && 0!=enterpriseImagesList.size()){
                        for (int i =0;i<enterpriseImagesList.size();i++){
                            MultipartFile imgFile = enterpriseImagesList.get(i);
                            if(null != imgFile && 0 != imgFile.getSize()){
                                String name = ossClientUtil.uploadImg2Oss(imgFile);
                                if (null==name){
                                    json.setMsg("图片上传失败");
                                    return json;
                                }else {
                                    if ("上传图片大小不能超过2M！".equals(name)){
                                        json.setMsg("上传企业图片大小不能超过2M！");
                                        return json;
                                    }
                                }
                                ImageJson imageJson = new ImageJson();
                                imageJson.setImageName(name);
                                imageJson.setImageUrl(ossClientUtil.getImgUrl(name));
                                imageList.add(imageJson);
                            }else {
                                json.setMsg("第"+(i+1)+"张，企业图片有误，请重新上传！");
                                return json;
                            }
                        }
                    }
                }else {
                    imageList = new ArrayList<>();
                    for(int i = 0; i < enterpriseImagesList.size(); i++){
                        MultipartFile imgFile = enterpriseImagesList.get(i);
                        if(null != imgFile && 0 != imgFile.getSize()){
                            String name = ossClientUtil.uploadImg2Oss(imgFile);
                            if (null==name){
                                json.setMsg("图片上传失败");
                                return json;
                            }else {
                                if ("上传图片大小不能超过2M！".equals(name)){
                                    json.setMsg("上传企业图片大小不能超过2M！");
                                    return json;
                                }
                            }
                            ImageJson imageJson = new ImageJson();
                            imageJson.setImageName(name);
                            imageJson.setImageUrl(ossClientUtil.getImgUrl(name));
                            imageList.add(imageJson);
                        }else {
                            json.setMsg("第"+(i+1)+"张，企业图片有误，请重新上传！");
                            return json;
                        }
                    }
                }
                if (10>imageList.size()) {
                    for (int i = imageList.size(); i < 10; i++) {
                        ImageJson imageJson = new ImageJson();
                        imageJson.setImageName("");
                        imageJson.setImageUrl("");
                        imageList.add(imageJson);
                    }
                }
                String jsonText = JSON.toJSONString(imageList,true);
                company.setEnterpriseImages(jsonText);
            }else {
                company.setEnterpriseImages(baCompany.getEnterpriseImages());
            }/*
            if (null!=video && 0!=video.getSize()){
                String name = ossClientUtil.uploadVideo2Oss(video);
                if (null==name){
                    json.setMsg("视频上传失败");
                    return json;
                }else {
                    if ("上传视频大小不能超过600M！".equals(name)){
                        json.setMsg("上传企业宣传片大小不能超过600M！");
                        return json;
                    }
                }
                company.setEnterpriseImageVideo(ossClientUtil.getVideoUrl(name));
            }*/
            //全景图
            if (null!=panoramaId){
                List<ImageJson> imageList = new ArrayList<>();
                if (null!=baCompany.getPanorama()){
                    String jsonStr = baCompany.getPanorama();
                    Type type = new TypeReference<List<ImageJson>>() {}.getType();
                    imageList = JSON.parseObject(jsonStr, type);
                    baCompany.setPanoramaJson(imageList);
                    for(int i = 0; i < 10; i++) {
                        if (panoramaId.contains("" + i)) {
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
                    if(null!=panoramaList && 0!=panoramaList.size()){
                        for (int i =0;i<panoramaList.size();i++){
                            MultipartFile imgFile = panoramaList.get(i);
                            if(null != imgFile && 0 != imgFile.getSize()){
                                String name = ossClientUtil.uploadImg2Oss(imgFile);
                                if (null==name){
                                    json.setMsg("图片上传失败");
                                    return json;
                                }else {
                                    if ("上传图片大小不能超过2M！".equals(name)){
                                        json.setMsg("上传全景图片大小不能超过2M！");
                                        return json;
                                    }
                                }
                                ImageJson imageJson = new ImageJson();
                                imageJson.setImageName(name);
                                imageJson.setImageUrl(ossClientUtil.getImgUrl(name));
                                imageList.add(imageJson);
                            }else {
                                json.setMsg("第"+(i+1)+"张，全景图片有误，请重新上传！");
                                return json;
                            }
                        }
                    }
                }else {
                    imageList = new ArrayList<>();
                    for(int i = 0; i < panoramaList.size(); i++){
                        MultipartFile imgFile = panoramaList.get(i);
                        if(null != imgFile && 0 != imgFile.getSize()){
                            String name = ossClientUtil.uploadImg2Oss(imgFile);
                            if (null==name){
                                json.setMsg("图片上传失败");
                                return json;
                            }else {
                                if ("上传图片大小不能超过2M！".equals(name)){
                                    json.setMsg("上传全景图片大小不能超过2M！");
                                    return json;
                                }
                            }
                            ImageJson imageJson = new ImageJson();
                            imageJson.setImageName(name);
                            imageJson.setImageUrl(ossClientUtil.getImgUrl(name));
                            imageList.add(imageJson);
                        }else {
                            json.setMsg("第"+(i+1)+"张，全景图片有误，请重新上传！");
                            return json;
                        }
                    }
                }
                if (10>imageList.size()) {
                    for (int i = imageList.size(); i < 10; i++) {
                        ImageJson imageJson = new ImageJson();
                        imageJson.setImageName("");
                        imageJson.setImageUrl("");
                        imageList.add(imageJson);
                    }
                }
                String jsonText = JSON.toJSONString(imageList,true);
                company.setPanorama(jsonText);
            }else {
                company.setPanorama(baCompany.getPanorama());
            }
            //检测中心图片
            if (null!=detectionCenterImagesId){
                List<ImageJson> imageList = new ArrayList<>();
                if (null!=baCompany.getDetectionCenterImages()){
                    String jsonStr = baCompany.getDetectionCenterImages();
                    Type type = new TypeReference<List<ImageJson>>() {}.getType();
                    imageList = JSON.parseObject(jsonStr, type);
                    baCompany.setDetectionCenterImagesJson(imageList);
                    for(int i = 0; i < 10; i++) {
                        if (detectionCenterImagesId.contains("" + i)) {
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
                    if(null!=detectionCenterImagesList && 0!=detectionCenterImagesList.size()){
                        for (int i =0;i<detectionCenterImagesList.size();i++){
                            MultipartFile imgFile = detectionCenterImagesList.get(i);
                            if(null != imgFile && 0 != imgFile.getSize()){
                                String name = ossClientUtil.uploadImg2Oss(imgFile);
                                if (null==name){
                                    json.setMsg("图片上传失败");
                                    return json;
                                }else {
                                    if ("上传图片大小不能超过2M！".equals(name)){
                                        json.setMsg("上传检测中心图片大小不能超过2M！");
                                        return json;
                                    }
                                }
                                ImageJson imageJson = new ImageJson();
                                imageJson.setImageName(name);
                                imageJson.setImageUrl(ossClientUtil.getImgUrl(name));
                                imageList.add(imageJson);
                            }else {
                                json.setMsg("第"+(i+1)+"张，检测中心图片有误，请重新上传！");
                                return json;
                            }
                        }
                    }
                }else {
                    imageList = new ArrayList<>();
                    for(int i = 0; i < detectionCenterImagesList.size(); i++){
                        MultipartFile imgFile = detectionCenterImagesList.get(i);
                        if(null != imgFile && 0 != imgFile.getSize()){
                            String name = ossClientUtil.uploadImg2Oss(imgFile);
                            if (null==name){
                                json.setMsg("图片上传失败");
                                return json;
                            }else {
                                if ("上传图片大小不能超过2M！".equals(name)){
                                    json.setMsg("上传检测中心图片大小不能超过2M！");
                                    return json;
                                }
                            }
                            ImageJson imageJson = new ImageJson();
                            imageJson.setImageName(name);
                            imageJson.setImageUrl(ossClientUtil.getImgUrl(name));
                            imageList.add(imageJson);
                        }else {
                            json.setMsg("第"+(i+1)+"张，检测中心图片有误，请重新上传！");
                            return json;
                        }
                    }
                }
                if (10>imageList.size()) {
                    for (int i = imageList.size(); i < 10; i++) {
                        ImageJson imageJson = new ImageJson();
                        imageJson.setImageName("");
                        imageJson.setImageUrl("");
                        imageList.add(imageJson);
                    }
                }
                String jsonText = JSON.toJSONString(imageList,true);
                company.setDetectionCenterImages(jsonText);
            }else {
                company.setDetectionCenterImages(baCompany.getDetectionCenterImages());
            }
            company.setUpdateUserId(shiroUser.getId());
            Boolean boo = baCompanyService.updateBaCompany(company);
            if (boo){
                SysSysLog sysLog = new SysSysLog();
                String ip = ClientIP.getClientIP(request);
                sysLog.setIpAddress(ip);
                sysLog.setCName("修改公司信息");
                sysLog.setRemark("修改公司信息");
                sysLog.setResourceId(resourceId);
                sysLog.setCreatedUserId(shiroUser.getId());
                sysSysLogService.addSysSysLog(sysLog);
                json.setSuccess(true);
                json.setMsg("修改成功");
            }else {
                json.setMsg("修改失败");
            }
        }catch (Exception e){
            json.setMsg("服务器异常");
            json.setObj(e);
        }
        return json;
    }


}
