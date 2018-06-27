<!DOCTYPE html>
<html lang="en" id="addProcedureManageHtml">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${request.contextPath}/css/produce.css">

    <style>
        body {
            overflow: auto;
        }

        hr {
            margin: 0;
        }

        .produce-right {
            border: 1px solid #ccc;
            padding: 5px;
        }

        .produce-left {
            border: 1px solid #ffffff;
            padding: 5px;
        }

        .main-add {
            width: 100%;
            margin: auto;
            border: 1px solid #ddd;
            border-radius: 10px;
            overflow: hidden;
        }

        .add-header {
            text-align: center;
            padding: 10px 0;
        }

        .add-title {
            text-align: center;
            font-size: 0;
            margin: 20px 0;
        }

        .add-title > a {
            color: #000;
            display: inline-block;
            width: 30px;
            height: 30px;
            font-size: 14px;
            border-radius: 50%;
            border: 1px solid #ddd;
            vertical-align: middle;
            position: relative;
        }

        .add-title > a > p {
            white-space: nowrap;
            position: absolute;
            bottom: 0;
            left: 0;
            transform: translate(-25%, 225%);
        }

        .add-title > a > span {
            line-height: 30px;
        }

        .add-title > span {
            display: inline-block;
            height: 3px;
            width: 100px;
            background: #ddd;
        }

        .main-list > h4 {
            padding-left: 30px;
        }

        .main-container {
            display: none;
        }

        .btn-box {
            text-align: right;
            padding: 20px;
        }

        .add-num-active {
            background: #ccc;
        }

        .workProcessError {
            color: #e73d4a;
            border-color: #e73d4a;
            border-right-color: rgb(231, 61, 74);
            background-color: #fbe1e3;
        }
    </style>
</head>
<body>
<div class="tools_bar" style="border-top: none; margin-bottom: 0px;">
    <div class="PartialButton">
        <a id="lr-replace" title="刷新当前(Ctrl+Q)" onclick="Replace()" class="tools_btn">
                            <span>
                                <b class="btn-reload">刷新</b>
                            </span>
        </a>
        <div class="tools_separator"></div>
        <a id="lr-leave" title="关闭当前窗口(Esc)" onclick="btn_back()" class="tools_btn">
                            <span>
                                <b class="btn-back">离开</b>
                            </span>
        </a>
    </div>
</div>
<div class="main-add">
    <h4 class="add-header">增加工序</h4>
    <hr>
    <div class="tipstitle_title settingtable bold bd todayInfoPanelTab rightPanelTitle_normal">
        <div class="tab_list_top" style="position: absolute">
            <div id="TableField1" class="tab_list bd add-num-active">选择产品</div>
            <div id="TableField2" class="tab_list bd">选择工序</div>
        </div>
    </div>
    <div class="procedureTab1">
        <form id="form1" autocomplete="off" style="margin: 1px">
            <div class="bd">
                <div class="ScrollBar" style="margin-top: 1px;">
                    <div id="TableProperty">
                        <table class="form">
                            <tr>
                                <th class="formTitle">
                                    *生产任务：
                                </th>
                                <td class="formValue">
                                    <input id="SeaWorkProcessCName" name="cName" type="text" class="txt required"/>
                                    <input id="SeaWorkProcessId" name="SeaWorkProcessId" type="hidden"/>
                                </td>
                                <th class="formTitle">
                                    生产任务编号：
                                </th>
                                <td class="formValue">
                                    <span id="yhProduceTaskNo"></span>
                                    <input id="yhProduceTaskId" type="hidden"/>
                                </td>
                            </tr>
                            <tr>
                                <th class="formTitle">
                                    出库编号：
                                </th>
                                <td class="formValue">
                                <#if receiveNode??>
                                ${receiveNode}
                                </#if>
                                    <input type="hidden" id="yhLeaveNo"
                                           value="<#if receiveNode??>${receiveNode}</#if>"/>
                                </td>
                                <th class="formTitle">
                                    *出库申请人：
                                </th>
                                <td class="formValue">
                                    <input id="yhLeavePerson" autocomplete="on" type="text" class="txt" style="width: 100%">
                                </td>
                            </tr>
                            <tr>
                                <th class="formTitle">
                                    *经手人：
                                </th>
                                <td class="formValue">
                                    <input id="yhBrokerage" autocomplete="on" type="text" class="txt" style="width: 100%">
                                </td>
                                <th class="formTitle">
                                    出库时间：
                                </th>
                                <td class="formValue">
                                    <input id="yhLeaveDate" type="text" class="txt Wdate" style="width: 100%; "
                                           onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                                </td>
                            </tr>
                            <tr>
                                <th class="formTitle">备注1：</th>
                                <td class="formValue" colspan="3">
                                    <textarea id="yhRemark1" style="width: 100%;height: 50px;"></textarea>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="tipstitle_title settingtable bold bd todayInfoPanelTab rightPanelTitle_normal">
                    <div class="tab_list_top" style="position: absolute">
                        <div id="TabTableField" class="tab_list bd actived">出库详情</div>
                    </div>
                    <div style="float: right;">
                        <div class="tools_bar_icon">
                            <div class="tools_separator" style="height: 23px; margin-right: 5px;"></div>
                            <div class="icon-botton" title="插入行" onclick="InsetTableRow()">
                                <img src="${staticImg}/images/Icon16/table_row_insert.png"/>
                            </div>
                            <div class="icon-botton" title="清空行" onclick="EmptyTableRow()">
                                <img src="${staticImg}/images/Icon16/table_row_delete.png"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="ScrollBar" style="margin-top: 1px;">
                    <div id="TableField" class="tabPanel">
                        <table id="grid_Field" class="grid" style="width: 100%">
                            <thead>
                            <tr>
                                <td style="width: 30px; text-align: center; border-left: none;">
                                    <div class="table-header">序号</div>
                                </td>
                                <td style="width: 80px; text-align: center">
                                    <div class="table-header">仓库</div>
                                </td>
                                <td style="width: 80px; text-align: center;">
                                    <div class="table-header">货物类型</div>
                                </td>
                                <td style="width: 100px; text-align: center;">
                                    <div class="table-header">产品名称</div>
                                </td>
                                <td style="width: 100px; text-align: center;">
                                    <div class="table-header">产品状态</div>
                                </td>
                                <td style="width: 100px; text-align: center;">
                                    <div class="table-header">入库规格</div>
                                </td>
                                <td style="width: 80px; text-align: center;">
                                    <div class="table-header">批次号</div>
                                </td>
                                <td style="width: 100px; text-align: center;">
                                    <div class="table-header">库存数量</div>
                                </td>
                                <td style="width: 100px; text-align: center;">
                                    <div class="table-header">*出库数量</div>
                                </td>
                                <td style="width: 100px; text-align: center;">
                                    <div class="table-header">单位</div>
                                </td>
                                <td style="width: 100px; text-align: center;">
                                    <div class="table-header">是否损耗</div>
                                </td>
                                <td style="width: 100px; text-align: center;">
                                    <div class="table-header">备注2</div>
                                </td>
                            </tr>
                            </thead>
                            <tbody></tbody>
                        </table>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <div class="procedureTab2 hidden">
        <div class="add-title" id="workProcessListText">
        <#--<#if workProcessList??>-->
                <#--<#list workProcessList as workProcess>-->
                    <#--<a class="add-num workProcessList" id="workProcessListId${workProcess.workProcessId}">-->
                        <#--<span>${workProcess_index+1}</span>-->
                        <#--<p>${workProcess.CName}</p>-->
                    <#--</a>-->
                    <#--<span class="hrClass"></span>-->
                <#--</#list>-->
              <#--</#if>-->
        </div>
        <div id="formAttributeClass">
        <#--<#if workProcessList??>-->
                <#--<#list workProcessList as workProcess>-->
                <#--<div class="main-container container produce-box record" id="formAttrbuteList${workProcess.workProcessId}">-->
                <#--</div>-->
                <#--</#list>-->
            <#--</#if>-->
        </div>
    </div>
    <hr>
    <div class="btn-box procedureTab1">
        <a class="btn btn-success" onclick="procedureNext()">下一步</a>
    </div>
    <div class="btn-box procedureTab2 hidden">
        <a class="btn btn-default procedureBtn1">上一步</a>
        <a class="btn btn-success procedureBtn2">下一步</a>
        <a class="btn btn-success procedureBtb3 hidden" onclick="addProcedureManage()">确定</a>
    </div>
</div>

<div id="workProcessNameModal" class="modal fade " data-width="780" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">生产任务</h4>
    </div>
    <div class="modal-body" id="SeaWorkProcessNameModalBody">
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="addWorkProcessName" type="button" class="btn green" disabled onclick="addWorkFlowName()">确定</button>
    </div>
</div>

<script>
    var shiroUserId = 0;
    var shrioUserName;
    <#if  shiroUser??>
    shiroUserId =${shiroUser.id!};
    shrioUserName = "${shiroUser.name!}";
    </#if>
    var indexNum = -1;
    var indexMax = 0;

    $(function () {
        leaveStockModalReload();
        TableTdEventAddProcess();
        getStockIdsByStock();
    });

    function loadProductAddProcess(warehouseId, typeId, productId, statusId, specId, batchNo, unitName, unitId, weight) {
        var warehouseEl = $("" + warehouseId);//仓库
        var typeEl = $("" + typeId);//货物类型
        var productEl = $("" + productId);//产品名称
        var statusEl = $("" + statusId);//产品状态
        var specEl = $("" + specId);//入库规格
        var batchEl = $("" + batchNo);//批次号
        var unitEl = $("" + unitName);//单位
        var unitIdEl = $("" + unitId);//单位Id
        var weightEl = $("" + weight);//库存数量

        $.post("${request.contextPath}/stock/getGoodsTypeByWarehouseId.json", {warehouseId: warehouseId}, function (res) {
            if (res.success) {
                typeEl.find("option").remove();
                var obj = res.obj;
                for (i in obj) {
                    typeEl.append("<option value='" + obj[i].isMaterial + "'>" + obj[i].materialName + "</option>")
                }
            }
        }, "JSON");

        typeEl.unbind("click");
        typeEl.on("click", function () {
            productEl.prev("div").text("");
            statusEl.prev("div").text("");
            specEl.prev("div").text("");
            batchEl.prev("div").text("");
            unitEl.empty();
            unitIdEl.empty();
            weightEl.empty();
            if (typeEl.val() == "") {
                productEl.find("option").remove();
            } else {
                productEl.find("option").remove();
                var isMaterial = typeEl.val();
                var a = {isMaterial: isMaterial, warehouseId: warehouseId};
                $.ajax({
                    type: "post", // 请求方式
                    url: "${request.contextPath}/stock/getProduct.json", //url地址
                    data: JSON.stringify(a), //数据
                    contentType: "application/json;charset=utf-8;",
                    dataType: "json",
                    success: function (res) {
                        if (res.success) {
                            productEl.find("option").remove();
                            var obj = res.obj;
                            for (i in obj) {
                                productEl.append("<option value='" + obj[i].productId + "'>" + obj[i].productName + "</option>")
                            }
                        }
                    }
                });
            }
        });

        productEl.unbind("click");
        productEl.on("click", function () {
            statusEl.prev("div").text("");
            specEl.prev("div").text("");
            batchEl.prev("div").text("");
            unitEl.empty();
            unitIdEl.empty();
            weightEl.empty();
            if (productEl.val() == "") {
                statusEl.find("option").remove();
            } else {
                statusEl.find("option").remove();
                var isMaterial = typeEl.val();
                var productId = productEl.val();
                var a = {isMaterial: isMaterial, warehouseId: warehouseId, productId: productId};
                $.ajax({
                    type: "post", // 请求方式
                    url: "${request.contextPath}/stock/getProductStatus.json", //url地址
                    data: JSON.stringify(a), //数据
                    contentType: "application/json;charset=utf-8;",
                    dataType: "json",
                    success: function (res) {
                        if (res.success) {
                            statusEl.find("option").remove();
                            var obj = res.obj;
                            for (i in obj) {
                                statusEl.append("<option value='" + obj[i].productStatus + "'>" + obj[i].productStatusName + "</option>")
                            }
                        }
                    }
                });
            }
        });

        statusEl.unbind("click");
        statusEl.on("click", function () {
            specEl.prev("div").text("");
            batchEl.prev("div").text("");
            unitEl.empty();
            unitIdEl.empty();
            weightEl.empty();
            if (statusEl.val() == "") {
                specEl.find("option").remove();
            } else {
                specEl.find("option").remove();
                var isMaterial = typeEl.val();
                var productId = productEl.val();
                var productStatus = statusEl.val();
                var a = {
                    isMaterial: isMaterial,
                    warehouseId: warehouseId,
                    productId: productId,
                    productStatus: productStatus
                };
                $.ajax({
                    type: "post", // 请求方式
                    url: "${request.contextPath}/stock/getProductSpec.json", //url地址
                    data: JSON.stringify(a), //数据
                    contentType: "application/json;charset=utf-8;",
                    dataType: "json",
                    success: function (res) {
                        if (res.success) {
                            specEl.find("option").remove();
                            var obj = res.obj;
                            for (i in obj) {
                                specEl.append("<option value='" + obj[i].productSpecName + "'>" + obj[i].productSpecName + "</option>")
                            }
                        }
                    }
                });
            }
        });

        specEl.unbind("click");
        specEl.on("click", function () {
            batchEl.prev("div").text("");
            unitEl.empty();
            unitIdEl.empty();
            weightEl.empty();
            if (specEl.val() == "") {
                batchEl.find("option").remove();
            } else {
                batchEl.find("option").remove();
                var isMaterial = typeEl.val();
                var productId = productEl.val();
                var productStatus = statusEl.val();
                var productSpecId = specEl.val();
                var a = {
                    isMaterial: isMaterial,
                    warehouseId: warehouseId,
                    productId: productId,
                    productStatus: productStatus,
                    productSpecName: productSpecId
                };
                $.ajax({
                    type: "post", // 请求方式
                    url: "${request.contextPath}/stock/getProductBatch.json", //url地址
                    data: JSON.stringify(a), //数据
                    contentType: "application/json;charset=utf-8;",
                    dataType: "json",
                    success: function (res) {
                        if (res.success) {
                            batchEl.find("option").remove();
                            var obj = res.obj;
                            for (i in obj) {
                                batchEl.append("<option value='" + obj[i].batchNo + "'>" + obj[i].batchNo + "</option>")
                            }
                        }
                    }
                });
            }
        });

        batchEl.unbind("click");
        batchEl.on("click", function () {
            unitEl.empty();
            unitIdEl.empty();
            weightEl.empty();
            if (batchEl.val() == "") {
                unitEl.find("option").remove();
            } else {
                unitEl.find("option").remove();
                var isMaterial = typeEl.val();
                var productId = productEl.val();
                var productStatus = statusEl.val();
                var productSpecId = specEl.val();
                var batchNo = batchEl.val();
                var a = {
                    isMaterial: isMaterial,
                    warehouseId: warehouseId,
                    productId: productId,
                    productStatus: productStatus,
                    productSpecName: productSpecId,
                    batchNo: batchNo
                };
                $.ajax({
                    type: "post", // 请求方式
                    url: "${request.contextPath}/stock/getOthers.json", //url地址
                    data: JSON.stringify(a), //数据
                    contentType: "application/json;charset=utf-8;",
                    dataType: "json",
                    success: function (res) {
                        if (res.success) {
                            weightEl.empty();
                            unitEl.empty();
                            unitIdEl.empty();
                            let obj = res.obj;
                            weightEl.append(obj.weight);
                            unitEl.append(obj.unitName);
                            unitIdEl.append(obj.unitId);
                            unitIdEl.addClass("hidden");
                        }
                    }
                });
            }
        });
    }

    //创建行
    function CreateTableRowAddProcess(index) {
        var tr = '';
        tr += '<tr class="leaveAddTr">';
        tr += '<td class="td-div" style="width: 30px; text-align: center;border-left: none;">' + index + '</td>';
        tr += '<td style="width: 50px; text-align: center;"><div></div>' + IsWarehouseReload(index) + '</td>';//仓库
        tr += '<td style="width: 60px; text-align: center;"><div></div>' + IsMaterialReload(index) + '</td>';//货物类型
        tr += '<td style="width: 60px; text-align: center;"><div></div>' + IsProductReload(index) + '</td>';//产品名称
        tr += '<td style="width: 60px; text-align: center;"><div></div>' + IsProductStatusReload(index) + '</td>';//产品状态
        tr += '<td style="width: 60px; text-align: center;"><div></div>' + IsProductSpecReload(index) + '</td>';//入库规格
        tr += '<td style="width: 60px; text-align: center;"><div></div>' + IsBatchNoReload(index) + '</td>';//批次号
        tr += '<td style="width: 60px; text-align: center;">' + IsWeightReload(index) + '</td>';//库存数量
        tr += '<td style="width: 50px; text-align: center;"><div></div><input id="outWeight' + index + '" type="text" maxlength="7" class="txt" name ="outWeight"/></td>';
        tr += '<td style="width: 60px; text-align: center;">' + IsUnitReload(index) + '</td>';//单位
        tr += '<td style="width: 60px; text-align: center;"><div></div>' + IsLossReload(index) + '</td>';//损耗
        tr += '<td style="width: 60px; text-align: center;"><div></div><input type="text" maxlength="10"  class="txt remark' + index + '" name =""/></td>';//备注2
        tr += '</tr>';
        return tr;
    }

    //仓库
    function IsWarehouseReload(index) {
        var html = '<select id="warehouse' + index + '" class="txtselect" datacol="no" type="select" checkexpession="NotNull">';
        html += '</select>';
        return html;
    }

    //货物类型
    function IsMaterialReload(index) {
        var html = '<select id="goodsType' + index + '" class="txtselect" datacol="no" type="select" checkexpession="NotNull">';
        html += '</select>';
        return html;
    }

    //产品名称
    function IsProductReload(index) {
        var html = '<select id="productName' + index + '" class="txtselect buyaogaiwo" name ="productName"  datacol="no" type="select" err="产品名称" checkexpession="NotNull">';
        html += '</select>';
        return html;
    }

    //产品状态
    function IsProductStatusReload(index) {
        var html = '<select id="productStatus' + index + '" class="txtselect" name ="productStatus"  datacol="no" type="select" checkexpession="NotNull">';
        html += '</select>';
        return html;
    }

    //入库规格
    function IsProductSpecReload(index) {
        var html = '<select id="productSpecName' + index + '" class="txtselect" name ="productSpecName"  datacol="no" type="select" err="规格" checkexpession="NotNull">';
        html += '</select>';
        return html;
    }

    //批次号
    function IsBatchNoReload(index) {
        var html = '<select id="batchNo' + index + '" class="txtselect" name ="batchNo"  datacol="no" type="select" checkexpession="NotNull">';
        html += '</select>';
        return html;
    }

    //库存数量
    function IsWeightReload(index) {
        var html = '<div id="weight' + index + '"></div>';
        return html;
    }

    //单位
    function IsUnitReload(index) {
        var html = '<div id="unitName' + index + '"></div><div id="unitId' + index + '"></div>';
        return html;
    }

    //损耗
    function IsLossReload(index) {
        var html = '<select id="loss' + index + '" class="txtselect" name ="loss"  datacol="no" type="select" checkexpession="NotNull">';
        html += '</select>';
        return html;
    }

    //表格点击事件
    function TableTdEventAddProcess() {
        $(".grid").off("click");
        $(".grid").on("click", ".td-div", function () {
            if ($(this).parent().hasClass("selected")) {
                $(this).parent().removeClass("selected");
            } else {
                $(this).parent().addClass("selected");
            }
        });
        let grid = $("#grid_Field tbody");
        grid.off("click");
        grid.on("click", "td", function () {
            if ($(this).find('div').length > 0) {
                var oldText = $(this).find('div').text();
                var obj = $(this).find('input,select');
                if (obj.attr('disabled')) {
                    return false;
                }
                var type = obj.attr('class');
                obj.show();
                obj.parent().find('div').html("");
                obj.trigger("focus");
                obj.blur(function () {
                    if (obj.parent().find('div').length > 0) {
                        var type = obj.attr('type');
                        var objclass = (obj.parent().find('input[type=text]').length > 0) ? obj.parent().find('input[type=text]').attr('class') : "";
                        var newText = "";
                        switch (type) {
                            case "select":
                                newText = obj.find("option:selected").text();
                                if (obj.parent().find('input[type=hidden]').length > 0) {
                                    if (obj.parent().find('input[type=hidden]').attr("class") != "buyaogaiwo") {
                                        obj.parent().find('input[type=hidden]').val(newText);
                                    }
                                }
                                obj.hide();
                                obj.parent().find('div').html(newText);
                                break;
                            default:
                                if (objclass != "txt icontree") {
                                    newText = obj.val();
                                    obj.hide();
                                    obj.parent().find('div').html(newText);
                                }
                                break;
                        }
                    }
                });
            }
        });
    }

    //默认创建一行
    function leaveStockModalReload() {
        //选中回显
        $("#grid_Field tbody").html(CreateTableRowAddProcess(1));
        $("#grid_Field tbody tr").find('select,input').attr('disabled', 'disabled').hide();
        $("#grid_Field tbody tr:eq(0)").find('select,input').removeAttr('disabled');
        initSearchAddProcess(1);
    }

    //select相互关联
    function initSearchAddProcess(num, isMaterial) {
        var warehouseId =${seaCucumberWarehouse.warehouseId!};
        var warehouseName = "${seaCucumberWarehouse.CName!}";
        $("#warehouse" + num).append("<option value='" + warehouseId + "' selected disabled>" + warehouseName + "</option>");
        $("#warehouse" + num).prev("div").text(warehouseName);
        //获取损耗类型
        $.post("${request.contextPath}/stock/getLoss.json", "", function (res) {
            $("#loss" + num).find("option").remove();
            var obj = res.obj;
            for (i in obj) {
                $("#loss" + num).append("<option value='" + obj[i].cname + "'>" + obj[i].cname + "</option>")
            }
            $("#loss" + num).val("否");
        }, "JSON");
        if (isMaterial == null || isMaterial == "") {
            loadProductAddProcess(warehouseId, "#goodsType" + num, "#productName" + num, "#productStatus" + num, "#productSpecName" + num, "#batchNo" + num, "#unitName" + num, "#unitId" + num, "#weight" + num);
        }
        $("#outWeight" + num).keyup(function () {
            var tmptxt = $(this).val();
            $(this).val(tmptxt.replace(/[^\d.]/g, ""));
        }).bind("paste", function () {
            var tmptxt = $(this).val();
            $(this).val(tmptxt.replace(/[^\d.]/g, ""));
        }).css("ime-mode", "disabled");
    }

    //增加工序下一步
    $(".procedureBtn2").click(function () {
        var workProcedureId = $(".add-num-active").last().attr("id").replace("workProcessListId", "");
        if (getRestricitveMsg(workProcedureId) == false) {
            return false;
        } else {
            indexNum < indexMax && indexNum++;
            $(".add-num").eq(indexNum - 1).addClass("add-num-active");
            $(".main-container").hide();
            $(".container").eq(indexNum - 1).show();
            if (indexNum > (indexMax - 1)) {
                $(".procedureBtn2").addClass("hidden");
                $(".procedureBtb3").removeClass("hidden");
            }
        }
    });
    //增加工序上一步
    $(".procedureBtn1").click(function () {
        if (indexNum == 1) {
            $(".procedureTab2").addClass("hidden");
            $("#TableField2").removeClass("add-num-active");
            $(".procedureTab1").removeClass("hidden");
            $("#TableField1").addClass("add-num-active");
        } else {
            indexNum > 1 && indexNum--;
            $(".add-num").eq(indexNum).removeClass("add-num-active");
            $(".main-container").hide();
            $(".container").eq(indexNum - 1).show();
        }
        if (indexNum < indexMax) {
            $(".procedureBtb3").addClass("hidden");
            $(".procedureBtn2").removeClass("hidden");
        }
    });

    //选择产品下一步
    function procedureNext() {
        Loading(true, "正在加载...", "#addProcedureManageHtml");
        if (indexNum != -1) {
            var boo = true;
            if ($("#SeaWorkProcessCName").val() != "") {
            } else {
                boo = false;
                tipDialog("生产任务不能为空！", 3, 'warning');
                return;
            }
            if (boo) {
                if ($('#yhProduceTaskNo').text() != "") {
                    boo = true
                } else {
                    boo = false;
                    tipDialog("生产任务编号不能为空！", 3, 'warning');
                    return;
                }
            }
            if (boo) {
                if ($.trim($('#yhLeavePerson').val()) != "") {

                } else {
                    boo = false;
                    tipDialog("出库申请人不得为空！", 3, 'warning');
                    Loading(false, "", "#addProcedureManageHtml");
                    return;
                }
            }
            if (boo) {
                if ($.trim($('#yhBrokerage').val()) != "") {

                } else {
                    boo = false;
                    tipDialog("经手人不得为空！", 3, 'warning');
                    Loading(false, "", "#addProcedureManageHtml");
                    return;
                }
            }
            if (boo) {
                if ($.trim($('#yhLeaveDate').val()) != "") {
                } else {
                    boo = false;
                    tipDialog("出库时间不得为空！", 3, 'warning');
                    Loading(false, "", "#addProcedureManageHtml");
                    return;
                }
            }
            if (boo) {
                var rawDataList = new Array();
                var leng = $("#grid_Field tbody>tr").length;
                for (var i = 0; i < leng; i++) {
                    var detail = {
                        warehouseId: "",//仓库id
                        isMaterial: "",//货物类型
                        productName: "",//产品名称
                        productId: "",//产品id
                        productStatus: "",//产品状态id
                        productStatusName: "",//产品状态名称
                        productSpecName: "",//规格名称
                        batchNo: "",//批次号
                        weight: "",//库存数量
                        outWeight: "",//出库数量
                        unitId: "",//单位Id
                        unitName: "",//单位名称
                        loss: "",//损耗
                        remark: ""//备注2
                    }
                    let num = i + 1;
                    let warehouseId = $("#grid_Field tbody>tr").eq(i).find("td:eq(1)").find("div").text();
                    if (warehouseId != "") {
                        detail.warehouseId = $("#grid_Field tbody>tr").eq(i).find("td:eq(1)").find("select").find("option:selected").val();
                    } else {
                        boo = false;
                        tipDialog("第" + num + "行仓库为空！", 3, 'warning');
                        Loading(false, "", "#addProcedureManageHtml");
                        return;
                    }
                    let isMaterial = $("#grid_Field tbody>tr").eq(i).find("td:eq(2)").find("div").text();
                    if (isMaterial != "") {
                        detail.isMaterial = $("#grid_Field tbody>tr").eq(i).find("td:eq(2)").find("select").val();
                    } else {
                        boo = false;
                        tipDialog("第" + num + "行货物类型为空！", 3, 'warning');
                        Loading(false, "", "#addProcedureManageHtml");
                        return;
                    }
                    let productName = $("#grid_Field tbody>tr").eq(i).find("div:eq(2)").text();
                    if (productName != "") {
                        detail.productId = $("#grid_Field tbody>tr").eq(i).find("td:eq(3)").find("select").val();
                        detail.productName = productName;
                    } else {
                        boo = false;
                        tipDialog("第" + num + "行产品为空！", 3, 'warning');
                        Loading(false, "", "#addProcedureManageHtml");
                        return;
                    }
                    let productStatus = $("#productStatus" + num + " option:selected").val();
                    let productStatusName = $("#grid_Field tbody>tr").eq(i).find("div:eq(3)").text();
                    if (productStatusName != "" && productStatus != "") {
                        detail.productStatusName = productStatusName;
                        detail.productStatus = productStatus;
                    } else {
                        boo = false;
                        tipDialog("第" + num + "行产品状态为空！", 3, 'warning');
                        Loading(false, "", "#addProcedureManageHtml");
                        return;
                    }
                    let productSpecName = $("#grid_Field tbody>tr").eq(i).find("div:eq(4)").text();
                    if (productSpecName != "") {
                        detail.productSpecName = productSpecName;
                    } else {
                        boo = false;
                        tipDialog("第" + num + "行规格为空！", 3, 'warning');
                        Loading(false, "", "#addProcedureManageHtml");
                        return;
                    }
                    let batchNo = $("#grid_Field tbody>tr").eq(i).find("div:eq(5)").text();
                    if (batchNo != "") {
                        detail.batchNo = batchNo;
                    } else {
                        boo = false;
                        tipDialog("第" + num + "行批次号为空！", 3, 'warning');
                        Loading(false, "", "#addProcedureManageHtml");
                        return;
                    }
                    let weight = $("#grid_Field tbody>tr").eq(i).find("div:eq(6)").text();
                    if (weight != "") {
                        detail.weight = weight;
                    } else {
                        boo = false;
                        tipDialog("第" + num + "行库存数量为空！", 3, 'warning');
                        Loading(false, "", "#addProcedureManageHtml");
                        return;
                    }
                    let outWeight = $("#grid_Field tbody>tr").eq(i).find("div:eq(7)").text();
                    if (outWeight != "") {
                        if (parseFloat(outWeight) > parseFloat(weight)) {
                            boo = false;
                            tipDialog("第" + num + "行出库数量不能大于库存数量！", 3, 'warning');
                            Loading(false, "", "#addProcedureManageHtml");
                            return;
                        } else {
                            detail.outWeight = outWeight;
                        }
                    } else {
                        boo = false;
                        tipDialog("第" + num + "行出库数量为空！", 3, 'warning');
                        Loading(false, "", "#addProcedureManageHtml");
                        return;
                    }
                    let unitId = $("#grid_Field tbody>tr").eq(i).find("td:eq(9)").find("div:eq(1)").text();
                    if (unitId != "") {
                        detail.unitId = unitId;
                    } else {
                        boo = false;
                        tipDialog("第" + num + "行单位ID为空！", 3, 'warning');
                        Loading(false, "", "#addProcedureManageHtml");
                        return;
                    }
                    let unitName = $("#grid_Field tbody>tr").eq(i).find("td:eq(9)").find("div:eq(0)").text();
                    if (unitName != "") {
                        detail.unitName = unitName;
                    } else {
                        boo = false;
                        tipDialog("第" + num + "行单位为空！", 3, 'warning');
                        Loading(false, "", "#addProcedureManageHtml");
                        return;
                    }
                    let loss = $("#grid_Field tbody>tr").eq(i).find("td:eq(10)").find("div:eq(0)").text();
                    if (loss != "") {
                        detail.loss = loss;
                    } else {
                        boo = false;
                        tipDialog("第" + num + "行是否损耗为空！", 3, 'warning');
                        Loading(false, "", "#addProcedureManageHtml");
                        return;
                    }
                    detail.remark = $("#grid_Field tbody>tr").eq(i).find("td:eq(11)").find("div:eq(0)").text();
                    rawData.push(detail);
                }
            }
            if (boo) {
                $(".procedureTab1").addClass("hidden");
                $("#TableField1").removeClass("add-num-active");
                $(".procedureTab2").removeClass("hidden");
                $("#TableField2").addClass("add-num-active");
                if (indexMax > 1) {
                    $(".procedureBtn2").removeClass("hidden");
                } else {
                    $(".procedureBtn2").addClass("hidden");
                    $(".procedureBtb3").removeClass("hidden");
                }
                Loading(false, "", "#addProcedureManageHtml");
            }
        } else {
            tipDialog("该工艺详情不存在，请先添加工艺详情！", 3, 'warning');
            Loading(false, "", "#addProcedureManageHtml");
            return;
        }
    }

    //创建增加工序模板
    function getFormAttributeList() {
        $(".workProcessList").each(function () {
            var workProcedureId = $(this).attr("id").replace("workProcessListId", "");
            for (let i = 0; i < 3; i++) {
                let handleType = i + 1;
                let url = "${request.contextPath}/stock/getFormAttributeListByHandleType.json?workProcessId=" + workProcedureId + "&&handleType=" + handleType;
                let str = "";
                if (handleType == 1) {
                    str = "操作人记录";
                } else if (handleType == 2) {
                    str = "审核人记录";
                } else if (handleType == 3) {
                    str = "巡检人记录"
                }
                $("#formAttrbuteList" + workProcedureId).append
                ('<div class="record-list formAttrbuteStr' + handleType + workProcedureId + '">' +
                        '<p class="produce-title">' + str + '</p>' +
                        '<input type="hidden" id="handleType' + workProcedureId + '" name="handleType" value="' + handleType + '"/>' +
                        '<input type="hidden" name="workProcessId" value="' + workProcedureId + '"/>' +
                        '</div>');
                $.ajax({
                    url: url,
                    type: "POST",
                    success: function (res) {
                        if (res.success) {
                            var resObj = res.obj;
                            if (resObj != null && resObj.length != 0) {
                                for (let a = 0; a < resObj.length; a++) {
                                    var formAttributeId = "";
                                    var controlType = "";
                                    var dataSourceCode = "";
                                    var dataSourceType = "";
                                    var restrictiveConditions = "";
                                    if (resObj[a].formAttributeId != null) {
                                        formAttributeId = resObj[a].formAttributeId
                                    }
                                    if (resObj[a].controlType != null) {
                                        controlType = resObj[a].controlType
                                    }
                                    if (resObj[a].dataSourceCode != null) {
                                        dataSourceCode = resObj[a].dataSourceCode
                                    }
                                    if (resObj[a].dataSourceType != null) {
                                        dataSourceType = resObj[a].dataSourceType
                                    }
                                    if (resObj[a].restrictiveConditions != null) {
                                        restrictiveConditions = resObj[a].restrictiveConditions;
                                    }
                                    $(".formAttrbuteStr" + resObj[a].handleType + resObj[a].workProcessId).append
                                    ('<div class="col-md-6">' +
                                            '<div class="produce-left" >' + resObj[a].propertyName + '</div>' +
                                            getTempletText(resObj[a], workProcedureId) +
                                            '</div>');
                                    getDataDictionartByCode(formAttributeId, dataSourceCode, dataSourceType);
                                    getRestricitveType(resObj[a], workProcedureId)
                                }
                            }
                        } else {
                            tipDialog(res.msg, 3, 0);
                        }
                    },
                    error: function (res) {
                        tipDialog(res.msg, 3, 0);
                    }
                })
            }
        });
    }

    //获取限制条件
    function getRestricitveType(formAttributeObj, workProcedureId) {
        var id = formAttributeObj.formAttributeId;
        var restrictiveConditions = formAttributeObj.restrictiveConditions;
        if (restrictiveConditions == 1) {
            $("#formOption" + id).addClass("workProcessNotNull" + workProcedureId);
        } else if (restrictiveConditions == 2) {
            $("#formOption" + id).attr("placeholder", "只能输入数字");
            $("#formOption" + id).addClass("workProcessOnlyNum" + workProcedureId);
        } else if (restrictiveConditions == 3) {
            $("#formOption" + id).attr("placeholder", "只能输入数字、字母");
            $("#formOption" + id).addClass("workProcessOnlyNumAndChar" + workProcedureId);
        } else if (restrictiveConditions == 4) {
            $("#formOption" + id).addClass("workProcessUnlimited" + workProcedureId);
        } else if (restrictiveConditions == 5) {
            $("#formOption" + id).attr({
                "value": shiroUserId,
                "type": "hidden"
            });
            $("#formOption" + id).parent().append('<span>' + shrioUserName + '</span>');
            $("#formOption" + id).addClass("workProcessThisUser" + workProcedureId);
        }
    }

    //获取限制内容
    function getRestricitveMsg(workProcedureId) {
        var boo = true;
        //1.不能为空
        $(".workProcessNotNull" + workProcedureId).each(function () {
            var val = $.trim($(this).val());
            if (val != "") {
                $(this).prev("div").removeClass("workProcessError");
            } else {
                $(this).prev("div").addClass("workProcessError");
                tipDialog("该项不能未空", 4, 'warning');
                boo = false;
            }
        });
        //2.只能输入数字
        $(".workProcessOnlyNum" + workProcedureId).each(function () {
            var val = $.trim($(this).val());
            var onlyNum = /^[0-9]*$/g;
            if (val != "") {
                if (onlyNum.test(val)) {
                    $(this).prev("div").removeClass("workProcessError");
                    boo = true
                } else {
                    boo = false;
                    $(this).attr("value", "");
                    $(this).prev("div").addClass("workProcessError");
                    tipDialog("只能输入数字", 4, 'warning');
                }
            } else {
                $(this).prev("div").addClass("workProcessError");
                tipDialog("该项不能未空", 4, 'warning');
                boo = false;
            }
        });
        //3.只能输入字母、数字
        $(".workProcessOnlyNumAndChar" + workProcedureId).each(function () {
            var onlyNumAndChar = /^[0-9a-zA-Z]+$/;
            var val = $.trim($(this).val());
            if (val != "") {
                if (onlyNumAndChar.test(val)) {
                    $(this).prev("div").removeClass("workProcessError");
                    boo = true;
                } else {
                    boo = false;
                    $(this).attr("value", "");
                    $(this).prev("div").addClass("workProcessError");
                    tipDialog("只能输入数字或字母", 4, 'warning');
                }
            } else {
                $(this).prev("div").addClass("workProcessError");
                tipDialog("该项不能未空", 4, 'warning');
                boo = false;
            }
        });
        return boo;
    }

    //获取控件类型
    function getTempletText(formAttributeObj, workProcedureId) {
        var strText = "";
        var id = formAttributeObj.formAttributeId;
        var num = formAttributeObj.controlType;
        var handleType = formAttributeObj.handleType;
        var workProcessName = formAttributeObj.workProcessName;
        var companyID = "";
        let timeStr = " WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})";
        if (num == 1) {
            strText += '<input class="produce-right formAttributeValue" id="formOption' + id + '" type="text"/>' +
                    '<input type="hidden" class="handleTypeValue" name="handleTypeValue" value="' + handleType + '">' +
                    '<input type="hidden" class="workProcedureIdValue" name="workProcedureIdValue" value="' + workProcedureId + '"/>' +
                    '<input type="hidden" class="workProcessNameValue" name="workProcessNameValue" value="' + workProcessName + '"/>'
        } else if (num == 2) {
            strText += '<select class="produce-right formAttributeValue" id="formOption' + id + '"></select>' +
                    '<input type="hidden" class="handleTypeValue" name="handleTypeValue" value="' + handleType + '">' +
                    '<input type="hidden" class="workProcedureIdValue" name="workProcedureIdValue" value="' + workProcedureId + '"/>' +
                    '<input type="hidden" class="workProcessNameValue" name="workProcessNameValue" value="' + workProcessName + '"/>'
        } else if (num == 3) {
            strText += '<input class="produce-right formAttributeValue" id="formOption' + id + '" type="text" class="txt Wdate" onfocus="' + timeStr + '"/>' +
                    '<input type="hidden" class="handleTypeValue" name="handleTypeValue" value="' + handleType + '">' +
                    '<input type="hidden" class="workProcedureIdValue" name="workProcedureIdValue" value="' + workProcedureId + '"/>' +
                    '<input type="hidden" class="workProcessNameValue" name="workProcessNameValue" value="' + workProcessName + '"/>'
        } else if (num == 5) {
            strText += '<textarea class="produce-right formAttributeValue" id="formOption' + id + '" maxlength="200" rows="5"></textarea>' +
                    '<input type="hidden" class="handleTypeValue" name="handleTypeValue" value="' + handleType + '">' +
                    '<input type="hidden" class="workProcedureIdValue" name="workProcedureIdValue" value="' + workProcedureId + '"/>' +
                    '<input type="hidden" class="workProcessNameValue" name="workProcessNameValue" value="' + workProcessName + '"/>'
        }
        return strText;
    }

    //根据数据源 类型查询
    function getDataDictionartByCode(id, code, type) {
        if (type == 1) {
            $.post('${request.contextPath}/stock/getDataDictionartByCode.json', {code: code}, function (res) {
                var resObj = res.obj;
                if (resObj != null) {
                    for (let i = 0; i < resObj.length; i++) {
                        $("#formOption" + id).append('<option ' +
                                'value="' + resObj[i].dataDictionaryDetailsId + '">' + resObj[i].cname + '</option>')
                    }
                }
            })
        } else if (type == 2) {
        <#if shiroUser??>
            companyID =${shiroUser.companyId!};
        </#if>
            $.post('${request.contextPath}/stock/getAllUserInStockManage.json', {}, function (res) {
                var resObj = res.obj;
                if (resObj != null) {
                    for (let i = 0; i < resObj.length; i++) {
                        $("#formOption" + id).append('<option ' +
                                'value="' + resObj[i].userId + '">' + resObj[i].cname + '</option>')
                    }
                }
            })
        }
    }

    var rawData = new Array();//出库详情
    var formAttributeValueList = new Array();//表单设置明细键值表
    var arrayList = new Array();//表单数据
    var allData = {
        produceTaskName: "",//生产任务名称
        workFlowId: "",//工艺id
        produceTaskNo: "",//生产任务编号
        produceTaskId: "",//生产任务id
        leaveNo: $("#yhLeaveNo").val(),//出库编号
        leavePerson: "",//出库申请人
        brokerage: "",//经手人
        leaveDate: "",//出库时间
        produceTaskTag: "",//生产任务标签
        remark: "",//备注1
        saLeaveStockDetails: rawData,
        formAttributeValueList: formAttributeValueList,
        attributeValueDataList: arrayList
    };
    var postData;

    //增加工序
    function addProcedureManage() {
        var workProcedureId = $(".add-num-active").last().attr("id").replace("workProcessListId", "");
        if (!getRestricitveMsg(workProcedureId)) {
            return false;
        } else {
            Loading(true, "正在加载数据...", "#addProcedureManageHtml");
            rawData.length = 0;
            arrayList.length = 0;
            if ($("#SeaWorkProcessCName").val() != "") {
                allData.produceTaskName = $("#SeaWorkProcessCName").val();
                allData.workFlowId = $("#SeaWorkProcessCName").next().attr("id");
                allData.produceTaskNo = $('#yhProduceTaskNo').text();
                allData.produceTaskId = $("#yhProduceTaskId").val();
            } else {
                tipDialog("生产任务不能为空！", 3, 'warning');
                Loading(false, "", "#addProcedureManageHtml");
                return;
            }
            if ($.trim($('#yhLeavePerson').val()) != "") {
                allData.leavePerson = $.trim($('#yhLeavePerson').val());
            } else {
                tipDialog("出库申请人不得为空！", 3, 'warning');
                Loading(false, "", "#addProcedureManageHtml");
                return;
            }
            if ($.trim($('#yhBrokerage').val()) != "") {
                allData.brokerage = $.trim($('#yhBrokerage').val());
            } else {
                tipDialog("经手人不得为空！", 3, 'warning');
                Loading(false, "", "#addProcedureManageHtml");
                return;
            }
            if ($.trim($('#yhLeaveDate').val()) != "") {
                allData.leaveDate = $.trim($('#yhLeaveDate').val());
            } else {
                tipDialog("出库时间不得为空！", 3, 'warning');
                Loading(false, "", "#addProcedureManageHtml");
                return;
            }
            let produceTaskId = $("#yhProduceTaskId").val();

            if ($.trim($('#yhRemark1').val()) != "") {
                allData.remark = $.trim($('#yhRemark1').val());
            }

            var leng = $("#grid_Field tbody>tr").length;
            for (var i = 0; i < leng; i++) {
                var detail = {
                    warehouseId: "",//仓库id
                    isMaterial: "",//货物类型
                    productName: "",//产品名称
                    productId: "",//产品id
                    productStatus: "",//产品状态id
                    productStatusName: "",//产品状态名称
                    productSpecName: "",//规格名称
                    batchNo: "",//批次号
                    weight: "",//库存数量
                    outWeight: "",//出库数量
                    unitId: "",//单位Id
                    unitName: "",//单位名称
                    loss: "",//损耗
                    remark: ""//备注2
                }
                let num = i + 1;
                let warehouseId = $("#warehouse" + num + " :selected").val();
                if (warehouseId != "") {
                    detail.warehouseId = warehouseId;
                } else {
                    tipDialog("第" + num + "行仓库为空！", 3, 'warning');
                    Loading(false, "", "#addProcedureManageHtml");
                    return;
                }
                let isMaterial = $("#goodsType" + num + " :selected").val();
                if (isMaterial != "") {
                    detail.isMaterial = isMaterial;
                } else {
                    tipDialog("第" + num + "行货物类型为空！", 3, 'warning');
                    Loading(false, "", "#addProcedureManageHtml");
                    return;
                }
                let productId = $("#productName" + num + " :selected").val();
                if (productId != "") {
                    detail.productId = productId;
                } else {
                    tipDialog("第" + num + "行产品ID为空！", 3, 'warning');
                    Loading(false, "", "#addProcedureManageHtml");
                    return;
                }
                let productName = $("#grid_Field tbody>tr").eq(i).find("div:eq(2)").text();
                if (productName != "") {
                    detail.productName = productName;
                } else {
                    tipDialog("第" + num + "行产品为空！", 3, 'warning');
                    Loading(false, "", "#addProcedureManageHtml");
                    return;
                }
                let productStatus = $("#productStatus" + num + " :selected").val();
                if (productStatus != "") {
                    detail.productStatus = productStatus;
                } else {
                    tipDialog("第" + num + "行产品状态为空！", 3, 'warning');
                    Loading(false, "", "#addProcedureManageHtml");
                    return;
                }
                let productStatusName = $("#grid_Field tbody>tr").eq(i).find("div:eq(3)").text();
                if (productStatusName != "") {
                    detail.productStatusName = productStatusName;
                } else {
                    tipDialog("第" + num + "行产品状态名称为空！", 3, 'warning');
                    Loading(false, "", "#addProcedureManageHtml");
                    return;
                }
                let productSpecName = $("#grid_Field tbody>tr").eq(i).find("div:eq(4)").text();
                if (productSpecName != "") {
                    detail.productSpecName = productSpecName;
                } else {
                    tipDialog("第" + num + "行规格为空！", 3, 'warning');
                    Loading(false, "", "#addProcedureManageHtml");
                    return;
                }
                let batchNo = $("#batchNo" + num + " :selected").val();
                if (batchNo != "") {
                    detail.batchNo = batchNo;
                } else {
                    tipDialog("第" + num + "行批次号为空！", 3, 'warning');
                    Loading(false, "", "#addProcedureManageHtml");
                    return;
                }
                let weight = $("#grid_Field tbody>tr").eq(i).find("div:eq(6)").text();
                if (weight != "") {
                    detail.weight = weight;
                } else {
                    tipDialog("第" + num + "行库存数量为空！", 3, 'warning');
                    Loading(false, "", "#addProcedureManageHtml");
                    return;
                }
                let outWeight = $("#grid_Field tbody>tr").eq(i).find("div:eq(7)").text();
                if (outWeight != "") {
                    detail.outWeight = outWeight;
                } else {
                    tipDialog("第" + num + "行出库数量为空！", 3, 'warning');
                    Loading(false, "", "#addProcedureManageHtml");
                    return;
                }
                let unitId = $("#grid_Field tbody>tr").eq(i).find("td:eq(9)").find("div:eq(1)").text();
                if (unitId != "") {
                    detail.unitId = unitId;
                } else {
                    tipDialog("第" + num + "行单位ID为空！", 3, 'warning');
                    Loading(false, "", "#addProcedureManageHtml");
                    return;
                }
                let unitName = $("#grid_Field tbody>tr").eq(i).find("td:eq(9)").find("div:eq(0)").text();
                if (unitName != "") {
                    detail.unitName = unitName;
                } else {
                    tipDialog("第" + num + "行单位为空！", 3, 'warning');
                    Loading(false, "", "#addProcedureManageHtml");
                    return;
                }
                let loss = $("#grid_Field tbody>tr").eq(i).find("td:eq(10)").find("div:eq(0)").text();
                if (loss != "") {
                    detail.loss = loss;
                } else {
                    tipDialog("第" + num + "行是否损耗为空！", 3, 'warning');
                    Loading(false, "", "#addProcedureManageHtml");
                    return;
                }
                detail.remark = $("#grid_Field tbody>tr").eq(i).find("td:eq(11)").find("div:eq(0)").text();
                rawData.push(detail);
            }
            allData.saLeaveStockDetails = rawData;
            postData = allData;
            let attributeValueDataList = {
                attributeValueDataList: arrayList
            };
        <#if workProcessList??>
            <#list workProcessList as workProcess>
                var workProcessId;
                workProcessId =${workProcess.workProcessId};
                var workProcessName = "${workProcess.CName}";
                if (workProcessId != null && workProcessId != "") {
                    for (let i = 0; i < 3; i++) {
                        var workProcess = {
                            handleType: "",
                            workProcessId: "",
                            workProcessName: ""
                        };
                        let index = i + 1;
                        var handleType = $(".formAttrbuteStr" + index + workProcessId).parents().find("input[name=workProcessId]").val();
                        if (handleType != null) {
                            workProcess.handleType = index;
                            workProcess.workProcessId = workProcessId;
                            workProcess.workProcessName = workProcessName;
                            formAttributeValueList.push(workProcess);
                        }
                    }
                }
            </#list>
        </#if>
            $(".formAttributeValue").each(function (index) {
                let field = {
                    attribute: "",
                    attributeValueId: "",
                    typeIndex: "",
                    handleType: "",
                    formAttributeValueId: "",
                    workProcessName: ""
                };
                let value = $.trim($(this).val());
                let attributeValueId = $.trim($(this).attr("id").replace("formOption", ""));
                let handleType = $.trim($(this).next("input[name=handleTypeValue]").val());
                let formAttributeValueId = $.trim($(this).next().next("input[name=workProcedureIdValue]").val());
                let workProcessName = $.trim($(this).next().next().next("input[name=workProcedureIdValue]").val());
                field.attribute = value;
                field.attributeValueId = attributeValueId;
                field.handleType = handleType;
                field.formAttributeValueId = formAttributeValueId;
                field.workProcessName = workProcessName;
                arrayList.push(field);
            });
            $.ajax({
                url: "${request.contextPath}/stock/addProcedureRecord.json",
                type: "POST",
                data: JSON.stringify(allData),
                contentType: "application/json;charset=utf-8;",
                dataType: "json",
                success: function (res) {
                    if (res.success) {
                        tipDialog(res.msg, 3, 2);
                        ThisCloseTab();
                    } else {
                        tipDialog(res.msg, 4, "warning");
                        Loading(false, "", "#addProcedureManageHtml");
                    }
                },
                error: function () {
                    tipDialog("网络异常", 0, 2);
                    Loading(false, "", "#addProcedureManageHtml");
                }
            });
        }
    }

    //跳转生产任务模态框
    $("#SeaWorkProcessCName").on("focus", function () {
        $("#workProcessNameModal").modal({
            remote: "${request.contextPath}/stock/workProcessNameModal.htm"
        });
    });

    //生产任务
    function addWorkFlowName() {
        Loading(true, "正在获取工艺详情", "#addProcedureManageHtml");
        var clickBg = $("#SeaWorkProcessNameModalBody").find(".clickBg").text();
        var clickBgId = $("#SeaWorkProcessNameModalBody").find(".clickBg").attr("id");
        $("#yhProduceTaskNo").val("");
        var arr = [];
        $(".productTitle").find("a").not("#product-1").each(function () {
            var trimText = $.trim($(this).text());
            arr.push(trimText);
        });
        arr.push(clickBg);
        var arrText = arr.join('－');
        $("#SeaWorkProcessCName").val(arrText);
        $("#SeaWorkProcessCName").next().attr("id", clickBgId);
        $("#workProcessNameModal").modal("hide");
        $.post("${request.contextPath}/stock/getProduceTaskNo.json", {workFlowId: clickBgId}, function (res) {
            var resObj = res.obj;
            $("#yhProduceTaskNo").text(resObj);
        }, "json");
        //获取工序详情
        $.ajax({
            url: "${request.contextPath}/stock/getWorkProcessListByWorkFlowIdWithStock.json?workFlowId=" + clickBgId,
            type: "POST",
            success: function (res) {
                $("#workProcessListText").html("");
                $("#formAttributeClass").html("");
                var resObj = res.obj;
                indexMax = 0;
                if (resObj != null && resObj.length > 0) {
                    for (let i = 0; i < resObj.length; i++) {
                        $("#workProcessListText").append
                        ('<a class="add-num workProcessList" id="workProcessListId' + resObj[i].workProcessId + '">' +
                                '<span>' + (i + 1) + '</span>' +
                                '<p>' + resObj[i].cname + '</p>' +
                                '</a>' +
                                '<span class="hrClass"></span>');
                        $("#formAttributeClass").append
                        ('<div class="main-container container produce-box record" id="formAttrbuteList' + resObj[i].workProcessId + '"></div>')
                    }
                    $(".container").eq(0).show();
                    $(".add-num").eq(0).addClass("add-num-active");
                    $(".hrClass:last").remove();
                    Loading(false, "", "#addProcedureManageHtml");
                    indexNum = 1;
                    indexMax = resObj.length;
                    getFormAttributeList();
                    return true;
                } else {
                    indexNum = -1;
                    indexMax = 0;
                    Loading(false, "", "#addProcedureManageHtml");
                    tipDialog("生产任务没有对应工序", 3, 'warning');
                }
            },
            error: function () {
                tipDialog("服务器异常", 3, 0);
                Loading(false, "", "#addProcedureManageHtml");
            }
        })
    }

    var RowIndex = 2;

    //插入一个空行
    function InsetTableRow(isMaterial) {
        var len = $("#grid_Field tbody tr").length;
        $("#grid_Field tbody").append(CreateTableRowAddProcess(len + 1));
        $("#grid_Field tbody tr").eq(len).find('input,select').hide();
        initSearchAddProcess(len + 1, isMaterial);
        TableTdEventAddProcess();
        var indexrow = 1;
        $("#grid_Field tbody tr").each(function () {
            $(this).find('td:eq(0)').html(indexrow);
            $(this).find("input,select,div").each(function () {
                let indexId = $(this).attr("id");
                if (undefined !== indexId) {
                    let index = indexId.match(/^[a-z|A-Z]+/gi);
                    $(this).attr("id", index + indexrow);
                }
            });
            indexrow++;
        })
    }

    //清空表格当前行
    function EmptyTableRow() {
        var trobj = $("#grid_Field tbody .selected");
        if (trobj != null && trobj != undefined && trobj.length > 0) {
            trobj.remove();
            var indexrow = 1;
            $("#grid_Field tbody tr").each(function () {
                $(this).find('td:eq(0)').html(indexrow);
                $(this).find("input,select,div").each(function () {
                    let indexId = $(this).attr("id");
                    if (undefined !== indexId) {
                        let index = indexId.match(/^[a-z|A-Z]+/gi);
                        $(this).attr("id", index + indexrow);
                    }
                });
                indexrow++;
            })
        } else {
//            tipCss($(this), "！\n");
            tipDialog("请先选择一行\n", 4, 'warning');
        }
    }

    //选中回显
    function getStockIdsByStock() {
        var stockIds = "${stockIds!}";
        if (null != stockIds && '' != stockIds) {
            $.post("${request.contextPath}/stock/showRecordBySelectForSeaCucumber.json", {stockIds: stockIds}, function (res) {
                if (res.success) {
                    var resObj = res.obj;
                    $("#grid_Field tbody").html("");
                    for (var i = 0; i < resObj.length; i++) {
                        let index = i + 1;
                        InsetTableRow(resObj[i].isMaterial);
                        //仓库
                        $("#warehouse" + index).attr("disabled", "disabled");
                        $("#warehouse" + index).append("<option value='" + resObj[i].warehouseId + "' selected>" + resObj[i].warehouseName + "</option>");
                        $("#warehouse" + index).prev("div").text(resObj[i].warehouseName);
                        //货物类型
                        $("#goodsType" + index).find("option").remove();
                        $("#goodsType" + index).attr("disabled", "disabled");
                        $("#goodsType" + index).append("<option value='" + resObj[i].isMaterial + "' selected>" + resObj[i].materialName + "</option>");
                        $("#goodsType" + index).prev("div").text(resObj[i].materialName);
                        //产品名称
                        $("#productName" + index).attr("disabled", "disabled");
                        $("#productName" + index).append("<option value='" + resObj[i].productId + "' selected>" + resObj[i].productName + "</option>");
                        $("#productName" + index).prev("div").text(resObj[i].productName);
                        //产品状态
                        $("#productStatus" + index).attr("disabld", "disabled");
                        $("#productStatus" + index).append("<option value='" + resObj[i].productStatus + "' selected>" + resObj[i].productStatusName + "</option>");
                        $("#productStatus" + index).prev("div").text(resObj[i].productStatusName);
                        //入库规格
                        $("#productSpecName" + index).attr("disabled", "disabled");
                        $("#productSpecName" + index).append("<option value='" + resObj[i].productSpecName + "' selected>" + resObj[i].productSpecName + "</option>");
                        $("#productSpecName" + index).prev("div").text(resObj[i].productSpecName);
                        //批次号
                        $("#batchNo" + index).attr("disabled", "disabled");
                        $("#batchNo" + index).append("<option value='" + resObj[i].batchNo + "' selected>" + resObj[i].batchNo + "</option>");
                        $("#batchNo" + index).prev("div").text(resObj[i].batchNo);
                        //库存
                        $("#weight" + index).text(resObj[i].weight);
                        //单位
                        $("#unitId" + index).text(resObj[i].unitId);
                        $("#unitId" + index).addClass("hidden");
                        $("#unitName" + index).text(resObj[i].unitName);
                        //损耗
                        //获取损耗类型
                        $.post("${request.contextPath}/stock/getLoss.json", "", function (res) {
                            $("#loss" + index).find("option").remove();
                            var obj = res.obj;
                            for (i in obj) {
                                $("#loss" + index).append("<option value='" + obj[i].cname + "'>" + obj[i].cname + "</option>")
                            }
                            $("#loss" + index).val("否");
                        }, "JSON");
                    }
                }
            });
        } else {
            $("#grid_Field tbody").html(CreateTableRowAddProcess(1));
            $("#grid_Field tbody tr").find('select,input').attr('disabled', 'disabled').hide();
            $("#grid_Field tbody tr:eq(0)").find('select,input').removeAttr('disabled');
            initSearchAddProcess(1);
        }
    }
</script>
</body>
</html>