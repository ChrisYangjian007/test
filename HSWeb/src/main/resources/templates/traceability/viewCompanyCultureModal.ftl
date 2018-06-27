<div class="col-md-12">
    <p><span class="line"></span>图片<span class="line"></span></p>
    <div class="image">
    <#if imageJsonList??&&imageJsonList?size!=0>
        <#list imageJsonList as images>
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