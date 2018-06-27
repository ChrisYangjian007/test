

<#--修改单位-->
<form id="updateUnitForm" style="margin: 1px">
    <input type="hidden" name="resourceId" <#if resourceId??>value="${resourceId}"</#if> />
    <input type="hidden" name="unitId" value="${sysUnit.unitId}">
    <table class="form">
        <tr>
            <th class="formTitle">单位名称：
            </th>
            <td class="formValue" colspan="3">
                <input id="updCName" name="cName" type="text" class="txt required" placeholder="${sysUnit.CName}" value="${sysUnit.CName}"/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">换算值：
            </th>
            <td class="formValue" colspan="3">
                <input id="updValue" name="value" type="text" class="txt required" placeholder="${sysUnit.value}" value="${sysUnit.value}"/>
            </td>
            </tr>
        <tr>
            <th class="formTitle">说明：
            </th>
            <td class="formValue" colspan="3">
                <textarea class="txtArea" style="resize: none" id="updRemark" name="remark" maxlength="200" rows="5" placeholder="${sysUnit.remark}">${sysUnit.remark}</textarea>
            </td>
        </tr>
    </table>
</form>
