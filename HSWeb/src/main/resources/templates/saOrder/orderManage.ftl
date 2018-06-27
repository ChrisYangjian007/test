<div>
    <div class="layoutPanel layout-center"
         style="position: absolute; z-index: 99; height: 100%; width: 100%;overflow: auto;">
        <div class="tools_bar" style="border-top: none; margin-bottom: 0px;">
            <div class="PartialButton">
                <a id="lr-replace" title="刷新当前(Ctrl+Q)" onclick="Replace()" class="tools_btn">
                <span>
                <b class="btn-reload">刷新</b>
                </span>
                </a>
                <div class="tools_separator"></div>

            <@shiro.hasPermission name="/order/downOrder">
                <a title="订单下载" onclick="downOrder()" class="tools_btn">
                <span>
                    <b class="btn-down">订单下载</b>
                </span>
                </a>
                <div class="tools_separator"></div>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/order/detailOrder">
                <a title="查看订单详情" onclick="orderDetail()" class="tools_btn">
                            <span>
                                <b class="btn-detail">查看订单详情</b>
                            </span>
                </a>
                <div class="tools_separator"></div>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/order/toOrderCount">
                <a title="当日订单统计" onclick="countToday()" class="tools_btn">
                            <span>
                                <b class="btn-update">当日订单统计</b>
                            </span>
                </a>
                <div class="tools_separator"></div>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/order/importOrder">
                <a title="打包导入" onclick="packOrder()" class="tools_btn">
                            <span>
                                <b class="btn-import">打包导入</b>
                            </span>
                </a>
                <div class="tools_separator"></div>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/order/refresh">
                <a title="订单更新" onclick="refreshOrder()" class="tools_btn">
                            <span>
                                <b class="btn-reload">订单更新</b>
                            </span>
                </a>
                <div class="tools_separator"></div>
            </@shiro.hasPermission>
                <a id="lr-leave" title="关闭当前窗口(Esc)" onclick="btn_back()" class="tools_btn">
                <span>
                    <b class="btn-back">离开</b>
                </span>
                </a>
            </div>
        </div>
        <div class="line"></div>
        <ul class="nav nav-tabs ">
            <li class="active">
                <a href="#tab1" id="a1" data-toggle="tab"> 已接受 </a>
            </li>
            <li>
                <a href="#tab2" id="a2" data-toggle="tab"> 已打包 </a>
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
                                订单申请编号：<input id="orderNo" name="produceNo" type="text" class="txt"
                                              style="width: 100px">
                            </td>
                            <td colspan="2">
                                订货日期：
                                <input id="orderDate" name="lastDate" type="text" class="txt Wdate"
                                       style="width: 140px; " onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
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
                                订单申请编号：<input id="orderNo1" name="produceNo" type="text" class="txt"
                                              style="width: 100px">
                            </td>
                            <td colspan="2">
                                订货日期：
                                <input id="orderDate2" name="orderDate1" type="text" class="txt Wdate"
                                       style="width: 140px; " onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
                            </td>
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


<!--当日订单统计-->
<div id="orderCountOrderToday" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">当日订单统计</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="updateWorkFlow" type="button" data-dismiss="modal" class="btn green">确定</button>
    </div>
</div>
//订单更新
<div id="refreshOrderModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">订单更新</h4>
    </div>
    <div class="modal-body">
        请选择订货开始时间：<input id="orderStartDate" name="lastDate" type="text" class="txt Wdate" style="width: 140px; "
                         onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd '})">
        请选择订货结束时间：<input id="orderEndDate" name="lastDate" type="text" class="txt Wdate" style="width: 140px; "
                         onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd '})">
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="refreshOk" type="button" class="btn green">确定</button>
    </div>
</div>
<div id="OrderDownModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">订单导出</h4>
    </div>
    <div class="modal-body">
        请选择订单开始时间：<input id="orderDownStartDate" name="lastDate" type="text" class="txt Wdate" style="width: 140px; "
                         onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd '})">
        请选择订单结束时间：<input id="orderDownEndDate" name="lastDate" type="text" class="txt Wdate" style="width: 140px; "
                         onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd '})">
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="orderDownOk" type="button" class="btn green">确定</button>
    </div>
</div>
<!--订单导入-->
<div id="orderImportModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">订单导入</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="orderImportBtn" type="button" class="btn green">确认</button>
    </div>
</div>

<script type="text/javascript">

    function orderDetail() {
        let orderName;
        if (1 == table) {
            orderName = GetJqGridRowValue("#gridTable", "orderName");
        }
        if (2 == table) {
            orderName = GetJqGridRowValue("#gridTable2", "orderName");
        }
        if (IsChecked(orderName)) {
            AddTabMenu(orderName + 'orderDetailIFrame', '${request.contextPath}/saOrder/orderDetailIFrame.htm?orderName=' + orderName, '订单详情', "application_view_detail.png", 'true', "${staticImg}");
        }
    }
    var resourceId;
    $(document).ready(function () {
        GetGrid();
        GetGrid2();
        gridPagerStyle();
        resourceId= top.$("#ModuleId").val();
    });
    var table = 1;
    $("#a1").unbind("click");
    $("#a1").click(function () {
        table = 1;
    });
    $("#a2").unbind("click");
    $("#a2").click(function () {
        table = 2;
    });
    //已接受
    function GetGrid() {
        $("#gridTable").jqGrid({
            url: "${request.contextPath}/saOrder/GridJson.json",
            datatype: "json",
            height: $(window).height() - 214,
            autowidth: true,
            colModel: [
                {label: "编号", name: "orderId", index: "orderId", width: 80},
                {label: "订单申请编号", name: "orderName", index: "orderName", width: 120},
                {label: "审批状态", name: "orderSpzt", index: "orderSpzt", width: 100},
                {label: "申请人", name: "orderOwnerid", index: "orderOwnerid", width: 80},
                {label: "所属门店", name: "orderSsmd", index: "orderSsmd", width: 150},
                {label: "经销商", name: "orderJxs", index: "orderJxs", width: 150},
                {
                    label: "订货日期", name: "orderDhrq", index: "orderDhrq", width: 150,
                    formatter: function (cellvalue, options, rowObject) {
                        return formatDate(cellvalue, 'yyyy-MM-dd');
                    }
                },
                {label: "收货人", name: "orderShr", index: "orderShr", width: 80},
                {label: "收货地址", name: "currentOperatingProcedures", index: "currentOperatingProcedures", width: 180},
                {label: "联系电话", name: "currentChecker", index: "currentChecker", width: 100}
            ],
            pagerpos: "right",
            recordpos: "left",
            viewrecords: true,
            rowNum: 15,
            rowList: [15, 30, 50, 100, 500],
            pager: "#gridPager",
            jsonReader: {
                root: "root", page: "page", total: "total", records: "records"
            },
            sortname: 'listIndex',
            sortorder: 'desc',
            shrinkToFit: false,
            gridview: true
        });
    }

    //已打包
    function GetGrid2() {
        $("#gridTable2").jqGrid({
            url: "${request.contextPath}/saOrder/GridJson1.json",
            datatype: "json",
            height: $(window).height() - 214,
            aotowidth: true,
            colModel: [
                {label: "编号", name: "orderId", index: "orderId", width: 80},
                {label: "订单申请编号", name: "orderName", index: "orderName", width: 120},
                {label: "审批状态", name: "orderSpzt", index: "orderSpzt", width: 100},
                {label: "申请人", name: "orderOwnerid", index: "orderOwnerid", width: 80},
                {label: "所属门店", name: "orderSsmd", index: "orderSsmd", width: 150},
                {label: "经销商", name: "orderJxs", index: "orderJxs", width: 150},
                {
                    label: "订货日期", name: "orderDhrq", index: "orderDhrq", width: 150,
                    formatter: function (cellvalue, options, rowObject) {
                        return formatDate(cellvalue, 'yyyy-MM-dd 00:00:00');
                    }
                },
                {label: "收货人", name: "orderShr", index: "orderShr", width: 80},
                {label: "收货地址", name: "currentOperatingProcedures", index: "currentOperatingProcedures", width: 180},
                {label: "联系电话", name: "currentChecker", index: "currentChecker", width: 100}
            ],
            pagerpos: "right",
            recordpos: "left",
            viewrecords: true,
            rowNum: 15,
            rowList: [15, 30, 50, 100, 500],
            pager: "#gridPager2",
            jsonReader: {
                root: "root", page: "page", total: "total", records: "records"
            },
            sortname: 'listIndex',
            sortorder: 'desc',
            shrinkToFit: false,
            gridview: true

        });
    }

    //分页布局
    function gridPagerStyle() {
        var width = document.getElementById("gridPager_right").offsetWidth + 5;
        $("#gridPager_right").attr("style", "width:" + width);
        width = document.getElementById("gridPager2_right").offsetWidth + 5;
        $("#gridPager2_right").attr("style", "width:" + width);
    }

    //查询已接受
    $("#btnSearch").unbind("click");
    $("#btnSearch").click(function () {
        var orderNo = $("#orderNo").val();
        var orderDate = $("#orderDate").val();
        var postData = {orderName: orderNo, orderDate: orderDate};
        $("#gridTable").jqGrid("setGridParam", {
            postData: postData,
            url: "${request.contextPath}/saOrder/GridJson.json",
            page: 1
        }).trigger("reloadGrid");
    });
    $("#refreshOk").unbind("click");
    $("#refreshOk").click(function () {
        var startDate = $("#orderStartDate").val();
        var endDate = $("#orderEndDate").val();

        if (startDate == null || startDate == "") {
            tipDialog("请选择出货开始时间", 4, "warning");
            return false;
        }
        if (endDate == null || endDate == "") {
            tipDialog("请选择出货结束时间", 4, "warning");
            return false;
        }
        Loading(true, "正在更新数据...");
        $.post('${request.contextPath}/saOrder/orderProduct.json', {
            startDate: startDate,
            endDate: endDate,
            resourceId :resourceId
        }, function (result) {
            if (result.success) {
                $("#gridTable").trigger("reloadGrid");
                tipDialog(result.msg, 4, '1');
                $("#refreshOrderModal").modal('hide')
            } else {
                tipDialog(result.msg, 4, '0');
            }
            Loading(false);
        }, "JSON");

    });
    $("#orderDownOk").unbind("click");
    $("#orderDownOk").click(function () {
        var startDate = $("#orderDownStartDate").val();
        var endDate = $("#orderDownEndDate").val();

        if (startDate == null || startDate == "") {
            tipDialog("请选择出货开始时间", 4, "warning");
            return false;
        }
        if (endDate == null || endDate == "") {
            tipDialog("请选择出货结束时间", 4, "warning");
            return false;
        }
        window.open("${request.contextPath}/saOrder/downOrder.htm?startDate=" + startDate + "&&endDate=" + endDate, "_blank");
        $("#OrderDownModal").modal('hide');

    });
    $("#btnClear").unbind("click");
    $("#btnClear").click(function () {
        var produceTaskNo = $("#orderNo").val("");
        var produceTaskName = $("#orderDate").val("");
        var postData = {orderName: "", orderDate: ""};
        $("#gridTable").jqGrid("setGridParam", {
            postData: postData,
            url: "${request.contextPath}/saOrder/GridJson.json",
            page: 1
        }).trigger("reloadGrid");
    });

    //订单统计
    function countToday() {
        $("#orderCountOrderToday").modal({
            remote: "${request.contextPath}/saOrder/orderCountTodayModal.htm"
        });
    }
    //订单更新
    function refreshOrder() {
        $("#refreshOrderModal").modal();
    }
    //订单下载
    function downOrder() {
        $("#OrderDownModal").modal();
    }
    //打包导入
    function packOrder() {
        $("#orderImportModal").modal({
            remote: "${request.contextPath}/saOrder/importOrderModal.htm"
        });
    }
    $("#orderImportBtn").unbind("click");
    $("#orderImportBtn").click(function () {
        Loading(true, "正在对比、导入数据...", "#orderImportModal");
        String.prototype.endWith = function (endStr) {
            var d = this.length - endStr.length;
            return (d >= 0 && this.lastIndexOf(endStr) == d)
        };
        var orderImportFile = $("#orderImportFile").val();
        if ("" == orderImportFile) {
            tipDialog("请选择要导入的订单文件", 4, 'a');
            Loading(false);
            return false;
        } else {
            if (!(orderImportFile.endWith(".txt"))) {
                tipDialog("请上传txt格式的文件!", 4, 'a');
                Loading(false);
                return false;
            }
        }
        var options = {
            type: "post",
            cache: false,
            url: '${request.contextPath}/saOrder/importOrder.json',
            success: function (res) {
                if (res.success) {
                    tipDialog(res.msg, 4, '1');
                    Loading(false, "", "#orderImportModal");
                    $("#gridTable").trigger("reloadGrid");
                    $("#orderImportModal").modal("hide");
                } else {
                    Loading(false, "", "#orderImportModal");
                    tipDialog(res.msg, 4, '1');
//                    $("#qrImportModal").modal("hide");
//                    tipDialog("数据有误，请修改再导入！", 4, '0');
//                    $("#excelErrorTr").removeClass("hidden");
//                    var msg = res.msg;
//                    var error = msg.split("，");
//                    var html = "";
//                    for (var i = 0; i < error.length; i++) {
//                        html += error[i] + "<br/>";
//                    }
//                    $("#excelErrorTd").html(html);
//                    Loading(false);
                }
            },
            error: function (xhr, ajaxOptions, thrownError) {
                tipDialog("请求错误!", 4, '0');
                Loading(false);
            }
        };
        $('#productImportForm').ajaxSubmit(options);

    });
    //查询已打包
    $("#btnSearch2").unbind("click");
    $("#btnSearch2").click(function () {
        var orderNo = $("#orderNo1").val();
        var orderDate = $("#orderDate1").val();
        var postData = {orderName: orderNo, orderDate: orderDate};
        $("#gridTable2").jqGrid("setGridParam", {
            postData: postData,
            url: "${request.contextPath}/saOrder/GridJson1.json",
            page: 1
        }).trigger("reloadGrid");
    });
    $("#btnClear2").unbind("click");
    $("#btnClear2").click(function () {
        var produceTaskNo = $("#orderNo1").val("");
        var produceTaskName = $("#orderDate1").val("");
        var postData = {orderName: "", orderDate: ""};
        $("#gridTable2").jqGrid("setGridParam", {
            postData: postData,
            url: "${request.contextPath}/saOrder/GridJson1.json",
            page: 1
        }).trigger("reloadGrid");
    });
</script>