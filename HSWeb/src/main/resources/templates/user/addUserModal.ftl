
<#--添加用户-->
<form id="addUserForm" style="margin: 1px">
    <table class="form">
        <tr>
            <th class="formTitle">工号：
            </th>
            <td class="formValue">
                <input id="code" name="code" type="text" class="txt required" maxlength="10"
                       onkeyup="this.value=this.value.replace(/\s/g,'')"
                       onblur="this.value=this.value.replace(/\s/g,'')"/>
            </td>
            <th class="formTitle">所在部门：
            </th>
            <td class="formValue">
                <input id="departmentName" name="departmentName" type="text"/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">登录账号：
            </th>
            <td class="formValue">
                <input id="account" name="account" type="text" class="txt required"
                       maxlength="10"
                       onkeyup="this.value=this.value.replace(/\s/g,'')"
                       onblur="this.value=this.value.replace(/\s/g,'')"/>
            </td>
            <th class="formTitle">姓名：
            </th>
            <td class="formValue">
                <input id="cName" name="cName" type="text" maxlength="10"/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">登录密码：
            </th>
            <td class="formValue">
                <input id="password" name="password" type="password" class="txt required"
                       placeholder="6～10位数字或字母" maxlength="10"
                       onkeyup="this.value=this.value.replace(/\s/g,'')"
                       onblur="this.value=this.value.replace(/\s/g,'')"/>
            </td>
            <th class="formTitle">确认密码：
            </th>
            <td class="formValue">
                <input id="confirmPassword" name="confirmPassword" type="password" class="txt required"
                       placeholder="6～10位数字或字母" maxlength="10"
                       onkeyup="this.value=this.value.replace(/\s/g,'')"
                       onblur="this.value=this.value.replace(/\s/g,'')"/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">用户状态：</th>
            <td class="formValue">
                <select id="status" class="txtselect" name="status">
                    <option value="1">正常</option>
                    <option value="2">禁用</option>
                </select>
            </td>
            <th class="formTitle">用户类型：</th>
            <td class="formValue">
                <select id="userType" class="txtselect" name="userType">
                    <option value="1" selected>PC用户</option>
                    <option value="2">手机用户</option>
                    <option value="3">两端用户</option>
                </select>
            </td>
        </tr>
        <tr id="tr1">
            <th class="formTitle">所属角色(PC)：
            </th>
            <td class="formValue" colspan="3">
                <select id="roleId1" class="txtselect" name="roleId1">
                <#if pcRolesList?? && pcRolesList?size!=0>
                    <option value="">==请选择==</option>
                    <#list pcRolesList as roles1>
                        <option value="${roles1.roleId}">${roles1.CName}</option>
                    </#list>
                <#else >
                    <option value="">==无数据==</option>
                </#if>
                </select>
            </td>
        </tr>
        <tr id="tr2">
            <th class="formTitle">所属角色(手机)：
            </th>
            <td class="formValue" colspan="3">
                <select id="roleId2" class="txtselect" name="roleId2">
                <#if phoneRoleList?? && phoneRoleList?size!=0>
                    <option value="">==请选择==</option>
                    <#list phoneRoleList as roles2>
                        <option value="${roles2.roleId}">${roles2.CName}</option>
                    </#list>
                <#else >
                    <option value="">==无数据==</option>
                </#if>
                </select>
            </td>
        </tr>
        <tr id="tr3">
            <th class="formTitle">所属角色(PC)：
            </th>
            <td class="formValue">
                <select id="roleId3" class="txtselect" name="roleId3">
                <#if pcRolesList?? && pcRolesList?size!=0>
                    <option value="">==请选择==</option>
                    <#list pcRolesList as roles3>
                        <option value="${roles3.roleId}">${roles3.CName}</option>
                    </#list>
                <#else >
                    <option value="">==无数据==</option>
                </#if>
                </select>
            </td>
            <th class="formTitle">所属角色(手机)：
            </th>
            <td class="formValue">
                <select id="roleId4" class="txtselect" name="roleId4">
                <#if phoneRoleList?? && phoneRoleList?size!=0>
                    <option value="">==请选择==</option>
                    <#list phoneRoleList as roles4>
                        <option value="${roles4.roleId}">${roles4.CName}</option>
                    </#list>
                <#else >
                    <option value="">==无数据==</option>
                </#if>
                </select>
            </td>
        </tr>
    </table>
</form>

<script>


    var tr1 = $("#tr1");
    var tr2 = $("#tr2");
    var tr3 = $("#tr3");
    $(document).ready(function () {
        tr1.fadeIn(0);
        tr2.fadeOut(0);
        tr3.fadeOut(0);
    });

    $("#userType").on("change",function(){
        var userType = $(this);
        var vals = userType.val();
        if(vals==1){
            tr1.fadeIn(0);
            tr2.fadeOut(0);
            tr3.fadeOut(0);
        }else if(vals==2){
            tr1.fadeOut(0);
            tr2.fadeIn(0);
            tr3.fadeOut(0);
        }else if(vals==3){
            tr1.fadeOut(0);
            tr2.fadeOut(0);
            tr3.fadeIn(0);
        }else {
            tr1.fadeOut(0);
            tr2.fadeOut(0);
            tr3.fadeOut(0);
        }
    });

</script>