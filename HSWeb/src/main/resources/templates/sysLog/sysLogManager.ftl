<!--刷新、添加-->
<div class="tools_bar leftline rightline" >
    <div class="PartialButton">
    <@shiro.hasPermission name="/sysLog/reload">
        <a id="lr-replace" title="刷新当前(Ctrl+Q)" onclick="Replace()" class="tools_btn">
                     <span>
                         <b class="btn-reload">刷新</b>
                     </span>
        </a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/sysLog/back">
        <div class="tools_separator"></div>
        <a id="lr-leave" title="关闭当前窗口(Esc)" onclick="btn_back()" class="tools_btn">
                    <span>
                        <b class="btn-back">离开</b>
                    </span>
        </a>
    </@shiro.hasPermission>
    </div>
</div>

<!--选择框-->
<div class="rightline" >
    <div class="bottomline QueryArea" >
        <table border="0" class="form-find">
            <tr id="selectParameter">
                <td>
                    操作用户
                    <select id="createdUserId" name="createdUserId" class="txtselect" datacol="yes" err="状态"
                            checkexpession="NotNull">
                        <option value="">==请选择==</option>
                        <#if baUserList??>
                            <#list baUserList as user>
                                <option value="${user.userId}">${user.account}</option>
                            </#list>
                        </#if>
                    </select>
                </td>
                <td>
                    操作模块
                    <select id="resourceId" name="resourceId" class="txtselect" datacol="yes" err="状态"
                            checkexpession="NotNull">
                        <option value="">==请选择==</option>
                    <#if baResourceList??>
                        <#list baResourceList as resource>
                            <option value="${resource.resourceId}">${resource.CName}</option>
                        </#list>
                    </#if>
                    </select>
                </td>
                <td colspan="2">
                    操作时间
                    <input id="beginDate" name="" type="text" class="txt Wdate" style="width: 140px; "
                           onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                    至
                    <input id="endDate" name="" type="text" class="txt Wdate" style="width: 140px; "
                           onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                </td>
                <td>
                    <input id="sysLogSearch" type="button" class="btnSearch" value="查 询">
                    <input id="sysLogClear" type="button" class="btnSearch" value="清 除">
                </td>
            </tr>
        </table>
    </div>
    <table id="gridTable"></table>
    <div id="gridPager"></div>
</div>


<script>
    $(document).ready(function () {
        GetGrid();
        gridPagerStyle();
    });

    //加载表格
    function GetGrid() {
        $("#gridTable").jqGrid({
            url: "${request.contextPath}/SysLog/GridJson.json",
            datatype: "json",
            height: ($(window).height() - 150),
            autowidth: true,
            colModel: [
                {label: "IP地址", name: "ipAddress", index: "ipAddress", width: 110, align: 'center'},
                {label: "地址名称", name: "ipAddressName", index: "ipAddressName", width: 80, align: 'center'},
                {label: "操作用户", name: "userName", index: "userName", width: 100, align: 'center'},
                {label: "操作时间", name: "createDate", index: "createDate", width: 140, align: 'center'},
                {label: "操作模块", name: "resourceName", index: "resourceName", width: 100, align: 'center'},
                {label: '操作', name: 'cname', index: 'cname', width: 120, align: 'center'},
                {label: "备注", name: "remark", index: "remark", width: 300, align: 'center'}
            ],
            //指定分页栏的位置
            pagerpos: "right",
            //是否要显示总记录数
            viewrecords: true,
            //定义记录信息的位置
            recordpos: "left",
            //定义翻页用的导航栏
            pager: "#gridPager",
            rowNum: 10,
            rowList: [10, 20, 30, 50, 100],
            jsonReader: {
                root: "root", page: "page", total: "total", records: "records"
            },
            //表格最左边的一列编号
            rownumbers: true,
            //初始化列宽的计算类型 false：使用colmodel的指定列宽
            shrinkToFit: false,
            //将整个表格的数据都构造完成后再添加到grid中
            gridview: true
        });
    }

    //分页布局
    function gridPagerStyle() {
        var width = document.getElementById("gridPager_right").offsetWidth + 5;
        $("#gridPager_right").attr("style", "width:" + width);
    }

    //查询
    $("#sysLogSearch").unbind("click");
    $("#sysLogSearch").click(function () {
        var createdUserId = $("#createdUserId").val();
        var resourceId = $("#resourceId").val();
        var beginDate = $("#beginDate").val();
        var endDate = $("#endDate").val();
        var postData = {createdUserId: createdUserId, resourceId: resourceId, beginDate: beginDate, endDate: endDate};
        //提交post并刷新表格
        $("#gridTable").jqGrid("setGridParam", {
            postData: postData,
            url: "${request.contextPath}/SysLog/GridJson.json",
            page: 1
        }).trigger("reloadGrid");
    });

    //清除
    $("#sysLogClear").unbind("click");
    $("#sysLogClear").click(function () {
        //清空input框、表格
        $("#createdUserId").val("");
        $("#resourceId").val("");
        $("#beginDate").val("");
        $("#endDate").val("");
        var postData = {createdUserId: "", resourceId: "", beginDate: "", endDate: ""};
        //提交post并刷新表格
        $("#gridTable").jqGrid("setGridParam", {
            postData: postData,
            url: "${request.contextPath}/SysLog/GridJson.json",
            page: 1
        }).trigger("reloadGrid");
    });

</script>
