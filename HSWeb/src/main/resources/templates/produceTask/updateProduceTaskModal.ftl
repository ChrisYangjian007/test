
<form id="updateProduceTaskForm" style="margin: 1px">
    <div class="bd">
        <div class="ScrollBar" style="margin-top: 1px;">
            <div id="TableProperty">
                <table class="form">
                    <tr>
                        <th class="formTitle">生产任务：
                        </th>
                        <td class="formValue">
                            <input type="hidden" id="produceTaskId" name="produceTaskId" value="${zsProduceTask.produceTaskId}">
                            <input type="text" class="txt " id="produceTaskName" name="produceTaskName" value="${zsProduceTask.produceTaskName}" readonly/>
                        </td>
                        <th class="formTitle">生产任务编号：
                        </th>
                        <td class="formValue">
                            <input type="text" class="txt " id="produceTaskNo" name="produceTaskNo" value="${zsProduceTask.produceTaskNo}" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <th class="formTitle">出库编号：
                        </th>
                        <td class="formValue">
                            <input type="text" class="txt " id="leaveNo" name="leaveNo" value="" readonly/>
                        </td>
                        <th class="formTitle">出库申请人：
                        </th>
                        <td class="formValue">
                            <input type="text" class="txt " id="leavePerson" name="leavePerson" placeholder="请输入" value=""/>
                        </td>
                    </tr>
                    <tr>
                        <th class="formTitle">经手人：
                        </th>
                        <td class="formValue">
                            <input type="text" class="txt " id="brokerage" name="brokerage" placeholder="请输入" value="" />
                        </td>
                        <th class="formTitle">出库时间：
                        </th>
                        <td class="formValue">
                            <input id="leaveDate" name="leaveDate" type="text" class="txt Wdate" style="width: 100%; "
                                   onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                        </td>
                    </tr>
                    <tr>
                        <th class="formTitle">生产任务标签：
                        </th>
                        <td class="formValue" colspan="3">
                            <input type="text" class="txt " id="brokerage" name="brokerage" value="" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <th class="formTitle">备注1：
                        </th>
                        <td class="formValue" colspan="3">
                            <input type="text" class="txt " id="brokerage" name="remark1" value="" readonly/>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div id="isWorkshopRecordDiv">
            <div class="tipstitle_title settingtable bold bd todayInfoPanelTab rightPanelTitle_normal">
                <div class="tab_list_top" style="position: absolute">
                    <div id="TabTableField1" class="tab_list bd actived">出库详情</div>
                </div>
                <div style="float: right;">
                    <div class="tools_bar_icon">
                        <div class="icon-botton" title="上移" onclick="Gridup()">
                            <img src="${staticImg}/images/Icon16/resultset_top.png" />
                        </div>
                        <div class="icon-botton" title="下移" onclick="Griddown()">
                            <img src="${staticImg}/images/Icon16/resultset_bottom.png" />
                        </div>
                        <div class="tools_separator" style="height: 23px; margin-right: 5px;"></div>
                        <div class="icon-botton" title="插入行" onclick="InsetTableRow()">
                            <img src="${staticImg}/images/Icon16/table_row_insert.png" />
                        </div>
                        <div class="icon-botton" title="清空行" onclick="EmptyTableRow()">
                            <img src="${staticImg}/images/Icon16/table_row_delete.png" />
                        </div>
                    </div>
                </div>
            </div>
            <div id="TableFieldDiv" class="ScrollBar" style="margin-top: 1px;">
                <div id="TableField1" class="tabPanel">
                    <table id="grid_Field1" class="grid" style="width: 100%">
                        <thead>
                        <tr>
                            <td style="width: 30px; text-align: center; border-left: none;">
                                <div class="table-header">&nbsp;</div>
                            </td>
                            <td style="width: 60px;">
                                <div class="table-header">仓库</div>
                            </td>
                            <td style="width: 80px; text-align: center;">
                                <div class="table-header">批次号</div>
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
                            <td style="width: 100px; text-align: center;">
                                <div class="table-header">库存数量</div>
                            </td>
                            <td style="width: 100px; text-align: center;">
                                <div class="table-header">出库数量</div>
                            </td>
                            <td style="width: 100px; text-align: center;">
                                <div class="table-header">是否耗损</div>
                            </td>
                            <td style="width: 100px; text-align: center;">
                                <div class="table-header">单位</div>
                            </td>
                            <td style="width: 100px; text-align: center;">
                                <div class="table-header">备注2</div>
                            </td>
                        </tr>
                        </thead>
                        <tbody>
                        <#--<#if 1==workProcess.isWorkshopRecord>-->
                                <#--<#if workProcess.tableField1??>-->

                        <#--</#if>-->
                            <#--</#if>-->
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</form>

<script>



    //插入一个空行
    function InsetTableRow() {
        if (1==TabTable){
            var trobj = $("#grid_Field1 tbody .selected");
            if (trobj.length) {
                trobj.before(CreateTableRow(RowIndex1,"one"));
                var selectedindex = (trobj.find('td:eq(0)').html()) - 1; $("#grid_Field1 tbody tr:eq(" + selectedindex + ")").find('input,select').hide();
                //更新表格序列号
                var indexrow = 1;
                $("#grid_Field1 tbody tr").each(function () { $(this).find('td:eq(0)').html(indexrow); indexrow++; });
            }else {
                var len=$("#grid_Field1 tbody tr").length;
                $("#grid_Field1 tbody").append(CreateTableRow(len+1,"one"));
                $("#grid_Field1 tbody tr:eq(" + len + ")").find('input,select').hide();
            }
            RowIndex1++;
        }else if (2==TabTable){
            var trobj = $("#grid_Field2 tbody .selected");
            if (trobj.length) {
                trobj.before(CreateTableRow(RowIndex2,"two"));
                var selectedindex = (trobj.find('td:eq(0)').html()) - 1;
                $("#grid_Field2 tbody tr:eq(" + selectedindex + ")").find('input,select').hide();
                //更新表格序列号
                var indexrow = 1;
                $("#grid_Field2 tbody tr").each(function () { $(this).find('td:eq(0)').html(indexrow); indexrow++; });
            }else {
                var len=$("#grid_Field2 tbody tr").length;
                $("#grid_Field2 tbody").append(CreateTableRow(len+1,"two"));
                $("#grid_Field2 tbody tr:eq(" + len + ")").find('input,select').hide();
            }
            RowIndex2++;
        }else if (3==TabTable){
            var trobj = $("#grid_Field3 tbody .selected");
            if (trobj.length) {
                trobj.before(CreateTableRow(RowIndex3,"there"));
                var selectedindex = (trobj.find('td:eq(0)').html()) - 1;
                $("#grid_Field3 tbody tr:eq(" + selectedindex + ")").find('input,select').hide();
                //更新表格序列号
                var indexrow = 1;
                $("#grid_Field3 tbody tr").each(function () { $(this).find('td:eq(0)').html(indexrow); indexrow++; });
            }else {
                var len = $("#grid_Field3 tbody tr").length;
                $("#grid_Field3 tbody").append(CreateTableRow(len+1,"there"));
                $("#grid_Field3 tbody tr:eq(" + len + ")").find('input,select').hide();
            }
            RowIndex3++;
        }
    }

    //清空表格当前行
    function EmptyTableRow() {
        if (1==TabTable) {
            var trobj = $("#grid_Field1 tbody .selected");
            if (trobj != null && trobj != undefined && trobj.length > 0) {
                trobj.remove();
                var indexrow = 1;
                $("#grid_Field1 tbody tr").each(function () {
                    $(this).find('td:eq(0)').html(indexrow);
                    indexrow++;
                });
            } else
                tipCss($(this), "请先选择一行！\n");
        }else if (2==TabTable){
            var trobj = $("#grid_Field2 tbody .selected");
            if (trobj != null && trobj != undefined && trobj.length > 0) {
                trobj.remove();
                var indexrow = 1;
                $("#grid_Field2 tbody tr").each(function () {
                    $(this).find('td:eq(0)').html(indexrow);
                    indexrow++;
                });
            } else
                tipCss($(this), "请先选择一行！\n");
        }else if (3==TabTable){
            var trobj = $("#grid_Field3 tbody .selected");
            if (trobj != null && trobj != undefined && trobj.length > 0) {
                trobj.remove();
                var indexrow = 1;
                $("#grid_Field3 tbody tr").each(function () {
                    $(this).find('td:eq(0)').html(indexrow);
                    indexrow++;
                });
            } else
                tipCss($(this), "请先选择一行！\n");
        }
    }

    //表格tr上移
    function Gridup() {
        if (1==TabTable) {
            var objParentTR = $("#grid_Field1 tbody .selected");
            var prevTR = objParentTR.prev();
            if (prevTR.length > 0) {
                prevTR.insertAfter(objParentTR);
                //更新表格序列号
                var indexrow = 1;
                $("#grid_Field1 tbody tr").each(function () {
                    $(this).find('td:eq(0)').html(indexrow); indexrow++;
                });
            } else {
                tipCss($(this), "请先选择一行农资或者当前已到第一行！\n");
                return;
            }
        }else if (2==TabTable){
            var objParentTR = $("#grid_Field2 tbody .selected");
            var prevTR = objParentTR.prev();
            if (prevTR.length > 0) {
                prevTR.insertAfter(objParentTR);
                //更新表格序列号
                var indexrow = 1;
                $("#grid_Field2 tbody tr").each(function () {
                    $(this).find('td:eq(0)').html(indexrow); indexrow++;
                });
            } else {
                tipCss($(this), "请先选择一行农资或者当前已到第一行！\n");
                return;
            }
        }else if (3==TabTable){
            var objParentTR = $("#grid_Field3 tbody .selected");
            var prevTR = objParentTR.prev();
            if (prevTR.length > 0) {
                prevTR.insertAfter(objParentTR);
                //更新表格序列号
                var indexrow = 1;
                $("#grid_Field3 tbody tr").each(function () {
                    $(this).find('td:eq(0)').html(indexrow); indexrow++;
                });
            } else {
                tipCss($(this), "请先选择一行农资或者当前已到第一行！\n");
                return;
            }
        }
    }

    //表格tr下移
    function Griddown(obj) {
        if (1==TabTable) {
            var objParentTR = $("#grid_Field1 tbody .selected");
            var nextTR = objParentTR.next();
            if (nextTR.length > 0) {
                nextTR.insertBefore(objParentTR);
                //更新表格序列号
                var indexrow = 1;
                $("#grid_Field1 tbody tr").each(function () {
                    $(this).find('td:eq(0)').html(indexrow);
                    indexrow++;
                });
            } else {
                tipCss($(this), "请先选择一行农资或者当前已到最后一行！\n");
                return;
            }
        }else if (2==TabTable){
            var objParentTR = $("#grid_Field2 tbody .selected");
            var nextTR = objParentTR.next();
            if (nextTR.length > 0) {
                nextTR.insertBefore(objParentTR);
                //更新表格序列号
                var indexrow = 1;
                $("#grid_Field2 tbody tr").each(function () {
                    $(this).find('td:eq(0)').html(indexrow);
                    indexrow++;
                });
            } else {
                tipCss($(this), "请先选择一行农资或者当前已到最后一行！\n");
                return;
            }
        }else if (3==TabTable){
            var objParentTR = $("#grid_Field3 tbody .selected");
            var nextTR = objParentTR.next();
            if (nextTR.length > 0) {
                nextTR.insertBefore(objParentTR);
                //更新表格序列号
                var indexrow = 1;
                $("#grid_Field3 tbody tr").each(function () {
                    $(this).find('td:eq(0)').html(indexrow);
                    indexrow++;
                });
            } else {
                tipCss($(this), "请先选择一行农资或者当前已到最后一行！\n");
                return;
            }
        }
    }
</script>