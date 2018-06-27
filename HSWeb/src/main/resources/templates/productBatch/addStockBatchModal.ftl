<style>
    .btn-search {
        display: inline-block;
        padding: 3px 5px;
        border: 1px solid #ccc;
    }

    .add-num-active {
        background: #ccc;
    }

    .txtselect {
        white-space: normal;
        word-break: break-all;
        word-wrap: break-word;
        height: 100% !important;
    }
</style>
<form id="formBatch" autocomplete="off" style="margin: 1px">
    <div class="bd">
        <div class="ScrollBar" style="margin-top: 1px;">
            <div id="TableProperty">
                <table class="form">
                    <tr>
                        <td align="center" colspan="3" style="padding:5px 0">
                            <input id="seachNoBatch" autocomplete="on" type="text" style="width:90%;height:25px"
                                   placeholder="输入单据/二维码编号或用手持抢扫一扫  （如：生产任务二维码/出货单二维码）"/>
                            <input id="myBatchId" type="hidden"/>
                        </td>
                        <td>
                            <a onclick="seachNoBatch()" class="btn-search">搜索</a>
                        </td>
                    </tr>
                    <tr>
                        <th class="formTitle">
                            入库编号：
                        </th>
                        <td class="formValue">
                        <#if addStockBatchNode??>
                        ${addStockBatchNode}
                        </#if>
                            <input type="hidden" id="enterNo" value="${addStockBatchNode}"/>
                        </td>
                        <th class="formTitle">
                            入库申请人：
                        </th>
                        <td class="formValue">
                            <input id="enterPerson" autocomplete="on" type="text" class="txt" style="width: 100%">
                        </td>
                    </tr>
                    <tr>
                        <th class="formTitle">
                            经手人：
                        </th>
                        <td class="formValue">
                            <input id="brokerage" autocomplete="on" type="text" class="txt" style="width: 100%">
                        </td>
                        <th class="formTitle">
                            入库时间：
                        </th>
                        <td class="formValue">
                            <input id="enterDate" type="text" class="txt Wdate" style="width: 100%; "
                                   onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                        </td>
                    </tr>
                    <tr>
                        <th class="formTitle">
                            库存预警：
                        </th>
                        <td class="formValue">
                            <input id="stockWarn" type="text" class="txt" style="width: 100%">
                        </td>
                        <th class="formTitle">
                            成品批次号：
                        </th>
                        <td class="formValue">
                            <input id="productNo" type="text" class="txt" style="width: 100%">
                        </td>
                    </tr>
                    <tr>
                        <th class="formTitle" id="produceNameText">
                            搜索收货单编号/生产任务编号
                        </th>
                        <td class="formValue produceNoText">
                        </td>
                    </tr>
                    <tr>
                        <th class="formTitle">备注：</th>
                        <td class="formValue" colspan="3">
                            <textarea id="remark" style="width: 100%;height: 50px;"></textarea>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="addStockDetail">
            <div class="tipstitle_title settingtable bold bd todayInfoPanelTab rightPanelTitle_normal">
                <div class="tab_list_top" style="position: absolute">
                    <div id="TabTableField1" class="tab_list bd add-num-active">入库产品</div>
                    <div id="TabTableField2" class="tab_list bd">入库详情</div>
                </div>
                <div style="float: right;">
                    <div class="tools_bar_icon">
                        <div class="tools_separator" style="height: 23px; margin-right: 5px;"></div>
                        <div class="icon-botton" title="插入行" onclick="InsetTableRowEnterStock()">
                            <img src="${staticImg}/images/Icon16/table_row_insert.png"/>
                        </div>
                        <div class="icon-botton" title="清空行" onclick="EmptyTableRowEnterStock()">
                            <img src="${staticImg}/images/Icon16/table_row_delete.png"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="ScrollBar" style="margin-top: 1px;">
                <div id="TableField1" class="tabPanel">
                    <table id="grid_Field1" class="grid" style="width: 100%">
                        <thead>
                        <tr>
                            <td style="text-align: center; border-left: none;">
                                <div class="table-header">序号</div>
                            </td>
                            <td style="text-align: center;">
                                <div class="table-header">批次号</div>
                            </td>
                            <td style="text-align: center;">
                                <div class="table-header">货物类型</div>
                            </td>
                            <td style="text-align: center;">
                                <div class="table-header">产品名称</div>
                            </td>
                            <td style="text-align: center;">
                                <div class="table-header">规格</div>
                            </td>
                            <td style="text-align: center; ">
                                <div class="table-header">检验结果</div>
                            </td>
                            <td style="text-align: center;">
                                <div class="table-header">原料数量</div>
                            </td>
                            <td style="text-align: center;">
                                <div class="table-header">单位</div>
                            </td>
                        </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                </div>
                <div id="TableField2" class="tabPane2 hidden">
                    <table id="grid_Field2" class="grid" style="width: 100%">
                        <thead>
                        <tr>
                            <td style="width: 30px; text-align: center; border-left: none;">
                                <div class="table-header">编号</div>
                            </td>
                            <td style="width: 60px;text-align: center;">
                                <div class="table-header">*批次号</div>
                            </td>
                            <td style="width: 60px;text-align: center;">
                                <div class="table-header">*货物类型</div>
                            </td>
                            <td style="width: 80px; text-align: center;">
                                <div class="table-header">*产品名称</div>
                            </td>
                            <td style="width: 80px; text-align: center;">
                                <div class="table-header">*产品状态</div>
                            </td>
                            <td style="width: 100px; text-align: center;">
                                <div class="table-header">*入库规格</div>
                            </td>
                            <td style="width: 100px; text-align: center;">
                                <div class="table-header">*入库数量</div>
                            </td>
                            <td style="width: 100px; text-align: center;">
                                <div class="table-header">*单位</div>
                            </td>
                            <td style="width: 100px; text-align: center;">
                                <div class="table-header">*入库仓库</div>
                            </td>
                            <td style="width: 100px; text-align: center;">
                                <div class="table-header">备注</div>
                            </td>
                            <td style="width: 100px; text-align: center;">
                                <div class="table-header">是否入活参库</div>
                            </td>
                        </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</form>
<script>

    var RowIndex = 1;
    var TabTable = 1;
    $(document).ready(function () {
        $("#addStockNextBtn").attr("disabled", true);
        RowIndex = 1;
        TabTable = 1;
        CreateTableEnterStock();
        TableTdEvent1();
        TableTdEvent2();
    });

    function addStockCancelButton(num) {
        TabTable = 1;
        $("#TableField2").addClass("hidden");
        $("#TableField1").removeClass("hidden");
        $("#TabTableField2").removeClass("add-num-active");
        $("#TabTableField1").addClass("add-num-active");
        if (num == 1) {
            $(".holdButton").remove();
        } else if (num == 2) {
            $(".holdButton").removeClass("holdButton");
        } else if (num == 3) {
            $("#grid_Field2 tbody tr").remove();
        }
        $("#buttonOne").removeClass("hidden");
        $("#buttonTwo").addClass("hidden");
    }

    function buttonTwo() {
        let a = $("#TableField1 .grid").find(".selected").size();
        if (a > 1) {
            tipDialog("不能多选！\n", 4, 'warning');
            return;
        }
        var checkStatus = $(".selected").children("td").children("select[name =enterRelatedType]").find("option:selected").val();
        var batchNo = $(".selected").children("td").children("input[name=enterBatchNo]").val();
        var enterInWeight = $(".selected").children("td").children("select[name=enterInWeight]").find("option:selected").val();
        var enterUnitId = $(".selected").children("td").children("select[name=enterUnitId]").find("option:selected").val();
        if (batchNo != null && batchNo != "") {
            if (checkStatus != 1) {
                if (enterInWeight != null && enterInWeight != "") {
                    if (enterUnitId != null && enterUnitId != "") {
                        TabTable = 2;
                        $("#buttonTwo").removeClass("hidden");
                        $("#buttonOne").addClass("hidden");
                        $("#TableField1 ").addClass("hidden");
                        $("#TableField2").removeClass("hidden");
                        $("#TabTableField1").removeClass("add-num-active");
                        $("#TabTableField2").addClass("add-num-active");
                        InsetTableRowEnterStock();
                    } else {
                        tipDialog("请填写完整！\n", 4, 'warning');
                    }
                } else {
                    tipDialog("请填写完整！\n", 4, 'warning');
                }
            } else {
                tipDialog("待检验商品不能入库！\n", 4, 'warning');
            }
        } else {
            tipDialog("请选择批次号！\n", 4, 'warning');
        }
    }

    //插入一个空行
    function InsetTableRowEnterStock() {
        if (1 == TabTable) {
            var len = $("#grid_Field1 tbody tr").length;
            $("#grid_Field1 tbody").append(CreateTableRowEnterStock(len + 1)).find('input,select').hide();
            $("#stockUnitName" + len).prev("div").text($(this).val());
            var indexrow = 1;
            $("#grid_Field1 tbody tr").each(function () {
                $(this).find('td:eq(0)').html(indexrow);
                $(this).find("input,select").each(function () {
                    let indexId = $(this).attr("id");
                    let index = indexId.match(/^[a-z|A-Z]+/gi);
                    $(this).attr("id", index + indexrow);
                });
                indexrow++;
            })
        } else if (2 == TabTable) {
            var receiveId = $(".selected").children("td").children("input[name=enterReceiveId]").val();
            var batchNo = $(".selected").children("td").children("input[name=enterBatchNo]").val();
            var productSpecName = $(".selected").children("td").children("select[name=enterProductSpecName]").prev("div").text();
            var inWeight = $(".selected").children("td").children("select[name=enterInWeight]").find("option:selected").val();
            var unitId = $(".selected").children("td").children("select[name=enterUnitId]").find("option:selected").val();
            var unitName = $(".selected").children("td").children("select[name=enterUnitId]").find("option:selected").text();
            var len = $("#grid_Field2 tbody tr").length;
            $("#grid_Field2 tbody").append(CreateTableRow2(len + 1)).find('input,select').not('input[name=stockUnitName]').hide();
            $("#productSpecNameDetail" + (len + 1)).attr("value", productSpecName);
            $("#inWeightDetail" + (len + 1)).attr("value", inWeight);
            $("#unitIdDetail" + (len + 1)).attr("value", unitId);
            $("#unitNameDetail" + (len + 1)).attr("value", unitName);
            $("#stockReceiveId" + (len + 1)).attr("value", receiveId);
            initSearchEnterStock2(len + 1, batchNo);
            let batchId = $("#myBatchId").val();
            if (len === 0 && null !== batchId && '' !== batchId) {
                Loading(true, "正在加载", "#addStockBatchModal");
                $.post("${request.contextPath}/productBatch/getDetailByBatchId.json", {batchId: batchId}, function (res) {
                    if (res.success) {
                        let obj = res.obj;
                        $("#enterIsMaterial1").prev("div").text(obj.goodsType);
                        $("#enterIsMaterial1").html("<option value='" + obj.goodsTypeId + "' selected>" + obj.goodsType + "</option>");
                        $("#enterIsMaterial1").attr("disabled", true);
                        $("#enterProductName1").prev("div").text(obj.productName);
                        $("#enterProductName1").html("<option value='" + obj.productId + "' selected>" + obj.productName + "</option>");
                        $("#enterProductName1").attr("disabled", true);
                        $("#enterProductStatus1").prev("div").text(obj.productStatus);
                        $("#enterProductStatus1").html("<option value='" + obj.productStatusId + "' selected>" + obj.productStatus + "</option>");
                        $("#enterProductStatus1").attr("disabled", true);
                        $("#enterProductSpecName1").prev("div").text(obj.productSpec);
                        $("#enterProductSpecName1").html("<option value='" + obj.productSpec + "' selected>" + obj.productSpec + "</option>");
                        $("#enterProductSpecName1").attr("disabled", true);
                    }
                    Loading(false, "", "#addStockBatchModal");
                });
            }
            var indexrow = 1;
            $("#grid_Field2 tbody tr").each(function () {
                $(this).find('td:eq(0)').html(indexrow);
                $(this).find("input,select").each(function () {
                    let indexId = $(this).attr("id");
                    let index = indexId.match(/^[a-z|A-Z]+/gi);
                    $(this).attr("id", index + indexrow);
                });
                indexrow++;
            })
        }
    }

    //清空表格当前行
    function EmptyTableRowEnterStock() {
        if (TabTable == 1) {
            var trobj = $("#grid_Field1 tbody .selected");
            if (trobj != null && trobj != undefined && trobj.length > 0) {
                trobj.remove();
                var indexrow = 1;
                $("#grid_Field1 tbody tr").each(function () {
                    $(this).find('td:eq(0)').html(indexrow);
                    $(this).find("input,select").each(function () {
                        let indexId = $(this).attr("id");
                        let index = indexId.match(/^[a-z|A-Z]+/gi);
                        $(this).attr("id", index + indexrow);
                    });
                    indexrow++;
                })
            } else {
                tipDialog("请先选择一行！\n", 4, 'warning');
            }
            var len = $("#grid_Field1 tbody tr").length;
            if (len < 1) {
                $("#addStockNextBtn").attr("disabled", true);
            }
        } else if (TabTable == 2) {
            var trobj = $("#grid_Field2 tbody .selected");
            if (trobj != null && trobj != undefined && trobj.length > 0) {
                trobj.remove();
                var indexrow = 1;
                $("#grid_Field2 tbody tr").each(function () {
                    $(this).find('td:eq(0)').html(indexrow);
                    $(this).find("input,select").each(function () {
                        let indexId = $(this).attr("id");
                        let index = indexId.match(/^[a-z|A-Z]+/gi);
                        $(this).attr("id", index + indexrow);
                    });
                    indexrow++;
                })
            } else {
                tipDialog("请先选择一行！\n", 4, 'warning');
            }
        }
    }

    //表格点击事件
    function TableTdEvent1() {
        $("#TableField1 .grid").on("click", ".td-div", function () {
            if ($(this).parent().hasClass("selected")) {
                $(this).parent().removeClass("selected");
            } else {
                $(this).parent().addClass("selected");
            }
        });
        var grid = $("#grid_Field1 tbody");
        grid.on("click", "td", function () {
            if ($(this).find('div').length > 0) {
                let oldText = $(this).find('div').text();
                let obj = $(this).find('input,select');
                if (obj.attr('disabled')) {
                    return false;
                }
                let type = obj.attr('class');
                obj.show();
                obj.parent().find('div').html("");
                obj.trigger("focus");
                obj.blur(function () {
                    if (obj.parent().find('div').length > 0) {
                        let type = obj.attr('type');
                        let objclass = (obj.parent().find('input[type=text]').length > 0) ? obj.parent().find('input[type=text]').attr('class') : "";
                        let newText = "";
                        switch (type) {
                            case "select":
                                newText = obj.find("option:selected").text();
                                if (obj.parent().find('input[type=hidden]').length > 0) {
                                    if (obj.parent().find('input[type=hidden]').attr("class") !== "buyaogaiwo") {
                                        obj.parent().find('input[type=hidden]').val(newText);
                                    }
                                }
                                obj.hide();
                                obj.parent().find('div').html(newText);
                                break;
                            default:
                                if (objclass !== "txt icontree") {
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

    //表格点击事件
    function TableTdEvent2() {
        $("#TableField2 .grid").on("click", ".td-div", function () {
            if ($(this).parent().hasClass("selected")) {
                $(this).parent().removeClass("selected");
            } else {
                $(this).parent().addClass("selected");
            }
        });
        var grid = $("#grid_Field2 tbody");
        grid.on("click", "td", function () {
            if ($(this).find('div').length > 0) {
                var obj = $(this).find('input,select');
                if (obj.attr('disabled')) {
                    return false;
                }
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
                                    if (obj.parent().find('input[type=hidden]').attr("class") !== "buyaogaiwo") {
                                        obj.parent().find('input[type=hidden]').val(newText);
                                    }
                                }
                                obj.hide();
                                obj.parent().find('div').html(newText);
                                break;
                            default:
                                if (objclass !== "txt icontree") {
                                    newText = obj.val();
                                    obj.hide();
                                    obj.parent().find('div').html(newText);
                                }
                                break;
                        }
                    }
                });
            } else {
                let obj = $(this).find('input');
                obj.show();
            }
        })
    }

    //创建入库产品行
    function CreateTableRowEnterStock(index) {
        var tr = '';
        tr += '<tr>';
        tr += '<td class="td-div" style="width: 30px; text-align: center;border-left: none;">' + index + '</td>';
        tr += '<td style="width: 50px; text-align: center;"><div></div><input id="enterBatchNo' + index + '" type="text" maxlength="10"  class="txt enterBatchNo" name ="enterBatchNo"/>' +
                '<input id="enterReceiveId' + index + '" type="hidden" maxlength="10"  class="txt enterReceiveId" name ="enterReceiveId"/>' +
                '</td>';
        tr += '<td style="width: 60px; text-align: center;"><div></div>' + IsMaterialType(index) + '</td>';
        tr += '<td style="width: 60px; text-align: center;"><div></div>' + IsProductName(index) + '</td>';
        tr += '<td style="width: 60px; text-align: center;"><div></div>' + IsStandard(index) + '</td>';
        tr += '<td style="width: 50px; text-align: center;"><div>---</div><select id="enterRelatedType' + index + '" type="select" maxlength="10"  class="txtselect" name ="enterRelatedType"><option class="checkStatusStyle">---</option></select></td>';
        tr += '<td style="width: 50px; text-align: center;"><div></div>' + IsMaterialWeight(index) + '</td>';
        tr += '<td style="width: 60px; text-align: center;"><div></div>' + IsUnit(index) + '</td>';
        tr += '</tr>';
        return tr;
    }

    //货物类型
    function IsMaterialType(index) {
        let html = '<select id="stockIsMaterial' + index + '" name="enterIsMaterial" class="txtselect stockIsMaterial' + index + '" datacol="no" type="select" checkexpession="NotNull">';
        html += '</select>';
        return html;
    }

    //产品名称
    function IsProductName(index) {
        let html = '<select id="stockProductName' + index + '" class="txtselect buyaogaiwo productValueId' + index + '" name ="enterProductId"  datacol="no" type="select" err="产品名称" checkexpession="NotNull">';
        html += '</select>';
        return html;
    }

    //规格
    function IsStandard(index) {
        let html = '<select id="stockProductSpecName' + index + '" class="txtselect" name ="enterProductSpecName"  datacol="no" type="select" err="规格" checkexpession="NotNull">';
        html += '</select>';
        return html;
    }

    //单位
    function IsUnit(index) {
        let html = '<select id="stockUnitId' + index + '" class="txtselect" name ="enterUnitId"  datacol="no" type="select" err="单位" checkexpession="NotNull">';
        html += '</select>';
        return html;
    }

    //原料数量
    function IsMaterialWeight(index) {
        let html = '<select id="enterInWeight' + index + '" class="txtselect" name ="enterInWeight"  datacol="no" type="select" err="原料数量" checkexpession="NotNull">';
        html += '</select>';
        return html;
    }

    //创建入库详情行
    function CreateTableRow2(index) {
        var tr = '';
        tr += '<tr class="holdButton">';
        tr += '<td class="td-div" style="width: 30px; text-align: center;border-left: none;">' + index + '</td>';
        tr += '<td style="width: 50px; text-align: center;"><div></div>' +
                '<input id="stockBatchNo' + index + '" type="text" maxlength="10"  class="txt stockBatchNo noSave" name ="stockBatchNo" />' +
                '<input id="productSpecNameDetail' + index + '" type="hidden" maxlength="10"  class="txt" name ="productSpecNameDetail" />' +
                '<input id="inWeightDetail' + index + '" type="hidden" maxlength="10"  class="txt" name ="inWeightDetail" />' +
                '<input id="unitIdDetail' + index + '" type="hidden" maxlength="10"  class="txt" name ="unitIdDetail" />' +
                '<input id="unitNameDetail' + index + '" type="hidden" maxlength="10"  class="txt" name ="unitNameDetail" />' +
                '<input id="stockReceiveId' + index + '" type="hidden" maxlength="10"  class="txt" name ="stockReceiveId" />' +
                '</td>';
        tr += '<td style="width: 60px; text-align: center;"><div></div>' + enterIsMaterialType(index) + '</td>';
        tr += '<td style="width: 60px; text-align: center;"><div></div>' + enterIsProductName(index) + '</td>';
        tr += '<td style="width: 60px; text-align: center;"><div></div>' + enterProductStatus(index) + '</td>';
        tr += '<td style="width: 60px; text-align: center;"><div></div>' + enterIsStandard(index) + '</td>';
        tr += '<td style="width: 50px; text-align: center;"><div></div><input id="stockInWeight' + index + '" type="text" maxlength="10"  class="txt" name ="stockInWeight" /></td>';
        tr += '<td style="width: 60px; text-align: center;">' + enterIsUnit(index) + '</td>';
        tr += '<td style="width: 50px; text-align: center;"><div></div>' + stockWareHouse(index) + '</td>';
        tr += '<td style="width: 50px; text-align: center;"><div></div><input id="stockRemark' + index + '" type="text" maxlength="10"  class="txt" name ="stockRemark"/></td>';
        tr += '<td style="width: 50px; text-align: center;"><div></div><select type="select" id="enterIsSeaCucumber' + index + '" maxlength="10"  class="txtselect" name ="enterIsSeaCucumber"><option value="1">是</option><option value="2" selected>否</option></select></td>';
        tr += '</tr>';
        return tr;
    }

    //货物类型
    function enterIsMaterialType(index) {
        var html = '<select id="enterIsMaterial' + index + '" name="stockIsMaterial" class="txtselect stockIsMaterial' + index + '" datacol="no" type="select" checkexpession="NotNull">';
        html += '</select>';
        return html;
    }

    //产品名称
    function enterIsProductName(index) {
        var html = '<select id="enterProductName' + index + '" class="txtselect buyaogaiwo productValueId' + index + '" name ="stockProductId"  datacol="no" type="select" err="产品名称" checkexpession="NotNull">';
        html += '</select>';
        return html;
    }

    //规格
    function enterIsStandard(index) {
        var html = '<select id="enterProductSpecName' + index + '" name="stockProductSpecName" class="txtselect"  datacol="no" type="select" err="规格" checkexpession="NotNull">';
        html += '</select>';
        return html;
    }

    //产品状态
    function enterProductStatus(index) {
        var html = '<select id="enterProductStatus' + index + '" name="enterProductStatus" class="txtselect"  datacol="no" type="select" err="产品状态" checkexpession="NotNull">';
        html += '</select>';
        return html;
    }

    //单位
    function enterIsUnit(index) {
        var html = '<input id="enterUnitName' + index + '" autocomplete="off" name="stockUnitName" type="text" class="txt icontree"/>';
        html += '<input id="enterUnitId' + index + '" name="stockUnitId" type="hidden" class="buyaogaiwo txt icontree"/>';
        return html;
    }

    //仓库类型
    function stockWareHouse(index) {
        var html = '<select id="stockWareHouse' + index + '" name="stockWareHouse" class="txtselect"  datacol="no" type="select" err="产品状态" checkexpession="NotNull">';
        html += '</select>';
        return html;
    }

    //默认创建一行
    function CreateTableEnterStock() {
        for (var i = 0; i < 1; i++) {
            $("#grid_Field1 tbody").append(CreateTableRowEnterStock(RowIndex));
            RowIndex++;
        }
        $("#grid_Field1 tbody tr").find('input,select').hide();
    }

    //入库信息select相互关联
    function initSearchEnterStock(num, batchNo) {
        Loading(true, "正在搜索", "#addStockBatchModal");
        $.post("${request.contextPath}/stock/getReceiveDetailByBatchNo.json", {batchNo: batchNo}, function (res) {
            $("#stockIsMaterial" + num).find("option").remove();
            var resObj = res.obj;
            $("#stockIsMaterial" + num).append("<option value=''>==请选择==</option>");
            if (resObj != null && resObj != "") {
                for (i in resObj) {
                    $("#stockIsMaterial" + num).append("<option value='" + resObj[i].goodsTypeId + "'>" + resObj[i].goodsType + "</option>")
                }
            } else {
                tipDialog(res.msg, 2, 'warning');
            }
            Loading(false, "", "#addStockBatchModal");
        }, "JSON");
        loadProductEnterStock("#stockIsMaterial" + num, "#stockProductName" + num, "#stockProductSpecName" + num, "#enterInWeight" + num, "#stockUnitId" + num, batchNo)
    }

    //入库详情select相互关联
    function initSearchEnterStock2(num, batchNo) {
        if (batchNo != "") {
            $("#stockBatchNo" + num).attr({
                "value": batchNo,
                "disabled": "disabled"
            });
            $("#stockBatchNo" + num).prev("div").text(batchNo);//批次号
            var productNo = $.trim($("#productNo").val());
            if (productNo != null && productNo != "") {
                $(".stockBatchNo").attr("value", productNo);
                $(".stockBatchNo").prev("div").text(productNo);
            }
            $.post("${request.contextPath}/receiveManagement/getGoodsType.json", "", function (res) {
                $("#enterIsMaterial" + num).find("option").remove();
                $("#enterIsMaterial" + num).append("<option value=''>==请选择==</option>");
                for (i in res) {
                    $("#enterIsMaterial" + num).append("<option value='" + res[i].type + "'>" + res[i].typeName + "</option>")
                }
            }, "JSON");
            loadProduct2("#enterIsMaterial" + num, "#enterProductName" + num, "#enterProductSpecName" + num, "#enterUnitName" + num, "#enterUnitId" + num);
            productStatusList(num, 15);
            getAllWareHouse(num);
            $("#stockInWeight" + num).keyup(function () {
                var tmptxt = $(this).val();
                $(this).val(tmptxt.replace(/[^\d.]/g, ""));
            }).bind("paste", function () {
                var tmptxt = $(this).val();
                $(this).val(tmptxt.replace(/[^\d.]/g, ""));
            }).css("ime-mode", "disabled");
            var isSeaCucumberText = $("#enterIsSeaCucumber" + num).find("option:selected").text();
            $("#enterIsSeaCucumber" + num).prev("div").text(isSeaCucumberText);
        }
    }

    function loadProductEnterStock(typeId, productId, specId, materialWeight, unit, batchNo) {
        let typeEl = $("" + typeId);
        let productEl = $("" + productId);
        let specEl = $("" + specId);
        let weightEl = $("" + materialWeight);
        let unitEl = $("" + unit);
        typeEl.prev("div").text("");
        productEl.prev("div").text("");
        specEl.prev("div").text("");
        weightEl.prev("div").text("");
        unitEl.prev("div").text("");
        typeEl.empty();
        productEl.empty();
        specEl.empty();
        weightEl.empty();
        unitEl.empty();
        typeEl.unbind("click");
        typeEl.click(function () {
            Loading(true, "正在加载", "#addStockBatchModal");
            productEl.prev("div").text("");
            specEl.prev("div").text("");
            weightEl.prev("div").text("");
            unitEl.prev("div").text("");
            if (typeEl.val() === "") {
                productEl.find("option").remove();
                productEl.append("<option value=''>==请先选择货物类型==</option>");
                Loading(false, "", "#addStockBatchModal");
            } else {
                productEl.find("option").remove();
                var goodsTypeId = typeEl.val();
                $.post("${request.contextPath}/stock/getReceiveDetailByGoodsType.json", {
                    batchNo: batchNo,
                    goodsTypeId: goodsTypeId
                }, function (res) {
                    if (res.success) {
                        productEl.find("option").remove();
                        productEl.append("<option value=''>==请选择==</option>");
                        var obj = res.obj;
                        for (i in obj) {
                            productEl.append("<option value='" + obj[i].productId + "'>" + obj[i].productName + "</option>")
                        }
                    } else {
                        tipDialog(res.msg, 3, 'warning');
                    }
                    Loading(false, "", "#addStockBatchModal");
                }, "JSON");
            }
        });
        productEl.unbind("click");
        productEl.click(function () {
            Loading(true, "正在加载", "#addStockBatchModal");
            specEl.prev("div").text("");
            weightEl.prev("div").text("");
            unitEl.prev("div").text("");
            specEl.empty();
            weightEl.empty();
            unitEl.empty();
            if (productEl.val() === '') {
                specEl.find("option").remove();
                specEl.append("<option value=''>请先选择产品</option>");
                Loading(false, "", "#addStockBatchModal");
            } else {
                specEl.find("option").remove();
                specEl.append("<option value=''>请选择</option>");
                var goodsTypeId = typeEl.val();
                var productId = productEl.val();
                $.post("${request.contextPath}/stock/getReceiveDetailByProductId.json", {
                    batchNo: batchNo,
                    goodsTypeId: goodsTypeId,
                    productId: productId
                }, function (res) {
                    if (res.success) {
                        specEl.find("option").remove();
                        specEl.append("<option value=''>==请选择==</option>");
                        var obj = res.obj;
                        for (i in obj) {
                            specEl.append("<option value='" + obj[i].productSpecName + "'>" + obj[i].productSpecName + "</option>")
                        }
                    } else {
                        tipDialog(res.msg, 2, 'warning');
                    }
                    Loading(false, "", "#addStockBatchModal");
                }, "JSON");
            }
        });
        specEl.unbind("click");
        specEl.click(function () {
            Loading(true, "正在加载", "#addStockBatchModal");
            weightEl.prev("div").text("");
            unitEl.prev("div").text("");
            weightEl.empty();
            unitEl.empty();
            let goodsTypeId = typeEl.val();
            let productId = productEl.val();
            let productSpecName = specEl.val();
            if (specEl.val() === "") {
                weightEl.find("option").remove();
                weightEl.append("<option value=''>请先选择规格</option>");
                Loading(false, "", "#addStockBatchModal");
            } else {
                $.post("${request.contextPath}/stock/getMaterialWeightBySpec.json", {
                    batchNo: batchNo,
                    goodsTypeId: goodsTypeId,
                    productId: productId,
                    productSpecName: productSpecName
                }, function (res) {
                    if (res.success) {
                        let obj = res.obj;
                        weightEl.find("option").remove();
                        weightEl.append("<option value=''>==请选择==</option>");
                        for (i in obj) {
                            weightEl.append("<option value='" + obj[i].materialWeight + "'>" + obj[i].materialWeight + "</option>");
                        }
                    } else {
                        tipDialog(res.msg, 2, 'warning');
                    }
                    Loading(false, "", "#addStockBatchModal");
                }, "JSON");
            }
        });

        weightEl.unbind("click");
        weightEl.click(function () {
            Loading(true, "正在加载", "#addStockBatchModal");
            unitEl.prev("div").text("");
            unitEl.empty();
            let goodsTypeId = typeEl.val();
            let productId = productEl.val();
            let productSpecName = specEl.val();
            let materialWeight = weightEl.val();
            if (weightEl.val() === '') {
                unitEl.find('option').remove();
                unitEl.append("<option value=''>请先选择原料数量</option>");
                Loading(false, "", "#addStockBatchModal");
            } else {
                $.post("${request.contextPath}/stock/getUnitByWeight.json", {
                    batchNo: batchNo,
                    goodsTypeId: goodsTypeId,
                    productId: productId,
                    productSpecName: productSpecName,
                    materialWeight: materialWeight
                }, function (res) {
                    if (res.success) {
                        unitEl.find("option").remove();
                        unitEl.append("<option value=''>==请选择==</option>");
                        let obj = res.obj;
                        for (let i = 0; i < obj.length; i++) {
                            unitEl.append("<option value='" + obj[i].unitId + "'>" + obj[i].unitName + "</option>");
                        }
                        $("#addStockNextBtn").removeAttr("disabled");
                    } else {
                        tipDialog(res.msg, 2, 'warning');
                    }
                    Loading(false, "", "#addStockBatchModal");
                }, "JSON");
            }
        });
    }

    function loadProduct2(typeId, productId, specId, unitName, unitId) {
        var typeEl = $("" + typeId);
        var productEl = $("" + productId);
        var specEl = $("" + specId);
        var priseEl = $("" + unitId);
        typeEl.unbind("click");
        typeEl.click(function () {
            productEl.prev().text("");
            specEl.prev().text("");
            priseEl.prev().text("");
            productEl.empty();
            specEl.empty();
            priseEl.empty();
            if (typeEl.val() == "") {
                productEl.find("option").remove();
                productEl.append("<option value='' selected>请先选择类型</option>");
            } else {
                productEl.find("option").remove();
                var type = typeEl.val();
                $.post("${request.contextPath}/receiveManagement/getProductByType.json", {type: type}, function (res) {
                    if (res.success) {
                        productEl.find("option").remove();
                        productEl.append("<option value=''>==请选择==</option>");
                        var obj = res.obj;
                        for (i in obj) {
                            productEl.append("<option value='" + obj[i].productTypeId + "'>" + obj[i].productTypeName + "</option>")
                        }
                    } else {
                        tipDialog(res.msg, 3, 'warning');
                    }
                }, "JSON");
            }
        });
        productEl.unbind("click");
        productEl.click(function () {
            specEl.prev().text("");
            priseEl.prev().text("");
            specEl.empty();
            priseEl.empty();
            if (productEl.val() == "") {
                specEl.find("option").remove();
                specEl.append("<option value=''>请先选择产品</option>");
            } else {
                specEl.find("option").remove();
                var type = productEl.val();
                $.post("${request.contextPath}/receiveManagement/getProductSpecName.json", {productTypeId: type}, function (res) {
                    if (res.success) {
                        specEl.find("option").remove();
                        specEl.append("<option value='' selected>==请选择==</option>");
                        var obj = res.obj;
                        for (i in obj) {
                            specEl.append("<option value='" + obj[i].productSpecification + "'>" + obj[i].productSpecification + "</option>")
                        }
                    } else {
                        tipDialog(res.msg, 2, 'warning');
                    }
                }, "JSON");
            }
        })
        $.post("${request.contextPath}/receiveManagement/getEnterprise.json", {}, function (res) {
            if (res.success) {
                priseEl.find("option").remove();
                priseEl.append("<option value='' selected>==请选择==</option>");
                var obj = res.obj;
                for (i in obj) {
                    priseEl.append("<option value='" + obj[i].enterpriseId + "'>" + obj[i].cName + "</option>")
                }
            } else {
                tipDialog(res.msg, 2, 'warning');
            }
        }, "JSON");
        $("" + unitName).focus(function () {
            var objId = this.id;
            comboBoxTree(objId, "182px");
            var itemtree = {
                onnodeclick: function (item) {
                    $("" + unitId).attr("value", item.id);
                    $("" + unitName).attr("value", item.text);
                },
                url: "${request.contextPath}/receiveManagement/getUnitName.json"
            };
            $("#comboBoxTree" + objId).treeview(itemtree);
            $("#comboBoxTree" + objId + "_" + GetQuery('KeyValue').replace(/-/g, '_')).parent('li').remove();
        });
    }

    //查询仓库类型
    function getAllWareHouse(index) {
        $.post("${request.contextPath}/warehouse/getAllZsWarehouseList.json", {}, function (res) {
            if (res.success) {
                $("#stockWareHouse" + index).find("option").remove();
                $("#stockWareHouse" + index).append("<option value=''>==请选择==</option>")
                let obj = res.obj;
                for (i in obj) {
                    if (obj[i].warehouseType != 1) {
                        $("#stockWareHouse" + index).append("<option value='" + obj[i].warehouseId + "'>" + obj[i].cname + "</option>")
                    }
                }
            }
        })
    }

    function seachNoBatch() {
        Loading(true, "正在加载数据...", "#addStockBatchModal");
        let seachNo = $.trim($("#seachNoBatch").val());
        let valueTest1 = /^\d{10}$/;
        $("#grid_Field1 tbody tr").remove();
        if (seachNo != "") {
            if (seachNo.indexOf("NO.SH") != -1) {
                $.post("${request.contextPath}/stock/geReceiveByReceiveNo.json", {receiveNo: seachNo}, function (res) {
                    var resObj = res.obj;
                    if (res.success) {
                        $("#addStockNextBtn").removeAttr("disabled");
                        let taskNameInput = '<input type="hidden" id="produceTaskName"/>';
                        let taskNoInput = '<input id="produceTaskNo" type="hidden" class="txt" style="width: 100px">';
                        let relatedIdInput = '<input id="relatedId" type="hidden"/>';
                        let relatedTypeInput = '<input id="relatedType" type="hidden"/>';
                        $("#produceNameText").text("收货单编号：");
                        $("#produceNameText").append(taskNameInput);
                        $("#produceTaskName").attr("value", "收货单编号");
                        $(".produceNoText").text(seachNo);
                        $(".produceNoText").append(relatedIdInput + relatedTypeInput + taskNoInput);
                        $("#produceTaskNo").attr("value", seachNo);
                        if (resObj[0].receiveId != null) {
                            $("#relatedId").attr("value", resObj[0].receiveId);
                            $("#relatedType").attr("value", 6);
                        }
                        for (var i = 0; i < resObj.length; i++) {
                            let index = i + 1;
                            $("#grid_Field1 tbody").append(CreateTableRowEnterStock(index)).find('input,select').hide();
                            $("#enterReceiveId" + index).attr("value", resObj[i].receiveDetailId);
                            $("#enterBatchNo" + index).attr({
                                "value": resObj[i].batchNo,
                                "disabled": "disabled"
                            });
                            $("#enterBatchNo" + index).prev("div").text(resObj[i].batchNo);//批次号
                            $("#stockIsMaterial" + index).append("<option value='" + resObj[i].goodsTypeId + "' selected>" + resObj[i].goodsType + "</option>");//货物类型
                            $("#stockIsMaterial" + index).attr("disabled", true);
                            $("#stockIsMaterial" + index).prev("div").text(resObj[i].goodsType);
                            $("#stockProductName" + index).append("<option value='" + resObj[i].productId + "' selected>" + resObj[i].productName + "</option>");//产品
                            $("#stockProductName" + index).attr("disabled", true);
                            $("#stockProductName" + index).prev("div").text(resObj[i].productName);
                            $("#stockProductSpecName" + index).append("<option value='" + resObj[i].productSpecName + "' selected>" + resObj[i].productSpecName + "</option>");//规格
                            $("#stockProductSpecName" + index).attr("disabled", true);
                            $("#stockProductSpecName" + index).prev("div").text(resObj[i].productSpecName);
                            $("#enterInWeight" + index).append("<option value='" + resObj[i].weight + "' selected>" + resObj[i].weight + "</option>");
                            $("#enterInWeight" + index).attr("disabled", true);
                            $("#enterInWeight" + index).prev("div").text(resObj[i].weight);
                            $("#stockUnitId" + index).append("<option value='" + resObj[i].unitId + "' selected>" + resObj[i].unitName + "</option>");
                            $("#stockUnitId" + index).attr("disabled", true);
                            $("#stockUnitId" + index).prev("div").text(resObj[i].unitName);
                            $("#enterRelatedType" + index).find("option").remove();
                            if (resObj[i].testResult == 0) {
                                $("#enterRelatedType" + index).append("<option value='0'>未报送</option>");
                                let resultText = $("#enterRelatedType" + index).find("option").text();
                                $("#enterRelatedType" + index).prev("div").text(resultText);
                            } else if (resObj[i].testResult == 1) {
                                $("#enterRelatedType" + index).append("<option value='1'>待检验</option>");
                                let resultText = $("#enterRelatedType" + index).find("option").text();
                                $("#enterRelatedType" + index).prev("div").text(resultText);
                            } else if (resObj[i].testResult == 2) {
                                $("#enterRelatedType" + index).append("<option value='2'>合格</option>");
                                let resultText = $("#enterRelatedType" + index).find("option").text();
                                $("#enterRelatedType" + index).prev("div").text(resultText);
                            } else if (resObj[i].testResult == 3) {
                                $("#enterRelatedType" + index).append("<option value='3'>不合格</option>");
                                let resultText = $("#enterRelatedType" + index).find("option").text();
                                $("#enterRelatedType" + index).prev("div").text(resultText);
                            }
                        }
                        Loading(false, "", "#addStockBatchModal");
                    } else {
                        $("#addStockNextBtn").attr("disabled", true);
                        $("#grid_Field1 tbody tr").remove();
                        tipDialog(res.msg, 2, 'warning');
                        Loading(false, "", "#addStockBatchModal");
                    }
                });
            } else if (valueTest1.test(seachNo)) {
                //通过二维码回显详情
                $.post("${request.contextPath}/stock/showRecordByQrCode.json", {qrCode: seachNo}, function (res) {
                    if (res.success) {
                        var resObj = res.obj;
                        if (resObj != null && resObj.length > 0) {
                            $("#addStockNextBtn").removeAttr("disabled");
                            let taskNameInput = '<input type="hidden" id="produceTaskName"/>';
                            let taskNoInput = '<input id="produceTaskNo" type="hidden" class="txt" style="width: 100px">';
                            let relatedIdInput = '<input id="relatedId" type="hidden"/>';
                            let relatedTypeInput = '<input id="relatedType" type="hidden"/>';
                            $("#produceNameText").text("生产任务编号：");
                            $("#produceNameText").append(taskNameInput);
                            $("#produceTaskName").attr("value", "生产任务编号");
                            $(".produceNoText").text(resObj[0].produceTaskNo);
                            $(".produceNoText").append(relatedIdInput + relatedTypeInput + taskNoInput);
                            $("#produceTaskNo").attr("value", resObj[0].produceTaskNo);
                            if (resObj[0].produceTaskId != null) {
                                $("#relatedId").attr("value", resObj[0].produceTaskId);
                                $("#relatedType").attr("value", 5);
                            }
                            if (resObj != null) {
                                for (var i = 0; i < resObj.length; i++) {
                                    let index = i + 1;
                                    $("#grid_Field1 tbody").append(CreateTableRowEnterStock(index)).find('input,select').hide();
                                    $("#enterBatchNo" + index).attr({
                                        "value": resObj[i].batchNo,
                                        "disabled": "disabled"
                                    });
                                    $("#enterBatchNo" + index).prev("div").text(resObj[i].batchNo);//批次号
                                    $("#stockIsMaterial" + index).append("<option value='" + resObj[i].isMaterial + "' selected>" + resObj[i].goodsType + "</option>");//货物类型
                                    $("#stockIsMaterial" + index).attr("disabled", true);
                                    $("#stockIsMaterial" + index).prev("div").text(resObj[i].goodsType);
                                    $("#stockProductName" + index).append("<option value='" + resObj[i].productId + "' selected>" + resObj[i].productName + "</option>");//产品
                                    $("#stockProductName" + index).attr("disabled", true);
                                    $("#stockProductName" + index).prev("div").text(resObj[i].productName);
                                    $("#stockProductSpecName" + index).append("<option value='" + resObj[i].productSpecName + "' selected>" + resObj[i].productSpecName + "</option>");//规格
                                    $("#stockProductSpecName" + index).attr("disabled", true);
                                    $("#stockProductSpecName" + index).prev("div").text(resObj[i].productSpecName);
                                    $("#enterInWeight" + index).append("<option value='" + resObj[i].weight + "' selected>" + resObj[i].weight + "</option>");
                                    $("#enterInWeight" + index).attr("disabled", true);
                                    $("#enterInWeight" + index).prev("div").text(resObj[i].weight);
                                    $("#stockUnitId" + index).append("<option value='" + resObj[i].unitId + "' selected>" + resObj[i].unitName + "</option>");
                                    $("#stockUnitId" + index).attr("disabled", true);
                                    $("#stockUnitId" + index).prev("div").text(resObj[i].unitName);
                                }
                            } else {
                                $("#grid_Field1 tbody tr").remove();
                                tipDialog(res.msg, 2, 'warning');
                            }
                            Loading(false, "", "#addStockBatchModal");
                        } else {
                            $("#addStockNextBtn").attr("disabled", true);
                            $("#grid_Field1 tbody tr").remove();
                            tipDialog("输入的编号不存在，请重新输入", 2, 'warning');
                            Loading(false, "", "#addStockBatchModal");
                        }
                    } else {
                        $("#addStockNextBtn").attr("disabled", true);
                        $("#grid_Field1 tbody tr").remove();
                        tipDialog(res.msg, 2, 'warning');
                        Loading(false, "", "#addStockBatchModal");
                    }
                })
            } else if (seachNo.indexOf("NO.CK") != -1) {
                $.post("${request.contextPath}/stock/geLeaveRecordByLeaveNo.json", {leaveNo: seachNo}, function (res) {
                    if (res.success) {
                        var resObj = res.obj;
                        if (null != resObj && resObj != '') {
                            $("#addStockNextBtn").removeAttr("disabled");
                            let taskNameInput = '<input type="hidden" id="produceTaskName"/>';
                            let taskNoInput = '<input id="produceTaskNo" type="hidden" class="txt" style="width: 100px">';
                            let relatedIdInput = '<input id="relatedId" type="hidden"/>';
                            let relatedTypeInput = '<input id="relatedType" type="hidden"/>';
                            $("#produceNameText").text("出库单编号：");
                            $("#produceNameText").append(taskNameInput);
                            $("#produceTaskName").attr("value", "出库单编号");
                            $(".produceNoText").text(seachNo);
                            $(".produceNoText").append(relatedIdInput + relatedTypeInput + taskNoInput);
                            $("#produceTaskNo").attr("value", seachNo);
                        } else {
                            $("#addStockNextBtn").attr("disabled", true);
                            $("#grid_Field1 tbody tr").remove();
                            tipDialog(res.msg, 2, 'warning');
                        }
                    } else {
                        $("#addStockNextBtn").attr("disabled", true);
                        $("#grid_Field1 tbody tr").remove();
                        tipDialog(res.msg, 2, 'warning');
                    }
                });
                $.post("${request.contextPath}/stock/showRecord.json", {leaveNo: seachNo}, function (res) {
                    if (res.success) {
                        var resObj = res.obj;
                        if (resObj != null) {
                            let taskNameInput = '<input type="hidden" id="produceTaskName"/>';
                            let taskNoInput = '<input id="produceTaskNo" type="hidden" class="txt" style="width: 100px">';
                            let relatedIdInput = '<input id="relatedId" type="hidden"/>';
                            let relatedTypeInput = '<input id="relatedType" type="hidden"/>';
                            if (resObj[0].produceTaskId != null) {
                                $("#relatedId").attr("value", resObj[0].produceTaskId);
                                $("#relatedType").attr("value", 5);
                            }
                            if (resObj != null) {
                                $("#addStockNextBtn").removeAttr("disabled");
                                for (var i = 0; i < resObj.length; i++) {
                                    let index = i + 1;
                                    $("#grid_Field1 tbody").append(CreateTableRowEnterStock(index)).find('input,select').hide();
                                    $("#enterBatchNo" + index).attr({
                                        "value": resObj[i].batchNo,
                                        "disabled": "disabled"
                                    });
                                    $("#enterBatchNo" + index).prev("div").text(resObj[i].batchNo);//批次号
                                    $("#stockIsMaterial" + index).append("<option value='" + resObj[i].isMaterial + "' selected>" + resObj[i].goodsType + "</option>");//货物类型
                                    $("#stockIsMaterial" + index).attr("disabled", true);
                                    $("#stockIsMaterial" + index).prev("div").text(resObj[i].goodsType);
                                    $("#stockProductName" + index).append("<option value='" + resObj[i].productId + "' selected>" + resObj[i].productName + "</option>");//产品
                                    $("#stockProductName" + index).attr("disabled", true);
                                    $("#stockProductName" + index).prev("div").text(resObj[i].productName);
                                    $("#stockProductSpecName" + index).append("<option value='" + resObj[i].productSpecName + "' selected>" + resObj[i].productSpecName + "</option>");//规格
                                    $("#stockProductSpecName" + index).attr("disabled", true);
                                    $("#stockProductSpecName" + index).prev("div").text(resObj[i].productSpecName);
                                    $("#enterInWeight" + index).append("<option value='" + resObj[i].weight + "' selected>" + resObj[i].weight + "</option>");
                                    $("#enterInWeight" + index).attr("disabled", true);
                                    $("#enterInWeight" + index).prev("div").text(resObj[i].weight);
                                    $("#stockUnitId" + index).append("<option value='" + resObj[i].unitId + "' selected>" + resObj[i].unitName + "</option>");
                                    $("#stockUnitId" + index).attr("disabled", true);
                                    $("#stockUnitId" + index).prev("div").text(resObj[i].unitName);
                                }
                            } else {
                                $("#addStockNextBtn").attr("disabled", true);
                                $("#grid_Field1 tbody tr").remove();
                                tipDialog(res.msg, 2, 'warning');
                            }
                        }
                    }
                    Loading(false, "", "#addStockBatchModal");

                })
            } else {
                $.post("${request.contextPath}/stock/showRecordByProduceTaskNoByEnterStock.json", {produceTaskNo: seachNo}, function (res) {
                    if (res.success) {
                        var resObj = res.obj;
                        if (resObj != null && resObj.length > 0) {
                            $("#addStockNextBtn").removeAttr("disabled");
                            let taskNameInput = '<input type="hidden" id="produceTaskName"/>';
                            let taskNoInput = '<input id="produceTaskNo" type="hidden" class="txt" style="width: 100px">';
                            let relatedIdInput = '<input id="relatedId" type="hidden"/>';
                            let relatedTypeInput = '<input id="relatedType" type="hidden"/>';
                            $("#produceNameText").text("生产任务编号：");
                            $("#produceNameText").append(taskNameInput);
                            $("#produceTaskName").attr("value", "生产任务编号");
                            $(".produceNoText").text(seachNo);
                            $(".produceNoText").append(relatedIdInput + relatedTypeInput + taskNoInput);
                            $("#produceTaskNo").attr("value", seachNo);
                            if (resObj[0].produceTaskId != null) {
                                $("#relatedId").attr("value", resObj[0].produceTaskId);
                                $("#relatedType").attr("value", 5);
                            }
                            if (resObj != null) {
                                for (var i = 0; i < resObj.length; i++) {
                                    let index = i + 1;
                                    $("#grid_Field1 tbody").append(CreateTableRowEnterStock(index)).find('input,select').hide();
                                    $("#enterBatchNo" + index).attr({
                                        "value": resObj[i].batchNo,
                                        "disabled": "disabled"
                                    });
                                    $("#enterBatchNo" + index).prev("div").text(resObj[i].batchNo);//批次号
                                    $("#stockIsMaterial" + index).append("<option value='" + resObj[i].isMaterial + "' selected>" + resObj[i].goodsType + "</option>");//货物类型
                                    $("#stockIsMaterial" + index).attr("disabled", true);
                                    $("#stockIsMaterial" + index).prev("div").text(resObj[i].goodsType);
                                    $("#stockProductName" + index).append("<option value='" + resObj[i].productId + "' selected>" + resObj[i].productName + "</option>");//产品
                                    $("#stockProductName" + index).attr("disabled", true);
                                    $("#stockProductName" + index).prev("div").text(resObj[i].productName);
                                    $("#stockProductSpecName" + index).append("<option value='" + resObj[i].productSpecName + "' selected>" + resObj[i].productSpecName + "</option>");//规格
                                    $("#stockProductSpecName" + index).attr("disabled", true);
                                    $("#stockProductSpecName" + index).prev("div").text(resObj[i].productSpecName);
                                    $("#enterInWeight" + index).append("<option value='" + resObj[i].weight + "' selected>" + resObj[i].weight + "</option>");
                                    $("#enterInWeight" + index).attr("disabled", true);
                                    $("#enterInWeight" + index).prev("div").text(resObj[i].weight);
                                    $("#stockUnitId" + index).append("<option value='" + resObj[i].unitId + "' selected>" + resObj[i].unitName + "</option>");
                                    $("#stockUnitId" + index).attr("disabled", true);
                                    $("#stockUnitId" + index).prev("div").text(resObj[i].unitName);
                                }
                            } else {
                                $("#grid_Field1 tbody tr").remove();
                                tipDialog(res.msg, 2, 'warning');
                            }
                            Loading(false, "", "#addStockBatchModal");
                        } else {
                            $("#addStockNextBtn").attr("disabled", true);
                            $("#grid_Field1 tbody tr").remove();
                            tipDialog("产品详情不存在", 2, 'warning');
                            Loading(false, "", "#addStockBatchModal");
                        }
                    } else {
                        $("#addStockNextBtn").attr("disabled", true);
                        $("#grid_Field1 tbody tr").remove();
                        tipDialog(res.msg, 2, 'warning');
                        Loading(false, "", "#addStockBatchModal");
                    }
                })
            }
        } else {
            Loading(false, "", "#addStockBatchModal");
            $("#addStockNextBtn").attr("disabled", true);
            tipDialog("请输入编号信息", 2, 'warning');
        }
    }

    function productStatusList(index, num) {
        $.post("${request.contextPath}/stock/getStockIsMaterialByCodeAndName.json", {id: num}, function (res) {
            if (res.success) {
                $("#enterProductStatus" + index).find("option").remove();
                $("#enterProductStatus" + index).append("<option value='' selected>===请选择===</option>");
                let obj = res.obj;
                for (i in obj) {
                    $("#enterProductStatus" + index).append("<option value='" + obj[i].dataDictionaryDetailsId + "'>" + obj[i].cname + "</option>");
                }
            } else {
                tipDialog(res.msg, 3, 'warning');
            }
        })
    }

    $("#grid_Field1").on("blur", ".enterBatchNo", function () {
        var batchNo = $.trim($(this).val());
        var num = $.trim($(this).parent().prev().text());
        initSearchEnterStock(num, batchNo);
    });

    $("#productNo").on("blur", function () {
        var productNo = $.trim($(this).val());
        var batchNo = $(".selected").children("td").children("input[name=enterBatchNo]").val();
        if (productNo != null && productNo != "") {
            $(".stockBatchNo").attr("value", productNo);
            $(".stockBatchNo").prev("div").text(productNo);
        }
    })
</script>