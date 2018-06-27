package com.dalian.sea.service;

import com.dalian.sea.model.PuReceiveDetail;
import com.dalian.sea.model.PuReceiveTest;
import com.dalian.sea.parameter.PPuReceiveTestMapper;
import com.dalian.sea.parameter.PuReceiveDetailPa;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 2018/2/7.
 */
public interface PuReceiveTestService {
    /**
     * 显示所有数据
     * @return
     */
    List<PPuReceiveTestMapper> puReceiveTestList(PPuReceiveTestMapper puReceiveTest,int page, int rows);
    /**
     * 根据id查询货品
     * @param id
     * @return
     */
    PuReceiveTest uploadingReports(Long id);
    /**
     * 显示检验方式
     * @return
     */
    List<PPuReceiveTestMapper> dataDictionary();
    /**
     * 新增检验结果
     * @return
     */
    Boolean addPuReceiveTest(PuReceiveTest puReceiveTest);

    /**
     * 显示货物类型
     * @return
     */
    List<PPuReceiveTestMapper> TypeOfGoods();

    /**
     *数据导出
     * @param request
     * @param response
     * @return
     */
    Boolean updateEnterpriseModalservice(HttpServletRequest request, HttpServletResponse response);

    /**
     * 根据receiveDetailId
     * 判断记录是否已存在
     * 报送检验
     * @param puReceiveDetail
     * @return
     */
    Boolean checkReceiveDetail(List<PuReceiveDetailPa> puReceiveDetail);

    /**
     * 根据receiveDetailId获取图片
     * @param receiveDetailId
     * @return
     */
    String getImageById(Long receiveDetailId);
}
