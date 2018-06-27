
<#if workFlow??>
<form  style="margin: 1px">
    <div class="bd">
        <div class="ScrollBar" style="margin-top: 1px;">
            <div id="">
                <table class="form">
                    <tr>
                        <th class="formTitle">所属工艺：
                        </th>
                        <td class="formValue" colspan="3">
                            <input type="text" class="txt " value="${workFlow.CName}" disabled/>
                        </td>
                    </tr>
                    <tr>
                        <th class="formTitle">工艺名称：
                        </th>
                        <td class="formValue" colspan="3">
                            <input type="text" class="txt required" value="${workProcess.CName}" disabled/>
                        </td>
                    </tr>
                    <tr>
                        <th class="formTitle">工序字段：
                        </th>
                        <td class="formValue" colspan="3">
                            <input type="text" class="txt required"
                                   value="<#if 0==workProcess.isWorkshopRecord>无<#elseif 1==workProcess.isWorkshopRecord>有</#if>"  disabled/>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div id="detailIsWorkshopRecordDiv" class="<#if 0==workProcess.isWorkshopRecord>hidden</#if>">
            <div class="tipstitle_title settingtable bold bd todayInfoPanelTab rightPanelTitle_normal">
                <div class="tab_list_top" style="position: absolute">
                    <div id="detailTabTableField1" class="tab_list bd actived">操作记录</div>
                    <div id="detailTabTableField2" class="tab_list bd ">审核记录</div>
                    <div id="detailTabTableField3" class="tab_list bd ">巡检记录</div>
                </div>
            </div>
            <div id="" class="ScrollBar" style="margin-top: 1px;">
                <div id="detailTableField1" class="tabPanel">
                    <table id="" class="grid" style="width: 100%">
                        <thead>
                        <tr>
                            <td style="width: 30px; text-align: center; border-left: none;">
                                <div class="table-header">&nbsp;</div>
                            </td>
                            <td style="width: 60px;">
                                <div class="table-header">名称</div>
                            </td>
                            <td style="width: 80px; text-align: center;">
                                <div class="table-header">类型</div>
                            </td>
                            <td style="width: 80px; text-align: center;">
                                <div class="table-header">限制条件</div>
                            </td>
                            <td style="width: 100px; text-align: center;">
                                <div class="table-header">数据源类型</div>
                            </td>
                            <td style="width: 100px; text-align: center;">
                                <div class="table-header">数据源</div>
                            </td>
                        </tr>
                        </thead>
                        <tbody>
                            <#if 1==workProcess.isWorkshopRecord>
                                <#if workProcess.tableField1??>
                                    <#list workProcess.tableField1 as tableField1>
                                    <tr>
                                        <td class="td-div" style="width: 30px; text-align: center;border-left: none;">${tableField1.listIndex}</td>
                                        <td style="width: 50px; text-align: center;"><div>${tableField1.propertyName}</div>
                                            <input id="" type="hidden"
                                                   name ="formAttributeId" value="${tableField1.formAttributeId}" />
                                        </td>
                                        <td class="click-show" style="width: 60px; text-align: center;">
                                            <div>
                                                <#if 1==tableField1.controlType>文本框
                                                <#elseif 2==tableField1.controlType>下拉框
                                                <#elseif 3==tableField1.controlType>日期框
                                                <#elseif 5==tableField1.controlType>多行文本
                                                </#if>
                                            </div>
                                        </td>
                                        <td style="width: 60px; text-align: center;">
                                            <div>
                                                <#if 1==tableField1.restrictiveConditions>不能为空
                                                <#elseif 2==tableField1.restrictiveConditions>只能输入数字
                                                <#elseif 3==tableField1.restrictiveConditions>数字、字母
                                                <#elseif 4==tableField1.restrictiveConditions>无限制
                                                <#elseif 5==tableField1.restrictiveConditions>默认当前用户
                                                </#if>
                                            </div>
                                        </td>
                                        <td style="width: 60px; text-align: center;">
                                            <div>
                                                <#if 0==tableField1.dataSourceType>固定
                                                <#elseif 1==tableField1.dataSourceType>数据字典
                                                <#elseif 2==tableField1.dataSourceType>PDA用户
                                                </#if>
                                            </div>
                                        </td>
                                        <td style="width: 50px; text-align: center;">
                                            <div>
                                                <#if tableField1.dataSource??>${tableField1.dataSource}</#if>
                                            </div>
                                            <input id="" class="dataSourceCode" type="hidden"
                                                   name="dataSourceCode" <#if tableField1.dataSourceCode??>value="${tableField1.dataSourceCode}"</#if>/>
                                    </tr>
                                    </#list>
                                </#if>
                            </#if>
                        </tbody>
                    </table>
                </div>
                <div id="detailTableField2" class="tabPanel hidden">
                    <table id="" class="grid" style="width: 100%">
                        <thead>
                        <tr>
                            <td style="width: 30px; text-align: center; border-left: none;">
                                <div class="table-header">&nbsp;</div>
                            </td>
                            <td style="width: 60px;">
                                <div class="table-header">名称</div>
                            </td>
                            <td style="width: 80px; text-align: center;">
                                <div class="table-header">类型</div>
                            </td>
                            <td style="width: 80px; text-align: center;">
                                <div class="table-header">限制条件</div>
                            </td>
                            <td style="width: 100px; text-align: center;">
                                <div class="table-header">数据源类型</div>
                            </td>
                            <td style="width: 100px; text-align: center;">
                                <div class="table-header">数据源</div>
                            </td>
                        </tr>
                        </thead>
                        <tbody>
                            <#if 1==workProcess.isWorkshopRecord>
                                <#if workProcess.tableField2??>
                                    <#list workProcess.tableField2 as tableField2>
                                    <tr>
                                        <td class="td-div" style="width: 30px; text-align: center;border-left: none;">${tableField2.listIndex}</td>
                                        <td style="width: 50px; text-align: center;"><div>${tableField2.propertyName}</div>
                                            <input id="" type="hidden"
                                                   name ="formAttributeId" value="${tableField2.formAttributeId}" />
                                        </td>
                                        <td class="click-show" style="width: 60px; text-align: center;">
                                            <div>
                                                <#if 1==tableField2.controlType>文本框
                                                <#elseif 2==tableField2.controlType>下拉框
                                                <#elseif 3==tableField2.controlType>日期框
                                                <#elseif 5==tableField2.controlType>多行文本
                                                </#if>
                                            </div>
                                        </td>
                                        <td style="width: 60px; text-align: center;">
                                            <div>
                                                <#if 1==tableField2.restrictiveConditions>不能为空
                                                <#elseif 2==tableField2.restrictiveConditions>只能输入数字
                                                <#elseif 3==tableField2.restrictiveConditions>数字、字母
                                                <#elseif 4==tableField2.restrictiveConditions>无限制
                                                <#elseif 5==tableField2.restrictiveConditions>默认当前用户
                                                </#if>
                                            </div>
                                        </td>
                                        <td style="width: 60px; text-align: center;">
                                            <div>
                                                <#if 0==tableField2.dataSourceType>固定
                                                <#elseif 1==tableField2.dataSourceType>数据字典
                                                <#elseif 2==tableField2.dataSourceType>PDA用户
                                                </#if>
                                            </div>
                                        </td>
                                        <td style="width: 50px; text-align: center;">
                                            <div>
                                                <#if tableField2.dataSource??>${tableField2.dataSource}</#if>
                                            </div>
                                            <input id="" class="dataSourceCode" type="hidden"
                                                   name="dataSourceCode" <#if tableField2.dataSourceCode??>value="${tableField2.dataSourceCode}"</#if>/>
                                         </td>
                                    </tr>
                                    </#list>
                                </#if>
                            </#if>
                        </tbody>
                    </table>
                </div>
                <div id="detailTableField3" class="tabPanel hidden">
                    <table id="" class="grid" style="width: 100%">
                        <thead>
                        <tr>
                            <td style="width: 30px; text-align: center; border-left: none;">
                                <div class="table-header">&nbsp;</div>
                            </td>
                            <td style="width: 60px;">
                                <div class="table-header">名称</div>
                            </td>
                            <td style="width: 80px; text-align: center;">
                                <div class="table-header">类型</div>
                            </td>
                            <td style="width: 80px; text-align: center;">
                                <div class="table-header">限制条件</div>
                            </td>
                            <td style="width: 100px; text-align: center;">
                                <div class="table-header">数据源类型</div>
                            </td>
                            <td style="width: 100px; text-align: center;">
                                <div class="table-header">数据源</div>
                            </td>
                        </tr>
                        </thead>
                        <tbody>
                            <#if 1==workProcess.isWorkshopRecord>
                                <#if workProcess.tableField3??>
                                    <#list workProcess.tableField3 as tableField3>
                                    <tr>
                                        <td class="td-div" style="width: 30px; text-align: center;border-left: none;">${tableField3.listIndex}</td>
                                        <td style="width: 50px; text-align: center;"><div>${tableField3.propertyName}</div>
                                            <input id="" type="hidden"
                                                   name ="formAttributeId" value="${tableField3.formAttributeId}" />
                                        </td>
                                        <td class="click-show" style="width: 60px; text-align: center;">
                                            <div>
                                                <#if 1==tableField3.controlType>文本框
                                                <#elseif 2==tableField3.controlType>下拉框
                                                <#elseif 3==tableField3.controlType>日期框
                                                <#elseif 5==tableField3.controlType>多行文本
                                                </#if>
                                            </div>
                                        </td>
                                        <td style="width: 60px; text-align: center;">
                                            <div>
                                                <#if 1==tableField3.restrictiveConditions>不能为空
                                                <#elseif 2==tableField3.restrictiveConditions>只能输入数字
                                                <#elseif 3==tableField3.restrictiveConditions>数字、字母
                                                <#elseif 4==tableField3.restrictiveConditions>无限制
                                                <#elseif 5==tableField3.restrictiveConditions>默认当前用户
                                                </#if>
                                            </div>
                                        </td>
                                        <td style="width: 60px; text-align: center;">
                                            <div>
                                                <#if 0==tableField3.dataSourceType>固定
                                                <#elseif 1==tableField3.dataSourceType>数据字典
                                                <#elseif 2==tableField3.dataSourceType>PDA用户
                                                </#if>
                                            </div>
                                        </td>
                                        <td style="width: 50px; text-align: center;">
                                            <div>
                                                <#if tableField3.dataSource??>${tableField3.dataSource}</#if>
                                            </div>
                                            <input id="" class="dataSourceCode" type="hidden"
                                                   name="dataSourceCode" <#if tableField3.dataSourceCode??>value="${tableField3.dataSourceCode}"</#if>/>
                                        </td>
                                    </tr>
                                    </#list>
                                </#if>
                            </#if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</form>

<#else >
<table class="form">
    <tr id="">
        <th class="formTitle">错误：
        </th>
        <td class="formValue" colspan="3">
            <input type="text" class="txt required" disabled value="请重试，再不行请联系管理员"/>
        </td>
    </tr>
</table>
</#if>

<script>

    var tabTableDetail = 1;

    $("#workProcessDetailModal").on("click","#detailTabTableField1",function(){
        tabTableDetail=1;
        $("#detailTableField2").addClass("hidden");
        $("#detailTableField3").addClass("hidden");
        $("#detailTableField1").removeClass("hidden");
        $("#detailTabTableField2").removeClass("actived");
        $("#detailTabTableField3").removeClass("actived");
        $("#detailTabTableField1").addClass("actived");
    });
    $("#workProcessDetailModal").on("click","#detailTabTableField2",function(){
        tabTableDetail=2;
        $("#detailTableField1").addClass("hidden");
        $("#detailTableField3").addClass("hidden");
        $("#detailTableField2").removeClass("hidden");
        $("#detailTabTableField1").removeClass("actived");
        $("#detailTabTableField3").removeClass("actived");
        $("#detailTabTableField2").addClass("actived");
    });
    $("#workProcessDetailModal").on("click","#detailTabTableField3",function(){
        tabTableDetail=3;
        $("#detailTableField1").addClass("hidden");
        $("#detailTableField2").addClass("hidden");
        $("#detailTableField3").removeClass("hidden");
        $("#detailTabTableField1").removeClass("actived");
        $("#detailTabTableField2").removeClass("actived");
        $("#detailTabTableField3").addClass("actived");
    });
</script>
