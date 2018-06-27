
<#--查看数据字典-->
<form style="margin: 1px">
<#--<div id="message" style="display: none"></div>-->
    <table class="form">
        <tr>
            <th class="formTitle">编码：
            </th>
            <td class="formValue">
                <input type="text" class="txt required" placeholder="${baDataDictionary.code}" value="${baDataDictionary.code}" readonly />
            </td>
            <th class="formTitle">名称：
            </th>
            <td class="formValue">
                <input type="text" class="txt required" placeholder="${parentDictionary.CName}" value="${parentDictionary.CName}" readonly />
            </td>
        </tr>
        <tr>
            <th class="formTitle">第一层：
            </th>
            <td class="formValue">
                <input type="hidden" value="1" readonly disabled/>
                <input type="text" class="txt" value="1" readonly disabled/>
            </td>
            <th class="formTitle">层次：</th>
            <td class="formValue">
                <input type="text" class="txt" value="${baDataDictionary.ALevel}" readonly disabled/>
                <input type="hidden" value="${baDataDictionary.ALevel}" readonly disabled/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">上一层名称：
            </th>
            <td class="formValue">
                <input type="hidden" value="${baDataDictionary.parentId}" readonly disabled/>
                <input type="text" class="txt" value="${parentDictionary.CName}" disabled/>
            </td>
            <th class="formTitle">显示顺序：</th>
            <td class="formValue">
                <input type="text" class="txt" value="${baDataDictionary.listIndex}" readonly disabled/>
                <input type="hidden" value="${baDataDictionary.listIndex}" />
            </td>
        </tr>
        <tr>
            <th class="formTitle">说明：
            </th>
            <td class="formValue" colspan="3">
                <textarea class="txtArea" style="resize: none" maxlength="200" rows="5" placeholder="${baDataDictionary.remark}" readonly>${baDataDictionary.remark}</textarea>
            </td>
        </tr>
    </table>
</form>