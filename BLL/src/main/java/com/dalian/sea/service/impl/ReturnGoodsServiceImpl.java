package com.dalian.sea.service.impl;

import com.dalian.sea.ExcelUtil.ExcelUtil;
import com.dalian.sea.mapper.ReturnGoodsMapper;
import com.dalian.sea.model.ReturnGoods;
import com.dalian.sea.parameter.ReturnGoodsPara;
import com.dalian.sea.service.ReturnGoodsService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author YH
 */
@Service
@Slf4j
public class ReturnGoodsServiceImpl implements ReturnGoodsService {
    @Autowired
    private ReturnGoodsMapper returnGoodsMapper;

    @Override
    public List<ReturnGoodsPara> getAllReturnGoodsDetail(ReturnGoodsPara returnGoodsPara, int page, int rows) {
        List<ReturnGoodsPara> returnGoodsParaList = null;
        try {
            PageHelper.startPage(page, rows);
            returnGoodsParaList = returnGoodsMapper.getReturnGoodsDetails(returnGoodsPara);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return returnGoodsParaList;
    }

    @Override
    public List<ReturnGoodsPara> getIsMaterial() {
        return returnGoodsMapper.getIsMaterial();
    }

    @Override
    public Boolean newReturnGoods(ReturnGoods returnGoods, Long userId) {
        Boolean res = false;
        try {
            Integer a = returnGoodsMapper.newReturnGoods(returnGoods, userId);
            if (a > 0) {
                res = true;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return res;
    }

    @Override
    public Boolean exportReturnGoods(Long returnGoodsDetailId, HttpServletRequest request, HttpServletResponse response) {
        Boolean res = false;
        ReturnGoods re = returnGoodsMapper.selectById(returnGoodsDetailId);
        if (null != re) {
            try {
                ExcelUtil.returnGoodsExport(re, request, response);
                res = true;
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        return res;
    }

    @Override
    public List<ReturnGoods> getReturnGoodsProduct(Long isMaterial) {
        return returnGoodsMapper.getReturnGoodsProduct(isMaterial);
    }

    @Override
    public List<ReturnGoods> getProductSpec(ReturnGoodsPara returnGoodsPara) {
        return returnGoodsMapper.getProductSpec(returnGoodsPara);
    }

    @Override
    public ReturnGoods selectById(Long returnGoodsDetailId) {
        return returnGoodsMapper.selectById(returnGoodsDetailId);
    }
}
