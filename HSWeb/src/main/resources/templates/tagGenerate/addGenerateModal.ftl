

<form id="addGenerateForm" style="margin: 1px">
    <table class="form">
        <tr>
            <th class="formTitle">新增二维码数量：
            </th>
            <td class="formValue">
                <input type="hidden" name="resourceId"
                       <#if resourceId??>value="${resourceId}"</#if>/>
                <input  id="tagCount" name="tagCount" style="text-align: center " type="text" class="txt " maxlength="10" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"/>
            </td>
        </tr>

    </table>
</form>
