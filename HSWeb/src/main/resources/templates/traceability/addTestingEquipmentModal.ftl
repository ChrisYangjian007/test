<#--新增检测设备-->
<form id="addTestingEquipmentForm" style="margin: 1px">
    <input type="hidden" name="resourceId" value="${resourceId}">
    <table class="form">
        <tr>
            <th class="formTitle">检验室：
            </th>
            <td class="formValue">
                <select id="enterpriseId" name="dataDictionaryDetailsId" class="txtselect" datacol="yes" err="分类" checkexpession="NotNull">
                    <option value="">==请选择==</option>
                <#if dictionary??&&dictionary?size!=0>
                    <#list dictionary as dy>
                        <option  value="${dy.dataDictionaryDetailsId}">${dy.testCName}</option>
                    </#list>
                </#if>
                </select>
            <th class="formTitle">编号：
            </th>
            <td class="formValue">
                <input class="txt required" id="testEqId" type="text" name="id" onKeyUp="this.value=this.value.replace(/\D/g,'')"/>
            </td>
            </td>
        </tr>
        <tr>
            <th class="formTitle">文字描述：
            </th>
            <td class="formValue" colspan="3">
                <textarea  class="txtArea" style="resize: none" id="testEqRemark" name="remark" rows="5"></textarea>
            </td>
        </tr>
    </table>
    <br/>
    <div class="col-md-12">
        <p><span class="line"></span>上传图片<span class="line"></span></p>
        <div class="image">
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                    <img src=""/>
                </div>
                <div>
                        <span class="btn green-jungle btn-outline btn-file">
                                <span class="fileinput-new"> 添加 </span>
                                <span class="fileinput-exists"> 修改 </span>
                                <input type="file" name="image"/>
                        </span>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
        </div>
    </div>
</form>