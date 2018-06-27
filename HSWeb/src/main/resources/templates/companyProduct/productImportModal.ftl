
<form id="productImportForm" style="margin: 1px">
    <table class="form">
        <tr>
            <th class="formTitle">产品导入：
            </th>
            <td class="formValue">
                <input type="hidden" name="resourceId" <#if resourceId??>value="${resourceId}"</#if> />
                <div class="fileinput fileinput-new" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail form-control" data-trigger="fileinput" style="width: 300px"></div>
                    <div>
                                <span class="btn green-jungle btn-outline btn-file">
                                    <span class="fileinput-new"> 上传文件 </span>
                                    <span class="fileinput-exists"> 修改 </span>
                                    <input id="productImportFile" class="form-control" type="file" name="productImportFile"/>
                                </span>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput">
                            删除
                        </a>
                    </div>
                </div>
            </td>
        </tr>
        <tr id="excelErrorTr" class="hidden">
            <th class="formTitle">文件错误：
            </th>
            <td class="formValue" id="excelErrorTd">
            </td>
        </tr>
    </table>
</form>