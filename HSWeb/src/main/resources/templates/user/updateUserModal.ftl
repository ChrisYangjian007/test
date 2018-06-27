
<#--编辑用户-->
<form id="updateUserForm" style="margin: 1px">
    <input type="hidden" name="userId" value="${baUser.userId}">
    <table class="form">
        <tr>
            <th class="formTitle">工号：
            </th>
            <td class="formValue">
                <input id="updCode" name="code" type="text" class="txt required" maxlength="10"
                       placeholder="${baUser.code}" value="${baUser.code}"
                       onkeyup="this.value=this.value.replace(/\s/g,'')"
                       onblur="this.value=this.value.replace(/\s/g,'')"/>
            </td>
            <th class="formTitle">姓名：
            </th>
            <td class="formValue">
                <input id="updCName" name="cName" type="text" maxlength="10"
                       placeholder="<#if baUser.CName??>${baUser.CName}<#else ></#if>"
                       value="<#if baUser.CName??>${baUser.CName}<#else >无</#if>"/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">所在部门：
            </th>
            <td class="formValue">
                <input id="updDepartmentName" name="departmentName" type="text" placeholder="<#if baUser.departmentName??>${baUser.departmentName}<#else ></#if>" value="<#if baUser.departmentName??>${baUser.departmentName}<#else >待分配</#if>"/>
            </td>
            <th class="formTitle">用户状态：</th>
            <td class="formValue">
                <select id="updStatus" class="txtselect" name="status">
                    <option value="1" <#if baUser.status==1>selected</#if>>正常</option>
                    <option value="2" <#if baUser.status==2>selected</#if>>禁用</option>
                </select>
            </td>
        </tr>
        <tr>
            <th class="formTitle">用户类型：</th>
            <td class="formValue" colspan="3">
                <select id="updUserType" class="txtselect" name="userType">
                    <option value="1" <#if baUser.userType==1>selected</#if>>PC用户</option>
                    <option value="2" <#if baUser.userType==2>selected</#if>>手机用户</option>
                    <option value="3" <#if baUser.userType==3>selected</#if>>两端用户</option>
                </select>
            </td>
        </tr>
        <tr id="updTr1">
            <th class="formTitle">所属角色(PC)：
            </th>
            <td class="formValue" colspan="3">
                <select id="updRoleId1" class="txtselect" name="roleId1">
                <#if pcRolesList?? && pcRolesList?size!=0>
                    <#list pcRolesList as roles1>
                        <option value="${roles1.roleId}" <#if baUser.rolePCId==roles1.roleId>selected</#if>>${roles1.CName}</option>
                    </#list>
                <#else >
                    <option value="">==无数据==</option>
                </#if>
                </select>
            </td>
        </tr>
        <tr id="updTr2">
            <th class="formTitle">所属角色(手机)：
            </th>
            <td class="formValue" colspan="3">
                <select id="updRoleId2" class="txtselect" name="roleId2">
                <#if phoneRoleList?? && phoneRoleList?size!=0>
                    <#list phoneRoleList as roles2>
                        <option value="${roles2.roleId}" <#if baUser.rolePhoneId==roles2.roleId>selected</#if>>${roles2.CName}</option>
                    </#list>
                <#else >
                    <option value="">==无数据==</option>
                </#if>
                </select>
            </td>
        </tr>
        <tr id="updTr3">
            <th class="formTitle">所属角色(PC)：
            </th>
            <td class="formValue">
                <select id="updRoleId3" class="txtselect" name="roleId3">
                <#if pcRolesList?? && pcRolesList?size!=0>
                    <#list pcRolesList as roles3>
                        <option value="${roles3.roleId}" <#if baUser.rolePCId==roles3.roleId>selected</#if>>${roles3.CName}</option>
                    </#list>
                <#else >
                    <option value="">==无数据==</option>
                </#if>
                </select>
            </td>
            <th class="formTitle">所属角色(手机)：
            </th>
            <td class="formValue">
                <select id="updRoleId4" class="txtselect" name="roleId4">
                <#if phoneRoleList?? && phoneRoleList?size!=0>
                    <#list phoneRoleList as roles4>
                        <option value="${roles4.roleId}" <#if baUser.rolePhoneId==roles4.roleId>selected</#if>>${roles4.CName}</option>
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


    var tr1 = $("#updTr1");
    var tr2 = $("#updTr2");
    var tr3 = $("#updTr3");
    $(document).ready(function () {
        var type = ${baUser.userType};
         if(1==type){
             tr1.fadeIn(0);
             tr2.fadeOut(0);
             tr3.fadeOut(0);
         }else if(2==type){
             tr1.fadeOut(0);
             tr2.fadeIn(0);
             tr3.fadeOut(0);
         }else if(3==type){
             tr1.fadeOut(0);
             tr2.fadeOut(0);
             tr3.fadeIn(0);
         }else {
             tr1.fadeOut(0);
             tr2.fadeOut(0);
             tr3.fadeOut(0);
         }
    });
    $("#updUserType").on("change",function(){
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