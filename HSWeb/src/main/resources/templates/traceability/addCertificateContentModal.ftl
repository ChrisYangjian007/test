

<form id="addCertificateContentForm" style="margin: 1px">
    <input type="hidden" name="certificateManageId" value="${zsCertificateManage.certificateManageId}">
    <input type="hidden" name="resourceId" value="${resourceId}">
    <table class="form">
        <tr>
            <th class="formTitle">名称：
            </th>
            <td class="formValue">
                <input class="txt" type="text" name="contentTitle" value="<#if zsCertificateManage.contentTitle??>${zsCertificateManage.contentTitle}</#if>" />
            </td>
        </tr>
        <tr>
            <th class="formTitle">内容：
            </th>
            <td class="formValue">
                <textarea  class="txtArea" style="resize: none" name="content" rows="5"><#if zsCertificateManage.content??>${zsCertificateManage.content}</#if></textarea>
            </td>
        </tr>
    </table>
</form>