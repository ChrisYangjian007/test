<#if workFlow??>
<form id="addWorkFlowForm" style="margin: 1px">
    <table class="form">
        <tr>
            <th class="formTitle">工艺名称：
            </th>
            <td class="formValue">
                <input type="hidden" name="resourceId" <#if resourceId??>value="${resourceId}"</#if> />
                <input id="cName" name="cName" type="text" class="txt required" maxlength="10"/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">上级模块：
            </th>
            <td class="formValue">
                <input type="hidden" name="parentId" value="${workFlow.workFlowId}">
                <input id="parentName" name="parentName" type="text" class="txt" value="${workFlow.CName}" disabled/>
            </td>
            <th class="formTitle">级别层次：</th>
            <td class="formValue">
                <input type="hidden" name="aLevel" value="${workFlow.ALevel+1}">
                <input id="aLevel" type="text" class="txt" value="${workFlow.ALevel+1}" disabled/>
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
            <input type="text" class="txt required" value="请重试，再不行请联系管理员" disabled />
        </td>
    </tr>
</table>
</#if>