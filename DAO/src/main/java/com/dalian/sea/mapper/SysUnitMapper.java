package com.dalian.sea.mapper;

import com.dalian.sea.model.SysUnit;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysUnitMapper extends Mapper<SysUnit> {

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
     * 获取单位通过parentId
     * @return
     */
    List<SysUnit> getSysUnitByParentId(Long id);

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
    Integer addSysUnit(SysUnit sysUnit);

    /**
     * 修改单位
     * @param sysUnit
     * @return
     */
    Integer updateSysUnit(SysUnit sysUnit);

    /**
     * 修改单位
     * @param sysUnit
     * @return
     */
    Integer updateSysUnitById(SysUnit sysUnit);

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