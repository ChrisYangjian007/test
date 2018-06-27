<#--新增-->
<form id="updateAddCorporateNews" style="margin: 1px">
    <input type="hidden" name="resourceId" value="${resourceId}">
    <table class="form">
        <tr>
            <th class="formTitle">名称：
            </th>
            <td class="formValue">
                <input type="hidden" name="corporateNewsId" value="<#if corporateNewsById.corporateNewsId??>${corporateNewsById.corporateNewsId}</#if>" />
                <input class="txt required" id="corporateNewsName" type="text" name="corporateNewsName" value="<#if corporateNewsById.corporateNewsName??>${corporateNewsById.corporateNewsName}</#if>" />
            </td>
        </tr>
        <tr>
            <th class="formTitle">内容：
            </th>
            <td class="formValue">
                  <textarea  class="txtArea" style="resize: none" id="updateAddRemark" name="remark" rows="5"><#if corporateNewsById.remark??>${corporateNewsById.remark}</#if></textarea>
            </td>
        </tr>
    </table>
</form>