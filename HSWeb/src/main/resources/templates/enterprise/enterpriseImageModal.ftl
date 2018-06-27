
<form  style="margin: 1px">
        <div class="col-md-12">
            <div class="image">
                <#if zsEnterprise.businessLicenseImage??&&zsEnterprise.businessLicenseImage!="">
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <p style="text-align: center;"><span class="line"></span>营业执照<span class="line"></span></p>
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                        <img src="<#if zsEnterprise.businessLicenseImage??&&zsEnterprise.businessLicenseImage!="">${zsEnterprise.businessLicenseImage}</#if>"/>
                    </div>
                </div>
                 </#if>
                <#if zsEnterprise.productionLicenseImage??&&zsEnterprise.productionLicenseImage!="">
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <p style="text-align: center;"><span class="line"></span>生产许可证<span class="line"></span></p>
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                        <img src="<#if zsEnterprise.productionLicenseImage??&&zsEnterprise.productionLicenseImage!="">${zsEnterprise.productionLicenseImage}</#if>"/>
                    </div>
                </div>
                </#if>
                <#if zsEnterprise.otherLicenseImageList??&&zsEnterprise.otherLicenseImageList[0]??&&zsEnterprise.otherLicenseImageList[0].imageUrl!="">
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <p style="text-align: center;"><span class="line"></span>其他<span class="line"></span></p>
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                        <img src="<#if zsEnterprise.otherLicenseImageList??&&zsEnterprise.otherLicenseImageList[0]??&&zsEnterprise.otherLicenseImageList[0].imageUrl!="">${zsEnterprise.otherLicenseImageList[0].imageUrl}</#if>"/>
                    </div>
                </div>
                </#if>
                <#if zsEnterprise.otherLicenseImageList??&&zsEnterprise.otherLicenseImageList[1]??&&zsEnterprise.otherLicenseImageList[1].imageUrl!="">
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <p style="text-align: center;"><span class="line"></span>其他<span class="line"></span></p>
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                        <img src="<#if zsEnterprise.otherLicenseImageList??&&zsEnterprise.otherLicenseImageList[1]??&&zsEnterprise.otherLicenseImageList[1].imageUrl!="">${zsEnterprise.otherLicenseImageList[1].imageUrl}</#if>"/>
                    </div>
                </div>
                </#if>
                <#if zsEnterprise.otherLicenseImageList??&&zsEnterprise.otherLicenseImageList[2]??&&zsEnterprise.otherLicenseImageList[2].imageUrl!="">
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <p style="text-align: center;"><span class="line"></span>其他<span class="line"></span></p>
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                        <img src="<#if zsEnterprise.otherLicenseImageList??&&zsEnterprise.otherLicenseImageList[2]??&&zsEnterprise.otherLicenseImageList[2].imageUrl!="">${zsEnterprise.otherLicenseImageList[2].imageUrl}</#if>"/>
                    </div>
                </div>
                </#if>
            </div>
        </div>
</form>