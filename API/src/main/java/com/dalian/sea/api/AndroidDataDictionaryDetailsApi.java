package com.dalian.sea.api;

import com.dalian.sea.json.JqGridParam;
import com.dalian.sea.json.Json;
import com.dalian.sea.model.BaDataDictionary;
import com.dalian.sea.model.BaDataDictionaryDetails;
import com.dalian.sea.model.SysUnit;
import com.dalian.sea.parameter.PBaDataDictionaryDetails;
import com.dalian.sea.parameter.PZsCompanyProduct;
import com.dalian.sea.service.BaDataDictionaryDetailsService;
import com.dalian.sea.service.BaDataDictionaryService;
import com.dalian.sea.service.SysUnitService;
import com.dalian.sea.service.ZsCompanyProductService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 * @author 杨文波
 * @date 2018/3/6
 */
@RestController
@Slf4j
@RequestMapping("/dictionary")
public class AndroidDataDictionaryDetailsApi {

    @Autowired
    private ZsCompanyProductService zsCompanyProductService;

    @Autowired
    private SysUnitService sysUnitService;

    /**
     * 根据名称获取数据字典下的货物类型
     *
     * @return
     */
    @PostMapping("/getGoodsType.json")
    @ResponseBody
    public Json getGoodsType(Integer page,Integer rows) {
        Json json = new Json();
        try {
            List<PZsCompanyProduct> zsCompanyProducts = zsCompanyProductService.getTypeForApi(page,rows);
            if (null!=zsCompanyProducts&&zsCompanyProducts.size()>0){
                PageInfo<PZsCompanyProduct> pageInfo = new PageInfo<>(zsCompanyProducts);
                json.setMsg("查询成功");
                json.setObj(new JqGridParam(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), zsCompanyProducts));
            }else {
                json.setMsg("查询暂无");
            }
            json.setSuccess(true);
        }catch (Exception e){
            json.setMsg("服务器异常!");
        }
        return json;
    }

    /**
     * 根据货物类型获取产品
     *
     * @param pZsCompanyProduct
     * @return
     */
    @PostMapping("/getProductByType.json")
    @ResponseBody
    public Json getProductByType(PZsCompanyProduct pZsCompanyProduct,Integer page,Integer rows) {
        Json json = new Json();
        try {
            List<PZsCompanyProduct> zsCompanyProducts = zsCompanyProductService.getProductByTypeAndNameAndSpecificationForApi(pZsCompanyProduct,page,rows);
            if (null != zsCompanyProducts && zsCompanyProducts.size() > 0) {
                PageInfo<PZsCompanyProduct> pageInfo = new PageInfo<>(zsCompanyProducts);
                json.setMsg("查询成功");
                json.setObj(new JqGridParam(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), zsCompanyProducts));
            } else {
                json.setMsg("查询暂无");
            }
            json.setSuccess(true);
        }catch (Exception e){
            json.setMsg("服务器异常!");
        }
        return json;
    }

    /**
     * 获取已有的产品规格
     *
     * @param pZsCompanyProduct
     * @return
     */
    @PostMapping("/getProductSpecName.json")
    @ResponseBody
    public Json getProductSpecName(PZsCompanyProduct pZsCompanyProduct,Integer page,Integer rows) {
        Json json = new Json();
        try{
            List<PZsCompanyProduct> zsCompanyProducts = zsCompanyProductService.getProductByTypeAndNameAndSpecificationForApi(pZsCompanyProduct,page,rows);
            if (null != zsCompanyProducts && zsCompanyProducts.size() > 0) {
                PageInfo<PZsCompanyProduct> pageInfo = new PageInfo<>(zsCompanyProducts);
                json.setMsg("查询成功");
                json.setObj(new JqGridParam(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), zsCompanyProducts));
            } else {
                json.setMsg("查询暂无");
            }
            json.setSuccess(true);
        }catch (Exception e){
            json.setMsg("服务器异常!");
        }
        return json;
    }

    /**
     * 获取单位
     *
     * @return
     */
    @PostMapping("/getUnitName.json")
    @ResponseBody
    public Object getUnitName(Integer page,Integer rows) {
        Json json = new Json();
        try{
            List<SysUnit> unitList = sysUnitService.getSysUnitNoZeroForApi(page,rows);
            if (null != unitList && unitList.size() > 0) {
                PageInfo<SysUnit> pageInfo = new PageInfo<>(unitList);
                json.setObj(new JqGridParam(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), unitList));
                json.setMsg("查询成功");
            } else {
                json.setMsg("查询暂无");
            }
            json.setSuccess(true);
        }catch (Exception e){
            json.setMsg("服务器异常!");
        }
        return json;
    }
}
