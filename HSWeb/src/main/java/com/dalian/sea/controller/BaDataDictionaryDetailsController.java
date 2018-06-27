package com.dalian.sea.controller;

import com.dalian.sea.config.shiro.ShiroUser;
import com.dalian.sea.json.JqGridParam;
import com.dalian.sea.json.Json;
import com.dalian.sea.model.BaDataDictionary;
import com.dalian.sea.model.BaDataDictionaryDetails;
import com.dalian.sea.parameter.PBaDataDictionaryDetails;
import com.dalian.sea.service.BaDataDictionaryDetailsService;
import com.dalian.sea.service.BaDataDictionaryService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * BaDataDictionaryDetailsController
 *
 * @author xintao
 * @date 2018/1/18
 */
@Slf4j
@Controller
@RequestMapping(value = "/dataDictionaryDetails")
public class BaDataDictionaryDetailsController extends LayoutRazor{
    @Autowired
    private BaDataDictionaryDetailsService baDataDictionaryDetailsService;
    @Autowired
    private BaDataDictionaryService baDataDictionaryService;

    /**
     * 根据数据字典ID获取
     * @param request
     * @param dataDictionaryId
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/GridJson.json")
    @ResponseBody
    public Object GridJson(HttpServletRequest request, Long dataDictionaryId, Integer page, Integer rows) {
        List<PBaDataDictionaryDetails> baResourceList = baDataDictionaryDetailsService.getBaDataDictionaryDetailsByDataDictionaryId(dataDictionaryId,page,rows);
        PageInfo<PBaDataDictionaryDetails> pageInfo = new PageInfo<>(baResourceList);
        return new JqGridParam(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getPages(),baResourceList);
    }


    /**
     * 添加modal
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/addDataDictionaryDetailsModal.htm")
    public String addDataDictionaryDetailsModal(HttpServletRequest request,Long id){

        if(null != id && 0 != id && !("").equals(id)){
            BaDataDictionary baDataDictionary = baDataDictionaryService.getBaDataDictionaryById(id);
            if(null != baDataDictionary){
                request.setAttribute("baDataDictionary",baDataDictionary);
            }
            BaDataDictionaryDetails baDataDictionaryDetails = baDataDictionaryDetailsService.getBaDataDictionaryDetailsMaxListIndex();
            if(null != baDataDictionaryDetails){
                request.setAttribute("listIndex",baDataDictionaryDetails.getListIndex()+1);
            }else {
                request.setAttribute("listIndex",1);
            }
        }

        return "dataDictionary/addBaDataDictionaryDetailsModal";
    }

    /**
     * 添加
     * @param request
     * @param baDataDictionaryDetails
     * @return
     */
    @RequestMapping(value = "/addBaDataDictionaryDetails.json")
    @ResponseBody
    public Object addBaDataDictionaryDetails(HttpServletRequest request,BaDataDictionaryDetails baDataDictionaryDetails){
        Json json = new Json();
        try {
            BaDataDictionaryDetails dataDictionaryDetails = baDataDictionaryDetailsService.getBaDataDictionaryDetailsByNameAndDataDictionaryId(baDataDictionaryDetails);
            if(null ==dataDictionaryDetails){
                Subject user = SecurityUtils.getSubject();
                ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                baDataDictionaryDetails.setCreateUserId(shiroUser.getId());
                if(baDataDictionaryDetailsService.addBaDataDictionaryDetails(baDataDictionaryDetails)){
                    json.setMsg("添加成功");
                    json.setSuccess(true);
                }else {
                    json.setMsg("添加失败");
                }
            }else {
                json.setMsg("该项目已经存在");
            }
        }catch (Exception e){
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 修改modal
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "updateDataDictionaryDetailsModal.htm")
    public String updateDataDictionaryDetailsModal(HttpServletRequest request,Long id){
        if(null != id && 0 != id){
            BaDataDictionaryDetails baDataDictionaryDetails = baDataDictionaryDetailsService.getBaDataDictionaryDetailsById(id);
            if(null != baDataDictionaryDetails){
                request.setAttribute("baDataDictionaryDetails",baDataDictionaryDetails);
                if(null != baDataDictionaryDetails.getDataDictionaryDetailsId() && 0 != baDataDictionaryDetails.getDataDictionaryDetailsId()){
                    BaDataDictionary baDataDictionary = baDataDictionaryService.getBaDataDictionaryById(baDataDictionaryDetails.getDataDictionaryId());
                    if(null != baDataDictionary){
                        request.setAttribute("baDataDictionary",baDataDictionary);
                    }
                }
            }
        }
        return "dataDictionary/updateBaDataDictionaryDetailsModal";
    }

    /**
     * 修改
     * @param request
     * @param baDataDictionaryDetails
     * @return
     */
    @RequestMapping(value = "/updateBaDataDictionaryDetails.json")
    @ResponseBody
    public Object updateBaDataDictionaryDetails(HttpServletRequest request,BaDataDictionaryDetails baDataDictionaryDetails){
        Json json = new Json();
        try {

            BaDataDictionaryDetails dataDictionaryDetails = baDataDictionaryDetailsService.getBaDataDictionaryDetailsByNameAndDataDictionaryId(baDataDictionaryDetails);
            if(null == dataDictionaryDetails || baDataDictionaryDetails.getDataDictionaryDetailsId().equals(dataDictionaryDetails.getDataDictionaryDetailsId())){
                Subject user = SecurityUtils.getSubject();
                ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                baDataDictionaryDetails.setUpdateUserId(shiroUser.getId());
                if(baDataDictionaryDetailsService.updateBaDataDictionaryDetailsById(baDataDictionaryDetails)){
                    json.setMsg("修改成功");
                    json.setSuccess(true);
                }else {
                    json.setMsg("修改失败");
                }
            }else {
                json.setMsg("该项目名已经存在");
            }

        }catch (Exception e){
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 删除
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteBaDataDictionaryDetails.json")
    @ResponseBody
    public Object deleteBaDataDictionaryDetails(HttpServletRequest request,Long id){
        Json json = new Json();
        try {
            if(null != id && 0 != id){
                BaDataDictionaryDetails baDataDictionaryDetails = baDataDictionaryDetailsService.getBaDataDictionaryDetailsById(id);
                if(null != baDataDictionaryDetails){
                    Subject user = SecurityUtils.getSubject();
                    ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                    baDataDictionaryDetails.setUpdateUserId(shiroUser.getId());
                    if(baDataDictionaryDetailsService.deleteBaDataDictionaryDetailsById(baDataDictionaryDetails)){
                        json.setMsg("删除成功");
                        json.setSuccess(true);
                    }else {
                        json.setMsg("删除失败");
                    }
                }
            }
        }catch (Exception e){
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 查看详情的详细信息
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/showDataDictionaryDetailsModal.htm")
    public String showDataDictionaryDetailsModal(HttpServletRequest request,Long id){
        if(null != id && 0 != id){
            BaDataDictionaryDetails baDataDictionaryDetails = baDataDictionaryDetailsService.getBaDataDictionaryDetailsById(id);
            if(null != baDataDictionaryDetails){
                request.setAttribute("baDataDictionaryDetails",baDataDictionaryDetails);
                if(null != baDataDictionaryDetails.getDataDictionaryId() && 0 != baDataDictionaryDetails.getDataDictionaryId()){
                    BaDataDictionary baDataDictionary = baDataDictionaryService.getBaDataDictionaryById(baDataDictionaryDetails.getDataDictionaryId());
                    if(null != baDataDictionary){
                        request.setAttribute("baDataDictionary",baDataDictionary);
                    }
                }
            }
        }
        return "dataDictionary/showDataDictionaryDetailsModal";
    }

}
