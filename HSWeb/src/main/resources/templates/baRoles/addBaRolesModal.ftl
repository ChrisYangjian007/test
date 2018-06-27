
<#--添加角色-->
<form id="addRolesForm" style="margin: 1px">
    <input type="hidden" name="companyId" value="${baCompany.companyId}">
    <table class="form">
        <tr>
            <th class="formTitle">角色编码：
            </th>
            <td class="formValue" colspan="3">
                <input id="code" name="code" type="text" class="txt required" maxlength="10"/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">角色名称：
            </th>
            <td class="formValue" colspan="3">
                <input id="cName" name="cName" type="text" class="txt required" maxlength="10"/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">角色分类：
            </th>
            <td class="formValue" colspan="3">
                <select id="category" class="txtselect" name="category" >
                    <option value="">==请选择==</option>
                    <option value="0">PC</option>
                    <option value="1">手持PDA</option>
                </select>
            </td>
        </tr>
        <tr>
            <th class="formTitle">显示顺序：</th>
            <td class="formValue" colspan="3">
                <input id="listIndex" type="text" class="txt" value="${listIndex}" disabled/>
                <input name="listIndex" type="hidden" value="${listIndex}" />
            </td>
        </tr>
        <tr>
            <th class="formTitle">描述：
            </th>
            <td class="formValue" colspan="3">
                <textarea class="txtArea" style="resize: none" id="remark" name="remark" maxlength="200" rows="5"></textarea>
            </td>
        </tr>
    </table>
</form>