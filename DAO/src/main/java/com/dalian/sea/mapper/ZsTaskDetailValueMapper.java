package com.dalian.sea.mapper;

import com.dalian.sea.model.ZsTaskDetailValue;
import com.dalian.sea.parameter.YhLeaveStock;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

public interface ZsTaskDetailValueMapper extends Mapper<ZsTaskDetailValue> {

    /**
     * 通过produce_task_id获取
     *
     * @param produceTaskId
     * @return
     */
    List<ZsTaskDetailValue> getTaskDetailValueByProduceTask(Long produceTaskId);

    /**
     * 通过produce_task_id删除
     *
     * @param produceTaskId
     * @return
     */
    Integer deleteTaskDetailValueByProduceTaskId(Long produceTaskId);

    /**
     * 新建
     *
     * @param yhLeaveStock
     * @param date
     * @param userId
     * @param produceTaskId
     * @return
     */
    Long newTaskDetailValue(@Param("yh") YhLeaveStock yhLeaveStock, @Param("date") Date date, @Param("userId") Long userId, @Param("produceTaskId") Long produceTaskId);


    /**
     * 通过produceTaskId
     * 查workFlowId
     * @param produceTaskId
     * @return
     */
    Long getWorkFlowId(Long produceTaskId);
}