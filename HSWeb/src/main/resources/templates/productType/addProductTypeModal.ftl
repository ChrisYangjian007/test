
<#--添加产品类型-->
<form id="addProductTypeForm" style="margin: 1px">
    <table class="form">
        <tr>
            <th class="formTitle">第一层：
            </th>
            <td class="formValue">
                <input type="hidden" name="resourceId" <#if resourceId??>value="${resourceId}"</#if> />
                <input id="firstId" name="firstId" type="hidden" value="<#if sysProductType??>1<#else >0</#if>"/>
                <input id="firstName" name="firstName" type="text" class="txt" value="<#if sysProductType??>1<#else >0</#if>" readonly disabled/>
            </td>
            <th class="formTitle">上级模块：
            </th>
            <td class="formValue">
                <input id="parentId" name="parentId" type="hidden" value="<#if sysProductType??>${sysProductType.productTypeId}<#else >0</#if>"/>
                <input id="parentName" name="parentName" type="text" class="txt" value="<#if sysProductType??>${sysProductType.CName}<#else >无上级</#if>" readonly disabled/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">级别层次：</th>
            <td class="formValue">
                <input id="aLevel"  type="text" class="txt" value="${aLevel}" readonly disabled/>
                <input name="aLevel" type="hidden" value="${aLevel}"/>
            </td>
            <th class="formTitle">显示顺序：</th>
            <td class="formValue">
                <input id="listIndex" type="text" class="txt" value="${listIndex}" readonly disabled/>
                <input name="listIndex" type="hidden" value="${listIndex}" />
            </td>
        </tr>
        <tr>
            <th class="formTitle">类型名称：
            </th>
            <td class="formValue" colspan="3">
                <input id="cName" name="cName" type="text" class="txt required" />
            </td>
        </tr>
        <tr>
            <th class="formTitle">说明：
            </th>
            <td class="formValue" colspan="3">
                <textarea class="txtArea" style="resize: none" id="memo" name="memo" maxlength="200" rows="5"></textarea>
            </td>
        </tr>
    </table>
</form>