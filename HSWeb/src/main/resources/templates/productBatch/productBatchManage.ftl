

<div class="tools_bar leftline rightline" style="margin-bottom: 0px; margin: 1px;">
    <div class="PartialButton">
        <a id="lr-replace" title="刷新当前(Ctrl+Q)" onclick="Replace()" class="tools_btn">
                <span>
                    <b class="btn-reload">刷新</b>
                </span>
        </a>
        <div class="tools_separator"></div>
    <@shiro.hasPermission name="/productBatch/productImport">
        <a title="批次导入" onclick="batchImport()" class="tools_btn tabOneA">
                <span>
                    <b class="btn-import">批次导入</b>
                </span>
        </a>
        <div class="tools_separator tabOneA"></div>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/productBatch/addEnterStockBatch">
        <a title="新建入库" onclick="addStockBatch()" class="tools_btn tabOneA">
            <span>
                <b class="btn-detail">新建入库</b>
            </span>
        </a>
        <div class="tools_separator tabOneA"></div>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/productBatch/seeDeliveryDetails">
        <a title="查看发货详情" onclick="seeDeliveryDetails()" class="tools_btn tabTwoA">
                <span>
                    <b class="btn-detail">查看发货详情</b>
                </span>
        </a>
        <div class="tools_separator tabTwoA"></div>
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
        <a href="#tabOne" onclick="tabOneA()" id="oneA" data-toggle="tab"> 已采集 </a>
    </li>
    <li>
        <a href="#tabTwo" onclick="tabTwoA()" id="twoA"  data-toggle="tab"> 已打包 </a>
    </li>
</ul>
<div class="rightline" style="margin: 1px; margin-top: -1px;">
    <div class="tab-content">
        <div class="tab-pane active" id="tabOne">
            <div class="bottomline QueryArea" style="margin: 0 1px;">
                <table border="0" class="form-find" style="height: 45px;">
                    <tr id="selectParameter">
                        <td>
                            产品名称：<input id="productName" name="productName" type="text" class="input-txt" style="width: 100px">
                        </td>
                        <td>
                            产品线：
                            <select id="productLine" name="productLine" class="txtselect" >
                            <#if companyProducts??>
                                <option value="">==请选择==</option>
                                <#list companyProducts as companyProduct>
                                    <option value="${companyProduct.productLineId}">${companyProduct.productLine}</option>
                                </#list>
                            <#else >
                                <option value="">==无数据==</option>
                            </#if>
                            </select>
                        </td>
                        <td>
                            产品大类：
                            <select id="productBigType" name="productBigType" class="txtselect" >
                                <option value="">==请先选择产品线==</option>
                            </select>
                        </td>
                        <td>
                            产品小类：
                            <select id="productSmallType" name="productSmallType" class="txtselect" >
                                <option value="">==请先选择产品大类==</option>
                            </select>
                        </td>
                    </tr>
                    <tr id="selectParameter">
                        <td>
                            成品批次号：<input id="batchCode" name="batchCode" type="text" class="input-txt" style="width: 100px">
                        </td>
                        <td>
                            产品规格：<input id="productFormat" name="productFormat" type="text" class="input-txt" style="width: 100px">
                        </td>
                        <td>
                            <input id="batch-Search" type="button" class="btnSearch" value="查 询">
                            <input id="batch-Clear" type="button" class="btnSearch" value="重 置">
                        </td>
                    </tr>
                </table>
            </div>
            <table id="gridTable" ></table>
            <div id="gridPager" ></div>
        </div>
        <div class="tab-pane" id="tabTwo">
            <div class="bottomline QueryArea" style="margin: 0 1px;">
                <table border="0" class="form-find" style="height: 45px;">
                    <tr>
                        <td>
                            产品名称：<input id="productNameTwo" name="productNameTwo" type="text" class="input-txt" style="width: 100px">
                        </td>
                        <td>
                            产品线：
                            <select id="productLineTwo" name="productLineTwo" class="txtselect" >
                            <#if companyProducts??>
                                <option value="">==请选择==</option>
                                <#list companyProducts as companyProduct>
                                    <option value="${companyProduct.productLineId}">${companyProduct.productLine}</option>
                                </#list>
                            <#else >
                                <option value="">==无数据==</option>
                            </#if>
                            </select>
                        </td>
                        <td>
                            产品大类：
                            <select id="productBigTypeTwo" name="productBigTypeTwo" class="txtselect" >
                                <option value="">==请先选择产品线==</option>
                            </select>
                        </td>
                        <td>
                            产品小类：
                            <select id="productSmallTypeTwo" name="productSmallTypeTwo" class="txtselect" >
                                <option value="">==请先选择产品大类==</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            成品批次号：<input id="batchCodeTwo" name="batchCodeTwo" type="text" class="input-txt" style="width: 100px">
                        </td>
                        <td>
                            产品规格：<input id="productFormatTwo" name="productFormatTwo" type="text" class="input-txt" style="width: 100px">
                        </td>
                        <td>
                            <input id="batch-Search-Two" type="button" class="btnSearch" value="查 询">
                            <input id="batch-Clear-Two" type="button" class="btnSearch" value="重 置">
                        </td>
                    </tr>
                </table>
            </div>
            <table id="gridTableTwo" ></table>
            <div id="gridPagerTwo" ></div>
        </div>
    </div>
</div>


<@shiro.hasPermission name="/productBatch/seeDeliveryDetails">
<!--查看发货详情-->
<div id="deliveryDetailsModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">查看发货详情</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button id="deliveryDetail" type="button" data-dismiss="modal" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>

<!--产品导入-->
<div id="batchImportModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">批次导入</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="batchImportBtn" type="button" class="btn green">确认</button>
    </div>
</div>

<@shiro.hasPermission name="/productBatch/addEnterStockBatch">
<!--新建出库-->
<div id="addStockBatchModal" class="modal fade " data-width="1050" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="addStockCancelButton(3)">
            <label>&times;</label>
        </button>
        <h4 class="modal-title">新建入库</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <div id="buttonOne">
            <button type="button" data-dismiss="modal" class="btn btn-outline dark" onclick="addStockCancelButton(3)">取消
            </button>
            <button type="button" class="btn green" id="addStockNextBtn" onclick="buttonTwo()">下一步</button>
        </div>
        <div id="buttonTwo" class="hidden">
            <button type="button" class="btn dark" onclick="addStockCancelButton(1)">取消</button>
            <button type="button" class="btn green" onclick="addStockCancelButton(2)">保存</button>
            <button id="addStockAndRecord" type="button" onclick="addStockAndRecord()" class="btn green">确认并打印入库单
            </button>
        </div>
    </div>
</div>
</@shiro.hasPermission>
<script type="text/javascript">

    function tabOneA() {
        $("#gridTable").trigger("reloadGrid");
        $(".tabTwoA").each(function () {
            $(this).addClass("hidden");
        });
        $(".tabOneA").each(function () {
            $(this).removeClass("hidden");
        });
        $("twoA").parent().removeClass("active");
        $("oneA").parent().addClass("active");
    }

    function tabTwoA() {
        $("#gridTableTwo").trigger("reloadGrid");
        $(".tabOneA").each(function () {
            $(this).addClass("hidden");
        });
        $(".tabTwoA").each(function () {
            $(this).removeClass("hidden");
        });
        $("oneA").parent().removeClass("active");
        $("twoA").parent().addClass("active");
    }


<@shiro.hasPermission name="/productBatch/seeDeliveryDetails">
<!--查看发货详情-->
    function seeDeliveryDetails() {
        let id = GetJqGridRowValue("#gridTableTwo", "batchId");
            if (IsChecked(id)) {
                $("#deliveryDetailsModal").modal({
                    remote: "${request.contextPath}/productBatch/deliveryDetailsModal.htm?batchId=" + id
                });

            }
    }
</@shiro.hasPermission>


    var resourceId;
    $(document).ready(function () {
        GetGrid();GetGridTwo();
        gridPagerStyle();
        resourceId = top.$("#ModuleId").val();
    });

    //加载表格
    function GetGrid() {
        $("#gridTable").jqGrid({
            url: "${request.contextPath}/productBatch/GridJson.json",
            datatype: "json",
            height: $(window).height() - 215,
            width: $(window).width(),
            colModel: [
                {label: '编号', name: 'batchId', index: 'batchId', width: 80, hidden: true},
                {label: "批次编号", name: "batchCode", index: "batchCode", width: 80},
                {label: "批次名称", name: "batchName", index: "batchName", width: 100},
                {label: "任务编号", name: "taskCode", index: "taskCode", width: 150},
                {label: "任务名称", name: "taskName", index: "taskName", width: 120},
                {label: "产品编码", name: "productCode", index: "productCode", width: 80},
                {label: "产品名称", name: "productName", index: "productName", width: 120},
                {label: "产品线", name: "productLine", index: "productLine", width: 100},
                {label: "产品大类", name: "productBigType", index: "productBigType", width: 120},
                {label: "产品小类", name: "productSmallType", index: "productSmallType", width: 120},
                {label: "产品规格", name: "productFormat", index: "productFormat", width: 120},
                {label: '标签绑定量', name: 'labelNumber', index: 'labelNumber', width: 100},
                {label: '包装比例', name: 'batchPack', index: 'batchPack', width: 120},
                {label: '箱码数量', name: 'boxNumber', index: 'boxNumber', width: 90},
                {label: '导入时间', name: 'createDate', index: 'createDate', width: 140}
            ],
            pagerpos: "right",
            recordpos: "left",
            viewrecords: true,
            rownumbers: true,
            rowNum: 15,
            rowList: [15, 30, 50, 100, 500],
            pager: "#gridPager",
            jsonReader: {
                root: "root", page: "page", total: "total", records: "records"
            },
            /*sortname: 'updateDate',
            sortorder: 'desc',*/
            shrinkToFit: false,
            gridview: true
        });
    }
    function GetGridTwo() {
        $("#gridTableTwo").jqGrid({
            url: "${request.contextPath}/productBatch/IsPackGridJson.json",
            datatype: "json",
            height: $(window).height() - 215,
            width: $(window).width(),
            colModel: [
                {label: '编号', name: 'batchId', index: 'batchId', width: 80, hidden: true },
                {label: "批次编号", name: "batchCode", index: "batchCode", width: 100},
                {label: "批次名称", name: "batchName", index: "batchName", width: 100 },
                {label: "产品编号", name: "productCode", index: "productCode", width: 120 },
                {label: "产品大类", name: "productBigType", index: "productBigType", width: 120 },
                {label: "产品小类", name: "productSmallType", index: "productSmallType", width: 120 },
                {label: "产品名称", name: "productName", index: "productName", width: 120 },
                {label: "产品规格", name: "productFormat", index: "productFormat", width: 120 },
                {label: '标签绑定量', name: 'labelNumber', index: 'labelNumber', width: 100 },
                {label: '剩余数量', name: 'notPackNumber', index: 'notPackNumber', width: 120 },
                {label: '打包数量', name: 'packNumber', index: 'packNumber', width: 100 },
                {label: '最新打包时间', name: 'lastPackTime', index: 'lastPackTime', width: 140 },
            ],
            pagerpos : "right",
            recordpos : "left",
            viewrecords: true,
            rownumbers: true,
            rowNum: 15,
            rowList: [15, 30, 50, 100, 500],
            pager: "#gridPagerTwo",
            jsonReader:{
                root:"root",page:"page",total:"total",records:"records"
            },
            shrinkToFit: false,
            gridview: true
        });
    }

    //分页布局
    function gridPagerStyle() {
        let width = document.getElementById("gridPager_right").offsetWidth + 5;
        $("#gridPager_right").attr("style", "width:" + width);
    }

    $("#productLine").on("change", function () {
        Loading(true, "正在获取产品大类...");
        let html = "<option value=''>==请先选择产品线==</option>";
        let txtProductTypeHtml = "<option value=''>==请先选择产品大类==</option>";
        let productBigType = $("#productBigType");
        let productSmallType = $("#productSmallType");
        let txtProductLineId = $("#productLine :selected").val();
        productSmallType.html(txtProductTypeHtml);
        if ("" != txtProductLineId) {
            $.post('${request.contextPath}/companyProduct/getZsCompanyProductByProductType.json', {productLineId: txtProductLineId}, function (result) {
                if (result.success) {
                    for (let i = 0; i < result.obj.length; i++) {
                        html += '<option value="' + result.obj[i].productCategoryId + '">' + result.obj[i].productCategory + '</option>';
                    }
                }
                productBigType.html(html);
                Loading(false);
            }, "JSON");
        } else {
            productBigType.html(html);
        }
    });

    $("#productBigType").on("change", function () {
        Loading(true, "正在获取产品小类...");
        var html = "<option value=''>==请先选择产品大类==</option>";
        var productSmallType = $("#productSmallType");
        var productBigType = $("#productBigType :selected").val();
        if ("" != productBigType) {
            $.post('${request.contextPath}/companyProduct/getZsCompanyProductByProductType.json', {productCategoryId: productBigType}, function (result) {
                if (result.success) {
                    for (var i = 0; i < result.obj.length; i++) {
                        html += '<option value="' + result.obj[i].productTypeId + '">' + result.obj[i].productTypeName + '</option>';
                    }
                }
                productSmallType.html(html);
                Loading(false);
            }, "JSON");
        } else {
            productSmallType.html(html);
        }
    });


    //批次导入
    function batchImport() {
        $("#batchImportModal").modal({
            remote: "${request.contextPath}/productBatch/batchImportModal.htm"
        });
    }

    $("#batchImportBtn").unbind("click");
    $("#batchImportBtn").click(function () {
        Loading(true, "正在对比、导入数据...", "#batchImportModal");
        String.prototype.endWith = function (endStr) {
            var d = this.length - endStr.length;
            return (d >= 0 && this.lastIndexOf(endStr) == d)
        };
        var batchImportFile = $("#batchImportFile").val();
        if ("" == batchImportFile) {
            tipDialog("请选择要导入的批次文件", 4, 'a');
            Loading(false, "", "#batchImportModal");
            return false;
        } else {
            if (!(batchImportFile.endWith(".txt"))) {
                tipDialog("请上传txt的文件!", 4, 'a');
                Loading(false, "", "#batchImportModal");
                return false;
            }
        }
        var options = {
            type: "post",
            cache: false,
            url: '${request.contextPath}/productBatch/importBatch.json?resourceId=' + resourceId,
            //importBatch.json',
            success: function (res) {
                if (res.success) {
                    tipDialog(res.msg, 4, '1');
                    Loading(false, "", "#batchImportModal");
                    $("#gridTable").trigger("reloadGrid");
                    $("#batchImportModal").modal("hide");
                } else {
                    tipDialog(res.msg, 4, '0');
                    $("#excelErrorTr").removeClass("hidden");
                    Loading(false, "", "#batchImportModal");
                }
            },
            error: function (xhr, ajaxOptions, thrownError) {
                tipDialog("请求错误!", 4, '0');
                Loading(false, "", "#batchImportModal");
            }
        };
        $('#batchImportForm').ajaxSubmit(options);

    });


    $("#batch-Search").unbind("click");
    $("#batch-Search").click(function () {
        Loading(true, "正在提交数据...");
        let productName = $("#productName").val();
        var productLine = $("#productLine :selected").val();
        if (productLine != null && productLine != "") {
            productLine = $("#productLine :selected").text();
        }
        var productBigType = $("#productBigType :selected").val();
        if (productBigType != null && productBigType != "") {
            productBigType = $("#productLine :selected").text();
        }
        var productSmallType = $("#productSmallType :selected").val();
        if (productSmallType != null && productSmallType != "") {
            productSmallType = $("#productLine :selected").text();
        }
        let batchCode = $("#batchCode").text();
        let productFormat = $("#productFormat").text();
        let postData = {
            productName: productName,
            productLine: productLine,
            productBigType: productBigType,
            productSmallType: productSmallType,
            batchCode: batchCode,
            productFormat: productFormat
        };
        $("#gridTable").jqGrid("setGridParam", {
            postData: postData,
            url: "${request.contextPath}/productBatch/GridJson.json",
            page: 1
        }).trigger("reloadGrid");
        Loading(false);
    });


    $("#batch-Clear").unbind("click");
    $("#batch-Clear").click(function () {
        Loading(true, "正在提交数据...");
        //重置表格
        let productName = $("#productName").val("");
        $("#productLine option:first").prop("selected", 'selected');
        $("#productBigType option:first").prop("selected", 'selected');
        $("#productSmallType option:first").prop("selected", 'selected');
//        let productBigType = $("#productBigType :selected").val("");
//        let productSmallType = $("#productSmallType :selected").val("");
        let batchCode = $("#batchCode").val("");
        let productFormat = $("#productFormat").val("");
        let postData = {
            productName: "",
            productLine: "",
            productBigType: "",
            productSmallType: "",
            batchCode: "",
            productFormat: ""
        };
        $("#gridTable").jqGrid("setGridParam", {
            url: "${request.contextPath}/productBatch/GridJson.json",
            postData: postData,
            page: 1
        }).trigger("reloadGrid");
        Loading(false);
    });

    $("#batch-Search-Two").unbind("click");
    $("#batch-Search-Two").click(function () {
        Loading(true, "正在提交数据...");
        let productName = $("#productNameTwo").val();
        var productLine = $("#productLineTwo :selected").val();
        if (productLine != null && productLine != "") {
            productLine = $("#productLineTwo :selected").text();
        }
        var productBigType = $("#productBigTypeTwo :selected").val();
        if (productBigType != null && productBigType != "") {
            productBigType = $("#productLineTwo :selected").text();
        }
        var productSmallType = $("#productSmallTypeTwo :selected").val();
        if (productSmallType != null && productSmallType != "") {
            productSmallType = $("#productLineTwo :selected").text();
        }
        let batchCode = $("#batchCodeTwo").text();
        let productFormat = $("#productFormatTwo").text();
        let postData = {
            productName: productName,
            productLine: productLine,
            productBigType: productBigType,
            productSmallType: productSmallType,
            batchCode: batchCode,
            productFormat: productFormat
        };
        $("#gridTableTwo").jqGrid("setGridParam", {
            postData: postData,
            url: "${request.contextPath}/productBatch/IsPackGridJson.json",
            page: 1
        }).trigger("reloadGrid");
        Loading(false);
    });

    $("#batch-Clear-Two").unbind("click");
    $("#batch-Clear-Two").click(function () {
        Loading(true, "正在提交数据...");
        //重置表格
        let productName = $("#productNameTwo").val("");
        $("#productLineTwo option:first").prop("selected", 'selected');
        $("#productBigTypeTwo option:first").prop("selected", 'selected');
        $("#productSmallTypeTwo option:first").prop("selected", 'selected');
//        let productBigType = $("#productBigType :selected").val("");
//        let productSmallType = $("#productSmallType :selected").val("");
        let batchCode = $("#batchCodeTwo").val("");
        let productFormat = $("#productFormatTwo").val("");
        let postData = {
            productName: "",
            productLine: "",
            productBigType: "",
            productSmallType: "",
            batchCode: "",
            productFormat: ""
        };
        $("#gridTableTwo").jqGrid("setGridParam", {
            url: "${request.contextPath}/productBatch/IsPackGridJson.json",
            postData: postData,
            page: 1
        }).trigger("reloadGrid");
        Loading(false);
    });
    //新建入库
    <@shiro.hasPermission name="/productBatch/addEnterStockBatch">
    function addStockBatch() {
        $("#addStockBatchModal").modal({
            remote: "${request.contextPath}/productBatch/addStockBatchModal.htm"
        });

        $('#addStockBatchModal').on('loaded.bs.modal', function (e) {
            function leaveStockModalReload() {
                let batchId = GetJqGridRowValue("#gridTable", "batchId");
                let batchCode = GetJqGridRowValue("#gridTable", "batchCode");
                let taskCode = GetJqGridRowValue("#gridTable", "taskCode");
                if (batchId !== undefined) {
                    $("#seachNoBatch").val(taskCode);
                    $("#myBatchId").val(batchId);
                    seachNoBatchReload(taskCode, batchCode);
                }
            }

            function clearNoNumReload(obj) {
                obj.value = obj.value.replace(/[^\d.]/g, "");  //清除“数字”和“.”以外的字符
                obj.value = obj.value.replace(/\.{2,}/g, "."); //只保留第一个. 清除多余的
                obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
                obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3');//只能输入两个小数
                if (obj.value.indexOf(".") < 0 && obj.value != "") {//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额
                    obj.value = parseFloat(obj.value);
                }
            }

            //创建入库产品行
            function CreateTableRowEnterStockReload(index) {
                var tr = '';
                tr += '<tr>';
                tr += '<td class="td-div" style="width: 30px; text-align: center;border-left: none;">' + index + '</td>';
                tr += '<td style="width: 50px; text-align: center;"><div></div><input id="enterBatchNo' + index + '" type="text" maxlength="10"  class="txt enterBatchNo" name ="enterBatchNo"/>' +
                        '<input id="enterReceiveId' + index + '" type="hidden" maxlength="10"  class="txt enterReceiveId" name ="enterReceiveId"/>' +
                        '</td>';
                tr += '<td style="width: 60px; text-align: center;"><div></div>' + IsMaterialTypeReload(index) + '</td>';
                tr += '<td style="width: 60px; text-align: center;"><div></div>' + IsProductNameReload(index) + '</td>';
                tr += '<td style="width: 60px; text-align: center;"><div></div>' + IsStandardReload(index) + '</td>';
                tr += '<td style="width: 50px; text-align: center;"><div>---</div><select id="enterRelatedType' + index + '" type="select" maxlength="10"  class="txtselect" name ="enterRelatedType"><option class="checkStatusStyle">---</option></select></td>';
                tr += '<td style="width: 50px; text-align: center;"><div></div>' + IsMaterialWeightReload(index) + '</td>';
                tr += '<td style="width: 60px; text-align: center;"><div></div>' + IsUnitReload(index) + '</td>';
                tr += '</tr>';
                return tr;
            }

            //货物类型
            function IsMaterialTypeReload(index) {
                let html = '<select id="stockIsMaterial' + index + '" name="enterIsMaterial" class="txtselect stockIsMaterial' + index + '" datacol="no" type="select" checkexpession="NotNull">';
                html += '</select>';
                return html;
            }

            //产品名称
            function IsProductNameReload(index) {
                let html = '<select id="stockProductName' + index + '" class="txtselect buyaogaiwo productValueId' + index + '" name ="enterProductId"  datacol="no" type="select" err="产品名称" checkexpession="NotNull">';
                html += '</select>';
                return html;
            }

            //规格
            function IsStandardReload(index) {
                let html = '<select id="stockProductSpecName' + index + '" class="txtselect" name ="enterProductSpecName"  datacol="no" type="select" err="规格" checkexpession="NotNull">';
                html += '</select>';
                return html;
            }

            //单位
            function IsUnitReload(index) {
                let html = '<select id="stockUnitId' + index + '" class="txtselect" name ="enterUnitId"  datacol="no" type="select" err="单位" checkexpession="NotNull">';
                html += '</select>';
                return html;
            }

            //原料数量
            function IsMaterialWeightReload(index) {
                let html = '<select id="enterInWeight' + index + '" class="txtselect" name ="enterInWeight"  datacol="no" type="select" err="原料数量" checkexpession="NotNull">';
                html += '</select>';
                return html;
            }

            //创建入库详情行
            function CreateTableRow2Reload(index) {
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
                tr += '<td style="width: 60px; text-align: center;"><div></div>' + enterIsMaterialTypeReload(index) + '</td>';
                tr += '<td style="width: 60px; text-align: center;"><div></div>' + enterIsProductNameReload(index) + '</td>';
                tr += '<td style="width: 60px; text-align: center;"><div></div>' + enterProductStatusReload(index) + '</td>';
                tr += '<td style="width: 60px; text-align: center;"><div></div>' + enterIsStandardReload(index) + '</td>';
                tr += '<td style="width: 50px; text-align: center;"><div></div><input id="stockInWeight' + index + '" type="text" maxlength="10"  class="txt" name ="stockInWeight" /></td>';
                tr += '<td style="width: 60px; text-align: center;">' + enterIsUnitReload(index) + '</td>';
                tr += '<td style="width: 50px; text-align: center;"><div></div>' + stockWareHouseReload(index) + '</td>';
                tr += '<td style="width: 50px; text-align: center;"><div></div><input id="stockRemark' + index + '" type="text" maxlength="10"  class="txt" name ="stockRemark"/></td>';
                tr += '<td style="width: 50px; text-align: center;"><div></div><select type="select" id="enterIsSeaCucumber' + index + '" maxlength="10"  class="txtselect" name ="enterIsSeaCucumber"><option value="1">是</option><option value="2" selected>否</option></select></td>';
                tr += '</tr>';
                return tr;
            }

            //货物类型
            function enterIsMaterialTypeReload(index) {
                var html = '<select id="enterIsMaterial' + index + '" name="stockIsMaterial" class="txtselect stockIsMaterial' + index + '" datacol="no" type="select" checkexpession="NotNull">';
                html += '</select>';
                return html;
            }

            //产品名称
            function enterIsProductNameReload(index) {
                var html = '<select id="enterProductName' + index + '" class="txtselect buyaogaiwo productValueId' + index + '" name ="stockProductId"  datacol="no" type="select" err="产品名称" checkexpession="NotNull">';
                html += '</select>';
                return html;
            }

            //规格
            function enterIsStandardReload(index) {
                var html = '<select id="enterProductSpecName' + index + '" name="stockProductSpecName" class="txtselect"  datacol="no" type="select" err="规格" checkexpession="NotNull">';
                html += '</select>';
                return html;
            }

            //产品状态
            function enterProductStatusReload(index) {
                var html = '<select id="enterProductStatus' + index + '" name="enterProductStatus" class="txtselect"  datacol="no" type="select" err="产品状态" checkexpession="NotNull">';
                html += '</select>';
                return html;
            }

            //单位
            function enterIsUnitReload(index) {
                var html = '<input id="enterUnitName' + index + '" autocomplete="off" name="stockUnitName" type="text" class="txt icontree"/>';
                html += '<input id="enterUnitId' + index + '" name="stockUnitId" type="hidden" class="buyaogaiwo txt icontree"/>';
                return html;
            }

            //仓库类型
            function stockWareHouseReload(index) {
                var html = '<select id="stockWareHouse' + index + '" name="stockWareHouse" class="txtselect"  datacol="no" type="select" err="产品状态" checkexpession="NotNull">';
                html += '</select>';
                return html;
            }

            //默认创建一行
            function CreateTableEnterStockReload() {
                for (var i = 0; i < 1; i++) {
                    $("#grid_Field1 tbody").append(CreateTableRowEnterStock(RowIndex));
                    RowIndex++;
                }
                $("#grid_Field1 tbody tr").find('input,select').hide();
            }

            //入库信息select相互关联
            function initSearchEnterStockReload(num, batchNo) {
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
            function initSearchEnterStock2Reload(num, batchNo) {
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

            function loadProductEnterStockReload(typeId, productId, specId, materialWeight, unit, batchNo) {
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

            function loadProduct2Reload(typeId, productId, specId, unitName, unitId) {
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
                });
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

            //表格点击事件
            function TableTdEventBatch1Reload() {
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
                })
            }

            //表格点击事件
            function TableTdEventBatch2Reload() {
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

            //搜索
            function seachNoBatchReload(seachNo, batchCode) {
                Loading(true, "正在加载数据...", "#addStockBatchModal");
                if (seachNo != "") {
                    $.post("${request.contextPath}/stock/showRecordByProduceTaskNoByEnterStock.json", {produceTaskNo: seachNo}, function (res) {
                        if (res.success) {
                            $("#grid_Field1 tbody tr").remove();
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
                                $("#productNo").val(batchCode);
                                if (resObj[0].produceTaskId != null) {
                                    $("#relatedId").attr("value", resObj[0].produceTaskId);
                                    $("#relatedType").attr("value", 5);
                                }
                                if (resObj != null) {
                                    for (var i = 0; i < resObj.length; i++) {
                                        let index = i + 1;
                                        $("#grid_Field1 tbody").append(CreateTableRowEnterStockReload(index)).find('input,select').hide();
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
                                    tipDialog(res.msg, 2, 'warning');
                                }
                                Loading(false, "", "#addStockBatchModal");
                            } else {
                                $("#addStockNextBtn").attr("disabled", true);
                                tipDialog("产品详情不存在", 2, 'warning');
                                Loading(false, "", "#addStockBatchModal");
                            }
                        } else {
                            $("#addStockNextBtn").attr("disabled", true);
                            tipDialog(res.msg, 2, 'warning');
                            Loading(false, "", "#addStockBatchModal");
                        }
                    });
                } else {
                    Loading(false, "", "#addStockBatchModal");
                    $("#addStockNextBtn").attr("disabled", true);
                    tipDialog("请输入编号信息", 2, 'warning');
                }
            }

            leaveStockModalReload();
            TableTdEventBatch1Reload();
            TableTdEventBatch2Reload();
        });
    }

    //新建入库
    function addStockAndRecord() {
        Loading(true, "正在加载数据...", "#addStockAndRecordModal");
        let enterStock = {
            enterNo: "",
            enterPerson: "",
            brokerage: "",
            enterDate: "",
            stockWarn: "",
            produceTaskNo: "",
            remark: "",
            relatedId: "",
            relatedType: ""
        };
        let relatedId = $.trim($("#relatedId").val());
        let relatedType = $.trim($("#relatedType").val());
        enterStock.relatedId = relatedId;
        enterStock.relatedType = relatedType;
        let enterStockDetailList = new Array();
        let stockList = new Array();
        let length1 = $("#grid_Field1 tbody>tr").length;
        let length2 = $("#grid_Field2 tbody>tr").length;
        let allData = {
            enterStock: enterStock,
            enterStockDetailList: enterStockDetailList,
            stockList: stockList
        };
        let enterNo = $.trim($("#enterNo").val());
        if (enterNo != "") {
            enterStock.enterNo = enterNo;
        } else {
            tipDialog("新建入库-入库编号有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
            Loading(false, "", "#addStockAndRecordModal");
            return false;
        }
        let enterPerson = $.trim($("#enterPerson").val());
        if (enterPerson != "") {
            enterStock.enterPerson = enterPerson;
        } else {
            tipDialog("新建入库-入库申请人有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
            Loading(false, "", "#addStockAndRecordModal");
            return false;
        }
        let brokerage = $.trim($("#brokerage").val());
        if (brokerage != "") {
            enterStock.brokerage = brokerage;
        } else {
            tipDialog("新建入库-经手人有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
            Loading(false, "", "#addStockAndRecordModal");
            return false;
        }
        let enterDate = $.trim($("#enterDate").val());
        if (enterDate != "") {
            enterStock.enterDate = enterDate;
        } else {
            tipDialog("新建入库-入库时间有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
            Loading(false, "", "#addStockAndRecordModal");
            return false;
        }
        let stockWarn = $.trim($("#stockWarn").val());
        if (stockWarn != "") {
            enterStock.stockWarn = stockWarn;
        } else {
            tipDialog("新建入库-库存预警有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
            Loading(false, "", "#addStockAndRecordModal");
            return false;
        }
        enterStock.produceTaskNo = $.trim($("#produceTaskNo").val());
        enterStock.remark = $.trim($("#remark").val());

        //入库详情
        for (let i = 0; i < length2; i++) {
            let field = {
                stockReceiveId: "",//收货单详情ID
                batchNo: "",//批次号
                isMaterial: "",//货物类型
                productId: "",//产品ID
                productName: "",//产品名称
                productStatus: "",//产品状态
                productSpecName: "",//规格名称
                unitId: "",//重量单位ID
                unitName: "",//重量单位名称
                warehouseId: "",//入库仓库ID
                //loss: "",//损耗
                remark: "",//备注
                listIndex: "",
                isSeaCucumber: "",//是否入活参库
                inCreateDate: ""
            };
            let field2 = {
                batchNo: "",//批次号
                isMaterial: "",//货物类型
                productId: "",//产品ID
                productName: "",//产品名称
                productStatus: "",//产品状态
                productSpecName: "",//入库规格名称
                unitId: "",//入库单位ID
                unitName: "",//入库单位名称
                warehouseId: "",//入库仓库ID
                //loss: "",//损耗
                remark: "",//备注
                listIndex: "",
                isSeaCucumber: "",//是否入活参库
                goodsBatchNo: "",//成品批次号
                enterProductSpecName: "",//规格名称
                materialWeight: "",//原料数量
                enterUnitId: "",//单位ID
                enterUnitName: "",//单位名称
                relatedId: "",//相关ID
                relatedType: ""//相关类型
            };
            field.listIndex = i + 1;
            field.inCreateDate = enterStock.enterDate;//入库时间
            field.stockReceiveId = $("#grid_Field2 tbody>tr").eq(i).find("input[name=stockReceiveId]").val();
            field2.relatedId = relatedId;
            field2.relatedType = relatedType;
            let goodsBathcNo = $.trim($("#productNo").val());
            if ("" != goodsBathcNo) {
                field2.goodsBatchNo = goodsBathcNo;
            }
            let stockBatchNo = $("#grid_Field2 tbody>tr").eq(i).find("input[name=stockBatchNo]").val();
            if ("" != stockBatchNo) {
                field.batchNo = stockBatchNo;
                field2.batchNo = stockBatchNo;
            } else {
                tipDialog("操作记录第" + (i + 1) + "行，入库详情-批次号有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                Loading(false, "", "#addStockAndRecordModal");
                return false;
            }
            let stockIsMaterial = $("#grid_Field2 tbody>tr").eq(i).find("select[name=stockIsMaterial] :selected").attr("value");
            if ("" != stockIsMaterial) {
                field.isMaterial = stockIsMaterial;
                field2.isMaterial = stockIsMaterial;
            } else {
                tipDialog("操作记录第" + (i + 1) + "行，入库详情-货物类型有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                Loading(false, "", "#addStockAndRecordModal");
                return false;
            }
            let stockProductId = $("#grid_Field2 tbody>tr").eq(i).find("select[name=stockProductId] :selected").attr("value");
            let stockProductName = $("#grid_Field2 tbody>tr").eq(i).find("select[name=stockProductId] :selected").text();
            if ("" != stockProductId && "" != stockProductName) {
                field.productId = stockProductId;
                field.productName = stockProductName;
                field2.productId = stockProductId;
                field2.productName = stockProductName;
            } else {
                tipDialog("操作记录第" + (i + 1) + "行，入库详情-产品有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                Loading(false, "", "#addStockAndRecordModal");
                return false;
            }
            let stockProductStatus = $("#grid_Field2 tbody>tr").eq(i).find("select[name=enterProductStatus] :selected").attr("value");
            if ("" != stockProductStatus) {
                field.productStatus = stockProductStatus;
                field2.productStatus = stockProductStatus;
            } else {
                tipDialog("操作记录第" + (i + 1) + "行，入库详情-产品状态有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                Loading(false, "", "#addStockAndRecordModal");
                return false;
            }
            let stockProductSpecName = $("#grid_Field2 tbody>tr").eq(i).find("select[name=stockProductSpecName] :selected").attr("value");
            if ("" != stockProductSpecName) {
                field.productSpecName = stockProductSpecName;
                field2.productSpecName = stockProductSpecName;
            } else {
                tipDialog("操作记录第" + (i + 1) + "行，入库详情-规格有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                Loading(false, "", "#addStockAndRecordModal");
                return false;
            }
            let stockInWeight = $("#grid_Field2 tbody>tr").eq(i).find("input[name=stockInWeight]").val();
            if ("" != stockInWeight) {
                field.inWeight = stockInWeight;
                field2.inWeight = stockInWeight;
            } else {
                tipDialog("操作记录第" + (i + 1) + "行，入库详情-入库数量有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                Loading(false, "", "#addStockAndRecordModal");
                return false;
            }
            let stockUnitId = $("#grid_Field2 tbody>tr").eq(i).find("input[name=stockUnitId]").val();
            let stockUnitName = $("#grid_Field2 tbody>tr").eq(i).find("input[name=stockUnitName]").val();
            if ("" != stockUnitId && "" != stockUnitName) {
                field.unitId = stockUnitId;
                field.unitName = stockUnitName;
                field2.unitId = stockUnitId;
                field2.unitName = stockUnitName;
            } else {
                tipDialog("操作记录第" + (i + 1) + "行，入库详情-单位有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                Loading(false, "", "#addStockAndRecordModal");
                return false;
            }
            let stockWarehouseId = $("#grid_Field2 tbody>tr").eq(i).find("select[name=stockWareHouse] :selected").attr("value");
            if ("" != stockWarehouseId) {
                field.warehouseId = stockWarehouseId;
                field2.warehouseId = stockWarehouseId;
            } else {
                tipDialog("操作记录第" + (i + 1) + "行，入库详情-仓库类型有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                Loading(false, "", "#addStockAndRecordModal");
                return false;
            }
            /*
            let loss = $("#grid_Field2 tbody>tr").eq(i).find("select[name=loss] :selected").attr("value");
            if ("" !== loss) {
                field.loss = loss;
                field2.loss = loss;
            } else {
                tipDialog("操作记录第" + (i + 1) + "行，入库详情-是否损耗有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                Loading(false, "", "#addStockAndRecordModal");
                return;
            }
            */
            let isSeaCucumber = $("#grid_Field2 tbody>tr").eq(i).find("select[name=enterIsSeaCucumber] :selected").attr("value");
            if ("" != isSeaCucumber) {
                field.isSeaCucumber = isSeaCucumber;
                field2.isSeaCucumber = isSeaCucumber;
            } else {
                tipDialog("操作记录第" + (i + 1) + "行，入库详情-是否入活参库有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                Loading(false, "", "#addStockAndRecordModal");
                return false;
            }
            let enterProductSpecName = $("#grid_Field2 tbody>tr").eq(i).find("input[name=productSpecNameDetail]").val();
            if ("" != enterProductSpecName) {
                field2.enterProductSpecName = enterProductSpecName;
            } else {
                tipDialog("操作记录第" + (i + 1) + "行，入库产品-规格名称有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                Loading(false, "", "#addStockAndRecordModal");
                return false;
            }
            let inWeightDetail = $("#grid_Field2 tbody>tr").eq(i).find("input[name=inWeightDetail]").val();
            if ("" != inWeightDetail) {
                field2.materialWeight = inWeightDetail;
            } else {
                tipDialog("操作记录第" + (i + 1) + "行，入库产品-原料有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                Loading(false, "", "#addStockAndRecordModal");
                return false;
            }
            let unitIdDetail = $("#grid_Field2 tbody>tr").eq(i).find("input[name=unitIdDetail]").val();
            if ("" != unitIdDetail) {
                field2.enterUnitId = unitIdDetail;
            } else {
                tipDialog("操作记录第" + (i + 1) + "行，入库产品-规格名称有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                Loading(false, "", "#addStockAndRecordModal");
                return false;
            }
            let unitNameDetail = $("#grid_Field2 tbody>tr").eq(i).find("input[name=unitNameDetail]").val();
            if ("" != unitNameDetail) {
                field2.enterUnitName = unitNameDetail;
            } else {
                tipDialog("操作记录第" + (i + 1) + "行，入库产品-规格名称有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                Loading(false, "", "#addStockAndRecordModal");
                return false;
            }
            field2.remark = $("#grid_Field2 tbody>tr").eq(i).find("input[name=stockRemark]").val();
            enterStockDetailList.push(field2);//入库详情表
            stockList.push(field);//库存表
        }
        $.ajax({
            type: "POST",
            url: "${request.contextPath}/stock/addEnterStockAndStock.json",
            data: JSON.stringify(allData),
            contentType: "application/json;charset=utf-8;",
            dataType: "json",
            success: function (res) {
                if (res.success) {
                    tipDialog(res.msg, 4, 2);
                    $("#addStockAndRecordModal").modal('hide');
                    $(".stockGridTable").each(function () {
                        var warehoseId = $(this).attr("id").replace("gridTable", "");
                        if (warehoseId != "") {
                            $("#gridTable" + warehoseId).trigger("reloadGrid");
                        }
                    });
                    Loading(false, "", "#addStockAndRecordModal");
                    $("#addStockAndRecordModal").modal('hide');
                    openPostWindow("${request.contextPath}/jasper/receiveStoragePdf/pdf", "enterStock", JSON.stringify(res.obj));
                    addStockCancelButton(1);
                } else {
                    tipDialog(res.msg, 4, 0);
                    Loading(false, "", "#addStockAndRecordModal");
                }
            },
            error: function () {
                tipDialog("网络异常", 0, 2);
                Loading(false, "", "#addStockAndRecordModal");
            }
        })
    }
    </@shiro.hasPermission>
</script>