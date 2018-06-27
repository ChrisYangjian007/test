

<div class="tools_bar leftline rightline" >
    <div class="PartialButton">
    <@shiro.hasPermission name="/sysUnit/reload">
        <a id="lr-replace" title="刷新当前(Ctrl+Q)" onclick="Replace()" class="tools_btn">
                <span>
                    <b class="btn-reload">刷新</b>
                </span>
        </a>
        <div class="tools_separator"></div>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/sysUnit/addSysUnit">
        <a id="lr-add" title="新增(Ctrl+Z)" onclick="addSysUnitModal()" class="tools_btn">
                <span>
                    <b class="btn-add">新增</b>
                </span>
        </a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/sysUnit/updateSysUnit">
        <a id="lr-edit" title="编辑(Ctrl+W)" onclick="updateSysUnitModal()" class="tools_btn">
                <span>
                    <b class="btn-update">编辑</b>
                </span>
        </a>
    </@shiro.hasPermission>
    <#--<@shiro.hasPermission name="/sysUnit/deleteSysUnit">-->
        <#--<a id="lr-delete" title="删除(Ctrl+S)" onclick="" class="tools_btn">-->
                <#--<span>-->
                    <#--<b class="btn-delete">删除</b>-->
                <#--</span>-->
        <#--</a>-->
    <#--</@shiro.hasPermission>-->
    <@shiro.hasPermission name="/sysUnit/back">
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
    <div id="gridPager"></div>
</div>


<@shiro.hasPermission name="/sysUnit/addSysUnit">
<!--添加单位-->
<div id="addUnitModal" class="modal fade " data-width="450" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">添加单位</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="addSysUnit" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>


<@shiro.hasPermission name="/sysUnit/updateSysUnit">
<!--修改单位-->
<div id="updateUnitModal" class="modal fade " data-width="450" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">修改单位</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="updateSysUnit" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>

<script>
    var resourceId;
    $(document).ready(function () {
        GetGrid();
        resourceId= top.$("#ModuleId").val();
    });
    //加载表格
    function GetGrid() {
        $("#gridTable").jqGrid({
                url: "${request.contextPath}/sysUnit/GridJson.json",
                datatype: "json",
                treeGrid: true,
                treeGridModel: "adjacency",
                ExpandColumn: "cname",//展开的列
                ExpandColClick:true,//树形表格是否展开
                height: $(window).height() - 85,
                autowidth: true,
                colModel: [
                { label: '主键', name: 'unitId', index: 'unitId', width: 80, hidden: true, key: true },
                { label: "名称", name: "cname", index: "cName", width: 200 },
                { label: "换算值", name: "value", index: "value", width: 100 },
                { label: '描述', name: 'remark', index: 'remark', width: 500 }
                ],
                pager: "false",
                viewrecords:true,
                jsonReader: {           // 设置读取数据时的字段
                    repeatitems: false  // 如果设为false，则jqGrid在解析json时，会根据name来搜索对应的数据元素
                },
                treeReader: {           //设置树形显示时4个关键字段对应的返回数据字段
                    level_field: "level",      // 属性层级
                    parent_id_field: "parent", //父级
                    leaf_field: "isLeaf",      //是否还有子级菜单
                    expanded_field: "expanded" //是否加载完毕
                },
                shrinkToFit: false
        });
    }


<@shiro.hasPermission name="/sysUnit/addSysUnit">
// 添加
    function addSysUnitModal() {
        $("#addUnitModal").modal({
            remote: "${request.contextPath}/sysUnit/addSysUnitModal.htm?resourceId="+resourceId
        });
    }

    $("#addSysUnit").unbind("click");
    $("#addSysUnit").click(function () {
        Loading(true,"正在提交...","#addUnitModal");
        var cName = $("#cName").val();
        if(""==cName){
            $("#cName").focus();
            tipDialog("单位名称不能为空",4,"warning");
            Loading(false,"","#addUnitModal");
            return false;
        }
        var value =  $("#value").val();
        if(""==value){
            $("#value").focus();
            tipDialog("换算值不能为空",4,"warning");
            Loading(false,"","#addUnitModal");
            return false;
        }
        $.post("${request.contextPath}/sysUnit/addSysUnit.json", $("#addUnitForm").serialize(), function (result) {
            if(result.success){
                $("#gridTable").trigger("reloadGrid");
                tipDialog(result.msg, 4, '1');
                $("#addUnitModal").modal('hide');
            }else {
                tipDialog(result.msg, 4, '0');
            }
            Loading(false,"","#addUnitModal");
        }, "JSON");
    });
</@shiro.hasPermission>


<@shiro.hasPermission name="/sysUnit/updateSysUnit">
//修改
    function updateSysUnitModal() {
        var id = GetJqGridRowValue("#gridTable", "unitId");
        if(IsChecked(id)){
            $("#updateUnitModal").modal({
                remote: "${request.contextPath}/sysUnit/updateSysUnitModal.htm?id=" + id+"&resourceId="+resourceId
            });
        }
    }

    $("#updateSysUnit").unbind("click");
    $("#updateSysUnit").click(function () {
        Loading(true,"正在提交...","#updateUnitModal");
        var cName = $("#updCName").val();
        if(""==cName){
            $("#updCName").focus();
            tipDialog("单位名称不能为空",4,"warning");
            Loading(false,"","#updateUnitModal");
            return false;
        }
        var value =  $("#updValue").val();
        if(""==value){
            $("#updValue").focus();
            tipDialog("换算值不能为空",4,"warning");
            Loading(false,"","#updateUnitModal");
            return false;
        }else {
            $("#updValue").val(value.replace(/,/gi,''));
        }
        $.post("${request.contextPath}/sysUnit/updateSysUnit.json", $("#updateUnitForm").serialize(), function (result) {
            if(result.success){
                $("#gridTable").trigger("reloadGrid");
                tipDialog(result.msg, 4, '1');
                $("#updateUnitModal").modal('hide');
            }else {
                tipDialog(result.msg, 4, '0');
            }
            Loading(false,"","#updateUnitModal");
        }, "JSON");
    });
</@shiro.hasPermission>

</script>