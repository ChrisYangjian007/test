package com.dalian.sea.mapper;

import com.dalian.sea.model.ZsXqKitchen;
import com.dalian.sea.parameter.PXqKitchen;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ZsXqKitchenMapper extends Mapper<ZsXqKitchen> {
    /***
     * 晓芹厨房
     * @return
     */
    List<PXqKitchen> getXqKitchen();
    /***
     * 根据ID查询
     * @return
     */
    PXqKitchen getXqKitchenById(Long kitchenId);

    /***
     * 新增晓芹厨房
     * @param pXqKitchen
     * @return
     */
    Integer addXqKitchen(PXqKitchen pXqKitchen);
    /***
     * 修改晓芹厨房
     * @param pXqKitchen
     * @return
     */
    Integer updateXqKitchen(PXqKitchen pXqKitchen);
    /***
     * 删除晓芹厨房
     * @param pXqKitchen
     * @return
     */
    Integer delectXqKitchen(PXqKitchen pXqKitchen);
}