
<div class="tools_bar leftline rightline" >
    <div class="PartialButton">
    <@shiro.hasPermission name="/qrCode/reload">
        <a id="lr-replace" title="刷新当前(Ctrl+Q)" onclick="Replace()" class="tools_btn">
                <span>
                    <b class="btn-reload">刷新</b>
                </span>
        </a>
        <div class="tools_separator"></div>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/qrCode/addQRCode">
        <a id="lr-add" title="新增(Ctrl+Z)" onclick="addQRCode()" class="tools_btn">
                <span>
                    <b class="btn-add">新增</b>
                </span>
        </a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/qrCode/downQRCode">
        <div class="tools_separator"></div>
        <a id="lr-down" title="下载" onclick="downQRCode()" class="tools_btn">
                <span>
                    <b class="btn-down">下载</b>
                </span>
        </a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/qrCode/deleteQRCode">
        <a id="lr-delete" title="删除(Ctrl+S)" onclick="deleteQRCode()" class="tools_btn">
                <span>
                    <b class="btn-delete">删除</b>
                </span>
        </a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/qrCode/qrCodeDetail">
        <a id="lr-detail" title="明细" onclick="qrCodeDetail()" class="tools_btn">
                <span>
                    <b class="btn-update">明细</b>
                </span>
        </a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/qrCode/back">
        <div class="tools_separator"></div>
        <a id="lr-leave" title="关闭当前窗口(Esc)" onclick="btn_back()" class="tools_btn">
                <span>
                    <b class="btn-back">离开</b>
                </span>
        </a>
    </@shiro.hasPermission>
    </div>
</div>
<div class="rightline" >
    <div class="bottomline QueryArea" >
        <table border="0" class="form-find" >
            <tr id="selectParameter">
                <td>
                    二维码编码：<input id="txtQRCode" name="qrCode" type="text" class="input-txt"
                                 style="width: 100px" onkeyup="this.value=this.value.replace(/\D/g,'')"
                                 onblur="this.value=this.value.replace(/\D/g,'')" maxlength="10">
                </td>
                <td>
                    <input id="qrCode-Search" type="button" class="btnSearch" value="查 询">
                    <input id="qrCode-Clear" type="button" class="btnSearch" value="重 置">
                </td>
            </tr>
        </table>
    </div>
    <table id="gridTable"></table>
    <div id="gridPager"></div>
</div>



<@shiro.hasPermission name="/qrCode/addQRCode">
<!--添加二维码编码-->
<div id="addQRCodeModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">添加二维码编码</h4>
    </div>
    <div class="modal-body">
        <form id="addQRCodeForm" style="margin: 1px">
            <table class="form">
                <tr id="addQRCodeTR">
                    <th class="formTitle" style="width: 200px">新增二维码数量：
                    </th>
                    <td class="formValue" colspan="3">
                        <input type="hidden" name="resourceId">
                        <input id="addQRCodeIntervalNumber" name="intervalNumber" type="text" class="txt required" maxlength="10"
                               onkeyup="this.value=this.value.replace(/\D/g,'')"
                               onblur="this.value=this.value.replace(/\D/g,'')" />
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="addQRCode" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>

<@shiro.hasPermission name="/qrCode/deleteQRCode">
<#--删除确认-->
<div id="deleteQRCodeDiv" class="modal fade " data-width="400" tabindex="-1" aria-hidden="true"
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
        <button id="deleteQRCode" type="button" class="btn green">确定</button>
    </div>
</div>
</@shiro.hasPermission>

<@shiro.hasPermission name="/qrCode/qrCodeDetail">
<#--明细-->
<div id="qrCodeDetailModal" class="modal fade " data-width="880" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">明细</h4>
    </div>
    <div class="modal-body">
        <form id="qrCodeDetailForm" style="margin: 1px">

        </form>
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>

<script>
    var resourceId;
    $(document).ready(function () {
        GetGrid();
        gridPagerStyle();
        resourceId= top.$("#ModuleId").val();
    });

    //加载表格
    function GetGrid() {
        $("#gridTable").jqGrid({
            url: "${request.contextPath}/qrCodeInterval/GridJson.json",
            datatype: "json",
            height: $(window).height() - 150,
            autowidth: true,
            colModel: [
                {label: '主键', name: 'intervalId', index: 'intervalId', width: 100, hidden: true },
                {label: "二维码编码区间", name: "startCode", index: "startCode", width: 180,
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue+"-"+rowObject.endCode;
                    }
                },
                {label: "生成量", name: "intervalNumber", index: "intervalNumber", width: 120 },
                {label: "创建时间", name: "createDate", index: "createDate", width: 150},
                {label: "创建人", name: "createUserName", index: "createUserName", width: 100},
                {label: '下载状态', name: 'downloadStatus', index: 'downloadStatus', width: 100,
                    formatter: function (cellvalue, options, rowObject) {
                        if (1===cellvalue){
                            return "未下载";
                        }else if (2===cellvalue){
                            return "已下载";
                        }else {
                            return "";
                        }
                    }
                }
            ],
            pagerpos : "right",
            recordpos : "left",
            viewrecords: true,
            rownumbers: true,
            rowNum: 15,
            rowList: [15, 30, 50, 100, 500],
            pager: "#gridPager",
            jsonReader:{
                root:"root",page:"page",total:"total",records:"records"
            },
            shrinkToFit: false,
            gridview: true
        });
    }
    //分页布局
    function gridPagerStyle() {
        var width = document.getElementById("gridPager_right").offsetWidth+5;
        $("#gridPager_right").attr("style","width:"+width);
    }

    <@shiro.hasPermission name="/qrCode/downQRCode">
    //下载
    function downQRCode() {
        let id = GetJqGridRowValue("#gridTable", "intervalId");
        if (IsChecked(id)) {
            window.open("${request.contextPath}/qrCode/downQRCode.json?intervalId="+id+"&resourceId="+resourceId, "_blank");
        }
        $("#gridTable").trigger("reloadGrid");
    }
    </@shiro.hasPermission>

    <@shiro.hasPermission name="/qrCode/addQRCode">
    <!--添加二维码编码-->
    function addQRCode() {
        $("#addQRCodeModal").modal("show");
    }
    $("#addQRCode").unbind("click");
    $("#addQRCode").on("click",function () {
        Loading(true,"正在提交数据，请耐心等待...","#addQRCodeModal");
        let num = $.trim($("#addQRCodeIntervalNumber").val());
        $("#addQRCodeForm input[name=resourceId]").val(resourceId);
        if (""!=num){
            $.post('${request.contextPath}/qrCode/addQRCode.json', $("#addQRCodeForm").serialize(), function (result) {
                if (result.success) {
                    $("#gridTable").trigger("reloadGrid");
                    tipDialog(result.msg, 4, '1');
                    $("#addQRCodeModal").removeData("bs.modal");
                    $("#addQRCodeModal").modal('hide');
                } else {
                    tipDialog(result.msg, 4, '0');
                }
                Loading(false,"","#addQRCodeModal");
            }, "JSON");
        }else {
            tipDialog("请输入要生成的二维码数量！", 4, 'wa');
            Loading(false,"","#addQRCodeModal");
        }
    });
    </@shiro.hasPermission>

    <@shiro.hasPermission name="/qrCode/deleteQRCode">
    //删除
    function deleteQRCode() {
        let id = GetJqGridRowValue("#gridTable", "intervalId");
        if (IsChecked(id)) {
            $("#deleteQRCodeDiv").modal("show");
        }
    }
    $("#deleteQRCode").unbind("click");
    $("#deleteQRCode").click(function () {
        Loading(true, "正在删除数据...","#deleteQRCodeModal");
        let id = GetJqGridRowValue("#gridTable", "intervalId");
        $.post('${request.contextPath}/qrCodeInterval/deleteQrCodeIntervalBy.json',{intervalId: id,resourceId:resourceId},function (result) {
            if(result.success){
                $("#gridTable").trigger("reloadGrid");
                tipDialog(result.msg, 4, '1');
                $("#deleteQRCodeDiv").modal("hide");
            }else {
                tipDialog(result.msg, 4, '0');
                $("#deleteQRCodeDiv").modal("hide");
            }
            Loading(false,"","#deleteQRCodeModal");
        }, "JSON");
    });
    </@shiro.hasPermission>

    <@shiro.hasPermission name="/qrCode/qrCodeDetail">
        //详情
    var qrCodeId;
        function qrCodeDetail() {
            let id = GetJqGridRowValue("#gridTable", "intervalId");
            if (IsChecked(id)) {
                qrCodeId = id;
                $("#qrCodeDetailModal").modal({
                    remote: "${request.contextPath}/qrCode/qrCodeDetailModal.htm?intervalId="+id
                });
                $('#qrCodeDetailModal').on('loaded.bs.modal', function (e) {
                    $("#qrCode-gridTable").jqGrid({
                        url: "${request.contextPath}/qrCode/GridJson.json?intervalId="+id,
                        datatype: "json",
                        height: 400,
                        autowidth: true,
                        colModel: [
                            {label: '主键', name: 'qrCodeId', index: 'qrCodeId', width: 100, hidden: true },
                            {label: "二维码编码", name: "qrCode", index: "qrCode", width: 120 },
                            {label: "绑定次数", name: "useNumber", index: "useNumber", width: 70 },
                            {label: "当前状态", name: "useStatus", index: "useStatus", width: 80,
                                formatter: function (cellvalue, options, rowObject) {
                                    if (1===cellvalue){ return "未绑定";
                                    }else if (2===cellvalue){ return "已绑定";
                                    }else { return ""; }
                                }
                            },
                            {label: '生产任务主键', name: 'currentProduceTaskId', index: 'currentProduceTaskId', hidden: true },
                            {label: "绑定生产任务编号", name: "currentProduceTaskCode", index: "currentProduceTaskCode", width: 160},
                            {label: '生产任务', name: 'currentProduceTaskName', index: 'currentProduceTaskName', width: 100 },
                            {label: "绑定时间", name: "bindDate", index: "bindDate", width: 120},
                            {label: '经手人主键', name: 'bindUserId', index: 'bindUserId', hidden: true },
                            {label: "经手人", name: "bindUserName", index: "bindUserName", width: 80}
                        ],
                        pagerpos : "right",
                        recordpos : "left",
                        viewrecords: true,
                        rownumbers: true,
                        rowNum: 10,
                        rowList: [10, 30, 50, 100, 500],
                        pager: "#qrCode-gridPager",
                        jsonReader:{
                            root:"root",page:"page",total:"total",records:"records"
                        },
                        shrinkToFit: false,
                        gridview: true
                    });
                    let width = document.getElementById("qrCode-gridPager_right").offsetWidth+5;
                    $("#qrCode-gridPager_right").attr("style","width:"+width);
                    $("#qrCodeUse-gridTable").jqGrid({
                        url: "${request.contextPath}/qrCodeUse/GridJson.json",
                        datatype: "json",
                        height: 400,
                        autowidth: true,
                        colModel: [
                            {label: "主键", name: "qrCodeUseId", index: "qrCodeUseId", hidden: true },
                            {label: "二维码编码", name: "qrCode", index: "qrCode", width: 120 },
                            {label: "生产任务主键", name: "produceTaskId", index: "produceTaskId", hidden: true },
                            {label: "绑定生产任务编号", name: "produceTaskCode", index: "produceTaskCode", width: 160},
                            {label: "生产任务", name: "produceTaskName", index: "produceTaskName", width: 80 },
                            {label: "绑定时间", name: "bindDate", index: "bindDate", width: 120},
                            {label: "经手人主键", name: "bindUserId", index: "bindUserId", hidden: true },
                            {label: "经手人", name: "bindUserName", index: "bindUserName", width: 80},
                            {label: "解绑时间", name: "unBindDate", index: "unBindDate", width: 120},
                            {label: "经手人主键", name: "unBindUserId", index: "unBindUserId", hidden: true },
                            {label: "经手人", name: "unBindUserName", index: "unBindUserName", width: 80}
                        ],
                        pagerpos : "right",
                        recordpos : "left",
                        viewrecords: true,
                        rownumbers: true,
                        rowNum: 10,
                        rowList: [10, 30, 50, 100, 500],
                        pager: "#qrCodeUse-gridPager",
                        jsonReader:{
                            root:"root",page:"page",total:"total",records:"records"
                        },
                        shrinkToFit: false,
                        gridview: true
                    });
                    width = document.getElementById("qrCodeUse-gridPager_right").offsetWidth+5;
                    $("#qrCodeUse-gridPager_right").attr("style","width:"+width);
                });
            }
        }
    </@shiro.hasPermission>

    $("#qrCode-Search").unbind("click");
    $("#qrCode-Search").click(function () {
        Loading(true, "正在提交数据...");
        var txtQRCode = $("#txtQRCode").val();
        if(!(/^\d{10}$/.test(txtQRCode))){
            tipDialog("二维码编码不满10位!", 4, '0');
        }else {
            $("#gridTable").jqGrid("setGridParam", {
                postData: {qrCode: txtQRCode},
                url: "${request.contextPath}/qrCodeInterval/GridJson.json",
                page: 1
            }).trigger("reloadGrid");
        }
        Loading(false);
    });


    $("#qrCode-Clear").unbind("click");
    $("#qrCode-Clear").click(function () {
        Loading(true, "正在提交数据...");
        //重置表格
        $("#txtQRCode").val("");
        $("#gridTable").jqGrid("setGridParam",{
            postData: {qrCode: ""},
            url:"${request.contextPath}/qrCodeInterval/GridJson.json",
            page:1
        }).trigger("reloadGrid");
        Loading(false);
    });
</script>


