
<#--查看产品类型详情-->
<form style="margin: 1px">
    <table class="form">
        <tr>
            <th class="formTitle">第一层：
            </th>
            <td class="formValue">
                <input type="hidden" value="<#if first??>${first.productTypeId}<#else >0</#if>"/>
                <input type="text" class="txt" value="<#if first??>${first.CName}<#else >无</#if>" readonly disabled/>
            </td>
            <th class="formTitle">上级模块：
            </th>
            <td class="formValue">
                <input type="hidden" value="<#if parent??>${parent.productTypeId}<#else >0</#if>"/>
                <input type="text" class="txt" value="<#if parent??>${parent.CName}<#else >无上级</#if>" readonly disabled/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">级别层次：</th>
            <td class="formValue">
                <input type="text" class="txt" value="${sysProductType.ALevel}" readonly disabled/>
                <input type="hidden" value="${sysProductType.ALevel}"/>
            </td>
            <th class="formTitle">显示顺序：</th>
            <td class="formValue">
                <input type="text" class="txt" value="${sysProductType.listIndex}" readonly disabled/>
                <input type="hidden" value="${sysProductType.listIndex}" />
            </td>
        </tr>
        <tr>
            <th class="formTitle">类型名称：
            </th>
            <td class="formValue" colspan="3">
                <input type="text" class="txt required" placeholder="${sysProductType.CName}" value="${sysProductType.CName}" readonly/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">说明：
            </th>
            <td class="formValue" colspan="3">
                <textarea class="txtArea" style="resize: none" maxlength="200" rows="5" placeholder="${sysProductType.memo}" readonly>${sysProductType.memo}</textarea>
            </td>
        </tr>
    </table>
</form>