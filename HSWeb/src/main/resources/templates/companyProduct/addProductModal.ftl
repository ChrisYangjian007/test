

<form id="addProductForm" style="margin: 1px">
    <table class="form">
        <tr>
            <th class="formTitle">产品编码：
            </th>
            <td class="formValue">
                <input name="productNo" type="text" class="txt " maxlength="10"/>
                <input type="hidden" name="resourceId" <#if resourceId??>value="${resourceId}"</#if> />
            </td>
            <th class="formTitle">产品名称：
            </th>
            <td class="formValue">
                <input id="addProductCName" name="cName" type="text" class="txt required"/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">类别：
            </th>
            <td class="formValue">
                <select id="addProductType" class="txtselect" name="type">
                <#if dataDictionaryDetailsList??>
                    <option value="">==请选择==</option>
                    <#list dataDictionaryDetailsList as dataDictionaryDetails>
                        <option value="${dataDictionaryDetails.dataDictionaryDetailsId}">${dataDictionaryDetails.CName}</option>
                    </#list>
                <#else >
                    <option value="">==无数据==</option>
                </#if>
                </select>
            </td>
            <th class="formTitle">产品线：
            </th>
            <td class="formValue">
                <select id="addProductProductLineId" class="txtselect" name="productLineId">
                <#if productTypeList??>
                    <option value="">==请选择==</option>
                    <#list productTypeList as productType>
                        <option value="${productType.productTypeId}">${productType.CName}</option>
                    </#list>
                <#else >
                    <option value="">==无数据==</option>
                </#if>
                </select>
            </td>
        </tr>
        <tr>
            <th class="formTitle">产品大类：
            </th>
            <td class="formValue">
                <select id="addProductProductCategoryId" class="txtselect" name="productCategoryId">
                    <option value="">==请先选择产品线==</option>
                </select>
            </td>
            <th class="formTitle">产品小类：
            </th>
            <td class="formValue">
                <select id="addProductProductTypeName" class="txtselect" name="productTypeName">
                    <option value="">==请先选择产品大类==</option>
                </select>
                <input id="addProductProductTypeId" name="productTypeId" type="hidden"/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">产品规格：
            </th>
            <td class="formValue">
                <input id="addProductProductSpecification" name="productSpecification" type="text" class="txt required" />
            </td>
            <th class="formTitle">单位：
            </th>
            <td class="formValue">
                <input id="addProductParentID" type="hidden" name="sysUnitId"/>
                <input id="addProductParentName" type="text" class="txt icontree" name="sysUnitName" />
            </td>
        </tr>
        <tr>
            <th class="formTitle">净重：
            </th>
            <td class="formValue">
                <input id="addProductNetWeight" name="netWeight" type="text" class="txt" />
            </td>
        </tr>
    </table>
</form>

<script>


    $("#addProductProductLineId").on("change",function () {
        Loading(true,"正在获取产品大类...");
        let html = "<option value=''>==请先选择产品线==</option>";
        let productTypeHtml = "<option value=''>==请先选择产品大类==</option>";
        let productCategoryId =$("#addProductProductCategoryId");
        let addProductProductTypeName =$("#addProductProductTypeName");
        productCategoryId.html(html);
        addProductProductTypeName.html(productTypeHtml);
        let productLineId =$("#addProductProductLineId :selected").val();
        if (""!=productLineId){
            $.post('${request.contextPath}/productType/getSysProductTypeByParentId.json', {parentId:productLineId}, function (result) {
                if(result.success){
                    for (let i=0;i<result.obj.length;i++){
                        html+='<option value="'+result.obj[i].productTypeId+'">'+result.obj[i].cname+'</option>';
                    }
                }
                productCategoryId.html(html);
                Loading(false);
            }, "JSON");
        }else {
            productCategoryId.html(html);
            Loading(false);
        }
    });

    $("#addProductProductCategoryId").on("change",function () {
        Loading(true,"正在获取产品小类...");
        let productTypeHtml = "<option value=''>==请先选择产品大类==</option>";
        let addProductProductTypeName =$("#addProductProductTypeName");
        addProductProductTypeName.html(productTypeHtml);
        let productCategoryId =$("#addProductProductCategoryId :selected").val();
        if (""!=productCategoryId){
            $.post('${request.contextPath}/productType/getSysProductTypeByParentId.json', {parentId:productCategoryId}, function (result) {
                if(result.success){
                    for (let i=0;i<result.obj.length;i++){
                        productTypeHtml+='<option id="'+result.obj[i].productTypeId+'" value="'+result.obj[i].cname+'">'+result.obj[i].cname+'</option>';
                    }
                }
                addProductProductTypeName.html(productTypeHtml);
                Loading(false);
            }, "JSON");
        }else {
            addProductProductTypeName.html(productTypeHtml);
            Loading(false);
        }
    });

    $("#addProductParentName").focus(function () {
        let objId = this.id;
        comboBoxTree(objId, "182px");
        let itemtree = {
            onnodeclick: function (item) {
                $("#addProductParentID").val(item.id);
                $('#addProductParentName').val(item.text);
            },
            url: "${request.contextPath}/sysUnit/TreeJson.json"
        };
        $("#comboBoxTree" + objId).treeview(itemtree);
        $("#comboBoxTree" + objId + "_" + GetQuery('KeyValue').replace(/-/g, '_')).parent('li').remove();
    });




</script>