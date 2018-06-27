<#--修改晓芹厨房-->
<form id="updateXqKitchenForm" style="margin: 1px">
    <input type="hidden" name="resourceId" value="${resourceId}">
    <table class="form">
        <tr>
            <th class="formTitle">名称：
            </th>
            <td class="formValue">
                <input  type="hidden"  name="kitchenId" value="<#if xqKitchenById.kitchenId??>${xqKitchenById.kitchenId}</#if>" />
                <input class="txt required" id="updKitchenName" type="text" name="kitchenName" value="<#if xqKitchenById.kitchenName??>${xqKitchenById.kitchenName}</#if>" />
            </td>
            <th class="formTitle">特点：
            </th>
            <td class="formValue">
                <input class="txt required" id="updFeatures" type="text" name="features" value="<#if xqKitchenById.features??>${xqKitchenById.features}</#if>" />
            </td>
        </tr>
        <tr>
            <th class="formTitle">配料：
            </th>
            <td class="formValue" colspan="3">
                <textarea  class="txtArea" style="resize: none" id="updIngredients" name="ingredients" rows="5" placeholder=" "><#if xqKitchenById.ingredients??>${xqKitchenById.ingredients}</#if></textarea>
            </td>
        </tr>
        <tr>
            <th class="formTitle">制作方法：
            </th>
            <td class="formValue" colspan="3">
                <textarea  class="txtArea" style="resize: none" id="updProductionMethod" name="productionMethod" rows="5"><#if xqKitchenById.productionMethod??>${xqKitchenById.productionMethod}</#if></textarea>
            </td>
        </tr>
    </table>
    <br/>
    <div class="col-md-12">
        <p><span class="line"></span>上传图片<span class="line"></span></p>
        <div class="image">
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                <#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[0]??&&xqKitchenById.imagesJson[0].imageUrl!="">
                 <img src="${xqKitchenById.imagesJson[0].imageUrl}" alt=""/>
                </#if>
                </div>
                <div>
                        <span class="btn green-jungle btn-outline btn-file">
                                 <#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[0]??&&xqKitchenById.imagesJson[0].imageUrl!="">
                                     <span class="fileinput-new"> 修改 </span>
                                 <#else>
                                     <span class="fileinput-new"> 添加 </span>
                                 </#if>
                                     <span class="fileinput-exists"> 修改 </span>
                            <input id="updKitchenimagesList" type="file" name="updKitchenimagesList"/>
                        </span>
                <#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[0]??&&xqKitchenById.imagesJson[0].imageUrl!="">
                    <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                </#if>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                <#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[1]??&&xqKitchenById.imagesJson[1].imageUrl!="">
                    <img src="${xqKitchenById.imagesJson[1].imageUrl}" alt=""/>
                </#if>
                </div>
                <div>
                        <span class="btn green-jungle btn-outline btn-file">
                                 <#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[1]??&&xqKitchenById.imagesJson[1].imageUrl!="">
                                     <span class="fileinput-new"> 修改 </span>
                                 <#else>
                                     <span class="fileinput-new"> 添加 </span>
                                 </#if>
                                     <span class="fileinput-exists"> 修改 </span>
                             <input id="updKitchenimagesList" type="file" name="updKitchenimagesList"/>
                        </span>
                <#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[1]??&&xqKitchenById.imagesJson[1].imageUrl!="">
                    <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                </#if>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                <#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[2]??&&xqKitchenById.imagesJson[2].imageUrl!="">
                    <img src="${xqKitchenById.imagesJson[2].imageUrl}" alt=""/>
                </#if>
                </div>
                <div>
                        <span class="btn green-jungle btn-outline btn-file">
                                 <#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[2]??&&xqKitchenById.imagesJson[2].imageUrl!="">
                                     <span class="fileinput-new"> 修改 </span>
                                 <#else>
                                     <span class="fileinput-new"> 添加 </span>
                                 </#if>
                                     <span class="fileinput-exists"> 修改 </span>
                            <input id="updKitchenimagesList" type="file" name="updKitchenimagesList"/>
                        </span>
                <#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[2]??&&xqKitchenById.imagesJson[2].imageUrl!="">
                    <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                </#if>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                <#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[3]??&&xqKitchenById.imagesJson[3].imageUrl!="">
                    <img src="${xqKitchenById.imagesJson[3].imageUrl}" alt=""/>
                </#if>
                </div>
                <div>
                        <span class="btn green-jungle btn-outline btn-file">
                                 <#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[3]??&&xqKitchenById.imagesJson[3].imageUrl!="">
                                     <span class="fileinput-new"> 修改 </span>
                                 <#else>
                                     <span class="fileinput-new"> 添加 </span>
                                 </#if>
                                     <span class="fileinput-exists"> 修改 </span>
                            <input id="updKitchenimagesList" type="file" name="updKitchenimagesList"/>
                        </span>
                <#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[3]??&&xqKitchenById.imagesJson[3].imageUrl!="">
                    <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                </#if>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                <#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[4]??&&xqKitchenById.imagesJson[4].imageUrl!="">
                    <img src="${xqKitchenById.imagesJson[4].imageUrl}" alt=""/>
                </#if>
                </div>
                <div>
                        <span class="btn green-jungle btn-outline btn-file">
                                 <#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[4]??&&xqKitchenById.imagesJson[4].imageUrl!="">
                                     <span class="fileinput-new"> 修改 </span>
                                 <#else>
                                     <span class="fileinput-new"> 添加 </span>
                                 </#if>
                                     <span class="fileinput-exists"> 修改 </span>
                            <input id="updKitchenimagesList" type="file" name="updKitchenimagesList"/>
                        </span>
                <#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[4]??&&xqKitchenById.imagesJson[4].imageUrl!="">
                    <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                </#if>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                <#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[5]??&&xqKitchenById.imagesJson[5].imageUrl!="">
                    <img src="${xqKitchenById.imagesJson[5].imageUrl}" alt=""/>
                </#if>
                </div>
                <div>
                        <span class="btn green-jungle btn-outline btn-file">
                                 <#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[5]??&&xqKitchenById.imagesJson[5].imageUrl!="">
                                     <span class="fileinput-new"> 修改 </span>
                                 <#else>
                                     <span class="fileinput-new"> 添加 </span>
                                 </#if>
                                     <span class="fileinput-exists"> 修改 </span>
                            <input id="updKitchenimagesList" type="file" name="updKitchenimagesList"/>
                        </span>
                <#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[5]??&&xqKitchenById.imagesJson[5].imageUrl!="">
                    <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                </#if>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                <#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[6]??&&xqKitchenById.imagesJson[6].imageUrl!="">
                    <img src="${xqKitchenById.imagesJson[6].imageUrl}" alt=""/>
                </#if>
                </div>
                <div>
                        <span class="btn green-jungle btn-outline btn-file">
                                 <#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[6]??&&xqKitchenById.imagesJson[6].imageUrl!="">
                                     <span class="fileinput-new"> 修改 </span>
                                 <#else>
                                     <span class="fileinput-new"> 添加 </span>
                                 </#if>
                                     <span class="fileinput-exists"> 修改 </span>
                            <input id="updKitchenimagesList" type="file" name="updKitchenimagesList"/>
                        </span>
                <#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[6]??&&xqKitchenById.imagesJson[6].imageUrl!="">
                    <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                </#if>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                <#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[7]??&&xqKitchenById.imagesJson[7].imageUrl!="">
                    <img src="${xqKitchenById.imagesJson[7].imageUrl}" alt=""/>
                </#if>
                </div>
                <div>
                        <span class="btn green-jungle btn-outline btn-file">
                                 <#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[7]??&&xqKitchenById.imagesJson[7].imageUrl!="">
                                     <span class="fileinput-new"> 修改 </span>
                                 <#else>
                                     <span class="fileinput-new"> 添加 </span>
                                 </#if>
                                     <span class="fileinput-exists"> 修改 </span>
                            <input id="updKitchenimagesList" type="file" name="updKitchenimagesList" value=" " />
                        </span>
                <#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[7]??&&xqKitchenById.imagesJson[7].imageUrl!="">
                    <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                </#if>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                <#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[8]??&&xqKitchenById.imagesJson[8].imageUrl!="">
                    <img src="${xqKitchenById.imagesJson[8].imageUrl}" alt=""/>
                </#if>
                </div>
                <div>
                        <span class="btn green-jungle btn-outline btn-file">
                                 <#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[8]??&&xqKitchenById.imagesJson[8].imageUrl!="">
                                     <span class="fileinput-new"> 修改 </span>
                                 <#else>
                                     <span class="fileinput-new"> 添加 </span>
                                 </#if>
                                     <span class="fileinput-exists"> 修改 </span>
                            <input id="updKitchenimagesList" type="file" name="updKitchenimagesList" value=" " />
                        </span>
                <#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[8]??&&xqKitchenById.imagesJson[8].imageUrl!="">
                    <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                </#if>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                <#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[9]??&&xqKitchenById.imagesJson[9].imageUrl!="">
                    <img src="${xqKitchenById.imagesJson[9].imageUrl}" alt=""/>
                </#if>
                </div>
                <div>
                        <span class="btn green-jungle btn-outline btn-file">
                                 <#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[9]??&&xqKitchenById.imagesJson[9].imageUrl!="">
                                     <span class="fileinput-new"> 修改 </span>
                                 <#else>
                                     <span class="fileinput-new"> 添加 </span>
                                 </#if>
                                     <span class="fileinput-exists"> 修改 </span>
                            <input id="updKitchenimagesList" type="file" name="updKitchenimagesList"/>
                        </span>
                <#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[9]??&&xqKitchenById.imagesJson[9].imageUrl!="">
                    <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                </#if>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <input type="hidden" id="updKitchenImageId" name="updKitchenImageId">
        </div>
    </div>
</form>
<script>
    $("input[name = updKitchenimagesList]").on("change",function(){
        var idx = $("input[id = updKitchenimagesList]").index($(this));
        var idxs = $("#updKitchenImageId").val();
        idxs = ""+idxs+idx;
        $("#updKitchenImageId").val(idxs);
    });
</script>