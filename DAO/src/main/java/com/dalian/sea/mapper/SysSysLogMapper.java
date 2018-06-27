package com.dalian.sea.mapper;

import com.dalian.sea.model.SysSysLog;
import com.dalian.sea.parameter.PSysLogSys;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author YH
 */
public interface SysSysLogMapper extends Mapper<SysSysLog> {

    /**
     * 查询所有
     */
    List<SysSysLog> getAllSysSysLog();

    /**
     * 点击查询获取日志
     * @param sysSysLog
     * @return
     */
    List<SysSysLog> getSysSysLogs(SysSysLog sysSysLog);
    /**
     * 添加日志
     */
    Integer addSysSysLog(SysSysLog sysSysLog);

    /**
     * 修改日志
     */
    Integer updateSysSysLog(SysSysLog sysSysLog);

    /**
     * 删除日志
     */
    Integer deleteSysSysLog(Long sysLogId);

    /**
     * 分页
     */
     List<PSysLogSys> getSysSysLogByJqGrid(@Param("pSysLogSys") PSysLogSys pSysLogSys,@Param("userId")Long userId);
}