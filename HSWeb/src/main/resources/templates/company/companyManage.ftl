

<div class="tools_bar leftline rightline" >
    <div class="PartialButton">
        <@shiro.hasPermission name="/company/reload">
            <a id="lr-replace" title="刷新当前(Ctrl+Q)" onclick="Replace()" class="tools_btn">
                <span>
                    <b class="btn-reload">刷新</b>
                </span>
            </a>
            <div class="tools_separator"></div>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/company/updateCompany">
            <a id="lr-edit" title="编辑(Ctrl+W)" onclick="updateCompany()" class="tools_btn">
                <span>
                    <b class="btn-update">编辑</b>
                </span>
            </a>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/company/companyDetail">
            <a id="lr-detail" title="查看详情" onclick="companyDetail()" class="tools_btn">
                <span>
                    <b class="btn-detail">查看详情</b>
                </span>
            </a>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/company/back">
            <div class="tools_separator"></div>
            <a id="lr-leave" title="关闭当前窗口(Esc)" onclick="btn_back()" class="tools_btn">
                <span>
                    <b class="btn-back">离开</b>
                </span>
            </a>
        </@shiro.hasPermission>
    </div>
</div>
<div class="rightline" >
    <table id="gridTable"></table>
</div>


<script type="text/javascript">
    $(document).ready(function () {
        GetGrid();
    });

    //加载表格
    function GetGrid() {
        $("#gridTable").jqGrid({
            url: "${request.contextPath}/company/GridJson.json",
            datatype: "json",
            height: $(window).height() - 150,
            autowidth: true,
            colModel: [
                {label: '主键', name: 'companyId', index: 'companyId', width: 80, hidden: true },
                {label: "公司名称", name: "cname", index: "cName", width: 150 },
                {label: "公司简称", name: "shortName", index: "shortName", width: 100 },
                {label: "公司分类", name: "category", index: "category", width: 80,
                    formatter: function (cellvalue, options, rowObject) {
                        if (cellvalue == 0) return "个体";
                        if (cellvalue == 1) return "公司";
                        if (cellvalue == 2) return "集团";
                        if (cellvalue == 3) return "机构";
                        if (cellvalue == 4) return "事业单位";
                        if (cellvalue == 5) return "供应商";
                        if (cellvalue == 6) return "客户";
                        if (cellvalue == 7) return "合作社";
                        if (cellvalue == 8) return "监管单位";
                        if (cellvalue == 9) return "种养殖户";
                    }
                },
                {label: "企业官方网站", name: "webUrl", index: "webUrl", width: 150 ,
                    formatter: function (cellvalue, options, rowObject) {
                        if (0==cellvalue.indexOf("http://")||0==cellvalue.indexOf("https://")){
                            return "<a style='color: #22cfc6' href='" + cellvalue + "' target='_blank'>" + cellvalue + "</a>";
                        }else {
                            return "<a style='color: #22cfc6' href='http://" + cellvalue + "' target='_blank'>" + cellvalue + "</a>";
                        }
                    }
                },
                {label: "全国服务热线", name: "companyPhone", index: "companyPhone", width: 100 },
                {label: "公司地址", name: "address", index: "address", width: 200 },
                {label: "微信", name: "weiChat", index: "weiChat", width: 60 },
                {label: '微信公众账号', name: 'weChatPublicNumber', index: 'weChatPublicNumber', width: 150 },
                {label: '海域位置', name: 'seaAreaPosition', index: 'seaAreaPosition', width: 250 }
            ],
            viewrecords: true,
            rownumbers: true,
            pager: false,
            jsonReader:{
                root:"root",page:"page",total:"total",records:"records"
            },
            shrinkToFit: false,
            gridview: true
        });
    }

<@shiro.hasPermission name="/company/updateCompany">
//修改
function updateCompany() {
    var companyId = GetJqGridRowValue("#gridTable", "companyId");
    var name = GetJqGridRowValue("#gridTable", "cname");
    var resourceId = top.$("#ModuleId").val();
    if (IsChecked(companyId)) {
        AddTabMenu(companyId + 'updateCompany', '${request.contextPath}/company/updateCompanyIFrame.htm?id=' + companyId+"&resourceId="+resourceId, '修改-'+name + '', "house.png", 'true', "${staticImg}");
    }
}
</@shiro.hasPermission>
<@shiro.hasPermission name="/company/companyDetail">
//详情
function companyDetail() {
    var companyId = GetJqGridRowValue("#gridTable", "companyId");
    var name = GetJqGridRowValue("#gridTable", "cname");
    if (IsChecked(companyId)) {
        AddTabMenu(companyId + 'companyDetail', '${request.contextPath}/company/companyDetailIFrame.htm?id=' + companyId, name + '-详情', "house.png", 'true', "${staticImg}");
    }
}

</@shiro.hasPermission>




</script>






