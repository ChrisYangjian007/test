package com.dalian.sea.parameter;

import com.dalian.sea.model.PuEnterStock;
import com.dalian.sea.model.PuEnterStockDetail;
import com.dalian.sea.model.ZsStock;
import lombok.Data;

import java.util.List;

/**
 * Created by 陈逸文 on 2018/3/12.
 */
@Data
public class PEnterStockAndStock {
   private PuEnterStock enterStock;//入库单
   private List<PuEnterStockDetail> enterStockDetailList;//入库详情集合
   private List<PZsStock> stockList;//库存集合
}
