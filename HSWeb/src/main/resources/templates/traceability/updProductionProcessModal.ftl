<form id="updProductionProcessForm" style="margin: 1px">
    <input type="hidden" name="resourceId" value="${resourceId}">
    <input type="hidden" name="productionProcessId" value="${zsProductionProcess.productionProcessId}">
    <table class="form">
        <tr>
            <th class="formTitle">名称：
            </th>
            <td class="formValue">
                <input class="txt required" id="updProductionProcessName" type="text" name="productionProcessName" value="${zsProductionProcess.productionProcessName}"/>
            </td>
        </tr>
    </table>
</form>