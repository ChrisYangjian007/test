package com.dalian.sea.service;

import com.dalian.sea.json.Json;
import com.dalian.sea.model.ZsQrCode;
import com.dalian.sea.parameter.PZsQrCode;
import com.dalian.sea.parameter.PZsQrCodeInterval;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 杨文波
 * @date 2018/3/8
 */
public interface ZsQrCodeService {
    /**
     * 通过二维码编号获取二维码数据
     *
     * @param qrCode
     * @return
     */
    ZsQrCode getQrCodeByQrCode(String qrCode);

    /**
     * 判断是否存在和使用
     * @param qrCodes
     * @return
     */
    Json isExistenceAndUse(String [] qrCodes);

    /**
     * 通过produceTaskId获取二维码
     *
     * @param produceTaskId
     * @return
     */
    List<ZsQrCode> getQrCodeByProduceTaskId(Long produceTaskId);


    /**
     * 通过间隔获取二维码
     *
     * @param intervalId
     * @return
     */
    List<PZsQrCode> getQrCodeByIntervalId(Long intervalId, Integer page, Integer rows);

    /**
     * 下载区间内的编码
     *
     * @param request
     * @param response
     * @param qrCodeInterval
     * @return
     */
    Boolean downQRCode(HttpServletRequest request, HttpServletResponse response, PZsQrCodeInterval qrCodeInterval);

    /**
     * 添加编码
     *
     * @param qrCodeInterval
     * @return
     */
    Boolean addQRCode(PZsQrCodeInterval qrCodeInterval);

    /**
     * 修改编码
     *
     * @param qrCode
     * @return
     */
    Boolean updateZsQrCode(ZsQrCode qrCode);

    /**
     * 绑定
     *
     * @param qrCode
     * @return
     */
    Boolean bindCode(ZsQrCode qrCode);

}
