
<#--详细-->
<form style="margin: 1px">
    <table class="form">
        <tr>
            <th class="formTitle">主要负责人：
            </th>
            <td class="formValue" colspan="3">
                <input type="text" class="txt" value="<#if zsWarehouse.warehouseUserName??>${zsWarehouse.warehouseUserName}</#if>" readonly disabled/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">仓库名称：
            </th>
            <td class="formValue" colspan="3">
                <input name="cName" type="text" class="txt required"  value="${zsWarehouse.CName}" readonly/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">预警天数：
            </th>
            <td class="formValue">
                <input type="text" class="txt required" value="${zsWarehouse.warningDay}" readonly/>
            </td>
            <td class="formValue" colspan="2">&nbsp;时间单位默认天
            </td>
        </tr>
        <tr>
            <th class="formTitle">描述：
            </th>
            <td class="formValue" colspan="3">
                <textarea class="txtArea" style="resize: none" name="remark" maxlength="200" rows="5"  readonly>${zsWarehouse.remark}</textarea>
            </td>
        </tr>
    </table>
</form>
