

<div id="layout" class="layout">
    <!--左边-->
    <div class="layoutPanel layout-west" >
        <div class="btnbartitle">
            <div>
                公司目录
            </div>
        </div>
        <div class="ScrollBar bbit-tree" id="ItemsTree"></div>
    </div>
    <!--中间-->
    <div class="layoutPanel layout-center">
        <div class="btnbartitle">
            <div>
                角色列表<span id="CenterTitle"></span>
            </div>
        </div>
        <div class="tools_bar" >
            <div class="PartialButton">
                <@shiro.hasPermission name="/role/reload">
                    <a id="lr-replace" title="刷新当前(Ctrl+Q)" onclick="Replace()" class="tools_btn">
                         <span>
                             <b class="btn-reload">刷新</b>
                         </span>
                    </a>
                    <div class="tools_separator"></div>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="/role/addRole">
                    <a id="lr-add" title="新增(Ctrl+Z)" onclick="addRoles()" class="tools_btn">
                        <span>
                            <b class="btn-add">新增</b>
                        </span>
                    </a>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="/role/updateRole">
                    <a id="lr-edit" title="编辑(Ctrl+W)" onclick="updateRoles()" class="tools_btn">
                        <span>
                            <b class="btn-update">编辑</b>
                        </span>
                    </a>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="/role/deleteRole">
                    <a id="lr-delete" title="删除(Ctrl+S)" onclick="deleteRoles()" class="tools_btn">
                        <span>
                            <b class="btn-delete">删除</b>
                        </span>
                    </a>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="/role/roleDetail">
                    <a id="lr-detail" title="详细信息(Ctrl+X)" onclick="rolesDetail()" class="tools_btn">
                        <span>
                            <b class="btn-detail">详细</b>
                        </span>
                    </a>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="/role/authentication">
                <div class="tools_separator"></div>
                    <a id="lr-set" class="tools_btn dropdown">
                        <div style="float: left;">
                            <div class="icon">
                                <img src="${staticImg}/images/Icon16/email_authentication.png">
                            </div>
                            <div class="text">权限设置</div>
                        </div>
                        <div class="dropdown-icon">
                            <img src="${staticImg}/images/dropdown-icon.png">
                        </div>
                        <div class="dropdown-data" style="display: none; top: 72px; left: 438.5px;">
                            <i></i>
                            <span></span>
                            <ul>
                                <@shiro.hasPermission name="/role/roleUser">
                                    <li id="lr-User" title="&nbsp;" onclick="btn_Member();">
                                        <img src="${staticImg}/images/Icon16/xfn.png">角色用户
                                    </li>
                                </@shiro.hasPermission>
                                <@shiro.hasPermission name="/role/userRole">
                                    <li id="lr-Role" title="&nbsp;" onclick="btn_AllotPermission();">
                                        <img src="${staticImg}/images/Icon16/group_key.png">角色权限
                                    </li>
                                </@shiro.hasPermission>
                                <#--<li id="lr-Batch" title="&nbsp;" onclick="btn_MemberBatch()">
                                    <img src="${staticImg}/images/Icon16/group_go.png">角色用户批量
                                </li>
                                <li id="lr-Scope" title="&nbsp;" onclick="btn_ScopePermission()">
                                    <img src="${staticImg}/images/Icon16/tree(2).png">数据范围
                                </li>-->
                            </ul>
                        </div>
                    </a>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="/role/back">
                    <div class="tools_separator"></div>
                    <a id="lr-leave" title="关闭当前窗口(Esc)" onclick="btn_back()" class="tools_btn">
                        <span>
                            <b class="btn-back">离开</b>
                        </span>
                    </a>
                </@shiro.hasPermission>
            </div>
        </div>
        <table border="0" class="form-find" >
            <tbody>
                <tr>
                    <th>
                        关键字：
                    </th>
                    <td>
                        <input id="txtSearckKey" type="text" class="input-txt" style="width: 200px">
                    </td>
                    <td>
                        <input id="btnSearch" type="button" class="btnSearch" value="搜 索" onclick="btn_Search('CName,Code')">
                    </td>
                </tr>
            </tbody>
        </table>
        <table id="gridTable"></table>
        <div id="gridPager"></div>
    </div>
</div>

<#--<@shiro.hasPermission name="/role/userRole">
<!--角色权限&ndash;&gt;
<div id="userRolesModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">角色权限</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="userRoles" type="button" data-dismiss="modal" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>-->

<@shiro.hasPermission name="/role/roleUser">
<!--角色用户-->
<div id="baRolesUserModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">角色用户</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn green">离开</button>
    </div>
</div>
</@shiro.hasPermission>

<@shiro.hasPermission name="/role/addRole">
<!--添加角色-->
<div id="addBaRolesModal" class="modal fade " data-width="500" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">添加角色</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="addBaRoles" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>

<@shiro.hasPermission name="/role/updateRole">
<#--修改角色信息-->
<div id="updateBaRolesModal" class="modal fade " data-width="500" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">修改角色</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="updateBaRoles" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>
<#--删除确认-->
<@shiro.hasPermission name="/role/deleteRole">
<div id="deleteRolesModal" class="modal fade " data-width="400" tabindex="-1" aria-hidden="true"
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
        <button id="deleteRoles" type="button" class="btn green">确定</button>
    </div>
</div>
</@shiro.hasPermission>

<@shiro.hasPermission name="/role/roleDetail">
<#--角色详情-->
<div id="baRolesDetailModal" class="modal fade " data-width="500" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">角色详情</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn green">离开</button>
    </div>
</div>
</@shiro.hasPermission>

<script type="text/javascript">
    $(document).ready(function () {
        GetTree();
        GetGrid();
        gridPagerStyle();
        Loadlayout();
    });
    var CompanyId = "", CompanyName = "";
    //加载左边树
    function GetTree() {
        var itemtree = {
            onnodeclick: function (item) {
                CompanyId = item.id;            //ID
                CompanyName = item.text;        //名称
                $("#CenterTitle").html(" - " + CompanyName);
                $("#gridTable").jqGrid('setGridParam', {
                    url: "${request.contextPath}/roles/GridJson.json?companyId=" + CompanyId, page: 1
                }).trigger('reloadGrid');
            },
            url: "${request.contextPath}/company/getBaCompanyByPidZero.json"
        };
        $("#ItemsTree").treeview(itemtree);
    }
    //加载表格
    function GetGrid() {
        $("#gridTable").jqGrid({
            url: "${request.contextPath}/roles/GridJson.json?companyId=" +1,
            datatype: "json",
            height: $(window).height() - 180,
            autowidth: true,
            colModel: [
                { label: '主键', name: 'roleId', index: 'roleId', width: 80, hidden: true },
                { label: '编码', name: 'code', index: 'code', width: 80 },
                { label: '角色', name: 'cname', index: 'cName', width: 100 },
                {
                    label: '成员人数', name: 'roleNumber', index: 'roleNumber', width: 80, align: 'center',
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue + "个人";
                    }
                },
                {
                    label: '角色分类', name: 'category', index: 'category', width: 80, align: 'center',
                    formatter: function (cellvalue, options, rowObject) {
                        if (0==cellvalue){
                            return "PC端";
                        }else {
                            return "手持PDA";
                        }
                    }
                },
                { label: '所属公司Id', name: 'companyId', index: 'companyId', hidden: true },
                { label: '所属公司', name: 'companyName', index: 'companyName', width: 300 },
                {
                    label: '有效', name: 'status', index: 'status', width: 45, align: 'center',
                    formatter: function (cellvalue, options, rowObject) {
                        if (cellvalue == null || cellvalue == undefined || cellvalue == 6) return "<img src='${staticImg}/images/checknomark.gif' />";
                        if (cellvalue == 1) return "<img src='${staticImg}/images/checkokmark.gif' />"; else return "<img src='${staticImg}/images/checknomark.gif' />";
                    }
                },
                { label: '描述', name: 'remark', index: 'remark', width: 600 }
            ],
            pagerpos : "right",
            viewrecords : true,
            recordpos : "left",
            pager: "#gridPager",
            rowNum: 15,
            rowList: [15, 25, 50, 100, 500],
            jsonReader:{
                root:"root",page:"page",total:"total",records:"records"
            },
            sortname: 'listIndex',
            sortorder: 'desc',
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

<@shiro.hasPermission name="/role/roleUser">
    //角色用户
    function btn_Member() {
        var roleId = GetJqGridRowValue("#gridTable", "roleId");
        var roleNumber = GetJqGridRowValue("#gridTable", "roleNumber");
        if (IsChecked(roleId)) {
            if ("0个人"!=roleNumber){
                $("#baRolesUserModal").modal({
                    remote: "${request.contextPath}/roles/baRolesUserModal.htm?id=" + roleId
                });
            }else {
                tipDialog('当前角色组无用户', 4, 'warning');
            }
        }
    }
</@shiro.hasPermission>

<@shiro.hasPermission name="/role/userRole">
    //角色权限
    function btn_AllotPermission() {
        var roleId = GetJqGridRowValue("#gridTable", "roleId");
        var RoleName = GetJqGridRowValue("#gridTable", "cname");
        if (IsChecked(roleId)) {
            AddTabMenu(roleId+'userRolesIFrame', '${request.contextPath}/roles/userRolesIFrame.htm?id=' + roleId, RoleName+'-角色权限', "group_key.png", 'true',"${staticImg}");
        }
    }
</@shiro.hasPermission>

<@shiro.hasPermission name="/role/authentication">
    //权限设置
    $("#lr-set").hover(function () {
        var left = $(this).offset().left - ($(this).find('.dropdown-data').width() / 2) + ($(this).width() / 2 + 9);
        $(this).find('.dropdown-data').show().css('top', ($(this).offset().top + 40)).css('left', left);
        $(this).find('.dropdown-icon').addClass('dropdown-icon-hover');
        $(this).addClass('dropdown-selected');
    }, function () {
        if (!$(this).hasClass("_click")) {
            $(this).removeClass('dropdown-selected');
            $(this).find('.dropdown-data').hide();
            $(this).find('.dropdown-icon').removeClass('dropdown-icon-hover');
        }
    });
</@shiro.hasPermission>

<@shiro.hasPermission name="/role/addRole">
    //添加角色
    function addRoles() {
      if (""!=CompanyId&&"0"!=CompanyId){
              $("#addBaRolesModal").modal({
                  remote: "${request.contextPath}/roles/addRolesModal.htm?id=" + CompanyId
              });
      }else {
          tipDialog("请选择左侧目录!", 4, 'warning');
      }
  }
    //添加角色提交
    $("#addBaRoles").unbind("click");
    $("#addBaRoles").click(function () {
        Loading(true,"正在提交数据...","#addBaRolesModal");
        var  code = $("#code").val();
        if(""==code){
            $("#code").focus();
            tipDialog("请输入编码", 4, 'warning');
            Loading(false,"","#addBaRolesModal");
            return false;
        }
        var cName = $("#cName").val();
        if(""==cName){
            $("#cName").focus();
            tipDialog("请输入名字", 4, 'warning');
            Loading(false,"","#addBaRolesModal");
            return false;
        }
        $.post('${request.contextPath}/roles/addBaRoles.json', $("#addRolesForm").serialize(), function (result) {
            if(result.success){
                $("#gridTable").trigger("reloadGrid");
                tipDialog(result.msg, 4, '1');
                $("#addBaRolesModal").modal('hide');
            }else {
                tipDialog(result.msg, 4, '0');
            }
            Loading(false,"","#addBaRolesModal");
        }, "JSON");
    });
</@shiro.hasPermission>


<@shiro.hasPermission name="/role/updateRole">
//修改角色
    function updateRoles() {
        var updId = GetJqGridRowValue("#gridTable", "roleId");
        if (IsChecked(updId)){
            $("#updateBaRolesModal").modal({
                remote: "${request.contextPath}/roles/updateRolesModal.htm?id=" + updId
            });
        }
    }
    //修改角色提交
    $("#updateBaRoles").unbind("click");
    $("#updateBaRoles").click(function () {
        Loading(true, "正在提交数据...","#updateBaRolesModal");
        var  code = $("#updcode").val();
        if(""==code){
            $("#updcode").focus();
            tipDialog("请输入编码", 4, 'warning');
            Loading(false,"","#updateBaRolesModal");
            return false;
        }
        var cName = $("#updCName").val();
        if(""==cName){
            $("#updCName").focus();
            tipDialog("请输入名字", 4, 'warning');
            Loading(false,"","#updateBaRolesModal");
            return false;
        }
        $.post('${request.contextPath}/roles/updateBaRoles.json', $("#updateRolesForm").serialize(), function (result) {
            if(result.success){
                $("#gridTable").trigger("reloadGrid");
                tipDialog(result.msg, 4, '1');
                $("#updateBaRolesModal").modal('hide');
            }else {
                tipDialog(result.msg, 4, '0');
            }
            Loading(false,"","#updateBaRolesModal");
        }, "JSON");
    });
</@shiro.hasPermission>

    //删除角色
    <@shiro.hasPermission name="/role/deleteRole">
    function deleteRoles() {
        var id = GetJqGridRowValue("#gridTable", "roleId");
        if (IsChecked(id)) {
            $("#deleteRolesModal").modal("show");
        }
    }
    $("#deleteRoles").unbind("click");
    $("#deleteRoles").click(function () {
        Loading(true,"正在删除数据...","#deleteRolesModal");
        var updId = GetJqGridRowValue("#gridTable", "roleId");
        $.post('${request.contextPath}/roles/deleteBaRoles.json',{id: updId}, function (res) {
            if (res.success) {
                $("#gridTable").trigger("reloadGrid");
                tipDialog(res.msg, 4, "warning");
                $("#deleteRolesModal").modal("hide");
            } else {
                tipDialog(res.msg, 4, "warning");
            }
            Loading(false,"","#deleteRolesModal");
        }, "json");
    });
    </@shiro.hasPermission>


<@shiro.hasPermission name="/role/roleDetail">
//角色详情
    function rolesDetail() {
        var updId = GetJqGridRowValue("#gridTable", "roleId");
        if (IsChecked(updId)){
            $("#baRolesDetailModal").modal({
                remote: "${request.contextPath}/roles/rolesDetailModal.htm?id=" + updId
            });
        }
    }
</@shiro.hasPermission>

</script>