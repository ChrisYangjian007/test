
    <div class="col-md-12">
        <p><span class="line"></span>图片<span class="line"></span></p>
        <div class="image">
        <#if corporateNewsById.image??>
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <img src="${corporateNewsById.image}" alt="${corporateNewsById.image}">
                    </div>
                </div>
        <#else >
            暂无图片
        </#if>
        </div>
    </div>