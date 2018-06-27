
<link rel="stylesheet" href="${staticCss}/js/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="${staticCss}/css/animate.css">
<link rel="stylesheet" href="${staticCss}/css/comment.css">
<link rel="stylesheet" href="${request.contextPath}/css/produce.css">
<style>

    .produce-left {
        /*width: 25%;*/
        /*border:1px solid #e7ecf1;*/
        border:1px solid #e7ecf1;
        font-size: 15px;
        height: 17px;
    }

</style>
<div class="tools_bar leftline rightline" style="margin-bottom: 0px; margin: 1px;">
    <div class="PartialButton">
    <@shiro.hasPermission name="/produceTask/updateWorkProcess">
        <a id="lr-detail" title="编辑工序详情" onclick="updateFormAttributeValue()" class="tools_btn">
                            <span>
                                <b class="btn-detail">编辑工序详情</b>
                            </span>
        </a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/produceTask/updateWorkProcess">
        <a id="lr-detail" title="返回" onclick="backProduceTaskDetail()" class="tools_btn">
                            <span>
                                <b class="btn-detail">返回</b>
                            </span>
        </a>
    </@shiro.hasPermission>
    </div>
</div>

<div class="produce-main">
    <#if pZsWorkProcessList??&&pZsWorkProcessList?size!=0>
        <#list pZsWorkProcessList as workProcess>
            <div class="produce-container">
                <div class="title">
                    <span class="produce-num">${workProcess_index+1}</span>
                    <span class="title-name">${workProcess.CName}</span>
                </div>
                <#if workProcess.valueDataList1??&&workProcess.valueDataList1?size!=0>
                    <div class="produce-box record">
                        <div class="record-list">
                            <p class="produce-title">操作记录</p>
                            <#list workProcess.valueDataList1 as valueData>
                                <#if valueData.value??&&valueData.value!="">
                                    <div class="col-md-4">
                                        <div class="produce-left" style="width: 35%;text-align: center;">${valueData.name}:</div>
                                        <div class="produce-left" style="width: 50%;">${valueData.value}</div>
                                    </div>
                                </#if>
                            </#list>
                        </div>
                        <#if workProcess.valueDataList2??&&workProcess.valueDataList2?size!=0>
                        <div class="record-list">
                            <p class="produce-title">审核记录</p>
                            <#list workProcess.valueDataList2 as valueData>
                                <#if valueData.value??&&valueData.value!="">
                                    <div class="col-md-4">
                                        <div class="produce-left" style="width: 35%;text-align: center;">${valueData.name}:</div>
                                        <div class="produce-left" style="width: 50%;">${valueData.value}</div>
                                    </div>
                                </#if>
                            </#list>
                        </div>
                        </#if>
                        <#if workProcess.valueDataList3??&&workProcess.valueDataList3?size!=0>
                        <div class="record-list">
                            <p class="produce-title">巡查记录</p>
                            <#list workProcess.valueDataList3 as valueData>
                                <#if valueData.value??&&valueData.value!="">
                                    <div class="col-md-4">
                                        <div class="produce-left" style="width: 35%;text-align: center;">${valueData.name}:</div>
                                        <div class="produce-left" style="width: 50%;">${valueData.value}</div>
                                    </div>
                                </#if>
                            </#list>
                        </div>
                        </#if>
                    </div>
                <#else >
                    无工序详情
                </#if>
            </div>
        </#list>
    <#else >
        无工序
    </#if>
</div>

<script>

    var isHaveWorkProcess = ${isHaveWorkProcess};
    var id= ${pZsProduceTask.produceTaskId};
    var resourceId = ${resourceId};

    $(document).ready(function () {
        Loading(false);
    });

    <@shiro.hasPermission name="/produceTask/updateWorkProcess">
        //编辑工序详情
        function updateFormAttributeValue() {
            if(1==isHaveWorkProcess){
                AddTabMenu(id+'updateFormAttributeIFrame', '${request.contextPath}/produceTask/updateFormAttributeIFrame.htm?id=' +id+"&resourceId="+resourceId,'编辑工序详情', "edit.gif", 'true',"${staticImg}");
             }else {
                tipDialog("当前生产任务所有工序已经删除，不能编辑",4,"warning");
            }
        }
    </@shiro.hasPermission>

    <@shiro.hasPermission name="/produceTask/updateWorkProcess">
        //   返回
        function backProduceTaskDetail() {
            var id = ${pZsProduceTask.produceTaskId};
            AddTabMenu(id+'produceTaskDetailIFrame', '${request.contextPath}/produceTask/produceTaskDetailIFrame.htm?id=' +id,'生产任务详情', "scroll_pane_detail.png", 'true',"${staticImg}");
        }
    </@shiro.hasPermission>
</script>
