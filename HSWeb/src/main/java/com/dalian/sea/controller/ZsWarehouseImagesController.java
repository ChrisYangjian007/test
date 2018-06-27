package com.dalian.sea.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalian.sea.ClientIP;
import com.dalian.sea.FileUpload.OSSClientUtil;
import com.dalian.sea.config.shiro.ShiroUser;
import com.dalian.sea.json.ImageJson;
import com.dalian.sea.json.JqGridParam;
import com.dalian.sea.json.Json;
import com.dalian.sea.model.SysSysLog;
import com.dalian.sea.model.ZsWarehouse;
import com.dalian.sea.parameter.PZsWarehouse;
import com.dalian.sea.parameter.PZsWarehouseImages;
import com.dalian.sea.service.SysSysLogService;
import com.dalian.sea.service.ZsWarehouseImagesService;
import com.dalian.sea.service.ZsWarehouseService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * ZsWarehouseImagesController
 *
 * @author TONE
 * @date 2018/5/29
 */
@Controller
@RequestMapping(value = "/warehouseImage")
public class ZsWarehouseImagesController {
    @Autowired
    private ZsWarehouseImagesService warehouseImagesService;
    @Autowired
    private ZsWarehouseService zsWarehouseService;
    @Autowired
    private OSSClientUtil ossClientUtil;
    @Autowired
    private SysSysLogService sysSysLogService;


    /**
     * 上传仓库检测图片
     * @param request
     * @return
     */
    @RequestMapping(value = "/uploadWarehouseImagesModal.htm")
    public String uploadWarehouseImagesModal(HttpServletRequest request,Long warehouseId){
        if (null!=warehouseId) {
            ZsWarehouse warehouse = zsWarehouseService.getWarehouseById(warehouseId);
            request.setAttribute("warehouse",warehouse);
            PZsWarehouseImages warehouseImages = warehouseImagesService.getAllImagesByWarehouseIdForMonth(warehouseId);
            if (null!=warehouseImages) {
                if (null!=warehouseImages.getImagesJson()) {
                    Type type = new TypeReference<List<ImageJson>>() {}.getType();
                    List<ImageJson> imageList = JSON.parseObject(warehouseImages.getImagesJson(), type);
                    warehouseImages.setWarehouseImagesJson(imageList);
                }
                request.setAttribute("warehouseImages", warehouseImages);
            }
        }
        return "warehouse/uploadWarehouseModal";
    }
    /**
     * 上传仓库检测图片
     * @param request
     * @return
     */
    @RequestMapping(value = "/uploadWarehouseImages.json")
    @ResponseBody
    public Object uploadWarehouseImages(HttpServletRequest request, Long warehouseId,Long resourceId,
                                      String warehouseImageId, List<MultipartFile> warehouseImage){
        Json json = new Json();
        try {
            Integer imageSize;
            Subject user = SecurityUtils.getSubject();
            ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
            PZsWarehouseImages warehouseImages = warehouseImagesService.getAllImagesByWarehouseIdForMonth(warehouseId);
            if (null!=warehouseImageId){
                List<ImageJson> imageList = new ArrayList<>();
                if (null!=warehouseImages&&null!=warehouseImages.getImagesJson()){
                    String jsonStr = warehouseImages.getImagesJson();
                    Type type = new TypeReference<List<ImageJson>>() {}.getType();
                    imageList = JSON.parseObject(jsonStr, type);
                    warehouseImages.setWarehouseImagesJson(imageList);
                    for(int i = 0; i < 2; i++) {
                        if (warehouseImageId.contains("" + i)) {
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
                    if(null!=warehouseImage && 0!=warehouseImage.size()){
                        for (int i =0;i<warehouseImage.size();i++){
                            MultipartFile imgFile = warehouseImage.get(i);
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
                    for(int i = 0; i < warehouseImage.size(); i++){
                        MultipartFile imgFile = warehouseImage.get(i);
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
                            json.setMsg("第"+(i+1)+"张，图片有误，请重新上传！");
                            return json;
                        }
                    }
                }
                imageSize=imageList.size();
                if (2>imageList.size()) {
                    for (int i = imageList.size(); i < 2; i++) {
                        ImageJson imageJson = new ImageJson();
                        imageJson.setImageName("");
                        imageJson.setImageUrl("");
                        imageList.add(imageJson);
                    }
                }
                String jsonText = JSON.toJSONString(imageList, true);
                Boolean boo ;
                if(null==warehouseImages) {
                    warehouseImages = new PZsWarehouseImages();
                    warehouseImages.setWarehouseId(warehouseId);
                    warehouseImages.setImagesSize(imageSize);
                    warehouseImages.setUploadMonth(new SimpleDateFormat("yyyy-MM").parse(new SimpleDateFormat("yyyy-MM").format(new Date())));
                    warehouseImages.setImagesJson(jsonText);
                    warehouseImages.setCreateUserId(shiroUser.getId());
                    boo = warehouseImagesService.addZsWarehouseImages(warehouseImages);
                }else {
                    warehouseImages.setImagesSize(imageSize);
                    warehouseImages.setImagesJson(jsonText);
                    warehouseImages.setUpdateUserId(shiroUser.getId());
                    boo = warehouseImagesService.updateZsWarehouseImages(warehouseImages);
                }
                if (boo){
                    SysSysLog sysLog = new SysSysLog();
                    String ip = ClientIP.getClientIP(request);
                    sysLog.setIpAddress(ip);
                    sysLog.setCName("上传登记表");
                    sysLog.setRemark(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date())+"-上传登记表");
                    sysLog.setResourceId(resourceId);
                    sysLog.setCreatedUserId(shiroUser.getId());
                    sysSysLogService.addSysSysLog(sysLog);
                    json.setSuccess(true);
                    json.setMsg("上传成功");
                }else {
                    json.setMsg("上传失败");
                }
            }
        }catch (Exception e){
            json.setObj(e);
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 仓库检测图片
     * @param request
     * @return
     */
    @RequestMapping(value = "/warehouseImagesHistoryModal.htm")
    public String getWarehouseImagesModal(HttpServletRequest request,Long warehouseId){
        if (null!=warehouseId) {
            request.setAttribute("warehouseId",warehouseId);
        }
        return "warehouse/warehouseImagesModal";
    }

    /**
     * 仓库检测图片
     * @return
     */
    @RequestMapping(value = "/GridJson.json")
    @ResponseBody
    public Object GridJson(HttpServletRequest request, PZsWarehouseImages warehouseImages, Integer page , Integer rows){
        List<PZsWarehouseImages> warehouseImagesList = warehouseImagesService.getImagesBy(warehouseImages,page,rows);
        PageInfo<PZsWarehouseImages> pageInfo = new PageInfo<>(warehouseImagesList);
        return new JqGridParam(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getPages(),warehouseImagesList);
    }

    /**
     * 仓库检测图片
     * @return
     */
    @RequestMapping(value = "/getWarehouseImagesById.json")
    @ResponseBody
    public Object getWarehouseImagesById(HttpServletRequest request, Long id){
        Json json = new Json();
        try {
            PZsWarehouseImages warehouseImages = warehouseImagesService.getImagesById(id);
            List<ImageJson> imageList = new ArrayList<>();
            if (null != warehouseImages && null != warehouseImages.getImagesJson()) {
                String jsonStr = warehouseImages.getImagesJson();
                Type type = new TypeReference<List<ImageJson>>() {
                }.getType();
                imageList = JSON.parseObject(jsonStr, type);
                warehouseImages.setWarehouseImagesJson(imageList);
                json.setObj(warehouseImages);
                json.setSuccess(true);
            }else {
                json.setMsg("当前数据有误，请联系管理员！");
            }
        }catch (Exception e){
            json.setMsg("服务器异常！");
        }
        return json;
    }





}
