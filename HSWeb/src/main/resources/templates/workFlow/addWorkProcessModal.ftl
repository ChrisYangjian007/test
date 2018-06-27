
<#if workFlow??>
<form id="addWorkProcessForm" autocomplete="off"  style="margin: 1px">
    <div class="bd">
        <div class="ScrollBar" style="margin-top: 1px;">
            <div id="TableProperty">
                <table class="form">
                    <tr>
                        <th class="formTitle">所属工艺：
                        </th>
                        <td class="formValue" colspan="3">
                            <input type="hidden" name="workFlowId" value="${workFlow.workFlowId}">
                            <input type="text" class="txt " maxlength="10" value="${workFlow.CName}" disabled/>
                        </td>
                    </tr>
                    <tr>
                        <th class="formTitle">工艺名称：
                        </th>
                        <td class="formValue" colspan="3">
                            <input id="workProcessCName" name="cName" type="text" class="txt required"/>
                            <input id="nameId" name="nameId" type="hidden"/>
                        </td>
                    </tr>
                    <tr>
                        <th class="formTitle">工序字段：
                        </th>
                        <td class="formValue" colspan="3">
                            <select id="isWorkshopRecord" class="txtselect" name="isWorkshopRecord">
                                <option value="0">无</option>
                                <option value="1">有</option>
                            </select>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div id="isWorkshopRecordDiv" class="hidden">
            <div class="tipstitle_title settingtable bold bd todayInfoPanelTab rightPanelTitle_normal">
                <div class="tab_list_top" style="position: absolute">
                    <div id="TabTableField1" class="tab_list bd actived">操作记录</div>
                    <div id="TabTableField2" class="tab_list bd ">审核记录</div>
                    <div id="TabTableField3" class="tab_list bd ">巡检记录</div>
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
                                <div class="table-header">名称</div>
                            </td>
                            <td style="width: 80px; text-align: center;">
                                <div class="table-header">类型</div>
                            </td>
                            <td style="width: 80px; text-align: center;">
                                <div class="table-header">限制条件</div>
                            </td>
                            <td style="width: 100px; text-align: center;">
                                <div class="table-header">数据源类型</div>
                            </td>
                            <td style="width: 100px; text-align: center;">
                                <div class="table-header">数据源</div>
                            </td>
                        </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                </div>
                <div id="TableField2" class="tabPanel hidden">
                    <table id="grid_Field2" class="grid" style="width: 100%">
                        <thead>
                        <tr>
                            <td style="width: 30px; text-align: center; border-left: none;">
                                <div class="table-header">&nbsp;</div>
                            </td>
                            <td style="width: 60px;">
                                <div class="table-header">名称</div>
                            </td>
                            <td style="width: 80px; text-align: center;">
                                <div class="table-header">类型</div>
                            </td>
                            <td style="width: 80px; text-align: center;">
                                <div class="table-header">限制条件</div>
                            </td>
                            <td style="width: 100px; text-align: center;">
                                <div class="table-header">数据源类型</div>
                            </td>
                            <td style="width: 100px; text-align: center;">
                                <div class="table-header">数据源</div>
                            </td>
                        </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                </div>
                <div id="TableField3" class="tabPanel hidden">
                    <table id="grid_Field3" class="grid" style="width: 100%">
                        <thead>
                        <tr>
                            <td style="width: 30px; text-align: center; border-left: none;">
                                <div class="table-header">&nbsp;</div>
                            </td>
                            <td style="width: 60px;">
                                <div class="table-header">名称</div>
                            </td>
                            <td style="width: 80px; text-align: center;">
                                <div class="table-header">类型</div>
                            </td>
                            <td style="width: 80px; text-align: center;">
                                <div class="table-header">限制条件</div>
                            </td>
                            <td style="width: 100px; text-align: center;">
                                <div class="table-header">数据源类型</div>
                            </td>
                            <td style="width: 100px; text-align: center;">
                                <div class="table-header">数据源</div>
                            </td>
                        </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</form >

<div id="workProcessNameModal" class="modal fade " data-width="780" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">工艺名称</h4>
    </div>
    <div class="modal-body" id="workProcessNameModalBody">
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="addWorkProcessName" type="button" class="btn green" onclick="addWorkProcessName()">确定</button>
    </div>
</div>

<#else >
<table class="form">
    <tr id="locationTr">
        <th class="formTitle">错误：
        </th>
        <td class="formValue" colspan="3">
            <input type="text" class="txt required" value="请重试，再不行请联系管理员" disabled />
        </td>
    </tr>
</table>
</#if>

<script>

    $("#addWorkProcessModal").on("focus","#workProcessCName",function(){
        $("#workProcessNameModal").modal({
            remote: "${request.contextPath}/workProcess/workProcessNameModal.htm"
        });
    });
    function addWorkProcessName() {
        var workProcessName = $("#workProcessNameModalBody").find(".clickBg").text();
        var nameId = $("#workProcessNameModalBody").find(".clickBg").find("input").attr("id");
        $("#workProcessCName").val(workProcessName);
        $("#nameId").val(nameId);
        $("#workProcessNameModal").modal("hide");
    }

    var TabTable = 1;

    $("#addWorkProcessModal").on("click","#TabTableField1",function(){
        TabTable=1;
        $("#TableField2").addClass("hidden");
        $("#TableField3").addClass("hidden");
        $("#TableField1").removeClass("hidden");
        $("#TabTableField2").removeClass("actived");
        $("#TabTableField3").removeClass("actived");
        $("#TabTableField1").addClass("actived");
    });
    $("#addWorkProcessModal").on("click","#TabTableField2",function(){
        TabTable=2;
        $("#TableField1").addClass("hidden");
        $("#TableField3").addClass("hidden");
        $("#TableField2").removeClass("hidden");
        $("#TabTableField1").removeClass("actived");
        $("#TabTableField3").removeClass("actived");
        $("#TabTableField2").addClass("actived");
    });
    $("#addWorkProcessModal").on("click","#TabTableField3",function(){
        TabTable=3;
        $("#TableField1").addClass("hidden");
        $("#TableField2").addClass("hidden");
        $("#TableField3").removeClass("hidden");
        $("#TabTableField1").removeClass("actived");
        $("#TabTableField2").removeClass("actived");
        $("#TabTableField3").addClass("actived");
    });

    $("#addWorkProcessModal").on("change","#isWorkshopRecord",function(){
        TabTable=1;
        var select = $("#isWorkshopRecord").val();
        if (0==select){
            $("#isWorkshopRecordDiv").addClass("hidden");
        }else if (1==select){
            $("#isWorkshopRecordDiv").removeClass("hidden");
            $("#TableField2").addClass("hidden");
            $("#TableField3").addClass("hidden");
            $("#TableField1").removeClass("hidden");
            $("#TabTableField2").removeClass("actived");
            $("#TabTableField3").removeClass("actived");
            $("#TabTableField1").addClass("actived");
        }
        if (closeAddWorkProcess){
            CreateTable();
            TableTdEvent();
            closeAddWorkProcess = false;
        }
    });


    //默认创建一行
    function CreateTable() {
        $("#grid_Field1 tbody").append(CreateTableRow1(RowIndex1,"one"));
        RowIndex1+=2;
        $("#grid_Field1 tbody tr").find('input,select').attr('disabled', 'disabled').hide();
        $("#grid_Field1 tbody tr").find('input,select').removeAttr('disabled');
        $("#grid_Field1 tbody tr").find('input[name=dataSource]').attr('disabled',"disabled");
        $("#grid_Field1 tbody tr").find('.dataSourceTypeSelect').attr('disabled',"disabled");

        $("#grid_Field2 tbody").append(CreateTableRow2(RowIndex2,"two"));
        RowIndex2++;
        $("#grid_Field2 tbody tr").find('input,select').attr('disabled', 'disabled').hide();
        $("#grid_Field2 tbody tr").find('input,select').removeAttr('disabled');
        $("#grid_Field2 tbody tr").find('input[name=dataSource]').attr('disabled',"disabled");
        $("#grid_Field2 tbody tr").find('.dataSourceTypeSelect').attr('disabled',"disabled");

        $("#grid_Field3 tbody").append(CreateTableRow3(RowIndex3,"three"));
        RowIndex3+=4;
        $("#grid_Field3 tbody tr").find('input,select').attr('disabled', 'disabled').hide();
        $("#grid_Field3 tbody tr").find('input,select').removeAttr('disabled');
        $("#grid_Field3 tbody tr").find('input[name=dataSource]').attr('disabled',"disabled");
        $("#grid_Field3 tbody tr").find('.dataSourceTypeSelect').attr('disabled',"disabled");

        $(".not-delete").find("input,select").attr("disabled","disabled");
    }

    function CreateTableRow1(index,type) {
        var tr = '';
        //操作人
        tr += '<tr class="not-delete">';
        tr += '<td class="td-div" style="width: 30px; text-align: center;border-left: none;">' + index + '</td>';
        tr += '<td style="width: 50px; text-align: center;"><div>操作人</div>' +
                '<input autocomplete="off" id="propertyName-' + index + "-"+type+'" type="text" maxlength="10"  class="txt" name ="propertyName" value="操作人" />' +
                '</td>';
        tr += '<td class="click-show" style="width: 60px; text-align: center;"><div>文本框</div>' +
                '<select id="controlType-' + index + "-" + type + '" class="txtselect" name ="controlType" datacol="no" type="select" err="类型" checkexpession="NotNull">' +
                '<option value="1" selected>文本框</option>' +
                '<option value="2">下拉框</option>' +
                '<option value="3">日期框</option>' +
                '<option value="5">多行文本</option>' +
                '</select>' +
                '</td>';
        tr += '<td style="width: 60px; text-align: center;"><div>默认当前用户</div>' +
                '<select id="restrictiveConditions-' + index + "-" + type + '" class="txtselect" name ="restrictiveConditions"  datacol="no" type="select" err="限制条件" checkexpession="NotNull">'+
                '<option value="1">不能为空</option>'+
                '<option value="2">只能输入数字</option>'+
                '<option value="3">数字、字母</option>'+
                '<option value="4">无限制</option>'+
                '<option value="5" selected>默认当前用户</option>'+
                '</select>'+
                '</td>';
        tr += '<td style="width: 60px; text-align: center;"><div>固定</div>' +
                '<select id="dataSourceType-' + index + "-" + type + '" class="txtselect dataSourceTypeSelect" name ="dataSourceType"  datacol="no" type="select" err="数据源类型" checkexpession="NotNull">' +
                '<option value="0" selected>固定</option>' +
                '<option value="1">数据字典</option>' +
                '<option value="2">PDA用户</option>' +
                '</select>'+
                '</td>';
        tr += '<td style="width: 50px; text-align: center;"><div></div>' +
                '<input id="dataSourceCode-' + index + "-" + type + '" class="dataSourceCode" type="hidden" name="dataSourceCode" autocomplete="off" />'+
                '<input id="dataSource-' + index + "-" + type + '" type="text" class="txt dataSource" name="dataSource" disabled autocomplete="off" />'+
                '</td>';
        tr += '</tr>';
        index++;
        //审核人
        tr += '<tr class="not-delete">';
        tr += '<td class="td-div" style="width: 30px; text-align: center;border-left: none;">' + index + '</td>';
        tr += '<td style="width: 50px; text-align: center;"><div>审核人</div>' +
                '<input autocomplete="off" id="propertyName-' + index + "-"+type+'" type="text" maxlength="10"  class="txt" name ="propertyName" value="审核人" />' +
                '</td>';
        tr += '<td class="click-show" style="width: 60px; text-align: center;"><div>下拉框</div>' +
                '<select id="controlType-' + index + "-" + type + '" class="txtselect" name ="controlType" datacol="no" type="select" err="类型" checkexpession="NotNull">' +
                '<option value="1">文本框</option>' +
                '<option value="2" selected>下拉框</option>' +
                '<option value="3">日期框</option>' +
                '<option value="5">多行文本</option>' +
                '</select>' +
                '</td>';
        tr += '<td style="width: 60px; text-align: center;"><div>不能为空</div>' +
                '<select id="restrictiveConditions-' + index + "-" + type + '" class="txtselect" name ="restrictiveConditions"  datacol="no" type="select" err="限制条件" checkexpession="NotNull">'+
                '<option value="1" selected>不能为空</option>'+
                '<option value="2">只能输入数字</option>'+
                '<option value="3">数字、字母</option>'+
                '<option value="4">无限制</option>'+
                '<option value="5">默认当前用户</option>'+
                '</select>'+
                '</td>';
        tr += '<td style="width: 60px; text-align: center;"><div>PDA用户</div>' +
                '<select id="dataSourceType-' + index + "-" + type + '" class="txtselect dataSourceTypeSelect" name ="dataSourceType"  datacol="no" type="select" err="数据源类型" checkexpession="NotNull">' +
                '<option value="0">固定</option>' +
                '<option value="1">数据字典</option>' +
                '<option value="2" selected>PDA用户</option>' +
                '</select>'+
                '</td>';
        tr += '<td style="width: 50px; text-align: center;"><div></div>' +
                '<input id="dataSourceCode-' + index + "-" + type + '" class="dataSourceCode" type="hidden" name="dataSourceCode" autocomplete="off"  />'+
                '<input id="dataSource-' + index + "-" + type + '" type="text" class="txt dataSource" name="dataSource" disabled autocomplete="off"  />'+
                '</td>';
        tr += '</tr>';
        return tr;
    }
    function CreateTableRow2(index,type) {
        var tr = '';
        //审核人
        tr += '<tr class="not-delete">';
        tr += '<td class="td-div" style="width: 30px; text-align: center;border-left: none;">' + index + '</td>';
        tr += '<td style="width: 50px; text-align: center;"><div>审核人</div>' +
                '<input autocomplete="off" id="propertyName-' + index + "-"+type+'" type="text" maxlength="10"  class="txt" name ="propertyName" value="审核人" />' +
                '</td>';
        tr += '<td class="click-show" style="width: 60px; text-align: center;"><div>文本框</div>' +
                '<select id="controlType-' + index + "-" + type + '" class="txtselect" name ="controlType" datacol="no" type="select" err="类型" checkexpession="NotNull">' +
                '<option value="1" selected>文本框</option>' +
                '<option value="2">下拉框</option>' +
                '<option value="3">日期框</option>' +
                '<option value="5">多行文本</option>' +
                '</select>' +
                '</td>';
        tr += '<td style="width: 60px; text-align: center;"><div>默认当前用户</div>' +
                '<select id="restrictiveConditions-' + index + "-" + type + '" class="txtselect" name ="restrictiveConditions"  datacol="no" type="select" err="限制条件" checkexpession="NotNull">'+
                '<option value="1">不能为空</option>'+
                '<option value="2">只能输入数字</option>'+
                '<option value="3">数字、字母</option>'+
                '<option value="4">无限制</option>'+
                '<option value="5" selected>默认当前用户</option>'+
                '</select>'+
                '</td>';
        tr += '<td style="width: 60px; text-align: center;"><div>固定</div>' +
                '<select id="dataSourceType-' + index + "-" + type + '" class="txtselect dataSourceTypeSelect" name ="dataSourceType"  datacol="no" type="select" err="数据源类型" checkexpession="NotNull">' +
                '<option value="0" selected>固定</option>' +
                '<option value="1">数据字典</option>' +
                '<option value="2">PDA用户</option>' +
                '</select>'+
                '</td>';
        tr += '<td style="width: 50px; text-align: center;"><div></div>' +
                '<input id="dataSourceCode-' + index + "-" + type + '" class="dataSourceCode" type="hidden" name="dataSourceCode" autocomplete="off"  />'+
                '<input id="dataSource-' + index + "-" + type + '" type="text" class="txt dataSource" name="dataSource" disabled autocomplete="off"  />'+
                '</td>';
        tr += '</tr>';
        return tr;
    }
    function CreateTableRow3(index,type) {
        var tr = '';
        //巡检结果
        tr += '<tr class="not-delete">';
        tr += '<td class="td-div" style="width: 30px; text-align: center;border-left: none;">' + index + '</td>';
        tr += '<td style="width: 50px; text-align: center;"><div>巡检结果</div>' +
                '<input autocomplete="off" id="propertyName-' + index + "-"+type+'" type="text" maxlength="10"  class="txt" name ="propertyName" value="巡检结果" />' +
                '</td>';
        tr += '<td class="click-show" style="width: 60px; text-align: center;"><div>下拉框</div>' +
                '<select id="controlType-' + index + "-" + type + '" class="txtselect" name ="controlType" datacol="no" type="select" err="类型" checkexpession="NotNull">' +
                '<option value="1">文本框</option>' +
                '<option value="2" selected>下拉框</option>' +
                '<option value="3">日期框</option>' +
                '<option value="5">多行文本</option>' +
                '</select>' +
                '</td>';
        tr += '<td style="width: 60px; text-align: center;"><div>不能为空</div>' +
                '<select id="restrictiveConditions-' + index + "-" + type + '" class="txtselect" name ="restrictiveConditions"  datacol="no" type="select" err="限制条件" checkexpession="NotNull">'+
                '<option value="1" selected>不能为空</option>'+
                '<option value="2">只能输入数字</option>'+
                '<option value="3">数字、字母</option>'+
                '<option value="4">无限制</option>'+
                '<option value="5">默认当前用户</option>'+
                '</select>'+
                '</td>';
        tr += '<td style="width: 60px; text-align: center;"><div>数据字典</div>' +
                '<select id="dataSourceType-' + index + "-" + type + '" class="txtselect dataSourceTypeSelect" name ="dataSourceType"  datacol="no" type="select" err="数据源类型" checkexpession="NotNull">' +
                '<option value="0">固定</option>' +
                '<option value="1" selected>数据字典</option>' +
                '<option value="2">PDA用户</option>' +
                '</select>'+
                '</td>';
        tr += '<td style="width: 50px; text-align: center;"><div>检验结果</div>' +
                '<input id="dataSourceCode-' + index + "-" + type + '" class="dataSourceCode" type="hidden" name="dataSourceCode" value="0102040000"/>'+
                '<input id="dataSource-' + index + "-" + type + '" type="text" class="txt dataSource" name="dataSource" disabled value="检验结果"/>'+
                '</td>';
        tr += '</tr>';
        index++;
        //巡检人
        tr += '<tr class="not-delete">';
        tr += '<td class="td-div" style="width: 30px; text-align: center;border-left: none;">' + index + '</td>';
        tr += '<td style="width: 50px; text-align: center;"><div>巡检人</div>' +
                '<input autocomplete="off" id="propertyName-' + index + "-"+type+'" type="text" maxlength="10"  class="txt" name ="propertyName" value="巡检人" />' +
                '</td>';
        tr += '<td class="click-show" style="width: 60px; text-align: center;"><div>文本框</div>' +
                '<select id="controlType-' + index + "-" + type + '" class="txtselect" name ="controlType" datacol="no" type="select" err="类型" checkexpession="NotNull">' +
                '<option value="1" selected>文本框</option>' +
                '<option value="2" >下拉框</option>' +
                '<option value="3">日期框</option>' +
                '<option value="5">多行文本</option>' +
                '</select>' +
                '</td>';
        tr += '<td style="width: 60px; text-align: center;"><div>默认当前用户</div>' +
                '<select id="restrictiveConditions-' + index + "-" + type + '" class="txtselect" name ="restrictiveConditions"  datacol="no" type="select" err="限制条件" checkexpession="NotNull">'+
                '<option value="1">不能为空</option>'+
                '<option value="2">只能输入数字</option>'+
                '<option value="3">数字、字母</option>'+
                '<option value="4">无限制</option>'+
                '<option value="5" selected>默认当前用户</option>'+
                '</select>'+
                '</td>';
        tr += '<td style="width: 60px; text-align: center;"><div>固定</div>' +
                '<select id="dataSourceType-' + index + "-" + type + '" class="txtselect dataSourceTypeSelect" name ="dataSourceType"  datacol="no" type="select" err="数据源类型" checkexpession="NotNull">' +
                '<option value="0" selected>固定</option>' +
                '<option value="1">数据字典</option>' +
                '<option value="2">PDA用户</option>' +
                '</select>'+
                '</td>';
        tr += '<td style="width: 50px; text-align: center;"><div></div>' +
                '<input id="dataSourceCode-' + index + "-" + type + '" class="dataSourceCode" type="hidden" name="dataSourceCode" autocomplete="off"  />'+
                '<input id="dataSource-' + index + "-" + type + '" type="text" class="txt dataSource" name="dataSource" disabled autocomplete="off"  />'+
                '</td>';
        tr += '</tr>';
        index++;
        //时间
        tr += '<tr class="not-delete">';
        tr += '<td class="td-div" style="width: 30px; text-align: center;border-left: none;">' + index + '</td>';
        tr += '<td style="width: 50px; text-align: center;"><div>时间</div>' +
                '<input autocomplete="off" id="propertyName-' + index + "-"+type+'" type="text" maxlength="10"  class="txt" name ="propertyName" value="时间" />' +
                '</td>';
        tr += '<td class="click-show" style="width: 60px; text-align: center;"><div>日期框</div>' +
                '<select id="controlType-' + index + "-" + type + '" class="txtselect" name ="controlType" datacol="no" type="select" err="类型" checkexpession="NotNull">' +
                '<option value="1">文本框</option>' +
                '<option value="2">下拉框</option>' +
                '<option value="3" selected>日期框</option>' +
                '<option value="5">多行文本</option>' +
                '</select>' +
                '</td>';
        tr += '<td style="width: 60px; text-align: center;"><div>不能为空</div>' +
                '<select id="restrictiveConditions-' + index + "-" + type + '" class="txtselect" name ="restrictiveConditions"  datacol="no" type="select" err="限制条件" checkexpession="NotNull">'+
                '<option value="1" selected>不能为空</option>'+
                '<option value="2">只能输入数字</option>'+
                '<option value="3">数字、字母</option>'+
                '<option value="4">无限制</option>'+
                '<option value="5">默认当前用户</option>'+
                '</select>'+
                '</td>';
        tr += '<td style="width: 60px; text-align: center;"><div>固定</div>' +
                '<select id="dataSourceType-' + index + "-" + type + '" class="txtselect dataSourceTypeSelect" name ="dataSourceType"  datacol="no" type="select" err="数据源类型" checkexpession="NotNull">' +
                '<option value="0" selected>固定</option>' +
                '<option value="1">数据字典</option>' +
                '<option value="2">PDA用户</option>' +
                '</select>'+
                '</td>';
        tr += '<td style="width: 50px; text-align: center;"><div></div>' +
                '<input id="dataSourceCode-' + index + "-" + type + '" class="dataSourceCode" type="hidden" name="dataSourceCode" autocomplete="off"  />'+
                '<input id="dataSource-' + index + "-" + type + '" type="text" class="txt dataSource" name="dataSource" disabled autocomplete="off"  />'+
                '</td>';
        tr += '</tr>';
        index++;
        //备注
        tr += '<tr class="not-delete">';
        tr += '<td class="td-div" style="width: 30px; text-align: center;border-left: none;">' + index + '</td>';
        tr += '<td style="width: 50px; text-align: center;"><div>备注</div>' +
                '<input autocomplete="off" id="propertyName-' + index + "-"+type+'" type="text" maxlength="10"  class="txt" name ="propertyName" value="备注" />' +
                '</td>';
        tr += '<td class="click-show" style="width: 60px; text-align: center;"><div>多行文本</div>' +
                '<select id="controlType-' + index + "-" + type + '" class="txtselect" name ="controlType" datacol="no" type="select" err="类型" checkexpession="NotNull">' +
                '<option value="1">文本框</option>' +
                '<option value="2">下拉框</option>' +
                '<option value="3">日期框</option>' +
                '<option value="5" selected>多行文本</option>' +
                '</select>' +
                '</td>';
        tr += '<td style="width: 60px; text-align: center;"><div>无限制</div>' +
                '<select id="restrictiveConditions-' + index + "-" + type + '" class="txtselect" name ="restrictiveConditions"  datacol="no" type="select" err="限制条件" checkexpession="NotNull">'+
                '<option value="1" >不能为空</option>'+
                '<option value="2">只能输入数字</option>'+
                '<option value="3">数字、字母</option>'+
                '<option value="4" selected>无限制</option>'+
                '<option value="5">默认当前用户</option>'+
                '</select>'+
                '</td>';
        tr += '<td style="width: 60px; text-align: center;"><div>固定</div>' +
                '<select id="dataSourceType-' + index + "-" + type + '" class="txtselect dataSourceTypeSelect" name ="dataSourceType"  datacol="no" type="select" err="数据源类型" checkexpession="NotNull">' +
                '<option value="0" selected>固定</option>' +
                '<option value="1">数据字典</option>' +
                '<option value="2">PDA用户</option>' +
                '</select>'+
                '</td>';
        tr += '<td style="width: 50px; text-align: center;"><div></div>' +
                '<input id="dataSourceCode-' + index + "-" + type + '" class="dataSourceCode" type="hidden" name="dataSourceCode" autocomplete="off"  />'+
                '<input id="dataSource-' + index + "-" + type + '" type="text" class="txt dataSource" name="dataSource" disabled autocomplete="off" />'+
                '</td>';
        tr += '</tr>';
        return tr;
    }

    //插入一个空行
    function InsetTableRow() {
        if (1==TabTable){
            var trobj = $("#grid_Field1 tbody .selected");
            if (trobj.length) {
                trobj.before(CreateTableRow(RowIndex1,"one"));
                var selectedindex = (trobj.find('td:eq(0)').html()) - 1;
                $("#grid_Field1 tbody tr:eq(" + selectedindex + ")").find('input,select').hide();
                //更新表格序列号
                var indexrow = 1;
                $("#grid_Field1 tbody tr").each(function () {
                    $(this).find('td:eq(0)').html(indexrow);
                    $(this).find("input,select").each(function () {
                        let indexId = $(this).attr("id");
                        let index = indexId.split("-");
                        $(this).attr("id",index[0]+"-"+indexrow+"-"+index[2]);
                    });
                    indexrow++;
                });
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
                $("#grid_Field2 tbody tr").each(function () {
                    $(this).find('td:eq(0)').html(indexrow);
                    $(this).find("input,select").each(function () {
                        let indexId = $(this).attr("id");
                        let index = indexId.split("-");
                        $(this).attr("id",index[0]+"-"+indexrow+"-"+index[2]);
                    });
                    indexrow++;
                });
            }else {
                var len=$("#grid_Field2 tbody tr").length;
                $("#grid_Field2 tbody").append(CreateTableRow(len+1,"two"));
                $("#grid_Field2 tbody tr:eq(" + len + ")").find('input,select').hide();
            }
            RowIndex2++;
        }else if (3==TabTable){
            var trobj = $("#grid_Field3 tbody .selected");
            if (trobj.length) {
                trobj.before(CreateTableRow(RowIndex3,"three"));
                var selectedindex = (trobj.find('td:eq(0)').html()) - 1;
                $("#grid_Field3 tbody tr:eq(" + selectedindex + ")").find('input,select').hide();
                //更新表格序列号
                var indexrow = 1;
                $("#grid_Field3 tbody tr").each(function () {
                    $(this).find('td:eq(0)').html(indexrow);
                    $(this).find("input,select").each(function () {
                        let indexId = $(this).attr("id");
                        let index = indexId.split("-");
                        $(this).attr("id",index[0]+"-"+indexrow+"-"+index[2]);
                    });
                    indexrow++;
                });
            }else {
                var len = $("#grid_Field3 tbody tr").length;
                $("#grid_Field3 tbody").append(CreateTableRow(len+1,"three"));
                $("#grid_Field3 tbody tr:eq(" + len + ")").find('input,select').hide();
            }
            RowIndex3++;
        }
    }

    //清空表格当前行
    function EmptyTableRow() {
        if (1==TabTable) {
            var trobj = $("#grid_Field1 tbody .selected");
            if (trobj != null && trobj != undefined && trobj.length > 0&&!(trobj.hasClass("not-delete"))) {
                trobj.remove();
                var indexrow = 1;
                $("#grid_Field1 tbody tr").each(function () {
                    $(this).find('td:eq(0)').html(indexrow);
                    $(this).find("input,select").each(function () {
                        let indexId = $(this).attr("id");
                        let index = indexId.split("-");
                        $(this).attr("id",index[0]+"-"+indexrow+"-"+index[2]);
                    });
                    indexrow++;
                });
            }  else if(trobj.length==0){
                tipDialog("请先选择一行！\n", 4, 'warning');
                //tipCss($(this), "请先选择一行！\n");
            }else{
                tipDialog("不能删除\n", 4, 'warning');
                //tipCss($(this), "不能删除\n");
            }
        }else if (2==TabTable){
            var trobj = $("#grid_Field2 tbody .selected");
            if (trobj != null && trobj != undefined && trobj.length > 0&&!(trobj.hasClass("not-delete"))) {
                trobj.remove();
                var indexrow = 1;
                $("#grid_Field2 tbody tr").each(function () {
                    $(this).find('td:eq(0)').html(indexrow);
                    $(this).find("input,select").each(function () {
                        let indexId = $(this).attr("id");
                        let index = indexId.split("-");
                        $(this).attr("id",index[0]+"-"+indexrow+"-"+index[2]);
                    });
                    indexrow++;
                });
            }  else if(trobj.length==0){
                tipDialog("请先选择一行！\n", 4, 'warning');
                //tipCss($(this), "请先选择一行！\n");
            }else{
                tipDialog("不能删除\n", 4, 'warning');
                //tipCss($(this), "不能删除\n");
            }
        }else if (3==TabTable){
            var trobj = $("#grid_Field3 tbody .selected");
            if (trobj != null && trobj != undefined && trobj.length > 0&&!(trobj.hasClass("not-delete"))) {
                trobj.remove();
                var indexrow = 1;
                $("#grid_Field3 tbody tr").each(function () {
                    $(this).find('td:eq(0)').html(indexrow);
                    $(this).find("input,select").each(function () {
                        let indexId = $(this).attr("id");
                        let index = indexId.split("-");
                        $(this).attr("id",index[0]+"-"+indexrow+"-"+index[2]);
                    });
                    indexrow++;
                });
            } else if(trobj.length==0){
                tipDialog("请先选择一行！\n", 4, 'warning');
                //tipCss($(this), "请先选择一行！\n");
            }else{
                tipDialog("不能删除\n", 4, 'warning');
                //tipCss($(this), "不能删除\n");
            }
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
                    $(this).find('td:eq(0)').html(indexrow);
                    $(this).find("input,select").each(function () {
                        let indexId = $(this).attr("id");
                        let index = indexId.split("-");
                        $(this).attr("id",index[0]+"-"+indexrow+"-"+index[2]);
                    });
                    indexrow++;
                });
            } else {
                tipDialog("请先选择一行或者当前已到第一行！\n", 4, 'warning');
                //tipCss($(this), "请先选择一行或者当前已到第一行！\n");
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
                    $(this).find('td:eq(0)').html(indexrow);
                    $(this).find("input,select").each(function () {
                        let indexId = $(this).attr("id");
                        let index = indexId.split("-");
                        $(this).attr("id",index[0]+"-"+indexrow+"-"+index[2]);
                    });
                    indexrow++;
                });
            } else {
                tipDialog("请先选择一行或者当前已到第一行！\n", 4, 'warning');
                //tipCss($(this), "请先选择一行或者当前已到第一行！\n");
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
                    $(this).find('td:eq(0)').html(indexrow);
                    $(this).find("input,select").each(function () {
                        let indexId = $(this).attr("id");
                        let index = indexId.split("-");
                        $(this).attr("id",index[0]+"-"+indexrow+"-"+index[2]);
                    });
                    indexrow++;
                });
            } else {
                tipDialog("请先选择一行或者当前已到第一行！\n", 4, 'warning');
                //tipCss($(this), "请先选择一行或者当前已到第一行！\n");
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
                    $(this).find("input,select").each(function () {
                        let indexId = $(this).attr("id");
                        let index = indexId.split("-");
                        $(this).attr("id",index[0]+"-"+indexrow+"-"+index[2]);
                    });
                    indexrow++;
                });
            } else {
                tipDialog("请先选择一行或者当前已到最后一行！\n", 4, 'warning');
                //tipCss($(this), "请先选择一行或者当前已到最后一行！\n");
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
                    $(this).find("input,select").each(function () {
                        let indexId = $(this).attr("id");
                        let index = indexId.split("-");
                        $(this).attr("id",index[0]+"-"+indexrow+"-"+index[2]);
                    });
                    indexrow++;
                });
            } else {
                tipDialog("请先选择一行或者当前已到最后一行！\n", 4, 'warning');
                //tipCss($(this), "请先选择一行或者当前已到最后一行！\n");
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
                    $(this).find("input,select").each(function () {
                        let indexId = $(this).attr("id");
                        let index = indexId.split("-");
                        $(this).attr("id",index[0]+"-"+indexrow+"-"+index[2]);
                    });
                    indexrow++;
                });
            } else {
                tipDialog("请先选择一行或者当前已到最后一行！\n", 4, 'warning');
                //tipCss($(this), "请先选择一行或者当前已到最后一行！\n");
                return;
            }
        }
    }

    //表格点击事件
    function TableTdEvent() {
        $("#TableFieldDiv .grid").on("click",".td-div",function(){
            $(this).parent().parent().find(".selected").each(function () {
                $(this).removeClass("selected");
            });
            $(this).parent().addClass("selected");
        });

        $("#TableFieldDiv div tbody").on("change",".dataSourceTypeSelect",function(){
            var thi = $(this);
            var html = thi.find("option:selected").text();
            var val = thi.val();
            var id = thi.attr("id");
            var index = id.split("-");
            if(0==val){
                $("#dataSource-"+index[1]+"-"+index[2]).removeClass("icontree");
                $("#dataSource-"+index[1]+"-"+index[2]).prop("disabled",true);
                $("#dataSourceCode-"+index[1]+"-"+index[2]).val("");
                $("#dataSource-"+index[1]+"-"+index[2]).attr("value","");
                $("#dataSource-"+index[1]+"-"+index[2]).parent().find('div').html("");
            }else if(1==val){
                $("#dataSource-"+index[1]+"-"+index[2]).addClass("icontree");
                $("#dataSource-"+index[1]+"-"+index[2]).prop("disabled",false);
            }else if(2==val){
                $("#dataSource-"+index[1]+"-"+index[2]).removeClass("icontree");
                $("#dataSource-"+index[1]+"-"+index[2]).prop("disabled",true);
                $("#dataSourceCode-"+index[1]+"-"+index[2]).val("");
                $("#dataSource-"+index[1]+"-"+index[2]).attr("value","");
                $("#dataSource-"+index[1]+"-"+index[2]).parent().find('div').html("");
            }
        });
        $("#TableFieldDiv div tbody").on("focus",".dataSource",function(){
            Loading(true,"正在获取数据,请等待...","#addWorkProcessModal");
            var objId = this.id;
            var index = objId.split("-");
            var type = $("#dataSourceType-"+index[1]+"-"+index[2]).val();
            $("#dataSource-" + index[1] + "-" + index[2]).html("");
            $("#dataSource-" + index[1] + "-" + index[2]).parent().find('div').removeData();
            if (1==type) {
                comboBoxTree(objId, "182px");
                let itemtree = {
                    onnodeclick: function (item) {
                        $("#dataSourceCode-" + index[1] + "-" + index[2]).val(item.code);
                        $("#dataSource-" + index[1] + "-" + index[2]).attr("value", item.text);
                        $("#dataSource-" + index[1] + "-" + index[2]).html(item.text);
                        $("#dataSource-" + index[1] + "-" + index[2]).parent().find('div').html(item.text);
                    },
                    url: "${request.contextPath}/dataDictionary/TreeJson.json"
                };
                $("#comboBoxTree" + objId).treeview(itemtree);
                $("#comboBoxTree" + objId + "_" + GetQuery('KeyValue').replace(/-/g, '_')).parent('li').remove();
            }
            Loading(false,"","#addWorkProcessModal");
        });
        $("#TableFieldDiv div tbody").on("click","td",function(){
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
                                    obj.parent().find('input[type=hidden]').val(newText);
                                }
                                obj.hide();
                                obj.parent().find('div').html(newText);
                                break;
                            default:
                                if (objclass != "txt icontree") {
                                    newText = obj.last().val();
                                    obj.hide();
                                    obj.parent().find('div').html(newText);
                                }
                                break;
                        }
                    }
                });
            }
        });
        $("#TableFieldDiv div tbody").on("change","[name='controlType']",function(){
            if($(this).find("option:selected").text()=="下拉框"){
                $(this).parents("tr").find(".dataSourceTypeSelect").prop("disabled",false);
            }else{
                $(this).parents("tr").find(".dataSourceTypeSelect").prop("disabled",true);
                $(this).parents("tr").find(".dataSourceTypeSelect").prev("div").text("固定");
                $(this).parents("tr").find(".dataSourceTypeSelect").find("option").removeAttr("selected");
                $(this).parents("tr").find(".dataSourceTypeSelect").find("option:first").attr("selected");
            }
            let html;
            if("1"!=$(this).find("option:selected").val()){
                html = '<option value="1" selected>不能为空</option>'+
                        '<option value="4">无限制</option>';
            }else {
                html = '<option value="1" selected>不能为空</option>'+
                        '<option value="2">只能输入数字</option>'+
                        '<option value="3">数字、字母</option>'+
                        '<option value="4">无限制</option>'+
                        '<option value="5">默认当前用户</option>';
            }
            $(this).parent().parent().find("select[name=restrictiveConditions]").parent().find("div").html("不能为空");
            $(this).parent().parent().find("select[name=restrictiveConditions]").html(html);
        });
        $("#TableFieldDiv div tbody").on("click",".click-show",function(){
            if($(this).find("option:selected").text()!=="下拉框"){
                $(this).parents("tr").find(".dataSourceTypeSelect").prev("div").text("固定");
                $(this).parents("tr").find("td:last").find("div").text("");
            }
        });
/*
        $("#grid_Field1 tbody").on("change",".dataSourceTypeSelect",function(){
            var thi = $(this);
            var html = thi.find("option:selected").text();
            var val = thi.val();
            var id = thi.attr("id");
            var index = id.split("-");
            if(0==val){
                $("#dataSource-"+index[1]+"-"+index[2]).removeClass("icontree");
                $("#dataSource-"+index[1]+"-"+index[2]).prop("disabled",true);
                $("#dataSourceCode-"+index[1]+"-"+index[2]).val("");
                $("#dataSource-"+index[1]+"-"+index[2]).attr("value","");
                $("#dataSource-"+index[1]+"-"+index[2]).parent().find('div').html("");
            }else if(1==val){
                $("#dataSource-"+index[1]+"-"+index[2]).addClass("icontree");
                $("#dataSource-"+index[1]+"-"+index[2]).prop("disabled",false);
            }
        });
        $("#grid_Field2 tbody").on("change",".dataSourceTypeSelect",function(){
            var thi = $(this);
            var html = thi.find("option:selected").text();
            var val = thi.val();
            var id = thi.attr("id");
            var index = id.split("-");
            if(0==val){
                $("#dataSource-"+index[1]+"-"+index[2]).removeClass("icontree");
                $("#dataSource-"+index[1]+"-"+index[2]).prop("disabled",true)
            }else if(1==val){
                $("#dataSource-"+index[1]+"-"+index[2]).addClass("icontree");
                $("#dataSource-"+index[1]+"-"+index[2]).prop("disabled",false);
                $("#dataSource-"+index[1]+"-"+index[2]).parent().find('div').html("");
            }
        });
        $("#grid_Field3 tbody").on("change",".dataSourceTypeSelect",function(){
            var thi = $(this);
            var html = thi.find("option:selected").text();
            var val = thi.val();
            var id = thi.attr("id");
            var index = id.split("-");
            if(0==val){
                $("#dataSource-"+index[1]+"-"+index[2]).removeClass("icontree");
                $("#dataSource-"+index[1]+"-"+index[2]).prop("disabled",true)
            }else if(1==val){
                $("#dataSource-"+index[1]+"-"+index[2]).addClass("icontree");
                $("#dataSource-"+index[1]+"-"+index[2]).prop("disabled",false);
                $("#dataSource-"+index[1]+"-"+index[2]).parent().find('div').html("");
            }
        });

        $("#grid_Field1 tbody").on("focus",".dataSource",function(){
            var objId = this.id;
            var index = objId.split("-");
            comboBoxTree(objId, "182px");
            var itemtree = {
                onnodeclick: function (item) {
                    $("#dataSourceCode-"+index[1]+"-"+index[2]).val(item.id);
                    $("#dataSource-"+index[1]+"-"+index[2]).attr("value",item.text);
                    $("#dataSource-"+index[1]+"-"+index[2]).parent().find('div').html(item.text);
                },
                url: "${request.contextPath}/dataDictionary/TreeJson.json"
            };
            $("#comboBoxTree" + objId).treeview(itemtree);
            $("#comboBoxTree" + objId + "_" + GetQuery('KeyValue').replace(/-/g, '_')).parent('li').remove();
        });
        $("#grid_Field2 tbody").on("focus",".dataSource",function(){
            var objId = this.id;
            var index = objId.split("-");
            comboBoxTree(objId, "182px");
            var itemtree = {
                onnodeclick: function (item) {
                    $("#dataSourceCode-"+index[1]+"-"+index[2]).val(item.id);
                    $("#dataSource-"+index[1]+"-"+index[2]).attr("value",item.text);
                    $("#dataSource-"+index[1]+"-"+index[2]).parent().find('div').html(item.text);
                },
                url: "${request.contextPath}/dataDictionary/TreeJson.json"
            };
            $("#comboBoxTree" + objId).treeview(itemtree);
            $("#comboBoxTree" + objId + "_" + GetQuery('KeyValue').replace(/-/g, '_')).parent('li').remove();
        });
        $("#grid_Field3 tbody").on("focus",".dataSource",function(){
            var objId = this.id;
            var index = objId.split("-");
            comboBoxTree(objId, "182px");
            var itemtree = {
                onnodeclick: function (item) {
                    $("#dataSourceCode-"+index[1]+"-"+index[2]).val(item.id);
                    $("#dataSource-"+index[1]+"-"+index[2]).attr("value",item.text);
                    $("#dataSource-"+index[1]+"-"+index[2]).parent().find('div').html(item.text);
                },
                url: "${request.contextPath}/dataDictionary/TreeJson.json"
            };
            $("#comboBoxTree" + objId).treeview(itemtree);
            $("#comboBoxTree" + objId + "_" + GetQuery('KeyValue').replace(/-/g, '_')).parent('li').remove();
        });

        $("#grid_Field1 tbody").on("click","td",function(){
            /!*canClick();*!/
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
                                    obj.parent().find('input[type=hidden]').val(newText);
                                }
                                obj.hide();
                                obj.parent().find('div').html(newText);
                                break;
                            default:
                                if (objclass != "txt icontree") {
                                    newText = obj.last().val();
                                    obj.hide();
                                    obj.parent().find('div').html(newText);
                                }
                                break;
                        }
                    }
                });
            }
        });
        $("#grid_Field2 tbody").on("click","td",function(){
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
                                    obj.parent().find('input[type=hidden]').val(newText);
                                }
                                obj.hide();
                                obj.parent().find('div').html(newText);
                                break;
                            default:
                                if (objclass != "txt icontree") {
                                    newText = obj.last().val();
                                    obj.hide();
                                    obj.parent().find('div').html(newText);
                                }
                                break;
                        }
                    }
                });
            }
        });
        $("#grid_Field3 tbody").on("click","td",function(){
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
                                    obj.parent().find('input[type=hidden]').val(newText);
                                }
                                obj.hide();
                                obj.parent().find('div').html(newText);
                                break;
                            default:
                                if (objclass != "txt icontree") {
                                    newText = obj.last().val();
                                    obj.hide();
                                    obj.parent().find('div').html(newText);
                                }
                                break;
                        }
                    }
                });
            }
        });

        $("#grid_Field1 tbody").on("change","[name='controlType']",function(){
            if($(this).find("option:selected").text()=="下拉框"){
                $(this).parents("tr").find(".dataSourceTypeSelect").prop("disabled",false);
            }else{
                $(this).parents("tr").find(".dataSourceTypeSelect").prop("disabled",true);
                $(this).parents("tr").find(".dataSourceTypeSelect").prev("div").text("固定");
            }
        });
        $("#grid_Field2 tbody").on("change","[name='controlType']",function(){
            if($(this).find("option:selected").text()=="下拉框"){
                $(this).parents("tr").find(".dataSourceTypeSelect").prop("disabled",false);
            }else{
                $(this).parents("tr").find(".dataSourceTypeSelect").prop("disabled",true);
                $(this).parents("tr").find(".dataSourceTypeSelect").prev("div").text("固定");
            }
        });
        $("#grid_Field3 tbody").on("change","[name='controlType']",function(){
            if($(this).find("option:selected").text()=="下拉框"){
                $(this).parents("tr").find(".dataSourceTypeSelect").prop("disabled",false);
            }else{
                $(this).parents("tr").find(".dataSourceTypeSelect").prop("disabled",true);
                $(this).parents("tr").find(".dataSourceTypeSelect").prev("div").text("固定");
            }
        });

        $("#grid_Field1 tbody").on("click",".click-show",function(){
            if($(this).find("option:selected").text()!=="下拉框"){
                $(this).parents("tr").find(".dataSourceTypeSelect").prev("div").text("固定");
                $(this).parents("tr").find("td:last").find("input").attr("disabled",true);
                $(this).parents("tr").find("td:last").find("div").text("");
            }else{
                $(this).parents("tr").find("td:last").find("input").attr("disabled",false);
            }
        });
        $("#grid_Field2 tbody").on("click",".click-show",function(){
            if($(this).find("option:selected").text()!=="下拉框"){
                $(this).parents("tr").find(".dataSourceTypeSelect").prev("div").text("固定");
                $(this).parents("tr").find("td:last").find("input").attr("disabled",true);
                $(this).parents("tr").find("td:last").find("div").text("");
            }else{
                $(this).parents("tr").find("td:last").find("input").attr("disabled",false);
            }
        });
        $("#grid_Field3 tbody").on("click",".click-show",function(){
            if($(this).find("option:selected").text()!=="下拉框"){
                $(this).parents("tr").find(".dataSourceTypeSelect").prev("div").text("固定");
                $(this).parents("tr").find("td:last").find("input").attr("disabled",true);
                $(this).parents("tr").find("td:last").find("div").text("");
            }else{
                $(this).parents("tr").find("td:last").find("input").attr("disabled",false);
            }
        });*/

    }

    //创建行
    function CreateTableRow(index,type) {
        var tr = '';
        tr += '<tr>';
        tr += '<td class="td-div" style="width: 30px; text-align: center;border-left: none;">' + index + '</td>';
        tr += '<td style="width: 50px; text-align: center;"><div></div><input id="propertyName-' + index + "-"+type+'" type="text" maxlength="10"  class="txt" name ="propertyName"  autocomplete="off" /></td>';
        tr += '<td class="click-show" style="width: 60px; text-align: center;"><div></div>' + controlType(index,type) + '</td>';
        tr += '<td style="width: 60px; text-align: center;"><div></div>' + restrictive(index,type) + '</td>';
        tr += '<td style="width: 60px; text-align: center;"><div></div>' + dataSourceType(index,type) + '</td>';
        tr += '<td style="width: 50px; text-align: center;"><div></div>' + dataSource(index,type) + '</td>';
        tr += '</tr>';
        return tr;
    }

    //类型
    function controlType(index,type) {
        var html = '<select id="controlType-' + index + "-" + type + '" class="txtselect" name ="controlType"  datacol="no" type="select" err="类型" checkexpession="NotNull">';
        html += '<option value="1">文本框</option>';
        html += '<option value="2">下拉框</option>';
        html += '<option value="3">日期框</option>';
        html += '<option value="5">多行文本</option>';
        html += '</select>';
        return html;
    }
    //限制条件
    function restrictive(index,type){
        var html = '<select id="restrictiveConditions-' + index + "-" + type + '" class="txtselect" name ="restrictiveConditions"  datacol="no" type="select" err="限制条件" checkexpession="NotNull">';
        html += '<option value="1">不能为空</option>';
        html += '<option value="2">只能输入数字</option>';
        html += '<option value="3">数字、字母</option>';
        html += '<option value="4">无限制</option>';
        html += '<option value="5">默认当前用户</option>';
        html += '</select>';
        return html;
    }
    //数据源类型
    function dataSourceType(index,type){
        var html = '<select id="dataSourceType-' + index + "-" + type + '" class="txtselect dataSourceTypeSelect" name ="dataSourceType"  datacol="no" type="select" err="数据源类型" checkexpession="NotNull" disabled>';
        html += '<option value="0">固定</option>';
        html += '<option value="1">数据字典</option>';
        html += '<option value="2">PDA用户</option>';
        html += '</select>';
        return html;
    }
    //数据源
    function dataSource(index,type){
        var html = '<input id="dataSourceCode-' + index + "-" + type + '" class="dataSourceCode" type="hidden" name="dataSourceCode" autocomplete="off" />';
        html += '<input id="dataSource-' + index + "-" + type + '" type="text" class="txt dataSource" name="dataSource" disabled autocomplete="off" />';
        return html;
    }


</script>






