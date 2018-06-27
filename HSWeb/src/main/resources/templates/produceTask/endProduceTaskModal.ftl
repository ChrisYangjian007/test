

<form id="endProduceTaskForm" style="margin: 1px">
    <input type="hidden" name="produceTaskId" value="${produceTaskId}">
    <p style="text-align: center">该生产任务 工序 <#if message??&&message!="">${message}<#else >无</#if> 尚未记录,是否要执行结束任务操作?</p>
</form>
