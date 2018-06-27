<style>
    .modal-title {
        text-align: center;
    }

    .ui-jqgrid tr.jqgrow td {
        white-space: normal !important;
        height: auto;
        vertical-align: text-top;
        padding-top: 2px;
        word-break: break-all;
    }
</style>
<div>
    <div class="layoutPanel layout-center"
         style="position: absolute; z-index: 99; height: 100%; width: 100%;overflow: auto;">
        <div class="tools_bar" style="border-top: none; margin-bottom: 0px;">
            <div class="PartialButton">
            <@shiro.hasPermission name="/receiveManagement/reload">
                <a id="lr-replace" title="刷新当前(Ctrl+Q)" onclick="Replace()" class="tools_btn">
                        <span>
                            <b class="btn-reload">刷新</b>
                        </span>
                </a>
                <div class="tools_separator"></div>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/receiveManagement/back">
                <a id="lr-leave" title="关闭当前窗口(Esc)" onclick="btn_back()" class="tools_btn">
                        <span>
                            <b class="btn-back">离开</b>
                        </span>
                </a>
            </@shiro.hasPermission>
            </div>
        </div>
        <div class="line"></div>
        <ul class="nav nav-tabs" id="mytabs">
        <@shiro.hasPermission name="/receive/received">
            <li class="active">
                <a href="#tab1" data-toggle="tab"> 已收货
                    <input type="hidden" value="1" name="receiveRecordTab">
                </a>
            </li>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/receive/returned">
            <li>
                <a href="#tab2" data-toggle="tab"> 已退货
                    <input type="hidden" value="2" name="receiveRecordTab">
                </a>
            </li>
        </@shiro.hasPermission>
        </ul>
        <div class="line"></div>
        <div class="tab-content">
        <@shiro.hasPermission name="/receive/received">
            <!--进行中-->
            <div class="tab-pane active" id="tab1">
                <div class="tools_bar" style="border-top: none; margin-bottom: 0px;">
                    <div class="PartialButton">
                        <@shiro.hasPermission name="/receiveManagement/addReceive.json">
                            <a id="lr-add" title="新建收货单" onclick="addReceive()" class="tools_btn">
                        <span>
                            <b class="btn-add">新建收货单</b>
                        </span>
                            </a>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="/receiveManagement/editReceive.json">
                            <a id="lr-edit" title="编辑" onclick="updateReceive()" class="tools_btn">
                        <span>
                            <b class="btn-update">编辑</b>
                        </span>
                            </a>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="/receiveManagement/checkReceive.json">
                            <a id="lr-detail" title="报送检验" onclick="checkReceive()" class="tools_btn">
                        <span>
                            <b class="btn-detail">报送检验</b>
                        </span>
                            </a>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="/receiveManagement/resourceDetail">
                            <a id="lr-detail" title="入库" onclick="addStockRecord()" class="tools_btn">
                        <span>
                            <b class="btn-detail">入库</b>
                        </span>
                            </a>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="/receiveManagement/returnGoods.json">
                            <a id="lr-detail" title="退货" onclick="returnGoods()" class="tools_btn">
                        <span>
                            <b class="btn-detail">退货</b>
                        </span>
                            </a>
                        </@shiro.hasPermission>
                    <#--打印箱标-->
                        <@shiro.hasPermission name="/receive/PrintCode.json">
                            <a id="lr-detail" title="打印箱码" onclick="printBoxLabel()" class="tools_btn">
                        <span>
                            <b class="btn-detail">打印箱码</b>
                        </span>
                            </a>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="/receiveManagement/exportData.json">
                            <a id="lr-detail" title="数据导出" onclick="exportData()" class="tools_btn">
                        <span>
                            <b class="btn-detail">数据导出</b>
                        </span>
                            </a>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="/jasper/downloadPdf/pdf">
                            <a id="lr-detail" title="打印收货单" onclick="pringReceivePdf()" class="tools_btn">
                        <span>
                            <b class="btn-detail">打印收货单</b>
                        </span>
                            </a>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="/receiveManagement/checkReport.json">
                            <a id="lr-detail" title="查看检验报告" onclick="checkReport()" class="tools_btn">
                        <span>
                            <b class="btn-detail">查看检验报告</b>
                        </span>
                            </a>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="/receiveManagement/deleteReceive.json">
                            <a id="lr-delete" title="删除" onclick="deleteReceive(1)" class="tools_btn">
                        <span>
                            <b class="btn-delete">删除</b>
                        </span>
                            </a>
                        </@shiro.hasPermission>
                    </div>
                </div>
                <div class="bottomline QueryArea" style="margin: 1px; margin-top: 0px; margin-bottom: 0px;">
                    <table border="0" class="form-find" style="height: 45px;">
                        <tbody>
                        <tr>
                            <td>
                                批次号：<input id="batchNo" name="batchNo" type="text" class="txt" style="width: 100px">
                            </td>
                            <td>
                                货物类型：
                                <select id="goodsTypeId" name="goodsTypeId" class="txtselect" datacol="yes" err="分类"
                                        checkexpession="NotNull">
                                    <option value="">==请选择==</option>
                                </select>
                            </td>
                            <td>
                                产品名称：
                                <select id="productId" name="productId" class="txtselect" datacol="yes" err="分类"
                                        checkexpession="NotNull">
                                    <option value="">请先选择类型</option>
                                </select>
                            </td>
                            <td>
                                规格：
                                <select id="productSpecName" name="productSpecName" class="txtselect" datacol="yes"
                                        err="状态"
                                        checkexpession="NotNull">
                                    <option value="">请先选择产品</option>
                                </select>
                            </td>
                            <td>
                                供应商：
                                <select id="enterpriseId" name="enterpriseId" class="txtselect" datacol="yes" err="状态"
                                        checkexpession="NotNull">
                                    <option value="">==请选择==</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                检验结果：
                                <select id="testResult" name="testResult" class="txtselect" datacol="yes" err="状态"
                                        checkexpession="NotNull">
                                    <option value="">==请选择==</option>
                                    <option value="1">待检验</option>
                                    <option value="2">合格</option>
                                    <option value="3">不合格</option>
                                </select>
                            </td>
                            <td>
                                货物状态：
                                <select id="receiptStatus" name="receiptStatus" class="txtselect" datacol="yes" err="状态"
                                        checkexpession="NotNull">
                                    <option value="">==请选择==</option>
                                    <option value="1">待处理</option>
                                    <option value="2">已退货</option>
                                    <option value="3">已入库</option>
                                    <option value="4">未打印</option>
                                </select>
                            </td>
                            <td colspan="2">
                                收货时间：
                                <input id="beginTime" type="text" class="txt Wdate" style="width: 140px; "
                                       onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                                至
                                <input id="endTime" type="text" class="txt Wdate" style="width: 140px; "
                                       onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                            </td>
                            <td>
                                <input id="btnSearch" type="button" class="btnSearch" value="查 询">
                                <input id="btnClear" type="button" class="btnSearch" value="重 置">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <table id="gridTable" tabindex="0" cellspacing="0" cellpadding="0" border="0" role="grid"
                       aria-multiselectable="false" aria-labelledby="gbox_gridTable" class="ui-jqgrid-btable"
                       style="width: 1341px;">
                </table>
                <div id="gridPager" class="ui-state-default ui-jqgrid-pager ui-corner-bottom" dir="ltr"
                     style="width: 1300px;height: 40px">
                </div>
            </div>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/receive/returned">
            <!--已完成-->
            <div class="tab-pane" id="tab2">
                <div class="tools_bar" style="border-top: none; margin-bottom: 0px;">
                    <div class="PartialButton">
                        <@shiro.hasPermission name="/jasper/receiveReturnPdf/pdf">
                            <a id="lr-detail" title="打印退货单" onclick="receiveReturnPdf()" class="tools_btn">
                                <span>
                                    <b class="btn-detail">打印退货单</b>
                                </span>
                            </a>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="/receiveManagement/checkReport.json">
                            <a id="lr-detail" title="查看检验报告" onclick="checkReport2()" class="tools_btn">
                                <span>
                                    <b class="btn-detail">查看检验报告</b>
                                </span>
                            </a>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="/receiveManagement/exportData.json">
                            <a id="lr-detail" title="数据导出" onclick="exportData()" class="tools_btn">
                                <span>
                                    <b class="btn-detail">数据导出</b>
                                </span>
                            </a>
                        </@shiro.hasPermission>

                        <@shiro.hasPermission name="/receiveManagement/deleteReceive.json">
                            <a id="lr-delete" title="删除" onclick="deleteReceive(2)" class="tools_btn">
                                <span>
                                    <b class="btn-delete">删除</b>
                                </span>
                            </a>
                        </@shiro.hasPermission>
                    </div>
                </div>

                <div class="bottomline QueryArea" style="margin: 1px; margin-top: 0px; margin-bottom: 0px;">
                    <table border="0" class="form-find" style="height: 45px;">
                        <tbody>
                        <tr>
                            <td>
                                批次号：<input id="batchNo2" name="batchNo2" type="text" class="txt" style="width: 100px">
                            </td>
                            <td>
                                货物类型：
                                <select id="goodsTypeId2" name="goodsTypeId2" class="txtselect" datacol="yes" err="分类"
                                        checkexpession="NotNull">
                                    <option value="">==请选择==</option>
                                </select>
                            </td>
                            <td>
                                产品名称：
                                <select id="productId2" name="productId2" class="txtselect" datacol="yes" err="分类"
                                        checkexpession="NotNull">
                                    <option value="">请先选择类型</option>
                                </select>
                            </td>
                            <td>
                                规格：
                                <select id="productSpecNameTwo" name="productSpecName2" class="txtselect" datacol="yes"
                                        err="状态"
                                        checkexpession="NotNull">
                                    <option value="">请先选择产品</option>
                                </select>
                            </td>
                            <td>
                                供应商：
                                <select id="enterpriseId2" name="enterpriseId2" class="txtselect" datacol="yes" err="状态"
                                        checkexpession="NotNull">
                                    <option value="">==请选择==</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                检验结果：
                                <select id="testResult2" name="testResult2" class="txtselect" datacol="yes" err="状态"
                                        checkexpession="NotNull">
                                    <option value="">==请选择==</option>
                                    <option value="1">待检验</option>
                                    <option value="2">合格</option>
                                    <option value="3">不合格</option>
                                </select>
                            </td>
                            <td>
                                货物状态：
                                <select id="receiptStatus2" name="receiptStatus2" class="txtselect" datacol="yes"
                                        err="状态"
                                        checkexpession="NotNull">
                                    <option value="">==请选择==</option>
                                    <option value="1">待处理</option>
                                    <option value="2">已退货</option>
                                    <option value="3">已入库</option>
                                    <option value="4">未打印</option>
                                </select>
                            </td>
                            <td colspan="2">
                                退货时间：
                                <input id="beginTime2" type="text" class="txt Wdate" style="width: 140px; "
                                       onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                                至
                                <input id="endTime2" type="text" class="txt Wdate" style="width: 140px; "
                                       onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                            </td>
                            <td>
                                <input id="btnSearch2" type="button" class="btnSearch" value="查 询">
                                <input id="btnClear2" type="button" class="btnSearch" value="重 置">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <table id="gridTable2" tabindex="0" cellspacing="0" cellpadding="0" border="0" role="grid"
                       aria-multiselectable="false" aria-labelledby="gbox_gridTable" class="ui-jqgrid-btable"
                       style="width: 1341px;">
                </table>
                <div id="gridPager2" class="ui-state-default ui-jqgrid-pager ui-corner-bottom" dir="ltr"
                     style="width: 1300px;height: 40px">
                </div>
            </div>
        </@shiro.hasPermission>
        </div>
    </div>
</div>
<@shiro.hasPermission name="/receiveManagement/addReceive.json">
<!--添加资源-->
<div id="addReceiveModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">新建收货单</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="addReceive" type="button" class="btn green">确认并打印</button>
    </div>
</div>
</@shiro.hasPermission>
<!--编辑-->
<@shiro.hasPermission name="/receiveManagement/editReceive.json">
<div id="updateReceiveModal" class="modal fade " data-width="1200" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">编辑</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="updateReceive" type="button" class="btn green">确定</button>
    </div>
</div>
</@shiro.hasPermission>
<!--报送检验确认-->
<@shiro.hasPermission name="/receiveManagement/checkReceive.json">
<div id="checkReceiveModal" class="modal fade " data-width="400" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">报送检验确认</h4>
    </div>
    <div class="modal-body">
        <p style="text-align: center">该几项是否要执行报送检验？</p>
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="checkReceive" type="button" class="btn green">确定</button>
    </div>
</div>
</@shiro.hasPermission>
<#--删除确认-->
<@shiro.hasPermission name="/receiveManagement/deleteReceive.json">
<div id="deleteReceiveModal" class="modal fade " data-width="400" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header" style="text-align: center">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">删除确认</h4>
    </div>
    <div class="modal-body">
        <p style="text-align: center">是否要执行删除该几项？</p>
        <p style="text-align: center">删除后数据不可恢复</p>
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="deleteReceive" type="button" class="btn green">确定</button>
    </div>
</div>
</@shiro.hasPermission>
<!--退货-->
<@shiro.hasPermission name="/receiveManagement/returnGoods.json">
<div id="returnGoodsModal" class="modal fade " data-width="900" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title" style="text-align: center">退货</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="returnGoods" type="button" class="btn green">确认并打印</button>
    </div>
</div>
</@shiro.hasPermission>
<!--数据导出-->
<@shiro.hasPermission name="/receiveManagement/exportData.json">
<div id="exportDataModal" class="modal fade " data-width="460" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">数据导出</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="export" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>
<!--检验报告-->
<@shiro.hasPermission name="/receiveManagement/checkReport.json">
<div id="checkReportModal" class="modal fade " data-width="1200" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">检验报告</h4>
    </div>
    <div class="modal-body" >
        <img id="checkReportImg" src="" height="400" width="400"/>
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn  green">确认</button>
    </div>
</div>
</@shiro.hasPermission>
<!--打印收货单-->
<@shiro.hasPermission name="/jasper/downloadPdf/pdf">
<div id="printReceivePdf" class="modal fade " data-width="460" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">打印收货单</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">确定</button>
    </div>
</div>
</@shiro.hasPermission>
<!--新建入库-->
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
            <button type="button" class="btn green" onclick="buttonTwo()">下一步</button>
        </div>
        <div id="buttonTwo" class="hidden">
            <button type="button" class="btn dark" onclick="addStockCancelButton(1)">取消</button>
            <button type="button" class="btn green" onclick="addStockCancelButton(2)">保存</button>
            <button id="addStockAndRecord" type="button" onclick="addStockAndRecord()" class="btn green">确认并打印入库单
            </button>
        </div>
    </div>
</div>
<!--添加供应商-->
<@shiro.hasPermission name="/enterprise/addEnterprise">
<div id="addenterpriseModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">添加供应商</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="addZsEnterprise" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>
<#--打印箱标-->
<@shiro.hasPermission name="/receive/PrintCode.json">
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
        resourceId = top.$("#ModuleId").val();
        if (!$("#mytabs").find("li:eq(0)").hasClass("active")) {
            $("#mytabs").find("li:eq(0)").addClass("active");
            $("#tab2").addClass("active");
            initSearch();
        <@shiro.hasPermission name="/receive/returned">
            GetGrid2("${request.contextPath}/receiveManagement/getReceiptCarryOut.json", "#gridPager2", "#gridTable2");
            // gridPagerStyle("gridPager2_right");
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/receive/returned">
            loadProduct("#goodsTypeId2", "#productId2", "#productSpecNameTwo", "#enterpriseId2");
        </@shiro.hasPermission>
        } else {
            initSearch();
        <@shiro.hasPermission name="/receive/received">
            GetGrid("${request.contextPath}/receiveManagement/getReceiptProcessing.json", "#gridPager", "#gridTable");
            // gridPagerStyle("gridPager_right");
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/receive/received">
            loadProduct("#goodsTypeId", "#productId", "#productSpecName", "#enterpriseId");
        </@shiro.hasPermission>
            $("#mytabs").find("li:eq(1)").unbind("click");
            $("#mytabs").find("li:eq(1)").click(function () {
            <@shiro.hasPermission name="/receive/returned">
                GetGrid2("${request.contextPath}/receiveManagement/getReceiptCarryOut.json", "#gridPager2", "#gridTable2");
                // gridPagerStyle("gridPager2_right");
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/receive/returned">
                loadProduct("#goodsTypeId2", "#productId2", "#productSpecNameTwo", "#enterpriseId2");
            </@shiro.hasPermission>
            })
        }
    });

    function clearNoNum(obj) {
        obj.value = obj.value.replace(/[^\d.]/g, "");  //清除“数字”和“.”以外的字符
        obj.value = obj.value.replace(/\.{2,}/g, "."); //只保留第一个. 清除多余的
        obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
        obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3');//只能输入两个小数
        if (obj.value.indexOf(".") < 0 && obj.value != "") {//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额
            obj.value = parseFloat(obj.value);
        }
    }

    /**
     * 搜索栏初始化
     * */
    function initSearch() {
        $.post("${request.contextPath}/receiveManagement/getGoodsType.json", "", function (res) {
        <@shiro.hasPermission name="/receive/received">
            $("#goodsTypeId").find("option").remove();
            $("#goodsTypeId").append("<option value=''>==请选择==</option>")
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/receive/returned">
            $("#goodsTypeId2").find("option").remove();
            $("#goodsTypeId2").append("<option value=''>==请选择==</option>")
        </@shiro.hasPermission>
            if (null != res && 0 != res.size) {
                for (i in res) {
                <@shiro.hasPermission name="/receive/received">
                    $("#goodsTypeId").append("<option value='" + res[i].type + "'>" + res[i].typeName + "</option>")
                </@shiro.hasPermission>
                <@shiro.hasPermission name="/receive/returned">
                    $("#goodsTypeId2").append("<option value='" + res[i].type + "'>" + res[i].typeName + "</option>")
                </@shiro.hasPermission>
                }
            }
        }, "JSON");
    }

    /**
     * 货物类型，产品，规格名称
     * @param typeId 货物类型dom元素id
     * @param productId 产品dom元素id
     * @param specId 规格名称dom元素id
     * */
    function loadProduct(typeId, productId, specId, priseId) {
        var typeEl = $("" + typeId);
        var productEl = $("" + productId);
        var specEl = $("" + specId);
        var priseEl = $("" + priseId);
        typeEl.unbind("click");
        typeEl.click(function () {
            if (typeEl.val() == "") {
                productEl.find("option").remove();
                productEl.append("<option value=''>请先选择类型</option>");
            } else {
                productEl.find("option").remove();
                productEl.append("<option value=''>请先选择类型</option>");
                var type = typeEl.val();
                $.post("${request.contextPath}/receiveManagement/getProductByType.json", {type: type}, function (res) {
                    if (res.success) {
                        productEl.find("option").remove();
                        productEl.append("<option value=''>==请选择==</option>")
                        var obj = res.obj;
                        for (i in obj) {
                            productEl.append("<option value='" + obj[i].productTypeName + "'>" + obj[i].productTypeName + "</option>")
                        }
                    } else {
                        tipDialog(res.msg, 3, 'warning');
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
                $.post("${request.contextPath}/receiveManagement/getProductSpecName.json", {productTypeName: type}, function (res) {
                    if (res.success) {
                        specEl.find("option").remove();
                        specEl.append("<option value=''>==请选择==</option>")
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
                priseEl.append("<option value=''>==请选择==</option>")
                var obj = res.obj;
                for (i in obj) {
                    priseEl.append("<option value='" + obj[i].enterpriseId + "'>" + obj[i].cname + "</option>")
                }
            } else {
                tipDialog(res.msg, 2, 'warning');
            }
        }, "JSON");

    }
    <@shiro.hasPermission name="/receive/received">
    /**
     * 加载进行中表格
     * @param url 请求地址
     * @param id 分页控件id
     * @param table 表格id
     * */
    function GetGrid(url, id, table) {
        $("" + table).jqGrid({
            url: url + "",
            datatype: "json",
            height: $(window).height() - 280,
            autowidth: true,
            colModel: [
                {label: "", name: "receiveDetailId", index: "receiveDetailId", hidden: true},
                {label: "", name: "qrcode", index: "qrcode", hidden: true},
                {label: "", name: "receiveId", index: "receiveId", hidden: true},
                {
                    label: "收货编号", name: "puReceive", index: "puReceive", width: 150,
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue.receiveNo;
                    }
                },
                {label: "货物类型", name: "goodsType", index: "goodsType", width: 100},
                {label: "批次号", name: "batchNo", index: "batchNo", width: 100},
                {label: "产品名称", name: "productName", index: "productName", width: 100, align: "center"},
                {label: "规格", name: "productSpecName", index: "productSpecName", width: 100},
                {label: "数量", name: "weight", index: "weight", width: 60},
                {label: "单位", name: "unitName", index: "unitName", width: 60},
                {
                    label: "供应商", name: "puReceive", index: "puReceive", width: 180, align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue.enterpriseName;
                    }
                },
                {
                    label: "发货人", name: "puReceive", index: "puReceive", width: 80, align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue.deliverName;
                    }
                },
                {
                    label: '收货人', name: 'puReceive', index: 'puReceive', width: 80, align: 'center',
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue.consignee;
                    }
                },
                {
                    label: "收货时间", name: "puReceive", index: "puReceive", width: 150,
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue.deliverDate;
                    }
                },
                {
                    label: "检验结果", name: "testResult", index: "testResult", width: 100,
                    formatter: function (cellvalue, options, rowObject) {
                        if (cellvalue == 0) {
                            return "未报送";
                        } else if (cellvalue == 1) {
                            return "待检验";
                        } else if (cellvalue == 2) {
                            return "合格";
                        } else if (cellvalue == 3) {
                            return "不合格";
                        }
                    }
                },
                {
                    label: "货物状态", name: "receiptStatus", index: "receiptStatus", width: 62,
                    formatter: function (cellvalue, options, rowObject) {
                        if (cellvalue == 1) {
                            return "待处理";
                        } else if (cellvalue == 2) {
                            return "已退货";
                        } else if (cellvalue == 3) {
                            return "已入库";
                        } else if (cellvalue == 4) {
                            return "未打印";
                        }
                    }
                },
                {
                    label: "备注", name: "puReceive", index: "puReceive", width: 160,
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue.remark;
                    }
                },
                {
                    label: "", name: "puReceive", index: "puReceive", width: 160, hidden: true,
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue.deliverDate;
                    }
                }
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
    </@shiro.hasPermission>

    <@shiro.hasPermission name="/receive/returned">
    /**
     * 加载已完成表格
     * @param url 请求地址
     * @param id 分页控件id
     * @param table 表格id
     * */
    function GetGrid2(url, id, table) {
        $("" + table).jqGrid({
            url: url + "",
            datatype: "json",
            height: $(window).height() - 280,
            autowidth: true,
            colModel: [
                {label: "", name: "receiveDetailId", index: "receiveDetailId", hidden: true},
                {label: "", name: "originalId", index: "originalId", hidden: true},
                {label: "", name: "receiveId", index: "receiveId", hidden: true},
                {
                    label: "收货编号", name: "puReceive", index: "puReceive", width: 150,
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue.receiveNo;
                    }
                },
                {label: "单据编号", name: "returnNo", index: "returnNo", width: 145, align: "center"},
                {label: "货物类型", name: "goodsType", index: "goodsType", width: 55},
                {label: "批次号", name: "batchNo", index: "batchNo", width: 80},
                {label: "产品名称", name: "productName", index: "productName", width: 80, align: "center"},
                {label: "规格", name: "productSpecName", index: "productSpecName", width: 80},
                {label: "数量", name: "weight", index: "weight", width: 60},
                {label: "单位", name: "unitName", index: "unitName", width: 60},
                {
                    label: "供应商", name: "puReceive", index: "puReceive", width: 180, align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue.enterpriseName;
                    }
                },
                {
                    label: "发货人", name: "puReceive", index: "puReceive", width: 80, align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue.deliverName;
                    }
                },
                {
                    label: '收货人', name: 'puReceive', index: 'puReceive', width: 80, align: 'center',
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue.consignee;
                    }
                },
                {
                    label: "收货时间", name: "puReceive", index: "puReceive", width: 75,
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue.deliverDate;
                    }
                },
                {
                    label: "检验结果", name: "testResult", index: "testResult", width: 55,
                    formatter: function (cellvalue, options, rowObject) {
                        if (cellvalue == 1) {
                            return "待检验";
                        } else if (cellvalue == 2) {
                            return "合格";
                        } else if (cellvalue == 3) {
                            return "不合格";
                        }
                    }
                },
                {
                    label: "货物状态", name: "receiptStatus", index: "receiptStatus", width: 55,
                    formatter: function (cellvalue, options, rowObject) {
                        if (cellvalue == 1) {
                            return "待处理";
                        } else if (cellvalue == 2) {
                            return "已退货";
                        } else if (cellvalue == 3) {
                            return "已入库";
                        } else if (cellvalue == 4) {
                            return "未打印";
                        }
                    }
                },
                {label: "退货原因", name: "returnReason", index: "returnReason", width: 100},
                {label: "处理方案", name: "disposePlan", index: "disposePlan", width: 100},
                {label: "经手人", name: "handler", index: "handler", width: 60},
                {label: "退货时间", name: "returnDate", index: "returnDate", width: 75},
                {label: "备注1", name: "remark", index: "remark", width: 150},
                {label: "备注2", name: "note", index: "note", width: 150}
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
    </@shiro.hasPermission>

    //分页布局
    function gridPagerStyle(id) {
        var width = document.getElementById("" + id).offsetWidth + 5;
        $("#gridPager_right").attr("style", "width:" + width);
    }

    /**
     * 进行中
     * */
    $("#btnSearch").unbind("click");
    $("#btnSearch").click(function () {
        var batchNo = $.trim($("#batchNo").val());
        var goodsTypeId = $("#goodsTypeId").val();
        var productName = $("#productId").val();
        var productSpecName = $("#productSpecName").val();
        var enterpriseId = $("#enterpriseId").val();
        var testResult = $("#testResult").val();
        var receiptStatus = $("#receiptStatus").val();
        var beginTime = $("#beginTime").val();
        var endTime = $("#endTime").val();
        var postData = {
            batchNo: batchNo,
            goodsTypeId: goodsTypeId,
            productName: productName,
            productSpecName: productSpecName,
            enterpriseId: enterpriseId,
            testResult: testResult,
            receiptStatus: receiptStatus,
            beginTime: beginTime,
            endTime: endTime
        };
        //提交post并刷新表格
        $("#gridTable").jqGrid("setGridParam", {
            postData: postData,
            url: "${request.contextPath}/receiveManagement/getReceiptProcessing.json",
            page: 1
        }).trigger("reloadGrid");
    });

    $("#btnClear").unbind("click");
    $("#btnClear").click(function () {
        //重置表格
        $("#batchNo").val("");
        $("#goodsTypeId").val("");
        $("#productId").val("");
        $("#productSpecName").val("");
        $("#enterpriseId").val("");
        $("#testResult").val("");
        $("#receiptStatus").val("");
        $("#beginTime").val("");
        $("#endTime").val("");
        var postData = {
            batchNo: "",
            goodsTypeId: "",
            productName: "",
            productSpecName: "",
            enterpriseId: "",
            testResult: "",
            receiptStatus: "",
            beginTime: "",
            endTime: ""
        };
        //提交post并刷新表格
        $("#gridTable").jqGrid("setGridParam", {
            postData: postData,
            url: "${request.contextPath}/receiveManagement/getReceiptProcessing.json",
            page: 1
        }).trigger("reloadGrid");
    });

    /**
     * 已完成
     * */
    $("#btnSearch2").unbind("click");
    $("#btnSearch2").click(function () {
        var batchNo = $.trim($("#batchNo2").val());
        var goodsTypeId = $("#goodsTypeId2").val();
        var productName = $("#productId2").val();
        var productSpecName = $("#productSpecNameTwo").val();
        var enterpriseId = $("#enterpriseId2").val();
        var testResult = $("#testResult2").val();
        var receiptStatus = $("#receiptStatus2").val();
        var beginTime = $("#beginTime2").val();
        var endTime = $("#endTime2").val();
        var postData = {
            batchNo: batchNo,
            goodsTypeId: goodsTypeId,
            productName: productName,
            productSpecName: productSpecName,
            enterpriseId: enterpriseId,
            testResult: testResult,
            receiptStatus: receiptStatus,
            beginTime: beginTime,
            endTime: endTime
        };
        //提交post并刷新表格
        $("#gridTable2").jqGrid("setGridParam", {
            postData: postData,
            url: "${request.contextPath}/receiveManagement/getReceiptCarryOut.json",
            page: 1,
        }).trigger("reloadGrid");
    });

    $("#btnClear2").unbind("click");
    $("#btnClear2").click(function () {
        //重置表格
        $("#batchNo2").val("");
        $("#goodsTypeId2").val("");
        $("#productId2").val("");
        $("#productSpecNameTwo").val("");
        $("#enterpriseId2").val("");
        $("#testResult2").val("");
        $("#receiptStatus2").val("");
        $("#beginTime2").val("");
        $("#endTime2").val("");
        //提交post并刷新表格
        var postData = {
            batchNo: "",
            goodsTypeId: "",
            productName: "",
            productSpecName: "",
            enterpriseId: "",
            testResult: "",
            receiptStatus: "",
            beginTime: "",
            endTime: ""
        };
        $("#gridTable2").jqGrid("setGridParam", {
            postData: postData,
            url: "${request.contextPath}/receiveManagement/getReceiptCarryOut.json",
            page: 1
        }).trigger("reloadGrid");
    });
    //新建收货单
    <@shiro.hasPermission name="/receiveManagement/addReceive.json">
    function addReceive() {
        $("#addReceiveModal").modal({
            remote: "${request.contextPath}/receiveManagement/addReceiveModal.htm"
        });
    }
    </@shiro.hasPermission>

    //编辑
    <@shiro.hasPermission name="/receiveManagement/editReceive.json">
    function updateReceive() {
        var receiveDetailIds = GetJqGridRowValue("#gridTable", "receiveDetailId");
//        var len = $(".ui-state-highlight").length;
        if (null == receiveDetailIds) {
            tipDialog('请选择记录!', 4, 'warning');
            return;
        }
        if (receiveDetailIds.split(",").length > 1) {
            tipDialog('不能多选!', 4, 'warning');
            return;
        } else {
            $("#updateReceiveModal").modal({
                remote: "${request.contextPath}/receiveManagement/updateReceiveModal.htm?receiveDetailIds=" + receiveDetailIds + "&resourceId=" + resourceId
            });
        }
    }
    </@shiro.hasPermission>

    //确认修改
    <@shiro.hasPermission name="/receiveManagement/editReceive.json">
    $("#updateReceive").unbind("click");
    $("#updateReceive").click(function () {
        Loading(true, "正在修改", "#updateReceiveModal");
        var rowData = new Array();
        var length = $("#updateUserForm tbody>tr").length;
        for (var i = 0; i < length; i++) {
            var receive = {
                receiveDetailId: "",
                receiveId: "",
                goodsTypeId: "",
                goodsType: "",
                batchNo: "",//批次号
                productId: "",
                productName: "",
                productSpecName: "",
                unitId: "",
                unitName: "",
                weight: 0,
                enterpriseId: "",
                enterpriseName: "",
                deliverName: "",//发货人
                consignee: "",//收货人
                deliverDate: "",
                remark: "",
                resourceId: "",//资源id
                receiveNo: "",//收货单id
            };
            receive.receiveDetailId = $("#updateUserForm tbody>tr").eq(i).find("input:eq(0)").val();
            receive.receiveId = $("#updateUserForm tbody>tr").eq(i).find("input:eq(1)").val();
            receive.goodsTypeId = $("#updateUserForm tbody>tr").eq(i).find("select:eq(0)").find("option:selected").val();
            receive.goodsType = $("#updateUserForm tbody>tr").eq(i).find("select:eq(0)").find("option:selected").text();
            receive.batchNo = $("#updateUserForm tbody>tr").eq(i).find("input:eq(2)").val();
            receive.productId = $("#updateUserForm tbody>tr").eq(i).find("select:eq(1)").find("option:selected").val();
            receive.productName = $("#updateUserForm tbody>tr").eq(i).find("select:eq(1)").find("option:selected").text();
            receive.productSpecName = $("#updateUserForm tbody>tr").eq(i).find("select:eq(2)").find("option:selected").val();
            receive.unitId = $("#updateUserForm tbody>tr").eq(i).find("select:eq(3)").find("option:selected").val();
            receive.unitName = $("#updateUserForm tbody>tr").eq(i).find("select:eq(3)").find("option:selected").text();
            receive.weight = $("#updateUserForm tbody>tr").eq(i).find("input:eq(3)").val();
            receive.enterpriseId = $("#updateUserForm tbody>tr").eq(i).find("select:eq(4)").find("option:selected").val();
            receive.enterpriseName = $("#updateUserForm tbody>tr").eq(i).find("select:eq(4)").find("option:selected").text();
            receive.deliverName = $("#updateUserForm tbody>tr").eq(i).find("input:eq(4)").val();
            receive.consignee = $("#updateUserForm tbody>tr").eq(i).find("input:eq(5)").val();
            receive.deliverDate = $("#updateUserForm tbody>tr").eq(i).find("input:eq(6)").val();
            receive.remark = $("#updateUserForm tbody>tr").eq(i).find("input:eq(7)").val();
            receive.resourceId = $("#updateUserForm tbody>tr").eq(i).find("input:eq(8)").val();
            receive.receiveNo = $("#updateUserForm tbody>tr").eq(i).find("input:eq(9)").val();
            rowData.push(receive);
        }
        for (var i = 0; i < rowData.length; i++) {
            var rece = rowData[i], num = i + 1;
            if (rece.goodsTypeId == '') {
                tipDialog("货物类型为空！", 3, 'warning');
                Loading(false, "", "#updateReceiveModal");
                return;
            }
            //验证批次号
            //var reg = /^[0-9]*$/;
            if ("" == rece.batchNo) {
                tipDialog("批次号为空！", 3, 'warning');
                Loading(false, "", "#updateReceiveModal");
                return;
            }
            if (rece.productId == '') {
                tipDialog("产品名称为空！", 3, 'warning');
                Loading(false, "", "#updateReceiveModal");
                return;
            }
            if (rece.productSpecName == '') {
                tipDialog("规格为空！", 3, 'warning');
                Loading(false, "", "#updateReceiveModal");
                return;
            }
            var reg1 = /^\+?[1-9][0-9]*$/;
            let weight = parseInt(rece.weight);
            if (!reg1.test(weight) || weight == "") {
                tipDialog("数量为空或格式有误！", 3, 'warning');
                Loading(false, "", "#updateReceiveModal");
                return;
            }
            if (rece.unitId == '') {
                tipDialog("单位为空！", 3, 'warning');
                Loading(false, "", "#updateReceiveModal");
                return;
            }
            if (rece.enterpriseId == '') {
                tipDialog("供应商为空！", 3, 'warning');
                Loading(false, "", "#updateReceiveModal");
                return;
            }
            //验证发货人
            if (rece.deliverName == "") {
                tipDialog("发货人为空！", 3, 'warning');
                Loading(false, "", "#updateReceiveModal");
                return;
            }
            //验证收货人
            if (rece.consignee == "") {
                tipDialog("收货人为空！", 3, 'warning');
                Loading(false, "", "#updateReceiveModal");
                return;
            }
            if (rece.deliverDate == '') {
                tipDialog("收货时间为空！", 3, 'warning');
                Loading(false, "", "#updateReceiveModal");
                return;
            }
        }
        $.ajax({
            type: "post", // 请求方式
            url: "${request.contextPath}/receiveManagement/updateReceive.json", //url地址
            data: JSON.stringify(rowData), //数据
            contentType: "application/json;charset=utf-8;",
            dataType: "json",
            success: function (res) {
                if (res.success) {
                    $("#gridTable").trigger("reloadGrid");
                    tipDialog(res.msg, 3, 2);
                    $("#updateReceiveModal").modal('hide');
                    Loading(false, "", "#updateReceiveModal");
                } else {
                    tipDialog(res.msg, 0, 2);
                    $("#updateReceiveModal").modal('hide');
                    Loading(false, "", "#updateReceiveModal");
                }
            },
            error: function () {
                tipDialog("网络异常", 0, 2);
                $('#updateReceiveModal').modal('hide');
                Loading(false, "", "#updateReceiveModal");
            }
        });
    });
    </@shiro.hasPermission>

    //报送检验
    <@shiro.hasPermission name="/receiveManagement/checkReceive.json">
    function checkReceive() {
        var receiveDetailIds = GetJqGridRowValue("#gridTable", "receiveDetailId");
        var receiveIds = GetJqGridRowValue("#gridTable", "receiveId");
        var receiptStatus = GetJqGridRowValue("#gridTable", "receiptStatus");
        if (null != receiptStatus) {
            let arr = receiptStatus.split(",");
            for (var i = 0; i < arr.length; i++) {
                if ("未打印" == arr[i]) {
                    tipDialog("未打印无法报送", 4, "warning");
                    return;
                }
            }
        }
        if (null == receiveDetailIds && null == receiveIds) {
            tipDialog("请选择您要报送的记录", 4, "warning");
        } else {
            $("#checkReceiveModal").modal("show");
        }
    }
    </@shiro.hasPermission>

    //报送检验确认
    <@shiro.hasPermission name="/receiveManagement/checkReceive.json">
    $("#checkReceive").unbind("click");
    $("#checkReceive").click(function () {
        Loading(true, "正在提交", "#checkReceiveModal");
        var receiveDetailIds = GetJqGridRowValue("#gridTable", "receiveDetailId");
        var receiveIds = GetJqGridRowValue("#gridTable", "receiveId");
        let arrReceiveDetailIds = receiveDetailIds.split(",");
        let arrReceiveIds = receiveIds.split(",");
        var data = new Array();
        for (var i = 0; i < arrReceiveDetailIds.length; i++) {
            var a = {receiveDetailId: arrReceiveDetailIds[i], receiveId: arrReceiveIds[i], resourceId: resourceId};
            data.push(a);
        }
        $.ajax({
            type: "post", // 请求方式
            url: "${request.contextPath}/receiveManagement/checkReceive.json",
            data: JSON.stringify(data),
            contentType: "application/json;charset=utf-8;",
            dataType: "json",
            success: function (res) {
                if (res.success) {
                    $("#gridTable").trigger("reloadGrid");
                    $("#checkReceiveModal").modal("hide");
                    tipDialog(res.msg, 4, 2);
                } else {
                    tipDialog(res.msg, 4, "warning");
                }
                Loading(false, "", "#checkReceiveModal");
            }
        })
    });

    </@shiro.hasPermission>
    //删除
    <@shiro.hasPermission name="/receiveManagement/deleteReceive.json">
    /**
     * @param type 1为进行中，2为已完成
     * */
    function deleteReceive(type) {
        var receiveDetailIds, receiveIds;
        if (type == 1) {
            receiveDetailIds = GetJqGridRowValue("#gridTable", "receiveDetailId");
            receiveIds = GetJqGridRowValue("#gridTable", "receiveId");
        } else {
            receiveDetailIds = GetJqGridRowValue("#gridTable2", "receiveDetailId");
            receiveIds = GetJqGridRowValue("#gridTable2", "receiveId");
        }
        if (null == receiveDetailIds && null == receiveIds) {
            tipDialog("请选择您要删除的记录", 4, "warning");
        } else {
            $("#deleteReceiveModal").modal("show");
            $("#deleteReceive").unbind("click");
            $("#deleteReceive").click(function () {
                let arrReceiveDetailIds = receiveDetailIds.split(",");
                let arrReceiveIds = receiveIds.split(",");
                var data = new Array();
                for (var i = 0; i < arrReceiveDetailIds.length; i++) {
                    var a = {
                        receiveDetailId: arrReceiveDetailIds[i],
                        receiveId: arrReceiveIds[i],
                        resourceId: resourceId
                    };
                    data.push(a);
                }
                $.ajax({
                    type: "post", // 请求方式
                    url: "${request.contextPath}/receiveManagement/deleteReceive.json", //url地址
                    data: JSON.stringify(data), //数据
                    contentType: "application/json;charset=utf-8;",
                    dataType: "json",
                    success: function (res) {
                        if (res.success) {
                            $("#gridTable").trigger("reloadGrid");
                            var postData = {
                                batchNo: "",
                                goodsTypeId: "",
                                productName: "",
                                productSpecName: "",
                                enterpriseId: "",
                                testResult: "",
                                receiptStatus: "",
                                beginTime: "",
                                endTime: ""
                            };
                            $("#gridTable2").jqGrid("setGridParam", {
                                postData: postData,
                                url: "${request.contextPath}/receiveManagement/getReceiptCarryOut.json",
                                page: 1
                            }).trigger("reloadGrid");
                            tipDialog(res.msg, 3, 2);
                            $("#deleteReceiveModal").modal("hide");
                        } else {
                            tipDialog(res.msg, 4, "warning");
                            $("#deleteReceiveModal").modal("hide");
                        }
                    }
                })
            })
        }
    }
    </@shiro.hasPermission>

    //退货
    <@shiro.hasPermission name="/receiveManagement/returnGoods.json">
    function returnGoods() {
        var receiveDetailIds = GetJqGridRowValue("#gridTable", "receiveDetailId");
        var testResults = GetJqGridRowValue("#gridTable", "testResult");
        if (null == receiveDetailIds) {
            tipDialog('请选择记录!', 4, 'warning');
            return;
        }
        var arrtestResults = testResults.split(",");
        for (var i = 0; i < arrtestResults.length; i++) {
            if (arrtestResults[i] == '未报送' || arrtestResults[i] == '待检验') {
                tipDialog('检验后才可退货!', 4, 'warning');
                return;
            }
        }
        if (null == receiveDetailIds) {
            tipDialog('请选择您要退货的记录!', 4, 'warning');
        } else {
            $("#returnGoodsModal").modal({
                remote: "${request.contextPath}/receiveManagement/returnGoodsModal.htm?receiveDetailIds=" + receiveDetailIds + "&resourceId=" + resourceId
            });
        }
    }
    </@shiro.hasPermission>

    //确认退货
    <@shiro.hasPermission name="/receiveManagement/returnGoods.json">
    $("#returnGoods").unbind("click");
    $("#returnGoods").click(function () {
        Loading(true, "正在退货", "#returnGoodsModal");
        var rowData = new Array();
        var length = $("#returnTable1 tbody>tr").length;
        for (var i = 0; i < length; i++) {
            var receive = {
                receiveDetailId: "",
                receiveId: "",
                //备注1
                remark: "",
                //退货数量
                count: 0,
                //退货原因
                returnReason: "",
                returnNo: "",
                disposePlan: "",
                handler: "",
                returnTime: "",
                //备注2
                note: "",
                weight: 0,
                resourceId: "",
            };
            receive.receiveDetailId = $("#returnTable1 tbody>tr").eq(i).find("input:eq(0)").val();
            receive.receiveId = $("#returnTable1 tbody>tr").eq(i).find("input:eq(1)").val();
            receive.count = $("#returnTable1 tbody>tr").eq(i).find("input:eq(2)").val();
            receive.remark = $("#returnTable1 tbody>tr").eq(i).find("input:eq(3)").val();
            receive.returnReason = $("#returnTable2").find("textarea:eq(0)").val();
            receive.disposePlan = $("#returnTable2").find("textarea:eq(1)").val();
            receive.note = $("#returnTable2").find("textarea:eq(2)").val();
            receive.handler = $("#returnTable2").find("input:eq(0)").val();
            receive.returnTime = $("#returnTable2").find("input:eq(1)").val();
            receive.weight = $("#returnTable1 tbody>tr").eq(i).find("td:eq(5)").text();
            receive.returnNo = $("#returnTable2 tr").eq(0).find("td:eq(1)").text();
            receive.resourceId = $("#returnTable2").find("input:eq(2)").val();
            rowData.push(receive);
        }
        for (var i = 0; i < rowData.length; i++) {
            var rece = rowData[i], num = i + 1;
            if (rece.count == '') {
                tipDialog("第" + num + "行退货数量为空！", 3, 'warning');
                Loading(false, "", "#returnGoodsModal");
                return;
            }
            let count = rece.count, weight = rece.weight;
            count = parseInt(count);
            weight = parseInt(weight);
            if (count > weight) {
                tipDialog("第" + num + "行退货数量不能大于已有数量！", 3, 'warning');
                Loading(false, "", "#returnGoodsModal");
                return;
            }
            let reg = /^[1-9]\d*$/;
            if (!reg.test(rece.count)) {
                tipDialog("第" + num + "行退货数量格式不正确！", 3, 'warning');
                Loading(false, "", "#returnGoodsModal");
                return;
            }
            if (rece.returnReason == '') {
                tipDialog("退货原因为空！", 3, 'warning');
                Loading(false, "", "#returnGoodsModal");
                return;
            }
            if (rece.disposePlan == '') {
                tipDialog("处理方案为空！", 3, 'warning');
                Loading(false, "", "#returnGoodsModal");
                return;
            }
            if (rece.handler == '') {
                tipDialog("经手人为空！", 3, 'warning');
                Loading(false, "", "#returnGoodsModal");
                return;
            }
            if (rece.returnTime == '') {
                tipDialog("退货时间为空！", 3, 'warning');
                Loading(false, "", "#returnGoodsModal");
                return;
            }
        }

        $.ajax({
            type: "post", // 请求方式
            url: "${request.contextPath}/receiveManagement/returnGoods.json", //url地址
            data: JSON.stringify(rowData), //数据
            contentType: "application/json;charset=utf-8;",
            dataType: "json",
            success: function (res) {
                if (res.success) {
                    window.open("${request.contextPath}/jasper/receiveReturnPdf/pdf?receiveDetails=" + res.obj.toString() + "&resourceId=" + resourceId);
                    var postData = {
                        batchNo: "",
                        goodsTypeId: "",
                        productName: "",
                        productSpecName: "",
                        enterpriseId: "",
                        testResult: "",
                        receiptStatus: "",
                        beginTime: "",
                        endTime: ""
                    };
                    $("#gridTable").trigger("reloadGrid");
                    $("#gridTable2").jqGrid("setGridParam", {
                        postData: postData,
                        url: "${request.contextPath}/receiveManagement/getReceiptCarryOut.json",
                        page: 1
                    }).trigger("reloadGrid");
                    tipDialog(res.msg, 3, 2);
                    $("#returnGoodsModal").modal('hide');
                    Loading(false, "", "#returnGoodsModal");
                } else {
                    tipDialog(res.msg, 4, "warning");
                    $("#returnGoodsModal").modal('hide');
                    Loading(false, "", "#returnGoodsModal");
                }
            },
            error: function () {
                tipDialog("网络异常", 0, 2);
                $('#returnGoodsModal').modal('hide');
                Loading(false, "", "#returnGoodsModal");
            }
        });
    });
    </@shiro.hasPermission>

    //查看检验报告
    <@shiro.hasPermission name="/receiveManagement/checkReport.json">
    function checkReport() {
        var receiveDetailId = GetJqGridRowValue("#gridTable", "receiveDetailId");
        var testResult = GetJqGridRowValue("#gridTable", "testResult");
        if (null == receiveDetailId) {
            tipDialog('请选择记录!', 4, 'warning');
        }
        let arrayr = receiveDetailId.split(",");
        if (arrayr.length > 1) {
            tipDialog('只能查看单条检验报告!', 4, 'warning');
            return;
        }
        if (testResult == '未报送' || testResult == '待检验') {
            tipDialog('检验后才可查看报告!', 4, 'warning');
            return;
        }
        $.post("${request.contextPath}/receiveManagement/checkReportModal.json", {receiveDetailId: receiveDetailId}, function (res) {
            let str = res.obj.toString();
            if (res.success) {
                $("#checkReportImg").attr("src", str);
                $("#checkReportModal").modal("show");
            } else {
                tipDialog(res.msg, 3, 'warning');
            }
        }, "JSON");
    }
    </@shiro.hasPermission>

    //已完成  查看检验报告
    <@shiro.hasPermission name="/receiveManagement/checkReport.json">
    function checkReport2() {
        var receiveDetailId = GetJqGridRowValue("#gridTable2", "originalId");
        if (null == receiveDetailId || "" == receiveDetailId) {
            tipDialog('请选择记录!', 4, 'warning');
            return;
        }
        if (receiveDetailId.split(",").length > 1) {
            tipDialog('不能多选!', 4, 'warning');
            return;
        }
        $.post("${request.contextPath}/receiveManagement/checkReportModal.json", {receiveDetailId: receiveDetailId}, function (res) {
            let str = res.obj.toString();
            if (res.success) {
                $("#checkReportImg").attr("src", str);
                $("#checkReportModal").modal("show");
            } else {
                tipDialog(res.msg, 3, 'warning');
            }
        }, "JSON");
    }
    </@shiro.hasPermission>

    //数据导出--进行中
    <@shiro.hasPermission name="/receiveManagement/exportData.json">
    function exportData() {
        $("#exportDataModal").modal({
            remote: "${request.contextPath}/receiveManagement/exportDataModal.htm"
        });
    }

    //确认导出
    $("#export").unbind("click");
    $("#export").click(function () {
        Loading(true, "", "#exportDataModal");
        var begin = $("#exportBeginTime").val();
        if ("" == begin) {
            $("#exportBeginTime").focus();
            tipDialog("请选择开始时间");
            Loading(false, "", "#exportDataModal");
            return false;
        }
        var end = $("#exportEndTime").val();
        if ("" == end) {
            $("#exportEndTime").focus();
            tipDialog("请选择截止时间");
            Loading(false, "", "#exportDataModal");
            return false;
        }
        if (new Date(end) - new Date(begin) <= 0) {
            tipDialog("截止时间应大于开始时间");
            $("#exportBeginTime").val("");
            $("#exportEndTime").val("");
            Loading(false, "", "#exportDataModal");
            return false;
        }
        let arr = new Array();
        $('input[name="receiveRecordTab"]').each(function (index, item) {
                    arr.push($(this).val());
                }
        );
        var data = $("#exportDataForm").serialize();
        window.open("${request.contextPath}/receiveManagement/exportData.json?" + data + "&resourceId=" + resourceId + "&arr=" + arr, "_blank");
        Loading(false, "", "#exportDataModal");
        $("#exportDataModal").modal('hide');
    });
    </@shiro.hasPermission>

    <!--打印收货单-->
    <@shiro.hasPermission name="/jasper/downloadPdf/pdf">
    function pringReceivePdf() {
        var receiveIds = GetJqGridRowValue("#gridTable", "receiveId");
        var receiveDetailIds = GetJqGridRowValue("#gridTable", "receiveDetailId");
        var receiveNos = GetJqGridRowValue("#gridTable", "receiveNo");
        if (null == receiveIds) {
            tipDialog('请选择记录!', 4, 'warning');
        }
        let arrtestResults = receiveIds.split(",");
        let receiveIdChild = arrtestResults[0];
        for (var i = 0; i < arrtestResults.length; i++) {
            if (arrtestResults[i] != receiveIdChild) {
                tipDialog('只能选择同一收货单下的数据!', 4, 'warning');
                return;
            }
        }
        if (arrtestResults.length > 10) {
            tipDialog('一次最多只能选择十条数据!', 4, 'warning');
            return;
        }
        window.open("${request.contextPath}/jasper/downloadPdf/pdf?receiveDetails=" + receiveDetailIds + "&resourceId=" + resourceId);
        setTimeout(function () {
            //重置表格
            $("#batchNo").val("");
            $("#goodsTypeId").val("");
            $("#productId").val("");
            $("#productSpecName").val("");
            $("#enterpriseId").val("");
            $("#testResult").val("");
            $("#receiptStatus").val("");
            $("#beginTime").val("");
            $("#endTime").val("");
            var postData = {
                batchNo: "",
                goodsTypeId: "",
                productName: "",
                productSpecName: "",
                enterpriseId: "",
                testResult: "",
                receiptStatus: "",
                beginTime: "",
                endTime: ""
            };
            //提交post并刷新表格
            $("#gridTable").jqGrid("setGridParam", {
                postData: postData,
                url: "${request.contextPath}/receiveManagement/getReceiptProcessing.json",
                page: 1
            }).trigger("reloadGrid");
        }, 6 * 1000);

    }
    </@shiro.hasPermission>

    <@shiro.hasPermission name="/jasper/receiveReturnPdf/pdf">
    function receiveReturnPdf() {
        var receiveDetailIds = GetJqGridRowValue("#gridTable2", "receiveDetailId");
        var returnNos = GetJqGridRowValue("#gridTable2", "returnNo");
        if (null == receiveDetailIds) {
            tipDialog('请选择记录!', 4, 'warning');
            return;
        }
        let returnNoArr = returnNos.split(",");
        let returnNo = returnNoArr[0];
        for (var i = 0; i < returnNoArr.length; i++) {
            if (returnNoArr[i] != returnNo) {
                tipDialog('只能选择同一退货单下的数据!', 4, 'warning');
                return;
            }
        }
        if (returnNoArr.length > 8) {
            tipDialog('一次最多只能选择八条数据!', 4, 'warning');
            return;
        }
        window.open("${request.contextPath}/jasper/receiveReturnPdf/pdf?receiveDetails=" + receiveDetailIds + "&resourceId=" + resourceId);
    }
    </@shiro.hasPermission>

    <@shiro.hasPermission name="/stock/addEnterStockAndStock">
    //新建入库
    function addStockRecord() {
        $("#addStockAndRecordModal").modal({
            remote: "${request.contextPath}/stock/addStockAndRecordModal.htm"
        });
    }

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
            field.remark = $("#grid_Field2 tbody>tr").eq(i).find("input[name=stockRemark]").val();
            enterStockDetailList.push(field2);//入库详情表
            stockList.push(field);//库存表
        }
        $.ajax({
            type: "POST",
            url: "${request.contextPath}/stock/addEnterStockAndStock.json",
            data: JSON.stringify(allData),
            contentType: "application/json;charset=utf-8;",
            dataType: "json",
            async: false,
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
            field.remark = $("#grid_Field2 tbody>tr").eq(i).find("input[name=stockRemark]").val();
            enterStockDetailList.push(field2);//入库详情表
            stockList.push(field);//库存表
        }
        $.ajax({
            type: "POST",
            url: "${request.contextPath}/stock/addEnterStockAndStock.json",
            data: JSON.stringify(allData),
            contentType: "application/json;charset=utf-8;",
            dataType: "json",
            async: false,
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

    //打印箱标
    <@shiro.hasPermission name="/receive/PrintCode.json">
    function printBoxLabel() {
        let batchNo = GetJqGridRowValue("#gridTable", "batchNo");
        if (null == batchNo) {
            tipDialog('请选择记录!', 4, 'warning');
            return;
        }
        if (batchNo.split(",").length > 1) {
            tipDialog('最多只能选择单个收货信息!', 4, 'warning');
            return;
        }
        let getData = {
            batchNo: batchNo,
            materialName: GetJqGridRowValue("#gridTable", "goodsType"),
            productName: GetJqGridRowValue("#gridTable", "productName"),
            productStatusName: "--",
            productSpecName: GetJqGridRowValue("#gridTable", "productSpecName"),
            inWeight: GetJqGridRowValue("#gridTable", "weight"),
            unitName: GetJqGridRowValue("#gridTable", "unitName"),
            createDate: GetJqGridRowValue("#gridTable", "puReceive"),
            qrcode: GetJqGridRowValue("#gridTable", "qrcode"),
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
            if (everyWeight > parseFloat(GetJqGridRowValue("#gridTable", "weight"))) {
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
            if (everyWeight > parseFloat(GetJqGridRowValue("#gridTable", "weight"))) {
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
</script>