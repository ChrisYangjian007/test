
    <div class="col-md-12">
        <p><span class="line"></span>图片<span class="line"></span></p>
        <div class="image">
        <#if productionInformationById.images??>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                    <img src="${productionInformationById.images}" alt="${productionInformationById.images}">
                </div>
            </div>
        <#else >
            暂无图片
        </#if>
        </div>
    </div>