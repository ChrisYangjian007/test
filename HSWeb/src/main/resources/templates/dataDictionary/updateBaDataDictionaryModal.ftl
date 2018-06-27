
<#--添加数据字典-->
<form id="updateBaDataDictionaryForm" style="margin: 1px">
<#--<div id="message" style="display: none"></div>-->
    <input type="hidden" name="dataDictionaryId" placeholder="${dataDictionary.dataDictionaryId}" value="${dataDictionary.dataDictionaryId}">
    <table class="form">
        <tr>
            <th class="formTitle">编码：
            </th>
            <td class="formValue">
                <input id="updCode" name="code" type="text" class="txt required" maxlength="10" placeholder="${dataDictionary.code}" value="${dataDictionary.code}" />
            </td>
            <th class="formTitle">名称：
            </th>
            <td class="formValue">
                <input id="updCName" name="cName" type="text" class="txt required" placeholder="${dataDictionary.CName}" value="${dataDictionary.CName}" />
            </td>
        </tr>
        <tr>
            <th class="formTitle">第一层：
            </th>
            <td class="formValue">
                <input id="updFirstId" name="firstId" type="hidden" value="1" readonly disabled/>
                <input id="" name="firstId" type="text" class="txt" value="1" readonly disabled/>
            </td>
            <th class="formTitle">层次：</th>
            <td class="formValue">
                <input id="updALevel" type="text" class="txt" value="${dataDictionary.ALevel}" readonly disabled/>
                <input name="updALevel" type="hidden" value="${dataDictionary.ALevel}" readonly disabled/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">上一层名称：
            </th>
            <td class="formValue">
                <input id="parentId" name="parentId" type="hidden" value="${dataDictionary.parentId}" readonly />
                <input id="parentName" name="parentName" type="text" class="txt" value="${parentDictionary.CName}" readonly disabled/>
            </td>
            <th class="formTitle">显示顺序：</th>
            <td class="formValue">
                <input id="listIndex" type="text" class="txt" value="${dataDictionary.listIndex}" readonly disabled/>
                <input name="listIndex" type="hidden" value="${dataDictionary.listIndex}" />
            </td>
        </tr>
        <tr>
            <th class="formTitle">说明：
            </th>
            <td class="formValue" colspan="3">
                <textarea class="txtArea" style="resize: none" id="remark" name="remark" maxlength="200" rows="5" placeholder="${dataDictionary.remark}">${dataDictionary.remark}</textarea>
            </td>
        </tr>
    </table>
</form>