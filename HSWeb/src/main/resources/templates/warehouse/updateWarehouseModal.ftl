
<#--新增-->
<form id="updateWarehouseForm" style="margin: 1px">
    <input type="hidden" name="resourceId" <#if resourceId??>value="${resourceId}"</#if> />
    <input type="hidden" name="warehouseId" value="${zsWarehouse.warehouseId}">
    <table class="form">
        <tr>
            <th class="formTitle">主要负责人：
            </th>
            <td class="formValue" colspan="3">
                    <select id="updWarehouseUserId" name="updWarehouseUserId" class="txtselect  selectOption" multiple>
                    <#if userList??&&userList?size!=0>
                        <#list userList as user >
                            <option value="${user.userId}" <#if user.isSelected==1>selected</#if>>${user.CName}</option>
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
                <input id="updCName" name="cName" type="text" class="txt required" placeholder="${zsWarehouse.CName}" value="${zsWarehouse.CName}" />
            </td>
            <th class="formTitle">仓库类型：
            </th>
            <td class="formValue">
                <select id="updWarehouseType" name="warehouseType" class="txtselect">
                    <option value="0" <#if zsWarehouse.warehouseType==0>selected</#if>>无</option>
                    <option value="1" <#if zsWarehouse.warehouseType==1>selected</#if>>活参库</option>
                </select>
            </td>
        </tr>
        <tr>
            <th class="formTitle">预警天数：
            </th>
            <td class="formValue">
                <input id="updWarningDay" name="warningDay" type="text" class="txt required"
                       placeholder="${zsWarehouse.warningDay}" value="${zsWarehouse.warningDay}"
                       maxlength="4" onkeyup="this.value=this.value.replace(/\D/g,'')"
                       onblur="this.value=this.value.replace(/\D/g,'')" />
            </td>
            <td class="formValue" colspan="2">&nbsp;时间单位默认天
            </td>
        </tr>
        <tr>
            <th class="formTitle">描述：
            </th>
            <td class="formValue" colspan="3">
                <textarea class="txtArea" style="resize: none" id="updRemark" name="remark" maxlength="200" rows="5" placeholder="${zsWarehouse.remark}">${zsWarehouse.remark}</textarea>
            </td>
        </tr>
    </table>
</form>

<script type="text/javascript">

    $("#updWarehouseUserId").select2({
        language: "zh-CN",
        placeholder: '请选择'
    });
</script>