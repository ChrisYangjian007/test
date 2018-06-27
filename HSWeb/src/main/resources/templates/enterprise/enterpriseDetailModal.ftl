
<#--详细-->
<form style="margin: 1px">
    <table class="form">
        <tr>
            <th class="formTitle">供应商名称：
            </th>
            <td class="formValue">
                <input type="text" class="txt" value="<#if zsEnterprise.CName??>${zsEnterprise.CName}<#else ></#if>" readonly disabled/>
            </td>
            <th class="formTitle">供应货品：
            </th>
            <td class="formValue">
                <input value="<#if zsEnterprise.shortName??>${zsEnterprise.shortName}<#else ></#if>" type="text" class="txt" name="shortName" readonly disabled/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">所属海域：
            </th>
            <td class="formValue">
                <input value="<#if zsEnterprise.corporateRep??>${zsEnterprise.corporateRep}<#else ></#if>"type="text" class="txt" name="corporateRep" readonly disabled />
            </td>
            <th class="formTitle">联系人：
            </th>
            <td class="formValue">
                <input type="text" class="txt"  value="<#if zsEnterprise.contact??>${zsEnterprise.contact}<#else ></#if>" readonly disabled/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">联系方式：
            </th>
            <td class="formValue">
                <input type="text"  value="<#if zsEnterprise.phone??>${zsEnterprise.phone}<#else ></#if>" class="txt" name="phone" readonly disabled/>
            </td>
            <th class="formTitle">详细地址：
            </th>
            <td class="formValue">
                <input type="text" value="<#if zsEnterprise.address??>${zsEnterprise.address}<#else ></#if>" class="txt" name="address" readonly disabled/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">电子邮件：
            </th>
            <td class="formValue">
                <input type="text" value="<#if zsEnterprise.email??>${zsEnterprise.email}<#else ></#if>" class="txt" readonly />
            </td>
            <th class="formTitle">供应商类型：
            </th>
            <td class="formValue">
                <select class="txtselect" datacol="yes" err="分类" checkexpession="NotNull">
                <#if zsEnterprise.enterpriseType??>
                    <option value="1" <#if zsEnterprise.enterpriseType==1>selected</#if>>原料供应</option>
                    <option value="2" <#if zsEnterprise.enterpriseType==2>selected</#if>>其他供应</option>
                <#else >
                    <option value="">未选择供应类型</option>
                </#if>
                </select>
            </td>
        </tr>
        <tr>
            <th class="formTitle">营业有效期：
            </th>
            <td class="formValue">
                <input type="text" class="txt Wdate" value="<#if zsEnterprise.businessLicenseDate??>${zsEnterprise.businessLicenseDate?string("yyyy-MM-dd")}</#if>" style="width: 100%; "
                       onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})" readonly/>
            </td>
            <th class="formTitle">生许有效期：
            </th>
            <td class="formValue">
                <input type="text" class="txt Wdate" value="<#if zsEnterprise.productionLicenseDate??>${zsEnterprise.productionLicenseDate?string("yyyy-MM-dd")}</#if>" style="width: 100%; "
                       onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})" readonly/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">其他有效期：
            </th>
            <td class="formValue">
                <input type="text" class="txt Wdate" value="<#if zsEnterprise.otherLicenseDate??>${zsEnterprise.otherLicenseDate?string("yyyy-MM-dd")}</#if>" style="width: 100%; "
                       onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})" readonly/>
            </td>
        </tr>
    </table>
</form>
