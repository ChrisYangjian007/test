
<form id="addCertificateManageForm" style="margin: 1px">
    <input type="hidden" name="resourceId" value="${resourceId}">
    <table class="form">
        <tr>
            <th class="formTitle">产品大类：
            </th>
            <td class="formValue">
                <select id="bigProductTypeId" name="bigProductTypeId" class="txtselect" datacol="yes" err="分类" checkexpession="NotNull">
                    <option value="">==请选择==</option>
                    <#if productTypeList??&&productTypeList?size!=0>
                        <#list productTypeList as productType>
                            <option  value="${productType.productTypeId}">${productType.CName}</option>
                        </#list>
                    <#else >
                        暂无数据，请去添加
                    </#if>
                </select>
            </td>
            <th class="formTitle">产品小类：
            </th>
            <td class="formValue">
                <select id="smallProductTypeId" name="smallProductTypeId" class="txtselect" datacol="yes" err="分类" checkexpession="NotNull">
                    <option value="">==请选择==</option>
                </select>
            </td>
        </tr>
        <tr>
            <th class="formTitle">生产许可证：
            </th>
            <td class="formValue">
                <input class="txt required" id="productionLicense" type="text" name="productionLicense" />
            </td>
            <th class="formTitle">产品生产过程：
            </th>
            <td class="formValue">
                <select id="productionProcessId" name="productionProcessId" class="txtselect" datacol="yes" err="分类" checkexpession="NotNull">
                    <option value="">==请选择==</option>
                    <#if productionProcessList??&&productionProcessList?size!=0>
                        <#list productionProcessList as productProcess>
                            <option  value="${productProcess.productionProcessId}">${productProcess.productionProcessName}</option>
                        </#list>
                    <#else >
                    暂无数据，请去添加
                </#if>
                </select>
            </td>
        </tr>
        <tr>
            <th class="formTitle">检验结果：
            </th>
            <td class="formValue">
                <select id="testResult" name="testResult" class="txtselect" datacol="yes" err="分类" checkexpession="NotNull">
                    <option value="1">检验合格</option>
                    <option value="2">检验不合格</option>
                </select>
            </td>
            <th class="formTitle">到期日：
            </th>
            <td class="formValue">
                <input id="endDate" name="endDate" type="text" class="txt Wdate" style="width: 100%; "
                       onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">产品标准号：
            </th>
            <td class="formValue">
                <input class="txt required" id="productStandards" type="text" name="productStandards" onKeyUp="this.value=this.value.replace(/[a-z\u4E00-\u9FA5]/g,'')"/>
            </td>
        </tr>
    </table>
    <div class="col-md-12">
        <p><span class="line"></span>上传图片<span class="line"></span></p>
        <div class="image">
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                    <img src=""/>
                </div>
                <div>
                        <span class="btn green-jungle btn-outline btn-file">
                                <span class="fileinput-new"> 添加 </span>
                                <span class="fileinput-exists"> 修改 </span>
                                <input type="file" name="images1"/>
                        </span>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                    <img src=""/>
                </div>
                <div>
                        <span class="btn green-jungle btn-outline btn-file">
                                <span class="fileinput-new"> 添加 </span>
                                <span class="fileinput-exists"> 修改 </span>
                                <input type="file" name="images2"/>
                        </span>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                    <img src=""/>
                </div>
                <div>
                        <span class="btn green-jungle btn-outline btn-file">
                                <span class="fileinput-new"> 添加 </span>
                                <span class="fileinput-exists"> 修改 </span>
                                <input type="file" name="images3"/>
                        </span>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                    <img src=""/>
                </div>
                <div>
                        <span class="btn green-jungle btn-outline btn-file">
                                <span class="fileinput-new"> 添加 </span>
                                <span class="fileinput-exists"> 修改 </span>
                                <input type="file" name="images4"/>
                        </span>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                    <img src=""/>
                </div>
                <div>
                        <span class="btn green-jungle btn-outline btn-file">
                                <span class="fileinput-new"> 添加 </span>
                                <span class="fileinput-exists"> 修改 </span>
                                <input type="file" name="images5"/>
                        </span>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                    <img src=""/>
                </div>
                <div>
                        <span class="btn green-jungle btn-outline btn-file">
                                <span class="fileinput-new"> 添加 </span>
                                <span class="fileinput-exists"> 修改 </span>
                                <input type="file" name="images6"/>
                        </span>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                    <img src=""/>
                </div>
                <div>
                        <span class="btn green-jungle btn-outline btn-file">
                                <span class="fileinput-new"> 添加 </span>
                                <span class="fileinput-exists"> 修改 </span>
                                <input type="file" name="images7"/>
                        </span>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                    <img src=""/>
                </div>
                <div>
                        <span class="btn green-jungle btn-outline btn-file">
                                <span class="fileinput-new"> 添加 </span>
                                <span class="fileinput-exists"> 修改 </span>
                                <input type="file" name="images8"/>
                        </span>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                    <img src=""/>
                </div>
                <div>
                        <span class="btn green-jungle btn-outline btn-file">
                                <span class="fileinput-new"> 添加 </span>
                                <span class="fileinput-exists"> 修改 </span>
                                <input type="file" name="images9"/>
                        </span>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                    <img src=""/>
                </div>
                <div>
                        <span class="btn green-jungle btn-outline btn-file">
                                <span class="fileinput-new"> 添加 </span>
                                <span class="fileinput-exists"> 修改 </span>
                                <input type="file" name="images10"/>
                        </span>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
        </div>
    </div>
</form>

<script>

    $("#bigProductTypeId").on("change",function(){
        var productTypeHtml = "<option value=''>==请选择==</option>";
        var addProductProductTypeName =$("#smallProductTypeId");
        addProductProductTypeName.html(productTypeHtml);
        var productCategoryId =$("#bigProductTypeId :selected").val();
        if (""!=productCategoryId){
            $.post('${request.contextPath}/productType/getSysProductTypeByParentId.json', {parentId:productCategoryId}, function (result) {
                if(result.success){
                    for (var i=0;i<result.obj.length;i++){
                        productTypeHtml+='<option id="'+result.obj[i].productTypeId+'" value="'+result.obj[i].productTypeId+'">'+result.obj[i].cname+'</option>';
                    }
                }
                addProductProductTypeName.html(productTypeHtml);
                Loading(false);
            }, "JSON");
        }else {
            addProductProductTypeName.html(productTypeHtml);
        }
    });

</script>