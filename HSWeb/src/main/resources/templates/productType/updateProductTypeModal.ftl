
<#--修改产品类型-->
<form id="updateProductTypeForm" style="margin: 1px">
    <input type="hidden" name="resourceId" <#if resourceId??>value="${resourceId}"</#if> />
    <input id="updId" name="productTypeId" type="hidden" value="${sysProductType.productTypeId}"/>
    <table class="form">
        <tr>
            <th class="formTitle">第一层：
            </th>
            <td class="formValue">
                <input id="updFirstId" name="firstId" type="hidden" value="<#if first??>${first.productTypeId}<#else >0</#if>"/>
                <input id="updFirstName" name="firstName" type="text" class="txt" value="<#if first??>${first.CName}<#else >无</#if>" readonly disabled/>
            </td>
            <th class="formTitle">上级模块：
            </th>
            <td class="formValue">
                <input id="updParentId" name="parentId" type="hidden" value="<#if parent??>${parent.productTypeId}<#else >0</#if>"/>
                <input id="updParentName" name="parentName" type="text" class="txt" value="<#if parent??>${parent.CName}<#else >无上级</#if>" readonly disabled/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">级别层次：</th>
            <td class="formValue">
                <input id="updALevel"  type="text" class="txt" value="${sysProductType.ALevel}" readonly disabled/>
                <input name="updALevel" type="hidden" value="${sysProductType.ALevel}"/>
            </td>
            <th class="formTitle">显示顺序：</th>
            <td class="formValue">
                <input id="listIndex" type="text" class="txt" value="${sysProductType.listIndex}" readonly disabled/>
                <input name="listIndex" type="hidden" value="${sysProductType.listIndex}" />
            </td>
        </tr>
        <tr>
            <th class="formTitle">类型名称：
            </th>
            <td class="formValue" colspan="3">
                <input id="updCName" name="cName" type="text" class="txt required" placeholder="${sysProductType.CName}" value="${sysProductType.CName}"/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">说明：
            </th>
            <td class="formValue" colspan="3">
                <textarea class="txtArea" style="resize: none" id="memo" name="memo" maxlength="200" rows="5" placeholder="${sysProductType.memo}">${sysProductType.memo}</textarea>
            </td>
        </tr>
    </table>
</form>