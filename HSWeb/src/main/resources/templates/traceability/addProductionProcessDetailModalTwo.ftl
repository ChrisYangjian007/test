<form id="addProductionProcessDetailFormTwo" style="margin: 1px">
    <input type="hidden" name="resourceId" value="${resourceId}">
    <table class="form">
        <tr>
            <th class="formTitle">编号：
            </th>
            <td class="formValue">
                <input class="txt required" id="addProductionProcessDetailNumber2" type="text" class="txt" name="productionProcessDetailNumber"
                       onKeyUp="this.value=this.value.replace(/\D/g,'')"/>
            </td>
            <th class="formTitle">生产过程：
            </th>
            <td class="formValue">
                <select class="txtselect" id="addProductionProcessId2" name="productionProcessId">
                 <option value="">=请选择=</option>
                <#if productionProcessList??&&productionProcessList?size!=0>
                    <#list productionProcessList as productionProcess>
                        <option value="${productionProcess.productionProcessId}">${productionProcess.productionProcessName}</option>
                    </#list>
                </#if>
                </select>
            </td>
        </tr>
        <tr>
            <th class="formTitle">过程详情名称：
            </th>
            <td class="formValue" colspan="3">
                <input class="txt required" id="addProductionProcessDetailName2" name="productionProcessDetailName" type="text" />
            </td>
        </tr>
        <tr>
            <th class="formTitle">文字描述：
            </th>
            <td class="formValue" colspan="3">
                <textarea  class="txtArea" style="resize: none" name="processDescription" rows="5"></textarea>
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
