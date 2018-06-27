package com.dalian.sea.mapper;

import com.dalian.sea.model.PuReceive;
import com.dalian.sea.model.PuReceiveDetail;
import com.dalian.sea.model.PuReceiveTest;
import com.dalian.sea.parameter.PuEnterStockDetailPara;
import com.dalian.sea.parameter.PuReceiveDetailPa;
import com.dalian.sea.parameter.PuReceiveDetaildPara;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 收货单明细
 *
 * @author 杨文波
 * @date 2018/01/25
 */
public interface PuReceiveDetailMapper extends Mapper<PuReceiveDetail> {

    /**
     * 收货单表格数据
     *
     * @param puReceiveDetaildPara
     * @return
     */
    List<PuReceiveDetaildPara> getReceiveCarryOut(PuReceiveDetaildPara puReceiveDetaildPara);

    /**
     * 根据receiveIdList获取收货单
     *
     * @param idList
     * @return
     */
    List<PuReceiveDetaildPara> getReceiveByReceiveId(List<Long> idList);

    /**
     * 根据id获取
     *
     * @param receiveDetailId
     * @return
     */
    PuReceiveDetail getReceiveDetailById(Long receiveDetailId);

    /**
     * 根据receiveIdDetailList获取收货单
     *
     * @param idList
     * @return
     */
    List<PuReceiveDetaildPara> getReceiveByReceiveDetailId(List<Long> idList);

    /**
     * 根据reveiveDetailId删除收货单明细
     *
     * @param puReceiveDetail
     * @param date
     * @param userId
     * @return
     */
    Integer deleteReceiveDetailById(@Param("pu") PuReceiveDetail puReceiveDetail, @Param("date") Date date, @Param("userId") Long userId);

    /**
     * 查询一个收货单的明细数量
     *
     * @param receiveId
     * @return
     */
    Integer countReceiveDetails(Long receiveId);

    /**
     * 新建收货单
     *
     * @param receiveId
     * @param puReceiveDetail
     * @return
     * */
	Integer insertReceiveDetail(@Param("pu") PuReceiveDetail puReceiveDetail, @Param("receiveId") Long receiveId, @Param("date")Date date,@Param("userId") Long userId);

	/**
     * 新建收货单后要直接打印需要用到收货单明细得id
     * @param receiveId
     * @return
     * */
    List<PuReceiveDetail> getDetailForInsertReceive(Long receiveId);
	/**
     * 报送检验
     * 修改test_result
     *
     * @param receiveDetailId
     * @return
     */
    Integer updateTestResult(Long receiveDetailId);

    /**
     * 修改receiveDetail
     * @param puReceiveDetailPa
     * @return
     */
    Integer updateReceiveDetail(@Param("pu") PuReceiveDetailPa puReceiveDetailPa, @Param("date") Date date, @Param("userId") Long userId);

    /**
     * 修改检验结果
     * @param puReceiveTest
     * @return
     */
    Integer updateResult(@Param("pt") PuReceiveTest puReceiveTest);

    /**
     * 退货修改数量
     * 退货一部分
     * @param puReceiveDetailPa
     * @return
     */
    Integer updateWeight(@Param("pr") PuReceiveDetailPa puReceiveDetailPa,@Param("date") Date date,@Param("userId") Long userId);


    /**
     * 修改复制后的收货单明细
     * @param puReceiveDetailPa
     * @param key 主键
     * @return
     */
    Integer returnDetail(@Param("pr") PuReceiveDetailPa puReceiveDetailPa,@Param("key") Long key);

    /**
     * 根据receiveDetailId
     * 查记录
     * @param receiveDetailId
     * @return
     */
    PuReceiveDetail queryOne(Long receiveDetailId);

    /**
     * 新建退货单
     * @param puReceiveDetail
     * @param date
     * @param userId
     * @return
     */
    Integer newOne(@Param("pu") PuReceiveDetailPa puReceiveDetail,@Param("date") Date date,@Param("userId") Long userId);

    /**
     * 根据id修改
     * */
    Integer updateByReceiveDetailId(PuReceiveDetail puReceiveDetail);

    List<PuReceiveDetail> getDetailByReceiveId(Long receiveId);

    /**
     * 根据单据编号查询所有收货单明细
     * @param receiveNo
     * @return
     */
    List<PuReceiveDetaildPara> getReceiveDetailByReceiveNo(String receiveNo);

    /**
     * 根据批次号查询所有收货单明细
     * @param receiveDetaildPara
     * @return
     */
    List<PuReceiveDetaildPara> getReceiveDetailByBatchNo(PuReceiveDetaildPara receiveDetaildPara);

    /**
     * 根据批次号查询货物类型
     * @param puReceiveDetail
     * @return
     */
    List<PuReceiveDetail> getGoodsTypeByReceiveBatchNo(PuReceiveDetail puReceiveDetail);

    /**
     * 修改打印状态
     *
     * */
    Integer updateIsPrint(@Param("receiveDetailId") List<Long> receiveDetailId);

    /**
     *根据日期查询
     * 海参总重量
     * @param date
     * @return
     */
    BigDecimal getHsWeightByDate(String date);


    /**
     * 根据日期查询
     * 鲍鱼总重量
     * @param date
     * @return
     */
    BigDecimal getByWeightByDate(String date);

    /**
     * 根据日期查询
     * 虾仁总重量
     * @param date
     * @return
     */
    BigDecimal getXrWeightByDate(String date);

    /**
     * 根据日期查询
     * 包材
     * @param date
     * @return
     */
    BigDecimal getBcWeightByDate(String date);

    /**
     * 根据年月
     * 查询海参
     * @param date
     * @return
     */
    BigDecimal getHsWeightByMonth(String date);

    /**
     * 根据年月
     * 查询鲍鱼
     * @param date
     * @return
     */
    BigDecimal getByWeightByMonth(String date);

    /**
     * 根据年月
     * 查询虾仁
     * @param date
     * @return
     */
    BigDecimal getXrWeightByMonth(String date);

    /**
     * 根据年月
     * 查询包材
     * @param date
     * @return
     */
    BigDecimal getBcWeightByMonth(String date);

    /**
     * 修改收货单成已入库状态
     */
    Integer updateReceiptStatus(@Param("receiveIdList")Set<Long> receiveIdList);

    /**
     * 根据单据编号查询和货物状态查询所有收货单明细
     * @param receiveDetaildPara
     * @return
     */
    List<PuReceiveDetaildPara> getAllReceiveByReceiveNo(PuReceiveDetaildPara receiveDetaildPara);
}