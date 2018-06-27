
<form id="updateCertificateManageForm" style="margin: 1px">
    <input type="hidden" name="certificateManageId" value="${pZsCertificateManage.certificateManageId}">
    <input type="hidden" name="resourceId" value="${resourceId}">
    <table class="form">
        <tr>
            <th class="formTitle">产品大类：
            </th>
            <td class="formValue">
                <select id="updBigProductTypeId" name="bigProductTypeId" class="txtselect" datacol="yes" err="分类" checkexpession="NotNull">
                <#if productTypeList??&&productTypeList?size!=0>
                    <#list productTypeList as productType>
                        <option  value="${productType.productTypeId}" <#if pZsCertificateManage.bigProductTypeId==productType.productTypeId>selected</#if>>${productType.CName}</option>
                    </#list>
                <#else >
                    暂无数据，请去添加
                </#if>
                </select>
            </td>
            <th class="formTitle">产品小类：
            </th>
            <td class="formValue">
                <select id="updSmallProductTypeId" name="smallProductTypeId" class="txtselect" datacol="yes" err="分类" checkexpession="NotNull">
                    <#if smallProductTypes??&&smallProductTypes?size!=0>
                        <#list smallProductTypes as productType>
                            <option  value="${productType.productTypeId}" <#if pZsCertificateManage.smallProductTypeId==productType.productTypeId>selected</#if>>${productType.CName}</option>
                        </#list>
                    <#else >
                        暂无数据，请去添加
                    </#if>
                </select>
            </td>
        </tr>
        <tr>
            <th class="formTitle">生产许可证：
            </th>
            <td class="formValue">
                <input class="txt required" id="updProductionLicense" type="text" name="productionLicense" value="${pZsCertificateManage.productionLicense}" />
            </td>
            <th class="formTitle">产品生产过程：
            </th>
            <td class="formValue">
                <select id="updProductionProcessId" name="productionProcessId" class="txtselect" datacol="yes" err="分类" checkexpession="NotNull">
                <#if productionProcessList??>
                    <#list productionProcessList as productProcess>
                        <option  value="${productProcess.productionProcessId}" <#if pZsCertificateManage.productionProcessId==productProcess.productionProcessId>selected</#if>>${productProcess.productionProcessName}</option>
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
                <select id="updTestResult" name="testResult" class="txtselect" datacol="yes" err="分类" checkexpession="NotNull">
                    <option value="1" <#if pZsCertificateManage.testResult==1>selected</#if>>检验合格</option>
                    <option value="2" <#if pZsCertificateManage.testResult==2>selected</#if>>检验不合格</option>
                </select>
            </td>
            <th class="formTitle">到期日：
            </th>
            <td class="formValue">
                <input id="updEndDate" name="endDate" type="text" class="txt Wdate" style="width: 100%; " value="${pZsCertificateManage.endDate?string("yyyy-MM-dd HH:mm:ss")}"
                       onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">产品标准号：
            </th>
            <td class="formValue">
                <input class="txt required" id="updProductStandards" type="text" name="productStandards" value="<#if pZsCertificateManage.productStandards??>${pZsCertificateManage.productStandards}</#if>"
                       onKeyUp="this.value=this.value.replace(/[a-z\u4E00-\u9FA5]/g,'')"/>
            </td>
        </tr>
    </table>
    <div class="col-md-12">
        <p><span class="line"></span>上传图片<span class="line"></span></p>
        <div class="image">
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                    <#if pZsCertificateManage.imageJsonList??&&pZsCertificateManage.imageJsonList[0]??&&pZsCertificateManage.imageJsonList[0].imageUrl!="">
                        <img src="${pZsCertificateManage.imageJsonList[0].imageUrl}" alt="${pZsCertificateManage.imageJsonList[0].imageName}">
                    </#if>
                </div>
                <div>
                     <span class="btn green-jungle btn-outline btn-file">
                                <#if pZsCertificateManage.imageJsonList??&&pZsCertificateManage.imageJsonList[0]??&&pZsCertificateManage.imageJsonList[0].imageUrl!="">
                                    <span class="fileinput-new"> 修改 </span>
                                <#else >
                                    <span class="fileinput-new"> 添加 </span>
                                </#if>
                                    <span class="fileinput-exists"> 修改 </span>
                                <input id="imageList" type="file" name="imageList"/>
                     </span>
                    <#if pZsCertificateManage.imageJsonList??&&pZsCertificateManage.imageJsonList[0]??&&pZsCertificateManage.imageJsonList[0].imageUrl!="">
                        <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                    </#if>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                <#if pZsCertificateManage.imageJsonList??&&pZsCertificateManage.imageJsonList[1]??&&pZsCertificateManage.imageJsonList[1].imageUrl!="">
                    <img src="${pZsCertificateManage.imageJsonList[1].imageUrl}" alt="${pZsCertificateManage.imageJsonList[1].imageName}">
                </#if>
                </div>
                <div>
                     <span class="btn green-jungle btn-outline btn-file">
                                <#if pZsCertificateManage.imageJsonList??&&pZsCertificateManage.imageJsonList[1]??&&pZsCertificateManage.imageJsonList[1].imageUrl!="">
                                    <span class="fileinput-new"> 修改 </span>
                                <#else >
                                    <span class="fileinput-new"> 添加 </span>
                                </#if>
                                    <span class="fileinput-exists"> 修改 </span>
                                <input id="imageList" type="file" name="imageList"/>
                     </span>
                <#if pZsCertificateManage.imageJsonList??&&pZsCertificateManage.imageJsonList[1]??&&pZsCertificateManage.imageJsonList[1].imageUrl!="">
                    <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                </#if>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                <#if pZsCertificateManage.imageJsonList??&&pZsCertificateManage.imageJsonList[2]??&&pZsCertificateManage.imageJsonList[2].imageUrl!="">
                    <img src="${pZsCertificateManage.imageJsonList[2].imageUrl}" alt="${pZsCertificateManage.imageJsonList[2].imageName}">
                </#if>
                </div>
                <div>
                     <span class="btn green-jungle btn-outline btn-file">
                                <#if pZsCertificateManage.imageJsonList??&&pZsCertificateManage.imageJsonList[2]??&&pZsCertificateManage.imageJsonList[2].imageUrl!="">
                                    <span class="fileinput-new"> 修改 </span>
                                <#else >
                                    <span class="fileinput-new"> 添加 </span>
                                </#if>
                                    <span class="fileinput-exists"> 修改 </span>
                                <input id="imageList" type="file" name="imageList"/>
                     </span>
                <#if pZsCertificateManage.imageJsonList??&&pZsCertificateManage.imageJsonList[2]??&&pZsCertificateManage.imageJsonList[2].imageUrl!="">
                    <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                </#if>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                <#if pZsCertificateManage.imageJsonList??&&pZsCertificateManage.imageJsonList[3]??&&pZsCertificateManage.imageJsonList[3].imageUrl!="">
                    <img src="${pZsCertificateManage.imageJsonList[3].imageUrl}" alt="${pZsCertificateManage.imageJsonList[3].imageName}">
                </#if>
                </div>
                <div>
                     <span class="btn green-jungle btn-outline btn-file">
                                <#if pZsCertificateManage.imageJsonList??&&pZsCertificateManage.imageJsonList[3]??&&pZsCertificateManage.imageJsonList[3].imageUrl!="">
                                    <span class="fileinput-new"> 修改 </span>
                                <#else >
                                    <span class="fileinput-new"> 添加 </span>
                                </#if>
                                    <span class="fileinput-exists"> 修改 </span>
                                <input id="imageList" type="file" name="imageList"/>
                     </span>
                <#if pZsCertificateManage.imageJsonList??&&pZsCertificateManage.imageJsonList[3]??&&pZsCertificateManage.imageJsonList[3].imageUrl!="">
                    <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                </#if>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                <#if pZsCertificateManage.imageJsonList??&&pZsCertificateManage.imageJsonList[4]??&&pZsCertificateManage.imageJsonList[4].imageUrl!="">
                    <img src="${pZsCertificateManage.imageJsonList[4].imageUrl}" alt="${pZsCertificateManage.imageJsonList[4].imageName}">
                </#if>
                </div>
                <div>
                     <span class="btn green-jungle btn-outline btn-file">
                                <#if pZsCertificateManage.imageJsonList??&&pZsCertificateManage.imageJsonList[4]??&&pZsCertificateManage.imageJsonList[4].imageUrl!="">
                                    <span class="fileinput-new"> 修改 </span>
                                <#else >
                                    <span class="fileinput-new"> 添加 </span>
                                </#if>
                                    <span class="fileinput-exists"> 修改 </span>
                                <input id="imageList" type="file" name="imageList"/>
                     </span>
                <#if pZsCertificateManage.imageJsonList??&&pZsCertificateManage.imageJsonList[4]??&&pZsCertificateManage.imageJsonList[4].imageUrl!="">
                    <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                </#if>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                <#if pZsCertificateManage.imageJsonList??&&pZsCertificateManage.imageJsonList[5]??&&pZsCertificateManage.imageJsonList[5].imageUrl!="">
                    <img src="${pZsCertificateManage.imageJsonList[5].imageUrl}" alt="${pZsCertificateManage.imageJsonList[5].imageName}">
                </#if>
                </div>
                <div>
                     <span class="btn green-jungle btn-outline btn-file">
                                <#if pZsCertificateManage.imageJsonList??&&pZsCertificateManage.imageJsonList[5]??&&pZsCertificateManage.imageJsonList[5].imageUrl!="">
                                    <span class="fileinput-new"> 修改 </span>
                                <#else >
                                    <span class="fileinput-new"> 添加 </span>
                                </#if>
                                    <span class="fileinput-exists"> 修改 </span>
                                <input id="imageList" type="file" name="imageList"/>
                     </span>
                <#if pZsCertificateManage.imageJsonList??&&pZsCertificateManage.imageJsonList[5]??&&pZsCertificateManage.imageJsonList[5].imageUrl!="">
                    <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                </#if>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                <#if pZsCertificateManage.imageJsonList??&&pZsCertificateManage.imageJsonList[6]??&&pZsCertificateManage.imageJsonList[6].imageUrl!="">
                    <img src="${pZsCertificateManage.imageJsonList[6].imageUrl}" alt="${pZsCertificateManage.imageJsonList[6].imageName}">
                </#if>
                </div>
                <div>
                     <span class="btn green-jungle btn-outline btn-file">
                                <#if pZsCertificateManage.imageJsonList??&&pZsCertificateManage.imageJsonList[6]??&&pZsCertificateManage.imageJsonList[6].imageUrl!="">
                                    <span class="fileinput-new"> 修改 </span>
                                <#else >
                                    <span class="fileinput-new"> 添加 </span>
                                </#if>
                                    <span class="fileinput-exists"> 修改 </span>
                                <input id="imageList" type="file" name="imageList"/>
                     </span>
                <#if pZsCertificateManage.imageJsonList??&&pZsCertificateManage.imageJsonList[6]??&&pZsCertificateManage.imageJsonList[6].imageUrl!="">
                    <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                </#if>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                <#if pZsCertificateManage.imageJsonList??&&pZsCertificateManage.imageJsonList[7]??&&pZsCertificateManage.imageJsonList[7].imageUrl!="">
                    <img src="${pZsCertificateManage.imageJsonList[7].imageUrl}" alt="${pZsCertificateManage.imageJsonList[7].imageName}">
                </#if>
                </div>
                <div>
                     <span class="btn green-jungle btn-outline btn-file">
                                <#if pZsCertificateManage.imageJsonList??&&pZsCertificateManage.imageJsonList[7]??&&pZsCertificateManage.imageJsonList[7].imageUrl!="">
                                    <span class="fileinput-new"> 修改 </span>
                                <#else >
                                    <span class="fileinput-new"> 添加 </span>
                                </#if>
                                    <span class="fileinput-exists"> 修改 </span>
                                <input id="imageList" type="file" name="imageList"/>
                     </span>
                <#if pZsCertificateManage.imageJsonList??&&pZsCertificateManage.imageJsonList[7]??&&pZsCertificateManage.imageJsonList[7].imageUrl!="">
                    <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                </#if>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                <#if pZsCertificateManage.imageJsonList??&&pZsCertificateManage.imageJsonList[8]??&&pZsCertificateManage.imageJsonList[8].imageUrl!="">
                    <img src="${pZsCertificateManage.imageJsonList[8].imageUrl}" alt="${pZsCertificateManage.imageJsonList[8].imageName}">
                </#if>
                </div>
                <div>
                     <span class="btn green-jungle btn-outline btn-file">
                                <#if pZsCertificateManage.imageJsonList??&&pZsCertificateManage.imageJsonList[8]??&&pZsCertificateManage.imageJsonList[8].imageUrl!="">
                                    <span class="fileinput-new"> 修改 </span>
                                <#else >
                                    <span class="fileinput-new"> 添加 </span>
                                </#if>
                                    <span class="fileinput-exists"> 修改 </span>
                                <input id="imageList" type="file" name="imageList"/>
                     </span>
                <#if pZsCertificateManage.imageJsonList??&&pZsCertificateManage.imageJsonList[8]??&&pZsCertificateManage.imageJsonList[8].imageUrl!="">
                    <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                </#if>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                <#if pZsCertificateManage.imageJsonList??&&pZsCertificateManage.imageJsonList[9]??&&pZsCertificateManage.imageJsonList[9].imageUrl!="">
                    <img src="${pZsCertificateManage.imageJsonList[9].imageUrl}" alt="${pZsCertificateManage.imageJsonList[9].imageName}">
                </#if>
                </div>
                <div>
                     <span class="btn green-jungle btn-outline btn-file">
                                <#if pZsCertificateManage.imageJsonList??&&pZsCertificateManage.imageJsonList[9]??&&pZsCertificateManage.imageJsonList[9].imageUrl!="">
                                    <span class="fileinput-new"> 修改 </span>
                                <#else >
                                    <span class="fileinput-new"> 添加 </span>
                                </#if>
                                    <span class="fileinput-exists"> 修改 </span>
                                <input id="imageList" type="file" name="imageList"/>
                     </span>
                <#if pZsCertificateManage.imageJsonList??&&pZsCertificateManage.imageJsonList[9]??&&pZsCertificateManage.imageJsonList[9].imageUrl!="">
                    <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                </#if>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <input type="hidden" id="imagesId" name="imagesId">
        </div>
    </div>
</form>

<script>

    $("#updBigProductTypeId").on("change",function(){
        var productTypeHtml = "<option value=''>==请选择==</option>";
        var addProductProductTypeName =$("#updSmallProductTypeId");
        addProductProductTypeName.html(productTypeHtml);
        var productCategoryId =$("#updBigProductTypeId :selected").val();
        if (""!=productCategoryId){
            $.post('${request.contextPath}/productType/getSysProductTypeByParentId.json', {parentId:productCategoryId}, function (result) {
                if(result.success){
                    for (var i=0;i<result.obj.length;i++){
                        productTypeHtml+='<option value="'+result.obj[i].productTypeId+'">'+result.obj[i].cname+'</option>';
                    }
                }
                addProductProductTypeName.html(productTypeHtml);
                Loading(false);
            }, "JSON");
        }else {
            addProductProductTypeName.html(productTypeHtml);
        }
    });

    $("input[name = imageList]").on("change",function(){
        var idx = $("input[id = imageList]").index($(this));
        var idxs = $("#imagesId").val();
        idxs = ""+idxs+idx;
        $("#imagesId").val(idxs);
    });

</script>