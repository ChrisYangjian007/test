
<#--角色详情-->
<form style="margin: 1px">
    <input type="hidden" placeholder="${baRoles.roleId}" value="${baRoles.roleId}" readonly>
    <table class="form">
        <tr>
            <th class="formTitle">角色编码：
            </th>
            <td class="formValue" colspan="3">
                <input type="text" class="txt required" placeholder="${baRoles.code}" value="${baRoles.code}" readonly />
            </td>
        </tr>
        <tr>
            <th class="formTitle">角色名称：
            </th>
            <td class="formValue" colspan="3">
                <input type="text" class="txt required" placeholder="${baRoles.CName}" value="${baRoles.CName}" readonly />
            </td>
        </tr>
        <tr>
            <th class="formTitle">角色分类：
            </th>
            <td class="formValue" colspan="3">
                <select class="txtselect">
                    <option <#if 0==baRoles.category>selected</#if>>PC</option>
                    <option <#if 1==baRoles.category>selected</#if>>手持PDA</option>
                </select>
            </td>
        </tr>
        <tr>
            <th class="formTitle">显示顺序：</th>
            <td class="formValue" colspan="3">
                <input type="text" class="txt" value="${baRoles.listIndex}" readonly disabled/>
                <input type="hidden" value="${baRoles.listIndex}" readonly />
            </td>
        </tr>
        <tr>
            <th class="formTitle">说明：
            </th>
            <td class="formValue" colspan="3">
                <textarea class="txtArea" style="resize: none" maxlength="200" rows="5" readonly>${baRoles.remark}</textarea>
            </td>
        </tr>
    </table>
</form>