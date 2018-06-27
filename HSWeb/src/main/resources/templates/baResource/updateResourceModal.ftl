
<#--修改资源-->
<form id="updateResourceForm" style="margin: 1px">
    <input type="hidden" name="resourceId" value="${resource.resourceId}">
    <table class="form" id="updateTable">
        <tr>
            <th class="formTitle">资源编码：
            </th>
            <td class="formValue">
                <input id="updCode" name="code" type="text" class="txt" maxlength="10" placeholder="<#if resource.code??>${resource.code}<#else ></#if>" value="<#if resource.code??>${resource.code}<#else ></#if>" />
            </td>
            <th class="formTitle">资源名称：
            </th>
            <td class="formValue">
                <input id="updCName" name="cName" type="text" class="txt" maxlength="10" placeholder="<#if resource.CName??>${resource.CName}<#else ></#if>" value="<#if resource.CName??>${resource.CName}<#else ></#if>" />
            </td>
        </tr>
        <tr>
            <th class="formTitle">上级模块：
            </th>
            <td class="formValue">
                <input id="updParentId" name="parentId" type="hidden" value="${resource.parentId}"/>
                <input id="updParentName" name="parentName" type="text" class="txt"  value="<#if parentResource??>${parentResource.CName}<#else >无上级</#if>" readonly disabled/>
            </td>
            <th class="formTitle">资源分类：
            </th>
            <td class="formValue">
                <select id="updCategory" class="txtselect" name="category" >
                    <#if resource.category??>
                        <option value="1" <#if resource.category==1>selected</#if>>目录</option>
                        <option value="2" <#if resource.category==2>selected</#if>>页面</option>
                        <option value="3" <#if resource.category==3>selected</#if>>按钮</option>
                        <option value="4" <#if resource.category==4>selected</#if>>右键按钮</option>
                        <option value="5" <#if resource.category==5>selected</#if>>字段列</option>
                    <#else >
                        <option value="1">目录</option>
                        <option value="2">页面</option>
                        <option value="3">按钮</option>
                        <option value="4">右键按钮</option>
                        <option value="5">字段列</option>
                    </#if>
                </select>
            </td>
        </tr>
        <tr>
            <th class="formTitle">Icon图标：
            </th>
            <td class="formValue">
                <input id="updIcon" name="icon" type="text" class="txt" placeholder="<#if resource.icon??>${resource.icon}<#else ></#if>" value="<#if resource.icon??>${resource.icon}<#else ></#if>" />
            </td>
            <th class="formTitle">连接目标：</th>
            <td class="formValue">
                <select id="updTarget" name="target" class="txtselect" >
                    <#if resource.target??>
                    <option value="iframe" <#if resource.target=="iframe">selected</#if>>iframe</option>
                    <option value="click" <#if resource.target=="click">selected</#if>>click</option>
                    <#else >
                        <option value="">-请选择-</option>
                        <option value="iframe">iframe</option>
                        <option value="click">click</option>
                    </#if>
                </select>
            </td>
        </tr>
        <tr>
            <th class="formTitle">级别层次：</th>
            <td class="formValue">
                <input id="updALevel" name="aLevel" type="text" class="txt" placeholder="${resource.ALevel}" value="${resource.ALevel}" readonly disabled />
            </td>
            <th class="formTitle">显示顺序：</th>
            <td class="formValue">
                <input id="updListIndex" name="listIndex" type="text" class="txt" placeholder="${resource.listIndex}" value="${resource.listIndex}"/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">选项：</th>
            <td class="formValue">
                <span class="item">
                    <input type="hidden" name="isExpand"/>
                    <input type="checkbox" <#if resource.isExpand==1>checked</#if> name="checkbox" id="updIsExpand" />
                    <label for="isexpand">展开</label>
                </span>
                <span class="item">
                    <input type="hidden" name="status"/>
                    <input type="checkbox" name="checkbox" <#if resource.status==1>checked</#if> id="updStatus" />
                    <label for="status">有效</label>
                </span>
            </td>
            <th class="formTitle">资源类别：
            </th>
            <td class="formValue">
            <#if resource??>
                <input type="text" class="txt"
                       value="<#if 0==resource.resourceType>PC<#else>手持PDA</#if>" disabled/>
                <input id="updResourceType" type="hidden" name="resourceType" value="${resource.resourceType}" />
            </#if>
            </td>
        </tr>
        <tr id="updLocationTr">
            <th class="formTitle">权限路径：
            </th>
            <td class="formValue" colspan="3">
                <input id="updLocation" name="location" type="text" class="txt" placeholder="<#if resource.location??>${resource.location}<#else ></#if>" value="<#if resource.location??>${resource.location}<#else ></#if>" />
            </td>
        </tr>
    <#if resource??>
        <#if 1==resource.resourceType>
            <tr id="updateMobileTr" class="<#if 1==resource.category>hidden</#if>">
                <th class="formTitle">手机图标：</th>
                <td class="formValue">
                    <input id="updateMobileIcon" name="mobileIcon" type="text" class="txt required"
                           <#if 1!=resource.category>value="${resource.mobileIcon}" placeholder="${resource.mobileIcon}"</#if> />
                </td>
                <th class="formTitle">手机地址：</th>
                <td class="formValue">
                    <input id="updateMobileLocation" name="mobileLocation" type="text" class="txt required"
                           <#if 1!=resource.category>value="${resource.mobileLocation}" placeholder="${resource.mobileLocation}"</#if> />
                </td>
            </tr>
        </#if>
    </#if>
        <tr>
            <th class="formTitle">说明：
            </th>
            <td class="formValue" colspan="3">
                <textarea class="txtArea" style="resize: none" id="updRemark" name="remark" maxlength="200" rows="5" placeholder="<#if resource.remark??>${resource.remark}<#else ></#if>"><#if resource.remark??>${resource.remark}<#else ></#if></textarea>
            </td>
        </tr>
    </table>
</form>


<script>

    var locationTr = $("#updLocationTr");
    var category =${resource.category};
    $(document).ready(function () {
        if(category==1){
            locationTr.fadeOut(0);
        }
    });
    $("#updCategory").on("change",function(){
        var select = document.getElementById("updTarget");
        category = $(this);
        var vals = category.val();
        if(vals==1){
            locationTr.fadeOut(0);
            select.options[0].selected = true;
        <#if resource??>
            <#if 1==resource.resourceType>
                $("#updateMobileTr").addClass("hidden");
            </#if>
        </#if>
        }else{
            locationTr.fadeIn(0);
            if(vals==2){
                select.options[0].selected = true;
            }else {
                select.options[1].selected = true;
            }
        <#if resource??>
            <#if 1==resource.resourceType>
                $("#updateMobileTr").removeClass("hidden");
            </#if>
        </#if>
        }
    });


</script>