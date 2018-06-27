
<div id="layout" class="layout" >
    <!--左边-->
    <div class="layoutPanel layout-west" >
        <div class="btnbartitle">
            <div>
                字典目录
            </div>
        </div>
        <div class="ScrollBar" id="ItemsTree"></div>
    </div>
    <!--中间-->
    <div class="layoutPanel layout-center">
        <div class="btnbartitle">
            <div>
                数据信息<span id="CenterTitle"></span>
            </div>
        </div>
        <div class="tools_bar" >
            <div class="PartialButton">
            <@shiro.hasPermission name="/dataDictionary/reload">
                <a id="lr-replace" title="刷新当前(Ctrl+Q)" onclick="Replace()" class="tools_btn">
                    <span>
                        <b class="btn-reload">刷新</b>
                    </span>
                </a>
                <div class="tools_separator"></div>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/dataDictionary/addDataDictionary">
                <a id="lr-add" title="新增(Ctrl+Z)" onclick="addDataDictionaryDetailsModal()" class="tools_btn">
                    <span>
                        <b class="btn-add">新增</b>
                    </span>
                </a>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/dataDictionary/updateDataDictionary">
                <a id="lr-edit" title="编辑(Ctrl+W)" onclick="updateDataDictionaryModal()" class="tools_btn">
                    <span>
                        <b class="btn-update">编辑</b>
                    </span>
                </a>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/dataDictionary/deleteDataDictionary">
                <a id="lr-delete" title="删除(Ctrl+S)" onclick="deleteDataDictionary()" class="tools_btn">
                    <span>
                        <b class="btn-delete">删除</b>
                    </span>
                </a>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/dataDictionary/dataDictionaryDetail">
                <a id="lr-detail" title="详细信息(Ctrl+X)" onclick="showDataDictionaryDetailsModal()" class="tools_btn">
                    <span>
                        <b class="btn-detail">详细</b>
                    </span>
                </a>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/dataDictionary/sortManage">
                <div class="tools_separator"></div>
                <a id="lr-manage" title="分类管理" onclick="btn_sortManage()" class="tools_btn">
                    <span>
                        <b class="btn-sort">分类管理</b>
                    </span>
                </a>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/dataDictionary/back">
                <div class="tools_separator"></div>
                <a id="lr-leave" title="关闭当前窗口(Esc)" onclick="btn_back()" class="tools_btn">
                    <span>
                        <b class="btn-back">离开</b>
                    </span>
                </a>
            </@shiro.hasPermission>
            </div>
        </div>
        <table id="gridTable"></table>
    </div>
</div>


<@shiro.hasPermission name="/dataDictionary/addDataDictionary">
<!--添加数据字典详情-->
<div id="addBaDataDictionaryDetailsModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">添加数据字典详情</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="addBaDataDictionaryDetails" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>


<@shiro.hasPermission name="/dataDictionary/updateDataDictionary">
<#--修改数据字典详情-->
<div id="updateBaDataDictionaryDetailsModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">修改数据字典详情</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="updateBaDataDictionaryDetails" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>

<#--删除确认-->
<@shiro.hasPermission name="/dataDictionary/deleteDataDictionary">
<div id="deleteDataDictionaryModal" class="modal fade " data-width="400" tabindex="-1" aria-hidden="true"
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
        <button id="deleteDataDictionary" type="button" class="btn green">确定</button>
    </div>
</div>
</@shiro.hasPermission>


<@shiro.hasPermission name="/dataDictionary/dataDictionaryDetail">
<#--查看数据字典-->
<div id="showBaDataDictionaryDetailsModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">查看数据字典</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn green">离开</button>
    </div>
</div>
</@shiro.hasPermission>

<script>
    $(document).ready(function () {
        GetTree();
        GetGrid();
        Loadlayout();
    });

    var id,name;
    //加载左边树
    function GetTree() {
        var itemtree = {
            onnodeclick: function (item) {
                id = item.id;            //ID
                name = item.text;        //名称
                $("#CenterTitle").html(" - " + name);
                $("#gridTable").jqGrid('setGridParam', {
                    url: "${request.contextPath}/dataDictionaryDetails/GridJson.json?dataDictionaryId=" + id, page: 1
                }).trigger('reloadGrid');
            },
            url: "${request.contextPath}/dataDictionary/TreeJson.json"
        };
        $("#ItemsTree").treeview(itemtree);
    }

    //加载表格
    function GetGrid() {
        $("#gridTable").jqGrid({
            url: "${request.contextPath}/dataDictionaryDetails/GridJson.json?dataDictionaryId=1",
            datatype: "json",
            height: ($(window).height() - 180),
            autowidth: true,
            colModel: [
                { label: '主键', name: 'dataDictionaryDetailsId', index: 'dataDictionaryDetailsId', hidden: true, key: true },
                { label: '名称', name: 'dataDictionaryDetailName', index: 'dataDictionaryDetailName', hidden: true },
                { label: "项目名称", name: "cname", index: "cname", width: 100 },
                { label: "项目值", name: "code", index: "code", width: 150 },
                { label: "说明", name: "remark", index: "remark", width: 400 },
                {
                    label: '有效', name: 'status', index: 'status', width: 45, align: 'center',
                    formatter: function (cellvalue, options, rowObject) {
                        if (cellvalue == null || cellvalue == undefined || cellvalue == 6) return "<img src='${staticImg}/images/checknomark.gif' />";
                        if (cellvalue == 1) return "<img src='${staticImg}/images/checkokmark.gif' />";else return "<img src='${staticImg}/images/checknomark.gif' />";
                    }
                },
                { label: "创建用户", name: "createUserName", index: "createUserName", width: 100 },
                {
                    label: "创建时间", name: "createDate", index: "createDate", width: 150,
                    formatter: function (cellvalue, options, rowObject) {
                        return formatDate(cellvalue, 'yyyy-MM-dd hh:mm:ss');
                    }
                },
                { label: "修改用户", name: "updateUserName", index: "updateUserName", width: 100 },
                {
                    label: "修改时间", name: "updateDate", index: "updateDate", width: 150,
                    formatter: function (cellvalue, options, rowObject) {
                        if(""==rowObject.updateUserName){
                            return "";
                        }else {
                            return formatDate(cellvalue, 'yyyy-MM-dd hh:mm:ss');
                        }
                    }
                }
            ],
            jsonReader:{
                root:"root",page:"page",total:"total",records:"records"
            },
            rownumbers: true,
            sortname: 'listIndex',
            sortorder: 'asc',
            shrinkToFit: false,
            gridview: true
        });
    }

    //分类管理
    function btn_sortManage() {
        AddTabMenu('dataDictionarySortManage', '${request.contextPath}/dataDictionary/sortManageIFrame.htm', '数据字典-分类管理', "sort_ascending.png", 'true',"${staticImg}");
    }


<@shiro.hasPermission name="/dataDictionary/addDataDictionary">
//添加详情
    function addDataDictionaryDetailsModal() {
        if(null != id && 0 != id){
            $("#addBaDataDictionaryDetailsModal").modal({
                remote: "${request.contextPath}/dataDictionaryDetails/addDataDictionaryDetailsModal.htm?id=" + id
            });
        }else {
            tipDialog("请先选择左边目录", 4, 'warning');
        }
    }
//添加详情
    $("#addBaDataDictionaryDetails").unbind("click");
    $("#addBaDataDictionaryDetails").click(function () {
        Loading(true,"正在提交数据...","#addBaDataDictionaryDetailsModal");
        var code = $("#code").val();
        if(""==code){
            $("#code").focus();
            tipDialog("请输入编码", 4, 'warning');
            Loading(false,"","#addBaDataDictionaryDetailsModal");
            return false;
        }
        var cName = $("#cName").val();
        if(""==cName){
            $("#cName").focus();
            tipDialog("请输入名称", 4, 'warning');
            Loading(false,"","#addBaDataDictionaryDetailsModal");
            return false;
        }
        $.post('${request.contextPath}/dataDictionaryDetails/addBaDataDictionaryDetails.json',$("#addBaDataDictionaryDetailsForm").serialize(),function (result) {
            if(result.success){
                $("#gridTable").trigger("reloadGrid");
                tipDialog(result.msg, 4, '1');
                $("#addBaDataDictionaryDetailsModal").modal('hide');
            }else {
                tipDialog(result.msg, 4, '0');
            }
            Loading(false,"","#addBaDataDictionaryDetailsModal");
        }, "JSON");
    });
</@shiro.hasPermission>


<@shiro.hasPermission name="/dataDictionary/updateDataDictionary">
//修改
    function updateDataDictionaryModal() {
        var updId = GetJqGridRowValue("#gridTable", "dataDictionaryDetailsId");
        if(IsChecked(updId)){
            $("#updateBaDataDictionaryDetailsModal").modal({
                remote: "${request.contextPath}/dataDictionaryDetails/updateDataDictionaryDetailsModal.htm?id=" + updId
            });
        }
    }
    $("#updateBaDataDictionaryDetails").unbind("click");
    $("#updateBaDataDictionaryDetails").click(function () {
        Loading(true,"正在提交数据...","#updateBaDataDictionaryDetails");
        var code = $("#updCode").val();
        if(""==code){
            $("#updCode").focus();
            tipDialog("请输入编码", 4, 'warning');
            Loading(false,"","#updateBaDataDictionaryDetails");
            return false;
        }
        var cName = $("#updCName").val();
        if(""==cName){
            $("#updCName").focus();
            tipDialog("请输入名称", 4, 'warning');
            Loading(false,"","#updateBaDataDictionaryDetails");
            return false;
        }
        $.post('${request.contextPath}/dataDictionaryDetails/updateBaDataDictionaryDetails.json',$("#updateBaDataDictionaryDetailsForm").serialize(),function (result) {
            if(result.success){
                $("#gridTable").trigger("reloadGrid");
                tipDialog(result.msg, 4, '1');
                $("#updateBaDataDictionaryDetailsModal").modal('hide');
            }else {
                tipDialog(result.msg, 4, '0');
            }
            Loading(false,"","#updateBaDataDictionaryDetails");
        }, "JSON");
    });
</@shiro.hasPermission>

    //删除
    <@shiro.hasPermission name="/dataDictionary/deleteDataDictionary">
    function deleteDataDictionary() {
        var updId = GetJqGridRowValue("#gridTable", "dataDictionaryDetailsId");
        if (IsChecked(updId)) {
            $("#deleteDataDictionaryModal").modal("show");
        }
    }
    $("#deleteDataDictionary").click(function () {
        Loading(true,"正在删除数据...","#deleteDataDictionaryModal");
        var updId = GetJqGridRowValue("#gridTable", "dataDictionaryDetailsId");
        $.post('${request.contextPath}/dataDictionaryDetails/deleteBaDataDictionaryDetails.json',{id: updId}, function (res) {
            if (res.success) {
                $("#gridTable").trigger("reloadGrid");
                tipDialog(res.msg, 4, "warning");
                $("#deleteDataDictionaryModal").modal("hide");
            } else {
                tipDialog(res.msg, 4, "warning");
            }
            Loading(false,"","#deleteDataDictionaryModal");
        }, "json");
    });
    </@shiro.hasPermission>


<@shiro.hasPermission name="/dataDictionary/dataDictionaryDetail">
//查看详情
    function showDataDictionaryDetailsModal() {
        var updId = GetJqGridRowValue("#gridTable", "dataDictionaryDetailsId");
        if(IsChecked(updId)){
            $("#showBaDataDictionaryDetailsModal").modal({
                remote: "${request.contextPath}/dataDictionaryDetails/showDataDictionaryDetailsModal.htm?id=" + updId
            });
        }
    }
</@shiro.hasPermission>
</script>