package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.ZsExpressMapper;
import com.dalian.sea.model.ZsExpress;
import com.dalian.sea.service.ZsExpressService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ZsExpressServiceImpl
 *
 * @author TONE
 * @date 2018/3/11.
 */
@Service("ZsExpressService")
public class ZsExpressServiceImpl implements ZsExpressService {
    @Autowired
    private ZsExpressMapper expressMapper;



    /**
     * 根据情况获取
     * @param express
     * @return
     */
    @Override
    public List<ZsExpress> getZsExpressByZsExpress(ZsExpress express,int page,int rows) {
        PageHelper.startPage(page,rows);
        return expressMapper.getZsExpressByZsExpress(express);
    }

    /**
     * 根据箱码获取
     * @param express
     * @return
     */
    @Override
    public ZsExpress getZsExpressByBoxCode(ZsExpress express) {
        return expressMapper.getZsExpressByBoxCode(express);
    }

    /**
     * 根据快递单号获取
     * @param express
     * @return
     */
    @Override
    public ZsExpress getZsExpressByExpressCode(ZsExpress express) {
        return expressMapper.getZsExpressByExpressCode(express);
    }

    /**
     * 添加快递单
     * @param express
     * @return
     */
    @Override
    public Long addZsExpress(ZsExpress express) {
        Long id =null;
        Integer integer = expressMapper.addZsExpress(express);
        if (0!=integer){
            id = express.getExpressId();
        }
        return id;
    }

    /**
     * 修改快递单
     * @param express
     * @return
     */
    @Override
    public Boolean updateZsExpress(ZsExpress express) {
        Boolean boo =false;
        Integer integer = expressMapper.updateZsExpress(express);
        if (0!=integer){
            boo = true;
        }
        return boo;
    }

    /**
     * 删除快递单
     * @param express
     * @return
     */
    @Override
    public Boolean deleteZsExpress(ZsExpress express) {
        Boolean boo =false;
        Integer integer = expressMapper.deleteZsExpress(express);
        if (0!=integer){
            boo = true;
        }
        return boo;
    }
}
