package com.dalian.sea.mapper;

import com.dalian.sea.model.ZsQrCode;
import com.dalian.sea.parameter.PZsQrCode;
import com.dalian.sea.parameter.PZsQrCodeInterval;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ZsQrCodeMapper extends Mapper<ZsQrCode> {
    /**
     * 通过二维码编号获取二维码数据
     * @param qrCode
     * @return
     * */
    ZsQrCode getQrCodeByQrCode(String qrCode);

    /**
     *通过produceTaskId获取二维码
     * @param produceTaskId
     * @return
     */
    List<ZsQrCode> getQrCodeByProduceTaskId(Long produceTaskId);


    /**
     * 通过间隔获取二维码
     * @param intervalId
     * @return
     */
    List<PZsQrCode> getQrCodeByIntervalId(@Param("intervalId") Long intervalId);

    /**
     * 添加多个编码
     * @return
     */
    Integer addQrCodeByStartCodeAndEndCode(@Param("zsQrCodeList") List<PZsQrCode> zsQrCodeList);

    /**
     * 修改编码
     * @param qrCode
     * @return
     */
    Integer updateZsQrCode(ZsQrCode qrCode);

    /**
     * 解绑
     * @param produceTaskId
     * @return
     */
    Integer unBindCode(@Param("produceTaskId") Long produceTaskId,@Param("userId") Long userId);

}