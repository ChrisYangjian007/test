package com.dalian.sea.controller;

import com.dalian.sea.DTO.TreeJsonDTO;
import com.dalian.sea.common.HandleHelper;
import com.dalian.sea.config.shiro.ShiroUser;
import com.dalian.sea.json.JqGridParam;
import com.dalian.sea.json.Json;
import com.dalian.sea.model.BaResource;
import com.dalian.sea.service.BaResourceService;
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
 * BaResourceController
 *
 * @author xintao
 * @date 2018/1/10
 */
@Slf4j
@Controller
@RequestMapping(value = "/resource")
public class BaResourceController extends LayoutRazor{
    @Autowired
    private BaResourceService baResourceService;


    /**
     * 资源管理
     * @return
     */
    @RequestMapping(value = "/baResourceManage.htm")
    public String baResourceManage(HttpServletRequest request){
        return freeMarkerIndexResult("baResource/baResourceManage.ftl",request);
    }

    /**
     * 添加资源
     * @return
     */
    @RequestMapping(value = "/addResourceModal.htm")
    public String addResourceModal(HttpServletRequest request,Long id){
        if (null!=id) {
            BaResource resource = baResourceService.getBaResourceById(id);
            if (null != resource) {
                request.setAttribute("resource", resource);
                request.setAttribute("aLevel", resource.getALevel()+1);
            }else {
                request.setAttribute("aLevel", "1");
            }
            resource = baResourceService.getBaResourceByMaxListIndex();
            if (null!=resource){
                request.setAttribute("listIndex", resource.getListIndex()+1);
            }else {
                request.setAttribute("listIndex", "1");
            }
        }
        return "/baResource/addResourceModal";
    }


    @RequestMapping(value = "/TreeJson.json")
    @ResponseBody
    public String TreeJson(HttpServletRequest request) {
        List<TreeJsonDTO> treeList = new ArrayList<>();
        try {
            List<BaResource> baResourceList = baResourceService.getAllBaResource();
            for(BaResource item : baResourceList){
                TreeJsonDTO tree = new TreeJsonDTO();
                tree.setId(String.valueOf(item.getResourceId()));
                tree.setText(item.getCName());
                tree.setValue(String.valueOf(item.getResourceId()));
                tree.setAttribute("Category");
                tree.setAttributeValue(String.valueOf(item.getCategory()));
                tree.setShowcheck(false);
                tree.setIsexpand(true);
                tree.setComplete(true);
                tree.setParentId(String.valueOf(item.getParentId()));
                tree.setImg("/images/Icon16/"+item.getIcon());
                tree.setHasChildren(baResourceList.stream().filter(t -> Objects.equals(t.getParentId(), item.getResourceId())).count() > 0);
                treeList.add(tree);
            }
        }catch (Exception e){
            log.info(String.valueOf(e));
        }
        return HandleHelper.TreeToJson2(treeList, "0");
    }

    @RequestMapping(value = "/GridJson.json")
    @ResponseBody
    public Object GridJson(HttpServletRequest request,BaResource baResource,Integer page,Integer rows) {
        List<BaResource> baResourceList = baResourceService.getBaResourceBy(baResource,page,rows);
        PageInfo<BaResource> pageInfo = new PageInfo<>(baResourceList);
        return new JqGridParam(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getPages(),baResourceList);
    }


    /**
     * 添加资源
     * @param request
     * @param baResource
     * @return
     */
    @RequestMapping(value = "/addBaResource.json")
    @ResponseBody
    public Object addBaResource(HttpServletRequest request,BaResource baResource) {
        Json json = new Json();
        try {
            BaResource resource = baResourceService.getResourceByCode(baResource.getCode());
            if(null == resource){
                resource = baResourceService.getBaResourceByNameAndPid(baResource);
                if (null==resource){
                    if (1!=baResource.getCategory()) {
                        if(null!=baResource.getLocation()) {
                            resource = baResourceService.getBaResourceByLocation(baResource);
                        }else {
                            json.setMsg("权限地址有误，请重新添加！");
                            return json;
                        }
                    }
                    if (null==resource) {
                        Subject user = SecurityUtils.getSubject();
                        ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                        baResource.setCreateUserId(shiroUser.getId());
                        resource = baResourceService.getBaResourceById(baResource.getParentId());
                        if (null != resource) {
                            baResource.setFirstId(resource.getFirstId());
                            baResource.setResourceType(resource.getResourceType());
                        } else {
                            baResource.setFirstId(new Long("0"));
                        }
                        Long id = baResourceService.addBaResource(baResource);
                        if (null != id) {
                            json.setMsg("添加成功");
                            json.setSuccess(true);
                        } else {
                            json.setMsg("添加失败");
                        }
                    }else {
                        json.setMsg("访问、权限路径已存在！");
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
     * 返回修改资源modal
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateResourceModal.htm")
    public String updateResourceModal(HttpServletRequest request,Long id){
        if (null!=id&&0!=id) {
            BaResource resource = baResourceService.getBaResourceById(id);
            if (null != resource) {
                request.setAttribute("resource", resource);
                if(0!=resource.getParentId()&&null!=resource.getParentId()&&!("").equals(resource.getParentId())){
                    BaResource resource1 = baResourceService.getBaResourceById(resource.getParentId());
                    request.setAttribute("parentResource",resource1);
                }
            }
        }
        return "baResource/updateResourceModal";
    }

    /**
     * 修改资源信息
     * @param request
     * @param baResource
     * @return
     */
    @RequestMapping(value = "/updateBaResource.json")
    @ResponseBody
    public Object updateBaResource(HttpServletRequest request,BaResource baResource){
        Json json = new Json();
        Subject user = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
        baResource.setUpdateUserId(shiroUser.getId());
        try {
            BaResource source = baResourceService.getResourceByCode(baResource.getCode());
            if(null == source || source.getResourceId().equals(baResource.getResourceId())){
                BaResource resource = baResourceService.getBaResourceByNameAndPid(baResource);
                if(null==resource||resource.getResourceId().equals(baResource.getResourceId())){
                    if (1!=baResource.getCategory()) {
                        if(null!=baResource.getLocation()) {
                            resource = baResourceService.getBaResourceByLocation(baResource);
                        }else {
                            json.setMsg("权限地址有误，请重新添加！");
                            return json;
                        }
                    }
                    if (null==resource||resource.getResourceId().equals(baResource.getResourceId())) {
                        if (baResourceService.updateBaResourceById(baResource)) {
                            json.setMsg("修改成功");
                            json.setSuccess(true);
                        } else {
                            json.setMsg("修改失败");
                        }
                    }else {
                        json.setMsg("访问、权限路径已存在！");
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
     * 删除资源
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteBaResource.json")
    @ResponseBody
    public Object deleteBaResource(HttpServletRequest request,Long id){
        Json json = new Json();
        try {
            if(null!=id){
                BaResource baResource = baResourceService.getBaResourceById(id);
                if(null != baResource){
                    Subject user = SecurityUtils.getSubject();
                    ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                    baResource.setUpdateUserId(shiroUser.getId());
                    if(baResourceService.deleteBaResource(baResource)){
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
     * 资源详情
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/resourceDetailModal.htm")
    public String resourceDetail(HttpServletRequest request,Long id){
        if(null != id){
                BaResource baResource = baResourceService.getBaResourceById(id);
                if(null != baResource){
                    request.setAttribute("baResourceDetail",baResource);
                    if(null!=baResource.getParentId()){
                        BaResource resource1 = baResourceService.getBaResourceById(baResource.getParentId());
                        request.setAttribute("parentResource",resource1);
                    }
                }
        }
        return "baResource/baResourceDetail";
    }
}
