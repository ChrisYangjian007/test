package com.dalian.sea.mapper;

import com.dalian.sea.model.PuReceiveDetail;
import com.dalian.sea.model.PuReceiveTest;
import com.dalian.sea.parameter.PPuReceiveTestMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PuReceiveTestMapper extends Mapper<PuReceiveTest> {

    /**
     * 显示所有数据
     * @return
     */
    public List<PPuReceiveTestMapper> puReceiveTestList(PPuReceiveTestMapper puReceiveTest);

    /**
     * 根据id查询货品
     * @param id
     * @return
     */
    public PuReceiveTest uploadingReports(Long id);

    /**
     * 显示检验方式
     * @return
     */
    public List<PPuReceiveTestMapper> dataDictionary();
    /**
     * 新增检验结果
     * @return
     */
    public Integer addPuReceiveTest(PuReceiveTest puReceiveTest);

    /**
     * 显示货物类型
     * @return
     */
    public List<PPuReceiveTestMapper> TypeOfGoods();

    /**
     * 导出数据
     * @param pPuReceiveTestMapper
     * @return
     */
    List<PPuReceiveTestMapper> getPuReceiveTest(PPuReceiveTestMapper pPuReceiveTestMapper);

    /**
     * 添加收货单ID
     * @param receiveDetailId
     * @return
     */
    Integer addReceiveDetailId(Long receiveDetailId);

    /**
     * 根据receiveDetailId
     * 查数量
     * @param receiveDetailId
     * @return
     */
    Integer selectCountById(Long receiveDetailId);

    /**
     * 根据receiveDetailId删除
     * @param receiveDetailId
     * @return
     */
    Integer deleteByReceiveDetailId(Long receiveDetailId);

    /**
     * 根据receiveDetailId获取图片
     * @param receiveDetailId
     * @return
     */
    String getImageById(Long receiveDetailId);
}