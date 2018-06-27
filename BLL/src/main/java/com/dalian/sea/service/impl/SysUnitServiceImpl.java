package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.SysUnitMapper;
import com.dalian.sea.model.SysUnit;
import com.dalian.sea.service.SysUnitService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SysUnitServiceImp
 *
 * @author xintao
 * @date 2018/1/17
 */
@Service("SysUnitService")
public class SysUnitServiceImpl implements SysUnitService {
    @Autowired
    private SysUnitMapper sysUnitMapper;


    /**
     * 获取所有
     * @return
     */
    @Override
    public List<SysUnit> getAllSysUnit() {
        return sysUnitMapper.getAllSysUnit();
    }

    /**
     * 获取所有
     * @param aLevel
     * @return
     */
    @Override
    public List<SysUnit> getSysUnitByALevel(Integer aLevel) {
        return sysUnitMapper.getSysUnitByALevel(aLevel);
    }

    /**
     * 获取所有
     * @return
     */
    @Override
    public List<SysUnit> getSysUnitNoZero() {
        return sysUnitMapper.getSysUnitNoZero();
    }
    /**
     * 获取所有
     * @return
     */
    @Override
    public List<SysUnit> getSysUnitNoZeroForApi(int page,int rows) {
        PageHelper.startPage(page,rows);
        return sysUnitMapper.getSysUnitNoZero();
    }

    /**
     * 获取单位到表格
     * @param id
     * @param page
     * @param rows
     * @return
     */
    @Override
    public List<SysUnit> getSysUnitForGridTable(Long id, Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        return sysUnitMapper.getSysUnitByParentId(id);
    }

    /**
     *通过parentId和cname获取单位
     * @param sysUnit
     * @return
     */
    @Override
    public SysUnit getSysUnitByParentIdAndName(SysUnit sysUnit) {
        return sysUnitMapper.getSysUnitByParentIdAndName(sysUnit);
    }

    /**
     * 添加单位
     * @param sysUnit
     * @return
     */
    @Override
    public Boolean addSysUnit(SysUnit sysUnit) {
        Boolean boo = false;
        Integer result = sysUnitMapper.addSysUnit(sysUnit);
        if(0<result){
            boo = true ;
        }
        return boo;
    }

    /**
     * 修改单位
     * @param sysUnit
     * @return
     */
    @Override
    public Boolean updateSysUnitById(SysUnit sysUnit) {
        Boolean boo = false ;
        Integer result = sysUnitMapper.updateSysUnitById(sysUnit);
        if(0<result){
            boo = true ;
        }
        return boo;
    }

    /**
     * 通过id获取单位
     * @param id
     * @return
     */
    @Override
    public SysUnit getSysUnitById(Long id) {
        return sysUnitMapper.getSysUnitById(id);
    }

    /**
     * 获取最大顺序数
     * @return
     */
    @Override
    public SysUnit getSysUnitMaxListIndex() {
        return sysUnitMapper.getSysUnitMaxListIndex();
    }

    /**
     * 通过cName获取最大层级
     * @param name
     * @return
     */
    @Override
    public SysUnit getMaxAlevelByName(String name) {
        return sysUnitMapper.getMaxAlevelByName(name);
    }
}
