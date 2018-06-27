
<div class="tools_bar leftline rightline" >
    <div class="PartialButton">
    <@shiro.hasPermission name="/user/reload">
        <a id="lr-replace" title="刷新当前(Ctrl+Q)" onclick="Replace()" class="tools_btn">
                     <span>
                         <b class="btn-reload">刷新</b>
                     </span>
        </a>
        <div class="tools_separator"></div>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/user/addUser">
        <a id="lr-add" title="新增(Ctrl+Z)" onclick="addUser()" class="tools_btn">
                    <span>
                        <b class="btn-add">新增</b>
                    </span>
        </a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/user/updateUser">
        <a id="lr-edit" title="编辑(Ctrl+W)" onclick="updateUserModal()" class="tools_btn">
                    <span>
                        <b class="btn-update">编辑</b>
                    </span>
        </a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/user/deleteUser">
        <a id="lr-delete" title="删除(Ctrl+S)" onclick="deleteBaResource()" class="tools_btn">
                    <span>
                        <b class="btn-delete">删除</b>
                    </span>
        </a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/user/userDetail">
        <a id="lr-detail" title="详细信息(Ctrl+X)" onclick="baUserDetail()" class="tools_btn">
                    <span>
                        <b class="btn-detail">详细</b>
                    </span>
        </a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/user/resetPassword">
        <div class="tools_separator"></div>
        <a id="Reset" title="重置密码" onclick="resetPassword()" class="tools_btn">
                    <span>
                        <b style="background: url('${staticImg}/images/Icon16/gnupg_keys.png') 50% 4px no-repeat;">重置密码</b>
                    </span>
        </a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/user/back">
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
    <div class="bottomline QueryArea" >
        <table border="0" class="form-find" >
            <tr id="selectParameter">
                <td>
                    工号：<input id="user-Code" name="code" type="text" class="input-txt" style="width: 100px" maxlength="10"
                              onkeyup="this.value=this.value.replace(/\s/g,'')"
                              onblur="this.value=this.value.replace(/\s/g,'')">
                </td>
                <td>
                    名称：<input id="user-CName" name="cName" type="text" class="input-txt" style="width: 100px"
                              maxlength="10">
                </td>
                <td>
                    所在部门：<input id="user-DepartmentName" name="departmentName" type="text" class="input-txt" style="width: 100px">
                </td>
                <td>
                    所属角色：
                    <select id="user-RoleId" name="roleId" class="txtselect" datacol="yes" err="所属角色" checkexpession="NotNull">
                    <#if baRolesList??>
                        <option value="">==请选择==</option>
                        <#list baRolesList as roles>
                            <option value="${roles.roleId}">${roles.CName}</option>
                        </#list>
                    </#if>
                    </select>
                </td>
                <td>
                    状态：
                    <select id="user-Status" name="status" class="txtselect" datacol="yes" err="所属角色" checkexpession="NotNull">
                        <option value="">==请选择==</option>
                        <option value="1">正常</option>
                        <option value="2">禁用</option>
                    </select>
                </td>
                <td>
                    <input id="user-Search" type="button" class="btnSearch" value="查 询">
                    <input id="user-Clear" type="button" class="btnSearch" value="重 置">
                </td>
            </tr>
        </table>
    </div>
    <table id="gridTable"></table>
    <div id="gridPager"></div>
</div>

<!--添加用户-->
<@shiro.hasPermission name="/user/addUser">
<div id="addBaUserModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">添加用户</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="addBaUser" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>

<!--修改用户信息-->
<@shiro.hasPermission name="/user/updateUser">
<div id="updateUserModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header" style="text-align: center">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">修改用户信息</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="updateUser" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>

<!--查看用户详情-->
<@shiro.hasPermission name="/user/userDetail">
<div id="userDetailModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header" style="text-align: center">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">用户详情</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn green">离开</button>
    </div>
</div>
</@shiro.hasPermission>

<#--删除确认-->
<@shiro.hasPermission name="/user/deleteUser">
<div id="deleteUserModal" class="modal fade " data-width="400" tabindex="-1" aria-hidden="true"
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
        <button id="deleteUser" type="button" class="btn green">确定</button>
    </div>
</div>
</@shiro.hasPermission>

<#--重置密码-->
<@shiro.hasPermission name="/user/resetPassword">
<div id="resetPasswordModal" class="modal fade " data-width="400" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header" style="text-align: center">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">重置密码</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="resetPassword" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>

<script>
    $(document).ready(function () {
        GetGrid();
        gridPagerStyle();
    });
    //加载表格
    function GetGrid() {
        $("#gridTable").jqGrid({
            url: "${request.contextPath}/user/GridJson.json",
            datatype: "json",
            height: ($(window).height() -150),
            autowidth: true,
            colModel: [
                {label: "主键", name: "userId", index: "userId", hidden: true},
                {label: "工号", name: "code", index: "code", width: 76},
                {label: "账号", name: "account", index: "account", width: 76},
                {label: "名称", name: "cname", index: "cName", width: 90},
                {label: "所在部门", name: "departmentName", index: "departmentName", width: 80},
                {label: "角色", name: "roleName", index: "roleName", width: 150},
                {label: '用户状态', name: 'status', index: 'status', width: 100, align: 'center',
                    formatter: function (cellvalue, options, rowObject) {
                            if(1==cellvalue){
                                return "正常";
                            }else if(2==cellvalue){
                                return "禁用";
                            }else {
                                return "";
                            }
                        }
                },
                {label: "上次登录时间", name: "previousVisit", index: "previousVisit", width: 140},
                {label: "创建时间", name: "createDate", index: "createDate", width: 140},
                {label: "创建用户", name: "createUserName", index: "createUserName", width: 80},
                {label: "更新用户", name: "updateUserName", index: "updateUserName", width: 80},
                {label: "更新时间", name: "updateDate", index: "updateDate", width: 140,
                    formatter: function (cellvalue, options, rowObject) {
                            if("" != rowObject.updateUserName){
                                return cellvalue;
                            }else {
                                return "";
                            }
                        }
                }
            ],
            pagerpos : "right",
            viewrecords : true,
            recordpos : "left",
            pager: "#gridPager",
            rowNum : 10,
            rowList:[10,20,30,50,100],
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
    //新增
<@shiro.hasPermission name="/user/addUser">
    function addUser() {
        $("#addBaUserModal").modal({
            remote: "${request.contextPath}/user/addUserModal.htm"
        });
    }
    $("#addBaUser").unbind("click");
    $("#addBaUser").click(function () {
        Loading(true, "正在提交数据...","#addBaUserModal");
        var code = $("#code").val();
        if(""==code){
            $("#code").focus();
            tipDialog("请输入工号",4,"warning");
            Loading(false,"","#addBaUserModal");
            return false;
        }
        var account = $("#account").val();
        if(""==account){
            $("#account").focus();
            tipDialog("请输入登录账号",4,"warning");
            Loading(false,"","#addBaUserModal");
            return false;
        }
        var password = $("#password").val();
        if(""==password){
            $("#password").focus();
            tipDialog("请输入密码",4,"warning");
            Loading(false,"","#addBaUserModal");
            return false;
        }else {
            if(6>password.length||10<password.length){
                $("#password").focus();
                tipDialog("密码长度应该在6～10个字符",4,"warning");
                Loading(false,"","#addBaUserModal");
                return false;
            }
        }
        var confirmPassword = $("#confirmPassword").val();
        if(""==confirmPassword){
            $("#confirmPassword").focus();
            tipDialog("请确认您的密码",4,"warning");
            Loading(false,"","#addBaUserModal");
            return false;
        }
        if(""!=password&&""!=confirmPassword){
            if(password!=confirmPassword){
                $("#password").focus();
                $("#confirmPassword").focus();
                tipDialog("您的密码与确认密码不一致",4,"warning");
                Loading(false,"","#addBaUserModal");
                return false;
            }
        }
        var userType = $("#userType").val();
        if("" == userType){
            tipDialog("请选择用户类型",4,"warning");
            Loading(false,"","#addBaUserModal");
            return false;
        }else {
            if(1==userType){
                var roleId1 = $("#roleId1").val();
                if("" == roleId1){
                    tipDialog("请选择所属角色(PC)",4,"warning");
                    Loading(false,"","#addBaUserModal");
                    return false;
                }
            }else if(2== userType){
                var roleId2 = $("#roleId2").val();
                if("" == roleId2){
                    tipDialog("请选择所属角色(手机)",4,"warning");
                    Loading(false,"","#addBaUserModal");
                    return false;
                }
            }else {
                var roleId3 = $("#roleId3").val();
                if("" == roleId3){
                    tipDialog("请选择所属角色(PC)",4,"warning");
                    Loading(false,"","#addBaUserModal");
                    return false;
                }
                var roleId4 = $("#roleId4").val();
                if("" == roleId4){
                    tipDialog("请选择所属角色(手机)",4,"warning");
                    Loading(false,"","#addBaUserModal");
                    return false;
                }
            }
        }
        $.post('${request.contextPath}/user/addUser.json', $("#addUserForm").serialize(), function (result) {
            if (result.success) {
                $("#gridTable").trigger("reloadGrid");
                tipDialog(result.msg, 4, '1');
                $("#addBaUserModal").modal('hide');
            } else {
                tipDialog(result.msg, 4, '0');
            }
            Loading(false,"","#addBaUserModal");
        }, "JSON");
    });
</@shiro.hasPermission>

    //修改
<@shiro.hasPermission name="/user/updateUser">
    function updateUserModal() {
        var id = GetJqGridRowValue("#gridTable", "userId");
        if (IsChecked(id)){
            $("#updateUserModal").modal({
                remote: "${request.contextPath}/user/updateUserModal.htm?id=" + id
            });
        }
    }
    $("#updateUser").unbind("click");
    $("#updateUser").click(function () {
        Loading(true, "正在提交数据...","#updateUserModal");
        var code = $("#updcode").val();
        if(""==code){
            $("#updCode").focus();
            tipDialog("请输入工号");
            Loading(false,"","#updateUserModal");
            return false;
        }
        var userType = $("#updUserType").val();
        if(""==userType){
            $("#updUserType").focus();
            tipDialog("请选择用户类型");
            Loading(false,"","#updateUserModal");
            return false;
        }else {
            if(1==userType){
                var roleId1 = $("#updRoleId1").val();
                if("" == roleId1){
                    tipDialog("请选择所属角色(PC)",4,"warning");
                    Loading(false,"","#updateUserModal");
                    return false;
                }
            }else if(2== userType){
                var roleId2 = $("#updRoleId2").val();
                if("" == roleId2){
                    tipDialog("请选择所属角色(手机)",4,"warning");
                    Loading(false,"","#updateUserModal");
                    return false;
                }
            }else {
                var roleId3 = $("#updRoleId3").val();
                if("" == roleId3){
                    tipDialog("请选择所属角色(PC)",4,"warning");
                    Loading(false,"","#updateUserModal");
                    return false;
                }
                var roleId4 = $("#updRoleId4").val();
                if("" == roleId4){
                    tipDialog("请选择所属角色(手机)",4,"warning");
                    Loading(false,"","#updateUserModal");
                    return false;
                }
            }
        }
        $.post('${request.contextPath}/user/updateUser.json', $("#updateUserForm").serialize(), function (result) {
            if (result.success) {
                $("#gridTable").trigger("reloadGrid");
                tipDialog(result.msg, 4, '1');
                $("#updateUserModal").modal('hide');
            } else {
                tipDialog(result.msg, 4, '0');
            }
            Loading(false,"","#updateUserModal");
        }, "JSON");
    });
</@shiro.hasPermission>

    //删除
    <@shiro.hasPermission name="/user/deleteUser">
    function deleteBaResource() {
        var  id = GetJqGridRowValue("#gridTable", "userId");
        if (IsChecked(id)) {
            $("#deleteUserModal").modal("show");
        }
    }
    $("#deleteUser").unbind("click");
    $("#deleteUser").click(function () {
        Loading(true,"正在删除数据...","#deleteUserModal");
        var id = GetJqGridRowValue("#gridTable", "userId");
        $.post('${request.contextPath}/user/deleteUser.json',{id: id}, function (res) {
            if (res.success) {
                $("#gridTable").trigger("reloadGrid");
                tipDialog(res.msg, 4, "warning");
                $("#deleteUserModal").modal("hide");
            } else {
                tipDialog(res.msg, 4, "warning");
            }
            Loading(false,"","#deleteUserModal");
        }, "json");
    });
    </@shiro.hasPermission>

    //详情
<@shiro.hasPermission name="/user/userDetail">
    function  baUserDetail() {
        var id = GetJqGridRowValue("#gridTable", "userId");
        if (IsChecked(id)){
            $("#userDetailModal").modal({
                remote: "${request.contextPath}/user/userDetailModal.htm?id=" + id
            });
        }
    }
</@shiro.hasPermission>

    //重置密码
<@shiro.hasPermission name="/user/resetPassword">
    function resetPassword() {
        var id = GetJqGridRowValue("#gridTable", "userId");
        if(IsChecked(id)){
            $("#resetPasswordModal").modal({
                remote: "${request.contextPath}/user/resetPasswordModal.htm?id=" + id
            });
        }
    }
    $("#resetPassword").unbind("click");
    $("#resetPassword").click(function () {
        Loading(true,"正在提交...","#resetPasswordModal");
        var newPassword = removeSpace($("#newPassword").val());
        if(""==newPassword){
            $("#newPassword").focus();
            tipDialog("请输入新的密码",4,"warning");
            Loading(false,"","#resetPasswordModal");
            return false;
        }else {
            if(6>newPassword.length || 10<newPassword.length){
                $("#newPassword").focus();
                tipDialog("新的密码应该在6～10位之间",4,"warning");
                Loading(false,"","#resetPasswordModal");
                return false;
            }
        }
        var confirmNewPassword = removeSpace($("#confirmNewPassword").val());
        if(""==confirmNewPassword){
            $("#confirmNewPassword").focus();
            tipDialog("请确认您的密码",4,"warning");
            Loading(false,"","#resetPasswordModal");
            return false;
        }
        if(""!=newPassword&&""!=confirmNewPassword){
            if(newPassword != confirmNewPassword){
                $("#newPassword").focus();
                $("#confirmNewPassword").focus();
                tipDialog("新密码与确认密码不一致",4,"warning");
                Loading(false,"","#resetPasswordModal");
                return false;
            }
        }
        $.post('${request.contextPath}/user/resetPassword.json', $("#resetPasswordForm").serialize(), function (result) {
            if (result.success) {
                $("#gridTable").trigger("reloadGrid");
                tipDialog(result.msg, 4, '1');
                $("#resetPasswordModal").modal('hide');
                if(1===parseInt(result.obj)){
                    Loading(true, "密码修改，请重新登陆！");
                    window.location.href = '${request.contextPath}/logout.htm';
                }
            } else {
                tipDialog(result.msg, 4, '0');
            }
            Loading(false,"","#resetPasswordModal");
        }, "JSON");
    });
</@shiro.hasPermission>

    $("#user-Search").unbind("click");
    $("#user-Search").click(function () {
        let code = $("#user-Code").val();
        let cName = $("#user-CName").val();
        let departmentName = $("#user-DepartmentName").val();
        let roleId = $("#user-RoleId").val();
        let status = $("#user-Status").val();
        let postData ={code:code,cName:cName,departmentName:departmentName,roleId:roleId,status:status};
        //提交post并刷新表格
        $("#gridTable").jqGrid("setGridParam",{
            postData:postData,
            url:"${request.contextPath}/user/GridJson.json",
            page:1
        }).trigger("reloadGrid");
    });

    $("#user-Clear").unbind("click");
    $("#user-Clear").click(function () {
        //重置表格
        $("#user-Code").val("");
        $("#user-CName").val("");
        $("#user-DepartmentName").val("");
        $("#user-RoleId").val("");
        $("#user-Status").val("");
        let postData ={code:"",cName:"",departmentName:"",roleId:"",status:""};
        $("#gridTable").jqGrid('setGridParam', {
            postData:postData,
            url: "${request.contextPath}/user/GridJson.json",
            page:1
        }).trigger('reloadGrid');
    });

</script>