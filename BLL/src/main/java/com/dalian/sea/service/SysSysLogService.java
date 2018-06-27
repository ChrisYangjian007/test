package com.dalian.sea.service;

import com.dalian.sea.model.SysSysLog;
import com.dalian.sea.parameter.PBaUser;
import com.dalian.sea.parameter.PSysLogSys;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author YH
 */
public interface SysSysLogService {

    /**
     * 查询所有
     */
    List<SysSysLog> getAllSysSysLog();

    /**
     * 添加日志
     */
    Boolean addSysSysLog(SysSysLog sysSysLog);

    /**
     * 修改日志
     */
    Boolean updateSysSysLog(SysSysLog sysSysLog);

    /**
     * 删除日志
     */
    Boolean deleteSysSysLog(Long sysLogId);

    /**
     * 分页
     * @param pSysLogSys
     * @param page
     * @param rows
     * @return
     */
    List<PSysLogSys> getSysSysLogByJqGrid(PSysLogSys pSysLogSys,Long userId, int page, int rows);
}
