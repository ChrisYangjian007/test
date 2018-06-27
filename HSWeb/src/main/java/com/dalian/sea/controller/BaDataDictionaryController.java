package com.dalian.sea.controller;

import com.dalian.sea.DTO.TreeJsonDTO;
import com.dalian.sea.common.HandleHelper;
import com.dalian.sea.config.shiro.ShiroUser;
import com.dalian.sea.json.JqGridParam;
import com.dalian.sea.json.Json;
import com.dalian.sea.model.BaDataDictionary;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * BaDataDictionaryController
 *
 * @author xintao
 * @date 2018/1/17
 */
@Slf4j
@Controller
@RequestMapping(value = "/dataDictionary")
public class BaDataDictionaryController extends LayoutRazor{
    @Autowired
    private BaDataDictionaryService baDataDictionaryService;
    @Autowired
    private BaDataDictionaryDetailsService baDataDictionaryDetailsService;


    /**
     * 数据字典
     * @return
     */
    @RequestMapping(value = "/dataDictionary.htm")
    public String dataDictionary(HttpServletRequest request){
        return freeMarkerIndexResult("dataDictionary/dataDictionary.ftl",request);
    }

    /**
     * 数据字典分类管理
     * @return
     */
    @RequestMapping(value = "/sortManageIFrame.htm")
    public String sortManage(HttpServletRequest request){
        return freeMarkerIndexResult("dataDictionary/sortManage.ftl",request);
    }


    /**
     * 数据字典tree
     * @param request
     * @return
     */
    @RequestMapping(value = "/TreeJson.json")
    @ResponseBody
    public String TreeJson(HttpServletRequest request) {
        List<TreeJsonDTO> treeList = new ArrayList<>();
        try {
            List<BaDataDictionary> baResourceList = baDataDictionaryService.getAllBaDataDictionary();
            for(BaDataDictionary item : baResourceList){
                TreeJsonDTO tree = new TreeJsonDTO();
                tree.setId(String.valueOf(item.getDataDictionaryId()));
                tree.setText(item.getCName());
                tree.setValue(String.valueOf(item.getDataDictionaryId()));
                tree.setAttribute("ALevel");
                tree.setAttributeValue(String.valueOf(item.getALevel()));
                tree.setAttributeA("code");
                tree.setAttributeValueA(String.valueOf(item.getCode()));
                tree.setShowcheck(false);
                tree.setIsexpand(true);
                tree.setComplete(true);
                tree.setParentId(String.valueOf(item.getParentId()));
                tree.setHasChildren(baResourceList.stream().filter(t -> Objects.equals(t.getParentId(), item.getDataDictionaryId())).count() > 0);
                treeList.add(tree);
            }
        }catch (Exception e){
            log.info(String.valueOf(e));
        }
        return HandleHelper.TreeToJson2(treeList, "0");
    }


    /**
     * 根据数据字典ID获取
     * @param request
     * @param parentId
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/GridJson.json")
    @ResponseBody
    public Object GridJson(HttpServletRequest request, Long parentId, Integer page, Integer rows) {
        List<BaDataDictionary> baResourceList = baDataDictionaryService.getBaDataDictionaryByPId(parentId,page,rows);
        PageInfo<BaDataDictionary> pageInfo = new PageInfo<>(baResourceList);
        return new JqGridParam(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getPages(),baResourceList);
    }

    /**
     * 添加modal
     * @return
     */
    @RequestMapping(value = "/addDataDictionaryModal.htm")
    public String addDataDictionaryModal(HttpServletRequest request,Long id){
        if (null!=id&&0!=id) {
            BaDataDictionary baDataDictionary = baDataDictionaryService.getBaDataDictionaryById(id);
            if (null != baDataDictionary) {
                request.setAttribute("baDataDictionary", baDataDictionary);
            }
            BaDataDictionary dataDictionary = baDataDictionaryService.getBaDataDictionaryMaxListIndex();
            if(null != dataDictionary){
                request.setAttribute("listIndex",baDataDictionary.getListIndex()+1);
            }else {
                request.setAttribute("listIndex",1);
            }
        }
        return "dataDictionary/addBaDataDictionaryModal";
    }

    /**
     * 添加
     * @param request
     * @param baDataDictionary
     * @return
     */
    @RequestMapping(value = "/addBaDataDictionary.json")
    @ResponseBody
    public Object addBaDataDictionary(HttpServletRequest request,BaDataDictionary baDataDictionary) {
        Json json = new Json();
        try {
            BaDataDictionary dictionary = baDataDictionaryService.getDataDictionaryByCode(baDataDictionary.getCode());
            if(null == dictionary){
            Subject user = SecurityUtils.getSubject();
            ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
            BaDataDictionary dataDictionary = baDataDictionaryService.getBaDataDictionaryByPIdAndName(baDataDictionary);
            if (null==dataDictionary){
                dataDictionary = baDataDictionaryService.getBaDataDictionaryById(baDataDictionary.getParentId());
                baDataDictionary.setCreateUserId(shiroUser.getId());
                baDataDictionary.setFirstId(dataDictionary.getFirstId());
                Long id = baDataDictionaryService.addBaDataDictionary(baDataDictionary);
                if (null!=id){
                    json.setMsg("添加成功");
                    json.setSuccess(true);
                }else {
                    json.setMsg("添加失败");
                }
            }else {
                json.setMsg("当前父级下已有该资源");
            }
            }else {
                json.setMsg("编码已经存在");
            }
        }catch (Exception e){
            log.info(String.valueOf(e));
            json.setObj(e);
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 返回修改modal
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateDataDictionaryModal.htm")
    public String updateDataDictionaryModal(HttpServletRequest request,Long id){
        if (null!=id&&0!=id) {
            BaDataDictionary dataDictionary = baDataDictionaryService.getBaDataDictionaryById(id);
            if (null != dataDictionary) {
                request.setAttribute("dataDictionary", dataDictionary);
                dataDictionary = baDataDictionaryService.getBaDataDictionaryById(dataDictionary.getParentId());
                request.setAttribute("parentDictionary",dataDictionary);
            }
        }
        return "dataDictionary/updateBaDataDictionaryModal";
    }

    /**
     * 修该数据字典信息
     * @param request
     * @param baDataDictionary
     * @return
     */
    @RequestMapping(value = "/updateBaDataDictionary.json")
    @ResponseBody
    public Object updateDataDictionary(HttpServletRequest request,BaDataDictionary baDataDictionary){
        Json json = new Json();
        Subject user = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
        baDataDictionary.setUpdateUserId(shiroUser.getId());
        try {
            BaDataDictionary dataDictionary = baDataDictionaryService.getDataDictionaryByCode(baDataDictionary.getCode());
            if(null == dataDictionary || dataDictionary.getDataDictionaryId().equals(baDataDictionary.getDataDictionaryId())){
                BaDataDictionary dictionary = baDataDictionaryService.getBaDataDictionaryByPIdAndName(baDataDictionary);
                if(null==dictionary || dictionary.getDataDictionaryId().equals(baDataDictionary.getDataDictionaryId())){
                    Boolean boo =baDataDictionaryService.updateBaDataDictionaryById(baDataDictionary);
                    if(boo){
                        json.setMsg("修改成功");
                        json.setSuccess(true);
                    }else {
                        json.setMsg("修改失败");
                    }
                }else {
                    json.setMsg("当前父级下已有该资源,无法修改");
                }
            }else {
                json.setMsg("编码已经存在");
            }
        }catch (Exception e){
            log.info(String.valueOf(e));
            json.setObj(e);
            json.setMsg("服务器异常");
        }
        return json;
    }


    /**
     * 删除书记字典
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteBaDataDictionary.json")
    @ResponseBody
    public Object deleteBaDataDictionary(HttpServletRequest request,Long id){
        Json json = new Json();
        try {
            if(null!=id){
                BaDataDictionary baDataDictionary = baDataDictionaryService.getBaDataDictionaryById(id);
                if(null != baDataDictionary){
                    Subject user = SecurityUtils.getSubject();
                    ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                    baDataDictionary.setUpdateUserId(shiroUser.getId());
                    Boolean boo = baDataDictionaryService.deleteBaDataDictionary(baDataDictionary);
                    if(boo){
                        json.setMsg("删除成功");
                        json.setSuccess(true);
                    }else {
                        json.setMsg("删除失败");
                    }
                }else {
                    json.setMsg("当前数据有误,请刷新");
                }
            }else {
                json.setMsg("当前数据不足,无法修改");
            }
        }catch (Exception e){
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 数据字典详情
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/showDataDictionaryModal.htm")
    public String dataDictionaryDetail(HttpServletRequest request,Long id){
        if(null != id ){
            BaDataDictionary baDataDictionary = baDataDictionaryService.getBaDataDictionaryById(id);
            if(null != baDataDictionary){
                request.setAttribute("baDataDictionary",baDataDictionary);
                baDataDictionary = baDataDictionaryService.getBaDataDictionaryById(baDataDictionary.getParentId());
                request.setAttribute("parentDictionary",baDataDictionary);
            }
        }
        return "dataDictionary/showDataDictionaryModal";
    }
}
