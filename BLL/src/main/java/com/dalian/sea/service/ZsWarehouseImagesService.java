package com.dalian.sea.service;

import com.dalian.sea.model.ZsWarehouseImages;
import com.dalian.sea.parameter.PZsWarehouseImages;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * ZsWarehouseImagesService
 *
 * @author TONE
 * @date 2018/5/29
 */
public interface ZsWarehouseImagesService {

    /**
     * 添加
     * @param warehouseImages
     * @return
     */
    Boolean addZsWarehouseImages(ZsWarehouseImages warehouseImages);


    /**
     * 修改
     * @param warehouseImages
     * @return
     */
    Boolean updateZsWarehouseImages(ZsWarehouseImages warehouseImages);

    /**
     * 根据ID获取
     * @param id
     * @return
     */
    PZsWarehouseImages getImagesById(Long id);

    /**
     * 根据仓库ID获取
     * @param warehouseId
     * @return
     */
    List<PZsWarehouseImages> getAllImagesByWarehouseId(@Param("warehouseId") Long warehouseId);

    /**
     * 根据仓库ID获取当月的
     * @param warehouseId
     * @return
     */
    PZsWarehouseImages getAllImagesByWarehouseIdForMonth(@Param("warehouseId") Long warehouseId);

    /**
     * 根据仓库ID和年月获取
     * @param warehouseImages
     * @param page
     * @param rows
     * @return
     */
    List<PZsWarehouseImages> getImagesBy(PZsWarehouseImages warehouseImages,int page,int rows);




}
