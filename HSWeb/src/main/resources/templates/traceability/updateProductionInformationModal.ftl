<#--修改生产控制点-->
<form id="updateProductionInformation" style="margin: 1px">
    <input type="hidden" name="productionInformationId" value="${productionInformationById.productionInformationId}">
    <input type="hidden" name="resourceId" value="${resourceId}">
    <table class="form">
        <tr>
            <th class="formTitle">编号：
            </th>
            <td class="formValue">
                <input class="txt required" id="updProductionInformationId" type="text" name="id" onKeyUp="this.value=this.value.replace(/\D/g,'')" value="<#if productionInformationById.id??>${productionInformationById.id}</#if>" />
            </td>
        </tr>
        <tr>
            <th class="formTitle">名称：
            </th>
            <td class="formValue">
                <input class="txt required" id="updProductionInformationName" type="text" name="productionInformationName" value="<#if productionInformationById.productionInformationName??>${productionInformationById.productionInformationName}</#if>" />
            </td>
        </tr>
        <tr>
            <th class="formTitle">文字描述：
            </th>
            <td class="formValue">
                <textarea  class="txtArea" style="resize: none" id="updProductionInformationRemark" name="remark" rows="5"><#if productionInformationById.remark??>${productionInformationById.remark}</#if></textarea>
            </td>
        </tr>
    </table>
    <br/>
    <div class="col-md-12">
        <p><span class="line"></span>上传图片<span class="line"></span></p>
        <div class="image">
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                    <#if productionInformationById.images??><img src="${productionInformationById.images}"/></#if>
                </div>
                <div>
                      <span class="btn green-jungle btn-outline btn-file">
                                 <#if productionInformationById.images??>
                                     <span class="fileinput-new"> 修改 </span>
                                 <#else>
                                     <span class="fileinput-new"> 添加 </span>
                                 </#if>
                                     <span class="fileinput-exists"> 修改 </span>
                            <input id="updProductionInformationImage" type="file" name="updProductionInformationImage" value=" " />
                        </span>
                <#if productionInformationById.images??>
                    <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                </#if>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <input type="hidden" id="updProductionInformationImageId" name="updProductionInformationImageId">
        </div>
    </div>
</form>

<script>
    $("input[name = updProductionInformationImage]").on("change",function(){
        var idx = $("input[id = updProductionInformationImage]").index($(this));
        var idxs = $("#updProductionInformationImageId").val();
        idxs = ""+idxs+idx;
        $("#updProductionInformationImageId").val(idxs);
    });
</script>