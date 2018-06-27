

<form id="updateProductForm" style="margin: 1px">
<#if companyProduct??>
    <table class="form">
        <tr>
            <th class="formTitle">产品编码：
            </th>
            <td class="formValue">
                <input type="hidden" name="resourceId" <#if resourceId??>value="${resourceId}"</#if> />
                <input name="productId" type="hidden" value="${companyProduct.productId}"/>
                <input name="productNo" type="text" class="txt " maxlength="10"
                       <#if companyProduct.productNo??>value="${companyProduct.productNo}"</#if>/>
            </td>
            <th class="formTitle">产品名称：
            </th>
            <td class="formValue">
                <input id="updateProductCName" name="cName" type="text" class="txt required" value="${companyProduct.CName}"/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">类别：
            </th>
            <td class="formValue">
                <select id="updateType" class="txtselect" name="type">
                <#if dataDictionaryDetailsList??>
                    <option value="">==请选择==</option>
                    <#list dataDictionaryDetailsList as dataDictionaryDetails>
                        <option value="${dataDictionaryDetails.dataDictionaryDetailsId}"
                                <#if dataDictionaryDetails.dataDictionaryDetailsId==companyProduct.type>selected</#if>>${dataDictionaryDetails.CName}</option>
                    </#list>
                <#else >
                    <option value="">==无数据==</option>
                </#if>
                </select>
            </td>
            <th class="formTitle">产品线：
            </th>
            <td class="formValue">
                <select id="updateProductLineId" class="txtselect" name="productLineId">
                <#if productTypeList??>
                    <option value="">==请选择==</option>
                    <#list productTypeList as productType>
                        <option value="${productType.productTypeId}"
                                <#if productType.productTypeId==companyProduct.productLineId>selected</#if>>${productType.CName}</option>
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
                <select id="updateProductCategoryId" class="txtselect" name="productCategoryId">
                <#if productCategory??>
                    <option value="">==请选择==</option>
                    <#list productCategory as productCategory>
                        <option value="${productCategory.productTypeId}"
                                <#if productCategory.productTypeId==companyProduct.productCategoryId>selected</#if>>${productCategory.CName}</option>
                    </#list>
                <#else >
                    <option value="">==无数据==</option>
                </#if>
                    <#--<option value="${companyProduct.productCategoryId}">${companyProduct.productCategory}</option>-->
                </select>
            </td>
            <th class="formTitle">产品小类：
            </th>
            <td class="formValue">
                <select id="updateProductTypeName" class="txtselect" name="productTypeName">
                <#if productType??>
                    <option value="">==请选择==</option>
                    <#list productType as productType>
                        <option value="${productType.productTypeId}"
                                <#if productType.productTypeId==companyProduct.productTypeId>selected</#if>>${productType.CName}</option>
                    </#list>
                <#else >
                    <option value="">==无数据==</option>
                </#if>

                    <#--<option id="${companyProduct.productTypeId}" value="${companyProduct.productTypeName}">
                        ${companyProduct.productTypeName}</option>-->
                </select>
                <input id="updateProductTypeId" name="productTypeId" type="hidden"/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">产品规格：
            </th>
            <td class="formValue">
                <input id="updateProductSpecification" name="productSpecification" type="text" class="txt required"
                       value="${companyProduct.productSpecification}"/>
            </td>
            <th class="formTitle">单位：
            </th>
            <td class="formValue">
                <input id="updateParentID" type="hidden" name="sysUnitId"
                       <#if companyProduct.sysUnitId??>value="${companyProduct.sysUnitId}" placeholder="${companyProduct.sysUnitId}" </#if>/>
                <input id="updateParentName" type="text" class="txt icontree" name="sysUnitName"
                       <#if companyProduct.sysUnitName??>value="${companyProduct.sysUnitName}" placeholder="${companyProduct.sysUnitName}" </#if>/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">净重：
            </th>
            <td class="formValue">
                <input id="updateNetWeight" name="netWeight" type="text" class="txt"
                       <#if companyProduct.netWeight??>value="${companyProduct.netWeight}" placeholder="${companyProduct.netWeight}" </#if>/>
            </td>
        </tr>
    </table>
<#else >
    <table class="form">
        <tr id="locationTr">
            <th class="formTitle">错误：
            </th>
            <td class="formValue" colspan="3">
                <input type="text" class="txt required" disabled value="请重试，再不行请联系管理员"/>
            </td>
        </tr>
    </table>
</#if>
</form>

<script>


    $("#updateProductLineId").on("change",function () {
        Loading(true,"正在获取产品大类...");
        var html = "<option value=''>==请先选择产品线==</option>";
        var productTypeHtml = "<option value=''>==请先选择产品大类==</option>";
        var productCategoryId =$("#updateProductCategoryId");
        var updateProductTypeName =$("#updateProductTypeName");
        productCategoryId.html(html);
        updateProductTypeName.html(productTypeHtml);
        var productLineId =$("#updateProductLineId :selected").val();
        if (""!=productLineId){
            $.post('${request.contextPath}/productType/getSysProductTypeByParentId.json', {parentId:productLineId}, function (result) {
                if(result.success){
                    for (var i=0;i<result.obj.length;i++){
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


    $("#updateProductCategoryId").on("change",function () {
        Loading(true,"正在获取产品小类...");
        var productTypeHtml = "<option value=''>==请先选择产品大类==</option>";
        var updateProductTypeName =$("#updateProductTypeName");
        var productCategoryId =$("#updateProductCategoryId :selected").val();
        updateProductTypeName.html(productTypeHtml);
        if (""!=productCategoryId){
            $.post('${request.contextPath}/productType/getSysProductTypeByParentId.json', {parentId:productCategoryId}, function (result) {
                if(result.success){
                    for (var i=0;i<result.obj.length;i++){
                        productTypeHtml+='<option id="'+result.obj[i].productTypeId+'" value="'+result.obj[i].cname+'">'+result.obj[i].cname+'</option>';
                    }
                }
                updateProductTypeName.html(productTypeHtml);
                Loading(false);
            }, "JSON");
        }else {
            updateProductTypeName.html(productTypeHtml);
            Loading(false);
        }
    });

    $("#updateParentName").focus(function () {
        var objId = this.id;
        comboBoxTree(objId, "182px");
        var itemtree = {
            onnodeclick: function (item) {
                $("#updateParentID").val(item.id);
                $('#updateParentName').val(item.text);
            },
            url: "${request.contextPath}/sysUnit/TreeJson.json"
        };
        $("#comboBoxTree" + objId).treeview(itemtree);
        $("#comboBoxTree" + objId + "_" + GetQuery('KeyValue').replace(/-/g, '_')).parent('li').remove();
    });




</script>