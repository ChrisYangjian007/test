

<div id="layout" class="layout" >
    <!--左边-->
    <div class="layoutPanel layout-west">
        <div class="btnbartitle">
            <div>
                工艺管理
            </div>
        </div>
        <div class="ztree" >
            <ul id="workFlowTree" class="ztree" >
            </ul>
        </div>
    </div>
    <!--中间-->
    <div class="layoutPanel layout-center">
        <div class="btnbartitle">
            <div>
                工艺详情<span id="CenterTitle"></span>
            </div>
        </div>
        <div class="tools_bar" >
            <div class="PartialButton">
            <@shiro.hasPermission name="/workProcess/reload">
                <a id="lr-replace" title="刷新当前(Ctrl+Q)" onclick="Replace()" class="tools_btn">
                    <span>
                        <b class="btn-reload">刷新</b>
                    </span>
                </a>
                <div class="tools_separator"></div>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/workProcess/addWorkProcess">
                <a id="lr-add" title="新增(Ctrl+Z)" onclick="addWorkProcess()" class="tools_btn">
                    <span>
                        <b class="btn-add">新增</b>
                    </span>
                </a>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/workProcess/updateWorkProcess">
                <a id="lr-edit" title="编辑(Ctrl+W)" onclick="updateWorkProcess()" class="tools_btn">
                    <span>
                        <b class="btn-update">编辑</b>
                    </span>
                </a>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/workProcess/deleteWorkProcess">
                <a id="lr-delete" title="删除(Ctrl+S)" onclick="deleteWorkProcess()" class="tools_btn">
                    <span>
                        <b class="btn-delete">删除</b>
                    </span>
                </a>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/workProcess/getFormAttribute">
                <a id="lr-detail" title="查看工序字段" onclick="getFormAttribute()" class="tools_btn">
                    <span>
                        <b class="btn-detail">查看工序字段</b>
                    </span>
                </a>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/workProcess/back">
                <div class="tools_separator"></div>
                <a id="lr-leave" title="关闭当前窗口(Esc)" onclick="btn_back()" class="tools_btn">
                    <span>
                        <b class="btn-back">离开</b>
                    </span>
                </a>
            </@shiro.hasPermission>
            </div>
        </div>
        <table id="workFlowGridTable"></table>
        <div id="workFlowGridPager"></div>
    </div>
</div>

<@shiro.hasPermission name="/workProcess/addWorkProcess">
<!--添加工艺详情-->
<div id="addWorkProcessModal" class="modal fade " data-width="780" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">添加工艺详情</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark" onclick="RowIndex()">取消</button>
        <button id="addWorkProcess" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>

<@shiro.hasPermission name="/workProcess/updateWorkProcess">
<!--修改工艺详情-->
<div id="updateWorkProcessModal" class="modal fade " data-width="780" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">修改工艺详情</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark" onclick="RowIndex()">取消</button>
        <button id="updateWorkProcess" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>

<!--添加工艺-->
<div id="addWorkFlowModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">添加工艺</h4>
    </div>
    <div class="modal-body">
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="addWorkFlow" type="button" class="btn green">确认</button>
    </div>
</div>

<!--修改工艺-->
<div id="updateWorkFlowModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">修改工艺</h4>
    </div>
    <div class="modal-body">
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="updateWorkFlow" type="button" class="btn green">确认</button>
    </div>
</div>

<#--删除工艺-->
<div id="deleteWorkFlowModal" class="modal fade " data-width="400" tabindex="-1" aria-hidden="true"
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
        <button id="deleteWorkFlow" type="button" class="btn green">确定</button>
    </div>
</div>

<@shiro.hasPermission name="/workProcess/getFormAttribute">
<!--查看工艺字段工艺详情-->
<div id="workProcessDetailModal" class="modal fade " data-width="780" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">查看工艺详情</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" class="btn green" data-dismiss="modal" >确认</button>
    </div>
</div>
</@shiro.hasPermission>

<@shiro.hasPermission name="/workProcess/deleteWorkProcess">
<div id="deleteWorkProcessModal" class="modal fade " data-width="400" tabindex="-1" aria-hidden="true"
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
        <button id="deleteWorkProcess" type="button" class="btn green">确定</button>
    </div>
</div>
</@shiro.hasPermission>

<link href="${staticJs}/js/zTree/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>
<script src="${staticJs}/js/zTree/jquery.ztree.core.js"></script>
<script src="${staticJs}/js/zTree/jquery.ztree.excheck.js"></script>
<script src="${staticJs}/js/zTree/jquery.ztree.exedit.js"></script>
<script src="${staticJs}/js/zTree/jquery.ztree.exhide.js"></script>

<script>
    var RowIndex1 = 1;
    var RowIndex2 = 1;
    var RowIndex3 = 1;
    var closeAddWorkProcess = true;
    var resourceId ;
    var UpdateTabTable = 1;

    function RowIndex() {
        UpdateTabTable = 1;
        RowIndex1 = 1;
        RowIndex2 = 1;
        RowIndex3 = 1;
        closeAddWorkProcess = true;
    }

    $(document).ready(function () {
        GetTree();
        GetGrid();
        gridPagerStyle();
        Loadlayout();
        resourceId = top.$("#ModuleId").val()
    });

    var workFlowTree;
    //加载左边树
    function GetTree() {
        var setting = {
            async: {
                enable: true,
                type: "POST",
                url: "${request.contextPath}/workFlow/getZsWorkFlowForZTree.json",
                cache: false,
                autoParam: ["id=workFlowId"],
                dataFilter: filter
            },
            data: {
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "pId",
                    rootPId: 0
                }
            },
            view: {
                selectedMulti: false,
                addHoverDom: addHoverDom,
                removeHoverDom: removeHoverDom
            },
            callback: {
                onClick: reloadzTree
            }
        };
        $.fn.zTree.init($("#workFlowTree"), setting);
        workFlowTree = $.fn.zTree.getZTreeObj("workFlowTree");
    }

    function addHoverDom(treeId, treeNode) {
        var sObj = $("#" + treeNode.tId + "_a");
        if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0 || $("#updateBtn_" + treeNode.tId).length > 0 || $("#deleteBtn_" + treeNode.tId).length > 0) return;
        var addStr = "<span class='button add' id='addBtn_" + treeNode.tId + "' title='添加' onfocus='this.blur();'></span>" ;
        var updateStr = "<span class='button update' id='updateBtn_" + treeNode.tId + "' title='修改' onfocus='this.blur();'></span>";
        var deleteStr = "<span class='button delete' id='deleteBtn_" + treeNode.tId + "' title='确认删除?' onfocus='this.blur();'></span>";
        if (0 == treeNode.level) {
            sObj.after(addStr);
        }else {
            if (4== treeNode.level){
                sObj.after(updateStr+deleteStr);
            }else {
                sObj.after(addStr+updateStr+deleteStr);
            }
        }
        var addBtn = $("#addBtn_" + treeNode.tId);
        var updateBtn = $("#updateBtn_" + treeNode.tId);
        var deleteBtn = $("#deleteBtn_" + treeNode.tId);
        if (addBtn) addBtn.bind("click", function () {
            addWorkFlow(treeNode);
            return false;
        });
        if (updateBtn) updateBtn.bind("click", function () {
            updateWorkFlow(treeNode);
            return false;
        });
        if (deleteBtn) deleteBtn.bind("click", function () {
            deleteWorkFlow(treeNode);
            return false;
        });
    }

    function removeHoverDom(treeId, treeNode) {
        $("#addBtn_" + treeNode.tId).unbind().remove();
        $("#updateBtn_" + treeNode.tId).unbind().remove();
        $("#deleteBtn_" + treeNode.tId).unbind().remove();
    }

    function filter(treeId, parentNode, childNodes) {
        if (!childNodes) return null;
        for (var i = 0, l = childNodes.obj.length; i < l; i++) {
            childNodes.obj[i].name = childNodes.obj[i].name.replace(/\.n/g, '.');
        }
        return childNodes.obj;
    }

    function reloadzTree() {
        var nodes = workFlowTree.getSelectedNodes();
        $("#workFlowGridTable").jqGrid('setGridParam', {
            url: "${request.contextPath}/workProcess/GridJson.json?workFlowId="+nodes[0].id
        }).trigger('reloadGrid');
        for (var i = 0, l = nodes.length; i < l; i++) {
            workFlowTree.reAsyncChildNodes(nodes[i], "refresh", true);
        }
    }

    function addWorkFlow(treeNode) {
        $("#addWorkFlowModal").modal({
            remote: "${request.contextPath}/workFlow/addWorkFlowModal.htm?id=" + treeNode.id+"&resourceId="+resourceId
        });
        $('#addWorkFlow').unbind("click");
        $("#addWorkFlow").click(function () {
            Loading(true,"正在提交数据...","#addWorkFlowModal");
            var cName = $.trim($("#cName").val());
            if(""==cName){
                $("#cName").focus();
                tipDialog("请输入工艺名称", 4, 'warning');
                Loading(false,"","#addWorkFlowModal");
                return false;
            }
            $.post('${request.contextPath}/workFlow/addWorkFlow.json', $('#addWorkFlowForm').serialize(), function (result) {
                if (result.success) {
                    tipDialog(result.msg, 4, '1');
                    workFlowTree.reAsyncChildNodes(treeNode, "refresh");
                    $('#addWorkFlowModal').modal('hide');
                } else {
                    tipDialog(result.msg, 4, '0');
                }
                Loading(false,"","#addWorkFlowModal");
            }, "JSON");
        });
    }

    function updateWorkFlow(treeNode) {
        $("#updateWorkFlowModal").modal({
            remote: "${request.contextPath}/workFlow/updateWorkFlowModal.htm?id=" + treeNode.id+"&resourceId="+resourceId
        });
        $('#updateWorkFlow').unbind("click");
        $("#updateWorkFlow").click(function () {
            Loading(true,"正在提交数据...","#updateWorkFlowModal");
            var cName = $.trim($("#updCName").val());
            if(""==cName){
                $("#updCName").focus();
                tipDialog("请输入工艺名称", 4, 'warning');
                Loading(false,"","#updateWorkFlowModal");
                return false;
            }
            $.post('${request.contextPath}/workFlow/updateWorkFlow.json', $('#updateWorkFlowForm').serialize(), function (result) {
                if (result.success) {
                    var node = workFlowTree.getNodeByParam("id", treeNode.pId, null);
                    workFlowTree.reAsyncChildNodes(node, "refresh");
                    tipDialog(result.msg, 4, '1');
                    $('#updateWorkFlowModal').modal('hide');
                } else {
                    tipDialog(result.msg, 4, '0');
                }
                Loading(false,"","#updateWorkFlowModal");
            }, "JSON");
        });
    }

    function deleteWorkFlow(treeNode) {
        $("#deleteWorkFlowModal").modal("show");
        $("#deleteWorkFlow").unbind("click");
        $("#deleteWorkFlow").click(function () {
            Loading(true,"正在删除数据...","#deleteWorkFlowModal");
            $.post('${request.contextPath}/workFlow/deleteWorkFlow.json',{workFlowId: treeNode.id,resourceId:resourceId},function (result) {
                if(result.success){
                    var node = workFlowTree.getNodeByParam("id", treeNode.pId, null);
                    workFlowTree.reAsyncChildNodes(node, "refresh");
                    tipDialog(result.msg, 4, '1');
                    $("#deleteWorkFlowModal").modal("hide");
                }else {
                    tipDialog(result.msg, 4, '0');
                }
                Loading(false,"","#deleteWorkFlowModal");
            }, "JSON");
        });
    }
    
    //加载表格
    function GetGrid() {
        $("#workFlowGridTable").jqGrid({
            url: "${request.contextPath}/workProcess/GridJson.json?workFlowId=1",
            datatype: "json",
            height: ($(window).height() - 130),
            autowidth: true,
            colModel: [
                { label: '主键', name: 'workProcessId', index: 'workProcessId', hidden: true, key: true },
                { label: "工艺名称", name: "cname", index: "cName", width: 100 },
                { label: "工序字段", name: "isWorkshopRecord", index: "isWorkshopRecord", width: 150,
                    formatter: function (cellvalue, options, rowObject) {
                        if (cellvalue == 1) return "有";
                        else return "无";
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
                        }else{
                            return formatDate(cellvalue, 'yyyy-MM-dd hh:mm:ss');
                        }
                    }
                }
            ],
            pagerpos : "right",
            viewrecords : true,
            recordpos : "left",
            pager: "#workFlowGridPager",
            rowNum : 15,
            rowList:[15,30,50,100,500],
            jsonReader:{
                root:"root",page:"page",total:"total",records:"records"
            },
            rownumbers: true,
            sortname: 'updateDate',
            sortorder: 'desc',
            shrinkToFit: false,
            gridview: true
        });
    }
    //分页布局
    function gridPagerStyle() {
        var width = document.getElementById("workFlowGridPager_right").offsetWidth+5;
        $("#workFlowGridPager_right").attr("style","width:"+width);
    }

<@shiro.hasPermission name="/workProcess/addWorkProcess">

    function addWorkProcess() {
        var nodes = workFlowTree.getSelectedNodes();
        if (""!=nodes){
            $("#addWorkProcessModal").modal({
                remote: "${request.contextPath}/workProcess/addWorkProcessModal.htm?id=" + nodes[0].id+"&resourceId="+resourceId
            })
        }else {
            tipDialog("请选择左侧工艺", 4, 'warning');
        }
    }

    $("#addWorkProcess").unbind("click");
    $("#addWorkProcess").click(function () {
        Loading(true,"正在提交数据...","#addWorkProcessModal");
        let tableField1 = new Array();
        let tableField2 = new Array();
        let tableField3 = new Array();
        let allData = {
            resourceId: resourceId,
            workFlowId: "",//所属工艺
            cname: "",//工艺名称
            nameId: "",//工艺名称ID
            isWorkshopRecord: "",//工艺字段
            tableField1: tableField1,
            tableField2: tableField2,
            tableField3: tableField3
        };
        let workFlowId = $.trim($("input[name=workFlowId]").val());
        if (""!=workFlowId){
            allData.workFlowId = workFlowId;
        }else {
            tipDialog("数据异常，请刷新后重新添加！", 4, 'warning');
            Loading(false,"","#addWorkProcessModal");
            return false;
        }
        let cName = $.trim($("#workProcessCName").val());
        if (""!=cName){
            allData.cname = cName;
        }else {
            tipDialog("请选择工艺名称！", 4, 'warning');
            Loading(false,"","#addWorkProcessModal");
            return false;
        }
        let nameId = $.trim($("#nameId").val());
        if (""!=nameId){
            allData.nameId = nameId;
        }else {
            tipDialog("工艺名称有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
            Loading(false,"","#addWorkProcessModal");
            return false;
        }
        let isWorkshopRecord = $.trim($("#isWorkshopRecord").val());
        if (""!=isWorkshopRecord){
            allData.isWorkshopRecord = isWorkshopRecord;
            if (1==isWorkshopRecord) {
                let res = false;

                let length1 = $("#grid_Field1 tbody>tr").length;
                let length2 = $("#grid_Field2 tbody>tr").length;
                let length3 = $("#grid_Field3 tbody>tr").length;

                for (let i = 0; i < length1; i++) {
                    let field = {
                        propertyName: "",
                        controlType: "",
                        restrictiveConditions: "",
                        dataSourceType: "",
                        dataSourceCode: "",
                        dataSource: "",
                        listIndex: "",
                        handleType: 1
                    };
                    field.listIndex = (i + 1);
                    field.propertyName = $("#grid_Field1 tbody>tr").eq(i).find("div:eq(0)").text();
                    let controlType = $("#grid_Field1 tbody>tr").eq(i).find("select[name=controlType] :selected").attr("value");
                    if ("" != controlType) {
                        field.controlType = controlType;
                    } else {
                        tipDialog("操作记录第" + (i + 1) + "行，类型有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                        Loading(false, "", "#addWorkProcessModal");
                        return false;
                    }
                    let restrictiveConditions = $("#grid_Field1 tbody>tr").eq(i).find("select[name=restrictiveConditions] :selected").attr("value");
                    if ("" != restrictiveConditions) {
                        field.restrictiveConditions = restrictiveConditions;
                    } else {
                        tipDialog("操作记录第" + (i + 1) + "行，限制条件有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                        Loading(false, "", "#addWorkProcessModal");
                        return false;
                    }
                    let dataSourceType = $("#grid_Field1 tbody>tr").eq(i).find("select[name=dataSourceType] :selected").attr("value");
                    if ("" != dataSourceType) {
                        if (2==controlType){
                            if(0==dataSourceType){
                                tipDialog("操作记录第" + (i + 1) + "行，数据源类型有误，请重新选择，如果还不行，请联系管理员！(下拉框不能选择固定)", 4, 'warning');
                                Loading(false, "", "#addWorkProcessModal");
                                return false;
                            }else {
                                field.dataSourceType = dataSourceType;
                            }
                        }else {
                            field.dataSourceType = dataSourceType;
                        }
                    } else {
                        tipDialog("操作记录第" + (i + 1) + "行，数据源类型有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                        Loading(false, "", "#addWorkProcessModal");
                        return false;
                    }
                    let dataSourceCode = $("#grid_Field1 tbody>tr").eq(i).find("input[name=dataSourceCode]").val();
                    if ("" != dataSourceCode) {
                        field.dataSourceCode = dataSourceCode;
                    } else {
                        if (1 == dataSourceType) {
                            tipDialog("操作记录第" + (i + 1) + "行，数据源有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                            Loading(false, "", "#addWorkProcessModal");
                            return false;
                        }
                    }
                    let dataSource = $("#grid_Field1 tbody>tr").eq(i).find("input[name=dataSource]").val();
                    if ("" != dataSource) {
                        field.dataSource = dataSource;
                    } else {
                        if (1 == dataSourceType) {
                            tipDialog("操作记录第" + (i + 1) + "行，数据源有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                            Loading(false, "", "#addWorkProcessModal");
                            return false;
                        }
                    }
                    tableField1.push(field);
                }
                for (let i = 0; i < length2; i++) {
                    let field = {
                        propertyName: "",
                        controlType: "",
                        restrictiveConditions: "",
                        dataSourceType: "",
                        dataSourceCode: "",
                        dataSource: "",
                        listIndex: "",
                        handleType: 2
                    };
                    field.listIndex = (i + 1);
                    field.propertyName = $("#grid_Field2 tbody>tr").eq(i).find("div:eq(0)").text();
                    let controlType = $("#grid_Field2 tbody>tr").eq(i).find("select[name=controlType] :selected").attr("value");
                    if ("" != controlType) {
                        field.controlType = controlType;
                    } else {
                        tipDialog("审核记录第" + (i + 1) + "行，类型有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                        Loading(false, "", "#addWorkProcessModal");
                        return false;
                    }
                    let restrictiveConditions = $("#grid_Field2 tbody>tr").eq(i).find("select[name=restrictiveConditions] :selected").attr("value");
                    if ("" != restrictiveConditions) {
                        field.restrictiveConditions = restrictiveConditions;
                    } else {
                        tipDialog("审核记录第" + (i + 1) + "行，限制条件有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                        Loading(false, "", "#addWorkProcessModal");
                        return false;
                    }
                    let dataSourceType = $("#grid_Field2 tbody>tr").eq(i).find("select[name=dataSourceType] :selected").attr("value");
                    if ("" != dataSourceType) {
                        if (2==controlType){
                            if(0==dataSourceType){
                                tipDialog("审核记录第" + (i + 1) + "行，数据源类型有误，请重新选择，如果还不行，请联系管理员！(下拉框不能选择固定)", 4, 'warning');
                                Loading(false, "", "#addWorkProcessModal");
                                return false;
                            }else {
                                field.dataSourceType = dataSourceType;
                            }
                        }else {
                            field.dataSourceType = dataSourceType;
                        }
                    } else {
                        tipDialog("审核记录第" + (i + 1) + "行，数据源类型有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                        Loading(false, "", "#addWorkProcessModal");
                        return false;
                    }
                    let dataSourceCode = $("#grid_Field2 tbody>tr").eq(i).find("input[name=dataSourceCode]").val();
                    if ("" != dataSourceCode) {
                        field.dataSourceCode = dataSourceCode;
                    } else {
                        if (1 == dataSourceType) {
                            tipDialog("审核记录第" + (i + 1) + "行，数据源有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                            Loading(false, "", "#addWorkProcessModal");
                            return false;
                        }
                    }
                    let dataSource = $("#grid_Field2 tbody>tr").eq(i).find("input[name=dataSource]").val();
                    if ("" != dataSource) {
                        field.dataSource = dataSource;
                    } else {
                        if (1 == dataSourceType) {
                            tipDialog("审核记录第" + (i + 1) + "行，数据源有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                            Loading(false, "", "#addWorkProcessModal");
                            return false;
                        }
                    }
                    tableField2.push(field);
                }
                for (let i = 0; i < length3; i++) {
                    let field = {
                        propertyName: "",
                        controlType: "",
                        restrictiveConditions: "",
                        dataSourceType: "",
                        dataSourceCode: "",
                        dataSource: "",
                        listIndex: "",
                        handleType: 3
                    };
                    field.listIndex = (i + 1);
                    field.propertyName = $("#grid_Field3 tbody>tr").eq(i).find("div:eq(0)").text();
                    let controlType = $("#grid_Field3 tbody>tr").eq(i).find("select[name=controlType] :selected").attr("value");
                    if ("" != controlType) {
                        field.controlType = controlType;
                    } else {
                        tipDialog("巡检记录第" + (i + 1) + "行，类型有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                        Loading(false, "", "#addWorkProcessModal");
                        return false;
                    }
                    let restrictiveConditions = $("#grid_Field3 tbody>tr").eq(i).find("select[name=restrictiveConditions] :selected").attr("value");
                    if ("" != restrictiveConditions) {
                        field.restrictiveConditions = restrictiveConditions;
                    } else {
                        tipDialog("巡检记录第" + (i + 1) + "行，限制条件有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                        Loading(false, "", "#addWorkProcessModal");
                        return false;
                    }
                    let dataSourceType = $("#grid_Field3 tbody>tr").eq(i).find("select[name=dataSourceType] :selected").attr("value");
                    if ("" != dataSourceType) {
                        if (2==controlType){
                            if(0==dataSourceType){
                                tipDialog("巡检记录第" + (i + 1) + "行，数据源类型有误，请重新选择，如果还不行，请联系管理员！(下拉框不能选择固定)", 4, 'warning');
                                Loading(false, "", "#addWorkProcessModal");
                                return false;
                            }else {
                                field.dataSourceType = dataSourceType;
                            }
                        }else {
                            field.dataSourceType = dataSourceType;
                        }
                    } else {
                        tipDialog("巡检记录第" + (i + 1) + "行，数据源类型有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                        Loading(false, "", "#addWorkProcessModal");
                        return false;
                    }
                    let dataSourceCode = $("#grid_Field3 tbody>tr").eq(i).find("input[name=dataSourceCode]").val();
                    if ("" != dataSourceCode) {
                        field.dataSourceCode = dataSourceCode;
                    } else {
                        if (1 == dataSourceType) {
                            tipDialog("巡检记录第" + (i + 1) + "行，数据源有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                            Loading(false, "", "#addWorkProcessModal");
                            return false;
                        }
                    }
                    let dataSource = $("#grid_Field3 tbody>tr").eq(i).find("input[name=dataSource]").val();
                    if ("" != dataSource) {
                        field.dataSource = dataSource;
                    } else {
                        if (1 == dataSourceType) {
                            tipDialog("巡检记录第" + (i + 1) + "行，数据源有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                            Loading(false, "", "#addWorkProcessModal");
                            return false;
                        }
                    }
                    tableField3.push(field);
                }

                $("#grid_Field1 tbody>tr>td>div").each(function () {
                    let idx = $(this).parent().parent().find("td").index($(this).parent());
                    if (5 != idx) {
                        if ("" == $(this).text()) {
                            let num = $(this).parents("tr").find("td").eq(0).text();
                            tipDialog("操作记录，第" + num + "行有空！", 3, 'warning');
                            tableField1 = new Array();
                            res = true;
                        }
                    }
                });
                $("#grid_Field2 tbody>tr>td>div").each(function () {
                    let idx = $(this).parent().parent().find("td").index($(this).parent());
                    if (5 != idx) {
                        if ("" == $(this).text()) {
                            let num = $(this).parents("tr").find("td").eq(0).text();
                            tipDialog("审核记录，第" + num + "行有空！", 3, 'warning');
                            tableField2 = new Array();
                            res = true;
                        }
                    }
                });
                $("#grid_Field3 tbody>tr>td>div").each(function () {
                    let idx = $(this).parent().parent().find("td").index($(this).parent());
                    if (5 != idx) {
                        if ("" == $(this).text()) {
                            let num = $(this).parents("tr").find("td").eq(0).text();
                            tipDialog("巡检记录，第" + num + "行有空！", 3, 'warning');
                            tableField3 = new Array();
                            res = true;
                        }
                    }
                });

                allData.tableField1 = tableField1;
                allData.tableField2 = tableField2;
                allData.tableField3 = tableField3;
                if (res) {
                    allData = "";
                    Loading(false, "", "#addWorkProcessModal");
                    return false;
                }
            }
        }
        $.ajax({
            type: "POST",
            url: "${request.contextPath}/workProcess/addWorkProcess.json",
            data:JSON.stringify(allData),
            contentType: "application/json;charset=utf-8;",
            dataType: "json",
            async:false,
            success: function (res) {
                $("#workFlowGridTable").trigger("reloadGrid");
                if (res.success){
                    RowIndex();
                    tipDialog(res.msg,4,1);
                    $('#addWorkProcessModal').modal('hide');
                }else {
                    tipDialog(res.msg,4,0);
                }
                Loading(false,"","#addWorkProcessModal");
            },
            error: function () {
                tipDialog("网络异常",4,0);
                Loading(false,"","#addWorkProcessModal");
            }
        });
        allData="";
    });

</@shiro.hasPermission>

<@shiro.hasPermission name="/workProcess/updateWorkProcess">

    function updateWorkProcess() {
        var workProcessId = GetJqGridRowValue("#workFlowGridTable", "workProcessId");
        if (IsChecked(workProcessId)){
            $("#updateWorkProcessModal").modal({
                remote: "${request.contextPath}/workProcess/updateWorkProcessModal.htm?id=" + workProcessId
            });
            $('#updateWorkProcessModal').on('loaded.bs.modal', function (e) {
                UpdateGridFieldShow();
                function UpdateGridFieldShow() {
                    var grid_Field1Length =$("#Update_Grid_Field1 tbody tr").length;
                    RowIndex1+=grid_Field1Length;
                    $("#Update_Grid_Field1 tbody tr").find('input,select').attr('disabled', 'disabled').hide();
                    $("#Update_Grid_Field1 tbody tr").find('input,select').removeAttr('disabled');
                    $("#Update_Grid_Field1 tbody tr").find('input[name=updateDataSource]').attr('disabled',"disabled");
                    //$("#Update_Grid_Field1 tbody tr").find('.dataSourceTypeSelect').removeAttr('disabled');
                    var grid_Field2Length =$("#Update_Grid_Field2 tbody tr").length;
                    RowIndex2+=grid_Field2Length;
                    $("#Update_Grid_Field2 tbody tr").find('input,select').attr('disabled', 'disabled').hide();
                    $("#Update_Grid_Field2 tbody tr").find('input,select').removeAttr('disabled');
                    $("#Update_Grid_Field2 tbody tr").find('input[name=updateDataSource]').attr('disabled',"disabled");
                    //$("#Update_Grid_Field2 tbody tr").find('.dataSourceTypeSelect').attr('disabled',"disabled");
                    var grid_Field3Length =$("#Update_Grid_Field3 tbody tr").length;
                    RowIndex3+=grid_Field3Length;
                    $("#Update_Grid_Field3 tbody tr").find('input,select').attr('disabled', 'disabled').hide();
                    $("#Update_Grid_Field3 tbody tr").find('input,select').removeAttr('disabled');
                    $("#Update_Grid_Field3 tbody tr").find('input[name=updateDataSource]').attr('disabled',"disabled");
                    //$("#Update_Grid_Field3 tbody tr").find('.dataSourceTypeSelect').attr('disabled',"disabled");

                    $("#UpdateTableFieldDiv .grid").on("click",".td-div",function(){
                        $(this).parent().parent().find(".selected").each(function () {
                            $(this).removeClass("selected");
                        });
                        $(this).parent().addClass("selected");
                    });
                    $("#UpdateTableFieldDiv div tbody").on("change",".dataSourceTypeSelect",function(){
                        var thi = $(this);
                        var html = thi.find("option:selected").text();
                        var val = thi.val();
                        var id = thi.attr("id");
                        var index = id.split("-");
                        if(0==val){
                            $("#updateDataSource-"+index[1]+"-"+index[2]).removeClass("icontree");
                            $("#updateDataSource-"+index[1]+"-"+index[2]).prop("disabled",true);
                            $("#dataSourceCode-"+index[1]+"-"+index[2]).val("");
                            $("#updateDataSource-"+index[1]+"-"+index[2]).attr("value","");
                            $("#updateDataSource-"+index[1]+"-"+index[2]).parent().find('div').html("");
                        }else if(1==val){
                            $("#updateDataSource-"+index[1]+"-"+index[2]).addClass("icontree");
                            $("#updateDataSource-"+index[1]+"-"+index[2]).prop("disabled",false);
                        }else if(2==val){
                            $("#updateDataSource-"+index[1]+"-"+index[2]).removeClass("icontree");
                            $("#updateDataSource-"+index[1]+"-"+index[2]).prop("disabled",true);
                            $("#dataSourceCode-"+index[1]+"-"+index[2]).val("");
                            $("#updateDataSource-"+index[1]+"-"+index[2]).attr("value","");
                            $("#updateDataSource-"+index[1]+"-"+index[2]).parent().find('div').html("");
                        }
                    });
                    $("#UpdateTableFieldDiv div tbody").on("focus",".dataSource",function(){
                        Loading(true,"正在获取数据,请等待...","#updateWorkProcessModal");
                        var objId = this.id;
                        var index = objId.split("-");
                        var type = $("#updateDataSourceType-"+index[1]+"-"+index[2]).val();
                        if (1==type) {
                            comboBoxTree(objId, "182px");
                            var itemtree = {
                                onnodeclick: function (item) {
                                    $("#updateDataSourceCode-" + index[1] + "-" + index[2]).val(item.code);
                                    $("#updateDataSource-" + index[1] + "-" + index[2]).attr("value", item.text);
                                    $("#updateDataSource-" + index[1] + "-" + index[2]).html(item.text);
                                    $("#updateDataSource-" + index[1] + "-" + index[2]).parent().find('div').html(item.text);
                                },
                                url: "${request.contextPath}/dataDictionary/TreeJson.json"
                            };
                            $("#comboBoxTree" + objId).treeview(itemtree);
                            $("#comboBoxTree" + objId + "_" + GetQuery('KeyValue').replace(/-/g, '_')).parent('li').remove();
                        }
                        Loading(false,"","#updateWorkProcessModal");
                    });
                    $("#UpdateTableFieldDiv div tbody").on("click","td",function(){
                        if ($(this).find('div').length > 0) {
                            var oldText = $(this).find('div').text();
                            var obj = $(this).find('input,select');
                            if (obj.attr('disabled')) {
                                return false;
                            }
                            var type = obj.attr('class');
                            obj.show();
                            obj.parent().find('div').html("");
                            obj.trigger("focus");
                            obj.blur(function () {
                                if (obj.parent().find('div').length > 0) {
                                    var type = obj.attr('type');
                                    var objclass = (obj.parent().find('input[type=text]').length > 0) ? obj.parent().find('input[type=text]').attr('class') : "";
                                    var newText = "";
                                    switch (type) {
                                        case "select":
                                            newText = obj.find("option:selected").text();
                                            if (obj.parent().find('input[type=hidden]').length > 0) {
                                                obj.parent().find('input[type=hidden]').val(newText);
                                            }
                                            obj.hide();
                                            obj.parent().find('div').html(newText);
                                            break;
                                        default:
                                            if (objclass != "txt icontree") {
                                                newText = obj.last().val();
                                                obj.hide();
                                                obj.parent().find('div').html(newText);
                                            }
                                            break;
                                    }
                                }
                            });
                        }
                    });
                    $("#UpdateTableFieldDiv div tbody").on("change","[name='updateControlType']",function(){
                        if($(this).find("option:selected").text()=="下拉框"){
                            $(this).parents("tr").find(".dataSourceTypeSelect").prop("disabled",false);
                        }else{
                            $(this).parents("tr").find(".dataSourceTypeSelect").prop("disabled",true);
                            $(this).parents("tr").find(".dataSourceTypeSelect").prev("div").text("固定");
                            $(this).parents("tr").find(".dataSourceTypeSelect").find("option").removeAttr("selected");
                            $(this).parents("tr").find(".dataSourceTypeSelect").find("option:first").attr("selected");
                        }
                        let html;
                        if("1"!=$(this).find("option:selected").val()){
                            html = '<option value="1" selected>不能为空</option>'+
                                    '<option value="4">无限制</option>';
                        }else {
                            html = '<option value="1" selected>不能为空</option>'+
                                    '<option value="2">只能输入数字</option>'+
                                    '<option value="3">数字、字母</option>'+
                                    '<option value="4">无限制</option>'+
                                    '<option value="5">默认当前用户</option>';
                        }
                        $(this).parent().parent().find("select[name=updateRestrictiveConditions]").parent().find("div").html("不能为空");
                        $(this).parent().parent().find("select[name=updateRestrictiveConditions]").html(html);
                        if("2"!=$(this).find("option:selected").val()){
                            html = '<option value="0" selected>固定</option>';
                            $(this).parent().parent().find("select[name=updateDataSourceType]").attr('disabled', 'disabled');
                        }else {
                            html = '<option value="0" selected>固定</option>'+
                                    '<option value="1">数据字典</option>'+
                                    '<option value="2">PDA用户</option>';
                        }
                        $(this).parent().parent().find("select[name=updateDataSourceType]").parent().find("div").html("固定");
                        $(this).parent().parent().find("select[name=updateDataSourceType]").html(html);
                    });
                    $("#UpdateTableFieldDiv div tbody").on("click",".click-show",function(){
                        if($(this).find("option:selected").text()!=="下拉框"){
                            //$(this).parents("tr").find(".dataSourceTypeSelect").prev("div").attr("text","固定");
                            $(this).parents("tr").find(".dataSourceTypeSelect").prev("div").text("固定");
                            $(this).parents("tr").find("td:last").find("div").text("");
                        }
                    });
                }
            });
        }
    }

    $("#updateWorkProcess").unbind("click");
    $("#updateWorkProcess").click(function () {
        Loading(true,"正在提交数据...","#updateWorkProcessModal");
        let tableField1 = new Array();
        let tableField2 = new Array();
        let tableField3 = new Array();
        let allData = {
            resourceId:resourceId,
            workFlowId: "",//所属工艺
            workProcessId: "",//工艺ID
            cname: "",//工艺名称
            nameId: "",//工艺名称ID
            isWorkshopRecord: "",//工艺字段
            tableField1: tableField1,
            tableField2: tableField2,
            tableField3: tableField3
        };
        let workFlowId = $.trim($("#UpdateWorkFlowId").val());
        if (""!=workFlowId){
            allData.workFlowId = workFlowId;
        }else {
            tipDialog("数据异常，请刷新后重新添加！", 4, 'warning');
            Loading(false,"","#updateWorkProcessModal");
            return false;
        }
        let workProcessId = $.trim($("#UpdateWorkProcessId").val());
        if (""!=workProcessId){
            allData.workProcessId = workProcessId;
        }else {
            tipDialog("数据异常，请刷新后重新添加！", 4, 'warning');
            Loading(false,"","#updateWorkProcessModal");
            return false;
        }
        let cName = $.trim($("#UpdateWorkProcessCName").val());
        if (""!=cName){
            allData.cname = cName;
        }else {
            tipDialog("请选择工艺名称！", 4, 'warning');
            Loading(false,"","#updateWorkProcessModal");
            return false;
        }
        let nameId = $.trim($("#UpdateNameId").val());
        if (""!=nameId){
            allData.nameId = nameId;
        }else {
            tipDialog("工艺名称有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
            Loading(false,"","#updateWorkProcessModal");
            return false;
        }
        let isWorkshopRecord = $.trim($("#updateIsWorkshopRecord").val());
        if (""!=isWorkshopRecord){
            allData.isWorkshopRecord = isWorkshopRecord;
            let res = false;

            let length1=$("#Update_Grid_Field1 tbody>tr").length;
            let length2=$("#Update_Grid_Field2 tbody>tr").length;
            let length3=$("#Update_Grid_Field3 tbody>tr").length;

            for(let i=0;i<length1;i++) {
                let field = {
                    formAttributeId: "",
                    propertyName: "",
                    controlType: "",
                    restrictiveConditions: "",
                    dataSourceType: "",
                    dataSourceCode: "",
                    dataSource: "",
                    listIndex: "",
                    handleType: 1
                };
                field.listIndex = (i+1);
                let formAttributeId = $("#Update_Grid_Field1 tbody>tr").eq(i).find("input[name=updateFormAttributeId]").attr("value");
                if (""!=formAttributeId&&undefined!=formAttributeId){
                    formAttributeId = formAttributeId.replace(/,/g,"");
                    field.formAttributeId = parseInt(formAttributeId);
                }
                field.propertyName = $("#Update_Grid_Field1 tbody>tr").eq(i).find("div:eq(0)").text();
                let controlType =$("#Update_Grid_Field1 tbody>tr").eq(i).find("select[name=updateControlType] :selected").attr("value");
                if (""!=controlType){
                    field.controlType =controlType;
                }else {
                    tipDialog("操作记录第"+(i+1)+"行，类型有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                    Loading(false,"","#updateWorkProcessModal");
                    return false;
                }
                let restrictiveConditions =$("#Update_Grid_Field1 tbody>tr").eq(i).find("select[name=updateRestrictiveConditions] :selected").attr("value");
                if (""!=restrictiveConditions){
                    field.restrictiveConditions =restrictiveConditions;
                }else {
                    tipDialog("操作记录第"+(i+1)+"行，限制条件有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                    Loading(false,"","#updateWorkProcessModal");
                    return false;
                }
                let dataSourceType =$("#Update_Grid_Field1 tbody>tr").eq(i).find("select[name=updateDataSourceType] :selected").attr("value");
                if (""!=dataSourceType){
                    if (2==controlType){
                        if(0==dataSourceType){
                            tipDialog("操作记录第" + (i + 1) + "行，数据源类型有误，请重新选择，如果还不行，请联系管理员！(下拉框不能选择固定)", 4, 'warning');
                            Loading(false, "", "#updateWorkProcessModal");
                            return false;
                        }else {
                            field.dataSourceType = dataSourceType;
                        }
                    }else {
                        field.dataSourceType = dataSourceType;
                    }
                }else {
                    tipDialog("操作记录第"+(i+1)+"行，数据源类型有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                    Loading(false,"","#updateWorkProcessModal");
                    return false;
                }
                if(1==dataSourceType){
                    let dataSourceCode =$("#Update_Grid_Field1 tbody>tr").eq(i).find("input[name=updateDataSourceCode]").val();
                    if (""!=dataSourceCode){
                        field.dataSourceCode =dataSourceCode;
                    }else {
                        tipDialog("操作记录第" + (i + 1) + "行，数据源有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                        Loading(false,"","#updateWorkProcessModal");
                        return false;
                    }
                    let dataSource =$("#Update_Grid_Field1 tbody>tr").eq(i).find("input[name=updateDataSource]").val();
                    if (""!=dataSource){
                        field.dataSource =dataSource;
                    }else {
                        tipDialog("操作记录第" + (i + 1) + "行，数据源有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                        Loading(false,"","#updateWorkProcessModal");
                        return false;
                    }
                }
                tableField1.push(field);
            }
            for(let i=0;i<length2;i++) {
                let field = {
                    formAttributeId: "",
                    propertyName: "",
                    controlType: "",
                    restrictiveConditions: "",
                    dataSourceType: "",
                    dataSourceCode: "",
                    dataSource: "",
                    listIndex: "",
                    handleType: 2
                };
                field.listIndex = (i+1);
                let formAttributeId =$("#Update_Grid_Field2 tbody>tr").eq(i).find("input[name=updateFormAttributeId]").attr("value");
                if (""!=formAttributeId&&undefined!=formAttributeId){
                    formAttributeId = formAttributeId.replace(/,/g,"");
                    field.formAttributeId =parseInt(formAttributeId);
                }
                field.propertyName = $("#Update_Grid_Field2 tbody>tr").eq(i).find("div:eq(0)").text();
                let controlType =$("#Update_Grid_Field2 tbody>tr").eq(i).find("select[name=updateControlType] :selected").attr("value");
                if (""!=controlType){
                    field.controlType =controlType;
                }else {
                    tipDialog("审核记录第"+(i+1)+"行，类型有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                    Loading(false,"","#updateWorkProcessModal");
                    return false;
                }
                let restrictiveConditions =$("#Update_Grid_Field2 tbody>tr").eq(i).find("select[name=updateRestrictiveConditions] :selected").attr("value");
                if (""!=restrictiveConditions){
                    field.restrictiveConditions =restrictiveConditions;
                }else {
                    tipDialog("审核记录第"+(i+1)+"行，限制条件有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                    Loading(false,"","#updateWorkProcessModal");
                    return false;
                }
                let dataSourceType =$("#Update_Grid_Field2 tbody>tr").eq(i).find("select[name=updateDataSourceType] :selected").attr("value");
                if (""!=dataSourceType){
                    if (2==controlType){
                        if(0==dataSourceType){
                            tipDialog("审核记录第" + (i + 1) + "行，数据源类型有误，请重新选择，如果还不行，请联系管理员！(下拉框不能选择固定)", 4, 'warning');
                            Loading(false, "", "#updateWorkProcessModal");
                            return false;
                        }else {
                            field.dataSourceType = dataSourceType;
                        }
                    }else {
                        field.dataSourceType = dataSourceType;
                    }
                }else {
                    tipDialog("审核记录第"+(i+1)+"行，数据源类型有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                    Loading(false,"","#updateWorkProcessModal");
                    return false;
                }
                if(1==dataSourceType) {
                    let dataSourceCode = $("#Update_Grid_Field2 tbody>tr").eq(i).find("input[name=updateDataSourceCode]").val();
                    if ("" != dataSourceCode) {
                        field.dataSourceCode = dataSourceCode;
                    } else {
                        tipDialog("审核记录第" + (i + 1) + "行，数据源有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                        Loading(false,"","#updateWorkProcessModal");
                        return false;
                    }
                    let dataSource = $("#Update_Grid_Field2 tbody>tr").eq(i).find("input[name=updateDataSource]").val();
                    if ("" != dataSource) {
                        field.dataSource = dataSource;
                    } else {
                        tipDialog("审核记录第" + (i + 1) + "行，数据源有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                        Loading(false,"","#updateWorkProcessModal");
                        return false;
                    }
                }
                tableField2.push(field);
            }
            for(let i=0;i<length3;i++) {
                let field = {
                    formAttributeId: "",
                    propertyName: "",
                    controlType: "",
                    restrictiveConditions: "",
                    dataSourceType: "",
                    dataSourceCode: "",
                    dataSource: "",
                    listIndex: "",
                    handleType: 3
                };
                field.listIndex = (i+1);
                let formAttributeId =$("#Update_Grid_Field3 tbody>tr").eq(i).find("input[name=updateFormAttributeId]").attr("value");
                if (""!=formAttributeId&&undefined!=formAttributeId){
                    formAttributeId = formAttributeId.replace(/,/g,"");
                    field.formAttributeId =parseInt(formAttributeId);
                }
                field.propertyName = $("#Update_Grid_Field3 tbody>tr").eq(i).find("div:eq(0)").text();
                let controlType =$("#Update_Grid_Field3 tbody>tr").eq(i).find("select[name=updateControlType] :selected").attr("value");
                if (""!=controlType){
                    field.controlType =controlType;
                }else {
                    tipDialog("巡检记录第"+(i+1)+"行，类型有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                    Loading(false,"","#updateWorkProcessModal");
                    return false;
                }
                let restrictiveConditions =$("#Update_Grid_Field3 tbody>tr").eq(i).find("select[name=updateRestrictiveConditions] :selected").attr("value");
                if (""!=restrictiveConditions){
                    field.restrictiveConditions =restrictiveConditions;
                }else {
                    tipDialog("巡检记录第"+(i+1)+"行，限制条件有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                    Loading(false,"","#updateWorkProcessModal");
                    return false;
                }
                let dataSourceType =$("#Update_Grid_Field3 tbody>tr").eq(i).find("select[name=updateDataSourceType] :selected").attr("value");
                if (""!=dataSourceType){
                    if (2==controlType){
                        if(0==dataSourceType){
                            tipDialog("巡检记录第" + (i + 1) + "行，数据源类型有误，请重新选择，如果还不行，请联系管理员！(下拉框不能选择固定)", 4, 'warning');
                            Loading(false, "", "#updateWorkProcessModal");
                            return false;
                        }else {
                            field.dataSourceType = dataSourceType;
                        }
                    }else {
                        field.dataSourceType = dataSourceType;
                    }
                }else {
                    tipDialog("巡检记录第"+(i+1)+"行，数据源类型有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                    Loading(false,"","#updateWorkProcessModal");
                    return false;
                }
                if(1==dataSourceType) {
                    let dataSourceCode = $("#Update_Grid_Field3 tbody>tr").eq(i).find("input[name=updateDataSourceCode]").val();
                    if ("" != dataSourceCode) {
                        field.dataSourceCode = dataSourceCode;
                    } else {
                        tipDialog("巡检记录第" + (i + 1) + "行，数据源有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                        Loading(false,"","#updateWorkProcessModal");
                        return false;
                    }
                    let dataSource = $("#Update_Grid_Field3 tbody>tr").eq(i).find("input[name=updateDataSource]").val();
                    if ("" != dataSource) {
                        field.dataSource = dataSource;
                    } else {
                        tipDialog("巡检记录第" + (i + 1) + "行，数据源有误，请重新选择，如果还不行，请联系管理员！", 4, 'warning');
                        Loading(false,"","#updateWorkProcessModal");
                        return false;
                    }
                }
                tableField3.push(field);
            }

            $("#Update_Grid_Field1 tbody>tr>td>div").each(function(){
                let idx = $(this).parent().parent().find("td").index($(this).parent());
                if (5!=idx) {
                    if ("" == $(this).text()) {
                        let num = $(this).parents("tr").find("td").eq(0).text();
                        tipDialog("操作记录，第" + num + "行有空！", 3, 'warning');
                        tableField1 = new Array();
                        res = true;
                    }
                }
            });
            $("#Update_Grid_Field2 tbody>tr>td>div").each(function(){
                let idx = $(this).parent().parent().find("td").index($(this).parent());
                if (5!=idx) {
                    if ("" == $(this).text()) {
                        let num = $(this).parents("tr").find("td").eq(0).text();
                        tipDialog("审核记录，第" + num + "行有空！", 3, 'warning');
                        tableField2 = new Array();
                        res = true;
                    }
                }
            });
            $("#Update_Grid_Field3 tbody>tr>td>div").each(function(){
                let idx = $(this).parent().parent().find("td").index($(this).parent());
                if (5!=idx) {
                    if ("" == $(this).text()) {
                        let num = $(this).parents("tr").find("td").eq(0).text();
                        tipDialog("巡检记录，第" + num + "行有空！", 3, 'warning');
                        tableField3 = new Array();
                        res = true;
                    }
                }
            });

            allData.tableField1 = tableField1;
            allData.tableField2 = tableField2;
            allData.tableField3 = tableField3;
            if (res){
                allData="";
                Loading(false,"","#updateWorkProcessModal");
                return false;
            }
        }
        $.ajax({
            type: "POST",
            url: "${request.contextPath}/workProcess/updateWorkProcess.json",
            data:JSON.stringify(allData),
            contentType: "application/json;charset=utf-8;",
            dataType: "json",
            async:false,
            success: function (res) {
                $("#workFlowGridTable").trigger("reloadGrid");
                if (res.success){
                    RowIndex();
                    tipDialog(res.msg,4,1);
                    $('#updateWorkProcessModal').modal('hide');
                }else {
                    tipDialog(res.msg,4,0);
                }
                Loading(false,"","#updateWorkProcessModal");
            },
            error: function (xhr) {
                tipDialog("网络异常",4,0);
                Loading(false,"","#updateWorkProcessModal");
            }
        });
        allData="";
    });

</@shiro.hasPermission>

<@shiro.hasPermission name="/workProcess/getFormAttribute">
//    查看工序详情
    function getFormAttribute() {
        var seaId = GetJqGridRowValue("#workFlowGridTable", "workProcessId");
        if(IsChecked(seaId)){
            $("#workProcessDetailModal").modal({
                remote: "${request.contextPath}/workProcess/workProcessDetailModal.htm?id=" + seaId
            });
        }
    }
</@shiro.hasPermission>

<@shiro.hasPermission name="/workProcess/deleteWorkProcess">
//    删除工序
    function deleteWorkProcess() {
        var id = GetJqGridRowValue("#workFlowGridTable", "workProcessId");
        if (IsChecked(id)) {
            $("#deleteWorkProcessModal").modal("show");
        }
    }
    $("#deleteWorkProcess").unbind("click");
    $("#deleteWorkProcess").click(function () {
        Loading(true,"正在删除数据...","#deleteWorkProcessModal");
        var id = GetJqGridRowValue("#workFlowGridTable", "workProcessId");
        $.post('${request.contextPath}/workProcess/deleteWorkProcess.json',{id: id,resourceId:resourceId,},function (result) {
            if(result.success){
                GetTree();
                $("#workFlowGridTable").trigger("reloadGrid");
                tipDialog(result.msg, 4, '1');
                $("#deleteWorkProcessModal").modal("hide");
            }else {
                tipDialog(result.msg, 4, '0');
            }
            Loading(false,"","#deleteWorkProcessModal");
        }, "JSON");
    });

</@shiro.hasPermission>
</script>