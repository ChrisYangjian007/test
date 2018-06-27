<style>
    .ui-jqgrid tr.jqgrow td {
        white-space: normal !important;
        height: auto;
        padding-top: 2px;
        word-break: break-all;
    }
</style>
<div>
    <div class="layoutPanel layout-center"
         style="position: absolute; z-index: 99; height: 100%; width: 100%;overflow: auto;">
        <div class="tools_bar" style="border-top: none; margin-bottom: 0px;">
            <div class="PartialButton">
            <@shiro.hasPermission name="/stockRecord/refresh">
                <a id="lr-replace" title="刷新当前(Ctrl+Q)" onclick="Replace()" class="tools_btn">
                        <span>
                            <b class="btn-reload">刷新</b>
                        </span>
                </a>
                <div class="tools_separator"></div>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/stockRecord/exportStockRecord.json">
                <a id="lr-detail" title="数据导出" onclick="exportStockRecord()" class="tools_btn">
                        <span>
                            <b class="btn-export">数据导出</b>
                        </span>
                </a>
                <div class="tools_separator"></div>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/stockRecord/returnManage.json">
                <a id="lr-detail" title="返货处理" onclick="returnManage()" class="tools_btn">
                        <span>
                            <b class="btn-update">返货处理</b>
                        </span>
                </a>
                <div class="tools_separator"></div>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/stockRecord/back">
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
        <ul class="nav nav-tabs" id="myRecordTab">
        <@shiro.hasPermission name="/stockRecord/enterStockRecord">
            <li class="active">
                <a href="#tab1" data-toggle="tab"> 入库记录
                    <input type="hidden" value="1" name="stockRecordTab">
                </a>
            </li>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/stockRecord/leaveStockRecord">
            <li>
                <a href="#tab2" data-toggle="tab"> 出库记录
                    <input type="hidden" value="2" name="stockRecordTab">
                </a>
            </li>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/stockRecord/lossRecord">
            <li>
                <a href="#tab3" data-toggle="tab"> 损耗记录
                    <input type="hidden" value="3" name="stockRecordTab">
                </a>
            </li>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/stockRecord/returnGoodsRecord">
            <li>
                <a href="#tab4" data-toggle="tab"> 返货处理记录
                    <input type="hidden" value="4" name="stockRecordTab">
                </a>
            </li>
        </@shiro.hasPermission>
        </ul>
        <div class="line"></div>
        <div class="tab-content">
        <@shiro.hasPermission name="/stockRecord/enterStockRecord">
            <!--入库记录-->
            <div class="tab-pane active" id="tab1">
                <div class="tools_bar" style="border-top: none; margin-bottom: 0px;">
                    <div class="PartialButton">
                        <@shiro.hasPermission name="/stockRecord/printIn.json">
                            <a id="lr-add" title="打印入库单" onclick="printIn()" class="tools_btn">
                        <span>
                            <b class="btn-detail">打印入库单</b>
                        </span>
                            </a>
                            <div class="tools_separator"></div>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="/stockRecord/printReturn.json">
                            <a id="lr-add" title="打印返库单" onclick="printReturn()" class="tools_btn">
                        <span>
                            <b class="btn-detail">打印返库单</b>
                        </span>
                            </a>
                            <div class="tools_separator"></div>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="/stockRecord/PrintCode.json">
                            <a id="lr-detail" title="打印箱码" onclick="printBoxLabel()" class="tools_btn">
                        <span>
                            <b class="btn-detail">打印箱码</b>
                        </span>
                            </a>
                            <div class="tools_separator"></div>
                        </@shiro.hasPermission>
                    </div>
                </div>
                <div class="bottomline QueryArea" style="margin: 0 1px;">
                    <table border="0" class="form-find" style="height: 45px;">
                        <tbody>
                        <tr>
                            <td>
                                仓库：
                                <select id="warehouseId1" name="warehouseId1" class="txtselect"
                                        datacol="yes" err="分类"
                                        checkexpession="NotNull">
                                    <option value="">请选择</option>
                                    <#if warehouseList??>
                                        <#list warehouseList as warehouse>
                                            <option value="${warehouse.warehouseId}">${warehouse.CName}</option>
                                        </#list>
                                    <#else>
                                        <option value="">无仓库</option>
                                    </#if>
                                </select>
                            </td>
                            <td>
                                批次号：<input id="batchNo1" name="batchNo1" type="text" class="txt"
                                           style="width: 100px">
                            </td>

                            <td>
                                货物类型：
                                <select id="isMaterial1" name="isMaterial1" class="txtselect"
                                        datacol="yes" err="分类"
                                        checkexpession="NotNull">
                                    <option value="">==请先选择仓库==</option>
                                </select>
                            </td>
                            <td>
                                产品状态：
                                <select id="productStatus1" name="productStatus1"
                                        class="txtselect" datacol="yes"
                                        err="状态"
                                        checkexpession="NotNull">
                                    <option value="">请选择</option>
                                </select>
                            </td>
                            <td>
                                产品名称：
                                <select id="productId1" name="productId1" class="txtselect"
                                        datacol="yes" err="分类"
                                        checkexpession="NotNull">
                                    <option value="">请先选择类型</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                入库规格：
                                <select id="productSpecName1" name="productSpecName1"
                                        class="txtselect" datacol="yes" err="状态"
                                        checkexpession="NotNull">
                                    <option value="">==请先选择产品==</option>
                                </select>
                            </td>

                            <td colspan="2">
                                入库时间：
                                <input id="beginTime1" type="text" class="txt Wdate" style="width: 140px; "
                                       onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                                至
                                <input id="endTime1" type="text" class="txt Wdate" style="width: 140px; "
                                       onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                            </td>
                            <td>
                                入库申请人：
                                <input id="enterPerson1" name="enterPerson" type="text" class="txt"
                                       style="width: 100px">
                            </td>
                            <td>
                                经手人：
                                <input id="brokerage1" name="brokerage" type="text" class="txt" style="width: 100px">
                            </td>
                            <td>
                                <input id="btnSearch1" type="button" class="btnSearch" value="查 询">
                                <input id="btnClear1" type="button" class="btnSearch" value="清除">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <table id="gridTable1" tabindex="0" cellspacing="0" cellpadding="0" border="0" role="grid"
                       aria-multiselectable="false" aria-labelledby="gbox_gridTable"
                       class="ui-jqgrid-btable"
                       style="width: 100%;">
                </table>
                <div id="gridPager1" class="ui-state-default ui-jqgrid-pager ui-corner-bottom" dir="ltr"
                     style="width: 100%;height: 40px">
                </div>
            </div>
        </@shiro.hasPermission>

        <@shiro.hasPermission name="/stockRecord/leaveStockRecord">
            <!--出库记录-->
            <div class="tab-pane" id="tab2">
                <div class="tools_bar" style="border-top: none; margin-bottom: 0px;">
                    <@shiro.hasPermission name="/stockRecord/printOut.json">
                        <a id="lr-edit" title="打印出库单" onclick="printOut()" class="tools_btn">
                        <span>
                            <b class="btn-detail">打印出库单</b>
                        </span>
                        </a>
                    </@shiro.hasPermission>
                </div>
                <div class="bottomline QueryArea" style="margin: 0 1px;">
                    <table border="0" class="form-find" style="height: 45px;">
                        <tbody>
                        <tr>
                            <td>
                                仓库：
                                <select id="warehouseId2" name="warehouseId2" class="txtselect"
                                        datacol="yes" err="分类"
                                        checkexpession="NotNull">
                                    <option value="">请选择</option>
                                    <#if warehouseList??>
                                        <#list warehouseList as warehouse>
                                            <option value="${warehouse.warehouseId}">${warehouse.CName}</option>
                                        </#list>
                                    <#else>
                                        <option value="">无仓库</option>
                                    </#if>
                                </select>
                            </td>
                            <td>
                                批次号：<input id="batchNo2" name="batchNo2" type="text" class="txt"
                                           style="width: 100px">
                            </td>

                            <td>
                                货物类型：
                                <select id="isMaterial2" name="isMaterial2" class="txtselect"
                                        datacol="yes" err="分类"
                                        checkexpession="NotNull">
                                    <option value="">==请先选择仓库==</option>
                                </select>
                            </td>
                            <td>
                                产品状态：
                                <select id="productStatus2" name="productStatus2"
                                        class="txtselect" datacol="yes"
                                        err="状态"
                                        checkexpession="NotNull">
                                    <option value="">请选择</option>
                                </select>
                            </td>
                            <td>
                                产品名称：
                                <select id="productId2" name="productId2" class="txtselect"
                                        datacol="yes" err="分类"
                                        checkexpession="NotNull">
                                    <option value="">请先选择类型</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                出库规格：
                                <select id="productSpecName2" name="productSpecName2"
                                        class="txtselect" datacol="yes" err="状态"
                                        checkexpession="NotNull">
                                    <option value="">==请先选择产品==</option>
                                </select>
                            </td>

                            <td colspan="2">
                                出库时间：
                                <input id="beginTime2" type="text" class="txt Wdate" style="width: 140px; "
                                       onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                                至
                                <input id="endTime2" type="text" class="txt Wdate" style="width: 140px; "
                                       onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                            </td>
                            <td>
                                出库申请人：
                                <input id="leavePerson2" name="leavePerson" type="text" class="txt"
                                       style="width: 100px">
                            </td>
                            <td>
                                经手人：
                                <input id="brokerage2" name="brokerage" type="text" class="txt" style="width: 100px">
                            </td>
                            <td>
                                <input id="btnSearch2" type="button" class="btnSearch" value="查 询">
                                <input id="btnClear2" type="button" class="btnSearch" value="清除">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <table id="gridTable2" tabindex="0" cellspacing="0" cellpadding="0" border="0" role="grid"
                       aria-multiselectable="false" aria-labelledby="gbox_gridTable"
                       class="ui-jqgrid-btable"
                       style="width: 100%;">
                </table>
                <div id="gridPager2" class="ui-state-default ui-jqgrid-pager ui-corner-bottom" dir="ltr"
                     style="width: 100%;height: 40px">
                </div>
            </div>
        </@shiro.hasPermission>

        <@shiro.hasPermission name="/stockRecord/lossRecord">
            <!--损耗记录-->
            <div class="tab-pane" id="tab3">
                <div class="bottomline QueryArea" style="margin: 0 1px;">
                    <table border="0" class="form-find" style="height: 45px;">
                        <tbody>
                        <tr>
                            <td>
                                仓库：
                                <select id="warehouseId3" name="warehouseId3" class="txtselect"
                                        datacol="yes" err="分类"
                                        checkexpession="NotNull">
                                    <option value="">请选择</option>
                                    <#if warehouseList??>
                                        <#list warehouseList as warehouse>
                                            <option value="${warehouse.warehouseId}">${warehouse.CName}</option>
                                        </#list>
                                    <#else>
                                        <option value="">无仓库</option>
                                    </#if>
                                </select>
                            </td>
                            <td>
                                批次号：<input id="batchNo3" name="batchNo3" type="text" class="txt"
                                           style="width: 100px">
                            </td>

                            <td>
                                货物类型：
                                <select id="isMaterial3" name="isMaterial3" class="txtselect"
                                        datacol="yes" err="分类"
                                        checkexpession="NotNull">
                                    <option value="">==请先选择仓库==</option>
                                </select>
                            </td>
                            <td>
                                产品状态：
                                <select id="productStatus3" name="productStatus3"
                                        class="txtselect" datacol="yes"
                                        err="状态"
                                        checkexpession="NotNull">
                                    <option value="">请选择</option>
                                </select>
                            </td>
                            <td>
                                产品名称：
                                <select id="productId3" name="productId3" class="txtselect"
                                        datacol="yes" err="分类"
                                        checkexpession="NotNull">
                                    <option value="">请先选择类型</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                出库规格：
                                <select id="productSpecName3" name="productSpecName3"
                                        class="txtselect" datacol="yes" err="状态"
                                        checkexpession="NotNull">
                                    <option value="">==请先选择产品==</option>
                                </select>
                            </td>

                            <td colspan="2">
                                出库时间：
                                <input id="beginTime3" type="text" class="txt Wdate" style="width: 140px; "
                                       onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                                至
                                <input id="endTime3" type="text" class="txt Wdate" style="width: 140px; "
                                       onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                            </td>
                            <td>
                                出库申请人：
                                <input id="leavePerson3" name="leavePerson3" type="text" class="txt"
                                       style="width: 100px">
                            </td>
                            <td>
                                经手人：
                                <input id="brokerage3" name="brokerage3" type="text" class="txt" style="width: 100px">
                            </td>
                            <td>
                                <input id="btnSearch3" type="button" class="btnSearch" value="查 询">
                                <input id="btnClear3" type="button" class="btnSearch" value="清除">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <table id="gridTable3" tabindex="0" cellspacing="0" cellpadding="0" border="0" role="grid"
                       aria-multiselectable="false" aria-labelledby="gbox_gridTable"
                       class="ui-jqgrid-btable"
                       style="width: 100%;">
                </table>
                <div id="gridPager3" class="ui-state-default ui-jqgrid-pager ui-corner-bottom" dir="ltr"
                     style="width: 100%;height: 40px">
                </div>
            </div>
        </@shiro.hasPermission>

        <@shiro.hasPermission name="/stockRecord/returnGoodsRecord">
            <!--返货处理记录-->
            <div class="tab-pane" id="tab4">
                <div class="tools_bar" style="border-top: none; margin-bottom: 0px;">
                    <div class="PartialButton">
                        <@shiro.hasPermission name="/stockRecord/returnExport.json">
                            <a id="lr-delete" title="返货数据导出" onclick="returnGoodsExport()" class="tools_btn">
                        <span>
                            <b class="btn-export">返货数据导出</b>
                        </span>
                            </a>
                        </@shiro.hasPermission>
                    </div>
                </div>
                <div class="bottomline QueryArea" style="margin: 0 1px;">
                    <table border="0" class="form-find" style="height: 45px;">
                        <tbody>
                        <tr>
                            <td>
                                货物类型：
                                <select id="isMaterial4" name="isMaterial4" class="txtselect"
                                        datacol="yes" err="分类"
                                        checkexpession="NotNull">
                                    <option value="">==请先选择仓库==</option>
                                </select>
                            </td>
                            <td>
                                产品名称：
                                <select id="productId4" name="productId4" class="txtselect"
                                        datacol="yes" err="分类"
                                        checkexpession="NotNull">
                                    <option value="">请先选择类型</option>
                                </select>
                            </td>
                            <td>
                                规格：
                                <select id="productSpecName4" name="productSpecName4"
                                        class="txtselect" datacol="yes" err="状态"
                                        checkexpession="NotNull">
                                    <option value="">==请先选择产品==</option>
                                </select>
                            </td>

                            <td colspan="2">
                                操作时间：
                                <input id="beginTime4" type="text" class="txt Wdate" style="width: 140px; "
                                       onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                                至
                                <input id="endTime4" type="text" class="txt Wdate" style="width: 140px; "
                                       onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                            </td>
                            <td>
                                操作人：
                                <input id="operator" name="operator" type="text" class="txt" style="width: 100px">
                            </td>
                            <td>
                                <input id="btnSearch4" type="button" class="btnSearch" value="查 询">
                                <input id="btnClear4" type="button" class="btnSearch" value="清除">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <table id="gridTable4" tabindex="0" cellspacing="0" cellpadding="0" border="0" role="grid"
                       aria-multiselectable="false" aria-labelledby="gbox_gridTable"
                       class="ui-jqgrid-btable"
                       style="width: 100%;">
                </table>
                <div id="gridPager4" class="ui-state-default ui-jqgrid-pager ui-corner-bottom" dir="ltr"
                     style="width: 100%;height: 40px">
                </div>
            </div>
        </@shiro.hasPermission>
        </div>
    </div>
</div>

<@shiro.hasPermission name="/stockRecord/returnManage.json">
<!--返货登记-->
<div id="returnManageModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label>
        </button>
        <h4 class="modal-title">返货登记</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="addReturnGoods" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>

<@shiro.hasPermission name="/stockRecord/PrintCode.json">
<#--打印箱标-->
<div id="printBoxLabelModal" class="modal fade " data-width="960" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label>
        </button>
        <h4 class="modal-title">打印箱标</h4>
    </div>
    <div class="modal-body table-responsive">
        <table id="boxLabelTable" class="table table-bordered">
            <tr>
                <th width="10%">批次号</th>
                <th width="10%">货物类型</th>
                <th width="8%">产品名称</th>
                <th width="8%">产品状态</th>
                <th width="8%">入库规格</th>
                <th width="8%">入库总量</th>
                <th width="8%">单位</th>
                <th width="12%">入库时间</th>
                <th width="8%">件数</th>
                <th width="8%">单件重量</th>
                <th width="8%">多余重量</th>
            </tr>
            <tr>
                <td>
                </td>
                <td>
                </td>
                <td>
                </td>
                <td>
                </td>
                <td>
                </td>
                <td>
                </td>
                <td>
                </td>
                <td>
                </td>
                <td>
                </td>
                <td>
                    <input id="everyWeight" type="text" class="form-control">
                </td>
                <td>
                </td>
            </tr>
        </table>
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="printBoxLabel" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>

<script>
    var resourceId;
    $(document).ready(function () {
        //资源id
        resourceId = top.$("#ModuleId").val();
        if (!$("#myRecordTab").find("li:eq(0)").hasClass("active")) {
            $("#myRecordTab").find("li:eq(0)").addClass("active");
            $("div.tab-pane:eq(0)").addClass("active");
        }

    <@shiro.hasPermission name="/stockRecord/enterStockRecord">
        //入库记录
        initSearch(1);
        GetGrid1();
    </@shiro.hasPermission>

    <@shiro.hasPermission name="/stockRecord/leaveStockRecord">
        //出库记录
        initSearch(2);
        GetGrid2();
    </@shiro.hasPermission>

    <@shiro.hasPermission name="/stockRecord/lossRecord">
        //损耗记录
        initSearch(3);
        GetGrid3();
    </@shiro.hasPermission>

    <@shiro.hasPermission name="/stockRecord/returnGoodsRecord">
        //返货记录
        initSearch(4);
        GetGrid4();
    </@shiro.hasPermission>
    });

    /**
     * 搜索栏初始化
     * */
    function initSearch(num) {
        //获取货物类型(根据num)
        $.post("${request.contextPath}/stockRecord/getGoodsType.json", {num: num}, function (res) {
            let typeEl = $("#isMaterial" + num);
            typeEl.find("option").remove();
            typeEl.append("<option value=''>==请选择==</option>");
            if (false == res.success) {
                typeEl.find("option").remove();
                typeEl.append("<option value=''>" + "无货物类型" + "</option>");
            } else {
                for (i in res.obj) {
                    typeEl.append("<option value='" + res.obj[i].isMaterial + "'>" + res.obj[i].materialName + "</option>");
                }
            }
        }, "JSON");

        //获取产品状态（根据num）
        if (4 !== num) {
            $.post("${request.contextPath}/stockRecord/getProductStatus.json", {num: num}, function (res) {
                let status = $("#productStatus" + num);
                status.find("option").remove();
                status.append("<option value=''>==请选择==</option>");
                var resObj = res.obj;
                if (res.success) {
                    for (i in resObj) {
                        status.append("<option value='" + resObj[i].productStatus + "'>" + resObj[i].productStatusName + "</option>");
                    }
                } else {
                    status.find("option").remove();
                    status.append("<option value=''>==无产品状态==</option>");
                }
            }, "JSON");
        }
        //货物类型isMaterial
    <@shiro.hasPermission name="/stockRecord/enterStockRecord">
        loadProduct1("#isMaterial1", "#productId1", "#productSpecName1");
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/stockRecord/leaveStockRecord">
        loadProduct2("#isMaterial2", "#productId2", "#productSpecName2");
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/stockRecord/lossRecord">
        loadProduct3("#isMaterial3", "#productId3", "#productSpecName3");
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/stockRecord/returnGoodsRecord">
        loadProduct4("#isMaterial4", "#productId4", "#productSpecName4");
    </@shiro.hasPermission>
    }

    <@shiro.hasPermission name="/stockRecord/enterStockRecord">
    /**
     * 入库表
     * 货物类型，产品，规格名称
     * @param typeId 货物类型dom元素id
     * @param productId 产品dom元素id
     * @param specId 规格名称dom元素id
     * */
    function loadProduct1(typeId, productId, specId) {
        var typeEl = $("" + typeId);
        var productEl = $("" + productId);
        var specEl = $("" + specId);
        typeEl.unbind("click");
        typeEl.click(function () {
            if (typeEl.val() == "") {
                productEl.find("option").remove();
                productEl.append("<option value=''>请先选择类型</option>");
                specEl.find("option").remove();
                specEl.append("<option value=''>请先选择产品</option>");
            } else {
                productEl.find("option").remove();
                productEl.append("<option value=''>请先选择类型</option>");
                var type = typeEl.val();
                $.post("${request.contextPath}/stockRecord/getEnterStockProductList.json", {isMaterial: type}, function (res) {
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
                $.post("${request.contextPath}/stockRecord/getEnterStockSpecList.json", {
                    isMaterial: typeEl.val(),
                    productId: type
                }, function (res) {
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
     * 加载入库记录表格
     * @param url 请求地址
     * @param id 分页控件id
     * @param table 表格id
     * */
    function GetGrid1() {
        $("#gridTable1").jqGrid({
            url: "${request.contextPath}/stockRecord/getAllEnterStockDetail.json",
            datatype: "json",
            height: $(window).height() - 280,
            autowidth: true,
            colModel: [
                {label: "", name: "enterStockDetailId", index: "enterStockDetailId", hidden: true},
                {label: "", name: "enterStockId", index: "enterStockId", hidden: true},
                {
                    label: "入库编号", name: "puEnterStock", index: "puEnterStock", width: 150, align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue.enterNo;
                    }
                },
                {
                    label: "入库仓库", name: "zsWarehouse", index: "zsWarehouse", width: 60, align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue.cname;
                    }
                },
                {label: "批次号", name: "batchNo", index: "batchNo", width: 100, align: "center"},
                {
                    label: "货物类型",
                    name: "baDataDictionaryDetails",
                    index: "baDataDictionaryDetails",
                    width: 100,
                    align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue.cname;
                    }
                },
                {label: "产品名称", name: "productName", index: "productName", width: 60, align: "center"},
                {label: "规格", name: "enterProductSpecName", index: "enterProductSpecName", width: 60, align: "center"},
                {label: "原料数量", name: "materialWeight", index: "materialWeight", width: 60, align: "center"},
                {label: "单位", name: "enterUnitName", index: "enterUnitName", width: 60, align: "center"},
                {
                    label: "产品状态",
                    name: "baDataDictionaryDetailsTwo",
                    index: "baDataDictionaryDetailsTwo",
                    width: 60,
                    align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue.cname;
                    }
                },
                {label: "入库规格", name: "productSpecName", index: "productSpecName", width: 80, align: "center"},
                {label: '入库数量', name: 'inWeight', index: 'inWeight', width: 80, align: 'center'},
                {label: "单位", name: "unitName", index: "unitName", width: 60, align: "center"},
                {
                    label: "入库时间", name: "puEnterStock", index: "puEnterStock", width: 125, align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue.enterDate;
                    }
                },
                {
                    label: "入库申请人", name: "puEnterStock", index: "puEnterStock", width: 80, align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue.enterPerson;
                    }
                },
                {
                    label: "经手人", name: "puEnterStock", index: "puEnterStock", width: 55, align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue.brokerage;
                    }
                },
                {label: "成品批次号", name: "goodsBatchNo", index: "goodsBatchNo", width: 80, align: "center"},
                {
                    label: "记入活参库", name: "isSeaCucumber", index: "isSeaCucumber", width: 80, align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        if (1 == cellvalue) {
                            return "是";
                        } else if (2 == cellvalue) {
                            return "否";
                        } else {
                            return "";
                        }
                    }
                },
                {
                    label: "时间预警", name: "puEnterStock", index: "puEnterStock", width: 60, align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue.timeWarn;
                    }
                },
                {
                    label: "库存预警", name: "puEnterStock", index: "puEnterStock", width: 60, align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue.stockWarn;
                    }
                },
                {
                    label: "备注1", name: "puEnterStock", index: "puEnterStock", width: 150, align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue.remark;
                    }
                },
                {label: "备注2", name: "remark", index: "remark", width: 150, align: "center"},
                {
                    label: "入库时间", name: "puEnterStock", index: "puEnterStock", hidden: true,
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue.enterDate;
                    }
                },
                {label: "入库箱码", name: "boxCode", index: "boxCode", hidden: true}
            ],
            pagerpos: "right",
            viewrecords: true,
            recordpos: "left",
            pager: "#gridPager1",
            rowNum: 10,
            rowList: [10, 20, 30, 50, 100],
            jsonReader: {
                root: "root", page: "page", total: "total", records: "records"
            },
            rownumbers: true,
            sortname: 'listIndex',
            sortorder: 'asc',
            shrinkToFit: false,
            gridview: true
        });
    }

    /**
     * 入库记录搜索
     * */
    $("#btnSearch1").unbind("click");
    $("#btnSearch1").click(function () {
        var warehouseId = $("#warehouseId1").val();
        var batchNo = $.trim($("#batchNo1").val());
        var isMaterial = $("#isMaterial1").val();
        var productStatus = $("#productStatus1").val();
        var productId = $("#productId1").val();
        var productSpecName = $("#productSpecName1").val();
        var beginTime = $("#beginTime1").val();
        var endTime = $("#endTime1").val();
        var enterPerson = $("#enterPerson1").val();
        var brokerage = $("#brokerage1").val();
        var postData = {
            warehouseId: warehouseId,
            batchNo: batchNo,
            isMaterial: isMaterial,
            productStatus: productStatus,
            productId: productId,
            productSpecName: productSpecName,
            beginTime: beginTime,
            endTime: endTime,
            enterPerson: enterPerson,
            brokerage: brokerage
        };
        //提交post并刷新表格
        $("#gridTable1").jqGrid("setGridParam", {
            postData: postData,
            url: "${request.contextPath}/stockRecord/getAllEnterStockDetail.json",
            page: 1
        }).trigger("reloadGrid");
    });
    $("#btnClear1").unbind("click");
    $("#btnClear1").click(function () {
        //重置表格
        $("#warehouseId1").val("");
        $("#batchNo1").val("");
        $("#isMaterial1").val("");
        $("#productStatus1").val("");
        $("#productId1").val("");
        $("#productSpecName1").val("");
        $("#beginTime1").val("");
        $("#endTime1").val("");
        $("#enterPerson1").val("");
        $("#brokerage1").val("");
        var postData = {
            warehouseId: "",
            batchNo: "",
            isMaterial: "",
            productStatus: "",
            productId: "",
            productSpecName: "",
            beginTime: "",
            endTime: "",
            enterPerson: "",
            brokerage: ""
        };
        //提交post并刷新表格
        $("#gridTable1").jqGrid("setGridParam", {
            postData: postData,
            url: "${request.contextPath}/stockRecord/getAllEnterStockDetail.json",
            page: 1
        }).trigger("reloadGrid");
    });
    </@shiro.hasPermission>

    <@shiro.hasPermission name="/stockRecord/leaveStockRecord">
    /**
     * 出库表
     * 货物类型，产品，规格名称
     * @param typeId 货物类型dom元素id
     * @param productId 产品dom元素id
     * @param specId 规格名称dom元素id
     * */
    function loadProduct2(typeId, productId, specId) {
        var typeEl = $("" + typeId);
        var productEl = $("" + productId);
        var specEl = $("" + specId);
        typeEl.unbind("click");
        typeEl.click(function () {
            if (typeEl.val() == "") {
                productEl.find("option").remove();
                productEl.append("<option value=''>请先选择类型</option>");
                specEl.find("option").remove();
                specEl.append("<option value=''>请先选择产品</option>");
            } else {
                productEl.find("option").remove();
                productEl.append("<option value=''>请先选择类型</option>");
                var type = typeEl.val();
                $.post("${request.contextPath}/stockRecord/getLeaveStockProductList.json", {isMaterial: type}, function (res) {
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
                $.post("${request.contextPath}/stockRecord/getLeaveStockSpecList.json", {
                    isMaterial: typeEl.val(),
                    productId: type
                }, function (res) {
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
     * 加载出库记录表格
     * @param url 请求地址
     * @param id 分页控件id
     * @param table 表格id
     * */
    function GetGrid2() {
        $("#gridTable2").jqGrid({
            url: "${request.contextPath}/stockRecord/getAllLeaveStockDetail.json",
            datatype: "json",
            height: $(window).height() - 280,
            width: $(window).width(),
            colModel: [
                {label: "", name: "leaveStockDetailId", index: "leaveStockDetailId", hidden: true},
                {label: "", name: "leaveStockId", index: "leaveStockId", hidden: true},
                {
                    label: "出库编号", name: "saLeaveStock", index: "saLeaveStock", width: 160, align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue.leaveNo;
                    }
                },
                {
                    label: "生产任务", name: "saLeaveStock", index: "saLeaveStock", width: 150, align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue.produceTaskName;
                    }
                },
                {
                    label: "生产编号", name: "saLeaveStock", index: "saLeaveStock", width: 145, align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue.produceTaskNo;
                    }
                },
                {
                    label: "生产任务标签", name: "saLeaveStock", index: "saLeaveStock", width: 80, align: "center",
                    formatter: function (cellvalue, option, rowObject) {
                        return cellvalue.produceTaskTag;
                    }
                },
                {
                    label: "仓库", name: "zsWarehouse", index: "zsWarehouse", width: 60, align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue.cname;
                    }
                },
                {label: "批次号", name: "batchNo", index: "batchNo", width: 60, align: "center"},
                {
                    label: "货物类型",
                    name: "baDataDictionaryDetails",
                    index: "baDataDictionaryDetails",
                    width: 60,
                    align: "center",
                    formatter: function (cellvalue, options, rowObject) {

                        return cellvalue.cname;
                    }
                },

                {label: "产品名称", name: "productName", index: "productName", width: 60, align: "center"},
                {
                    label: "产品状态",
                    name: "baDataDictionaryDetailsTwo",
                    index: "baDataDictionaryDetailsTwo",
                    width: 60,
                    align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue.cname;
                    }
                },
                {label: "出库规格", name: "productSpecName", index: "productSpecName", width: 80, align: "center"},
                {label: '出库数量', name: "outWeight", index: "outWeight", width: 80, align: 'center'},
                {label: "单位", name: "unitName", index: "unitName", width: 60, align: "center"},
                {
                    label: "出库时间", name: "saLeaveStock", index: "saLeaveStock", width: 125, align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue.leaveDate;
                    }
                },
                {
                    label: "出库申请人", name: "saLeaveStock", index: "saLeaveStock", width: 80, align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue.leavePerson;
                    }
                },
                {
                    label: "经手人", name: "saLeaveStock", index: "saLeaveStock", width: 55, align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue.brokerage;
                    }
                },
                {
                    label: "备注1", name: "saLeaveStock", index: "saLeaveStock", width: 150, align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue.remark;
                    }
                },
                {label: "备注2", name: "remark", index: "remark", width: 150, align: "center"}
            ],
            pagerpos: "right",
            viewrecords: true,
            recordpos: "left",
            pager: "#gridPager2",
            rowNum: 10,
            rowList: [10, 20, 30, 50, 100],
            jsonReader: {
                root: "root", page: "page", total: "total", records: "records"
            },
            rownumbers: true,
            sortname: 'listIndex',
            sortorder: 'asc',
            shrinkToFit: false,
            gridview: true
        });
    }

    /**
     * 出库记录搜索
     **/
    $("#btnSearch2").unbind("click");
    $("#btnSearch2").click(function () {
        var warehouseId = $("#warehouseId2").val();
        var batchNo = $.trim($("#batchNo2").val());
        var isMaterial = $("#isMaterial2").val();
        var productStatus = $("#productStatus2").val();
        var productId = $("#productId2").val();
        var productSpecName = $("#productSpecName2").val();
        var beginTime = $("#beginTime2").val();
        var endTime = $("#endTime2").val();
        var leavePerson = $("#leavePerson2").val();
        var brokerage = $("#brokerage2").val();
        var postData = {
            warehouseId: warehouseId,
            batchNo: batchNo,
            isMaterial: isMaterial,
            productStatus: productStatus,
            productId: productId,
            productSpecName: productSpecName,
            beginTime: beginTime,
            endTime: endTime,
            leavePerson: leavePerson,
            brokerage: brokerage
        };
        //提交post并刷新表格
        $("#gridTable2").jqGrid("setGridParam", {
            postData: postData,
            url: "${request.contextPath}/stockRecord/getAllLeaveStockDetail.json",
            page: 1
        }).trigger("reloadGrid");
    });

    $("#btnClear2").unbind("click");
    $("#btnClear2").click(function () {
        //重置表格
        $("#warehouseId2").val("");
        $("#batchNo2").val("");
        $("#isMaterial2").val("");
        $("#productStatus2").val("");
        $("#productId2").val("");
        $("#productSpecName2").val("");
        $("#beginTime2").val("");
        $("#endTime2").val("");
        $("#leavePerson2").val("");
        $("#brokerage2").val("");
        var postData = {
            warehouseId: "",
            batchNo: "",
            isMaterial: "",
            productStatus: "",
            productId: "",
            productSpecName: "",
            beginTime: "",
            endTime: "",
            leavePerson: "",
            brokerage: ""
        };
        //提交post并刷新表格
        $("#gridTable2").jqGrid("setGridParam", {
            postData: postData,
            url: "${request.contextPath}/stockRecord/getAllLeaveStockDetail.json",
            page: 1
        }).trigger("reloadGrid");
    });
    </@shiro.hasPermission>

    <@shiro.hasPermission name="/stockRecord/lossRecord">
    /**
     * 损耗表
     * 货物类型，产品，规格名称
     * @param typeId 货物类型dom元素id
     * @param productId 产品dom元素id
     * @param specId 规格名称dom元素id
     * */
    function loadProduct3(typeId, productId, specId) {
        var typeEl = $("" + typeId);
        var productEl = $("" + productId);
        var specEl = $("" + specId);
        typeEl.unbind("click");
        typeEl.click(function () {
            if (typeEl.val() == "") {
                productEl.find("option").remove();
                productEl.append("<option value=''>请先选择类型</option>");
                specEl.find("option").remove();
                specEl.append("<option value=''>请先选择产品</option>");
            } else {
                productEl.find("option").remove();
                productEl.append("<option value=''>请先选择类型</option>");
                var type = typeEl.val();
                $.post("${request.contextPath}/stockRecord/getLossStockProductList.json", {isMaterial: type}, function (res) {
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
                $.post("${request.contextPath}/stockRecord/getLossStockSpecList.json", {
                    isMaterial: typeEl.val(),
                    productId: type
                }, function (res) {
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
     * 加载损耗记录表格
     * @param url 请求地址
     * @param id 分页控件id
     * @param table 表格id
     * */
    function GetGrid3() {
        $("#gridTable3").jqGrid({
            url: "${request.contextPath}/stockRecord/getAllLossRecord.json",
            datatype: "json",
            height: $(window).height() - 230,
            width: $(window).width(),
            colModel: [
                {label: "", name: "leaveStockDetailId", index: "leaveStockDetailId", width: 60, hidden: true},
                {
                    label: "仓库", name: "zsWarehouse", index: "zsWarehouse", width: 60, align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue.cname;
                    }
                },
                {label: "批次号", name: "batchNo", index: "batchNo", width: 60, align: "center"},
                {
                    label: "货物类型",
                    name: "baDataDictionaryDetails",
                    index: "baDataDictionaryDetails",
                    width: 60,
                    align: "center",
                    formatter: function (cellvalue, options, rowObject) {

                        return cellvalue.cname;
                    }
                },

                {label: "产品名称", name: "productName", index: "productName", width: 60, align: "center"},
                {
                    label: "产品状态",
                    name: "baDataDictionaryDetailsTwo",
                    index: "baDataDictionaryDetailsTwo",
                    width: 60,
                    align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue.cname;
                    }
                },
                {label: "出库规格", name: "productSpecName", index: "productSpecName", width: 80, align: "center"},
                {label: '损耗类型', name: "loss", index: "loss", width: 80, align: 'center'},
                {label: '损耗数量', name: "outWeight", index: "outWeight", width: 80, align: 'center'},
                {label: "单位", name: "unitName", index: "unitName", width: 60, align: "center"},
                {
                    label: "备注1", name: "saLeaveStock", index: "saLeaveStock", width: 150, align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue.remark;
                    }
                },
                {label: "备注2", name: "remark", index: "remark", width: 150, align: "center"},
                {
                    label: "出库时间", name: "saLeaveStock", index: "saLeaveStock", width: 125, align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue.leaveDate;
                    }
                },
                {
                    label: "出库申请人", name: "saLeaveStock", index: "saLeaveStock", width: 80, align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue.leavePerson;
                    }
                },
                {
                    label: "经手人", name: "saLeaveStock", index: "saLeaveStock", width: 55, align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue.brokerage;
                    }
                }

            ],
            pagerpos: "right",
            viewrecords: true,
            recordpos: "left",
            pager: "#gridPager3",
            rowNum: 10,
            rowList: [10, 20, 30, 50, 100],
            jsonReader: {
                root: "root", page: "page", total: "total", records: "records"
            },
            rownumbers: true,
            sortname: 'listIndex',
            sortorder: 'asc',
            shrinkToFit: false,
            gridview: true
        });
    }

    /**
     * 损耗记录搜索
     * */
    $("#btnSearch3").unbind("click");
    $("#btnSearch3").click(function () {
        var warehouseId = $("#warehouseId3").val();
        var batchNo = $.trim($("#batchNo3").val());
        var isMaterial = $("#isMaterial3").val();
        var productStatus = $("#productStatus3").val();
        var productId = $("#productId3").val();
        var productSpecName = $("#productSpecName3").val();
        var beginTime = $("#beginTime3").val();
        var endTime = $("#endTime3").val();
        var leavePerson = $("#leavePerson3").val();
        var brokerage = $("#brokerage3").val();
        var postData = {
            warehouseId: warehouseId,
            batchNo: batchNo,
            isMaterial: isMaterial,
            productStatus: productStatus,
            productId: productId,
            productSpecName: productSpecName,
            beginTime: beginTime,
            endTime: endTime,
            leavePerson: leavePerson,
            brokerage: brokerage
        };
        //提交post并刷新表格
        $("#gridTable3").jqGrid("setGridParam", {
            postData: postData,
            url: "${request.contextPath}/stockRecord/getAllLossRecord.json",
            page: 1
        }).trigger("reloadGrid");
    });

    $("#btnClear3").unbind("click");
    $("#btnClear3").click(function () {
        //重置表格
        $("#warehouseId3").val("");
        $("#batchNo3").val("");
        $("#isMaterial3").val("");
        $("#productStatus3").val("");
        $("#productId3").val("");
        $("#productSpecName3").val("");
        $("#beginTime3").val("");
        $("#endTime3").val("");
        $("#leavePerson3").val("");
        $("#brokerage3").val("");
        var postData = {
            warehouseId: "",
            batchNo: "",
            isMaterial: "",
            productStatus: "",
            productId: "",
            productSpecName: "",
            beginTime: "",
            endTime: "",
            leavePerson: "",
            brokerage: ""
        };
        //提交post并刷新表格
        $("#gridTable3").jqGrid("setGridParam", {
            postData: postData,
            url: "${request.contextPath}/stockRecord/getAllLossRecord.json",
            page: 1
        }).trigger("reloadGrid");
    });
    </@shiro.hasPermission>

    <@shiro.hasPermission name="/stockRecord/returnGoodsRecord">
    /**
     * 返货记录
     * 货物类型，产品，规格名称
     * @param typeId 货物类型dom元素id
     * @param productId 产品dom元素id
     * @param specId 规格名称dom元素id
     * */
    function loadProduct4(typeId, productId, specId) {
        var typeEl = $("" + typeId);
        var productEl = $("" + productId);
        var specEl = $("" + specId);
        typeEl.unbind("click");
        typeEl.click(function () {
            if (typeEl.val() == "") {
                productEl.find("option").remove();
                productEl.append("<option value=''>请先选择类型</option>");
                specEl.find("option").remove();
                specEl.append("<option value=''>请先选择产品</option>");
            } else {
                productEl.find("option").remove();
                productEl.append("<option value=''>请先选择类型</option>");
                var type = typeEl.val();
                $.post("${request.contextPath}/stockRecord/getReturnGoodsProductList.json", {isMaterial: type}, function (res) {
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
                $.post("${request.contextPath}/stockRecord/getReturnGoodsSpecList.json", {
                    isMaterial: typeEl.val(),
                    productId: type
                }, function (res) {
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
     * 加载返货处理记录表格
     * @param url 请求地址
     * @param id 分页控件id
     * @param table 表格id
     * */
    function GetGrid4() {
        $("#gridTable4").jqGrid({
            url: "${request.contextPath}/stockRecord/getAllReturnGoodsRecord.json",
            datatype: "json",
            height: $(window).height() - 280,
            width: $(window).width(),
            colModel: [
                {label: "", name: "returnGoodsDetailId", index: "returnGoodsDetailId", hidden: true},
                {label: "产品名称", name: "productName", index: "productName", width: 60, align: "center"},
                {label: "批次号", name: "batchNo", index: "batchNo", width: 60, align: "center"},
                {label: "规格", name: "productSpecName", index: "productSpecName", width: 80, align: "center"},
                {label: "顾客姓名", name: "customerName", index: "customerName", width: 60, align: "center"},
                {label: "提货日期", name: "takeDate", index: "takeDate", width: 80, align: "center"},
                {label: "卡号", name: "cardNumber", index: "cardNumber", width: 100, align: "center"},
                {label: "顾客地址", name: "customerAddress", index: "customerAddress", width: 100, align: "center"},
                {label: "返货日期", name: "returnDate", index: "returnDate", width: 80, align: "center"},
                {label: "快递单号", name: "expressNumber", index: "expressNumber", width: 100, align: "center"},
                {label: "返货种类", name: "goodsType", index: "goodsType", width: 100, align: "center"},
                {
                    label: "上传图片", name: "iamges", index: "iamges", width: 100, align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        if (null != cellvalue && "" != cellvalue) {
                            return "已上传";
                        } else {
                            return "未上传";
                        }
                    }
                },
                {label: "操作人", name: "operator", index: "operator", width: 100, align: "center"},
                {label: "操作时间", name: "updateDate", index: "updateDate", width: 80, align: "center"},
                {
                    label: "不合格情况描述",
                    name: "unqualifiedDescription",
                    index: "unqualifiedDescription",
                    width: 100,
                    align: "center"
                },
                {label: "检察员1", name: "inspectorOne", index: "inspectorOne", width: 100, align: "center"},
                {label: "检查日期1", name: "checkDateOne", index: "checkDateOne", width: 80, align: "center"},
                {label: "不合格措施", name: "treatmentMeasures", index: "treatmentMeasures", width: 100, align: "center"},
                {label: "检察员2", name: "inspectorTwo", index: "inspectorTwo", width: 100, align: "center"},
                {label: "检查日期2", name: "checkDateTwo", index: "checkDateTwo", width: 80, align: "center"},
                {label: "不合格性质", name: "nonconformance", index: "nonconformance", width: 100, align: "center"},
                {label: "处置方式", name: "disposalWay", index: "disposalWay", width: 100, align: "center"},
                {label: "主持人", name: "host", index: "host", width: 100, align: "center"},
                {label: "参加人员", name: "participants", index: "participants", width: 100, align: "center"},
                {label: "备注", name: "remark", index: "remark", width: 100, align: "center"},
            ],
            pagerpos: "right",
            viewrecords: true,
            recordpos: "left",
            pager: "#gridPager4",
            rowNum: 10,
            rowList: [10, 20, 30, 50, 100],
            jsonReader: {
                root: "root", page: "page", total: "total", records: "records"
            },
            rownumbers: true,
            sortname: 'listIndex',
            sortorder: 'asc',
            shrinkToFit: false,
            gridview: true
        });
    }

    /**
     * 返货记录搜索
     * */
    $("#btnSearch4").unbind("click");
    $("#btnSearch4").click(function () {
        var isMaterial = $("#isMaterial4").val();
        var productId = $("#productId4").val();
        var productSpecName = $("#productSpecName4").val();
        var beginTime = $("#beginTime4").val();
        var endTime = $("#endTime4").val();
        var operator = $("#operator").val();
        var postData = {
            goodsTypeId: isMaterial,
            productId: productId,
            productSpecName: productSpecName,
            beginTime: beginTime,
            endTime: endTime,
            operator: operator
        };
        //提交post并刷新表格
        $("#gridTable4").jqGrid("setGridParam", {
            postData: postData,
            url: "${request.contextPath}/stockRecord/getAllReturnGoodsRecord.json",
            page: 1
        }).trigger("reloadGrid");
    });

    $("#btnClear4").unbind("click");
    $("#btnClear4").click(function () {
        //重置表格
        $("#isMaterial4").val("");
        $("#productId4").val("");
        $("#productSpecName4").val("");
        $("#beginTime4").val("");
        $("#endTime4").val("");
        $("#operator").val("");
        var postData = {
            goodsTypeId: "",
            productId: "",
            productSpecName: "",
            beginTime: "",
            endTime: "",
            operator: ""
        };
        //提交post并刷新表格
        $("#gridTable4").jqGrid("setGridParam", {
            postData: postData,
            url: "${request.contextPath}/stockRecord/getAllReturnGoodsRecord.json",
            page: 1
        }).trigger("reloadGrid");
    });
    </@shiro.hasPermission>

    <@shiro.hasPermission name="/stockRecord/returnManage.json">
    //返货处理、返货登记
    function returnManage() {
        $("#returnManageModal").modal({
            remote: "${request.contextPath}/stockRecord/returnManageModal.htm?resourceId=" + resourceId
        });
    }
    </@shiro.hasPermission>

    <@shiro.hasPermission name="/stockRecord/returnExport.json">
    //返货数据导出
    function returnGoodsExport() {
        var returnGoodsDetailId = GetJqGridRowValue("#gridTable4", "returnGoodsDetailId");
        if (null != returnGoodsDetailId && "" != returnGoodsDetailId) {
            window.open("${request.contextPath}/stockRecord/returnGoodsExport.json?returnGoodsDetailId=" + returnGoodsDetailId + "&resourceId=" + resourceId, "_blank");
            $("#gridTable4").trigger("reloadGrid");
        } else {
            tipDialog('请选择返货记录!', 4, 'warning');
        }
    }
    </@shiro.hasPermission>

    <@shiro.hasPermission name="/stockRecord/exportStockRecord.json">
    //数据导出
    function exportStockRecord() {
        let arr = new Array();
        $('input[name="stockRecordTab"]').each(function (index, item) {
                    arr.push($(this).val());
                }
        );
        if (arr.length > 0) {
            window.open("${request.contextPath}/stockRecord/exportStockRecord.json?resourceId=" + resourceId + "&arr=" + arr, "_blank");
        }
    }
    </@shiro.hasPermission>

    <@shiro.hasPermission name="/stockRecord/PrintCode.json">
    //打印箱标
    function printBoxLabel() {
        let batchNo = GetJqGridRowValue("#gridTable1", "batchNo");
        if (null == batchNo) {
            tipDialog('请选择记录!', 4, 'warning');
            return;
        }
        let getData = {
            batchNo: batchNo,
            materialName: GetJqGridRowValue("#gridTable1", "baDataDictionaryDetails"),
            productName: GetJqGridRowValue("#gridTable1", "productName"),
            productStatusName: GetJqGridRowValue("#gridTable1", "baDataDictionaryDetailsTwo"),
            productSpecName: GetJqGridRowValue("#gridTable1", "productSpecName"),
            inWeight: GetJqGridRowValue("#gridTable1", "inWeight"),
            unitName: GetJqGridRowValue("#gridTable1", "unitName"),
            createDate: GetJqGridRowValue("#gridTable1", "puEnterStock"),
            qrcode: GetJqGridRowValue("#gridTable1", "boxCode"),
            every: "",
            num: ""
        }
        $("#printBoxLabelModal").modal("show");
        var boxLabelTable = $("#boxLabelTable").find("tr").eq(1);
        boxLabelTable.find("td").eq(0).html(batchNo);
        boxLabelTable.find("td").eq(1).html(getData.materialName);
        boxLabelTable.find("td").eq(2).html(getData.productName);
        boxLabelTable.find("td").eq(3).html(getData.productStatusName);
        boxLabelTable.find("td").eq(4).html(getData.productSpecName);
        boxLabelTable.find("td").eq(5).html(getData.inWeight);
        boxLabelTable.find("td").eq(6).html(getData.unitName);
        boxLabelTable.find("td").eq(7).html(getData.createDate);
        boxLabelTable.find("td").eq(8).html("");
        boxLabelTable.find("td").eq(9).find("input").val("");
        boxLabelTable.find("td").eq(10).html("");
        $("#everyWeight").keyup(function () {
            var tmptxt = $(this).val();
            $(this).val(tmptxt.replace(/[^\d.]/g, ""));
            boxLabelTable.find("td").eq(8).html("");
            boxLabelTable.find("td").eq(10).html("");
            let everyWeight = parseFloat($(this).val());
            if (everyWeight > parseFloat(GetJqGridRowValue("#gridTable1", "inWeight"))) {
                tipDialog('单件重量不得大于入库总重量!', 4, 'warning');
                boxLabelTable.find("td").eq(9).find("input").val("");
                return;
            }
            let re = getData.inWeight % everyWeight;
            if (re > 0) {
                getData.num = (getData.inWeight - re) / everyWeight + 1;
            } else {
                getData.num = getData.inWeight / everyWeight;
            }
            getData.every = everyWeight;
            boxLabelTable.find("td").eq(8).html(getData.num)
            boxLabelTable.find("td").eq(10).html(re)
        }).bind("paste", function () {
            var tmptxt = $(this).val();
            $(this).val(tmptxt.replace(/[^\d.]/g, ""));
            boxLabelTable.find("td").eq(8).html("");
            boxLabelTable.find("td").eq(10).html("");
            let everyWeight = parseFloat($(this).val());
            if (everyWeight > parseFloat(GetJqGridRowValue("#gridTable1", "inWeight"))) {
                tipDialog('单件重量不得大于入库总重量!', 4, 'warning');
                boxLabelTable.find("td").eq(9).find("input").val("");
                return;
            }
            let re = getData.inWeight % everyWeight;
            if (re > 0) {
                getData.num = (getData.inWeight - re) / everyWeight + 1;
            } else {
                getData.num = getData.inWeight / everyWeight;
            }
            getData.every = everyWeight;
            boxLabelTable.find("td").eq(8).html(getData.num)
            boxLabelTable.find("td").eq(10).html(re)
        }).css("ime-mode", "disabled");
        $("#printBoxLabel").unbind("click");
        $("#printBoxLabel").click(function () {
            let value = $("#everyWeight").val();
            if (null == value || "" == value) {
                tipDialog('请输入单件重量!', 4, 'warning');
                return;
            }
            $("#printBoxLabelModal").modal("hide");
            openPostWindowAndSysLog("${request.contextPath}/stock/printBoxLabel/pdf", "enterStock", JSON.stringify(getData), resourceId);
        })
    }

    </@shiro.hasPermission>

    <@shiro.hasPermission name="/stockRecord/printIn.json">
    //打印入库单
    function printIn() {
        var enterStockDetailId = GetJqGridRowValue("#gridTable1", "enterStockDetailId");
        if (null == enterStockDetailId || '' == enterStockDetailId) {
            tipDialog('请选择入库记录!', 4, 'warning');
            return;
        }
        $.post("${request.contextPath}/stockRecord/getDetail.json", {enterStockDetailId: enterStockDetailId}, function (res) {
            let NO = res.obj.enterStock.enterNo;
            if (res.success) {
                if (NO.indexOf("NO.RK") != -1) {
                    openPostWindow("${request.contextPath}/jasper/receiveStoragePdf/pdf?resourceId=" + resourceId, "enterStock", JSON.stringify(res.obj));
                    $("#gridTable1").trigger("reloadGrid");
                } else {
                    tipDialog('请选择入库记录!', 4, 'warning');
                }
            }
        });
    }
    </@shiro.hasPermission>

    <@shiro.hasPermission name="/stockRecord/printReturn.json">
    //打印返库单
    function printReturn() {
        var enterStockDetailId = GetJqGridRowValue("#gridTable1", "enterStockDetailId");
        if (null == enterStockDetailId || '' == enterStockDetailId) {
            tipDialog('请选择返库记录!', 4, 'warning');
            return;
        }
        $.post("${request.contextPath}/stockRecord/getDetail.json", {enterStockDetailId: enterStockDetailId}, function (res) {
            let NO = res.obj.enterStock.enterNo;
            if (res.success) {
                if (NO.indexOf("NO.FK") != -1) {
                    openPostWindow("${request.contextPath}/jasper/receiveDepotPdf/pdf?resourceId=" + resourceId, "enterStock", JSON.stringify(res.obj));
                    $("#gridTable1").trigger("reloadGrid");
                } else {
                    tipDialog('请选择返库记录!', 4, 'warning');
                }
            }
        });
    }
    </@shiro.hasPermission>

    <@shiro.hasPermission name="/stockRecord/printOut.json">
    //打印出库单
    function printOut() {
        var leaveStockDetailId = GetJqGridRowValue("#gridTable2", "leaveStockDetailId");
        if (null == leaveStockDetailId || '' == leaveStockDetailId) {
            tipDialog('请选择出库记录!', 4, 'warning');
            return;
        }
        $.post("${request.contextPath}/stockRecord/getLeaveStockPrint.json", {leaveStockDetailId: leaveStockDetailId}, function (res) {
            if (res.success) {
                openPostWindow("${request.contextPath}/jasper/receiveOutGoingPdf/pdf?resourceId=" + resourceId, "leaveStock", JSON.stringify(res.obj));
                $("#gridTable2").trigger("reloadGrid");
            }
        });
    }
    </@shiro.hasPermission>
</script>