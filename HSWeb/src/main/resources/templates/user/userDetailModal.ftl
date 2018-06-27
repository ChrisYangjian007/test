

<#--编辑用户-->
<form id="userFormDetail" style="margin: 1px">
    <table class="form">
        <tr>
            <th class="formTitle">工号：
            </th>
            <td class="formValue">
                <input type="text" class="txt" maxlength="10" value="<#if baUser.code??>${baUser.code}<#else >无</#if>" readonly/>
            </td>
            <th class="formTitle">姓名：
            </th>
            <td class="formValue">
                <input type="text" value="<#if baUser.CName??>${baUser.CName}<#else >无</#if>" readonly/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">所在部门：
            </th>
            <td class="formValue">
                <input type="text" value="<#if baUser.departmentName??>${baUser.departmentName}<#else >待分配</#if>" readonly/>
            </td>
            <th class="formTitle">用户状态：</th>
            <td class="formValue">
                <input type="text" value="<#if baUser.status==1>正常<#elseif baUser.status==2>禁用</#if>" readonly/>
            </td>

        </tr>
        <tr>
            <th class="formTitle">用户类型：</th>
            <td class="formValue">
                <input type="text" value="<#if baUser.userType??><#if 1==baUser.userType>PC用户<#elseif 2==baUser.userType>PDA用户<#else >两端用户</#if><#else >无</#if>" readonly/>
            </td>
            <th class="formTitle">所属角色：</th>
            <td class="formValue">
                <input type="text" value="<#if baUser.roleName??>${baUser.roleName}<#else >无</#if>" readonly/>
            </td>
        </tr>
    </table>
</form>