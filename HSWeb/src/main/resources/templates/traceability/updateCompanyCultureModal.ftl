<#--新增-->
<form id="updateCompanyCulture" style="margin: 1px">
    <input type="hidden" name="resourceId" value="${resourceId}">
    <table class="form">
        <tr>
            <th class="formTitle">名称：
            </th>
            <td class="formValue">
                <input type="hidden" name="companyCultureId" value="<#if companyCultureById.companyCultureId??>${companyCultureById.companyCultureId}</#if>" />
                <input class="txt required" id="updCompanyCultureName" type="text" name="companyCultureName" value="<#if companyCultureById.companyCultureName??>${companyCultureById.companyCultureName}</#if>" />
            </td>
        </tr>
        <tr>
            <th class="formTitle">内容：
            </th>
            <td class="formValue">
                <textarea  class="txtArea" style="resize: none" id="updCultureRemark" name="remark" rows="5"><#if companyCultureById.remark??>${companyCultureById.remark}</#if></textarea>
            </td>
        </tr>
    </table>
    <br/>
    <div class="col-md-12">
        <p><span class="line"></span>上传图片<span class="line"></span></p>
        <div class="image">
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                <#if companyCultureById.imageJsonList??&&companyCultureById.imageJsonList[0]??&&companyCultureById.imageJsonList[0].imageUrl!="">
                    <img src="${companyCultureById.imageJsonList[0].imageUrl}" alt="${companyCultureById.imageJsonList[0].imageName}">
                </#if>
                </div>
                <div>
                     <span class="btn green-jungle btn-outline btn-file">
                                <#if companyCultureById.imageJsonList??&&companyCultureById.imageJsonList[0]??&&companyCultureById.imageJsonList[0].imageUrl!="">
                                    <span class="fileinput-new"> 修改 </span>
                                <#else >
                                    <span class="fileinput-new"> 添加 </span>
                                </#if>
                                    <span class="fileinput-exists"> 修改 </span>
                                <input id="updCompanyCultureImage" type="file" name="updCompanyCultureImage"/>
                     </span>
                <#if companyCultureById.imageJsonList??&&companyCultureById.imageJsonList[0]??&&companyCultureById.imageJsonList[0].imageUrl!="">
                    <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                </#if>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                <#if companyCultureById.imageJsonList??&&companyCultureById.imageJsonList[1]??&&companyCultureById.imageJsonList[1].imageUrl!="">
                    <img src="${companyCultureById.imageJsonList[1].imageUrl}" alt="${companyCultureById.imageJsonList[1].imageName}">
                </#if>
                </div>
                <div>
                     <span class="btn green-jungle btn-outline btn-file">
                                <#if companyCultureById.imageJsonList??&&companyCultureById.imageJsonList[1]??&&companyCultureById.imageJsonList[1].imageUrl!="">
                                    <span class="fileinput-new"> 修改 </span>
                                <#else >
                                    <span class="fileinput-new"> 添加 </span>
                                </#if>
                                    <span class="fileinput-exists"> 修改 </span>
                                <input id="updCompanyCultureImage" type="file" name="updCompanyCultureImage"/>
                     </span>
                <#if companyCultureById.imageJsonList??&&companyCultureById.imageJsonList[1]??&&companyCultureById.imageJsonList[1].imageUrl!="">
                    <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                </#if>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                <#if companyCultureById.imageJsonList??&&companyCultureById.imageJsonList[2]??&&companyCultureById.imageJsonList[2].imageUrl!="">
                    <img src="${companyCultureById.imageJsonList[2].imageUrl}" alt="${companyCultureById.imageJsonList[2].imageName}">
                </#if>
                </div>
                <div>
                     <span class="btn green-jungle btn-outline btn-file">
                                <#if companyCultureById.imageJsonList??&&companyCultureById.imageJsonList[2]??&&companyCultureById.imageJsonList[2].imageUrl!="">
                                    <span class="fileinput-new"> 修改 </span>
                                <#else >
                                    <span class="fileinput-new"> 添加 </span>
                                </#if>
                                    <span class="fileinput-exists"> 修改 </span>
                                <input id="updCompanyCultureImage" type="file" name="updCompanyCultureImage"/>
                     </span>
                <#if companyCultureById.imageJsonList??&&companyCultureById.imageJsonList[2]??&&companyCultureById.imageJsonList[2].imageUrl!="">
                    <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                </#if>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                <#if companyCultureById.imageJsonList??&&companyCultureById.imageJsonList[3]??&&companyCultureById.imageJsonList[3].imageUrl!="">
                    <img src="${companyCultureById.imageJsonList[3].imageUrl}" alt="${companyCultureById.imageJsonList[3].imageName}">
                </#if>
                </div>
                <div>
                     <span class="btn green-jungle btn-outline btn-file">
                                <#if companyCultureById.imageJsonList??&&companyCultureById.imageJsonList[3]??&&companyCultureById.imageJsonList[3].imageUrl!="">
                                    <span class="fileinput-new"> 修改 </span>
                                <#else >
                                    <span class="fileinput-new"> 添加 </span>
                                </#if>
                                    <span class="fileinput-exists"> 修改 </span>
                                <input id="updCompanyCultureImage" type="file" name="updCompanyCultureImage"/>
                     </span>
                <#if companyCultureById.imageJsonList??&&companyCultureById.imageJsonList[3]??&&companyCultureById.imageJsonList[3].imageUrl!="">
                    <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                </#if>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                <#if companyCultureById.imageJsonList??&&companyCultureById.imageJsonList[4]??&&companyCultureById.imageJsonList[4].imageUrl!="">
                    <img src="${companyCultureById.imageJsonList[4].imageUrl}" alt="${companyCultureById.imageJsonList[4].imageName}">
                </#if>
                </div>
                <div>
                     <span class="btn green-jungle btn-outline btn-file">
                                <#if companyCultureById.imageJsonList??&&companyCultureById.imageJsonList[4]??&&companyCultureById.imageJsonList[4].imageUrl!="">
                                    <span class="fileinput-new"> 修改 </span>
                                <#else >
                                    <span class="fileinput-new"> 添加 </span>
                                </#if>
                                    <span class="fileinput-exists"> 修改 </span>
                                <input id="updCompanyCultureImage" type="file" name="updCompanyCultureImage"/>
                     </span>
                <#if companyCultureById.imageJsonList??&&companyCultureById.imageJsonList[4]??&&companyCultureById.imageJsonList[4].imageUrl!="">
                    <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                </#if>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                <#if companyCultureById.imageJsonList??&&companyCultureById.imageJsonList[5]??&&companyCultureById.imageJsonList[5].imageUrl!="">
                    <img src="${companyCultureById.imageJsonList[5].imageUrl}" alt="${companyCultureById.imageJsonList[5].imageName}">
                </#if>
                </div>
                <div>
                     <span class="btn green-jungle btn-outline btn-file">
                                <#if companyCultureById.imageJsonList??&&companyCultureById.imageJsonList[5]??&&companyCultureById.imageJsonList[5].imageUrl!="">
                                    <span class="fileinput-new"> 修改 </span>
                                <#else >
                                    <span class="fileinput-new"> 添加 </span>
                                </#if>
                                    <span class="fileinput-exists"> 修改 </span>
                                <input id="updCompanyCultureImage" type="file" name="updCompanyCultureImage"/>
                     </span>
                <#if companyCultureById.imageJsonList??&&companyCultureById.imageJsonList[5]??&&companyCultureById.imageJsonList[5].imageUrl!="">
                    <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                </#if>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                <#if companyCultureById.imageJsonList??&&companyCultureById.imageJsonList[6]??&&companyCultureById.imageJsonList[6].imageUrl!="">
                    <img src="${companyCultureById.imageJsonList[6].imageUrl}" alt="${companyCultureById.imageJsonList[6].imageName}">
                </#if>
                </div>
                <div>
                     <span class="btn green-jungle btn-outline btn-file">
                                <#if companyCultureById.imageJsonList??&&companyCultureById.imageJsonList[6]??&&companyCultureById.imageJsonList[6].imageUrl!="">
                                    <span class="fileinput-new"> 修改 </span>
                                <#else >
                                    <span class="fileinput-new"> 添加 </span>
                                </#if>
                                    <span class="fileinput-exists"> 修改 </span>
                                <input id="updCompanyCultureImage" type="file" name="updCompanyCultureImage"/>
                     </span>
                <#if companyCultureById.imageJsonList??&&companyCultureById.imageJsonList[6]??&&companyCultureById.imageJsonList[6].imageUrl!="">
                    <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                </#if>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                <#if companyCultureById.imageJsonList??&&companyCultureById.imageJsonList[7]??&&companyCultureById.imageJsonList[7].imageUrl!="">
                    <img src="${companyCultureById.imageJsonList[7].imageUrl}" alt="${companyCultureById.imageJsonList[7].imageName}">
                </#if>
                </div>
                <div>
                     <span class="btn green-jungle btn-outline btn-file">
                                <#if companyCultureById.imageJsonList??&&companyCultureById.imageJsonList[7]??&&companyCultureById.imageJsonList[7].imageUrl!="">
                                    <span class="fileinput-new"> 修改 </span>
                                <#else >
                                    <span class="fileinput-new"> 添加 </span>
                                </#if>
                                    <span class="fileinput-exists"> 修改 </span>
                                <input id="updCompanyCultureImage" type="file" name="updCompanyCultureImage"/>
                     </span>
                <#if companyCultureById.imageJsonList??&&companyCultureById.imageJsonList[7]??&&companyCultureById.imageJsonList[7].imageUrl!="">
                    <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                </#if>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                <#if companyCultureById.imageJsonList??&&companyCultureById.imageJsonList[8]??&&companyCultureById.imageJsonList[8].imageUrl!="">
                    <img src="${companyCultureById.imageJsonList[8].imageUrl}" alt="${companyCultureById.imageJsonList[8].imageName}">
                </#if>
                </div>
                <div>
                     <span class="btn green-jungle btn-outline btn-file">
                                <#if companyCultureById.imageJsonList??&&companyCultureById.imageJsonList[8]??&&companyCultureById.imageJsonList[8].imageUrl!="">
                                    <span class="fileinput-new"> 修改 </span>
                                <#else >
                                    <span class="fileinput-new"> 添加 </span>
                                </#if>
                                    <span class="fileinput-exists"> 修改 </span>
                                <input id="updCompanyCultureImage" type="file" name="updCompanyCultureImage"/>
                     </span>
                <#if companyCultureById.imageJsonList??&&companyCultureById.imageJsonList[8]??&&companyCultureById.imageJsonList[8].imageUrl!="">
                    <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                </#if>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                <#if companyCultureById.imageJsonList??&&companyCultureById.imageJsonList[9]??&&companyCultureById.imageJsonList[9].imageUrl!="">
                    <img src="${companyCultureById.imageJsonList[9].imageUrl}" alt="${companyCultureById.imageJsonList[9].imageName}">
                </#if>
                </div>
                <div>
                     <span class="btn green-jungle btn-outline btn-file">
                                <#if companyCultureById.imageJsonList??&&companyCultureById.imageJsonList[9]??&&companyCultureById.imageJsonList[9].imageUrl!="">
                                    <span class="fileinput-new"> 修改 </span>
                                <#else >
                                    <span class="fileinput-new"> 添加 </span>
                                </#if>
                                    <span class="fileinput-exists"> 修改 </span>
                                <input id="updCompanyCultureImage" type="file" name="updCompanyCultureImage"/>
                     </span>
                <#if companyCultureById.imageJsonList??&&companyCultureById.imageJsonList[9]??&&companyCultureById.imageJsonList[9].imageUrl!="">
                    <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                </#if>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <input type="hidden" id="updCompanyCultureImageId" name="updCompanyCultureImageId">
        </div>
    </div>
</form>

<script>

    $("input[name = updCompanyCultureImage]").on("change",function(){
        var idx = $("input[id = updCompanyCultureImage]").index($(this));
        var idxs = $("#updCompanyCultureImageId").val();
        idxs = ""+idxs+idx;
        $("#updCompanyCultureImageId").val(idxs);
    });

</script>