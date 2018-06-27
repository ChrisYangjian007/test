<style>
    .btn-search {
        display: inline-block;
        padding: 3px 5px;
        border: 1px solid #ccc;
    }
</style>
<form id="form1" autocomplete="off" style="margin: 1px">
    <div class="bd">
        <div class="ScrollBar" style="margin-top: 1px;">
            <div id="TableProperty">
                <table class="form">
                    <tr>
                        <td align="center" colspan="3" style="padding:5px 0">
                            <input id="yhResourceId" type="hidden" value="${resourceId!}"/>
                            <input id="seachNo2" autocomplete="on" type="text" style="width:90%;height:25px"
                                   placeholder="输入单据/二维码或手持枪扫一扫 （如：生产任务二维码/出货单二维码）"/>
                        </td>
                        <td>
                            <a onclick="searchNo2()" class="btn-search">搜索</a>
                        </td>
                    </tr>
                    <tr>
                        <th class="formTitle">
                            *生产任务：
                        </th>
                        <td class="formValue">
                            <input id="workProcessCName" name="cName" type="text" class="txt required"/>
                            <input id="nameId" name="nameId" type="hidden"/>
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
                        ${leaveStockNode!}
                            <input type="hidden" id="yhLeaveNo" value="${leaveStockNode!}"/>
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
                        <th class="formTitle">
                            生产任务标签：
                        </th>
                        <td class="formValue" colspan="3">
                            <input id="yhCode" type="text" class="txt" style="width: 100%"
                                   placeholder="扫一扫或输入标签编号，绑定生产任务标签二维码">
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
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</form>

<div id="workProcessNameModal" class="modal fade " data-width="780" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">生产任务</h4>
    </div>
    <div class="modal-body" id="workProcessNameModalBody">
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="addWorkProcessName" type="button" class="btn green" disabled onclick="addWorkFlowName()">确定</button>
    </div>
</div>

<script>

    //跳转生产任务模态框
    $("#leaveStockModal").on("focus", "#workProcessCName", function () {
        $("#workProcessNameModal").modal({
            remote: "${request.contextPath}/stock/workProcessNameModal.htm"
        });
    });

    //生产任务
    function addWorkFlowName() {
        let clickBg = $("#workProcessNameModalBody").find(".clickBg").text();
        let clickBgId = $("#workProcessNameModalBody").find(".clickBg").attr("id");
        $("#yhProduceTaskNo").val("");
        $.post("${request.contextPath}/stock/isHasProcess.json", {workFlowId: clickBgId}, function (res) {
            if (res.success) {
                let arr = [];
                $(".productTitle").find("a").not("#product-1").each(function () {
                    let trimText = $.trim($(this).text());
                    arr.push(trimText);
                });
                arr.push(clickBg);
                let arrText = arr.join('－');
                $("#workProcessCName").val(arrText);
                $("#workProcessCName").next().attr("id", clickBgId);
                $("#workProcessNameModal").modal("hide");
                $.post("${request.contextPath}/stock/getProduceTaskNo.json", {workFlowId: clickBgId}, function (res) {
                    let resObj = res.obj;
                    $("#yhProduceTaskNo").text(resObj);
                }, "json");
            } else {
                tipDialog(res.msg, 2, 'warning');
            }
        });
    }

    //生产任务标签
    $("#yhCode").on("input", function () {
        $(this).val($(this).val().replace(/，/ig, ','));
    });
    $("#yhCode").on("blur", function () {
        var codeTxt = $(this).val();
        if (null != codeTxt && "" != codeTxt) {
            var reg = /^\d{10}$/;
            if (codeTxt.indexOf(",") > -1) {
                var textSplit = codeTxt.split(",");
                for (var i = 0; i < textSplit.length; i++) {
                    if (!reg.test(textSplit[i])) {
                        tipDialog("格式错误，必须是10位数字", 2, 'warning');
                        $("#yhCode").trigger("focus");
                    }
                }
            } else {
                if (!reg.test(codeTxt)) {
                    tipDialog("格式错误，必须是10位数字", 2, 'warning');
                    $("#yhCode").trigger("focus");
                }
            }
        }
    });

    var RowIndex = 2;

    //插入一个空行
    function InsetTableRow() {
        var len = $("#grid_Field tbody tr").length;
        $("#grid_Field tbody").append(CreateTableRow(len + 1));
        $("#grid_Field tbody tr").eq(len).find('input,select').hide();
        initSearch(len + 1);
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
            tipDialog("请先选择一行！\n", 4, 'warning');
        }
    }

    //表格点击事件
    function TableTdEvent() {
        $(".grid").on("click", ".td-div", function () {
            if ($(this).parent().hasClass("selected")) {
                $(this).parent().removeClass("selected");
            } else {
                $(this).parent().addClass("selected");
            }
        });
        var grid = $("#grid_Field tbody");
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
        })
    }

    function clearNoNum(obj) {
        obj.value = obj.value.replace(/[^\d.]/g, "");  //清除“数字”和“.”以外的字符
        obj.value = obj.value.replace(/\.{2,}/g, "."); //只保留第一个. 清除多余的
        obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
        obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3');//只能输入两个小数
        if (obj.value.indexOf(".") < 0 && obj.value != "") {//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额
            obj.value = parseFloat(obj.value);
        }
    }

    //创建行
    function CreateTableRow(index) {
        var tr = '';
        tr += '<tr class="leaveAddTr">';
        tr += '<td class="td-div" style="width: 30px; text-align: center;border-left: none;">' + index + '</td>';
        tr += '<td style="width: 50px; text-align: center;"><div></div>' + IsWarehouse(index) + '</td>';//仓库
        tr += '<td style="width: 60px; text-align: center;"><div></div>' + IsMaterial(index) + '</td>';//货物类型
        tr += '<td style="width: 60px; text-align: center;"><div></div>' + IsProduct(index) + '</td>';//产品名称
        tr += '<td style="width: 60px; text-align: center;"><div></div>' + IsProductStatus(index) + '</td>';//产品状态
        tr += '<td style="width: 60px; text-align: center;"><div></div>' + IsProductSpec(index) + '</td>';//入库规格
        tr += '<td style="width: 60px; text-align: center;"><div></div>' + IsBatchNo(index) + '</td>';//批次号
        tr += '<td style="width: 60px; text-align: center;">' + IsWeight(index) + '</td>';//库存数量
        tr += '<td style="width: 50px; text-align: center;"><div></div><input id="outWeight' + index + '" type="text" maxlength="10" class="txt" name ="outWeight" onkeyup="clearNoNum(this)"/></td>';
        tr += '<td style="width: 60px; text-align: center;">' + IsUnit(index) + '</td>';//单位
        tr += '<td style="width: 60px; text-align: center;"><div></div>' + IsLoss(index) + '</td>';//损耗
        tr += '<td style="width: 60px; text-align: center;"><div></div><input type="text" maxlength="10"  class="txt"/></td>';//备注2
        tr += '</tr>';
        return tr;
    }

    //仓库
    function IsWarehouse(index) {
        var html = '<select id="warehouse' + index + '" class="txtselect" datacol="no" type="select" checkexpession="NotNull">';
        html += '</select>';
        return html;
    }

    //货物类型
    function IsMaterial(index) {
        var html = '<select id="goodsType' + index + '" class="txtselect" datacol="no" type="select" checkexpession="NotNull">';
        html += '</select>';
        return html;
    }

    //产品名称
    function IsProduct(index) {
        var html = '<select id="productName' + index + '" class="txtselect buyaogaiwo" name ="productName"  datacol="no" type="select" err="产品名称" checkexpession="NotNull">';
        html += '</select>';
        return html;
    }

    //产品状态
    function IsProductStatus(index) {
        var html = '<select id="productStatus' + index + '" class="txtselect" name ="productStatus"  datacol="no" type="select" checkexpession="NotNull">';
        html += '</select>';
        return html;
    }

    //入库规格
    function IsProductSpec(index) {
        var html = '<select id="productSpecName' + index + '" class="txtselect" name ="productSpecName"  datacol="no" type="select" err="规格" checkexpession="NotNull">';
        html += '</select>';
        return html;
    }

    //批次号
    function IsBatchNo(index) {
        var html = '<select id="batchNo' + index + '" class="txtselect" name ="batchNo"  datacol="no" type="select" checkexpession="NotNull">';
        html += '</select>';
        return html;
    }

    //库存数量
    function IsWeight(index) {
        var html = '<div id="weight' + index + '"></div>';
        return html;
    }


    //单位
    function IsUnit(index) {
        var html = '<div id="unitName' + index + '"></div><div id="unitId' + index + '"></div>';
        return html;
    }

    //损耗
    function IsLoss(index) {
        var html = '<select id="loss' + index + '" class="txtselect" name ="loss"  datacol="no" type="select" checkexpession="NotNull">';
        html += '</select>';
        return html;
    }

    //默认创建一行
    function CreateTable() {
        for (var i = 0; i < 1; i++) {
            $("#grid_Field tbody").append(CreateTableRow(RowIndex));
            RowIndex++;
        }
        $("#grid_Field tbody tr").find('select,input').attr('disabled', 'disabled').hide();
        $("#grid_Field tbody tr:eq(0)").find('select,input').removeAttr('disabled');
        initSearch(1);
    }

    //select相互关联
    function initSearch(num) {
        //获取仓库
        $.post("${request.contextPath}/stock/getWarehouse.json", "", function (res) {
            $("#warehouse" + num).find("option").remove();
            if (res.success) {
                let resObj = res.obj;
                for (i in resObj) {
                    $("#warehouse" + num).append("<option value='" + resObj[i].warehouseId + "'>" + resObj[i].warehouseName + "</option>");
                }
            } else {
                $("#warehouse" + num).append("<option value=''>==请选择==</option>");
            }
        }, "JSON");
        //获取损耗类型
        $.post("${request.contextPath}/stock/getLoss.json", "", function (res) {
            $("#loss" + num).find("option").remove();
            var obj = res.obj;
            for (i in obj) {
                $("#loss" + num).append("<option value='" + obj[i].cname + "'>" + obj[i].cname + "</option>");
            }
            $("#loss" + num).val("否");
        }, "JSON");

        loadProduct("#warehouse" + num, "#goodsType" + num, "#productName" + num, "#productStatus" + num, "#productSpecName" + num, "#batchNo" + num, "#unitName" + num, "#unitId" + num, "#weight" + num);
        $("#weight" + num).keyup(function () {
            var tmptxt = $(this).val();
            $(this).val(tmptxt.replace(/[^\d.]/g, ""));
        }).bind("paste", function () {
            var tmptxt = $(this).val();
            $(this).val(tmptxt.replace(/[^\d.]/g, ""));
        }).css("ime-mode", "disabled");
    }

    function loadProduct(warehouseId, typeId, productId, statusId, specId, batchNo, unitName, unitId, weight) {
        var warehouseEl = $("" + warehouseId);//仓库
        var typeEl = $("" + typeId);//货物类型
        var productEl = $("" + productId);//产品名称
        var statusEl = $("" + statusId);//产品状态
        var specEl = $("" + specId);//入库规格
        var batchEl = $("" + batchNo);//批次号
        var unitEl = $("" + unitName);//单位
        var unitIdEl = $("" + unitId);//单位Id
        var weightEl = $("" + weight);//库存数量
        //点击仓库获取货物类型
        warehouseEl.unbind("click");
        warehouseEl.click(function () {
            Loading(true, "正在加载数据...", "#leaveStockModal");
            typeEl.prev("div").text("");
            productEl.prev("div").text("");
            statusEl.prev("div").text("");
            specEl.prev("div").text("");
            batchEl.prev("div").text("");
            unitEl.empty();
            unitIdEl.empty();
            weightEl.empty();
            if (warehouseEl == "") {
                typeEl.find("option").remove();
            } else {
                typeEl.find("option").remove();
                var warehouseId = warehouseEl.val();
                $.post("${request.contextPath}/stock/getGoodsTypeByWarehouseId.json", {warehouseId: warehouseId}, function (res) {
                    if (res.success) {
                        typeEl.find("option").remove();
                        var obj = res.obj;
                        for (i in obj) {
                            typeEl.append("<option value='" + obj[i].isMaterial + "'>" + obj[i].materialName + "</option>")
                        }
                    }
                    Loading(false, "", "#leaveStockModal");
                }, "JSON");
            }
        });

        //点击货物类型获取产品名称
        typeEl.unbind("click");
        typeEl.click(function () {
            Loading(true, "正在加载数据...", "#leaveStockModal");
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
                var warehouseId = warehouseEl.val();
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
                        } else {
                            tipDialog(res.msg, 2, 'warning');
                        }
                        Loading(false, "", "#leaveStockModal");
                    }
                });
            }
        });

        //点击产品名称获取产品状态
        productEl.unbind("click");
        productEl.click(function () {
            Loading(true, "正在加载数据...", "#leaveStockModal");
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
                var warehouseId = warehouseEl.val();
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
                        } else {
                            tipDialog(res.msg, 2, 'warning');
                        }
                        Loading(false, "", "#leaveStockModal");
                    }
                });
            }
        });

        //点击产品状态获取规格
        statusEl.unbind("click");
        statusEl.click(function () {
            Loading(true, "正在加载数据...", "#leaveStockModal");
            specEl.prev("div").text("");
            batchEl.prev("div").text("");
            unitEl.empty();
            unitIdEl.empty();
            weightEl.empty();
            if (statusEl.val() == "") {
                specEl.find("option").remove();
            } else {
                specEl.find("option").remove();
                var warehouseId = warehouseEl.val();
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
                        } else {
                            tipDialog(res.msg, 2, 'warning');
                        }
                        Loading(false, "", "#leaveStockModal");
                    }
                });
            }
        });

        //点击规格获取批次号
        specEl.unbind("click");
        specEl.click(function () {
            Loading(true, "正在加载数据...", "#leaveStockModal");
            batchEl.prev("div").text("");
            unitEl.empty();
            unitIdEl.empty();
            weightEl.empty();
            if (specEl.val() == "") {
                batchEl.find("option").remove();
            } else {
                batchEl.find("option").remove();
                var warehouseId = warehouseEl.val();
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
                        } else {
                            tipDialog(res.msg, 2, 'warning');
                        }
                        Loading(false, "", "#leaveStockModal");
                    }
                });
            }
        });

        //点击批次号获取库存数量和单位
        batchEl.unbind("click");
        batchEl.click(function () {
            Loading(true, "正在加载数据...", "#leaveStockModal");
            unitEl.empty();
            unitIdEl.empty();
            weightEl.empty();
            if (batchEl.val() == "") {
                unitEl.find("option").remove();
            } else {
                unitEl.find("option").remove();
                var warehouseId = warehouseEl.val();
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
                            var obj = res.obj;
                            weightEl.append(obj.weight);
                            unitEl.append(obj.unitName);
                            unitIdEl.append(obj.unitId);
                            unitIdEl.addClass("hidden");
                        } else {
                            tipDialog(res.msg, 2, 'warning');
                        }
                        Loading(false, "", "#leaveStockModal");
                    }
                });
            }
        });
    }

    //搜索
    function searchNo2() {
        Loading(true, "正在搜索", "#leaveStockModal");
        let seachNo = $.trim($("#seachNo2").val());
        let regNumber = /^\d{10}$/;
        if (regNumber.test(seachNo)) {
            ajax1 = $.post("${request.contextPath}/stock/getProduceTaskByQrCode.json", {qrCode: seachNo}, function (res) {
                if (res.success) {
                    let resObj = res.obj;
                    $("#workProcessCName").val(resObj.currentProduceTaskName);
                    $("#workProcessCName").attr("disabled", "disabled");
                    $("#yhProduceTaskNo").text(resObj.currentProduceTaskCode);
                    $("#yhProduceTaskId").val(resObj.currentProduceTaskId);
                    $("#seachNo2").val("");
                } else {
                    tipDialog(res.msg, 3, 'warning');
                }
            }, "JSON");
            //获取workFlowId
            ajax2 = $.post("${request.contextPath}/stock/getWorkFlowIdByQrCode.json", {qrCode: seachNo}, function (res) {
                if (res.success) {
                    let resObj = res.obj;
                    $("#workProcessCName").next().attr("id", resObj);
                } else {
                    if ("" != res.msg) {
                        tipDialog(res.msg, 3, "warning");
                    }
                }
            }, "JSON");
            //通过二维码回显详情
            ajax3 = $.post("${request.contextPath}/stock/showRecordByQrCode.json", {qrCode: seachNo}, function (res) {
                if (res.success) {
                    let resObj = res.obj;
                    $(".leaveAddTr").remove();
                    for (var i = 0; i < resObj.length; i++) {
                        let index = i + 1;
                        $("#grid_Field tbody").append(CreateTableRow(index)).find('input,select').hide();
                        //仓库
                        $("#warehouse" + index).attr("disabled", "disabled");
                        $("#warehouse" + index).append("<option value='" + resObj[i].warehouseId + "' selected>" + resObj[i].warehouseName + "</option>");
                        $("#warehouse" + index).prev("div").text(resObj[i].warehouseName);
                        //货物类型
                        $("#goodsType" + index).attr("disabled", "disabled");
                        $("#goodsType" + index).append("<option value='" + resObj[i].goodsTypeId + "' selected>" + resObj[i].goodsType + "</option>");
                        $("#goodsType" + index).prev("div").text(resObj[i].goodsType);
                        //产品名称
                        $("#productName" + index).attr("disabled", "disabled");
                        $("#productName" + index).append("<option value='" + resObj[i].productId + "' selected>" + resObj[i].productName + "</option>");
                        $("#productName" + index).prev("div").text(resObj[i].productName);
                        //产品状态
                        $("#productStatus" + index).attr("disabled", "disabled");
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
                        $("#weight" + index).text(resObj[i].resWeight);
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
                                $("#loss" + index).append("<option value='" + obj[i].cname + "'>" + obj[i].cname + "</option>");
                            }
                            $("#loss" + index).val("否");
                        }, "JSON");
                    }
                } else {
                    if ("" != res.msg) {
                        tipDialog(res.msg, 3, "warning");
                    }
                }
            }, "JSON");
            $.when(ajax1, ajax2, ajax3).done(function () {
                Loading(false, "", "#leaveStockModal");
            }).fail(function () {
                tipDialog("网络异常,请刷新！", 3, 0);
                $('#leaveStockModal').modal('hide');
                Loading(false, "", "#leaveStockModal");
            });
        } else {
            if (seachNo.indexOf("NO.CK") != -1) {
                //出库单编号
                ajax4 = $.post("${request.contextPath}/stock/geLeaveRecordByLeaveNo.json", {leaveNo: seachNo}, function (res) {
                    if (res.success) {
                        let resObj = res.obj;
                        $("#workProcessCName").val(resObj.produceTaskName);
                        $("#workProcessCName").attr("disabled", "disabled");
                        $("#yhProduceTaskNo").text(resObj.produceTaskNo);
                        $("#yhProduceTaskId").val(resObj.produceTaskId);
                        $("#seachNo2").val("");
                    } else {
                        tipDialog(res.msg, 3, 'warning');
                    }
                }, "JSON");
                //获取workFlowId
                ajax5 = $.post("${request.contextPath}/stock/getWorkFlowIdById.json", {leaveNo: seachNo}, function (res) {
                    if (res.success) {
                        let resObj = res.obj;
                        $("#workProcessCName").next().attr("id", resObj);
                    } else {
                        if ("" != res.msg) {
                            tipDialog(res.msg, 3, "warning");
                        }
                    }
                }, "JSON");
                //搜索出库编号回显
                ajax6 = $.post("${request.contextPath}/stock/showRecord.json", {leaveNo: seachNo}, function (res) {
                    if (res.success) {
                        let resObj = res.obj;
                        $(".leaveAddTr").remove();
                        for (var i = 0; i < resObj.length; i++) {
                            let index = i + 1;
                            $("#grid_Field tbody").append(CreateTableRow(index)).find('input,select').hide();
                            //仓库
                            $("#warehouse" + index).attr("disabled", "disabled");
                            $("#warehouse" + index).append("<option value='" + resObj[i].warehouseId + "' selected>" + resObj[i].warehouseName + "</option>");
                            $("#warehouse" + index).prev("div").text(resObj[i].warehouseName);
                            //货物类型
                            $("#goodsType" + index).attr("disabled", "disabled");
                            $("#goodsType" + index).append("<option value='" + resObj[i].goodsTypeId + "' selected>" + resObj[i].goodsType + "</option>");
                            $("#goodsType" + index).prev("div").text(resObj[i].goodsType);
                            //产品名称
                            $("#productName" + index).attr("disabled", "disabled");
                            $("#productName" + index).append("<option value='" + resObj[i].productId + "' selected>" + resObj[i].productName + "</option>");
                            $("#productName" + index).prev("div").text(resObj[i].productName);
                            //产品状态
                            $("#productStatus" + index).attr("disabled", "disabled");
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
                            $("#weight" + index).text(resObj[i].resWeight);
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
                                    $("#loss" + index).append("<option value='" + obj[i].cname + "'>" + obj[i].cname + "</option>");
                                }
                                $("#loss" + index).val("否");
                            }, "JSON");
                        }
                    } else {
                        if ("" != res.msg) {
                            tipDialog(res.msg, 3, "warning");
                        }
                    }
                }, "JSON");
                $.when(ajax4, ajax5, ajax6).done(function () {
                    Loading(false, "", "#leaveStockModal");
                }).fail(function () {
                    tipDialog("网络异常,请刷新！", 3, 0);
                    $('#leaveStockModal').modal('hide');
                    Loading(false, "", "#leaveStockModal");
                });
            } else {
                //根据生产任务编号
                ajax7 = $.post("${request.contextPath}/stock/getProduceTaskByProduceTaskNoYh.json", {produceTaskNo: seachNo}, function (res) {
                    if (res.success) {
                        let resObj = res.obj;
                        $("#workProcessCName").val(resObj.produceTaskName);
                        $("#workProcessCName").attr("disabled", "disabled");
                        $("#yhProduceTaskNo").text(resObj.produceTaskNo);
                        $("#yhProduceTaskId").val(resObj.produceTaskId);
                        $("#seachNo2").val("");
                    } else {
                        tipDialog(res.msg, 3, 'warning');
                    }
                }, "json");
                //获取workFlowId
                ajax8 = $.post("${request.contextPath}/stock/getWorkFlowIdByNo.json", {produceTaskNo: seachNo}, function (res) {
                    if (res.success) {
                        let resObj = res.obj;
                        $("#workProcessCName").next().attr("id", resObj);
                    } else {
                        if ("" != res.msg) {
                            tipDialog(res.msg, 3, "warning");
                        }
                    }
                }, "json");
                //搜索生产任务编号回显
                ajax9 = $.post("${request.contextPath}/stock/showRecordByProduceTaskNo.json", {produceTaskNo: seachNo}, function (res) {
                    if (res.success) {
                        let resObj = res.obj;
                        $(".leaveAddTr").remove();
                        for (var i = 0; i < resObj.length; i++) {
                            let index = i + 1;
                            $("#grid_Field tbody").append(CreateTableRow(index)).find('input,select').hide();
                            //仓库
                            $("#warehouse" + index).attr("disabled", "disabled");
                            $("#warehouse" + index).append("<option value='" + resObj[i].warehouseId + "' selected>" + resObj[i].warehouseName + "</option>");
                            $("#warehouse" + index).prev("div").text(resObj[i].warehouseName);
                            //货物类型
                            $("#goodsType" + index).attr("disabled", "disabled");
                            $("#goodsType" + index).append("<option value='" + resObj[i].goodsTypeId + "' selected>" + resObj[i].goodsType + "</option>");
                            $("#goodsType" + index).prev("div").text(resObj[i].goodsType);
                            //产品名称
                            $("#productName" + index).attr("disabled", "disabled");
                            $("#productName" + index).append("<option value='" + resObj[i].productId + "' selected>" + resObj[i].productName + "</option>");
                            $("#productName" + index).prev("div").text(resObj[i].productName);
                            //产品状态
                            $("#productStatus" + index).attr("disabled", "disabled");
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
                            $("#weight" + index).text(resObj[i].resWeight);
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
                                    $("#loss" + index).append("<option value='" + obj[i].cname + "'>" + obj[i].cname + "</option>");
                                }
                                $("#loss" + index).val("否");
                            }, "JSON");
                        }

                    } else {
                        if ("" != res.msg) {
                            tipDialog(res.msg, 3, "warning");
                        }
                    }
                });
                $.when(ajax7, ajax8, ajax9).done(function () {
                    Loading(false, "", "#leaveStockModal");
                }).fail(function () {
                    tipDialog("网络异常,请刷新！", 3, 0);
                    $('#leaveStockModal').modal('hide');
                    Loading(false, "", "#leaveStockModal");
                });
            }
        }
    }

    //提交
    $("#addLeaveStock").unbind("click");
    $("#addLeaveStock").click(function () {
        Loading(true, "正在出库", "#leaveStockModal");
        var rawData = new Array();
        var postData;
        var allData = {
            resourceId: "",
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
            saLeaveStockDetails: rawData
        };
        allData.resourceId = $("#yhResourceId").val();
        if ($("#workProcessCName").val() != "") {
            allData.produceTaskName = $.trim($("#workProcessCName").val());
            allData.workFlowId = $("#workProcessCName").next().attr("id");
            allData.produceTaskNo = $('#yhProduceTaskNo').text();
            allData.produceTaskId = $("#yhProduceTaskId").val();
        } else {
            tipDialog("生产任务不能为空！", 3, 'warning');
            Loading(false, "", "#leaveStockModal");
            return;
        }
        if ($.trim($('#yhLeavePerson').val()) != "") {
            allData.leavePerson = $.trim($('#yhLeavePerson').val());
        } else {
            tipDialog("出库申请人不得为空！", 3, 'warning');
            Loading(false, "", "#leaveStockModal");
            return;
        }
        if ($.trim($('#yhBrokerage').val()) != "") {
            allData.brokerage = $.trim($('#yhBrokerage').val());
        } else {
            tipDialog("经手人不得为空！", 3, 'warning');
            Loading(false, "", "#leaveStockModal");
            return;
        }
        if ($.trim($('#yhLeaveDate').val()) != "") {
            allData.leaveDate = $.trim($('#yhLeaveDate').val());
        } else {
            tipDialog("出库时间不得为空！", 3, 'warning');
            Loading(false, "", "#leaveStockModal");
            return;
        }
        let produceTaskId = $("#yhProduceTaskId").val();
        if (null == produceTaskId || "" == produceTaskId) {
            if ($("#yhCode").val() != null && $("#yhCode").val() != "") {
                allData.produceTaskTag = $.trim($("#yhCode").val());
            } else {
                tipDialog("生产任务标签为空！", 3, 'warning');
                Loading(false, "", "#leaveStockModal");
                return;
            }
        } else {
            if ($("#yhCode").val() != null && $("#yhCode").val() != "") {
                allData.produceTaskTag = $.trim($("#yhCode").val());
            }
        }

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
            }, num = i + 1;
            detail.warehouseId = $("#warehouse" + num + " :selected").val();
            detail.isMaterial = $("#goodsType" + num + " :selected").val();
            detail.productId = $("#productName" + num + " :selected").val();
            detail.productName = $("#grid_Field tbody>tr").eq(i).find("div:eq(2)").text();
            detail.productStatus = $("#productStatus" + num + " :selected").val();
            detail.productStatusName = $("#grid_Field tbody>tr").eq(i).find("div:eq(3)").text();
            detail.productSpecName = $("#grid_Field tbody>tr").eq(i).find("div:eq(4)").text();
            detail.batchNo = $("#batchNo" + num + " :selected").val();
            detail.weight = $("#grid_Field tbody>tr").eq(i).find("div:eq(6)").text();
            detail.outWeight = $("#grid_Field tbody>tr").eq(i).find("div:eq(7)").text();
            detail.unitId = $("#grid_Field tbody>tr").eq(i).find("td:eq(9)").find("div:eq(1)").text();
            detail.unitName = $("#grid_Field tbody>tr").eq(i).find("td:eq(9)").find("div:eq(0)").text();
            detail.loss = $("#grid_Field tbody>tr").eq(i).find("td:eq(10)").find("div:eq(0)").text();
            detail.remark = $("#grid_Field tbody>tr").eq(i).find("td:eq(11)").find("div:eq(0)").text();
            rawData.push(detail);
        }
        for (var i = 0; i < rawData.length; i++) {
            var rece = rawData[i], num = i + 1;
            if ($("#grid_Field tbody>tr").eq(i).find("div:eq(0)").text() == '' || rece.warehouseId == "") {
                tipDialog("第" + num + "行仓库为空！", 3, 'warning');
                Loading(false, "", "#leaveStockModal");
                return;
            }
            if ($("#grid_Field tbody>tr").eq(i).find("div:eq(1)").text() == '') {
                tipDialog("第" + num + "行货物类型为空！", 3, 'warning');
                Loading(false, "", "#leaveStockModal");
                return;
            }
            if (rece.productName == '') {
                tipDialog("第" + num + "行产品为空！", 3, 'warning');
                Loading(false, "", "#leaveStockModal");
                return;
            }
            if (rece.productStatusName == '') {
                tipDialog("第" + num + "行产品状态为空！", 3, 'warning');
                Loading(false, "", "#leaveStockModal");
                return;
            }
            if (rece.productSpecName == '') {
                tipDialog("第" + num + "行规格为空！", 3, 'warning');
                Loading(false, "", "#leaveStockModal");
                return;
            }
            if ($("#grid_Field tbody>tr").eq(i).find("div:eq(5)").text() == '') {
                tipDialog("第" + num + "行批次号为空！", 3, 'warning');
                Loading(false, "", "#leaveStockModal");
                return;
            }
            if (rece.weight == '') {
                tipDialog("第" + num + "行库存数量为空！", 3, 'warning');
                Loading(false, "", "#leaveStockModal");
                return;
            }
            if (rece.weight == 0) {
                tipDialog("第" + num + "行库存数量为零，无法出库！", 3, 'warning');
                Loading(false, "", "#leaveStockModal");
                return;
            }
            let reg = /^[0-9]+(.[0-9]{1,2})?$/;
            if (rece.outWeight === '' || !reg.test(rece.outWeight) || rece.outWeight == 0) {
                tipDialog("第" + num + "行出库数量为空或格式不正确(大于0)！", 3, 'warning');
                Loading(false, "", "#leaveStockModal");
                return;
            }
            if (rece.loss == '') {
                tipDialog("第" + num + "请选择是否损耗！", 3, 'warning');
                Loading(false, "", "#leaveStockModal");
                return;
            }
            let a = rece.outWeight * 1;
            let b = rece.weight * 1;
            if (a > b) {
                tipDialog("第" + num + "行出库数量大于库存数量！", 3, 'warning');
                Loading(false, "", "#leaveStockModal");
                return;
            }
            if (rece.unitName == '' || rece.unitId == '') {
                tipDialog("第" + num + "行单位为空！", 3, 'warning');
                Loading(false, "", "#leaveStockModal");
                return;
            }
        }
        allData.saLeaveStockDetails = rawData;
        postData = allData;
        $.ajax({
            type: "post", // 请求方式
            url: "${request.contextPath}/stock/insertLeaveStock.json", //url地址
            data: JSON.stringify(postData), //数据
            contentType: "application/json;charset=utf-8;",
            dataType: "json",
            success: function (res) {
                if (res.success) {
                    tipDialog(res.msg, 3, 2);
                    $('#leaveStockModal').modal('hide');
                    $(".stockGridTable").each(function () {
                        let warehoseId = $(this).attr("id").replace("gridTable", "");
                        if ("" != warehoseId && undefined != warehoseId) {
                            //重置表格
                            $("#batchNo" + warehoseId).val("");
                            $("#isMaterial" + warehoseId).val("");
                            $("#productStatus" + warehoseId).val("");
                            $("#productId" + warehoseId).val("");
                            $("#productSpecId" + warehoseId).val("");
                            $("#beginTime" + warehoseId).val("");
                            $("#endTime" + warehoseId).val("");
                            let postData = {
                                batchNo: "",
                                isMaterial: "",
                                productStatus: "",
                                productId: "",
                                productSpecId: "",
                                beginTime: "",
                                endTime: ""
                            };
                            //提交post并刷新表格
                            $("#gridTable" + warehoseId).jqGrid("setGridParam", {
                                postData: postData,
                                url: "${request.contextPath}/stock/GridJson.json?warehouseId=" + warehoseId,
                                page: 1
                            }).trigger("reloadGrid");
                        }
                    });
                    Loading(false, "", "#leaveStockModal");
                    let arr = res.obj.saLeaveStockDetails;
                    if (arr.length > 0) {
                        openPostWindow("${request.contextPath}/jasper/receiveOutGoingPdf/pdf", "leaveStock", JSON.stringify(res.obj));
                    }
                } else {
                    tipDialog(res.msg, 3, 0);
                    Loading(false, "", "#leaveStockModal");
                }
            },
            error: function () {
                tipDialog("服务器异常", 3, 0);
                Loading(false, "", "#leaveStockModal");
            }
        });
    });
</script>