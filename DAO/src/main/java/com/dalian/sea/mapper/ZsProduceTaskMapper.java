package com.dalian.sea.mapper;

import com.dalian.sea.model.ZsProduceTask;
import com.dalian.sea.parameter.PProduceTaskPDA;
import com.dalian.sea.parameter.PZsProduceTask;
import com.dalian.sea.parameter.YhLeaveStock;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

public interface ZsProduceTaskMapper extends Mapper<ZsProduceTask> {

    /**
     * 获取进行中生产任务
     * @param pZsProduceTask
     * @return
     */
    List<PZsProduceTask> getProcessProduceTask(PZsProduceTask pZsProduceTask);

    /**
     * 获取已结束生产任务
     * @param pZsProduceTask
     * @return
     */
    List<PZsProduceTask> getEndProduceTask(PZsProduceTask pZsProduceTask);

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
    Integer deleteProduceTaskById(ZsProduceTask zsProduceTask);

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
     * 修改当前工艺和状态
     * @param zsProduceTask
     * @return
     */
    Integer updateProduceTask(ZsProduceTask zsProduceTask);

    /**
     * 通过操作类型获取生产任务详情
     * colType(1-产看 2-操作)
     * @param cloType
     * @return
     */
    List<PZsProduceTask> getPZsProduceTaskByColType(@Param("cloType") Integer cloType);

    /**
     * 通过操作类型获取生产任务详情
     * colType(3-审核)
     * @return
     */
    List<PZsProduceTask> getPZsProduceTaskByColTypeThree();

    /**
     * 通过操作类型获取生产任务详情
     * colType(3-审核)
     * @return
     */
    PZsProduceTask getPZsProduceTaskByColTypeThreeForPDAByCode(@Param("code") String code);

    /**
     *通过操作类型获取生产任务详情
     * （colType=4）
     * @return
     */
    List<PZsProduceTask> getPZsProduceTaskByCollType();

    /**
     *通过操作类型获取生产任务详情
     * （colType=4）
     * @return
     */
    PZsProduceTask getPZsProduceTaskByColTypeForPDAByCode(@Param("cloType") Integer cloType,@Param("code") String code);

    /**
     * 通过id修改produceTaskStatus为已结束
     * @param produceTaskId
     * @param updateUserId
     * @return
     */
    Integer updateTaskStatusById(@Param("produceTaskId")Long produceTaskId, @Param("updateUserId")Long updateUserId);

    /**
     * 获取今日生产任务
     * @return
     */
    List<PZsProduceTask> getTodayProduceTask();
	/**
     * 新建生产任务
     * @param yhLeaveStock
     * @param date
     * @param userId
     * @return
     */
    Long newProduceTask(@Param("yh") YhLeaveStock yhLeaveStock, @Param("date") Date date,@Param("userId") Long userId);


    /**
     * 查询生产任务状态
     * @param produceTaskId
     * @return
     */
    Byte getProduceTaskStatusById(Long produceTaskId);
}