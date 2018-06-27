<#if workFlow??>
<form id="updateWorkProcessForm" style="margin: 1px">
    <div class="bd">
        <div class="ScrollBar" style="margin-top: 1px;">
            <div id="UpdateTableProperty">
                <table class="form">
                    <tr>
                        <th class="formTitle">所属工艺：
                        </th>
                        <td class="formValue" colspan="3">
                            <input id="UpdateWorkProcessId" type="hidden" name="workProcessId" value="${workProcess.workProcessId}">
                            <input id="UpdateWorkFlowId" type="hidden" value="${workFlow.workFlowId}">
                            <input type="text" class="txt " maxlength="10" value="${workFlow.CName}" disabled/>
                        </td>
                    </tr>
                    <tr>
                        <th class="formTitle">工艺名称：
                        </th>
                        <td class="formValue" colspan="3">
                            <input id="UpdateWorkProcessCName" name="cName" type="text" class="txt required" value="${workProcess.CName}"/>
                            <input id="UpdateNameId" name="nameId" type="hidden" <#if workProcess.nameId??>value="${workProcess.nameId}"</#if>/>
                        </td>
                    </tr>
                    <tr>
                        <th class="formTitle">工序字段：
                        </th>
                        <td class="formValue" colspan="3">
                            <select id="updateIsWorkshopRecord" class="txtselect" name="isWorkshopRecord">
                                <option value="0" <#if 0==workProcess.isWorkshopRecord>selected</#if>>无</option>
                                <option value="1" <#if 1==workProcess.isWorkshopRecord>selected</#if>>有</option>
                            </select>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div id="updateIsWorkshopRecordDiv" class="<#if 0==workProcess.isWorkshopRecord>hidden</#if>">
            <div class="tipstitle_title settingtable bold bd todayInfoPanelTab rightPanelTitle_normal">
                <div class="tab_list_top" style="position: absolute">
                    <div id="UpdateTabTableField1" class="tab_list bd actived">操作记录</div>
                    <div id="UpdateTabTableField2" class="tab_list bd ">审核记录</div>
                    <div id="UpdateTabTableField3" class="tab_list bd ">巡检记录</div>
                </div>
                <div style="float: right;">
                    <div class="tools_bar_icon">
                        <div class="icon-botton" title="上移" onclick="UpdateGridup()">
                            <img src="${staticImg}/images/Icon16/resultset_top.png" />
                        </div>
                        <div class="icon-botton" title="下移" onclick="UpdateGriddown()">
                            <img src="${staticImg}/images/Icon16/resultset_bottom.png" />
                        </div>
                        <div class="tools_separator" style="height: 23px; margin-right: 5px;"></div>
                        <div class="icon-botton" title="插入行" onclick="UpdateInsetTableRow()">
                            <img src="${staticImg}/images/Icon16/table_row_insert.png" />
                        </div>
                        <div class="icon-botton" title="清空行" onclick="UpdateEmptyTableRow()">
                            <img src="${staticImg}/images/Icon16/table_row_delete.png" />
                        </div>
                    </div>
                </div>
            </div>
            <div id="UpdateTableFieldDiv" class="ScrollBar" style="margin-top: 1px;">
                <div id="UpdateTableField1" class="tabPanel">
                    <table id="Update_Grid_Field1" class="grid" style="width: 100%">
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
                        <tbody>
                            <#if 1==workProcess.isWorkshopRecord>
                                <#if workProcess.tableField1??>
                                    <#list workProcess.tableField1 as tableField1>
                                        <tr>
                                        <td class="td-div" style="width: 30px; text-align: center;border-left: none;">${tableField1.listIndex}</td>
                                        <td style="width: 50px; text-align: center;"><div>${tableField1.propertyName}</div>
                                            <input id="updateFormAttributeId-${tableField1.listIndex}-one" type="hidden"
                                                   name ="updateFormAttributeId" value="${tableField1.formAttributeId}" />
                                            <input id="updatePropertyName-${tableField1.listIndex}-one" type="text" maxlength="10" class="txt"
                                                   name ="updatePropertyName" value="${tableField1.propertyName}" />
                                        </td>
                                        <td class="click-show" style="width: 60px; text-align: center;">
                                            <div>
                                                <#if 1==tableField1.controlType>文本框
                                                <#elseif 2==tableField1.controlType>下拉框
                                                <#elseif 3==tableField1.controlType>日期框
                                                <#elseif 5==tableField1.controlType>多行文本
                                                </#if>
                                            </div>
                                            <select id="updateControlType-${tableField1.listIndex}-one" class="txtselect"
                                                    name ="updateControlType" datacol="no" type="select" err="类型" checkexpession="NotNull">
                                                <option value="1" <#if 1==tableField1.controlType>selected</#if>>文本框</option>
                                                <option value="2" <#if 2==tableField1.controlType>selected</#if>>下拉框</option>
                                                <option value="3" <#if 3==tableField1.controlType>selected</#if>>日期框</option>
                                                <option value="5" <#if 5==tableField1.controlType>selected</#if>>多行文本</option>
                                            </select>
                                        </td>
                                        <td style="width: 60px; text-align: center;">
                                            <div>
                                            <#if 1==tableField1.controlType>
                                                <#if 1==tableField1.restrictiveConditions>不能为空
                                                <#elseif 2==tableField1.restrictiveConditions>只能输入数字
                                                <#elseif 3==tableField1.restrictiveConditions>数字、字母
                                                <#elseif 4==tableField1.restrictiveConditions>无限制
                                                <#elseif 5==tableField1.restrictiveConditions>默认当前用户
                                                </#if>
                                            <#else>
                                                <#if 1==tableField1.restrictiveConditions>不能为空
                                                <#elseif 4==tableField1.restrictiveConditions>无限制
                                                </#if>
                                            </#if>
                                            </div>
                                            <select id="updateRestrictiveConditions-${tableField1.listIndex}-one" class="txtselect"
                                                    name ="updateRestrictiveConditions"  datacol="no" type="select" err="限制条件" checkexpession="NotNull">
                                                <#if 1==tableField1.controlType>
                                                    <option value="1" <#if 1==tableField1.restrictiveConditions>selected</#if>>不能为空</option>
                                                    <option value="2" <#if 2==tableField1.restrictiveConditions>selected</#if>>只能输入数字</option>
                                                    <option value="3" <#if 3==tableField1.restrictiveConditions>selected</#if>>数字、字母</option>
                                                    <option value="4" <#if 4==tableField1.restrictiveConditions>selected</#if>>无限制</option>
                                                    <option value="5" <#if 5==tableField1.restrictiveConditions>selected</#if>>默认当前用户</option>
                                                <#else >
                                                    <option value="1" <#if 1==tableField1.restrictiveConditions>selected</#if>>不能为空</option>
                                                    <option value="4" <#if 4==tableField1.restrictiveConditions>selected</#if>>无限制</option>
                                                </#if>
                                            </select>
                                        </td>
                                        <td style="width: 60px; text-align: center;">
                                            <div>
                                                <#if 0==tableField1.dataSourceType>固定
                                                <#elseif 1==tableField1.dataSourceType>数据字典
                                                <#elseif 2==tableField1.dataSourceType>PDA用户
                                                </#if>
                                            </div>
                                            <select id="updateDataSourceType-${tableField1.listIndex}-one" class="txtselect dataSourceTypeSelect"
                                                    name ="updateDataSourceType"  datacol="no" type="select" err="数据源类型" checkexpession="NotNull">
                                                <#if 2==tableField1.controlType>
                                                    <option value="0" <#if 0==tableField1.dataSourceType>selected</#if>>固定</option>
                                                    <option value="1" <#if 1==tableField1.dataSourceType>selected</#if>>数据字典</option>
                                                    <option value="2" <#if 2==tableField1.dataSourceType>selected</#if>>PDA用户</option>
                                                <#else >
                                                    <option value="0" <#if 0==tableField1.dataSourceType>selected</#if>>固定</option>
                                                </#if>
                                            </select>
                                        </td>
                                        <td style="width: 50px; text-align: center;">
                                            <div>
                                                <#if tableField1.dataSource??>${tableField1.dataSource}</#if>
                                            </div>
                                            <input id="updateDataSourceCode-${tableField1.listIndex}-one" class="dataSourceCode" type="hidden"
                                                   name="updateDataSourceCode" <#if tableField1.dataSourceCode??>value="${tableField1.dataSourceCode}"</#if> autocomplete="off" />
                                            <input id="updateDataSource-${tableField1.listIndex}-one" type="text" class="txt dataSource"
                                                   name="updateDataSource" <#if tableField1.dataSource??>value="${tableField1.dataSource}" <#else >disabled</#if> autocomplete="off" />
                                        </td>
                                    </tr>
                                    </#list>
                                </#if>
                            </#if>
                        </tbody>
                    </table>
                </div>
                <div id="UpdateTableField2" class="tabPanel hidden">
                    <table id="Update_Grid_Field2" class="grid" style="width: 100%">
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
                        <tbody>
                            <#if 1==workProcess.isWorkshopRecord>
                                <#if workProcess.tableField2??>
                                    <#list workProcess.tableField2 as tableField2>
                                    <tr>
                                        <td class="td-div" style="width: 30px; text-align: center;border-left: none;">${tableField2.listIndex}</td>
                                        <td style="width: 50px; text-align: center;"><div>${tableField2.propertyName}</div>
                                            <input id="updateFormAttributeId-${tableField2.listIndex}-two" type="hidden"
                                                   name ="updateFormAttributeId" value="${tableField2.formAttributeId}" />
                                            <input id="updatePropertyName-${tableField2.listIndex}-two" type="text" maxlength="10" class="txt"
                                                   name ="updatePropertyName" value="${tableField2.propertyName}" />
                                        </td>
                                        <td class="click-show" style="width: 60px; text-align: center;">
                                            <div>
                                                <#if 1==tableField2.controlType>文本框
                                                <#elseif 2==tableField2.controlType>下拉框
                                                <#elseif 3==tableField2.controlType>日期框
                                                <#elseif 5==tableField2.controlType>多行文本
                                                </#if>
                                            </div>
                                            <select id="updateControlType-${tableField2.listIndex}-two" class="txtselect"
                                                    name ="updateControlType" datacol="no" type="select" err="类型" checkexpession="NotNull">
                                                <option value="1" <#if 1==tableField2.controlType>selected</#if>>文本框</option>
                                                <option value="2" <#if 2==tableField2.controlType>selected</#if>>下拉框</option>
                                                <option value="3" <#if 3==tableField2.controlType>selected</#if>>日期框</option>
                                                <option value="5" <#if 5==tableField2.controlType>selected</#if>>多行文本</option>
                                            </select>
                                        </td>
                                        <td style="width: 60px; text-align: center;">
                                            <div>
                                            <#if 1==tableField2.controlType>
                                                <#if 1==tableField2.restrictiveConditions>不能为空
                                                <#elseif 2==tableField2.restrictiveConditions>只能输入数字
                                                <#elseif 3==tableField2.restrictiveConditions>数字、字母
                                                <#elseif 4==tableField2.restrictiveConditions>无限制
                                                <#elseif 5==tableField2.restrictiveConditions>默认当前用户
                                                </#if>
                                            <#else >
                                                <#if 1==tableField2.restrictiveConditions>不能为空
                                                <#elseif 4==tableField2.restrictiveConditions>无限制
                                                </#if>
                                            </#if>
                                            </div>
                                            <select id="updateRestrictiveConditions-${tableField2.listIndex}-two" class="txtselect"
                                                    name ="updateRestrictiveConditions"  datacol="no" type="select" err="限制条件" checkexpession="NotNull">
                                                <#if 1==tableField2.controlType>
                                                    <option value="1" <#if 1==tableField2.restrictiveConditions>selected</#if>>不能为空</option>
                                                    <option value="2" <#if 2==tableField2.restrictiveConditions>selected</#if>>只能输入数字</option>
                                                    <option value="3" <#if 3==tableField2.restrictiveConditions>selected</#if>>数字、字母</option>
                                                    <option value="4" <#if 4==tableField2.restrictiveConditions>selected</#if>>无限制</option>
                                                    <option value="5" <#if 5==tableField2.restrictiveConditions>selected</#if>>默认当前用户</option>
                                                <#else >
                                                    <option value="1" <#if 1==tableField2.restrictiveConditions>selected</#if>>不能为空</option>
                                                    <option value="4" <#if 4==tableField2.restrictiveConditions>selected</#if>>无限制</option>
                                                </#if>
                                            </select>
                                        </td>
                                        <td style="width: 60px; text-align: center;">
                                            <div>
                                                <#if 0==tableField2.dataSourceType>固定
                                                <#elseif 1==tableField2.dataSourceType>数据字典
                                                <#elseif 2==tableField2.dataSourceType>PDA用户
                                                </#if>
                                            </div>
                                            <select id="updateDataSourceType-${tableField2.listIndex}-two" class="txtselect dataSourceTypeSelect"
                                                    name ="updateDataSourceType"  datacol="no" type="select" err="数据源类型" checkexpession="NotNull">
                                                <#if 2==tableField2.controlType>
                                                    <option value="0" <#if 0==tableField2.dataSourceType>selected</#if>>固定</option>
                                                    <option value="1" <#if 1==tableField2.dataSourceType>selected</#if>>数据字典</option>
                                                    <option value="2" <#if 2==tableField2.dataSourceType>selected</#if>>PDA用户</option>
                                                <#else >
                                                    <option value="0" <#if 0==tableField2.dataSourceType>selected</#if>>固定</option>
                                                </#if>
                                            </select>
                                        </td>
                                        <td style="width: 50px; text-align: center;">
                                            <div>
                                                <#if tableField2.dataSource??>${tableField2.dataSource}</#if>
                                            </div>
                                            <input id="dupdatDataSourceCode-${tableField2.listIndex}-two" class="dataSourceCode" type="hidden"
                                                   name="updateDataSourceCode" <#if tableField2.dataSourceCode??>value="${tableField2.dataSourceCode}"</#if> autocomplete="off" />
                                            <input id="updateDataSource-${tableField2.listIndex}-two" type="text" class="txt dataSource"
                                                   name="updateDataSource" <#if tableField2.dataSource??>value="${tableField2.dataSource}" <#else >disabled</#if> autocomplete="off" />
                                        </td>
                                    </tr>
                                    </#list>
                                </#if>
                            </#if>
                        </tbody>
                    </table>
                </div>
                <div id="UpdateTableField3" class="tabPanel hidden">
                    <table id="Update_Grid_Field3" class="grid" style="width: 100%">
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
                        <tbody>
                            <#if 1==workProcess.isWorkshopRecord>
                                <#if workProcess.tableField3??>
                                    <#list workProcess.tableField3 as tableField3>
                                    <tr>
                                        <td class="td-div" style="width: 30px; text-align: center;border-left: none;">${tableField3.listIndex}</td>
                                        <td style="width: 50px; text-align: center;"><div>${tableField3.propertyName}</div>
                                            <input id="updateFormAttributeId-${tableField3.listIndex}-three" type="hidden"
                                                   name ="updateFormAttributeId" value="${tableField3.formAttributeId}" />
                                            <input id="updatePropertyName-${tableField3.listIndex}-three" type="text" maxlength="10" class="txt"
                                                   name ="updatePropertyName" value="${tableField3.propertyName}" />
                                        </td>
                                        <td class="click-show" style="width: 60px; text-align: center;">
                                            <div>
                                                <#if 1==tableField3.controlType>文本框
                                                <#elseif 2==tableField3.controlType>下拉框
                                                <#elseif 3==tableField3.controlType>日期框
                                                <#elseif 5==tableField3.controlType>多行文本
                                                </#if>
                                            </div>
                                            <select id="updateControlType-${tableField3.listIndex}-three" class="txtselect"
                                                    name ="updateControlType" datacol="no" type="select" err="类型" checkexpession="NotNull">
                                                <option value="1" <#if 1==tableField3.controlType>selected</#if>>文本框</option>
                                                <option value="2" <#if 2==tableField3.controlType>selected</#if>>下拉框</option>
                                                <option value="3" <#if 3==tableField3.controlType>selected</#if>>日期框</option>
                                                <option value="5" <#if 5==tableField3.controlType>selected</#if>>多行文本</option>
                                            </select>
                                        </td>
                                        <td style="width: 60px; text-align: center;">
                                            <div>
                                            <#if 1==tableField3.controlType>
                                                <#if 1==tableField3.restrictiveConditions>不能为空
                                                <#elseif 2==tableField3.restrictiveConditions>只能输入数字
                                                <#elseif 3==tableField3.restrictiveConditions>数字、字母
                                                <#elseif 4==tableField3.restrictiveConditions>无限制
                                                <#elseif 5==tableField3.restrictiveConditions>默认当前用户
                                                </#if>
                                            <#else >
                                                <#if 1==tableField3.restrictiveConditions>不能为空
                                                <#elseif 4==tableField3.restrictiveConditions>无限制
                                                </#if>
                                            </#if>
                                            </div>
                                            <select id="updateRestrictiveConditions-${tableField3.listIndex}-three" class="txtselect"
                                                    name ="updateRestrictiveConditions"  datacol="no" type="select" err="限制条件" checkexpession="NotNull">
                                                <#if 1==tableField3.controlType>
                                                    <option value="1" <#if 1==tableField3.restrictiveConditions>selected</#if>>不能为空</option>
                                                    <option value="2" <#if 2==tableField3.restrictiveConditions>selected</#if>>只能输入数字</option>
                                                    <option value="3" <#if 3==tableField3.restrictiveConditions>selected</#if>>数字、字母</option>
                                                    <option value="4" <#if 4==tableField3.restrictiveConditions>selected</#if>>无限制</option>
                                                    <option value="5" <#if 5==tableField3.restrictiveConditions>selected</#if>>默认当前用户</option>
                                                <#else >
                                                    <option value="1" <#if 1==tableField3.restrictiveConditions>selected</#if>>不能为空</option>
                                                    <option value="4" <#if 4==tableField3.restrictiveConditions>selected</#if>>无限制</option>
                                                </#if>
                                            </select>
                                        </td>
                                        <td style="width: 60px; text-align: center;">
                                            <div>
                                                <#if 0==tableField3.dataSourceType>固定
                                                <#elseif 1==tableField3.dataSourceType>数据字典
                                                <#elseif 2==tableField3.dataSourceType>PDA用户
                                                </#if>
                                            </div>
                                            <select id="updateDataSourceType-${tableField3.listIndex}-three" class="txtselect dataSourceTypeSelect"
                                                    name ="updateDataSourceType"  datacol="no" type="select" err="数据源类型" checkexpession="NotNull">
                                            <#if 2==tableField3.controlType>
                                                <option value="0" <#if 0==tableField3.dataSourceType>selected</#if>>固定</option>
                                                <option value="1" <#if 1==tableField3.dataSourceType>selected</#if>>数据字典</option>
                                                <option value="2" <#if 2==tableField3.dataSourceType>selected</#if>>PDA用户</option>
                                            <#else >
                                                <option value="0" <#if 0==tableField3.dataSourceType>selected</#if>>固定</option>
                                            </#if>
                                            </select>
                                        </td>
                                        <td style="width: 50px; text-align: center;">
                                            <div>
                                                <#if tableField3.dataSource??>${tableField3.dataSource}</#if>
                                            </div>
                                            <input id="updateDataSourceCode-${tableField3.listIndex}-three" class="dataSourceCode" type="hidden"
                                                   name="updateDataSourceCode" <#if tableField3.dataSourceCode??>value="${tableField3.dataSourceCode}"</#if> autocomplete="off" />
                                            <input id="updateDataSource-${tableField3.listIndex}-three" type="text" class="txt dataSource"
                                                   name="updateDataSource" <#if tableField3.dataSource??>value="${tableField3.dataSource}" <#else >disabled</#if> autocomplete="off" />
                                        </td>
                                    </tr>
                                    </#list>
                                </#if>
                            </#if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</form>

<div id="updateWorkProcessNameModal" class="modal fade " data-width="780" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">工艺名称</h4>
    </div>
    <div class="modal-body" id="updateWorkProcessNameModalBody">
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="updateWorkProcessName" type="button" class="btn green" onclick="updateWorkProcessName()">确定</button>
    </div>
</div>

<#else >
<table class="form">
    <tr id="locationTr">
        <th class="formTitle">错误：
        </th>
        <td class="formValue" colspan="3">
            <input type="text" class="txt required" disabled value="请重试，再不行请联系管理员"/>
        </td>
    </tr>
</table>
</#if>


<script>


    $("#updateWorkProcessModal").on("focus","#UpdateWorkProcessCName",function(){
        $("#updateWorkProcessNameModal").modal({
            remote: "${request.contextPath}/workProcess/workProcessNameModal.htm"
        });
    });

    function updateWorkProcessName() {
        var workProcessName = $("#updateWorkProcessNameModalBody").find(".clickBg").text();
        var nameId = $("#updateWorkProcessNameModalBody").find(".clickBg").find("input").attr("id");
        $("#UpdateWorkProcessCName").val(workProcessName);
        $("#UpdateNameId").val(nameId);
        $("#updateWorkProcessNameModal").modal("hide");
    }

    $("#updateWorkProcessModal").on("click","#UpdateTabTableField1",function(){
        UpdateTabTable=1;
        $("#UpdateTableField2").addClass("hidden");
        $("#UpdateTableField3").addClass("hidden");
        $("#UpdateTableField1").removeClass("hidden");
        $("#UpdateTabTableField2").removeClass("actived");
        $("#UpdateTabTableField3").removeClass("actived");
        $("#UpdateTabTableField1").addClass("actived");
    });
    $("#updateWorkProcessModal").on("click","#UpdateTabTableField2",function(){
        UpdateTabTable=2;
        $("#UpdateTableField1").addClass("hidden");
        $("#UpdateTableField3").addClass("hidden");
        $("#UpdateTableField2").removeClass("hidden");
        $("#UpdateTabTableField1").removeClass("actived");
        $("#UpdateTabTableField3").removeClass("actived");
        $("#UpdateTabTableField2").addClass("actived");
    });
    $("#updateWorkProcessModal").on("click","#UpdateTabTableField3",function(){
        UpdateTabTable=3;
        $("#UpdateTableField1").addClass("hidden");
        $("#UpdateTableField2").addClass("hidden");
        $("#UpdateTableField3").removeClass("hidden");
        $("#UpdateTabTableField1").removeClass("actived");
        $("#UpdateTabTableField2").removeClass("actived");
        $("#UpdateTabTableField3").addClass("actived");
    });
    $("#updateWorkProcessModal").on("change","#updateIsWorkshopRecord",function(){
        UpdateTabTable=1;
        var select = $("#updateIsWorkshopRecord").val();
        if (0==select){
            $("#updateIsWorkshopRecordDiv").addClass("hidden");
        }else if (1==select){
            $("#updateIsWorkshopRecordDiv").removeClass("hidden");
            $("#UpdateTableField2").addClass("hidden");
            $("#UpdateTableField3").addClass("hidden");
            $("#UpdateTableField1").removeClass("hidden");
            $("#UpdateTabTableField2").removeClass("actived");
            $("#UpdateTabTableField3").removeClass("actived");
            $("#UpdateTabTableField1").addClass("actived");
        }
    });


    //插入一个空行
    function UpdateInsetTableRow() {
        if (1==UpdateTabTable){
            var trobj = $("#Update_Grid_Field1 tbody .selected");
            if (trobj.length) {
                trobj.before(UpdateCreateTableRow(RowIndex1,"one"));
                var selectedindex = (trobj.find('td:eq(0)').html()) - 1; $("#Update_Grid_Field1 tbody tr:eq(" + selectedindex + ")").find('input,select').hide();
                //更新表格序列号
                var indexrow = 1;
                $("#Update_Grid_Field1 tbody tr").each(function () {
                    $(this).find('td:eq(0)').html(indexrow);
                    $(this).find("input,select").each(function () {
                        let indexId = $(this).attr("id");
                        let index = indexId.split("-");
                        $(this).attr("id",index[0]+"-"+indexrow+"-"+index[2]);
                    });
                    indexrow++;
                });
            }else {
                var len=$("#Update_Grid_Field1 tbody tr").length;
                $("#Update_Grid_Field1 tbody").append(UpdateCreateTableRow(len+1,"one"));
                $("#Update_Grid_Field1 tbody tr:eq(" + len + ")").find('input,select').hide();
            }
            RowIndex1++;
        }else if (2==UpdateTabTable){
            var trobj = $("#Update_Grid_Field2 tbody .selected");
            if (trobj.length) {
                trobj.before(UpdateCreateTableRow(RowIndex2,"two"));
                var selectedindex = (trobj.find('td:eq(0)').html()) - 1;
                $("#Update_Grid_Field2 tbody tr:eq(" + selectedindex + ")").find('input,select').hide();
                //更新表格序列号
                var indexrow = 1;
                $("#Update_Grid_Field2 tbody tr").each(function () {
                    $(this).find('td:eq(0)').html(indexrow);
                    $(this).find("input,select").each(function () {
                        let indexId = $(this).attr("id");
                        let index = indexId.split("-");
                        $(this).attr("id",index[0]+"-"+indexrow+"-"+index[2]);
                    });
                    indexrow++;
                });
            }else {
                var len=$("#Update_Grid_Field2 tbody tr").length;
                $("#Update_Grid_Field2 tbody").append(UpdateCreateTableRow(len+1,"two"));
                $("#Update_Grid_Field2 tbody tr:eq(" + len + ")").find('input,select').hide();
            }
            RowIndex2++;
        }else if (3==UpdateTabTable){
            var trobj = $("#Update_Grid_Field3 tbody .selected");
            if (trobj.length) {
                trobj.before(UpdateCreateTableRow(RowIndex3,"three"));
                var selectedindex = (trobj.find('td:eq(0)').html()) - 1;
                $("#Update_Grid_Field3 tbody tr:eq(" + selectedindex + ")").find('input,select').hide();
                //更新表格序列号
                var indexrow = 1;
                $("#Update_Grid_Field3 tbody tr").each(function () {
                    $(this).find('td:eq(0)').html(indexrow);
                    indexrow++;
                    $(this).find("input,select").each(function () {
                        let indexId = $(this).attr("id");
                        let index = indexId.split("-");
                        $(this).attr("id",index[0]+"-"+indexrow+"-"+index[2]);
                    });
                });
            }else {
                var len = $("#Update_Grid_Field3 tbody tr").length;
                $("#Update_Grid_Field3 tbody").append(UpdateCreateTableRow(len+1,"three"));
                $("#Update_Grid_Field3 tbody tr:eq(" + len + ")").find('input,select').hide();
            }
            RowIndex3++;
        }
    }

    //清空表格当前行
    function UpdateEmptyTableRow() {
        if (1==UpdateTabTable) {
            var trobj = $("#Update_Grid_Field1 tbody .selected");
            if (trobj != null && trobj != undefined && trobj.length > 0) {
                trobj.remove();
                var indexrow = 1;
                $("#Update_Grid_Field1 tbody tr").each(function () {
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
        }else if (2==UpdateTabTable){
            var trobj = $("#Update_Grid_Field2 tbody .selected");
            if (trobj != null && trobj != undefined && trobj.length > 0) {
                trobj.remove();
                var indexrow = 1;
                $("#Update_Grid_Field2 tbody tr").each(function () {
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
        }else if (3==UpdateTabTable){
            var trobj = $("#Update_Grid_Field3 tbody .selected");
            if (trobj != null && trobj != undefined && trobj.length > 0) {
                trobj.remove();
                var indexrow = 1;
                $("#Update_Grid_Field3 tbody tr").each(function () {
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
    function UpdateGridup() {
        if (1==UpdateTabTable) {
            var objParentTR = $("#Update_Grid_Field1 tbody .selected");
            var prevTR = objParentTR.prev();
            if (prevTR.length > 0) {
                prevTR.insertAfter(objParentTR);
                //更新表格序列号
                var indexrow = 1;
                $("#Update_Grid_Field1 tbody tr").each(function () {
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
        }else if (2==UpdateTabTable){
            var objParentTR = $("#Update_Grid_Field2 tbody .selected");
            var prevTR = objParentTR.prev();
            if (prevTR.length > 0) {
                prevTR.insertAfter(objParentTR);
                //更新表格序列号
                var indexrow = 1;
                $("#Update_Grid_Field2 tbody tr").each(function () {
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
        }else if (3==UpdateTabTable){
            var objParentTR = $("#Update_Grid_Field3 tbody .selected");
            var prevTR = objParentTR.prev();
            if (prevTR.length > 0) {
                prevTR.insertAfter(objParentTR);
                //更新表格序列号
                var indexrow = 1;
                $("#Update_Grid_Field3 tbody tr").each(function () {
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
    function UpdateGriddown(obj) {
        if (1==UpdateTabTable) {
            var objParentTR = $("#Update_Grid_Field1 tbody .selected");
            var nextTR = objParentTR.next();
            if (nextTR.length > 0) {
                nextTR.insertBefore(objParentTR);
                //更新表格序列号
                var indexrow = 1;
                $("#Update_Grid_Field1 tbody tr").each(function () {
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
        }else if (2==UpdateTabTable){
            var objParentTR = $("#Update_Grid_Field2 tbody .selected");
            var nextTR = objParentTR.next();
            if (nextTR.length > 0) {
                nextTR.insertBefore(objParentTR);
                //更新表格序列号
                var indexrow = 1;
                $("#Update_Grid_Field2 tbody tr").each(function () {
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
        }else if (3==UpdateTabTable){
            var objParentTR = $("#Update_Grid_Field3 tbody .selected");
            var nextTR = objParentTR.next();
            if (nextTR.length > 0) {
                nextTR.insertBefore(objParentTR);
                //更新表格序列号
                var indexrow = 1;
                $("#Update_Grid_Field3 tbody tr").each(function () {
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
    function UpdateTableTdEvent() {
        $("#UpdateTableFieldDiv .grid").on("click",".td-div",function(){
            $(this).parent().parent().find(".selected").each(function () {
                $(this).removeClass("selected");
            });
            $(this).parent().addClass("selected");
        });

        $("#UpdateTableFieldDiv div tbody").on("change",".dataSourceTypeSelect",function(){
            var thi = $(this);
            var html = thi.find("option:selected").text();
            var val = thi.val();
            var id = thi.attr("id");
            var index = id.split("-");
            if(0==val){
                $("#updateDataSource-"+index[1]+"-"+index[2]).removeClass("icontree");
                $("#updateDataSource-"+index[1]+"-"+index[2]).prop("disabled",true);
                $("#updateDataSourceCode-"+index[1]+"-"+index[2]).val("");
                $("#updateDataSource-"+index[1]+"-"+index[2]).attr("value","");
                $("#updateDataSource-"+index[1]+"-"+index[2]).parent().find('div').html("");
            }else if(1==val){
                $("#updateDataSource-"+index[1]+"-"+index[2]).addClass("icontree");
                $("#updateDataSource-"+index[1]+"-"+index[2]).prop("disabled",false);
            }else if(2==val){
                $("#updateDataSource-"+index[1]+"-"+index[2]).removeClass("icontree");
                $("#updateDataSource-"+index[1]+"-"+index[2]).prop("disabled",true);
                $("#updateDataSourceCode-"+index[1]+"-"+index[2]).val("");
                $("#updateDataSource-"+index[1]+"-"+index[2]).attr("value","");
                $("#updateDataSource-"+index[1]+"-"+index[2]).parent().find('div').html("");
            }
        });
        $("#UpdateTableFieldDiv div tbody").on("focus",".dataSource",function(){
            var objId = this.id;
            var index = objId.split("-");
            var type = $("#updateDataSourceType-"+index[1]+"-"+index[2]).val();
            if (1==type) {
                comboBoxTree(objId, "182px");
                var itemtree = {
                    onnodeclick: function (item) {
                        $("#updateDataSourceCode-" + index[1] + "-" + index[2]).val(item.code);
                        $("#updateDataSource-" + index[1] + "-" + index[2]).attr("value", item.text);
                        $("#updateDataSource-" + index[1] + "-" + index[2]).html(item.text);
                        $("#updateDataSource-" + index[1] + "-" + index[2]).parent().find('div').html(item.text);
                    },
                    url: "${request.contextPath}/dataDictionary/TreeJson.json"
                };
                $("#comboBoxTree" + objId).treeview(itemtree);
                $("#comboBoxTree" + objId + "_" + GetQuery('KeyValue').replace(/-/g, '_')).parent('li').remove();
            }
        });
        $("#UpdateTableFieldDiv div tbody").on("click","td",function(){
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
        $("#UpdateTableFieldDiv div tbody").on("change","[name='updateControlType']",function(){
            if($(this).find("option:selected").text()=="下拉框"){
                $(this).parents("tr").find(".dataSourceTypeSelect").prop("disabled",false);
            }else{
                $(this).parents("tr").find(".dataSourceTypeSelect").prop("disabled",true);
                $(this).parents("tr").find(".dataSourceTypeSelect").prev("div").text("固定");
            }
        });
        $("#UpdateTableFieldDiv div tbody").on("click",".click-show",function(){
            if($(this).find("option:selected").text()!=="下拉框"){
                //$(this).parents("tr").find(".dataSourceTypeSelect").prev("div").attr("text","固定");
                $(this).parents("tr").find(".dataSourceTypeSelect").prev("div").text("固定");
                $(this).parents("tr").find("td:last").find("div").text("");
            }
        });
    }

    //创建行
    function UpdateCreateTableRow(index,type) {
        var tr = '';
        tr += '<tr>';
        tr += '<td class="td-div" style="width: 30px; text-align: center;border-left: none;">' + index + '</td>';
        tr += '<td style="width: 50px; text-align: center;"><div></div><input autocomplete="off" id="updatePropertyName-' + index + "-"+type+'" type="text" maxlength="10"  class="txt" name ="updatePropertyName" /></td>';
        tr += '<td class="click-show" style="width: 60px; text-align: center;"><div></div>' + UpdateControlType(index,type) + '</td>';
        tr += '<td style="width: 60px; text-align: center;"><div></div>' + UpdateRestrictive(index,type) + '</td>';
        tr += '<td style="width: 60px; text-align: center;"><div></div>' + UpdateDataSourceType(index,type) + '</td>';
        tr += '<td style="width: 50px; text-align: center;"><div></div>' + UpdateDataSource(index,type) + '</td>';
        tr += '</tr>';
        return tr;
    }

    //类型
    function UpdateControlType(index,type) {
        var html = '<select id="updateControlType-' + index + "-" + type + '" class="txtselect" name ="updateControlType"  datacol="no" type="select" err="类型" checkexpession="NotNull">';
        html += '<option value="1">文本框</option>';
        html += '<option value="2">下拉框</option>';
        html += '<option value="3">日期框</option>';
        html += '<option value="5">多行文本</option>';
        html += '</select>';
        return html;
    }
    //限制条件
    function UpdateRestrictive(index,type){
        var html = '<select id="updateRestrictiveConditions-' + index + "-" + type + '" class="txtselect" name ="updateRestrictiveConditions"  datacol="no" type="select" err="限制条件" checkexpession="NotNull">';
        html += '<option value="1">不能为空</option>';
        html += '<option value="2">只能输入数字</option>';
        html += '<option value="3">数字、字母</option>';
        html += '<option value="4">无限制</option>';
        html += '<option value="5">默认</option>';
        html += '</select>';
        return html;
    }
    //数据源类型
    function UpdateDataSourceType(index,type){
        var html = '<select id="updateDataSourceType-' + index + "-" + type + '" class="txtselect dataSourceTypeSelect" name ="updateDataSourceType"  datacol="no" type="select" err="数据源类型" checkexpession="NotNull" disabled>';
        html += '<option value="0">固定</option>';
        html += '<option value="1">数据字典</option>';
        html += '<option value="2">PDA用户</option>';
        html += '</select>';
        return html;
    }
    //数据源
    function UpdateDataSource(index,type){
        var html = '<input id="updateDataSourceCode-' + index + "-" + type + '" class="dataSourceCode" type="hidden" name="updateDataSourceCode" autocomplete="off"  />';
        html += '<input id="updateDataSource-' + index + "-" + type + '" type="text" class="txt dataSource" name="updateDataSource" disabled autocomplete="off"  />';
        return html;
    }

</script>










