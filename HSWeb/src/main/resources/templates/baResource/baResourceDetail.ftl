
<#--资源详情-->
<form style="margin: 1px">
    <table class="form" >
        <tr>
            <th class="formTitle">资源编码：
            </th>
            <td class="formValue">
                <input type="text" class="txt" placeholder="<#if baResourceDetail.code??>${baResourceDetail.code}<#else >无编码</#if>" value="<#if baResourceDetail.code??>${baResourceDetail.code}<#else >无编码</#if>" readonly />
            </td>
            <th class="formTitle">资源名称：
            </th>
            <td class="formValue">
                <input type="text" class="txt" placeholder="<#if baResourceDetail.CName??>${baResourceDetail.CName}<#else >无资源名称</#if>" value="<#if baResourceDetail.CName??>${baResourceDetail.CName}<#else >无资源名称</#if>" readonly />
            </td>
        </tr>
        <tr>
            <th class="formTitle">上级模块：
            </th>
            <td class="formValue">
                <input type="hidden" disabled/>
                <input type="text" class="txt"  value="<#if parentResource??>${parentResource.CName}<#else >无上级</#if>" readonly disabled/>
            </td>
            <th class="formTitle">资源分类：
            </th>
            <td class="formValue">
                <select class="txtselect" readonly>
                    <#if baResourceDetail.category??>
                        <option value="1" <#if baResourceDetail.category==1>selected</#if>>目录</option>
                        <option value="2" <#if baResourceDetail.category==2>selected</#if>>页面</option>
                        <option value="3" <#if baResourceDetail.category==3>selected</#if>>按钮</option>
                        <option value="4" <#if baResourceDetail.category==4>selected</#if>>右键按钮</option>
                        <option value="5" <#if baResourceDetail.category==5>selected</#if>>字段列</option>
                    <#else >
                        <option value="">无资源分类</option>
                    </#if>
                </select>
            </td>
        </tr>
        <tr>
            <th class="formTitle">Icon图标：
            </th>
            <td class="formValue">
                <input type="text" class="txt" placeholder="<#if baResourceDetail.icon??>${baResourceDetail.icon}<#else >无Icon图标</#if>" value="<#if baResourceDetail.icon??>${baResourceDetail.icon}<#else >无Icon图标</#if>" readonly />
            </td>
            <th class="formTitle">连接目标：</th>
            <td class="formValue">
                <select class="txtselect" readonly>
                    <#if baResourceDetail.target??>
                        <option value="iframe" <#if baResourceDetail.target=="iframe">selected</#if>>iframe</option>
                        <option value="click" <#if baResourceDetail.target=="click">selected</#if>>click</option>
                    <#else >
                        <option>无连接目标</option>
                    </#if>
                </select>
            </td>
        </tr>
        <tr>
            <th class="formTitle">级别层次：</th>
            <td class="formValue">
                <input type="text" class="txt" placeholder="<#if baResourceDetail.ALevel??>${baResourceDetail.ALevel}<#else >无级别层次</#if>" value="<#if baResourceDetail.ALevel??>${baResourceDetail.ALevel}<#else >无级别层次</#if>" readonly disabled />
            </td>
            <th class="formTitle">显示顺序：</th>
            <td class="formValue">
                <input type="text" class="txt" placeholder="<#if baResourceDetail.listIndex??>${baResourceDetail.listIndex}<#else >无显示顺序</#if>" value="<#if baResourceDetail.listIndex??>${baResourceDetail.listIndex}<#else >无显示顺序</#if>" readonly disabled />
            </td>
        </tr>
        <tr>
            <th class="formTitle">选项：</th>
            <td class="formValue">
                <span class="item">
                    <input type="hidden"/>
                    <#if baResourceDetail.isExpand??>
                        <input type="checkbox" <#if baResourceDetail.isExpand==1>checked</#if> name="checkbox" id="updIsExpand" readonly />
                    <#else >
                        <input type="checkbox">
                    </#if>
                    <label for="isexpand">展开</label>
                </span>
                <span class="item">
                    <input type="hidden"/>
                    <#if baResourceDetail.status??>
                        <input type="checkbox" name="checkbox" <#if baResourceDetail.status==1>checked</#if> id="updStatus" readonly />
                    <#else >
                        <input type="checkbox">
                    </#if>
                    <label for="status">有效</label>
                </span>
            </td>
            <th class="formTitle">资源类别：
            </th>
            <td class="formValue">
                <select class="txtselect" >
                    <option value="0" <#if baResourceDetail.resourceType==0>selected</#if>>PC</option>
                    <option value="1" <#if baResourceDetail.resourceType==1>selected</#if>>手持PDA</option>
                </select>
            </td>
        </tr>
        <tr>
            <th class="formTitle">权限路径：
            </th>
            <td class="formValue" colspan="3">
                <input type="text" class="txt" placeholder="<#if baResourceDetail.location??>${baResourceDetail.location}<#else >无权限路径</#if>" value="<#if baResourceDetail.location??>${baResourceDetail.location}<#else >无权限路径</#if>" readonly />
            </td>
        </tr>
        <tr>
            <th class="formTitle">说明：
            </th>
            <td class="formValue" colspan="3">
                <textarea class="txtArea" style="resize: none" maxlength="200" rows="5" placeholder="<#if baResourceDetail.remark??>${baResourceDetail.remark}<#else >无</#if>" readonly><#if baResourceDetail.remark??>${baResourceDetail.remark}<#else >无</#if></textarea>
            </td>
        </tr>
    </table>
</form>