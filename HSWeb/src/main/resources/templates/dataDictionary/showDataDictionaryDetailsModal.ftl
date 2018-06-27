
<#--数据字典详情详细-->
<form style="margin: 1px">
<#--<div id="message" style="display: none"></div>-->
    <input type="hidden" placeholder="${baDataDictionaryDetails.dataDictionaryDetailsId}" value="${baDataDictionaryDetails.dataDictionaryDetailsId}" readonly>
    <table class="form">
        <tr>
            <th class="formTitle">编码：
            </th>
            <td class="formValue">
                <input type="text" class="txt required" placeholder="${baDataDictionaryDetails.code}" value="${baDataDictionaryDetails.code}" readonly />
            </td>
            <th class="formTitle">名称：
            </th>
            <td class="formValue">
                <input type="text" class="txt required" placeholder="${baDataDictionaryDetails.CName}" value="${baDataDictionaryDetails.CName}" readonly/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">数据字典名称：
            </th>
            <td class="formValue">
                <input type="hidden" value="${baDataDictionaryDetails.dataDictionaryId}" readonly />
                <input type="text" class="txt" value="${baDataDictionary.CName}" readonly disabled/>
            </td>
            <th class="formTitle">显示顺序：</th>
            <td class="formValue">
                <input type="text" class="txt" value="${baDataDictionaryDetails.listIndex}" disabled/>
            <#--<input name="listIndex" type="hidden" placeholder="${baDataDictionaryDetails.listIndex}" value="${baDataDictionaryDetails.listIndex}" readonly />-->
            </td>
        </tr>
        <tr>
            <th class="formTitle">说明：
            </th>
            <td class="formValue" colspan="3">
                <textarea class="txtArea" style="resize: none" maxlength="200" rows="5" placeholder="${baDataDictionaryDetails.remark}" readonly>${baDataDictionaryDetails.remark}</textarea>
            </td>
        </tr>
    </table>
</form>