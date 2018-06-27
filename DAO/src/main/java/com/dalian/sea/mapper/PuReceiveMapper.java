package com.dalian.sea.mapper;

import com.dalian.sea.model.PuReceive;
import com.dalian.sea.model.PuReceiveDetail;
import com.dalian.sea.parameter.PuReceiveApi;
import com.dalian.sea.parameter.PuReceiveDetailPa;
import com.dalian.sea.parameter.PuReceiveDetaildPara;
import com.dalian.sea.parameter.PuReceivePara;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

/**
 * 收货单
 * */
public interface PuReceiveMapper extends Mapper<PuReceive> {

    /**
     * 获取已完成收货单表格数据
     * @param puReceive
     * @return
     * */
    List<PuReceive> getReceiveCarryOut(@Param("puReceive") PuReceive puReceive);

    /**
     * 根据reveive id删除收货单
     * @param receiveId
     * @param userId
     * @param date
     * @return
     */
    Integer deleteReceiveById(@Param("receiveId") Long receiveId, @Param("userId") Long userId, @Param("date")Date date);

    /**
     * 新建收货单
     * @param puReceiveApi
     * @return
     * */
    Integer insertReceive(@Param("pu") PuReceivePara puReceiveApi, @Param("userId") Long userId, @Param("date")Date date);

    /**
     * 新建收货单
     * @param puReceiveApi
     * @return
     * */
    Integer insertReceiveApi(@Param("pu") PuReceiveApi puReceiveApi, @Param("userId") Long userId, @Param("date")Date date);

    /**
     * 修改receive
     * @param puReceiveDetailPa
     * @return
     */
    Integer updateReceive(@Param("pu") PuReceiveDetailPa puReceiveDetailPa, @Param("userId") Long userId, @Param("date")Date date);

    /**
     * 修改receive
     * @param puReceive
     * @return
     */
    Integer updateReceiveById(PuReceive puReceive);


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
     * 根据id
     * @param receiveNo
     * @return
     */
    PuReceiveApi getReceiveByReceiveNoForApi(String receiveNo);

    /**
     * 根据id
     * @param receiveNo
     * @return
     */
    PuReceiveApi getReceiveByReceiveNoForIsPrint(@Param("receiveNo") String receiveNo,@Param("isPrint") Integer isPrint);

	/**
     *根据收货单id查找收货单
     * @param puReceiveDetailPa
     * @return
     */
    PuReceive queryReceive(@Param("pu") PuReceiveDetailPa puReceiveDetailPa);

    /**
     * 新建一条
     */
    Integer copyOne(@Param("pu") PuReceive puReceive,@Param("userId") Long userId, @Param("date")Date date);

    /**
     * 获取收货单列表（打印与未打印）
     * */
    List<PuReceive> getIsPrintReceiveList(@Param("isPrint") Byte isPrint);

}