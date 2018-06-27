
<#--新增-->
<form id="updateEnterpriseForm" style="margin: 1px">
    <table class="form">
                <table class="form">
                    <tr>
                        <th class="formTitle">供应商名称：
                        </th>
                        <td class="formValue">
                            <input type="hidden" id="enterpriseId" name="enterpriseId" value="${zsEnterprise.enterpriseId}"/>
                            <input type="hidden" name="resourceId" <#if resourceId??>value="${resourceId}"</#if> />
                            <input  class="txt required" id="cNameUp" type="text" class="txt" name="cName" value="<#if zsEnterprise.CName??>${zsEnterprise.CName}<#else ></#if>" />
                        </td>
                        <th class="formTitle">供应货品：
                        </th>
                        <td class="formValue">
                            <input id="shortNameUp" value="<#if zsEnterprise.shortName??>${zsEnterprise.shortName}<#else ></#if>" type="text" class="txt" name="shortName" />
                        </td>
                    </tr>
                    <tr>
                        <th class="formTitle">所属海域：
                        </th>
                        <td class="formValue">
                            <input id="corporateRepUp" value="<#if zsEnterprise.corporateRep??>${zsEnterprise.corporateRep}<#else ></#if>"type="text" class="txt" name="corporateRep"  />
                        </td>
                        <th class="formTitle">联系人：
                        </th>
                        <td class="formValue">
                            <input  class="txt required" id="contactUp" type="text" class="txt" name="contact"  value="<#if zsEnterprise.contact??>${zsEnterprise.contact}<#else ></#if>" />
                        </td>
                    </tr>
                    <tr>
                        <th class="formTitle">联系方式：
                        </th>
                        <td class="formValue">
                            <input id="phoneUp" type="text"  value="<#if zsEnterprise.phone??>${zsEnterprise.phone}<#else ></#if>" class="txt" name="phone" />
                        </td>
                        <th class="formTitle">详细地址：
                        </th>
                        <td class="formValue">
                            <input id="addressUp" type="text" value="<#if zsEnterprise.address??>${zsEnterprise.address}<#else ></#if>" class="txt" name="address" />
                        </td>
                    </tr>
                    <tr>
                        <th class="formTitle">电子邮件：
                        </th>
                        <td class="formValue">
                            <input id="emailUp" type="text" value="<#if zsEnterprise.email??>${zsEnterprise.email}<#else ></#if>" class="txt" name="email" />
                        </td>
                        <th class="formTitle">供应商类型：
                        </th>
                        <td class="formValue">
                        <#--<input id="enterpriseType" type="text" class="txt" name="enterpriseType" />-->
                            <select id="enterpriseTypeUp" name="enterpriseType" class="txtselect" datacol="yes" err="分类" checkexpession="NotNull">
                                <#if zsEnterprise.enterpriseType??>
                                    <option value="">=请选择=</option>
                                    <option value="1" <#if zsEnterprise.enterpriseType==1>selected</#if>>原料供应</option>
                                    <option value="2" <#if zsEnterprise.enterpriseType==2>selected</#if>>其他供应</option>
                                 <#else >
                                     <option value="">=请选择=</option>
                                     <option value="1">原料供应</option>
                                     <option value="2">其他供应</option>
                                </#if>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th class="formTitle">营业有效期：
                        </th>
                        <td class="formValue">
                            <input id="businessLicenseDateUp" name="businessLicenseDate" type="text" class="txt Wdate" value="<#if zsEnterprise.businessLicenseDate??>${zsEnterprise.businessLicenseDate?string("yyyy-MM-dd")}</#if>" style="width: 100%; "
                                   onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})"/>
                        </td>
                        <th class="formTitle">生许有效期：
                        </th>
                        <td class="formValue">
                            <input id="productionLicenseDateUp" name="productionLicenseDate" type="text" class="txt Wdate" value="<#if zsEnterprise.productionLicenseDate??>${zsEnterprise.productionLicenseDate?string("yyyy-MM-dd")}</#if>" style="width: 100%; "
                                   onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})"/>
                        </td>
                    </tr>
                    <tr>
                        <th class="formTitle">其他有效期：
                        </th>
                        <td class="formValue">
                            <input id="otherLicenseDateUp" name="otherLicenseDate" type="text" class="txt Wdate" value="<#if zsEnterprise.otherLicenseDate??>${zsEnterprise.otherLicenseDate?string("yyyy-MM-dd")}</#if>" style="width: 100%; "
                                   onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})"/>
                        </td>
                    </tr>
    </table>
        <div class="col-md-12">
            <div class="image">
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <p style="text-align: center;"><span class="line"></span>营业执照<span class="line"></span></p>
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                        <img src="<#if zsEnterprise.businessLicenseImage??&&zsEnterprise.businessLicenseImage!="">${zsEnterprise.businessLicenseImage}</#if>"/>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if zsEnterprise.businessLicenseImage??&&zsEnterprise.businessLicenseImage!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else >
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                                <span class="fileinput-exists"> 修改 </span>
                                <input id="updLicenseImage1" type="file" name="updLicenseImage1"/>
                        </span>
                        <#if zsEnterprise.businessLicenseImage??&&zsEnterprise.businessLicenseImage!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <input type="hidden" id="updLicenseImageId1" name="updLicenseImageId1" value="<#if zsEnterprise.businessLicenseImage??&&zsEnterprise.businessLicenseImage!="">3<#else >1</#if>">
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <p style="text-align: center;"><span class="line"></span>生产许可证<span class="line"></span></p>
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                        <img src="<#if zsEnterprise.productionLicenseImage??&&zsEnterprise.productionLicenseImage!="">${zsEnterprise.productionLicenseImage}</#if>"/>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if zsEnterprise.productionLicenseImage??&&zsEnterprise.productionLicenseImage!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else >
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                                <span class="fileinput-exists"> 修改 </span>
                                <input id="updLicenseImage2" type="file" name="updLicenseImage2"/>
                        </span>
                        <#if zsEnterprise.productionLicenseImage??&&zsEnterprise.productionLicenseImage!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <input type="hidden" id="updLicenseImageId2" name="updLicenseImageId2" value="<#if zsEnterprise.productionLicenseImage??&&zsEnterprise.productionLicenseImage!="">3<#else >1</#if>">
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <p style="text-align: center;"><span class="line"></span>其他<span class="line"></span></p>
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                        <img src="<#if zsEnterprise.otherLicenseImageList??&&zsEnterprise.otherLicenseImageList[0]??&&zsEnterprise.otherLicenseImageList[0].imageUrl!="">${zsEnterprise.otherLicenseImageList[0].imageUrl}</#if>"/>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if zsEnterprise.otherLicenseImageList??&&zsEnterprise.otherLicenseImageList[0]??&&zsEnterprise.otherLicenseImageList[0].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else >
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                                <span class="fileinput-exists"> 修改 </span>
                                <input id="updLicenseImage3" type="file" name="updLicenseImage3"/>
                        </span>
                        <#if zsEnterprise.otherLicenseImageList??&&zsEnterprise.otherLicenseImageList[0]??&&zsEnterprise.otherLicenseImageList[0].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <input type="hidden" id="updLicenseImageId3" name="updLicenseImageId3" value="1">
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <p style="text-align: center;"><span class="line"></span>其他<span class="line"></span></p>
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                        <img src="<#if zsEnterprise.otherLicenseImageList??&&zsEnterprise.otherLicenseImageList[1]??&&zsEnterprise.otherLicenseImageList[1].imageUrl!="">${zsEnterprise.otherLicenseImageList[1].imageUrl}</#if>"/>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if zsEnterprise.otherLicenseImageList??&&zsEnterprise.otherLicenseImageList[1]??&&zsEnterprise.otherLicenseImageList[1].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else >
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                                <span class="fileinput-exists"> 修改 </span>
                                <input id="updLicenseImage4" type="file" name="updLicenseImage4"/>
                        </span>
                        <#if zsEnterprise.otherLicenseImageList??&&zsEnterprise.otherLicenseImageList[1]??&&zsEnterprise.otherLicenseImageList[1].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <input type="hidden" id="updLicenseImageId4" name="updLicenseImageId4" value="1">
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <p style="text-align: center;"><span class="line"></span>其他<span class="line"></span></p>
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                        <img src="<#if zsEnterprise.otherLicenseImageList??&&zsEnterprise.otherLicenseImageList[2]??&&zsEnterprise.otherLicenseImageList[2].imageUrl!="">${zsEnterprise.otherLicenseImageList[2].imageUrl}</#if>"/>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if zsEnterprise.otherLicenseImageList??&&zsEnterprise.otherLicenseImageList[2]??&&zsEnterprise.otherLicenseImageList[2].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else >
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                                <span class="fileinput-exists"> 修改 </span>
                                <input id="updLicenseImage5" type="file" name="updLicenseImage5"/>
                        </span>
                        <#if zsEnterprise.otherLicenseImageList??&&zsEnterprise.otherLicenseImageList[2]??&&zsEnterprise.otherLicenseImageList[2].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <input type="hidden" id="updLicenseImageId5" name="updLicenseImageId5" value="1">
            </div>
        </div>
</form>

<script>


     $("#updLicenseImage1").on("change",function(){
         $("#updLicenseImageId1").val("2");
     });
     $("#updLicenseImage2").on("change",function(){
         $("#updLicenseImageId2").val("2");
     });
     $("#updLicenseImage3").on("change",function(){
         $("#updLicenseImageId3").val("2");
     });
     $("#updLicenseImage4").on("change",function(){
         $("#updLicenseImageId4").val("2");
     });
     $("#updLicenseImage5").on("change",function(){
         $("#updLicenseImageId5").val("2");
     });

</script>