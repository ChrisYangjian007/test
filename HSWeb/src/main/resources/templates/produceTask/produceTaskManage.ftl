

<div>
    <div class="layoutPanel layout-center" style="position: absolute; z-index: 99; height: 100%; width: 100%;overflow: auto;">
        <div class="tools_bar" style="border-top: none; margin-bottom: 0px;">
            <div class="PartialButton">
                <@shiro.hasPermission name="/produceTask/reload">
                    <a id="lr-replace" title="刷新当前(Ctrl+Q)" onclick="Replace()" class="tools_btn">
                            <span>
                                <b class="btn-reload">刷新</b>
                            </span>
                    </a>
                    <div class="tools_separator"></div>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="/produceTask/produceTaskDetail">
                    <a id="lr-detail" title="查看生产任务详情" onclick="produceTaskDetail()" class="tools_btn">
                            <span>
                                <b class="btn-detail">查看生产任务详情</b>
                            </span>
                    </a>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="/produceTask/endProduceTask">
                    <a id="end" title="结束任务" onclick="endProduceTask()" class="tools_btn">
                            <span>
                                <b class="btn-end">结束任务</b>
                            </span>
                    </a>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="/produceTask/deleteProduceTask">
                    <a id="lr-delete" title="删除" onclick="deleteProduceTask()" class="tools_btn">
                            <span>
                                <b class="btn-delete">删除</b>
                            </span>
                    </a>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="/produceTask/back">
                    <div class="tools_separator"></div>
                    <a id="lr-leave" title="关闭当前窗口(Esc)" onclick="btn_back()" class="tools_btn">
                <span>
                    <b class="btn-back">离开</b>
                </span>
                    </a>
                </@shiro.hasPermission>
            </div>
        </div>
        <div class="line"></div>
        <ul class="nav nav-tabs ">
            <li class="active">
                <a href="#tab1" id="a1" data-toggle="tab"> 进行中 </a>
            </li>
            <li>
                <a href="#tab2"id="a2"  data-toggle="tab"> 已完成 </a>
            </li>
        </ul>
        <div class="line"></div>
        <div class="tab-content">
            <div class="tab-pane active" id="tab1">
                <div class="bottomline QueryArea" style="margin: 1px; margin-top: 0px; margin-bottom: 0px;">
                    <table border="0" class="form-find" style="height: 45px;">
                        <tbody>
                        <tr>
                            <td>
                                生产任务编号：<input id="txtProduceNo" name="produceNo" type="text" class="txt" style="width: 100px">
                            </td>
                            <td>
                                生产任务：<input id="txtProduce" name="produce" type="text" class="txt" style="width: 100px">
                            </td>
                            <td>
                                出库申请人 <input id="txtUser" name="user" type="text" class="txt" style="width: 100px">
                            </td>
                            <td colspan="2">
                                最后一次出库时间：
                                <input id="beginDate1" name="beginDate" type="text" class="txt Wdate" style="width: 140px; " onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})">至
                                <input id="endDate1" name="endDate" type="text" class="txt Wdate" style="width: 140px; " onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                当前工序审核状态：
                                <select id="txtStatus" name="status" class="txtselect" datacol="yes" err="状态"
                                        checkexpession="NotNull">
                                    <option value="">==请选择==</option>
                                    <option value="0">未提交</option>
                                    <option value="1">已提交</option>
                                    <option value="2">已审核</option>
                                    <option value="3">无工序状态</option>
                                </select>
                            </td>
                            <td>
                                <input id="btnSearch" type="button" class="btnSearch" value="查 询">
                                <input id="btnClear" type="button" class="btnSearch" value="重 置">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <table id="gridTable"></table>
                <div id="gridPager"></div>

            </div>
            <div class="tab-pane" id="tab2">
                <div class="bottomline QueryArea" style="margin: 1px; margin-top: 0px; margin-bottom: 0px;">
                    <table border="0" class="form-find" style="height: 45px;">
                        <tbody>
                        <tr>
                            <td>
                                生产任务编号：<input id="txtProduceNo2" name="produceNo" type="text" class="txt" style="width: 100px">
                            </td>
                            <td>
                                生产任务：<input id="txtProduce2" name="produce" type="text" class="txt" style="width: 100px">
                            </td>
                            <td>
                                出库申请人：<input id="txtUser2" name="user" type="text" class="txt" style="width: 100px">
                            </td>
                            <td colspan="2">
                                最后一次出库时间：
                                <input id="beginDate2" name="beginDate" type="text" class="txt Wdate" style="width: 140px; " onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})">至
                                <input id="endDate2" name="endDate" type="text" class="txt Wdate" style="width: 140px; " onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input id="btnSearch2" type="button" class="btnSearch" value="查 询">
                                <input id="btnClear2" type="button" class="btnSearch" value="重 置">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <table id="gridTable2"></table>
                <div id="gridPager2"></div>
            </div>
        </div>
    </div>
</div>

<@shiro.hasPermission name="/produceTask/deleteProduceTask">
<#--删除确认-->
<div id="deleteProduceTaskModal" class="modal fade " data-width="400" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header" style="text-align: center">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">删除确认</h4>
    </div>
    <div class="modal-body">
        <p style="text-align: center">删除后数据不可恢复</p>
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="deleteProduceTask" type="button" class="btn green">确定</button>
    </div>
</div>
</@shiro.hasPermission>

<@shiro.hasPermission name="/produceTask/endProduceTask">
<#--结束任务-->
<div id="endProduceTaskModal" class="modal fade " data-width="400" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header" style="text-align: center">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">结束任务</h4>
    </div>
    <div class="modal-body">
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="endProduceTask" type="button" class="btn green">确定</button>
    </div>
</div>
</@shiro.hasPermission>

<script type="text/javascript">

    var resourceId;

    $(document).ready(function () {
        GetGrid();
        GetGrid2();
        gridPagerStyle();
        resourceId = top.$("#ModuleId").val()
    });
    var table = 1;
    $("#a1").unbind("click");
    $("#a1").click(function () {
        table =1;
        $("#end").removeClass("hidden");
    });
    $("#a2").unbind("click");
    $("#a2").click(function () {
        table =2;
        $("#end").addClass("hidden");
    });
    //进行中生产任务
    function GetGrid() {
        $("#gridTable").jqGrid({
            url: "${request.contextPath}/produceTask/GridJson.json",
            datatype: "json",
            height: $(window).height() - 214,
            width: $(window).width(),
            colModel: [
                {label: "编号", name: "produceTaskId", index: "produceTaskId", width: 80},
                {label: "生产任务编号", name: "produceTaskNo", index: "produceTaskNo", width: 200},
                {label: "生产任务", name: "produceTaskName", index: "produceTaskName",width: 300},
                {label: "出库次数", name: "leaveStockNumber", index: "leaveStockNumber", width: 80},
                {label: "最后一次出库申请人", name: "leaveUser", index: "leaveUser", width: 150},
                {
                    label: "最后一次出库时间", name: "leaveDates", index: "leaveDates", width: 150,
                    formatter: function (cellvalue, options, rowObject) {
                        return formatDate(cellvalue, 'yyyy-MM-dd hh:mm:ss');
                    }
                },
                {label: "入库次数", name: "enterStockNumber", index: "enterStockNumber", width: 80},
                {
                    label: "生产任务当前任务操作工序", name: "currentOperatingProcedures", index: "currentOperatingProcedures", width: 180,
                    formatter: function (cellvalue, options, rowObject) {
                        if(""==cellvalue){
                            return "无当前工序";
                        }else {
                            return cellvalue;
                        }
                    }
                },
                {label: "当前工序审核状态", name: "checkStatus", index: "checkStatus", width: 100,
                    formatter: function (cellvalue, options, rowObject) {
                    if(""==rowObject.currentOperatingProcedures){
                        return "无当前工序";
                    }else {
                        if(0==cellvalue){
                            return "未提交";
                        }else if(1==cellvalue){
                            return "已提交";
                        }else if(2==cellvalue){
                            return "已审核";
                        }else {
                            return "";
                        }
                    }
                    }
                },
                {
                    label: "当前工序检查人", name: "currentChecker", index: "currentChecker", width: 100,
                    formatter: function (cellvalue, options, rowObject) {
                        if(""==cellvalue){
                            return "无当前工序检查人";
                        }else {
                            return cellvalue;
                        }
                    }
                }
            ],
            pagerpos: "right",
            recordpos: "left",
            viewrecords: true,
            rowNum: 15,
            rowList: [15, 30, 50, 100, 500],
            pager: "#gridPager",
            jsonReader:{
                root:"root",page:"page",total:"total",records:"records"
            },
            sortname: 'listIndex',
            sortorder: 'desc',
            shrinkToFit: false,
            gridview: true
        });
    }

    //生产任务已结束
    function GetGrid2() {
        $("#gridTable2").jqGrid({
            url: "${request.contextPath}/produceTask/GridJson2.json",
            datatype: "json",
            height: $(window).height() - 214,
            width: $(window).width(),
            colModel: [
                {label: "编号", name: "produceTaskId", index: "produceTaskId", width: 80},
                {label: "生产任务编号", name: "produceTaskNo", index: "produceTaskNo", width: 200},
                {label: "生产任务", name: "produceTaskName", index: "produceTaskName",width: 200},
                {label: "出库次数", name: "leaveStockNumber", index: "leaveStockNumber", width: 80},
                {label: "最后一次出库申请人", name: "leaveUser", index: "leaveUser", width: 150},
                {
                    label: "最后一次出库时间", name: "leaveDates", index: "leaveDates", width: 150,
                    formatter: function (cellvalue, options, rowObject) {
                        return formatDate(cellvalue, 'yyyy-MM-dd hh:mm:ss');
                    }
                },
                {label: "入库次数", name: "enterStockNumber", index: "enterStockNumber", width: 80},
                {
                    label: "最后一次入库时间", name: "enterDates", index: "enterDates", width: 150,
                    formatter: function (cellvalue, options, rowObject) {
                        return formatDate(cellvalue, 'yyyy-MM-dd hh:mm:ss');
                    }
                },
                {label: "最后一次入库申请人", name: "enterUser", index: "enterUser", width: 120},
                {label: "结束时间", name: "updateDate", index: "updateDate", width: 150},
                {label: "结束人", name: "endUserName", index: "endUserName", width: 100}
            ],
            pagerpos: "right",
            recordpos: "left",
            viewrecords: true,
            rowNum: 15,
            rowList: [15, 30, 50, 100, 500],
            pager: "#gridPager2",
            jsonReader:{
                root:"root",page:"page",total:"total",records:"records"
            },
            sortname: 'listIndex',
            sortorder: 'desc',
            shrinkToFit: false,
            gridview: true

        });
    }

    //分页布局
    function gridPagerStyle() {
        var width = document.getElementById("gridPager_right").offsetWidth+5;
        $("#gridPager_right").attr("style","width:"+width);
        width = document.getElementById("gridPager2_right").offsetWidth+5;
        $("#gridPager2_right").attr("style","width:"+width);
    }

    //查询进行中
    $("#btnSearch").unbind("click");
    $("#btnSearch").click(function () {
        var produceTaskNo = $("#txtProduceNo").val();
        var produceTaskName = $("#txtProduce").val();
        var lastUserName = $("#txtUser").val();
        var beginDate = $("#beginDate1").val();
        var endDate = $("#endDate1").val();
        var auditStatus = $("#txtStatus").val();
        var postData ={produceTaskNo: produceTaskNo, produceTaskName: produceTaskName,
            lastUserName: lastUserName, beginDate: beginDate, endDate: endDate,
            auditStatus: auditStatus};
        $("#gridTable").jqGrid("setGridParam",{
            postData:postData,
            url: "${request.contextPath}/produceTask/GridJson.json",
            page:1
        }).trigger("reloadGrid");
    });
    $("#btnClear").unbind("click");
    $("#btnClear").click(function () {
        var produceTaskNo = $("#txtProduceNo").val("");
        var produceTaskName = $("#txtProduce").val("");
        var lastUserName = $("#txtUser").val("");
        var lastWarehouse = $("#txtWarehouse").val("");
        var beginDate = $("#beginDate1").val("");
        var endDate = $("#endDate1").val("");
        var auditStatus = $("#txtStatus").val("");
        var postData ={produceTaskNo: "", produceTaskName: "",
            lastUserName: "", lastWarehouse: "", beginDate: "", endDate: "",
            auditStatus: ""};
        $("#gridTable").jqGrid("setGridParam",{
            postData:postData,
            url: "${request.contextPath}/produceTask/GridJson.json",
            page:1
        }).trigger("reloadGrid");
    });

<@shiro.hasPermission name="/produceTask/produceTaskDetail">
//    查看生产任务详情
    function produceTaskDetail() {
        var id;
        if(1==table){
            id = GetJqGridRowValue("#gridTable", "produceTaskId");
        }else {
            id = GetJqGridRowValue("#gridTable2", "produceTaskId");
        }
        if(null!=id){
            AddTabMenu(id+'produceTaskDetailIFrame', '${request.contextPath}/produceTask/produceTaskDetailIFrame.htm?id=' +id+"&resourceId="+resourceId,'生产任务详情', "scroll_pane_detail.png", 'true',"${staticImg}");
        }else {
            tipDialog("请选择您要查看的列！",4,"warning");
        }
    }
</@shiro.hasPermission>
    //删除
    <@shiro.hasPermission name="/produceTask/deleteProduceTask">
    function deleteProduceTask() {
        var id;
        if(1==table){
            id = GetJqGridRowValue("#gridTable", "produceTaskId");
        }else {
            id = GetJqGridRowValue("#gridTable2", "produceTaskId");
        }
        if (null == id) {
            tipDialog("请选择您要删除的记录", 4, "warning");
        } else {
            $("#deleteProduceTaskModal").modal("show");
        }
    }
    $("#deleteProduceTask").unbind("click");
    $("#deleteProduceTask").click(function () {
        var id;
        if(1==table){
            id = GetJqGridRowValue("#gridTable", "produceTaskId");
        }else {
            id = GetJqGridRowValue("#gridTable2", "produceTaskId");
        }
        Loading(true,"正在提交...");
        $.post("${request.contextPath}/produceTask/deleteProduceTask.json", {id: id,resourceId: resourceId}, function (res) {
            if (res.success) {
                $("#gridTable").trigger("reloadGrid");
                $("#gridTable2").trigger("reloadGrid");
                tipDialog(res.msg, 4, "1");
                $("#deleteProduceTaskModal").modal("hide");
            } else {
                tipDialog(res.msg, 4, "warning");
                $("#deleteProduceTaskModal").modal("hide");
            }
            Loading(false);
        }, "json");
    });
    </@shiro.hasPermission>
<@shiro.hasPermission name="/produceTask/produceTaskDetail">

    function updateProduceTask() {
        var id;
        if(1==table){
            id = GetJqGridRowValue("#gridTable", "produceTaskId");
        }
        if(null != id){
            $("#updateProduceTaskModal").modal({
                remote: "${request.contextPath}/produceTask/updateProduceTaskModal.htm"
            });
        }else {
            tipDialog("请选择生产任务",4,"warning")
        }

    }
</@shiro.hasPermission>

<@shiro.hasPermission name="/produceTask/endProduceTask">
//    结束任务
    function endProduceTask() {
       var id = GetJqGridRowValue("#gridTable", "produceTaskId");
       if(null!=id){
            $("#endProduceTaskModal").modal({
                remote:"${request.contextPath}/produceTask/endProduceTaskModal.htm?produceTaskId="+id
            });
       }else {
           tipDialog("请选择您要结束的生产任务",4,"warning");
       }
    }

    $("#endProduceTask").unbind("click");
    $("#endProduceTask").click(function () {
        Loading(true,"正在提交...","#endProduceTaskModal");
        $.post("${request.contextPath}/produceTask/endProduceTask.json", $("#endProduceTaskForm").serialize(), function (res) {
            if(res.success){
                $("#gridTable").trigger("reloadGrid");
                $("#gridTable2").trigger("reloadGrid");
                tipDialog(res.msg,4,"1");
                $("#endProduceTaskModal").modal("hide");
            }else {
                tipDialog(res.msg,4,"warning");
            }
            Loading(false,"","#endProduceTaskModal");
        }, "JSON");
    });
</@shiro.hasPermission>

    //查询已结束
    $("#btnSearch2").unbind("click");
    $("#btnSearch2").click(function () {
        var produceTaskNo = $("#txtProduceNo2").val();
        var produceTaskName = $("#txtProduce2").val();
        var lastUserName = $("#txtUser2").val();
        var beginDate = $("#beginDate2").val();
        var endDate = $("#endDate2").val();
        var postData ={produceTaskNo: produceTaskNo, produceTaskName: produceTaskName,
            lastUserName: lastUserName, beginDate: beginDate, endDate: endDate};
        $("#gridTable2").jqGrid("setGridParam",{
            postData:postData,
            url: "${request.contextPath}/produceTask/GridJson2.json",
            page:1
        }).trigger("reloadGrid");
    });
    $("#btnClear2").unbind("click");
    $("#btnClear2").click(function () {
        var produceTaskNo = $("#txtProduceNo2").val("");
        var produceTaskName = $("#txtProduce2").val("");
        var lastUserName = $("#txtUser2").val("");
        var lastWarehouse = $("#txtWarehouse2").val("");
        var beginDate = $("#beginDate2").val("");
        var endDate = $("#endDate2").val("");
        var postData ={produceTaskNo: "", produceTaskName: "",
            lastUserName: "", lastWarehouse: "", beginDate: "", endDate: ""};
        $("#gridTable2").jqGrid("setGridParam",{
            postData:postData,
            url: "${request.contextPath}/produceTask/GridJson2.json",
            page:1
        }).trigger("reloadGrid");
    });
</script>