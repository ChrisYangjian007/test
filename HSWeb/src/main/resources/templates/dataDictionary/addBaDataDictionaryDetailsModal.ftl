
<#--添加数据字典详情-->
<form id="addBaDataDictionaryDetailsForm" style="margin: 1px">
<#--<div id="message" style="display: none"></div>-->
    <table class="form">
        <tr>
            <th class="formTitle">编码：
            </th>
            <td class="formValue">
                <input id="code" name="code" type="text" class="txt required" maxlength="10"/>
            </td>
            <th class="formTitle">名称：
            </th>
            <td class="formValue">
                <input id="cName" name="cName" type="text" class="txt required" />
            </td>
        </tr>
        <tr>
            <th class="formTitle">数据字典名称：
            </th>
            <td class="formValue">
                <input id="dataDictionaryId" name="dataDictionaryId" type="hidden" value="${baDataDictionary.dataDictionaryId}"/>
                <input id="dataDictionaryName" name="dataDictionaryName" type="text" class="txt" value="${baDataDictionary.CName}" readonly disabled/>
            </td>
            <th class="formTitle">显示顺序：</th>
            <td class="formValue">
                <input id="listIndex" type="text" class="txt" value="${listIndex}" readonly disabled/>
                <input name="listIndex" type="hidden" value="${listIndex}" />
            </td>
        </tr>
        <tr>
            <th class="formTitle">说明：
            </th>
            <td class="formValue" colspan="3">
                <textarea class="txtArea" style="resize: none" id="remark" name="remark" maxlength="200" rows="5"></textarea>
            </td>
        </tr>
    </table>
</form>