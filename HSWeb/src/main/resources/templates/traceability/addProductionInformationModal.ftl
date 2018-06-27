<#--新增生产控制点-->
<form id="addProduction" style="margin: 1px">
    <input type="hidden" name="resourceId" value="${resourceId}">
    <table class="form">
        <tr>
            <th class="formTitle">编号：
            </th>
            <td class="formValue">
                <input class="txt required" id="productionInformationId" type="text" name="id" onKeyUp="this.value=this.value.replace(/\D/g,'')"/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">名称：
            </th>
            <td class="formValue">
                <input class="txt required" id="productionInformationName" type="text" name="productionInformationName"/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">文字描述：
            </th>
            <td class="formValue">
                <textarea  class="txtArea" style="resize: none" id="productionInformationRemark" name="remark" rows="5"></textarea>
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