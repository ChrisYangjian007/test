
<#--修改数据字典详情-->
<form id="updateBaDataDictionaryDetailsForm" style="margin: 1px">
<#--<div id="message" style="display: none"></div>-->
    <input type="hidden" name="dataDictionaryDetailsId" placeholder="${baDataDictionaryDetails.dataDictionaryDetailsId}" value="${baDataDictionaryDetails.dataDictionaryDetailsId}">
    <table class="form">
        <tr>
            <th class="formTitle">编码：
            </th>
            <td class="formValue">
                <input id="updCode" name="code" type="text" class="txt required" maxlength="10" placeholder="${baDataDictionaryDetails.code}" value="${baDataDictionaryDetails.code}" />
            </td>
            <th class="formTitle">名称：
            </th>
            <td class="formValue">
                <input id="updCName" name="cName" type="text" class="txt required" placeholder="${baDataDictionaryDetails.CName}" value="${baDataDictionaryDetails.CName}" />
            </td>
        </tr>
        <tr>
            <th class="formTitle">数据字典名称：
            </th>
            <td class="formValue">
                <input id="updDataDictionaryId" name="dataDictionaryId" type="hidden" value="${baDataDictionaryDetails.dataDictionaryId}" readonly />
                <input id="updDataDictionaryName" name="dataDictionaryName" type="text" class="txt" value="${baDataDictionary.CName}" readonly disabled/>
            </td>
            <th class="formTitle">显示顺序：</th>
            <td class="formValue">
                <input id="updListIndex" type="text" class="txt" value="${baDataDictionaryDetails.listIndex}" readonly disabled/>
                <#--<input name="listIndex" type="hidden" placeholder="${baDataDictionaryDetails.listIndex}" value="${baDataDictionaryDetails.listIndex}" readonly />-->
            </td>
        </tr>
        <tr>
            <th class="formTitle">说明：
            </th>
            <td class="formValue" colspan="3">
                <textarea class="txtArea" style="resize: none" id="updRemark" name="remark" maxlength="200" rows="5" placeholder="${baDataDictionaryDetails.remark}">${baDataDictionaryDetails.remark}</textarea>
            </td>
        </tr>
    </table>
</form>