<form id="updateProductionProcessDetailForm" style="margin: 1px">
    <input type="hidden" name="productionProcessDetailedId" value="${zsProductionProcessDetail.productionProcessDetailedId}">
    <input type="hidden" name="resourceId" value="${resourceId}">
    <table class="form">
        <tr>
            <th class="formTitle">编号：
            </th>
            <td class="formValue">
                <input class="txt required" id="updateProductionProcessDetailNumber" type="text" class="txt" name="productionProcessDetailNumber" value="${zsProductionProcessDetail.productionProcessDetailNumber}"
                       onKeyUp="this.value=this.value.replace(/\D/g,'')"/>
            </td>
            <th class="formTitle">生产过程：
            </th>
            <td class="formValue">
                <select class="txtselect" name="productionProcessId">
                    <#if productionProcessList??&&productionProcessList?size!=0>
                        <#list productionProcessList as productionProcess>
                            <option value="${productionProcess.productionProcessId}"<#if productionProcess.productionProcessId==zsProductionProcessDetail.productionProcessId>selected</#if>>
                                    ${productionProcess.productionProcessName}
                            </option>
                        </#list>
                    </#if>
                </select>
            </td>
        </tr>
        <tr>
            <th class="formTitle">过程详情名称：
            </th>
            <td class="formValue" colspan="3">
                <input class="txt required" id="updProductionProcessDetailName" name="productionProcessDetailName" type="text" value="${zsProductionProcessDetail.productionProcessDetailName}" />
            </td>
        </tr>
        <tr>
            <th class="formTitle">文字描述：
            </th>
            <td class="formValue" colspan="3">
                <textarea  class="txtArea" style="resize: none" name="processDescription" rows="5">${zsProductionProcessDetail.processDescription}</textarea>
            </td>
        </tr>
    </table>
    <br/>
    <div class="col-md-12">
        <p><span class="line"></span>上传图片<span class="line"></span></p>
        <div class="image">
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                <#if zsProductionProcessDetail.detailImage??><img src="${zsProductionProcessDetail.detailImage}"/></#if>
                </div>
                <div>
                      <span class="btn green-jungle btn-outline btn-file">
                                 <#if zsProductionProcessDetail.detailImage??>
                                     <span class="fileinput-new"> 修改 </span>
                                 <#else>
                                     <span class="fileinput-new"> 添加 </span>
                                 </#if>
                                     <span class="fileinput-exists"> 修改 </span>
                            <input id="updProductionProcessDetailImage" type="file" name="updProductionProcessDetailImage"/>
                        </span>
                <#if zsProductionProcessDetail.detailImage??>
                    <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                </#if>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <input type="hidden" id="updProductionProcessDetailImageId" name="updProductionProcessDetailImageId">
        </div>
    </div>
</form>

<script>
    $("input[name = updProductionProcessDetailImage]").on("change",function(){
        var idx = $("input[id = updProductionProcessDetailImage]").index($(this));
        var idxs = $("#updProductionProcessDetailImageId").val();
        idxs = ""+idxs+idx;
        $("#updProductionProcessDetailImageId").val(idxs);
    });
</script>