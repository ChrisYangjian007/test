package com.dalian.sea.controller;


import Utils.GenerateUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.dalian.sea.ClientIP;
import com.dalian.sea.config.shiro.ShiroUser;
import com.dalian.sea.json.JqGridParam;
import com.dalian.sea.json.Json;
import com.dalian.sea.model.SaError;
import com.dalian.sea.model.SaOrder;
import com.dalian.sea.model.SysSysLog;
import com.dalian.sea.parameter.*;
import com.dalian.sea.service.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


/**
 * @author 杨建
 * @date 2018/3/22
 */
@Slf4j
@Controller
@RequestMapping(value = "/saOrder")
public class SaOrderController extends LayoutRazor {
    @Autowired
    private SaOrderService orderService;
    @Autowired
    private SaOrderDetailService orderDetailService;
    @Autowired
    private AsyncTaskService asyncTaskService;
    @Autowired
    private ZsProduceTaskService zsProduceTaskService;
    @Autowired
    private ZsCompanyProductService companyProductService;
    @Autowired
    private SysSysLogService sysSysLogService;
    @Value("${crm-url}")
    private String url;
    /**
     * 订单页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/orderManage.htm")
    public String orderManage(HttpServletRequest request) {
        return freeMarkerIndexResult("saOrder/orderManage.ftl", request);
    }

    @RequestMapping(value = "/importOrderModal.htm")
    public String importOrderManage(HttpServletRequest request) {
        return "saOrder/orderImportModal";
    }

    /**当日订单统计
     * @param request
     * @return
     */
    @RequestMapping(value = "/orderCountTodayModal.htm")
    public String importOrderTodayModal(HttpServletRequest request) {
        List<POrderDetail> orderDetailList = orderDetailService.getOrderDetailCountToday();
//        request.setAttribute("orderDetailList",orderDetailList);
        return "saOrder/orderCountTodayModal";
    }
    @RequestMapping("/GridJsonTongji.json")
    @ResponseBody
    public Object DetailGridTongjiJson(POrderDetail orderDetail, Integer page, Integer rows) {
         if (orderDetail.getStartDate()==null){
             List<POrderDetail> orderDetailList = orderDetailService.getOrderDetailCountToday();
             PageInfo<POrderDetail> pageInfo = new PageInfo<>(orderDetailList);
             return new JqGridParam(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), orderDetailList);
         }
        if (orderDetail.getStartDate()!=null&& orderDetail.getStartDate()!=""){
            orderDetail.setStartDate(orderDetail.getStartDate()+" 00:00:00");
        }
        if (orderDetail.getEndDate()!=null&& orderDetail.getEndDate()!=""){
            orderDetail.setEndDate(orderDetail.getEndDate()+" 23:59:59");
        }
        List<POrderDetail> orderDetailList = orderDetailService.getOrderDetailCountbyday(orderDetail, page, rows);
        PageInfo<POrderDetail> pageInfo = new PageInfo<>(orderDetailList);
        return new JqGridParam(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), orderDetailList);
    }

    /**
     * 订单详情
     * @param request
     * @param orderName
     * @return
     */
    @RequestMapping(value = "/orderDetailIFrame.htm")
    public String orderDetailIFrame(HttpServletRequest request,String orderName) {
        PSaOrder pSaOrder = new PSaOrder();
        pSaOrder.setOrderName(orderName);
        PSaOrder saOrder = orderService.selectSaOrderByName(pSaOrder);
        request.setAttribute("saOrder",saOrder);
        return freeMarkerIndexResult("saOrder/orderDetailIFrame.ftl", request);
    }
    /**
     * 加载明细表格
     */
    @RequestMapping("/orderDetailGrid.json")
    @ResponseBody
    public Object DetailGridJson(POrderDetail orderDetail, Integer page, Integer rows) {
        List<POrderDetail> orderDetailList = orderDetailService.selectListOrderDetailByOrder(orderDetail, page, rows);
        PageInfo<POrderDetail> pageInfo = new PageInfo<>(orderDetailList);
        return new JqGridParam(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), orderDetailList);
    }
    /**
     * 加载表格
     */
    @RequestMapping("/GridJson.json")
    @ResponseBody
    public Object GridJson(PSaOrder order, Integer page, Integer rows) {
        List<PSaOrder> orderList = orderService.selectListOrderByOrder(order, page, rows);
        PageInfo<PSaOrder> pageInfo = new PageInfo<>(orderList);
        return new JqGridParam(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), orderList);
    }
    /**
     * 加载已打包表格
     */
    @RequestMapping("/GridJson1.json")
    @ResponseBody
    public Object GridJson2(PSaOrder order, Integer page, Integer rows) {
        order.setOrderIspack((byte) 1);
        List<PSaOrder> orderList = orderService.selectListOrderByOrder(order, page, rows);
        PageInfo<PSaOrder> pageInfo = new PageInfo<>(orderList);
        return new JqGridParam(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), orderList);
    }
    @RequestMapping("/getIsOrder.json")
    @ResponseBody
    public Object getOrderByName(PSaOrder order) {
        Json json = new Json();
        try {
            PSaOrder pSaOrder = orderService.selectSaOrderByName(order);
            if (pSaOrder!=null){
                json.setSuccess(true);
                json.setObj(pSaOrder);
            }else {
                json.setSuccess(false);
            }

        }catch (Exception e){
            json.setSuccess(false);
        }
         return json;

    }
    /**订单上传
     * @param request
     * @return
     */
    @RequestMapping("/importOrder.json")
    @ResponseBody
    public Object productImport(HttpServletRequest request) {
        Json json = new Json();
        File tempFile = null;
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile multfile = multipartRequest.getFile("file");
            if (!multfile.isEmpty()) {
                String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/";
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
                    json.setMsg("数据量太大,导入失败");
                    json.setSuccess(false);
                    return json;
                }
            }
        }catch (Exception e){
            json.setMsg("数据转换异常,导入失败");
            json.setSuccess(false);
            return json;
        }

        List<SaOrder> orderList = new ArrayList<>();
        List<PBoxOrderTemp> orderTempList=new ArrayList<>();
        List<SaError> errorList=new ArrayList<>();
        String rom = String.valueOf(System.currentTimeMillis());
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(tempFile), "utf-8");
            BufferedReader br = new BufferedReader(read);//构造一个BufferedReader类来读取文件
            String s = null;
            int num = 0;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                if (s.length() > 0) {
                    String[] split = s.split("\\|");
                    //跳过标题
                    if (split.length > 1) {
                        if (num == 0) {
                            SaOrder saOrder = new SaOrder();
                            saOrder.setOrderName(split[0]);
                            saOrder.setOrderIspack((byte) 1);
                            saOrder.setOrderPackCount(Integer.valueOf(split[8]));
                            orderList.add(saOrder);
                        }
                        if (num==1){
                            PBoxOrderTemp orderTemp = new PBoxOrderTemp();
                            orderTemp.setQrCode(split[0]);
                            orderTemp.setBoxCode(split[1]);
                            orderTemp.setHeapCode(split[2]);
                            orderTemp.setOrderCode(split[3]);
                            orderTemp.setOrderDetailCode(split[4]);
                            orderTemp.setProductCode(split[5]);
                            orderTemp.setProductName(split[6]);
                            orderTemp.setProductFormat(split[7]);
                            orderTemp.setProductUnit(split[8]);
                            orderTemp.setCreateDate(new Date());
                            orderTemp.setCreateUserName("ceshi");
                            orderTemp.setRandom(rom);
                            orderTempList.add(orderTemp);
                        }
                        if (num==2){
                            SaError saError = new SaError();
                            saError.setErrorCode(split[0]);
                            saError.setErrorReason(split[1]);
                            saError.setErrorDate(split[2]);
                            errorList.add(saError);
                        }
                    }
                } else {
                    //读到空白,数量加1
                    num++;
                }

            }
            br.close();
            tempFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg("txt数据解析有误");
            return json;

        }
            if (orderList.size()>0){
                SaOrder saOrder = orderList.get(0);
                PSaOrder pSaOrder = new PSaOrder();
                pSaOrder.setOrderName(saOrder.getOrderName());
                PSaOrder order = orderService.selectSaOrderByName(pSaOrder);
                if (order.getOrderIspack()==1){
                    json.setMsg("订单已导入,勿重复导入");
                    json.setSuccess(false);
                    return json;
                }
                Boolean b = asyncTaskService.importOrder(orderList, orderTempList, errorList, rom);
                if (b){
                    json.setMsg("导入成功");
                    json.setSuccess(true);
                    return json;
                }else {
                    json.setMsg("数据有误，导入失败");
                    json.setSuccess(false);
                    return json;
                }
            }else {
                json.setMsg("无订单信息,不可导入");
                json.setSuccess(false);
                return json;
            }


    }

    /**
     * 订单同步
     *
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @RequestMapping("/orderProduct.json")
    @ResponseBody
    public Object orderSynchronization(String startDate, String endDate,Long resourceId,HttpServletRequest httpServletRequest) throws IOException, InterruptedException, ExecutionException, ParseException {
        Json json = new Json();
        Subject user = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sf.parse(endDate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
        Date tomorrow = c.getTime();
        endDate=new SimpleDateFormat("yyyy-MM-dd").format(tomorrow);
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

        Response response = client.newCall(request).execute();
        UserInfo userInfo = null;
        if (response.isSuccessful()) {
            userInfo = JSON.parseObject(response.body().string(), new TypeReference<UserInfo>() {
            });
            if (userInfo.isResult()) {
                RequestBody body1 = new FormBody.Builder()
                        .add("serviceName", "cquery")
                        .add("objectApiName", "dhsqd")
                        .add("expressions", "dhrq>='" + startDate + "'and dhrq<'" + endDate + "'"+"and spzt='已批准'")
                        .add("binding", userInfo.binding)
                        .add("is_deleted", "0")
                        .build();
                Request request1 = new Request.Builder()
                        .url(url+"distributor.action")
                        .post(body1)
                        .build();
                Response response1 = client.newCall(request1).execute();
                List<List<OrderInfo>> lists = new ArrayList<>();
                List<List<OrderDetailInfo>> lists1 = new ArrayList<>();
                List<OrderInfo> orderdata=new ArrayList<>();
                if (response1.isSuccessful()) {
                    OrderResult orderResult = JSON.parseObject(response1.body().string(), new TypeReference<OrderResult>() {
                    });
                    orderdata = orderResult.getData();
                    int rows = 5;
                    for (int i = 0; i < rows; i++) {
                        if (i != (rows - 1)) {
                            lists.add(orderdata.subList(((orderdata.size() / rows) * i), ((orderdata.size() / rows) * (i + 1))));
                        } else {
                            lists.add(orderdata.subList(((orderdata.size() / rows) * i), orderdata.size()));
                        }
                    }
                }
                //拉取明细
                RequestBody body2 = new FormBody.Builder()
                        .add("serviceName", "cquery")
                        .add("objectApiName", "dhsqmx")
                        .add("expressions", "createdate>='" + startDate + "'and createdate<'" + endDate + "'")
                        .add("binding", userInfo.binding)
                        .add("is_deleted", "0")
                        .build();
                Request request2 = new Request.Builder()
                        .url(url+"distributor.action")
                        .post(body2)
                        .build();
                Response response2 = client.newCall(request2).execute();
                List<OrderDetailInfo> orderDetailInfoList = null;
                if (response2.isSuccessful()) {
                    JSONObject object = JSON.parseObject(response2.body().string());
                    String data = object.getString("data");
                    orderDetailInfoList = JSON.parseObject(data, new TypeReference<List<OrderDetailInfo>>() {
                    }.getType());
                } else {
                    json.setSuccess(false);
                    json.setMsg("订货明细拉取失败");
                    return json;
                }
                RequestBody body3 = new FormBody.Builder()
                        .add("serviceName", "cquery")
                        .add("objectApiName", "jxsdhsqmx")
                        .add("expressions", "createdate>='" + startDate + "'and createdate<'" + endDate + "'")
                        .add("binding", userInfo.binding)
                        .add("is_deleted", "0")
                        .build();
                Request request3 = new Request.Builder()
                        .url(url+"distributor.action")
                        .post(body3)
                        .build();
                Response response3 = client.newCall(request3).execute();
                List<OrderDetailInfo> orderDetailInfoList1 = null;
                if (response3.isSuccessful()) {
                    JSONObject object = JSON.parseObject(response3.body().string());
                    String data = object.getString("data");
                    orderDetailInfoList1 = JSON.parseObject(data, new TypeReference<List<OrderDetailInfo>>() {
                    }.getType());
                } else {
                    json.setSuccess(false);
                    json.setMsg("经销商订货明细拉取失败");
                    return json;
                }
                orderDetailInfoList.addAll(orderDetailInfoList1);
                int rows = 5;
                for (int i = 0; i < rows; i++) {
                    if (i != (rows - 1)) {
                        lists1.add(orderDetailInfoList.subList(((orderDetailInfoList.size() / rows) * i), ((orderDetailInfoList.size() / rows) * (i + 1))));
                    } else {
                        lists1.add(orderDetailInfoList.subList(((orderDetailInfoList.size() / rows) * i), orderDetailInfoList.size()));
                    }
                }
//                List<Future<Boolean>>  listFutureList=new ArrayList<>();
//                for (int i = 0; i <lists.size() ; i++) {
//                    Future<Boolean> orderFuture = orderService.orderSynchronization(lists.get(i));
//                    listFutureList.add(orderFuture);
//                }
//                for (int i = 0; i <lists1.size() ; i++) {
//                    Future<Boolean> orderDetailFuture = orderDetailService.orderDetailSynchronization(lists1.get(i));
//                    listFutureList.add(orderDetailFuture);
//                }

                List<Future<Boolean>> listFutureList = asyncTaskService.orderAndDetailSynchronization(lists, lists1);
                Thread.sleep(5000);
                for (int i = 0; i <listFutureList.size() ; i++) {
                    Future<Boolean> future = listFutureList.get(i);
                    if (!future.get()){
                        json.setMsg("同步失败");
                        json.setSuccess(false);
                        return json;
                    }
                }
                SysSysLog sysLog = new SysSysLog();
                String ip = ClientIP.getClientIP(httpServletRequest);
                sysLog.setIpAddress(ip);
                sysLog.setCName("订单同步");
                sysLog.setResourceId(resourceId);
                sysLog.setCreatedUserId(shiroUser.getId());
                sysSysLogService.addSysSysLog(sysLog);
                    json.setMsg("成功同步"+orderdata.size()+"个订单");
                    json.setSuccess(true);
                    return json;
            }
        }
        {
            json.setMsg("用户登录失败");
            return json;
        }


    }

    @RequestMapping(value = "/downOrder.htm")
    public void downGenerate(HttpServletRequest request, String startDate, String endDate, HttpServletResponse response) throws IOException, ParseException {
//        if (startDate==null && endDate==null){
//            Date date = new Date();
//            startDate=new SimpleDateFormat("yyyy-MM-dd").format(date);
//            endDate=new SimpleDateFormat("yyyy-MM-dd").format(date);
//        }
//        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = sf.parse(endDate);
//        Calendar c = Calendar.getInstance();
//        c.setTime(date);
//        c.add(Calendar.DAY_OF_MONTH, 1);// 时间+1天
//        Date tomorrow = c.getTime();
//        endDate=new SimpleDateFormat("yyyy-MM-dd").format(tomorrow);
        endDate=endDate+" 23:59:59";
        PSaOrder order = new PSaOrder();
        order.setStartDate(startDate);
        order.setEndDate(endDate);
        order.setOrderIspack((byte) 0);
        order.setOrderSpzt("已批准");
        List<PSaOrder> saOrderList = orderService.selectListOrderdown(order);
//        POrderDetail detail = new POrderDetail();
//        detail.setStartDate(startDate);
//        detail.setEndDate(endDate);
//        List<POrderDetail> detailList = orderDetailService.selectListOrderDetailDown(detail);
        List<String> list=new ArrayList<>();
        for (int i = 0; i < saOrderList.size(); i++) {
            list.add(saOrderList.get(i).getOrderName());
        }
        List<POrderDetail> detailList=new ArrayList<>();
        if (list.size()>0){
            detailList =orderDetailService.selectOrderDetailByOrderList(list);
        }

        //导出txt文件
        response.setContentType("text/plain");
        String title = "订单信息";
        String userAgent = request.getHeader("USER-AGENT");
        String importTitle = GenerateUtils.getImportTitle(userAgent, title);
        response.setHeader("Content-Disposition", "attachment; filename=" + importTitle + ".txt");
//        BufferedOutputStream buff = null;
        ServletOutputStream outSTr = null;
        OutputStreamWriter osw=null;
        BufferedWriter bufw=null;
        try {
            outSTr = response.getOutputStream(); // 建立
//            buff = new BufferedOutputStream(outSTr);
            osw = new OutputStreamWriter(outSTr);
            bufw = new BufferedWriter(osw);
            //把内容写入文件
//            buff.write(("OrderInfo:" + System.getProperty("line.separator")).getBytes("utf-8"));
            bufw.write("OrderInfo:"+"\r\n");
            for (int i = 0; i < saOrderList.size(); i++) {
                PSaOrder pSaOrder = saOrderList.get(i);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = formatter.format(pSaOrder.getOrderDhrq());
                if (pSaOrder.getOrderShr() == null) {
                    pSaOrder.setOrderShr("暂无");
                }
                if (pSaOrder.getOrderShdz() == null) {
                    pSaOrder.setOrderShdz("暂无");
                }
                if (pSaOrder.getOrderLxdh() == null) {
                    pSaOrder.setOrderLxdh("暂无");
                }
                if (pSaOrder.getOrderSsmd() == null) {
                    pSaOrder.setOrderSsmd("暂无");
                }
                if (pSaOrder.getOrderJxs() == null) {
                    pSaOrder.setOrderJxs("暂无");
                }
//                buff.write((pSaOrder.getOrderName() + "|" + dateString + "|" + pSaOrder.getOrderOwnerid() + "|" + pSaOrder.getOrderShr() + "|" + pSaOrder.getOrderShdz() + "|" + pSaOrder.getOrderLxdh() + "|" + pSaOrder.getOrderSsmd() + "|" + pSaOrder.getOrderJxs() + System.getProperty("line.separator")).getBytes("utf-8"));
                bufw.write(pSaOrder.getOrderName() + "|" + dateString + "|" + pSaOrder.getOrderOwnerid() + "|" + pSaOrder.getOrderShr() + "|" + pSaOrder.getOrderShdz() + "|" + pSaOrder.getOrderLxdh() + "|" + pSaOrder.getOrderSsmd() + "|" + pSaOrder.getOrderJxs()+"\r\n");
//                 bufw.newLine();
            }
//            buff.write(System.getProperty("line.separator").getBytes("utf-8"));
               bufw.write("\r\n");
//            buff.write(("ProdInfo:" + System.getProperty("line.separator")).getBytes("utf-8"));
            bufw.write("ProdInfo:"+"\r\n");
            for (int i = 0; i < detailList.size(); i++) {
                POrderDetail pOrderDetail = detailList.get(i);
                if (pOrderDetail.getOrderDetailName() == null) {
                    pOrderDetail.setOrderDetailName("暂无");
                }
                if (pOrderDetail.getOrderDetailDhsq() == null) {
                    pOrderDetail.setOrderDetailDhsq("暂无");
                }
                if (pOrderDetail.getOrderDetailCpbh() == null) {
                    pOrderDetail.setOrderDetailCpbh("暂无");
                }
                if (pOrderDetail.getProductName() == null) {
                    pOrderDetail.setProductName("暂无");
                }
                if (pOrderDetail.getOrderDetailGg() == null) {
                    pOrderDetail.setOrderDetailGg("暂无");
                }
                if (pOrderDetail.getOrderDetailDw() == null) {
                    pOrderDetail.setOrderDetailDw("暂无");
                }
                if (pOrderDetail.getOrderDetailSl() == null) {
                    pOrderDetail.setOrderDetailSl("0");
                }
//                buff.write((pOrderDetail.getOrderDetailName() + "|" + pOrderDetail.getOrderDetailDhsq() + "|" + pOrderDetail.getOrderDetailCpbh() + "|" + pOrderDetail.getProductName() + "|" + pOrderDetail.getOrderDetailGg() + "|" + pOrderDetail.getOrderDetailDw() + "|" + pOrderDetail.getOrderDetailSl() + System.getProperty("line.separator")).getBytes("utf-8"));
                bufw.write(pOrderDetail.getOrderDetailName() + "|" + pOrderDetail.getOrderDetailDhsq() + "|" + pOrderDetail.getOrderDetailCpbh() + "|" + pOrderDetail.getProductName() + "|" + pOrderDetail.getOrderDetailGg() + "|" + pOrderDetail.getOrderDetailDw() + "|" + pOrderDetail.getOrderDetailSl()+"\r\n");
            }
//            buff.flush();
//            buff.close();
            bufw.flush();
            bufw.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
//                buff.close();
                outSTr.close();
                bufw.close();
                osw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
   @RequestMapping("/downTaskProduce")
    public void downTaskProduce(PZsCompanyProduct companyProduct,PZsProduceTask pZsProduceTask,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
       List<PZsProduceTask> taskList = zsProduceTaskService.selectProduceTaskList(pZsProduceTask);
       companyProduct.setType(21L);
       List<PZsCompanyProduct> productList = companyProductService.getZsCompanyProductList(companyProduct);
//       companyProductService.se
       //导出txt文件
       response.setContentType("text/plain");
       String title = "采集信息";
       String userAgent = request.getHeader("USER-AGENT");
       String importTitle = GenerateUtils.getImportTitle(userAgent, title);
       response.setHeader("Content-Disposition", "attachment; filename=" + importTitle + ".txt");
//       BufferedOutputStream buff = null;
       ServletOutputStream outSTr = null;
       OutputStreamWriter osw=null;
       BufferedWriter bufw=null;
       try {
           outSTr = response.getOutputStream(); // 建立
//           buff = new BufferedOutputStream(outSTr);
           osw = new OutputStreamWriter(outSTr);
           bufw = new BufferedWriter(osw);
           //把内容写入文件
//           buff.write(("TaskInfo:" + System.getProperty("line.separator")).getBytes("utf-8"));
           bufw.write("TaskInfo:"+"\r\n");
//           bufw.newLine();
           for (int i = 0; i < taskList.size(); i++) {
               PZsProduceTask task = taskList.get(i);
               SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
               String time = sdf.format(task.getCreateDate());
//               buff.write((task.getProduceTaskNo() + "|" + task.getProduceTaskName() + "|" + time + System.getProperty("line.separator")).getBytes("utf-8"));
               bufw.write(task.getProduceTaskNo() + "|" + task.getProduceTaskName() + "|" + time+"\r\n" );
//               bufw.newLine();
           }
//           buff.write(System.getProperty("line.separator").getBytes("utf-8"));
           bufw.write("\r\n");
//           buff.write(("ProdInfo:" + System.getProperty("line.separator")).getBytes("utf-8"));
           bufw.write("ProdInfo:"+"\r\n");
//           bufw.newLine();
           for (int i = 0; i <productList.size() ; i++) {
               PZsCompanyProduct product = productList.get(i);
               if (product.getProductNo()==null){
                   product.setProductNo("暂无");
               }
               if (product.getTypeName()==null){
                   product.setTypeName("暂无");
               }
               if (product.getCName()==null){
                   product.setCName("暂无");
               }
               if (product.getProductSpecification()==null){
                   product.setProductSpecification("暂无");
               }
               if (product.getSysUnitName()==null){
                   product.setSysUnitName("暂无");
               }
               if (product.getNetWeight()==null){
                   product.setNetWeight("暂无");
               }
               if (product.getProductLine()==null){
                   product.setProductLine("暂无");
               }
               if (product.getProductCategory()==null){
                   product.setProductCategory("暂无");
               }
               if (product.getProductTypeName()==null){
                   product.setProductTypeName("暂无");
               }
               if (product.getRemark()==null){
                   product.setRemark("暂无");
               }
               bufw.write(product.getProductNo() + "|" + product.getTypeName() + "|" + product.getCName()+ "|" + product.getProductSpecification() + "|" + product.getSysUnitName() + "|" + product.getNetWeight() + "|" + product.getProductLine() + "|"+product.getProductCategory()+ "|"+product.getProductTypeName()+ "|"+product.getRemark()+"\r\n");
//               bufw.newLine();
               //               buff.write((product.getProductNo() + "|" + product.getTypeName() + "|" + product.getCName()+ "|" + product.getProductSpecification() + "|" + product.getSysUnitName() + "|" + product.getNetWeight() + "|" + product.getProductLine() + "|"+product.getProductCategory()+ "|"+product.getProductTypeName()+ "|"+product.getRemark()+ System.getProperty("line.separator")).getBytes("utf-8"));
           }
//           buff.flush();
//           buff.close();
           bufw.flush();
           bufw.close();
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           try {
//               buff.close();
               outSTr.close();
               osw.close();
               bufw.close();
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
   }
//    /**订单上传
//     * @param request
//     * @return
//     */
//    @RequestMapping("/importOrderIo.json")
//    @ResponseBody
//    public Object productImportIo(HttpServletRequest request) throws IOException {
//        ServletInputStream sis = request.getInputStream();
//        Json json = new Json();
//        String filePath = request.getSession().getServletContext().getRealPath("/order.txt");
//        File dir = new File(filePath);
//        if (!dir.exists()) {
//            dir.createNewFile();
//        }
//        try {
//            FileOutputStream fos = new FileOutputStream(filePath);
//            byte[] media = new byte[1024];
//            int length = sis.read(media, 0, 1024);
//            while(length  != -1)
//            {
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
//        try {
//            InputStreamReader read = new InputStreamReader(new FileInputStream(dir), "utf-8");
//            BufferedReader br = new BufferedReader(read);//构造一个BufferedReader类来读取文件
//            String s = null;
//            int num = 0;
//            List<SaOrder> orderList = new ArrayList<>();
//            List<PBoxOrderTemp> orderTempList=new ArrayList<>();
//            List<SaError> errorList=new ArrayList<>();
//            String rom = String.valueOf(System.currentTimeMillis());
//            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
//                if (s.length() > 0) {
//                    String[] split = s.split("\\|");
//                    //跳过标题
//                    if (split.length > 1) {
//                        if (num == 0) {
//                            SaOrder saOrder = new SaOrder();
//                            saOrder.setOrderName(split[0]);
//                            saOrder.setOrderIspack((byte) 1);
//                            saOrder.setOrderPackCount(Integer.valueOf(split[8]));
//                            orderList.add(saOrder);
//                        }
//                        if (num==1){
//                            PBoxOrderTemp orderTemp = new PBoxOrderTemp();
//                            orderTemp.setQrCode(split[0]);
//                            orderTemp.setBoxCode(split[1]);
//                            orderTemp.setHeapCode(split[2]);
//                            orderTemp.setOrderCode(split[3]);
//                            orderTemp.setOrderDetailCode(split[4]);
//                            orderTemp.setProductCode(split[5]);
//                            orderTemp.setProductName(split[6]);
//                            orderTemp.setProductFormat(split[7]);
//                            orderTemp.setProductUnit(split[8]);
//                            orderTemp.setCreateDate(new Date());
//                            orderTemp.setCreateUserName("测试");
//                            orderTemp.setRandom(rom);
//                            orderTempList.add(orderTemp);
//                        }
//                        if (num==2){
//                            SaError saError = new SaError();
//                            saError.setErrorCode(split[0]);
//                            saError.setErrorReason(split[1]);
//                            saError.setErrorDate(split[2]);
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
//            if (orderList.size()>0){
//                SaOrder saOrder = orderList.get(0);
//                PSaOrder pSaOrder = new PSaOrder();
//                pSaOrder.setOrderName(saOrder.getOrderName());
//                PSaOrder order = orderService.selectSaOrderByName(pSaOrder);
//                if (order.getOrderIspack()==1){
//                    json.setMsg("订单已导入,勿重复导入");
//                    json.setSuccess(false);
//                    return json;
//                }
//                Boolean b = asyncTaskService.importOrder(orderList, orderTempList, errorList, rom);
//                if (b){
//                    json.setMsg("导入成功");
//                    json.setSuccess(true);
//                    return json;
//                }else {
//                    json.setMsg("数据有误，导入失败");
//                    json.setSuccess(false);
//                    return json;
//                }
//            }else {
//                json.setMsg("无订单信息,不可导入");
//                json.setSuccess(false);
//                return json;
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            json.setMsg("txt数据解析有误");
//            return json;
//
//        }
//    }
}
