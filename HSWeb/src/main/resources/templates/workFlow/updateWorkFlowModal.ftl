<#if workFlow??>
<form id="updateWorkFlowForm" style="margin: 1px">
    <table class="form">
        <tr>
            <th class="formTitle">工艺名称：
            </th>
            <td class="formValue">
                <input type="hidden" name="resourceId" <#if resourceId??>value="${resourceId}"</#if> />
                <input type="hidden" name="workFlowId" value="${workFlow.workFlowId}">
                <input id="updCName" name="cName" type="text" class="txt required" maxlength="10" value="${workFlow.CName}"/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">上级模块：
            </th>
            <td class="formValue">
                <input type="text" class="txt" disabled value="${workFlow.parentName}"/>
            </td>
            <th class="formTitle">级别层次：</th>
            <td class="formValue">
                <input type="text" class="txt" disabled value="${workFlow.ALevel}"/>
            </td>
        </tr>
    </table>
</form>
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










