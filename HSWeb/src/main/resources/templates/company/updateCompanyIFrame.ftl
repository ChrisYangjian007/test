
<link href="${request.contextPath}/css/companyStyle.css" type="text/css" rel="stylesheet"/>

<div class="tools_bar leftline rightline" style="position: fixed;width: 100%;z-index: 1;">
    <div class="PartialButton">
    <@shiro.hasPermission name="/updateCompany/reload">
        <a id="lr-replace" title="刷新当前(Ctrl+Q)" onclick="Replace()" class="tools_btn">
            <span>
                <b class="btn-reload">刷新</b>
            </span>
        </a>
        <div class="tools_separator"></div>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/updateCompany/submit">
        <a id="lr-leave" title="完成" onclick="updateCompanySubmit()" class="tools_btn">
            <span>
                <b class="btn-submit">完成</b>
            </span>
        </a>
        <div class="tools_separator"></div>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/updateCompany/addContent">
        <a id="lr-leave" title="增加内容" onclick="addContent()" class="tools_btn">
            <span>
                <b class="btn-add">增加内容</b>
            </span>
        </a>
        <div class="tools_separator"></div>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/updateCompany/back">
        <a id="lr-leave" title="关闭当前窗口(Esc)" onclick="btn_back()" class="tools_btn">
            <span>
                <b class="btn-back">离开</b>
            </span>
        </a>
    </@shiro.hasPermission>
    </div>
</div>
<form id="updateCompanyForm">
<div class="rightline test" style="margin: 1px; margin-top: -1px;">
    <div class="main">
        <div class="col-md-6">
            <p>公司名称</p>
            <input class="update-input" type="text" name="cName" value="${company.CName}" placeholder="${company.CName}" maxlength="30"/>
            <input type="hidden" name="companyId" value="${company.companyId}" />
            <input type="hidden" name="resourceId" <#if resourceId??>value="${resourceId}"</#if> />
        </div>
        <div class="col-md-6">
            <p>公司简称</p>
            <input class="update-input" type="text" name="shortName" value="${company.shortName}" placeholder="${company.shortName}" maxlength="30"/>
        </div>
        <div class="col-md-6">
            <p>公司性质</p>
            <select class="update-input" name="category">
                <option value="0" <#if company.category==0>selected</#if>>个体</option>
                <option value="1" <#if company.category==1>selected</#if>>公司</option>
                <option value="2" <#if company.category==2>selected</#if>>集团</option>
                <option value="3" <#if company.category==3>selected</#if>>机构</option>
                <option value="4" <#if company.category==4>selected</#if>>事业单位</option>
                <option value="5" <#if company.category==5>selected</#if>>供应商</option>
                <option value="6" <#if company.category==6>selected</#if>>客户</option>
                <option value="7" <#if company.category==7>selected</#if>>合作社</option>
                <option value="8" <#if company.category==8>selected</#if>>监管单位</option>
                <option value="9" <#if company.category==9>selected</#if>>种养殖户</option>
            </select>
        </div>
        <div class="col-md-6">
            <p>企业官方网站</p>
            <input class="update-input" type="text" name="webUrl" value="${company.webUrl}" placeholder="${company.webUrl}" maxlength="50"/>
        </div>
        <div class="col-md-6">
            <p>公司服务热线</p>
            <input class="update-input" type="text" name="companyPhone" value="${company.companyPhone}" placeholder="${company.companyPhone}" maxlength="30"/>
        </div>
        <div class="col-md-6">
            <p>公司地址</p>
            <input class="update-input" type="text" name="address" value="${company.address}" placeholder="${company.address}"/>
        </div>
        <div class="col-md-6">
            <p>关注微信</p>
            <input class="update-input" type="text" name="weiChat" value="${company.weiChat}" placeholder="${company.weiChat}"/>
        </div>
        <div class="col-md-6">
            <p>微信公众号</p>
            <input class="update-input" type="text" name="weChatPublicNumber" value="${company.weChatPublicNumber}" placeholder="${company.weChatPublicNumber}"/>
        </div>
        <div class="col-md-6">
            <p>海域位置</p>
            <input class="update-input" type="text" name="seaAreaPosition" value="${company.seaAreaPosition}" placeholder="${company.seaAreaPosition}"/>
        </div>
        <div class="col-md-6">
            <p>企业形象短片</p>
            <input class="update-input" type="text" name="enterpriseImageVideo" value="${company.enterpriseImageVideo}" placeholder="${company.enterpriseImageVideo}"/>
        </div>
        <div class="col-md-12">
            <p><span class="line"></span>海域图片<span class="line"></span></p>
            <div class="image">
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.seaAreaImagesJson??&&company.seaAreaImagesJson[0]??&&company.seaAreaImagesJson[0].imageUrl!="">
                            <img src="${company.seaAreaImagesJson[0].imageUrl}" alt="${company.seaAreaImagesJson[0].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.seaAreaImagesJson??&&company.seaAreaImagesJson[0]??&&company.seaAreaImagesJson[0].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="seaAreaImagesList" type="file" name="seaAreaImagesList" value=" " />
                        </span>
                        <#if company.seaAreaImagesJson??&&company.seaAreaImagesJson[0]??&&company.seaAreaImagesJson[0].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.seaAreaImagesJson??&&company.seaAreaImagesJson[1]??&&company.seaAreaImagesJson[1].imageUrl!="">
                            <img src="${company.seaAreaImagesJson[1].imageUrl}" alt="${company.seaAreaImagesJson[1].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.seaAreaImagesJson??&&company.seaAreaImagesJson[1]??&&company.seaAreaImagesJson[1].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="seaAreaImagesList" type="file" name="seaAreaImagesList" value=" " />
                        </span>
                        <#if company.seaAreaImagesJson??&&company.seaAreaImagesJson[1]??&&company.seaAreaImagesJson[1].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.seaAreaImagesJson??&&company.seaAreaImagesJson[2]??&&company.seaAreaImagesJson[2].imageUrl!="">
                            <img src="${company.seaAreaImagesJson[2].imageUrl}" alt="${company.seaAreaImagesJson[2].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.seaAreaImagesJson??&&company.seaAreaImagesJson[2]??&&company.seaAreaImagesJson[2].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="seaAreaImagesList" type="file" name="seaAreaImagesList" value=" " />
                        </span>
                        <#if company.seaAreaImagesJson??&&company.seaAreaImagesJson[2]??&&company.seaAreaImagesJson[2].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.seaAreaImagesJson??&&company.seaAreaImagesJson[3]??&&company.seaAreaImagesJson[3].imageUrl!="">
                            <img src="${company.seaAreaImagesJson[3].imageUrl}" alt="${company.seaAreaImagesJson[3].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.seaAreaImagesJson??&&company.seaAreaImagesJson[3]??&&company.seaAreaImagesJson[3].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="seaAreaImagesList" type="file" name="seaAreaImagesList" value=" " />
                        </span>
                        <#if company.seaAreaImagesJson??&&company.seaAreaImagesJson[3]??&&company.seaAreaImagesJson[3].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.seaAreaImagesJson??&&company.seaAreaImagesJson[4]??&&company.seaAreaImagesJson[4].imageUrl!="">
                            <img src="${company.seaAreaImagesJson[4].imageUrl}" alt="${company.seaAreaImagesJson[4].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.seaAreaImagesJson??&&company.seaAreaImagesJson[4]??&&company.seaAreaImagesJson[4].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="seaAreaImagesList" type="file" name="seaAreaImagesList" value=" " />
                        </span>
                        <#if company.seaAreaImagesJson??&&company.seaAreaImagesJson[4]??&&company.seaAreaImagesJson[4].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.seaAreaImagesJson??&&company.seaAreaImagesJson[5]??&&company.seaAreaImagesJson[5].imageUrl!="">
                            <img src="${company.seaAreaImagesJson[5].imageUrl}" alt="${company.seaAreaImagesJson[5].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.seaAreaImagesJson??&&company.seaAreaImagesJson[5]??&&company.seaAreaImagesJson[5].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="seaAreaImagesList" type="file" name="seaAreaImagesList" value=" " />
                        </span>
                        <#if company.seaAreaImagesJson??&&company.seaAreaImagesJson[5]??&&company.seaAreaImagesJson[5].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.seaAreaImagesJson??&&company.seaAreaImagesJson[6]??&&company.seaAreaImagesJson[6].imageUrl!="">
                            <img src="${company.seaAreaImagesJson[6].imageUrl}" alt="${company.seaAreaImagesJson[6].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.seaAreaImagesJson??&&company.seaAreaImagesJson[6]??&&company.seaAreaImagesJson[6].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="seaAreaImagesList" type="file" name="seaAreaImagesList" value=" " />
                        </span>
                        <#if company.seaAreaImagesJson??&&company.seaAreaImagesJson[6]??&&company.seaAreaImagesJson[6].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.seaAreaImagesJson??&&company.seaAreaImagesJson[7]??&&company.seaAreaImagesJson[7].imageUrl!="">
                            <img src="${company.seaAreaImagesJson[7].imageUrl}" alt="${company.seaAreaImagesJson[7].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.seaAreaImagesJson??&&company.seaAreaImagesJson[7]??&&company.seaAreaImagesJson[7].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="seaAreaImagesList" type="file" name="seaAreaImagesList" value=" " />
                        </span>
                        <#if company.seaAreaImagesJson??&&company.seaAreaImagesJson[7]??&&company.seaAreaImagesJson[7].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.seaAreaImagesJson??&&company.seaAreaImagesJson[8]??&&company.seaAreaImagesJson[8].imageUrl!="">
                            <img src="${company.seaAreaImagesJson[8].imageUrl}" alt="${company.seaAreaImagesJson[8].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.seaAreaImagesJson??&&company.seaAreaImagesJson[8]??&&company.seaAreaImagesJson[8].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="seaAreaImagesList" type="file" name="seaAreaImagesList" value=" " />
                        </span>
                        <#if company.seaAreaImagesJson??&&company.seaAreaImagesJson[8]??&&company.seaAreaImagesJson[8].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.seaAreaImagesJson??&&company.seaAreaImagesJson[9]??&&company.seaAreaImagesJson[9].imageUrl!="">
                            <img src="${company.seaAreaImagesJson[9].imageUrl}" alt="${company.seaAreaImagesJson[9].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.seaAreaImagesJson??&&company.seaAreaImagesJson[9]??&&company.seaAreaImagesJson[9].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="seaAreaImagesList" type="file" name="seaAreaImagesList" value=" " />
                        </span>
                        <#if company.seaAreaImagesJson??&&company.seaAreaImagesJson[9]??&&company.seaAreaImagesJson[9].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <input type="hidden" id="seaAreaImagesId" name="seaAreaImagesId">
            </div>
        </div>
        <div class="col-md-12">
            <p><span class="line"></span>公司简介<span class="line"></span></p>
            <textarea class="introduction" style="resize: none" id="enterpriseIntroduction" name="enterpriseIntroduction" maxlength="3000" rows="5">${company.enterpriseIntroduction}</textarea>
        </div>
        <div class="col-md-12">
            <p><span class="line"></span>企业图片<span class="line"></span></p>
            <div class="image">
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.enterpriseImagesJson??&&company.enterpriseImagesJson[0]??&&company.enterpriseImagesJson[0].imageUrl!="">
                            <img src="${company.enterpriseImagesJson[0].imageUrl}" alt="${company.enterpriseImagesJson[0].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.enterpriseImagesJson??&&company.enterpriseImagesJson[0]??&&company.enterpriseImagesJson[0].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="enterpriseImagesList" type="file" name="enterpriseImagesList" value="" />
                        </span>
                        <#if company.enterpriseImagesJson??&&company.enterpriseImagesJson[0]??&&company.enterpriseImagesJson[0].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.enterpriseImagesJson??&&company.enterpriseImagesJson[1]??&&company.enterpriseImagesJson[1].imageUrl!="">
                            <img src="${company.enterpriseImagesJson[1].imageUrl}" alt="${company.enterpriseImagesJson[1].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.enterpriseImagesJson??&&company.enterpriseImagesJson[1]??&&company.enterpriseImagesJson[1].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="enterpriseImagesList" type="file" name="enterpriseImagesList" value="" />
                        </span>
                        <#if company.enterpriseImagesJson??&&company.enterpriseImagesJson[1]??&&company.enterpriseImagesJson[1].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.enterpriseImagesJson??&&company.enterpriseImagesJson[2]??&&company.enterpriseImagesJson[2].imageUrl!="">
                            <img src="${company.enterpriseImagesJson[2].imageUrl}" alt="${company.enterpriseImagesJson[2].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.enterpriseImagesJson??&&company.enterpriseImagesJson[2]??&&company.enterpriseImagesJson[2].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="enterpriseImagesList" type="file" name="enterpriseImagesList" value="" />
                        </span>
                        <#if company.enterpriseImagesJson??&&company.enterpriseImagesJson[2]??&&company.enterpriseImagesJson[2].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.enterpriseImagesJson??&&company.enterpriseImagesJson[3]??&&company.enterpriseImagesJson[3].imageUrl!="">
                            <img src="${company.enterpriseImagesJson[3].imageUrl}" alt="${company.enterpriseImagesJson[3].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.enterpriseImagesJson??&&company.enterpriseImagesJson[3]??&&company.enterpriseImagesJson[3].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="enterpriseImagesList" type="file" name="enterpriseImagesList" value="" />
                        </span>
                        <#if company.enterpriseImagesJson??&&company.enterpriseImagesJson[3]??&&company.enterpriseImagesJson[3].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.enterpriseImagesJson??&&company.enterpriseImagesJson[4]??&&company.enterpriseImagesJson[4].imageUrl!="">
                            <img src="${company.enterpriseImagesJson[4].imageUrl}" alt="${company.enterpriseImagesJson[4].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.enterpriseImagesJson??&&company.enterpriseImagesJson[4]??&&company.enterpriseImagesJson[4].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="enterpriseImagesList" type="file" name="enterpriseImagesList" value="" />
                        </span>
                        <#if company.enterpriseImagesJson??&&company.enterpriseImagesJson[4]??&&company.enterpriseImagesJson[4].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.enterpriseImagesJson??&&company.enterpriseImagesJson[5]??&&company.enterpriseImagesJson[5].imageUrl!="">
                            <img src="${company.enterpriseImagesJson[5].imageUrl}" alt="${company.enterpriseImagesJson[5].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.enterpriseImagesJson??&&company.enterpriseImagesJson[5]??&&company.enterpriseImagesJson[5].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="enterpriseImagesList" type="file" name="enterpriseImagesList" value="" />
                        </span>
                        <#if company.enterpriseImagesJson??&&company.enterpriseImagesJson[5]??&&company.enterpriseImagesJson[5].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.enterpriseImagesJson??&&company.enterpriseImagesJson[6]??&&company.enterpriseImagesJson[6].imageUrl!="">
                            <img src="${company.enterpriseImagesJson[6].imageUrl}" alt="${company.enterpriseImagesJson[6].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.enterpriseImagesJson??&&company.enterpriseImagesJson[6]??&&company.enterpriseImagesJson[6].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="enterpriseImagesList" type="file" name="enterpriseImagesList" value="" />
                        </span>
                        <#if company.enterpriseImagesJson??&&company.enterpriseImagesJson[6]??&&company.enterpriseImagesJson[6].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.enterpriseImagesJson??&&company.enterpriseImagesJson[7]??&&company.enterpriseImagesJson[7].imageUrl!="">
                            <img src="${company.enterpriseImagesJson[7].imageUrl}" alt="${company.enterpriseImagesJson[7].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.enterpriseImagesJson??&&company.enterpriseImagesJson[7]??&&company.enterpriseImagesJson[7].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="enterpriseImagesList" type="file" name="enterpriseImagesList" value="" />
                        </span>
                        <#if company.enterpriseImagesJson??&&company.enterpriseImagesJson[7]??&&company.enterpriseImagesJson[7].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.enterpriseImagesJson??&&company.enterpriseImagesJson[8]??&&company.enterpriseImagesJson[8].imageUrl!="">
                            <img src="${company.enterpriseImagesJson[8].imageUrl}" alt="${company.enterpriseImagesJson[8].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.enterpriseImagesJson??&&company.enterpriseImagesJson[8]??&&company.enterpriseImagesJson[8].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="enterpriseImagesList" type="file" name="enterpriseImagesList" value="" />
                        </span>
                        <#if company.enterpriseImagesJson??&&company.enterpriseImagesJson[8]??&&company.enterpriseImagesJson[8].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.enterpriseImagesJson??&&company.enterpriseImagesJson[9]??&&company.enterpriseImagesJson[9].imageUrl!="">
                            <img src="${company.enterpriseImagesJson[9].imageUrl}" alt="${company.enterpriseImagesJson[9].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.enterpriseImagesJson??&&company.enterpriseImagesJson[9]??&&company.enterpriseImagesJson[9].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="enterpriseImagesList" type="file" name="enterpriseImagesList" value="" />
                        </span>
                        <#if company.enterpriseImagesJson??&&company.enterpriseImagesJson[9]??&&company.enterpriseImagesJson[9].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <input type="hidden" id="enterpriseImagesId" name="enterpriseImagesId">
            </div>
        </div>
        <#--<div class="col-md-12">
            <p><span class="line"></span>企业形象短片<span class="line"></span></p>
            <div style="margin-left: 25%">
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 600px; height: 230px;">
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <span class="fileinput-new"> 添加 </span>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="video" type="file" name="video" />
                        </span>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
            </div>
        </div>-->
        <div class="col-md-12">
            <p><span class="line"></span>晓芹全景图<span class="line"></span></p>
            <div class="image">
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.panoramaJson??&&company.panoramaJson[0]??&&company.panoramaJson[0].imageUrl!="">
                            <img src="${company.panoramaJson[0].imageUrl}" alt="${company.panoramaJson[0].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.panoramaJson??&&company.panoramaJson[0]??&&company.panoramaJson[0].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="panoramaList" type="file" name="panoramaList" value="" />
                        </span>
                        <#if company.panoramaJson??&&company.panoramaJson[0]??&&company.panoramaJson[0].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.panoramaJson??&&company.panoramaJson[1]??&&company.panoramaJson[1].imageUrl!="">
                            <img src="${company.panoramaJson[1].imageUrl}" alt="${company.panoramaJson[1].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.panoramaJson??&&company.panoramaJson[1]??&&company.panoramaJson[1].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="panoramaList" type="file" name="panoramaList" value="" />
                        </span>
                        <#if company.panoramaJson??&&company.panoramaJson[1]??&&company.panoramaJson[1].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.panoramaJson??&&company.panoramaJson[2]??&&company.panoramaJson[2].imageUrl!="">
                            <img src="${company.panoramaJson[2].imageUrl}" alt="${company.panoramaJson[2].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.panoramaJson??&&company.panoramaJson[2]??&&company.panoramaJson[2].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="panoramaList" type="file" name="panoramaList" value="" />
                        </span>
                        <#if company.panoramaJson??&&company.panoramaJson[2]??&&company.panoramaJson[2].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.panoramaJson??&&company.panoramaJson[3]??&&company.panoramaJson[3].imageUrl!="">
                            <img src="${company.panoramaJson[3].imageUrl}" alt="${company.panoramaJson[3].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.panoramaJson??&&company.panoramaJson[3]??&&company.panoramaJson[3].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="panoramaList" type="file" name="panoramaList" value="" />
                        </span>
                        <#if company.panoramaJson??&&company.panoramaJson[3]??&&company.panoramaJson[3].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.panoramaJson??&&company.panoramaJson[4]??&&company.panoramaJson[4].imageUrl!="">
                            <img src="${company.panoramaJson[4].imageUrl}" alt="${company.panoramaJson[4].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.panoramaJson??&&company.panoramaJson[4]??&&company.panoramaJson[4].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="panoramaList" type="file" name="panoramaList" value="" />
                        </span>
                        <#if company.panoramaJson??&&company.panoramaJson[4]??&&company.panoramaJson[4].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.panoramaJson??&&company.panoramaJson[5]??&&company.panoramaJson[5].imageUrl!="">
                            <img src="${company.panoramaJson[5].imageUrl}" alt="${company.panoramaJson[5].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.panoramaJson??&&company.panoramaJson[5]??&&company.panoramaJson[5].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="panoramaList" type="file" name="panoramaList" value="" />
                        </span>
                        <#if company.panoramaJson??&&company.panoramaJson[5]??&&company.panoramaJson[5].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.panoramaJson??&&company.panoramaJson[6]??&&company.panoramaJson[6].imageUrl!="">
                            <img src="${company.panoramaJson[6].imageUrl}" alt="${company.panoramaJson[6].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.panoramaJson??&&company.panoramaJson[6]??&&company.panoramaJson[6].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="panoramaList" type="file" name="panoramaList" value="" />
                        </span>
                        <#if company.panoramaJson??&&company.panoramaJson[6]??&&company.panoramaJson[6].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.panoramaJson??&&company.panoramaJson[7]??&&company.panoramaJson[7].imageUrl!="">
                            <img src="${company.panoramaJson[7].imageUrl}" alt="${company.panoramaJson[7].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.panoramaJson??&&company.panoramaJson[7]??&&company.panoramaJson[7].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="panoramaList" type="file" name="panoramaList" value="" />
                        </span>
                        <#if company.panoramaJson??&&company.panoramaJson[7]??&&company.panoramaJson[7].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.panoramaJson??&&company.panoramaJson[8]??&&company.panoramaJson[8].imageUrl!="">
                            <img src="${company.panoramaJson[8].imageUrl}" alt="${company.panoramaJson[8].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.panoramaJson??&&company.panoramaJson[8]??&&company.panoramaJson[8].imageUrl!="">>
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="panoramaList" type="file" name="panoramaList" value="" />
                        </span>
                        <#if company.panoramaJson??&&company.panoramaJson[8]??&&company.panoramaJson[8].imageUrl!="">>
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.panoramaJson??&&company.panoramaJson[9]??&&company.panoramaJson[9].imageUrl!="">>
                            <img src="${company.panoramaJson[9].imageUrl}" alt="${company.panoramaJson[9].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.panoramaJson??&&company.panoramaJson[9]??&&company.panoramaJson[9].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="panoramaList" type="file" name="panoramaList" value="" />
                        </span>
                        <#if company.panoramaJson??&&company.panoramaJson[9]??&&company.panoramaJson[9].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <input type="hidden" id="panoramaId" name="panoramaId">
            </div>
        </div>
        <div class="col-md-12">
            <p><span class="line"></span>检测中心简介<span class="line"></span></p>
            <textarea class="introduction" style="resize: none" id="detectionCenterIntroduction" name="detectionCenterIntroduction" maxlength="3000" rows="5">${company.detectionCenterIntroduction}</textarea>
        </div>
        <div class="col-md-12">
            <p><span class="line"></span>检测中心图片<span class="line"></span></p>
            <div class="image">
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.detectionCenterImagesJson??&&company.detectionCenterImagesJson[0]??&&company.detectionCenterImagesJson[0].imageUrl!="">
                            <img src="${company.detectionCenterImagesJson[0].imageUrl}" alt="${company.detectionCenterImagesJson[0].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.detectionCenterImagesJson??&&company.detectionCenterImagesJson[0]??&&company.detectionCenterImagesJson[0].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="detectionCenterImagesList" type="file" name="detectionCenterImagesList" value="" />
                        </span>
                        <#if company.detectionCenterImagesJson??&&company.detectionCenterImagesJson[0]??&&company.detectionCenterImagesJson[0].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.detectionCenterImagesJson??&&company.detectionCenterImagesJson[1]??&&company.detectionCenterImagesJson[1].imageUrl!="">
                            <img src="${company.detectionCenterImagesJson[1].imageUrl}" alt="${company.detectionCenterImagesJson[1].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.detectionCenterImagesJson??&&company.detectionCenterImagesJson[1]??&&company.detectionCenterImagesJson[1].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="detectionCenterImagesList" type="file" name="detectionCenterImagesList" value="" />
                        </span>
                        <#if company.detectionCenterImagesJson??&&company.detectionCenterImagesJson[1]??&&company.detectionCenterImagesJson[1].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.detectionCenterImagesJson??&&company.detectionCenterImagesJson[2]??&&company.detectionCenterImagesJson[2].imageUrl!="">
                            <img src="${company.detectionCenterImagesJson[2].imageUrl}" alt="${company.detectionCenterImagesJson[2].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.detectionCenterImagesJson??&&company.detectionCenterImagesJson[2]??&&company.detectionCenterImagesJson[2].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="detectionCenterImagesList" type="file" name="detectionCenterImagesList" value="" />
                        </span>
                        <#if company.detectionCenterImagesJson??&&company.detectionCenterImagesJson[2]??&&company.detectionCenterImagesJson[2].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.detectionCenterImagesJson??&&company.detectionCenterImagesJson[3]??&&company.detectionCenterImagesJson[3].imageUrl!="">
                            <img src="${company.detectionCenterImagesJson[3].imageUrl}" alt="${company.detectionCenterImagesJson[3].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.detectionCenterImagesJson??&&company.detectionCenterImagesJson[3]??&&company.detectionCenterImagesJson[3].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="detectionCenterImagesList" type="file" name="detectionCenterImagesList" value="" />
                        </span>
                        <#if company.detectionCenterImagesJson??&&company.detectionCenterImagesJson[3]??&&company.detectionCenterImagesJson[3].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.detectionCenterImagesJson??&&company.detectionCenterImagesJson[4]??&&company.detectionCenterImagesJson[4].imageUrl!="">
                            <img src="${company.detectionCenterImagesJson[4].imageUrl}" alt="${company.detectionCenterImagesJson[4].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.detectionCenterImagesJson??&&company.detectionCenterImagesJson[4]??&&company.detectionCenterImagesJson[4].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="detectionCenterImagesList" type="file" name="detectionCenterImagesList" value="" />
                        </span>
                        <#if company.detectionCenterImagesJson??&&company.detectionCenterImagesJson[4]??&&company.detectionCenterImagesJson[4].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.detectionCenterImagesJson??&&company.detectionCenterImagesJson[5]??&&company.detectionCenterImagesJson[5].imageUrl!="">
                            <img src="${company.detectionCenterImagesJson[5].imageUrl}" alt="${company.detectionCenterImagesJson[5].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.detectionCenterImagesJson??&&company.detectionCenterImagesJson[5]??&&company.detectionCenterImagesJson[5].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="detectionCenterImagesList" type="file" name="detectionCenterImagesList" value="" />
                        </span>
                        <#if company.detectionCenterImagesJson??&&company.detectionCenterImagesJson[5]??&&company.detectionCenterImagesJson[5].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.detectionCenterImagesJson??&&company.detectionCenterImagesJson[6]??&&company.detectionCenterImagesJson[6].imageUrl!="">
                            <img src="${company.detectionCenterImagesJson[6].imageUrl}" alt="${company.detectionCenterImagesJson[6].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.detectionCenterImagesJson??&&company.detectionCenterImagesJson[6]??&&company.detectionCenterImagesJson[6].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="detectionCenterImagesList" type="file" name="detectionCenterImagesList" value="" />
                        </span>
                        <#if company.detectionCenterImagesJson??&&company.detectionCenterImagesJson[6]??&&company.detectionCenterImagesJson[6].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.detectionCenterImagesJson??&&company.detectionCenterImagesJson[7]??&&company.detectionCenterImagesJson[7].imageUrl!="">
                            <img src="${company.detectionCenterImagesJson[7].imageUrl}" alt="${company.detectionCenterImagesJson[7].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.detectionCenterImagesJson??&&company.detectionCenterImagesJson[7]??&&company.detectionCenterImagesJson[7].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="detectionCenterImagesList" type="file" name="detectionCenterImagesList" value="" />
                        </span>
                        <#if company.detectionCenterImagesJson??&&company.detectionCenterImagesJson[7]??&&company.detectionCenterImagesJson[7].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.detectionCenterImagesJson??&&company.detectionCenterImagesJson[8]??&&company.detectionCenterImagesJson[8].imageUrl!="">
                            <img src="${company.detectionCenterImagesJson[8].imageUrl}" alt="${company.detectionCenterImagesJson[8].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.detectionCenterImagesJson??&&company.detectionCenterImagesJson[8]??&&company.detectionCenterImagesJson[8].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="detectionCenterImagesList" type="file" name="detectionCenterImagesList" value="" />
                        </span>
                        <#if company.detectionCenterImagesJson??&&company.detectionCenterImagesJson[8]??&&company.detectionCenterImagesJson[8].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        <#if company.detectionCenterImagesJson??&&company.detectionCenterImagesJson[9]??&&company.detectionCenterImagesJson[9].imageUrl!="">
                            <img src="${company.detectionCenterImagesJson[9].imageUrl}" alt="${company.detectionCenterImagesJson[9].imageName}"/>
                        </#if>
                    </div>
                    <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if company.detectionCenterImagesJson??&&company.detectionCenterImagesJson[9]??&&company.detectionCenterImagesJson[9].imageUrl!="">
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                            <span class="fileinput-exists"> 修改 </span>
                            <input id="detectionCenterImagesList" type="file" name="detectionCenterImagesList" value="" />
                        </span>
                        <#if company.detectionCenterImagesJson??&&company.detectionCenterImagesJson[9]??&&company.detectionCenterImagesJson[9].imageUrl!="">
                            <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                        </#if>
                        <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                    </div>
                </div>
                <input type="hidden" id="detectionCenterImagesId" name="detectionCenterImagesId">
            </div>
        </div>
    <#if company.addContentJson??>
        <#list company.addContentJson as addContentJson>
            <div class="col-md-12">
                <p><span class="line"></span>${addContentJson.title}<span class="line"></span></p>
                <div style="text-align: center; margin-left: 19%;">
                    <script id="${addContentJson.title}container" name="${addContentJson.title}content" type="text/plain">${addContentJson.content}</script>
                </div>
            </div>
        </#list>
    </#if>
        <input type="hidden" name="addContent">
    </div>
</div>
</form>

<@shiro.hasPermission name="/updateCompany/addContent">
<!--添加内容-->
<div id="addContentModal" class="modal fade " data-width="930" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header"><#--
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>-->
        <h4 class="modal-title">添加内容</h4>
    </div>
    <div class="modal-body">
        <form id="addContentForm" style="margin: 1px">
            <table class="form">
                <tr id="locationTr">
                    <input type="hidden" name="resourceId" <#if resourceId??>value="${resourceId}"</#if> />
                    <th class="formTitle">标题：
                    </th>
                    <td class="formValue" colspan="3">
                        <input type="text" class="txt required" placeholder="标题"
                               name="title" id="title"/>
                    </td>
                </tr>
                <tr>
                    <th class="formTitle">内容：
                    </th>
                    <td class="formValue" colspan="3">
                        <script id="container" name="content" type="text/plain"></script>
                    </td>
                </tr>
            </table>
            <input type="hidden" name="addContent">
        </form>
    </div>
    <div class="modal-footer">
        <button id="closeAddContentModal" type="button" class="btn btn-outline dark">取消</button>
        <button id="addContentBtn" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>


<script type="text/javascript" charset="utf-8" src="${staticJs}/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${staticJs}/js/ueditor/ueditor.all.js"></script>
<script type="text/javascript" charset="utf-8" src="${staticJs}/js/ueditor/lang/zh-cn/zh-cn.js"></script>

<script>
    <#if company.addContentJson??>
        <#list company.addContentJson as addContentJson>
        var ue${addContentJson.title} = UE.getEditor('${addContentJson.title}container',{
            // 服务器统一请求接口路径
            serverUrl: "${request.contextPath}"+"/ueditor/config.htm",
            toolbars:[
                [
                    //'anchor', //锚点
                    //'undo', //撤销
                    //'redo', //重做
                    'bold', //加粗
                    'indent', //首行缩进
                    //'snapscreen', //截图
                    'italic', //斜体
                    'underline', //下划线
                    'strikethrough', //删除线
                    //'subscript', //下标
                    //'fontborder', //字符边框
                    //'superscript', //上标
                    //'formatmatch', //格式刷
                    'blockquote', //引用
                    //'pasteplain', //纯文本粘贴模式
                    //'selectall', //全选
                    //'print', //打印
                    //'preview', //预览
                    'horizontal', //分隔线
                    //'removeformat', //清除格式
                    //'time', //时间
                    //'date', //日期
                    //'|',//分隔符，这里仅用于展示，可以有效的排布工具栏的图标位置
                    //'unlink', //取消链接
                    'insertrow', //前插入行
                    'insertcol', //前插入列
                    'mergeright', //右合并单元格
                    'mergedown', //下合并单元格
                    'deleterow', //删除行
                    'deletecol', //删除列
                    'splittorows', //拆分成行
                    'splittocols', //拆分成列
                    'splittocells', //完全拆分单元格
                    'deletecaption', //删除表格标题
                    'inserttitle', //插入标题
                    'mergecells', //合并多个单元格
                    'deletetable', //删除表格
                    //'cleardoc', //清空文档
                    //'insertparagraphbeforetable', //"表格前插入行"
                    //'insertcode', //代码语言
                    'fontfamily', //字体
                    'fontsize', //字号
                    'paragraph', //段落格式
                    'simpleupload', //单图上传
                    'insertimage', //多图上传
                    //'edittable', //表格属性
                    //'edittd', //单元格属性
                    'link', //超链接
                    'emotion', //表情
                    'spechars', //特殊字符
                    //'searchreplace', //查询替换
                    //'map', //Baidu地图
                    //'insertvideo', //视频
                    //'help', //帮助
                    'justifyleft', //居左对齐
                    'justifyright', //居右对齐
                    'justifycenter', //居中对齐
                    'justifyjustify', //两端对齐
                    'forecolor', //字体颜色
                    //'backcolor', //背景色
                    'insertorderedlist', //有序列表
                    'insertunorderedlist', //无序列表
                    //'fullscreen', //全屏
                    //'directionalityltr', //从左向右输入
                    //'directionalityrtl', //从右向左输入
                    //'rowspacingtop', //段前距
                    //'rowspacingbottom', //段后距
                    //'pagebreak', //分页
                    //'insertframe', //插入Iframe
                    'imagenone', //默认
                    'imageleft', //左浮动
                    'imageright', //右浮动
                    //'attachment', //附件
                    'imagecenter', //居中
                    //'wordimage', //图片转存
                    'lineheight', //行间距
                    'edittip ', //编辑提示
                    //'customstyle', //自定义标题
                    //'autotypeset', //自动排版
                    //'touppercase', //字母大写
                    //'tolowercase', //字母小写
                    //'background', //背景
                    //'template', //模板
                    //'scrawl', //涂鸦
                    'inserttable', //插入表格
                    //'drafts', // 从草稿箱加载
                    'charts' // 图表
                ]
            ],
            textarea:"${addContentJson.title}content",// 提交表单时，服务器获取编辑器提交内容的所用的参数，多实例时可以给容器name属性，会将name给定的值最为每个实例的键值，不用每次实例化的时候都设置这个值
            initialContent:"${addContentJson.content}",//初始化内容，也可以通过上方的textarea/script给定值
            autoClearinitialContent:false,//是否自动清除编辑器初始内容，注意：如果focus属性设置为true，这个也为真，那么编辑器一上来就会触发导致初始化的内容看不到了
            focus:false,//初始化时，是否让编辑器获得焦点true或false
            initialFrameWidth:800,//初始化编辑器宽度，默认1000,如果为1000则该参数可以不写
            initialFrameHeight:300,//初始化编辑器高度，默认320
            readonly:false,//是否为只读状态，默认为false
            enableAutoSave:true, //启用自动保存，默认为true
            saveInterval:500,//自动保存间隔时间，默认值为500
            imageScaleEnabled:true,//启用图片拉伸缩放,默认值为true
            allHtmlEnabled:false, //提交到后台的数据是否包含整个html字符串,默认值为false
            maxListLevel:5, //限制可以tab的级数， 设置-1为不限制, 默认值为3
            minFrameWidth:800,//编辑器拖动时最小宽度，默认800
            minFrameHeight:220, //编辑器拖动时最小高度，默认220
            topOffset:0,//浮动时工具栏距离浏览器顶部的高度，用于某些具有固定头部的页面
            sourceEditor:"codemirror",//源码的查看方式，codemirror是代码高亮，textarea是文本框，默认是codemirror，注意默认codemirror只能在ie8+和非ie中使用
            codeMirrorJsUrl:"${staticJs}/js/ueditor/third-party/codemirror/codemirror.js",//如果sourceEditor是codemirror需要配置这项，codeMirror js加载的路径
            codeMirrorCssUrl:"${staticJs}/js/ueditor/third-party/codemirror/codemirror.css",//如果sourceEditor是codemirror需要配置这项，codeMirror css加载的路径
            webAppKey:"1SfQ6t4NYl3aGoVUb611ldkZy39eupfK",//webAppKey 百度应用的APIkey
            imageUrl: "",
            /* 上传图片配置项 */
            imageActionName: "uploadimage", /* 执行上传图片的action名称 */
            imageFieldName: "upfile", /* 提交的图片表单名称 */
            imageMaxSize: 2048000, /* 上传大小限制，单位B */
            imageAllowFiles: [".png", ".jpg", ".jpeg", ".gif", ".bmp"], /* 上传图片格式显示 */
            imageCompressEnable: true, /* 是否压缩图片,默认是true */
            imageCompressBorder: 1600, /* 图片压缩最长边限制 */
            imageInsertAlign: "none", /* 插入的图片浮动方式 */
            imageUrlPrefix: "", /* 图片访问路径前缀 */
            imagePathFormat: "/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}", /* 上传保存路径,可以自定义保存路径和文件名格式 */
            /* {filename} 会替换成原文件名,配置这项需要注意中文乱码问题 */
            /* {rand:6} 会替换成随机数,后面的数字是随机数的位数 */
            /* {time} 会替换成时间戳 */
            /* {yyyy} 会替换成四位年份 */
            /* {yy} 会替换成两位年份 */
            /* {mm} 会替换成两位月份 */
            /* {dd} 会替换成两位日期 */
            /* {hh} 会替换成两位小时 */
            /* {ii} 会替换成两位分钟 */
            /* {ss} 会替换成两位秒 */
            /* 非法字符 \ : * ? " < > | */
            /* 具请体看线上文档: fex.baidu.com/ueditor/#use-format_upload_filename */

            /* 涂鸦图片上传配置项 */
            scrawlActionName: "uploadscrawl", /* 执行上传涂鸦的action名称 */
            scrawlFieldName: "upfile", /* 提交的图片表单名称 */
            scrawlPathFormat: "/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}", /* 上传保存路径,可以自定义保存路径和文件名格式 */
            scrawlMaxSize: 2048000, /* 上传大小限制，单位B */
            scrawlUrlPrefix: "", /* 图片访问路径前缀 */
            scrawlInsertAlign: "none",

            /* 截图工具上传 */
            snapscreenActionName: "uploadimage", /* 执行上传截图的action名称 */
            snapscreenPathFormat: "/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}", /* 上传保存路径,可以自定义保存路径和文件名格式 */
            snapscreenUrlPrefix: "", /* 图片访问路径前缀 */
            snapscreenInsertAlign: "none", /* 插入的图片浮动方式 */

            /* 抓取远程图片配置 */
            catcherLocalDomain: ["127.0.0.1", "localhost", "img.baidu.com"],
            catcherActionName: "catchimage", /* 执行抓取远程图片的action名称 */
            catcherFieldName: "source", /* 提交的图片列表表单名称 */
            catcherPathFormat: "/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}", /* 上传保存路径,可以自定义保存路径和文件名格式 */
            catcherUrlPrefix: "", /* 图片访问路径前缀 */
            catcherMaxSize: 2048000, /* 上传大小限制，单位B */
            catcherAllowFiles: [".png", ".jpg", ".jpeg", ".gif", ".bmp"], /* 抓取图片格式显示 */

            /* 上传视频配置 */
            videoActionName: "uploadvideo", /* 执行上传视频的action名称 */
            videoFieldName: "upfile", /* 提交的视频表单名称 */
            videoPathFormat: "/ueditor/jsp/upload/video/{yyyy}{mm}{dd}/{time}{rand:6}", /* 上传保存路径,可以自定义保存路径和文件名格式 */
            videoUrlPrefix: "", /* 视频访问路径前缀 */
            videoMaxSize: 102400000, /* 上传大小限制，单位B，默认100MB */
            videoAllowFiles: [
                ".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg",
                ".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid"], /* 上传视频格式显示 */

            /* 上传文件配置 */
            fileActionName: "uploadfile", /* controller里,执行上传视频的action名称 */
            fileFieldName: "upfile", /* 提交的文件表单名称 */
            filePathFormat: "/ueditor/jsp/upload/file/{yyyy}{mm}{dd}/{time}{rand:6}", /* 上传保存路径,可以自定义保存路径和文件名格式 */
            fileUrlPrefix: "", /* 文件访问路径前缀 */
            fileMaxSize: 51200000, /* 上传大小限制，单位B，默认50MB */
            fileAllowFiles: [
                ".png", ".jpg", ".jpeg", ".gif", ".bmp",
                ".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg",
                ".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid",
                ".rar", ".zip", ".tar", ".gz", ".7z", ".bz2", ".cab", ".iso",
                ".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx", ".pdf", ".txt", ".md", ".xml"
            ], /* 上传文件格式显示 */

            /* 列出指定目录下的图片 */
            imageManagerActionName: "listimage", /* 执行图片管理的action名称 */
            imageManagerListPath: "/ueditor/jsp/upload/image/", /* 指定要列出图片的目录 */
            imageManagerListSize: 20, /* 每次列出文件数量 */
            imageManagerUrlPrefix: "", /* 图片访问路径前缀 */
            imageManagerInsertAlign: "none", /* 插入的图片浮动方式 */
            imageManagerAllowFiles: [".png", ".jpg", ".jpeg", ".gif", ".bmp"], /* 列出的文件类型 */

            /* 列出指定目录下的文件 */
            fileManagerActionName: "listfile", /* 执行文件管理的action名称 */
            fileManagerListPath: "/ueditor/jsp/upload/file/", /* 指定要列出文件的目录 */
            fileManagerUrlPrefix: "", /* 文件访问路径前缀 */
            fileManagerListSize: 20, /* 每次列出文件数量 */
            fileManagerAllowFiles: [
                ".png", ".jpg", ".jpeg", ".gif", ".bmp",
                ".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg",
                ".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid",
                ".rar", ".zip", ".tar", ".gz", ".7z", ".bz2", ".cab", ".iso",
                ".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx", ".pdf", ".txt", ".md", ".xml"
            ], /* 列出的文件类型 */
            zIndex : 189
        });
        </#list>
    </#if>
    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
    UE.Editor.prototype.getActionUrl = function(action) {
        if (action == 'uploadimage' || action == 'uploadfile' || action == 'uploadvideo' || action == 'uploadimage') {
            return '${request.contextPath}/ueditor/uploadfile.json';
        } else if (action == 'uploadscrawl') {
            return '${request.contextPath}/ueditor/uploadScrawl.json';
        } else {
            return this._bkGetActionUrl.call(this, action);
        }
    };
    $(document).ready(function () {
        Loading(false);

    });


    $("input[name = seaAreaImagesList]").on("change",function(){
        var idx = $("input[id = seaAreaImagesList]").index($(this));
        var idxs = $("#seaAreaImagesId").val();
        /*if(""==idxs){
            idxs = "" + idx;
        }else {
            idxs += "," + idx;
        }*/
        idxs = ""+idxs+idx;
        $("#seaAreaImagesId").val(idxs);
    });

    $("input[name = enterpriseImagesList]").on("change",function(){
        var idx = $("input[id = enterpriseImagesList]").index($(this));
        var idxs = $("#enterpriseImagesId").val();
        idxs = ""+idxs+idx;
        $("#enterpriseImagesId").val(idxs);
    });

    $("input[name = panoramaList]").on("change",function(){
        var idx = $("input[id = panoramaList]").index($(this));
        var idxs = $("#panoramaId").val();
        idxs = ""+idxs+idx;
        $("#panoramaId").val(idxs);
    });

    $("input[name = detectionCenterImagesList]").on("change",function(){
        var idx = $("input[id = detectionCenterImagesList]").index($(this));
        var idxs = $("#detectionCenterImagesId").val();
        idxs = ""+idxs+idx;
        $("#detectionCenterImagesId").val(idxs);
    });

<@shiro.hasPermission name="/updateCompany/submit">
//提交修改内容
    function updateCompanySubmit() {
        Loading(true, "正在提交数据...");
        var addContent = "[";
    <#if company.addContentJson??>
    var index = 1;
        <#list company.addContentJson as addContentJson>
            var content = ue${addContentJson.title}.getContent();
            content = content.replace(/"/g,"\'");
            addContent+="{"+"\"title\":"+"\"${addContentJson.title}\","+"\"content\":"+"\""+content+"\"}";
            if (${company.addContentJson?size}>index){
                addContent+=",";
            }
            index++;
        </#list>
    </#if>
        addContent+="]";
        $("input[name = addContent]").val(addContent);

        var cName = $("input[name=cName]").val();
        if (""==cName) {
            $("input[name=cName]").focus();
            tipDialog("请输入公司名称", 4, 'a');
            Loading(false);
            return false;
        }
        var shortName = $("input[name=shortName]").val();
        if (""==shortName) {
            $("input[name=shortName]").focus();
            tipDialog("请输入公司简称", 4, 'a');
            Loading(false);
            return false;
        }
        var webUrl = $("input[name=webUrl]").val();
        if (""==webUrl) {
            $("input[name=webUrl]").focus();
            tipDialog("请输入企业官方网站", 4, 'a');
            Loading(false);
            return false;
        }
        var companyPhone = $("input[name=companyPhone]").val();
        if (""==companyPhone) {
            $("input[name=companyPhone]").focus();
            tipDialog("请输入企业电话", 4, 'a');
            Loading(false);
            return false;
        }
        var address = $("input[name=address]").val();
        if (""==address) {
            $("input[name=address]").focus();
            tipDialog("请输入企业地址", 4, 'a');
            Loading(false);
            return false;
        }
        var weiChat = $("input[name=weiChat]").val();
        if (""==weiChat) {
            $("input[name=weiChat]").focus();
            tipDialog("请输入微信号", 4, 'a');
            Loading(false);
            return false;
        }
        var weChatPublicNumber = $("input[name=weChatPublicNumber]").val();
        if (""==weChatPublicNumber) {
            $("input[name=weChatPublicNumber]").focus();
            tipDialog("请输入微信公众号", 4, 'a');
            Loading(false);
            return false;
        }
        var seaAreaPosition = $("input[name=seaAreaPosition]").val();
        if (""==seaAreaPosition) {
            $("input[name=seaAreaPosition]").focus();
            tipDialog("请输入海域位置", 4, 'a');
            Loading(false);
            return false;
        }
        var enterpriseImageVideo = $("input[name=enterpriseImageVideo]").val();
        if (""==enterpriseImageVideo) {
            $("input[name=enterpriseImageVideo]").focus();
            tipDialog("请输入企业形象短片html代码", 4, 'a');
            Loading(false);
            return false;
        }
        /*String.prototype.endWith=function(endStr){
            var d=this.length-endStr.length;
            return (d>=0&&this.lastIndexOf(endStr)==d)
        };
        var video = $("#video").val();
        if (""!=video) {
            if (!(video.endWith(".mp4"))) {
                tipDialog("请上传MP4格式的视频", 4, 'a');
                Loading(false);
                return false;
            }
        }*/
        var options = {
            type: "post",
            cache: false,
            url: '${request.contextPath}/company/updateCompanySubmit.json',
            success: function (res) {
                if (res.success) {
                    tipDialog(res.msg, 4, '1');
                    Loading(true, "正在刷新...");
                    Replace();
                }
                else {
                    tipDialog(res.msg, 4, '0');
                    Loading(false);
                }
            },
            error: function (xhr, ajaxOptions, thrownError) {
                tipDialog("请求失败!", 4, '0');
                Loading(false);
            }
        };
        $('#updateCompanyForm').ajaxSubmit(options);
    }

</@shiro.hasPermission>

<@shiro.hasPermission name="/updateCompany/addContent">
//添加内容
    function addContent() {
        $("#addContentModal").modal("show");
    }
    var addContentUe;
    $('#addContentModal').on('show.bs.modal', function(e) {
        addContentUe = UE.getEditor('container',{
            // 服务器统一请求接口路径
            serverUrl: "${request.contextPath}"+"/ueditor/config.htm",
            toolbars:[
                [
                    //'anchor', //锚点
                    //'undo', //撤销
                    //'redo', //重做
                    'bold', //加粗
                    'indent', //首行缩进
                    //'snapscreen', //截图
                    'italic', //斜体
                    'underline', //下划线
                    'strikethrough', //删除线
                    //'subscript', //下标
                    //'fontborder', //字符边框
                    //'superscript', //上标
                    //'formatmatch', //格式刷
                    'blockquote', //引用
                    //'pasteplain', //纯文本粘贴模式
                    //'selectall', //全选
                    //'print', //打印
                    //'preview', //预览
                    'horizontal', //分隔线
                    //'removeformat', //清除格式
                    //'time', //时间
                    //'date', //日期
                    //'|',//分隔符，这里仅用于展示，可以有效的排布工具栏的图标位置
                    //'unlink', //取消链接
                    'insertrow', //前插入行
                    'insertcol', //前插入列
                    'mergeright', //右合并单元格
                    'mergedown', //下合并单元格
                    'deleterow', //删除行
                    'deletecol', //删除列
                    'splittorows', //拆分成行
                    'splittocols', //拆分成列
                    'splittocells', //完全拆分单元格
                    'deletecaption', //删除表格标题
                    'inserttitle', //插入标题
                    'mergecells', //合并多个单元格
                    'deletetable', //删除表格
                    //'cleardoc', //清空文档
                    //'insertparagraphbeforetable', //"表格前插入行"
                    //'insertcode', //代码语言
                    'fontfamily', //字体
                    'fontsize', //字号
                    'paragraph', //段落格式
                    'simpleupload', //单图上传
                    'insertimage', //多图上传
                    //'edittable', //表格属性
                    //'edittd', //单元格属性
                    'link', //超链接
                    'emotion', //表情
                    'spechars', //特殊字符
                    //'searchreplace', //查询替换
                    //'map', //Baidu地图
                    //'insertvideo', //视频
                    //'help', //帮助
                    'justifyleft', //居左对齐
                    'justifyright', //居右对齐
                    'justifycenter', //居中对齐
                    'justifyjustify', //两端对齐
                    'forecolor', //字体颜色
                    //'backcolor', //背景色
                    'insertorderedlist', //有序列表
                    'insertunorderedlist', //无序列表
                    //'fullscreen', //全屏
                    //'directionalityltr', //从左向右输入
                    //'directionalityrtl', //从右向左输入
                    //'rowspacingtop', //段前距
                    //'rowspacingbottom', //段后距
                    //'pagebreak', //分页
                    //'insertframe', //插入Iframe
                    'imagenone', //默认
                    'imageleft', //左浮动
                    'imageright', //右浮动
                    //'attachment', //附件
                    'imagecenter', //居中
                    //'wordimage', //图片转存
                    'lineheight', //行间距
                    'edittip ', //编辑提示
                    //'customstyle', //自定义标题
                    //'autotypeset', //自动排版
                    //'touppercase', //字母大写
                    //'tolowercase', //字母小写
                    //'background', //背景
                    //'template', //模板
                    //'scrawl', //涂鸦
                    'inserttable', //插入表格
                    //'drafts', // 从草稿箱加载
                    'charts' // 图表
                ]
            ],
            textarea:"content",// 提交表单时，服务器获取编辑器提交内容的所用的参数，多实例时可以给容器name属性，会将name给定的值最为每个实例的键值，不用每次实例化的时候都设置这个值
            initialContent:"请输入内容",//初始化内容，也可以通过上方的textarea/script给定值
            autoClearinitialContent:true,//是否自动清除编辑器初始内容，注意：如果focus属性设置为true，这个也为真，那么编辑器一上来就会触发导致初始化的内容看不到了
            focus:false,//初始化时，是否让编辑器获得焦点true或false
            initialFrameWidth:800,//初始化编辑器宽度，默认1000,如果为1000则该参数可以不写
            initialFrameHeight:300,//初始化编辑器高度，默认320
            readonly:false,//是否为只读状态，默认为false
            enableAutoSave:true, //启用自动保存，默认为true
            saveInterval:500,//自动保存间隔时间，默认值为500
            imageScaleEnabled:true,//启用图片拉伸缩放,默认值为true
            allHtmlEnabled:false, //提交到后台的数据是否包含整个html字符串,默认值为false
            maxListLevel:5, //限制可以tab的级数， 设置-1为不限制, 默认值为3
            minFrameWidth:800,//编辑器拖动时最小宽度，默认800
            minFrameHeight:220, //编辑器拖动时最小高度，默认220
            topOffset:0,//浮动时工具栏距离浏览器顶部的高度，用于某些具有固定头部的页面
            sourceEditor:"codemirror",//源码的查看方式，codemirror是代码高亮，textarea是文本框，默认是codemirror，注意默认codemirror只能在ie8+和非ie中使用
            codeMirrorJsUrl:"${staticJs}/js/ueditor/third-party/codemirror/codemirror.js",//如果sourceEditor是codemirror需要配置这项，codeMirror js加载的路径
            codeMirrorCssUrl:"${staticJs}/js/ueditor/third-party/codemirror/codemirror.css",//如果sourceEditor是codemirror需要配置这项，codeMirror css加载的路径
            webAppKey:"1SfQ6t4NYl3aGoVUb611ldkZy39eupfK",//webAppKey 百度应用的APIkey
            imageUrl: "",
            /* 上传图片配置项 */
            imageActionName: "uploadimage", /* 执行上传图片的action名称 */
            imageFieldName: "upfile", /* 提交的图片表单名称 */
            imageMaxSize: 2048000, /* 上传大小限制，单位B */
            imageAllowFiles: [".png", ".jpg", ".jpeg", ".gif", ".bmp"], /* 上传图片格式显示 */
            imageCompressEnable: true, /* 是否压缩图片,默认是true */
            imageCompressBorder: 1600, /* 图片压缩最长边限制 */
            imageInsertAlign: "none", /* 插入的图片浮动方式 */
            imageUrlPrefix: "", /* 图片访问路径前缀 */
            imagePathFormat: "/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}", /* 上传保存路径,可以自定义保存路径和文件名格式 */
            /* {filename} 会替换成原文件名,配置这项需要注意中文乱码问题 */
            /* {rand:6} 会替换成随机数,后面的数字是随机数的位数 */
            /* {time} 会替换成时间戳 */
            /* {yyyy} 会替换成四位年份 */
            /* {yy} 会替换成两位年份 */
            /* {mm} 会替换成两位月份 */
            /* {dd} 会替换成两位日期 */
            /* {hh} 会替换成两位小时 */
            /* {ii} 会替换成两位分钟 */
            /* {ss} 会替换成两位秒 */
            /* 非法字符 \ : * ? " < > | */
            /* 具请体看线上文档: fex.baidu.com/ueditor/#use-format_upload_filename */

            /* 涂鸦图片上传配置项 */
            scrawlActionName: "uploadscrawl", /* 执行上传涂鸦的action名称 */
            scrawlFieldName: "upfile", /* 提交的图片表单名称 */
            scrawlPathFormat: "/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}", /* 上传保存路径,可以自定义保存路径和文件名格式 */
            scrawlMaxSize: 2048000, /* 上传大小限制，单位B */
            scrawlUrlPrefix: "", /* 图片访问路径前缀 */
            scrawlInsertAlign: "none",

            /* 截图工具上传 */
            snapscreenActionName: "uploadimage", /* 执行上传截图的action名称 */
            snapscreenPathFormat: "/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}", /* 上传保存路径,可以自定义保存路径和文件名格式 */
            snapscreenUrlPrefix: "", /* 图片访问路径前缀 */
            snapscreenInsertAlign: "none", /* 插入的图片浮动方式 */

            /* 抓取远程图片配置 */
            catcherLocalDomain: ["127.0.0.1", "localhost", "img.baidu.com"],
            catcherActionName: "catchimage", /* 执行抓取远程图片的action名称 */
            catcherFieldName: "source", /* 提交的图片列表表单名称 */
            catcherPathFormat: "/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}", /* 上传保存路径,可以自定义保存路径和文件名格式 */
            catcherUrlPrefix: "", /* 图片访问路径前缀 */
            catcherMaxSize: 2048000, /* 上传大小限制，单位B */
            catcherAllowFiles: [".png", ".jpg", ".jpeg", ".gif", ".bmp"], /* 抓取图片格式显示 */

            /* 上传视频配置 */
            videoActionName: "uploadvideo", /* 执行上传视频的action名称 */
            videoFieldName: "upfile", /* 提交的视频表单名称 */
            videoPathFormat: "/ueditor/jsp/upload/video/{yyyy}{mm}{dd}/{time}{rand:6}", /* 上传保存路径,可以自定义保存路径和文件名格式 */
            videoUrlPrefix: "", /* 视频访问路径前缀 */
            videoMaxSize: 102400000, /* 上传大小限制，单位B，默认100MB */
            videoAllowFiles: [
                ".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg",
                ".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid"], /* 上传视频格式显示 */

            /* 上传文件配置 */
            fileActionName: "uploadfile", /* controller里,执行上传视频的action名称 */
            fileFieldName: "upfile", /* 提交的文件表单名称 */
            filePathFormat: "/ueditor/jsp/upload/file/{yyyy}{mm}{dd}/{time}{rand:6}", /* 上传保存路径,可以自定义保存路径和文件名格式 */
            fileUrlPrefix: "", /* 文件访问路径前缀 */
            fileMaxSize: 51200000, /* 上传大小限制，单位B，默认50MB */
            fileAllowFiles: [
                ".png", ".jpg", ".jpeg", ".gif", ".bmp",
                ".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg",
                ".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid",
                ".rar", ".zip", ".tar", ".gz", ".7z", ".bz2", ".cab", ".iso",
                ".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx", ".pdf", ".txt", ".md", ".xml"
            ], /* 上传文件格式显示 */

            /* 列出指定目录下的图片 */
            imageManagerActionName: "listimage", /* 执行图片管理的action名称 */
            imageManagerListPath: "/ueditor/jsp/upload/image/", /* 指定要列出图片的目录 */
            imageManagerListSize: 20, /* 每次列出文件数量 */
            imageManagerUrlPrefix: "", /* 图片访问路径前缀 */
            imageManagerInsertAlign: "none", /* 插入的图片浮动方式 */
            imageManagerAllowFiles: [".png", ".jpg", ".jpeg", ".gif", ".bmp"], /* 列出的文件类型 */

            /* 列出指定目录下的文件 */
            fileManagerActionName: "listfile", /* 执行文件管理的action名称 */
            fileManagerListPath: "/ueditor/jsp/upload/file/", /* 指定要列出文件的目录 */
            fileManagerUrlPrefix: "", /* 文件访问路径前缀 */
            fileManagerListSize: 20, /* 每次列出文件数量 */
            fileManagerAllowFiles: [
                ".png", ".jpg", ".jpeg", ".gif", ".bmp",
                ".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg",
                ".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid",
                ".rar", ".zip", ".tar", ".gz", ".7z", ".bz2", ".cab", ".iso",
                ".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx", ".pdf", ".txt", ".md", ".xml"
            ], /* 列出的文件类型 */
            zIndex : 210

        });
    });

    $("#closeAddContentModal").unbind("click");
    $("#closeAddContentModal").click(function () {
        Loading(true, "正在刷新...","#addContentModal");
        $("#addContentModal").modal("hide");
        Replace();
    });

    $("#addContentBtn").unbind("click");
    $("#addContentBtn").click(function () {
        Loading(true, "正在提交数据...","#addContentModal");
        var title = $.trim($("#title").val());
        var title = $.trim($("#title").val());
        if (""==title){
            tipDialog("请输入标题！", 4, 'a');
            Loading(false,"","#addContentModal");
            return false;
        }
        var content = addContentUe.getContent();
        if (""==content){
            tipDialog("请输入内容！", 4, 'a');
            Loading(false,"","#addContentModal");
            return false;
        }
        content = content.replace(/"/g,"\'");
        $("input[name = addContent]").val("[{"+"\"title\":"+"\""+title+"\","+"\"content\":"+"\""+content+"\"}]");

        var options = {
            type: "post",
            cache: false,
            url: '${request.contextPath}/company/addContent.json?companyId='+${company.companyId},
            success: function (res) {
                if (res.success) {
                    tipDialog(res.msg, 4, '1');
                    Loading(true, "正在刷新...","#addContentModal");
                    Replace();
                } else {
                    tipDialog(res.msg, 4, '0');
                    Loading(false,"","#addContentModal");
                }
            },
            error: function (xhr, ajaxOptions, thrownError) {
                tipDialog("请求失败!", 4, '0');
                Loading(false,"","#addContentModal");
            }
        };
        $('#addContentForm').ajaxSubmit(options);
    });

</@shiro.hasPermission>

</script>



