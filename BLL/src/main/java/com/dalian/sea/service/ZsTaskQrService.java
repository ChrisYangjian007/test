package com.dalian.sea.service;

import com.dalian.sea.model.ZsTaskQr;

/**
 *
 * @author 杨文波
 * @date 2018/3/8
 */
public interface ZsTaskQrService {

    /**
     * 绑定任务编码以及二维码标签
     * @param zsTaskQr
     * @return
     * */
    boolean insertTaskQr(ZsTaskQr zsTaskQr);

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
