package com.dalian.sea.controller;

import com.dalian.sea.ClientIP;
import com.dalian.sea.config.shiro.ShiroUser;
import com.dalian.sea.json.JqGridParam;
import com.dalian.sea.json.Json;
import com.dalian.sea.model.BaResource;
import com.dalian.sea.model.SysSysLog;
import com.dalian.sea.model.ZsSchedule;
import com.dalian.sea.parameter.*;
import com.dalian.sea.service.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * HomeController2
 *
 * @author xintao
 * @date 2018/1/9
 */
@Controller
@Slf4j
@RequestMapping(value = "/Home")
public class HomeController extends LayoutRazor {

    @Autowired
    private BaResourceService baResourceService;

    @Autowired
    private ZsProduceTaskService zsProduceTaskService;

    @Autowired
    private SaOrderService saOrderService;

    @Autowired
    private TagSweepService tagSweepService;

    @Autowired
    private TagGenerateService tagGenerateService;

    @Autowired
    private PuReceiveDetailService puReceiveDetailService;

    @Autowired
    private ZsCompanyProductService zsCompanyProductService;

    @Autowired
    private ZsScheduleService zsScheduleService;

    @Autowired
    private SysSysLogService sysSysLogService;

    @RequestMapping(value = "/AccordionPage.htm")
    public String AccordionPage(HttpServletRequest request) {
        List<PZsProduceTask> produceTaskList = zsProduceTaskService.getTodayProduceTask();
        if (null != produceTaskList && 0 != produceTaskList.size()) {
            request.setAttribute("produceTaskList", produceTaskList);
        }
        //获取今日订单
        List<PSaOrder> saOrderList = saOrderService.getTodayOrder();
        if (null != saOrderList && 0 != saOrderList.size()) {
            request.setAttribute("saOrderList", saOrderList);
        }
        //今日订单总数量
        Integer count = saOrderService.todayOrderCount();
        request.setAttribute("count", count);
        //今日已完成订单数量
        Integer finishCount = saOrderService.todayFinishedCount();
        request.setAttribute("finishCount", finishCount);
        //总扫码量
        Integer sweepTotal = tagSweepService.getSweepTotal();
        request.setAttribute("sweepTotal", sweepTotal);
        //当月扫码量
        Integer monthSweep = tagSweepService.getMonthSweep();
        request.setAttribute("monthSweep", monthSweep);
        //总生成量
        Integer tagCountTotal = tagGenerateService.getTagCountTotal();
        request.setAttribute("tagCountTotal", tagCountTotal);
        //当月生成量
        Integer monthTagCount = tagGenerateService.getMonthTagCount();
        request.setAttribute("monthTagCount", monthTagCount);
        //扫码异常总量
        Integer sweepExceptionTotal = tagSweepService.sweepExceptionTotal();
        request.setAttribute("sweepExceptionTotal", sweepExceptionTotal);
        //今日扫码异常
        Integer todaySweepException = tagSweepService.todaySweepException();
        request.setAttribute("todaySweepException", todaySweepException);
        //总绑定量
        Integer alreadyCount = tagGenerateService.totalLabelNumber();
        request.setAttribute("alreadyCount", alreadyCount);
        //当月绑定量
        Integer monthLabelNumber = tagGenerateService.monthLabelNumber();
        request.setAttribute("monthLabelNumber", monthLabelNumber);
        //当月生产计划
        List<ZsSchedulePara> zsScheduleParaList = zsScheduleService.getMonthSchedule();
        if (null != zsScheduleParaList && 0 != zsScheduleParaList.size()) {
            request.setAttribute("zsScheduleParaList", zsScheduleParaList);
        }
        return freeMarkerIndexResult("/Home/AccordionPage.ftl", request);
    }

    @RequestMapping(value = "/Shortcuts.htm")
    public String Shortcuts() throws Exception {
        return "/Home/Shortcuts";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/LoadAccordionMenu.json")
    @ResponseBody
    public Object LoadAccordionMenu(HttpServletRequest request) {
        Json json = new Json();
        try {
            Subject user = SecurityUtils.getSubject();
            ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
            List<BaResource> baResourceList = baResourceService.getMenuByUserId(shiroUser.getId());
            json.setObj(baResourceList);
            json.setSuccess(true);
        } catch (Exception e) {
            json.setMsg("数据异常");
            json.setObj(e);
        }
        return json;
    }

    @RequestMapping(value = "/SetModuleId.json")
    @ResponseBody
    public String SetModuleId() {
        return "1";
    }

    /**
     * 离开tab事件
     *
     * @param moduleId
     * @param moduleName
     * @return
     */
    @RequestMapping(value = "/SetLeave.json")
    @ResponseBody
    public String SetLeave(String moduleId, String moduleName) {
        return moduleId;
    }

    /**
     * 跳转当月生产计划modal
     */
    @RequestMapping("/addScheduleModal.htm")
    public String scheduleModal(HttpServletRequest request) {
        //即食海参规格
        List<PZsCompanyProduct> jshsSpecList = zsCompanyProductService.getSpecByProductCategory((long)21, (long) 4);
        if (null != jshsSpecList && 0 != jshsSpecList.size()) {
            request.setAttribute("jshsSpecList", jshsSpecList);
        }
        //即食鲍鱼规格
        List<PZsCompanyProduct> jsbySpecList = zsCompanyProductService.getSpecByProductCategory((long)21, (long)9);
        if (null != jsbySpecList && 0 != jsbySpecList.size()) {
            request.setAttribute("jsbySpecList", jsbySpecList);
        }
        //干海参规格
        List<PZsCompanyProduct> ghsSpecList = zsCompanyProductService.getSpecByProductCategory((long)21, (long)11);
        if (null != ghsSpecList && 0 != ghsSpecList.size()) {
            request.setAttribute("ghsSpecList", ghsSpecList);
        }
        //半干海参规格
        List<PZsCompanyProduct> bghsSpecList = zsCompanyProductService.getSpecByProductCategory((long)21, (long)7);
        if (null != bghsSpecList && 0 != bghsSpecList.size()) {
            request.setAttribute("bghsSpecList", bghsSpecList);
        }
        //冷冻鲍鱼规格
        List<PZsCompanyProduct> ldbySpecList = zsCompanyProductService.getSpecByProductCategory((long)21, (long)28);
        if (null != ldbySpecList && 0 != ldbySpecList.size()) {
            request.setAttribute("ldbySpecList", ldbySpecList);
        }
        //其他货品规格
        List<PZsCompanyProduct> qthpSpecList = zsCompanyProductService.getSpecByProductCategory((long)21, (long)44);
        if (null != qthpSpecList && 0 != qthpSpecList.size()) {
            request.setAttribute("qthpSpecList", qthpSpecList);
        }

        //即食海参单位
        List<PZsCompanyProduct> jshsUnitList = zsCompanyProductService.getUnitByProductCategory((long)21, (long)4);
        if (null != jshsUnitList && 0 != jshsUnitList.size()) {
            request.setAttribute("jshsUnitList", jshsUnitList);
        }
        //即食鲍鱼单位
        List<PZsCompanyProduct> jsbyUnitList = zsCompanyProductService.getUnitByProductCategory((long)21, (long)9);
        if (null != jsbyUnitList && 0 != jsbyUnitList.size()) {
            request.setAttribute("jsbyUnitList", jsbyUnitList);
        }
        //干海参单位
        List<PZsCompanyProduct> ghsUnitList = zsCompanyProductService.getUnitByProductCategory((long)21, (long)11);
        if (null != ghsUnitList && 0 != ghsUnitList.size()) {
            request.setAttribute("ghsUnitList", ghsUnitList);
        }
        //半干海参单位
        List<PZsCompanyProduct> bghsUnitList = zsCompanyProductService.getUnitByProductCategory((long)21, (long)7);
        if (null != bghsUnitList && 0 != bghsUnitList.size()) {
            request.setAttribute("bghsUnitList", bghsUnitList);
        }
        //冷冻鲍鱼单位
        List<PZsCompanyProduct> ldbyUnitList = zsCompanyProductService.getUnitByProductCategory((long) 21, (long) 28);
        if (null != ldbyUnitList && 0 != ldbyUnitList.size()) {
            request.setAttribute("ldbyUnitList", ldbyUnitList);
        }
        //其他货品单位
        List<PZsCompanyProduct> qthpUnitList = zsCompanyProductService.getUnitByProductCategory((long) 21, (long) 44);
        if (null != qthpUnitList && 0 != qthpUnitList.size()) {
            request.setAttribute("qthpUnitList", qthpUnitList);
        }
        return "/Home/addScheduleModal";
    }

    /**
     * 跳转扫码区域详情modal
     */
    @RequestMapping("/sweepAreaDetailModal.htm")
    public String sweepAreaDetailModal() {
        return "/Home/sweepAreaDetailModal";
    }

    /**
     * 扫码量地区分布图
     */
    @RequestMapping("/scanAreaDistribution.json")
    @ResponseBody
    public Json scanAreaDistribution() {
        Json json = new Json();
        List<ScanAreaDistribution> list = tagSweepService.getScanAreaDistribution();
        if (null != list) {
            json.setObj(list);
            json.setSuccess(true);
        } else {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 获取本周扫码量统计图
     */
    @RequestMapping("/getWeekSweep.json")
    @ResponseBody
    public Json getWeekSweep() {
        Json json = new Json();
        List<Integer> list = tagSweepService.weekSweep();
        if (null != list && 0 != list.size()) {
            json.setSuccess(true);
            json.setObj(list);
        } else {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 获取本月扫码量统计图
     */
    @RequestMapping("/getMonthSweep.json")
    @ResponseBody
    public Json getMonthSweep() {
        Json json = new Json();
        List<Integer> list = tagSweepService.monthSweep();
        if (null != list && 0 != list.size()) {
            json.setSuccess(true);
            json.setObj(list);
        } else {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 获取今年扫码量统计图
     */
    @RequestMapping("/getYearSweep.json")
    @ResponseBody
    public Json getYearSweep() {
        Json json = new Json();
        List<Integer> list = tagSweepService.yearSweep();
        if (null != list && 0 != list.size()) {
            json.setSuccess(true);
            json.setObj(list);
        } else {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 获取本周收货统计
     */
    @RequestMapping("/getWeekReceive.json")
    @ResponseBody
    public Json getWeekReceive() {
        Json json = new Json();
        ReceiveEcharts re = puReceiveDetailService.getWeekReceive();
        if (null != re) {
            json.setSuccess(true);
            json.setObj(re);
        } else {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 获取本月收货统计
     */
    @RequestMapping("/getMonthReceive.json")
    @ResponseBody
    public Json getMonthReceive() {
        Json json = new Json();
        ReceiveEcharts re = puReceiveDetailService.getMonthReceive();
        if (null != re) {
            json.setSuccess(true);
            json.setObj(re);
        } else {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 获取年收货统计
     */
    @RequestMapping("/getYearReceive.json")
    @ResponseBody
    public Json getYearReceive() {
        Json json = new Json();
        ReceiveEcharts re = puReceiveDetailService.getYearReceive();
        if (null != re) {
            json.setSuccess(true);
            json.setObj(re);
        } else {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 获取本周 标签生成、绑定图
     */
    @RequestMapping("/getWeekTag.json")
    @ResponseBody
    public Json getWeekTag() {
        Json json = new Json();
        TagEcharts ta = tagGenerateService.weekTag();
        if (null != ta) {
            json.setSuccess(true);
            json.setObj(ta);
        } else {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 获取本月 标签生成、绑定图
     */
    @RequestMapping("/getMonthTag.json")
    @ResponseBody
    public Json getMonthTag() {
        Json json = new Json();
        TagEcharts ta = tagGenerateService.monthTag();
        if (null != ta) {
            json.setSuccess(true);
            json.setObj(ta);
        } else {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 获取本年 标签生成、绑定图
     */
    @RequestMapping("/getYearTag.json")
    @ResponseBody
    public Json getYearTag() {
        Json json = new Json();
        TagEcharts ta = tagGenerateService.yearTag();
        if (null != ta) {
            json.setSuccess(true);
            json.setObj(ta);
        } else {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 获取地区扫码分布详情table
     */
    @RequestMapping("/getSweepGridTable.json")
    @ResponseBody
    public Object getSweepGridTable(Integer page, Integer rows) {
        List<TagSweepPara> list = tagSweepService.getGridTable(page, rows);
        PageInfo<TagSweepPara> pageInfo = new PageInfo<>(list);
        return new JqGridParam(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), list);
    }

    /**
     * 新建生产计划
     */
    @RequestMapping("/addSchedule.json")
    @ResponseBody
    public Json addSchedule(@RequestBody List<ZsSchedule> ZsScheduleList, HttpServletRequest request) {
        Json json = new Json();
        Subject subject = SecurityUtils.getSubject();
        ShiroUser user = (ShiroUser) subject.getPrincipal();
        try {
            Boolean res = zsScheduleService.addSchedule(ZsScheduleList, user.getId(), user.getName());
            if (res) {
                SysSysLog sysLog = new SysSysLog();
                String ip = ClientIP.getClientIP(request);
                sysLog.setIpAddress(ip);
                sysLog.setCName("新建当月生产计划");
                sysLog.setRemark("新建当月生产计划");
                sysLog.setResourceId(Long.valueOf(1));
                sysLog.setCreatedUserId(user.getId());
                sysSysLogService.addSysSysLog(sysLog);
                json.setSuccess(true);
                json.setMsg("新建成功");
            } else {
                json.setSuccess(false);
                json.setMsg("新建失败");
            }
        } catch (Exception e) {
            json.setSuccess(false);
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 修改生产计划
     */
    @RequestMapping("/updateSchedule.json")
    @ResponseBody
    public Json updateSchedule(@RequestBody List<ZsSchedule> zsScheduleList,HttpServletRequest request) {
        Json json = new Json();
        Subject subject = SecurityUtils.getSubject();
        ShiroUser user = (ShiroUser) subject.getPrincipal();
        try {
            Boolean res = zsScheduleService.updateMonthSchedule(zsScheduleList, user.getId());
            if (res) {
                SysSysLog sysLog = new SysSysLog();
                String ip = ClientIP.getClientIP(request);
                sysLog.setIpAddress(ip);
                sysLog.setCName("修改当月生产计划");
                sysLog.setRemark("修改当月生产计划");
                sysLog.setResourceId(Long.valueOf(1));
                sysLog.setCreatedUserId(user.getId());
                sysSysLogService.addSysSysLog(sysLog);
                json.setSuccess(true);
                json.setMsg("修改成功");
            } else {
                json.setSuccess(false);
                json.setMsg("修改失败");
            }
        } catch (Exception e) {
            json.setSuccess(false);
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 当月是否已有生产计划
     *
     * @return
     */
    @RequestMapping("duringMonthSchedule.json")
    @ResponseBody
    public Json duringMonthSchedule() {
        Json json = new Json();
        try {
            Boolean res = zsScheduleService.duringMonthSchedule();
            if (res) {
                json.setSuccess(true);
                json.setMsg("本月已有生产计划");
            } else {
                json.setSuccess(false);
                json.setMsg("本月无生产计划");
            }
        } catch (Exception e) {
            json.setSuccess(false);
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 跳转生产计划Modal
     */
    @RequestMapping("/scheduleHistoryModal.htm")
    public String scheduleHistoryModal() {
        return "Home/ScheduleHistoryModal";
    }

    /**
     * 获取生产计划历史
     */
    @RequestMapping("/getScheduleHistoryModal.json")
    @ResponseBody
    public Object getScheduleHistory(Integer page, Integer rows) {
        List<ZsSchedulePara> list = zsScheduleService.getAllSchedule(page, rows);
        PageInfo<ZsSchedulePara> pageInfo = new PageInfo<>(list);
        return new JqGridParam(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), list);
    }

    /**
     * 跳转修改生产计划modal
     */
    @RequestMapping("/updateScheduleModal.htm")
    public String updateScheduleModal(HttpServletRequest request) {
        //即食海参规格
        List<PZsCompanyProduct> jshsSpecList = zsCompanyProductService.getSpecByProductCategory((long)21, (long) 4);
        if (null != jshsSpecList && 0 != jshsSpecList.size()) {
            request.setAttribute("jshsSpecList", jshsSpecList);
        }
        //即食鲍鱼规格
        List<PZsCompanyProduct> jsbySpecList = zsCompanyProductService.getSpecByProductCategory((long)21, (long)9);
        if (null != jsbySpecList && 0 != jsbySpecList.size()) {
            request.setAttribute("jsbySpecList", jsbySpecList);
        }
        //干海参规格
        List<PZsCompanyProduct> ghsSpecList = zsCompanyProductService.getSpecByProductCategory((long)21, (long)11);
        if (null != ghsSpecList && 0 != ghsSpecList.size()) {
            request.setAttribute("ghsSpecList", ghsSpecList);
        }
        //半干海参规格
        List<PZsCompanyProduct> bghsSpecList = zsCompanyProductService.getSpecByProductCategory((long)21, (long)7);
        if (null != bghsSpecList && 0 != bghsSpecList.size()) {
            request.setAttribute("bghsSpecList", bghsSpecList);
        }
        //冷冻鲍鱼规格
        List<PZsCompanyProduct> ldbySpecList = zsCompanyProductService.getSpecByProductCategory((long)21, (long)28);
        if (null != ldbySpecList && 0 != ldbySpecList.size()) {
            request.setAttribute("ldbySpecList", ldbySpecList);
        }
        //其他货品规格
        List<PZsCompanyProduct> qthpSpecList = zsCompanyProductService.getSpecByProductCategory((long)21, (long)44);
        if (null != qthpSpecList && 0 != qthpSpecList.size()) {
            request.setAttribute("qthpSpecList", qthpSpecList);
        }

        //即食海参单位
        List<PZsCompanyProduct> jshsUnitList = zsCompanyProductService.getUnitByProductCategory((long)21, (long)4);
        if (null != jshsUnitList && 0 != jshsUnitList.size()) {
            request.setAttribute("jshsUnitList", jshsUnitList);
        }
        //即食鲍鱼单位
        List<PZsCompanyProduct> jsbyUnitList = zsCompanyProductService.getUnitByProductCategory((long)21, (long)9);
        if (null != jsbyUnitList && 0 != jsbyUnitList.size()) {
            request.setAttribute("jsbyUnitList", jsbyUnitList);
        }
        //干海参单位
        List<PZsCompanyProduct> ghsUnitList = zsCompanyProductService.getUnitByProductCategory((long)21, (long)11);
        if (null != ghsUnitList && 0 != ghsUnitList.size()) {
            request.setAttribute("ghsUnitList", ghsUnitList);
        }
        //半干海参单位
        List<PZsCompanyProduct> bghsUnitList = zsCompanyProductService.getUnitByProductCategory((long)21, (long)7);
        if (null != bghsUnitList && 0 != bghsUnitList.size()) {
            request.setAttribute("bghsUnitList", bghsUnitList);
        }
        //冷冻鲍鱼单位
        List<PZsCompanyProduct> ldbyUnitList = zsCompanyProductService.getUnitByProductCategory((long) 21, (long) 28);
        if (null != ldbyUnitList && 0 != ldbyUnitList.size()) {
            request.setAttribute("ldbyUnitList", ldbyUnitList);
        }
        //其他货品单位
        List<PZsCompanyProduct> qthpUnitList = zsCompanyProductService.getUnitByProductCategory((long) 21, (long) 44);
        if (null != qthpUnitList && 0 != qthpUnitList.size()) {
            request.setAttribute("qthpUnitList", qthpUnitList);
        }
        return "/Home/updateScheduleModal";
    }

    /**
     * 获取当月生产计划
     */
    @RequestMapping("/getMonthSchedule.json")
    @ResponseBody
    public Json getMonthSchedule() {
        Json json = new Json();
        try {
            List<ZsSchedulePara> list = zsScheduleService.getMonthSchedule();
            if (null != list && 0 != list.size()) {
                json.setSuccess(true);
                json.setObj(list);
            } else {
                json.setMsg("当月暂无生产计划");
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg("服务器异常");
        }
        return json;
    }
}

