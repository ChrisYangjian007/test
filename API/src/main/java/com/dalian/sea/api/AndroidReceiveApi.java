package com.dalian.sea.api;

import com.dalian.sea.json.JqGridParam;
import com.dalian.sea.json.Json;
import com.dalian.sea.model.PuReceive;
import com.dalian.sea.model.PuReceiveDetail;
import com.dalian.sea.model.SysUnit;
import com.dalian.sea.model.ZsEnterprise;
import com.dalian.sea.parameter.*;
import com.dalian.sea.service.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author 杨文波
 * @date 2018/3/7
 */
@RestController
@Slf4j
@RequestMapping("/receive")
public class AndroidReceiveApi {

    @Autowired
    private PuReceiveDetailService puReceiveDetailService;

    @Autowired
    private BaDataDictionaryDetailsService dataDictionaryDetailsService;

    @Autowired
    private ZsEnterpriseService zsEnterpriseService;

    @Autowired
    private PuReceiveService puReceiveService;

    @Autowired
    private ZsCompanyProductService zsCompanyProductService;

    @Autowired
    private PuReceiveTestService puReceiveTestService;

    @Autowired
    private SysUnitService sysUnitService;


    /**
     * 获取收货编号
     */
    @PostMapping("/getReceiveData.json")
    public Object addReceiveModal() {
        Json json = new Json();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
            Date now = new Date();
            StringBuilder sb = new StringBuilder();
            sb.append("NO.SH");
            sb.append(sdf.format(now));
            sb.append(new Random().nextInt(99));
            json.setMsg("查询成功");
            json.setObj(sb.toString());
            json.setSuccess(true);
        } catch (Exception e) {
            json.setMsg("服务器异常!");
        }
        return json;
    }

    @PostMapping(value = "/insertReceive.json")
    public Object insertReceive(@RequestBody PuReceiveApi puReceiveApi) {
        Json json = new Json();
        try {
            PuReceiveApi puReceive = puReceiveService.getReceiveByReceiveNo(puReceiveApi.getReceiveNo());
            if (null != puReceive) {
                json.setMsg("改收货编号的收货单已存在");
            } else {
                boolean res = puReceiveDetailService.insertReceiveForApi(puReceiveApi);
                if (res) {
                    json.setMsg("插入成功");
                } else {
                    json.setMsg("插入失败");
                }
                json.setSuccess(true);
            }
        } catch (Exception e) {
            json.setMsg("服务器异常!");
        }
        return json;
    }

    @PostMapping(value = "/insertReceiveDetail.json")
    public Object insertReceiveDetail(@RequestBody PuReceiveDetail puReceiveDetail) {
        Json json = new Json();
        try {
            boolean res = puReceiveDetailService.insertReceiveDetailForApi(puReceiveDetail);
            if (res) {
                json.setMsg("插入成功");
            } else {
                json.setMsg("插入失败");
            }
            json.setSuccess(true);
        } catch (Exception e) {
            json.setMsg("服务器异常!");
        }
        return json;
    }

    /**
     * 编辑收货单
     * @param isPrint 0:已打印，1:未打印
     */
    @PostMapping("/getUpdateReceiveData.json")
    public Object getUpdateReceiveData(HttpServletRequest request, HttpServletResponse response, String receiveNo, @RequestParam(name = "isPrint",required = true) Integer isPrint) {
        Json json = new Json();
        try {
            if (null!=isPrint){
                PuReceiveApi puReceive = puReceiveService.getReceiveByReceiveNoForIsPrint(receiveNo, isPrint);
                if (null != puReceive) {
                    json.setMsg("查询成功");
                    json.setObj(puReceive);
                } else {
                    json.setMsg("查询暂无");
                }
                json.setSuccess(true);
            }else {
                response.setStatus(400);
            }
        } catch (Exception e) {
            json.setMsg("服务器异常!");
        }
        return json;
    }

    /**
     * 编辑收货单
     */
    @PostMapping("/updateReceiveData.json")
    public Object updateReceiveData(@RequestBody PuReceive puReceive) {
        Json json = new Json();
        try {
            boolean res = puReceiveService.updateReceiveById(puReceive);
            if (res) {
                json.setMsg("修改成功");
            } else {
                json.setMsg("修改失败");
            }
            json.setSuccess(true);
        } catch (Exception e) {
            json.setMsg("服务器异常!");
        }
        return json;
    }

    /**
     * 编辑收货单详情
     */
    @PostMapping("/getUpdateReceiveDetailData.json")
    public Object getUpdateReceiveDetailData(Long receiveDetailId) {
        Json json = new Json();
        try {
            PuReceiveDetail puReceiveDetail = puReceiveDetailService.getByReceiveDetailId(receiveDetailId);
            if (null != puReceiveDetail) {
                json.setMsg("查询成功");
                json.setObj(puReceiveDetail);
            } else {
                json.setMsg("查询暂无");
            }
            json.setSuccess(true);
        } catch (Exception e) {
            json.setMsg("服务器异常!");
        }
        return json;
    }

    /**
     * 编辑收货单详情
     */
    @PostMapping("/updateReceiveDetailData.json")
    public Object updateReceiveDetailData(@RequestBody PuReceiveDetail puReceiveDetail) {
        Json json = new Json();
        try {
            boolean res = puReceiveDetailService.updateByReceiveDetailId(puReceiveDetail);
            if (res) {
                json.setMsg("修改成功");
            } else {
                json.setMsg("修改失败");
            }
            json.setSuccess(true);
        } catch (Exception e) {
            json.setMsg("服务器异常!");
        }
        return json;
    }

    /**
     * 删除收货单详情
     */
    @PostMapping("/deleteReceiveDetailData.json")
    public Object deleteReceiveDetailData(PuReceiveDetail pu) {
        Json json = new Json();
        try {
            PuReceiveDetail puReceiveDetail = puReceiveDetailService.getByReceiveDetailId(pu.getReceiveDetailId());
            if (null != puReceiveDetail) {
                puReceiveDetail.setUpdateDate(new Date());
                puReceiveDetail.setUpdateUserId(pu.getUpdateUserId());
                boolean res = puReceiveDetailService.deleteReceiveDetailAndReceive(puReceiveDetail);
                if (res) {
                    json.setMsg("删除成功");
                } else {
                    json.setMsg("删除失败");
                }
            } else {
                json.setMsg("查询不到该明细");
            }
            json.setSuccess(true);
        } catch (Exception e) {
            json.setMsg("服务器异常!");
        }
        return json;
    }


    /**
     * 获取未打印收货单列表
     *
     * @param isPrint 是否打印了
     * @param page    第几页
     * @param rows    每页容量
     * @return 表格数据
     * @see com.dalian.sea.json.JqGridParam
     */
    @PostMapping("/getAllIsPrintList.json")
    public Object getAllIsPrintList(Byte isPrint, Integer page, Integer rows) {
        Json json = new Json();
        try {
            List<PuReceive> puReceives = puReceiveService.getIsPrintReceiveList(isPrint, page, rows);
            if (null != puReceives && !puReceives.isEmpty()) {
                PageInfo<PuReceive> pageInfo = new PageInfo<>(puReceives);
                json.setMsg("获取成功");
                json.setObj(new JqGridParam(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), puReceives));
            } else {
                json.setMsg("查询暂无");
            }
            json.setSuccess(true);
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * @param puReceive 收货编号
     * @return 表格数据
     * @see com.dalian.sea.json.JqGridParam
     */
    @PostMapping("/getReceiveDetailList.json")
    public Object getReceiveDetailList(PuReceive puReceive) {
        Json json = new Json();
        try {
            PuReceive puReceives = puReceiveService.getReceiveByReceiveNo(puReceive);
            if (null != puReceives) {
                List<PuReceiveDetail> puReceiveDetails = puReceiveDetailService.getDetailByReceiveId(puReceives.getReceiveId());
                if (null != puReceiveDetails && !puReceiveDetails.isEmpty()) {
                    json.setMsg("获取成功");
                    json.setObj(puReceiveDetails);
                } else {
                    json.setMsg("查询暂无");
                }
            } else {
                json.setMsg("查询暂无");
            }
            json.setSuccess(true);
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }
}
