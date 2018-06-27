
<#--修改角色信息-->
<form id="updateRolesForm" style="margin: 1px">
<#--<div id="message" style="display: none"></div>-->
    <input type="hidden" name="roleId" placeholder="${baRoles.roleId}" value="${baRoles.roleId}">
    <table class="form">
        <tr>
            <th class="formTitle">角色编码：
            </th>
            <td class="formValue" colspan="3">
                <input id="updCode" name="code" type="text" class="txt required" maxlength="10" placeholder="${baRoles.code}" value="${baRoles.code}" />
            </td>
        </tr>
        <tr>
            <th class="formTitle">角色名称：
            </th>
            <td class="formValue" colspan="3">
                <input id="updCName" name="cName" type="text" class="txt required" maxlength="10" placeholder="${baRoles.CName}" value="${baRoles.CName}" />
            </td>
        </tr>
        <tr>
            <th class="formTitle">角色分类：
            </th>
            <td class="formValue" colspan="3">
                <select id="category" class="txtselect" name="category" >
                    <option value="0" <#if 0==baRoles.category>selected</#if>>PC</option>
                    <option value="1" <#if 1==baRoles.category>selected</#if>>手持PDA</option>
                </select>
            </td>
        </tr>
        <tr>
            <th class="formTitle">显示顺序：</th>
            <td class="formValue" colspan="3">
                <input id="listIndex" type="text" class="txt" placeholder="${baRoles.listIndex}" value="${baRoles.listIndex}" disabled/>
                <input name="listIndex" type="hidden" placeholder="${baRoles.listIndex}" value="${baRoles.listIndex}" />
            </td>
        </tr>
        <tr>
            <th class="formTitle">说明：
            </th>
            <td class="formValue" colspan="3">
                <textarea class="txtArea" style="resize: none" id="remark" name="remark" maxlength="200" rows="5" placeholder="${baRoles.remark}">${baRoles.remark}</textarea>
            </td>
        </tr>
    </table>
</form>