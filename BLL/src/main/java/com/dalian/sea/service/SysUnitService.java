package com.dalian.sea.service;

import com.dalian.sea.model.SysUnit;

import java.util.List;

/**
 * SysUnitService
 *
 * @author xintao
 * @date 2018/1/17
 */
public interface SysUnitService {

    /**
     * 获取所有
     * @return
     */
    List<SysUnit> getAllSysUnit();

    /**
     * 获取所有
     * @param aLevel
     * @return
     */
    List<SysUnit> getSysUnitByALevel(Integer aLevel);

    /**
     * 获取所有
     * @return
     */
    List<SysUnit> getSysUnitNoZero();

    /**
     * 获取所有
     * @return
     */
    List<SysUnit> getSysUnitNoZeroForApi(int page,int rows);

    /**
     * 获取单位到表格
     * @param id
     * @param page
     * @param rows
     * @return
     */
    List<SysUnit> getSysUnitForGridTable(Long id,Integer page,Integer rows);

    /**
     * 通过parentId和cname获取单位
     * @param sysUnit
     * @return
     */
    SysUnit getSysUnitByParentIdAndName(SysUnit sysUnit);

    /**
     * 添加单位
     * @param sysUnit
     * @return
     */
    Boolean addSysUnit(SysUnit sysUnit);

    /**
     * 修改单位
     * @param sysUnit
     * @return
     */
    Boolean updateSysUnitById(SysUnit sysUnit);

    /**
     * 通过id获取单位
     * @param id
     * @return
     */
    SysUnit getSysUnitById(Long id);

    /**
     * 获取最大顺序数
     * @return
     */
    SysUnit getSysUnitMaxListIndex();

    /**
     * 通过cName获取最大层级
     * @return
     */
    SysUnit getMaxAlevelByName(String name);
}
