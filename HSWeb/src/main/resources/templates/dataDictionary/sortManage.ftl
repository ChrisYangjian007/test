
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
            <@shiro.hasPermission name="/dataDictionarySortManage/reload">
                <a id="lr-replace" title="刷新当前(Ctrl+Q)" onclick="Replace()" class="tools_btn">
                    <span>
                        <b class="btn-reload">刷新</b>
                    </span>
                </a>
                <div class="tools_separator"></div>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/dataDictionarySortManage/addDataDictionary">
                <a id="lr-add" title="新增(Ctrl+Z)" onclick="addDataDictionary()" class="tools_btn">
                    <span>
                        <b class="btn-add">新增</b>
                    </span>
                </a>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/dataDictionarySortManage/updateDataDictionary">
                <a id="lr-edit" title="编辑(Ctrl+W)" onclick="updateDataDictionaryModal()" class="tools_btn">
                    <span>
                        <b class="btn-update">编辑</b>
                    </span>
                </a>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/dataDictionarySortManage/deleteDataDictionary">
                <a id="lr-delete" title="删除(Ctrl+S)" onclick="deleteDataDictionary()" class="tools_btn">
                    <span>
                        <b class="btn-delete">删除</b>
                    </span>
                </a>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/dataDictionarySortManage/dataDictionaryDetail">
                <a id="lr-detail" title="详细信息(Ctrl+X)" onclick="showDataDictionaryModal()" class="tools_btn">
                    <span>
                        <b class="btn-detail">详细</b>
                    </span>
                </a>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/dataDictionarySortManage/back">
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
        <div id="gridPager"></div>
    </div>
</div>


<@shiro.hasPermission name="/dataDictionarySortManage/addDataDictionary">
<!--添加数据字典-->
<div id="addBaDataDictionaryModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">添加数据字典</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="addBaDataDictionary" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>


<@shiro.hasPermission name="/dataDictionarySortManage/updateDataDictionary">
<!--修改数据字典-->
<div id="updateBaDataDictionaryModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">修改数据字典</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="updateBaDataDictionary" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>
<#--删除确认-->
<@shiro.hasPermission name="/dataDictionarySortManage/deleteDataDictionary">
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


<@shiro.hasPermission name="/dataDictionarySortManage/dataDictionaryDetail">
<#--查看数据字典-->
<div id="showBaDataDictionaryModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">查看数据字典详情</h4>
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
        gridPagerStyle();
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
                    url: "${request.contextPath}/dataDictionary/GridJson.json?parentId=" + id, page: 1
                }).trigger('reloadGrid');
            },
            url: "${request.contextPath}/dataDictionary/TreeJson.json"
        };
        $("#ItemsTree").treeview(itemtree);
    }

    //加载表格
    function GetGrid() {
        $("#gridTable").jqGrid({
            url: "${request.contextPath}/dataDictionary/GridJson.json?parentId=1",
            datatype: "json",
            height: $(window).height() - 115,
            autowidth: true,
            colModel: [
                { label: '主键', name: 'dataDictionaryId', index: 'dataDictionaryId', hidden: true, key: true },
                { label: "编码", name: "code", index: "code", width: 150 },
                { label: "名称", name: "cname", index: "cname", width: 200 },
                {label: "层次", name: "alevel", index: "aLevel", width: 45, align: "center"},
                {
                    label: '有效', name: 'status', index: 'status', width: 45, align: 'center',
                    formatter: function (cellvalue, options, rowObject) {
                        if (cellvalue == null || cellvalue == undefined || cellvalue == 6) return "<img src='${staticImg}/images/checknomark.gif' />";
                        if (cellvalue == 1) return "<img src='${staticImg}/images/checkokmark.gif' />";else return "<img src='${staticImg}/images/checknomark.gif' />";
                    }
                },
                {label: "说明", name: "remark", index: "remark", width: 400},
                {
                    label: "创建时间", name: "createDate", index: "createDate", width: 150,
                    formatter: function (cellvalue, options, rowObject) {
                        return formatDate(cellvalue, 'yyyy-MM-dd hh:mm:ss');
                    }
                }
            ],
            pagerpos : "right",
            viewrecords : true,
            recordpos : "left",
            pager: "#gridPager",
            rowNum : 10,
            rowList:[10,20,30,50,100],
            rownumbers: true,
            jsonReader:{
                root:"root",page:"page",total:"total",records:"records"
            },
            sortname: 'listIndex',
            sortorder: 'asc',
            shrinkToFit: false,
            gridview: true
        });
    }


    function gridPagerStyle() {
        var width = document.getElementById("gridPager_right").offsetWidth+5;
        $("#gridPager_right").attr("style","width:"+width);
    }

<@shiro.hasPermission name="/dataDictionarySortManage/addDataDictionary">
    //新增
    function addDataDictionary() {
        if(null != id){
            $("#addBaDataDictionaryModal").modal({
                remote: "${request.contextPath}/dataDictionary/addDataDictionaryModal.htm?id=" + id
            });
        }else {
            tipDialog("请先选择左边目录", 4, 'warning');
        }
    }
    //新增
    $("#addBaDataDictionary").unbind("click");
    $("#addBaDataDictionary").click(function () {
        Loading(true,"正在提交数据...","#addBaDataDictionaryModal");
        var code = $("#code").val();
        if(""==code){
            $("#code").focus();
            tipDialog("请输入编码", 4, 'warning');
            Loading(false,"","#addBaDataDictionaryModal");
            return false;
        }
        var cName = $("#cName").val();
        if(""==cName){
            $("#cName").focus();
            tipDialog("请输入名称", 4, 'warning');
            Loading(false,"","#addBaDataDictionaryModal");
            return false;
        }
        $.post('${request.contextPath}/dataDictionary/addBaDataDictionary.json',$("#addBaDataDictionaryForm").serialize(),function (result) {
            if(result.success){
                $("#gridTable").trigger("reloadGrid");
                GetTree();
                tipDialog(result.msg, 4, '1');
                $("#addBaDataDictionaryModal").modal('hide');
            }else {
                tipDialog(result.msg, 4, '0');
            }
            Loading(false,"","#addBaDataDictionaryModal");
        }, "JSON");
    });
</@shiro.hasPermission>

<@shiro.hasPermission name="/dataDictionarySortManage/updateDataDictionary">
    //修改
    function updateDataDictionaryModal() {
        var updId = GetJqGridRowValue("#gridTable", "dataDictionaryId");
        if(IsChecked(updId)){
            $("#updateBaDataDictionaryModal").modal({
                remote: "${request.contextPath}/dataDictionary/updateDataDictionaryModal.htm?id=" + updId
            });
        }
    }
    //修改
    $("#updateBaDataDictionary").unbind("click");
    $("#updateBaDataDictionary").click(function () {
        Loading(true,"正在提交数据...","#updateBaDataDictionaryModal");
        var code = $("#updCode").val();
        if(""==code){
            $("#updCode").focus();
            tipDialog("请输入编码", 4, 'warning');
            Loading(false,"","#updateBaDataDictionaryModal");
            return false;
        }
        var cName = $("#updCName").val();
        if(""==cName){
            $("#updCName").focus();
            tipDialog("请输入名称", 4, 'warning');
            Loading(false,"","#updateBaDataDictionaryModal");
            return false;
        }
        $.post('${request.contextPath}/dataDictionary/updateBaDataDictionary.json',$("#updateBaDataDictionaryForm").serialize(),function (result) {
            if(result.success){
                $("#gridTable").trigger("reloadGrid");
                GetTree();
                tipDialog(result.msg, 4, '1');
                $("#updateBaDataDictionaryModal").modal('hide');
            }else {
                tipDialog(result.msg, 4, '0');
            }
            Loading(false,"","#updateBaDataDictionaryModal");
        }, "JSON");
    });
</@shiro.hasPermission>
    //删除
    <@shiro.hasPermission name="/dataDictionarySortManage/deleteDataDictionary">
    function deleteDataDictionary() {
        var updId = GetJqGridRowValue("#gridTable", "dataDictionaryId");
        if (IsChecked(updId)) {
            $("#deleteDataDictionaryModal").modal("show");
        }
    }
    $("#deleteDataDictionary").unbind("click");
    $("#deleteDataDictionary").click(function () {
        Loading(true,"正在删除数据...","#deleteDataDictionaryModal");
        var updId = GetJqGridRowValue("#gridTable", "dataDictionaryId");
        $.post('${request.contextPath}/dataDictionary/deleteBaDataDictionary.json',{id: updId}, function (res) {
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

<@shiro.hasPermission name="/dataDictionarySortManage/dataDictionaryDetail">
    //查看详情
    function showDataDictionaryModal() {
         var updId = GetJqGridRowValue("#gridTable", "dataDictionaryId");
        if(IsChecked(updId)){
            $("#showBaDataDictionaryModal").modal({
                remote: "${request.contextPath}/dataDictionary/showDataDictionaryModal.htm?id=" + updId
            });
        }
    }
</@shiro.hasPermission>
</script>