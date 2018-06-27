package com.dalian.sea.api;

import com.dalian.sea.json.JqGridParam;
import com.dalian.sea.json.Json;
import com.dalian.sea.model.ZsEnterprise;
import com.dalian.sea.service.ZsEnterpriseService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author 杨文波
 * @date 2018/3/6
 */
@RestController
@Slf4j
@RequestMapping("/enterprise")
public class AndroidZsEnterpriseApi {

    @Autowired
    private ZsEnterpriseService zsEnterpriseService;

    @PostMapping("/getEnterprise.json")
    public Object getEnterprise(@RequestParam("name") String name, Integer page, Integer rows){
        Json json = new Json();
        try {
            List<ZsEnterprise> zsEnterprises = zsEnterpriseService.getEnterpriseForApi(page,rows,name);
            if (null!=zsEnterprises&&zsEnterprises.size()>0){
                PageInfo<ZsEnterprise> pageInfo = new PageInfo<>(zsEnterprises);
                json.setObj(new JqGridParam(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), zsEnterprises));
            }else {
                json.setMsg("查询结果暂无");
            }
            json.setSuccess(true);
        }catch (Exception e){
            json.setMsg("服务器异常!");
        }
        return json;
    }
}
