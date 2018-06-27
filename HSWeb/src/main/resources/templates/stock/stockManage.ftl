<#include "../layout/head.ftl">
<#include "../layout/baseJs.ftl">
<div>
    <div class="layoutPanel layout-center"
         style="position: absolute; z-index: 99; height: 100%; width: 100%;overflow: auto;">
        <div class="tools_bar" style="border-top: none; margin-bottom: 0px;">
            <div class="PartialButton">
            <@shiro.hasPermission name="/stock/reload">
                <a id="lr-replace" title="刷新当前(Ctrl+Q)" onclick="Replace()" class="tools_btn">
                        <span>
                            <b class="btn-reload">刷新</b>
                        </span>
                </a>
                <div class="tools_separator"></div>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/stock/exportDate.json">
                <a id="lr-detail" title="数据导出" onclick="warehouseExport()" class="tools_btn">
                        <span>
                            <b class="btn-export">数据导出</b>
                        </span>
                </a>
                <div class="tools_separator"></div>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/stock/back">
                <a id="lr-leave" title="关闭当前窗口(Esc)" onclick="btn_back()" class="tools_btn">
                        <span>
                            <b class="btn-back">离开</b>
                        </span>
                </a>
                <div class="tools_separator"></div>
            </@shiro.hasPermission>
            </div>
        </div>
        <div class="line"></div>
        <ul class="nav nav-tabs ">
        <#if warehouseList??>
            <#list warehouseList as wareHouse>
                <li class="wareHouse" <#if wareHouse.warehouseType ==1>id="wareHouse${wareHouse.warehouseId!}"</#if>>
                    <a class="wareHouseList" href="#tab${wareHouse.warehouseId?c!}"
                       data-toggle="tab"> ${wareHouse.CName!} </a>
                </li>
            </#list>
        </#if>
        </ul>
        <div class="line"></div>
        <div class="tab-content">
        <#if warehouseList??>
            <#list warehouseList as warehouse>
                <#if warehouse.warehouseId??>
                    <div class="tab-pane <#if warehouse_index ==0> active</#if>" id="tab${warehouse.warehouseId}">
                        <div class="tools_bar" style="border-top: none; margin-bottom: 0px;">
                            <div class="PartialButton">
                                <div class="huoshen hidden"
                                     <#if warehouse.warehouseType ==1>id="huoshen${warehouse.warehouseId!}"</#if>>
                                    <@shiro.hasPermission name="/stock/addProcedureManageIFrame">
                                        <a id="lr-add" title="增加工序"
                                           onclick="addProcedureManage(${warehouse.warehouseId!})" class="tools_btn">
                                        <span>
                                            <b class="btn-add">增加工序</b>
                                        </span>
                                        </a>
                                    </@shiro.hasPermission>
                                    <@shiro.hasPermission name="/stock/updateStock">
                                        <a id="lr-edit" title="编辑" onclick="updateStockModal(${warehouse.warehouseId!})"
                                           class="tools_btn">
                                        <span>
                                            <b class="btn-update">编辑</b>
                                        </span>
                                        </a>
                                    </@shiro.hasPermission>
                                </div>
                                <div class="enterAndLeaveStockBtn"
                                     <#if warehouse.warehouseType ==1>id="enterAndLeaveStockBtn${warehouse.warehouseId!}"</#if>>
                                    <@shiro.hasPermission name="/stock/addEnterStockAndStock">
                                        <a id="lr-detail" title="新建入库" onclick="addReceive()" class="tools_btn">
                        <span>
                            <b class="btn-detail">新建入库</b>
                        </span>
                                        </a>
                                    </@shiro.hasPermission>
                                    <@shiro.hasPermission name="/stock/leaveStock.json">
                                        <a id="lr-detail" title="新建出库" onclick="leaveStock()" class="tools_btn">
                        <span>
                            <b class="btn-add">新建出库</b>
                        </span>
                                        </a>
                                    </@shiro.hasPermission>
                                    <@shiro.hasPermission name="/stock/rebackStock">
                                        <a id="lr-detail" title="成品返库" onclick="rebackStock()" class="tools_btn">
                        <span>
                            <b class="btn-detail">成品返库</b>
                        </span>
                                        </a>
                                    </@shiro.hasPermission>
                                </div>
                            </div>
                        </div>

                        <div class="bottomline QueryArea" style="margin: 1px; margin-top: 0px; margin-bottom: 0px;">
                            <div class="pull-right">
                                <span>
                                    截止至${.now}共有<span
                                        id="timeWarningText${warehouse.warehouseId?c!}">0</span>条记录即将到期，<span
                                        id="stockWarningText${warehouse.warehouseId?c!}">0</span>条记录库存将报警
                                </span>
                            </div>
                            <table border="0" class="form-find" style="height: 45px;">
                                <tbody>
                                <tr>
                                    <td>
                                        批次号：<input id="batchNo${warehouse.warehouseId}" name="batchNo" type="text"
                                                   class="txt" style="width: 100px">
                                    </td>
                                    <td>
                                        货物类型：
                                        <select id="isMaterial${warehouse.warehouseId}" name="isMaterial"
                                                class="txtselect" datacol="yes" err="分类"
                                                checkexpession="NotNull">
                                            <option value="">==请选择==</option>
                                        </select>
                                    </td>
                                    <td>
                                        产品名称：
                                        <select id="productId${warehouse.warehouseId}" name="productId"
                                                class="txtselect" datacol="yes" err="分类"
                                                checkexpession="NotNull">
                                            <option value="">请先选择类型</option>
                                        </select>
                                    </td>
                                    <td>
                                        入库规格：
                                        <select id="productSpecId${warehouse.warehouseId}" name="productSpecId"
                                                class="txtselect" datacol="yes" err="状态"
                                                checkexpession="NotNull">
                                            <option value="">==请选择==</option>
                                        </select>
                                    </td>
                                    <td>
                                        产品状态：
                                        <select id="productStatus${warehouse.warehouseId}" name="productStatus"
                                                class="txtselect" datacol="yes"
                                                err="状态"
                                                checkexpession="NotNull">
                                            <option value="">请先选择产品</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        入库时间：
                                        <input id="beginTime${warehouse.warehouseId}" name="beginTime" type="text"
                                               class="txt Wdate" style="width: 140px; "
                                               onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                                        至
                                        <input id="endTime${warehouse.warehouseId}" name="endTime" type="text"
                                               class="txt Wdate" style="width: 140px; "
                                               onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                                    </td>
                                    <td>
                                        <input id="btnSearch${warehouse.warehouseId}" type="button" class="btnSearch"
                                               value="查 询">
                                        <input id="btnClear${warehouse.warehouseId}" type="button" class="btnSearch"
                                               value="重 置">
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <table id="gridTable${warehouse.warehouseId}" class="stockGridTable">
                        </table>
                        <div id="gridPager${warehouse.warehouseId}">
                        </div>

                    </div>
                </#if>
            </#list>
        </#if>
        </div>
    </div>
</div>
<@shiro.hasPermission name="/stock/addEnterStockAndStock">
<!--添加资源-->
<div id="addStockAndRecordModal" class="modal fade " data-width="1050" tabindex="-1" aria-hidden="true"
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

<!--新建出库-->
<@shiro.hasPermission name="/stock/leaveStock.json">
<div id="leaveStockModal" class="modal fade" data-width="1150" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">出库</h4>
    </div>
    <div class="modal-body">
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="addLeaveStock" type="button" class="btn green">确认并打印</button>
    </div>
</div>
</@shiro.hasPermission>
<@shiro.hasPermission name="/stock/rebackStock">
<!--成品返库-->
<div id="rebackStockModal" class="modal fade " data-width="1050" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">成品返库</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button type="button" onclick="reBackStockAndRecord()" class="btn green">确认并打印返库单</button>
    </div>
</div>
</@shiro.hasPermission>
<@shiro.hasPermission name="/stock/updateStock">
<!---->
<div id="updateStockModal" class="modal fade " data-width="900" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header" style="text-align: center">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">编辑活参库</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button type="button" class="btn green" onclick="updateStock()">确定</button>
    </div>
</div>
</@shiro.hasPermission>

<script>
    var resourceId;
    var isShowModal = ${isShowModal};
    $(document).ready(function () {
        //资源id
        resourceId = top.$("#ModuleId").val();
        $(".wareHouse").eq(0).attr("class", "wareHouse active");
        let idArray = [];
        $(".stockGridTable").each(function () {
            let warehoseId = $(this).attr("id").replace("gridTable", "");
            idArray.push(warehoseId);
        });
        let firstId = idArray[0];
        if (firstId !== null && firstId !== undefined) {
            $("#huoshen" + firstId).removeClass("hidden");
            $("#enterAndLeaveStockBtn" + firstId).addClass("hidden");
            GetGrid("${request.contextPath}/stock/GridJson.json?warehouseId=" + firstId, "#gridPager" + firstId, "#gridTable" + firstId, firstId);
            initSearch(firstId);
        }
        if (1 == isShowModal) {
            leaveStock();
        }
    });

    //tab点击事件
    $(".wareHouseList").on("click", function () {
        let id = $(this).attr("href").replace("#tab", "");
        if (id !== null && id !== undefined) {
            $("#huoshen" + id).removeClass("hidden");
            $("#enterAndLeaveStockBtn" + id).addClass("hidden");
            GetGrid("${request.contextPath}/stock/GridJson.json?warehouseId=" + id, "#gridPager" + id, "#gridTable" + id, id);
            initSearch(id);
        }
    });

    /**
     * 搜索栏初始化
     * */
    function initSearch(warehouseId) {
        if (warehouseId!==null&&warehouseId!==undefined) {
            $.post("${request.contextPath}/stock/getStockIsMaterial.json", {warehouseId: warehouseId}, function (res) {
                var resObj = res.obj;
                $("#isMaterial" + warehouseId).find("option").remove();
                $("#isMaterial" + warehouseId).append("<option value=''>==请选择==</option>");
                if (resObj != null && "" != resObj) {
                    for (i in resObj) {
                        $("#isMaterial" + warehouseId).append("<option value='" + resObj[i].isMaterial + "'>" + resObj[i].materialName + "</option>");
                    }
                }
            }, "JSON");
        }
        $.post("${request.contextPath}/stock/getStockProductStatus.json", "", function (res) {
            if (warehouseId!==null&&warehouseId!==undefined) {
                $("#productStatus" + warehouseId).find("option").remove();
                $("#productStatus" + warehouseId).append("<option value=''>==请选择==</option>");
                for (i in res) {
                    $("#productStatus" + warehouseId).append("<option value='" + res[i].productStatus + "'>" + res[i].productStatusName + "</option>");
                }
            }
        }, "JSON");
        if (warehouseId!==null&&warehouseId!==undefined) {
            loadProduct("#isMaterial" + warehouseId, "#productId" + warehouseId, "#productSpecId" + warehouseId, warehouseId);
        }
    }

    /**
     * 货物类型，产品，规格名称
     * @param typeId 货物类型dom元素id
     * @param productId 产品dom元素id
     * @param specId 规格名称dom元素id
     * @param warehouseId 仓库Id
     * */
    function loadProduct(typeId, productId, specId, warehouseId) {
        var typeEl = $("" + typeId);
        var productEl = $("" + productId);
        var specEl = $("" + specId);
        typeEl.unbind("click");
        typeEl.click(function () {
            if (typeEl.val() == "") {
                productEl.find("option").remove();
                productEl.append("<option value=''>请先选择类型</option>");
            } else {
                productEl.find("option").remove();
                productEl.append("<option value=''>请先选择类型</option>");
                var type = typeEl.val();
                $.post("${request.contextPath}/stock/getStockList.json?warehouseId=" + warehouseId, {isMaterial: type}, function (res) {
                    if (res.success) {
                        productEl.find("option").remove();
                        productEl.append("<option value=''>==请选择==</option>");
                        var obj = res.obj;
                        for (i in obj) {
                            productEl.append("<option value='" + obj[i].productId + "'>" + obj[i].productName + "</option>")
                        }
                    }
                }, "JSON");
            }
        });
        productEl.unbind("click");
        productEl.click(function () {
            if (typeEl.val() == "") {
                specEl.find("option").remove();
                specEl.append("<option value=''>请先选择产品</option>");
            } else {
                specEl.find("option").remove();
                specEl.append("<option value=''>请先选择产品</option>");
                var type = productEl.val();
                $.post("${request.contextPath}/stock/getProductStatusListForStock.json?warehouseId=" + warehouseId + "&&isMaterial=" + typeEl.val(), {productId: type}, function (res) {
                    if (res.success) {
                        specEl.find("option").remove();
                        specEl.append("<option value=''>==请选择==</option>");
                        var obj = res.obj;
                        for (i in obj) {
                            specEl.append("<option value='" + obj[i].productSpecName + "'>" + obj[i].productSpecName + "</option>")
                        }
                    }
                }, "JSON");
            }
        });

    }

    /**
     * 加载进行中表格
     * @param url 请求地址
     * @param id 分页控件id
     * @param table 表格id
     * */
    function GetGrid(url, id, table, warehouseId) {
        $("" + table).jqGrid({
            url: url + "",
            datatype: "json",
            height: $(window).height() - 280,
            width: $(window).width(),
            colModel: [
                {label: "", name: "stockId", index: "stockId", hidden: true, classes: "stockId"},
                {label: "", name: "warehouseId", index: "warehouseId", hidden: true},
                {label: "批次号", name: "batchNo", index: "batchNo", width: 100},
                {label: "货物类型", name: "materialName", index: "isMaterial", width: 100},
                {label: "产品名称", name: "productName", index: "productId", width: 100, align: "center"},
                {label: "产品状态", name: "productStatusName", index: "productStatus", width: 100, align: "center"},
                {label: "入库规格", name: "productSpecName", index: "productSpecName", width: 120, align: "center"},
                {label: "库存数量", name: "weight", index: "weight", width: 60, cellattr: addWeightCellAttr},
                {label: "单位", name: "unitName", index: "unitName", width: 60},
                {
                    label: "最新入库时间",
                    name: "inCreateDate",
                    index: "inCreateDate",
                    width: 180,
                    align: "center",
                    cellattr: addCellAttr
                },
                {label: "时间预警", name: "warningDay", index: "warningDay", width: 80},
                {label: '库存预警', name: 'stockWarn', index: 'stockWarn', width: 80},
                {label: '过期个数', name: 'overdueCount', index: 'overdueCount', hidden: true, classes: "overdueCount"}
            ],
            pagerpos: "right",
            viewrecords: true,
            recordpos: "left",
            pager: "" + id,
            rowNum: 10,
            rowList: [10, 20, 30, 50, 100],
            jsonReader: {
                root: "root", page: "page", total: "total", records: "records"
            },
            sortname: 'listIndex',
            sortorder: 'asc',
            shrinkToFit: false,
            gridview: true,
            multiselect: true//开启全选
        });
    }

    function addCellAttr(rowId, val, rawObject, cm, rdata) {
        if (rawObject.isWarning == 1) {
            $("#timeWarningText" + rawObject.warehouseId).text("0");
            $("#timeWarningText" + rawObject.warehouseId).text(rawObject.overdueCount);
            return "style='background-color:#f51414'";
        }
    }

    function addWeightCellAttr(rowId, val, rawObject, cm, rdata) {
        if (rawObject.isStockWarning == 1) {
            $("#stockWarningText" + rawObject.warehouseId).text("0");
            $("#stockWarningText" + rawObject.warehouseId).text(rawObject.stockWarningCount);
            return "style='background-color:#f55c09'";
        }
    }

    /**
     * 进行中
     * */
    $(".stockGridTable").each(function () {
        var warehoseId = $(this).attr("id").replace("gridTable", "");
        if (warehoseId != "") {
            $("#btnSearch" + warehoseId).unbind("click");
            $("#btnSearch" + warehoseId).click(function () {
                var batchNo = $.trim($("#batchNo" + warehoseId).val());//批次号
                var isMaterial = $.trim($("#isMaterial" + warehoseId).val());//货物类型
                var productStatus = $.trim($("#productStatus" + warehoseId).val());//产品状态
                var productId = $.trim($("#productId" + warehoseId).val());//产品名
                var productSpecId = $.trim($("#productSpecId" + warehoseId).val());//入库规格
                var beginTime = $.trim($("#beginTime" + warehoseId).val());//开始时间
                var endTime = $.trim($("#endTime" + warehoseId).val());//结束时间
                var postData = {
                    batchNo: batchNo,
                    isMaterial: isMaterial,
                    productStatus: productStatus,
                    productId: productId,
                    productSpecName: productSpecId,
                    beginTime: beginTime,
                    endTime: endTime
                };
                //提交post并刷新表格
                $("#gridTable" + warehoseId).jqGrid("setGridParam", {
                    postData: postData,
                    url: "${request.contextPath}/stock/GridJson.json?warehouseId=" + warehoseId,
                    page: 1
                }).trigger("reloadGrid");
            });

            $("#btnClear" + warehoseId).unbind("click");
            $("#btnClear" + warehoseId).click(function () {
                //重置表格
                $("#batchNo" + warehoseId).val("");
                $("#isMaterial" + warehoseId).val("");
                $("#productStatus" + warehoseId).val("");
                $("#productId" + warehoseId).val("");
                $("#productSpecId" + warehoseId).val("");
                $("#beginTime" + warehoseId).val("");
                $("#endTime" + warehoseId).val("");
                var postData = {
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
            });
        }
    })

    //新建入库
    <@shiro.hasPermission name="/stock/addEnterStockAndStock">
    function addReceive() {
        $("#addStockAndRecordModal").modal({
            remote: "${request.contextPath}/stock/addStockAndRecordModal.htm"
        });
    }
    </@shiro.hasPermission>

    //新建出库单
    <@shiro.hasPermission name="/stock/leaveStock.json">
    function leaveStock() {
        $.post('${request.contextPath}/stock/countDetail.json', '', function (res) {
            if (res.success) {
                $("#leaveStockModal").modal({
                    remote: "${request.contextPath}/stock/leaveStockModal.htm?resourceId=" + resourceId
                });
                $('#leaveStockModal').on('loaded.bs.modal', function (e) {
                    function leaveStockModalReload() {
                        $("#grid_Field tbody").html("");
                        let arr = new Array();
                        $(".stockGridTable tbody .ui-state-highlight .stockId").each(function () {
                            arr.push($(this).html())
                        });
                        var stockIds = arr.join(',');
                        //选中回显
                        if (null != stockIds && '' != stockIds) {
                            $.post("${request.contextPath}/stock/showRecordBySelect.json", {stockIds: stockIds}, function (res) {
                                var resObj = res.obj;
                                if (null != resObj && "" != resObj) {
                                    $("#grid_Field tbody").html("");
                                    for (var i = 0; i < resObj.length; i++) {
                                        let index = i + 1;
                                        $("#grid_Field tbody").append(CreateTableRowReload(index)).find('input,select').hide();
                                        //仓库
                                        $("#warehouse" + index).attr("disabled", "disabled");
                                        $("#warehouse" + index).append("<option value='" + resObj[i].warehouseId + "' selected>" + resObj[i].warehouseName + "</option>");
                                        $("#warehouse" + index).prev("div").text(resObj[i].warehouseName);
                                        //货物类型
                                        $("#goodsType" + index).attr("disabled", "disabled");
                                        $("#goodsType" + index).append("<option value='" + resObj[i].isMaterial + "' selected>" + resObj[i].materialName + "</option>");
                                        $("#goodsType" + index).prev("div").text(resObj[i].materialName);
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
                                                $("#loss" + index).append("<option value='" + obj[i].cname + "'>" + obj[i].cname + "</option>");
                                            }
                                            $("#loss" + index).val("否");
                                        }, "JSON");
                                    }
                                } else {
                                    $("#grid_Field tbody").html(CreateTableRowReload(1));
                                    $("#grid_Field tbody tr").find('select,input').attr('disabled', 'disabled').hide();
                                    $("#grid_Field tbody tr:eq(0)").find('select,input').removeAttr('disabled');
                                    initSearchReload(1);
                                }
                            });
                        } else {
                            $("#grid_Field tbody").html(CreateTableRowReload(1));
                            $("#grid_Field tbody tr").find('select,input').attr('disabled', 'disabled').hide();
                            $("#grid_Field tbody tr:eq(0)").find('select,input').removeAttr('disabled');
                            initSearchReload(1);
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

                    //创建行
                    function CreateTableRowReload(index) {
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
                        tr += '<td style="width: 50px; text-align: center;"><div></div><input id="outWeight' + index + '" type="text" maxlength="10" class="txt" name ="outWeight" onkeyup="clearNoNumReload(this)"/></td>';
                        tr += '<td style="width: 60px; text-align: center;">' + IsUnitReload(index) + '</td>';//单位
                        tr += '<td style="width: 60px; text-align: center;"><div></div>' + IsLossReload(index) + '</td>';//损耗
                        tr += '<td style="width: 60px; text-align: center;"><div></div><input type="text" maxlength="10"  class="txt"/></td>';//备注2
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
                    function TableTdEventReload() {
                        $("#leaveStockModal").on("click", ".grid .td-div", function () {
                            if ($(this).parent().hasClass("selected")) {
                                $(this).parent().removeClass("selected");
                            } else {
                                $(this).parent().addClass("selected");
                            }
                        });
                        $("#leaveStockModal").on("click", "#grid_Field tbody td", function () {
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

                    //select相互关联
                    function initSearchReload(num) {
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
                            if (res.success) {
                                var obj = res.obj;
                                for (i in obj) {
                                    $("#loss" + num).append("<option value='" + obj[i].cname + "'>" + obj[i].cname + "</option>");
                                }
                                $("#loss" + num).val("否");
                            }
                        }, "JSON");
                        loadProductReload("#warehouse" + num, "#goodsType" + num, "#productName" + num, "#productStatus" + num, "#productSpecName" + num, "#batchNo" + num, "#unitName" + num, "#unitId" + num, "#weight" + num);
                        $("#weight" + num).keyup(function () {
                            var tmptxt = $(this).val();
                            $(this).val(tmptxt.replace(/[^\d.]/g, ""));
                        }).bind("paste", function () {
                            var tmptxt = $(this).val();
                            $(this).val(tmptxt.replace(/[^\d.]/g, ""));
                        }).css("ime-mode", "disabled");
                    }

                    function loadProductReload(warehouseId, typeId, productId, statusId, specId, batchNo, unitName, unitId, weight) {
                        var warehouseEl = $("" + warehouseId);//仓库
                        var typeEl = $("" + typeId);//货物类型
                        var productEl = $("" + productId);//产品名称
                        var statusEl = $("" + statusId);//产品状态
                        var specEl = $("" + specId);//入库规格
                        var batchEl = $("" + batchNo);//批次号
                        var unitEl = $("" + unitName);//单位
                        var unitIdEl = $("" + unitId);//单位Id
                        var weightEl = $("" + weight);//库存数量

                        $(warehouseId).on("click", function () {
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

                        $(typeId).on("click", function () {
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

                        $(productId).on("click", function () {
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

                        $(statusId).on("click", function () {
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

                        $(specId).on("click", function () {
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

                        $(batchNo).on("click", function () {
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

                    leaveStockModalReload();
                    TableTdEventReload();
                });
            } else {
                tipDialog(res.msg, 3, 'warning');
            }
        }, 'JSON');
    }
    </@shiro.hasPermission>

    <@shiro.hasPermission name="/stock/rebackStock">
    //成品返库
    function rebackStock() {
        $("#rebackStockModal").modal({
            remote: "${request.contextPath}/stock/rebackStockModal.htm"
        });
    }
    </@shiro.hasPermission>
    //编辑
    <@shiro.hasPermission name="/stock/updateStock">
    function updateStockModal(warehouseId) {
        var str = $("#wareHouse" + warehouseId).children("a").attr("href");
        str = str.replace("#tab", "#gridTable");
        var stockId = GetJqGridRowValue(str, "stockId");
        if (stockId == null) {
            tipDialog('请选择您要编辑的记录!', 4, 'warning');
        } else {
            var stockIDs = stockId.split(",");
            $("#updateStockModal").modal({
                remote: "${request.contextPath}/stock/updateStockModal.htm?stockIDs=" + stockIDs
            });
        }
    }
    </@shiro.hasPermission>

    <@shiro.hasPermission name="/stock/updateStock">
    //编辑活参库
    function updateStock() {
        Loading(true, "正在提交", "#updateStockModal");
        let stockList = new Array();
        let length = $("#grid_Field4 tbody>tr").length;
        let allData = {
            stockList: stockList
        };
        for (let i = 0; i < length; i++) {
            let updateStock = {
                stockId: "",//库存ID
                isMaterial: "",//货物类型
                productId: "",//产品ID
                productName: "",//产品名称
                productStatus: "",//产品状态
                productSpecName: "",//规格名称
                weight: "",//库存数量
                unitId: "",//重量单位ID
                unitName: "",//重量单位名称
                updateDate: "",//入库时间
                stockWarn: ""//库存预警
            };
            let stockId = $("#grid_Field4 tbody>tr").eq(i).find("input[name=updateStockId]").val();
            if ("" != stockId) {
                updateStock.stockId = stockId;
            } else {
                tipDialog("操作记录第" + (i + 1) + "行，活参编辑-库存ID有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                Loading(false, "", "#updateStockModal");
                return false;
            }
            let isMaterial = $("#grid_Field4 tbody>tr").eq(i).find("select[name=updateIsMaterial] :selected").attr("value");
            if ("" != isMaterial) {
                updateStock.isMaterial = isMaterial;
            } else {
                tipDialog("操作记录第" + (i + 1) + "行，活参编辑-货物类型有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                Loading(false, "", "#updateStockModal");
                return false;
            }
            let productId = $("#grid_Field4 tbody>tr").eq(i).find("select[name=updateProductId] :selected").attr("value");
            let productName = $("#grid_Field4 tbody>tr").eq(i).find("select[name=updateProductId] :selected").text();
            if ("" != productId && "" != productName) {
                updateStock.productId = productId;
                updateStock.productName = productName;
            } else {
                tipDialog("操作记录第" + (i + 1) + "行，活参编辑-产品名称有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                Loading(false, "", "#updateStockModal");
                return false;
            }
            let productStatus = $("#grid_Field4 tbody>tr").eq(i).find("select[name=updateProductStatus] :selected").attr("value");
            if ("" != productStatus) {
                updateStock.productStatus = productStatus;
            } else {
                tipDialog("操作记录第" + (i + 1) + "行，活参编辑-产品类别有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                Loading(false, "", "#updateStockModal");
                return false;
            }
            let productSpecName = $("#grid_Field4 tbody>tr").eq(i).find("select[name=updateProductSpecName] :selected").attr("value");
            if ("" != productSpecName) {
                updateStock.productSpecName = productSpecName;
            } else {
                tipDialog("操作记录第" + (i + 1) + "行，活参编辑-入库规格有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                Loading(false, "", "#updateStockModal");
                return false;
            }
            let weight = $("#grid_Field4 tbody>tr").eq(i).find("input[name=updateWeight]").val();
            if ("" != weight) {
                updateStock.weight = weight;
            } else {
                tipDialog("操作记录第" + (i + 1) + "行，活参编辑-库存数量有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                Loading(false, "", "#updateStockModal");
                return false;
            }
            let unitId = $("#grid_Field4 tbody>tr").eq(i).find("select[name=updateUnitId] :selected").attr("value");
            let unitName = $("#grid_Field4 tbody>tr").eq(i).find("select[name=updateUnitId] :selected").text();
            if ("" != unitId && "" != unitName) {
                updateStock.unitId = unitId;
                updateStock.unitName = unitName;
            } else {
                tipDialog("操作记录第" + (i + 1) + "行，活参编辑-单位有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                Loading(false, "", "#updateStockModal");
                return false;
            }
            let updateDate = $("#grid_Field4 tbody>tr").eq(i).find("input[name=updateUpdateDate]").val();
            if ("" != updateDate) {
                updateStock.updateDate = updateDate;
            } else {
                tipDialog("操作记录第" + (i + 1) + "行，活参编辑-入库时间有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                Loading(false, "", "#updateStockModal");
                return false;
            }
            let stockWarn = $("#grid_Field4 tbody>tr").eq(i).find("input[name=updateStockWarn]").val();
            if ("" != stockWarn) {
                updateStock.stockWarn = stockWarn;
            } else {
                tipDialog("操作记录第" + (i + 1) + "行，活参编辑-库存预警有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                Loading(false, "", "#updateStockModal");
                return false;
            }
            stockList.push(updateStock);
        }
        $.ajax({
            type: "POST",
            url: "${request.contextPath}/stock/updateStock.json",
            data: JSON.stringify(allData),
            contentType: "application/json;charset=utf-8;",
            dataType: "json",
            async: false,
            success: function (res) {
                if (res.success) {
                    tipDialog(res.msg, 3, 2);
                    $("#updateStockModal").modal('hide');
                    $(".stockGridTable").each(function () {
                        var warehoseId = $(this).attr("id").replace("gridTable", "");
                        if (warehoseId != "") {
                            $("#gridTable" + warehoseId).trigger("reloadGrid");
                        }
                    })
                } else {
                    tipDialog(res.msg, 4, "warning");
                    $("#gridTable").trigger("reloadGrid");
                }
                Loading(false, "", "#updateStockModal");
            },
            error: function () {
                tipDialog("网络异常", 0, 2);
                Loading(false, "", "#updateStockModal");
            }
        })
    }
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/stock/addEnterStockAndStock">
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
                stockReceiveId:"",//收货单详情ID
                batchNo: "",//批次号
                isMaterial: "",//货物类型
                productId: "",//产品ID
                productName: "",//产品名称
                productStatus: "",//产品状态
                productSpecName: "",//规格名称
                unitId: "",//重量单位ID
                unitName: "",//重量单位名称
                warehouseId: "",//入库仓库ID
                loss:"",//损耗
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
                loss:"",//损耗
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
            let loss=$("#grid_Field2 tbody>tr").eq(i).find("select[name=loss] :selected").attr("value");
            if(""!==loss){
                field.loss=loss;
                field2.loss = loss;
            } else {
                tipDialog("操作记录第" + (i + 1) + "行，入库详情-是否损耗有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                Loading(false, "", "#addStockAndRecordModal");
                return;
            }
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

    <@shiro.hasPermission name="/stock/rebackStock">
    //成品返库入库
    function reBackStockAndRecord() {
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
        let enterStockDetailList = new Array();
        let stockList = new Array();
        let length2 = $("#grid_Field3 tbody>tr").length;
        let allData = {
            enterStock: enterStock,
            enterStockDetailList: enterStockDetailList,
            stockList: stockList
        };

        let enterNo = $.trim($("#reBackNo").val());
        if (enterNo != "") {
            enterStock.enterNo = enterNo;
        } else {
            tipDialog("成品返库-返库编号有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
            Loading(false);
            return false;
        }
        let enterPerson = $.trim($("#reBackPerson").val());
        if (enterPerson != "") {
            enterStock.enterPerson = enterPerson;
        } else {
            tipDialog("成品返库-返库申请人有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
            Loading(false);
            return false;
        }
        let brokerage = $.trim($("#reBackBrokerage").val());
        if (brokerage != "") {
            enterStock.brokerage = brokerage;
        } else {
            tipDialog("成品返库-经手人有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
            Loading(false);
            return false;
        }
        let enterDate = $.trim($("#reBackDate").val());
        if (enterDate != "") {
            enterStock.enterDate = enterDate;
        } else {
            tipDialog("成品返库-返库时间有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
            Loading(false);
            return false;
        }
        let stockWarn = $.trim($("#reBackStockWarn").val());
        if (stockWarn != "") {
            enterStock.stockWarn = stockWarn;
        } else {
            tipDialog("成品返库-库存预警有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
            Loading(false);
            return false;
        }
        enterStock.remark = $.trim($("#reBackRemark").val());

        //入库详情
        for (let i = 0; i < length2; i++) {
            let field = {
                batchNo: "",//批次号
                isMaterial: "",//货物类型
                productId: "",//产品ID
                productName: "",//产品名称
                productStatus: "",//产品状态
                productSpecName: "",//规格名称
                unitId: "",//重量单位ID
                unitName: "",//重量单位名称
                warehouseId: "",//入库仓库ID
                remark: "",//备注
                listIndex: "",
                isSeaCucumber: ""//是否入活参库
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
            field2.relatedId = relatedId;
            field2.relatedType = relatedType;
            let stockBatchNo = $("#grid_Field3 tbody>tr").eq(i).find("input[name=stockBatchNo]").val();
            if ("" != stockBatchNo) {
                field.batchNo = stockBatchNo;
                field2.batchNo = stockBatchNo;
            } else {
                tipDialog("操作记录第" + (i + 1) + "行，返库详情-批次号有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                Loading(false);
                return false;
            }
            let stockIsMaterial = $("#grid_Field3 tbody>tr").eq(i).find("select[name=stockIsMaterial] :selected").attr("value");
            if ("" != stockIsMaterial) {
                field.isMaterial = stockIsMaterial;
                field2.isMaterial = stockIsMaterial;
            } else {
                tipDialog("操作记录第" + (i + 1) + "行，返库详情-货物类型有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                Loading(false);
                return false;
            }
            let stockProductId = $("#grid_Field3 tbody>tr").eq(i).find("select[name=stockProductId] :selected").attr("value");
            let stockProductName = $("#grid_Field3 tbody>tr").eq(i).find("select[name=stockProductId] :selected").text();
            if ("" != stockProductId && "" != stockProductName) {
                field.productId = stockProductId;
                field.productName = stockProductName;
                field2.productId = stockProductId;
                field2.productName = stockProductName;
            } else {
                tipDialog("操作记录第" + (i + 1) + "行，返库详情-产品有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                Loading(false);
                return false;
            }
            let stockProductStatus = $("#grid_Field3 tbody>tr").eq(i).find("select[name=enterProductStatus] :selected").attr("value");
            if ("" != stockProductStatus) {
                field.productStatus = stockProductStatus;
                field2.productStatus = stockProductStatus;
            } else {
                tipDialog("操作记录第" + (i + 1) + "行，返库详情-产品类型有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                Loading(false);
                return false;
            }
            let stockProductSpecName = $("#grid_Field3 tbody>tr").eq(i).find("select[name=stockProductSpecName] :selected").attr("value");
            if ("" != stockProductName) {
                field.productSpecName = stockProductSpecName;
                field2.productSpecName = stockProductSpecName;
            } else {
                tipDialog("操作记录第" + (i + 1) + "行，返库详情-规格有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                Loading(false);
                return false;
            }
            let stockInWeight = $("#grid_Field3 tbody>tr").eq(i).find("input[name=stockInWeight]").val();
            if ("" != stockInWeight) {
                field.inWeight = stockInWeight;
                field2.inWeight = stockInWeight;
            } else {
                tipDialog("操作记录第" + (i + 1) + "行，返库详情-入库重量有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                Loading(false);
                return false;
            }
            let stockUnitId = $("#grid_Field3 tbody>tr").eq(i).find("input[name=stockUnitId]").val();
            let stockUnitName = $("#grid_Field3 tbody>tr").eq(i).find("input[name=stockUnitName]").val();
            if ("" != stockUnitId && "" != stockUnitName) {
                field.unitId = stockUnitId;
                field.unitName = stockUnitName;
                field2.unitId = stockUnitId;
                field2.unitName = stockUnitName;
            } else {
                tipDialog("操作记录第" + (i + 1) + "行，返库详情-单位有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                Loading(false);
                return false;
            }
            let stockWarehouseId = $("#grid_Field3 tbody>tr").eq(i).find("select[name=stockWareHouse] :selected").attr("value");
            if ("" != stockWarehouseId) {
                field.warehouseId = stockWarehouseId;
                field2.warehouseId = stockWarehouseId;
            } else {
                tipDialog("操作记录第" + (i + 1) + "行，返库详情-仓库类型有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                Loading(false);
                return false;
            }
            let isSeaCucumber = $("#grid_Field3 tbody>tr").eq(i).find("select[name=enterIsSeaCucumber] :selected").attr("value");
            if ("" != isSeaCucumber) {
                field.isSeaCucumber = isSeaCucumber;
                field2.isSeaCucumber = isSeaCucumber;
            } else {
                tipDialog("操作记录第" + (i + 1) + "行，返库详情-是否入活参库有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                Loading(false);
                return false;
            }
            let enterProductSpecName = $("#grid_Field3 tbody>tr").eq(i).find("input[name=productSpecNameDetail]").val();
            if ("" != enterProductSpecName) {
                field2.enterProductSpecName = enterProductSpecName;
            } else {
                tipDialog("操作记录第" + (i + 1) + "行，返库详情-规格名称有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                Loading(false);
                return false;
            }
            let inWeightDetail = $("#grid_Field3 tbody>tr").eq(i).find("input[name=inWeightDetail]").val();
            if ("" != inWeightDetail) {
                field2.materialWeight = inWeightDetail;
            } else {
                tipDialog("操作记录第" + (i + 1) + "行，返库详情-原料有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                Loading(false);
                return false;
            }
            let unitIdDetail = $("#grid_Field3 tbody>tr").eq(i).find("input[name=unitIdDetail]").val();
            if ("" != unitIdDetail) {
                field2.enterUnitId = unitIdDetail;
            } else {
                tipDialog("操作记录第" + (i + 1) + "行，返库详情-规格名称有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                Loading(false);
                return false;
            }
            let unitNameDetail = $("#grid_Field3 tbody>tr").eq(i).find("input[name=unitNameDetail]").val();
            if ("" != unitNameDetail) {
                field2.enterUnitName = unitNameDetail;
            } else {
                tipDialog("操作记录第" + (i + 1) + "行，返库详情-规格名称有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                Loading(false);
                return false;
            }
            field.remark = field2.remark = $("#grid_Field3 tbody>tr").eq(i).find("input[name=stockRemark]").val();
            enterStockDetailList.push(field2);//返库详情表
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
                    $(".stockGridTable").each(function () {
                        var warehoseId = $(this).attr("id").replace("gridTable", "");
                        if (warehoseId != "") {
                            $("#gridTable" + warehoseId).trigger("reloadGrid");
                        }
                    });
                    tipDialog(res.msg, 3, 2);
                    Loading(false);
                    $("#rebackStockModal").modal('hide');
                    openPostWindow("${request.contextPath}/jasper/receiveDepotPdf/pdf", "enterStock", JSON.stringify(res.obj));
                } else {
                    tipDialog(res.msg, 4, 0);
                    Loading(false);
                }
            },
            error: function () {
                tipDialog("网络异常", 4, 0);
                Loading(false)
            }
        })
    }
    </@shiro.hasPermission>

    //数据导出
    <@shiro.hasPermission name="/stock/exportDate.json">
    function warehouseExport() {
        window.open("${request.contextPath}/stock/exportData.json?resourceId=" + resourceId, "_blank");
    }
    </@shiro.hasPermission>

    //增加工序
    <@shiro.hasPermission name="/stock/addProcedureManageIFrame">
    function addProcedureManage(warehouseId) {
        var str = $("#wareHouse" + warehouseId).children("a").attr("href");
        str = str.replace("#tab", "#gridTable");
        var stockIds = GetJqGridRowValue(str, "stockId");
        if (stockIds === undefined) {
            stockIds = "";
        }
        AddTabMenu('addProcedureManage', '${request.contextPath}/stock/addProcedureManageIFrame.htm?warehouseId=' + warehouseId + "&&stockIds=" + stockIds, '增加工序', "sort_ascending.png", 'true', "${staticImg}");
    }
    </@shiro.hasPermission>
</script>