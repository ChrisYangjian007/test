<#--新增-->
<form id="updateCorporateNews" style="margin: 1px">
    <input type="hidden" name="resourceId" value="${resourceId}">
    <table class="form">
        <tr>
            <th class="formTitle">标题：
            </th>
            <td class="formValue">
                <input type="hidden" name="corporateNewsId" value="<#if corporateNewsById.corporateNewsId??>${corporateNewsById.corporateNewsId}</#if>" />
                <input class="txt required" id="updTitle" type="text" name="title" value="<#if corporateNewsById.title??>${corporateNewsById.title}</#if>" />
            </td>
        </tr>
        <tr>
            <th class="formTitle">链接：
            </th>
            <td class="formValue">
                <input class="txt required" id="updLink" type="text" name="link" value="<#if corporateNewsById.link??>${corporateNewsById.link}</#if>" />
            </td>
        </tr>
        <tr>
            <th class="formTitle">时间：
            </th>
            <td class="formValue">
                <input  class="txt required" id="updCorporateNewsDate"  name="corporateNewsDate" type="text" class="txt Wdate" style="width: 100%; " value="<#if corporateNewsById.corporateNewsDate??>${corporateNewsById.corporateNewsDate?string('yyyy-MM-dd HH:mm:ss')}</#if>"
                        onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
            </td>
        </tr>
    </table>
    <br/>
    <div class="col-md-12">
        <p><span class="line"></span>上传图片<span class="line"></span></p>
        <div class="image">
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                <#if corporateNewsById.image??>
                    <img src="${corporateNewsById.image}"/>
                </#if>
                </div>
                <div>
                        <span class="btn green-jungle btn-outline btn-file">
                                  <#if corporateNewsById.image??>
                                     <span class="fileinput-new"> 修改 </span>
                                 <#else>
                                     <span class="fileinput-new"> 添加 </span>
                                 </#if>
                                     <span class="fileinput-exists"> 修改 </span>
                            <input id="updCorporateNewsImage" type="file" name="updCorporateNewsImage"/>
                        </span>
                <#if corporateNewsById.image??>
                    <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                </#if>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <input type="hidden" id="updCorporateNewsImageId" name="updCorporateNewsImageId">
        </div>
    </div>
</form>

<script>

    $("input[name = updCorporateNewsImage]").on("change",function(){
        var idx = $("input[id = updCorporateNewsImage]").index($(this));
        var idxs = $("#updCorporateNewsImageId").val();
        idxs = ""+idxs+idx;
        $("#updCorporateNewsImageId").val(idxs);
    });

</script>