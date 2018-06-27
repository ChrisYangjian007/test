package com.dalian.sea.service;

import com.dalian.sea.model.Ireport;
import com.dalian.sea.parameter.PEnterStockAndStock;
import com.dalian.sea.parameter.YhLeaveStock;

import java.util.List;

/**
 * IreportService
 *
 * @author TONE
 * @date 2018/4/3.
 */
public interface IreportService {

    /**
     * 入库单
     * @param stockAndStock
     * @param userName
     * @return
     */
    List<Ireport> receiveStoragePdf(PEnterStockAndStock stockAndStock,String userName);

    /**
     * 返库单
     * @param stockAndStock
     * @param userName
     * @return
     */
    List<Ireport> receiveDepotPdf(PEnterStockAndStock stockAndStock,String userName);

    /**
     * 出库单
     * @param leaveStock
     * @param userName
     * @return
     */
    List<Ireport> receiveOutGoingPdf(YhLeaveStock leaveStock, String userName);


}
