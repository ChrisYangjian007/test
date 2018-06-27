
<#--新增-->
<form id="addWarehouseForm" style="margin: 1px">
    <table class="form">
        <tr>
            <th class="formTitle">主要负责人：
            </th>
            <td class="formValue" colspan="3">
                <input type="hidden" name="resourceId" <#if resourceId??>value="${resourceId}"</#if> />
                <select id="warehouseUserId" name="warehouseUserId" class="txtselect  selectOption" multiple>
                    <#--<option value="">==请选择==</option>-->
                    <#if baUserList??&&baUserList?size!=0>
                        <#list baUserList as user >
                                <option value="${user.userId}">${user.CName}</option>
                        </#list>
                    <#else>
                        <option value="">暂无用户</option>
                    </#if>
                </select>
            </td>
        </tr>
        <tr>
            <th class="formTitle">仓库名称：
            </th>
            <td class="formValue">
                <input id="cName" name="cName" type="text" class="txt required" />
            </td>
            <th class="formTitle">仓库类型：
            </th>
            <td class="formValue">
                <select id="warehouseType" name="warehouseType" class="txtselect">
                    <option value="0">无</option>
                    <option value="1">活参库</option>
                </select>
            </td>
        </tr>
        <tr>
            <th class="formTitle">预警天数：
            </th>
            <td class="formValue">
                <input id="warningDay" name="warningDay" type="text" class="txt required"
                       maxlength="4" onkeyup="this.value=this.value.replace(/\D/g,'')"
                       onblur="this.value=this.value.replace(/\D/g,'')"/>
            </td>
            <td class="formValue" colspan="2">&nbsp;时间单位默认天
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

<script type="text/javascript">

    $("#warehouseUserId").select2({
        language: "zh-CN",
        placeholder: '请选择'
    });

    <#--$("#managerId").on("change",function(){-->
        <#--var manage = $(this);-->
        <#--var vals = manage.val();-->
        <#--if(""==vals){-->
            <#--document.getElementById("role").value = "";-->
        <#--}else{-->
            <#--$.post("${request.contextPath}/user/getRoleName.json",{id:vals},function (result) {-->
                <#--if(result.success){-->
                    <#--document.getElementById("role").value = ""+result.msg-->
                <#--}else {-->
                    <#--toastr["error"](result.msg);-->
                <#--}-->
            <#--},"JSON");-->
        <#--}-->
    <#--});-->
</script>