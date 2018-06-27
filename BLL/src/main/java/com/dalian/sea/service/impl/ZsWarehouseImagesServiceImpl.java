package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.ZsWarehouseImagesMapper;
import com.dalian.sea.model.ZsWarehouseImages;
import com.dalian.sea.parameter.PZsWarehouseImages;
import com.dalian.sea.service.ZsWarehouseImagesService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ZsWarehouseImagesServiceImpl
 *
 * @author TONE
 * @date 2018/5/29
 */
@Service
public class ZsWarehouseImagesServiceImpl implements ZsWarehouseImagesService {
    @Autowired
    private ZsWarehouseImagesMapper warehouseImagesMapper;


    /**
     * 添加
     * @param warehouseImages
     * @return
     */
    @Override
    public Boolean addZsWarehouseImages(ZsWarehouseImages warehouseImages) {
        Boolean boo =false;
        Integer integer = warehouseImagesMapper.addZsWarehouseImages(warehouseImages);
        if (0!=integer){
            boo = true;
        }
        return boo;
    }

    /**
     * 修改
     * @param warehouseImages
     * @return
     */
    @Override
    public Boolean updateZsWarehouseImages(ZsWarehouseImages warehouseImages) {
        Boolean boo =false;
        Integer integer = warehouseImagesMapper.updateZsWarehouseImages(warehouseImages);
        if (0!=integer){
            boo = true;
        }
        return boo;
    }

    /**
     * 根据ID获取
     * @param id
     * @return
     */
    @Override
    public PZsWarehouseImages getImagesById(Long id) {
        return warehouseImagesMapper.getImagesById(id);
    }

    /**
     * 根据仓库ID获取
     * @param warehouseId
     * @return
     */
    @Override
    public List<PZsWarehouseImages> getAllImagesByWarehouseId(Long warehouseId) {
        return warehouseImagesMapper.getAllImagesByWarehouseId(warehouseId);
    }

    /**
     * 根据仓库ID获取当月的
     * @param warehouseId
     * @return
     */
    @Override
    public PZsWarehouseImages getAllImagesByWarehouseIdForMonth(Long warehouseId) {
        return warehouseImagesMapper.getAllImagesByWarehouseIdForMonth(warehouseId);
    }

    /**
     * 根据仓库ID和年月获取
     * @param warehouseImages
     * @param page
     * @param rows
     * @return
     */
    @Override
    public List<PZsWarehouseImages> getImagesBy(PZsWarehouseImages warehouseImages, int page, int rows) {
        PageHelper.startPage(page,rows);
        return warehouseImagesMapper.getAllImagesByWarehouseId(warehouseImages.getWarehouseId());
    }
}
