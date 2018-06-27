package com.dalian.sea.service;

import com.dalian.sea.json.Json;
import com.dalian.sea.model.PuReceiveDetail;
import com.dalian.sea.parameter.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 收货单详情
 *
 * @author 杨文波
 * @date 2018/1/24
 */
public interface PuReceiveDetailService {

    /**
     * 收货单详情
     */
    List<PuReceiveDetaildPara> getReceiveCarryOut(PuReceiveDetaildPara puReceiveDetail, int page, int rows);

    /**
     * 根据reveiveId批量获取收货单明细
     */
    List<PuReceiveDetaildPara> getReceiveByReceiveId(List<Long> idList);

    /**
     * 根据reveiveDetailId批量获取收货单明细
     */
    List<PuReceiveDetaildPara> getReceiveByReceiveDetailId(List<Long> idList);


    /**
     * 根据receiveDetailId删除收货单明细
     */
    Boolean deleteReceiveDetailById(PuReceiveDetail puReceiveDetail, Long userId);

    /**
     * 删除收货单明细和收货单
     */
    Boolean deleteReceiveDetailAndReceive(PuReceiveDetail puReceiveDetail);

    /**
     * 新建收货单
     */
    Long insertReceiveDetail(PuReceivePara puReceivePara, Long userId);

    /**
     * 新建收货单后要直接打印需要用到收货单明细得id
     */
    String getDetailForInsertReceive(Long receiveId);

    /**
     * api新建收货单
     */
    boolean insertReceiveForApi(PuReceiveApi puReceiveApi);

    /**
     * api新建收货单详情
     */
    boolean insertReceiveDetailForApi(PuReceiveDetail puReceiveDetail);

    /**
     * 数据导出
     *
     * @param puReceiveDetail
     * @param request
     * @param response
     * @param arr
     * @return
     */
    Boolean exportData(PuReceiveDetaildPara puReceiveDetail, HttpServletRequest request, HttpServletResponse response,Integer[] arr);

    /**
     * 批量修改
     * 包括修改receive和receiveDetail
     *
     * @param puReceiveDetailPas
     * @param userId
     * @return
     */
    Boolean editReceive(List<PuReceiveDetailPa> puReceiveDetailPas, Long userId);

    /**
     * 退货
     */
    Json returnGoods(List<PuReceiveDetailPa> puReceiveDetailPas, Long userId);

    /**
     * 根据Id获取
     *
     * @param receiveDetailId
     * @return
     */
    PuReceiveDetail getByReceiveDetailId(Long receiveDetailId);

    /**
     * 根据id修改
     */
    boolean updateByReceiveDetailId(PuReceiveDetail puReceiveDetail);

    List<PuReceiveDetail> getDetailByReceiveId(Long receiveId);

    /**
     * 根据单据编号查询所有收货单明细
     *
     * @param receiveNo
     * @return
     */
    List<PuReceiveDetaildPara> getReceiveDetailByReceiveNo(String receiveNo);

    /**
     * 根据批次号查询所有收货单明细
     *
     * @param receiveDetaildPara
     * @return
     */
    List<PuReceiveDetaildPara> getReceiveDetailByBatchNo(PuReceiveDetaildPara receiveDetaildPara);

    /**
     * 根据批次号查询货物类型
     *
     * @param puReceiveDetail
     * @return
     */
    List<PuReceiveDetail> getGoodsTypeByReceiveBatchNo(PuReceiveDetail puReceiveDetail);

    /**
     * 根据收货单明细id，收货单id获取信息
     *
     * @param receiveDetailId
     * @return
     */
    List<JasperReportData> getPrintReceivePdf(String[] receiveDetailId);

    /**
     * 根据收货单明细id，收货单id获取信息
     *
     * @param receiveDetailId
     * @return
     */
    List<JasperReturnData> getPrintReceiveReturn(String[] receiveDetailId);


    /**
     * 本周收货统计
     *
     * @return
     */
    ReceiveEcharts getWeekReceive();

    /**
     * 本月收货量统计
     * @return
     */
    ReceiveEcharts getMonthReceive();

    /**
     * 年收货量
     * @return
     */
    ReceiveEcharts getYearReceive();

    /**
     * 根据单据编号查询和货物状态查询所有收货单明细
     * @param receiveDetaildPara
     * @return
     */
    List<PuReceiveDetaildPara> getByReceiveNoAndReceiptStatus(PuReceiveDetaildPara receiveDetaildPara);
}
