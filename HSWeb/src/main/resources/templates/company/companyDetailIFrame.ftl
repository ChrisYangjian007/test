


<link href="${request.contextPath}/css/companyStyle.css" type="text/css" rel="stylesheet"/>

<div class="tools_bar leftline rightline" style="margin-bottom: 0px; margin: 1px;">
    <div class="PartialButton">
    <@shiro.hasPermission name="/companyDetaill/reload">
        <a id="lr-replace" title="刷新当前(Ctrl+Q)" onclick="Replace()" class="tools_btn">
            <span>
                <b class="btn-reload">刷新</b>
            </span>
        </a>
        <div class="tools_separator"></div>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/companyDetaill/back">
        <a id="lr-leave" title="关闭当前窗口(Esc)" onclick="btn_back()" class="tools_btn">
            <span>
                <b class="btn-back">离开</b>
            </span>
        </a>
    </@shiro.hasPermission>
    </div>
</div>
<div class="rightline test" style="margin: 1px; margin-top: -1px;">
    <div class="main">
        <div class="col-md-6">
            <p>公司名称</p>
            <div>${company.CName}</div>
        </div>
        <div class="col-md-6">
            <p>公司简称</p>
            <div>${company.shortName}</div>
        </div>
        <div class="col-md-6">
            <p>公司性质</p>
            <div>
            <#if 0==company.category>个体
            <#elseif 1==company.category>公司
            <#elseif 2==company.category>集团
            <#elseif 3==company.category>机构
            <#elseif 4==company.category>事业单位
            <#elseif 5==company.category>供应商
            <#elseif 6==company.category>客户
            <#elseif 7==company.category>合作社
            <#elseif 8==company.category>监管单位
            <#else>种养殖户
            </#if>
            </div>
        </div>
        <div class="col-md-6">
            <p>企业官方网站</p>
            <div>
                <a onclick="webUrl('${company.webUrl}')">${company.webUrl}</a>
            </div>
        </div>
        <div class="col-md-6">
            <p>公司服务热线</p>
            <div>${company.companyPhone}</div>
        </div>
        <div class="col-md-6">
            <p>公司地址</p>
            <div>${company.address}</div>
        </div>
        <div class="col-md-6">
            <p>关注微信</p>
            <div>${company.weiChat}</div>
        </div>
        <div class="col-md-6">
            <p>微信公众号</p>
            <div>${company.weChatPublicNumber}</div>
        </div>
        <div class="col-md-6">
            <p>海域位置</p>
            <div>${company.seaAreaPosition}</div>
        </div>
        <div class="col-md-12">
            <p><span class="line"></span>海域图片<span class="line"></span></p>
            <div class="image" style="text-align: center">
                <#if company.seaAreaImagesJson??>
                    <#list company.seaAreaImagesJson as seaAreaImagesJson>
                        <img src="${seaAreaImagesJson.imageUrl}" alt="${seaAreaImagesJson.imageName}">
                    </#list>
                </#if>
            </div>
        </div>
        <div class="col-md-12">
            <p><span class="line"></span>公司简介<span class="line"></span></p>
            <div class="introduction">
            ${company.enterpriseIntroduction}
            </div>
        </div>
        <div class="col-md-12">
            <p><span class="line"></span>企业图片<span class="line"></span></p>
            <div class="image" style="text-align: center">
            <#if company.enterpriseImagesJson??>
                <#list company.enterpriseImagesJson as enterpriseImagesJson>
                    <img src="${enterpriseImagesJson.imageUrl}" alt="${enterpriseImagesJson.imageName}">
                </#list>
            </#if>
            </div>
        </div>
        <div class="col-md-12">
            <p><span class="line"></span>企业形象短片<span class="line"></span></p>
            <div style="text-align: center; height: 530px;">
                <#if company.enterpriseImageVideo??>
                    <#--<video preload="auto" controls>
                        <source src="${company.enterpriseImageVideo}" type="video/mp4">
                    </video>-->
                    ${company.enterpriseImageVideo}
                </#if>
            </div>
        </div>
        <div class="col-md-12">
            <p><span class="line"></span>晓芹全景图<span class="line"></span></p>
            <div class="image" style="text-align: center">
            <#if company.panoramaJson??>
                <#list company.panoramaJson as panoramaJson>
                    <img src="${panoramaJson.imageUrl}" alt="${panoramaJson.imageName}">
                </#list>
            </#if>
            </div>
        </div>
        <div class="col-md-12">
            <p><span class="line"></span>检测中心简介<span class="line"></span></p>
            <div class="introduction">
            ${company.detectionCenterIntroduction}
            </div>
        </div>
        <div class="col-md-12">
            <p><span class="line"></span>检测中心图片<span class="line"></span></p>
            <div class="image" style="text-align: center">
            <#if company.detectionCenterImagesJson??>
                <#list company.detectionCenterImagesJson as detectionCenterImagesJson>
                    <img src="${detectionCenterImagesJson.imageUrl}" alt="${detectionCenterImagesJson.imageName}">
                </#list>
            </#if>
            </div>
        </div>
        <#if company.addContentJson??>
            <#list company.addContentJson as addContentJson>
                <div class="col-md-12">
                    <p><span class="line"></span>${addContentJson.title}<span class="line"></span></p>
                    <div style="text-align: center;">
                        ${addContentJson.content}
                    </div>
                </div>
            </#list>
        </#if>
    </div>
</div>





<script>
    $(document).ready(function () {

        Loading(false);

    });

    function webUrl(data) {
        if (0==data.indexOf("http://")||0==data.indexOf("https://")){
            window.open(data,"_blank");
        }else {
            window.open("http://"+data,"_blank");
        }
    }

</script>








