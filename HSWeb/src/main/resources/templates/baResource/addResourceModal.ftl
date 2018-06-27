

<form id="addResourceForm" style="margin: 1px">
    <#--<div id="message" style="display: none"></div>-->
    <table class="form">
        <tr>
            <th class="formTitle">资源编码：
            </th>
            <td class="formValue">
                <input id="code" name="code" type="text" class="txt required" maxlength="10" />
            </td>
            <th class="formTitle">资源名称：
            </th>
            <td class="formValue">
                <input id="cName" name="cName" type="text" class="txt required" maxlength="10"/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">上级模块：
            </th>
            <td class="formValue">
                <input id="parentId" name="parentId" type="hidden" value="<#if resource??>${resource.resourceId}<#else >0</#if>"/>
                <input id="parentName" name="parentName" type="text" class="txt" value="<#if resource??>${resource.CName}<#else >无上级</#if>" disabled/>
            </td>
            <th class="formTitle">资源分类：
            </th>
            <td class="formValue">
                <select id="category" class="txtselect" name="category">
                    <option value="1">目录</option>
                    <option value="2">页面</option>
                    <option value="3">按钮</option>
                    <option value="4">右键按钮</option>
                    <option value="5">字段列</option>
                </select>
            </td>
        </tr>
        <tr>
            <th class="formTitle">Icon图标：
            </th>
            <td class="formValue">
                <input id="icon" name="icon" type="text" class="txt" />
            </td>
            <th class="formTitle">连接目标：</th>
            <td class="formValue">
                <select id="target" name="target" class="txtselect" >
                    <option value="iframe">iframe</option>
                    <option value="click">click</option>
                </select>
            </td>
        </tr>
        <tr>
            <th class="formTitle">级别层次：</th>
            <td class="formValue">
                <input id="aLevel"  type="text" class="txt" value="${aLevel}" disabled/>
                <input name="aLevel" type="hidden" value="${aLevel}"/>
            </td>
            <th class="formTitle">显示顺序：</th>
            <td class="formValue">
                <input id="listIndex" type="text" class="txt" value="${listIndex}"/>
                <input name="listIndex" type="hidden" value="${listIndex}" />
            </td>
        </tr>
        <tr>
            <th class="formTitle">选项：</th>
            <td class="formValue">
                <span class="item">
                    <input type="hidden" name="isExpand"/>
                    <input type="checkbox" name="checkbox" id="isExpand" />
                    <label for="isexpand">展开</label>
                </span>
                <span class="item">
                    <input type="hidden" name="status"/>
                    <input type="checkbox" name="checkbox" checked="checked" id="status" />
                    <label for="status">有效</label>
                </span>
            </td>
            <th class="formTitle">资源类别：
            </th>
            <td class="formValue">
            <#if resource??>
                <input type="text" class="txt"
                       value="<#if 0==resource.resourceType>PC<#else>手持PDA</#if>" disabled/>
                <input id="resourceType" type="hidden" name="resourceType" value="${resource.resourceType}" />
            <#else >
                <select id="resourceType" class="txtselect" name="resourceType">
                    <option value="0">PC</option>
                    <option value="1">手持PDA</option>
                </select>
            </#if>
            </td>
        </tr>
        <tr id="locationTr">
            <th class="formTitle">权限路径：
            </th>
            <td class="formValue" colspan="3">
                <input id="location" name="location" type="text" class="txt required" />
            </td>
        </tr>
        <#if resource??>
            <#if 1==resource.resourceType>
                <tr id="addMobileTr" class="hidden">
                    <th class="formTitle">手机图标：</th>
                    <td class="formValue">
                        <input id="addMobileIcon" name="mobileIcon" type="text" class="txt required" />
                    </td>
                    <th class="formTitle">手机地址：</th>
                    <td class="formValue">
                        <input id="addMobileLocation" name="mobileLocation" type="text" class="txt required" />
                    </td>
                </tr>
            </#if>
        </#if>
        <tr>
            <th class="formTitle">说明：
            </th>
            <td class="formValue" colspan="3">
                <textarea class="txtArea" style="resize: none" id="remark" name="remark" maxlength="200" rows="5"></textarea>
            </td>
        </tr>
    </table>
</form>

<script>
    var locationTr = $("#locationTr");
    $(document).ready(function () {
        locationTr.fadeOut(0);
    });
    $("#category").on("change",function(){
        var select = document.getElementById("target");
        var category = $(this);
        var vals = category.val();
        if(vals==1){
            locationTr.fadeOut(0);
            select.options[0].selected = true;
            <#if resource??>
                <#if 1==resource.resourceType>
                    $("#addMobileTr").addClass("hidden");
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
                    $("#addMobileTr").removeClass("hidden");
                </#if>
            </#if>
        }
    });

</script>