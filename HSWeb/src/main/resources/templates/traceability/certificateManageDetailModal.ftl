

<form  style="margin: 1px">
    <table class="form">
        <tr>
            <th class="formTitle">产品大类：
            </th>
            <td class="formValue">
                <input class="txt" type="text" value="${pZsCertificateManage.bigProductTypeName}" readonly/>
            </td>
            <th class="formTitle">产品小类：
            </th>
            <td class="formValue">
                <input class="txt" type="text" value="${pZsCertificateManage.smallProductTypeName}" readonly/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">生产许可证：
            </th>
            <td class="formValue">
                <input class="txt" type="text" value="${pZsCertificateManage.productionLicense}" readonly/>
            </td>
            <th class="formTitle">产品生产过程：
            </th>
            <td class="formValue">
                <input class="txt" type="text" value="${pZsCertificateManage.productionProcessName}" readonly/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">检验结果：
            </th>
            <td class="formValue">
                <input class="txt" type="text" value="<#if pZsCertificateManage.testResult==1>检验合格<#else >检验不合格</#if>" readonly/>
            </td>
            <th class="formTitle">到期日：
            </th>
            <td class="formValue">
                <input type="text" class="txt Wdate" style="width: 100%; " value="${pZsCertificateManage.endDate?string("yyyy-MM-dd HH:mm:ss")}" readonly/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">产品标准号：
            </th>
            <td class="formValue">
                <input class="txt required" type="text" value="<#if pZsCertificateManage.productStandards??>${pZsCertificateManage.productStandards}</#if>" readonly/>
            </td>
        </tr>
    </table>
    <div class="col-md-12">
        <p><span class="line"></span>图片<span class="line"></span></p>
        <div class="image">
            <#if pZsCertificateManage.imageJsonList??&&pZsCertificateManage.imageJsonList?size!=0>
                <#list pZsCertificateManage.imageJsonList as images>
                    <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                        <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 130px; height: 120px;">
                            <img src="${images.imageUrl}" alt="${images.imageName}">
                        </div>
                    </div>
                </#list>
            <#else >
                暂无图片
            </#if>
        </div>
    </div>
</form>