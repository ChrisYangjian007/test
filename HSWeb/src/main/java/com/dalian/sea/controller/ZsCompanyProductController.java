package com.dalian.sea.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalian.sea.AsyncTaskConfig.AsyncTaskConfig;
import com.dalian.sea.ClientIP;
import com.dalian.sea.ExcelUtil.ImportExcelUtil;
import com.dalian.sea.config.shiro.ShiroUser;
import com.dalian.sea.json.JqGridParam;
import com.dalian.sea.json.Json;
import com.dalian.sea.model.SysProductType;
import com.dalian.sea.model.SysSysLog;
import com.dalian.sea.parameter.*;
import com.dalian.sea.service.*;
import com.dalian.sea.ExcelUtil.ExcelUtil;
import com.dalian.sea.service.impl.AsyncTaskServiceImpl;
import com.dalian.sea.service.impl.ZsCompanyProductServiceImpl;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * ZsCompanyProductController
 *
 * @author xintao
 * @date 2018/1/23
 */
@Slf4j
@Controller
@RequestMapping(value = "/companyProduct")
public class ZsCompanyProductController extends LayoutRazor {
    @Autowired
    private ZsCompanyProductService companyProductService;
    @Autowired
    private SysProductTypeService productTypeService;
    @Autowired
    private BaDataDictionaryDetailsService dataDictionaryDetailsService;
    @Autowired
    private AsyncTaskService asyncTaskService;
    @Autowired
    private SysSysLogService sysSysLogService;
    @Value("${crm-url}")
    private String url;

    /**
     * 产品管理页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/productManage.htm")
    public String productManage(HttpServletRequest request) {
        List<PZsCompanyProduct> companyProducts = companyProductService.getType();
        if (null != companyProducts && 0 != companyProducts.size()) {
            request.setAttribute("type", companyProducts);
        }
        companyProducts = new ArrayList<>();
        companyProducts = companyProductService.getProductLine();
        if (null != companyProducts && 0 != companyProducts.size()) {
            request.setAttribute("companyProducts", companyProducts);
        }
        return freeMarkerIndexResult("companyProduct/productManage.ftl", request);
    }

    /**
     * 添加产品modal
     *
     * @param request
     * @return
     */
    @RequestMapping("/addProductModal.htm")
    public String addProductModal(HttpServletRequest request,Long resourceId) {
        request.setAttribute("resourceId", resourceId);
        List<PBaDataDictionaryDetails> dataDictionaryDetailsList = dataDictionaryDetailsService.getBaDataDictionaryDetailsByCode("0102010000");
        if (null != dataDictionaryDetailsList && 0 != dataDictionaryDetailsList.size()) {
            request.setAttribute("dataDictionaryDetailsList", dataDictionaryDetailsList);
        }
        List<SysProductType> productTypeList = productTypeService.getSysProductTypeByALevel(1);
        if (null != productTypeList && 0 != productTypeList.size()) {
            request.setAttribute("productTypeList", productTypeList);
        }
        return "/companyProduct/addProductModal";
    }


    /**
     * 修改产品
     *
     * @param request
     * @return
     */
    @RequestMapping("/updateProductModal.htm")
    public String updateProductModal(HttpServletRequest request, Long id,Long resourceId) {
        request.setAttribute("resourceId", resourceId);
        PZsCompanyProduct companyProduct = companyProductService.getZsCompanyProductById(id);
        if (null != companyProduct) {
            request.setAttribute("companyProduct", companyProduct);
            List<SysProductType> productCategory = productTypeService.getSysProductTypeByParentId(companyProduct.getProductLineId());
            if (null != productCategory && 0 != productCategory.size()) {
                request.setAttribute("productCategory", productCategory);
            }
            List<SysProductType> productType = productTypeService.getSysProductTypeByParentId(companyProduct.getProductCategoryId());
            if (null != productType && 0 != productType.size()) {
                request.setAttribute("productType", productType);
            }
        }
        List<PBaDataDictionaryDetails> dataDictionaryDetailsList = dataDictionaryDetailsService.getBaDataDictionaryDetailsByCode("0102010000");
        if (null != dataDictionaryDetailsList && 0 != dataDictionaryDetailsList.size()) {
            request.setAttribute("dataDictionaryDetailsList", dataDictionaryDetailsList);
        }
        List<SysProductType> productTypeList = productTypeService.getSysProductTypeByALevel(1);
        if (null != productTypeList && 0 != productTypeList.size()) {
            request.setAttribute("productTypeList", productTypeList);
        }
        return "/companyProduct/updateProductModal";
    }

    /**
     * 产品管理
     *
     * @param request
     * @param companyProduct
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/GridJson.json")
    @ResponseBody
    public Object GridJson(HttpServletRequest request, PZsCompanyProduct companyProduct, Integer page, Integer rows) {
        List<PZsCompanyProduct> zsCompanyProductList = companyProductService.getZsCompanyProductBy(companyProduct, page, rows);
        PageInfo<PZsCompanyProduct> pageInfo = new PageInfo<>(zsCompanyProductList);
        return new JqGridParam(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), zsCompanyProductList);
    }


    /**
     * 根据情况获取
     *
     * @param request
     * @param companyProduct
     * @return
     */
    @RequestMapping(value = "/getZsCompanyProductByProductType.json")
    @ResponseBody
    public Object getZsCompanyProductByProductType(HttpServletRequest request, PZsCompanyProduct companyProduct) {
        Json json = new Json();
        try {
            List<PZsCompanyProduct> companyProducts = companyProductService.getZsCompanyProductByProductType(companyProduct);
            if (null != companyProducts && 0 != companyProducts.size()) {
                json.setObj(companyProducts);
                json.setSuccess(true);
            } else {
                json.setMsg("失败");
            }
        } catch (Exception e) {
            log.info(String.valueOf(e));
            json.setObj(e);
            json.setMsg("服务器异常");
        }
        return json;
    }


    /**
     * 根据类型获取产品线
     * @param request
     * @param companyProduct
     * @return
     */
    @RequestMapping(value = "/getProductLineByType.json")
    @ResponseBody
    public Object getProductLineByType(HttpServletRequest request, PZsCompanyProduct companyProduct) {
        Json json = new Json();
        try {
            List<PZsCompanyProduct> companyProducts = companyProductService.getProductLineByType(companyProduct);
            if (null != companyProducts && 0 != companyProducts.size()) {
                json.setObj(companyProducts);
                json.setSuccess(true);
            } else {
                json.setMsg("失败");
            }
        } catch (Exception e) {
            log.info(String.valueOf(e));
            json.setObj(e);
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 添加产品
     *
     * @param request
     * @param companyProduct
     * @return
     */
    @RequestMapping(value = "/addZsCompanyProduct.json")
    @ResponseBody
    public Object addZsCompanyProduct(HttpServletRequest request, PZsCompanyProduct companyProduct,Long resourceId) {
        Json json = new Json();
        try {
            Subject user = SecurityUtils.getSubject();
            ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
            PZsCompanyProduct pZsCompanyProduct = companyProductService.getZsCompanyProductByNoAndNameAndSpec(companyProduct);
            if (null == pZsCompanyProduct) {
                companyProduct.setCreateUserId(shiroUser.getId());
                Long id = companyProductService.addZsCompanyProduct(companyProduct);
                if (null != id) {
                    SysSysLog sysLog = new SysSysLog();
                    String ip = ClientIP.getClientIP(request);
                    sysLog.setIpAddress(ip);
                    sysLog.setCName("添加产品");
                    sysLog.setRemark("添加("+companyProduct.getCName()+")产品");
                    sysLog.setResourceId(resourceId);
                    sysLog.setCreatedUserId(shiroUser.getId());
                    sysSysLogService.addSysSysLog(sysLog);
                    json.setMsg("添加成功");
                    json.setSuccess(true);
                } else {
                    json.setMsg("添加失败");
                }
            } else {
                json.setMsg("已有该编号、名称和规格的产品，请修改");
            }
        } catch (Exception e) {
            json.setObj(e);
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 修改产品
     *
     * @param request
     * @param companyProduct
     * @return
     */
    @RequestMapping(value = "/updateZsCompanyProductBy.json")
    @ResponseBody
    public Object updateZsCompanyProductBy(HttpServletRequest request, PZsCompanyProduct companyProduct,Long resourceId) {
        Json json = new Json();
        try {
            Subject user = SecurityUtils.getSubject();
            ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
            PZsCompanyProduct pZsCompanyProduct = companyProductService.getZsCompanyProductByNoAndNameAndSpec(companyProduct);
            if (null == pZsCompanyProduct) {
                companyProduct.setCreateUserId(shiroUser.getId());
                Boolean boo = companyProductService.updateZsCompanyProductBy(companyProduct);
                if (boo) {
                    SysSysLog sysLog = new SysSysLog();
                    String ip = ClientIP.getClientIP(request);
                    sysLog.setIpAddress(ip);
                    sysLog.setCName("修改产品");
                    sysLog.setRemark("修改("+companyProduct.getCName()+")产品");
                    sysLog.setResourceId(resourceId);
                    sysLog.setCreatedUserId(shiroUser.getId());
                    sysSysLogService.addSysSysLog(sysLog);
                    json.setMsg("修改成功");
                    json.setSuccess(true);
                } else {
                    json.setMsg("修改失败");
                }
            } else {
                json.setMsg("已有该编号、名称和规格的产品，请修改");
            }
        } catch (Exception e) {
            json.setObj(e);
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 删除产品
     *
     * @param request
     * @param companyProduct
     * @return
     */
    @RequestMapping(value = "/deleteZsCompanyProduct.json")
    @ResponseBody
    public Object deleteZsCompanyProduct(HttpServletRequest request, PZsCompanyProduct companyProduct,Long resourceId) {
        Json json = new Json();
        try {
            Subject user = SecurityUtils.getSubject();
            ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
            companyProduct.setCreateUserId(shiroUser.getId());
            Boolean boo = companyProductService.deleteZsCompanyProduct(companyProduct);
            if (boo) {
                SysSysLog sysLog = new SysSysLog();
                String ip = ClientIP.getClientIP(request);
                sysLog.setIpAddress(ip);
                sysLog.setCName("删除产品");
                sysLog.setRemark("删除("+companyProduct.getCName()+")产品");
                sysLog.setResourceId(resourceId);
                sysLog.setCreatedUserId(shiroUser.getId());
                sysSysLogService.addSysSysLog(sysLog);
                json.setMsg("删除成功");
                json.setSuccess(true);
            } else {
                json.setMsg("删除失败");
            }
        } catch (Exception e) {
            json.setObj(e);
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 模板导出
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/templateExport.json")
    @ResponseBody
    public Object templateExport(HttpServletRequest request, HttpServletResponse response,Long resourceId) {
        Json json = new Json();
        try {
            Subject user = SecurityUtils.getSubject();
            ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
            List<String> name = new ArrayList<>();
            List<List<?>> view = new ArrayList<>();
            List<String> str = new ArrayList<>();
            String title = "产品模板";
            name.add("产品编码");
            view.add(str);
            name.add("产品名称");
            view.add(str);
            name.add("类别");
            view.add(str);
            name.add("产品线");
            view.add(str);
            name.add("产品大类");
            view.add(str);
            name.add("产品小类");
            view.add(str);
            name.add("产品规格");
            view.add(str);
            name.add("单位");
            view.add(str);
            name.add("净重");
            view.add(str);
            ExcelUtil.xsl(title, name, view, request, response);
            SysSysLog sysLog = new SysSysLog();
            String ip = ClientIP.getClientIP(request);
            sysLog.setIpAddress(ip);
            sysLog.setCName("导出产品模板");
            sysLog.setRemark(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date())+"—导出产品模板");
            sysLog.setResourceId(resourceId);
            sysLog.setCreatedUserId(shiroUser.getId());
            sysSysLogService.addSysSysLog(sysLog);
        } catch (Exception e) {
            json.setObj(e);
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 产品导出
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/productExport.json")
    @ResponseBody
    public Object productExport(HttpServletRequest request, HttpServletResponse response,Long resourceId) {
        Json json = new Json();
        try {
            Subject user = SecurityUtils.getSubject();
            ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
            Boolean boo = companyProductService.productExport(request, response);
            if (boo) {
                SysSysLog sysLog = new SysSysLog();
                String ip = ClientIP.getClientIP(request);
                sysLog.setIpAddress(ip);
                sysLog.setCName("产品导出");
                sysLog.setRemark(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date())+"—产品导出");
                sysLog.setResourceId(resourceId);
                sysLog.setCreatedUserId(shiroUser.getId());
                sysSysLogService.addSysSysLog(sysLog);
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
     * 产品导入页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/productImportModal.htm")
    public String productImportModal(HttpServletRequest request,Long resourceId) {
        request.setAttribute("resourceId", resourceId);
        return "companyProduct/productImportModal";
    }


    /**
     * 产品导入
     *
     * @param request
     * @return
     */
    @RequestMapping("/productImport.json")
    @ResponseBody
    public Object productImport(HttpServletRequest request,Long resourceId) {
        Json json = new Json();
        Boolean isNull = true;
        try {
            Subject user = SecurityUtils.getSubject();
            ShiroUser shiroUser = (ShiroUser) user.getPrincipal();

            InputStream in = null;
            List<List<Object>> listob = null;
            List<List<List<Object>>> lists = new ArrayList<>();
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = multipartRequest.getFile("productImportFile");

            if (file.isEmpty()) {
                json.setMsg("需要导入的产品文件为空！");
            } else {
                in = file.getInputStream();
                listob = ImportExcelUtil.getBankListByExcel(in, file.getOriginalFilename());
                int listSize = listob.size();
                if (0 != listSize) {
                    // 存放所有的线程，用于获取结果
                    List<Future<List<Json>>> lstFuture = new ArrayList<>();
                    List<PZsCompanyProduct> companyProductList = new ArrayList<>();
                    if (100 < listSize) {
                        int rows = 5;
                        for (int i = 0; i < rows; i++) {
                            if (i != (rows - 1)) {
                                lists.add(listob.subList(((listSize / rows) * i), ((listSize / rows) * (i + 1))));
                            } else {
                                lists.add(listob.subList(((listSize / rows) * i), listSize));
                            }
                        }
                        for (int i = 0; i < lists.size(); i++) {
                            while (true) {
                                try {
                                    Future<List<Json>> future = asyncTaskService.productImport(lists.get(i), listob, lists.get(0).size(), i, shiroUser.getId());
                                    lstFuture.add(future);
                                    break;
                                } catch (TaskRejectedException e) {
                                    Thread.sleep(1000);
                                }
                            }
                        }
                        for (Future<List<Json>> future : lstFuture) {
                            for (Json json1 : future.get()) {
                                if (null == json1.getMsg() || "".equals(json1.getMsg())) {
                                    companyProductList.add((PZsCompanyProduct) json1.getObj());
                                } else {
                                    isNull = false;
                                    if (null != json.getMsg()) {
                                        json.setMsg(json.getMsg() + "，" + json1.getMsg());
                                    } else {
                                        json.setMsg(json1.getMsg());
                                    }
                                }
                            }
                        }
                    } else {
                        List<Json> jsonList = companyProductService.productImport(listob, shiroUser.getId());
                        for (Json json1 : jsonList) {
                            if (null == json1.getMsg() || "".equals(json1.getMsg())) {
                                companyProductList.add((PZsCompanyProduct) json1.getObj());
                            } else {
                                isNull = false;
                                if (null != json.getMsg()) {
                                    json.setMsg(json.getMsg() + "，" + json1.getMsg());
                                } else {
                                    json.setMsg(json1.getMsg());
                                }
                            }
                        }
                    }
                    if (isNull) {
                        Boolean boo = companyProductService.addZsCompanyProductList(companyProductList);
                        if (boo) {
                            SysSysLog sysLog = new SysSysLog();
                            String ip = ClientIP.getClientIP(request);
                            sysLog.setIpAddress(ip);
                            sysLog.setCName("产品导出");
                            sysLog.setRemark(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date())+" — 产品导出");
                            sysLog.setResourceId(resourceId);
                            sysLog.setCreatedUserId(shiroUser.getId());
                            sysSysLogService.addSysSysLog(sysLog);
                            json.setSuccess(true);
                            json.setMsg("导入成功！");
                        } else {
                            json.setMsg("导入失败！");
                        }
                    }
                } else {
                    json.setMsg("文件内容为空！");
                }
            }
        } catch (Exception e) {
            json.setObj(e);
            json.setMsg("服务器异常！");
        }
        return json;
    }

    /**
     * 产品同步
     *
     * @param
     * @return
     */
    @RequestMapping("/synchronizationProduct.json")
    @ResponseBody
    public Object productSynchronization(HttpServletRequest httpServletRequest,Long resourceId) throws IOException, InterruptedException, ExecutionException {
        Json json = new Json();
       log.info("查看参数"+resourceId);
        Subject user = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("serviceName", "clogin")
                .add("userName", "jiekou@xiaoqin.com")
                .add("password", "xiaoqin!@#$")
                .build();
        Request request = new Request.Builder()
                .url(url+"distributor.action")
                .post(body)
                .build();
       log.info("开始执行登录请求");
        Response response = client.newCall(request).execute();
       log.info("登录请求完成");
        String str = null;
        if (response.isSuccessful()) {
            str = response.body().string();
            log.info("获取登录请求结果"+str);
            UserInfo userInfo = JSON.parseObject(str, new TypeReference<UserInfo>() {
            });

            if (userInfo.isResult()) {
                RequestBody body1 = new FormBody.Builder()
                        .add("serviceName", "cquery")
                        .add("objectApiName", "product")
                        .add("expressions", "lb='货品'")
                        .add("binding", userInfo.binding)
                        .add("is_deleted", "0")
                        .build();
                Request request1 = new Request.Builder()
                        .url(url+"distributor.action")
                        .post(body1)
                        .build();
                log.info("开始执行数据请求");
                Response response1 = client.newCall(request1).execute();
                log.info("数据请求完成");
                String str1 = null;
                if (response1.isSuccessful()) {
                    str1 = response1.body().string();
                    log.info("获取数据请求结果"+str);
                    DataInfo dataInfo = JSON.parseObject(str1, new TypeReference<DataInfo>() {
                    });
                    List<ProductInfo> data = dataInfo.getData();
                    List<List<ProductInfo>> lists = new ArrayList<>();
                    int rows = 5;
                    for (int i = 0; i < rows; i++) {
                        if (i != (rows - 1)) {
                            lists.add(data.subList(((data.size() / rows) * i), ((data.size() / rows) * (i + 1))));
                        } else {
                            lists.add(data.subList(((data.size() / rows) * i), data.size()));
                        }
                    }
                    List<Future<Boolean>> lstFuture = new ArrayList<>();
                    log.info("开始执行数据库操作");
                        for (int i = 0; i < lists.size(); i++) {
                            Future<Boolean> future = asyncTaskService.productSynchronization(lists.get(i));
                            lstFuture.add(future);
                        }
                        Thread.sleep(3000);
                        for (int i = 0; i <lstFuture.size() ; i++) {
                            Future<Boolean> future = lstFuture.get(i);
                            Boolean aBoolean = future.get();
                            if (!aBoolean){
                                json.setMsg("同步失败");
                               return json;
                            }

                        }
//                    Boolean b = asyncTaskService.importProductSynchronization(lists);
//                    log.info("数据库操作完成"+b);
                        log.info("插入日志开始");
                        SysSysLog sysLog = new SysSysLog();
                        String ip = ClientIP.getClientIP(httpServletRequest);
                        sysLog.setIpAddress(ip);
                        sysLog.setCName("产品同步");
                        sysLog.setRemark(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date())+" — 产品同步");
                        sysLog.setResourceId(resourceId);
                        sysLog.setCreatedUserId(shiroUser.getId());
                        sysSysLogService.addSysSysLog(sysLog);
                        log.info("插入日志结束");
                        json.setSuccess(true);
                        json.setMsg("同步成功");
                        return json;


                }
            }

        }
        {
            json.setMsg("同步失败");
            return json;
        }


    }


}
