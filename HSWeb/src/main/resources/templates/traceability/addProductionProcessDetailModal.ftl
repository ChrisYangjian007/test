<form id="addProductionProcessDetailForm" style="margin: 1px">
    <input type="hidden" name="resourceId" value="${resourceId}">
    <table class="form">
        <tr>
            <th class="formTitle">编号：
            </th>
            <td class="formValue">
                <input class="txt required" id="productionProcessDetailNumber" type="text" name="productionProcessDetailNumber" onKeyUp="this.value=this.value.replace(/\D/g,'')"/>
            </td>
            <th class="formTitle">生产过程名称：
            </th>
            <td class="formValue">
                <input type="hidden" name="productionProcessId" value="${zsProductionProcess.productionProcessId}">
                <input class="txt required" type="text" value="${zsProductionProcess.productionProcessName}" readonly disabled/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">过程详情名称：
            </th>
            <td class="formValue" colspan="3">
                <input class="txt required" id="productionProcessDetailName" name="productionProcessDetailName" type="text" />
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