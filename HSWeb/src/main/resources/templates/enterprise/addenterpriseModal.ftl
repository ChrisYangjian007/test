
<#--新增-->
<form id="addenterpriseForm" style="margin: 1px">
    <table class="form">
        <tr>
            <th class="formTitle">供应商名称：
            </th>
            <td class="formValue">
                <input type="hidden" name="resourceId" <#if resourceId??>value="${resourceId}"</#if> />
                <input class="txt required" id="cName" type="text" class="txt" name="cName"/>
            </td>
            <th class="formTitle">供应货品：
            </th>
            <td class="formValue">
                <input id="shortName" type="text" class="txt" name="shortName" />
            </td>
        </tr>
        <tr>
            <th class="formTitle">所属海域：
            </th>
            <td class="formValue">
                <input id="corporateRep" type="text" class="txt" name="corporateRep" />
            </td>
            <th class="formTitle">联系人：
            </th>
            <td class="formValue">
                <input class="txt required" id="contact" type="text" class="txt" name="contact" />
            </td>
        </tr>
        <tr>
            <th class="formTitle">联系方式：
            </th>
            <td class="formValue">
                <input id="phone" type="text" class="txt" name="phone" />
            </td>
            <th class="formTitle">详细地址：
            </th>
            <td class="formValue">
                <input id="address" type="text" class="txt" name="address" />
            </td>
        </tr>
        <tr>
            <th class="formTitle">电子邮件：
            </th>
            <td class="formValue">
                <input id="email" type="text" class="txt" name="email" />
            </td>
            <th class="formTitle">供应商类型：
            </th>
            <td class="formValue">
                <#--<input id="enterpriseType" type="text" class="txt" name="enterpriseType" />-->
                <select id="enterpriseType" name="enterpriseType" class="txtselect" datacol="yes" err="分类" checkexpession="NotNull">
                    <option value="" selected>=请选择=</option>
                    <option value="1">原料供应</option>
                    <option value="2">其他供应</option>
                </select>
            </td>
        </tr>
        <tr>
            <th class="formTitle">营业有效期：
            </th>
            <td class="formValue">
                <input id="businessLicenseDate" name="businessLicenseDate" type="text" class="txt Wdate" style="width: 100%; "
                       onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})"/>
            </td>
            <th class="formTitle">生许有效期：
            </th>
            <td class="formValue">
                <input id="productionLicenseDate" name="productionLicenseDate" type="text" class="txt Wdate" style="width: 100%; "
                       onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})"/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">其他有效期：
            </th>
            <td class="formValue">
                <input id="otherLicenseDate" name="otherLicenseDate" type="text" class="txt Wdate" style="width: 100%; "
                       onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})"/>
            </td>
        </tr>
    </table>
    <div class="col-md-12">
        <div class="image">
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <p style="text-align: center;"><span class="line"></span>营业执照<span class="line"></span></p>
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                    <img src=""/>
                </div>
                <div>
                        <span class="btn green-jungle btn-outline btn-file">
                                <span class="fileinput-new"> 添加 </span>
                                <span class="fileinput-exists"> 修改 </span>
                                <input id="licenseImage1" type="file" name="licenseImage1"/>
                        </span>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <p style="text-align: center;"><span class="line"></span>生产许可证<span class="line"></span></p>
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                    <img src=""/>
                </div>
                <div>
                        <span class="btn green-jungle btn-outline btn-file">
                                <span class="fileinput-new"> 添加 </span>
                                <span class="fileinput-exists"> 修改 </span>
                                <input id="licenseImage2" type="file" name="licenseImage2"/>
                        </span>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <p style="text-align: center;"><span class="line"></span>其他<span class="line"></span></p>
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                    <img src=""/>
                </div>
                <div>
                        <span class="btn green-jungle btn-outline btn-file">
                                <span class="fileinput-new"> 添加 </span>
                                <span class="fileinput-exists"> 修改 </span>
                                <input id="licenseImage3" type="file" name="licenseImage3"/>
                        </span>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <p style="text-align: center;"><span class="line"></span>其他<span class="line"></span></p>
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                    <img src=""/>
                </div>
                <div>
                        <span class="btn green-jungle btn-outline btn-file">
                                <span class="fileinput-new"> 添加 </span>
                                <span class="fileinput-exists"> 修改 </span>
                                <input id="licenseImage4" type="file" name="licenseImage4"/>
                        </span>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <p style="text-align: center;"><span class="line"></span>其他<span class="line"></span></p>
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                    <img src=""/>
                </div>
                <div>
                        <span class="btn green-jungle btn-outline btn-file">
                                <span class="fileinput-new"> 添加 </span>
                                <span class="fileinput-exists"> 修改 </span>
                                <input id="licenseImage5" type="file" name="licenseImage5"/>
                        </span>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
        </div>
    </div>
</form>
