<#--修改检测设备内容-->
<form id="updateTestingEquipmentDetailForm" style="margin: 1px">
    <input type="hidden" name="testingEquipmentDetailId" value="${testingEquipmentDetailById.testingEquipmentDetailId}">
    <input type="hidden" name="resourceId" value="${resourceId}">
    <table class="form">
        <tr>
            <th class="formTitle">检验室：
            </th>
            <td class="formValue">
                <select class="txtselect" name="testingEquipmentId">
                <#if testingEquipmentList??&&testingEquipmentList?size!=0>
                    <#list testingEquipmentList as testingEquipment>
                        <option value="${testingEquipment.testingEquipmentId}"<#if testingEquipment.testingEquipmentId==testingEquipmentDetailById.testingEquipmentId>selected</#if>>
                        ${testingEquipment.testCName}
                        </option>
                    </#list>
                </#if>
                </select>
            </td>
            <th class="formTitle">名称：
            </th>
            <td class="formValue">
                <input type="hidden" name="testingEquipmentDetailId" <#if testingEquipmentDetailById.testingEquipmentDetailId??>value="${testingEquipmentDetailById.testingEquipmentDetailId}"</#if>/>
                <input class="txt required" id="updTestingEquipmentDetailCname" type="text" name="testingEquipmentDetailCname" value="<#if testingEquipmentDetailById.testingEquipmentDetailCname??>${testingEquipmentDetailById.testingEquipmentDetailCname}</#if>" />
            </td>
        </tr>
        <tr>
            <th class="formTitle">文字描述：
            </th>
            <td class="formValue" colspan="3">
                <textarea  class="txtArea" style="resize: none" id="updTestEqDetailRemark" name="remark" rows="5"><#if testingEquipmentDetailById.remark??>${testingEquipmentDetailById.remark}</#if></textarea>
            </td>
        </tr>
    </table>
    <br/>
    <div class="col-md-12">
        <p><span class="line"></span>上传图片<span class="line"></span></p>
        <div class="image">
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
<#if testingEquipmentDetailById.images??><img src="${testingEquipmentDetailById.images}"/></#if>
                </div>
                <div>
                      <span class="btn green-jungle btn-outline btn-file">
                                 <#if testingEquipmentDetailById.images??>
                                     <span class="fileinput-new"> 修改 </span>
                                 <#else>
                                     <span class="fileinput-new"> 添加 </span>
                                 </#if>
                                     <span class="fileinput-exists"> 修改 </span>
                            <input id="updTestEqDetailImage" type="file" name="updTestEqDetailImage"/>
                        </span>
                <#if testingEquipmentDetailById.images??>
                    <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                </#if>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <input type="hidden" id="updTestEqDetailImageId" name="updTestEqDetailImageId">
        </div>
    </div>
</form>

<script>

    $("input[name = updTestEqDetailImage]").on("change",function(){
        var idx = $("input[id = updTestEqDetailImage]").index($(this));
        var idxs = $("#updTestEqDetailImageId").val();
        idxs = ""+idxs+idx;
        $("#updTestEqDetailImageId").val(idxs);
    });

</script>