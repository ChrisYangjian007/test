<#--修改检测设备-->
<form id="updateTestingEquipmentForm" style="margin: 1px">
    <input type="hidden" name="resourceId" value="${resourceId}">
    <table class="form">
        <tr>
            <th class="formTitle">检验室：
            </th>
            <td class="formValue">
                <input id="updTestingEquipmentId" type="hidden"  name="testingEquipmentId" value="<#if testingEquipmentById.testingEquipmentId??>${testingEquipmentById.testingEquipmentId}</#if>" />
                <select id="updEnterpriseId" name="dataDictionaryDetailsId" class="txtselect" datacol="yes" err="分类" checkexpession="NotNull">
                    <option value="">==请选择==</option>
                    <#if dictionary??&&dictionary?size!=0>
                        <#list dictionary as dy>
                            <option  value="${dy.dataDictionaryDetailsId}" <#if dy.dataDictionaryDetailsId==testingEquipmentById.dataDictionaryDetailsId>selected</#if>>${dy.testCName}</option>
                        </#list>
                    </#if>
                </select>
            </td>
            <th class="formTitle">编号：
            </th>
            <td class="formValue">
                <input class="txt required" id="updTestEqId" type="text" name="id" value="<#if testingEquipmentById.id??>${testingEquipmentById.id}</#if>" />
            </td>
        </tr>
        <tr>
            <th class="formTitle">文字描述：
            </th>
            <td class="formValue" colspan="3">
                <textarea  class="txtArea" style="resize: none" id="updTestEqRemark" name="remark" rows="5"><#if testingEquipmentById.remark??>${testingEquipmentById.remark}</#if></textarea>
            </td>
        </tr>
    </table>
    <br/>
    <div class="col-md-12">
        <p><span class="line"></span>上传图片<span class="line"></span></p>
        <div class="image">
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                <#if testingEquipmentById.images??><img src="${testingEquipmentById.images}"/></#if>
                </div>
                <div>
                         <span class="btn green-jungle btn-outline btn-file">
                                  <#if testingEquipmentById.images??>
                                      <span class="fileinput-new"> 修改 </span>
                                  <#else>
                                      <span class="fileinput-new"> 添加 </span>
                                  </#if>
                                      <span class="fileinput-exists"> 修改 </span>
                            <input id="updTestEqImage" type="file" name="updTestEqImage"/>
                         </span>
                <#if testingEquipmentById.images??>
                    <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                </#if>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <input type="hidden" id="updTestEqImageId" name="updTestEqImageId">
        </div>
    </div>
</form>

<script>

    $("input[name = updTestEqImage]").on("change",function(){
        var idx = $("input[id = updTestEqImage]").index($(this));
        var idxs = $("#updTestEqImageId").val();
        idxs = ""+idxs+idx;
        $("#updTestEqImageId").val(idxs);
    });

</script>