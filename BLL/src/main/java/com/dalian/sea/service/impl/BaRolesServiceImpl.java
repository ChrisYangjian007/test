package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.BaRolesMapper;
import com.dalian.sea.model.BaRoles;
import com.dalian.sea.parameter.PBaRoles;
import com.dalian.sea.service.BaRolesService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * BaRolesServiceImpl
 *
 * @author xintao
 * @date 2018/1/15
 */
@Service("BaRolesService")
public class BaRolesServiceImpl implements BaRolesService{
    @Autowired
    private BaRolesMapper baRolesMapper;

    /**
     * 根据公司ID获取角色
     * @param companyId
     * @return
     */
    @Override
    public List<PBaRoles> getBaRolesByCompanyId(Long companyId,Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        return baRolesMapper.getBaRolesByCompanyId(companyId);
    }

    /**
     * 获取角色和公司名称
     * @return
     */
    @Override
    public List<PBaRoles> getBaRolesAndCompanyName() {
        return baRolesMapper.getBaRolesAndCompanyName();
    }

    /**
     * 根据ID获取角色
     * @param id
     * @return
     */
    @Override
    public BaRoles getBaRolesById(Long id) {
        return baRolesMapper.getBaRolesById(id);
    }
    /**
     * 获取最大顺序数
     * @return
     */
    @Override
    public BaRoles getBaRoleByMaxListIndex() {
        return baRolesMapper.getBaRoleByMaxListIndex();
    }

    @Override
    public Boolean updateUserRoles(Long roleId, String[] id) {
        return null;
    }

    /**
     * 添加角色
     * @param baRoles
     * @return
     */
    @Override
    public Boolean addBaRoles(BaRoles baRoles) {
        Boolean boo = false;
        Integer result = baRolesMapper.addBaRoles(baRoles);
        if(0<result){
                boo = true ;
        }
        return boo;
    }

    /**
     * 通过名字和公司id获取角色
     * @return
     */
    @Override
    public BaRoles getBaRolesByNameAndCompanyId(BaRoles baRoles) {

        return baRolesMapper.getBaRolesByNameAndCompanyId(baRoles);
    }

    /**
     * 通过id修改角色部分信息
     * @param baRoles
     * @return
     */
    @Override
    public Boolean updateBaRolesById(BaRoles baRoles) {
        Boolean boo = false;
        Integer result = baRolesMapper.updateRolesById(baRoles);
        if(0<result){
            boo = true;
        }
        return boo;
    }

    /**
     * 删除角色
     * @param baRoles
     * @return
     */
    @Override
    public Boolean deleteBaRolesById(BaRoles baRoles) {
        Boolean boo =false;
        Integer result = baRolesMapper.deleteBaRolesById(baRoles);
        if(0<result){
            boo = true;
        }
        return boo;
    }

    /**
     * 获取全部角色信息
     * @return
     */
    @Override
    public List<BaRoles> getAllBaRoles() {
        return baRolesMapper.getAllBaRoles();
    }

    /**
     * 通过编码获取角色
     * @param code
     * @return
     */
    @Override
    public BaRoles getRolesByCode(String code) {
        return baRolesMapper.getRolesByCode(code);
    }
}
