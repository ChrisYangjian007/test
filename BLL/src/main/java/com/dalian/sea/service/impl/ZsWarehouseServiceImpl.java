package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.ZsWarehouseMapper;
import com.dalian.sea.mapper.ZsWarehouseUserMapper;
import com.dalian.sea.model.BaUser;
import com.dalian.sea.model.ZsWarehouse;
import com.dalian.sea.model.ZsWarehouseUser;
import com.dalian.sea.parameter.PZsWarehouse;
import com.dalian.sea.service.ZsWarehouseService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/31.
 */
@Service("ZsWarehouseService")
public class ZsWarehouseServiceImpl implements ZsWarehouseService {

    @Autowired
    private ZsWarehouseMapper zsWarehouseMapper;
    @Autowired
    private ZsWarehouseUserMapper zsWarehouseUserMapper;

    /**
     * 加载到表格
     *
     * @param zsWarehouse
     * @param page
     * @param rows
     * @return
     */
    @Override
    public List<PZsWarehouse> getWareHouseForGrid(PZsWarehouse zsWarehouse, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<PZsWarehouse> zsWarehouseList = zsWarehouseMapper.getWareHouseForGrid(zsWarehouse);
        if (null != zsWarehouseList && 0 != zsWarehouseList.size()) {
            for (PZsWarehouse pZsWarehouse : zsWarehouseList) {
                if (null != pZsWarehouse.getWarehouseUser() && 0 != pZsWarehouse.getWarehouseUser().size()) {
                    String warehouseUserName = "";
                    Integer i = 1;
                    for (BaUser baUser : pZsWarehouse.getWarehouseUser()) {
                        if (i == pZsWarehouse.getWarehouseUser().size()) {
                            warehouseUserName += baUser.getCName();
                        } else {
                            warehouseUserName += baUser.getCName() + ",";
                        }
                    }
                    pZsWarehouse.setWarehouseUserName(warehouseUserName);
                }
            }
        }
        return zsWarehouseList;
    }

    /**
     * 通过名字获取仓库
     *
     * @param zsWarehouse
     * @return
     */
    @Override
    public ZsWarehouse getWarehouseByName(ZsWarehouse zsWarehouse) {
        return zsWarehouseMapper.getWarehouseByName(zsWarehouse);
    }

    /**
     * 添加
     *
     * @param zsWarehouse
     * @return
     */
    @Override
    @Transactional
    public Boolean addWarehouse(ZsWarehouse zsWarehouse, Long[] warehouseUserId) {
        Boolean boo = false;
        try {
            Integer result = zsWarehouseMapper.addWarehouse(zsWarehouse);
            List<ZsWarehouseUser> warehouseUserList = new ArrayList<>();
            for (int i = 0; i < warehouseUserId.length; i++) {
                ZsWarehouseUser zsWarehouseUser = new ZsWarehouseUser();
                zsWarehouseUser.setWarehouseId(zsWarehouse.getWarehouseId());
                zsWarehouseUser.setUserId(warehouseUserId[i]);
                zsWarehouseUser.setCreateUserId(zsWarehouse.getCreateUserId());
                warehouseUserList.add(zsWarehouseUser);
            }
            Integer result2 = zsWarehouseUserMapper.addWarehouseUserList(warehouseUserList);
            if (0 < result && 0 < result2) {
                boo = true;
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return boo;
    }

    /**
     * 获取最大顺序数
     *
     * @return
     */
    @Override
    public ZsWarehouse getWarehouseMaxListIndex() {
        return zsWarehouseMapper.getWarehouseMaxListIndex();
    }

    /**
     * 通过id获取仓库
     *
     * @param id
     * @return
     */
    @Override
    public PZsWarehouse getWarehouseById(Long id) {
        PZsWarehouse pZsWarehouse = zsWarehouseMapper.getWarehouseById(id);
        return pZsWarehouse;
    }

    /**
     * 修改
     *
     * @param zsWarehouse
     * @return
     */
    @Override
    @Transactional
    public Boolean updateZsWarehouseById(ZsWarehouse zsWarehouse, Long[] updWarehouseUserId) {
        Boolean boo = false;
        try {
            Integer result = zsWarehouseMapper.updateZsWarehouseById(zsWarehouse);
            Integer result1 = 0;
            List<ZsWarehouseUser> zsWarehouseUserList = zsWarehouseUserMapper.getWarehouseUserByWarehouseId(zsWarehouse.getWarehouseId());
            if (null != zsWarehouseUserList && 0 != zsWarehouseUserList.size()) {
                result1 = zsWarehouseUserMapper.deleteByWarehouseId(zsWarehouse);
            } else {
                result1 = 1;
            }
            List<ZsWarehouseUser> warehouseUserList = new ArrayList<>();
            for (int i = 0; i < updWarehouseUserId.length; i++) {
                ZsWarehouseUser zsWarehouseUser = new ZsWarehouseUser();
                zsWarehouseUser.setUserId(updWarehouseUserId[i]);
                zsWarehouseUser.setWarehouseId(zsWarehouse.getWarehouseId());
                zsWarehouseUser.setCreateUserId(zsWarehouseUser.getUpdateUserId());
                warehouseUserList.add(zsWarehouseUser);
            }
            Integer result2 = 0;
            if (null != warehouseUserList && 0 != warehouseUserList.size()) {
                result2 = zsWarehouseUserMapper.addWarehouseUserList(warehouseUserList);
            } else {
                result2 = 1;
            }
            if (0 < result && 0 < result1 && 0 < result2) {
                boo = true;
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return boo;
    }

    /**
     * 删除
     *
     * @param zsWarehouse
     * @return
     */
    @Override
    @Transactional
    public Boolean deleteZsWarehouseById(ZsWarehouse zsWarehouse) {
        Boolean boo = false;
        try {

            Integer result = zsWarehouseMapper.deleteZsWarehouseById(zsWarehouse);
            Integer result1 = 0;
            List<ZsWarehouseUser> zsWarehouseUserList = zsWarehouseUserMapper.getWarehouseUserByWarehouseId(zsWarehouse.getWarehouseId());
            if (null != zsWarehouseUserList && 0 != zsWarehouseUserList.size()) {
                result1 = zsWarehouseUserMapper.deleteByWarehouseId(zsWarehouse);
            } else {
                result1 = 1;
            }
            if (0 < result && 0 < result1) {
                boo = true;
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return boo;
    }

    /**
     * 获取仓库信息
     *
     * @return
     */
    @Override
    public List<ZsWarehouse> getAllWarehouse(Long userId, Long companyId) {
        List<ZsWarehouse> list = new ArrayList<>();
        //获取当前用户管理的仓库id
        if (companyId == 0) {
            list = zsWarehouseMapper.getAllWarehouse();
        } else {
            List<Long> ids = zsWarehouseUserMapper.getWarehouseIdByUserId(userId);
            for (Long id : ids) {
                ZsWarehouse zs = zsWarehouseMapper.getWarehouseByWarehouseId(id);
                if (null != zs) {
                    list.add(zs);
                }
            }
        }
        return list;
    }

    @Override
    public List<ZsWarehouse> getWareHouseByUserId(Long userId) {
        return zsWarehouseMapper.getWareHouseByUserId(userId);
    }


}
