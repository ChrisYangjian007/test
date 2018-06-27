package com.dalian.sea.service;

import com.dalian.sea.model.PuReceive;
import com.dalian.sea.model.PuReceiveDetail;
import com.dalian.sea.parameter.PuReceiveApi;
import com.dalian.sea.parameter.PuReceiveDetailPa;
import com.dalian.sea.parameter.PuReceiveDetaildPara;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author 杨文波
 * @date 2018/1/24
 */
public interface PuReceiveService {
    /**
     * 获取已完成收货单表格数据
     * @param puReceive
     * @param page
     * @param rows
     * @return
     * */
    List<PuReceive> getReceiveCarryOut(PuReceive puReceive,int page,int rows);

    /**
     * 根据receiveId删除收货单
     * @param puReceiveDetails
     * @param userId
     * @return
     */
    Boolean deleteReceiveById(List<PuReceiveDetailPa> puReceiveDetails, Long userId);

    /**
     * 根据
     * @param receive
     * @return
     */
    PuReceive getReceiveByReceiveNo(PuReceive receive);

    /**
     * 根据id
     * @param receiveId
     * @return
     */
    PuReceive getReceiveByReceiveId(Long receiveId);

    /**
     * 根据编号
     * @param receiveNo
     * @return
     */
    PuReceiveApi getReceiveByReceiveNo(String receiveNo);

    /**
     * 根据编号
     * @param receiveNo
     * @return
     */
    PuReceiveApi getReceiveByReceiveNoForIsPrint(String receiveNo,Integer isPrint);

    /**
     * 修改收货单
     * */
    boolean updateReceiveById(PuReceive puReceive);

    /**
     * 获取收货单列表（打印与未打印）
     * */
    List<PuReceive> getIsPrintReceiveList(Byte isPrint,int page,int size);

}
