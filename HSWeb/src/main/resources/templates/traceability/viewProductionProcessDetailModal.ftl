
<div class="col-md-12">
    <p><span class="line"></span>图片<span class="line"></span></p>
    <div class="image">
    <#if zsProductionProcessDetail.detailImage??>
        <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
            <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                <img src="${zsProductionProcessDetail.detailImage}" alt="${zsProductionProcessDetail.detailImage}">
            </div>
        </div>
    <#else >
        暂无图片
    </#if>
    </div>
</div>