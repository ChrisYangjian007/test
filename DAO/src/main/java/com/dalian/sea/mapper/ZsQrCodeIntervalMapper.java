package com.dalian.sea.mapper;

import com.dalian.sea.model.ZsQrCode;
import com.dalian.sea.model.ZsQrCodeInterval;
import com.dalian.sea.parameter.PZsQrCode;
import com.dalian.sea.parameter.PZsQrCodeInterval;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ZsQrCodeIntervalMapper extends Mapper<ZsQrCodeInterval> {

    /**
     * 根据情况获取二维码编码间隔
     * @param qrCode
     * @return
     */
    List<PZsQrCodeInterval> getQrCodeIntervalByQrCode(PZsQrCode qrCode);

    /**
     * 根据ID获取二维码编码间隔
     * @param intervalId
     * @return
     */
    PZsQrCodeInterval getQrCodeIntervalByIntervalId(Long intervalId);

    /**
     * 获取最大编码二维码
     * @return
     */
    PZsQrCodeInterval getQrCodeIntervalForNew();

    /**
     * 添加
     * @param qrCodeInterval
     * @return
     */
    Integer addQrCodeInterval(PZsQrCodeInterval qrCodeInterval);

    /**
     * 修改下载状态
     * @param qrCodeInterval
     * @return
     */
    Integer updateQrCodeIntervalByIntervalIdForDownStatus(PZsQrCodeInterval qrCodeInterval);

    /**
     * 删除
     * @param qrCodeInterval
     * @return
     */
    Integer deleteQrCodeIntervalBy(PZsQrCodeInterval qrCodeInterval);



}