

<div id="layout" class="layout" >
    <!--左边-->
    <div class="layoutPanel layout-west">
        <div class="btnbartitle">
            <div>
                导航目录
            </div>
        </div>
        <div class="ScrollBar" id="ItemsTree"></div>
    </div>
    <!--中间-->
    <div class="layoutPanel layout-center">
        <div class="btnbartitle">
            <div>
                模块信息
            </div>
        </div>
        <div class="tools_bar" >
            <div class="PartialButton">
                <@shiro.hasPermission name="/resource/reload">
                    <a id="lr-replace" title="刷新当前(Ctrl+Q)" onclick="Replace()" class="tools_btn">
                        <span>
                            <b class="btn-reload">刷新</b>
                        </span>
                    </a>
                    <div class="tools_separator"></div>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="/resource/addResource">
                    <a id="lr-add" title="新增(Ctrl+Z)" onclick="addResource()" class="tools_btn">
                        <span>
                            <b class="btn-add">新增</b>
                        </span>
                    </a>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="/resource/updateResource">
                    <a id="lr-edit" title="编辑(Ctrl+W)" onclick="updateResuorceModal()" class="tools_btn">
                        <span>
                            <b class="btn-update">编辑</b>
                        </span>
                    </a>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="/resource/deleteResource">
                    <a id="lr-delete" title="删除(Ctrl+S)" onclick="deleteBaResource()" class="tools_btn">
                        <span>
                            <b class="btn-delete">删除</b>
                        </span>
                    </a>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="/resource/resourceDetail">
                    <a id="lr-detail" title="详细信息(Ctrl+X)" onclick="baResourceDetail()" class="tools_btn">
                        <span>
                            <b class="btn-detail">详细</b>
                        </span>
                    </a>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="/resource/back">
                    <div class="tools_separator"></div>
                    <a id="lr-leave" title="关闭当前窗口(Esc)" onclick="btn_back()" class="tools_btn">
                        <span>
                            <b class="btn-back">离开</b>
                        </span>
                    </a>
                </@shiro.hasPermission>
            </div>
        </div>
        <div class="bottomline QueryArea" >
            <table border="0" class="form-find" >
                <tbody>
                <tr id="selectParameter">
                    <td>
                        编码：<input id="txtCode" name="code" type="text" class="input-txt" style="width: 100px">
                    </td>
                    <td>
                        名称：<input id="txtCName" name="cName" type="text" class="input-txt" style="width: 100px">
                    </td>
                    <td>
                        分类：
                        <select id="txtCategory" name="category" class="txtselect" datacol="yes" err="分类" checkexpession="NotNull">
                            <option value="">==请选择==</option>
                            <option value="1">目录</option>
                            <option value="2">页面</option>
                            <option value="3">按钮</option>
                            <option value="4">右键按钮</option>
                            <option value="5">字段列</option>
                        </select>
                    </td>
                    <td>
                        状态：
                        <select id="txtStatus" name="status" class="txtselect" datacol="yes" err="状态" checkexpession="NotNull">
                            <option value="">==请选择==</option>
                            <option value="1">正常</option>
                            <option value="2">禁用</option>
                        </select>
                    </td>
                    <td>
                        <input id="resource-Search" type="button" class="btnSearch" value="查 询">
                        <input id="resource-Clear" type="button" class="btnSearch" value="重 置">
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <table id="gridTable"></table>
        <div id="gridPager"></div>
    </div>
</div>

<@shiro.hasPermission name="/resource/addResource">
<!--添加资源-->
<div id="addBaResourceModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
            <h4 class="modal-title">添加资源</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="addBaResource" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>

<@shiro.hasPermission name="/resource/updateResource">
<!--修改资源-->
<div id="updateBaResourceModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header" style="text-align: center">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">修改资源</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="updateResource" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>

<@shiro.hasPermission name="/resource/resourceDetail">
<!--查看资源详情-->
<div id="baResourceDetailModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header" style="text-align: center">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">资源详情</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn green">离开</button>
    </div>
</div>
</@shiro.hasPermission>
<#--删除确认-->
<@shiro.hasPermission name="/resource/deleteResource">
<div id="deleteResourceModal" class="modal fade " data-width="400" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header" style="text-align: center">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">删除确认</h4>
    </div>
    <div class="modal-body">
        <p style="text-align: center">删除后数据不可恢复</p>
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="deleteResource" type="button" class="btn green">确定</button>
    </div>
</div>
</@shiro.hasPermission>

<script>
    $(document).ready(function () {
        GetTree();
        GetGrid();
        gridPagerStyle();
        Loadlayout();
    });
    var ID = "0", category = "", Name = "";
    //加载左边树
    function GetTree() {
        var itemtree = {
            onnodeclick: function (item) {
                ID = item.id;            //ID
                category = item.Category; //分类
                Name = item.text;        //名称
                $("#gridTable").jqGrid('setGridParam', {
                    url: "${request.contextPath}/resource/GridJson.json?parentId="+ID
                }).trigger('reloadGrid');
            },
            url: "${request.contextPath}/resource/TreeJson.json"
        };
        $("#ItemsTree").treeview(itemtree);
    }
    //加载表格
    function GetGrid() {
        $("#gridTable").jqGrid({
            url: "${request.contextPath}/resource/GridJson.json?parentId=0",
            datatype: "json",
            height: ($(window).height() - 180),
            autowidth: true,
            colModel: [
                {label: "主键", name: "resourceId", index: "resourceId", hidden: true},
                {label: "编码", name: "code", index: "code", width: 76},
                {label: "名称", name: "cname", index: "cName", width: 100},
                {
                    label: "分类", name: "category", index: "category", width: 45, align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        if (cellvalue == 1) return "目录";
                        if (cellvalue == 2) return "页面";
                        if (cellvalue == 3) return "按钮";
                        if (cellvalue == 4) return "右键按钮";
                        if (cellvalue == 5) return "字段列";
                    }
                },
                {label: "访问、权限地址", name: "location", index: "location", width: 200},
                {label: "手机权限地址", name: "mobileLocation", index: "mobileLocation", width: 200},
                {
                    label: "手机是否显示", name: "mobileShow", index: "mobileShow", width: 100,
                    formatter: function (cellvalue, options, rowObject) {
                        if (cellvalue == 1) return "显示";
                        else return "隐藏";
                    }
                },
                {label: "目标", name: "target", index: "target", width: 45, align: "center"},
                {label: "层次", name: "alevel", index: "aLevel", width: 45, align: "center"},
                {
                    label: '有效', name: 'status', index: 'status', width: 45, align: 'center',
                    formatter: function (cellvalue, options, rowObject) {
                        if (cellvalue == null || cellvalue == undefined || cellvalue == 6) return "<img src='${staticImg}/images/checknomark.gif' />";
                        if (cellvalue == 1) return "<img src='${staticImg}/images/checkokmark.gif' />"; else return "<img src='${staticImg}/images/checknomark.gif' />";
                    }
                },
                {label: "说明", name: "remark", index: "remark", width: 400}
            ],
            pagerpos : "right",
            viewrecords : true,
            recordpos : "left",
            pager: "#gridPager",
            rowNum : 15,
            rowList:[15,30,50,100,500],
            jsonReader:{
                root:"root",page:"page",total:"total",records:"records"
            },
            sortname: 'listIndex',
            sortorder: 'asc',
            rownumbers: true,
            shrinkToFit: false,
            gridview: true
        });
    }
    //分页布局
    function gridPagerStyle() {
        var width = document.getElementById("gridPager_right").offsetWidth+5;
        $("#gridPager_right").attr("style","width:"+width);
    }

<@shiro.hasPermission name="/resource/addResource">
    //新增
    function addResource() {
        if (""!=ID){
            if (2>=category){
                $("#addBaResourceModal").modal({
                    remote: "${request.contextPath}/resource/addResourceModal.htm?id=" + ID
                });
            }else {
                tipDialog("选中级别不能添加下级，请重新选择!", 4, 'warning');
            }
        }else {
            tipDialog("请选择左侧目录!", 4, 'warning');
        }
    }

    $("#addBaResource").unbind("click");
    $("#addBaResource").click(function () {
        Loading(true, "正在提交数据...","#addBaResourceModal");
        var code = $("#code").val();
        if(""==code){
            $("#code").focus();
            tipDialog("请输入资源编码", 4, 'warning');
            Loading(false,"","#addBaResourceModal");
            return false;
        }
        var cName = $("#cName").val();
        if(""==cName){
            $("#cName").focus();
            tipDialog("请输入资源名称", 4, 'warning');
            Loading(false,"","#addBaResourceModal");
            return false;
        }
        var icon = $("#icon").val();
        if(""==icon){
            $("#icon").focus();
            tipDialog("请输入Icon图标", 4, 'warning');
            Loading(false,"","#addBaResourceModal");
            return false;
        }
        var isExpand = $("input[name=isExpand]");
        var status = $("input[name=status]");
       if ($("#isExpand").is(":checked")){
           isExpand.val(1)
       }else {
           isExpand.val(0)
       }
       if ($("#status").is(":checked")){
           status.val(1)
       }else {
           status.val(2)
       }
       var category = $("#category").val();
       if(1 != category){
            var location = $("#location").val();
            if(""==location){
                $("#location").focus();
                tipDialog("请输入权限路径", 4, 'warning');
                Loading(false,"","#addBaResourceModal");
                return false;
            }
       }else {
           $("#location").val("");
       }
       var listIndex = $("#listIndex").val();
       if(""==listIndex){
           $("#listIndex").focus();
           tipDialog("请输入显示顺序", 4, 'warning');
           Loading(false,"","#addBaResourceModal");
           return false;
       }
       var resourceType = $("#resourceType").val();
       if(""==resourceType){
           tipDialog("请选择资源类别", 4, 'warning');
           Loading(false,"","#addBaResourceModal");
           return false;
       }else {
           if (1==resourceType&& 1!= category){
               let mobileIcon = $("#addMobileIcon").val();
               if(""==mobileIcon){
                   $("#addMobileIcon").focus();
                   tipDialog("请输入手机图标", 4, 'warning');
                   Loading(false,"","#addBaResourceModal");
                   return false;
               }
               let mobileLocation = $("#addMobileLocation").val();
               if(""==mobileLocation){
                   $("#addMobileLocation").focus();
                   tipDialog("请输入手机地址", 4, 'warning');
                   Loading(false,"","#addBaResourceModal");
                   return false;
               }
           }
       }
        $.post('${request.contextPath}/resource/addBaResource.json', $("#addResourceForm").serialize(), function (result) {
            if (result.success) {
                GetTree();
                $("#gridTable").trigger("reloadGrid");
                tipDialog(result.msg, 4, '1');
                $("#addBaResourceModal").removeData("bs.modal");
                $("#addBaResourceModal").modal('hide');
            } else {
                tipDialog(result.msg, 4, '0');
            }
            Loading(false,"","#addBaResourceModal");
        }, "JSON");
    });
</@shiro.hasPermission>

<@shiro.hasPermission name="/resource/updateResource">
    //修改
    function updateResuorceModal() {
        var id = GetJqGridRowValue("#gridTable", "resourceId");
        if (IsChecked(id)){
            $("#updateBaResourceModal").modal({
                remote: "${request.contextPath}/resource/updateResourceModal.htm?id=" + id
            });
        }
    }

    $("#updateResource").unbind("click");
    $("#updateResource").click(function () {
        Loading(true,"正在提交数据...","#updateBaResourceModal");
        var code = $("#updCode").val();
        if(""==code){
            $("#updCode").focus();
            tipDialog("请输入资源编码", 4, 'warning');
            Loading(false,"","#updateBaResourceModal");
            return false;
        }
        var cName = $("#updCName").val();
        if(""==cName){
            $("#updCName").focus();
            tipDialog("请输入资源名称", 4, 'warning');
            Loading(false,"","#updateBaResourceModal");
            return false;
        }
        var icon = $("#updIcon").val();
        if(""==icon){
            $("#updIcon").focus();
            tipDialog("请输入Icon图标", 4, 'warning');
            Loading(false,"","#updateBaResourceModal");
            return false;
        }
        var isExpand = $("input[name=isExpand]");
        var status = $("input[name=status]");
        if ($("#updIsExpand").is(":checked")){
            isExpand.val(1)
        }else {
            isExpand.val(0)
        }
        if ($("#updStatus").is(":checked")){
            status.val(1)
        }else {
            status.val(2)
        }
        var category = $("#updCategory").val();
        if(1 != category){
            var location = $("#updLocation").val();
            if(""==location){
                $("#updLocation").focus();
                tipDialog("请输入权限路径", 4, 'warning');
                Loading(false,"","#updateBaResourceModal");
                return false;
            }
        }else {
            $("#updLocation").val("");
        }
        var listIndex = $("#updListIndex").val();
        if(""==listIndex){
            $("#updListIndex").focus();
            tipDialog("请输入显示顺序", 4, 'warning');
            Loading(false,"","#updateBaResourceModal");
            return false;
        }
        var resourceType = $("#updResourceType").val();
        if(""==resourceType){
            tipDialog("请重试", 4, 'warning');
            Loading(false,"","#updateBaResourceModal");
            return false;
        }else {
            if (1==resourceType&& 1!= category){
                let mobileIcon = $("#updateMobileIcon").val();
                if(""==mobileIcon){
                    $("#updateMobileIcon").focus();
                    tipDialog("请输入手机图标", 4, 'warning');
                    Loading(false,"","#updateBaResourceModal");
                    return false;
                }
                let mobileLocation = $("#updateMobileLocation").val();
                if(""==mobileLocation){
                    $("#updateMobileLocation").focus();
                    tipDialog("请输入手机地址", 4, 'warning');
                    Loading(false,"","#updateBaResourceModal");
                    return false;
                }
            }
        }
        $.post('${request.contextPath}/resource/updateBaResource.json', $("#updateResourceForm").serialize(), function (result) {
            if (result.success) {
                GetTree();
                $("#gridTable").trigger("reloadGrid");
                tipDialog(result.msg, 4, '1');
                $("#updateBaResourceModal").removeData("bs.modal");
                $("#updateBaResourceModal").modal('hide')
            } else {
                tipDialog(result.msg, 4, '0');
            }
            Loading(false,"","#updateBaResourceModal");
        }, "JSON");
    });
</@shiro.hasPermission>
    //删除
    <@shiro.hasPermission name="/resource/deleteResource">
    function deleteBaResource() {
        var id = GetJqGridRowValue("#gridTable", "resourceId");
        if (IsChecked(id)) {
            $("#deleteResourceModal").modal("show");
        }
    }
    $("#deleteResource").unbind("click");
    $("#deleteResource").click(function () {
        Loading(true,"正在删除数据...","#deleteResourceModal");
        var id = GetJqGridRowValue("#gridTable", "resourceId");
        $.post('${request.contextPath}/resource/deleteBaResource.json',{id: id}, function (res) {
            if (res.success) {
                $("#gridTable").trigger("reloadGrid");
                tipDialog(res.msg, 4, "warning");
                $("#deleteResourceModal").modal("hide");
            } else {
                tipDialog(res.msg, 4, "warning");
            }
            Loading(false,"","#deleteResourceModal");
        }, "json");
    });
    </@shiro.hasPermission>

<@shiro.hasPermission name="/resource/resourceDetail">
    //详情
    function  baResourceDetail() {
       var id = GetJqGridRowValue("#gridTable", "resourceId");
        if (IsChecked(id)){
            $("#baResourceDetailModal").modal({
                remote: "${request.contextPath}/resource/resourceDetailModal.htm?id=" + id
            });
        }
    }
</@shiro.hasPermission>

    $("#resource-Search").unbind("click");
    $("#resource-Search").click(function () {
        Loading(true,"正在提交数据...");
        var code = $("#txtCode").val();
        var cName = $("#txtCName").val();
        var category = $("#txtCategory").val();
        var status = $("#txtStatus").val();
        var postData ={code:code,cName:cName,category:category,status:status,parentId:ID};
        //提交post并刷新表格
        $("#gridTable").jqGrid("setGridParam",{
            postData:postData,
            url:"${request.contextPath}/resource/GridJson.json",
            page:1
        }).trigger("reloadGrid");
        Loading(false);
    });

    $("#resource-Clear").unbind("click");
    $("#resource-Clear").click(function () {
        Loading(true,"正在提交数据...");
        //重置表格
        $("#txtCode").val("");
        $("#txtCName").val("");
        $("#txtCategory").val("");
        $("#txtStatus").val("");
        $("#gridTable").jqGrid('setGridParam', {
            postData:{code:"",cName:"",category:"",status:"",parentId:ID},
            url: "${request.contextPath}/resource/GridJson.json",
            page:1
        }).trigger('reloadGrid');
        Loading(false);
    });

</script>