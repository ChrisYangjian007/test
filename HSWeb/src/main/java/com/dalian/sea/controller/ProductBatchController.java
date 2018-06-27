package com.dalian.sea.controller;

import Utils.GenerateUtils;
import com.dalian.sea.ClientIP;
import com.dalian.sea.json.JqGridParam;
import com.dalian.sea.json.Json;
import com.dalian.sea.model.*;
import com.dalian.sea.parameter.BatchEnterStockDetail;
import com.dalian.sea.parameter.PZsBatch;
import com.dalian.sea.parameter.PZsBatchOrder;
import com.dalian.sea.parameter.PZsCompanyProduct;
import com.dalian.sea.service.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2018/2/24.
 */
@Slf4j
@Controller
@RequestMapping(value = "/productBatch")
public class ProductBatchController extends LayoutRazor {

    @Autowired
    private ZsBatchService zsBatchService;
    @Autowired
    private ZsBatchOrderService zsBatchOrderService;
    @Autowired
    private ZsCompanyProductService companyProductService;
    @Autowired
    private AsyncTaskService asyncTaskService;
    @Autowired
    private SysSysLogService sysSysLogService;

    /**
     * 查看发货详情
     * @param request
     * @return
     */
    @RequestMapping(value = "/deliveryDetailsModal.htm")
    public String deliveryDetailsModal(HttpServletRequest request,Long batchId) {
        request.setAttribute("batchId",batchId);
        return "productBatch/deliveryDetailsModal";
    }
    @RequestMapping(value = "/GridJsonPack.json")
    @ResponseBody
    public Object GridJsonPack(ZsBatchOrder zsBatchOrder, Integer page, Integer rows) {
        List<PZsBatchOrder> pZsBatchList = zsBatchOrderService.selectBatchOrderListGropBy(zsBatchOrder, page, rows);
        PageInfo<PZsBatchOrder> pageInfo = new PageInfo<>(pZsBatchList);
        return new JqGridParam(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), pZsBatchList);
    }
    /**
     * 成品批次管理
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/productBatchManage.htm")
    public String productBatchManage(HttpServletRequest request) {
        List<PZsCompanyProduct> companyProducts = companyProductService.getType();
        if (null != companyProducts && 0 != companyProducts.size()) {
            request.setAttribute("type", companyProducts);
        }
        companyProducts = new ArrayList<>();
        companyProducts = companyProductService.getProductLine();
        if (null != companyProducts && 0 != companyProducts.size()) {
            request.setAttribute("companyProducts", companyProducts);
        }
        return freeMarkerIndexResult("productBatch/productBatchManage.ftl", request);
    }

    @RequestMapping(value = "/batchImportModal.htm")
    public String importBatchManage(HttpServletRequest request, Long resourceId) {
        request.setAttribute("resourceId", resourceId);
        return "/productBatch/batchImportModal";
    }

    /**
     * 批次列表
     *
     * @param pZsBatch
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/GridJson.json")
    @ResponseBody
    public Object GridJson(PZsBatch pZsBatch, Integer page, Integer rows) {
        List<PZsBatch> pZsBatchList = zsBatchService.selectZsBatchByBatch(pZsBatch, page, rows);
        PageInfo<PZsBatch> pageInfo = new PageInfo<>(pZsBatchList);
        return new JqGridParam(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), pZsBatchList);
    }
    @RequestMapping(value = "/IsPackGridJson.json")
    @ResponseBody
    public Object GridJsonIsPack(PZsBatch pZsBatch, Integer page, Integer rows) {
        List<PZsBatch> pZsBatchList = zsBatchService.selectZsBatchByBatchIsPack(pZsBatch, page, rows);
        PageInfo<PZsBatch> pageInfo = new PageInfo<>(pZsBatchList);
        return new JqGridParam(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), pZsBatchList);
    }
    @RequestMapping("/importBatch.json")
    @ResponseBody
    public Object batchImport(HttpServletRequest request) throws IOException {
        Json json = new Json();
        File tempFile = null;
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile multfile = multipartRequest.getFile("file");
            if (!multfile.isEmpty()) {
                String filePath = request.getSession().getServletContext().getRealPath("/") + "batch/";
                File dir = new File(filePath);
                if (!dir.exists()) {
                    dir.mkdir();
                }
                String path = filePath + multfile.getOriginalFilename();
                //save to the /upload path
                try {
                    tempFile = new File(path);
                    FileUtils.copyInputStreamToFile(multfile.getInputStream(), tempFile);
                } catch (Exception e) {
                    json.setMsg("文本内容太大,导入失败");
                    json.setSuccess(false);
                    return json;
                }
            }
        } catch (Exception e) {
            json.setMsg("数据转换异常,导入失败");
            json.setSuccess(false);
            return json;
        }
        PZsBatch zsBatch = new PZsBatch();
        List<BoxBatchTemp> batchTempList = new ArrayList<>();
        List<SaError> errorList = new ArrayList<>();
        String rom = String.valueOf(System.currentTimeMillis());
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(tempFile), "utf-8");
            BufferedReader br = new BufferedReader(read);//构造一个BufferedReader类来读取文件
            String s = null;
            int num = 0;
            String zsCode = null;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                if (s.length() > 0) {
                    String[] split = s.split("\\|");
                    //跳过标题
                    if (split.length > 1) {
                        if (num == 0) {
                            zsBatch.setTaskCode(split[0]);
                            zsBatch.setTaskName(split[1]);
                            zsBatch.setProductCode(split[2]);
                            zsBatch.setProductCategory(split[3]);
                            zsBatch.setProductName(split[4]);
                            zsBatch.setProductFormat(split[5]);
                            zsBatch.setProductUnit(split[6]);
                            zsBatch.setProductWeight(split[7]);
                            zsBatch.setProductLine(split[8]);
                            zsBatch.setProductBigType(split[9]);
                            zsBatch.setProductSmallType(split[10]);
                            zsBatch.setBatchCode(split[11]);
                            zsBatch.setBatchName(split[12]);
                            zsBatch.setBatchDate(split[13]);
                            zsBatch.setBatchPack(split[14]);
                            zsBatch.setBatchUnique(zsBatch.getTaskCode() + zsBatch.getProductCode() + zsBatch.getBatchCode() + zsBatch.getBatchDate());
                        }
                        if (num == 1) {
                            BoxBatchTemp batchTemp = new BoxBatchTemp();
                            batchTemp.setQrCode(split[0]);
                            zsCode = batchTemp.getQrCode().substring(batchTemp.getQrCode().length() - 14, batchTemp.getQrCode().length());
                            batchTemp.setQrClearCode(GenerateUtils.zstoCode(zsCode));
                            batchTemp.setQrRuleName(zsCode.substring(0, 4));
                            batchTemp.setBoxCode(split[1]);
                            batchTemp.setOperator(split[2]);
                            batchTemp.setOperationTime(split[3]);
                            batchTemp.setCreateDate(new Date());
                            batchTemp.setRandom(rom);
                            batchTempList.add(batchTemp);
                        }
                        if (num == 2) {
                            SaError saError = new SaError();
                            saError.setErrorCode(split[0]);
                            saError.setErrorReason(split[1]);
                            saError.setErrorDate(split[2]);
                            saError.setType("2");
                            errorList.add(saError);
                        }
                    }
                } else {
                    //读到空白,数量加1
                    num++;
                }

            }
            read.close();
            br.close();
            tempFile.delete();

        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg("txt数据解析有误");
            return json;
        }
        if (zsBatch.getBatchUnique() != null) {
            PZsBatch batchByBatch = zsBatchService.getBatchByBatch(zsBatch);
            if (batchByBatch != null) {
                json.setMsg("批次已导入,勿重复导入");
                json.setSuccess(false);
                return json;
            }
            Boolean b = asyncTaskService.importBatchByList(zsBatch, batchTempList, errorList, rom);
            if (b) {
                SysSysLog sysLog = new SysSysLog();
                String ip = ClientIP.getClientIP(request);
                sysLog.setIpAddress(ip);
                sysLog.setCName("批次导入");
                sysLog.setRemark(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()) + " — 批次导入");
                sysSysLogService.addSysSysLog(sysLog);
                json.setMsg("导入成功");
                json.setSuccess(true);
                return json;
            } else {
                json.setMsg("服务器异常,导入失败");
                json.setSuccess(false);
                return json;
            }
        } else {
            json.setMsg("无批次信息,不可导入");
            json.setSuccess(false);
            return json;
        }

    }

    /**
     * 新建入库
     *
     * @return
     */
    @RequestMapping("/addStockBatchModal.htm")
    public String addStockBatch(HttpServletRequest request) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        Date now = new Date();
        StringBuilder sb = new StringBuilder();
        sb.append("NO.RK");
        sb.append(sdf.format(now));
        sb.append(new Random().nextInt(99));
        request.setAttribute("addStockBatchNode", sb.toString());
        return "productBatch/addStockBatchModal";
    }

    /**
     * 根据批次id回显入库详情
     *
     * @param batchId
     * @return
     */
    @RequestMapping("/getDetailByBatchId.json")
    @ResponseBody
    public Json getDetailByBatchId(Long batchId) {
        Json json = new Json();
        try {
            BatchEnterStockDetail ba= zsBatchService.getEnterStockDetailByBatchId(batchId);
            if (null != ba) {
                json.setSuccess(true);
                json.setObj(ba);
            } else {
                json.setMsg("该批次产品不存在");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
            e.printStackTrace();
        }
        return json;
    }

//    @RequestMapping("/importBatchIo.json")
//    @ResponseBody
//    public Object batchImportByIo(HttpServletRequest request) throws IOException {
//        ServletInputStream sis = request.getInputStream();
//        int contentLength = request.getContentLength();
//
//        System.out.println("文件长度"+contentLength);
//        Json json = new Json();
//        String filePath = request.getSession().getServletContext().getRealPath("/batch.txt");
//        File dir = new File(filePath);
//        if (!dir.exists()) {
//            dir.createNewFile();
//        }
//        try {
//            FileOutputStream fos = new FileOutputStream(filePath);
//            byte[] media = new byte[1024];
//            int length = sis.read(media, 0, 1024);
//            while (length != -1) {
//                fos.write(media, 0, length);
//                length = sis.read(media, 0, 1024);
//            }
//            fos.close();
//            sis.close();
//        } catch (Exception e) {
//            json.setMsg("io流读取失败");
//            json.setSuccess(false);
//            return json;
//        }
//
//        try {
//            InputStreamReader read = new InputStreamReader(new FileInputStream(dir), "utf-8");
//            BufferedReader br = new BufferedReader(read);//构造一个BufferedReader类来读取文件
//            String s = null;
//            int num = 0;
//            PZsBatch zsBatch = new PZsBatch();
//            List<BoxBatchTemp> batchTempList = new ArrayList<>();
//            List<SaError> errorList = new ArrayList<>();
//            String rom = String.valueOf(System.currentTimeMillis());
//            String zsCode = null;
//            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
//                if (s.length() > 0) {
//                    String[] split = s.split("\\|");
//                    //跳过标题
//                    if (split.length > 1) {
//                        if (num == 0) {
//                            zsBatch.setTaskCode(split[0]);
//                            zsBatch.setTaskName(split[1]);
//                            zsBatch.setProductCode(split[2]);
//                            zsBatch.setProductCategory(split[3]);
//                            zsBatch.setProductName(split[4]);
//                            zsBatch.setProductFormat(split[5]);
//                            zsBatch.setProductUnit(split[6]);
//                            zsBatch.setProductWeight(split[7]);
//                            zsBatch.setProductLine(split[8]);
//                            zsBatch.setProductBigType(split[9]);
//                            zsBatch.setProductSmallType(split[10]);
//                            zsBatch.setBatchCode(split[11]);
//                            zsBatch.setBatchDate(split[12]);
//                            zsBatch.setBatchPack(split[13]);
//                        }
//                        if (num == 1) {
//                            BoxBatchTemp batchTemp = new BoxBatchTemp();
//                            batchTemp.setQrCode(split[0]);
//                            zsCode = batchTemp.getQrCode().substring(batchTemp.getQrCode().length() - 14, batchTemp.getQrCode().length());
//                            batchTemp.setQrClearCode(GenerateUtils.zstoCode(zsCode));
//                            batchTemp.setQrRuleName(zsCode.substring(0, 4));
//                            batchTemp.setBoxCode(split[1]);
//                            batchTemp.setOperator(split[2]);
//                            batchTemp.setOperationTime(split[3]);
//                            batchTemp.setRandom(rom);
//                            batchTempList.add(batchTemp);
//                        }
//                        if (num == 2) {
//                            SaError saError = new SaError();
//                            saError.setErrorCode(split[0]);
//                            saError.setErrorReason(split[1]);
//                            saError.setErrorDate(split[2]);
//                            saError.setType("2");
//                            errorList.add(saError);
//                        }
//                    }
//                } else {
//                    //读到空白,数量加1
//                    num++;
//                }
//
//            }
//            br.close();
//            dir.delete();
//            if (zsBatch.getTaskCode() != null) {
//                PZsBatch batchByBatch = zsBatchService.getBatchByBatch(zsBatch);
//                if (batchByBatch != null) {
//                    json.setMsg("批次已导入,勿重复导入");
//                    json.setSuccess(false);
//                    return json;
//                }
//                Boolean b = asyncTaskService.importBatchByList(zsBatch, batchTempList, errorList, rom);
//                if (b) {
//                    json.setMsg("导入成功");
//                    json.setSuccess(true);
//                    return json;
//                } else {
//                    json.setMsg("服务器异常,导入失败");
//                    json.setSuccess(false);
//                    return json;
//                }
//            } else {
//                json.setMsg("无批次信息,不可导入");
//                json.setSuccess(false);
//                return json;
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            json.setMsg("txt数据解析有误");
//            return json;
//        }
//    }
}
