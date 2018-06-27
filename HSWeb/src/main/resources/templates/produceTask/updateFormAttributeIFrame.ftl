
<link rel="stylesheet" href="${staticCss}/js/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="${staticCss}/css/animate.css">
<link rel="stylesheet" href="${staticCss}/css/comment.css">
<link rel="stylesheet" href="${request.contextPath}/css/produce.css">

<div style="position: fixed;width: 100%;z-index: 1" class="tools_bar leftline rightline" style="margin-bottom: 0px; margin: 1px;">
    <div  class="PartialButton">
        <a id="lr-detail" title="确定" onclick="updateAttributeValue()" class="tools_btn">
                            <span>
                                <b  class="btn-detail">确定</b>
                            </span>
        </a>
        <a id="lr-detail" title="取消" onclick="RemoveDiv(${produceTaskId}+'updateFormAttributeIFrame')" class="tools_btn">
            <span>
                <b  class="btn-detail">取消</b>
            </span>
        </a>
    </div>
</div>
<div class="produce-main">
    <#if pZsWorkProcessList??&&pZsWorkProcessList?size!=0>
        <#--<input type="hidden" value="${pZsWorkProcessList}" id="pZsWorkProcessList"/>-->
        <#list pZsWorkProcessList as workProcess>
            <#if workProcess.PBaFormAttributeValueList1??&&workProcess.PBaFormAttributeValueList1?size!=0>
                <div class="produce-container" style="margin-top: 45px">
                    <div class="title">
                        <span class="produce-num">${workProcess_index+1}</span>
                        <span class="title-name">${workProcess.CName}</span>
                    </div>
                    <div class="produce-box record">
                        <div class="record-list">
                            <p class="produce-title">操作记录</p>
                            <#list workProcess.PBaFormAttributeValueList1 as formAttributeValue>
                                <input type="hidden" name="formAttributeValueId" value="${formAttributeValue.formAttributeValueId}">
                                <#if formAttributeValue.PBaFormAttributeList??&&formAttributeValue.PBaFormAttributeList?size!=0>
                                    <#list formAttributeValue.PBaFormAttributeList as attribute>
                                        <div class="col-md-4">
                                            <input type="hidden" name="attributeId" value="${attribute.formAttributeId}">
                                            <div class="produce-left">${attribute.propertyName}:</div>
                                            <#if attribute.value??>
                                                <#if attribute.controlType==1||attribute.controlType==5>
                                                        <#if attribute.restrictiveConditions==1>
                                                            <input name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" type="text" class="produce-right" value="${attribute.value}" />
                                                        <#elseif attribute.restrictiveConditions==2>
                                                            <input name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" type="text" class="produce-right" value="${attribute.value}"
                                                                   onKeyUp="this.value=this.value.replace(/\D/g,'')" />
                                                        <#elseif attribute.restrictiveConditions==3>
                                                            <input name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" type="text" class="produce-right" value="${attribute.value}"
                                                                   onkeyup="this.value=this.value.replace(/[^\w\.\/]/ig,'')" />
                                                        <#elseif attribute.restrictiveConditions==4>
                                                            <input name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" type="text" class="produce-right" value="${attribute.value}" />
                                                        <#elseif attribute.restrictiveConditions==5>
                                                            <input name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" type="hidden" value="${attribute.value}" readonly/>
                                                            <input type="text" class="produce-right" value="${attribute.defaultUserName}" readonly>
                                                        </#if>
                                                <#elseif attribute.controlType==2>
                                                    <#if attribute.dataSourceType==1>
                                                        <select name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" class="produce-right">
                                                            <option value=""></option>
                                                            <#if attribute.dictionaryDetails??&&attribute.dictionaryDetails?size!=0>
                                                                <#list attribute.dictionaryDetails as details>
                                                                    <option value="${details.dataDictionaryDetailsId}" <#if (attribute.value?number)==details.dataDictionaryDetailsId>selected</#if>>${details.CName}</option>
                                                                </#list>
                                                            <#else >
                                                                <option value="">暂无数据，请至数据字典添加</option>
                                                            </#if>
                                                        </select>
                                                    <#elseif attribute.dataSourceType==2>
                                                        <#if attribute.propertyName=="审核人">
                                                            <input name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" type="hidden" value="${attribute.value}" readonly/>
                                                            <input type="text" class="produce-right" value="${attribute.defaultUserName}" readonly>
                                                        <#else>
                                                            <select name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" class="produce-right">
                                                                <option value=""></option>
                                                                <#if baUserList??&&baUserList?size!=0>
                                                                    <#list baUserList as user>
                                                                        <option value="${user.userId}" <#if (attribute.value?number)==user.userId>selected</#if>>${user.CName}</option>
                                                                    </#list>
                                                                </#if>
                                                            </select>
                                                        </#if>
                                                    </#if>
                                                <#elseif attribute.controlType==3>
                                                        <input name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" type="text" class="txt Wdate" style="width:70%;height:20px;border:1px solid #C0C0C0;" value="${((attribute.value?date)?string("yyyy-MM-dd HH:mm:ss"))!}"
                                                               onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                                                </#if>
                                            <#else >
                                                <#if attribute.controlType==1||attribute.controlType==5>
                                                    <#if attribute.restrictiveConditions==1>
                                                        <input name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" type="text" class="produce-right" />
                                                    <#elseif attribute.restrictiveConditions==2>
                                                        <input name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" type="text" class="produce-right"
                                                               onKeyUp="this.value=this.value.replace(/\D/g,'')" />
                                                    <#elseif attribute.restrictiveConditions==3>
                                                        <input name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" type="text" class="produce-right"
                                                               onkeyup="this.value=this.value.replace(/[^\w\.\/]/ig,'')" />
                                                    <#elseif attribute.restrictiveConditions==4>
                                                        <input name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" type="text" class="produce-right" />
                                                    <#elseif attribute.restrictiveConditions==5>
                                                        <input name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" type="hidden"  value="${shiroUser.id}" readonly/>
                                                        <input type="text" class="produce-right" value="${shiroUser.name}" readonly>
                                                    </#if>
                                                <#elseif attribute.controlType==2>
                                                    <#if attribute.dataSourceType==1>
                                                        <select name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" class="produce-right">
                                                            <option value=""></option>
                                                            <#if attribute.dictionaryDetails??&&attribute.dictionaryDetails?size!=0>
                                                                <#list attribute.dictionaryDetails as details>
                                                                    <option value="${details.dataDictionaryDetailsId}">${details.CName}</option>
                                                                </#list>
                                                            <#else >
                                                                <option value="">暂无数据，请至数据字典添加</option>
                                                            </#if>
                                                        </select>
                                                    <#elseif attribute.dataSourceType==2>
                                                        <select name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" class="produce-right">
                                                            <option value=""></option>
                                                            <#if baUserList??&&baUserList?size!=0>
                                                                <#list baUserList as user>
                                                                    <option value="${user.userId}">${user.CName}</option>
                                                                </#list>
                                                            </#if>
                                                        </select>
                                                    </#if>
                                                <#elseif attribute.controlType==3>
                                                    <input name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" type="text" class="txt Wdate" style="width:70%;height:20px;border:1px solid #C0C0C0;"
                                                           onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                                                </#if>
                                            </#if>
                                        </div>
                                    </#list>
                                </#if>
                            </#list>
                        </div>
                        <#if workProcess.PBaFormAttributeValueList2??&&workProcess.PBaFormAttributeValueList2?size!=0>
                            <div class="record-list">
                                <p class="produce-title">审核记录</p>
                                <#list workProcess.PBaFormAttributeValueList2 as formAttributeValue>
                                    <input type="hidden" name="formAttributeValueId" value="${formAttributeValue.formAttributeValueId}">
                                    <#if formAttributeValue.PBaFormAttributeList??&&formAttributeValue.PBaFormAttributeList?size!=0>
                                        <#list formAttributeValue.PBaFormAttributeList as attribute>
                                            <div class="col-md-4">
                                                <input type="hidden" name="attributeId" value="${attribute.formAttributeId}">
                                                <div class="produce-left">${attribute.propertyName}</div>
                                                <#if attribute.value??>
                                                    <#if attribute.controlType==1||attribute.controlType==5>
                                                        <#if attribute.restrictiveConditions==1>
                                                            <input name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" type="text" class="produce-right" value="${attribute.value}" />
                                                        <#elseif attribute.restrictiveConditions==2>
                                                            <input name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" type="text" class="produce-right" value="${attribute.value}"
                                                                   onKeyUp="this.value=this.value.replace(/\D/g,'')" />
                                                        <#elseif attribute.restrictiveConditions==3>
                                                            <input name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" type="text" class="produce-right" value="${attribute.value}"
                                                                   onkeyup="this.value=this.value.replace(/[^\w\.\/]/ig,'')" />
                                                        <#elseif attribute.restrictiveConditions==4>
                                                            <input name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" type="text" class="produce-right" value="${attribute.value}" />
                                                        <#elseif attribute.restrictiveConditions==5>
                                                            <input name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" type="hidden" value="${attribute.value}" readonly/>
                                                            <input type="text" class="produce-right" value="${attribute.defaultUserName}" readonly>
                                                        </#if>
                                                    <#elseif attribute.controlType==2>
                                                        <#if attribute.dataSourceType==1>
                                                            <select name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" class="produce-right">
                                                                <option value=""></option>
                                                                <#if attribute.dictionaryDetails??&&attribute.dictionaryDetails?size!=0>
                                                                    <#list attribute.dictionaryDetails as details>
                                                                        <option value="${details.dataDictionaryDetailsId}" <#if (attribute.value?number)==details.dataDictionaryDetailsId>selected</#if>>${details.CName}</option>
                                                                    </#list>
                                                                <#else >
                                                                    <option value="">暂无数据，请至数据字典添加</option>
                                                                </#if>
                                                            </select>
                                                        <#elseif attribute.dataSourceType==2>
                                                            <select name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" class="produce-right">
                                                                <option value=""></option>
                                                                <#if baUserList??&&baUserList?size!=0>
                                                                    <#list baUserList as user>
                                                                        <option value="${user.userId}" <#if (attribute.value?number)==user.userId>selected</#if>>${user.CName}</option>
                                                                    </#list>
                                                                </#if>
                                                            </select>
                                                        </#if>
                                                    <#elseif attribute.controlType==3>
                                                        <input name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" type="text" class="txt Wdate" style="width:70%;height:20px;border:1px solid #C0C0C0;" value="${((attribute.value?date)?string("yyyy-MM-dd HH:mm:ss"))!}"
                                                               onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                                                    </#if>
                                                <#else >
                                                    <#if attribute.controlType==1||attribute.controlType==5>
                                                        <#if attribute.restrictiveConditions==1>
                                                            <input name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" type="text" class="produce-right" />
                                                        <#elseif attribute.restrictiveConditions==2>
                                                            <input name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" type="text" class="produce-right"
                                                                   onKeyUp="this.value=this.value.replace(/\D/g,'')" />
                                                        <#elseif attribute.restrictiveConditions==3>
                                                            <input name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" type="text" class="produce-right"
                                                                   onkeyup="this.value=this.value.replace(/[^\w\.\/]/ig,'')" />
                                                        <#elseif attribute.restrictiveConditions==4>
                                                            <input name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" type="text" class="produce-right" />
                                                        <#elseif attribute.restrictiveConditions==5>
                                                            <input name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" type="hidden"  value="${shiroUser.id}" readonly/>
                                                            <input type="text" class="produce-right" value="${shiroUser.name}" readonly>
                                                        </#if>
                                                    <#elseif attribute.controlType==2>
                                                        <#if attribute.dataSourceType==1>
                                                            <select name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" class="produce-right">
                                                                <option value=""></option>
                                                                <#if attribute.dictionaryDetails??&&attribute.dictionaryDetails?size!=0>
                                                                    <#list attribute.dictionaryDetails as details>
                                                                        <option value="${details.dataDictionaryDetailsId}">${details.CName}</option>
                                                                    </#list>
                                                                <#else >
                                                                    <option value="">暂无数据，请至数据字典添加</option>
                                                                </#if>
                                                            </select>
                                                        <#elseif attribute.dataSourceType==2>
                                                            <select name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" class="produce-right">
                                                                <option value=""></option>
                                                                <#if baUserList??&&baUserList?size!=0>
                                                                    <#list baUserList as user>
                                                                        <option value="${user.userId}">${user.CName}</option>
                                                                    </#list>
                                                                </#if>
                                                            </select>
                                                        </#if>
                                                    <#elseif attribute.controlType==3>
                                                        <input name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" type="text" class="txt Wdate" style="width:70%;height:20px;border:1px solid #C0C0C0;"
                                                               onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                                                    </#if>
                                                </#if>
                                            </div>
                                        </#list>
                                    </#if>
                                </#list>
                            </div>
                        <#else>
                            <#if workProcess.tableField2??&&workProcess.tableField2?size!=0>
                                <div class="record-list" id="workProcessId${workProcess.workProcessId}tableField2">
                                    <p class="produce-title">审核记录</p>
                                    <#list workProcess.tableField2 as attribute>
                                        <div class="col-md-4" id="divworkProcessId${workProcess.workProcessId}tableField2attributeId${attribute.formAttributeId}" onchange="updValueWithReviewer(${workProcess.workProcessId},${attribute.formAttributeId},${workProcess.reviewerId},${workProcess.reviewerName})">
                                            <input type="hidden" name="attributeId" value="${attribute.formAttributeId}">
                                            <div class="produce-left">${attribute.propertyName}:</div>
                                            <#if attribute.controlType==1||attribute.controlType==5>
                                                <#if attribute.restrictiveConditions==1>
                                                    <input name="workProcessId${workProcess.workProcessId}tableField2attributeId${attribute.formAttributeId}" type="text" class="produce-right" />
                                                <#elseif attribute.restrictiveConditions==2>
                                                    <input name="workProcessId${workProcess.workProcessId}tableField2attributeId${attribute.formAttributeId}" type="text" class="produce-right"
                                                           onKeyUp="this.value=this.value.replace(/\D/g,'')" />
                                                <#elseif attribute.restrictiveConditions==3>
                                                    <input name="workProcessId${workProcess.workProcessId}tableField2attributeId${attribute.formAttributeId}" type="text" class="produce-right"
                                                           onkeyup="this.value=this.value.replace(/[^\w\.\/]/ig,'')" />
                                                <#elseif attribute.restrictiveConditions==4>
                                                    <input name="workProcessId${workProcess.workProcessId}tableField2attributeId${attribute.formAttributeId}" type="text" class="produce-right" />
                                                <#elseif attribute.restrictiveConditions==5>
                                                    <input name="workProcessId${workProcess.workProcessId}tableField2attributeId${attribute.formAttributeId}" type="hidden"  value="" readonly/>
                                                    <input type="text" class="produce-right" value="${shiroUser.name}" readonly>
                                                </#if>
                                            <#elseif attribute.controlType==2>
                                                <#if attribute.dataSourceType==1>
                                                    <select name="workProcessId${workProcess.workProcessId}tableField2attributeId${attribute.formAttributeId}" class="produce-right">
                                                        <option value=""></option>
                                                        <#if attribute.dictionaryDetails??&&attribute.dictionaryDetails?size!=0>
                                                            <#list attribute.dictionaryDetails as details>
                                                                <option value="${details.dataDictionaryDetailsId}">${details.CName}</option>
                                                            </#list>
                                                        <#else >
                                                            <option value="">暂无数据，请至数据字典添加</option>
                                                        </#if>
                                                    </select>
                                                <#elseif attribute.dataSourceType==2>
                                                    <select name="workProcessId${workProcess.workProcessId}tableField2attributeId${attribute.formAttributeId}" class="produce-right">
                                                        <option value=""></option>
                                                        <#if baUserList??&&baUserList?size!=0>
                                                            <#list baUserList as user>
                                                                <option value="${user.userId}">${user.CName}</option>
                                                            </#list>
                                                        </#if>
                                                    </select>
                                                </#if>
                                            <#elseif attribute.controlType==3>
                                                <input name="workProcessId${workProcess.workProcessId}tableField2attributeId${attribute.formAttributeId}" type="text" class="txt Wdate" style="width:70%;height:20px;border:1px solid #C0C0C0;" value="${((attribute.value?date)?string("yyyy-MM-dd HH:mm:ss"))!}"
                                                       onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                                            </#if>
                                        </div>
                                    </#list>
                                </div>
                            </#if>
                        </#if>
                    <#if workProcess.PBaFormAttributeValueList3??&&workProcess.PBaFormAttributeValueList3?size!=0>
                        <div class="record-list">
                            <p class="produce-title">巡检记录</p>
                            <#list workProcess.PBaFormAttributeValueList3 as formAttributeValue>
                                <input type="hidden" name="formAttributeValueId" value="${formAttributeValue.formAttributeValueId}">
                                <#if formAttributeValue.PBaFormAttributeList??&&formAttributeValue.PBaFormAttributeList?size!=0>
                                    <#list formAttributeValue.PBaFormAttributeList as attribute>
                                        <div class="col-md-4">
                                            <input type="hidden" name="attributeId" value="${attribute.formAttributeId}">
                                            <div class="produce-left">${attribute.propertyName}</div>
                                            <#if attribute.value??>
                                                <#if attribute.controlType==1||attribute.controlType==5>
                                                    <#if attribute.restrictiveConditions==1>
                                                        <input name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" type="text" class="produce-right" value="${attribute.value}" />
                                                    <#elseif attribute.restrictiveConditions==2>
                                                        <input name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" type="text" class="produce-right" value="${attribute.value}"
                                                               onKeyUp="this.value=this.value.replace(/\D/g,'')" />
                                                    <#elseif attribute.restrictiveConditions==3>
                                                        <input name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" type="text" class="produce-right" value="${attribute.value}"
                                                               onkeyup="this.value=this.value.replace(/[^\w\.\/]/ig,'')" />
                                                    <#elseif attribute.restrictiveConditions==4>
                                                        <input name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" type="text" class="produce-right" value="${attribute.value}" />
                                                    <#elseif attribute.restrictiveConditions==5>
                                                        <input name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" type="hidden" value="${attribute.value}" readonly/>
                                                        <input type="text" class="produce-right" value="${attribute.defaultUserName}" readonly>
                                                    </#if>
                                                <#elseif attribute.controlType==2>
                                                    <#if attribute.dataSourceType==1>
                                                        <select name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" class="produce-right">
                                                            <option value=""></option>
                                                            <#if attribute.dictionaryDetails??&&attribute.dictionaryDetails?size!=0>
                                                                <#list attribute.dictionaryDetails as details>
                                                                    <option value="${details.dataDictionaryDetailsId}" <#if (attribute.value?number)==details.dataDictionaryDetailsId>selected</#if>>${details.CName}</option>
                                                                </#list>
                                                            <#else >
                                                                <option value="">暂无数据，请至数据字典添加</option>
                                                            </#if>
                                                        </select>
                                                    <#elseif attribute.dataSourceType==2>
                                                        <select name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" class="produce-right">
                                                            <option value=""></option>
                                                            <#if baUserList??&&baUserList?size!=0>
                                                                <#list baUserList as user>
                                                                    <option value="${user.userId}" <#if (attribute.value?number)==user.userId>selected</#if>>${user.CName}</option>
                                                                </#list>
                                                            </#if>
                                                        </select>
                                                    </#if>
                                                <#elseif attribute.controlType==3>
                                                    <input name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" type="text" class="txt Wdate" style="width:70%;height:20px;border:1px solid #C0C0C0;" value="${((attribute.value?date)?string("yyyy-MM-dd HH:mm:ss"))!}"
                                                           onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                                                </#if>
                                            <#else>
                                                <#if attribute.controlType==1||attribute.controlType==5>
                                                    <#if attribute.restrictiveConditions==1>
                                                        <input name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" type="text" class="produce-right" />
                                                    <#elseif attribute.restrictiveConditions==2>
                                                        <input name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" type="text" class="produce-right"
                                                               onKeyUp="this.value=this.value.replace(/\D/g,'')" />
                                                    <#elseif attribute.restrictiveConditions==3>
                                                        <input name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" type="text" class="produce-right"
                                                               onkeyup="this.value=this.value.replace(/[^\w\.\/]/ig,'')" />
                                                    <#elseif attribute.restrictiveConditions==4>
                                                        <input name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" type="text" class="produce-right" />
                                                    <#elseif attribute.restrictiveConditions==5>
                                                        <input name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" type="hidden"  value="${shiroUser.id}" readonly/>
                                                        <input type="text" class="produce-right" value="${shiroUser.name}" readonly>
                                                    </#if>
                                                <#elseif attribute.controlType==2>
                                                    <#if attribute.dataSourceType==1>
                                                        <select name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" class="produce-right">
                                                            <option value=""></option>
                                                            <#if attribute.dictionaryDetails??&&attribute.dictionaryDetails?size!=0>
                                                                <#list attribute.dictionaryDetails as details>
                                                                    <option value="${details.dataDictionaryDetailsId}">${details.CName}</option>
                                                                </#list>
                                                            <#else >
                                                                <option value="">暂无数据，请至数据字典添加</option>
                                                            </#if>
                                                        </select>
                                                    <#elseif attribute.dataSourceType==2>
                                                        <select name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" class="produce-right">
                                                            <option value=""></option>
                                                            <#if baUserList??&&baUserList?size!=0>
                                                                <#list baUserList as user>
                                                                    <option value="${user.userId}">${user.CName}</option>
                                                                </#list>
                                                            </#if>
                                                        </select>
                                                    </#if>
                                                <#elseif attribute.controlType==3>
                                                    <input name="workProcessId${workProcess.workProcessId}formAttributeValueId${formAttributeValue.formAttributeValueId}attributeId${attribute.formAttributeId}" type="text" class="txt Wdate" style="width:70%;height:20px;border:1px solid #C0C0C0;"
                                                           onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                                                </#if>
                                            </#if>
                                        </div>
                                    </#list>
                                </#if>
                            </#list>
                        </div>
                    <#else >
                        <#if workProcess.tableField3??&&workProcess.tableField3?size!=0>
                            <div class="record-list" id="workProcessId${workProcess.workProcessId}tableField3">
                                <p class="produce-title">巡检记录</p>
                                <#list workProcess.tableField3 as attribute>
                                    <div class="col-md-4" id="divworkProcessId${workProcess.workProcessId}tableField3attributeId${attribute.formAttributeId}" onchange="updValue(${workProcess.workProcessId},${attribute.formAttributeId},3)">
                                        <input type="hidden" name="attributeId" value="${attribute.formAttributeId}">
                                        <div class="produce-left">${attribute.propertyName}:</div>
                                        <#if attribute.controlType==1||attribute.controlType==5>
                                            <#if attribute.restrictiveConditions==1>
                                                <input name="workProcessId${workProcess.workProcessId}tableField3attributeId${attribute.formAttributeId}" type="text" class="produce-right" />
                                            <#elseif attribute.restrictiveConditions==2>
                                                <input name="workProcessId${workProcess.workProcessId}tableField3attributeId${attribute.formAttributeId}" type="text" class="produce-right"
                                                       onKeyUp="this.value=this.value.replace(/\D/g,'')" />
                                            <#elseif attribute.restrictiveConditions==3>
                                                <input name="workProcessId${workProcess.workProcessId}tableField3attributeId${attribute.formAttributeId}" type="text" class="produce-right"
                                                       onkeyup="this.value=this.value.replace(/[^\w\.\/]/ig,'')" />
                                            <#elseif attribute.restrictiveConditions==4>
                                                <input name="workProcessId${workProcess.workProcessId}tableField3attributeId${attribute.formAttributeId}" type="text" class="produce-right" />
                                            <#elseif attribute.restrictiveConditions==5>
                                                <input name="workProcessId${workProcess.workProcessId}tableField3attributeId${attribute.formAttributeId}" type="hidden"  value="" readonly/>
                                                <input type="text" class="produce-right" value="${shiroUser.name}" readonly>
                                            </#if>
                                        <#elseif attribute.controlType==2>
                                            <#if attribute.dataSourceType==1>
                                                <select name="workProcessId${workProcess.workProcessId}tableField3attributeId${attribute.formAttributeId}" class="produce-right">
                                                    <option value=""></option>
                                                    <#if attribute.dictionaryDetails??&&attribute.dictionaryDetails?size!=0>
                                                        <#list attribute.dictionaryDetails as details>
                                                            <option value="${details.dataDictionaryDetailsId}">${details.CName}</option>
                                                        </#list>
                                                    <#else >
                                                        <option value="">暂无数据，请至数据字典添加</option>
                                                    </#if>
                                                </select>
                                            <#elseif attribute.dataSourceType==2>
                                                <select name="workProcessId${workProcess.workProcessId}tableField3attributeId${attribute.formAttributeId}" class="produce-right">
                                                    <option value=""></option>
                                                    <#if baUserList??&&baUserList?size!=0>
                                                        <#list baUserList as user>
                                                            <option value="${user.userId}">${user.CName}</option>
                                                        </#list>
                                                    </#if>
                                                </select>
                                            </#if>
                                        <#elseif attribute.controlType==3>
                                            <input name="workProcessId${workProcess.workProcessId}tableField3attributeId${attribute.formAttributeId}" type="text" class="txt Wdate" style="width:70%;height:20px;border:1px solid #C0C0C0;"
                                                   onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                                        </#if>
                                    </div>
                                </#list>
                            </div>
                        </#if>
                    </#if>
                    </div>
                </div>
            <#else >
                <div class="produce-container" style="margin-top: 45px">
                    <div class="title">
                        <span class="produce-num">${workProcess_index+1}</span>
                        <span class="title-name">${workProcess.CName}</span>
                    </div>
                    <div class="produce-box record">
                        <#if workProcess.tableField1??&&workProcess.tableField1?size!=0>
                        <div class="record-list" id="workProcessId${workProcess.workProcessId}tableField1">
                            <p class="produce-title">操作记录</p>
                            <#list workProcess.tableField1 as attribute>
                                <div class="col-md-4" id="divworkProcessId${workProcess.workProcessId}tableField1attributeId${attribute.formAttributeId}" onchange="updValue(${workProcess.workProcessId},${attribute.formAttributeId},1)">
                                    <input type="hidden" name="attributeId" value="${attribute.formAttributeId}">
                                    <div class="produce-left">${attribute.propertyName}:</div>
                                    <#if attribute.controlType==1||attribute.controlType==5>
                                        <#if attribute.restrictiveConditions==1>
                                            <input name="workProcessId${workProcess.workProcessId}tableField1attributeId${attribute.formAttributeId}" type="text" class="produce-right tableField1" />
                                        <#elseif attribute.restrictiveConditions==2>
                                            <input name="workProcessId${workProcess.workProcessId}tableField1attributeId${attribute.formAttributeId}" type="text" class="produce-right tableField1"
                                                   onKeyUp="this.value=this.value.replace(/\D/g,'')" />
                                        <#elseif attribute.restrictiveConditions==3>
                                            <input name="workProcessId${workProcess.workProcessId}tableField1attributeId${attribute.formAttributeId}" type="text" class="produce-right tableField1"
                                                   onkeyup="this.value=this.value.replace(/[^\w\.\/]/ig,'')" />
                                        <#elseif attribute.restrictiveConditions==4>
                                            <input name="workProcessId${workProcess.workProcessId}tableField1attributeId${attribute.formAttributeId}" type="text" class="produce-right tableField1" />
                                        <#elseif attribute.restrictiveConditions==5>
                                            <input name="workProcessId${workProcess.workProcessId}tableField1attributeId${attribute.formAttributeId}" type="hidden"  value="" readonly/>
                                            <input type="text" name="workProcessId${workProcess.workProcessId}tableField1attributeId${attribute.formAttributeId}name" class="produce-right" value="" readonly>
                                        </#if>
                                    <#elseif attribute.controlType==2>
                                        <#if attribute.dataSourceType==1>
                                            <select name="workProcessId${workProcess.workProcessId}tableField1attributeId${attribute.formAttributeId}" class="produce-right tableField1">
                                                <option value=""></option>
                                                <#if attribute.dictionaryDetails??&&attribute.dictionaryDetails?size!=0>
                                                    <#list attribute.dictionaryDetails as details>
                                                        <option value="${details.dataDictionaryDetailsId}">${details.CName}</option>
                                                    </#list>
                                                <#else >
                                                    <option value="">暂无数据，请至数据字典添加</option>
                                                </#if>
                                            </select>
                                        <#elseif attribute.dataSourceType==2>
                                            <#if attribute.propertyName=="审核人">
                                                <input name="workProcessId${workProcess.workProcessId}tableField1attributeId${attribute.formAttributeId}" type="hidden"  value="" readonly/>
                                                <input type="text" name="workProcessId${workProcess.workProcessId}tableField1attributeId${attribute.formAttributeId}name" class="produce-right" value="" readonly>
                                            <#else>
                                                <select name="workProcessId${workProcess.workProcessId}tableField1attributeId${attribute.formAttributeId}" class="produce-right tableField1">
                                                    <option value=""></option>
                                                    <#if baUserList??&&baUserList?size!=0>
                                                        <#list baUserList as user>
                                                            <option value="${user.userId}">${user.CName}</option>
                                                        </#list>
                                                    </#if>
                                                </select>
                                            </#if>
                                        </#if>
                                    <#elseif attribute.controlType==3>
                                        <input name="workProcessId${workProcess.workProcessId}tableField1attributeId${attribute.formAttributeId}" type="text" class="txt Wdate tableField1" style="width:70%;height:20px;border:1px solid #C0C0C0;"
                                               onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})" onchange/>
                                    </#if>
                                </div>
                            </#list>
                        </div>
                        </#if>
                        <#if workProcess.tableField2??&&workProcess.tableField2?size!=0>
                        <div class="record-list" id="workProcessId${workProcess.workProcessId}tableField2">
                            <p class="produce-title">审核记录</p>
                            <#list workProcess.tableField2 as attribute>
                                <div class="col-md-4" id="divworkProcessId${workProcess.workProcessId}tableField2attributeId${attribute.formAttributeId}" onchange="updValue(${workProcess.workProcessId},${attribute.formAttributeId},2)">
                                    <input type="hidden" name="attributeId" value="${attribute.formAttributeId}">
                                    <div class="produce-left">${attribute.propertyName}</div>
                                    <#if attribute.controlType==1||attribute.controlType==5>
                                        <#if attribute.restrictiveConditions==1>
                                            <input name="workProcessId${workProcess.workProcessId}tableField2attributeId${attribute.formAttributeId}" type="text" class="produce-right tableField2" />
                                        <#elseif attribute.restrictiveConditions==2>
                                            <input name="workProcessId${workProcess.workProcessId}tableField2attributeId${attribute.formAttributeId}" type="text" class="produce-right tableField2"
                                                   onKeyUp="this.value=this.value.replace(/\D/g,'')" />
                                        <#elseif attribute.restrictiveConditions==3>
                                            <input name="workProcessId${workProcess.workProcessId}tableField2attributeId${attribute.formAttributeId}" type="text" class="produce-right tableField2"
                                                   onkeyup="this.value=this.value.replace(/[^\w\.\/]/ig,'')" />
                                        <#elseif attribute.restrictiveConditions==4>
                                            <input name="workProcessId${workProcess.workProcessId}tableField2attributeId${attribute.formAttributeId}" type="text" class="produce-right tableField2" />
                                        <#elseif attribute.restrictiveConditions==5>
                                            <input name="workProcessId${workProcess.workProcessId}tableField2attributeId${attribute.formAttributeId}" type="hidden"  value="" readonly/>
                                            <input type="text" name="workProcessId${workProcess.workProcessId}tableField2attributeId${attribute.formAttributeId}name" class="produce-right" value="" readonly>
                                        </#if>
                                    <#elseif attribute.controlType==2>
                                        <#if attribute.dataSourceType==1>
                                            <select name="workProcessId${workProcess.workProcessId}tableField2attributeId${attribute.formAttributeId}" class="produce-right tableField2">
                                                <option value=""></option>
                                                <#if attribute.dictionaryDetails??&&attribute.dictionaryDetails?size!=0>
                                                    <#list attribute.dictionaryDetails as details>
                                                        <option value="${details.dataDictionaryDetailsId}">${details.CName}</option>
                                                    </#list>
                                                <#else >
                                                    <option value="">暂无数据，请至数据字典添加</option>
                                                </#if>
                                            </select>
                                        <#elseif attribute.dataSourceType==2>
                                            <select name="workProcessId${workProcess.workProcessId}tableField2attributeId${attribute.formAttributeId}" class="produce-right tableField2">
                                                <option value=""></option>
                                                <#if baUserList??&&baUserList?size!=0>
                                                    <#list baUserList as user>
                                                        <option value="${user.userId}">${user.CName}</option>
                                                    </#list>
                                                </#if>
                                            </select>
                                        </#if>
                                    <#elseif attribute.controlType==3>
                                        <input name="workProcessId${workProcess.workProcessId}tableField2attributeId${attribute.formAttributeId}" type="text" class="txt Wdate tableField2" style="width:70%;height:20px;border:1px solid #C0C0C0;"
                                               onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})" onchange/>
                                    </#if>
                                </div>
                            </#list>
                        </div>
                        </#if>
                        <#if workProcess.tableField3??&&workProcess.tableField3?size!=0>
                        <div class="record-list" id="workProcessId${workProcess.workProcessId}tableField3">
                            <p class="produce-title">巡检记录</p>
                            <#list workProcess.tableField3 as attribute>
                                <div class="col-md-4" id="divworkProcessId${workProcess.workProcessId}tableField3attributeId${attribute.formAttributeId}" onchange="updValue(${workProcess.workProcessId},${attribute.formAttributeId},3)">
                                    <input type="hidden" name="attributeId" value="${attribute.formAttributeId}">
                                    <div class="produce-left">${attribute.propertyName}</div>
                                    <#if attribute.controlType==1||attribute.controlType==5>
                                        <#if attribute.restrictiveConditions==1>
                                            <input name="workProcessId${workProcess.workProcessId}tableField3attributeId${attribute.formAttributeId}" type="text" class="produce-right tableField3" />
                                        <#elseif attribute.restrictiveConditions==2>
                                            <input name="workProcessId${workProcess.workProcessId}tableField3attributeId${attribute.formAttributeId}" type="text" class="produce-right tableField3"
                                                   onKeyUp="this.value=this.value.replace(/\D/g,'')" />
                                        <#elseif attribute.restrictiveConditions==3>
                                            <input name="workProcessId${workProcess.workProcessId}tableField3attributeId${attribute.formAttributeId}" type="text" class="produce-right tableField3"
                                                   onkeyup="this.value=this.value.replace(/[^\w\.\/]/ig,'')" />
                                        <#elseif attribute.restrictiveConditions==4>
                                            <input name="workProcessId${workProcess.workProcessId}tableField3attributeId${attribute.formAttributeId}" type="text" class="produce-right tableField3" />
                                        <#elseif attribute.restrictiveConditions==5>
                                            <input name="workProcessId${workProcess.workProcessId}tableField3attributeId${attribute.formAttributeId}" type="hidden"  value="" readonly/>
                                            <input type="text" name="workProcessId${workProcess.workProcessId}tableField3attributeId${attribute.formAttributeId}name" class="produce-right" value="" readonly>
                                        </#if>
                                    <#elseif attribute.controlType==2>
                                        <#if attribute.dataSourceType==1>
                                            <select name="workProcessId${workProcess.workProcessId}tableField3attributeId${attribute.formAttributeId}" class="produce-right tableField3">
                                                <option value=""></option>
                                                <#if attribute.dictionaryDetails??&&attribute.dictionaryDetails?size!=0>
                                                    <#list attribute.dictionaryDetails as details>
                                                        <option value="${details.dataDictionaryDetailsId}">${details.CName}</option>
                                                    </#list>
                                                <#else >
                                                    <option value="">暂无数据，请至数据字典添加</option>
                                                </#if>
                                            </select>
                                        <#elseif attribute.dataSourceType==2>
                                            <select name="workProcessId${workProcess.workProcessId}tableField3attributeId${attribute.formAttributeId}" class="produce-right tableField3">
                                                <option value=""></option>
                                                <#if baUserList??&&baUserList?size!=0>
                                                    <#list baUserList as user>
                                                        <option value="${user.userId}">${user.CName}</option>
                                                    </#list>
                                                </#if>
                                            </select>
                                        </#if>
                                    <#elseif attribute.controlType==3>
                                        <input name="workProcessId${workProcess.workProcessId}tableField3attributeId${attribute.formAttributeId}" type="text" class="txt Wdate tableField3" style="width:70%;height:20px;border:1px solid #C0C0C0;"
                                               onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})" onchange/>
                                    </#if>
                                </div>
                            </#list>
                        </div>
                        </#if>
                    </div>
                </div>
            </#if>
        </#list>
    <#else >
        无工序
    </#if>
</div>

<script>

    var resourceId = ${resourceId};

    var produceTaskId = ${produceTaskId};

    $(document).ready(function () {
        Loading(false);
    });

    function updateAttributeValue() {
        var pBaFormAttributeValueList = new Array();
            var allData = {
                produceTaskId: produceTaskId,
                resourceId: resourceId,
                pBaFormAttributeValueList: pBaFormAttributeValueList
            };
            <#list pZsWorkProcessList as workProcess>
                var workProcessId = ${workProcess.workProcessId};
                var workProcessName = "${workProcess.CName}";
                <#if workProcess.PBaFormAttributeValueList1??&&0!=workProcess.PBaFormAttributeValueList1?size>
                    <#list workProcess.PBaFormAttributeValueList1 as formAttributes1>
                        var attributeValueDataList = new Array();
                        var pBaFormAttributeValue = {
                            formAttributeValueId: "",
                            produceTaskId: produceTaskId,
                            workProcessId: workProcessId,
                            workProcessName: workProcessName,
                            handleType: 1,
                            loggerJson: "",
                            attributeValueDataList: attributeValueDataList
                        };
                        var formAttributeValueId = ${formAttributes1.formAttributeValueId};
                        pBaFormAttributeValue.formAttributeValueId = formAttributeValueId;
                        var loggerJson = ${formAttributes1.loggerJson}
                        pBaFormAttributeValue.loggerJson = loggerJson;
                        <#list formAttributes1.PBaFormAttributeList as formAttributeList>
                                var attributeValueData = {
                                    attributeId: "",
                                    value: ""
                                };
                                var attributeId = ${formAttributeList.formAttributeId};
                                attributeValueData.attributeId = attributeId;
                                var valueId = "[name=workProcessId" + workProcessId + "formAttributeValueId" + formAttributeValueId + "attributeId" + attributeId+"]";
                                var valued = $.trim($(""+valueId).val());
                                if(null!=valued&&""!=valued){
                                    attributeValueData.value = valued;
                                    attributeValueDataList.push(attributeValueData);
                                }else {
                                    if(1==${formAttributeList.restrictiveConditions}){
                                        tipDialog("${workProcess.CName}-${formAttributeList.propertyName}不能为空",4,"warning");
                                        return false;
                                    }
                                }
                        </#list>
                        pBaFormAttributeValue.attributeValueDataList = attributeValueDataList;
                        pBaFormAttributeValueList.push(pBaFormAttributeValue);
                    </#list>
                    <#if workProcess.PBaFormAttributeValueList2??&&0!=workProcess.PBaFormAttributeValueList2?size>
                        <#list workProcess.PBaFormAttributeValueList2 as formAttributes2>
                            var attributeValueDataList = new Array();
                            var pBaFormAttributeValue = {
                                formAttributeValueId: "",
                                produceTaskId: produceTaskId,
                                workProcessId: workProcessId,
                                workProcessName: workProcessName,
                                handleType: 2,
                                loggerJson: "",
                                attributeValueDataList: attributeValueDataList
                            };
                            var formAttributeValueId = ${formAttributes2.formAttributeValueId};
                            pBaFormAttributeValue.formAttributeValueId = formAttributeValueId;
                            var loggerJson = ${formAttributes2.loggerJson}
                            pBaFormAttributeValue.loggerJson = loggerJson;
                            <#list formAttributes2.PBaFormAttributeList as formAttributeList>
                                var attributeValueData = {
                                    attributeId: "",
                                    value: ""
                                };
                                var attributeId = ${formAttributeList.formAttributeId};
                                attributeValueData.attributeId = attributeId;
                                var valueId = "[name=workProcessId" + workProcessId + "formAttributeValueId" + formAttributeValueId + "attributeId" + attributeId+"]";
                                var valued = $.trim($(""+valueId).val());
                                if(null!=valued&&""!=valued){
                                    attributeValueData.value = valued;
                                    attributeValueDataList.push(attributeValueData);
                                }else {
                                    if(1==${formAttributeList.restrictiveConditions}){
                                        tipDialog("${workProcess.CName}-${formAttributeList.propertyName}不能为空",4,"warning");
                                        return false;
                                    }
                                }
                            </#list>
                            pBaFormAttributeValue.attributeValueDataList = attributeValueDataList;
                            pBaFormAttributeValueList.push(pBaFormAttributeValue);
                        </#list>
                    <#else>
                        <#if workProcess.tableField2??&&workProcess.tableField2?size!=0>
                            var attributeValueDataList = new Array();
                            var pBaFormAttributeValue = {
                                formAttributeValueId: "",
                                produceTaskId: produceTaskId,
                                workProcessId: workProcessId,
                                workProcessName: workProcessName,
                                handleType: 2,
                                loggerJson: "",
                                attributeValueDataList: attributeValueDataList
                            };
                            var result2 = 0;
                            $("#workProcessId"+workProcessId+"tableField2 input[type='text']").each(function () {
                                var resultValue2=$(this).val();
                                if(null!=resultValue2&&""!=resultValue2){
                                    result2 = 1;
                                }
                            });
                            if(1==result2){
                            <#list workProcess.tableField2 as formAttributes2>
                                    var attributeValueData = {
                                        attributeId: "",
                                        value: ""
                                    };
                                    var attributeId = ${formAttributes2.formAttributeId};
                                    attributeValueData.attributeId = attributeId;
                                var valueId = "[name=workProcessId" + workProcessId + "tableField2attributeId" + attributeId+"]";
                                var valued = $.trim($("" + valueId).val());
                                if(null!=valued&&""!=valued){
                                    attributeValueData.value = valued;
                                    attributeValueDataList.push(attributeValueData);
                                }else {
                                    if(1==${formAttributes2.restrictiveConditions}){
                                        tipDialog("${workProcess.CName}-${formAttributes2.propertyName}不能为空",4,"warning");
                                        return false;
                                    }
                                }
                            </#list>
                            }
                            pBaFormAttributeValue.attributeValueDataList = attributeValueDataList;
                            pBaFormAttributeValueList.push(pBaFormAttributeValue);
                        </#if>
                    </#if>
                    <#if workProcess.PBaFormAttributeValueList3??&&0!=workProcess.PBaFormAttributeValueList3?size>
                        <#list workProcess.PBaFormAttributeValueList3 as formAttributes3>
                            var attributeValueDataList = new Array();
                            var pBaFormAttributeValue = {
                                formAttributeValueId: "",
                                produceTaskId: produceTaskId,
                                workProcessId: workProcessId,
                                workProcessName: workProcessName,
                                handleType: 3,
                                loggerJson: "",
                                attributeValueDataList: attributeValueDataList
                            };
                            var formAttributeValueId = ${formAttributes3.formAttributeValueId};
                            pBaFormAttributeValue.formAttributeValueId = formAttributeValueId;
                            var loggerJson = ${formAttributes3.loggerJson}
                            pBaFormAttributeValue.loggerJson = loggerJson;
                            <#list formAttributes3.PBaFormAttributeList as formAttributeList>
                                var attributeValueData = {
                                    attributeId: "",
                                    value: ""
                                };
                                var attributeId = ${formAttributeList.formAttributeId};
                                attributeValueData.attributeId = attributeId;
                                var valueId = "[name=workProcessId" + workProcessId + "formAttributeValueId" + formAttributeValueId + "attributeId" + attributeId+"]";
                                var valued = $.trim($(""+valueId).val());
                                if(null!=valued&&""!=valued){
                                    attributeValueData.value = valued;
                                    attributeValueDataList.push(attributeValueData);
                                }else {
                                    if(1==${formAttributeList.restrictiveConditions}){
                                        tipDialog("${workProcess.CName}-${formAttributeList.propertyName}不能为空",4,"warning");
                                        return false;
                                    }
                                }
                            </#list>
                            pBaFormAttributeValue.attributeValueDataList = attributeValueDataList;
                            pBaFormAttributeValueList.push(pBaFormAttributeValue);
                        </#list>
                    <#else >
                        <#if workProcess.tableField3??&&workProcess.tableField3?size!=0>
                            var attributeValueDataList = new Array();
                            var pBaFormAttributeValue = {
                                formAttributeValueId: "",
                                produceTaskId: produceTaskId,
                                workProcessId: workProcessId,
                                workProcessName: workProcessName,
                                handleType: 3,
                                loggerJson: "",
                                attributeValueDataList: attributeValueDataList
                            };
                            var result3 = 0;
                            $("#workProcessId"+workProcessId+"tableField3 input[type='text']").each(function () {
                                var resultValue3=$(this).val();
                                if(null!=resultValue3&&""!=resultValue3){
                                    result3 = 1;
                                }
                            });
                            if(1==result3){
                                var res3 = 0;
                                $("#workProcessId"+workProcessId+"tableField2 input[type='text']").each(function () {
                                    var resultValue2=$(this).val();
                                    if(null!=resultValue2&&""!=resultValue2){
                                        res3 = 1;
                                    }
                                });
                                if(1==res3){
                            <#list workProcess.tableField3 as formAttributes3>
                                    var attributeValueData = {
                                        attributeId: "",
                                        value: ""
                                    };
                                    var attributeId = ${formAttributes3.formAttributeId};
                                    attributeValueData.attributeId = attributeId;
                                var valueId = "[name=workProcessId" + workProcessId + "tableField3attributeId" + attributeId+"]";
                                var valued = $.trim($("" + valueId).val());
                                if(null!=valued&&""!=valued){
                                    attributeValueData.value = valued;
                                    attributeValueDataList.push(attributeValueData);
                                }else {
                                    if(1==${formAttributes3.restrictiveConditions}){
                                        tipDialog("${workProcess.CName}-${formAttributes3.propertyName}不能为空",4,"warning");
                                        return false;
                                    }
                                }
                            </#list>
                                }else {
                                    tipDialog("请先填写${workProcess.CName}-审核记录",4,"warning");
                                    return false;
                                }
                            }
                            pBaFormAttributeValue.attributeValueDataList = attributeValueDataList;
                            pBaFormAttributeValueList.push(pBaFormAttributeValue);
                        </#if>
                    </#if>
                <#else >
                    <#if workProcess.tableField1??&&workProcess.tableField1?size!=0>
                        var attributeValueDataList = new Array();
                        var pBaFormAttributeValue = {
                            formAttributeValueId: "",
                            produceTaskId: produceTaskId,
                            workProcessId: workProcessId,
                            workProcessName: workProcessName,
                            handleType: 1,
                            loggerJson: "",
                            attributeValueDataList: attributeValueDataList
                        };
                        var result1 = 0;
                        $("#workProcessId"+workProcessId+"tableField1 input[type='text']").each(function () {
                            var resultValue1=$(this).val();
                            if(null!=resultValue1&&""!=resultValue1){
                                result1 = 1;
                            }
                        });
                        if(1==result1){
                        <#list workProcess.tableField1 as formAttributes1>
                                var attributeValueData = {
                                    attributeId: "",
                                    value: ""
                                };
                                var attributeId = ${formAttributes1.formAttributeId};
                                attributeValueData.attributeId = attributeId;
                                var valueId = "[name=workProcessId" + workProcessId + "tableField1attributeId" + attributeId+"]";
                                var valued = $.trim($("" + valueId).val());
                                if(null!=valued&&""!=valued){
                                        attributeValueData.value = valued;
                                        attributeValueDataList.push(attributeValueData);
                                }else {
                                    if(1==${formAttributes1.restrictiveConditions}){
                                        tipDialog("${workProcess.CName}-${formAttributes1.propertyName}不能为空",4,"warning");
                                        return false;
                                    }
                                }
                        </#list>
                        }
                        pBaFormAttributeValue.attributeValueDataList = attributeValueDataList;
                        pBaFormAttributeValueList.push(pBaFormAttributeValue);
                    </#if>
                    <#if workProcess.tableField2??&&workProcess.tableField2?size!=0>
                        var attributeValueDataList = new Array();
                        var pBaFormAttributeValue = {
                            formAttributeValueId: "",
                            produceTaskId: produceTaskId,
                            workProcessId: workProcessId,
                            workProcessName: workProcessName,
                            handleType: 2,
                            loggerJson: "",
                            attributeValueDataList: attributeValueDataList
                        };
                        var result2 = 0;
                        $("#workProcessId"+workProcessId+"tableField2 input[type='text']").each(function () {
                            var resultValue2=$(this).val();
                            if(null!=resultValue2&&""!=resultValue2){
                                result2 = 1;
                            }
                        });
                        if(1==result2) {
                            var res2 = 0;
                            $("#workProcessId"+workProcessId+"tableField1 input[type='text']").each(function () {
                                var nowValue = $(this).val();
                                if (null != nowValue && "" != nowValue) {
                                    res2 = 1;
                                }
                            });
                            if (1==res2) {
                                <#list workProcess.tableField2 as formAttributes2>
                                    var attributeValueData = {
                                        attributeId: "",
                                        value: ""
                                    };
                                    var attributeId = ${formAttributes2.formAttributeId};
                                    attributeValueData.attributeId = attributeId;
                                    var valueId = "[name=workProcessId" + workProcessId + "tableField2attributeId" + attributeId + "]";
                                    var valued = $.trim($("" + valueId).val());
                                    if (null != valued && "" != valued) {
                                        attributeValueData.value = valued;
                                        attributeValueDataList.push(attributeValueData);
                                    } else {
                                        if (1 ==${formAttributes2.restrictiveConditions}) {
                                            tipDialog("${workProcess.CName}-${formAttributes2.propertyName}不能为空", 4, "warning");
                                            return false;
                                        }
                                    }
                                </#list>
                            } else {
                                tipDialog("请先填写${workProcess.CName}-操作记录", 4, "warning");
                                return false;
                            }
                        }
                        pBaFormAttributeValue.attributeValueDataList = attributeValueDataList;
                        pBaFormAttributeValueList.push(pBaFormAttributeValue);
                    </#if>
                    <#if workProcess.tableField3??&&workProcess.tableField3?size!=0>
                        var attributeValueDataList = new Array();
                        var pBaFormAttributeValue = {
                            formAttributeValueId: "",
                            produceTaskId: produceTaskId,
                            workProcessId: workProcessId,
                            workProcessName: workProcessName,
                            handleType: 3,
                            loggerJson: "",
                            attributeValueDataList: attributeValueDataList
                        };
                        var result3 = 0;
                        $("#workProcessId"+workProcessId+"tableField3 input[type='text']").each(function () {
                            var resultValue3=$(this).val();
                            if(null!=resultValue3&&""!=resultValue3){
                                result3 = 1;
                            }
                        });
                        if(1==result3){
                        <#list workProcess.tableField3 as formAttributes3>
                                var attributeValueData = {
                                    attributeId: "",
                                    value: ""
                                };
                                var attributeId = ${formAttributes3.formAttributeId};
                                attributeValueData.attributeId = attributeId;
                                var valueId = "[name=workProcessId" + workProcessId + "tableField3attributeId" + attributeId+"]";
                                var valued = $.trim($("" + valueId).val());
                                if(null!=valued&&""!=valued){
                                    var res3 = 0;
                                    $("#workProcessId"+workProcessId+"tableField2 input[class='produce-right tableField2']").each(function () {
                                        var nowValue=$(this).val();
                                        if(null!=nowValue&&""!=nowValue){
                                            res3 = 1;
                                        }
                                    });
                                    $("#workProcessId"+workProcessId+"tableField2 input[class='txt Wdate tableField2']").each(function () {
                                        var nowValue=$(this).val();
                                        if(null!=nowValue&&""!=nowValue){
                                            res3 = 1;
                                        }
                                    });
                                    if(1==res3){
                                        attributeValueData.value = valued;
                                        attributeValueDataList.push(attributeValueData);
                                    }else {
                                        tipDialog("请先填写${workProcess.CName}-审核记录",4,"warning");
                                        return false;
                                    }
                                }else {
                                    if(1==${formAttributes3.restrictiveConditions}){
                                        tipDialog("${workProcess.CName}-${formAttributes3.propertyName}不能为空",4,"warning");
                                        return false;
                                    }
                                }
                        </#list>
                        }
                        pBaFormAttributeValue.attributeValueDataList = attributeValueDataList;
                        pBaFormAttributeValueList.push(pBaFormAttributeValue);
                    </#if>
                </#if>
            </#list>
        allData.pBaFormAttributeValueList = pBaFormAttributeValueList;
        $.ajax({
            type: "POST",
            url: "${request.contextPath}/formAttributeValue/updateFormAttributeValue.json",
            data:{test: ""+JSON.stringify(allData)},
            async:false,
            success: function (res) {
                if (res.success){
                    tipDialog(res.msg,4,1);
                }else {
                    tipDialog(res.msg,4,0);
                }
            },
            error: function (xhr) {
                tipDialog("网络异常",4,0);
            }
        });
        allData="";
    }

    function updValue(workProcessId,attributeId,handleType){
        if(1==handleType){
          var nameAttributeValue1 = $.trim($("[name=workProcessId"+workProcessId+"tableField1attributeId"+attributeId+"]").val());
          if(null!=nameAttributeValue1&&""!=nameAttributeValue1){
              $.ajax({
                  type: "POST",
                  url: "${request.contextPath}/formAttribute/getDefaultFormAttributeByWorkProcessIdAndName.json",
                  data: {workProcessId: workProcessId},
                  async:false,
                  success: function (result) {
                      if(result.success){
                          var attribute2List = new Array();
//                          if(1==handleType){
                              for(var i = 0; i < result.obj.length; i++){
                                  if(1==result.obj[i].handleType&&5==result.obj[i].restrictiveConditions){
                                      var name = "[name=workProcessId"+workProcessId+"tableField1attributeId"+result.obj[i].formAttributeId+"]";
                                      $(""+name).val(${shiroUser.id});
                                      var valueName = "[name=workProcessId"+workProcessId+"tableField1attributeId"+result.obj[i].formAttributeId+"name]";
                                      $(""+valueName).val("${shiroUser.name}");
                                  }
                                  if(1==result.obj[i].handleType&&"审核人"==result.obj[i].propertyName){
                                      var name = "[name=workProcessId"+workProcessId+"tableField1attributeId"+result.obj[i].formAttributeId+"]";
                                      $(""+name).val(${shiroUser.id});
                                      var valueName = "[name=workProcessId"+workProcessId+"tableField1attributeId"+result.obj[i].formAttributeId+"name]";
                                      $(""+valueName).val("${shiroUser.name}");
                                  }
                                  if(2==result.obj[i].handleType){
                                     attribute2List.push(result.obj[i]);
                                  }
                              }
                              if(1==attribute2List.length&&"审核人"==attribute2List[0].propertyName){
                                  var name = "[name=workProcessId"+workProcessId+"tableField2attributeId"+attribute2List[0].formAttributeId+"]";
                                  $(""+name).val(${shiroUser.id});
                                  var valueName = "[name=workProcessId"+workProcessId+"tableField2attributeId"+attribute2List[0].formAttributeId+"name]";
                                  $(""+valueName).val("${shiroUser.name}");
                              }
//                          }
                      }else {
                          tipDialog(result.msg,4,0);
                      }
                  },
                  error: function (xhr) {
                      tipDialog("网络异常",4,0);
                  }
              });
          }else {
              var res1 = 0;
              $("#workProcessId"+workProcessId+"tableField1 input[class='produce-right tableField1']").each(function () {
                  var nowValue1=$(this).val();
                  if(null!=nowValue1&&""!=nowValue1){
                      res1 = 1;
                  }
              });
              $("#workProcessId"+workProcessId+"tableField1 input[class='txt Wdate tableField1']").each(function () {
                  var dateValue1=$(this).val();
                  if(null!=dateValue1&&""!=dateValue1){
                      res1 = 1;
                  }
              });
              if(res1==0){
                  $.ajax({
                      type: "POST",
                      url: "${request.contextPath}/formAttribute/getDefaultFormAttributeByWorkProcessIdAndName.json",
                      data: {workProcessId: workProcessId},
                      async:false,
                      success: function (result) {
                          if(result.success){
                              var attribute2List = new Array();
//                              if(1==handleType){
                                  for(var i = 0; i < result.obj.length; i++){
                                      if(1==result.obj[i].handleType&&5==result.obj[i].restrictiveConditions){
                                          var name = "[name=workProcessId"+workProcessId+"tableField1attributeId"+result.obj[i].formAttributeId+"]";
                                          $(""+name).val("");
                                          var valueName = "[name=workProcessId"+workProcessId+"tableField1attributeId"+result.obj[i].formAttributeId+"name]";
                                          $(""+valueName).val("");
                                      }
                                      if(1==result.obj[i].handleType&&"审核人"==result.obj[i].propertyName){
                                          var name = "[name=workProcessId"+workProcessId+"tableField1attributeId"+result.obj[i].formAttributeId+"]";
                                          $(""+name).val("");
                                          var valueName = "[name=workProcessId"+workProcessId+"tableField1attributeId"+result.obj[i].formAttributeId+"name]";
                                          $(""+valueName).val("");
                                      }
                                      if(2==result.obj[i].handleType){
                                          attribute2List.push(result.obj[i]);
                                      }
                                  }
                              if(1==attribute2List.length&&"审核人"==attribute2List[0].propertyName){
                                  var name = "[name=workProcessId"+workProcessId+"tableField2attributeId"+attribute2List[0].formAttributeId+"]";
                                  $(""+name).val("");
                                  var valueName = "[name=workProcessId"+workProcessId+"tableField2attributeId"+attribute2List[0].formAttributeId+"name]";
                                  $(""+valueName).val("");
                              }
//                              }
                          }else {
                              tipDialog(result.msg,4,0);
                          }
                      },
                      error: function (xhr) {
                          tipDialog("网络异常",4,0);
                      }
                  });
              }
          }
        }
        if(2==handleType){
            var nameAttributeValue2 = $.trim($("[name=workProcessId"+workProcessId+"tableField2attributeId"+attributeId+"]").val());
            if(null!=nameAttributeValue2&&""!=nameAttributeValue2){
                $.ajax({
                    type: "POST",
                    url: "${request.contextPath}/formAttribute/getDefaultFormAttributeByWorkProcessIdAndName.json",
                    data: {workProcessId: workProcessId},
                    async:false,
                    success: function (result) {
                        if(result.success){
//                            if(1==handleType){
                                for(var i = 0; i < result.obj.length; i++){
                                    if(2==result.obj[i].handleType&&5==result.obj[i].restrictiveConditions){
                                        var name = "[name=workProcessId"+workProcessId+"tableField2attributeId"+result.obj[i].formAttributeId+"]";
                                        $(""+name).val(${shiroUser.id});
                                        var valueName = "[name=workProcessId"+workProcessId+"tableField2attributeId"+result.obj[i].formAttributeId+"name]";
                                        $(""+valueName).val("${shiroUser.name}");
                                    }
                                    if(2==result.obj[i].handleType&&"审核人"==result.obj[i].propertyName){
                                        var name = "[name=workProcessId"+workProcessId+"tableField2attributeId"+result.obj[i].formAttributeId+"]";
                                        $(""+name).val(${shiroUser.id});
                                        var valueName = "[name=workProcessId"+workProcessId+"tableField2attributeId"+result.obj[i].formAttributeId+"name]";
                                        $(""+valueName).val("${shiroUser.name}");
                                    }
                                }
//                            }
                        }else {
                            tipDialog(result.msg,4,0);
                        }
                    },
                    error: function (xhr) {
                        tipDialog("网络异常",4,0);
                    }
                });
            }else {
                var res2 = 0;
                $("#workProcessId"+workProcessId+"tableField2 input[class='produce-right tableField2']").each(function () {
                    var nowValue2=$(this).val();
                    if(null!=nowValue2&&""!=nowValue2){
                        res2 = 1;
                    }
                });
                $("#workProcessId"+workProcessId+"tableField2 input[class='txt Wdate tableField2']").each(function () {
                    var dateValue2=$(this).val();
                    if(null!=dateValue2&&""!=dateValue2){
                        res2 = 1;
                    }
                });
                if(res2==0){
                    $.ajax({
                        type: "POST",
                        url: "${request.contextPath}/formAttribute/getDefaultFormAttributeByWorkProcessIdAndName.json",
                        data: {workProcessId: workProcessId},
                        async:false,
                        success: function (result) {
                            if(result.success){
//                                if(1==handleType){
                                    for(var i = 0; i < result.obj.length; i++){
                                        if(2==result.obj[i].handleType&&5==result.obj[i].restrictiveConditions){
                                            var name = "[name=workProcessId"+workProcessId+"tableField2attributeId"+result.obj[i].formAttributeId+"]";
                                            $(""+name).val("");
                                            var valueName = "[name=workProcessId"+workProcessId+"tableField2attributeId"+result.obj[i].formAttributeId+"name]";
                                            $(""+valueName).val("");
                                        }
                                        if(2==result.obj[i].handleType&&"审核人"==result.obj[i].propertyName){
                                            var name = "[name=workProcessId"+workProcessId+"tableField2attributeId"+result.obj[i].formAttributeId+"]";
                                            $(""+name).val("");
                                            var valueName = "[name=workProcessId"+workProcessId+"tableField2attributeId"+result.obj[i].formAttributeId+"name]";
                                            $(""+valueName).val("");
                                        }
                                    }
//                                }
                            }else {
                                tipDialog(result.msg,4,0);
                            }
                        },
                        error: function (xhr) {
                            tipDialog("网络异常",4,0);
                        }
                    });
                }
            }
        }
        if(3==handleType){
            var nameAttributeValue3 = $.trim($("[name=workProcessId"+workProcessId+"tableField3attributeId"+attributeId+"]").val());
            if(null!=nameAttributeValue3&&""!=nameAttributeValue3){
                $.ajax({
                    type: "POST",
                    url: "${request.contextPath}/formAttribute/getDefaultFormAttributeByWorkProcessIdAndName.json",
                    data: {workProcessId: workProcessId},
                    async:false,
                    success: function (result) {
                        if(result.success){
//                            if(1==handleType){
                                for(var i = 0; i < result.obj.length; i++){
                                    if(3==result.obj[i].handleType&&5==result.obj[i].restrictiveConditions){
                                        var name = "[name=workProcessId"+workProcessId+"tableField3attributeId"+result.obj[i].formAttributeId+"]";
                                        $(""+name).val(${shiroUser.id});
                                        var valueName = "[name=workProcessId"+workProcessId+"tableField3attributeId"+result.obj[i].formAttributeId+"name]";
                                        $(""+valueName).val("${shiroUser.name}");
                                    }
                                }
//                            }
                        }else {
                            tipDialog(result.msg,4,0);
                        }
                    },
                    error: function (xhr) {
                        tipDialog("网络异常",4,0);
                    }
                });
            }else {
                var res3 = 0;
                $("#workProcessId"+workProcessId+"tableField3 input[class='produce-right tableField3']").each(function () {
                    var nowValue3=$(this).val();
                    if(null!=nowValue3&&""!=nowValue3){
                        res3 = 1;
                    }
                });
                $("#workProcessId"+workProcessId+"tableField3 input[class='txt Wdate tableField3']").each(function () {
                    var dateValue3=$(this).val();
                    if(null!=dateValue3&&""!=dateValue3){
                        res3 = 1;
                    }
                });
                if(res3==0){
                    $.ajax({
                        type: "POST",
                        url: "${request.contextPath}/formAttribute/getDefaultFormAttributeByWorkProcessIdAndName.json",
                        data: {workProcessId: workProcessId},
                        async:false,
                        success: function (result) {
                            if(result.success){
//                                if(1==handleType){
                                    for(var i = 0; i < result.obj.length; i++){
                                        if(3==result.obj[i].handleType&&5==result.obj[i].restrictiveConditions){
                                            var name = "[name=workProcessId"+workProcessId+"tableField3attributeId"+result.obj[i].formAttributeId+"]";
                                            $(""+name).val("");

                                            var valueName = "[name=workProcessId"+workProcessId+"tableField3attributeId"+result.obj[i].formAttributeId+"name]";
                                            $(""+valueName).val("");
                                        }
                                    }
//                                }
                            }else {
                                tipDialog(result.msg,4,0);
                            }
                        },
                        error: function (xhr) {
                            tipDialog("网络异常",4,0);
                        }
                    });
                }
            }
        }
    }
    
    function updValueWithReviewer(workProcessId,attributeId,reviewerId,reviewerName) {
        var nameAttributeValue2 = $.trim($("[name=workProcessId"+workProcessId+"tableField2attributeId"+attributeId+"]").val());
        if(null!=nameAttributeValue2&&""!=nameAttributeValue2){
            $.ajax({
                type: "POST",
                url: "${request.contextPath}/formAttribute/getDefaultFormAttributeByWorkProcessIdAndName.json",
                data: {workProcessId: workProcessId},
                async:false,
                success: function (result) {
                    if(result.success){
//                        if(1==handleType){
                            for(var i = 0; i < result.obj.length; i++){
                                if(2==result.obj[i].handleType&&5==result.obj[i].restrictiveConditions){
                                    var name = "[name=workProcessId"+workProcessId+"tableField2attributeId"+result.obj[i].formAttributeId+"]";
                                    $(""+name).val(${shiroUser.id});
                                    var valueName = "[name=workProcessId"+workProcessId+"tableField2attributeId"+result.obj[i].formAttributeId+"name]";
                                    $(""+valueName).val("${shiroUser.name}");
                                }
                                if(2==result.obj[i].handleType&&"审核人"==result.obj[i].propertyName){
                                    var name = "[name=workProcessId"+workProcessId+"tableField2attributeId"+result.obj[i].formAttributeId+"]";
                                    $(""+name).val(reviewerId);
                                    var valueName = "[name=workProcessId"+workProcessId+"tableField2attributeId"+result.obj[i].formAttributeId+"name]";
                                    $(""+valueName).val(reviewerName);
                                }
                            }
//                        }
                    }else {
                        tipDialog(result.msg,4,0);
                    }
                },
                error: function (xhr) {
                    tipDialog("网络异常",4,0);
                }
            });
        }else {
            var res2 = 0;
            $("#workProcessId"+workProcessId+"tableField2 input[class='produce-right tableField2']").each(function () {
                var nowValue=$(this).val();
                if(null!=nowValue&&""!=nowValue){
                    res2 = 1;
                }
            });
            $("#workProcessId"+workProcessId+"tableField2 input[class='txt Wdate tableField2']").each(function () {
                var dateValue=$(this).val();
                if(null!=dateValue&&""!=dateValue){
                    res2 = 1;
                }
            });
            if(res2==0){
                $.ajax({
                    type: "POST",
                    url: "${request.contextPath}/formAttribute/getDefaultFormAttributeByWorkProcessIdAndName.json",
                    data: {workProcessId: workProcessId},
                    async:false,
                    success: function (result) {
                        if(result.success){
//                            if(1==handleType){
                                for(var i = 0; i < result.obj.length; i++){
                                    if(2==result.obj[i].handleType&&5==result.obj[i].restrictiveConditions){
                                        var name = "[name=workProcessId"+workProcessId+"tableField2attributeId"+result.obj[i].formAttributeId+"]";
                                        $(""+name).val("");
                                        var valueName = "[name=workProcessId"+workProcessId+"tableField2attributeId"+result.obj[i].formAttributeId+"name]";
                                        $(""+valueName).val("");
                                    }
                                    if(2==result.obj[i].handleType&&"审核人"==result.obj[i].propertyName){
                                        var name = "[name=workProcessId"+workProcessId+"tableField2attributeId"+result.obj[i].formAttributeId+"]";
                                        $(""+name).val("");
                                        var valueName = "[name=workProcessId"+workProcessId+"tableField2attributeId"+result.obj[i].formAttributeId+"name]";
                                        $(""+valueName).val("");
                                    }
                                }
//                            }
                        }else {
                            tipDialog(result.msg,4,0);
                        }
                    },
                    error: function (xhr) {
                        tipDialog("网络异常",4,0);
                    }
                });
            }
        }
    }
</script>