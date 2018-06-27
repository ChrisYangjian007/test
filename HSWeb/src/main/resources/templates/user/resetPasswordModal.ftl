

<#--重置密码-->
<form id="resetPasswordForm" style="margin: 1px">
    <input type="hidden" name="userId" value="${baUser.userId}">
    <table class="form">
        <tr>
            <th class="formTitle">登录名：
            </th>
            <td class="formValue">
                <input id="resetAccount" name="account" type="text" class="txt required"
                       maxlength="10" value="${baUser.account}" readonly/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">新密码：
            </th>
            <td class="formValue">
                <input id="newPassword" name="password" type="password" class="txt required"
                       placeholder="6～10位数字或字母" maxlength="10"
                       onkeyup="this.value=this.value.replace(/\s/g,'')"
                       onblur="this.value=this.value.replace(/\s/g,'')"/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">确认密码：
            </th>
            <td class="formValue">
                <input id="confirmNewPassword" name="confirmNewPassword" type="password" class="txt required"
                       placeholder="6～10位数字或字母" maxlength="10"
                       onkeyup="this.value=this.value.replace(/\s/g,'')"
                       onblur="this.value=this.value.replace(/\s/g,'')"/>
            </td>
        </tr>
    </table>
</form>