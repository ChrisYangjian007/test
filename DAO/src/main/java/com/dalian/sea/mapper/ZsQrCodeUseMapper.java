package com.dalian.sea.mapper;

import com.dalian.sea.model.ZsQrCodeUse;
import com.dalian.sea.parameter.PZsQrCodeUse;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ZsQrCodeUseMapper extends Mapper<ZsQrCodeUse> {

    /**
     * 根据二维码ID获取使用情况
     * @param qrCodeId
     * @return
     */
    List<PZsQrCodeUse> getQrCodeUseByQrCodeId(Long qrCodeId);

    /**
     * 添加
     * @param qrCodeUse
     * @return
     */
    Integer addQrCodeUse(ZsQrCodeUse qrCodeUse);

    /**
     * 修改
     * @param qrCodeUse
     * @return
     */
    Integer updateQrCodeUse(ZsQrCodeUse qrCodeUse);

    /**
     * 修改
     * @param produceTaskId
     * @param userId
     * @param userName
     * @return
     */
    Integer unBindCode(@Param("produceTaskId") Long produceTaskId,@Param("userId") Long userId,@Param("userName") String userName);

    /**
     * 根据生产任务id获取
     * @param produceTaskId
     * @return
     */
    List<PZsQrCodeUse> getQrCodeUseByProDuceTaskId(Long produceTaskId);
}