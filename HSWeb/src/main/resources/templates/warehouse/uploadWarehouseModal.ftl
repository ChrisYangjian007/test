
<form id="uploadWarehouseImagesForm" style="margin: 1px">
    <#if resourceId??><input type="hidden" name="resourceId" value="${resourceId}" /></#if>
    <#if warehouse??>
    <table class="form">
        <tr>
            <th class="formTitle">仓库名称：
            </th>
            <td class="formValue" colspan="3">
                <input type="hidden" name="warehouseId" value="${warehouse.warehouseId}"/>
                <input type="text" value="${warehouse.CName}" disabled/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">登记表上传：
            </th>
        </tr>
    </table>
        <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
            <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if warehouseImages??&&warehouseImages.imagesJson??&&warehouseImages.warehouseImagesJson[0]??&&warehouseImages.warehouseImagesJson[0].imageUrl!="">
                            <img src="${warehouseImages.warehouseImagesJson[0].imageUrl}" alt="${warehouseImages.warehouseImagesJson[0].imageName}"/>
                        </#if>
            </div>
            <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if warehouseImages??&&warehouseImages.imagesJson??&&warehouseImages.warehouseImagesJson[0]??&&warehouseImages.warehouseImagesJson[0].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="warehouseImage" type="file" name="warehouseImage" value="" onchange="UnDisabled()"/>
                        </span>
                        <#if warehouseImages??&&warehouseImages.imagesJson??&&warehouseImages.warehouseImagesJson[0]??&&warehouseImages.warehouseImagesJson[0].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
            </div>
        </div>
        <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
            <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if warehouseImages??&&warehouseImages.imagesJson??&&warehouseImages.warehouseImagesJson[1]??&&warehouseImages.warehouseImagesJson[1].imageUrl!="">
                            <img src="${warehouseImages.warehouseImagesJson[1].imageUrl}" alt="${warehouseImages.warehouseImagesJson[1].imageName}"/>
                        </#if>
            </div>
            <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if warehouseImages??&&warehouseImages.imagesJson??&&warehouseImages.warehouseImagesJson[1]??&&warehouseImages.warehouseImagesJson[1].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="warehouseImage" type="file" name="warehouseImage" value="" onchange="UnDisabled()"/>
                        </span>
                        <#if warehouseImages??&&warehouseImages.imagesJson??&&warehouseImages.warehouseImagesJson[1]??&&warehouseImages.warehouseImagesJson[1].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
            </div>
        </div>
        <input type="hidden" id="warehouseImageId" name="warehouseImageId">
    <#else >
    <table class="form">
        <tr >
            <th class="formTitle">错误：
            </th>
            <td class="formValue" colspan="3">
                <input style="color: red" type="text" class="txt" value="请重试，再不行请联系管理员" disabled />
            </td>
        </tr>
    </table>
    </#if>
</form>

<script>

    $("input[name = warehouseImage]").on("change",function(){
        var idx = $("input[id = warehouseImage]").index($(this));
        var idxs = $("#warehouseImageId").val();
        idxs = ""+idxs+idx;
        $("#warehouseImageId").val(idxs);
    });

    function UnDisabled() {
        $("#uploadWarehouseImages").attr('disabled',false);
    }
</script>



