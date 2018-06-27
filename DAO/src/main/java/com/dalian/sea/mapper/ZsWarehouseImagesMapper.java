package com.dalian.sea.mapper;

import com.dalian.sea.model.ZsWarehouseImages;
import com.dalian.sea.parameter.PZsWarehouseImages;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

public interface ZsWarehouseImagesMapper extends Mapper<ZsWarehouseImages> {

    /**
     * 添加
     * @param warehouseImages
     * @return
     */
    Integer addZsWarehouseImages(ZsWarehouseImages warehouseImages);


    /**
     * 修改
     * @param warehouseImages
     * @return
     */
    Integer updateZsWarehouseImages(ZsWarehouseImages warehouseImages);


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
     * @param warehouseId
     * @param uploadMonth
     * @return
     */
    List<PZsWarehouseImages> getImagesByWarehouseIdAndUploadMonth(@Param("warehouseId") Long warehouseId,@Param("uploadMonth") Date uploadMonth);

}