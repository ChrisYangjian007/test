package com.dalian.sea.mapper;

import com.dalian.sea.model.ZsTaskQr;
import tk.mybatis.mapper.common.Mapper;

public interface ZsTaskQrMapper extends Mapper<ZsTaskQr> {

    /**
     * 绑定任务编码以及二维码标签
     * @param zsTaskQr
     * @return
     * */
    Integer insertTaskQr(ZsTaskQr zsTaskQr);

    /**
     * 根据code获取
     * @param codeId
     * @return
     * */
    ZsTaskQr getTaskQrByCode(Long codeId);

    /**
     * 通过produceTaskId获取编码
     * @param produceTaskId
     * @return
     */
    ZsTaskQr getTaskQrByProduceTaskId(Long produceTaskId);
}