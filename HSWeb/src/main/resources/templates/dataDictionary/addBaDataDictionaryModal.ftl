
<#--添加数据字典-->
<form id="addBaDataDictionaryForm" style="margin: 1px">
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
            <th class="formTitle">第一层：
            </th>
            <td class="formValue">
                <input id="firstId" name="firstId" type="hidden" value="1"/>
                <input id="" name="firstId" type="text" class="txt" value="1" disabled/>
            </td>
            <th class="formTitle">层次：</th>
            <td class="formValue">
                <input id="aLevel" type="text" class="txt" value="${baDataDictionary.ALevel+1}" readonly/>
                <input name="aLevel" type="hidden" value="${baDataDictionary.ALevel+1}" readonly/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">上一层名称：
            </th>
            <td class="formValue">
                <input id="parentId" name="parentId" type="hidden" value="${baDataDictionary.dataDictionaryId}"/>
                <input id="parentName" name="parentName" type="text" class="txt" value="${baDataDictionary.CName}" readonly disabled/>
            </td>
            <th class="formTitle">显示顺序：</th>
            <td class="formValue">
                <input id="listIndex" type="text" class="txt" value="${listIndex}" readonly disabled/>
                <input name="listIndex" type="hidden" value="${listIndex}" readonly />
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