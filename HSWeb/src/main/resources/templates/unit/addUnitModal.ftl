

<#--添加单位-->
<form id="addUnitForm" style="margin: 1px">
    <table class="form">
    <tr>
        <th class="formTitle">单位名称：
        </th>
        <td class="formValue" colspan="3">
            <input type="hidden" name="resourceId" <#if resourceId??>value="${resourceId}"</#if> />
            <input id="cName" name="cName" type="text" class="txt required" />
        </td>
    </tr>
    <tr>
        <th class="formTitle">换算值：
        </th>
        <td class="formValue" colspan="3">
            <input id="value" name="value" type="text" class="txt required" />
        </td>
    </tr>
    <tr>
        <th class="formTitle">父级单位：
        </th>
        <td class="formValue" colspan="3">
            <input id="parentId" type="hidden" name="parentId"/>
            <input id="parentName" type="text" class="txt icontree" name="parentName" />
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

<script>


    $("#parentName").focus(function () {
        var objId = this.id;
        comboBoxTree(objId, "182px");
        var itemtree = {
            onnodeclick: function (item) {
                $("#parentId").val(item.id);
                $('#parentName').val(item.text);
            },
            url: "${request.contextPath}/sysUnit/TreeJsonForParent.json"
        };
        $("#comboBoxTree" + objId).treeview(itemtree);
        $("#comboBoxTree" + objId + "_" + GetQuery('KeyValue').replace(/-/g, '_')).parent('li').remove();
    });

</script>