package com.dalian.sea.api;

import com.dalian.sea.json.JqGridParam;
import com.dalian.sea.json.Json;
import com.dalian.sea.model.ZsProduceTask;
import com.dalian.sea.model.ZsQrCode;
import com.dalian.sea.model.ZsQrCodeUse;
import com.dalian.sea.model.ZsTaskQr;
import com.dalian.sea.parameter.PZsProduceTask;
import com.dalian.sea.parameter.PZsQrCode;
import com.dalian.sea.service.ZsProduceTaskService;
import com.dalian.sea.service.ZsQrCodeService;
import com.dalian.sea.service.ZsTaskQrService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 *
 * @author 杨文波
 * @date 2018/3/8
 */
@Slf4j
@RestController
@RequestMapping("/produce")
public class AndroidProduceTaskApi {

    @Autowired
    private ZsProduceTaskService produceTaskService;

    @Autowired
    private ZsQrCodeService zsQrCodeService;

    @Autowired
    private ZsTaskQrService zsTaskQrService;

    @PostMapping("/getProduceTask.json")
    public Object getProduceTask(Integer type,Long userId,Integer page,Integer rows){
        Json json = new Json();
        try {
            List<PZsProduceTask> taskList = produceTaskService.getPZsProduceTaskByColTypeForPDA(type,userId,page,rows);
            if (null!=taskList&&taskList.size()>0){
                PageInfo<PZsProduceTask> pageInfo = new PageInfo<>(taskList);
                json.setObj(new JqGridParam(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getPages(),taskList));
                json.setMsg("查询成功");
            }else {
                json.setMsg("查询暂无");
            }
            json.setSuccess(true);
        }catch (Exception e){
            json.setMsg("服务器异常!");
        }
        return json;
    }

    @PostMapping("/getProduceTaskByCode.json")
    public Object getProduceTaskByCode(Integer type,Long userId,String code){
        Json json = new Json();
        try {
            ZsQrCode qrCode = zsQrCodeService.getQrCodeByQrCode(code);
            if (null!=qrCode) {
                if (2==qrCode.getUseStatus()) {
                    if (null!=qrCode.getCurrentProduceTaskCode()&&!"".equals(qrCode.getCurrentProduceTaskCode())) {
                        json = produceTaskService.getPZsProduceTaskByColTypeForPDAByCode(qrCode.getCurrentProduceTaskCode(), type, userId);
                        if (json.isSuccess()) {
                            json.setMsg("查询成功");
                        }
                    }else {
                        json.setMsg("当前二维码数据有误，请联系管理员");
                    }
                }else {
                    json.setMsg("当前二维码未绑定");
                }
            }else {
                json.setMsg("当前二维码不存在");
            }
            json.setSuccess(true);
        }catch (Exception e){
            json.setMsg("服务器异常!");
        }
        return json;
    }

    @PostMapping("/isCanUse.json")
    public Object isCanUse(String code,Integer type){
        Json json = new Json();
        try {
            if (null!=type&&null!=code&&!"".equals(code)) {
                ZsQrCode zsQrCode = zsQrCodeService.getQrCodeByQrCode(code);
                if (null != zsQrCode) {
                    if (1 == type) {
                        if (2 == zsQrCode.getUseStatus()&&null!=zsQrCode.getCurrentProduceTaskCode()&&!"".equals(zsQrCode.getCurrentProduceTaskCode())) {
                            json.setObj(zsQrCode.getCurrentProduceTaskCode());
                        } else {
                            json.setMsg("当前二维码未绑定！");
                        }
                    } else if (2 == type) {
                        if (1 == zsQrCode.getUseStatus()) {
                            json.setObj(zsQrCode.getQrCode());
                        } else {
                            json.setMsg("当前二维码已绑定！");
                        }
                    } else {
                    }
                } else {
                    json.setMsg("无当前二维码编码数据！");
                }
            } else {
                json.setMsg("请求数据有误！");
            }
            json.setSuccess(true);
        }catch (Exception e){
            json.setMsg("服务器异常!");
        }
        return json;
    }

    @PostMapping("/bindCode.json")
    public Object bindCode(PZsQrCode qrCode){
        Json json = new Json();
        try {
            if (null!=qrCode.getCurrentProduceTaskCode()&&!"".equals(qrCode.getCurrentProduceTaskCode())){
                ZsProduceTask produceTask = produceTaskService.getProduceTaskByNo(qrCode.getCurrentProduceTaskCode());
                if (null!=produceTask) {
                    if (1==produceTask.getProduceTaskStatus()) {
                        if (null != qrCode.getQrCode() && !"".equals(qrCode.getQrCode())) {
                            ZsQrCode zsQrCode = zsQrCodeService.getQrCodeByQrCode(qrCode.getQrCode());
                            if (null != zsQrCode) {
                                if (2 != zsQrCode.getUseStatus()) {
                                    qrCode.setQrCodeId(zsQrCode.getQrCodeId());
                                    qrCode.setCurrentProduceTaskId(produceTask.getProduceTaskId());
                                    qrCode.setCurrentProduceTaskName(produceTask.getProduceTaskName());
                                    qrCode.setBindDate(new Date());
                                    qrCode.setUseStatus((byte) 2);
                                    qrCode.setUseNumber(zsQrCode.getUseNumber() + 1);
                                    qrCode.setUpdateUserId(zsQrCode.getBindUserId());
                                    Boolean boo = zsQrCodeService.bindCode(qrCode);
                                    if (boo) {
                                        json.setMsg("绑定成功");
                                        json.setObj(true);
                                    } else {
                                        json.setMsg("绑定失败");
                                        json.setObj(false);
                                    }
                                } else {
                                    json.setMsg("当前二维码编码已绑定生产任务！");
                                    json.setObj(false);
                                }
                            } else {
                                json.setMsg("无当前二维码编码！");
                                json.setObj(false);
                            }
                        } else {
                            json.setMsg("无二维码编码！");
                            json.setObj(false);
                        }
                    }else {
                        json.setMsg("当前生产任务已结束！");
                        json.setObj(false);
                    }
                }else {
                    json.setMsg("无当前生产任务编码！");
                    json.setObj(false);
                }
            }else {
                json.setMsg("无生产任务编码！");
                json.setObj(false);
            }
            json.setSuccess(true);
        }catch (Exception e){
            json.setMsg("服务器异常!");
        }
        return json;
    }




}
