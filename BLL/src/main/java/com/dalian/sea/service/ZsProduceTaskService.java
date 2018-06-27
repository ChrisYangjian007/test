package com.dalian.sea.service;

import com.dalian.sea.json.Json;
import com.dalian.sea.model.ZsProduceTask;
import com.dalian.sea.parameter.PProduceTaskPDA;
import com.dalian.sea.parameter.PZsProduceTask;

import java.util.List;

/**
 * Created by Administrator on 2018/2/26.
 */
public interface ZsProduceTaskService {

    /**
     * 获取进行中生产任务
     * @param pZsProduceTask
     * @param page
     * @param rows
     * @return
     */
    List<PZsProduceTask> getProcessProduceTask(PZsProduceTask pZsProduceTask, Integer page, Integer rows);

    /**
     * 获取已结束生产任务
     * @param pZsProduceTask
     * @param page
     * @param rows
     * @return
     */
    List<PZsProduceTask> getEndProduceTask(PZsProduceTask pZsProduceTask, Integer page, Integer rows);

    /**
     * 根据id获取生产任务
     * @param id
     * @return
     */
    PZsProduceTask getProduceTaskByDetailId(Long id);

    /**
     * 根据id获取生产任务
     * @param id
     * @return
     */
    PProduceTaskPDA getProduceTaskByIdForPDA(Long id);

    /**
     * 通过produce_task_id删除生产任务
     * @param zsProduceTask
     * @return
     */
    Boolean deleteProduceTaskById(ZsProduceTask zsProduceTask);

    /**
     * 根据编号获取生产任务
     * @param no
     * @return
     */
    ZsProduceTask getProduceTaskByNo(String no);

    /**
     * 根据编号获取生产任务
     * @param produceTaskNo
     * @return
     */
    ZsProduceTask getProduceTaskByProduceTaskNo(String produceTaskNo);

    /**
     * 通过操作类型获取生产任务详情
     * colType(1-产看 2-操作 3-审核 4-巡检)
     * @param cloType
     * @return
     */
    List<PZsProduceTask> getPZsProduceTaskByColTypeForPDA(Integer cloType,Long userId,int page,int rows);

    /**
     * 通过操作类型获取生产任务详情
     * colType(1-产看 2-操作 3-审核 4-巡检)
     * @param cloType
     * @return
     */
    Json getPZsProduceTaskByColTypeForPDAByCode(String code, Integer cloType, Long userId);

    /**
     * 通过id修改produceTaskStatus为已结束
     * @param produceTaskId
     * @param updateUserId
     * @return
     */
    Boolean updateTaskStatusById(Long produceTaskId,Long updateUserId);

    /**
     * 获取今日生产任务
     * @return
     */
    List<PZsProduceTask> getTodayProduceTask();

    /**获取进行中生产任务
     * @param pZsProduceTask
     * @return
     */
    List<PZsProduceTask> selectProduceTaskList(PZsProduceTask pZsProduceTask);

    /**
     * 查询生产任务状态
     * @param produceTaskId
     * @return
     */
    Byte getProduceTaskStatusById(Long produceTaskId);
}
