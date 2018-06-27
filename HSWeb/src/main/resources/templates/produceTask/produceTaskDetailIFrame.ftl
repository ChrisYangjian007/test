
<link rel="stylesheet" href="${staticCss}/js/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="${staticCss}/css/animate.css">
<link rel="stylesheet" href="${staticCss}/css/comment.css">
<link rel="stylesheet" href="${request.contextPath}/css/produce.css">

<div class="tools_bar leftline rightline" style="margin-bottom: 0px; margin: 1px;">
    <div class="PartialButton">
        <@shiro.hasPermission name="/produceTask/processDetail">
            <a id="lr-detail" title="查看工序详情" onclick="fromAttribute()" class="tools_btn">
                            <span>
                                <b class="btn-detail">查看工序详情</b>
                            </span>
            </a>
        </@shiro.hasPermission>
    </div>
</div>
<div class="produce-main">
    <div class="produce-container">
        <div class="title">
            <span class="line"></span>
            <span class="title-name">生产任务</span>
        </div>
        <div class="produce-box">
            <ul class="produce-task">
                <li>
                    <p>生产任务状态</p>
                    <span class="finish">
                        <#if pZsProduceTask.produceTaskStatus??>
                            <#if pZsProduceTask.produceTaskStatus==1>进行中
                            <#elseif pZsProduceTask.produceTaskStatus==2>已结束
                            <#else >
                            </#if>
                        <#else >
                        </#if>
                    </span>
                </li>
                <li>
                    <p>生产任务</p>
                    <h4 style="color:#3598dc;">
                        <#if pZsProduceTask.produceTaskName??>
                            ${pZsProduceTask.produceTaskName}
                        <#else >
                        </#if>
                    </h4>
                </li>
                <li>
                    <p>生产任务编号</p>
                    <h4>
                        <#if pZsProduceTask.produceTaskNo??>
                            ${pZsProduceTask.produceTaskNo}
                        <#else >
                        </#if>
                    </h4>
                </li>
                <li>
                    <p>生产任务二维码编号</p>
                    <h4>
                        <#if code??>
                            ${code}
                        <#else >
                        </#if>
                    </h4>
                </li>
            </ul>
        </div>
    </div>
    <div class="produce-container">
        <div class="title">
            <span class="line"></span>
            <span class="title-name">记录工序状态</span>
        </div>
        <div class="produce-box">
            <#if pZsWorkProcessList??&&pZsWorkProcessList?size!=0>
                <ul class="produce-record">
                    <li>
                        <p>工序</p>
                        <span class="diagonal"></span>
                        <p>状态</p>
                    </li>
                    <#list pZsWorkProcessList as workProcess>
                        <li>
                            <p>${workProcess.CName}</p>
                            <span class="diagonal"></span>
                            <p>
                                <#if workProcess.baFormAttributeValue??>
                                    <#if workProcess.baFormAttributeValue.handleType==1>
                                        已提交
                                    <#else >
                                        已审核
                                    </#if>
                                <#else >
                                    未提交
                                </#if>
                            </p>
                        </li>
                    </#list>
                </ul>
            <#else >
                暂无数据
            </#if>
        </div>
    </div>
    <div class="produce-container">
        <div class="title">
            <span class="line"></span>
            <span class="title-name">出库记录</span>
        </div>
        <div class="produce-box table-box">
            <table class="table">
                <thead>
                <tr>
                    <th>出库编号</th>
                    <th>仓库</th>
                    <th>批次号</th>
                    <th>货物类型</th>
                    <th>产品名称</th>
                    <th>产品状态</th>
                    <th>出库规格</th>
                    <th>出库数量</th>
                    <th>单位</th>
                    <th>出库申请人</th>
                    <th>经手人</th>
                    <th>出库时间</th>
                </tr>
                </thead>
                <tbody>
                <#if saLeaveStockDetailParaList??&&saLeaveStockDetailParaList?size!=0>
                    <#list saLeaveStockDetailParaList as leaveStock>
                        <tr>
                            <td>
                                <#if leaveStock.saLeaveStock.leaveNo??>${leaveStock.saLeaveStock.leaveNo}
                                    <#else >

                                </#if>
                            </td>
                            <td>
                                <#if leaveStock.zsWarehouse??>${leaveStock.zsWarehouse.CName}
                                    <#else >

                                </#if>
                            </td>
                            <td>
                                <#if leaveStock.batchNo??>${leaveStock.batchNo}
                                    <#else >

                                </#if>
                            </td>
                            <td>
                                <#if leaveStock.materialName??>${leaveStock.materialName}
                                    <#else >

                                </#if>
                            </td>
                            <td>
                                <#if leaveStock.productName??>${leaveStock.productName}
                                    <#else >

                                </#if>
                            </td>
                            <td>
                                <#if leaveStock.productStatusName??>${leaveStock.productStatusName}
                                <#else >

                                </#if>
                            </td>
                            <td>
                                <#if leaveStock.productSpecName??>${leaveStock.productSpecName}
                                    <#else >

                                </#if>
                            </td>
                            <td>
                                <#if leaveStock.outWeight??>${leaveStock.outWeight}
                                    <#else >

                                </#if>
                            </td>
                            <td>
                                <#if leaveStock.unitName??>${leaveStock.unitName}
                                    <#else >

                                </#if>
                            </td>
                            <td>
                                <#if leaveStock.saLeaveStock.leavePerson??>${leaveStock.saLeaveStock.leavePerson}
                                    <#else >

                                </#if>
                            </td>
                            <td>
                                <#if leaveStock.saLeaveStock.brokerage??>${leaveStock.saLeaveStock.brokerage}
                                    <#else >

                                </#if>
                            </td>
                            <td>
                                <#if leaveStock.saLeaveStock.leaveDate??>${leaveStock.saLeaveStock.leaveDate?string("yyyy-MM-dd HH:mm:ss")}
                                    <#else >

                                </#if>
                            </td>
                        </tr>
                    </#list>
                 <#else >
                     <tr>
                         <td colspan="12">暂无记录</td>
                     </tr>
                 </#if>
                </tbody>
            </table>
        </div>
    </div>
    <div class="produce-container">
        <div class="title">
            <span class="line"></span>
            <span class="title-name">入库记录</span>
        </div>
        <div class="produce-box table-box">
            <table class="table">
                <thead>
                <tr>
                    <th>入库编号</th>
                    <th>仓库</th>
                    <th>批次号</th>
                    <th>货物类型</th>
                    <th>产品名称</th>
                    <th>规格</th>
                    <th>原料数量</th>
                    <th>单位</th>
                    <th>产品状态</th>
                    <th>入库规格</th>
                    <th>入库数量</th>
                    <th>单位</th>
                    <th>入库申请人</th>
                    <th>经手人</th>
                    <th>入库时间</th>
                </tr>
                </thead>
                <tbody>
                    <#if puEnterStockDetailParaList??>
                        <#list puEnterStockDetailParaList as enterStock>
                            <tr>
                                <td>
                                    <#if enterStock.enterNo??>${enterStock.enterNo}
                                    <#else >
                                    </#if>
                                </td>
                                <td>
                                    <#if enterStock.zsWarehouse.CName??>${enterStock.zsWarehouse.CName}
                                    <#else >
                                    </#if>
                                </td>
                                <td>
                                    <#if enterStock.batchNo??>${enterStock.batchNo}
                                    <#else >
                                    </#if>
                                </td>
                                <td>
                                    <#if enterStock.materialName??>${enterStock.materialName}
                                    <#else >
                                    </#if>
                                </td>
                                <td>
                                    <#if enterStock.productName??>${enterStock.productName}
                                    <#else >
                                    </#if>
                                </td>
                                <td>
                                    <#if enterStock.enterProductSpecName??>${enterStock.enterProductSpecName}
                                    <#else >
                                    </#if>
                                </td>
                                <td>
                                    <#if enterStock.materialWeight??>${enterStock.materialWeight}
                                    <#else >
                                    </#if>
                                </td>
                                <td>
                                    <#if enterStock.unitName??>${enterStock.unitName}
                                    <#else >
                                    </#if>
                                </td>
                                <td>
                                    <#if enterStock.productStatusName??>${enterStock.productStatusName}
                                    <#else >
                                    </#if>
                                </td>
                                <td>
                                    <#if enterStock.productSpecName??>${enterStock.productSpecName}
                                    <#else >
                                    </#if>
                                </td>
                                <td>
                                    <#if enterStock.inWeight??>${enterStock.inWeight}
                                    <#else >
                                    </#if>
                                </td>
                                <td>
                                    <#if enterStock.unitName??>${enterStock.unitName}
                                    <#else >
                                    </#if>
                                </td>
                                <td>
                                    <#if enterStock.puEnterStock.enterPerson??>${enterStock.puEnterStock.enterPerson}
                                    <#else >
                                    </#if>
                                </td>
                                <td>
                                    <#if enterStock.puEnterStock.brokerage??>${enterStock.puEnterStock.brokerage}
                                    <#else >
                                    </#if>
                                </td>
                                <td>
                                    <#if enterStock.puEnterStock.enterDate??>${enterStock.puEnterStock.enterDate?string("yyyy-MM-dd HH:mm:ss")}
                                    <#else >
                                    </#if>
                                </td>
                            </tr>
                        </#list>
                    <#else >
                        <td colspan="15">暂无数据</td>
                    </#if>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script type="text/javascript">

    var canValueDetail = ${canValueDetail};
    var resourceId = ${resourceId};

    $(document).ready(function () {
        Loading(false);
    });
    function fromAttribute() {
        if(1==canValueDetail){
            var id= ${pZsProduceTask.produceTaskId};
            AddTabMenu(id+'fromAttributeIFrame', '${request.contextPath}/produceTask/fromAttributeIFrame.htm?id=' +id+"&resourceId="+resourceId,'工序详情', "scroll_pane_detail.png", 'true',"${staticImg}");
        }else {
            tipDialog("该生产任务无工序，不能查看详情",4,"warning");
        }
    }
</script>