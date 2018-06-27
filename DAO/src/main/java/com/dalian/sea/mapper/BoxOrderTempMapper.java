package com.dalian.sea.mapper;

import com.dalian.sea.model.BoxOrder;
import com.dalian.sea.model.BoxOrderTemp;
import com.dalian.sea.model.SaOrderDetail;
import com.dalian.sea.parameter.PBoxOrderTemp;
import com.dalian.sea.parameter.POrderDetail;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BoxOrderTempMapper extends Mapper<BoxOrderTemp> {
    /**插入
     * @param temp
     * @return
     */
  Integer insertBoxOrderSelective(BoxOrderTemp temp);

    /**批量插入
     * @param list
     * @return
     */
  Integer insertBoxOrderTempByBatch(List<PBoxOrderTemp> list);


    /** 根据订单明细分组查询
     * @param temp
     * @return
     */
  List<POrderDetail>   selectListBoxOrderTempByGroupDetail(PBoxOrderTemp temp);

  /**根据箱码分组
   * @param temp
   * @return
   */
  List<BoxOrder>   selectListBoxOrderTempByGroupBox(PBoxOrderTemp temp);

  /**
   * 根据二维码编码获取
   * @param orderTemp
   * @return
   */
  PBoxOrderTemp getBoxOrderTempByQrCodeOrOrderCodeForPDA(PBoxOrderTemp orderTemp);

  /**查询未被打包的订单信息
   * @return
   */
  List<BoxOrderTemp> selectBoxOrderTempListByNotPack();

  /**批量更新已打包
   * @param ids
   */
  void  updateListBoxOrderTem(@Param("ids")List<Long> ids);
}